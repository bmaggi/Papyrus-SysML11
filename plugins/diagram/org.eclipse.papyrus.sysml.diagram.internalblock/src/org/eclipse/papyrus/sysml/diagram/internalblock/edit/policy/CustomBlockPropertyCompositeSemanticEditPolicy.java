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
package org.eclipse.papyrus.sysml.diagram.internalblock.edit.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.gmf.diagram.common.provider.IGraphicalTypeRegistry;
import org.eclipse.papyrus.sysml.diagram.common.edit.policy.BlockPropertyCompositeSemanticEditPolicy;
import org.eclipse.papyrus.sysml.diagram.common.utils.SysMLCreateOrShowExistingElementHelper;
import org.eclipse.papyrus.sysml.diagram.internalblock.provider.CustomGraphicalTypeRegistry;
import org.eclipse.papyrus.sysml.diagram.internalblock.provider.ElementTypes;
import org.eclipse.papyrus.sysml.diagram.internalblock.provider.GraphicalTypeRegistry;
import org.eclipse.papyrus.uml.diagram.common.helper.CreateOrShowExistingElementHelper;
import org.eclipse.papyrus.uml.service.types.command.CommentAnnotatedElementsCreateCommand;
import org.eclipse.papyrus.uml.service.types.command.CommentAnnotatedElementsReorientCommand;
import org.eclipse.papyrus.uml.service.types.command.ConstraintConstrainedElementsCreateCommand;
import org.eclipse.papyrus.uml.service.types.command.ConstraintConstrainedElementsReorientCommand;
import org.eclipse.papyrus.uml.service.types.element.UMLElementTypes;

/**
 * <pre>
 * Custom semantic edit policy that replace DefaultSemanticEditPolicy
 * in order to manage Class Diagram specific elements (reference
 * relationships COMMENT_ANNOTATED_ELEMENT and CONSTRAINT_CONSTRAINED_ELEMENT).
 * </pre>
 */
public class CustomBlockPropertyCompositeSemanticEditPolicy extends BlockPropertyCompositeSemanticEditPolicy {

	/** Local graphical type registry for graphical elements */
	private IGraphicalTypeRegistry registry = new CustomGraphicalTypeRegistry();

	/** Local graphical type registry for inherited graphical elements */
	private IGraphicalTypeRegistry inheritedRegistry = new GraphicalTypeRegistry();

	private CreateOrShowExistingElementHelper linkHelper = new CreateOrShowExistingElementHelper();

	/** the sysml link helper to use for specific case */
	private CreateOrShowExistingElementHelper sysmlLinkHelper = new SysMLCreateOrShowExistingElementHelper();

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {

		if (ElementTypes.COMMENT_ANNOTATED_ELEMENT == req.getElementType()) {
			return getGEFWrapper(new CommentAnnotatedElementsCreateCommand(req));
		}

		if (ElementTypes.CONSTRAINT_CONSTRAINED_ELEMENT == req.getElementType()) {
			return getGEFWrapper(new ConstraintConstrainedElementsCreateCommand(req));
		}

		String newEdgeGraphicalType = registry.getEdgeGraphicalType(req.getElementType());

		if (inheritedRegistry.isKnownEdgeType(newEdgeGraphicalType)) {
			return UnexecutableCommand.INSTANCE;
		}

		if (!registry.isKnownEdgeType(newEdgeGraphicalType)) {
			return UnexecutableCommand.INSTANCE;
		}

		final Command defaultCommand = super.getCreateRelationshipCommand(req);
		if (defaultCommand.canExecute()) {
			final IElementType elementType = req.getElementType();
			if (UMLElementTypes.CONNECTOR.equals(req.getElementType())) {
				return this.sysmlLinkHelper.getCreateOrRestoreElementCommand(req, defaultCommand, elementType);
			}
			if (UMLElementTypes.DEPENDENCY.equals(req.getElementType())) {
				return this.linkHelper.getCreateOrRestoreElementCommand(req, defaultCommand, elementType);
			}
		}
		return defaultCommand;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Command getReorientReferenceRelationshipCommand(ReorientReferenceRelationshipRequest req) {

		View reconnectedView = (View) req.getParameter(org.eclipse.papyrus.infra.services.edit.utils.RequestParameterConstants.GRAPHICAL_RECONNECTED_EDGE);
		String reconnectedViewType = (reconnectedView != null) ? reconnectedView.getType() : org.eclipse.papyrus.infra.gmfdiag.common.providers.IGraphicalTypeRegistry.UNDEFINED_TYPE;

		if (ElementTypes.COMMENT_ANNOTATED_ELEMENT.getSemanticHint().equals(reconnectedViewType)) {
			return getGEFWrapper(new CommentAnnotatedElementsReorientCommand(req));
		}

		if (ElementTypes.CONSTRAINT_CONSTRAINED_ELEMENT.getSemanticHint().equals(reconnectedViewType)) {
			return getGEFWrapper(new ConstraintConstrainedElementsReorientCommand(req));
		}

		return super.getReorientReferenceRelationshipCommand(req);
	}

}
