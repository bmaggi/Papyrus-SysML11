/*****************************************************************************
 * Copyright (c) 2012 CEA LIST.
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
package org.eclipse.papyrus.sysml.diagram.internalblock.tests.creation.node;

import static org.eclipse.papyrus.sysml.diagram.internalblock.tests.utils.EditorUtils.getDiagramView;
import static org.eclipse.papyrus.sysml.diagram.internalblock.tests.utils.TestPrepareUtils.changeVisibility;
import static org.eclipse.papyrus.sysml.diagram.internalblock.tests.utils.TestPrepareUtils.createGraphicalNode;
import static org.eclipse.papyrus.sysml.diagram.internalblock.tests.utils.TestUtils.createNodeFromPalette;

import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.sysml.diagram.common.utils.SysMLGraphicalTypes;
import org.eclipse.papyrus.sysml.diagram.internalblock.tests.AbstractTest;
import org.eclipse.papyrus.sysml.service.types.element.SysMLElementTypes;
import org.eclipse.papyrus.uml.service.types.element.UMLElementTypes;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * JUnit tests for element creation test (via palette tools) in a Property "structure" compartment (meaningless compartment).
 */
public class TestNodeCreationOnNestedPropertyStructureCompartment extends AbstractTest {

	/** Compartment of a nested Property */
	public static View containerView;

	/** Compartment of a deep nested Property */
	public static View subContainerView;
	
	@BeforeClass
	public static void prepareContainerForTest() throws Exception {
		try {
			View blockView = ViewUtil.getChildBySemanticHint(getDiagramView(), SysMLGraphicalTypes.SHAPE_SYSML_BLOCK_AS_COMPOSITE_ID);
			View blockStructureView = ViewUtil.getChildBySemanticHint(blockView, SysMLGraphicalTypes.COMPARTMENT_SYSML_STRUCTURE_ID);
			View partView = createGraphicalNode(SysMLElementTypes.PART_PROPERTY, SysMLGraphicalTypes.SHAPE_SYSML_BLOCKPROPERTY_AS_COMPOSITE_ID, blockStructureView);
			View partStructureView = ViewUtil.getChildBySemanticHint(partView, SysMLGraphicalTypes.COMPARTMENT_SYSML_BLOCKPROPERTY_STRUCTURE_ID);		
			View propertyPartView = createGraphicalNode(UMLElementTypes.PROPERTY, SysMLGraphicalTypes.SHAPE_SYSML_BLOCKPROPERTY_AS_COMPOSITE_ID, partStructureView);
			containerView = ViewUtil.getChildBySemanticHint(propertyPartView, SysMLGraphicalTypes.COMPARTMENT_SYSML_BLOCKPROPERTY_STRUCTURE_ID);
			if(containerView == null) {
				throw new Exception("Unable to prepare container for test.");
			}
			// Ensure the compartment is visible (required for EditPart to be found)
			if(!containerView.isVisible()) {
				changeVisibility(containerView);
			}
		
			View nestedPartView = createGraphicalNode(SysMLElementTypes.PART_PROPERTY, SysMLGraphicalTypes.SHAPE_SYSML_BLOCKPROPERTY_AS_COMPOSITE_ID, partStructureView);
			View nestedPartStructureView = ViewUtil.getChildBySemanticHint(nestedPartView, SysMLGraphicalTypes.COMPARTMENT_SYSML_BLOCKPROPERTY_STRUCTURE_ID);		
			View subPropertyPartView = createGraphicalNode(UMLElementTypes.PROPERTY, SysMLGraphicalTypes.SHAPE_SYSML_BLOCKPROPERTY_AS_COMPOSITE_ID, nestedPartStructureView);
			subContainerView = ViewUtil.getChildBySemanticHint(subPropertyPartView, SysMLGraphicalTypes.COMPARTMENT_SYSML_BLOCKPROPERTY_STRUCTURE_ID);
			if(subContainerView == null) {
				throw new Exception("Unable to prepare container for test.");
			}
			// Ensure the compartment is visible (required for EditPart to be found)
			if(!subContainerView.isVisible()) {
				changeVisibility(subContainerView);
			}
			
		} catch (Exception e) {
			throw new Exception("Unable to prepare container for test.", e);
		}
	}

	@Test
	public void createActorPart() throws Exception {
		createNodeFromPalette("internalblock.tool.actorpart", containerView, false);
	}
	
	@Test
	public void createPort() throws Exception {
		createNodeFromPalette("internalblock.tool.port", containerView, false);
	}

	@Test
	public void createFlowPort_IN() throws Exception {
		createNodeFromPalette("internalblock.tool.flowport_in", containerView, false);
	}
	
	@Test
	public void createFlowPort_OUT() throws Exception {
		createNodeFromPalette("internalblock.tool.flowport_out", containerView, false);
	}
	
	@Test
	public void createFlowPort_INOUT() throws Exception {
		createNodeFromPalette("internalblock.tool.flowport_inout", containerView, false);
	}
	
	@Test
	public void createFlowPort_NA() throws Exception {
		createNodeFromPalette("internalblock.tool.flowport_na", containerView, false);
	}
	
	@Test
	public void createPart() throws Exception {
		createNodeFromPalette("internalblock.tool.part", containerView, false);
	}
	
	@Test
	public void createProperty() throws Exception {
		createNodeFromPalette("internalblock.tool.property", containerView, false);
	}
	
	@Test
	public void createReference() throws Exception {
		createNodeFromPalette("internalblock.tool.reference", containerView, false);
	}
	
	@Test
	public void createValue() throws Exception {
		createNodeFromPalette("internalblock.tool.value", containerView, false);
	}
	
	@Test
	public void createComment() throws Exception {
		createNodeFromPalette("internalblock.tool.comment", containerView, true);
	}

	@Test
	public void createConstraint() throws Exception {
		createNodeFromPalette("internalblock.tool.constraint", containerView, false);
	}
	
	//////////////////////////////////////////////////
	// test on deep nested Property compartment
	//////////////////////////////////////////////////
	
	@Test
	public void createActorPartOnDeepNestedPropertyStructureCompartment() throws Exception {
		createNodeFromPalette("internalblock.tool.actorpart", containerView, false);
	}
	
	@Test
	public void createPortOnDeepNestedPropertyStructureCompartment() throws Exception {
		createNodeFromPalette("internalblock.tool.port", containerView, false);
	}

	@Test
	public void createFlowPort_INOnDeepNestedPropertyStructureCompartment() throws Exception {
		createNodeFromPalette("internalblock.tool.flowport_in", containerView, false);
	}
	
	@Test
	public void createFlowPort_OUTOnDeepNestedPropertyStructureCompartment() throws Exception {
		createNodeFromPalette("internalblock.tool.flowport_out", containerView, false);
	}
	
	@Test
	public void createFlowPort_INOUTOnDeepNestedPropertyStructureCompartment() throws Exception {
		createNodeFromPalette("internalblock.tool.flowport_inout", containerView, false);
	}
	
	@Test
	public void createFlowPort_NAOnDeepNestedPropertyStructureCompartment() throws Exception {
		createNodeFromPalette("internalblock.tool.flowport_na", containerView, false);
	}
	
	@Test
	public void createPartOnDeepNestedPropertyStructureCompartment() throws Exception {
		createNodeFromPalette("internalblock.tool.part", containerView, false);
	}
	
	@Test
	public void createPropertyOnDeepNestedPropertyStructureCompartment() throws Exception {
		createNodeFromPalette("internalblock.tool.property", containerView, false);
	}
	
	@Test
	public void createReferenceOnDeepNestedPropertyStructureCompartment() throws Exception {
		createNodeFromPalette("internalblock.tool.reference", containerView, false);
	}
	
	@Test
	public void createValueOnDeepNestedPropertyStructureCompartment() throws Exception {
		createNodeFromPalette("internalblock.tool.value", containerView, false);
	}
	
	@Test
	public void createCommentOnDeepNestedPropertyStructureCompartment() throws Exception {
		createNodeFromPalette("internalblock.tool.comment", containerView, true);
	}

	@Test
	public void createConstraintOnDeepNestedPropertyStructureCompartment() throws Exception {
		createNodeFromPalette("internalblock.tool.constraint", containerView, false);
	}
}
