/*****************************************************************************
 * Copyright (c) 2013 CEA LIST.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Juan Cadavid (CEA LIST) juan.cadavid@cea.fr - Initial API and implementation
 *****************************************************************************/
package org.eclipse.papyrus.sysml.nattable.menu.handlers;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.papyrus.infra.emf.gmf.command.GMFtoEMFCommandWrapper;
import org.eclipse.papyrus.infra.services.edit.commands.IConfigureCommandFactory;
import org.eclipse.papyrus.infra.services.edit.service.ElementEditServiceUtils;
import org.eclipse.papyrus.infra.services.edit.service.IElementEditService;
import org.eclipse.papyrus.sysml.diagram.common.commands.CreatePartWithTypeConfigureCommandFactory;
import org.eclipse.papyrus.sysml.service.types.element.SysMLElementTypes;

/**
 * <pre>
 * Command handler for Part (Property) creation
 *
 * </pre>
 *
 * @generated
 */
public class PartPropertyHandler extends AbstractSysmlNattableCreateCommandHandler {

	/**
	 * <pre>
	 * &#64;see org.eclipse.papyrus.uml.service.creation.handler.CreateHandler#getElementTypeToCreate()
	 *
	 * &#64;return the IElementType this handler is supposed to create
	 *
	 * </pre>
	 *
	 * @generated
	 */
	@Override
	protected IElementType getElementTypeToCreate() {
		return SysMLElementTypes.PART_PROPERTY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Command buildCommand() {

		if (getCommandContext() == null) {
			return UnexecutableCommand.INSTANCE;
		}

		EObject container = getCommandContext().getContainer();
		EReference reference = getCommandContext().getReference();

		IElementEditService provider = ElementEditServiceUtils.getCommandProvider(container);
		if (provider == null) {
			return UnexecutableCommand.INSTANCE;
		}

		CreateElementRequest createRequest = null;
		if (reference == null) {
			createRequest = new CreateElementRequest(container, getElementTypeToCreate());
		} else {
			createRequest = new CreateElementRequest(container, getElementTypeToCreate(), reference);
		}
		createRequest.setParameter(IConfigureCommandFactory.CONFIGURE_COMMAND_FACTORY_ID, new CreatePartWithTypeConfigureCommandFactory());

		// Retrieve create command from the Element Edit service
		ICommand createGMFCommand = provider.getEditCommand(createRequest);
		if (createGMFCommand != null) {
			Command emfCommand = new GMFtoEMFCommandWrapper(createGMFCommand);
			return emfCommand;
		}
		return UnexecutableCommand.INSTANCE;
	}
}
