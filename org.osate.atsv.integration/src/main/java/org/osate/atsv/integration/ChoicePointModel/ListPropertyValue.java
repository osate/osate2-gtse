package org.osate.atsv.integration.ChoicePointModel;

import java.util.Collections;
import java.util.List;

public class ListPropertyValue extends PropertyValue {

	/**
	 * Default serial version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This holds the actual items in our lists. They may also be lists.
	 */
	private List<PropertyValue> parsedList = null;

	/**
	 * Since all lists use the variable type "list" we have to track what the type of propertyvalues in this list are
	 */
	private ATSVVariableType innerType;

	/**
	 * Creates a new list of property values.
	 *
	 * @param type The type of the individual, inner property values -- the type of the property value returned by this
	 * constructor will always be a list.
	 * @param value An encoded version of the list.
	 */
	public ListPropertyValue(String componentPath, String propertyName, String value, ATSVVariableType type) {
		super(componentPath, propertyName, value, ATSVVariableType.LIST);
		innerType = type;
		value = value.replaceAll("\\[|\\]", "");
		this.value = value;

		// TODO: Start here. We need to find a way to encode the inner type of the list, since its type is currently just "list"
		// Of course, this type encoding may itself need to be multiple levels deep, since we might someday have lists of lists.
		if (innerType == ATSVVariableType.INTEGER || type == ATSVVariableType.FLOAT
				|| type == ATSVVariableType.STRING) {
			parsedList = Collections.singletonList(new LiteralPropertyValue(componentPath, propertyName, value, type));
		} else if (innerType == ATSVVariableType.REFERENCE) {
			parsedList = Collections
					.singletonList(new ReferencePropertyValue(componentPath, propertyName, value, type));
		}
	}

	public List<PropertyValue> getItems() {
		return parsedList;
	}
}
