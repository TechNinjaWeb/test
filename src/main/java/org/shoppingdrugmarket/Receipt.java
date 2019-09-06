/**
 * <p>
 * Receipt.java
 * The text and html based receipt associated with a customer and their prescriptions
 * </p>
 * @author Raymond Thompson
 * @version 1.1 (September 5, 2019)
 * @since 1.0
 */
package org.shoppingdrugmarket;

import java.text.DecimalFormat;
import java.util.List;


/**
 * A text or html based representation of a customer's prescription cost
 */
public class Receipt {
	/*
	 * VARIABLE DECLARATIONS
	 */
	
	// Dependent variables
	private Customer customer;
	private List<Prescription> prescriptions;
	// Accumulators
	private double totalCost = 0.0;
	private int totalOptimalPoints = 0;
	// Output
	private String textResult = "";
	private String htmlResult = "";
	private DecimalFormat decimalFormat = new DecimalFormat("#.00");
	
	/*
	 * GETTERS AND SETTERS
	 */

	/**
	 * Set the customer to be associated with the receipt
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Store the list of the customer's prescriptions
	 * @param prescriptions the prescriptions to set
	 */
	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	/**
	 * Get this receipts generated text representation
	 * @return the textResult
	 */
	public String getTextResult() {
		return textResult;
	}

	/**
	 * Get this receipts generated html
	 * @return the htmlResult
	 */
	public String getHtmlResult() {
		return htmlResult;
	}
	
	/*
	 * CONSTRUCTORS
	 */

	/**
	 * a Receipt takes a customer and their prescriptions, sets up some accumulators
	 * for the total cost and total optimal points and genrates both 
	 * the text and html output for a receipt
	 * 
	 * @param customer an instance of the customer
	 * @param prescriptions a list of the customer's prescriptions
	 */
	Receipt(Customer customer, List<Prescription> prescriptions) {
		setCustomer(customer);
		setPrescriptions(prescriptions);

	    // Generate the required output for the receipt
	    generateReceipt();
	}
	
	/*
	 * CLASS METHODS
	 */
	
    /**
     * Calculate the cost of any one prescription
     * by applying any custom business logic
     * @param prescription
     * @return the cost associated with the prescription
     */
	public static double CalculatePrescriptionCost(Prescription prescription) {
    	double thisCost = 0.0;
    	Medication.Types type = prescription.getMedication().getMedicationType();

    	// Business Logic
    	switch(type) {        
        // apply discounts for this patient based on the medication type of the size of the prescription
        case ANTIHISTAMINE:
            int s = prescription.getSize();
            if (s > 100) {
                thisCost += s * 0.8;
            } else if (s > 50) {
                thisCost += s * 0.9;
            } else {
                thisCost += s;
            }
            break;
        case DECONGESTANT:
            thisCost += prescription.getSize() * 2;
            break;
        case PAINKILLER:
            double z = prescription.getSize();
            if (z > 200) {
                thisCost += z * 1.5;
            } else if (z > 100) {
                thisCost += z * 2;
            } else {
                thisCost += z * 3;
            }
            break;
        default:
        	break;
    }
        
        return thisCost;
    }
	
	/**
	 * Calculate the optimal points associated with any one prescription
	 * by applying any custom business logic
	 * @param prescription
	 * @return the optimal points associated with the prescription
	 */
	public static int CalculateOptimalPoints(Prescription prescription) {
    	int thisOptimalPoints = 0;
    	Medication.Types type = prescription.getMedication().getMedicationType();
    	
    	// add optimal points for future in-store redemption
        thisOptimalPoints += 100;
        // we're running a promo to give bonus optimal points for decongestants!
        if (type == Medication.Types.DECONGESTANT) {
            thisOptimalPoints += 200;
        }
        
        return thisOptimalPoints;
    }
	
	/*
	 * INSTANCE METHODS
	 */
	
    /**
     * Calculate the cost of any one prescription
     * by applying any custom business logic
     * @param prescription
     * @return the cost associated with the prescription
     */
    public double calculatePrescriptionCost(Prescription prescription) {
    	return Receipt.CalculatePrescriptionCost(prescription);
    }
    
    /**
     * Calculate the optimal points associated with any one prescription
     * by applying any custom business logic
     * @param prescription
     * @return the optimal points associated with the prescription
     */
    public int calculateOptimalPoints(Prescription prescription) {
    	return Receipt.CalculateOptimalPoints(prescription);
    }
    
    /**
     * Generates both the text and html based outputs and stores them in
     * private member variables
     */
    public void generateReceipt() {
    	// Initialize required variables
    	totalCost = 0.0;
        totalOptimalPoints = 0;
        // Initialize output variables
    	textResult = "Prescription receipt for " + customer.getName() + ":\n";
		htmlResult = "<html><body><p><h3>Prescription receipt for " + customer.getName() + ":</h3></p>";
        htmlResult += "<table><tr><th>Medication</th><th>Price</th><th>Optimal Points</th></tr>";
        
        // Process each prescription
        for (Prescription prescription : prescriptions) {
        	// cast each accumulator respectively for use in calculations
            double thisCost = calculatePrescriptionCost(prescription);
            int thisOptimalPoints = calculateOptimalPoints(prescription);
            
            // now we can add to the string, showing the cost and number of points gained per item
            textResult += "\t " + prescription.getMedication().getMedicationName() + ":\t" + decimalFormat.format(thisCost/100.0) + "\t" + String.valueOf(thisOptimalPoints) + "\n";
            htmlResult += "<tr><td>" + prescription.getMedication().getMedicationName() +
                    "</td><td>" + decimalFormat.format(thisCost/100.0) +
                    "</td><td>" + String.valueOf(thisOptimalPoints) + "</td></tr>";
            
            totalCost += thisCost/100;
            totalOptimalPoints += thisOptimalPoints;
        }
        
        // Finalize the result text and html outputs
        textResult += "Total cost:\t" + decimalFormat.format(totalCost) + "\n";
        textResult += "Total optimal points earned:\t" + String.valueOf(totalOptimalPoints) + "\n";
        
        htmlResult += "</table>";
        htmlResult += "<p>Total cost: " + decimalFormat.format(totalCost) + "</p>";
        htmlResult += "<p>Total optimal points earned: " + totalOptimalPoints + "</p></body></html>";
    }
}
