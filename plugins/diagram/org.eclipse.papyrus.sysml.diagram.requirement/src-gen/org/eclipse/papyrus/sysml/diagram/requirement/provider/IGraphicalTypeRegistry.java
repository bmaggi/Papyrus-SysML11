/*****************************************************************************
 * Copyright (c) 2013, 2015 CEA LIST, Christian W. Damus, and others.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * CEA LIST- Initial API and implementation
 * Nizar GUEDIDI (CEA LIST)- modification
 * Christian W. Damus - bug 477384
 *
 ****************************************************************************/
package org.eclipse.papyrus.sysml.diagram.requirement.provider;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

/**
 * This interface provides an API for accessing the local graphical type registry.
 * This registry provides a graphical type for existing model elements.
 * 
 * @deprecated Use the {@link org.eclipse.papyrus.gmf.diagram.common.provider.IGraphicalTypeRegistry} type, instead.
 */
@Deprecated
public interface IGraphicalTypeRegistry extends org.eclipse.papyrus.gmf.diagram.common.provider.IGraphicalTypeRegistry {

	/** Constant for undefined graphical type */
	public static final String UNDEFINED_TYPE = "undefined_type";

	/**
	 * Get a valid edge graphical type for the domain element.
	 *
	 * @param domainElement
	 *            the domain element.
	 * @return the domain element graphical type.
	 */
	@Override
	public String getEdgeGraphicalType(EObject domainElement);

	/**
	 * Get a valid edge graphical type for the element type.
	 *
	 * @param elementType
	 *            the element type.
	 * @param containerType
	 *            the type of the graphical owner of the domain element.
	 * @return the domain element graphical type.
	 */
	@Override
	public String getEdgeGraphicalType(IElementType elementType);

	/**
	 * Get a valid edge graphical type for the proposed type.
	 *
	 * @param proposedType
	 *            the proposed type.
	 * @param containerType
	 *            the type of the graphical owner of the domain element.
	 * @return the domain element graphical type.
	 */
	@Override
	public String getEdgeGraphicalType(String proposedType);

	/**
	 * Get a valid node graphical type for the domain element.
	 *
	 * @param domainElement
	 *            the domain element.
	 * @param containerType
	 *            the type of the graphical owner of the domain element.
	 * @return the domain element graphical type.
	 */
	@Override
	public String getNodeGraphicalType(EObject domainElement, String containerType);

	/**
	 * Get a valid node graphical type for the element type.
	 *
	 * @param elementType
	 *            the element type.
	 * @param containerType
	 *            the type of the graphical owner of the domain element.
	 * @return the domain element graphical type.
	 */
	@Override
	public String getNodeGraphicalType(IElementType elementType, String containerType);

	/**
	 * Get a valid node graphical type for the proposed type.
	 *
	 * @param proposedType
	 *            the proposed type.
	 * @param containerType
	 *            the type of the graphical owner of the domain element.
	 * @return the domain element graphical type.
	 */
	@Override
	public String getNodeGraphicalType(String proposedType, String containerType);

	/**
	 * Test if the type is a known edge graphical type in this diagram
	 *
	 * @param type
	 *            the type to test
	 * @return true if the type is known
	 */
	@Override
	public boolean isKnownEdgeType(String type);

	/**
	 * Test if the type is a known node graphical type in this diagram
	 *
	 * @param type
	 *            the type to test
	 * @return true if the type is known
	 */
	@Override
	public boolean isKnownNodeType(String type);

}
