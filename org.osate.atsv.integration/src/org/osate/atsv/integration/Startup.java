package org.osate.atsv.integration;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IStartup;
import org.osate.workspace.WorkspacePlugin;

public class Startup implements IStartup {

	private Thread serverThread;

	@Override
	public void earlyStartup() {

		IPreferenceStore store = WorkspacePlugin.getDefault().getPreferenceStore();

		if (!store.getBoolean(Activator.ATSV_INTEGRATION_ENABLED)) {
			return;
		}

		serverThread = new Thread(new NetworkHandler(store.getInt(Activator.ATSV_INTEGRATION_PORT)));
		serverThread.setDaemon(true);
		serverThread.start();

	}

}
