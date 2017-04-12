/*****************************************************************************
 * Copyright (c) 2011, 2016 CEA LIST, Christian W. Damus, and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *
 *  CEA LIST - Initial API and implementation
 *  Christian W. Damus (CEA) - bug 434993
 *  Fanch Bonnabesse (ALL4TEC) fanch.bonnabesse@alltec.net - Bug 419357
 *  Christian W. Damus - 488791
 *
 *****************************************************************************/
package org.eclipse.papyrus.sysml.diagram.blockdefinition.tests;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.papyrus.infra.gmfdiag.common.preferences.PreferencesConstantsHelper;
import org.eclipse.papyrus.junit.framework.classification.tests.AbstractPapyrusTest;
import org.eclipse.papyrus.junit.utils.rules.HouseKeeper;
import org.eclipse.papyrus.sysml.diagram.blockdefinition.Activator;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.intro.IIntroPart;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.osgi.framework.Bundle;

/**
 * Abstract Papyrus initialization class (required to get Service activation).
 */
public abstract class AbstractTest extends AbstractPapyrusTest {

	@ClassRule
	public static final HouseKeeper.Static houseKeeper = new HouseKeeper.Static();

	public static IEditorPart editor = null;

	@BeforeClass
	public static void openPapyrusWithAnEmptyProject() throws Exception {
		Runnable closeIntroRunnable = new Runnable() {

			public void run() {
				IIntroPart introPart = PlatformUI.getWorkbench().getIntroManager().getIntro();
				PlatformUI.getWorkbench().getIntroManager().closeIntro(introPart);
			}
		};
		PlatformUI.getWorkbench().getDisplay().syncExec(closeIntroRunnable);

		// Prepare new project for tests
		IProject testProject = houseKeeper.createProject("TestProject");

		// Copy EmptyModel from bundle to the test project
		final IFile emptyModel_di = testProject.getFile("ModelWithBDD.di");
		IFile emptyModel_no = testProject.getFile("ModelWithBDD.notation");
		IFile emptyModel_uml = testProject.getFile("ModelWithBDD.uml");

		Bundle bundle = Platform.getBundle("org.eclipse.papyrus.sysml.diagram.blockdefinition.tests");
		emptyModel_di.create(bundle.getResource("/model/ModelWithBDD.di").openStream(), true, new NullProgressMonitor());
		emptyModel_no.create(bundle.getResource("/model/ModelWithBDD.notation").openStream(), true, new NullProgressMonitor());
		emptyModel_uml.create(bundle.getResource("/model/ModelWithBDD.uml").openStream(), true, new NullProgressMonitor());

		// Open the EmptyModel.di file with Papyrus (assumed to be the default editor for "di" files here).
		editor = houseKeeper.openPapyrusEditor(emptyModel_di);

		// Set the preference to never for the dialog to display existing link instead of create a new one.
		// The activator of "org.eclipse.papyrus.sysml.diagram.blockdefinition" returns the preference store of "org.eclipse.papyrus.infra.gmfdiag.preferences".
		final IPreferenceStore store = Activator.getInstance().getPreferenceStore();
		final String alwaysCreateLinkPreferenceName = PreferencesConstantsHelper.getPapyrusEditorConstant(PreferencesConstantsHelper.RESTORE_LINK_ELEMENT);
		store.setValue(alwaysCreateLinkPreferenceName, true);
		store.setDefault(alwaysCreateLinkPreferenceName, true);
	}
}
