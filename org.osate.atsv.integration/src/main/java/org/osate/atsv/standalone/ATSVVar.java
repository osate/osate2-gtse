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
package org.osate.atsv.standalone;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

import org.osate.atsv.integration.ChoicePointModel.ATSVVariableType;

/**
 * This class describes an actual ATSV variable: it's name, type, and value
 * It's also annotated to aid in serialization / deserialization
 * 
 * @author sam
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ATSVVar implements Serializable {

	/**
	 * Default serialization id
	 */
	private static final long serialVersionUID = 1L;

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

	public void setName(String name) {
		this.name = name;
	}

	public void setType(ATSVVariableType type) {
		this.type = type;
	}

	public void setVal(String val) {
		this.val = val;
	}
}
