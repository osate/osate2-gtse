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
package org.osate.atsv.standalone;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.osate.atsv.standalone.ATSVVarCollectionAdapter.AdaptedVars;

/**
 * This class converts the collection of ATSV vars between a list (when serializing) 
 * and a map (when deserializing).   
 * 
 * It's adapted from https://stackoverflow.com/a/17025137
 * 
 * @author sam
 *
 */
public class ATSVVarCollectionAdapter extends XmlAdapter<AdaptedVars, Map<String, ATSVVar>> {

	/**
	 * Helper class, holds the list of vars that actually gets written / read
	 */
	public static class AdaptedVars {
		public List<ATSVVar> var = new ArrayList<ATSVVar>();
	}

	@Override
	public Map<String, ATSVVar> unmarshal(AdaptedVars adaptedVars) throws Exception {
		Map<String, ATSVVar> map = new LinkedHashMap<String, ATSVVar>(adaptedVars.var.size());
		if (null != adaptedVars) {
			for (ATSVVar var : adaptedVars.var) {
				map.put(var.getName(), var);
			}
		}
		return map;
	}

	@Override
	public AdaptedVars marshal(Map<String, ATSVVar> map) throws Exception {
		AdaptedVars varList = new AdaptedVars();
		if (null != map) {
			for (ATSVVar oldVar : map.values()) {
				varList.var.add(oldVar);
			}
		}
		return varList;
	}
}