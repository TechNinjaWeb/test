package org.shoppingdrugmarket;

import java.util.ArrayList;

/**
 * A customer of Shopping Drug Market
 */
public class Customer {
    private String name;
    private ArrayList<Prescription> prescriptions = new ArrayList<Prescription>();

    /**
     * A customer must have a name
     *
     * @param name a string defining the customer's name
     */
    public Customer(String name) {
        this.name = name;
    }

    /**
     * When a customer needs to add a prescription to their list of prescriptions
     *
     * @param newPrescription the new prescription being added for the customer
     */
    public void addNewPrescription(Prescription newPrescription) {
        prescriptions.add(newPrescription);
    }

    
    /**
     * Get the number of prescriptions saved
     * @return
     */
    public int countPrescriptions() {
    	return prescriptions.size();
    }
    
    /**
     * Get the customer's name
     * @return
     */
    public String getName() {
        return name;
    }
    
    /**
     * Get a list of prescriptions
     * @return
     */
    public ArrayList<Prescription> getPrescriptions() {
    	return prescriptions;
    }

    /**
     * Generate a plain text receipt for the customer's purchases
     *
     * @return a string containing the items purchased, their cost and the number of points the customer received
     */
    public String generatePrescriptionReceiptText() {    	
    	Receipt receipt = new Receipt(this, prescriptions);
        return receipt.getTextResult();
    }

    /**
     * Generate an HTML receipt for e-mailing to the customer
     *
     * @return an html string containing the items purchased, their cost and the number of points the customer received
     * @throws InvalidReceiptException 
     */
    public String generatePrescriptionReceiptHtml() {
    	Receipt receipt = new Receipt(this, prescriptions);
        return receipt.getHtmlResult();
    }

}
