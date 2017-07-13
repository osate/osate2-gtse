package org.osate.atsv.standalone;

import java.io.File;
import java.util.Collection;
import java.util.LinkedHashSet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.osate.atsv.integration.ChoicePointModel.ATSVVariableType;

@XmlRootElement(name = "vars")
@XmlAccessorType(XmlAccessType.FIELD)
public class ATSVVarCollection {

	@XmlElement(name = "var")
	private Collection<ATSVVar> theCollection = new LinkedHashSet<>();

	public void addVar(String name, ATSVVariableType type, String val) {
		theCollection.add(new ATSVVar(name, type, val));
	}

	public Collection<ATSVVar> getVars() {
		return theCollection;
	}

	public void setVars(Collection<ATSVVar> newVars) {
		theCollection = newVars;
	}

	public void loadFromFile(File f) throws JAXBException {
		this.setVars(
				((ATSVVarCollection) JAXBContext.newInstance(ATSVVarCollection.class).createUnmarshaller().unmarshal(f))
						.getVars());
	}

	public void writeToFile(File f) throws JAXBException {
		Marshaller marshaller = JAXBContext.newInstance(ATSVVarCollection.class).createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(this, f);
	}
}