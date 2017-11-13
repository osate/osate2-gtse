package org.osate.atsv.integration.EngineConfigModel;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This class models the set restriction (membership and exclusion) configurators.
 *
 * These restrictions take the following forms:
 *
 *   Set Membership -- Choosing one value means another choicepoint's choices are restricted to the specified set.
 *     X = 7 → Y ∈ {1,3,9}
 *     X = 5 → Y ∈ {2,4,7}
 *
 *   Set Exclusion -- Choosing one value means another choicepoint's choices cannot be any of the specified set.
 *     X = 7 → Y ∉ {1,3,9}
 *     X = 5 → Y ∉ {2,4,7}
 *
 * The exclusions serialize to this xml:
 *
 *     <Membership>
 *       <Variable>a</Variable>
 *       <VariableValue>4</VariableValue>
 *       <DependentVariable v0="1" v1="2" v2="3">b</DependentVariable>
 *     </Membership>
 *     <Exclusion>
 *       <Variable>a</Variable>
 *       <VariableValue>5</VariableValue>
 *       <DependentVariable v0="1" v1="2" v2="3">b</DependentVariable>
 *     </Exclusion>
 *
 * @author Sam
 */
public class SetRestrictionConfiguratorModel extends ConfiguratorModel {

	@XmlElement(name = "VariableValue")
	private String varVal1;

	/**
	 * We use a custom class here since the dependent variable includes the set involved
	 * in the restriction.
	 */
	@XmlElement(name = "DependentVariable")
	private SetRestrictionDependentVariableModel var2;

	private boolean isMembership;

	/**
	 * @param varName1 Name of the variable on the left hand side of the restriction
	 * @param varVal1 The value on the left hand side -- ie the value that triggers the configurator
	 * @param varName2 Name of the variable on the right hand side of the restriction
	 * @param varVals2 The set of values that the second (RHS) variable is required / forbidden to take
	 * @param isMembership True for a membership configurator, false for exclusion
	 */
	public SetRestrictionConfiguratorModel(String varName1, String varVal1, String varName2, Collection<String> varVals2,
			boolean isMembership) {
		super(varName1, varName2);
		this.varVal1 = varVal1;
		var2 = new SetRestrictionDependentVariableModel(varName2, varVals2);
		this.isMembership = isMembership;
	}

	public boolean isMembership() {
		return isMembership;
	}

	public boolean isExclusion() {
		return !isMembership;
	}

	public SetRestrictionDependentVariableModel getDependentVariable() {
		return this.var2;
	}

	@Override
	@XmlTransient
	/**
	 * This is unused and removed from serialization because the name of the second variable is stored in the
	 * SetRestrictionDependentVariableModel
	 */
	public String getVarName2() {
		// TODO Auto-generated method stub
		return null;
	}

}
