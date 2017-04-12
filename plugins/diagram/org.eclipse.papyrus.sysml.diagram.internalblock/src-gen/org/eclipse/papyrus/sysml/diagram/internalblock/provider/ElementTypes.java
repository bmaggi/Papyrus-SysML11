/*****************************************************************************
 * Copyright (c) 2011 CEA LIST.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *
 *		CEA LIST - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.sysml.diagram.internalblock.provider;

import org.eclipse.gmf.runtime.emf.type.core.AbstractElementTypeEnumerator;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.papyrus.uml.diagram.composite.edit.parts.CommentBodyEditPart;
import org.eclipse.papyrus.uml.diagram.composite.edit.parts.CommentBodyEditPartCN;
import org.eclipse.papyrus.uml.diagram.composite.edit.parts.ConstraintNameEditPart;
import org.eclipse.papyrus.uml.diagram.composite.edit.parts.ConstraintNameEditPartCN;
import org.eclipse.papyrus.uml.diagram.composite.edit.parts.ConstraintSpecificationEditPart;
import org.eclipse.papyrus.uml.diagram.composite.edit.parts.ConstraintSpecificationEditPartCN;
import org.eclipse.papyrus.uml.diagram.composite.providers.UMLElementTypes;

public class ElementTypes extends AbstractElementTypeEnumerator {

	/** ********************************************************* */
	/** SysML Internal Block Diagram specific elements **************** */
	/** ********************************************************* */

	/** SysML Internal Block Diagram :: Diagram */
	public static final String DIAGRAM_ID = "InternalBlock";

	/** ********************************************************* */
	/** SysML Internal Block Diagram - CompositeDiagram related elements */
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

	/** CompositeDiagram :: CONSTRAINT_CONSTRAINED_ELEMENT */
	public static final IHintedType CONSTRAINT_CONSTRAINED_ELEMENT = (IHintedType) UMLElementTypes.Constraint_ConstrainedElementEdge;

}
