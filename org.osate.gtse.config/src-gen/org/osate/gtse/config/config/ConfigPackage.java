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
package org.osate.gtse.config.config;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.osate.aadl2.Aadl2Package;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.osate.gtse.config.config.ConfigFactory
 * @model kind="package"
 * @generated
 */
public interface ConfigPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "config";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.osate.org/gtse/config/Config";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "config";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ConfigPackage eINSTANCE = org.osate.gtse.config.config.impl.ConfigPackageImpl.init();

  /**
   * The meta object id for the '{@link org.osate.gtse.config.config.impl.ConfigPkgImpl <em>Pkg</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.osate.gtse.config.config.impl.ConfigPkgImpl
   * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getConfigPkg()
   * @generated
   */
  int CONFIG_PKG = 0;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIG_PKG__OWNED_ELEMENT = Aadl2Package.NAMED_ELEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIG_PKG__OWNED_COMMENT = Aadl2Package.NAMED_ELEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIG_PKG__NAME = Aadl2Package.NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIG_PKG__QUALIFIED_NAME = Aadl2Package.NAMED_ELEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owned Property Association</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIG_PKG__OWNED_PROPERTY_ASSOCIATION = Aadl2Package.NAMED_ELEMENT__OWNED_PROPERTY_ASSOCIATION;

  /**
   * The feature id for the '<em><b>Root</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIG_PKG__ROOT = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Configurations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIG_PKG__CONFIGURATIONS = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Analyses</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIG_PKG__ANALYSES = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIG_PKG__OUTPUTS = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Pkg</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIG_PKG_FEATURE_COUNT = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link org.osate.gtse.config.config.impl.OutputVariableImpl <em>Output Variable</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.osate.gtse.config.config.impl.OutputVariableImpl
   * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getOutputVariable()
   * @generated
   */
  int OUTPUT_VARIABLE = 1;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OUTPUT_VARIABLE__OWNED_ELEMENT = Aadl2Package.NAMED_ELEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OUTPUT_VARIABLE__OWNED_COMMENT = Aadl2Package.NAMED_ELEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OUTPUT_VARIABLE__NAME = Aadl2Package.NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OUTPUT_VARIABLE__QUALIFIED_NAME = Aadl2Package.NAMED_ELEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owned Property Association</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OUTPUT_VARIABLE__OWNED_PROPERTY_ASSOCIATION = Aadl2Package.NAMED_ELEMENT__OWNED_PROPERTY_ASSOCIATION;

  /**
   * The feature id for the '<em><b>Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OUTPUT_VARIABLE__TYPE = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Limit</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OUTPUT_VARIABLE__LIMIT = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Output Variable</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OUTPUT_VARIABLE_FEATURE_COUNT = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.osate.gtse.config.config.impl.LimitImpl <em>Limit</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.osate.gtse.config.config.impl.LimitImpl
   * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getLimit()
   * @generated
   */
  int LIMIT = 2;

  /**
   * The feature id for the '<em><b>Relation</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIMIT__RELATION = 0;

  /**
   * The feature id for the '<em><b>Bound</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIMIT__BOUND = 1;

  /**
   * The number of structural features of the '<em>Limit</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIMIT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.osate.gtse.config.config.impl.ConfigurationImpl <em>Configuration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.osate.gtse.config.config.impl.ConfigurationImpl
   * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getConfiguration()
   * @generated
   */
  int CONFIGURATION = 3;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIGURATION__OWNED_ELEMENT = Aadl2Package.NAMED_ELEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIGURATION__OWNED_COMMENT = Aadl2Package.NAMED_ELEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIGURATION__NAME = Aadl2Package.NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIGURATION__QUALIFIED_NAME = Aadl2Package.NAMED_ELEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owned Property Association</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIGURATION__OWNED_PROPERTY_ASSOCIATION = Aadl2Package.NAMED_ELEMENT__OWNED_PROPERTY_ASSOCIATION;

  /**
   * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIGURATION__PARAMETERS = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Extended</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIGURATION__EXTENDED = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Combined</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIGURATION__COMBINED = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Assignments</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIGURATION__ASSIGNMENTS = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIGURATION__CONSTRAINTS = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 4;

  /**
   * The number of structural features of the '<em>Configuration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIGURATION_FEATURE_COUNT = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 5;

  /**
   * The meta object id for the '{@link org.osate.gtse.config.config.impl.CombinationImpl <em>Combination</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.osate.gtse.config.config.impl.CombinationImpl
   * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getCombination()
   * @generated
   */
  int COMBINATION = 4;

  /**
   * The feature id for the '<em><b>Unsafe</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMBINATION__UNSAFE = 0;

  /**
   * The feature id for the '<em><b>Configuration</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMBINATION__CONFIGURATION = 1;

  /**
   * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMBINATION__ARGUMENTS = 2;

  /**
   * The number of structural features of the '<em>Combination</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COMBINATION_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.osate.gtse.config.config.impl.ConfigParameterImpl <em>Parameter</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.osate.gtse.config.config.impl.ConfigParameterImpl
   * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getConfigParameter()
   * @generated
   */
  int CONFIG_PARAMETER = 5;

  /**
   * The feature id for the '<em><b>Owned Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIG_PARAMETER__OWNED_ELEMENT = Aadl2Package.NAMED_ELEMENT__OWNED_ELEMENT;

  /**
   * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIG_PARAMETER__OWNED_COMMENT = Aadl2Package.NAMED_ELEMENT__OWNED_COMMENT;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIG_PARAMETER__NAME = Aadl2Package.NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIG_PARAMETER__QUALIFIED_NAME = Aadl2Package.NAMED_ELEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owned Property Association</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIG_PARAMETER__OWNED_PROPERTY_ASSOCIATION = Aadl2Package.NAMED_ELEMENT__OWNED_PROPERTY_ASSOCIATION;

  /**
   * The feature id for the '<em><b>Category</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIG_PARAMETER__CATEGORY = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Classifier</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIG_PARAMETER__CLASSIFIER = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Property Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIG_PARAMETER__PROPERTY_TYPE = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Choices</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIG_PARAMETER__CHOICES = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Parameter</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIG_PARAMETER_FEATURE_COUNT = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link org.osate.gtse.config.config.impl.ConfigValueImpl <em>Value</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.osate.gtse.config.config.impl.ConfigValueImpl
   * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getConfigValue()
   * @generated
   */
  int CONFIG_VALUE = 6;

  /**
   * The number of structural features of the '<em>Value</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIG_VALUE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.osate.gtse.config.config.impl.AssignmentImpl <em>Assignment</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.osate.gtse.config.config.impl.AssignmentImpl
   * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getAssignment()
   * @generated
   */
  int ASSIGNMENT = 7;

  /**
   * The feature id for the '<em><b>Wildcard</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSIGNMENT__WILDCARD = 0;

  /**
   * The feature id for the '<em><b>Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSIGNMENT__REF = 1;

  /**
   * The feature id for the '<em><b>Property</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSIGNMENT__PROPERTY = 2;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSIGNMENT__VALUE = 3;

  /**
   * The number of structural features of the '<em>Assignment</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSIGNMENT_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.osate.gtse.config.config.impl.ArgumentImpl <em>Argument</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.osate.gtse.config.config.impl.ArgumentImpl
   * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getArgument()
   * @generated
   */
  int ARGUMENT = 8;

  /**
   * The feature id for the '<em><b>Parameter</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARGUMENT__PARAMETER = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARGUMENT__VALUE = 1;

  /**
   * The number of structural features of the '<em>Argument</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARGUMENT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.osate.gtse.config.config.impl.ElementRefImpl <em>Element Ref</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.osate.gtse.config.config.impl.ElementRefImpl
   * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getElementRef()
   * @generated
   */
  int ELEMENT_REF = 9;

  /**
   * The feature id for the '<em><b>Element</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_REF__ELEMENT = 0;

  /**
   * The feature id for the '<em><b>Prev</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_REF__PREV = 1;

  /**
   * The number of structural features of the '<em>Element Ref</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_REF_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.osate.gtse.config.config.impl.ConstraintImpl <em>Constraint</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.osate.gtse.config.config.impl.ConstraintImpl
   * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getConstraint()
   * @generated
   */
  int CONSTRAINT = 10;

  /**
   * The feature id for the '<em><b>Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTRAINT__CONDITION = 0;

  /**
   * The feature id for the '<em><b>Relation</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTRAINT__RELATION = 1;

  /**
   * The feature id for the '<em><b>Consequence</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTRAINT__CONSEQUENCE = 2;

  /**
   * The number of structural features of the '<em>Constraint</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTRAINT_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.osate.gtse.config.config.impl.ConditionImpl <em>Condition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.osate.gtse.config.config.impl.ConditionImpl
   * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getCondition()
   * @generated
   */
  int CONDITION = 11;

  /**
   * The feature id for the '<em><b>Lhs</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITION__LHS = 0;

  /**
   * The feature id for the '<em><b>Relation</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITION__RELATION = 1;

  /**
   * The feature id for the '<em><b>Rhs</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITION__RHS = 2;

  /**
   * The number of structural features of the '<em>Condition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITION_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.osate.gtse.config.config.impl.ConditionExpressionImpl <em>Condition Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.osate.gtse.config.config.impl.ConditionExpressionImpl
   * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getConditionExpression()
   * @generated
   */
  int CONDITION_EXPRESSION = 12;

  /**
   * The number of structural features of the '<em>Condition Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITION_EXPRESSION_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.osate.gtse.config.config.impl.SetValueImpl <em>Set Value</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.osate.gtse.config.config.impl.SetValueImpl
   * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getSetValue()
   * @generated
   */
  int SET_VALUE = 13;

  /**
   * The feature id for the '<em><b>Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_VALUE__ELEMENTS = CONDITION_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Set Value</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SET_VALUE_FEATURE_COUNT = CONDITION_EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.osate.gtse.config.config.impl.ConditionValueImpl <em>Condition Value</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.osate.gtse.config.config.impl.ConditionValueImpl
   * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getConditionValue()
   * @generated
   */
  int CONDITION_VALUE = 14;

  /**
   * The number of structural features of the '<em>Condition Value</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITION_VALUE_FEATURE_COUNT = CONDITION_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.osate.gtse.config.config.impl.ConfigElementImpl <em>Element</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.osate.gtse.config.config.impl.ConfigElementImpl
   * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getConfigElement()
   * @generated
   */
  int CONFIG_ELEMENT = 15;

  /**
   * The feature id for the '<em><b>Element</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIG_ELEMENT__ELEMENT = CONDITION_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Property</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIG_ELEMENT__PROPERTY = CONDITION_EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIG_ELEMENT_FEATURE_COUNT = CONDITION_EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.osate.gtse.config.config.impl.CandidateListImpl <em>Candidate List</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.osate.gtse.config.config.impl.CandidateListImpl
   * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getCandidateList()
   * @generated
   */
  int CANDIDATE_LIST = 16;

  /**
   * The feature id for the '<em><b>Candidates</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CANDIDATE_LIST__CANDIDATES = CONFIG_VALUE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Candidate List</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CANDIDATE_LIST_FEATURE_COUNT = CONFIG_VALUE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.osate.gtse.config.config.impl.NamedElementRefImpl <em>Named Element Ref</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.osate.gtse.config.config.impl.NamedElementRefImpl
   * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getNamedElementRef()
   * @generated
   */
  int NAMED_ELEMENT_REF = 17;

  /**
   * The feature id for the '<em><b>Ref</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_ELEMENT_REF__REF = CONFIG_VALUE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_ELEMENT_REF__ARGUMENTS = CONFIG_VALUE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Combined</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_ELEMENT_REF__COMBINED = CONFIG_VALUE_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Assignments</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_ELEMENT_REF__ASSIGNMENTS = CONFIG_VALUE_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_ELEMENT_REF__CONSTRAINTS = CONFIG_VALUE_FEATURE_COUNT + 4;

  /**
   * The number of structural features of the '<em>Named Element Ref</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_ELEMENT_REF_FEATURE_COUNT = CONFIG_VALUE_FEATURE_COUNT + 5;

  /**
   * The meta object id for the '{@link org.osate.gtse.config.config.impl.PropertyValueImpl <em>Property Value</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.osate.gtse.config.config.impl.PropertyValueImpl
   * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getPropertyValue()
   * @generated
   */
  int PROPERTY_VALUE = 18;

  /**
   * The feature id for the '<em><b>Exp</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_VALUE__EXP = CONFIG_VALUE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Property Value</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_VALUE_FEATURE_COUNT = CONFIG_VALUE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.osate.gtse.config.config.impl.NestedAssignmentsImpl <em>Nested Assignments</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.osate.gtse.config.config.impl.NestedAssignmentsImpl
   * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getNestedAssignments()
   * @generated
   */
  int NESTED_ASSIGNMENTS = 19;

  /**
   * The feature id for the '<em><b>Assignments</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NESTED_ASSIGNMENTS__ASSIGNMENTS = CONFIG_VALUE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NESTED_ASSIGNMENTS__CONSTRAINTS = CONFIG_VALUE_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Nested Assignments</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NESTED_ASSIGNMENTS_FEATURE_COUNT = CONFIG_VALUE_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.osate.gtse.config.config.Type <em>Type</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.osate.gtse.config.config.Type
   * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getType()
   * @generated
   */
  int TYPE = 20;

  /**
   * The meta object id for the '{@link org.osate.gtse.config.config.Relation <em>Relation</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.osate.gtse.config.config.Relation
   * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getRelation()
   * @generated
   */
  int RELATION = 21;


  /**
   * Returns the meta object for class '{@link org.osate.gtse.config.config.ConfigPkg <em>Pkg</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Pkg</em>'.
   * @see org.osate.gtse.config.config.ConfigPkg
   * @generated
   */
  EClass getConfigPkg();

  /**
   * Returns the meta object for the reference '{@link org.osate.gtse.config.config.ConfigPkg#getRoot <em>Root</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Root</em>'.
   * @see org.osate.gtse.config.config.ConfigPkg#getRoot()
   * @see #getConfigPkg()
   * @generated
   */
  EReference getConfigPkg_Root();

  /**
   * Returns the meta object for the containment reference list '{@link org.osate.gtse.config.config.ConfigPkg#getConfigurations <em>Configurations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Configurations</em>'.
   * @see org.osate.gtse.config.config.ConfigPkg#getConfigurations()
   * @see #getConfigPkg()
   * @generated
   */
  EReference getConfigPkg_Configurations();

  /**
   * Returns the meta object for the attribute list '{@link org.osate.gtse.config.config.ConfigPkg#getAnalyses <em>Analyses</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Analyses</em>'.
   * @see org.osate.gtse.config.config.ConfigPkg#getAnalyses()
   * @see #getConfigPkg()
   * @generated
   */
  EAttribute getConfigPkg_Analyses();

  /**
   * Returns the meta object for the containment reference list '{@link org.osate.gtse.config.config.ConfigPkg#getOutputs <em>Outputs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Outputs</em>'.
   * @see org.osate.gtse.config.config.ConfigPkg#getOutputs()
   * @see #getConfigPkg()
   * @generated
   */
  EReference getConfigPkg_Outputs();

  /**
   * Returns the meta object for class '{@link org.osate.gtse.config.config.OutputVariable <em>Output Variable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Output Variable</em>'.
   * @see org.osate.gtse.config.config.OutputVariable
   * @generated
   */
  EClass getOutputVariable();

  /**
   * Returns the meta object for the attribute '{@link org.osate.gtse.config.config.OutputVariable#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Type</em>'.
   * @see org.osate.gtse.config.config.OutputVariable#getType()
   * @see #getOutputVariable()
   * @generated
   */
  EAttribute getOutputVariable_Type();

  /**
   * Returns the meta object for the containment reference '{@link org.osate.gtse.config.config.OutputVariable#getLimit <em>Limit</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Limit</em>'.
   * @see org.osate.gtse.config.config.OutputVariable#getLimit()
   * @see #getOutputVariable()
   * @generated
   */
  EReference getOutputVariable_Limit();

  /**
   * Returns the meta object for class '{@link org.osate.gtse.config.config.Limit <em>Limit</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Limit</em>'.
   * @see org.osate.gtse.config.config.Limit
   * @generated
   */
  EClass getLimit();

  /**
   * Returns the meta object for the attribute '{@link org.osate.gtse.config.config.Limit#getRelation <em>Relation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Relation</em>'.
   * @see org.osate.gtse.config.config.Limit#getRelation()
   * @see #getLimit()
   * @generated
   */
  EAttribute getLimit_Relation();

  /**
   * Returns the meta object for the containment reference '{@link org.osate.gtse.config.config.Limit#getBound <em>Bound</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Bound</em>'.
   * @see org.osate.gtse.config.config.Limit#getBound()
   * @see #getLimit()
   * @generated
   */
  EReference getLimit_Bound();

  /**
   * Returns the meta object for class '{@link org.osate.gtse.config.config.Configuration <em>Configuration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Configuration</em>'.
   * @see org.osate.gtse.config.config.Configuration
   * @generated
   */
  EClass getConfiguration();

  /**
   * Returns the meta object for the containment reference list '{@link org.osate.gtse.config.config.Configuration#getParameters <em>Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Parameters</em>'.
   * @see org.osate.gtse.config.config.Configuration#getParameters()
   * @see #getConfiguration()
   * @generated
   */
  EReference getConfiguration_Parameters();

  /**
   * Returns the meta object for the reference '{@link org.osate.gtse.config.config.Configuration#getExtended <em>Extended</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Extended</em>'.
   * @see org.osate.gtse.config.config.Configuration#getExtended()
   * @see #getConfiguration()
   * @generated
   */
  EReference getConfiguration_Extended();

  /**
   * Returns the meta object for the containment reference list '{@link org.osate.gtse.config.config.Configuration#getCombined <em>Combined</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Combined</em>'.
   * @see org.osate.gtse.config.config.Configuration#getCombined()
   * @see #getConfiguration()
   * @generated
   */
  EReference getConfiguration_Combined();

  /**
   * Returns the meta object for the containment reference list '{@link org.osate.gtse.config.config.Configuration#getAssignments <em>Assignments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Assignments</em>'.
   * @see org.osate.gtse.config.config.Configuration#getAssignments()
   * @see #getConfiguration()
   * @generated
   */
  EReference getConfiguration_Assignments();

  /**
   * Returns the meta object for the containment reference list '{@link org.osate.gtse.config.config.Configuration#getConstraints <em>Constraints</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Constraints</em>'.
   * @see org.osate.gtse.config.config.Configuration#getConstraints()
   * @see #getConfiguration()
   * @generated
   */
  EReference getConfiguration_Constraints();

  /**
   * Returns the meta object for class '{@link org.osate.gtse.config.config.Combination <em>Combination</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Combination</em>'.
   * @see org.osate.gtse.config.config.Combination
   * @generated
   */
  EClass getCombination();

  /**
   * Returns the meta object for the attribute '{@link org.osate.gtse.config.config.Combination#isUnsafe <em>Unsafe</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Unsafe</em>'.
   * @see org.osate.gtse.config.config.Combination#isUnsafe()
   * @see #getCombination()
   * @generated
   */
  EAttribute getCombination_Unsafe();

  /**
   * Returns the meta object for the reference '{@link org.osate.gtse.config.config.Combination#getConfiguration <em>Configuration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Configuration</em>'.
   * @see org.osate.gtse.config.config.Combination#getConfiguration()
   * @see #getCombination()
   * @generated
   */
  EReference getCombination_Configuration();

  /**
   * Returns the meta object for the containment reference list '{@link org.osate.gtse.config.config.Combination#getArguments <em>Arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Arguments</em>'.
   * @see org.osate.gtse.config.config.Combination#getArguments()
   * @see #getCombination()
   * @generated
   */
  EReference getCombination_Arguments();

  /**
   * Returns the meta object for class '{@link org.osate.gtse.config.config.ConfigParameter <em>Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Parameter</em>'.
   * @see org.osate.gtse.config.config.ConfigParameter
   * @generated
   */
  EClass getConfigParameter();

  /**
   * Returns the meta object for the attribute '{@link org.osate.gtse.config.config.ConfigParameter#getCategory <em>Category</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Category</em>'.
   * @see org.osate.gtse.config.config.ConfigParameter#getCategory()
   * @see #getConfigParameter()
   * @generated
   */
  EAttribute getConfigParameter_Category();

  /**
   * Returns the meta object for the reference '{@link org.osate.gtse.config.config.ConfigParameter#getClassifier <em>Classifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Classifier</em>'.
   * @see org.osate.gtse.config.config.ConfigParameter#getClassifier()
   * @see #getConfigParameter()
   * @generated
   */
  EReference getConfigParameter_Classifier();

  /**
   * Returns the meta object for the reference '{@link org.osate.gtse.config.config.ConfigParameter#getPropertyType <em>Property Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Property Type</em>'.
   * @see org.osate.gtse.config.config.ConfigParameter#getPropertyType()
   * @see #getConfigParameter()
   * @generated
   */
  EReference getConfigParameter_PropertyType();

  /**
   * Returns the meta object for the containment reference '{@link org.osate.gtse.config.config.ConfigParameter#getChoices <em>Choices</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Choices</em>'.
   * @see org.osate.gtse.config.config.ConfigParameter#getChoices()
   * @see #getConfigParameter()
   * @generated
   */
  EReference getConfigParameter_Choices();

  /**
   * Returns the meta object for class '{@link org.osate.gtse.config.config.ConfigValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Value</em>'.
   * @see org.osate.gtse.config.config.ConfigValue
   * @generated
   */
  EClass getConfigValue();

  /**
   * Returns the meta object for class '{@link org.osate.gtse.config.config.Assignment <em>Assignment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Assignment</em>'.
   * @see org.osate.gtse.config.config.Assignment
   * @generated
   */
  EClass getAssignment();

  /**
   * Returns the meta object for the attribute '{@link org.osate.gtse.config.config.Assignment#isWildcard <em>Wildcard</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Wildcard</em>'.
   * @see org.osate.gtse.config.config.Assignment#isWildcard()
   * @see #getAssignment()
   * @generated
   */
  EAttribute getAssignment_Wildcard();

  /**
   * Returns the meta object for the containment reference '{@link org.osate.gtse.config.config.Assignment#getRef <em>Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Ref</em>'.
   * @see org.osate.gtse.config.config.Assignment#getRef()
   * @see #getAssignment()
   * @generated
   */
  EReference getAssignment_Ref();

  /**
   * Returns the meta object for the reference '{@link org.osate.gtse.config.config.Assignment#getProperty <em>Property</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Property</em>'.
   * @see org.osate.gtse.config.config.Assignment#getProperty()
   * @see #getAssignment()
   * @generated
   */
  EReference getAssignment_Property();

  /**
   * Returns the meta object for the containment reference '{@link org.osate.gtse.config.config.Assignment#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value</em>'.
   * @see org.osate.gtse.config.config.Assignment#getValue()
   * @see #getAssignment()
   * @generated
   */
  EReference getAssignment_Value();

  /**
   * Returns the meta object for class '{@link org.osate.gtse.config.config.Argument <em>Argument</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Argument</em>'.
   * @see org.osate.gtse.config.config.Argument
   * @generated
   */
  EClass getArgument();

  /**
   * Returns the meta object for the reference '{@link org.osate.gtse.config.config.Argument#getParameter <em>Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Parameter</em>'.
   * @see org.osate.gtse.config.config.Argument#getParameter()
   * @see #getArgument()
   * @generated
   */
  EReference getArgument_Parameter();

  /**
   * Returns the meta object for the containment reference '{@link org.osate.gtse.config.config.Argument#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value</em>'.
   * @see org.osate.gtse.config.config.Argument#getValue()
   * @see #getArgument()
   * @generated
   */
  EReference getArgument_Value();

  /**
   * Returns the meta object for class '{@link org.osate.gtse.config.config.ElementRef <em>Element Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Element Ref</em>'.
   * @see org.osate.gtse.config.config.ElementRef
   * @generated
   */
  EClass getElementRef();

  /**
   * Returns the meta object for the reference '{@link org.osate.gtse.config.config.ElementRef#getElement <em>Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Element</em>'.
   * @see org.osate.gtse.config.config.ElementRef#getElement()
   * @see #getElementRef()
   * @generated
   */
  EReference getElementRef_Element();

  /**
   * Returns the meta object for the containment reference '{@link org.osate.gtse.config.config.ElementRef#getPrev <em>Prev</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Prev</em>'.
   * @see org.osate.gtse.config.config.ElementRef#getPrev()
   * @see #getElementRef()
   * @generated
   */
  EReference getElementRef_Prev();

  /**
   * Returns the meta object for class '{@link org.osate.gtse.config.config.Constraint <em>Constraint</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Constraint</em>'.
   * @see org.osate.gtse.config.config.Constraint
   * @generated
   */
  EClass getConstraint();

  /**
   * Returns the meta object for the containment reference '{@link org.osate.gtse.config.config.Constraint#getCondition <em>Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Condition</em>'.
   * @see org.osate.gtse.config.config.Constraint#getCondition()
   * @see #getConstraint()
   * @generated
   */
  EReference getConstraint_Condition();

  /**
   * Returns the meta object for the attribute '{@link org.osate.gtse.config.config.Constraint#getRelation <em>Relation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Relation</em>'.
   * @see org.osate.gtse.config.config.Constraint#getRelation()
   * @see #getConstraint()
   * @generated
   */
  EAttribute getConstraint_Relation();

  /**
   * Returns the meta object for the containment reference '{@link org.osate.gtse.config.config.Constraint#getConsequence <em>Consequence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Consequence</em>'.
   * @see org.osate.gtse.config.config.Constraint#getConsequence()
   * @see #getConstraint()
   * @generated
   */
  EReference getConstraint_Consequence();

  /**
   * Returns the meta object for class '{@link org.osate.gtse.config.config.Condition <em>Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Condition</em>'.
   * @see org.osate.gtse.config.config.Condition
   * @generated
   */
  EClass getCondition();

  /**
   * Returns the meta object for the containment reference '{@link org.osate.gtse.config.config.Condition#getLhs <em>Lhs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Lhs</em>'.
   * @see org.osate.gtse.config.config.Condition#getLhs()
   * @see #getCondition()
   * @generated
   */
  EReference getCondition_Lhs();

  /**
   * Returns the meta object for the attribute '{@link org.osate.gtse.config.config.Condition#getRelation <em>Relation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Relation</em>'.
   * @see org.osate.gtse.config.config.Condition#getRelation()
   * @see #getCondition()
   * @generated
   */
  EAttribute getCondition_Relation();

  /**
   * Returns the meta object for the containment reference '{@link org.osate.gtse.config.config.Condition#getRhs <em>Rhs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Rhs</em>'.
   * @see org.osate.gtse.config.config.Condition#getRhs()
   * @see #getCondition()
   * @generated
   */
  EReference getCondition_Rhs();

  /**
   * Returns the meta object for class '{@link org.osate.gtse.config.config.ConditionExpression <em>Condition Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Condition Expression</em>'.
   * @see org.osate.gtse.config.config.ConditionExpression
   * @generated
   */
  EClass getConditionExpression();

  /**
   * Returns the meta object for class '{@link org.osate.gtse.config.config.SetValue <em>Set Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Set Value</em>'.
   * @see org.osate.gtse.config.config.SetValue
   * @generated
   */
  EClass getSetValue();

  /**
   * Returns the meta object for the containment reference list '{@link org.osate.gtse.config.config.SetValue#getElements <em>Elements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Elements</em>'.
   * @see org.osate.gtse.config.config.SetValue#getElements()
   * @see #getSetValue()
   * @generated
   */
  EReference getSetValue_Elements();

  /**
   * Returns the meta object for class '{@link org.osate.gtse.config.config.ConditionValue <em>Condition Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Condition Value</em>'.
   * @see org.osate.gtse.config.config.ConditionValue
   * @generated
   */
  EClass getConditionValue();

  /**
   * Returns the meta object for class '{@link org.osate.gtse.config.config.ConfigElement <em>Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Element</em>'.
   * @see org.osate.gtse.config.config.ConfigElement
   * @generated
   */
  EClass getConfigElement();

  /**
   * Returns the meta object for the containment reference '{@link org.osate.gtse.config.config.ConfigElement#getElement <em>Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Element</em>'.
   * @see org.osate.gtse.config.config.ConfigElement#getElement()
   * @see #getConfigElement()
   * @generated
   */
  EReference getConfigElement_Element();

  /**
   * Returns the meta object for the reference '{@link org.osate.gtse.config.config.ConfigElement#getProperty <em>Property</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Property</em>'.
   * @see org.osate.gtse.config.config.ConfigElement#getProperty()
   * @see #getConfigElement()
   * @generated
   */
  EReference getConfigElement_Property();

  /**
   * Returns the meta object for class '{@link org.osate.gtse.config.config.CandidateList <em>Candidate List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Candidate List</em>'.
   * @see org.osate.gtse.config.config.CandidateList
   * @generated
   */
  EClass getCandidateList();

  /**
   * Returns the meta object for the containment reference list '{@link org.osate.gtse.config.config.CandidateList#getCandidates <em>Candidates</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Candidates</em>'.
   * @see org.osate.gtse.config.config.CandidateList#getCandidates()
   * @see #getCandidateList()
   * @generated
   */
  EReference getCandidateList_Candidates();

  /**
   * Returns the meta object for class '{@link org.osate.gtse.config.config.NamedElementRef <em>Named Element Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Named Element Ref</em>'.
   * @see org.osate.gtse.config.config.NamedElementRef
   * @generated
   */
  EClass getNamedElementRef();

  /**
   * Returns the meta object for the reference '{@link org.osate.gtse.config.config.NamedElementRef#getRef <em>Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Ref</em>'.
   * @see org.osate.gtse.config.config.NamedElementRef#getRef()
   * @see #getNamedElementRef()
   * @generated
   */
  EReference getNamedElementRef_Ref();

  /**
   * Returns the meta object for the containment reference list '{@link org.osate.gtse.config.config.NamedElementRef#getArguments <em>Arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Arguments</em>'.
   * @see org.osate.gtse.config.config.NamedElementRef#getArguments()
   * @see #getNamedElementRef()
   * @generated
   */
  EReference getNamedElementRef_Arguments();

  /**
   * Returns the meta object for the containment reference list '{@link org.osate.gtse.config.config.NamedElementRef#getCombined <em>Combined</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Combined</em>'.
   * @see org.osate.gtse.config.config.NamedElementRef#getCombined()
   * @see #getNamedElementRef()
   * @generated
   */
  EReference getNamedElementRef_Combined();

  /**
   * Returns the meta object for the containment reference list '{@link org.osate.gtse.config.config.NamedElementRef#getAssignments <em>Assignments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Assignments</em>'.
   * @see org.osate.gtse.config.config.NamedElementRef#getAssignments()
   * @see #getNamedElementRef()
   * @generated
   */
  EReference getNamedElementRef_Assignments();

  /**
   * Returns the meta object for the containment reference list '{@link org.osate.gtse.config.config.NamedElementRef#getConstraints <em>Constraints</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Constraints</em>'.
   * @see org.osate.gtse.config.config.NamedElementRef#getConstraints()
   * @see #getNamedElementRef()
   * @generated
   */
  EReference getNamedElementRef_Constraints();

  /**
   * Returns the meta object for class '{@link org.osate.gtse.config.config.PropertyValue <em>Property Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Property Value</em>'.
   * @see org.osate.gtse.config.config.PropertyValue
   * @generated
   */
  EClass getPropertyValue();

  /**
   * Returns the meta object for the containment reference '{@link org.osate.gtse.config.config.PropertyValue#getExp <em>Exp</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Exp</em>'.
   * @see org.osate.gtse.config.config.PropertyValue#getExp()
   * @see #getPropertyValue()
   * @generated
   */
  EReference getPropertyValue_Exp();

  /**
   * Returns the meta object for class '{@link org.osate.gtse.config.config.NestedAssignments <em>Nested Assignments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Nested Assignments</em>'.
   * @see org.osate.gtse.config.config.NestedAssignments
   * @generated
   */
  EClass getNestedAssignments();

  /**
   * Returns the meta object for the containment reference list '{@link org.osate.gtse.config.config.NestedAssignments#getAssignments <em>Assignments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Assignments</em>'.
   * @see org.osate.gtse.config.config.NestedAssignments#getAssignments()
   * @see #getNestedAssignments()
   * @generated
   */
  EReference getNestedAssignments_Assignments();

  /**
   * Returns the meta object for the containment reference list '{@link org.osate.gtse.config.config.NestedAssignments#getConstraints <em>Constraints</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Constraints</em>'.
   * @see org.osate.gtse.config.config.NestedAssignments#getConstraints()
   * @see #getNestedAssignments()
   * @generated
   */
  EReference getNestedAssignments_Constraints();

  /**
   * Returns the meta object for enum '{@link org.osate.gtse.config.config.Type <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Type</em>'.
   * @see org.osate.gtse.config.config.Type
   * @generated
   */
  EEnum getType();

  /**
   * Returns the meta object for enum '{@link org.osate.gtse.config.config.Relation <em>Relation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Relation</em>'.
   * @see org.osate.gtse.config.config.Relation
   * @generated
   */
  EEnum getRelation();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  ConfigFactory getConfigFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link org.osate.gtse.config.config.impl.ConfigPkgImpl <em>Pkg</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.osate.gtse.config.config.impl.ConfigPkgImpl
     * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getConfigPkg()
     * @generated
     */
    EClass CONFIG_PKG = eINSTANCE.getConfigPkg();

    /**
     * The meta object literal for the '<em><b>Root</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONFIG_PKG__ROOT = eINSTANCE.getConfigPkg_Root();

    /**
     * The meta object literal for the '<em><b>Configurations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONFIG_PKG__CONFIGURATIONS = eINSTANCE.getConfigPkg_Configurations();

    /**
     * The meta object literal for the '<em><b>Analyses</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONFIG_PKG__ANALYSES = eINSTANCE.getConfigPkg_Analyses();

    /**
     * The meta object literal for the '<em><b>Outputs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONFIG_PKG__OUTPUTS = eINSTANCE.getConfigPkg_Outputs();

    /**
     * The meta object literal for the '{@link org.osate.gtse.config.config.impl.OutputVariableImpl <em>Output Variable</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.osate.gtse.config.config.impl.OutputVariableImpl
     * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getOutputVariable()
     * @generated
     */
    EClass OUTPUT_VARIABLE = eINSTANCE.getOutputVariable();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute OUTPUT_VARIABLE__TYPE = eINSTANCE.getOutputVariable_Type();

    /**
     * The meta object literal for the '<em><b>Limit</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OUTPUT_VARIABLE__LIMIT = eINSTANCE.getOutputVariable_Limit();

    /**
     * The meta object literal for the '{@link org.osate.gtse.config.config.impl.LimitImpl <em>Limit</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.osate.gtse.config.config.impl.LimitImpl
     * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getLimit()
     * @generated
     */
    EClass LIMIT = eINSTANCE.getLimit();

    /**
     * The meta object literal for the '<em><b>Relation</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LIMIT__RELATION = eINSTANCE.getLimit_Relation();

    /**
     * The meta object literal for the '<em><b>Bound</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LIMIT__BOUND = eINSTANCE.getLimit_Bound();

    /**
     * The meta object literal for the '{@link org.osate.gtse.config.config.impl.ConfigurationImpl <em>Configuration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.osate.gtse.config.config.impl.ConfigurationImpl
     * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getConfiguration()
     * @generated
     */
    EClass CONFIGURATION = eINSTANCE.getConfiguration();

    /**
     * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONFIGURATION__PARAMETERS = eINSTANCE.getConfiguration_Parameters();

    /**
     * The meta object literal for the '<em><b>Extended</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONFIGURATION__EXTENDED = eINSTANCE.getConfiguration_Extended();

    /**
     * The meta object literal for the '<em><b>Combined</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONFIGURATION__COMBINED = eINSTANCE.getConfiguration_Combined();

    /**
     * The meta object literal for the '<em><b>Assignments</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONFIGURATION__ASSIGNMENTS = eINSTANCE.getConfiguration_Assignments();

    /**
     * The meta object literal for the '<em><b>Constraints</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONFIGURATION__CONSTRAINTS = eINSTANCE.getConfiguration_Constraints();

    /**
     * The meta object literal for the '{@link org.osate.gtse.config.config.impl.CombinationImpl <em>Combination</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.osate.gtse.config.config.impl.CombinationImpl
     * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getCombination()
     * @generated
     */
    EClass COMBINATION = eINSTANCE.getCombination();

    /**
     * The meta object literal for the '<em><b>Unsafe</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute COMBINATION__UNSAFE = eINSTANCE.getCombination_Unsafe();

    /**
     * The meta object literal for the '<em><b>Configuration</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMBINATION__CONFIGURATION = eINSTANCE.getCombination_Configuration();

    /**
     * The meta object literal for the '<em><b>Arguments</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COMBINATION__ARGUMENTS = eINSTANCE.getCombination_Arguments();

    /**
     * The meta object literal for the '{@link org.osate.gtse.config.config.impl.ConfigParameterImpl <em>Parameter</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.osate.gtse.config.config.impl.ConfigParameterImpl
     * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getConfigParameter()
     * @generated
     */
    EClass CONFIG_PARAMETER = eINSTANCE.getConfigParameter();

    /**
     * The meta object literal for the '<em><b>Category</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONFIG_PARAMETER__CATEGORY = eINSTANCE.getConfigParameter_Category();

    /**
     * The meta object literal for the '<em><b>Classifier</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONFIG_PARAMETER__CLASSIFIER = eINSTANCE.getConfigParameter_Classifier();

    /**
     * The meta object literal for the '<em><b>Property Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONFIG_PARAMETER__PROPERTY_TYPE = eINSTANCE.getConfigParameter_PropertyType();

    /**
     * The meta object literal for the '<em><b>Choices</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONFIG_PARAMETER__CHOICES = eINSTANCE.getConfigParameter_Choices();

    /**
     * The meta object literal for the '{@link org.osate.gtse.config.config.impl.ConfigValueImpl <em>Value</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.osate.gtse.config.config.impl.ConfigValueImpl
     * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getConfigValue()
     * @generated
     */
    EClass CONFIG_VALUE = eINSTANCE.getConfigValue();

    /**
     * The meta object literal for the '{@link org.osate.gtse.config.config.impl.AssignmentImpl <em>Assignment</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.osate.gtse.config.config.impl.AssignmentImpl
     * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getAssignment()
     * @generated
     */
    EClass ASSIGNMENT = eINSTANCE.getAssignment();

    /**
     * The meta object literal for the '<em><b>Wildcard</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ASSIGNMENT__WILDCARD = eINSTANCE.getAssignment_Wildcard();

    /**
     * The meta object literal for the '<em><b>Ref</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ASSIGNMENT__REF = eINSTANCE.getAssignment_Ref();

    /**
     * The meta object literal for the '<em><b>Property</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ASSIGNMENT__PROPERTY = eINSTANCE.getAssignment_Property();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ASSIGNMENT__VALUE = eINSTANCE.getAssignment_Value();

    /**
     * The meta object literal for the '{@link org.osate.gtse.config.config.impl.ArgumentImpl <em>Argument</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.osate.gtse.config.config.impl.ArgumentImpl
     * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getArgument()
     * @generated
     */
    EClass ARGUMENT = eINSTANCE.getArgument();

    /**
     * The meta object literal for the '<em><b>Parameter</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ARGUMENT__PARAMETER = eINSTANCE.getArgument_Parameter();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ARGUMENT__VALUE = eINSTANCE.getArgument_Value();

    /**
     * The meta object literal for the '{@link org.osate.gtse.config.config.impl.ElementRefImpl <em>Element Ref</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.osate.gtse.config.config.impl.ElementRefImpl
     * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getElementRef()
     * @generated
     */
    EClass ELEMENT_REF = eINSTANCE.getElementRef();

    /**
     * The meta object literal for the '<em><b>Element</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELEMENT_REF__ELEMENT = eINSTANCE.getElementRef_Element();

    /**
     * The meta object literal for the '<em><b>Prev</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ELEMENT_REF__PREV = eINSTANCE.getElementRef_Prev();

    /**
     * The meta object literal for the '{@link org.osate.gtse.config.config.impl.ConstraintImpl <em>Constraint</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.osate.gtse.config.config.impl.ConstraintImpl
     * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getConstraint()
     * @generated
     */
    EClass CONSTRAINT = eINSTANCE.getConstraint();

    /**
     * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONSTRAINT__CONDITION = eINSTANCE.getConstraint_Condition();

    /**
     * The meta object literal for the '<em><b>Relation</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONSTRAINT__RELATION = eINSTANCE.getConstraint_Relation();

    /**
     * The meta object literal for the '<em><b>Consequence</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONSTRAINT__CONSEQUENCE = eINSTANCE.getConstraint_Consequence();

    /**
     * The meta object literal for the '{@link org.osate.gtse.config.config.impl.ConditionImpl <em>Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.osate.gtse.config.config.impl.ConditionImpl
     * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getCondition()
     * @generated
     */
    EClass CONDITION = eINSTANCE.getCondition();

    /**
     * The meta object literal for the '<em><b>Lhs</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITION__LHS = eINSTANCE.getCondition_Lhs();

    /**
     * The meta object literal for the '<em><b>Relation</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONDITION__RELATION = eINSTANCE.getCondition_Relation();

    /**
     * The meta object literal for the '<em><b>Rhs</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITION__RHS = eINSTANCE.getCondition_Rhs();

    /**
     * The meta object literal for the '{@link org.osate.gtse.config.config.impl.ConditionExpressionImpl <em>Condition Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.osate.gtse.config.config.impl.ConditionExpressionImpl
     * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getConditionExpression()
     * @generated
     */
    EClass CONDITION_EXPRESSION = eINSTANCE.getConditionExpression();

    /**
     * The meta object literal for the '{@link org.osate.gtse.config.config.impl.SetValueImpl <em>Set Value</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.osate.gtse.config.config.impl.SetValueImpl
     * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getSetValue()
     * @generated
     */
    EClass SET_VALUE = eINSTANCE.getSetValue();

    /**
     * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SET_VALUE__ELEMENTS = eINSTANCE.getSetValue_Elements();

    /**
     * The meta object literal for the '{@link org.osate.gtse.config.config.impl.ConditionValueImpl <em>Condition Value</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.osate.gtse.config.config.impl.ConditionValueImpl
     * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getConditionValue()
     * @generated
     */
    EClass CONDITION_VALUE = eINSTANCE.getConditionValue();

    /**
     * The meta object literal for the '{@link org.osate.gtse.config.config.impl.ConfigElementImpl <em>Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.osate.gtse.config.config.impl.ConfigElementImpl
     * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getConfigElement()
     * @generated
     */
    EClass CONFIG_ELEMENT = eINSTANCE.getConfigElement();

    /**
     * The meta object literal for the '<em><b>Element</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONFIG_ELEMENT__ELEMENT = eINSTANCE.getConfigElement_Element();

    /**
     * The meta object literal for the '<em><b>Property</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONFIG_ELEMENT__PROPERTY = eINSTANCE.getConfigElement_Property();

    /**
     * The meta object literal for the '{@link org.osate.gtse.config.config.impl.CandidateListImpl <em>Candidate List</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.osate.gtse.config.config.impl.CandidateListImpl
     * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getCandidateList()
     * @generated
     */
    EClass CANDIDATE_LIST = eINSTANCE.getCandidateList();

    /**
     * The meta object literal for the '<em><b>Candidates</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CANDIDATE_LIST__CANDIDATES = eINSTANCE.getCandidateList_Candidates();

    /**
     * The meta object literal for the '{@link org.osate.gtse.config.config.impl.NamedElementRefImpl <em>Named Element Ref</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.osate.gtse.config.config.impl.NamedElementRefImpl
     * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getNamedElementRef()
     * @generated
     */
    EClass NAMED_ELEMENT_REF = eINSTANCE.getNamedElementRef();

    /**
     * The meta object literal for the '<em><b>Ref</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NAMED_ELEMENT_REF__REF = eINSTANCE.getNamedElementRef_Ref();

    /**
     * The meta object literal for the '<em><b>Arguments</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NAMED_ELEMENT_REF__ARGUMENTS = eINSTANCE.getNamedElementRef_Arguments();

    /**
     * The meta object literal for the '<em><b>Combined</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NAMED_ELEMENT_REF__COMBINED = eINSTANCE.getNamedElementRef_Combined();

    /**
     * The meta object literal for the '<em><b>Assignments</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NAMED_ELEMENT_REF__ASSIGNMENTS = eINSTANCE.getNamedElementRef_Assignments();

    /**
     * The meta object literal for the '<em><b>Constraints</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NAMED_ELEMENT_REF__CONSTRAINTS = eINSTANCE.getNamedElementRef_Constraints();

    /**
     * The meta object literal for the '{@link org.osate.gtse.config.config.impl.PropertyValueImpl <em>Property Value</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.osate.gtse.config.config.impl.PropertyValueImpl
     * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getPropertyValue()
     * @generated
     */
    EClass PROPERTY_VALUE = eINSTANCE.getPropertyValue();

    /**
     * The meta object literal for the '<em><b>Exp</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PROPERTY_VALUE__EXP = eINSTANCE.getPropertyValue_Exp();

    /**
     * The meta object literal for the '{@link org.osate.gtse.config.config.impl.NestedAssignmentsImpl <em>Nested Assignments</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.osate.gtse.config.config.impl.NestedAssignmentsImpl
     * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getNestedAssignments()
     * @generated
     */
    EClass NESTED_ASSIGNMENTS = eINSTANCE.getNestedAssignments();

    /**
     * The meta object literal for the '<em><b>Assignments</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NESTED_ASSIGNMENTS__ASSIGNMENTS = eINSTANCE.getNestedAssignments_Assignments();

    /**
     * The meta object literal for the '<em><b>Constraints</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NESTED_ASSIGNMENTS__CONSTRAINTS = eINSTANCE.getNestedAssignments_Constraints();

    /**
     * The meta object literal for the '{@link org.osate.gtse.config.config.Type <em>Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.osate.gtse.config.config.Type
     * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getType()
     * @generated
     */
    EEnum TYPE = eINSTANCE.getType();

    /**
     * The meta object literal for the '{@link org.osate.gtse.config.config.Relation <em>Relation</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.osate.gtse.config.config.Relation
     * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getRelation()
     * @generated
     */
    EEnum RELATION = eINSTANCE.getRelation();

  }

} //ConfigPackage
