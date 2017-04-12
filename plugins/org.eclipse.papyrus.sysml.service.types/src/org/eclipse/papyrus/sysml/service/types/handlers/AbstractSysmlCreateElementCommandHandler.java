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
 *  Vincent Lorenzo (CEA LIST) vincent.lorenzo@cea.fr - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.sysml.service.types.handlers;

import org.eclipse.papyrus.sysml.service.types.filter.SysmlCommandFilter;
import org.eclipse.papyrus.uml.service.types.filter.ICommandFilter;
import org.eclipse.papyrus.uml.service.types.ui.handlers.AbstractCreateElementCommandHandler;

/**
 *
 * Abstract handler for every creation command for Sysml elements
 * used in the ModelExplorer contextual ("Create new child") menu
 *
 */
public abstract class AbstractSysmlCreateElementCommandHandler extends AbstractCreateElementCommandHandler {

	private static final ICommandFilter filter = SysmlCommandFilter.INSTANCE;

	@Override
	public ICommandFilter getCommandFilter() {
		return filter;
	}

}
