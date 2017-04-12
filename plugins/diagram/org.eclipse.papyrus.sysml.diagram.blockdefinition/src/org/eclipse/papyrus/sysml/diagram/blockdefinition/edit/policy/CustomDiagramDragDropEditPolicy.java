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
package org.eclipse.papyrus.sysml.diagram.blockdefinition.edit.policy;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.DropObjectsRequest;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.ISpecializationType;
import org.eclipse.osgi.util.NLS;
import org.eclipse.papyrus.gmf.diagram.common.commands.IdentityCommandWithNotification;
import org.eclipse.papyrus.gmf.diagram.common.edit.policy.CommonDiagramDragDropEditPolicy;
import org.eclipse.papyrus.infra.widgets.toolbox.notification.Type;
import org.eclipse.papyrus.sysml.diagram.blockdefinition.dnd.helper.CustomLinkMappingHelper;
import org.eclipse.papyrus.sysml.diagram.blockdefinition.messages.Messages;
import org.eclipse.papyrus.sysml.diagram.blockdefinition.provider.CustomGraphicalTypeRegistry;
import org.eclipse.papyrus.sysml.diagram.blockdefinition.provider.ElementTypes;
import org.eclipse.papyrus.sysml.service.types.element.SysMLElementTypes;
import org.eclipse.papyrus.uml.diagram.clazz.custom.helper.InstanceSpecificationLinkHelper;
import org.eclipse.papyrus.uml.diagram.common.commands.CommonDeferredCreateConnectionViewCommand;
import org.eclipse.papyrus.uml.diagram.common.commands.SemanticAdapter;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.AssociationClass;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.TypedElement;

/**
 * Custom diagram drag drop edit policy for elements handling drop in the Block Definition Diagram.
 */
public class CustomDiagramDragDropEditPolicy extends CommonDiagramDragDropEditPolicy {

	/** Default constructor. */
	public CustomDiagramDragDropEditPolicy() {
		super(new CustomLinkMappingHelper());
		registry = new CustomGraphicalTypeRegistry();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Set<String> getSpecificDropBehaviorTypes() {
		Set<String> result = new HashSet<String>();
		result.add(ElementTypes.INSTANCE_SPECIFICATION.getSemanticHint());
		result.add(ElementTypes.INSTANCE_SPECIFICATION_LINK.getSemanticHint());
		return result;
	}

	@Override
	protected ICommand getSpecificDropCommand(DropObjectsRequest dropRequest, EObject droppedEObject, String nodeType, String edgeType) {
		if (ElementTypes.INSTANCE_SPECIFICATION.getSemanticHint().equalsIgnoreCase(nodeType) ||
				ElementTypes.INSTANCE_SPECIFICATION_LINK.getSemanticHint().equalsIgnoreCase(edgeType)) {
			return new CommandProxy(dropInstanceSpecification(nodeType, dropRequest, droppedEObject));
		}
		return super.getSpecificDropCommand(dropRequest, droppedEObject, nodeType, edgeType);
	}

	/**
	 * drop a instance specification as a link or as a node
	 *
	 * @param dropRequest
	 *            the drop request
	 * @param droppedEObject
	 *            the element
	 * @param edgeType
	 *            the edge type
	 * @return the command in charge of the drop
	 */
	protected Command dropInstanceSpecification(String droppedNodeType, DropObjectsRequest dropRequest, EObject droppedEObject) {
		if (false == droppedEObject instanceof InstanceSpecification) {
			return UnexecutableCommand.INSTANCE;
		}
		// Drop as link
		List<InstanceSpecification> endTypes = InstanceSpecificationLinkHelper.getEnds(((InstanceSpecification) droppedEObject));
		if (endTypes.size() > 0) {
			Element source = endTypes.get(0);
			Element target = endTypes.get(1);
			return new ICommandProxy(dropBinaryLink(new CompositeCommand("drop InstanceSpecification link"), source, target, ElementTypes.INSTANCE_SPECIFICATION_LINK.getSemanticHint(), dropRequest.getLocation(), (Element) droppedEObject));
		}
		// Drop as node
		return new ICommandProxy(getDefaultDropNodeCommand(droppedNodeType, droppedEObject, dropRequest.getLocation()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ICommand getUnknownDropCommand(DropObjectsRequest dropRequest, EObject droppedEObject) {

		// Add a notification message in case an attempt to drop a UML Association in this diagram occurs.
		if ((droppedEObject instanceof Association) && !(droppedEObject instanceof AssociationClass)) {
			if (!((ISpecializationType) SysMLElementTypes.ASSOCIATION).getMatcher().matches(droppedEObject)) {
				return new IdentityCommandWithNotification(Messages.Notification_Drop_Title, NLS.bind(Messages.Notification_Drop_UMLAssociationWarning, EMFCoreUtil.getQualifiedName(droppedEObject, true)), Type.WARNING);
			}
		}

		return super.getUnknownDropCommand(dropRequest, droppedEObject);
	}

	/**
	 * the method provides command to create the binary link into the diagram.
	 * If the source and the target views do not exist, these views will be
	 * created.
	 *
	 * @param cc
	 *            the composite command that will contain the set of command to
	 *            create the binary link
	 * @param source
	 *            the source the element source of the link
	 * @param target
	 *            the target the element target of the link
	 * @param edgeType
	 *            the edge type used to create the view
	 * @param location
	 *            the location the location where the view will be be created
	 * @param semanticLink
	 *            the semantic link that will be attached to the view
	 *
	 * @return the composite command
	 */
	protected CompositeCommand dropBinaryLink(CompositeCommand cc, Element source, Element target, String edgeType, Point absoluteLocation, Element semanticLink) {
		IAdaptable sourceViewAdapter = findAdapter(cc, source, getLinkSourceDropLocation(absoluteLocation, source, target), edgeType);
		IAdaptable targetViewAdapter = findAdapter(cc, target, getLinkTargetDropLocation(absoluteLocation, source, target), edgeType);

		CreateConnectionViewRequest.ConnectionViewDescriptor linkdescriptor = new CreateConnectionViewRequest.ConnectionViewDescriptor(getElementType(edgeType, semanticLink), edgeType, getDiagramPreferencesHint());
		CommonDeferredCreateConnectionViewCommand createConnectionViewCommand = new CommonDeferredCreateConnectionViewCommand(getEditingDomain(), edgeType, sourceViewAdapter, targetViewAdapter, getViewer(), getDiagramPreferencesHint(), linkdescriptor, null);
		createConnectionViewCommand.setElement(semanticLink);
		cc.compose(createConnectionViewCommand);
		return cc;
	}

	/**
	 * {@inheritDoc}
	 */
	protected IAdaptable getElementType(String elementID, Element semanticLink) {
		IElementType result = ElementTypes.getElementTypeByID(elementID);
		return result == null ? new SemanticAdapter(semanticLink, null) : result;
	}

	/**
	 * This method allows to specify a location for the creation of a node at
	 * the source of a dropped link. Overriding implementations must not modify
	 * the absoluteLocation parameter (use {@link Point#getCopy()})
	 *
	 * @param absoluteLocation
	 *            the request's drop location
	 * @param source
	 *            the source of the dropped link
	 * @param target
	 *            the target of the dropped link
	 * @return the new location for the node
	 */
	protected Point getLinkSourceDropLocation(Point absoluteLocation, Element source, Element target) {
		return absoluteLocation;
	}

	/**
	 * This method allows to specify a location for the creation of a node at
	 * the target of a dropped link. Overriding implementations must not modify
	 * the absoluteLocation parameter (use {@link Point#getCopy()})
	 *
	 * @param absoluteLocation
	 *            the request's drop location
	 * @param source
	 *            the source of the dropped link
	 * @param target
	 *            the target of the dropped link
	 * @return the new location for the node
	 */
	protected Point getLinkTargetDropLocation(Point absoluteLocation, Element source, Element target) {
		if (lookForEditPart(source) == null && lookForEditPart(target) == null) {
			return absoluteLocation.getTranslated(100, 0);
		}
		return absoluteLocation;
	}

	/**
	 * the method provides command to create the binary link into the diagram.
	 * Find source/target adapter
	 * If the source and the target views do not exist, these views will be
	 * created.
	 * 
	 * @see dropBinaryLink(CompositeCommand cc, Element source, Element target, int linkVISUALID
	 *      , Point absoluteLocation, Element semanticLink)
	 *
	 * @param cc
	 *            the composite command that will contain the set of command to
	 *            create the binary link
	 * @param source
	 *            source/target link node
	 * @param point
	 *            source/target node location
	 */
	private IAdaptable findAdapter(CompositeCommand cc, Element source, Point dropLocation, String edgeType) {
		GraphicalEditPart editPart = (GraphicalEditPart) lookForEditPart(source);
		if (editPart != null) {
			return new SemanticAdapter(null, editPart.getModel());
		}
		ICommand createCommand = getDefaultDropNodeCommand(edgeType, dropLocation, source);
		cc.add(createCommand);
		return (IAdaptable) createCommand.getCommandResult().getReturnValue();
	}

	/**
	 * {@inheritDoc}
	 */
	protected EditPart lookForEditPart(EObject semantic) {
		return lookForEditPart(semantic, false);
	}

	/**
	 * Look for editPart from his semantic.
	 *
	 * @param semantic
	 *            the semantic
	 *
	 * @return the edits the part or null if not found
	 */
	protected EditPart lookForEditPart(EObject semantic, boolean byType) {
		Collection<EditPart> editPartSet = getHost().getViewer().getEditPartRegistry().values();
		Iterator<EditPart> editPartIterator = editPartSet.iterator();
		while (editPartIterator.hasNext()) {
			EditPart nextEditPart = editPartIterator.next();
			if (false == isEditPartTypeAdapted(nextEditPart.getClass(), semantic.eClass())) {
				continue;
			}
			EObject nextSemantic = ((GraphicalEditPart) nextEditPart).resolveSemanticElement();
			if (semantic.equals(nextSemantic)) {
				return nextEditPart;
			}
			if (byType && nextSemantic instanceof TypedElement && ((TypedElement) nextSemantic).getType() != null) {
				EObject nextSemanticType = ((TypedElement) nextSemantic).getType();
				if (semantic.equals(nextSemanticType)) {
					return nextEditPart;
				}
			}
		}
		return null;
	}

	/**
	 * Check if the edit part type is the best one to represent an object of the
	 * given EClass type
	 *
	 * @param editPartClass
	 *            the type of EditPart which may represent a semantic element
	 * @param eClass
	 *            the EClass type of the represented semantic element
	 * @return true if an edit part of this type should be selected
	 */
	private boolean isEditPartTypeAdapted(Class<? extends EditPart> editPartClass, EClass eClass) {
		if (DiagramEditPart.class.isAssignableFrom(editPartClass) || CompartmentEditPart.class.isAssignableFrom(editPartClass)) {
			// the edit part is disqualified, as a compartment or a diagram can
			// not be dropped
			return false;
		} else if (GraphicalEditPart.class.isAssignableFrom(editPartClass)) {
			// check the edit part type against advised ones
			return isEditPartTypeSuitableForEClass(editPartClass.asSubclass(GraphicalEditPart.class), eClass);
		} else {
			// only a GraphicalEditPart must be selected
			return false;
		}
	}

	/**
	 * Check if an edit part type correctly represent a semantic element of the
	 * given EClass. Subclasses should implement this method to restrict the
	 * possibilities during drop of a link. If an edit part is not of a suitable
	 * type, returning false will eliminate it to represent the element as a
	 * source or target edit part. This can be used for example to disable label
	 * edit parts, which may represent the same model element as the main node.
	 *
	 * @param editPartClass
	 *            the type of EditPart which must be checked
	 * @param eClass
	 *            the EClass type of the element which the edit part must
	 *            represent
	 * @return the only edit part type which can be selected (return a common
	 *         super type if several edit parts can be chosen)
	 */
	protected boolean isEditPartTypeSuitableForEClass(Class<? extends GraphicalEditPart> editPartClass, EClass eClass) {
		return true;
	}
}
