package org.osate.atsv.integration.network;

import java.io.Serializable;

public class Request implements Serializable {

	/**
	 * Default serial version 
	 */
	private static final long serialVersionUID = 1L;

	private String pluginId = null;
	
	private String packageName = null;
	
	private String componentImplementationName = null;
	
	private String operationModeName = null;

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
	
	public boolean hasMode(){
		return operationModeName != null;
	}
}
