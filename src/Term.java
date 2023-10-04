/**
 * Represents a term in a polynomial equation.
 */
public class Term implements Comparable<Term> {
    private double coefficient; // Data member to hold numerical coefficient of a term
    private int degree; // Data member to hold the degree of a term
    private char literal; // Data member to hold the literal coefficient of a term

    /**
     * Constructs a term with a coefficient of 0, degree of 0, and a literal of 'x'.
     */
    public Term() {
        coefficient = 0;
        degree = 0;
        literal = 'x';
    }

    /**
     * Constructs a term with the specified coefficient, literal, and degree.
     *
     * @param coef    The numerical coefficient of the term.
     * @param literal The literal coefficient of the term.
     * @param degree  The degree of the term.
     */
    public Term(double coef, char literal, int degree) {
        this.coefficient = coef;
        this.literal = literal;
        this.degree = degree;
    }

    /**
     * Sets the numerical coefficient of this term.
     *
     * @param coef The numerical coefficient to set.
     */
    public void setCoefficient(double coef) {
        this.coefficient = coef;
    }

    /**
     * Sets the literal coefficient of this term.
     *
     * @param literal The literal coefficient to set.
     */
    public void setLiteral(char literal) {
        this.literal = literal;
    }

    /**
     * Sets the degree of this term.
     *
     * @param degree The degree to set.
     */
    public void setDegree(int degree) {
        this.degree = degree;
    }

    /**
     * Returns the numerical coefficient of this term.
     *
     * @return The numerical coefficient.
     */
    public double getCoefficient() {
        return this.coefficient;
    }

    /**
     * Returns the literal coefficient of this term.
     *
     * @return The literal coefficient.
     */
    public char getLiteral() {
        return this.literal;
    }

    /**
     * Returns the degree of this term.
     *
     * @return The degree.
     */
    public int getDegree() {
        return this.degree;
    }

    /**
     * Compares this term to another term based on their degrees.
     * Returns 0 if the degrees are equal, a positive integer if the degree
     * of this term is greater, and a negative integer if the degree of this term
     * is lesser than the other term's degree.
     *
     * @param another The term to compare to.
     * @return 0 if degrees are equal, positive integer if greater, negative integer if lesser.
     */
    @Override
    public int compareTo(Term another) {
        if (this.getDegree() == another.getDegree()) {
            return 0;
        } else if (this.getDegree() > another.getDegree()) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * Returns a string representation of the term in the format, e.g., "3x^2".
     *
     * @return The string representation of the term.
     */
    @Override
    public String toString() {
        if (coefficient == 0) {
            return "";
        } else if (coefficient == 1 && degree == 1) {
            return "" + literal;
        } else if (coefficient == 1 && degree != 1) {
            return "" + literal + "^" + degree;
        } else {
            return (coefficient + literal + "^" + degree);
        }
    }
}
