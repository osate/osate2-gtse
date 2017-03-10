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
