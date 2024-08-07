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
   * The '<em><b>NONE</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #NONE_VALUE
   * @generated
   * @ordered
   */
  NONE(0, "NONE", "_"),

  /**
   * The '<em><b>GT</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #GT_VALUE
   * @generated
   * @ordered
   */
  GT(1, "GT", ">"),

  /**
   * The '<em><b>GTE</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #GTE_VALUE
   * @generated
   * @ordered
   */
  GTE(2, "GTE", ">="),

  /**
   * The '<em><b>EQ</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #EQ_VALUE
   * @generated
   * @ordered
   */
  EQ(3, "EQ", "=="),

  /**
   * The '<em><b>NEQ</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #NEQ_VALUE
   * @generated
   * @ordered
   */
  NEQ(4, "NEQ", "!="),

  /**
   * The '<em><b>LT</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #LT_VALUE
   * @generated
   * @ordered
   */
  LT(5, "LT", "<"),

  /**
   * The '<em><b>LTE</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #LTE_VALUE
   * @generated
   * @ordered
   */
  LTE(6, "LTE", "<="),

  /**
   * The '<em><b>FB</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #FB_VALUE
   * @generated
   * @ordered
   */
  FB(7, "FB", "forbids"),

  /**
   * The '<em><b>RQ</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #RQ_VALUE
   * @generated
   * @ordered
   */
  RQ(8, "RQ", "requires"),

  /**
   * The '<em><b>IN</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #IN_VALUE
   * @generated
   * @ordered
   */
  IN(9, "IN", "in");

  /**
   * The '<em><b>NONE</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #NONE
   * @model literal="_"
   * @generated
   * @ordered
   */
  public static final int NONE_VALUE = 0;

  /**
   * The '<em><b>GT</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #GT
   * @model literal="&gt;"
   * @generated
   * @ordered
   */
  public static final int GT_VALUE = 1;

  /**
   * The '<em><b>GTE</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #GTE
   * @model literal="&gt;="
   * @generated
   * @ordered
   */
  public static final int GTE_VALUE = 2;

  /**
   * The '<em><b>EQ</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #EQ
   * @model literal="=="
   * @generated
   * @ordered
   */
  public static final int EQ_VALUE = 3;

  /**
   * The '<em><b>NEQ</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #NEQ
   * @model literal="!="
   * @generated
   * @ordered
   */
  public static final int NEQ_VALUE = 4;

  /**
   * The '<em><b>LT</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #LT
   * @model literal="&lt;"
   * @generated
   * @ordered
   */
  public static final int LT_VALUE = 5;

  /**
   * The '<em><b>LTE</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #LTE
   * @model literal="&lt;="
   * @generated
   * @ordered
   */
  public static final int LTE_VALUE = 6;

  /**
   * The '<em><b>FB</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #FB
   * @model literal="forbids"
   * @generated
   * @ordered
   */
  public static final int FB_VALUE = 7;

  /**
   * The '<em><b>RQ</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #RQ
   * @model literal="requires"
   * @generated
   * @ordered
   */
  public static final int RQ_VALUE = 8;

  /**
   * The '<em><b>IN</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #IN
   * @model literal="in"
   * @generated
   * @ordered
   */
  public static final int IN_VALUE = 9;

  /**
   * An array of all the '<em><b>Relation</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final Relation[] VALUES_ARRAY =
    new Relation[]
    {
      NONE,
      GT,
      GTE,
      EQ,
      NEQ,
      LT,
      LTE,
      FB,
      RQ,
      IN,
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
      case NONE_VALUE: return NONE;
      case GT_VALUE: return GT;
      case GTE_VALUE: return GTE;
      case EQ_VALUE: return EQ;
      case NEQ_VALUE: return NEQ;
      case LT_VALUE: return LT;
      case LTE_VALUE: return LTE;
      case FB_VALUE: return FB;
      case RQ_VALUE: return RQ;
      case IN_VALUE: return IN;
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
  @Override
  public int getValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
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
