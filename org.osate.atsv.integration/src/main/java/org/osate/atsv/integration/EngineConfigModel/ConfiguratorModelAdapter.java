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

import org.osate.atsv.integration.exception.UnsupportedFeatureException;

/**
 * This class is necessary to map to the naming scheme used in the *.ecf file:
 * <Configurator>
 *     <Equal>
 *         <Variable>e</Variable>
 *         <Variable>f</Variable>
 *     </Equal>
 *     <Unique>
 *         <Variable>c</Variable>
 *         <Variable>d</Variable>
 *     </Unique>
 *     <Requires>
 *         <Variable>a</Variable>
 *         <VariableValue>2</VariableValue>
 *         <DependentVariable>b</DependentVariable>
 *         <DependentVariableValue>4</DependentVariableValue>
 *     </Requires>
 *     <Forbids>
 *         <Variable>a</Variable>
 *         <VariableValue>3</VariableValue>
 *         <DependentVariable>b</DependentVariable>
 *         <DependentVariableValue>4</DependentVariableValue>
 *     </Forbids>
 *     <Membership>
 *         <Variable>a</Variable>
 *         <VariableValue>4</VariableValue>
 *         <DependentVariable v0="1" v1="2" v2="3">b</DependentVariable>
 *     </Membership>
 *     <Exclusion>
 *         <Variable>a</Variable>
 *         <VariableValue>5</VariableValue>
 *         <DependentVariable v0="1" v1="2" v2="3">b</DependentVariable>
 *     </Exclusion>
 * </Configurator>
 *  This class has been adapted from http://stackoverflow.com/a/23995734
 */
public class ConfiguratorModelAdapter extends XmlAdapter<JAXBElement<? extends ConfiguratorModel>, ConfiguratorModel> {

	@Override
	public ConfiguratorModel unmarshal(JAXBElement<? extends ConfiguratorModel> cm) throws Exception {
		return cm.getValue();
	}

	@Override
	public JAXBElement<? extends ConfiguratorModel> marshal(ConfiguratorModel cm) throws Exception {
		String type;
		if (cm instanceof SimpleConfiguratorModel) {
			SimpleConfiguratorModel scm = (SimpleConfiguratorModel) cm;
			type = scm.isEquality() ? "Equal" : "Unique";
			return new JAXBElement<SimpleConfiguratorModel>(new QName(type), SimpleConfiguratorModel.class, scm);
		} else if (cm instanceof ImpliesConfiguratorModel) {
			ImpliesConfiguratorModel icm = (ImpliesConfiguratorModel) cm;
			type = icm.isRequires() ? "Requires" : "Forbids";
			return new JAXBElement<ImpliesConfiguratorModel>(new QName(type), ImpliesConfiguratorModel.class, icm);
		} else if (cm instanceof SetRestrictionConfiguratorModel) {
			SetRestrictionConfiguratorModel srcm = (SetRestrictionConfiguratorModel) cm;
			type = srcm.isMembership() ? "Membership" : "Exclusion";
			return new JAXBElement<SetRestrictionConfiguratorModel>(new QName(type),
					SetRestrictionConfiguratorModel.class, srcm);
		} else {
			throw new UnsupportedFeatureException("Got a configurator model of unknown type");
		}
	}
}