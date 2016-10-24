package org.osate.atsv.integration.EngineConfigModel;

public final class UniformDistributionModel extends DistributionModel {

	protected UniformDistributionModel(float min, float max) {
		super(min, max);
		this.distType = distributionType.UNIFORM;
	}
}
