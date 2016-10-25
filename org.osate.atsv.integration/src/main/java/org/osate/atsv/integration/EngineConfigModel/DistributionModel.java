package org.osate.atsv.integration.EngineConfigModel;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * The base class for distribution models used by variables in ATSV. Do not instantiate this class directly, rather,
 * use one of the subtypes. 
 * 
 * Note that ATSV uses floats for value representations (based on rounding errors, eg one of their
 * files has the value 2.299999952316284, and:
 * 
 * java> double d = x * Math.pow(10,8)
 * double d = 2.2999999523162842E8
 * 
 */
public class DistributionModel {

	/**
	 * 
	 * The possible distributions for variables in ATSV.
	 * 
	 * ATSV will crash if these aren't lowercase, and JAXB uses name() instead of toString(), so I'm breaking convention.
	 */
	public enum distributionType {
		uniform, triangular, normal;
	};

	/**
	 * The type of this distribution
	 */
	@XmlAttribute
	protected distributionType distType;

	/**
	 * The minimum value the variable can take 
	 */
	@XmlAttribute
	private float min;

	/**
	 * The maximum value the variable can take 
	 */
	@XmlAttribute
	private float max;

	/**
	 * This constructor shouldn't be called directly, use one of the subclasses instead
	 */
	protected DistributionModel(float min, float max) {
		this.min = min;
		this.max = max;
	}
}
