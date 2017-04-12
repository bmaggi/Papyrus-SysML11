/*****************************************************************************
 * Copyright (c) 2017 CEA LIST and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 	 Maged Elaasar - Initial API and Implementation
 *
 *****************************************************************************/
package org.eclipse.papyrus.sysml.architecture.migration;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.papyrus.infra.core.architecture.RepresentationKind;
import org.eclipse.papyrus.infra.core.architecture.merged.MergedArchitectureDescriptionLanguage;
import org.eclipse.papyrus.infra.architecture.ArchitectureDomainManager;
import org.eclipse.papyrus.infra.gmfdiag.common.reconciler.DiagramReconciler_1_3_0;
import org.eclipse.papyrus.infra.gmfdiag.representation.PapyrusDiagram;
import org.eclipse.papyrus.infra.viewpoints.policy.ProfileUtils;
import org.eclipse.papyrus.infra.viewpoints.style.PapyrusViewStyle;
import org.eclipse.papyrus.sysml.architecture.SysMLArchitectureContextIds;

/**
 * SysML Diagram Reconciler from 1.2.0 to 1.3.0 that switches the old PapyrusViewStyle by
 * the new PapyrusDiagramStyle
 */
public class SysMLDiagramReconciler_1_3_0 extends DiagramReconciler_1_3_0 {

	private static final String BLOCK_DEFINITION_DIAGRAM = "BlockDefinition";
	private static final String INTERNAL_BLOCK_DIAGRAM = "InternalBlock";
	private static final String PARAMETRIC_DIAGRAM = "Parametric";
	private static final String REQUIREMENT_DIAGRAM = "RequirementDiagram";
	
	/**
	 * @see org.eclipse.papyrus.infra.gmfdiag.common.reconciler.DiagramReconciler_1_3_0#getPapyrusDiagram(org.eclipse.papyrus.infra.viewpoints.style.PapyrusViewStyle)
	 *
	 * @param oldStyle
	 * @return
	 */
	@Override
	protected PapyrusDiagram getDiagramKind(Diagram diagram, PapyrusViewStyle oldStyle) {
		if (oldStyle != null) {
			org.eclipse.papyrus.infra.viewpoints.configuration.PapyrusDiagram oldDiagramKind =
				(org.eclipse.papyrus.infra.viewpoints.configuration.PapyrusDiagram) oldStyle.getConfiguration();
			if (oldDiagramKind != null)
				return getDiagramKind(oldDiagramKind.getName(), diagram);
		}
		if (BLOCK_DEFINITION_DIAGRAM.equals(diagram.getType())) {
			return getDiagramKind("Block Definition Diagram", diagram);
		} else if (INTERNAL_BLOCK_DIAGRAM.equals(diagram.getType())) {
			return getDiagramKind("Internal Block Diagram", diagram);
		} else if (PARAMETRIC_DIAGRAM.equals(diagram.getType())) {
			return getDiagramKind("Parametric Diagram", diagram);
		} else if (REQUIREMENT_DIAGRAM.equals(diagram.getType())) {
			return getDiagramKind("Requirement Diagram", diagram);
		}
		return null;
	}

	/**
	 * Gets the diagram kind that matches given name and that supports the given diagram
	 */
	protected PapyrusDiagram getDiagramKind(String name, Diagram diagram) {
		ArchitectureDomainManager manager = ArchitectureDomainManager.getInstance();
		MergedArchitectureDescriptionLanguage context = (MergedArchitectureDescriptionLanguage) manager.getArchitectureContextById(SysMLArchitectureContextIds.SysML);
		for(RepresentationKind pKind : context.getRepresentationKinds()) {
			if (pKind.getName().equals(name)) {
				PapyrusDiagram dKind = (PapyrusDiagram) pKind;
				if (dKind.getModelRules().get(0).getElement().isInstance(diagram.getElement())) {
					boolean allStereotypesApplied = true;
					Collection<EClass> appliedStereotypes = 
						ProfileUtils.getProfileHelper().getAppliedStereotypes(diagram.getElement());
					for (EClass stereotype : dKind.getModelRules().get(0).getStereotypes()) {
						if (!appliedStereotypes.contains(stereotype)) {
							allStereotypesApplied = false;
						}
					}
					if (allStereotypesApplied)
						return dKind;
				}
			}
		}
		return null;
	}
}
