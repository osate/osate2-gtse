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

import org.osate.aadl2.ComponentCategory;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.Property;

import org.osate.aadl2.impl.NamedElementImpl;

import org.osate.gtse.config.config.ConfigPackage;
import org.osate.gtse.config.config.ConfigParameter;
import org.osate.gtse.config.config.ConfigValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.osate.gtse.config.config.impl.ConfigParameterImpl#getCategory <em>Category</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.impl.ConfigParameterImpl#getClassifier <em>Classifier</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.impl.ConfigParameterImpl#getPropertyType <em>Property Type</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.impl.ConfigParameterImpl#getCandidates <em>Candidates</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConfigParameterImpl extends NamedElementImpl implements ConfigParameter
{
  /**
   * The default value of the '{@link #getCategory() <em>Category</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCategory()
   * @generated
   * @ordered
   */
  protected static final ComponentCategory CATEGORY_EDEFAULT = ComponentCategory.ABSTRACT;

  /**
   * The cached value of the '{@link #getCategory() <em>Category</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCategory()
   * @generated
   * @ordered
   */
  protected ComponentCategory category = CATEGORY_EDEFAULT;

  /**
   * The cached value of the '{@link #getClassifier() <em>Classifier</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getClassifier()
   * @generated
   * @ordered
   */
  protected ComponentClassifier classifier;

  /**
   * The cached value of the '{@link #getPropertyType() <em>Property Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPropertyType()
   * @generated
   * @ordered
   */
  protected Property propertyType;

  /**
   * The cached value of the '{@link #getCandidates() <em>Candidates</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCandidates()
   * @generated
   * @ordered
   */
  protected EList<ConfigValue> candidates;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ConfigParameterImpl()
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
    return ConfigPackage.Literals.CONFIG_PARAMETER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ComponentCategory getCategory()
  {
    return category;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCategory(ComponentCategory newCategory)
  {
    ComponentCategory oldCategory = category;
    category = newCategory == null ? CATEGORY_EDEFAULT : newCategory;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.CONFIG_PARAMETER__CATEGORY, oldCategory, category));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ComponentClassifier getClassifier()
  {
    if (classifier != null && ((EObject)classifier).eIsProxy())
    {
      InternalEObject oldClassifier = (InternalEObject)classifier;
      classifier = (ComponentClassifier)eResolveProxy(oldClassifier);
      if (classifier != oldClassifier)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ConfigPackage.CONFIG_PARAMETER__CLASSIFIER, oldClassifier, classifier));
      }
    }
    return classifier;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ComponentClassifier basicGetClassifier()
  {
    return classifier;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setClassifier(ComponentClassifier newClassifier)
  {
    ComponentClassifier oldClassifier = classifier;
    classifier = newClassifier;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.CONFIG_PARAMETER__CLASSIFIER, oldClassifier, classifier));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Property getPropertyType()
  {
    if (propertyType != null && ((EObject)propertyType).eIsProxy())
    {
      InternalEObject oldPropertyType = (InternalEObject)propertyType;
      propertyType = (Property)eResolveProxy(oldPropertyType);
      if (propertyType != oldPropertyType)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ConfigPackage.CONFIG_PARAMETER__PROPERTY_TYPE, oldPropertyType, propertyType));
      }
    }
    return propertyType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Property basicGetPropertyType()
  {
    return propertyType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPropertyType(Property newPropertyType)
  {
    Property oldPropertyType = propertyType;
    propertyType = newPropertyType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.CONFIG_PARAMETER__PROPERTY_TYPE, oldPropertyType, propertyType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ConfigValue> getCandidates()
  {
    if (candidates == null)
    {
      candidates = new EObjectContainmentEList<ConfigValue>(ConfigValue.class, this, ConfigPackage.CONFIG_PARAMETER__CANDIDATES);
    }
    return candidates;
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
      case ConfigPackage.CONFIG_PARAMETER__CANDIDATES:
        return ((InternalEList<?>)getCandidates()).basicRemove(otherEnd, msgs);
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
      case ConfigPackage.CONFIG_PARAMETER__CATEGORY:
        return getCategory();
      case ConfigPackage.CONFIG_PARAMETER__CLASSIFIER:
        if (resolve) return getClassifier();
        return basicGetClassifier();
      case ConfigPackage.CONFIG_PARAMETER__PROPERTY_TYPE:
        if (resolve) return getPropertyType();
        return basicGetPropertyType();
      case ConfigPackage.CONFIG_PARAMETER__CANDIDATES:
        return getCandidates();
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
      case ConfigPackage.CONFIG_PARAMETER__CATEGORY:
        setCategory((ComponentCategory)newValue);
        return;
      case ConfigPackage.CONFIG_PARAMETER__CLASSIFIER:
        setClassifier((ComponentClassifier)newValue);
        return;
      case ConfigPackage.CONFIG_PARAMETER__PROPERTY_TYPE:
        setPropertyType((Property)newValue);
        return;
      case ConfigPackage.CONFIG_PARAMETER__CANDIDATES:
        getCandidates().clear();
        getCandidates().addAll((Collection<? extends ConfigValue>)newValue);
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
      case ConfigPackage.CONFIG_PARAMETER__CATEGORY:
        setCategory(CATEGORY_EDEFAULT);
        return;
      case ConfigPackage.CONFIG_PARAMETER__CLASSIFIER:
        setClassifier((ComponentClassifier)null);
        return;
      case ConfigPackage.CONFIG_PARAMETER__PROPERTY_TYPE:
        setPropertyType((Property)null);
        return;
      case ConfigPackage.CONFIG_PARAMETER__CANDIDATES:
        getCandidates().clear();
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
      case ConfigPackage.CONFIG_PARAMETER__CATEGORY:
        return category != CATEGORY_EDEFAULT;
      case ConfigPackage.CONFIG_PARAMETER__CLASSIFIER:
        return classifier != null;
      case ConfigPackage.CONFIG_PARAMETER__PROPERTY_TYPE:
        return propertyType != null;
      case ConfigPackage.CONFIG_PARAMETER__CANDIDATES:
        return candidates != null && !candidates.isEmpty();
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
    result.append(" (category: ");
    result.append(category);
    result.append(')');
    return result.toString();
  }

} //ConfigParameterImpl
