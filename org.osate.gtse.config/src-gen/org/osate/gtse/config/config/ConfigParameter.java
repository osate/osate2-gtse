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

import org.eclipse.emf.ecore.EObject;

import org.osate.aadl2.ComponentCategory;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.Property;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.osate.gtse.config.config.ConfigParameter#getCategory <em>Category</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.ConfigParameter#getClassifier <em>Classifier</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.ConfigParameter#getPropertyType <em>Property Type</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.ConfigParameter#getChoices <em>Choices</em>}</li>
 * </ul>
 *
 * @see org.osate.gtse.config.config.ConfigPackage#getConfigParameter()
 * @model
 * @generated
 */
public interface ConfigParameter extends EObject, NamedElement
{
  /**
   * Returns the value of the '<em><b>Category</b></em>' attribute.
   * The literals are from the enumeration {@link org.osate.aadl2.ComponentCategory}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Category</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Category</em>' attribute.
   * @see org.osate.aadl2.ComponentCategory
   * @see #setCategory(ComponentCategory)
   * @see org.osate.gtse.config.config.ConfigPackage#getConfigParameter_Category()
   * @model
   * @generated
   */
  ComponentCategory getCategory();

  /**
   * Sets the value of the '{@link org.osate.gtse.config.config.ConfigParameter#getCategory <em>Category</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Category</em>' attribute.
   * @see org.osate.aadl2.ComponentCategory
   * @see #getCategory()
   * @generated
   */
  void setCategory(ComponentCategory value);

  /**
   * Returns the value of the '<em><b>Classifier</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Classifier</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Classifier</em>' reference.
   * @see #setClassifier(ComponentClassifier)
   * @see org.osate.gtse.config.config.ConfigPackage#getConfigParameter_Classifier()
   * @model
   * @generated
   */
  ComponentClassifier getClassifier();

  /**
   * Sets the value of the '{@link org.osate.gtse.config.config.ConfigParameter#getClassifier <em>Classifier</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Classifier</em>' reference.
   * @see #getClassifier()
   * @generated
   */
  void setClassifier(ComponentClassifier value);

  /**
   * Returns the value of the '<em><b>Property Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Property Type</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Property Type</em>' reference.
   * @see #setPropertyType(Property)
   * @see org.osate.gtse.config.config.ConfigPackage#getConfigParameter_PropertyType()
   * @model
   * @generated
   */
  Property getPropertyType();

  /**
   * Sets the value of the '{@link org.osate.gtse.config.config.ConfigParameter#getPropertyType <em>Property Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Property Type</em>' reference.
   * @see #getPropertyType()
   * @generated
   */
  void setPropertyType(Property value);

  /**
   * Returns the value of the '<em><b>Choices</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Choices</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Choices</em>' containment reference.
   * @see #setChoices(ConfigValue)
   * @see org.osate.gtse.config.config.ConfigPackage#getConfigParameter_Choices()
   * @model containment="true"
   * @generated
   */
  ConfigValue getChoices();

  /**
   * Sets the value of the '{@link org.osate.gtse.config.config.ConfigParameter#getChoices <em>Choices</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Choices</em>' containment reference.
   * @see #getChoices()
   * @generated
   */
  void setChoices(ConfigValue value);

} // ConfigParameter
