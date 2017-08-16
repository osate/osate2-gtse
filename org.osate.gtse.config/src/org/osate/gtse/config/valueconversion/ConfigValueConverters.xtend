package org.osate.gtse.config.valueconversion

import org.eclipse.xtext.common.services.DefaultTerminalConverters
import org.eclipse.xtext.conversion.IValueConverter
import org.eclipse.xtext.conversion.ValueConverter
import org.eclipse.xtext.conversion.ValueConverterException
import org.eclipse.xtext.conversion.impl.AbstractNullSafeConverter
import org.eclipse.xtext.nodemodel.INode
import org.osate.aadl2.ComponentCategory

class ConfigValueConverters extends DefaultTerminalConverters {
	
	@ValueConverter(rule = "ComponentCategory")
	def IValueConverter<ComponentCategory> ComponentCategory() {
		new AbstractNullSafeConverter<ComponentCategory>() {

			override public ComponentCategory internalToValue(String string, INode node) {
				ComponentCategory::get(string.toLowerCase());
			}

			override public String internalToString(ComponentCategory value) {
				return value.getName();
			}

		};
	}

	@ValueConverter(rule = "AadlClassifierReference")
	def IValueConverter<String> AadlClassifierReference() {
		new AbstractNullSafeConverter<String> () {
			
			override protected internalToString(String value) {
				value.split('::').map[it.split('\\.').map[id | ID().toString(id)].join('.')].join('::')
			}
			
			override protected internalToValue(String string, INode node) throws ValueConverterException {
				string.replace('^', '')
			}
			
		}
	}

	@ValueConverter(rule = "QualifiedName")
	def IValueConverter<String> QualifiedName() {
		new AbstractNullSafeConverter<String> () {
			
			override protected internalToString(String value) {
				value.split('::').map[it.split('\\.').map[id | ID().toString(id)].join('.')].join('::')
			}
			
			override protected internalToValue(String string, INode node) throws ValueConverterException {
				string.replace('^', '')
			}
			
		}
	}

}