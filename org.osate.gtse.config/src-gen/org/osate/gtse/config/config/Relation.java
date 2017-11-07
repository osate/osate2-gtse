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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Relation</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.osate.gtse.config.config.ConfigPackage#getRelation()
 * @model
 * @generated
 */
public enum Relation implements Enumerator
{
  /**
   * The '<em><b>GT</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #GT_VALUE
   * @generated
   * @ordered
   */
  GT(0, "GT", ">"),

  /**
   * The '<em><b>GTE</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #GTE_VALUE
   * @generated
   * @ordered
   */
  GTE(1, "GTE", ">="),

  /**
   * The '<em><b>EQ</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #EQ_VALUE
   * @generated
   * @ordered
   */
  EQ(2, "EQ", "=="),

  /**
   * The '<em><b>NEQ</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #NEQ_VALUE
   * @generated
   * @ordered
   */
  NEQ(3, "NEQ", "!="),

  /**
   * The '<em><b>LT</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #LT_VALUE
   * @generated
   * @ordered
   */
  LT(4, "LT", "<"),

  /**
   * The '<em><b>LTE</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #LTE_VALUE
   * @generated
   * @ordered
   */
  LTE(5, "LTE", "<=");

  /**
   * The '<em><b>GT</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>GT</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #GT
   * @model literal="&gt;"
   * @generated
   * @ordered
   */
  public static final int GT_VALUE = 0;

  /**
   * The '<em><b>GTE</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>GTE</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #GTE
   * @model literal="&gt;="
   * @generated
   * @ordered
   */
  public static final int GTE_VALUE = 1;

  /**
   * The '<em><b>EQ</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>EQ</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #EQ
   * @model literal="=="
   * @generated
   * @ordered
   */
  public static final int EQ_VALUE = 2;

  /**
   * The '<em><b>NEQ</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>NEQ</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #NEQ
   * @model literal="!="
   * @generated
   * @ordered
   */
  public static final int NEQ_VALUE = 3;

  /**
   * The '<em><b>LT</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>LT</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #LT
   * @model literal="&lt;"
   * @generated
   * @ordered
   */
  public static final int LT_VALUE = 4;

  /**
   * The '<em><b>LTE</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>LTE</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #LTE
   * @model literal="&lt;="
   * @generated
   * @ordered
   */
  public static final int LTE_VALUE = 5;

  /**
   * An array of all the '<em><b>Relation</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final Relation[] VALUES_ARRAY =
    new Relation[]
    {
      GT,
      GTE,
      EQ,
      NEQ,
      LT,
      LTE,
    };

  /**
   * A public read-only list of all the '<em><b>Relation</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<Relation> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Relation</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param literal the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static Relation get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      Relation result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Relation</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param name the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static Relation getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      Relation result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Relation</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static Relation get(int value)
  {
    switch (value)
    {
      case GT_VALUE: return GT;
      case GTE_VALUE: return GTE;
      case EQ_VALUE: return EQ;
      case NEQ_VALUE: return NEQ;
      case LT_VALUE: return LT;
      case LTE_VALUE: return LTE;
    }
    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final int value;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final String name;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final String literal;

  /**
   * Only this class can construct instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private Relation(int value, String name, String literal)
  {
    this.value = value;
    this.name = name;
    this.literal = literal;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getLiteral()
  {
    return literal;
  }

  /**
   * Returns the literal value of the enumerator, which is its string representation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    return literal;
  }
  
} //Relation
