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

import org.osate.aadl2.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pkg</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.osate.gtse.config.config.ConfigPkg#getRoot <em>Root</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.ConfigPkg#getConfigurations <em>Configurations</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.ConfigPkg#getAnalyses <em>Analyses</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.ConfigPkg#getOutputs <em>Outputs</em>}</li>
 * </ul>
 *
 * @see org.osate.gtse.config.config.ConfigPackage#getConfigPkg()
 * @model
 * @generated
 */
public interface ConfigPkg extends EObject, NamedElement
{
  /**
   * Returns the value of the '<em><b>Root</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Root</em>' reference.
   * @see #setRoot(Configuration)
   * @see org.osate.gtse.config.config.ConfigPackage#getConfigPkg_Root()
   * @model
   * @generated
   */
  Configuration getRoot();

  /**
   * Sets the value of the '{@link org.osate.gtse.config.config.ConfigPkg#getRoot <em>Root</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Root</em>' reference.
   * @see #getRoot()
   * @generated
   */
  void setRoot(Configuration value);

  /**
   * Returns the value of the '<em><b>Configurations</b></em>' containment reference list.
   * The list contents are of type {@link org.osate.gtse.config.config.Configuration}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Configurations</em>' containment reference list.
   * @see org.osate.gtse.config.config.ConfigPackage#getConfigPkg_Configurations()
   * @model containment="true"
   * @generated
   */
  EList<Configuration> getConfigurations();

  /**
   * Returns the value of the '<em><b>Analyses</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Analyses</em>' attribute list.
   * @see org.osate.gtse.config.config.ConfigPackage#getConfigPkg_Analyses()
   * @model unique="false"
   * @generated
   */
  EList<String> getAnalyses();

  /**
   * Returns the value of the '<em><b>Outputs</b></em>' containment reference list.
   * The list contents are of type {@link org.osate.gtse.config.config.OutputVariable}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Outputs</em>' containment reference list.
   * @see org.osate.gtse.config.config.ConfigPackage#getConfigPkg_Outputs()
   * @model containment="true"
   * @generated
   */
  EList<OutputVariable> getOutputs();

} // ConfigPkg
