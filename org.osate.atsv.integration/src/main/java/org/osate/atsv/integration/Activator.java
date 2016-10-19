package org.osate.atsv.integration;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.osate.atsv.integration"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	public static String ATSV_INTEGRATION_ENABLED = "ATSV_INTEGRATION_ENABLED";
	public static String ATSV_INTEGRATION_PORT = "ATSV_INTEGRATION_PORT";
	
	/**
	 * The constructor
	 */
	public Activator() {
		super();
		this.getPreferenceStore().setDefault(ATSV_INTEGRATION_ENABLED, true);
		this.getPreferenceStore().setDefault(ATSV_INTEGRATION_PORT, 4444);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}
}
