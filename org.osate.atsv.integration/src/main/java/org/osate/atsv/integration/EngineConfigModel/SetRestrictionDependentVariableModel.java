package org.osate.atsv.integration.EngineConfigModel;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;

/**
 * This encodes the "DependentVariable" element of the set restriction configurators.
 * We have to use a class here because there are two parts to the element:
 *
 * 1. A list of attributes (values that are in the set)
 * 2. The name of the variable
 *
 * @author Sam
 */
public class SetRestrictionDependentVariableModel {

	@XmlJavaTypeAdapter(ConfiguratorModelAdapter.class)
	@XmlAnyAttribute
	private Map<QName, String> values = new HashMap<>();

	@XmlValue
	private String varName;

	private int counter = 0;

	/**
	 * Initialize the values model with the supplied list of values
	 * @param values The values that the variable can have
	 */
	public SetRestrictionDependentVariableModel(String varName, Collection<String> values) {
		this.varName = varName;
		for (String s : values) {
			this.add(s);
		}
	}

	/**
	 * Add another possible value to the list
	 * @param s The new value to add
	 */
	public void add(String s) {
		values.put(new QName("v" + counter++), s);
	}

}
