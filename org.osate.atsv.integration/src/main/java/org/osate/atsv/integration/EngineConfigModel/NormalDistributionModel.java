package org.osate.atsv.integration.EngineConfigModel;

import javax.xml.bind.annotation.XmlAttribute;

public final class NormalDistributionModel extends DistributionModel {

	/**
	 * The mean value of the data, only used in triangularly-distributed data 
	 */
	@XmlAttribute
	private float mean;
	
	protected NormalDistributionModel(float min, float max, float mean) {
		super(min, max);
		this.mean = mean;
		this.distType = distributionType.NORMAL;
	}
}
