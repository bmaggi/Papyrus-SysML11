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
package org.eclipse.papyrus.sysml.diagram.internalblock.part;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.infra.gmfdiag.common.structure.DiagramStructure;
import org.eclipse.papyrus.uml.diagram.common.utils.UMLGraphicalTypes;
import org.eclipse.uml2.uml.UMLPackage;

/**
 *
 * @author vl222926
 *         Adapted code for the code generated for the UML Diagrams
 */
public class SysMLVisualIDRegistry {

	private static final String UNKNOWN = "-1"; //$NON-NLS-1$

	public static String getLinkWithClassVisualID(EObject domainElement) {
		if (domainElement == null) {
			return UNKNOWN;
		}
		if (UMLPackage.eINSTANCE.getConnector().isSuperTypeOf(domainElement.eClass())) {
			return UMLGraphicalTypes.LINK_UML_CONNECTOR_ID;
		}
		// if(UMLPackage.eINSTANCE.getAssociationClass().isSuperTypeOf(domainElement.eClass())) {
		// return AssociationClassLinkEditPart.VISUAL_ID;
		// }
		// if(UMLPackage.eINSTANCE.getAssociation().isSuperTypeOf(domainElement.eClass())) {
		// return AssociationEditPart.VISUAL_ID;
		// }
		// if(UMLPackage.eINSTANCE.getAssociation().isSuperTypeOf(domainElement.eClass())) {
		// return AssociationBranchEditPart.VISUAL_ID;
		// }
		// if(UMLPackage.eINSTANCE.getGeneralization().isSuperTypeOf(domainElement.eClass())) {
		// return GeneralizationEditPart.VISUAL_ID;
		// }
		// if(UMLPackage.eINSTANCE.getInterfaceRealization().isSuperTypeOf(domainElement.eClass())) {
		// return InterfaceRealizationEditPart.VISUAL_ID;
		// }
		// if(UMLPackage.eINSTANCE.getSubstitution().isSuperTypeOf(domainElement.eClass())) {
		// return SubstitutionEditPart.VISUAL_ID;
		// }
		// if(UMLPackage.eINSTANCE.getRealization().isSuperTypeOf(domainElement.eClass())) {
		// return RealizationEditPart.VISUAL_ID;
		// }
		// if(UMLPackage.eINSTANCE.getAbstraction().isSuperTypeOf(domainElement.eClass())) {
		// return AbstractionEditPart.VISUAL_ID;
		// }
		// if(UMLPackage.eINSTANCE.getUsage().isSuperTypeOf(domainElement.eClass())) {
		// return UsageEditPart.VISUAL_ID;
		// }
		if (UMLPackage.eINSTANCE.getDependency().isSuperTypeOf(domainElement.eClass())) {
			return UMLGraphicalTypes.LINK_UML_DEPENDENCY_ID;
		}
		// if(UMLPackage.eINSTANCE.getDependency().isSuperTypeOf(domainElement.eClass())) {
		// return DependencyBranchEditPart.VISUAL_ID;
		// }
		// if(UMLPackage.eINSTANCE.getElementImport().isSuperTypeOf(domainElement.eClass())) {
		// return ElementImportEditPart.VISUAL_ID;
		// }
		// if(UMLPackage.eINSTANCE.getPackageImport().isSuperTypeOf(domainElement.eClass())) {
		// return PackageImportEditPart.VISUAL_ID;
		// }
		// if(UMLPackage.eINSTANCE.getPackageMerge().isSuperTypeOf(domainElement.eClass())) {
		// return PackageMergeEditPart.VISUAL_ID;
		// }
		// if(UMLPackage.eINSTANCE.getProfileApplication().isSuperTypeOf(domainElement.eClass())) {
		// return ProfileApplicationEditPart.VISUAL_ID;
		// }
		// if(UMLPackage.eINSTANCE.getTemplateBinding().isSuperTypeOf(domainElement.eClass())) {
		// return TemplateBindingEditPart.VISUAL_ID;
		// }
		// if(UMLPackage.eINSTANCE.getGeneralizationSet().isSuperTypeOf(domainElement.eClass())) {
		// return GeneralizationSetEditPart.VISUAL_ID;
		// }
		// if(UMLPackage.eINSTANCE.getInstanceSpecification().isSuperTypeOf(domainElement.eClass())) {
		// return InstanceSpecificationLinkEditPart.VISUAL_ID;
		// }
		// if(UMLPackage.eINSTANCE.getInformationFlow().isSuperTypeOf(domainElement.eClass())) {
		// return InformationFlowEditPart.VISUAL_ID;
		// }
		return UNKNOWN;
	}

	// TODO : this class as not been tested...
	public static final DiagramStructure TYPED_INSTANCE = new DiagramStructure() {

		public String getVisualIDAsString(final View view) {
			return view.getType();
		}

		@Override
		public String getVisualID(View view) {// TODO : not useful with sysml, because we are using String!
			final String type = view.getType();
			return org.eclipse.papyrus.uml.diagram.composite.part.UMLVisualIDRegistry.getVisualID(view);
		}


		@Override
		public String getModelID(View view) {
			return org.eclipse.papyrus.uml.diagram.composite.part.UMLVisualIDRegistry.getModelID(view);
		}


		@Override
		public String getNodeVisualID(View containerView, EObject domainElement) {
			return org.eclipse.papyrus.uml.diagram.composite.part.UMLVisualIDRegistry.getNodeVisualID(containerView, domainElement);
		}


		@Override
		public boolean checkNodeVisualID(View containerView, EObject domainElement, String candidate) {
			return org.eclipse.papyrus.uml.diagram.composite.part.UMLVisualIDRegistry.checkNodeVisualID(containerView, domainElement, candidate);
		}


		@Override
		public boolean isCompartmentVisualID(String visualID) {
			return org.eclipse.papyrus.uml.diagram.composite.part.UMLVisualIDRegistry.isCompartmentVisualID(visualID);
		}


		@Override
		public boolean isSemanticLeafVisualID(String visualID) {
			return org.eclipse.papyrus.uml.diagram.composite.part.UMLVisualIDRegistry.isSemanticLeafVisualID(visualID);
		}
	};
}
