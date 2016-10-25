package org.osate.atsv.integration.EngineConfigModel;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * A triangular distribution for ATSV variables.
 */
public final class TriangularDistributionModel extends DistributionModel {

	/**
	 * The mode of the data, only used in triangularly-distributed data 
	 */
	@XmlAttribute
	private float mode;

	/**
	 * Create a new triangular distribution with the specified parameters
	 * @param min Minimum value
	 * @param max Maximum value
	 * @param mean The mode of the distribution
	 */
	public TriangularDistributionModel(float min, float max, float mode) {
		super(min, max);
		this.mode = mode;
		this.distType = distributionType.triangular;
	}	
}
