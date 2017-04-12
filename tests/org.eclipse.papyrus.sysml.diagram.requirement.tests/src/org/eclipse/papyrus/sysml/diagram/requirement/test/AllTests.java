/*****************************************************************************
 * Copyright (c) 2013 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  (CEA LIST) - Initial API and implementation
 /*****************************************************************************/
package org.eclipse.papyrus.sysml.diagram.requirement.test;

import org.eclipse.papyrus.sysml.diagram.requirement.test.canonical.AllCanonicalTests;
import org.junit.runner.RunWith;
import org.eclipse.papyrus.junit.framework.classification.ClassificationSuite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * All tests together.
 */
@RunWith(ClassificationSuite.class)
@SuiteClasses({
// canonical
AllCanonicalTests.class,
// load
//LoadTests.class 
})
public class AllTests {
}
