/*****************************************************************************
 * Copyright (c) 2012, 2016 CEA LIST, Christian W. Damus, and others.
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
 *  Christian W. Damus - bugs 485220, 488791
 *
 *****************************************************************************/
package org.eclipse.papyrus.sysml.diagram.parametric.tests;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.papyrus.infra.core.sashwindows.di.service.IPageManager;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.core.utils.ServiceUtils;
import org.eclipse.papyrus.junit.framework.classification.tests.AbstractPapyrusTest;
import org.eclipse.papyrus.junit.utils.rules.HouseKeeper;
import org.eclipse.papyrus.sysml.diagram.parametric.tests.utils.Constants;
import org.eclipse.papyrus.sysml.diagram.parametric.tests.utils.EditorUtils;
import org.eclipse.ui.IEditorPart;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.osgi.framework.Bundle;

/**
 * Abstract Papyrus initialization class (required to get Service activation).
 */
public abstract class AbstractTest4B extends AbstractPapyrusTest {

	@ClassRule
	public static final HouseKeeper.Static houseKeeper = new HouseKeeper.Static();

	public static IEditorPart editor = null;

	@BeforeClass
	public static void openPapyrusWithAParametricDiagramOwnedByBlock() throws Exception {
		// Prepare new project for tests
		IProject testProject = houseKeeper.createProject("TestProject");

		// Copy EmptyModel from bundle to the test project
		final IFile emptyModel_di = testProject.getFile("ModelWithPD.di");
		IFile emptyModel_no = testProject.getFile("ModelWithPD.notation");
		IFile emptyModel_uml = testProject.getFile("ModelWithPD.uml");

		Bundle bundle = Platform.getBundle("org.eclipse.papyrus.sysml.diagram.parametric.tests");
		emptyModel_di.create(bundle.getResource("/model/ModelWithPD.di").openStream(), true, new NullProgressMonitor());
		emptyModel_no.create(bundle.getResource("/model/ModelWithPD.notation").openStream(), true, new NullProgressMonitor());
		emptyModel_uml.create(bundle.getResource("/model/ModelWithPD.uml").openStream(), true, new NullProgressMonitor());

		// Open the EmptyModel.di file with Papyrus (assumed to be the default editor for "di" files here).
		editor = houseKeeper.openPapyrusEditor(emptyModel_di);

		setActiveDiagramViewByName(Constants.PARAMETRIC_OWNED_BY_BLOCK_DIAGRAM_NAME);
	}

	public static boolean setActiveDiagramViewByName(String diagramName) throws Exception {
		if (diagramName == null) {
			return false;
		}
		ServicesRegistry serviceRegistry = EditorUtils.getEditor().getAdapter(ServicesRegistry.class);
		IPageManager iPageManager = ServiceUtils.getInstance().getService(IPageManager.class, serviceRegistry);
		List<Object> allPages = iPageManager.allPages();
		for (Object object : allPages) {
			if (object instanceof Diagram) {
				if (diagramName.equals(((Diagram) object).getName())) {
					iPageManager.selectPage(object);
					return true;
				}
			}
		}
		return false;
	}
}
