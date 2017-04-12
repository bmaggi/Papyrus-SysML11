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

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.DropObjectsRequest;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.gmf.diagram.common.edit.policy.CommonDiagramDragDropEditPolicy;
import org.eclipse.papyrus.infra.gmfdiag.common.adapter.SemanticAdapter;
import org.eclipse.papyrus.infra.gmfdiag.common.commands.CommonDeferredCreateConnectionViewCommand;
import org.eclipse.papyrus.sysml.blocks.NestedConnectorEnd;
import org.eclipse.papyrus.sysml.diagram.internalblock.dnd.helper.CustomLinkMappingHelper;
import org.eclipse.papyrus.sysml.diagram.internalblock.provider.CustomGraphicalTypeRegistry;
import org.eclipse.papyrus.sysml.diagram.internalblock.utils.PortDropHelper;
import org.eclipse.papyrus.sysml.service.types.utils.ConnectorUtils;
import org.eclipse.papyrus.uml.diagram.common.edit.part.AbstractElementNodeLabelEditPart;
import org.eclipse.papyrus.uml.diagram.common.utils.UMLGraphicalTypes;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EncapsulatedClassifier;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.util.UMLUtil;

/** Customization of the DND edit policy for the Internal Block Diagram */
public class CustomDragDropEditPolicy extends CommonDiagramDragDropEditPolicy {

	private ConnectorUtils utils = new ConnectorUtils();

	/** Default constructor. */
	public CustomDragDropEditPolicy() {
		super(new CustomLinkMappingHelper());
		registry = new CustomGraphicalTypeRegistry();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Set<String> getSpecificDropBehaviorTypes() {
		Set<String> specificDropBehaviorTypes = new HashSet<String>();

		specificDropBehaviorTypes.add(UMLGraphicalTypes.LINK_UML_CONNECTOR_ID);

		return specificDropBehaviorTypes;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ICommand getSpecificDropCommand(DropObjectsRequest dropRequest, EObject droppedEObject, String nodeType, String edgeType) {
		if ((UMLGraphicalTypes.LINK_UML_CONNECTOR_ID.equals(edgeType)) && (droppedEObject instanceof Connector)) {
			return getConnectorDropCommand(dropRequest, (Connector) droppedEObject, edgeType);
		}

		return org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand.INSTANCE;
	}

	protected ICommand getConnectorDropCommand(DropObjectsRequest dropRequest, Connector droppedEObject, String edgeType) {

		// Only manage binary link during drop
		if (droppedEObject.getEnds().size() != 2) {
			return org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand.INSTANCE;
		}

		ConnectorEnd source = droppedEObject.getEnds().get(0);
		ConnectorEnd target = droppedEObject.getEnds().get(1);

		return dropBinaryLink(new CompositeCommand("drop Connector"), source.getRole(), target.getRole(), edgeType, dropRequest.getLocation(), droppedEObject);
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
	 * @param linkVISUALID
	 *            the link VISUALID used to create the view
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
		IAdaptable droppedViewAdapter = new SemanticAdapter(semanticLink, null);

		CreateConnectionViewRequest.ConnectionViewDescriptor linkdescriptor = new CreateConnectionViewRequest.ConnectionViewDescriptor(droppedViewAdapter, edgeType, getDiagramPreferencesHint());
		CommonDeferredCreateConnectionViewCommand createConnectionViewCommand = new CommonDeferredCreateConnectionViewCommand(getEditingDomain(), edgeType, sourceViewAdapter, targetViewAdapter, getViewer(), getDiagramPreferencesHint(), linkdescriptor, null);
		createConnectionViewCommand.setElement(semanticLink);
		cc.compose(createConnectionViewCommand);
		return cc;
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
		IAdaptable result = getElement2IAdaptableRegistryHelper().findAdapter(source);
		if (result != null) {
			return result;
		}
		GraphicalEditPart editPart = (GraphicalEditPart) lookForEditPart(source);
		if (editPart != null) {
			return new SemanticAdapter(null, editPart.getModel());
		}
		if (isPortSource(source)) {
			ICommand createPortCommand = createPortViewCommand(source, dropLocation);
			cc.add(createPortCommand);
			return (IAdaptable) createPortCommand.getCommandResult().getReturnValue();
		}
		ICommand createCommand = getDefaultDropNodeCommand(edgeType, dropLocation, source);
		cc.add(createCommand);
		return (IAdaptable) createCommand.getCommandResult().getReturnValue();
	}

	protected boolean isPortSource(Element source) {
		return source instanceof Port;
	}

	private ICommand createPortViewCommand(Element source, Point dropLocation) {
		if (false == source instanceof Port) {
			return new CommandProxy(UnexecutableCommand.INSTANCE);
		}
		EditPart portContainer = lookForEditPart(source.eContainer(), true);
		if (portContainer == null) {
			return new CommandProxy(UnexecutableCommand.INSTANCE);
		}
		PortDropHelper portDropHelper = new PortDropHelper(getEditingDomain());
		return portDropHelper.getDropPortOnPart((Port) source, dropLocation.getCopy(), (GraphicalEditPart) portContainer);
	}

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
			if (!isEditPartTypeAdapted(nextEditPart.getClass(), semantic.eClass())) {
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
			// the edit part is disqualified, as a compartment or a diagram can not be dropped
			return false;
		} else if (AbstractElementNodeLabelEditPart.class.isAssignableFrom(editPartClass)) {
			return false;
		} else if (GraphicalEditPart.class.isAssignableFrom(editPartClass)) {
			// check the edit part type against advised ones
			return isEditPartTypeSuitableForEClass(editPartClass.asSubclass(GraphicalEditPart.class), eClass);
		}
		return false;
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

	/**
	 * get the first valid target view for the given source. the couple source/target is valid if the path to them from the future container is valid
	 *
	 * @param sourceView
	 * @param targetViews
	 * @return
	 */
	private View getFirstValidTargetViewForSource(View sourceView, Collection<View> targetViews, Connector droppedConnector) {
		for (View targetView : targetViews) {
			if (isValidTargetViewForSource(sourceView, targetView, droppedConnector)) {
				return targetView;
			}
		}

		return null;
	}

	private boolean isValidTargetViewForSource(View sourceView, View targetView, Connector droppedConnector) {
		if (!(utils.isCrossingEncapsulation(sourceView, targetView))) {
			// no encapsulation. Computes the end paths
			List<Property> sourceNestedPaths = utils.getNestedPropertyPath(sourceView, targetView);
			List<Property> targetNestedPaths = utils.getNestedPropertyPath(targetView, sourceView);

			// check this is compatible with current connector
			ConnectorEnd sourceConnectorEnd = utils.getSourceConnectorEnd(droppedConnector);
			NestedConnectorEnd nestedSourceConnectorEnd = UMLUtil.getStereotypeApplication(sourceConnectorEnd, NestedConnectorEnd.class);
			if (nestedSourceConnectorEnd != null) {
				List<Property> currentSourceConnectorPropertyPath = nestedSourceConnectorEnd.getPropertyPath();
				// compare the 2 list
				if (!sourceNestedPaths.equals(currentSourceConnectorPropertyPath)) {
					return false;
				}

			} else { // no end for the current connector, so the sourceNestedPath should be empty
				if (!sourceNestedPaths.isEmpty()) {
					return false;
				}
			}


			ConnectorEnd targetConnectorEnd = utils.getTargetConnectorEnd(droppedConnector);
			NestedConnectorEnd nestedTargetConnectorEnd = UMLUtil.getStereotypeApplication(targetConnectorEnd, NestedConnectorEnd.class);
			if (nestedTargetConnectorEnd != null) {
				List<Property> currentTargetConnectorPropertyPath = nestedTargetConnectorEnd.getPropertyPath();
				// compare the 2 list
				if (!targetNestedPaths.equals(currentTargetConnectorPropertyPath)) {
					return false;
				}

			} else { // no end for the current connector, so the targetNestedPaths should be empty
				if (!targetNestedPaths.isEmpty()) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * This methods looks for views representing a given {@link ConnectorEnd} in the host diagram.
	 *
	 * @param end
	 *            the {@link ConnectorEnd} to look for.
	 * @return the list of {@link View} representing the eObject.
	 */
	protected Set<View> getViewsForConnectorEnd(ConnectorEnd end) {
		Set<View> views = new HashSet<View>();

		// Retrieve host diagram
		View hostView = ((IGraphicalEditPart) getHost()).getNotationView();
		View hostDiagram = (hostView instanceof Diagram) ? hostView : hostView.getDiagram();

		// Retrieve all views for the ConnectorEnd role
		EReference[] refs = { NotationPackage.eINSTANCE.getView_Element() };
		@SuppressWarnings("unchecked")
		Collection<View> relatedViews = EMFCoreUtil.getReferencers(end.getRole(), refs);

		// TODO: We should filter views not only for the current diagram,
		// but also consider only views nested within the owner of the connector that are closest to the host view
		// (in case there are several instances of a Part within which the connector could appear).
		// Connector connector = (Connector)end.getOwner();
		// StructuredClassifier connectorOwner = (StructuredClassifier)connector.getOwner();
		// Parse and select views from host diagram only
		Iterator<View> it = relatedViews.iterator();
		while (it.hasNext()) {
			View currentView = it.next();
			if (currentView.getDiagram() == hostDiagram) { // FIXME: Cf TODO above.

				boolean isInView = false;
				View containerView = currentView;
				while (containerView != null && !(containerView instanceof Diagram)) {
					if (containerView == getReferenceViewForConnectorEnd()) {
						isInView = true;
					}
					containerView = ViewUtil.getContainerView(containerView);
				}

				if (isInView) {

					EObject containerElement = ViewUtil.getContainerView(currentView).getElement();

					// If the ConnectorEnd partWithPort is not null, only select Views for which
					// the graphical parent reference partWithPort.
					if (end.getPartWithPort() != null) {
						if (containerElement == end.getPartWithPort()) {
							views.add(currentView);
						}
					} else {
						// If the role is a Port, its graphical parent is a EncapsulatedClassifier
						if (end.getRole() instanceof Port) {
							if (containerElement instanceof EncapsulatedClassifier) {
								views.add(currentView);
							} else if (containerElement instanceof Property) {
								Property property = (Property) containerElement;
								if (property.getType() == end.getRole().getOwner()) {
									views.add(currentView);
								}
							}
						} else { // No further test needed
							views.add(currentView);
						}

					}
				}
			}
		}

		return views;
	}

	protected View getReferenceViewForConnectorEnd() {
		return ((IGraphicalEditPart) getHost()).getNotationView();
	}

}
