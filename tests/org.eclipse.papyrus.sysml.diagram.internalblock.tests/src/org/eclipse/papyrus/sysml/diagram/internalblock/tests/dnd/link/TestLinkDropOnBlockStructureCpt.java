/*****************************************************************************
 * Copyright (c) 2012, 2015 CEA LIST, Christian W. Damus, and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *		
 *		CEA LIST - Initial API and implementation
 *		Christian W. Damus - bug 470296
 *
 *****************************************************************************/
package org.eclipse.papyrus.sysml.diagram.internalblock.tests.dnd.link;

import static org.eclipse.papyrus.sysml.diagram.internalblock.tests.utils.EditorUtils.getDiagramView;
import static org.eclipse.papyrus.sysml.diagram.internalblock.tests.utils.EditorUtils.getTransactionalEditingDomain;
import static org.eclipse.papyrus.sysml.diagram.internalblock.tests.utils.TestPrepareUtils.createConnectorLink;
import static org.eclipse.papyrus.sysml.diagram.internalblock.tests.utils.TestPrepareUtils.createGraphicalNode;
import static org.eclipse.papyrus.sysml.diagram.internalblock.tests.utils.TestPrepareUtils.createLink;
import static org.eclipse.papyrus.sysml.diagram.internalblock.tests.utils.TestUtils.dropFromModelExplorer;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.infra.emf.gmf.command.GMFtoEMFCommandWrapper;
import org.eclipse.papyrus.infra.services.edit.utils.GMFCommandUtils;
import org.eclipse.papyrus.sysml.diagram.common.utils.SysMLGraphicalTypes;
import org.eclipse.papyrus.sysml.diagram.internalblock.tests.AbstractTest;
import org.eclipse.papyrus.sysml.service.types.element.SysMLElementTypes;
import org.eclipse.papyrus.uml.service.types.element.UMLElementTypes;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.ConnectableElement;
import org.eclipse.uml2.uml.EncapsulatedClassifier;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * JUnit tests for Link DnD tests (via palette tools).
 */
public class TestLinkDropOnBlockStructureCpt extends AbstractTest {

	public static EObject dependency;
	public static EObject connector;
	public static EObject association;

	public static View blockStructureView;

	@BeforeClass
	public static void prepare() throws Exception {

		View blockView = ViewUtil.getChildBySemanticHint(getDiagramView(), SysMLGraphicalTypes.SHAPE_SYSML_BLOCK_AS_COMPOSITE_ID);
		blockStructureView = ViewUtil.getChildBySemanticHint(blockView, SysMLGraphicalTypes.COMPARTMENT_SYSML_STRUCTURE_ID);

		View partSourceView = createGraphicalNode(SysMLElementTypes.PART_PROPERTY, SysMLGraphicalTypes.SHAPE_SYSML_BLOCKPROPERTY_AS_COMPOSITE_ID, blockStructureView);
		View partTargetView = createGraphicalNode(SysMLElementTypes.PART_PROPERTY, SysMLGraphicalTypes.SHAPE_SYSML_BLOCKPROPERTY_AS_COMPOSITE_ID, blockStructureView);

		dependency = createLink(UMLElementTypes.DEPENDENCY, partSourceView.getElement(), partTargetView.getElement());
		connector = createConnectorLink((EncapsulatedClassifier) blockView.getElement(), (ConnectableElement) partSourceView.getElement(), (Property) null, (ConnectableElement) partTargetView.getElement(), (Property) null);

		org.eclipse.uml2.uml.Class block = (org.eclipse.uml2.uml.Class) blockView.getElement();
		association = createAssociation(block, block);
	}

	@Test
	public void dropDependencyInBlock() throws Exception {
		dropFromModelExplorer(dependency, blockStructureView, true);
	}

	@Test
	public void dropConnectorInBlock() throws Exception {
		dropFromModelExplorer(connector, blockStructureView, true);
	}

	@Test
	public void dropAssociationInBlock() throws Exception {
		dropFromModelExplorer(association, blockStructureView, false);
	}

	//
	// Test framework
	//

	static EObject createAssociation(final Type end1, final Type end2) throws Exception {

		AbstractTransactionalCommand createConnectorCommand = new AbstractTransactionalCommand(getTransactionalEditingDomain(), "Create association", null) {

			@Override
			protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				Association result = end1.createAssociation(true, AggregationKind.NONE_LITERAL, null, 0, 1, end2,
						true, AggregationKind.NONE_LITERAL, null, 0, 1);

				return CommandResult.newOKCommandResult(result);
			}

		};

		getTransactionalEditingDomain().getCommandStack().execute(new GMFtoEMFCommandWrapper(createConnectorCommand));

		return GMFCommandUtils.getCommandEObjectResult(createConnectorCommand);
	}
}
