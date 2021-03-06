/*****************************************************************************
 * Copyright (c) 2010, 2013 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Tatiana Fesenko (CEA LIST) - Initial API and implementation
 *  Christian W. Damus (CEA) - Support creating models in repositories (CDO)
 *
 *****************************************************************************/
package org.eclipse.papyrus.sysml.diagram.ui;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.sysml.architecture.SysMLArchitectureContextIds;
import org.eclipse.papyrus.uml.diagram.wizards.pages.SelectArchitectureContextPage;
import org.eclipse.papyrus.uml.diagram.wizards.wizards.CreateModelWizard;
import org.eclipse.ui.IWorkbench;


/**
 * The Class NewSysMLModelWizard.
 */
public class NewSysMLModelWizard extends CreateModelWizard {

	/**
	 * Instantiates a new new sys ml model wizard.
	 */
	public NewSysMLModelWizard() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		super.init(workbench, selection);
		setWindowTitle("New SysML Model");
	}

	@Override
	public String getModelKindName() {
		return "Papyrus SysML";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] getSelectedContexts() {
		return new String[] { SysMLArchitectureContextIds.SysML };
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
		// here SysML is the only available category
	}

	@Override
	public boolean isPapyrusRootWizard() {
		return false;
	}

}
