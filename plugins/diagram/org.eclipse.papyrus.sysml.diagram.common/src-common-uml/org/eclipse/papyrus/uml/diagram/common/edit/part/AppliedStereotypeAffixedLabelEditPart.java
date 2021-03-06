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
package org.eclipse.papyrus.uml.diagram.common.edit.part;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.infra.gmfdiag.common.editpart.IPapyrusEditPart;
import org.eclipse.papyrus.uml.diagram.common.editpolicies.AppliedStereotypeExternalNodeEditPolicy;
import org.eclipse.papyrus.uml.diagram.common.editpolicies.AppliedStereotypeLabelDisplayEditPolicy;
import org.eclipse.papyrus.uml.diagram.common.editpolicies.IDirectEdition;
import org.eclipse.papyrus.uml.diagram.common.figure.node.AppliedStereotypeWrappingLabelFigure;

/**
 * This class implements an affixed label edit part for applied stereotype representation on border items.
 */
public class AppliedStereotypeAffixedLabelEditPart extends AbstractElementLabelEditPart implements IBorderItemEditPart, IPapyrusEditPart {

	/** Constructor */
	public AppliedStereotypeAffixedLabelEditPart(View view) {
		super(view);
		addSnapBackLocation();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(AppliedStereotypeLabelDisplayEditPolicy.STEREOTYPE_LABEL_POLICY, new AppliedStereotypeExternalNodeEditPolicy());
	}

	@Override
	public IBorderItemLocator getBorderItemLocator() {
		IFigure parentFigure = getFigure().getParent();
		if (parentFigure != null && parentFigure.getLayoutManager() != null) {
			Object constraint = parentFigure.getLayoutManager().getConstraint(getFigure());
			return constraint instanceof IBorderItemLocator ? (IBorderItemLocator) constraint : null;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected IFigure createFigure() {
		IFigure label = createFigurePrim();
		defaultText = getLabelTextHelper(label);
		return label;
	}

	protected IFigure createFigurePrim() {
		return new AppliedStereotypeWrappingLabelFigure();
	}

	@Override
	public IFigure getPrimaryShape() {
		return getFigure();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isEditable() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParserOptions getParserOptions() {
		return ParserOptions.NONE;
	}

	/**
	 * Forbid direct edition on this label.
	 */
	@Override
	public int getDirectEditionType() {
		// The label is read-only (defined in GMFGen model)
		return IDirectEdition.NO_DIRECT_EDITION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getLabelRole() {
		return "Stereotype";//$NON-NLS-1$
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getIconPathRole() {
		return "";//$NON-NLS-1$
	}
}
