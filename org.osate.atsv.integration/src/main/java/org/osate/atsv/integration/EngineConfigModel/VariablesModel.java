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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.osate.atsv.integration.ChoicePointModel.ATSVVariableType;
import org.osate.atsv.integration.annotation.StringConfiguratorHack;

public class VariablesModel {

	@XmlJavaTypeAdapter(VariableModelAdapter.class)
	@XmlAnyElement
	private List<VariableModel> variables = new ArrayList<>();

	/**
	 * Variable Name -> Object Model
	 */
	@StringConfiguratorHack
	@XmlTransient
	private Map<String, VariableModel> variableMap;

	/**
	 * Add a variable to the internal list of variables.
	 *
	 * This is not designed to be called by clients, @see org.osate.atsv.integration.EngineConfigGenerator#addVariable()
	 * @param vm The variable model to add
	 */
	public void addVariable(VariableModel vm) {
		variables.add(vm);
	}

	/**
	 * Empty the list of variables
	 */
	public void clear() {
		variables.clear();
		variableMap = null;
	}

	/**
	 * This converts the variables in this model to floats, which is required by the
	 * {@link StringConfiguratorHack}.
	 *
	 * @param varName The name of the variable this model is used by
	 * @param varVal The selected value of the variable
	 * @return The float representation of the selected value
	 */
	@StringConfiguratorHack
	public float convertToDiscreteFloat(String varName, String varVal) {
		// Assumes this is only called *after* all variables have been added
		if (variableMap == null) {
			variableMap = buildVariableMap();
		}
		if (!variableMap.containsKey(varName)) {
			// TODO: Handle this error
			System.err.println("Value used in configurator but not in choice.");
		}
		ValuesModel vals = variableMap.get(varName).getValues();
		variableMap.get(varName).setType(ATSVVariableType.DISCRETE_FLOAT);
		vals.cacheAndConvertToFloat();
		variableMap.get(varName).setValue(Float.toString(vals.getIdFromCache(varVal)));
		return vals.getIdFromCache(varVal);
	}

	/**
	 * Initializes the variable mapping
	 * @return A map from variable names to the variable model objects
	 */
	@StringConfiguratorHack
	private Map<String, VariableModel> buildVariableMap() {
		Map<String, VariableModel> ret = new HashMap<String, VariableModel>();
		for(VariableModel v : variables) {
			ret.put(v.getTitle(), v);
		}
		return ret;
	}

	/**
	 * This serializes the variable map into a map of strings usable in the request.properties file
	 * @return The mapping, ready to be put into the request.properties file
	 */
	@StringConfiguratorHack
	public Map<String, String> getVarCacheStrs() {
		if (variableMap == null) {
			// Nothing cached, so we don't need to serialize anything
			return Collections.EMPTY_MAP;
		}
		Map<String, String> ret = new HashMap<>();
		String header;
		for (VariableModel vm : variableMap.values()) {
			if (vm.getValues() != null && vm.getValues().isCached()) {
				for (Entry<String, Float> e : vm.getValues().getCache().entrySet()) {
					header = "ConfigCacheHack-" + vm.getTitle() + "-";
					ret.put(header + Float.toString(e.getValue()), e.getKey());
				}
			}
		}
		return ret;
	}

}
