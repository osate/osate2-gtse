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
package org.osate.atsv.integration.EngineConfigModel;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.osate.atsv.integration.ConfiguratorVerifier;
import org.osate.atsv.integration.exception.ConfiguratorRepresentationException;
import org.osate.atsv.integration.exception.UnsatisfiableConstraint;

/**
 * Holds the list of configurators (basically constraints) in a particular choicepoint set
 *
 */
public class ConfiguratorsModel {

	@XmlJavaTypeAdapter(ConfiguratorModelAdapter.class)
	@XmlAnyElement
	private List<ConfiguratorModel> configurators = new ArrayList<>();

	/**
	 * Add a configurator to the internal list of variables.
	 * 
	 * This is not designed to be called by clients, @see {@link org.osate.atsv.integration.EngineConfigGenerator#addEqualityConstraint(String, String)}
	 * and @see {@link org.osate.atsv.integration.EngineConfigGenerator#addUniquenessConstraint(String, String)}
	 * @param cm The configurator model to add
	 */
	public void addConfigurator(ConfiguratorModel cm) {
		configurators.add(cm);
	}

	/**
	 * Empty the list of configurators
	 */
	public void clear() {
		configurators.clear();
	}

	public boolean isEmpty() {
		return configurators.isEmpty();
	}

	public void validateConfigurator() throws ConfiguratorRepresentationException, UnsatisfiableConstraint {
		if (!configurators.isEmpty()) {
			ConfiguratorVerifier.validate(configurators);
		}
	}

}
