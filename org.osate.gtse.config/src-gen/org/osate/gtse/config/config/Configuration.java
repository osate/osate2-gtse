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

import org.eclipse.emf.ecore.EObject;

import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.osate.gtse.config.config.Configuration#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.Configuration#getExtended <em>Extended</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.Configuration#getCombined <em>Combined</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.Configuration#getAssignments <em>Assignments</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.Configuration#getConstraints <em>Constraints</em>}</li>
 * </ul>
 *
 * @see org.osate.gtse.config.config.ConfigPackage#getConfiguration()
 * @model
 * @generated
 */
public interface Configuration extends EObject, NamedElement
{
  /**
   * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
   * The list contents are of type {@link org.osate.gtse.config.config.ConfigParameter}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parameters</em>' containment reference list.
   * @see org.osate.gtse.config.config.ConfigPackage#getConfiguration_Parameters()
   * @model containment="true"
   * @generated
   */
  EList<ConfigParameter> getParameters();

  /**
   * Returns the value of the '<em><b>Extended</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Extended</em>' reference.
   * @see #setExtended(ComponentClassifier)
   * @see org.osate.gtse.config.config.ConfigPackage#getConfiguration_Extended()
   * @model
   * @generated
   */
  ComponentClassifier getExtended();

  /**
   * Sets the value of the '{@link org.osate.gtse.config.config.Configuration#getExtended <em>Extended</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Extended</em>' reference.
   * @see #getExtended()
   * @generated
   */
  void setExtended(ComponentClassifier value);

  /**
   * Returns the value of the '<em><b>Combined</b></em>' containment reference list.
   * The list contents are of type {@link org.osate.gtse.config.config.Combination}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Combined</em>' containment reference list.
   * @see org.osate.gtse.config.config.ConfigPackage#getConfiguration_Combined()
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
   * @see org.osate.gtse.config.config.ConfigPackage#getConfiguration_Assignments()
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
   * @see org.osate.gtse.config.config.ConfigPackage#getConfiguration_Constraints()
   * @model containment="true"
   * @generated
   */
  EList<Constraint> getConstraints();

} // Configuration
