/*****************************************************************************
 * Copyright (c) 2011-2012 CEA LIST.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *		
 *		CEA LIST - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.sysml.diagram.blockdefinition.tests.creation.inherited;

import static org.eclipse.papyrus.sysml.diagram.blockdefinition.tests.utils.EditorUtils.getDiagramView;
import static org.eclipse.papyrus.sysml.diagram.blockdefinition.tests.utils.TestPrepareUtils.changeVisibility;
import static org.eclipse.papyrus.sysml.diagram.blockdefinition.tests.utils.TestUtils.createNodeFromPalette;

import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.sysml.diagram.blockdefinition.provider.ElementTypes;
import org.eclipse.papyrus.sysml.diagram.blockdefinition.tests.AbstractTest;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * JUnit tests for element creation test (via palette tools) in a Model compartment (TopNode - still inherited from class diagram).
 */
public class TestNodeCreationOnInstanceSpecificationCompartmentCN extends AbstractTest {

	public static View containerView;

	@BeforeClass
	public static void prepareContainerForTest() throws Exception {
		try {
			// force custom provider loading...
			createNodeFromPalette("blockdefinition.tool.block", getDiagramView(), true);
			//

			createNodeFromPalette("blockdefinition.tool.model", getDiagramView(), true);
			View topNodeView = ViewUtil.getChildBySemanticHint(getDiagramView(), ElementTypes.MODEL.getSemanticHint());
			View topNodeCompartmentView = ViewUtil.getChildBySemanticHint(topNodeView, ElementTypes.MODEL_COMPARTMENT_PACKAGEABLE_ELEMENT_HINT);

			// Ensure the compartment is visible (required for EditPart to be found)
			if(!topNodeCompartmentView.isVisible()) {
				changeVisibility(topNodeCompartmentView);
			}

			createNodeFromPalette("blockdefinition.tool.instancespecification", topNodeCompartmentView, true);
			View childNodeView = ViewUtil.getChildBySemanticHint(topNodeCompartmentView, ElementTypes.INSTANCE_SPECIFICATION_CN.getSemanticHint());
			containerView = ViewUtil.getChildBySemanticHint(childNodeView, ElementTypes.INSTANCE_SPECIFICATION_CN_COMPARTMENT_SLOT_HINT);

			if(containerView == null) {
				throw new Exception("Unable to prepare container for test.");
			}

			// Ensure the compartment is visible (required for EditPart to be found)
			if(!containerView.isVisible()) {
				changeVisibility(containerView);
			}

		} catch (Exception e) {
			throw new Exception("Unable to prepare container for test.", e);
		}
	}

	@Test
	public void createActor() throws Exception {
		createNodeFromPalette("blockdefinition.tool.actor", containerView, false);
	}

	@Test
	public void createActorPart() throws Exception {
		createNodeFromPalette("blockdefinition.tool.actorpart", containerView, false);
	}

	@Test
	public void createBlock() throws Exception {
		createNodeFromPalette("blockdefinition.tool.block", containerView, false);
	}

	@Test
	public void createComment() throws Exception {
		createNodeFromPalette("blockdefinition.tool.comment", containerView, false);
	}

	@Test
	public void createConstraint() throws Exception {
		createNodeFromPalette("blockdefinition.tool.constraint", containerView, false);
	}

	@Test
	public void createConstraintBlock() throws Exception {
		createNodeFromPalette("blockdefinition.tool.constraintblock", containerView, false);
	}

	@Test
	public void createConstraintProperty() throws Exception {
		createNodeFromPalette("blockdefinition.tool.constraintproperty", containerView, false);
	}

	@Test
	public void createDataType() throws Exception {
		createNodeFromPalette("blockdefinition.tool.datatype", containerView, false);
	}

	@Test
	public void createDimension() throws Exception {
		createNodeFromPalette("blockdefinition.tool.dimension", containerView, false);
	}

	@Test
	public void createEnumeration() throws Exception {
		createNodeFromPalette("blockdefinition.tool.enumeration", containerView, false);
	}

	@Test
	public void createEnumerationLiteral() throws Exception {
		createNodeFromPalette("blockdefinition.tool.enumerationliteral", containerView, false);
	}

	@Test
	public void createFlowPort() throws Exception {
		createNodeFromPalette("blockdefinition.tool.flowport", containerView, false);
	}

	@Test
	public void createFlowProperty() throws Exception {
		createNodeFromPalette("blockdefinition.tool.flowproperty", containerView, false);
	}

	@Test
	public void createFlowSpecification() throws Exception {
		createNodeFromPalette("blockdefinition.tool.flowspecification", containerView, false);
	}

	@Test
	public void createInstanceSpecification() throws Exception {
		createNodeFromPalette("blockdefinition.tool.instancespecification", containerView, false);
	}

	@Test
	public void createInterface() throws Exception {
		createNodeFromPalette("blockdefinition.tool.interface", containerView, false);
	}

	@Test
	public void createModel() throws Exception {
		createNodeFromPalette("blockdefinition.tool.model", containerView, false);
	}

	@Test
	public void createOperation() throws Exception {
		createNodeFromPalette("blockdefinition.tool.operation", containerView, false);
	}

	@Test
	public void createPackage() throws Exception {
		createNodeFromPalette("blockdefinition.tool.package", containerView, false);
	}

	@Test
	public void createPart() throws Exception {
		createNodeFromPalette("blockdefinition.tool.part", containerView, false);
	}

	@Test
	public void createPort() throws Exception {
		createNodeFromPalette("blockdefinition.tool.port", containerView, false);
	}

	@Test
	public void createPrimitiveType() throws Exception {
		createNodeFromPalette("blockdefinition.tool.primitivetype", containerView, false);
	}

	@Test
	public void createProperty() throws Exception {
		createNodeFromPalette("blockdefinition.tool.property", containerView, false);
	}

	@Test
	public void createReception() throws Exception {
		createNodeFromPalette("blockdefinition.tool.reception", containerView, false);
	}

	@Test
	public void createReference() throws Exception {
		createNodeFromPalette("blockdefinition.tool.reference", containerView, false);
	}

	@Test
	public void createSignal() throws Exception {
		createNodeFromPalette("blockdefinition.tool.signal", containerView, false);
	}

	@Test
	public void createSlot() throws Exception {
		createNodeFromPalette("blockdefinition.tool.slot", containerView, true);
	}

	@Test
	public void createUnit() throws Exception {
		createNodeFromPalette("blockdefinition.tool.unit", containerView, false);
	}

	@Test
	public void createValue() throws Exception {
		createNodeFromPalette("blockdefinition.tool.value", containerView, false);
	}

	@Test
	public void createValueType() throws Exception {
		createNodeFromPalette("blockdefinition.tool.valuetype", containerView, false);
	}
}
