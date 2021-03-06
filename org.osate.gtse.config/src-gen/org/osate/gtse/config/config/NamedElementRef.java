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

import org.eclipse.emf.common.util.EList;

import org.osate.aadl2.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Named Element Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.osate.gtse.config.config.NamedElementRef#getRef <em>Ref</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.NamedElementRef#getArguments <em>Arguments</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.NamedElementRef#getCombined <em>Combined</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.NamedElementRef#getAssignments <em>Assignments</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.NamedElementRef#getConstraints <em>Constraints</em>}</li>
 * </ul>
 *
 * @see org.osate.gtse.config.config.ConfigPackage#getNamedElementRef()
 * @model
 * @generated
 */
public interface NamedElementRef extends ConfigValue, ConditionValue
{
  /**
   * Returns the value of the '<em><b>Ref</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ref</em>' reference.
   * @see #setRef(NamedElement)
   * @see org.osate.gtse.config.config.ConfigPackage#getNamedElementRef_Ref()
   * @model
   * @generated
   */
  NamedElement getRef();

  /**
   * Sets the value of the '{@link org.osate.gtse.config.config.NamedElementRef#getRef <em>Ref</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ref</em>' reference.
   * @see #getRef()
   * @generated
   */
  void setRef(NamedElement value);

  /**
   * Returns the value of the '<em><b>Arguments</b></em>' containment reference list.
   * The list contents are of type {@link org.osate.gtse.config.config.Argument}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Arguments</em>' containment reference list.
   * @see org.osate.gtse.config.config.ConfigPackage#getNamedElementRef_Arguments()
   * @model containment="true"
   * @generated
   */
  EList<Argument> getArguments();

  /**
   * Returns the value of the '<em><b>Combined</b></em>' containment reference list.
   * The list contents are of type {@link org.osate.gtse.config.config.Combination}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Combined</em>' containment reference list.
   * @see org.osate.gtse.config.config.ConfigPackage#getNamedElementRef_Combined()
   * @model containment="true"
   * @generated
   */
  EList<Combination> getCombined();

  /**
   * Returns the value of the '<em><b>Assignments</b></em>' containment reference list.
   * The list contents are of type {@link org.osate.gtse.config.config.Assignment}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Assignments</em>' containment reference list.
   * @see org.osate.gtse.config.config.ConfigPackage#getNamedElementRef_Assignments()
   * @model containment="true"
   * @generated
   */
  EList<Assignment> getAssignments();

  /**
   * Returns the value of the '<em><b>Constraints</b></em>' containment reference list.
   * The list contents are of type {@link org.osate.gtse.config.config.Constraint}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Constraints</em>' containment reference list.
   * @see org.osate.gtse.config.config.ConfigPackage#getNamedElementRef_Constraints()
   * @model containment="true"
   * @generated
   */
  EList<Constraint> getConstraints();

} // NamedElementRef
