/**
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
package org.osate.gtse.config.config.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.osate.gtse.config.config.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ConfigFactoryImpl extends EFactoryImpl implements ConfigFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static ConfigFactory init()
  {
    try
    {
      ConfigFactory theConfigFactory = (ConfigFactory)EPackage.Registry.INSTANCE.getEFactory(ConfigPackage.eNS_URI);
      if (theConfigFactory != null)
      {
        return theConfigFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new ConfigFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConfigFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case ConfigPackage.CONFIG_PKG: return createConfigPkg();
      case ConfigPackage.OUTPUT_VARIABLE: return createOutputVariable();
      case ConfigPackage.LIMIT: return createLimit();
      case ConfigPackage.CONFIGURATION: return createConfiguration();
      case ConfigPackage.COMBINATION: return createCombination();
      case ConfigPackage.CONFIG_PARAMETER: return createConfigParameter();
      case ConfigPackage.CONFIG_VALUE: return createConfigValue();
      case ConfigPackage.ASSIGNMENT: return createAssignment();
      case ConfigPackage.ARGUMENT: return createArgument();
      case ConfigPackage.ELEMENT_REF: return createElementRef();
      case ConfigPackage.CANDIDATE_LIST: return createCandidateList();
      case ConfigPackage.NAMED_ELEMENT_REF: return createNamedElementRef();
      case ConfigPackage.PROPERTY_VALUE: return createPropertyValue();
      case ConfigPackage.NESTED_ASSIGNMENTS: return createNestedAssignments();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object createFromString(EDataType eDataType, String initialValue)
  {
    switch (eDataType.getClassifierID())
    {
      case ConfigPackage.TYPE:
        return createTypeFromString(eDataType, initialValue);
      case ConfigPackage.RELATION:
        return createRelationFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String convertToString(EDataType eDataType, Object instanceValue)
  {
    switch (eDataType.getClassifierID())
    {
      case ConfigPackage.TYPE:
        return convertTypeToString(eDataType, instanceValue);
      case ConfigPackage.RELATION:
        return convertRelationToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConfigPkg createConfigPkg()
  {
    ConfigPkgImpl configPkg = new ConfigPkgImpl();
    return configPkg;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OutputVariable createOutputVariable()
  {
    OutputVariableImpl outputVariable = new OutputVariableImpl();
    return outputVariable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Limit createLimit()
  {
    LimitImpl limit = new LimitImpl();
    return limit;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Configuration createConfiguration()
  {
    ConfigurationImpl configuration = new ConfigurationImpl();
    return configuration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Combination createCombination()
  {
    CombinationImpl combination = new CombinationImpl();
    return combination;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConfigParameter createConfigParameter()
  {
    ConfigParameterImpl configParameter = new ConfigParameterImpl();
    return configParameter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConfigValue createConfigValue()
  {
    ConfigValueImpl configValue = new ConfigValueImpl();
    return configValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Assignment createAssignment()
  {
    AssignmentImpl assignment = new AssignmentImpl();
    return assignment;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Argument createArgument()
  {
    ArgumentImpl argument = new ArgumentImpl();
    return argument;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ElementRef createElementRef()
  {
    ElementRefImpl elementRef = new ElementRefImpl();
    return elementRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CandidateList createCandidateList()
  {
    CandidateListImpl candidateList = new CandidateListImpl();
    return candidateList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NamedElementRef createNamedElementRef()
  {
    NamedElementRefImpl namedElementRef = new NamedElementRefImpl();
    return namedElementRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PropertyValue createPropertyValue()
  {
    PropertyValueImpl propertyValue = new PropertyValueImpl();
    return propertyValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NestedAssignments createNestedAssignments()
  {
    NestedAssignmentsImpl nestedAssignments = new NestedAssignmentsImpl();
    return nestedAssignments;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Type createTypeFromString(EDataType eDataType, String initialValue)
  {
    Type result = Type.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertTypeToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Relation createRelationFromString(EDataType eDataType, String initialValue)
  {
    Relation result = Relation.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertRelationToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConfigPackage getConfigPackage()
  {
    return (ConfigPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static ConfigPackage getPackage()
  {
    return ConfigPackage.eINSTANCE;
  }

} //ConfigFactoryImpl
