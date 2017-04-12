package org.eclipse.papyrus.sysml.diagram.common.utils;

import java.util.Collection;

import org.eclipse.papyrus.sysml.diagram.common.preferences.ILabelPreferenceConstants;
import org.eclipse.uml2.uml.MultiplicityElement;


public class SysMLMultiplicityElementUtil extends org.eclipse.papyrus.uml.tools.utils.MultiplicityElementUtil {

	/**
	 * If multiplicity is [1] (SysML default), only show when explicitly asked.
	 */
	public static String formatMultiplicity(MultiplicityElement element, Collection<String> maskValues) {
		return formatMultiplicity(element, !maskValues.contains(ILabelPreferenceConstants.DISP_DEFAULT_MULTIPLICITY));
	}

	public static String formatMultiplicity(MultiplicityElement element, boolean blockDefault) {
		String result = formatMultiplicity(element);
		return manageDefaultMultiplicity(result, blockDefault);
	}
}
