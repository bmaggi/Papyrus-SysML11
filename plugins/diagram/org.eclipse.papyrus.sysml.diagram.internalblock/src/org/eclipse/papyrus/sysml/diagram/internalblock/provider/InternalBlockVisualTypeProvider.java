/*****************************************************************************
 * Copyright (c) 2015 Christian W. Damus and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Christian W. Damus - Initial API and implementation
 *   
 *****************************************************************************/

package org.eclipse.papyrus.sysml.diagram.internalblock.provider;

import org.eclipse.papyrus.gmf.diagram.common.provider.SysMLVisualTypeProvider;
import org.eclipse.papyrus.uml.diagram.composite.providers.UMLVisualTypeProvider;

/**
 * Visual type provider for the SysML Internal Block Diagram.
 */
public class InternalBlockVisualTypeProvider extends SysMLVisualTypeProvider {

	public InternalBlockVisualTypeProvider() {
		super(new CustomGraphicalTypeRegistry(), new UMLVisualTypeProvider());
	}

}
