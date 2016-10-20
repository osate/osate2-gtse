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
public class InputTokenAdapter extends XmlAdapter<JAXBElement<InputTokenModel>, InputTokenModel> {

	private int counter = 0;

	@Override
	public InputTokenModel unmarshal(JAXBElement<InputTokenModel> itm) throws Exception {
		return itm.getValue();
	}

	@Override
	public JAXBElement<InputTokenModel> marshal(InputTokenModel itm) throws Exception {
		return new JAXBElement<InputTokenModel>(new QName("var" + counter++), InputTokenModel.class, itm);
	}
}