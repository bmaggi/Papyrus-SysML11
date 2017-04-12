package org.eclipse.papyrus.sysml.diagram.blockdefinition.tests.canonical;

import static org.eclipse.papyrus.sysml.diagram.blockdefinition.tests.utils.EditorUtils.getDiagramCommandStack;
import static org.eclipse.papyrus.sysml.diagram.blockdefinition.tests.utils.EditorUtils.getDiagramView;
import static org.eclipse.papyrus.sysml.diagram.blockdefinition.tests.utils.EditorUtils.getTransactionalEditingDomain;
import static org.eclipse.papyrus.sysml.diagram.blockdefinition.tests.utils.TestPrepareUtils.createElement;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.papyrus.infra.services.edit.service.ElementEditServiceUtils;
import org.eclipse.papyrus.sysml.diagram.blockdefinition.tests.AbstractTest;
import org.eclipse.papyrus.sysml.service.types.element.SysMLElementTypes;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.junit.Assert;
import org.junit.Test;

public class TestAggregationValue extends AbstractTest {

	@Test
	public void testValueTypedBlockPropertyAggregation() throws Exception {
		EObject container = createElement(SysMLElementTypes.BLOCK, getDiagramView());
		Assert.assertNotNull("Can't create container.", container);
		// create ValueType node
		EObject valueType = createElement(SysMLElementTypes.VALUE_TYPE, getDiagramView());
		Assert.assertNotNull("Can't create ValueType node.", valueType);
		// add Property to the container
		EObject propertyObject = createElement(SysMLElementTypes.REFERENCE_PROPERTY, container);
		Assert.assertNotNull("Can't create property.", propertyObject);
		// test property aggregation
		Assert.assertEquals("Wrong aggregation.", AggregationKind.NONE_LITERAL, ((Property) propertyObject).getAggregation());
		// setup type of the property
		SetRequest setTypeRequest = new SetRequest(getTransactionalEditingDomain(), propertyObject, UMLPackage.eINSTANCE.getTypedElement_Type(), valueType);
		ICommand setTypeCommand = ElementEditServiceUtils.getCommandProvider(propertyObject.eContainer()).getEditCommand(setTypeRequest);
		Assert.assertTrue("SetType command is not executable.", setTypeCommand.canExecute());
		getDiagramCommandStack().execute(new ICommandProxy(setTypeCommand));
		// test property aggregation
		Assert.assertEquals("Wrong aggregation.", AggregationKind.COMPOSITE_LITERAL, ((Property) propertyObject).getAggregation());
	}
}
