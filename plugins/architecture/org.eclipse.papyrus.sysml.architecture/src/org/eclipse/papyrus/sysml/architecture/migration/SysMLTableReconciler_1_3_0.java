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

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.core.command.AbstractCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.papyrus.infra.core.architecture.RepresentationKind;
import org.eclipse.papyrus.infra.core.architecture.merged.MergedArchitectureDescriptionLanguage;
import org.eclipse.papyrus.infra.nattable.representation.PapyrusSyncTable;
import org.eclipse.papyrus.infra.architecture.ArchitectureDomainManager;
import org.eclipse.papyrus.infra.nattable.common.reconciler.TableReconciler;
import org.eclipse.papyrus.infra.nattable.model.nattable.Table;
import org.eclipse.papyrus.sysml.architecture.SysMLArchitectureContextIds;

/**
 * UML Table Reconciler from 1.0.0 to 1.1.0 that replaces the old prototype value for tables
 * with ones based on table kinds
 */
public class SysMLTableReconciler_1_3_0 extends TableReconciler {

	private static final String ALLOCATION_TABLE = "Allocation Table";
	private static final String REQUIREMENT_TABLE = "Requirement Table";

	@Override
	public ICommand getReconcileCommand(Table table) {
		if (table.getPrototype() instanceof org.eclipse.papyrus.infra.viewpoints.configuration.PapyrusView) {
			org.eclipse.papyrus.infra.viewpoints.configuration.PapyrusView oldTableKind =
				(org.eclipse.papyrus.infra.viewpoints.configuration.PapyrusView) table.getPrototype();
			
			RepresentationKind newTableKind = null;
			if (oldTableKind != null) {
				if (ALLOCATION_TABLE.equals(oldTableKind.getName())) {
					newTableKind = getSyncTableKind(oldTableKind.getName(), table);
				} else if (REQUIREMENT_TABLE.equals(oldTableKind.getName())) {
					newTableKind = getSyncTableKind(oldTableKind.getName(), table);
				}
			};
			
			if (newTableKind != null)
				return new ReplaceTablePrototypeCommand(table, newTableKind);
		}
		return null;
	}

	protected PapyrusSyncTable getSyncTableKind(String name, Table table) {
		ArchitectureDomainManager manager = ArchitectureDomainManager.getInstance();
		MergedArchitectureDescriptionLanguage context = (MergedArchitectureDescriptionLanguage) manager.getArchitectureContextById(SysMLArchitectureContextIds.SysML);
		for(RepresentationKind pKind : context.getRepresentationKinds()) {
			if (pKind.getName().equals(name)) {
				PapyrusSyncTable tKind = (PapyrusSyncTable) pKind;
				if (tKind.getModelRules().get(0).getElement().isInstance(table.getContext())) {
					return tKind;
				}
			}
		}
		return null;
	}

	/**
	 * A command to replace the old table prototype with the new representation kinds 
	 */
	protected class ReplaceTablePrototypeCommand extends AbstractCommand {

		private Table table;
		private RepresentationKind newKind;

		public ReplaceTablePrototypeCommand(Table table, RepresentationKind newKind) {
			super("Replace the papyrus view style from 1.0.0 to 1.1.0");
			this.table = table;
			this.newKind = newKind;
		}

		@Override
		protected CommandResult doExecuteWithResult(IProgressMonitor progressMonitor, IAdaptable info) throws ExecutionException {
			table.setPrototype(newKind);
			return CommandResult.newOKCommandResult();
		}

		@Override
		public boolean canUndo() {
			return false;
		}

		@Override
		public boolean canRedo() {
			return false;
		}

		@Override
		protected CommandResult doRedoWithResult(IProgressMonitor progressMonitor, IAdaptable info) throws ExecutionException {
			throw new ExecutionException("Should not be called, canRedo false"); //$NON-NLS-1$
		}

		@Override
		protected CommandResult doUndoWithResult(IProgressMonitor progressMonitor, IAdaptable info) throws ExecutionException {
			throw new ExecutionException("Should not be called, canUndo false"); //$NON-NLS-1$
		}
	}
}
