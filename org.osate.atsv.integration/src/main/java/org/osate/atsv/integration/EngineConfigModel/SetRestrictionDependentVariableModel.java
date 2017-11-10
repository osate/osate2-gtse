package org.osate.atsv.integration.EngineConfigModel;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;

public class SetRestrictionDependentVariableModel {

	@XmlJavaTypeAdapter(ConfiguratorModelAdapter.class)
	@XmlAnyAttribute
	private Map<QName, String> values = new HashMap<>();

	@XmlElement(name = "DependentVariable")
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
