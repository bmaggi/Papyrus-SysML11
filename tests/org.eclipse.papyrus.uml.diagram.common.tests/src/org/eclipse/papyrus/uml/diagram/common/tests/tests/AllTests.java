/*****************************************************************************
 * Copyright (c) 2014, 2015 CEA LIST, Christian W. Damus, and others.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Vincent Lorenzo (CEA LIST) vincent.lorenzo@cea.fr - Initial API and implementation
 *  Christian W. Damus - bug 471954
 *
 *****************************************************************************/
package org.eclipse.papyrus.uml.diagram.common.tests.tests;


import org.eclipse.papyrus.junit.framework.classification.ClassificationSuite;
import org.eclipse.papyrus.uml.diagram.common.canonical.tests.CanonicalRegressionTest;
import org.eclipse.papyrus.uml.diagram.common.tests.css.Bug431694_UndoDeleteTest;
import org.eclipse.papyrus.uml.diagram.common.tests.parser.HTMLCleanerTestCase;
import org.eclipse.papyrus.uml.diagram.common.tests.parser.HTMLCornerBentFigureTestCase;
import org.eclipse.papyrus.uml.diagram.common.tests.parser.MultiplicityStringFormatterTestCase;
import org.eclipse.papyrus.uml.diagram.common.tests.parser.ValueSpecificationUtilTestCase;
import org.eclipse.papyrus.uml.diagram.common.tests.stereotype.display.AppliedStereotypeDisplayStructureTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(ClassificationSuite.class)
@SuiteClasses({
		AppliedStereotypeDisplayStructureTest.class,
		CommonBendpointsTest.class,
		HTMLCleanerTestCase.class,
		HTMLCornerBentFigureTestCase.class,
		Bug431694_UndoDeleteTest.class,
		Bug418509_ReorientationAssociation.class,
		MultiplicityStringFormatterTestCase.class,
		ValueSpecificationUtilTestCase.class,
		CanonicalRegressionTest.class,
		ShapeCustomisationTest.class,
		Bug476873_MoveCommandTest.class,
		Bug495430_DuplicatedTransitionsTest.class,
		Bug488744_PortPositionTest.class
})

public class AllTests {
	// JUnit 4 test suite
}
