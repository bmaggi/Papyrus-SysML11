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

package org.eclipse.papyrus.sysml.nattable.requirement.tests.insert;

import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.selection.command.ClearAllSelectionsCommand;
import org.eclipse.nebula.widgets.nattable.selection.command.SelectRowsCommand;
import org.eclipse.papyrus.infra.nattable.manager.table.NattableModelManager;

/**
 * This class allows to manage the insert tests.
 */
public abstract class AbstractInsertRowsTest extends AbstractInsertTest {

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.uml.nattable.generic.tests.paste.overwrite.AbstractPasteOverwriteTest#manageSelection(org.eclipse.papyrus.infra.nattable.manager.table.NattableModelManager)
	 */
	@Override
	public void manageSelection(final NattableModelManager manager) throws Exception {
		final NatTable natTable = (NatTable) manager.getAdapter(NatTable.class);
		natTable.doCommand(new ClearAllSelectionsCommand());
		manager.getBodyLayerStack().getSelectionLayer().doCommand(new SelectRowsCommand(manager.getBodyLayerStack().getSelectionLayer(), 0, 0, false, false));
	}

	/**
	 * @see org.eclipse.papyrus.uml.nattable.generic.tests.insert.AbstractInsertTest#getCommandId()
	 *
	 * @return
	 */
	@Override
	public String getCommandId() {
		return "org.eclipse.papyrus.infra.nattable.row.insert"; //$NON-NLS-1$
	}
}
