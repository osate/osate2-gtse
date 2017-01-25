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

	public static final String ATSV_INTEGRATION_ENABLED = "ATSV_INTEGRATION_ENABLED";
	public static final String ATSV_INTEGRATION_PORT = "ATSV_INTEGRATION_PORT";
	public static final String ATSV_FILES_DIRECTORY = "ATSV_FILES_DIRECTORY";
	public static final String ATSV_SAMPLE_COUNT = "ATSV_SAMPLE_COUNT";
	
	/**
	 * The constructor
	 */
	public Activator() {
		super();
		this.getPreferenceStore().setDefault(ATSV_INTEGRATION_ENABLED, true);
		this.getPreferenceStore().setDefault(ATSV_INTEGRATION_PORT, 4444);
		this.getPreferenceStore().setDefault(ATSV_FILES_DIRECTORY, System.getProperty("user.dir"));
		this.getPreferenceStore().setDefault(ATSV_SAMPLE_COUNT, 25);
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
