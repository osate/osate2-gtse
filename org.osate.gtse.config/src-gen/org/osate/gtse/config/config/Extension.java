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
 * A representation of the model object '<em><b>Extension</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.osate.gtse.config.config.Extension#isUnsafe <em>Unsafe</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.Extension#getExtended <em>Extended</em>}</li>
 * </ul>
 *
 * @see org.osate.gtse.config.config.ConfigPackage#getExtension()
 * @model
 * @generated
 */
public interface Extension extends EObject
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
   * @see org.osate.gtse.config.config.ConfigPackage#getExtension_Unsafe()
   * @model
   * @generated
   */
  boolean isUnsafe();

  /**
   * Sets the value of the '{@link org.osate.gtse.config.config.Extension#isUnsafe <em>Unsafe</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Unsafe</em>' attribute.
   * @see #isUnsafe()
   * @generated
   */
  void setUnsafe(boolean value);

  /**
   * Returns the value of the '<em><b>Extended</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Extended</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Extended</em>' reference.
   * @see #setExtended(NamedElement)
   * @see org.osate.gtse.config.config.ConfigPackage#getExtension_Extended()
   * @model
   * @generated
   */
  NamedElement getExtended();

  /**
   * Sets the value of the '{@link org.osate.gtse.config.config.Extension#getExtended <em>Extended</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Extended</em>' reference.
   * @see #getExtended()
   * @generated
   */
  void setExtended(NamedElement value);

} // Extension
