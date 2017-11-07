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

import org.osate.aadl2.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Output Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.osate.gtse.config.config.OutputVariable#getType <em>Type</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.OutputVariable#getLimit <em>Limit</em>}</li>
 * </ul>
 *
 * @see org.osate.gtse.config.config.ConfigPackage#getOutputVariable()
 * @model
 * @generated
 */
public interface OutputVariable extends EObject, NamedElement
{
  /**
   * Returns the value of the '<em><b>Type</b></em>' attribute.
   * The literals are from the enumeration {@link org.osate.gtse.config.config.Type}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' attribute.
   * @see org.osate.gtse.config.config.Type
   * @see #setType(Type)
   * @see org.osate.gtse.config.config.ConfigPackage#getOutputVariable_Type()
   * @model
   * @generated
   */
  Type getType();

  /**
   * Sets the value of the '{@link org.osate.gtse.config.config.OutputVariable#getType <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' attribute.
   * @see org.osate.gtse.config.config.Type
   * @see #getType()
   * @generated
   */
  void setType(Type value);

  /**
   * Returns the value of the '<em><b>Limit</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Limit</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Limit</em>' containment reference.
   * @see #setLimit(Limit)
   * @see org.osate.gtse.config.config.ConfigPackage#getOutputVariable_Limit()
   * @model containment="true"
   * @generated
   */
  Limit getLimit();

  /**
   * Sets the value of the '{@link org.osate.gtse.config.config.OutputVariable#getLimit <em>Limit</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Limit</em>' containment reference.
   * @see #getLimit()
   * @generated
   */
  void setLimit(Limit value);

} // OutputVariable
