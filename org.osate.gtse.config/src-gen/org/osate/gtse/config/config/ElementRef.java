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
 * A representation of the model object '<em><b>Element Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.osate.gtse.config.config.ElementRef#getElement <em>Element</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.ElementRef#getPrev <em>Prev</em>}</li>
 * </ul>
 *
 * @see org.osate.gtse.config.config.ConfigPackage#getElementRef()
 * @model
 * @generated
 */
public interface ElementRef extends EObject
{
  /**
   * Returns the value of the '<em><b>Element</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Element</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Element</em>' reference.
   * @see #setElement(NamedElement)
   * @see org.osate.gtse.config.config.ConfigPackage#getElementRef_Element()
   * @model
   * @generated
   */
  NamedElement getElement();

  /**
   * Sets the value of the '{@link org.osate.gtse.config.config.ElementRef#getElement <em>Element</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Element</em>' reference.
   * @see #getElement()
   * @generated
   */
  void setElement(NamedElement value);

  /**
   * Returns the value of the '<em><b>Prev</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Prev</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Prev</em>' containment reference.
   * @see #setPrev(ElementRef)
   * @see org.osate.gtse.config.config.ConfigPackage#getElementRef_Prev()
   * @model containment="true"
   * @generated
   */
  ElementRef getPrev();

  /**
   * Sets the value of the '{@link org.osate.gtse.config.config.ElementRef#getPrev <em>Prev</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Prev</em>' containment reference.
   * @see #getPrev()
   * @generated
   */
  void setPrev(ElementRef value);

} // ElementRef
