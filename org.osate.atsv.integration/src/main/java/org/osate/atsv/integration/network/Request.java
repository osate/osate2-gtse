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
package org.osate.atsv.integration.network;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.osate.atsv.integration.ChoicePointModel.ChoicePointSpecification;
import org.osate.atsv.integration.annotation.StringConfiguratorHack;

public class Request implements Serializable {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The ids of the GTSE-compatible plugins to run
	 */
	private Set<String> pluginIds = new HashSet<>();

	/**
	 * The name of the package the component to instantiate is in
	 */
	private String packageName = null;

	/**
	 * The name of the component to instantiate
	 */
	private String componentImplementationName = null;

	/**
	 * The name of the operational mode. Optional.
	 */
	private String operationModeName = null;

	/**
	 * The choicepoint information that can be used to instantiate the model.
	 */
	private Set<ChoicePointSpecification> choices = new HashSet<>();

	/**
	 * This maps variable names to their user-specified limit/threshold
	 */
	private Map<String, Limit> limits = new HashMap<>();

	/**
	 * Maps choicepoint name to a map of subbed values to original (string) values.
	 *
	 * Ex:
	 *
	 * my.component -> <1.0 -> "component.option1">
	 * my.component -> <2.0 -> "component.option2">
	 */
	@StringConfiguratorHack
	private Map<String, Map<Float, String>> configuratorCache = new HashMap<>();

	public Collection<String> getPluginIds() {
		return pluginIds;
	}

	public void setPluginIds(String[] pluginIds) {
		this.pluginIds.addAll(Arrays.asList(pluginIds));
	}

	public void addPluginId(String pluginId) {
		this.pluginIds.add(pluginId);
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getComponentImplementationName() {
		return componentImplementationName;
	}

	public void setComponentImplementationName(String componentImplementationName) {
		this.componentImplementationName = componentImplementationName;
	}

	public String getOperationModeName() {
		return operationModeName;
	}

	public void setOperationModeName(String operationModeName) {
		this.operationModeName = operationModeName;
	}

	public boolean hasMode() {
		return operationModeName != null;
	}

	public void addChoicePoint(ChoicePointSpecification cps) {
		choices.add(cps);
	}

	public Set<ChoicePointSpecification> getChoicepoints() {
		return choices;
	}

	public void addLimit(String name, String op, String limit) {
		limits.put(name, new Limit(op, limit));
	}

	public Map<String, Limit> getLimits() {
		return limits;
	}

	@StringConfiguratorHack
	public Map<String, Map<Float, String>> getConfiguratorCache() {
		return configuratorCache;
	}

	@StringConfiguratorHack
	public void addConfiguratorCacheEntry(String choicepointName, Float subbedVal, String origVal) {
		if (!configuratorCache.containsKey(choicepointName)) {
			configuratorCache.put(choicepointName, new HashMap<>());
		}
		configuratorCache.get(choicepointName).put(subbedVal, origVal);
	}
}
