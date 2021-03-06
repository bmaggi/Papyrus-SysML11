/*****************************************************************************
 * Copyright (c) 2013, 2016 CEA LIST, Christian W. Damus, and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *		
 *	CEA LIST - Initial API and implementation
 *  Christian W. Damus - bug 485220
 *
 *****************************************************************************/
package org.eclipse.papyrus.sysml.diagram.parametric.tests.utils;

import java.util.Iterator;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Tool;
import org.eclipse.gef.util.EditPartUtilities;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramEditDomain;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.infra.core.sasheditor.editor.ISashWindowsContainer;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.core.utils.ServiceUtils;
import org.eclipse.papyrus.sysml.diagram.parametric.ParametricDiagramForMultiEditor;
import org.eclipse.papyrus.sysml.diagram.parametric.factory.DiagramPaletteFactory;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;


public class EditorUtils {

	public static ParametricDiagramForMultiEditor getDiagramEditor() throws Exception {

		ServicesRegistry serviceRegistry = getEditor().getAdapter(ServicesRegistry.class);
		try {
			return (ParametricDiagramForMultiEditor) ServiceUtils.getInstance().getService(ISashWindowsContainer.class, serviceRegistry).getActiveEditor();

		} catch (ServiceException e) {
			throw new Exception("Unable to retrieve service.", e);
		} catch (ClassCastException e) {
			throw new Exception("Active diagram is not an parametric.", e);
		}
	}

	public static DiagramEditPart getDiagramEditPart() throws Exception {

		try {
			return getDiagramEditor().getDiagramEditPart();

		} catch (NullPointerException e) {
			throw new Exception("Could not find diagram edit part.", e);
		}
	}

	public static Diagram getDiagramView() throws Exception {

		try {
			return getDiagramEditor().getDiagram();

		} catch (NullPointerException e) {
			throw new Exception("Could not find diagram view.", e);
		}
	}

	public static EditPart getEditPart(View view) throws Exception {

		// Test if the container is the diagram itself first
		DiagramEditPart diagramEditPart = getDiagramEditPart();
		if (diagramEditPart.getModel() == view) {
			return diagramEditPart;
		}

		// Test diagram children and look for the view
		@SuppressWarnings("unchecked")
		Iterator<EditPart> it = EditPartUtilities.getAllChildren(diagramEditPart).iterator();
		while (it.hasNext()) {
			EditPart editPart = it.next();
			if (editPart.getModel() == view) {
				return editPart;
			}
		}

		// Test diagram nested connections and look for the view
		@SuppressWarnings("unchecked")
		Iterator<EditPart> itLinks = EditPartUtilities.getAllNestedConnectionEditParts(diagramEditPart).iterator();
		while (itLinks.hasNext()) {
			EditPart editPart = itLinks.next();
			if (editPart.getModel() == view) {
				return editPart;
			}
		}

		throw new Exception("Unable to find edit part for the given view.( type: "+view.getType()+") in diagramEditPart :"+ diagramEditPart.toString());
	}

	public static Tool getPaletteTool(String toolId) throws Exception {
		DiagramPaletteFactory factory = new DiagramPaletteFactory();
		return factory.createTool(toolId);
	}

	public static IDiagramEditDomain getDiagramEditingDomain() throws Exception {
		return getDiagramEditor().getDiagramEditDomain();
	}

	public static DiagramCommandStack getDiagramCommandStack() throws Exception {
		return getDiagramEditingDomain().getDiagramCommandStack();
	}

	public static CommandStack getCommandStack() throws Exception {
		return getTransactionalEditingDomain().getCommandStack();
	}

	public static TransactionalEditingDomain getTransactionalEditingDomain() throws Exception {

		ServicesRegistry serviceRegistry = getEditor().getAdapter(ServicesRegistry.class);
		try {
			return ServiceUtils.getInstance().getTransactionalEditingDomain(serviceRegistry);

		} catch (ServiceException e) {
			throw new Exception("Unable to retrieve service.", e);
		}
	}

	public static IEditorPart getEditor() throws Exception {
		RunnableWithResult<IEditorPart> getEditorRunnable = new RunnableWithResult.Impl<IEditorPart>() {

			public void run() {
				setResult(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor());
			}
		};
		Display.getDefault().syncExec(getEditorRunnable);
		return getEditorRunnable.getResult();
	}

}
