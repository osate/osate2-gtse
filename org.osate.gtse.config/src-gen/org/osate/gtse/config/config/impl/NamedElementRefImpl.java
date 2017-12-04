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
package org.osate.gtse.config.config.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.osate.aadl2.NamedElement;

import org.osate.gtse.config.config.Argument;
import org.osate.gtse.config.config.Assignment;
import org.osate.gtse.config.config.Combination;
import org.osate.gtse.config.config.ConfigPackage;
import org.osate.gtse.config.config.Constraint;
import org.osate.gtse.config.config.NamedElementRef;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Named Element Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.osate.gtse.config.config.impl.NamedElementRefImpl#getRef <em>Ref</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.impl.NamedElementRefImpl#getArguments <em>Arguments</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.impl.NamedElementRefImpl#getCombined <em>Combined</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.impl.NamedElementRefImpl#getAssignments <em>Assignments</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.impl.NamedElementRefImpl#getConstraints <em>Constraints</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NamedElementRefImpl extends ConfigValueImpl implements NamedElementRef
{
  /**
   * The cached value of the '{@link #getRef() <em>Ref</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRef()
   * @generated
   * @ordered
   */
  protected NamedElement ref;

  /**
   * The cached value of the '{@link #getArguments() <em>Arguments</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArguments()
   * @generated
   * @ordered
   */
  protected EList<Argument> arguments;

  /**
   * The cached value of the '{@link #getCombined() <em>Combined</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCombined()
   * @generated
   * @ordered
   */
  protected EList<Combination> combined;

  /**
   * The cached value of the '{@link #getAssignments() <em>Assignments</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAssignments()
   * @generated
   * @ordered
   */
  protected EList<Assignment> assignments;

  /**
   * The cached value of the '{@link #getConstraints() <em>Constraints</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConstraints()
   * @generated
   * @ordered
   */
  protected EList<Constraint> constraints;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected NamedElementRefImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return ConfigPackage.Literals.NAMED_ELEMENT_REF;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NamedElement getRef()
  {
    if (ref != null && ((EObject)ref).eIsProxy())
    {
      InternalEObject oldRef = (InternalEObject)ref;
      ref = (NamedElement)eResolveProxy(oldRef);
      if (ref != oldRef)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ConfigPackage.NAMED_ELEMENT_REF__REF, oldRef, ref));
      }
    }
    return ref;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NamedElement basicGetRef()
  {
    return ref;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRef(NamedElement newRef)
  {
    NamedElement oldRef = ref;
    ref = newRef;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.NAMED_ELEMENT_REF__REF, oldRef, ref));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Argument> getArguments()
  {
    if (arguments == null)
    {
      arguments = new EObjectContainmentEList<Argument>(Argument.class, this, ConfigPackage.NAMED_ELEMENT_REF__ARGUMENTS);
    }
    return arguments;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Combination> getCombined()
  {
    if (combined == null)
    {
      combined = new EObjectContainmentEList<Combination>(Combination.class, this, ConfigPackage.NAMED_ELEMENT_REF__COMBINED);
    }
    return combined;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Assignment> getAssignments()
  {
    if (assignments == null)
    {
      assignments = new EObjectContainmentEList<Assignment>(Assignment.class, this, ConfigPackage.NAMED_ELEMENT_REF__ASSIGNMENTS);
    }
    return assignments;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Constraint> getConstraints()
  {
    if (constraints == null)
    {
      constraints = new EObjectContainmentEList<Constraint>(Constraint.class, this, ConfigPackage.NAMED_ELEMENT_REF__CONSTRAINTS);
    }
    return constraints;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case ConfigPackage.NAMED_ELEMENT_REF__ARGUMENTS:
        return ((InternalEList<?>)getArguments()).basicRemove(otherEnd, msgs);
      case ConfigPackage.NAMED_ELEMENT_REF__COMBINED:
        return ((InternalEList<?>)getCombined()).basicRemove(otherEnd, msgs);
      case ConfigPackage.NAMED_ELEMENT_REF__ASSIGNMENTS:
        return ((InternalEList<?>)getAssignments()).basicRemove(otherEnd, msgs);
      case ConfigPackage.NAMED_ELEMENT_REF__CONSTRAINTS:
        return ((InternalEList<?>)getConstraints()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case ConfigPackage.NAMED_ELEMENT_REF__REF:
        if (resolve) return getRef();
        return basicGetRef();
      case ConfigPackage.NAMED_ELEMENT_REF__ARGUMENTS:
        return getArguments();
      case ConfigPackage.NAMED_ELEMENT_REF__COMBINED:
        return getCombined();
      case ConfigPackage.NAMED_ELEMENT_REF__ASSIGNMENTS:
        return getAssignments();
      case ConfigPackage.NAMED_ELEMENT_REF__CONSTRAINTS:
        return getConstraints();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case ConfigPackage.NAMED_ELEMENT_REF__REF:
        setRef((NamedElement)newValue);
        return;
      case ConfigPackage.NAMED_ELEMENT_REF__ARGUMENTS:
        getArguments().clear();
        getArguments().addAll((Collection<? extends Argument>)newValue);
        return;
      case ConfigPackage.NAMED_ELEMENT_REF__COMBINED:
        getCombined().clear();
        getCombined().addAll((Collection<? extends Combination>)newValue);
        return;
      case ConfigPackage.NAMED_ELEMENT_REF__ASSIGNMENTS:
        getAssignments().clear();
        getAssignments().addAll((Collection<? extends Assignment>)newValue);
        return;
      case ConfigPackage.NAMED_ELEMENT_REF__CONSTRAINTS:
        getConstraints().clear();
        getConstraints().addAll((Collection<? extends Constraint>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case ConfigPackage.NAMED_ELEMENT_REF__REF:
        setRef((NamedElement)null);
        return;
      case ConfigPackage.NAMED_ELEMENT_REF__ARGUMENTS:
        getArguments().clear();
        return;
      case ConfigPackage.NAMED_ELEMENT_REF__COMBINED:
        getCombined().clear();
        return;
      case ConfigPackage.NAMED_ELEMENT_REF__ASSIGNMENTS:
        getAssignments().clear();
        return;
      case ConfigPackage.NAMED_ELEMENT_REF__CONSTRAINTS:
        getConstraints().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case ConfigPackage.NAMED_ELEMENT_REF__REF:
        return ref != null;
      case ConfigPackage.NAMED_ELEMENT_REF__ARGUMENTS:
        return arguments != null && !arguments.isEmpty();
      case ConfigPackage.NAMED_ELEMENT_REF__COMBINED:
        return combined != null && !combined.isEmpty();
      case ConfigPackage.NAMED_ELEMENT_REF__ASSIGNMENTS:
        return assignments != null && !assignments.isEmpty();
      case ConfigPackage.NAMED_ELEMENT_REF__CONSTRAINTS:
        return constraints != null && !constraints.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //NamedElementRefImpl
