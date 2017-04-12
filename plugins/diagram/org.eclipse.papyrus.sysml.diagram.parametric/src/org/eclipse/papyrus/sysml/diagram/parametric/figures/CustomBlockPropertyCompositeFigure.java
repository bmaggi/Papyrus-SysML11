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
package org.eclipse.papyrus.sysml.diagram.parametric.figures;

import org.eclipse.draw2d.Label;
import org.eclipse.papyrus.sysml.diagram.common.figure.BlockPropertyCompositeFigure;
import org.eclipse.papyrus.infra.gmfdiag.common.figure.node.PapyrusWrappingLabel;

public class CustomBlockPropertyCompositeFigure extends
		BlockPropertyCompositeFigure {

	@Override
	protected void initTagLabel(String value) {
		// do nothing: we don't want a two lines element (tag + name)
	}

	@Override
	public PapyrusWrappingLabel getTaggedLabel() {
		// return a mock Label
		return new PapyrusWrappingLabel();
	}

	@Override
	public void setNameLabelIcon(boolean displayNameLabelIcon) {
		getNameLabel().setIcon(null);
	}
}
