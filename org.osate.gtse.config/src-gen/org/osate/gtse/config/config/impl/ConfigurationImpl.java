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

import org.osate.aadl2.ComponentClassifier;

import org.osate.aadl2.impl.NamedElementImpl;

import org.osate.gtse.config.config.Argument;
import org.osate.gtse.config.config.Assignment;
import org.osate.gtse.config.config.Combination;
import org.osate.gtse.config.config.ConfigPackage;
import org.osate.gtse.config.config.ConfigParameter;
import org.osate.gtse.config.config.Configuration;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.osate.gtse.config.config.impl.ConfigurationImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.impl.ConfigurationImpl#getExtended <em>Extended</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.impl.ConfigurationImpl#getCombined <em>Combined</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.impl.ConfigurationImpl#getArguments <em>Arguments</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.impl.ConfigurationImpl#getAssignments <em>Assignments</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConfigurationImpl extends NamedElementImpl implements Configuration
{
  /**
   * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParameters()
   * @generated
   * @ordered
   */
  protected EList<ConfigParameter> parameters;

  /**
   * The cached value of the '{@link #getExtended() <em>Extended</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExtended()
   * @generated
   * @ordered
   */
  protected ComponentClassifier extended;

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
   * The cached value of the '{@link #getArguments() <em>Arguments</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArguments()
   * @generated
   * @ordered
   */
  protected EList<Argument> arguments;

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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ConfigurationImpl()
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
    return ConfigPackage.Literals.CONFIGURATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ConfigParameter> getParameters()
  {
    if (parameters == null)
    {
      parameters = new EObjectContainmentEList<ConfigParameter>(ConfigParameter.class, this, ConfigPackage.CONFIGURATION__PARAMETERS);
    }
    return parameters;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ComponentClassifier getExtended()
  {
    if (extended != null && ((EObject)extended).eIsProxy())
    {
      InternalEObject oldExtended = (InternalEObject)extended;
      extended = (ComponentClassifier)eResolveProxy(oldExtended);
      if (extended != oldExtended)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ConfigPackage.CONFIGURATION__EXTENDED, oldExtended, extended));
      }
    }
    return extended;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ComponentClassifier basicGetExtended()
  {
    return extended;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExtended(ComponentClassifier newExtended)
  {
    ComponentClassifier oldExtended = extended;
    extended = newExtended;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.CONFIGURATION__EXTENDED, oldExtended, extended));
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
      combined = new EObjectContainmentEList<Combination>(Combination.class, this, ConfigPackage.CONFIGURATION__COMBINED);
    }
    return combined;
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
      arguments = new EObjectContainmentEList<Argument>(Argument.class, this, ConfigPackage.CONFIGURATION__ARGUMENTS);
    }
    return arguments;
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
      assignments = new EObjectContainmentEList<Assignment>(Assignment.class, this, ConfigPackage.CONFIGURATION__ASSIGNMENTS);
    }
    return assignments;
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
      case ConfigPackage.CONFIGURATION__PARAMETERS:
        return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
      case ConfigPackage.CONFIGURATION__COMBINED:
        return ((InternalEList<?>)getCombined()).basicRemove(otherEnd, msgs);
      case ConfigPackage.CONFIGURATION__ARGUMENTS:
        return ((InternalEList<?>)getArguments()).basicRemove(otherEnd, msgs);
      case ConfigPackage.CONFIGURATION__ASSIGNMENTS:
        return ((InternalEList<?>)getAssignments()).basicRemove(otherEnd, msgs);
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
      case ConfigPackage.CONFIGURATION__PARAMETERS:
        return getParameters();
      case ConfigPackage.CONFIGURATION__EXTENDED:
        if (resolve) return getExtended();
        return basicGetExtended();
      case ConfigPackage.CONFIGURATION__COMBINED:
        return getCombined();
      case ConfigPackage.CONFIGURATION__ARGUMENTS:
        return getArguments();
      case ConfigPackage.CONFIGURATION__ASSIGNMENTS:
        return getAssignments();
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
      case ConfigPackage.CONFIGURATION__PARAMETERS:
        getParameters().clear();
        getParameters().addAll((Collection<? extends ConfigParameter>)newValue);
        return;
      case ConfigPackage.CONFIGURATION__EXTENDED:
        setExtended((ComponentClassifier)newValue);
        return;
      case ConfigPackage.CONFIGURATION__COMBINED:
        getCombined().clear();
        getCombined().addAll((Collection<? extends Combination>)newValue);
        return;
      case ConfigPackage.CONFIGURATION__ARGUMENTS:
        getArguments().clear();
        getArguments().addAll((Collection<? extends Argument>)newValue);
        return;
      case ConfigPackage.CONFIGURATION__ASSIGNMENTS:
        getAssignments().clear();
        getAssignments().addAll((Collection<? extends Assignment>)newValue);
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
      case ConfigPackage.CONFIGURATION__PARAMETERS:
        getParameters().clear();
        return;
      case ConfigPackage.CONFIGURATION__EXTENDED:
        setExtended((ComponentClassifier)null);
        return;
      case ConfigPackage.CONFIGURATION__COMBINED:
        getCombined().clear();
        return;
      case ConfigPackage.CONFIGURATION__ARGUMENTS:
        getArguments().clear();
        return;
      case ConfigPackage.CONFIGURATION__ASSIGNMENTS:
        getAssignments().clear();
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
      case ConfigPackage.CONFIGURATION__PARAMETERS:
        return parameters != null && !parameters.isEmpty();
      case ConfigPackage.CONFIGURATION__EXTENDED:
        return extended != null;
      case ConfigPackage.CONFIGURATION__COMBINED:
        return combined != null && !combined.isEmpty();
      case ConfigPackage.CONFIGURATION__ARGUMENTS:
        return arguments != null && !arguments.isEmpty();
      case ConfigPackage.CONFIGURATION__ASSIGNMENTS:
        return assignments != null && !assignments.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //ConfigurationImpl
