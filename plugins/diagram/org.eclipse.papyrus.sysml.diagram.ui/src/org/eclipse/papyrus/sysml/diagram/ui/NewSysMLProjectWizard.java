/*****************************************************************************
 * Copyright (c) 2010 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Tatiana Fesenko (CEA LIST) - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.sysml.diagram.ui;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.sysml.architecture.SysMLArchitectureContextIds;
import org.eclipse.papyrus.uml.diagram.wizards.pages.SelectArchitectureContextPage;
import org.eclipse.papyrus.uml.diagram.wizards.wizards.NewPapyrusProjectWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;


/**
 * The Class NewSysMLProjectWizard.
 */
public class NewSysMLProjectWizard extends NewPapyrusProjectWizard {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		super.init(workbench, selection);
		setWindowTitle("New SysML Project");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected WizardNewProjectCreationPage createNewProjectCreationPage() {
		WizardNewProjectCreationPage newProjectPage = super.createNewProjectCreationPage();
		newProjectPage.setTitle("Papyrus SysML Project");
		newProjectPage.setDescription("Create a New Papyrus SysML Project");
		return newProjectPage;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected SelectArchitectureContextPage createSelectArchitectureContextPage() {
		// here SysML is the only available category
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void saveDiagramCategorySettings() {
		// do nothing
		// here SysML is the only available category
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] getSelectedContexts() {
		return new String[] { SysMLArchitectureContextIds.SysML };
	}

	@Override
	public boolean isPapyrusRootWizard() {
		return false;
	}

}
