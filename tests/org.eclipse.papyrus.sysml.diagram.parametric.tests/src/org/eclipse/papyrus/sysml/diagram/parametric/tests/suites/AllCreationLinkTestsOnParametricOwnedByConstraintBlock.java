package org.eclipse.papyrus.sysml.diagram.parametric.tests.suites;

import org.eclipse.papyrus.sysml.diagram.parametric.tests.creation.link.connector.TestLinkCreationConnectorFromPart4CB;
import org.eclipse.papyrus.sysml.diagram.parametric.tests.creation.link.connector.TestLinkCreationConnectorFromReference4CB;
import org.eclipse.papyrus.sysml.diagram.parametric.tests.creation.link.connector.TestLinkCreationConnectorFromValue4CB;
import org.junit.runner.RunWith;
import org.eclipse.papyrus.junit.framework.classification.ClassificationSuite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(ClassificationSuite.class)
@SuiteClasses({ 
	TestLinkCreationConnectorFromPart4CB.class,
	TestLinkCreationConnectorFromReference4CB.class,
	TestLinkCreationConnectorFromValue4CB.class,	
})

public class AllCreationLinkTestsOnParametricOwnedByConstraintBlock {

}
