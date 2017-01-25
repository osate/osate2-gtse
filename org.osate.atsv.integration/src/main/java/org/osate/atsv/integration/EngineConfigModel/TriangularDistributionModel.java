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
