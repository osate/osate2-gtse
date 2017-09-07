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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Candidate List</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.osate.gtse.config.config.CandidateList#getCandidates <em>Candidates</em>}</li>
 * </ul>
 *
 * @see org.osate.gtse.config.config.ConfigPackage#getCandidateList()
 * @model
 * @generated
 */
public interface CandidateList extends ConfigValue
{
  /**
   * Returns the value of the '<em><b>Candidates</b></em>' containment reference list.
   * The list contents are of type {@link org.osate.gtse.config.config.ConfigValue}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Candidates</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Candidates</em>' containment reference list.
   * @see org.osate.gtse.config.config.ConfigPackage#getCandidateList_Candidates()
   * @model containment="true"
   * @generated
   */
  EList<ConfigValue> getCandidates();

} // CandidateList
