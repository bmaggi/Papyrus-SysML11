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

package org.eclipse.papyrus.gmf.diagram.common.provider;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.infra.gmfdiag.common.service.visualtype.AbstractVisualTypeProvider;
import org.eclipse.papyrus.infra.gmfdiag.common.service.visualtype.IVisualTypeProvider;
import org.eclipse.papyrus.sysml.diagram.common.Activator;
import org.eclipse.papyrus.sysml.service.types.element.SysMLElementTypes;

/**
 * Generic visual type provider for SysML diagrams that, for the most part, delegates
 * to the diagram's specific {@link IGraphicalTypeRegistry} and the {@link IVisualTypeProvider}
 * of the UML diagram that it extends.
 */
public class SysMLVisualTypeProvider extends AbstractVisualTypeProvider {

	private final IGraphicalTypeRegistry types;
	private final IVisualTypeProvider uml;

	/**
	 * Initializes me with my delegates.
	 *
	 * @param types
	 *            the graphical type registry to which I delegate SysML-specific queries
	 * @param uml
	 *            the visual type provider of the UML diagram that the SysML diagram extends
	 */
	protected SysMLVisualTypeProvider(IGraphicalTypeRegistry types, IVisualTypeProvider uml) {
		super();

		this.types = types;
		this.uml = uml;
	}

	@Override
	public IElementType getElementType(Diagram diagram, String viewType) {
		IElementType result = getCustomElementType(viewType);

		if ((result == null) && (viewType != null)) {
			// Look for a standard SysML element type
			for (Field field : SysMLElementTypes.class.getDeclaredFields()) {
				if (IElementType.class.isAssignableFrom(field.getType()) && Modifier.isStatic(field.getModifiers()) && Modifier.isPublic(field.getModifiers())) {
					try {
						IElementType candidate = (IElementType) field.get(null);
						if ((candidate instanceof IHintedType) && viewType.equals(((IHintedType) candidate).getSemanticHint())) {
							result = candidate;
							break;
						}
					} catch (Exception e) {
						// Reflection problems on this class shouldn't happen
						Activator.log.error(e);
					}
				}
			}

			if (result == null) {
				// Still null? Try the UML diagram
				result = uml.getElementType(diagram, viewType);
			}
		}

		return result;
	}

	/**
	 * Overridden by subclasses in diagrams that provide custom SysML visual element types
	 * in their {@code ElementTypes} enumerations.
	 * 
	 * @param viewType
	 *            the view type to look up
	 * @return the corresponding element type, or {@code null} if none
	 */
	protected IElementType getCustomElementType(String viewType) {
		return null;
	}

	@Override
	public String getNodeType(View parentView, EObject element) {
		String result = types.getNodeGraphicalType(element, parentView.getType());

		if (result == null) {
			// Try the UML diagram
			result = uml.getNodeType(parentView, element);
		}

		return result;
	}

	@Override
	public String getLinkType(Diagram diagram, EObject element) {
		String result = types.getEdgeGraphicalType(element);

		if (result == null) {
			// Try the UML diagram
			result = uml.getLinkType(diagram, element);
		}

		return result;
	}

}
