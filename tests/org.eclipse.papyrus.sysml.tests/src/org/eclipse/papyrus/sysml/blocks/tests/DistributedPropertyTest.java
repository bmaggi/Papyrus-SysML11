/*****************************************************************************
 * Copyright (c) 2009 CEA LIST.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Yann Tanguy (CEA LIST) yann.tanguy@cea.fr - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.sysml.blocks.tests;

import junit.framework.TestCase;
import junit.textui.TestRunner;

import org.eclipse.papyrus.sysml.blocks.BlocksFactory;
import org.eclipse.papyrus.sysml.blocks.DistributedProperty;
import org.junit.Ignore;

/**
 * <!-- begin-user-doc --> A test case for the model object '<em><b>Distributed Property</b></em>'.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
@Ignore("no tests")
public class DistributedPropertyTest extends TestCase {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(DistributedPropertyTest.class);
	}

	/**
	 * The fixture for this Distributed Property test case.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected DistributedProperty fixture = null;

	/**
	 * Constructs a new Distributed Property test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public DistributedPropertyTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Distributed Property test case.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected DistributedProperty getFixture() {
		return fixture;
	}

	/**
	 * Sets the fixture for this Distributed Property test case.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void setFixture(DistributedProperty fixture) {
		this.fixture = fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(BlocksFactory.eINSTANCE.createDistributedProperty());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} // DistributedPropertyTest
