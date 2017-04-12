/*****************************************************************************
 * Copyright (c) 2013 CEA LIST.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *		Régis CHEVREL: chevrel.regis <at> gmail.com
 *		CEA LIST - Initial API and implementation
 *		Trung-Truc Nguyen Bug 439935 
 *****************************************************************************/
package org.eclipse.papyrus.sysml.diagram.parametric.parser;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.infra.gmfdiag.common.commands.SemanticAdapter;
import org.eclipse.papyrus.sysml.constraints.ConstraintBlock;
import org.eclipse.papyrus.uml.diagram.common.parser.NamedElementLabelParser;
import org.eclipse.uml2.uml.util.UMLUtil;

public class ParametricFrameParser extends NamedElementLabelParser {

	/**
	 * /**
	 * Respect the SysML diagram frame "diagramKind [modelElementType] modelElementName [diagramName]"
	 * For the parametric diagram, the modelElementName might be a Block or a ConstraintBlock element
	 * 
	 * A parametric diagram frame, therefore, may be: 
	 * 
	 * par [Block] blockName [diagramName] OR
	 * par [ConstraintBlock] constraintBlockName [diagramName]
	 *
	 */
	@Override
	public String getPrintString(IAdaptable element, int flags) {
		String elementType = null;
		if(element instanceof SemanticAdapter){
			Object realObject = ((SemanticAdapter) element).getAdapter(EObject.class);
			if (realObject instanceof org.eclipse.uml2.uml.Class) {
				elementType = UMLUtil.getStereotypeApplication(((org.eclipse.uml2.uml.Class) realObject), ConstraintBlock.class) != null ? "ConstraintBlock" : "Block";
			}
		}
		return "par [" + elementType + "] " + super.getPrintString(element, flags);
	}
}
