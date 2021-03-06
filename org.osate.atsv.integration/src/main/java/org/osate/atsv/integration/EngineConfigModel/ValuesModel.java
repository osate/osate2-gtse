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

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;

import org.osate.atsv.integration.annotation.StringConfiguratorHack;

/**
 * This class enables the modeling of a list of explicit variable values. Note that types are tracked
 * elsewhere, so all values must be strings.
 *
 * Note that ATSV uses floats for value representations (based on rounding errors, eg one of their
 * files has the value 2.299999952316284, and:
 *
 * java> double d = x * Math.pow(10,8)
 * double d = 2.2999999523162842E8
 *
 * )
 */
public class ValuesModel {

	/**
	 * Maps names to string representations of values. Ordering must be preserved for the configurator
	 * caching hack, see {@link StringConfiguratorHack}
	 */
	@XmlJavaTypeAdapter(VariableModelAdapter.class)
	@XmlAnyAttribute
	private Map<QName, String> values = new LinkedHashMap<>();

	@XmlTransient
	private int counter = 0;

	/**
	 * Maps values to their float representation
	 */
	@XmlTransient
	@StringConfiguratorHack
	private Map<String, Float> cache = null;

	/**
	 * Initialize the values model with the supplied list of values
	 * @param values The values that the variable can have
	 */
	public ValuesModel(String... values) {
		for (String s : values) {
			this.add(s);
		}
	}

	/**
	 * Add another possible value to the list
	 * @param s The new value to add
	 */
	public void add(String s) {
		values.put(new QName("val" + counter++), s);
	}

	/**
	 * Get the first value, if it exists. Used if we can't find a reasonable default
	 * @return
	 */
	public String getDefault() {
		if (values.isEmpty()) {
			return "";
		}
		return values.values().iterator().next();
	}

	public Collection<String> getValueSet() {
		return values.values();
	}

	/**
	 * Converts all the values in this model and caches them
	 */
	@StringConfiguratorHack
	public void cacheAndConvertToFloat() {
		if (cache != null) {
			return; // Don't recreate the cache if we already have one
		}
		float i = 0;
		cache = new HashMap<String, Float>();
		for (QName n : values.keySet()) {
			cache.put(values.get(n), i);
			values.put(n, Float.toString(i++));
		}
	}

	/**
	 * Gets the float version of a cached value
	 * @param val The value to get the float version of
	 * @return The float representation of the value
	 */
	@StringConfiguratorHack
	public float getIdFromCache(String val) {
		return cache.get(val);
	}

	/**
	 * Gets the entire cache
	 * @return
	 */
	@StringConfiguratorHack
	public Map<String, Float> getCache() {
		return cache;
	}

	/**
	 * True if the cache has already been created for this model, false otherwise
	 * @return
	 */
	@StringConfiguratorHack
	public boolean isCached() {
		if (cache != null) {
			return true;
		}
		return false;
	}
}
