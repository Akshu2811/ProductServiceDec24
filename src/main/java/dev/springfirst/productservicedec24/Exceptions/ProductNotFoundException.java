package dev.springfirst.productservicedec24.Exceptions;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String message) {
        super(message);
    }
    /* Types of exceptions -
    Checked Exception - compile time exceptions
    Unchecked Exception - runtime exceptions
     */
}
