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
package org.osate.atsv.integration.network;

import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.instance.InstanceFactory;
import org.osate.aadl2.instance.InstanceObject;
import org.osate.aadl2.instance.InstanceReferenceValue;
import org.osate.atsv.integration.EngineConfigModel.VariableModel.ATSVVariableType;
import org.osate.atsv.integration.exception.UnhandledVariableTypeException;

public class ReferencePropertyValue extends PropertyValue {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;
	private InstanceObject referencedElement;

	public ReferencePropertyValue(String componentPath, String propertyName, String value, ATSVVariableType type) {
		super(componentPath, propertyName, value, type);
	}

	@Override
	public PropertyExpression getValue() throws UnhandledVariableTypeException {
		if (type == ATSVVariableType.STRING) {
			InstanceReferenceValue irf = InstanceFactory.eINSTANCE.createInstanceReferenceValue();
			irf.setReferencedInstanceObject(referencedElement);
			return irf;
		}
		throw new UnhandledVariableTypeException("Can't handle " + type + " as a reference!");
	}

	public void setReferencedElement(InstanceObject referencedElement) {
		this.referencedElement = referencedElement;
	}

}
