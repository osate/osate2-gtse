package org.osate.gtse.config.config

import java.util.HashSet
import java.util.List
import org.osate.gtse.config.config.Configuration

class ConfigurationExt {
	
	static def List<Configuration> getLinearization(Configuration config) {
		val acc = newLinkedHashSet()
		linHelper(config, acc)
		acc.toList.reverseView
	}
	
	private static def void linHelper (Configuration config, HashSet<Configuration> acc) {
		val cfgs = config.combined.map[configuration]
		cfgs.forEach[linHelper(it, acc)]
		acc.add(config)
	}
	
}
