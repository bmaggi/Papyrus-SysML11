/*****************************************************************************
 * Copyright (c) 2013 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * CEA LIST- Initial API and implementation
 * Nizar GUEDIDI (CEA LIST)- modification
 *
 ****************************************************************************/
package org.eclipse.papyrus.sysml.diagram.requirement.provider;

import org.eclipse.gmf.runtime.emf.type.core.AbstractElementTypeEnumerator;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.AbstractionNameEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.AppliedStereotypeAbstractionEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.AppliedStereotypeDependencyEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.AppliedStereotypePackageImportEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.AppliedStereotypeRealizationEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ClassAttributeCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ClassAttributeCompartmentEditPartCN;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ClassNameEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ClassNameEditPartCN;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ClassNestedClassifierCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ClassNestedClassifierCompartmentEditPartCN;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ClassOperationCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ClassOperationCompartmentEditPartCN;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.CommentBodyEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.CommentBodyEditPartCN;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ConstraintBodyEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ConstraintBodyEditPartCN;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ConstraintNameEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ConstraintNameEditPartCN;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.DefaultNamedElementNameEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.DependencyNameEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.PackageNameEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.PackageNameEditPartCN;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.PackagePackageableElementCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.PackagePackageableElementCompartmentEditPartCN;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.RealizationNameEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.providers.UMLElementTypes;

public class ElementTypes extends AbstractElementTypeEnumerator {

	/** ********************************************************* */
	/** SysML Requirement Diagram specific elements **************** */
	/** ********************************************************* */

	/** SysML Requirement Diagram :: Diagram */
	public static final String DIAGRAM_ID = "RequirementDiagram"; //$NON-NLS-1$




	/** ********************************************************* */
	/** SysML Requirement Diagram - ClassDiagram related elements */
	/** ********************************************************* */


	/** ClassDiagram :: PACKAGE */
	public static final IHintedType PACKAGE = (IHintedType) UMLElementTypes.Package_Shape;

	public static final String PACKAGE_COMPARTMENT_PACKAGEABLE_ELEMENT_HINT = PackagePackageableElementCompartmentEditPart.VISUAL_ID;

	public static final String PACKAGE_LABEL_NAME_HINT = PackageNameEditPart.VISUAL_ID;

	/** ClassDiagram :: PACKAGE_CN */
	public static final IHintedType PACKAGE_CN = (IHintedType) UMLElementTypes.Package_Shape_CN;

	public static final String PACKAGE_CN_COMPARTMENT_PACKAGEABLE_ELEMENT_HINT = PackagePackageableElementCompartmentEditPartCN.VISUAL_ID;

	public static final String PACKAGE_CN_LABEL_NAME_HINT = PackageNameEditPartCN.VISUAL_ID;

	/** ClassDiagram :: CLASS */
	public static final IHintedType CLASS = (IHintedType) UMLElementTypes.Class_Shape;

	public static final String CLASS_COMPARTMENT_ATTRIBUTE_HINT = ClassAttributeCompartmentEditPart.VISUAL_ID;

	public static final String CLASS_COMPARTMENT_OPERATION_HINT = ClassOperationCompartmentEditPart.VISUAL_ID;

	public static final String CLASS_COMPARTMENT_NESTED_CLASSIFIER_HINT = ClassNestedClassifierCompartmentEditPart.VISUAL_ID;

	public static final String CLASS_LABEL_NAME_HINT = ClassNameEditPart.VISUAL_ID;

	/** ClassDiagram :: CLASS_CN */
	public static final IHintedType CLASS_CN = (IHintedType) UMLElementTypes.Class_Shape_CN;

	public static final String CLASS_CN_COMPARTMENT_ATTRIBUTE_HINT = ClassAttributeCompartmentEditPartCN.VISUAL_ID;

	public static final String CLASS_CN_COMPARTMENT_OPERATION_HINT = ClassOperationCompartmentEditPartCN.VISUAL_ID;

	public static final String CLASS_CN_COMPARTMENT_NESTED_CLASSIFIER_HINT = ClassNestedClassifierCompartmentEditPartCN.VISUAL_ID;

	public static final String CLASS_CN_LABEL_NAME_HINT = ClassNameEditPartCN.VISUAL_ID;

	/** ClassDiagram :: DEFAULT_NAMED_ELEMENT */
	public static final IHintedType DEFAULT_NAMED_ELEMENT = (IHintedType) UMLElementTypes.NamedElement_DefaultShape;

	public static final String DEFAULT_NAMED_ELEMENT_LABEL_NAME_HINT = DefaultNamedElementNameEditPart.VISUAL_ID;


	/** ClassDiagram :: COMMENT */
	public static final IHintedType COMMENT = (IHintedType) UMLElementTypes.Comment_Shape;

	public static final String COMMENT_LABEL_BODY_HINT = CommentBodyEditPart.VISUAL_ID;

	/** ClassDiagram :: COMMENT_CN */
	public static final IHintedType COMMENT_CN = (IHintedType) UMLElementTypes.Comment_Shape_CN;

	public static final String COMMENT_CN_LABEL_BODY_HINT = CommentBodyEditPartCN.VISUAL_ID;

	/** ClassDiagram :: CONSTRAINT */
	public static final IHintedType CONSTRAINT = (IHintedType) UMLElementTypes.Constraint_PackagedElementShape;

	public static final String CONSTRAINT_LABEL_NAME_HINT = ConstraintNameEditPart.VISUAL_ID;

	public static final String CONSTRAINT_LABEL_BODY_HINT = ConstraintBodyEditPart.VISUAL_ID;

	/** ClassDiagram :: CONSTRAINT_CN */
	public static final IHintedType CONSTRAINT_CN = (IHintedType) UMLElementTypes.Constraint_PackagedElementShape_CN;

	public static final String CONSTRAINT_CN_LABEL_NAME_HINT = ConstraintNameEditPartCN.VISUAL_ID;

	public static final String CONSTRAINT_CN_LABEL_BODY_HINT = ConstraintBodyEditPartCN.VISUAL_ID;


	/** ClassDiagram :: ABSTRACTION */
	public static final IHintedType ABSTRACTION = (IHintedType) UMLElementTypes.Abstraction_Edge;

	public static final String ABSTRACTION_LABEL_NAME_HINT = AbstractionNameEditPart.VISUAL_ID;

	public static final String ABSTRACTION_LABEL_APPLIED_STEREOTYPE_HINT = AppliedStereotypeAbstractionEditPart.VISUAL_ID;

	/** ClassDiagram :: CONTAINMENT_LINK */
	public static final IHintedType CONTAINMENT_LINK = (IHintedType) UMLElementTypes.Element_ContainmentEdge;


	/** ClassDiagram :: DEPENDENCY */
	public static final IHintedType DEPENDENCY = (IHintedType) UMLElementTypes.Dependency_Edge;

	public static final String DEPENDENCY_LABEL_NAME_HINT = DependencyNameEditPart.VISUAL_ID;

	public static final String DEPENDENCY_LABEL_APPLIED_STEREOTYPE_HINT = AppliedStereotypeDependencyEditPart.VISUAL_ID;

	/** ClassDiagram :: COMMENT_ANNOTATED_ELEMENT */
	public static final IHintedType COMMENT_ANNOTATED_ELEMENT = (IHintedType) UMLElementTypes.Comment_AnnotatedElementEdge;

	/** ClassDiagram :: CONSTRAINT_CONSTRAINED_ELEMENT */
	public static final IHintedType CONSTRAINT_CONSTRAINED_ELEMENT = (IHintedType) UMLElementTypes.Constraint_ConstrainedElementEdge;

	/** ClassDiagram :: REALIZATION */
	public static final IHintedType REALIZATION = (IHintedType) UMLElementTypes.Realization_Edge;

	public static final String REALIZATION_LABEL_APPLIED_STEREOTYPE_HINT = AppliedStereotypeRealizationEditPart.VISUAL_ID;

	public static final String REALIZATION_LABEL_NAME_HINT = RealizationNameEditPart.VISUAL_ID;

	/** ClassDiagram :: PACKAGE_IMPORT */
	public static final IHintedType PACKAGE_IMPORT = (IHintedType) UMLElementTypes.PackageImport_Edge;

	public static final String PACKAGE_IMPORT_LABEL_APPLIED_STEREOTYPE_HINT = AppliedStereotypePackageImportEditPart.VISUAL_ID;

	/** ClassDiagram :: ChildLabelNodes */
	public static final IHintedType CLASS_PROPERTY_CLN = (IHintedType) UMLElementTypes.Property_ClassAttributeLabel;

	public static final IHintedType CLASS_RECEPTION_CLN = (IHintedType) UMLElementTypes.Reception_ReceptionLabel;

	public static final IHintedType CLASS_OPERATION_CLN = (IHintedType) UMLElementTypes.Operation_ClassOperationLabel;

	public static final IHintedType CLASS_CLASS_CLN = (IHintedType) UMLElementTypes.Class_ClassNestedClassifierLabel;

	public static final IHintedType CLASS_INTERFACE_CLN = (IHintedType) UMLElementTypes.Interface_ClassNestedClassifierLabel;

	public static final IHintedType CLASS_ENUMERATION_CLN = (IHintedType) UMLElementTypes.Enumeration_ClassNestedClassifierLabel;

	public static final IHintedType CLASS_PRIMITIVE_TYPE_CLN = (IHintedType) UMLElementTypes.PrimitiveType_ClassNestedClassifierLabel;

	public static final IHintedType CLASS_DATA_TYPE_CLN = (IHintedType) UMLElementTypes.DataType_ClassNestedClassifierLabel;

	public static final IHintedType CLASS_SIGNAL_CLN = (IHintedType) UMLElementTypes.Signal_ClassNestedClassifierLabel;

}
