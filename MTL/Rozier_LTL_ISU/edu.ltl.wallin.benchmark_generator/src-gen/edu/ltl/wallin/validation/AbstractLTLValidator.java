/*
 * generated by Xtext 2.14.0
 */
package edu.ltl.wallin.validation;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.validation.AbstractDeclarativeValidator;

public abstract class AbstractLTLValidator extends AbstractDeclarativeValidator {
	
	@Override
	protected List<EPackage> getEPackages() {
		List<EPackage> result = new ArrayList<EPackage>();
		result.add(edu.ltl.wallin.lTL.LTLPackage.eINSTANCE);
		return result;
	}
}