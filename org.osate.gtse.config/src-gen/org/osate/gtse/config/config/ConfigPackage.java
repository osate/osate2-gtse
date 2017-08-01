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
   * The number of structural features of the '<em>Pkg</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIG_PKG_FEATURE_COUNT = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.osate.gtse.config.config.impl.ConfigurationImpl <em>Configuration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.osate.gtse.config.config.impl.ConfigurationImpl
   * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getConfiguration()
   * @generated
   */
  int CONFIGURATION = 1;

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
   * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIGURATION__EXTENSIONS = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIGURATION__ARGUMENTS = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Assignments</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIGURATION__ASSIGNMENTS = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Configuration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIGURATION_FEATURE_COUNT = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link org.osate.gtse.config.config.impl.ExtensionImpl <em>Extension</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.osate.gtse.config.config.impl.ExtensionImpl
   * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getExtension()
   * @generated
   */
  int EXTENSION = 2;

  /**
   * The feature id for the '<em><b>Unsafe</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENSION__UNSAFE = 0;

  /**
   * The feature id for the '<em><b>Extended</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENSION__EXTENDED = 1;

  /**
   * The number of structural features of the '<em>Extension</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXTENSION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.osate.gtse.config.config.impl.ConfigParameterImpl <em>Parameter</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.osate.gtse.config.config.impl.ConfigParameterImpl
   * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getConfigParameter()
   * @generated
   */
  int CONFIG_PARAMETER = 3;

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
   * The feature id for the '<em><b>Candidates</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIG_PARAMETER__CANDIDATES = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Parameter</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIG_PARAMETER_FEATURE_COUNT = Aadl2Package.NAMED_ELEMENT_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link org.osate.gtse.config.config.impl.AssignmentImpl <em>Assignment</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.osate.gtse.config.config.impl.AssignmentImpl
   * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getAssignment()
   * @generated
   */
  int ASSIGNMENT = 4;

  /**
   * The feature id for the '<em><b>Element</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSIGNMENT__ELEMENT = 0;

  /**
   * The feature id for the '<em><b>Property</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSIGNMENT__PROPERTY = 1;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSIGNMENT__VALUE = 2;

  /**
   * The number of structural features of the '<em>Assignment</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSIGNMENT_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.osate.gtse.config.config.impl.ConfigValueImpl <em>Value</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.osate.gtse.config.config.impl.ConfigValueImpl
   * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getConfigValue()
   * @generated
   */
  int CONFIG_VALUE = 5;

  /**
   * The number of structural features of the '<em>Value</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONFIG_VALUE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.osate.gtse.config.config.impl.ArgumentImpl <em>Argument</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.osate.gtse.config.config.impl.ArgumentImpl
   * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getArgument()
   * @generated
   */
  int ARGUMENT = 6;

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
  int ELEMENT_REF = 7;

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
   * The meta object id for the '{@link org.osate.gtse.config.config.impl.NamedElementRefImpl <em>Named Element Ref</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.osate.gtse.config.config.impl.NamedElementRefImpl
   * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getNamedElementRef()
   * @generated
   */
  int NAMED_ELEMENT_REF = 8;

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
   * The feature id for the '<em><b>Assignments</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_ELEMENT_REF__ASSIGNMENTS = CONFIG_VALUE_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Named Element Ref</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMED_ELEMENT_REF_FEATURE_COUNT = CONFIG_VALUE_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.osate.gtse.config.config.impl.PropertyValueImpl <em>Property Value</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.osate.gtse.config.config.impl.PropertyValueImpl
   * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getPropertyValue()
   * @generated
   */
  int PROPERTY_VALUE = 9;

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
   * Returns the meta object for the containment reference list '{@link org.osate.gtse.config.config.Configuration#getExtensions <em>Extensions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Extensions</em>'.
   * @see org.osate.gtse.config.config.Configuration#getExtensions()
   * @see #getConfiguration()
   * @generated
   */
  EReference getConfiguration_Extensions();

  /**
   * Returns the meta object for the containment reference list '{@link org.osate.gtse.config.config.Configuration#getArguments <em>Arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Arguments</em>'.
   * @see org.osate.gtse.config.config.Configuration#getArguments()
   * @see #getConfiguration()
   * @generated
   */
  EReference getConfiguration_Arguments();

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
   * Returns the meta object for class '{@link org.osate.gtse.config.config.Extension <em>Extension</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Extension</em>'.
   * @see org.osate.gtse.config.config.Extension
   * @generated
   */
  EClass getExtension();

  /**
   * Returns the meta object for the attribute '{@link org.osate.gtse.config.config.Extension#isUnsafe <em>Unsafe</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Unsafe</em>'.
   * @see org.osate.gtse.config.config.Extension#isUnsafe()
   * @see #getExtension()
   * @generated
   */
  EAttribute getExtension_Unsafe();

  /**
   * Returns the meta object for the reference '{@link org.osate.gtse.config.config.Extension#getExtended <em>Extended</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Extended</em>'.
   * @see org.osate.gtse.config.config.Extension#getExtended()
   * @see #getExtension()
   * @generated
   */
  EReference getExtension_Extended();

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
   * Returns the meta object for the containment reference list '{@link org.osate.gtse.config.config.ConfigParameter#getCandidates <em>Candidates</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Candidates</em>'.
   * @see org.osate.gtse.config.config.ConfigParameter#getCandidates()
   * @see #getConfigParameter()
   * @generated
   */
  EReference getConfigParameter_Candidates();

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
   * Returns the meta object for the containment reference '{@link org.osate.gtse.config.config.Assignment#getElement <em>Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Element</em>'.
   * @see org.osate.gtse.config.config.Assignment#getElement()
   * @see #getAssignment()
   * @generated
   */
  EReference getAssignment_Element();

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
   * Returns the meta object for class '{@link org.osate.gtse.config.config.ConfigValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Value</em>'.
   * @see org.osate.gtse.config.config.ConfigValue
   * @generated
   */
  EClass getConfigValue();

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
     * The meta object literal for the '<em><b>Extensions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONFIGURATION__EXTENSIONS = eINSTANCE.getConfiguration_Extensions();

    /**
     * The meta object literal for the '<em><b>Arguments</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONFIGURATION__ARGUMENTS = eINSTANCE.getConfiguration_Arguments();

    /**
     * The meta object literal for the '<em><b>Assignments</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONFIGURATION__ASSIGNMENTS = eINSTANCE.getConfiguration_Assignments();

    /**
     * The meta object literal for the '{@link org.osate.gtse.config.config.impl.ExtensionImpl <em>Extension</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.osate.gtse.config.config.impl.ExtensionImpl
     * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getExtension()
     * @generated
     */
    EClass EXTENSION = eINSTANCE.getExtension();

    /**
     * The meta object literal for the '<em><b>Unsafe</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EXTENSION__UNSAFE = eINSTANCE.getExtension_Unsafe();

    /**
     * The meta object literal for the '<em><b>Extended</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXTENSION__EXTENDED = eINSTANCE.getExtension_Extended();

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
     * The meta object literal for the '<em><b>Candidates</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONFIG_PARAMETER__CANDIDATES = eINSTANCE.getConfigParameter_Candidates();

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
     * The meta object literal for the '<em><b>Element</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ASSIGNMENT__ELEMENT = eINSTANCE.getAssignment_Element();

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
     * The meta object literal for the '{@link org.osate.gtse.config.config.impl.ConfigValueImpl <em>Value</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.osate.gtse.config.config.impl.ConfigValueImpl
     * @see org.osate.gtse.config.config.impl.ConfigPackageImpl#getConfigValue()
     * @generated
     */
    EClass CONFIG_VALUE = eINSTANCE.getConfigValue();

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
     * The meta object literal for the '<em><b>Assignments</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NAMED_ELEMENT_REF__ASSIGNMENTS = eINSTANCE.getNamedElementRef_Assignments();

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

  }

} //ConfigPackage
