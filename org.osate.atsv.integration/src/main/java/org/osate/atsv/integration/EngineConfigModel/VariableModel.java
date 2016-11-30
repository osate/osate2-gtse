package org.osate.atsv.integration.EngineConfigModel;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class VariableModel {

	public enum ATSVVariableType {
		FLOAT(0), DISCRETE_FLOAT(1), STRING(2), INTEGER(4);

		private int typeVal;

		ATSVVariableType(int typeVal) {
			this.typeVal = typeVal;
		}

		public int getTypeVal() {
			return typeVal;
		}
	}

	/**
	 * The name of the variable
	 */
	@XmlAttribute
	private String title;

	/**
	 * Whether or not this variable is captured by ATSV.
	 */
	@XmlAttribute
	private boolean capture;

	/**
	 * Whether or not the variable is sampled
	 */
	@XmlAttribute
	private boolean sampled;

	/**
	 * Whether this is an input (0) or output (1) value. Note that it has to render to a number, since the
	 * .ecf file uses 0 and 1 instead of false and true.
	 */
	@XmlAttribute
	private int ioValue;

	/**
	 * The type of this variable.
	 */
	@XmlAttribute
	private int type;

	/**
	 * The initial value of this variable, rendered as a string. Ignored if this is an output variable.
	 */
	@XmlAttribute
	private String value;

	/**
	 * The preference value for this variable. This is used in the preference-based samplers, so we just leave it zero.
	 */
	@XmlAttribute
	private final int preference = 0;

	/**
	 * The possible values this variable can take.
	 */
	@XmlElement
	private ValuesModel values;

	/**
	 * The distribution of this variable's values
	 */
	@XmlElement
	private DistributionModel distribution;

	/**
	 * Create a new variable model without a list of possible values or a distribution
	 * 
	 * @param title The name of this variable, this should be correspond to an input token if it's an input variable
	 * @param sampled Whether or not this variable is sampled
	 * @param isInput True if this is an input variable, false if it's output
	 * @param type The type of this variable
	 * @param value The default value of this variable. It should be of the class's type parameter, though the value
	 * will be ignored if its an output variable. 
	 */
	public VariableModel(String title, boolean sampled, boolean isInput, ATSVVariableType type,
			String value) throws NumberFormatException {
		this.title = title;
		/*
		 *  See Nov 22 2016 email from Gary Stump:
		 *  [capture] means that they are recorded for the dataset (probably for your case, 
		 *  just assume true or capture all)
		 */
		this.capture = true;
		this.sampled = sampled;
		this.ioValue = isInput ? 0 : 1;
		this.type = type.typeVal;
		if (isInput) {
			this.value = value;
			if (type == ATSVVariableType.INTEGER) {
				Integer.parseInt(value);
			} else if (type == ATSVVariableType.FLOAT || type == ATSVVariableType.DISCRETE_FLOAT) {
				Float.parseFloat(value);
			}
		}
	}

	/**
	 * Create a new variable model with a list of possible values
	 * 
	 * @param title The name of this variable, this should be correspond to an input token if it's an input variable
	 * @param sampled Whether or not this variable is sampled
	 * @param isInput True if this is an input variable, false if it's output
	 * @param type The type of this variable
	 * @param value The default value of this variable. It should be of the class's type parameter, though the value
	 * will be ignored if its an output variable. 
	 * @param valuesModel The values this variable can take
	 */
	public VariableModel(String title, boolean sampled, boolean isInput, ATSVVariableType type,
			String value, ValuesModel valuesModel) {
		this(title, sampled, isInput, type, value);
		this.values = valuesModel;
	}

	/**
	 * Create a new variable model with a distribution
	 * 
	 * @param title The name of this variable, this should be correspond to an input token if it's an input variable
	 * @param capture Whether or not this variable is captured
	 * @param sampled Whether or not this variable is sampled
	 * @param isInput True if this is an input variable, false if it's output
	 * @param type The type of this variable
	 * @param value The default value of this variable. It should be of the class's type parameter, though the value
	 * will be ignored if its an output variable.
	 * @param distributionModel The distribution of the data this variable tracks
	 */
	public VariableModel(String title, boolean sampled, boolean isInput, ATSVVariableType type,
			String value, DistributionModel distributionModel) {
		this(title, sampled, isInput, type, value);
		this.distribution = distributionModel;
	}

	/**
	 * Getter for the name of the variable
	 * @return The name of the variable
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Getter for if this is an input variable
	 * @return True if the variable is input, false if its output
	 */
	public boolean isInput() {
		return ioValue == 0;
	}
}
