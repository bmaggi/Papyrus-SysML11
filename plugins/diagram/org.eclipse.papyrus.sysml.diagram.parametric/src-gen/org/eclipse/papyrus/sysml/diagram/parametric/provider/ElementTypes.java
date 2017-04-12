/*****************************************************************************
 * Copyright (c) 2013 CEA LIST.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *		R�gis CHEVREL: chevrel.regis <at> gmail.com
 *		CEA LIST - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.sysml.diagram.parametric.provider;

import org.eclipse.gmf.runtime.emf.type.core.AbstractElementTypeEnumerator;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.CommentBodyEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.CommentBodyEditPartCN;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ConstraintNameEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ConstraintNameEditPartCN;
import org.eclipse.papyrus.uml.diagram.composite.edit.parts.ConstraintSpecificationEditPart;
import org.eclipse.papyrus.uml.diagram.composite.edit.parts.ConstraintSpecificationEditPartCN;
import org.eclipse.papyrus.uml.diagram.composite.providers.UMLElementTypes;

public class ElementTypes extends AbstractElementTypeEnumerator {

	/** ********************************************************* */
	/** SysML Parametric Diagram specific elements **************** */
	/** ********************************************************* */

	/** SysML Parametric Diagram :: Diagram */
	public static final String DIAGRAM_ID = "Parametric";




	/** ********************************************************* */
	/** SysML Parametric Diagram - CompositeDiagram related elements */
	/** ********************************************************* */

	/** CompositeDiagram :: CONSTRAINT */
	public static final IHintedType CONSTRAINT = (IHintedType) UMLElementTypes.Constraint_Shape;

	public static final String CONSTRAINT_LABEL_NAME_HINT = ConstraintNameEditPart.VISUAL_ID;

	public static final String CONSTRAINT_LABEL_SPECIFICATION_HINT = ConstraintSpecificationEditPart.VISUAL_ID;

	/** CompositeDiagram :: COMMENT_CN */
	public static final IHintedType COMMENT_CN = (IHintedType) UMLElementTypes.Comment_Shape_CN;

	public static final String COMMENT_CN_LABEL_BODY_HINT = CommentBodyEditPartCN.VISUAL_ID;

	/** CompositeDiagram :: COMMENT */
	public static final IHintedType COMMENT = (IHintedType) UMLElementTypes.Comment_Shape;

	public static final String COMMENT_LABEL_BODY_HINT = CommentBodyEditPart.VISUAL_ID;

	/** CompositeDiagram :: CONSTRAINT_CN */
	public static final IHintedType CONSTRAINT_CN = (IHintedType) UMLElementTypes.Constraint_Shape_CN;

	public static final String CONSTRAINT_CN_LABEL_NAME_HINT = ConstraintNameEditPartCN.VISUAL_ID;

	public static final String CONSTRAINT_CN_LABEL_SPECIFICATION_HINT = ConstraintSpecificationEditPartCN.VISUAL_ID;

	/** CompositeDiagram :: COMMENT_ANNOTATED_ELEMENT */
	public static final IHintedType COMMENT_ANNOTATED_ELEMENT = (IHintedType) UMLElementTypes.Comment_AnnotatedElementEdge;

	/** CompositeDiagram :: CONTEXT_LINK */
	public static final IHintedType CONTEXT_LINK = (IHintedType) getElementType("org.eclipse.papyrus.sysml.diagram.parametric.Constraint_ContextEdge"); //$NON-NLS-1$

	/** CompositeDiagram :: CONContextLinkAppliedStereotype */
	public static final IHintedType CONTEXT_LINK_APPLIED_STEREOTYPE = (IHintedType) getElementType("org.eclipse.papyrus.sysml.diagram.parametric.Constraint_KeywordLabel"); //$NON-NLS-1$

}
