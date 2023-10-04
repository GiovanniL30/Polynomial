import java.util.Scanner;

/**
 * This class represents a polynomial arithmetic program that allows users to evaluate, add, subtract,
 * multiply, and divide polynomials. The program takes user input and performs operations based on the
 * chosen menu options.
 * <p>
 * Users can choose an option from the menu and perform operations on polynomials. The program handles
 * input validation and displays the results of the operations.
 *
 * @author Giovanni M. Leo
 * @since October 4, 2023
 */
public class PolynomialArithmetic {
    private final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        PolynomialArithmetic program;
        program = new PolynomialArithmetic();
        program.run();

    }

    /**
     * Runs the polynomial arithmetic program.
     *
     */
    public void run() {
        byte choice = 0;
        while (choice != 6) {
            showMenu();
            choice = readChoice((byte) 1, (byte) 6);
            switch (choice) {
                case 1 -> evaluatePolynomial();
                case 2 -> doOperation(Operations.ADD);
                case 3 -> doOperation(Operations.SUBTRACT);
                case 4 -> doOperation(Operations.MULTIPLY);
                case 5 -> doOperation(Operations.DIVIDE);
                case 6 -> System.out.println("Thank you for using this program.");
            }
        }
    }

    /**
     * Reads a choice from the user within a specified range.
     *
     * @param low  The lower bound of the valid choices.
     * @param high The upper bound of the valid choices.
     * @return The user's choice.
     */
    private byte readChoice(byte low, byte high) {
        byte choice;
        System.out.print("Enter your choice<" + low + "... " + high + ">: ");
        choice = (byte) readInteger(low, high);
        return choice;
    }

    /**
     * Displays the main menu of the polynomial arithmetic program.
     */
    public void showMenu() {
        System.out.println("-----------------------MENU--------------------------");
        System.out.println("1. Evaluate a polynomial");
        System.out.println("2. Add two polynomials");
        System.out.println("3. Subtract a polynomial from another polynomial");
        System.out.println("4. Multiply two polynomials");
        System.out.println("5. Divide a polynomial by another polynomial");
        System.out.println("6. Quit");
        System.out.println("--------------------------------------------------------");
    }

    /**
     * Allows the user to evaluate a polynomial.
     */
    public void evaluatePolynomial() {
        System.out.println("\nYou want to evaluate a polynomial.");
        Polynomial p = readPolynomial();
        System.out.println("\nThe polynomial entered is " + p.toString());
        System.out.print("What is the value to be assigned to the variable of the polynomial? ");
        double value = readDouble();

        System.out.println("\nThe polynomial evaluates to : " + p.evaluate(value));
        System.out.println("Press enter to continue.....");
        sc.nextLine();
    }

    /**
     * Reads an integer from the user within a specified range.
     *
     * @param low  The lower bound of the valid integers.
     * @param high The upper bound of the valid integers.
     * @return The user's input integer.
     */
    private int readInteger(int low, int high) {
        boolean validInput = false;
        int value = 0;
        while (!validInput) {
            try {
                value = Integer.parseInt(sc.nextLine());
                if (value < low) {
                    System.out.print("The number must not be lower than " + low + ". ");
                } else if (value > high) {
                    System.out.print("The number must not be greater than " + high + ". ");
                } else {
                    validInput = true;
                }
            } catch (Exception x) {
                System.out.println("You have to enter an integer from " + low + " to " + high + ".");
            }
        }
        return value;
    }

    /**
     * Reads a double from the user.
     *
     * @return The user's input double.
     */
    private double readDouble() {
        boolean validInput = false;
        double value = 0;
        while (!validInput) {
            try {
                value = Double.parseDouble(sc.nextLine());
                validInput = true;
            } catch (Exception x) {
                System.out.println("\nYou have to enter a number.");
            }
        }
        return value;
    }

    /**
     * Reads a polynomial from the user.
     *
     * @return The polynomial entered by the user.
     */
    public Polynomial readPolynomial() {
        Polynomial p = new Polynomial();
        int degree;
        char literalCoefficient;
        System.out.println("The polynomial should involve one variable/literal only.");

        do {
            System.out.print("What is the literal coefficient of the polynomial in one variable? ");
            literalCoefficient = sc.nextLine().charAt(0);
        } while (!Character.isAlphabetic(literalCoefficient));

        System.out.print("What is the degree of the polynomial? ");
        degree = readInteger(Integer.MIN_VALUE, Integer.MAX_VALUE);

        for (int x = degree; x >= 0; x = x - 1) {
            Term term = readTerm(literalCoefficient, x);
            p.addTerm(term);
        }
        return p;
    }

    /**
     * Reads a term of a polynomial from the user.
     *
     * @param literal The literal coefficient for the term.
     * @param degree  The degree of the term.
     * @return The term entered by the user.
     */
    public Term readTerm(char literal, int degree) {
        double nCoeff;
        System.out.print("Enter the numerical coefficient of the term with degree " + degree + ": ");
        nCoeff = readDouble();
        return new Term(nCoeff, literal, degree);
    }

    /**
     * Performs a polynomial operation (addition, subtraction, multiplication, or division) on two polynomials.
     *
     * @param operation The operation to perform.
     */
    public void doOperation(Operations operation) {
        System.out.println("\nYou want to " + operation + " two polynomials.");
        System.out.println("\nEnter the first polynomial.");
        Polynomial p1 = readPolynomial();
        System.out.println("\nEnter the second polynomial.");
        System.out.println("Note that the second polynomial should have the same variable/literal as the first polynomial.");
        Polynomial p2 = readPolynomial();
        System.out.println("\nFirst polynomial : " + p1.toString());
        System.out.println("Second polynomial : " + p2.toString());

        if (p1.getTerms().get(0).getLiteral() != p2.getTerms().get(0).getLiteral()) {
            System.out.println("\nThe two polynomials cannot be added because they have different literals.");
        } else {
            switch (operation) { // apply proper operation
                case ADD -> System.out.println("\nSum of the polynomials : " + p1.add(p2));
                case SUBTRACT -> System.out.println("\nDifference of the polynomials : " + p1.subtract(p2));
                case MULTIPLY -> System.out.println("\nProduct of the polynomials : " + p1.multiply(p2));
                case DIVIDE -> System.out.println("\nQuotient of the polynomials : " + p1.divide(p2));
            }
        }

        System.out.println("Press enter to continue.....");
        sc.nextLine();
    }

    /**
     * Constant values for Operations
     * */
    public enum Operations {
        DIVIDE, SUBTRACT, MULTIPLY, ADD
    }
}
