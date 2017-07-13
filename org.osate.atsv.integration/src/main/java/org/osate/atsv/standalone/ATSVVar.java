package org.osate.atsv.standalone;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

import org.osate.atsv.integration.ChoicePointModel.ATSVVariableType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ATSVVar {

	@XmlAttribute
	private String name;

	@XmlAttribute
	private ATSVVariableType type;

	@XmlValue
	private String val;

	public ATSVVar(String name, ATSVVariableType type, String val) {
		this.name = name;
		this.type = type;
		this.val = val;
	}

	public ATSVVar() {
		// JAXB requires a default constructor, I guess?
	}

	public String getName() {
		return name;
	}

	public ATSVVariableType getType() {
		return type;
	}

	public String getVal() {
		return val;
	}
}
