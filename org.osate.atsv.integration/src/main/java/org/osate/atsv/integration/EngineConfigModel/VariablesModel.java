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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

public class VariablesModel {

	@XmlJavaTypeAdapter(VariableModelAdapter.class)
	@XmlAnyElement
	private List<VariableModel> variables = new ArrayList<>();

	/**
	 * Variable Name -> Object Model
	 */
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

	public float convertToDiscreteFloat(String varName, String varVal) {
		// Assumes this is only called *after* all variables have been added
		if (variableMap == null) {
			variableMap = buildVariableMap();
		}

		ValuesModel vals = variableMap.get(varName).getValues();
		vals.cacheAndConvertToFloat();
		return vals.getIdFromCache(varVal);
	}

	private Map<String, VariableModel> buildVariableMap() {
		Map<String, VariableModel> ret = new HashMap<String, VariableModel>();
		for(VariableModel v : variables) {
			ret.put(v.getTitle(), v);
		}
		return ret;
	}

	public Map<String, String> getVarCaches() {
		Map<String, String> ret = new HashMap<>();
		String header;
		for (VariableModel vm : variableMap.values()) {
			if (vm.getValues().isCached()) {
				for (Entry<String, Float> e : vm.getValues().getCache().entrySet()) {
					header = "ConfigCacheHack-"+vm.getTitle();
					ret.put(header + e.getKey(), Float.toString(e.getValue()));
				}
			}
		}
		return ret;
	}
}
