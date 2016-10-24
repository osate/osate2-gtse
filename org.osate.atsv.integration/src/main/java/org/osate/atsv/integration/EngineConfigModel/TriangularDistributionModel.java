package org.osate.atsv.integration.EngineConfigModel;

import javax.xml.bind.annotation.XmlAttribute;

public final class TriangularDistributionModel extends DistributionModel {

	/**
	 * The mode of the data, only used in triangularly-distributed data 
	 */
	@XmlAttribute
	private float mode;

	public TriangularDistributionModel(float min, float max, float mode) {
		super(min, max);
		this.mode = mode;
		this.distType = distributionType.TRIANGULAR;
	}	
}
