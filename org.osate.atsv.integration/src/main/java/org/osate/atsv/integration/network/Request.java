package org.osate.atsv.integration.network;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
}
