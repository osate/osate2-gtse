package org.osate.atsv.integration.EngineConfigModel;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class VariablesModel {

	@XmlJavaTypeAdapter(VariableModelAdapter.class)
	@XmlAnyElement
	private List<VariableModel> variables = new ArrayList<>();
	
	/**
	 * Add a variable to the internal list of variables.
	 * 
	 * This is not designed to be called by clients, @see org.osate.atsv.integration.EngineConfigGenerator#addVariable()
	 * @param vm The variable model to add
	 */
	public void addVariable(VariableModel vm){
		variables.add(vm);
	}

	/**
	 * Empty the list of variables
	 */
	public void clear() {
		variables.clear();
	}	
}
