package org.osate.atsv.integration.network;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Request implements Serializable {

	/**
	 * Default serial version 
	 */
	private static final long serialVersionUID = 1L;

	private String pluginId = null;

	private String packageName = null;

	private String componentImplementationName = null;

	private String operationModeName = null;

	private Set<ChoicePointSpecification> choices = new HashSet<>();

	public String getPluginId() {
		return pluginId;
	}

	public void setPluginId(String pluginId) {
		this.pluginId = pluginId;
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
