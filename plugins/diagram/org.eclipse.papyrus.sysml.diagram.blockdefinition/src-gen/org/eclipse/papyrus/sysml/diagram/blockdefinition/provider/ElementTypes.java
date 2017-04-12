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
package org.eclipse.papyrus.sysml.diagram.blockdefinition.provider;

import org.eclipse.gmf.runtime.emf.type.core.AbstractElementTypeEnumerator;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.CommentBodyEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.CommentBodyEditPartCN;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ConstraintBodyEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ConstraintBodyEditPartCN;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ConstraintNameEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ConstraintNameEditPartCN;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.InstanceSpecificationNameEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.InstanceSpecificationNameEditPartCN;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.InstanceSpecificationSlotCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.InstanceSpecificationSlotCompartmentEditPartCN;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ModelNameEditPartCN;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ModelNameEditPartTN;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ModelPackageableElementCompartmentEditPartCN;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ModelPackageableElementCompartmentEditPartTN;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.PackageNameEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.PackageNameEditPartCN;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.PackagePackageableElementCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.PackagePackageableElementCompartmentEditPartCN;
import org.eclipse.papyrus.uml.diagram.clazz.providers.UMLElementTypes;

public class ElementTypes extends AbstractElementTypeEnumerator {

	/** ********************************************************* */
	/** SysML Block Definition Diagram specific elements **************** */
	/** ********************************************************* */

	/** SysML Block Definition Diagram :: Diagram */
	public static final String DIAGRAM_ID = "BlockDefinition"; //$NON-NLS-1$




	/** ********************************************************* */
	/** SysML Block Definition Diagram - ClassDiagram related elements */
	/** ********************************************************* */


	/** ClassDiagram :: MODEL */
	public static final IHintedType MODEL = (IHintedType) UMLElementTypes.Model_Shape;

	public static final String MODEL_COMPARTMENT_PACKAGEABLE_ELEMENT_HINT = ModelPackageableElementCompartmentEditPartTN.VISUAL_ID;

	public static final String MODEL_LABEL_NAME_TN_HINT = ModelNameEditPartTN.VISUAL_ID;

	/** ClassDiagram :: MODEL_CN */
	public static final IHintedType MODEL_CN = (IHintedType) UMLElementTypes.Model_Shape_CN;

	public static final String MODEL_CN_COMPARTMENT_PACKAGEABLE_ELEMENT_HINT = ModelPackageableElementCompartmentEditPartCN.VISUAL_ID;

	public static final String MODEL_CN_LABEL_NAME_HINT = ModelNameEditPartCN.VISUAL_ID;

	/** ClassDiagram :: PACKAGE */
	public static final IHintedType PACKAGE = (IHintedType) UMLElementTypes.Package_Shape;

	public static final String PACKAGE_COMPARTMENT_PACKAGEABLE_ELEMENT_HINT = PackagePackageableElementCompartmentEditPart.VISUAL_ID;

	public static final String PACKAGE_LABEL_NAME_HINT = PackageNameEditPart.VISUAL_ID;

	/** ClassDiagram :: PACKAGE_CN */
	public static final IHintedType PACKAGE_CN = (IHintedType) UMLElementTypes.Package_Shape_CN;

	public static final String PACKAGE_CN_COMPARTMENT_PACKAGEABLE_ELEMENT_HINT = PackagePackageableElementCompartmentEditPartCN.VISUAL_ID;

	public static final String PACKAGE_CN_LABEL_NAME_HINT = PackageNameEditPartCN.VISUAL_ID;

	/** ClassDiagram :: INSTANCE_SPECIFICATION */
	public static final IHintedType INSTANCE_SPECIFICATION = (IHintedType) UMLElementTypes.InstanceSpecification_Shape;

	/** ClassDiagram :: INSTANCE_SPECIFICATION_LINK */
	public static final IHintedType INSTANCE_SPECIFICATION_LINK = (IHintedType) UMLElementTypes.InstanceSpecification_Edge;

	public static final String INSTANCE_SPECIFICATION_COMPARTMENT_SLOT_HINT = InstanceSpecificationSlotCompartmentEditPart.VISUAL_ID;

	public static final String INSTANCE_SPECIFICATION_LABEL_NAME_HINT = InstanceSpecificationNameEditPart.VISUAL_ID;

	/** ClassDiagram :: INSTANCE_SPECIFICATION_CN */
	public static final IHintedType INSTANCE_SPECIFICATION_CN = (IHintedType) UMLElementTypes.InstanceSpecification_Shape_CN;

	public static final String INSTANCE_SPECIFICATION_CN_COMPARTMENT_SLOT_HINT = InstanceSpecificationSlotCompartmentEditPartCN.VISUAL_ID;

	public static final String INSTANCE_SPECIFICATION_CN_LABEL_NAME_HINT = InstanceSpecificationNameEditPartCN.VISUAL_ID;

	/** ClassDiagram :: CONSTRAINT */
	public static final IHintedType CONSTRAINT = (IHintedType) UMLElementTypes.Constraint_PackagedElementShape;

	public static final String CONSTRAINT_LABEL_NAME_HINT = ConstraintNameEditPart.VISUAL_ID;

	public static final String CONSTRAINT_LABEL_BODY_HINT = ConstraintBodyEditPart.VISUAL_ID;

	/** ClassDiagram :: CONSTRAINT_CN */
	public static final IHintedType CONSTRAINT_CN = (IHintedType) UMLElementTypes.Constraint_PackagedElementShape_CN;

	public static final String CONSTRAINT_CN_LABEL_NAME_HINT = ConstraintNameEditPartCN.VISUAL_ID;

	public static final String CONSTRAINT_CN_LABEL_BODY_HINT = ConstraintBodyEditPartCN.VISUAL_ID;

	/** ClassDiagram :: COMMENT */
	public static final IHintedType COMMENT = (IHintedType) UMLElementTypes.Comment_Shape;

	public static final String COMMENT_LABEL_BODY_HINT = CommentBodyEditPart.VISUAL_ID;

	/** ClassDiagram :: COMMENT_CN */
	public static final IHintedType COMMENT_CN = (IHintedType) UMLElementTypes.Comment_Shape_CN;

	public static final String COMMENT_CN_LABEL_BODY_HINT = CommentBodyEditPartCN.VISUAL_ID;


	/** ClassDiagram :: COMMENT_ANNOTATED_ELEMENT */
	public static final IHintedType COMMENT_ANNOTATED_ELEMENT = (IHintedType) UMLElementTypes.Comment_AnnotatedElementEdge;

	/** ClassDiagram :: CONSTRAINT_CONSTRAINED_ELEMENT */
	public static final IHintedType CONSTRAINT_CONSTRAINED_ELEMENT = (IHintedType) UMLElementTypes.Constraint_ConstrainedElementEdge;

	/** ClassDiagram :: ChildLabelNodes */
	public static final IHintedType INSTANCE_SPECIFICATION_SLOT_CLN = (IHintedType) UMLElementTypes.Slot_SlotLabel;


	public static IElementType getElementTypeByID(String visualID) {
		if (ElementTypes.INSTANCE_SPECIFICATION_LINK.getSemanticHint().equalsIgnoreCase(visualID)) {
			return ElementTypes.INSTANCE_SPECIFICATION_LINK;
		}
		return null;
	}
}
