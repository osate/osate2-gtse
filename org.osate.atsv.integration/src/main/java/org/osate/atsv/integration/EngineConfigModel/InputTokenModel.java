package org.osate.atsv.integration.EngineConfigModel;

import javax.xml.bind.annotation.XmlAttribute;


public class InputTokenModel {
	
	/**
	 * The name of this input token. Should map to the title of the associated variable(?)
	 */
	@XmlAttribute
	private String name;
	
	/**
	 * Unused(?)
	 */
	@XmlAttribute
	private String token = "";
	
	public InputTokenModel(String name){
		this.name = name;
	}
}
