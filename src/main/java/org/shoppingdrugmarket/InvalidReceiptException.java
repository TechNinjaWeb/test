/**
 * <p>
 * InvalidReceipt.java
 * 
 * Will be called if errors exist in the customer's receipt
 * that is to be printed
 * </p>
 * @author Raymond Thompson
 * @version 1.1 (September 5, 2019)
 * @since 1.0
 */
package org.shoppingdrugmarket;

/**
 * Invalidates the receipt if errors are found
 */
@SuppressWarnings("serial")
public class InvalidReceiptException extends Exception {
	/**
	 * Default constructor: just calls parent constructor
	 */
	InvalidReceiptException() { super(); }
	/**
	 * Parameterized Constructor: Takes a message and passes it to super
	 * @param message
	 */
	InvalidReceiptException(String message)  { super(message); }
}