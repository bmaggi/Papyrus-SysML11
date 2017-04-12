/*****************************************************************************
 * Copyright (c) 2015 CEA LIST and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Nicolas FAUVERGUE (ALL4TEC) nicolas.fauvergue@all4tec.net - Initial API and implementation
 *   
 *****************************************************************************/

package org.eclipse.papyrus.sysml.nattable.requirement.tests.paste.overwrite;

import java.util.Map;

import org.eclipse.nebula.widgets.nattable.selection.command.SelectRowsCommand;
import org.eclipse.papyrus.infra.nattable.handler.InsertInTableHandler;
import org.eclipse.papyrus.infra.nattable.manager.table.NattableModelManager;
import org.eclipse.papyrus.infra.nattable.utils.UserActionConstants;

/**
 * Test pastes overwrite all of selection with hidden categories.
 */
public class PasteEmptyOverwriteReplaceAll_Test extends AbstractPasteEmptyOverwriteTest {

	/**
	 * Constructor.
	 */
	public PasteEmptyOverwriteReplaceAll_Test() {
		super();
	}

	/**
	 * Manage the selection with the shift key.
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.uml.nattable.clazz.config.tests.paste.overwrite.AbstractPasteOverwriteTest#manageSelection(org.eclipse.papyrus.infra.nattable.manager.table.TreeNattableModelManager)
	 */
	@Override
	public void manageSelection(final NattableModelManager manager) throws Exception {
		super.manageSelection(manager);
		manager.getBodyLayerStack().getSelectionLayer().doCommand(new SelectRowsCommand(manager.getBodyLayerStack().getSelectionLayer(), 0, 2, true, false));
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.sysml.nattable.requirement.tests.paste.overwrite.AbstractPasteOverwriteTest#manageParameters(java.util.Map)
	 */
	@Override
	public void manageParameters(Map<Object, Object> parameters) {
		super.manageParameters(parameters);
		parameters.put(InsertInTableHandler.USER_ACTION__PREFERRED_USER_ACTION, UserActionConstants.REPLACE_USER_ACTION);
	}
}
