/**
 * Represents a polynomial with a list of terms. Each term consists of a coefficient,
 * a literal, and a degree. Provides methods to manipulate polynomials including addition,
 * subtraction, multiplication, division, and evaluation.
 *
 * @author Giovanni M. Leo
 * @since October 4, 2023
 */

import java.util.LinkedList;

public class Polynomial {
    private LinkedList<Term> terms; // data member to reference a

    public Polynomial() {
        terms = new LinkedList<>();
    }

    /**
     * Adds a term to the Polynomial, simplifying it if necessary.
     *
     * @param newTerm The term to add.
     */
    public void addTerm(Term newTerm) {

        int indexToInsert = 0; //initial index to insert or at the start

        for (Term term : this.getTerms()) {

            if (newTerm.getDegree() > term.getDegree()) { //found the index where the newTerm will be inserted
                break;
            }

            if (newTerm.getDegree() == term.getDegree()) { //evaluate if two terms have same degree
                double newCoef = newTerm.getCoefficient() + term.getCoefficient(); // compute for the new coefficient
                term.setCoefficient(newCoef);
                if (newCoef == 0) terms.remove(term); // remove the term if coefficient is zero
                return; // don't add the newTerm, end the operation
            }

            indexToInsert++; // increase the index

        }

        terms.add(indexToInsert, newTerm); // insert the newTerm depending on the index
    }


    /**
     * Evaluates the Polynomial for a given value.
     *
     * @param value The value at which to evaluate the Polynomial.
     * @return The result of the evaluation.
     */
    public double evaluate(double value) {
        double sum = 0;
        for (Term currTerm : terms) {
            sum += currTerm.getCoefficient() * Math.pow(value, currTerm.getDegree());
        }
        return sum;
    }


    /**
     * Adds another Polynomial to this Polynomial.
     *
     * @param otherPolynomial The Polynomial to add.
     * @return The resulting Polynomial after addition.
     */
    public Polynomial add(Polynomial otherPolynomial) {
        Polynomial result = new Polynomial();

        for (Term term : this.getTerms()) { // deep copy each terms of the Polynomial that invoked the method to avoid mutations
            result.getTerms().add(new Term(term.getCoefficient(), term.getLiteral(), term.getDegree()));
        }

        for (Term term : otherPolynomial.getTerms()) { // add all terms together
            result.addTerm(new Term(term.getCoefficient(), term.getLiteral(), term.getDegree()));
        }

        return result;
    }


    /**
     * Subtracts another Polynomial from this Polynomial.
     *
     * @param otherPolynomial The Polynomial to subtract.
     * @return The resulting Polynomial after subtraction.
     */
    public Polynomial subtract(Polynomial otherPolynomial) {
        Polynomial result = new Polynomial();


        for (Term term : this.getTerms()) { // deep copy each terms of the Polynomial that invoked the method to avoid mutations
            result.getTerms().add(new Term(term.getCoefficient(), term.getLiteral(), term.getDegree()));
        }

        for (Term term : otherPolynomial.getTerms()) {
            result.addTerm(new Term(term.getCoefficient() * -1, term.getLiteral(), term.getDegree())); // invert the coefficient
        }

        return result;
    }


    /**
     * Multiplies this Polynomial by another Polynomial.
     *
     * @param otherPolynomial The Polynomial to multiply by.
     * @return The resulting Polynomial after multiplication.
     */
    public Polynomial multiply(Polynomial otherPolynomial) {

        Polynomial result = new Polynomial();

        for(Term firstPolyTerm: this.getTerms()) { // loop on each Terms of the Polynomial that invoked the method


            //while looping through, multiply the current term on the iteration to the terms of the other polynomial
            for(Term secondPolyTerm: otherPolynomial.getTerms()) {
                Term newTerm = new Term();
                newTerm.setCoefficient(firstPolyTerm.getCoefficient() * secondPolyTerm.getCoefficient());
                newTerm.setDegree(firstPolyTerm.getDegree() + secondPolyTerm.getDegree());
                newTerm.setLiteral(secondPolyTerm.getLiteral());
                result.addTerm(newTerm); // add the term to the result
            }

        }

        return result;
    }


    /**
     * Divides this Polynomial by another Polynomial and returns the quotient and remainder.
     *
     * @param divisor The Polynomial to divide by.
     * @return The quotient and remainder as a Quotient object.
     */
    public Quotient divide(Polynomial divisor) {

        Quotient result = new Quotient();

        Polynomial quotient = new Polynomial();
        Polynomial remainder = new Polynomial();

        LinkedList<Term> dividend = new LinkedList<>();
        Term qTerm;
        Polynomial subtrahend = new Polynomial();

        for (int ctr = 0; ctr < this.getTerms().size(); ctr++) {
            Term currentTerm = this.getTerms().get(ctr);
            dividend.add(new Term(currentTerm.getCoefficient(), currentTerm.getLiteral(), currentTerm.getDegree()));
        }

        remainder.setTerms(dividend);

        while ( (remainder != null) && (remainder.getTerms().get(0).getDegree() >= divisor.getTerms().get(0).getDegree())) {
            Term numTerm = remainder.getTerms().get(0);
            Term divTerm = divisor.getTerms().get(0);
            qTerm = new Term(numTerm.getCoefficient() / divTerm.getCoefficient(), numTerm.getLiteral(), numTerm.getDegree() - divTerm.getDegree());
            quotient.addTerm(qTerm);
            LinkedList<Term> pQTerm = new LinkedList<>();
            pQTerm.add(qTerm);
            Polynomial multiplier = new Polynomial();
            multiplier.setTerms(pQTerm);
            subtrahend = multiplier.multiply(divisor);
            remainder = remainder.subtract(subtrahend);
        }

        if (quotient.getTerms().size() == 0) quotient.addTerm(new Term(0, 'x', 0));
        result.setQuotientP(quotient);
        if (remainder.getTerms().size() == 0) remainder.addTerm(new Term(0, 'x', 0));
        result.setRemainderP(remainder); // Invoke appropriate method to set remainder member of quotient
        return result;
    }


    /**
     * Returns the list of terms in the Polynomial.
     *
     * @return The list of terms.
     */
    public LinkedList<Term> getTerms() {
        return terms;
    }


    /**
     * Sets the list of terms in the Polynomial.
     *
     * @param t The list of terms to set.
     */
    public void setTerms(LinkedList<Term> t) {
        terms = t;
    }

    /**
     * Returns a string representation of the Polynomial.
     *
     * @return A string representation of the Polynomial.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        if (terms == null) return " ";
        for (int ctr = 0; ctr < terms.size(); ctr++) {
            Term currTerm = terms.get(ctr);

            if (currTerm.getCoefficient() > 0) {
                if (ctr != 0) {
                    s.append(" +");
                }
            } else {
                s.append(" -");
            }
            if (currTerm.getCoefficient() != 1 || currTerm.getDegree() == 0) {
                s.append(" ").append(Math.abs(currTerm.getCoefficient()));
            }
            switch (currTerm.getDegree()) {
                case 0:
                    break;
                case 1:
                    s.append((terms.get(0)).getLiteral());
                    break;
                default:
                    s.append((terms.get(0)).getLiteral()).append("^").append(currTerm.getDegree());
            }
        }
        return s.toString();
    }
}