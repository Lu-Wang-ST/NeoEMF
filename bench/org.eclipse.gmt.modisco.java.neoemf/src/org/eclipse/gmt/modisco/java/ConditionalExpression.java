/**
 */
package org.eclipse.gmt.modisco.java;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Conditional Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.ConditionalExpression#getElseExpression <em>Else Expression</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.ConditionalExpression#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.ConditionalExpression#getThenExpression <em>Then Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.gmt.modisco.java.neoemf.meta.JavaPackage#getConditionalExpression()
 * @model
 * @generated
 */
public interface ConditionalExpression extends Expression {
    /**
     * Returns the value of the '<em><b>Else Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Else Expression</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Else Expression</em>' containment reference.
     * @see #setElseExpression(Expression)
     * @see org.eclipse.gmt.modisco.java.neoemf.meta.JavaPackage#getConditionalExpression_ElseExpression()
     * @model containment="true" required="true" ordered="false"
     * @generated
     */
    Expression getElseExpression();

    /**
     * Sets the value of the '{@link org.eclipse.gmt.modisco.java.ConditionalExpression#getElseExpression <em>Else Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Else Expression</em>' containment reference.
     * @see #getElseExpression()
     * @generated
     */
    void setElseExpression(Expression value);

    /**
     * Returns the value of the '<em><b>Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Expression</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Expression</em>' containment reference.
     * @see #setExpression(Expression)
     * @see org.eclipse.gmt.modisco.java.neoemf.meta.JavaPackage#getConditionalExpression_Expression()
     * @model containment="true" required="true" ordered="false"
     * @generated
     */
    Expression getExpression();

    /**
     * Sets the value of the '{@link org.eclipse.gmt.modisco.java.ConditionalExpression#getExpression <em>Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Expression</em>' containment reference.
     * @see #getExpression()
     * @generated
     */
    void setExpression(Expression value);

    /**
     * Returns the value of the '<em><b>Then Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Then Expression</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Then Expression</em>' containment reference.
     * @see #setThenExpression(Expression)
     * @see org.eclipse.gmt.modisco.java.neoemf.meta.JavaPackage#getConditionalExpression_ThenExpression()
     * @model containment="true" required="true" ordered="false"
     * @generated
     */
    Expression getThenExpression();

    /**
     * Sets the value of the '{@link org.eclipse.gmt.modisco.java.ConditionalExpression#getThenExpression <em>Then Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Then Expression</em>' containment reference.
     * @see #getThenExpression()
     * @generated
     */
    void setThenExpression(Expression value);

} // ConditionalExpression
