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
 * A representation of the model object '<em><b>Combination</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.osate.gtse.config.config.Combination#isUnsafe <em>Unsafe</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.Combination#getConfiguration <em>Configuration</em>}</li>
 * </ul>
 *
 * @see org.osate.gtse.config.config.ConfigPackage#getCombination()
 * @model
 * @generated
 */
public interface Combination extends EObject
{
  /**
   * Returns the value of the '<em><b>Unsafe</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Unsafe</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Unsafe</em>' attribute.
   * @see #setUnsafe(boolean)
   * @see org.osate.gtse.config.config.ConfigPackage#getCombination_Unsafe()
   * @model
   * @generated
   */
  boolean isUnsafe();

  /**
   * Sets the value of the '{@link org.osate.gtse.config.config.Combination#isUnsafe <em>Unsafe</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Unsafe</em>' attribute.
   * @see #isUnsafe()
   * @generated
   */
  void setUnsafe(boolean value);

  /**
   * Returns the value of the '<em><b>Configuration</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Configuration</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Configuration</em>' reference.
   * @see #setConfiguration(NamedElement)
   * @see org.osate.gtse.config.config.ConfigPackage#getCombination_Configuration()
   * @model
   * @generated
   */
  NamedElement getConfiguration();

  /**
   * Sets the value of the '{@link org.osate.gtse.config.config.Combination#getConfiguration <em>Configuration</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Configuration</em>' reference.
   * @see #getConfiguration()
   * @generated
   */
  void setConfiguration(NamedElement value);

} // Combination
