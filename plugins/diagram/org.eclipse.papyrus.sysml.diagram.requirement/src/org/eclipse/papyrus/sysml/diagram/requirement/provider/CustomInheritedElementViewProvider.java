/*****************************************************************************
 * Copyright (c) 2010 CEA LIST.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 *   Mickaël ADAM (ALL4TEC) mickael.adam@all4tec.net - Bug 510587
 *****************************************************************************/
package org.eclipse.papyrus.sysml.diagram.requirement.provider;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateEdgeViewOperation;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateNodeViewOperation;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.papyrus.sysml.diagram.common.utils.SysMLGraphicalTypes;
import org.eclipse.papyrus.sysml.service.types.element.SysMLElementTypes;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ClassAttributeCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ClassNameEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ClassNestedClassifierCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ClassOperationCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.part.UMLVisualIDRegistry;
import org.eclipse.papyrus.uml.diagram.common.helper.PreferenceInitializerForElementHelper;

/**
 * SysML Requirement diagram inherited elements view providers from UML Class diagram view providers
 *
 */
public class CustomInheritedElementViewProvider extends InheritedClassDiagramViewProvider {

	public CustomInheritedElementViewProvider() {
		super();
	}

	/**
	 * 
	 * @see org.eclipse.papyrus.sysml.diagram.requirement.provider.InheritedClassDiagramViewProvider#provides(org.eclipse.gmf.runtime.diagram.core.services.view.CreateNodeViewOperation)
	 */
	@Override
	protected boolean provides(final CreateNodeViewOperation operation) {
		boolean provides = false;

		// Must have a container
		if (operation.getContainerView() != null) {

			// This provider is registered for Requirement Diagram only
			String diagramType = operation.getContainerView().getDiagram().getType();
			if (ElementTypes.DIAGRAM_ID.equals(diagramType)) {

				// Get the type of the container
				String containerGraphicalType = operation.getContainerView().getType();
				if (ElementTypes.DIAGRAM_ID.equals(containerGraphicalType)) {

					// get the type
					IElementType elementType = (IElementType) operation.getSemanticAdapter().getAdapter(IElementType.class);
					if (SysMLElementTypes.REQUIREMENT == elementType
							|| SysMLElementTypes.PROBLEM == elementType
							|| SysMLElementTypes.RATIONALE == elementType) {
						provides = true;
					}
				}
			}
		}
		return provides || super.provides(operation);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.sysml.diagram.requirement.provider.InheritedClassDiagramViewProvider#provides(org.eclipse.gmf.runtime.diagram.core.services.view.CreateEdgeViewOperation)
	 */
	@Override
	protected boolean provides(final CreateEdgeViewOperation operation) {
		boolean provides = false;

		// Must have a container
		if (operation.getContainerView() != null) {

			String diagramType = operation.getContainerView().getDiagram().getType();
			if (ElementTypes.DIAGRAM_ID.equals(diagramType)) {

				// get the type
				IElementType elementType = (IElementType) operation.getSemanticAdapter().getAdapter(IElementType.class);
				if (SysMLElementTypes.COPY == elementType
						|| SysMLElementTypes.DERIVE_REQT == elementType
						|| SysMLElementTypes.SATISFY == elementType
						|| SysMLElementTypes.VERIFY == elementType) {
					provides = true;
				}
			}
		}
		return provides || super.provides(operation);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.sysml.diagram.requirement.provider.InheritedClassDiagramViewProvider#createNode(org.eclipse.core.runtime.IAdaptable, org.eclipse.gmf.runtime.notation.View, java.lang.String, int, boolean,
	 *      org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint)
	 */
	@Override
	public Node createNode(final IAdaptable semanticAdapter, final View containerView, final String semanticHint, final int index, final boolean persisted, final PreferencesHint preferencesHint) {
		Node node = null;
		if (SysMLElementTypes.REQUIREMENT.getDisplayName().equals(semanticHint)) {
			node = createRequirementNode(semanticAdapter, containerView, index, persisted, preferencesHint);
		} else if (SysMLElementTypes.PROBLEM.getDisplayName().equals(semanticHint)
				|| SysMLElementTypes.RATIONALE.getDisplayName().equals(semanticHint)) {

			final EObject domainElement = getSemanticElement(semanticAdapter);
			node = createComment_Shape(domainElement, containerView, index, persisted, preferencesHint);
		}

		return null != node ? node : super.createNode(semanticAdapter, containerView, semanticHint, index, persisted, preferencesHint);
	}


	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.papyrus.sysml.diagram.requirement.provider.InheritedClassDiagramViewProvider#createEdge(org.eclipse.core.runtime.IAdaptable, org.eclipse.gmf.runtime.notation.View, java.lang.String, int, boolean,
	 *      org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint)
	 */
	@Override
	public Edge createEdge(final IAdaptable semanticAdapter, final View containerView, final String semanticHint, final int index, final boolean persisted, final PreferencesHint preferencesHint) {
		Edge edge = null;

		if (SysMLElementTypes.COPY.getDisplayName().equals(semanticHint)
				|| SysMLElementTypes.DERIVE_REQT.getDisplayName().equals(semanticHint)
				|| SysMLElementTypes.SATISFY.getDisplayName().equals(semanticHint)
				|| SysMLElementTypes.VERIFY.getDisplayName().equals(semanticHint)) {
			final EObject domainElement = getSemanticElement(semanticAdapter);
			edge = createAbstraction_Edge(domainElement, containerView, index, persisted, preferencesHint);
		}

		return null != edge ? edge : super.createEdge(semanticAdapter, containerView, semanticHint, index, persisted, preferencesHint);
	}

	/**
	 * create a <code>Requirement Node</code>.
	 * 
	 * @param IAdaptable
	 *            (for semantic element)
	 * @param containerView
	 *            the container view that will contain the created view.
	 * @param index
	 *            position in the container view's list of children views.
	 * @param persisted
	 *            indicates if the created view will be persisted or not
	 * @param preferencesHint
	 *            The preference hint that is to be used to find the appropriate
	 *            preference store from which to retrieve diagram preference
	 *            values. The preference hint is mapped to a preference store in
	 *            the preference registry <@link DiagramPreferencesRegistry>.
	 * @return the created <code>Requirement Node</code>
	 */
	protected Shape createRequirementNode(final IAdaptable semanticAdapter, final View containerView, final int index, final boolean persisted, final PreferencesHint preferencesHint) {
		Shape node;
		node = NotationFactory.eINSTANCE.createShape();
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(SysMLGraphicalTypes.SHAPE_SYSML_REQUIREMENT_AS_CLASSIFER_ID);
		ViewUtil.insertChildView(containerView, node, index, persisted);
		final EObject domainElement = getSemanticElement(semanticAdapter);
		node.setElement(domainElement);
		stampShortcut(containerView, node);
		// initializeFromPreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint.getPreferenceStore();
		PreferenceInitializerForElementHelper.initForegroundFromPrefs(node, prefStore, "Class");//$NON-NLS-1$
		PreferenceInitializerForElementHelper.initFontStyleFromPrefs(node, prefStore, "Class");//$NON-NLS-1$
		PreferenceInitializerForElementHelper.initBackgroundFromPrefs(node, prefStore, "Class");//$NON-NLS-1$

		// createLabel(node, UMLVisualIDRegistry.getType(ClassNameEditPart.VISUAL_ID));

		createLabel(node, UMLVisualIDRegistry.getType(ClassNameEditPart.VISUAL_ID));

		createCompartment(node, UMLVisualIDRegistry.getType(ClassAttributeCompartmentEditPart.VISUAL_ID), true, true, true, true);
		createCompartment(node, UMLVisualIDRegistry.getType(ClassOperationCompartmentEditPart.VISUAL_ID), true, true, true, true);
		createCompartment(node, UMLVisualIDRegistry.getType(ClassNestedClassifierCompartmentEditPart.VISUAL_ID), true, true, true, true);

		/*
		 * Addition of the information compartment, and the labels it contains.
		 */
		Node compartment = createCompartment(node, SysMLGraphicalTypes.COMPARTMENT_SYSML_REQUIREMENT_IDINFO_AS_LIST_ID, true, true, true, true);
		createLabel(compartment, SysMLGraphicalTypes.LABEL_SYSML_REQUIREMENT_ID_ID);
		createLabel(compartment, SysMLGraphicalTypes.LABEL_SYSML_REQUIREMENT_TEXT_ID);

		PreferenceInitializerForElementHelper.initCompartmentsStatusFromPrefs(node, prefStore, "Class");//$NON-NLS-1$
		return node;
	}
}
