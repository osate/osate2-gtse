/*******************************************************************************
 * OSATE2-GTSE
 *
 * Copyright 2017 Carnegie Mellon University. All Rights Reserved.
 *
 * NO WARRANTY. THIS CARNEGIE MELLON UNIVERSITY AND SOFTWARE ENGINEERING INSTITUTE
 * MATERIAL IS FURNISHED ON AN "AS-IS" BASIS. CARNEGIE MELLON UNIVERSITY MAKES NO
 * WARRANTIES OF ANY KIND, EITHER EXPRESSED OR IMPLIED, AS TO ANY MATTER INCLUDING,
 * BUT NOT LIMITED TO, WARRANTY OF FITNESS FOR PURPOSE OR MERCHANTABILITY,
 * EXCLUSIVITY, OR RESULTS OBTAINED FROM USE OF THE MATERIAL. CARNEGIE MELLON
 * UNIVERSITY DOES NOT MAKE ANY WARRANTY OF ANY KIND WITH RESPECT TO FREEDOM FROM
 * PATENT, TRADEMARK, OR COPYRIGHT INFRINGEMENT.
 *
 * Released under an Eclipse Public License - v1.0-style license, please see
 * license.txt or contact permission@sei.cmu.edu for full terms. 
 *
 * DM17-0002
 *******************************************************************************/
package org.osate.atsv.integration;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class PreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public PreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("ATSV Integration Preferences");
	}

	@Override
	public void init(IWorkbench workbench) {
		// No special initialization...
	}

	@Override
	protected void createFieldEditors() {
		final BooleanFieldEditor enableATSVIntegration = new BooleanFieldEditor(Activator.ATSV_INTEGRATION_ENABLED,
				"&Enable integration with ATSV", getFieldEditorParent());
		enableATSVIntegration.loadDefault();
		addField(enableATSVIntegration);

		final IntegerFieldEditor atsvConnectionPortNumber = new IntegerFieldEditor(Activator.ATSV_INTEGRATION_PORT,
				"ATSV &Connection Port", getFieldEditorParent());
		atsvConnectionPortNumber.setTextLimit(5);
		atsvConnectionPortNumber.loadDefault();
		atsvConnectionPortNumber.setValidRange(0, 65535);
		atsvConnectionPortNumber.fillIntoGrid(getFieldEditorParent(), 2);
		addField(atsvConnectionPortNumber);

		DirectoryFieldEditor atsvFilesDir = new DirectoryFieldEditor(Activator.ATSV_FILES_DIRECTORY,
				"&Directory for ATSV Files:", getFieldEditorParent());
		atsvFilesDir.loadDefault();
		addField(atsvFilesDir);

		final IntegerFieldEditor atsvSampleCount = new IntegerFieldEditor(Activator.ATSV_SAMPLE_COUNT,
				"ATSV &Sample Count", getFieldEditorParent());
		atsvSampleCount.setTextLimit(5);
		atsvSampleCount.loadDefault();
		atsvSampleCount.fillIntoGrid(getFieldEditorParent(), 2);
		addField(atsvSampleCount);
	}

}
