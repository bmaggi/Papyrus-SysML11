/*****************************************************************************
 * Copyright (c) 2012 CEA LIST.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Camille Letavernier (CEA LIST) camille.letavernier@cea.fr - Initial API and implementation
 *****************************************************************************/
package org.eclipse.papyrus.sysml.nattable.requirement.tests.tests;


import org.eclipse.papyrus.sysml.nattable.requirement.tests.bugs.AxisChangeIndexWithConfigurationTest;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.bugs.AxisChangeIndexWithoutConfigurationTest;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.bugs.InvertedAxisChangeIndexWithConfigurationTest;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.bugs.InvertedAxisChangeIndexWithoutConfigurationTest;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.bugs.RequirementReorderingTest;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.importfile.ImportCellsAll_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.importfile.ImportEmptyAddAll_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.importfile.ImportEmptyReplaceAll_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.importfile.ImportEmptySkipAll_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.importfile.ImportRowsAddAll_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.importfile.ImportRowsReplaceAll_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.importfile.ImportRowsSkipAll_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.insert.InsertEmptyAddAll_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.insert.InsertEmptyAxisIdentifierNotExisting_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.insert.InsertEmptyClipboard_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.insert.InsertEmptyFailColumns_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.insert.InsertEmptyReplaceAll_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.insert.InsertEmptySkipAll_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.insert.InsertEmptyWarningNotExisting_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.insert.InsertRowsAddAll_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.insert.InsertRowsAxisIdentifierNotExisting_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.insert.InsertRowsFailColumns_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.insert.InsertRowsFailRows_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.insert.InsertRowsReplaceAll_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.insert.InsertRowsSkipAll_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.insert.InsertRowsWarningNotExisting_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.paste.overwrite.PasteCellsOverwriteAll_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.paste.overwrite.PasteCellsOverwriteByOneLine_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.paste.overwrite.PasteCellsOverwriteFailColumns_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.paste.overwrite.PasteCellsOverwriteFailRows_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.paste.overwrite.PasteColumnsOverwriteAll_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.paste.overwrite.PasteColumnsOverwriteByOneLine_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.paste.overwrite.PasteColumnsOverwriteFailColumns_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.paste.overwrite.PasteColumnsOverwriteFailRows_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.paste.overwrite.PasteEmptyClipboard_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.paste.overwrite.PasteEmptyOverwriteAddAll_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.paste.overwrite.PasteEmptyOverwriteAxisIdentifierNotExisting_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.paste.overwrite.PasteEmptyOverwriteFailColumns_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.paste.overwrite.PasteEmptyOverwriteReplaceAll_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.paste.overwrite.PasteEmptyOverwriteSkipAll_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.paste.overwrite.PasteEmptyOverwriteWarningNotExisting_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.paste.overwrite.PasteRowsOverwriteAll_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.paste.overwrite.PasteRowsOverwriteAxisIdentifierNotExisting_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.paste.overwrite.PasteRowsOverwriteFailColumns_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.paste.overwrite.PasteRowsOverwriteFailRows_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.paste.overwrite.PasteRowsOverwriteWarningNotExisting_Test;
import org.eclipse.papyrus.sysml.nattable.requirement.tests.paste.without.service.edit.Bug443814_Enumeration;
import org.junit.runner.RunWith;
import org.eclipse.papyrus.junit.framework.classification.ClassificationSuite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(ClassificationSuite.class)
@SuiteClasses({
		SortAxisRequirementTableTest.class,
		InvertAxisRequirementTableTest.class,
		EditAxisRequirementTableTest.class,
		Bug443814_Enumeration.class,
		AxisChangeIndexWithConfigurationTest.class,
		AxisChangeIndexWithoutConfigurationTest.class,
		InvertedAxisChangeIndexWithConfigurationTest.class,
		InvertedAxisChangeIndexWithoutConfigurationTest.class,
		TableCreationInSysMLModelTest.class,
		OpenTableTest.class,
		TableCreationInUMLModel.class,
		RevealRequirementTableTest.class,
		
		// Bug 439501: Test the requirements reordering
		RequirementReorderingTest.class,

		// Paste Overwrite tests
		PasteEmptyClipboard_Test.class,
		
		PasteCellsOverwriteAll_Test.class,
		PasteCellsOverwriteByOneLine_Test.class,
		PasteCellsOverwriteFailColumns_Test.class,
		PasteCellsOverwriteFailRows_Test.class,

		PasteColumnsOverwriteAll_Test.class,
		PasteColumnsOverwriteByOneLine_Test.class,
		PasteColumnsOverwriteFailColumns_Test.class,
		PasteColumnsOverwriteFailRows_Test.class,

		PasteRowsOverwriteAll_Test.class,
		PasteRowsOverwriteFailColumns_Test.class,
		PasteRowsOverwriteFailRows_Test.class,
		PasteRowsOverwriteWarningNotExisting_Test.class,
		PasteRowsOverwriteAxisIdentifierNotExisting_Test.class,
		
		PasteEmptyOverwriteReplaceAll_Test.class,
		PasteEmptyOverwriteAddAll_Test.class,
		PasteEmptyOverwriteSkipAll_Test.class,
		PasteEmptyOverwriteFailColumns_Test.class,
		PasteEmptyOverwriteWarningNotExisting_Test.class,
		PasteEmptyOverwriteAxisIdentifierNotExisting_Test.class,

		// Insert tests
		InsertEmptyClipboard_Test.class,
		
		InsertRowsAddAll_Test.class,
		InsertRowsReplaceAll_Test.class,
		InsertRowsSkipAll_Test.class,
		InsertRowsFailColumns_Test.class,
		InsertRowsFailRows_Test.class,
		InsertRowsWarningNotExisting_Test.class,
		InsertRowsAxisIdentifierNotExisting_Test.class,

		InsertEmptyReplaceAll_Test.class,
		InsertEmptyAddAll_Test.class,
		InsertEmptySkipAll_Test.class,
		InsertEmptyFailColumns_Test.class,
		InsertEmptyWarningNotExisting_Test.class,
		InsertEmptyAxisIdentifierNotExisting_Test.class,
		
		// Import tests
		ImportCellsAll_Test.class,
		ImportEmptyReplaceAll_Test.class,
		ImportEmptyAddAll_Test.class,
		ImportEmptySkipAll_Test.class,
		ImportRowsReplaceAll_Test.class,
		ImportRowsAddAll_Test.class,
		ImportRowsSkipAll_Test.class
})
public class AllTests {

	/** The type of the requirement table */
	public static final String REQUIREMENT_TABLE_ID = "PapyrusSysMLRequirementTable"; //$NON-NLS-1$

	/** the creation command tested for the requirement table */
	public static final String COMMAND_ID = "org.eclipse.papyrus.sysml.nattable.requirement.create.withoutdialog.command"; //$NON-NLS-1$
}
