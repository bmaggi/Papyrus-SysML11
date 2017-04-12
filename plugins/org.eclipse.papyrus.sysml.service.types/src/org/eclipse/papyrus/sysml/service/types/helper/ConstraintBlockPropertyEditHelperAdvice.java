/*****************************************************************************
 * Copyright (c) 2010 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *****************************************************************************/
package org.eclipse.papyrus.sysml.service.types.helper;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.ISpecializationType;
import org.eclipse.gmf.runtime.emf.type.core.commands.ConfigureElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelperAdvice;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.papyrus.sysml.blocks.ValueType;
import org.eclipse.papyrus.sysml.constraints.ConstraintBlock;
import org.eclipse.papyrus.sysml.service.types.element.SysMLElementTypes;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.util.UMLUtil;

/** Specific advice for Property creation owned by a {@link ConstraintBlock} */
public class ConstraintBlockPropertyEditHelperAdvice extends AbstractEditHelperAdvice {

	/**
	 * Every {@link Property} created in the context of a {@link ConstraintBlock} is Composite.
	 *
	 * {@inheritDoc}
	 */
	@Override
	protected ICommand getBeforeConfigureCommand(final ConfigureRequest request) {

		return new ConfigureElementCommand(request) {

			@Override
			protected CommandResult doExecuteWithResult(IProgressMonitor progressMonitor, IAdaptable info) throws ExecutionException {
				Property element = (Property) request.getElementToConfigure();
				if (element != null) {

					Element owner = element.getOwner();
					if (((ISpecializationType) SysMLElementTypes.CONSTRAINT_BLOCK).getMatcher().matches(owner)) {
						element.setAggregation(AggregationKind.COMPOSITE_LITERAL);
					}
				}
				return CommandResult.newOKCommandResult(element);
			}
		};
	}


	@Override
	protected ICommand getBeforeSetCommand(SetRequest request) {
		ICommand gmfCommand = super.getBeforeSetCommand(request);
		EObject elementToEdit = request.getElementToEdit();
		if (false == elementToEdit instanceof Property || request.getFeature() != UMLPackage.eINSTANCE.getTypedElement_Type()) {
			return gmfCommand;
		}
		Property propertyToEdit = (Property) elementToEdit;
		if (!isBlockOrValueTypeProperty(propertyToEdit)) {
			return gmfCommand;
		}
		AggregationKind expectedAggregation = getExpectedAggregationKind(propertyToEdit.getType(), (Type) request.getValue(), propertyToEdit.getAggregation());
		if (expectedAggregation == null) {
			return gmfCommand;
		}
		EStructuralFeature feature = UMLPackage.eINSTANCE.getProperty_Aggregation();
		SetRequest setRequest = new SetRequest(propertyToEdit, feature, expectedAggregation);
		ICommand changeAggregationCommand = new SetValueCommand(setRequest);
		return CompositeCommand.compose(gmfCommand, changeAggregationCommand);
	}

	private boolean isBlockOrValueTypeProperty(Property property) {
		Element owner = property.getOwner();
		return ((ISpecializationType) SysMLElementTypes.BLOCK).getMatcher().matches(owner) || ((ISpecializationType) SysMLElementTypes.VALUE_TYPE).getMatcher().matches(owner);
	}

	private boolean isValueType(Type value) {
		return value instanceof DataType && UMLUtil.getStereotypeApplication(value, ValueType.class) != null;
	}

	private AggregationKind getExpectedAggregationKind(Type currentType, Type newType, AggregationKind currentAggregationKind) {
		if (isValueType(currentType) && !isValueType(newType) && AggregationKind.COMPOSITE_LITERAL == currentAggregationKind) {
			return AggregationKind.NONE_LITERAL;
		}
		if (!isValueType(currentType) && isValueType(newType)) {
			return AggregationKind.COMPOSITE_LITERAL;
		}
		return null;
	}
}
