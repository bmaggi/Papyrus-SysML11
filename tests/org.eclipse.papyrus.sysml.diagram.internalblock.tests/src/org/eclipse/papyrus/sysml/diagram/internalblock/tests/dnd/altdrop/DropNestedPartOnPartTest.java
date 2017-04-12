/*****************************************************************************
 * Copyright (c) 2016 CEA LIST and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Nicolas FAUVERGUE (ALL4TEC) nicolas.fauvergue@all4tec.net - Initial API and implementation - Bug 488557
 *   
 *****************************************************************************/

package org.eclipse.papyrus.sysml.diagram.internalblock.tests.dnd.altdrop;

import static org.eclipse.papyrus.sysml.diagram.internalblock.tests.utils.EditorUtils.getDiagramView;
import static org.eclipse.papyrus.sysml.diagram.internalblock.tests.utils.TestPrepareUtils.createGraphicalNode;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.commands.wrappers.GEFtoEMFCommandWrapper;
import org.eclipse.papyrus.sysml.diagram.common.edit.part.BlockCompositeEditPart;
import org.eclipse.papyrus.sysml.diagram.common.edit.part.BlockPropertyStructureCompartmentEditPart;
import org.eclipse.papyrus.sysml.diagram.common.edit.part.StructureCompartmentEditPart;
import org.eclipse.papyrus.sysml.diagram.common.utils.SysMLGraphicalTypes;
import org.eclipse.papyrus.sysml.diagram.internalblock.tests.AbstractTest;
import org.eclipse.papyrus.sysml.diagram.internalblock.tests.utils.EditorUtils;
import org.eclipse.papyrus.sysml.service.types.element.SysMLElementTypes;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This allows to test the drag and drop of a nested part of part into another part.
 * The edit parts have to stay same.
 * 
 */
public class DropNestedPartOnPartTest extends AbstractTest {

	/**
	 * The dropped element.
	 */
	public static EObject droppedElement;

	/**
	 * The graphical edit part of the part1.
	 */
	public static IGraphicalEditPart part1Compartment;

	/**
	 * The graphical edit part of the part2.
	 */
	public static IGraphicalEditPart part2Compartment;

	/**
	 * The graphical edit part of nested part (object to drop).
	 */
	public static IGraphicalEditPart compartmentToDrop;

	/**
	 * This allows to prepare the test.
	 * 
	 * @throws Exception
	 *             The exception.
	 */
	@BeforeClass
	public static void prepareContainerForTest() throws Exception {
		try {
			// prepare container
			View blockView = ViewUtil.getChildBySemanticHint(getDiagramView(), SysMLGraphicalTypes.SHAPE_SYSML_BLOCK_AS_COMPOSITE_ID);
			View blockStructureView = ViewUtil.getChildBySemanticHint(blockView, SysMLGraphicalTypes.COMPARTMENT_SYSML_STRUCTURE_ID);

			if ((null == blockView) || (null == blockStructureView)) {
				throw new Exception("Unable to prepare container for test."); //$NON-NLS-1$
			}

			// prepare the model
			// Create 2 part views
			View partView1 = createGraphicalNode(SysMLElementTypes.PART_PROPERTY, SysMLGraphicalTypes.SHAPE_SYSML_BLOCKPROPERTY_AS_COMPOSITE_ID, blockStructureView);
			View partStructureView1 = ViewUtil.getChildBySemanticHint(partView1, SysMLGraphicalTypes.COMPARTMENT_SYSML_BLOCKPROPERTY_STRUCTURE_ID);

			View partView2 = createGraphicalNode(SysMLElementTypes.PART_PROPERTY, SysMLGraphicalTypes.SHAPE_SYSML_BLOCKPROPERTY_AS_COMPOSITE_ID, blockStructureView);

			// Create the nested part
			View nestedPartView1 = createGraphicalNode(SysMLElementTypes.PART_PROPERTY, SysMLGraphicalTypes.SHAPE_SYSML_BLOCKPROPERTY_AS_COMPOSITE_ID, partStructureView1);
			if (null == nestedPartView1) {
				throw new Exception("Unable to prepare container for test.");
			}
			droppedElement = nestedPartView1.getElement();

			// Try to get the correct edit parts of part1, part2 and the nested part
			final IGraphicalEditPart diagram = EditorUtils.getDiagramEditPart();

			Assert.assertTrue("The diagram edit part children must not be empty", !diagram.getChildren().isEmpty()); //$NON-NLS-1$
			Assert.assertTrue("The first diagram edit part child must be a block composite edit part", diagram.getChildren().get(0) instanceof BlockCompositeEditPart); //$NON-NLS-1$
			Assert.assertTrue("The block composite edit part children must not be empty", !((IGraphicalEditPart) diagram.getChildren().get(0)).getChildren().isEmpty()); //$NON-NLS-1$

			for (final Object blockChild : ((IGraphicalEditPart) diagram.getChildren().get(0)).getChildren()) {
				final IGraphicalEditPart blockChildEP = (IGraphicalEditPart) blockChild;
				if (blockChildEP instanceof StructureCompartmentEditPart) {
					for (final Object child : ((IGraphicalEditPart) ((IGraphicalEditPart) diagram.getChildren().get(0)).getChildren().get(1)).getChildren()) {
						final IGraphicalEditPart childEP = (IGraphicalEditPart) child;
						if (childEP.resolveSemanticElement().equals(partView1.getElement())) {
							for (final Object part1Child : childEP.getChildren()) {
								final IGraphicalEditPart part1ChildEP = (IGraphicalEditPart) part1Child;
								if (part1ChildEP instanceof BlockPropertyStructureCompartmentEditPart) {
									part1Compartment = part1ChildEP;
									compartmentToDrop = (IGraphicalEditPart) part1ChildEP.getChildren().get(0);
								}
							}
						} else if (childEP.resolveSemanticElement().equals(partView2.getElement())) {
							for (final Object part2Child : childEP.getChildren()) {
								final IGraphicalEditPart part2ChildEP = (IGraphicalEditPart) part2Child;
								if (part2ChildEP instanceof BlockPropertyStructureCompartmentEditPart) {
									part2Compartment = part2ChildEP;
								}
							}
						}
					}
				}
			}

		} catch (Exception e) {
			throw new Exception("Unable to prepare container for test.", e); //$NON-NLS-1$
		}
	}

	/**
	 * This allows to test the drag and drop of a nested part contained in a part into another part.
	 * 
	 * @throws Exception
	 *             The exception.
	 */
	@Test
	public void testDragAndDropNestedPartIntoPart() throws Exception {

		// Check initial nested part composite edit part
		Assert.assertNotNull("The first part compartment edit part must not be null", part1Compartment); //$NON-NLS-1$
		Assert.assertNotNull("The second part compartment edit part must not be null", part2Compartment); //$NON-NLS-1$
		Assert.assertNotNull("The nest part to drop must not be null", compartmentToDrop); //$NON-NLS-1$
		Assert.assertTrue("The nested part composite edit part must have children edit part", !compartmentToDrop.getChildren().isEmpty()); //$NON-NLS-1$

		// Manage a drag and drop for nestedPart from part1 to part2
		final Request req = createDragDropRequest(compartmentToDrop);
		final Command command = part2Compartment.getCommand(req);
		Assert.assertNotNull("ComponentEditPart must be dropable from diagram to self semantically parent", command); //$NON-NLS-1$
		EditorUtils.getCommandStack().execute(GEFtoEMFCommandWrapper.wrap(command));

		Assert.assertTrue("The part1 structure compartement edit part must be empty", part1Compartment.getChildren().isEmpty()); //$NON-NLS-1$
		Assert.assertTrue("The part2 structure compartement edit part must not be empty", !part2Compartment.getChildren().isEmpty()); //$NON-NLS-1$
		// Bug 488557: The nested part have to keep its domposite edit part children after a drop into another part
		Assert.assertTrue("The nested part composite edit part must have children edit part", !compartmentToDrop.getChildren().isEmpty()); //$NON-NLS-1$
	}

	/**
	 * This allows to create the drag and drop request to execute.
	 * 
	 * @param editPart
	 *            The edit part to move.
	 * @return The created request.
	 */
	protected Request createDragDropRequest(final IGraphicalEditPart editPart) {
		ChangeBoundsRequest result = new ChangeBoundsRequest();
		result.setEditParts(editPart);
		result.setLocation(new Point(1, 1));
		result.setType(org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants.REQ_DROP);
		return result;
	}

}
