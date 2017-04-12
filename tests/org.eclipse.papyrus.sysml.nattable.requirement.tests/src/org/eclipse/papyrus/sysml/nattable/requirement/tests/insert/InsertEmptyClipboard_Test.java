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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.papyrus.infra.nattable.messages.Messages;
import org.junit.Assert;

/**
 * Test insert with empty clipboard.
 */
public class InsertEmptyClipboard_Test extends AbstractInsertEmptyTest {

	/**
	 * Constructor.
	 */
	public InsertEmptyClipboard_Test() {
		super();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.sysml.nattable.requirement.tests.paste.overwrite.AbstractPasteInsertTest#checkReturned_Status(org.eclipse.core.runtime.IStatus)
	 */
	@Override
	protected void checkReturned_Status(final IStatus status) {
		Assert.assertEquals("Error must be caught", IStatus.ERROR, status.getSeverity()); //$NON-NLS-1$
		Assert.assertEquals("Error message is not the expected message", Messages.PasteImportHandler_EmptyClipboardString, status.getMessage()); //$NON-NLS-1$
	}
}
