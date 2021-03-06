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
package org.eclipse.papyrus.sysml.diagram.internalblock.tests.creation.node.inherited;

import static org.eclipse.papyrus.sysml.diagram.internalblock.tests.utils.EditorUtils.getDiagramView;
import static org.eclipse.papyrus.sysml.diagram.internalblock.tests.utils.TestPrepareUtils.createGraphicalNode;
import static org.eclipse.papyrus.sysml.diagram.internalblock.tests.utils.TestUtils.createNodeFromPalette;

import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.sysml.diagram.internalblock.provider.ElementTypes;
import org.eclipse.papyrus.sysml.diagram.internalblock.tests.AbstractTest;
import org.eclipse.papyrus.uml.service.types.element.UMLElementTypes;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * JUnit tests for element creation test (via palette tools) in a Comment (TopNode - still inherited from class diagram).
 */
public class TestNodeCreationOnComment extends AbstractTest {

	public static View containerView;

	@BeforeClass
	public static void prepareContainerForTest() throws Exception {
		try {
			containerView = createGraphicalNode(UMLElementTypes.COMMENT, ElementTypes.COMMENT.getSemanticHint(), getDiagramView());
			if(containerView == null) {
				throw new Exception("Unable to prepare container for test.");
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
		createNodeFromPalette("internalblock.tool.comment", containerView, false);
	}

	@Test
	public void createConstraint() throws Exception {
		createNodeFromPalette("internalblock.tool.constraint", containerView, false);
	}
}
