package org.osate.atsv.integration.EngineConfigModel;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;

/**
 * This class enables the modeling of a list of explicit variable values. Note that types are tracked 
 * elsewhere, so all values must be strings. 
 * 
 * Note that ATSV uses floats for value representations (based on rounding errors, eg one of their
 * files has the value 2.299999952316284, and:
 * 
 * java> double d = x * Math.pow(10,8)
 * double d = 2.2999999523162842E8
 */
public class ValuesModel {
	
	@XmlJavaTypeAdapter(VariableModelAdapter.class)
	@XmlAnyAttribute
	private Map<QName, String> values = new HashMap<>();
	
	private int counter = 0;
	
	/**
	 * Initialize the values model with the supplied list of values
	 * @param values The values that the variable can have
	 */
	public ValuesModel(String... values){
		for(String s : values){
			this.add(s);
		}
	}

	/**
	 * Add another possible value to the list
	 * @param s The new value to add
	 */
	public void add(String s){
		values.put(new QName("val" + counter++), s);
	}
}
