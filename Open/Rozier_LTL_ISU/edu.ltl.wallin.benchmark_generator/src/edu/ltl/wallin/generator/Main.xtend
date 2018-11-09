/*
 * generated by Xtext 2.14.0
 */
package edu.ltl.wallin.generator

import com.google.inject.Inject
import com.google.inject.Provider
import edu.ltl.wallin.LTLStandaloneSetup
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.generator.GeneratorContext
import org.eclipse.xtext.generator.GeneratorDelegate
import org.eclipse.xtext.generator.JavaIoFileSystemAccess
import org.eclipse.xtext.util.CancelIndicator
import org.eclipse.xtext.validation.CheckMode
import org.eclipse.xtext.validation.IResourceValidator
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl

class Main {

	def static main(String[] args) {
		var verbose = false;
		var output_filename = "BC_OUTPUT";
		if (args.empty) {
			System::err.println('Aborting: no formula provided!')
			return
		}else if(args.length < 2){
			System::err.println('Aborting: trace length or formula missing!')
			return
		}
		
		var i = 0;
		for(i = 0; i < args.length; i++){
			if(args.get(i).equals("-v")) verbose = true;
			if(args.get(i).equals("-o")) output_filename = args.get(i + 1);
		}
		
		val injector = new LTLStandaloneSetup().createInjectorAndDoEMFRegistration
		val main = injector.getInstance(Main)
		main.runGenerator(args.get(0), Integer::parseInt(args.get(1)), verbose, output_filename)
	}
		

	@Inject Provider<ResourceSet> resourceSetProvider

	@Inject IResourceValidator validator

	@Inject GeneratorDelegate generator

	@Inject JavaIoFileSystemAccess fileAccess

	def protected runGenerator(String string, int trace_length, boolean verbose, String output_filename) {
		// Load the resource
		val set = resourceSetProvider.get
		val resource = set.getResource(URI.createFileURI(string), true)

//		// Validate the resource
//		val issues = validator.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl)
//		if (!issues.empty) {
//			issues.forEach[System.err.println(it)]
//			return
//		}

		// Configure and start the generator
		fileAccess.outputPath = 'src-gen/'
		val context = new GeneratorContext => [
			cancelIndicator = CancelIndicator.NullImpl
		]
		LTLGenerator.doGenerate(resource, fileAccess, context, trace_length, verbose, output_filename)
		if(verbose) System.out.println('Code generation finished.')
	}
}