/*****************************************************************************
 * Copyright (c) 2011 CEA LIST.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *
 *		CEA LIST - Initial API and implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.sysml.diagram.common.edit.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.papyrus.infra.gmfdiag.common.editpolicies.DefaultCompartmentSemanticEditPolicy;
import org.eclipse.papyrus.infra.services.edit.commands.IConfigureCommandFactory;
import org.eclipse.papyrus.sysml.blocks.Block;
import org.eclipse.papyrus.sysml.diagram.common.commands.CreateFlowPortWithFlowSpecificationConfigureCommandFactory;
import org.eclipse.papyrus.sysml.service.types.element.SysMLElementTypes;

/**
 * Semantic edit policy for {@link Block} structure compartment.
 */
public class FlowPortCompartmentSemanticEditPolicy extends DefaultCompartmentSemanticEditPolicy {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Command getCreateCommand(CreateElementRequest req) {

		IElementType elementTypeToCreate = req.getElementType();
		IElementType baseType = elementTypeToCreate;

		if (SysMLElementTypes.FLOW_PORT_NA == baseType) {
			req.setParameter(IConfigureCommandFactory.CONFIGURE_COMMAND_FACTORY_ID, new CreateFlowPortWithFlowSpecificationConfigureCommandFactory());
		}

		return super.getCreateCommand(req);
	}
}
