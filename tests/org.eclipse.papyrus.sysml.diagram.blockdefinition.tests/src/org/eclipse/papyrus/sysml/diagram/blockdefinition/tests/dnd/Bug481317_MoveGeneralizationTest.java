/*****************************************************************************
 * Copyright (c) 2016 CEA LIST and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   CEA LIST - Initial API and implementation
 *   
 *****************************************************************************/

package org.eclipse.papyrus.sysml.diagram.blockdefinition.tests.dnd;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.requests.MoveRequest;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.impl.NodeImpl;
import org.eclipse.gmf.runtime.notation.impl.ShapeImpl;
import org.eclipse.papyrus.infra.emf.gmf.command.GMFtoEMFCommandWrapper;
import org.eclipse.papyrus.infra.services.edit.service.ElementEditServiceUtils;
import org.eclipse.papyrus.infra.services.edit.service.IElementEditService;
import org.eclipse.papyrus.junit.framework.classification.tests.AbstractPapyrusTest;
import org.eclipse.papyrus.junit.utils.DiagramUtils;
import org.eclipse.papyrus.junit.utils.rules.PapyrusEditorFixture;
import org.eclipse.papyrus.junit.utils.rules.PluginResource;
import org.eclipse.papyrus.sysml.diagram.common.edit.part.BlockEditPart;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Property;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * 
 * Used to test the deletion of the inherited property when a generalization is moved.
 *
 */
@PluginResource("/resources/bugs/Bug481317_BDD/sysml.di")
public class Bug481317_MoveGeneralizationTest extends AbstractPapyrusTest {

	/** The name of the containing block of the property. */
	private static final String NAME_BLOCK_1 = "Block1"; //$NON-NLS-1$
	/** The name of the source block of the generalization. */
	private static final String NAME_BLOCK_2 = "Block2"; //$NON-NLS-1$
	/** The name of the target block of the generalization. */
	private static final String NAME_BLOCK_3 = "Block3"; //$NON-NLS-1$
	/** The name of the diagram used for testing. */
	private static final String NAME_DIAGRAM = "Block Definition Diagram"; //$NON-NLS-1$

	/** The Papyrus editor fixture. */
	@Rule
	public PapyrusEditorFixture papyrusEditorFixture = new PapyrusEditorFixture();

	/** The target Block edit part. */
	private EditPart targetBlockEditPart = null;

	/** The generalization to move. */
	private Generalization generalizationToMove = null;

	/** The property to check the deletion. */
	private Property property = null;

	/** The source Block edit part */
	private EditPart generalizationSourceBlockEditPart = null;

	/** The diagram. */
	private Diagram diagram = null;

	@Before
	public void init() {
		diagram = DiagramUtils.getNotationDiagram(papyrusEditorFixture.getModelSet(), NAME_DIAGRAM);
		assertNotNull(diagram);

		papyrusEditorFixture.getPageManager().openPage(diagram);
		papyrusEditorFixture.flushDisplayEvents();

		// The target block of the generalization
		targetBlockEditPart = getEditPart(NAME_BLOCK_3);
		assertTrue("The Edit Part must be a BlockEditPart", targetBlockEditPart instanceof BlockEditPart); //$NON-NLS-1$

		// The source block of the generalization
		generalizationSourceBlockEditPart = getEditPart(NAME_BLOCK_2);
		assertTrue("The Edit Part must be a BlockEditPart", generalizationSourceBlockEditPart instanceof BlockEditPart); //$NON-NLS-1$

		Object sourceModel = generalizationSourceBlockEditPart.getModel();
		if (sourceModel instanceof ShapeImpl) {
			EObject sourceElement = ((ShapeImpl) sourceModel).getElement();
			assertTrue("The element must be a Classifier", sourceElement instanceof Classifier);//$NON-NLS-1$

			EList<Generalization> generalizations = ((Classifier) sourceElement).getGeneralizations();
			assertTrue(generalizations.size() >= 1);

			generalizationToMove = generalizations.get(0);
		}

		// The edit part of the block which contained the property (Block1)
		EditPart propertyBlockEditPart = getEditPart(NAME_BLOCK_1); // The source block of the property
		assertTrue("The Edit Part must be a BlockEditPart", propertyBlockEditPart instanceof BlockEditPart); //$NON-NLS-1$

		Object propertyBlockModel = propertyBlockEditPart.getModel();
		if (propertyBlockModel instanceof ShapeImpl) {
			EObject propertyBlockElement = ((ShapeImpl) propertyBlockModel).getElement();

			// Checks that the element is a Block and gets all its attributes
			assertTrue("The Element must be a Classifier", propertyBlockElement instanceof Classifier);//$NON-NLS-1$
			EList<Property> properties = ((Classifier) propertyBlockElement).getAttributes();

			assertTrue(properties.size() >= 1);
			property = properties.get(0);
		}
	}

	/**
	 * Tests if the property is referenced on the block on the diagram.
	 */
	@Test
	public void testPropertyReference() {
		// Get views of the property, must be null.
		View propertyView = getDiagramViewProperty(property, NAME_BLOCK_2);
		assertNotNull(propertyView);
	}

	/**
	 * Tests of the move of a generalization (Block2 to Block3) to delete the referencing view of the property (prop1).
	 */
	@Test
	public void testMoveGeneralization() {
		List<EObject> elements = new ArrayList<EObject>();
		elements.add(generalizationToMove);

		// Retrieve the command to move the generalization.
		ICommand command = getCommandMovedElements(elements);
		assertNotNull(command);

		// Moved the generalization
		getCommandStack().execute(new GMFtoEMFCommandWrapper(command));

		// Get the views, the property view on the must be deleted
		View propertyView = getDiagramViewProperty(property, NAME_BLOCK_2);
		assertNull(propertyView);

		getCommandStack().undo();

		// After the undo command, the property view must be NOT null
		propertyView = getDiagramViewProperty(property, NAME_BLOCK_2);
		assertNotNull(propertyView);

		getCommandStack().redo();

		// After the redo command, the property view must be null
		propertyView = getDiagramViewProperty(property, NAME_BLOCK_2);
		assertNull(propertyView);
	}


	/**
	 * This creates the command to move the Elements.
	 *
	 * @param elementsToMove
	 *            The list of the elements to move.
	 * @return The command to move the elements.
	 */
	private ICommand getCommandMovedElements(final List<EObject> elementsToMove) {
		ICommand command = null;

		final Object modelTarget = targetBlockEditPart.getModel();
		assertThat(modelTarget, instanceOf(NodeImpl.class));

		final EObject elementTarget = ((NodeImpl) modelTarget).getElement();
		assertNotNull(elementTarget);

		final MoveRequest moveRequest = new MoveRequest(elementTarget, elementsToMove);
		final IElementEditService provider = ElementEditServiceUtils.getCommandProvider(elementTarget);

		assertNotNull(provider);

		// Retrieve delete command from the Element Edit service
		command = provider.getEditCommand(moveRequest);

		return command;
	}


	/**
	 * Gets the command stack.
	 *
	 * @return the command stack
	 */
	private CommandStack getCommandStack() {
		return papyrusEditorFixture.getEditingDomain().getCommandStack();
	}

	/**
	 * Gets the edit part.
	 *
	 * @param semanticElement
	 *            the semantic element
	 * @return The edit part
	 */
	private EditPart getEditPart(final String semanticElement) {
		final View diagramView = getDiagramView(semanticElement);
		assertNotNull(diagramView);

		final IGraphicalEditPart semanticEP = DiagramUtils.findEditPartforView(papyrusEditorFixture.getEditor(), diagramView, IGraphicalEditPart.class);
		assertNotNull(semanticEP);

		return semanticEP;
	}

	/**
	 * Gets view of the semantic element.
	 *
	 * @param semanticElement
	 *            The name of the semantic element.
	 * @return A corresponding view of the semantic element.
	 */
	private View getDiagramView(final String semanticElement) {
		View diagramView = DiagramUtils.findShape(diagram, semanticElement);
		if (diagramView == null) {
			diagramView = DiagramUtils.findEdge(diagram, semanticElement);
		}
		if (diagramView == null) {
			Iterator<?> iterator = diagram.eAllContents();
			while (null == diagramView && iterator.hasNext()) {
				final Object object = iterator.next();
				if (object instanceof View) {
					diagramView = DiagramUtils.findShape((View) object, semanticElement);
					if (diagramView == null) {
						diagramView = DiagramUtils.findEdge((View) object, semanticElement);
					}
				}
			}
		}

		return diagramView;
	}

	/**
	 * Gets view of a property on the diagram and on a block.
	 *
	 * @param property
	 *            The property.
	 * @param blockNameReference
	 *            The name of the class in which the property reference must be.
	 * @return A corresponding view of the property.
	 */
	private View getDiagramViewProperty(final Property property, final String blockNameReference) {
		View diagramView = findViewPropertyReference(diagram, property);

		if (diagramView == null) {
			Iterator<?> iterator = diagram.eAllContents();
			while (null == diagramView && iterator.hasNext()) {
				final Object object = iterator.next();
				if (object instanceof View) {
					EObject element = ((View) object).getElement();

					if (element instanceof Classifier) {
						if (blockNameReference.equals(((Classifier) element).getName())) {
							diagramView = findViewPropertyReference((View) object, property);
						}
					}
				}
			}
		}

		return diagramView;
	}

	/**
	 * Gets view of the property on a specific element.
	 *
	 * @param container
	 *            The specific element to find property.
	 * @param property
	 *            Property to find.
	 * @return A view of the property.
	 */
	private View findViewPropertyReference(final View container, final Property property) {
		for (final Object viewObject : container.getChildren()) {
			final View view = (View) viewObject;
			if (view.getElement() instanceof Property) {
				final Property element = (Property) view.getElement();
				if (property.equals(element)) {
					return view;
				}
			}
		}
		return null;
	}
}
