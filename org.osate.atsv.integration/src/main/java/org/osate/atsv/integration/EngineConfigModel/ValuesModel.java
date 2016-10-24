package org.osate.atsv.integration.EngineConfigModel;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;

public class ValuesModel {
	
	@XmlJavaTypeAdapter(VariableModelAdapter.class)
	@XmlAnyAttribute
	private Map<QName, String> values = new HashMap<>();
	
	private int counter = 0;
	
	public ValuesModel(String... values){
		for(String s : values){
			this.add(s);
		}
	}

	public void add(String s){
		values.put(new QName("val" + counter++), s);
	}
}
