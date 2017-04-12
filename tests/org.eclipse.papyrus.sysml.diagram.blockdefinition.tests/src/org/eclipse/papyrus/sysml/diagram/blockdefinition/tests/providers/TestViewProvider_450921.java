/*****************************************************************************
 * Copyright (c) 2015 CEA LIST and others.
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

package org.eclipse.papyrus.sysml.diagram.blockdefinition.tests.providers;

import org.junit.Assert;

import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateNodeViewOperation;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.sysml.diagram.blockdefinition.provider.CustomViewProvider;
import org.eclipse.papyrus.sysml.diagram.blockdefinition.tests.AbstractTest;
import org.eclipse.papyrus.sysml.diagram.blockdefinition.tests.utils.EditorUtils;
import org.eclipse.papyrus.sysml.diagram.common.utils.SysMLGraphicalTypes;
import org.junit.Before;
import org.junit.Test;

/**
 * Proposed fix for #450921 allows null semantic adapters to be passed to papyrus ViewProvider's. 
 * (Before the patch, all null semantic adapters were filtered by the CreateViewCommand). 
 * This test verifies that the sysml view providers do not fail facing now allowed null adapters.
 */
public class TestViewProvider_450921 extends AbstractTest {

	protected CustomViewProvider provider;

	protected View diagram;

	@Before
	public void setUp() throws Exception {
		provider = new CustomViewProvider();
		diagram = EditorUtils.getDiagramView();
	}

	@Test
	public void testNullSemanticAdapter() throws Exception {
		IOperation o = new CreateNodeViewOperation(null, diagram, SysMLGraphicalTypes.SHAPE_SYSML_BLOCK_AS_CLASSIFIER_ID, -1, false, EditorUtils.getDiagramEditPart().getDiagramPreferencesHint());
		Assert.assertTrue("Provider should provide node while semantic adapter null", provider.provides(o));
	}

	@Test
	public void testNullSemanticAdapterAndEmptyHint() throws Exception {
		IOperation o = new CreateNodeViewOperation(null, diagram, "", -1, false, EditorUtils.getDiagramEditPart().getDiagramPreferencesHint());
		Assert.assertFalse("Provider should not provide undefined element", provider.provides(o));
	}

	@Test
	public void testNullSemanticAdapterAndDabHint() throws Exception {
		IOperation o = new CreateNodeViewOperation(null, diagram, "Bad hint", -1, false, EditorUtils.getDiagramEditPart().getDiagramPreferencesHint());
		Assert.assertFalse("Provider should not provide undefined element", provider.provides(o));
	}
}
