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