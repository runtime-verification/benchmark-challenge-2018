'use strict';

const monitor = require('./monitor.js');

exports.before = beforeFunction;
exports.after = afterFunction;

const supportedModules = ['fs','http'];
const supportedNames = J$.initParams.names;

//const filteredFunctionNames = ['http.createServer','write','writeHead','end']

// collect all http.ServerResponse (possibly inherited) methods
const http = require('http')
const functions = []
const response = http.ServerResponse.prototype
for (const k in response) {
	const obj = response[k]
	if (typeof obj === 'function')
		functions.push(obj)
}

function isInSupportedModule(data) {
	return supportedModules.some((m, i, a) => data.functionName.startsWith(m));
}

function traceCb(data) { // true if the callback needs to be traced
    return supportedNames.includes(data.functionName) || isInSupportedModule(data); 
}

function skip(data) { // optimization to monitor functions with names with initParams.names
    return !data.functionObject._jalangi_callId && !supportedNames.includes(data.functionName);
}

function beforeFunction(data,logFd) { 
    if(skip(data)) // optimization to monitor names with initParams.names
	return data;
    const args = data.arguments, argc = args.length;
    if (argc > 0 && typeof args[argc-1] === 'function' && traceCb(data)) {
	const cb = args[argc-1];
	// wrap the callback and store the call ID
	function wrapper() {
	    cb._jalangi_callId = data.callId;
	    return cb.apply(data.target, arguments);
	}
	args[argc-1] = wrapper;
    }
    // check if this function is a callback
    if (data.functionObject._jalangi_callId) {
	data.callId = data.functionObject._jalangi_callId;
	data.event = 'cb_pre';
    }
    else
	data.event = 'func_pre';
    monitor.sendEvent(data,logFd);
    return data;
}

function afterFunction(data,logFd) {
    if(!J$.initParams.func_post || skip(data))
	return data;
    if (data.functionObject._jalangi_callId) {
    	data.callId = data.functionObject._jalangi_callId;
    	data.event = 'cb_post';
    }
    else
    	data.event = 'func_post';	
    monitor.sendEvent(data,logFd);
    return data;
}
