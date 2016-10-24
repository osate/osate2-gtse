package org.osate.atsv.integration.EngineConfigModel;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.namespace.QName;

/**
 * This class is necessary to map to the naming scheme used in the *.ecf file: 
 *  <inputTokens>
 *      <var0 name="..." token=""/>
 *      <var1 name="..." token=""/>
 *      ...
 *  </inputTokens>
 *  As the tokens are named "varX" where X increases each time, we need a custom adapter.
 *  This class has been adapted from http://stackoverflow.com/a/23995734 
 */
public class VariableModelAdapter extends XmlAdapter<JAXBElement<VariableModel>, VariableModel> {

	private int counter = 0;

	@Override
	public VariableModel unmarshal(JAXBElement<VariableModel> vm) throws Exception {
		return vm.getValue();
	}

	@Override
	public JAXBElement<VariableModel> marshal(VariableModel vm) throws Exception {
		return new JAXBElement<VariableModel>(new QName("varName" + counter++), VariableModel.class, vm);
	}
}