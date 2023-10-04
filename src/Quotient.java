/**
 * Represents the quotient and remainder of a polynomial division operation.
 */
public class Quotient {
    private Polynomial quotientP;
    private Polynomial remainderP;

    /**
     * Initializes an empty Quotient object with null quotient and remainder Polynomials.
     */
    public Quotient() {
        quotientP = null;
        remainderP = null;
    }

    /**
     * Sets the quotient Polynomial.
     *
     * @param q The quotient Polynomial to set.
     */
    public void setQuotientP(Polynomial q) {
        quotientP = q;
    }

    /**
     * Sets the remainder Polynomial.
     *
     * @param p The remainder Polynomial to set.
     */
    public void setRemainderP(Polynomial p) {
        remainderP = p;
    }

    /**
     * Gets the quotient Polynomial.
     *
     * @return The quotient Polynomial.
     */
    public Polynomial getQuotientP() {
        return quotientP;
    }

    /**
     * Gets the remainder Polynomial.
     *
     * @return The remainder Polynomial.
     */
    public Polynomial getRemainderP() {
        return remainderP;
    }

    /**
     * Returns a string representation of the Quotient object, including the quotient and remainder Polynomials.
     *
     * @return A string representation of the Quotient object.
     */
    public String toString() {
        return (" Quotient: " + quotientP.toString() + " Remainder: " + remainderP.toString());
    }
}
