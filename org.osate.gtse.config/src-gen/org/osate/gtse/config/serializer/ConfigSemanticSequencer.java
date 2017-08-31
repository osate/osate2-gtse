/* 
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
 */
package org.osate.gtse.config.serializer;

import com.google.inject.Inject;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Parameter;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;
import org.osate.aadl2.Aadl2Package;
import org.osate.aadl2.AadlBoolean;
import org.osate.aadl2.AadlInteger;
import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.AadlReal;
import org.osate.aadl2.AadlString;
import org.osate.aadl2.AbstractFeature;
import org.osate.aadl2.AbstractImplementation;
import org.osate.aadl2.AbstractPrototype;
import org.osate.aadl2.AbstractSubcomponent;
import org.osate.aadl2.AbstractType;
import org.osate.aadl2.AccessConnection;
import org.osate.aadl2.AccessSpecification;
import org.osate.aadl2.ArrayDimension;
import org.osate.aadl2.ArrayRange;
import org.osate.aadl2.ArraySize;
import org.osate.aadl2.BasicProperty;
import org.osate.aadl2.BasicPropertyAssociation;
import org.osate.aadl2.BooleanLiteral;
import org.osate.aadl2.BusAccess;
import org.osate.aadl2.BusImplementation;
import org.osate.aadl2.BusPrototype;
import org.osate.aadl2.BusSubcomponent;
import org.osate.aadl2.BusType;
import org.osate.aadl2.ClassifierType;
import org.osate.aadl2.ClassifierValue;
import org.osate.aadl2.ComponentImplementationReference;
import org.osate.aadl2.ComponentPrototypeActual;
import org.osate.aadl2.ComponentPrototypeBinding;
import org.osate.aadl2.ComponentTypeRename;
import org.osate.aadl2.ComputedValue;
import org.osate.aadl2.ConnectedElement;
import org.osate.aadl2.ContainedNamedElement;
import org.osate.aadl2.ContainmentPathElement;
import org.osate.aadl2.DataAccess;
import org.osate.aadl2.DataImplementation;
import org.osate.aadl2.DataPort;
import org.osate.aadl2.DataPrototype;
import org.osate.aadl2.DataSubcomponent;
import org.osate.aadl2.DataType;
import org.osate.aadl2.DefaultAnnexLibrary;
import org.osate.aadl2.DefaultAnnexSubclause;
import org.osate.aadl2.DeviceImplementation;
import org.osate.aadl2.DevicePrototype;
import org.osate.aadl2.DeviceSubcomponent;
import org.osate.aadl2.DeviceType;
import org.osate.aadl2.EndToEndFlow;
import org.osate.aadl2.EndToEndFlowSegment;
import org.osate.aadl2.EnumerationLiteral;
import org.osate.aadl2.EnumerationType;
import org.osate.aadl2.EventDataPort;
import org.osate.aadl2.EventDataSource;
import org.osate.aadl2.EventPort;
import org.osate.aadl2.EventSource;
import org.osate.aadl2.FeatureConnection;
import org.osate.aadl2.FeatureGroup;
import org.osate.aadl2.FeatureGroupConnection;
import org.osate.aadl2.FeatureGroupPrototype;
import org.osate.aadl2.FeatureGroupPrototypeActual;
import org.osate.aadl2.FeatureGroupPrototypeBinding;
import org.osate.aadl2.FeatureGroupType;
import org.osate.aadl2.FeatureGroupTypeRename;
import org.osate.aadl2.FeaturePrototype;
import org.osate.aadl2.FeaturePrototypeBinding;
import org.osate.aadl2.FeaturePrototypeReference;
import org.osate.aadl2.FlowEnd;
import org.osate.aadl2.FlowImplementation;
import org.osate.aadl2.FlowSegment;
import org.osate.aadl2.FlowSpecification;
import org.osate.aadl2.GroupExtension;
import org.osate.aadl2.ImplementationExtension;
import org.osate.aadl2.IntegerLiteral;
import org.osate.aadl2.ListType;
import org.osate.aadl2.ListValue;
import org.osate.aadl2.MemoryImplementation;
import org.osate.aadl2.MemoryPrototype;
import org.osate.aadl2.MemorySubcomponent;
import org.osate.aadl2.MemoryType;
import org.osate.aadl2.MetaclassReference;
import org.osate.aadl2.ModalPropertyValue;
import org.osate.aadl2.Mode;
import org.osate.aadl2.ModeBinding;
import org.osate.aadl2.ModeTransition;
import org.osate.aadl2.ModeTransitionTrigger;
import org.osate.aadl2.NamedValue;
import org.osate.aadl2.NumericRange;
import org.osate.aadl2.Operation;
import org.osate.aadl2.PackageRename;
import org.osate.aadl2.ParameterConnection;
import org.osate.aadl2.PortConnection;
import org.osate.aadl2.PortProxy;
import org.osate.aadl2.PortSpecification;
import org.osate.aadl2.PrivatePackageSection;
import org.osate.aadl2.ProcessImplementation;
import org.osate.aadl2.ProcessPrototype;
import org.osate.aadl2.ProcessSubcomponent;
import org.osate.aadl2.ProcessType;
import org.osate.aadl2.ProcessorImplementation;
import org.osate.aadl2.ProcessorPrototype;
import org.osate.aadl2.ProcessorSubcomponent;
import org.osate.aadl2.ProcessorType;
import org.osate.aadl2.Property;
import org.osate.aadl2.PropertyAssociation;
import org.osate.aadl2.PropertyConstant;
import org.osate.aadl2.PropertySet;
import org.osate.aadl2.PublicPackageSection;
import org.osate.aadl2.RangeType;
import org.osate.aadl2.RangeValue;
import org.osate.aadl2.RealLiteral;
import org.osate.aadl2.Realization;
import org.osate.aadl2.RecordType;
import org.osate.aadl2.RecordValue;
import org.osate.aadl2.ReferenceType;
import org.osate.aadl2.ReferenceValue;
import org.osate.aadl2.StringLiteral;
import org.osate.aadl2.SubprogramAccess;
import org.osate.aadl2.SubprogramCall;
import org.osate.aadl2.SubprogramCallSequence;
import org.osate.aadl2.SubprogramGroupAccess;
import org.osate.aadl2.SubprogramGroupImplementation;
import org.osate.aadl2.SubprogramGroupPrototype;
import org.osate.aadl2.SubprogramGroupSubcomponent;
import org.osate.aadl2.SubprogramGroupType;
import org.osate.aadl2.SubprogramImplementation;
import org.osate.aadl2.SubprogramPrototype;
import org.osate.aadl2.SubprogramProxy;
import org.osate.aadl2.SubprogramSubcomponent;
import org.osate.aadl2.SubprogramType;
import org.osate.aadl2.SystemImplementation;
import org.osate.aadl2.SystemPrototype;
import org.osate.aadl2.SystemSubcomponent;
import org.osate.aadl2.SystemType;
import org.osate.aadl2.ThreadGroupImplementation;
import org.osate.aadl2.ThreadGroupPrototype;
import org.osate.aadl2.ThreadGroupSubcomponent;
import org.osate.aadl2.ThreadGroupType;
import org.osate.aadl2.ThreadImplementation;
import org.osate.aadl2.ThreadPrototype;
import org.osate.aadl2.ThreadSubcomponent;
import org.osate.aadl2.ThreadType;
import org.osate.aadl2.TypeExtension;
import org.osate.aadl2.UnitLiteral;
import org.osate.aadl2.UnitsType;
import org.osate.aadl2.VirtualBusImplementation;
import org.osate.aadl2.VirtualBusPrototype;
import org.osate.aadl2.VirtualBusSubcomponent;
import org.osate.aadl2.VirtualBusType;
import org.osate.aadl2.VirtualProcessorImplementation;
import org.osate.aadl2.VirtualProcessorPrototype;
import org.osate.aadl2.VirtualProcessorSubcomponent;
import org.osate.aadl2.VirtualProcessorType;
import org.osate.gtse.config.config.Argument;
import org.osate.gtse.config.config.Assignment;
import org.osate.gtse.config.config.Combination;
import org.osate.gtse.config.config.ConfigPackage;
import org.osate.gtse.config.config.ConfigParameter;
import org.osate.gtse.config.config.ConfigPkg;
import org.osate.gtse.config.config.Configuration;
import org.osate.gtse.config.config.ElementRef;
import org.osate.gtse.config.config.NamedElementRef;
import org.osate.gtse.config.config.NestedAssignments;
import org.osate.gtse.config.config.PropertyValue;
import org.osate.gtse.config.services.ConfigGrammarAccess;
import org.osate.xtext.aadl2.serializer.Aadl2SemanticSequencer;

@SuppressWarnings("all")
public class ConfigSemanticSequencer extends Aadl2SemanticSequencer {

	@Inject
	private ConfigGrammarAccess grammarAccess;
	
	@Override
	public void sequence(ISerializationContext context, EObject semanticObject) {
		EPackage epackage = semanticObject.eClass().getEPackage();
		ParserRule rule = context.getParserRule();
		Action action = context.getAssignedAction();
		Set<Parameter> parameters = context.getEnabledBooleanParameters();
		if (epackage == Aadl2Package.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case Aadl2Package.AADL_BOOLEAN:
				if (rule == grammarAccess.getPropertyTypeRule()
						|| rule == grammarAccess.getBooleanTypeRule()) {
					sequence_BooleanType(context, (AadlBoolean) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getUnnamedPropertyTypeRule()
						|| rule == grammarAccess.getUnnamedBooleanTypeRule()) {
					sequence_UnnamedBooleanType(context, (AadlBoolean) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.AADL_INTEGER:
				if (rule == grammarAccess.getPropertyTypeRule()
						|| rule == grammarAccess.getIntegerTypeRule()) {
					sequence_IntegerType(context, (AadlInteger) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getUnnamedPropertyTypeRule()
						|| rule == grammarAccess.getUnnamedIntegerTypeRule()) {
					sequence_UnnamedIntegerType(context, (AadlInteger) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.AADL_PACKAGE:
				sequence_AadlPackage(context, (AadlPackage) semanticObject); 
				return; 
			case Aadl2Package.AADL_REAL:
				if (rule == grammarAccess.getPropertyTypeRule()
						|| rule == grammarAccess.getRealTypeRule()) {
					sequence_RealType(context, (AadlReal) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getUnnamedPropertyTypeRule()
						|| rule == grammarAccess.getUnnamedRealTypeRule()) {
					sequence_UnnamedRealType(context, (AadlReal) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.AADL_STRING:
				if (rule == grammarAccess.getPropertyTypeRule()
						|| rule == grammarAccess.getStringTypeRule()) {
					sequence_StringType(context, (AadlString) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getUnnamedPropertyTypeRule()
						|| rule == grammarAccess.getUnnamedStringTypeRule()) {
					sequence_UnnamedStringType(context, (AadlString) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.ABSTRACT_FEATURE:
				sequence_AbstractFeature(context, (AbstractFeature) semanticObject); 
				return; 
			case Aadl2Package.ABSTRACT_IMPLEMENTATION:
				sequence_AbstractImplementation(context, (AbstractImplementation) semanticObject); 
				return; 
			case Aadl2Package.ABSTRACT_PROTOTYPE:
				sequence_AbstractPrototype(context, (AbstractPrototype) semanticObject); 
				return; 
			case Aadl2Package.ABSTRACT_SUBCOMPONENT:
				sequence_AbstractSubcomponent(context, (AbstractSubcomponent) semanticObject); 
				return; 
			case Aadl2Package.ABSTRACT_TYPE:
				sequence_AbstractType(context, (AbstractType) semanticObject); 
				return; 
			case Aadl2Package.ACCESS_CONNECTION:
				sequence_AccessConnection(context, (AccessConnection) semanticObject); 
				return; 
			case Aadl2Package.ACCESS_SPECIFICATION:
				sequence_AccessSpecification(context, (AccessSpecification) semanticObject); 
				return; 
			case Aadl2Package.ARRAY_DIMENSION:
				sequence_ArrayDimension(context, (ArrayDimension) semanticObject); 
				return; 
			case Aadl2Package.ARRAY_RANGE:
				sequence_ArrayRange(context, (ArrayRange) semanticObject); 
				return; 
			case Aadl2Package.ARRAY_SIZE:
				sequence_ArraySize(context, (ArraySize) semanticObject); 
				return; 
			case Aadl2Package.BASIC_PROPERTY:
				sequence_RecordField(context, (BasicProperty) semanticObject); 
				return; 
			case Aadl2Package.BASIC_PROPERTY_ASSOCIATION:
				sequence_FieldPropertyAssociation(context, (BasicPropertyAssociation) semanticObject); 
				return; 
			case Aadl2Package.BOOLEAN_LITERAL:
				sequence_BooleanLiteral(context, (BooleanLiteral) semanticObject); 
				return; 
			case Aadl2Package.BUS_ACCESS:
				sequence_BusAccess(context, (BusAccess) semanticObject); 
				return; 
			case Aadl2Package.BUS_IMPLEMENTATION:
				sequence_BusImplementation(context, (BusImplementation) semanticObject); 
				return; 
			case Aadl2Package.BUS_PROTOTYPE:
				sequence_BusPrototype(context, (BusPrototype) semanticObject); 
				return; 
			case Aadl2Package.BUS_SUBCOMPONENT:
				sequence_BusSubcomponent(context, (BusSubcomponent) semanticObject); 
				return; 
			case Aadl2Package.BUS_TYPE:
				sequence_BusType(context, (BusType) semanticObject); 
				return; 
			case Aadl2Package.CLASSIFIER_TYPE:
				if (rule == grammarAccess.getPropertyTypeRule()
						|| rule == grammarAccess.getClassifierTypeRule()) {
					sequence_ClassifierType(context, (ClassifierType) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getUnnamedPropertyTypeRule()
						|| rule == grammarAccess.getUnnamedClassifierTypeRule()) {
					sequence_UnnamedClassifierType(context, (ClassifierType) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.CLASSIFIER_VALUE:
				if (rule == grammarAccess.getCPropertyExpressionRule()
						|| rule == grammarAccess.getConstantPropertyExpressionRule()
						|| rule == grammarAccess.getPropertyExpressionRule()
						|| rule == grammarAccess.getComponentClassifierTermRule()) {
					sequence_ComponentClassifierTerm(context, (ClassifierValue) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getQCReferenceRule()
						|| rule == grammarAccess.getPropertyOwnerRule()) {
					sequence_QCReference(context, (ClassifierValue) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.COMPONENT_IMPLEMENTATION_REFERENCE:
				sequence_ComponentImplementationReference(context, (ComponentImplementationReference) semanticObject); 
				return; 
			case Aadl2Package.COMPONENT_PROTOTYPE_ACTUAL:
				sequence_ComponentReference(context, (ComponentPrototypeActual) semanticObject); 
				return; 
			case Aadl2Package.COMPONENT_PROTOTYPE_BINDING:
				sequence_ComponentPrototypeBinding(context, (ComponentPrototypeBinding) semanticObject); 
				return; 
			case Aadl2Package.COMPONENT_TYPE_RENAME:
				sequence_CTRename(context, (ComponentTypeRename) semanticObject); 
				return; 
			case Aadl2Package.COMPUTED_VALUE:
				sequence_ComputedTerm(context, (ComputedValue) semanticObject); 
				return; 
			case Aadl2Package.CONNECTED_ELEMENT:
				if (rule == grammarAccess.getConnectedElementChainRule()) {
					sequence_ConnectedElementChain(context, (ConnectedElement) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getConnectedElementRule()) {
					sequence_ConnectedElement(context, (ConnectedElement) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getAbstractConnectionEndRule()) {
					sequence_ConnectedElement_InternalEvent_ProcessorPort(context, (ConnectedElement) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getNestedConnectedElementRule()) {
					sequence_ConnectedElement_NestedConnectedElement(context, (ConnectedElement) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getProcessorConnectionEndRule()) {
					sequence_ConnectedElement_ProcessorPort(context, (ConnectedElement) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getAccessConnectionEndRule()) {
					sequence_ConnectedElement_ProcessorSubprogram(context, (ConnectedElement) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getInternalEventRule()) {
					sequence_InternalEvent(context, (ConnectedElement) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getProcessorPortRule()) {
					sequence_ProcessorPort(context, (ConnectedElement) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getProcessorSubprogramRule()) {
					sequence_ProcessorSubprogram(context, (ConnectedElement) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.CONTAINED_NAMED_ELEMENT:
				sequence_ContainmentPath(context, (ContainedNamedElement) semanticObject); 
				return; 
			case Aadl2Package.CONTAINMENT_PATH_ELEMENT:
				sequence_ContainmentPathElement(context, (ContainmentPathElement) semanticObject); 
				return; 
			case Aadl2Package.DATA_ACCESS:
				sequence_DataAccess(context, (DataAccess) semanticObject); 
				return; 
			case Aadl2Package.DATA_IMPLEMENTATION:
				sequence_DataImplementation(context, (DataImplementation) semanticObject); 
				return; 
			case Aadl2Package.DATA_PORT:
				sequence_DataPort(context, (DataPort) semanticObject); 
				return; 
			case Aadl2Package.DATA_PROTOTYPE:
				sequence_DataPrototype(context, (DataPrototype) semanticObject); 
				return; 
			case Aadl2Package.DATA_SUBCOMPONENT:
				sequence_DataSubcomponent(context, (DataSubcomponent) semanticObject); 
				return; 
			case Aadl2Package.DATA_TYPE:
				sequence_DataType(context, (DataType) semanticObject); 
				return; 
			case Aadl2Package.DEFAULT_ANNEX_LIBRARY:
				sequence_DefaultAnnexLibrary(context, (DefaultAnnexLibrary) semanticObject); 
				return; 
			case Aadl2Package.DEFAULT_ANNEX_SUBCLAUSE:
				sequence_DefaultAnnexSubclause(context, (DefaultAnnexSubclause) semanticObject); 
				return; 
			case Aadl2Package.DEVICE_IMPLEMENTATION:
				sequence_DeviceImplementation(context, (DeviceImplementation) semanticObject); 
				return; 
			case Aadl2Package.DEVICE_PROTOTYPE:
				sequence_DevicePrototype(context, (DevicePrototype) semanticObject); 
				return; 
			case Aadl2Package.DEVICE_SUBCOMPONENT:
				sequence_DeviceSubcomponent(context, (DeviceSubcomponent) semanticObject); 
				return; 
			case Aadl2Package.DEVICE_TYPE:
				sequence_DeviceType(context, (DeviceType) semanticObject); 
				return; 
			case Aadl2Package.END_TO_END_FLOW:
				sequence_EndToEndFlow(context, (EndToEndFlow) semanticObject); 
				return; 
			case Aadl2Package.END_TO_END_FLOW_SEGMENT:
				if (rule == grammarAccess.getETEConnectionFlowRule()) {
					sequence_ETEConnectionFlow(context, (EndToEndFlowSegment) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getETESubcomponentFlowRule()) {
					sequence_ETESubcomponentFlow(context, (EndToEndFlowSegment) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.ENUMERATION_LITERAL:
				sequence_EnumerationLiteral(context, (EnumerationLiteral) semanticObject); 
				return; 
			case Aadl2Package.ENUMERATION_TYPE:
				if (rule == grammarAccess.getPropertyTypeRule()
						|| rule == grammarAccess.getEnumerationTypeRule()) {
					sequence_EnumerationType(context, (EnumerationType) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getUnnamedPropertyTypeRule()
						|| rule == grammarAccess.getUnnamedEnumerationTypeRule()) {
					sequence_UnnamedEnumerationType(context, (EnumerationType) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.EVENT_DATA_PORT:
				sequence_EventDataPort(context, (EventDataPort) semanticObject); 
				return; 
			case Aadl2Package.EVENT_DATA_SOURCE:
				sequence_EventDataSource(context, (EventDataSource) semanticObject); 
				return; 
			case Aadl2Package.EVENT_PORT:
				sequence_EventPort(context, (EventPort) semanticObject); 
				return; 
			case Aadl2Package.EVENT_SOURCE:
				sequence_EventSource(context, (EventSource) semanticObject); 
				return; 
			case Aadl2Package.FEATURE_CONNECTION:
				sequence_FeatureConnection(context, (FeatureConnection) semanticObject); 
				return; 
			case Aadl2Package.FEATURE_GROUP:
				sequence_FeatureGroup(context, (FeatureGroup) semanticObject); 
				return; 
			case Aadl2Package.FEATURE_GROUP_CONNECTION:
				sequence_FeatureGroupConnection(context, (FeatureGroupConnection) semanticObject); 
				return; 
			case Aadl2Package.FEATURE_GROUP_PROTOTYPE:
				sequence_FeatureGroupPrototype(context, (FeatureGroupPrototype) semanticObject); 
				return; 
			case Aadl2Package.FEATURE_GROUP_PROTOTYPE_ACTUAL:
				sequence_FeatureGroupPrototypeActual(context, (FeatureGroupPrototypeActual) semanticObject); 
				return; 
			case Aadl2Package.FEATURE_GROUP_PROTOTYPE_BINDING:
				sequence_FeatureGroupPrototypeBinding(context, (FeatureGroupPrototypeBinding) semanticObject); 
				return; 
			case Aadl2Package.FEATURE_GROUP_TYPE:
				sequence_FeatureGroupType(context, (FeatureGroupType) semanticObject); 
				return; 
			case Aadl2Package.FEATURE_GROUP_TYPE_RENAME:
				sequence_FGTRename(context, (FeatureGroupTypeRename) semanticObject); 
				return; 
			case Aadl2Package.FEATURE_PROTOTYPE:
				sequence_FeaturePrototype(context, (FeaturePrototype) semanticObject); 
				return; 
			case Aadl2Package.FEATURE_PROTOTYPE_BINDING:
				sequence_FeaturePrototypeBinding(context, (FeaturePrototypeBinding) semanticObject); 
				return; 
			case Aadl2Package.FEATURE_PROTOTYPE_REFERENCE:
				sequence_FeaturePrototypeReference(context, (FeaturePrototypeReference) semanticObject); 
				return; 
			case Aadl2Package.FLOW_END:
				sequence_FlowEnd(context, (FlowEnd) semanticObject); 
				return; 
			case Aadl2Package.FLOW_IMPLEMENTATION:
				if (rule == grammarAccess.getFlowPathImplRule()) {
					sequence_FlowPathImpl(context, (FlowImplementation) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getFlowImplementationRule()) {
					sequence_FlowPathImpl_FlowSinkImpl_FlowSourceImpl(context, (FlowImplementation) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getFlowSinkImplRule()) {
					sequence_FlowSinkImpl(context, (FlowImplementation) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getFlowSourceImplRule()) {
					sequence_FlowSourceImpl(context, (FlowImplementation) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.FLOW_SEGMENT:
				if (rule == grammarAccess.getConnectionFlowRule()) {
					sequence_ConnectionFlow(context, (FlowSegment) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getSubcomponentFlowRule()) {
					sequence_SubcomponentFlow(context, (FlowSegment) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.FLOW_SPECIFICATION:
				if (rule == grammarAccess.getFlowSpecificationRule()) {
					sequence_FlowPathSpec_FlowSinkSpec_FlowSourceSpec_FlowSpecRefinement(context, (FlowSpecification) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getFlowPathSpecRule()) {
					sequence_FlowPathSpec(context, (FlowSpecification) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getFlowSinkSpecRule()) {
					sequence_FlowSinkSpec(context, (FlowSpecification) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getFlowSourceSpecRule()) {
					sequence_FlowSourceSpec(context, (FlowSpecification) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getFlowSpecRefinementRule()) {
					sequence_FlowSpecRefinement(context, (FlowSpecification) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.GROUP_EXTENSION:
				sequence_GroupExtension(context, (GroupExtension) semanticObject); 
				return; 
			case Aadl2Package.IMPLEMENTATION_EXTENSION:
				sequence_ImplementationExtension(context, (ImplementationExtension) semanticObject); 
				return; 
			case Aadl2Package.INTEGER_LITERAL:
				if (rule == grammarAccess.getNumberValueRule()
						|| rule == grammarAccess.getIntegerLitRule()) {
					sequence_IntegerLit(context, (IntegerLiteral) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getCPropertyExpressionRule()
						|| rule == grammarAccess.getConstantPropertyExpressionRule()
						|| rule == grammarAccess.getPropertyExpressionRule()
						|| rule == grammarAccess.getIntegerTermRule()
						|| rule == grammarAccess.getNumAltRule()) {
					sequence_IntegerTerm(context, (IntegerLiteral) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.LIST_TYPE:
				sequence_ListType(context, (ListType) semanticObject); 
				return; 
			case Aadl2Package.LIST_VALUE:
				sequence_ListTerm(context, (ListValue) semanticObject); 
				return; 
			case Aadl2Package.MEMORY_IMPLEMENTATION:
				sequence_MemoryImplementation(context, (MemoryImplementation) semanticObject); 
				return; 
			case Aadl2Package.MEMORY_PROTOTYPE:
				sequence_MemoryPrototype(context, (MemoryPrototype) semanticObject); 
				return; 
			case Aadl2Package.MEMORY_SUBCOMPONENT:
				sequence_MemorySubcomponent(context, (MemorySubcomponent) semanticObject); 
				return; 
			case Aadl2Package.MEMORY_TYPE:
				sequence_MemoryType(context, (MemoryType) semanticObject); 
				return; 
			case Aadl2Package.METACLASS_REFERENCE:
				if (rule == grammarAccess.getAllReferenceRule()) {
					sequence_AllReference(context, (MetaclassReference) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getQMReferenceRule()
						|| rule == grammarAccess.getPropertyOwnerRule()) {
					sequence_QMReference(context, (MetaclassReference) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.MODAL_PROPERTY_VALUE:
				if (rule == grammarAccess.getModalPropertyValueRule()) {
					sequence_ModalPropertyValue(context, (ModalPropertyValue) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getOptionalModalPropertyValueRule()) {
					sequence_OptionalModalPropertyValue(context, (ModalPropertyValue) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getPropertyValueRule()) {
					sequence_PropertyValue(context, (ModalPropertyValue) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.MODE:
				sequence_Mode(context, (Mode) semanticObject); 
				return; 
			case Aadl2Package.MODE_BINDING:
				sequence_ModeRef(context, (ModeBinding) semanticObject); 
				return; 
			case Aadl2Package.MODE_TRANSITION:
				sequence_ModeTransition(context, (ModeTransition) semanticObject); 
				return; 
			case Aadl2Package.MODE_TRANSITION_TRIGGER:
				sequence_Trigger(context, (ModeTransitionTrigger) semanticObject); 
				return; 
			case Aadl2Package.NAMED_VALUE:
				if (rule == grammarAccess.getConstantValueRule()
						|| rule == grammarAccess.getNumAltRule()) {
					sequence_ConstantValue(context, (NamedValue) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getConstantPropertyExpressionRule()
						|| rule == grammarAccess.getPropertyExpressionRule()
						|| rule == grammarAccess.getLiteralorReferenceTermRule()) {
					sequence_LiteralorReferenceTerm(context, (NamedValue) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.NUMERIC_RANGE:
				if (rule == grammarAccess.getIntegerRangeRule()) {
					sequence_IntegerRange(context, (NumericRange) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getRealRangeRule()) {
					sequence_RealRange(context, (NumericRange) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.OPERATION:
				sequence_SignedConstant(context, (Operation) semanticObject); 
				return; 
			case Aadl2Package.PACKAGE_RENAME:
				if (rule == grammarAccess.getPackageRenameRule()) {
					sequence_PackageRename(context, (PackageRename) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getRenameAllRule()) {
					sequence_RenameAll(context, (PackageRename) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.PARAMETER:
				sequence_Parameter(context, (org.osate.aadl2.Parameter) semanticObject); 
				return; 
			case Aadl2Package.PARAMETER_CONNECTION:
				sequence_ParameterConnection(context, (ParameterConnection) semanticObject); 
				return; 
			case Aadl2Package.PORT_CONNECTION:
				sequence_PortConnection(context, (PortConnection) semanticObject); 
				return; 
			case Aadl2Package.PORT_PROXY:
				sequence_PortProxy(context, (PortProxy) semanticObject); 
				return; 
			case Aadl2Package.PORT_SPECIFICATION:
				sequence_PortSpecification(context, (PortSpecification) semanticObject); 
				return; 
			case Aadl2Package.PRIVATE_PACKAGE_SECTION:
				sequence_PrivatePackageSection(context, (PrivatePackageSection) semanticObject); 
				return; 
			case Aadl2Package.PROCESS_IMPLEMENTATION:
				sequence_ProcessImplementation(context, (ProcessImplementation) semanticObject); 
				return; 
			case Aadl2Package.PROCESS_PROTOTYPE:
				sequence_ProcessPrototype(context, (ProcessPrototype) semanticObject); 
				return; 
			case Aadl2Package.PROCESS_SUBCOMPONENT:
				sequence_ProcessSubcomponent(context, (ProcessSubcomponent) semanticObject); 
				return; 
			case Aadl2Package.PROCESS_TYPE:
				sequence_ProcessType(context, (ProcessType) semanticObject); 
				return; 
			case Aadl2Package.PROCESSOR_IMPLEMENTATION:
				sequence_ProcessorImplementation(context, (ProcessorImplementation) semanticObject); 
				return; 
			case Aadl2Package.PROCESSOR_PROTOTYPE:
				sequence_ProcessorPrototype(context, (ProcessorPrototype) semanticObject); 
				return; 
			case Aadl2Package.PROCESSOR_SUBCOMPONENT:
				sequence_ProcessorSubcomponent(context, (ProcessorSubcomponent) semanticObject); 
				return; 
			case Aadl2Package.PROCESSOR_TYPE:
				sequence_ProcessorType(context, (ProcessorType) semanticObject); 
				return; 
			case Aadl2Package.PROPERTY:
				sequence_PropertyDefinition(context, (Property) semanticObject); 
				return; 
			case Aadl2Package.PROPERTY_ASSOCIATION:
				if (rule == grammarAccess.getBasicPropertyAssociationRule()) {
					sequence_BasicPropertyAssociation(context, (PropertyAssociation) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getPModelRule()
						|| rule == grammarAccess.getContainedPropertyAssociationRule()) {
					sequence_ContainedPropertyAssociation(context, (PropertyAssociation) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getPropertyAssociationRule()) {
					sequence_PropertyAssociation(context, (PropertyAssociation) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.PROPERTY_CONSTANT:
				sequence_PropertyConstant(context, (PropertyConstant) semanticObject); 
				return; 
			case Aadl2Package.PROPERTY_SET:
				sequence_PropertySet(context, (PropertySet) semanticObject); 
				return; 
			case Aadl2Package.PUBLIC_PACKAGE_SECTION:
				sequence_PublicPackageSection(context, (PublicPackageSection) semanticObject); 
				return; 
			case Aadl2Package.RANGE_TYPE:
				if (rule == grammarAccess.getPropertyTypeRule()
						|| rule == grammarAccess.getRangeTypeRule()) {
					sequence_RangeType(context, (RangeType) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getUnnamedPropertyTypeRule()
						|| rule == grammarAccess.getUnnamedRangeTypeRule()) {
					sequence_UnnamedRangeType(context, (RangeType) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.RANGE_VALUE:
				sequence_NumericRangeTerm(context, (RangeValue) semanticObject); 
				return; 
			case Aadl2Package.REAL_LITERAL:
				if (rule == grammarAccess.getNumberValueRule()
						|| rule == grammarAccess.getRealLitRule()) {
					sequence_RealLit(context, (RealLiteral) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getCPropertyExpressionRule()
						|| rule == grammarAccess.getConstantPropertyExpressionRule()
						|| rule == grammarAccess.getPropertyExpressionRule()
						|| rule == grammarAccess.getRealTermRule()
						|| rule == grammarAccess.getNumAltRule()) {
					sequence_RealTerm(context, (RealLiteral) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.REALIZATION:
				sequence_Realization(context, (Realization) semanticObject); 
				return; 
			case Aadl2Package.RECORD_TYPE:
				if (rule == grammarAccess.getPropertyTypeRule()
						|| rule == grammarAccess.getRecordTypeRule()) {
					sequence_RecordType(context, (RecordType) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getUnnamedPropertyTypeRule()
						|| rule == grammarAccess.getUnnamedRecordTypeRule()) {
					sequence_UnnamedRecordType(context, (RecordType) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.RECORD_VALUE:
				if (rule == grammarAccess.getOldRecordTermRule()) {
					sequence_OldRecordTerm(context, (RecordValue) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getCPropertyExpressionRule()
						|| rule == grammarAccess.getConstantPropertyExpressionRule()
						|| rule == grammarAccess.getPropertyExpressionRule()
						|| rule == grammarAccess.getRecordTermRule()) {
					sequence_RecordTerm(context, (RecordValue) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.REFERENCE_TYPE:
				if (rule == grammarAccess.getPropertyTypeRule()
						|| rule == grammarAccess.getReferenceTypeRule()) {
					sequence_ReferenceType(context, (ReferenceType) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getUnnamedPropertyTypeRule()
						|| rule == grammarAccess.getUnnamedReferenceTypeRule()) {
					sequence_UnnamedReferenceType(context, (ReferenceType) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.REFERENCE_VALUE:
				sequence_ReferenceTerm(context, (ReferenceValue) semanticObject); 
				return; 
			case Aadl2Package.STRING_LITERAL:
				sequence_StringTerm(context, (StringLiteral) semanticObject); 
				return; 
			case Aadl2Package.SUBPROGRAM_ACCESS:
				sequence_SubprogramAccess(context, (SubprogramAccess) semanticObject); 
				return; 
			case Aadl2Package.SUBPROGRAM_CALL:
				sequence_SubprogramCall(context, (SubprogramCall) semanticObject); 
				return; 
			case Aadl2Package.SUBPROGRAM_CALL_SEQUENCE:
				sequence_SubprogramCallSequence(context, (SubprogramCallSequence) semanticObject); 
				return; 
			case Aadl2Package.SUBPROGRAM_GROUP_ACCESS:
				sequence_SubprogramGroupAccess(context, (SubprogramGroupAccess) semanticObject); 
				return; 
			case Aadl2Package.SUBPROGRAM_GROUP_IMPLEMENTATION:
				sequence_SubprogramGroupImplementation(context, (SubprogramGroupImplementation) semanticObject); 
				return; 
			case Aadl2Package.SUBPROGRAM_GROUP_PROTOTYPE:
				sequence_SubprogramGroupPrototype(context, (SubprogramGroupPrototype) semanticObject); 
				return; 
			case Aadl2Package.SUBPROGRAM_GROUP_SUBCOMPONENT:
				sequence_SubprogramGroupSubcomponent(context, (SubprogramGroupSubcomponent) semanticObject); 
				return; 
			case Aadl2Package.SUBPROGRAM_GROUP_TYPE:
				sequence_SubprogramGroupType(context, (SubprogramGroupType) semanticObject); 
				return; 
			case Aadl2Package.SUBPROGRAM_IMPLEMENTATION:
				sequence_SubprogramImplementation(context, (SubprogramImplementation) semanticObject); 
				return; 
			case Aadl2Package.SUBPROGRAM_PROTOTYPE:
				sequence_SubprogramPrototype(context, (SubprogramPrototype) semanticObject); 
				return; 
			case Aadl2Package.SUBPROGRAM_PROXY:
				sequence_SubprogramProxy(context, (SubprogramProxy) semanticObject); 
				return; 
			case Aadl2Package.SUBPROGRAM_SUBCOMPONENT:
				sequence_SubprogramSubcomponent(context, (SubprogramSubcomponent) semanticObject); 
				return; 
			case Aadl2Package.SUBPROGRAM_TYPE:
				sequence_SubprogramType(context, (SubprogramType) semanticObject); 
				return; 
			case Aadl2Package.SYSTEM_IMPLEMENTATION:
				sequence_SystemImplementation(context, (SystemImplementation) semanticObject); 
				return; 
			case Aadl2Package.SYSTEM_PROTOTYPE:
				sequence_SystemPrototype(context, (SystemPrototype) semanticObject); 
				return; 
			case Aadl2Package.SYSTEM_SUBCOMPONENT:
				sequence_SystemSubcomponent(context, (SystemSubcomponent) semanticObject); 
				return; 
			case Aadl2Package.SYSTEM_TYPE:
				sequence_SystemType(context, (SystemType) semanticObject); 
				return; 
			case Aadl2Package.THREAD_GROUP_IMPLEMENTATION:
				sequence_ThreadGroupImplementation(context, (ThreadGroupImplementation) semanticObject); 
				return; 
			case Aadl2Package.THREAD_GROUP_PROTOTYPE:
				sequence_ThreadGroupPrototype(context, (ThreadGroupPrototype) semanticObject); 
				return; 
			case Aadl2Package.THREAD_GROUP_SUBCOMPONENT:
				sequence_ThreadGroupSubcomponent(context, (ThreadGroupSubcomponent) semanticObject); 
				return; 
			case Aadl2Package.THREAD_GROUP_TYPE:
				sequence_ThreadGroupType(context, (ThreadGroupType) semanticObject); 
				return; 
			case Aadl2Package.THREAD_IMPLEMENTATION:
				sequence_ThreadImplementation(context, (ThreadImplementation) semanticObject); 
				return; 
			case Aadl2Package.THREAD_PROTOTYPE:
				sequence_ThreadPrototype(context, (ThreadPrototype) semanticObject); 
				return; 
			case Aadl2Package.THREAD_SUBCOMPONENT:
				sequence_ThreadSubcomponent(context, (ThreadSubcomponent) semanticObject); 
				return; 
			case Aadl2Package.THREAD_TYPE:
				sequence_ThreadType(context, (ThreadType) semanticObject); 
				return; 
			case Aadl2Package.TYPE_EXTENSION:
				sequence_TypeExtension(context, (TypeExtension) semanticObject); 
				return; 
			case Aadl2Package.UNIT_LITERAL:
				if (rule == grammarAccess.getUnitLiteralConversionRule()) {
					sequence_UnitLiteralConversion(context, (UnitLiteral) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getUnitLiteralRule()) {
					sequence_UnitLiteral(context, (UnitLiteral) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.UNITS_TYPE:
				if (rule == grammarAccess.getPropertyTypeRule()
						|| rule == grammarAccess.getUnitsTypeRule()) {
					sequence_UnitsType(context, (UnitsType) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getUnnamedPropertyTypeRule()
						|| rule == grammarAccess.getUnnamedUnitsTypeRule()) {
					sequence_UnnamedUnitsType(context, (UnitsType) semanticObject); 
					return; 
				}
				else break;
			case Aadl2Package.VIRTUAL_BUS_IMPLEMENTATION:
				sequence_VirtualBusImplementation(context, (VirtualBusImplementation) semanticObject); 
				return; 
			case Aadl2Package.VIRTUAL_BUS_PROTOTYPE:
				sequence_VirtualBusPrototype(context, (VirtualBusPrototype) semanticObject); 
				return; 
			case Aadl2Package.VIRTUAL_BUS_SUBCOMPONENT:
				sequence_VirtualBusSubcomponent(context, (VirtualBusSubcomponent) semanticObject); 
				return; 
			case Aadl2Package.VIRTUAL_BUS_TYPE:
				sequence_VirtualBusType(context, (VirtualBusType) semanticObject); 
				return; 
			case Aadl2Package.VIRTUAL_PROCESSOR_IMPLEMENTATION:
				sequence_VirtualProcessorImplementation(context, (VirtualProcessorImplementation) semanticObject); 
				return; 
			case Aadl2Package.VIRTUAL_PROCESSOR_PROTOTYPE:
				sequence_VirtualProcessorPrototype(context, (VirtualProcessorPrototype) semanticObject); 
				return; 
			case Aadl2Package.VIRTUAL_PROCESSOR_SUBCOMPONENT:
				sequence_VirtualProcessorSubcomponent(context, (VirtualProcessorSubcomponent) semanticObject); 
				return; 
			case Aadl2Package.VIRTUAL_PROCESSOR_TYPE:
				sequence_VirtualProcessorType(context, (VirtualProcessorType) semanticObject); 
				return; 
			}
		else if (epackage == ConfigPackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case ConfigPackage.ARGUMENT:
				sequence_Argument(context, (Argument) semanticObject); 
				return; 
			case ConfigPackage.ASSIGNMENT:
				sequence_Assignment(context, (Assignment) semanticObject); 
				return; 
			case ConfigPackage.COMBINATION:
				sequence_Arguments_Combination(context, (Combination) semanticObject); 
				return; 
			case ConfigPackage.CONFIG_PARAMETER:
				sequence_Candidates_ConfigParameter_FClassifierType_FPropertyType(context, (ConfigParameter) semanticObject); 
				return; 
			case ConfigPackage.CONFIG_PKG:
				sequence_ConfigPkg(context, (ConfigPkg) semanticObject); 
				return; 
			case ConfigPackage.CONFIGURATION:
				sequence_Assignments_Configuration_Parameters(context, (Configuration) semanticObject); 
				return; 
			case ConfigPackage.ELEMENT_REF:
				sequence_ElementRef(context, (ElementRef) semanticObject); 
				return; 
			case ConfigPackage.NAMED_ELEMENT_REF:
				sequence_Arguments_Assignments_ConfigExpression(context, (NamedElementRef) semanticObject); 
				return; 
			case ConfigPackage.NESTED_ASSIGNMENTS:
				sequence_Assignments_ConfigExpression(context, (NestedAssignments) semanticObject); 
				return; 
			case ConfigPackage.PROPERTY_VALUE:
				sequence_ConfigExpression(context, (PropertyValue) semanticObject); 
				return; 
			}
		if (errorAcceptor != null)
			errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Contexts:
	 *     Argument returns Argument
	 *
	 * Constraint:
	 *     (parameter=[ConfigParameter|ID] value=ConfigExpression)
	 */
	protected void sequence_Argument(ISerializationContext context, Argument semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, ConfigPackage.Literals.ARGUMENT__PARAMETER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ConfigPackage.Literals.ARGUMENT__PARAMETER));
			if (transientValues.isValueTransient(semanticObject, ConfigPackage.Literals.ARGUMENT__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ConfigPackage.Literals.ARGUMENT__VALUE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getArgumentAccess().getParameterConfigParameterIDTerminalRuleCall_0_0_1(), semanticObject.eGet(ConfigPackage.Literals.ARGUMENT__PARAMETER, false));
		feeder.accept(grammarAccess.getArgumentAccess().getValueConfigExpressionParserRuleCall_2_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     ConfigExpression returns NamedElementRef
	 *
	 * Constraint:
	 *     (ref=[NamedElement|CNAME] (arguments+=Argument arguments+=Argument*)? (assignments+=Assignment assignments+=Assignment*)?)
	 */
	protected void sequence_Arguments_Assignments_ConfigExpression(ISerializationContext context, NamedElementRef semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Combination returns Combination
	 *
	 * Constraint:
	 *     (unsafe?='unsafe'? configuration=[Configuration|ID] (arguments+=Argument arguments+=Argument*)?)
	 */
	protected void sequence_Arguments_Combination(ISerializationContext context, Combination semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Assignment returns Assignment
	 *
	 * Constraint:
	 *     (((ref=ElementRef property=[Property|PNAME]?) | property=[Property|PNAME]) value=ConfigExpression)
	 */
	protected void sequence_Assignment(ISerializationContext context, Assignment semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     ConfigExpression returns NestedAssignments
	 *
	 * Constraint:
	 *     (assignments+=Assignment assignments+=Assignment*)?
	 */
	protected void sequence_Assignments_ConfigExpression(ISerializationContext context, NestedAssignments semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Configuration returns Configuration
	 *     NamedElement returns Configuration
	 *
	 * Constraint:
	 *     (
	 *         name=ID 
	 *         (parameters+=ConfigParameter parameters+=ConfigParameter*)? 
	 *         extended=[ComponentClassifier|CNAME]? 
	 *         (combined+=Combination combined+=Combination*)? 
	 *         (assignments+=Assignment assignments+=Assignment*)?
	 *     )
	 */
	protected void sequence_Assignments_Configuration_Parameters(ISerializationContext context, Configuration semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     ConfigParameter returns ConfigParameter
	 *     NamedElement returns ConfigParameter
	 *
	 * Constraint:
	 *     (
	 *         name=ID 
	 *         ((category=ComponentCategory classifier=[ComponentClassifier|CNAME]) | propertyType=[Property|PNAME]) 
	 *         (candidates+=ConfigExpression candidates+=ConfigExpression*)?
	 *     )
	 */
	protected void sequence_Candidates_ConfigParameter_FClassifierType_FPropertyType(ISerializationContext context, ConfigParameter semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     ConfigExpression returns PropertyValue
	 *
	 * Constraint:
	 *     exp=CPropertyExpression
	 */
	protected void sequence_ConfigExpression(ISerializationContext context, PropertyValue semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, ConfigPackage.Literals.PROPERTY_VALUE__EXP) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ConfigPackage.Literals.PROPERTY_VALUE__EXP));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getConfigExpressionAccess().getExpCPropertyExpressionParserRuleCall_1_1_0(), semanticObject.getExp());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     ConfigPkg returns ConfigPkg
	 *     NamedElement returns ConfigPkg
	 *
	 * Constraint:
	 *     (root=[Configuration|ID] configurations+=Configuration*)
	 */
	protected void sequence_ConfigPkg(ISerializationContext context, ConfigPkg semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     ElementRef returns ElementRef
	 *     ElementRef.ElementRef_1_0_0 returns ElementRef
	 *
	 * Constraint:
	 *     (element=[NamedElement|ID] | (prev=ElementRef_ElementRef_1_0_0 element=[NamedElement|ID]))
	 */
	protected void sequence_ElementRef(ISerializationContext context, ElementRef semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
}
