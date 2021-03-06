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

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.CreateEditPoliciesOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.IEditPolicyProvider;
import org.eclipse.papyrus.sysml.diagram.blockdefinition.edit.policy.CustomDiagramDragDropEditPolicy;

public class BlockDefinitionDiagramEditPolicyProvider extends AbstractProvider implements IEditPolicyProvider {

	public boolean provides(IOperation operation) {

		CreateEditPoliciesOperation epOperation = (CreateEditPoliciesOperation) operation;
		if (!(epOperation.getEditPart() instanceof GraphicalEditPart)) {
			return false;
		}
		GraphicalEditPart gep = (GraphicalEditPart) epOperation.getEditPart();
		String diagramType = gep.getNotationView().getDiagram().getType();
		if (!ElementTypes.DIAGRAM_ID.equals(diagramType)) {
			return false;
		}

		if (gep instanceof org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ModelEditPartTN) {
			return true;
		}
		if (gep instanceof org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ModelPackageableElementCompartmentEditPartTN) {
			return true;
		}
		if (gep instanceof org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ModelEditPartCN) {
			return true;
		}
		if (gep instanceof org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ModelPackageableElementCompartmentEditPartCN) {
			return true;
		}
		if (gep instanceof org.eclipse.papyrus.uml.diagram.clazz.edit.parts.PackageEditPart) {
			return true;
		}
		if (gep instanceof org.eclipse.papyrus.uml.diagram.clazz.edit.parts.PackagePackageableElementCompartmentEditPart) {
			return true;
		}
		if (gep instanceof org.eclipse.papyrus.uml.diagram.clazz.edit.parts.PackageEditPartCN) {
			return true;
		}
		if (gep instanceof org.eclipse.papyrus.uml.diagram.clazz.edit.parts.PackagePackageableElementCompartmentEditPartCN) {
			return true;
		}
		if (gep instanceof org.eclipse.papyrus.uml.diagram.clazz.edit.parts.InstanceSpecificationEditPart) {
			return true;
		}
		if (gep instanceof org.eclipse.papyrus.uml.diagram.clazz.edit.parts.InstanceSpecificationSlotCompartmentEditPart) {
			return true;
		}
		if (gep instanceof org.eclipse.papyrus.uml.diagram.clazz.edit.parts.InstanceSpecificationEditPartCN) {
			return true;
		}
		if (gep instanceof org.eclipse.papyrus.uml.diagram.clazz.edit.parts.InstanceSpecificationSlotCompartmentEditPartCN) {
			return true;
		}
		if (gep instanceof org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ConstraintEditPart) {
			return true;
		}
		if (gep instanceof org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ConstraintEditPartCN) {
			return true;
		}
		if (gep instanceof org.eclipse.papyrus.uml.diagram.clazz.edit.parts.CommentEditPart) {
			return true;
		}
		if (gep instanceof org.eclipse.papyrus.uml.diagram.clazz.edit.parts.CommentEditPartCN) {
			return true;
		}

		return false;
	}

	public void createEditPolicies(EditPart editPart) {
		editPart.installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE, new CustomDiagramDragDropEditPolicy());
	}

}
