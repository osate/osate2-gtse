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
