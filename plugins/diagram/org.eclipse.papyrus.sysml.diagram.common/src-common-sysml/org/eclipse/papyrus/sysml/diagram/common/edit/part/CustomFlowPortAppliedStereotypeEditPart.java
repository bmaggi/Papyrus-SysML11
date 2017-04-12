/**
 * Copyright (c) 2015 CEA LIST.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Nicolas FAUVERGUE (ALL4TEC) nicolas.fauvergue@all4tec.net - Bug 472932
 */
package org.eclipse.papyrus.sysml.diagram.common.edit.part;

import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.infra.gmfdiag.common.editpolicies.IMaskManagedLabelEditPolicy;
import org.eclipse.papyrus.infra.gmfdiag.common.editpolicies.IndirectMaskLabelEditPolicy;
import org.eclipse.papyrus.uml.diagram.common.editpolicies.AppliedStereotypeLabelDisplayEditPolicy;
import org.eclipse.papyrus.uml.diagram.composite.edit.parts.PortAppliedStereotypeEditPart;
import org.eclipse.papyrus.uml.diagram.composite.edit.policies.UMLTextSelectionEditPolicy;

/**
 * The custom edit policy for the port applied stereotype.
 */
public class CustomFlowPortAppliedStereotypeEditPart extends PortAppliedStereotypeEditPart {

	/**
	 * Constructor.
	 *
	 * @param view
	 *            The view
	 */
	public CustomFlowPortAppliedStereotypeEditPart(final View view) {
		super(view);
	}

	/**
	 * Redefine the refresh label to get the STEREOTYPE_LABEL_POLICY if exist and don't recalculate the text.
	 * 
	 * @see org.eclipse.papyrus.uml.diagram.composite.edit.parts.PortAppliedStereotypeEditPart#refreshLabel()
	 */
	@Override
	protected void refreshLabel() {
		EditPolicy maskLabelPolicy = getEditPolicy(IMaskManagedLabelEditPolicy.MASK_MANAGED_LABEL_EDIT_POLICY);
		if (maskLabelPolicy == null) {
			maskLabelPolicy = getEditPolicy(IndirectMaskLabelEditPolicy.INDRIRECT_MASK_MANAGED_LABEL);
		}
		// Bug 472932 : Stereotype is always displayed
		if (maskLabelPolicy == null) {
			maskLabelPolicy = getEditPolicy(AppliedStereotypeLabelDisplayEditPolicy.STEREOTYPE_LABEL_POLICY);
		}
		if (maskLabelPolicy == null) {
			final View view = (View) getModel();
			if (view.isVisible()) {
				setLabelTextHelper(getFigure(), getLabelText());
				setLabelIconHelper(getFigure(), getLabelIcon());
			} else {
				setLabelTextHelper(getFigure(), ""); //$NON-NLS-1$
				setLabelIconHelper(getFigure(), null);
			}
		}
		final Object pdEditPolicy = getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
		if (pdEditPolicy instanceof UMLTextSelectionEditPolicy) {
			((UMLTextSelectionEditPolicy) pdEditPolicy).refreshFeedback();
		}
		final Object sfEditPolicy = getEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE);
		if (sfEditPolicy instanceof UMLTextSelectionEditPolicy) {
			((UMLTextSelectionEditPolicy) sfEditPolicy).refreshFeedback();
		}
	}
}

