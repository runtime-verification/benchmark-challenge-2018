/**
 * generated by Xtext 2.14.0
 */
package edu.ltl.wallin.lTL;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Binary Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link edu.ltl.wallin.lTL.BinaryExpr#getLeft <em>Left</em>}</li>
 *   <li>{@link edu.ltl.wallin.lTL.BinaryExpr#getOp <em>Op</em>}</li>
 *   <li>{@link edu.ltl.wallin.lTL.BinaryExpr#isEnd <em>End</em>}</li>
 *   <li>{@link edu.ltl.wallin.lTL.BinaryExpr#getRight <em>Right</em>}</li>
 * </ul>
 *
 * @see edu.ltl.wallin.lTL.LTLPackage#getBinaryExpr()
 * @model
 * @generated
 */
public interface BinaryExpr extends Formula
{
  /**
   * Returns the value of the '<em><b>Left</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Left</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Left</em>' containment reference.
   * @see #setLeft(Formula)
   * @see edu.ltl.wallin.lTL.LTLPackage#getBinaryExpr_Left()
   * @model containment="true"
   * @generated
   */
  Formula getLeft();

  /**
   * Sets the value of the '{@link edu.ltl.wallin.lTL.BinaryExpr#getLeft <em>Left</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Left</em>' containment reference.
   * @see #getLeft()
   * @generated
   */
  void setLeft(Formula value);

  /**
   * Returns the value of the '<em><b>Op</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Op</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Op</em>' attribute.
   * @see #setOp(String)
   * @see edu.ltl.wallin.lTL.LTLPackage#getBinaryExpr_Op()
   * @model
   * @generated
   */
  String getOp();

  /**
   * Sets the value of the '{@link edu.ltl.wallin.lTL.BinaryExpr#getOp <em>Op</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Op</em>' attribute.
   * @see #getOp()
   * @generated
   */
  void setOp(String value);

  /**
   * Returns the value of the '<em><b>End</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>End</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>End</em>' attribute.
   * @see #setEnd(boolean)
   * @see edu.ltl.wallin.lTL.LTLPackage#getBinaryExpr_End()
   * @model
   * @generated
   */
  boolean isEnd();

  /**
   * Sets the value of the '{@link edu.ltl.wallin.lTL.BinaryExpr#isEnd <em>End</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>End</em>' attribute.
   * @see #isEnd()
   * @generated
   */
  void setEnd(boolean value);

  /**
   * Returns the value of the '<em><b>Right</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Right</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Right</em>' containment reference.
   * @see #setRight(Formula)
   * @see edu.ltl.wallin.lTL.LTLPackage#getBinaryExpr_Right()
   * @model containment="true"
   * @generated
   */
  Formula getRight();

  /**
   * Sets the value of the '{@link edu.ltl.wallin.lTL.BinaryExpr#getRight <em>Right</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Right</em>' containment reference.
   * @see #getRight()
   * @generated
   */
  void setRight(Formula value);

} // BinaryExpr
