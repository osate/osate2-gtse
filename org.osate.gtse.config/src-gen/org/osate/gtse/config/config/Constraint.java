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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.osate.gtse.config.config.Constraint#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.Constraint#getRelation <em>Relation</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.Constraint#getConsequence <em>Consequence</em>}</li>
 * </ul>
 *
 * @see org.osate.gtse.config.config.ConfigPackage#getConstraint()
 * @model
 * @generated
 */
public interface Constraint extends EObject
{
  /**
   * Returns the value of the '<em><b>Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Condition</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Condition</em>' containment reference.
   * @see #setCondition(Condition)
   * @see org.osate.gtse.config.config.ConfigPackage#getConstraint_Condition()
   * @model containment="true"
   * @generated
   */
  Condition getCondition();

  /**
   * Sets the value of the '{@link org.osate.gtse.config.config.Constraint#getCondition <em>Condition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Condition</em>' containment reference.
   * @see #getCondition()
   * @generated
   */
  void setCondition(Condition value);

  /**
   * Returns the value of the '<em><b>Relation</b></em>' attribute.
   * The literals are from the enumeration {@link org.osate.gtse.config.config.Relation}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Relation</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Relation</em>' attribute.
   * @see org.osate.gtse.config.config.Relation
   * @see #setRelation(Relation)
   * @see org.osate.gtse.config.config.ConfigPackage#getConstraint_Relation()
   * @model
   * @generated
   */
  Relation getRelation();

  /**
   * Sets the value of the '{@link org.osate.gtse.config.config.Constraint#getRelation <em>Relation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Relation</em>' attribute.
   * @see org.osate.gtse.config.config.Relation
   * @see #getRelation()
   * @generated
   */
  void setRelation(Relation value);

  /**
   * Returns the value of the '<em><b>Consequence</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Consequence</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Consequence</em>' containment reference.
   * @see #setConsequence(Condition)
   * @see org.osate.gtse.config.config.ConfigPackage#getConstraint_Consequence()
   * @model containment="true"
   * @generated
   */
  Condition getConsequence();

  /**
   * Sets the value of the '{@link org.osate.gtse.config.config.Constraint#getConsequence <em>Consequence</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Consequence</em>' containment reference.
   * @see #getConsequence()
   * @generated
   */
  void setConsequence(Condition value);

} // Constraint
