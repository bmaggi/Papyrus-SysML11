package org.eclipse.papyrus.sysml.diagram.parametric.tests.suites;

import org.eclipse.papyrus.sysml.diagram.parametric.tests.creation.link.connector.TestLinkCreationConnectorFromConstraintParameter;
import org.eclipse.papyrus.sysml.diagram.parametric.tests.creation.link.connector.TestLinkCreationConnectorFromPart;
import org.eclipse.papyrus.sysml.diagram.parametric.tests.creation.link.connector.TestLinkCreationConnectorFromReference;
import org.eclipse.papyrus.sysml.diagram.parametric.tests.creation.link.connector.TestLinkCreationConnectorFromValue;
import org.junit.runner.RunWith;
import org.eclipse.papyrus.junit.framework.classification.ClassificationSuite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(ClassificationSuite.class)
@SuiteClasses({ 
	TestLinkCreationConnectorFromPart.class,
	TestLinkCreationConnectorFromReference.class,
	TestLinkCreationConnectorFromValue.class,
	TestLinkCreationConnectorFromConstraintParameter.class,
	
})

public class AllCreationLinkTestsOnParametricOwnedByBlock {

}
