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
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.osate.aadl2.impl.NamedElementImpl;

import org.osate.gtse.config.config.ConfigPackage;
import org.osate.gtse.config.config.ConfigPkg;
import org.osate.gtse.config.config.Configuration;
import org.osate.gtse.config.config.OutputVariable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pkg</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.osate.gtse.config.config.impl.ConfigPkgImpl#getRoot <em>Root</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.impl.ConfigPkgImpl#getConfigurations <em>Configurations</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.impl.ConfigPkgImpl#getAnalyses <em>Analyses</em>}</li>
 *   <li>{@link org.osate.gtse.config.config.impl.ConfigPkgImpl#getOutputs <em>Outputs</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConfigPkgImpl extends NamedElementImpl implements ConfigPkg
{
  /**
   * The cached value of the '{@link #getRoot() <em>Root</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRoot()
   * @generated
   * @ordered
   */
  protected Configuration root;

  /**
   * The cached value of the '{@link #getConfigurations() <em>Configurations</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConfigurations()
   * @generated
   * @ordered
   */
  protected EList<Configuration> configurations;

  /**
   * The cached value of the '{@link #getAnalyses() <em>Analyses</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnalyses()
   * @generated
   * @ordered
   */
  protected EList<String> analyses;

  /**
   * The cached value of the '{@link #getOutputs() <em>Outputs</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOutputs()
   * @generated
   * @ordered
   */
  protected EList<OutputVariable> outputs;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ConfigPkgImpl()
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
    return ConfigPackage.Literals.CONFIG_PKG;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Configuration getRoot()
  {
    if (root != null && root.eIsProxy())
    {
      InternalEObject oldRoot = (InternalEObject)root;
      root = (Configuration)eResolveProxy(oldRoot);
      if (root != oldRoot)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ConfigPackage.CONFIG_PKG__ROOT, oldRoot, root));
      }
    }
    return root;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Configuration basicGetRoot()
  {
    return root;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRoot(Configuration newRoot)
  {
    Configuration oldRoot = root;
    root = newRoot;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ConfigPackage.CONFIG_PKG__ROOT, oldRoot, root));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Configuration> getConfigurations()
  {
    if (configurations == null)
    {
      configurations = new EObjectContainmentEList<Configuration>(Configuration.class, this, ConfigPackage.CONFIG_PKG__CONFIGURATIONS);
    }
    return configurations;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getAnalyses()
  {
    if (analyses == null)
    {
      analyses = new EDataTypeEList<String>(String.class, this, ConfigPackage.CONFIG_PKG__ANALYSES);
    }
    return analyses;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<OutputVariable> getOutputs()
  {
    if (outputs == null)
    {
      outputs = new EObjectContainmentEList<OutputVariable>(OutputVariable.class, this, ConfigPackage.CONFIG_PKG__OUTPUTS);
    }
    return outputs;
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
      case ConfigPackage.CONFIG_PKG__CONFIGURATIONS:
        return ((InternalEList<?>)getConfigurations()).basicRemove(otherEnd, msgs);
      case ConfigPackage.CONFIG_PKG__OUTPUTS:
        return ((InternalEList<?>)getOutputs()).basicRemove(otherEnd, msgs);
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
      case ConfigPackage.CONFIG_PKG__ROOT:
        if (resolve) return getRoot();
        return basicGetRoot();
      case ConfigPackage.CONFIG_PKG__CONFIGURATIONS:
        return getConfigurations();
      case ConfigPackage.CONFIG_PKG__ANALYSES:
        return getAnalyses();
      case ConfigPackage.CONFIG_PKG__OUTPUTS:
        return getOutputs();
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
      case ConfigPackage.CONFIG_PKG__ROOT:
        setRoot((Configuration)newValue);
        return;
      case ConfigPackage.CONFIG_PKG__CONFIGURATIONS:
        getConfigurations().clear();
        getConfigurations().addAll((Collection<? extends Configuration>)newValue);
        return;
      case ConfigPackage.CONFIG_PKG__ANALYSES:
        getAnalyses().clear();
        getAnalyses().addAll((Collection<? extends String>)newValue);
        return;
      case ConfigPackage.CONFIG_PKG__OUTPUTS:
        getOutputs().clear();
        getOutputs().addAll((Collection<? extends OutputVariable>)newValue);
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
      case ConfigPackage.CONFIG_PKG__ROOT:
        setRoot((Configuration)null);
        return;
      case ConfigPackage.CONFIG_PKG__CONFIGURATIONS:
        getConfigurations().clear();
        return;
      case ConfigPackage.CONFIG_PKG__ANALYSES:
        getAnalyses().clear();
        return;
      case ConfigPackage.CONFIG_PKG__OUTPUTS:
        getOutputs().clear();
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
      case ConfigPackage.CONFIG_PKG__ROOT:
        return root != null;
      case ConfigPackage.CONFIG_PKG__CONFIGURATIONS:
        return configurations != null && !configurations.isEmpty();
      case ConfigPackage.CONFIG_PKG__ANALYSES:
        return analyses != null && !analyses.isEmpty();
      case ConfigPackage.CONFIG_PKG__OUTPUTS:
        return outputs != null && !outputs.isEmpty();
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
    result.append(" (analyses: ");
    result.append(analyses);
    result.append(')');
    return result.toString();
  }

} //ConfigPkgImpl
