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

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.infra.emf.appearance.helper.AppearanceHelper;
import org.eclipse.papyrus.infra.gmfdiag.common.figure.node.PapyrusWrappingLabel;
import org.eclipse.papyrus.sysml.diagram.common.Activator;
import org.eclipse.papyrus.uml.diagram.common.util.DiagramEditPartsUtil;
import org.eclipse.swt.graphics.Image;
import org.eclipse.uml2.uml.Port;

/**
 * This class implements a {@link Port} affixed label edit part.
 */
public class PortAffixedLabelNameEditPart extends AbstractElementLabelEditPart implements IBorderItemEditPart {

	/** Constructor */
	public PortAffixedLabelNameEditPart(View view) {
		super(view);
		addSnapBackLocation();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
	}

	/**
	 * @see org.eclipse.papyrus.infra.gmfdiag.common.editpart.PapyrusLabelEditPart#getDefaultTextAlignment()
	 *
	 * @return
	 */
	@Override
	protected int getDefaultTextAlignment() {
		return PositionConstants.LEFT;
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
	protected Image getLabelIcon() {
		EObject parserElement = getParserElement();
		if (parserElement == null) {
			return null;
		}

		List<View> views = DiagramEditPartsUtil.findViews(parserElement, getViewer());
		for (View view : views) {
			if (AppearanceHelper.showElementIcon(view)) {
				return Activator.getInstance().getLabelProvider().getImage(parserElement);
			}
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
		return new PapyrusWrappingLabel();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getLabelRole() {
		return "Label"; //$NON-NLS-1$
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getIconPathRole() {
		return ""; //$NON-NLS-1$
	}
}
