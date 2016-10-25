package org.osate.atsv.integration.EngineConfigModel;

/**
 * A uniform distribution for ATSV variables.
 */
public final class UniformDistributionModel extends DistributionModel {

	/**
	 * Create a new uniform distribution with the specified parameters
	 * @param min Minimum value
	 * @param max Maximum value
	 */
	public UniformDistributionModel(float min, float max) {
		super(min, max);
		this.distType = distributionType.uniform;
	}
}
