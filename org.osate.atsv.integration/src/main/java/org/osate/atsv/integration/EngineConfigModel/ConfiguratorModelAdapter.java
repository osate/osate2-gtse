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
 * <configurator> 
 * 	<Unique>
 * 		<var>VarName1</var>
 * 		<var>VarName2</var>
 * 	</Unique>
 * 	<Equal>
 * 		<var>VarName4</var>
 * 		<var>VarName1</var>
 * 	</Equal>
 * </configurator>
 *  As the tokens are both named "var," each time, we need a custom adapter.
 *  This class has been adapted from http://stackoverflow.com/a/23995734 
 */
public class ConfiguratorModelAdapter extends XmlAdapter<JAXBElement<ConfiguratorModel>, ConfiguratorModel> {

	@Override
	public ConfiguratorModel unmarshal(JAXBElement<ConfiguratorModel> cm) throws Exception {
		return cm.getValue();
	}

	@Override
	public JAXBElement<ConfiguratorModel> marshal(ConfiguratorModel cm) throws Exception {
		String type = cm.isEquality() ? "Equal" : "Unique";
		return new JAXBElement<ConfiguratorModel>(new QName(type), ConfiguratorModel.class, cm);
	}
}