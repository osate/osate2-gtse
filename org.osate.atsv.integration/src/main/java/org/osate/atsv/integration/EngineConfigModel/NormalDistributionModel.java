package org.osate.atsv.integration.EngineConfigModel;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * A normal distribution for ATSV variables.
 */
public final class NormalDistributionModel extends DistributionModel {

	/**
	 * The mean value of the data, only used in triangularly-distributed data 
	 */
	@XmlAttribute
	private float mean;
	
	/**
	 * Create a new normal distribution with the specified parameters
	 * @param min Minimum value
	 * @param max Maximum value
	 * @param mean The mean of the distribution
	 */
	public NormalDistributionModel(float min, float max, float mean) {
		super(min, max);
		this.mean = mean;
		this.distType = distributionType.normal;
	}
}
