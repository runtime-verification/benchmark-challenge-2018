'use strict';

const stringify = require('./stringify-trunc')
const fs = require('fs')

exports.sendEvent = function sendEvent(data,logFd) {
	// prepare to send
	const body = {
		event: data.event,
		name: data.functionName,
		id: data.callId,
		res: data.result,
		args: Object.values(data.arguments),  // make it an array
		targetId: data.targetId,
		resultId: data.resultId,
		argIds: data.argIds
	};

	// serialize and write
	const serialized = stringify(body,{depth:5});
	fs.writeSync(logFd, serialized+'\n');
}
