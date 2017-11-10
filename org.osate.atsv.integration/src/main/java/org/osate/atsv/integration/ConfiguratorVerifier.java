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
package org.osate.atsv.integration;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.osate.atsv.integration.EngineConfigModel.ConfiguratorModel;
import org.osate.atsv.integration.EngineConfigModel.SimpleConfiguratorModel;
import org.osate.atsv.integration.exception.UnsatisfiableConstraint;

public class ConfiguratorVerifier {

	/**
	 * Validates (ie, ensures the satisfiability of) the list of configurators
	 *
	 * Implements Algorithm 4.6.2 from Kroening and Strichman's "Decision Procedures: An
	 * Algorithmic Point of View" (Second Edition)
	 * See https://doi.org/10.1007/978-3-662-50497-0
	 *
	 * @param configurators The constraints to check the consistency of
	 * @throws UnsatisfiableConstraint Signifies the given constraints are unsatisfiable
	 */
	public static void validate(List<SimpleConfiguratorModel> configurators) throws UnsatisfiableConstraint {
		// TODO: Reimplement using union find

		Map<String, Set<String>> eqClasses = new HashMap<String, Set<String>>();
		Set<SimpleConfiguratorModel> eqConfs = configurators.stream().filter(SimpleConfiguratorModel::isEquality)
				.collect(Collectors.toSet());
		Set<SimpleConfiguratorModel> neqConfs = configurators.stream().filter(SimpleConfiguratorModel::isUnique)
				.collect(Collectors.toSet());
		Set<String> union;
		String name1, name2;
		for (ConfiguratorModel cm : eqConfs) {
			name1 = cm.getVarName1();
			name2 = cm.getVarName2();
			if (!eqClasses.containsKey(name1)) {
				eqClasses.put(name1, new HashSet<String>(Collections.singleton(name1)));
			}
			if (!eqClasses.containsKey(name2)) {
				eqClasses.put(name2, new HashSet<String>(Collections.singleton(name2)));
			}
			union = eqClasses.get(name1);
			union.addAll(eqClasses.get(name2));
			for (String name : union) {
				eqClasses.put(name, union);
			}
		}
		for (ConfiguratorModel cm : neqConfs) {
			name1 = cm.getVarName1();
			name2 = cm.getVarName2();
			if (eqClasses.containsKey(name1)) {
				if (eqClasses.get(name1).contains(name2)) {
					throw new UnsatisfiableConstraint("The specified configurators are unsatisfiable");
				}
			}
		}
	}
}