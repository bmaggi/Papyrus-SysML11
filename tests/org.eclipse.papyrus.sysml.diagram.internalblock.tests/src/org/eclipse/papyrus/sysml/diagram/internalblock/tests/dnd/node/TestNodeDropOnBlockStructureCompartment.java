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
package org.eclipse.papyrus.sysml.diagram.internalblock.tests.dnd.node;

import static org.eclipse.papyrus.sysml.diagram.internalblock.tests.utils.EditorUtils.getDiagramView;
import static org.eclipse.papyrus.sysml.diagram.internalblock.tests.utils.TestPrepareUtils.createElement;
import static org.eclipse.papyrus.sysml.diagram.internalblock.tests.utils.TestUtils.dropFromModelExplorer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.sysml.diagram.common.utils.SysMLGraphicalTypes;
import org.eclipse.papyrus.sysml.diagram.internalblock.tests.AbstractTest;
import org.eclipse.papyrus.sysml.service.types.element.SysMLElementTypes;
import org.eclipse.papyrus.uml.service.types.element.UMLElementTypes;
import org.eclipse.uml2.uml.UMLPackage;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * JUnit tests for element Drop test (via palette tools) on Block structure compartment.
 */
public class TestNodeDropOnBlockStructureCompartment extends AbstractTest {

	public static View containerView;

	public static EObject containerPackage;

	@BeforeClass
	public static void prepareContainerForTest() throws Exception {
		try {
			// prepare container
			View blockView = ViewUtil.getChildBySemanticHint(getDiagramView(), SysMLGraphicalTypes.SHAPE_SYSML_BLOCK_AS_COMPOSITE_ID);
			containerView = ViewUtil.getChildBySemanticHint(blockView, SysMLGraphicalTypes.COMPARTMENT_SYSML_STRUCTURE_ID);
			if(containerView == null) {
				throw new Exception("Unable to prepare container for test.");
			}
			
			// prepare container package (getDiagramView references the shown Block).
			containerPackage = EMFCoreUtil.getContainer(getDiagramView().getElement(), UMLPackage.eINSTANCE.getPackage());
			
		} catch (Exception e) {
			throw new Exception("Unable to prepare container for test.", e);
		}
	}

	@Test
	public void testDropActorPart() throws Exception {
		EObject intermediateContainer = createElement(SysMLElementTypes.BLOCK, containerPackage);
		EObject droppedElement = createElement(SysMLElementTypes.ACTOR_PART_PROPERTY, intermediateContainer);
		dropFromModelExplorer(droppedElement, containerView, false);
	}
	
	@Test
	public void testDropActorPartContained() throws Exception {
		dropFromModelExplorer(SysMLElementTypes.ACTOR_PART_PROPERTY, containerView, true);
	}

	@Test
	public void testDropPort() throws Exception {
		EObject intermediateContainer = createElement(SysMLElementTypes.BLOCK, containerPackage);
		EObject droppedElement = createElement(UMLElementTypes.PORT, intermediateContainer);
		dropFromModelExplorer(droppedElement, containerView, false);
	}
	
	@Test
	public void testDropPortContained() throws Exception {
		dropFromModelExplorer(UMLElementTypes.PORT, containerView, false);
	}
	
	@Test
	public void testDropFlowPort_IN() throws Exception {
		EObject intermediateContainer = createElement(SysMLElementTypes.BLOCK, containerPackage);
		EObject droppedElement = createElement(SysMLElementTypes.FLOW_PORT_IN, intermediateContainer);
		dropFromModelExplorer(droppedElement, containerView, false);
	}
	
	@Test
	public void testDropFlowPort_INContained() throws Exception {
		dropFromModelExplorer(SysMLElementTypes.FLOW_PORT_IN, containerView, false);
	}
	
	@Test
	public void testDropFlowPort_OUT() throws Exception {
		EObject intermediateContainer = createElement(SysMLElementTypes.BLOCK, containerPackage);
		EObject droppedElement = createElement(SysMLElementTypes.FLOW_PORT_OUT, intermediateContainer);
		dropFromModelExplorer(droppedElement, containerView, false);
	}
	
	@Test
	public void testDropFlowPort_OUTContained() throws Exception {
		dropFromModelExplorer(SysMLElementTypes.FLOW_PORT_OUT, containerView, false);
	}
	
	@Test
	public void testDropFlowPort_INOUT() throws Exception {
		EObject intermediateContainer = createElement(SysMLElementTypes.BLOCK, containerPackage);
		EObject droppedElement = createElement(SysMLElementTypes.FLOW_PORT_IN_OUT, intermediateContainer);
		dropFromModelExplorer(droppedElement, containerView, false);
	}
	
	@Test
	public void testDropFlowPort_INOUTContained() throws Exception {
		dropFromModelExplorer(SysMLElementTypes.FLOW_PORT_IN_OUT, containerView, false);
	}
	
	@Test
	public void testDropFlowPort_NA() throws Exception {
		EObject intermediateContainer = createElement(SysMLElementTypes.BLOCK, containerPackage);
		EObject droppedElement = createElement(SysMLElementTypes.FLOW_PORT_NA, intermediateContainer);
		dropFromModelExplorer(droppedElement, containerView, false);
	}
	
	@Test
	public void testDropFlowPort_NAContained() throws Exception {
		dropFromModelExplorer(SysMLElementTypes.FLOW_PORT_NA, containerView, false);
	}
	
	@Test
	public void testDropPart() throws Exception {
		EObject intermediateContainer = createElement(SysMLElementTypes.BLOCK, containerPackage);
		EObject droppedElement = createElement(SysMLElementTypes.PART_PROPERTY, intermediateContainer);
		dropFromModelExplorer(droppedElement, containerView, false);
	}
	
	@Test
	public void testDropPartContained() throws Exception {
		dropFromModelExplorer(SysMLElementTypes.PART_PROPERTY, containerView, true);
	}
	
	@Test
	public void testDropProperty() throws Exception {
		EObject intermediateContainer = createElement(SysMLElementTypes.BLOCK, containerPackage);
		EObject droppedElement = createElement(UMLElementTypes.PROPERTY, intermediateContainer);
		dropFromModelExplorer(droppedElement, containerView, false);
	}
	
	@Test
	public void testDropPropertyContained() throws Exception {
		dropFromModelExplorer(UMLElementTypes.PROPERTY, containerView, true);
	}
	
	@Test
	public void testDropReference() throws Exception {
		EObject intermediateContainer = createElement(SysMLElementTypes.BLOCK, containerPackage);
		EObject droppedElement = createElement(SysMLElementTypes.REFERENCE_PROPERTY, intermediateContainer);
		dropFromModelExplorer(droppedElement, containerView, false);
	}
	
	@Test
	public void testDropReferenceContained() throws Exception {
		dropFromModelExplorer(SysMLElementTypes.REFERENCE_PROPERTY, containerView, true);
	}
	
	@Test
	public void testDropValue() throws Exception {
		EObject intermediateContainer = createElement(SysMLElementTypes.BLOCK, containerPackage);
		EObject droppedElement = createElement(SysMLElementTypes.VALUE_PROPERTY, intermediateContainer);
		dropFromModelExplorer(droppedElement, containerView, false);
	}
	
	@Test
	public void testDropValueContained() throws Exception {
		dropFromModelExplorer(SysMLElementTypes.VALUE_PROPERTY, containerView, true);
	}
	
	@Test
	public void testDropComment() throws Exception {
		EObject droppedElement = createElement(UMLElementTypes.COMMENT, containerPackage);
		dropFromModelExplorer(droppedElement, containerView, false);
	}

	@Test
	public void testDropCommentContained() throws Exception {
		dropFromModelExplorer(UMLElementTypes.COMMENT, containerView, true);
	}

	@Test
	public void testDropConstraint() throws Exception {
		EObject droppedElement = createElement(UMLElementTypes.CONSTRAINT, containerPackage);
		dropFromModelExplorer(droppedElement, containerView, false);
	}

	@Test
	public void testDropConstraintContained() throws Exception {
		dropFromModelExplorer(UMLElementTypes.CONSTRAINT, containerView, true);
	}
}
