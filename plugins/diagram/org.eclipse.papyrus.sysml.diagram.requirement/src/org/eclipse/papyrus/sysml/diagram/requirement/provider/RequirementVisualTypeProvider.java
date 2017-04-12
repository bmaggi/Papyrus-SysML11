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

package org.eclipse.papyrus.sysml.diagram.requirement.provider;

import org.eclipse.papyrus.gmf.diagram.common.provider.SysMLVisualTypeProvider;
import org.eclipse.papyrus.uml.diagram.clazz.providers.UMLVisualTypeProvider;

/**
 * Visual type provider for the SysML Requirement Diagram.
 */
public class RequirementVisualTypeProvider extends SysMLVisualTypeProvider {

	public RequirementVisualTypeProvider() {
		super(new GraphicalTypeRegistry(), new UMLVisualTypeProvider());
	}

}
