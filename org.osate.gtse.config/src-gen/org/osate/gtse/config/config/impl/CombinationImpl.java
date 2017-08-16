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

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.osate.aadl2.NamedElement;

import org.osate.gtse.config.config.Combination;
import org.osate.gtse.config.config.ConfigPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Combination</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.osate.gtse.config.config.impl.CombinationImpl#isUnsafe <em>Unsafe</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.impl.CombinationImpl#getConfiguration <em>Configuration</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CombinationImpl extends MinimalEObjectImpl.Container implements Combination
{
  /**
   * The default value of the '{@link #isUnsafe() <em>Unsafe</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isUnsafe()
   * @generated
   * @ordered
   */
  protected static final boolean UNSAFE_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isUnsafe() <em>Unsafe</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isUnsafe()
   * @generated
   * @ordered
   */
  protected boolean unsafe = UNSAFE_EDEFAULT;

  /**
   * The cached value of the '{@link #getConfiguration() <em>Configuration</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConfiguration()
   * @generated
   * @ordered
   */
  protected NamedElement configuration;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CombinationImpl()
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
    return ConfigPackage.Literals.COMBINATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isUnsafe()
  {
    return unsafe;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUnsafe(boolean newUnsafe)
  {
    boolean oldUnsafe = unsafe;
    unsafe = newUnsafe;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.COMBINATION__UNSAFE, oldUnsafe, unsafe));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NamedElement getConfiguration()
  {
    if (configuration != null && ((EObject)configuration).eIsProxy())
    {
      InternalEObject oldConfiguration = (InternalEObject)configuration;
      configuration = (NamedElement)eResolveProxy(oldConfiguration);
      if (configuration != oldConfiguration)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ConfigPackage.COMBINATION__CONFIGURATION, oldConfiguration, configuration));
      }
    }
    return configuration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NamedElement basicGetConfiguration()
  {
    return configuration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConfiguration(NamedElement newConfiguration)
  {
    NamedElement oldConfiguration = configuration;
    configuration = newConfiguration;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.COMBINATION__CONFIGURATION, oldConfiguration, configuration));
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
      case ConfigPackage.COMBINATION__UNSAFE:
        return isUnsafe();
      case ConfigPackage.COMBINATION__CONFIGURATION:
        if (resolve) return getConfiguration();
        return basicGetConfiguration();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case ConfigPackage.COMBINATION__UNSAFE:
        setUnsafe((Boolean)newValue);
        return;
      case ConfigPackage.COMBINATION__CONFIGURATION:
        setConfiguration((NamedElement)newValue);
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
      case ConfigPackage.COMBINATION__UNSAFE:
        setUnsafe(UNSAFE_EDEFAULT);
        return;
      case ConfigPackage.COMBINATION__CONFIGURATION:
        setConfiguration((NamedElement)null);
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
      case ConfigPackage.COMBINATION__UNSAFE:
        return unsafe != UNSAFE_EDEFAULT;
      case ConfigPackage.COMBINATION__CONFIGURATION:
        return configuration != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (unsafe: ");
    result.append(unsafe);
    result.append(')');
    return result.toString();
  }

} //CombinationImpl
