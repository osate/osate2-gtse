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

import java.io.File;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.osate.atsv.integration.ChoicePointModel.ATSVVariableType;

/**
 * This class holds a map of the variables used by ATSV (so, just input and output values)
 * It's also annotated to support serialization since the files get modified by ATSV and GTSE
 * and then read in by the other one.
 * 
 * @author sam
 *
 */
@XmlRootElement(name = "atsv-io")
public class ATSVVarCollection implements Serializable {

	/**
	 * Default serialization id 
	 */
	private static final long serialVersionUID = 1L;

	@XmlElement(name = "vars")
	@XmlJavaTypeAdapter(ATSVVarCollectionAdapter.class)
	private Map<String, ATSVVar> theMap = new LinkedHashMap<>();

	public void addVar(String name, ATSVVariableType type, String val) {
		theMap.put(name, new ATSVVar(name, type, val));
	}

	@XmlTransient
	public Map<String, ATSVVar> getVars() {
		return theMap;
	}

	public void setVars(Map<String, ATSVVar> newVars) {
		theMap = newVars;
	}

	/**
	 * Helper method to trigger deserialization from the specified file
	 * 
	 * @param f The file to deserialize
	 * @throws JAXBException
	 */
	public void loadFromFile(File f) throws JAXBException {
		this.setVars(
				((ATSVVarCollection) JAXBContext.newInstance(ATSVVarCollection.class).createUnmarshaller().unmarshal(f))
						.getVars());
	}

	/**
	 * Helper method to trigger deserialization from the specified file given a path. Wraps
	 * {@link #loadFromFile(File)}
	 * 
	 * @param path The file to deserialize
	 * @throws JAXBException
	 */
	public void loadFromFile(String path) throws JAXBException {
		loadFromFile(new File(path));
	}

	/**
	 * Helper method to trigger serialization of the current state to the specified file
	 * 
	 * @param f The file to deserialize
	 * @throws JAXBException
	 */
	public void writeToFile(File f) throws JAXBException {
		Marshaller marshaller = JAXBContext.newInstance(ATSVVarCollection.class).createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(this, f);
	}

	/**
	 * Helper method to trigger serialization of the current state to the specified file given a 
	 * path. Wraps {@link WriteToFile(File)}
	 * 
	 * @param f The file to deserialize
	 * @throws JAXBException
	 */
	public void writeToFile(String path) throws JAXBException {
		writeToFile(new File(path));
	}

}