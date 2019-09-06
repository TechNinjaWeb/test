/**
 * <p>
 * GithubTestCases.java
 * Testing the program using conditions from the github repository
 * </p>
 * 
 * @author Raymond Thompson
 * @version 1.1 (September 5, 2019)
 * @since 1.0
 * @repositoryhttps://github.com/ehealthinnovation/techtest-java
 */
package org.shoppingdrugmarket;

import org.junit.Assert;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static org.hamcrest.Matchers.equalToIgnoringCase;

public class GithubTestCases {
	/*
	 * CONSTANTS DECLARATIONS
	 */
	
	// Represents the cents due to rounding must be integer
	final int ANTIHISTAMINE_COST = 1;
	final int DECONGESTANT_COST = 2;
	final int PAINKILLER_COST = 3;

	/**
	 * <p>
	 * Test the Medication for:
	 * 	- a name
	 * 	- a type
	 * </p>
	 */
    @Test
    public void testMedication() {
    	String name = "Advil";
    	Medication medication;
    	
//    	Medication, which has a name and type of a stocked medication.
    	medication = new Medication(name, Medication.PAINKILLER);

    	// Medication has a name
        Assert.assertThat(medication.getMedicationName(), equalToIgnoringCase(name)); 

    	medication = new Medication("", Medication.PAINKILLER);    	
        Assert.assertThat(medication.getMedicationName(), equalToIgnoringCase(""));
        
        // Medication has a type
        Assert.assertEquals(medication.getMedicationType() >= 0, true);
    }

	/**
	 * <p>
	 * Test the Prescription for:
	 * 	- a medication
	 * 	- a medication type
	 * 	- The standard price of antihistamines is 1 cents per unit
	 * 	- The standard price of decongestants is 2 cents per unit
	 * 	- The standard price of pain killers is 3 cent per unit
	 * </p>
	 */
    @Test
    public void testPrescription() throws InvalidReceiptException {
    	String name = "Fake Drug Name";
    	int type;
    	int qty;
    	boolean condition;
    	
    	Medication medication;
        Prescription prescription;
        ArrayList<Prescription> prescriptions;
        
//    	Prescription, which has a medication and the number of units of medication that have been prescribed.
        type = Medication.ANTIHISTAMINE;
        medication = new Medication(name, type);
        prescription = new Prescription(medication, 221);
        
        condition = prescription.getMedication() instanceof Medication;
        Assert.assertTrue(condition);
        condition = prescription.getSize() > 0;
        Assert.assertTrue(condition);
        
        
//    	The standard price of antihistamines is 1 cents per unit
        type = Medication.ANTIHISTAMINE;
        qty = 1;

    	medication = new Medication(name, type);
        prescription = new Prescription(medication, qty);
        
        condition = Receipt.CalculatePrescriptionCost(prescription) == ANTIHISTAMINE_COST;
        Assert.assertEquals(condition, true);
        
        
//    	The standard price of decongestants is 2 cents per unit
        type = Medication.DECONGESTANT;
        qty = 1;
        		
    	medication = new Medication(name, type);
        prescription = new Prescription(medication, qty);
        prescriptions = new ArrayList<Prescription>();
        
        prescriptions.add(prescription);
        
        condition = Receipt.CalculatePrescriptionCost(prescription) == DECONGESTANT_COST;
        Assert.assertEquals(condition, true);
        
        
        
//    	The standard price of pain killers is 3 cent per unit
        type = Medication.PAINKILLER;
        qty = 1;
        		
    	medication = new Medication(name, type);
        prescription = new Prescription(medication, qty);
        
        condition = Receipt.CalculatePrescriptionCost(prescription) == PAINKILLER_COST;
        Assert.assertEquals(condition, true);
    }
    
    
    
	/**
	 * <p>
	 * Test the Customer for:
	 * 	- a medication
	 * 	- the number of units of medication that have been prescribed
	 * 	- buy more than 50 units of antihistamines, get a 10% discount off the standard price
	 * 	- buy more than 100 units of antihistamines, get a 20% discount off the standard price
	 * 	- buy more than 100 units of painkillers, get a 33% discount off the standard price
	 *  - buy more than 200 units of painkillers, get a 50% discount off the standard price
	 * </p>
	 */
    @Test
    public void testCustomer() throws InvalidReceiptException {
    	DecimalFormat decimalFormat = new DecimalFormat("#.00");
    	
    	String customerName = "John Smith";
    	String drugName = "Fake Drug";
    	int type;
    	int qty;
    	boolean condition;
    	
    	Customer customer;
    	Medication medication;
        Prescription prescription;
        
        
//    	Customer, which has a name and a list of prescriptions        
        customer = new Customer(customerName);
        customer.addNewPrescription(new Prescription(new Medication("Fake Meds 1", Medication.ANTIHISTAMINE), 1));
        customer.addNewPrescription(new Prescription(new Medication("Fake Meds 2", Medication.DECONGESTANT), 1));
        customer.addNewPrescription(new Prescription(new Medication("Fake Meds 3", Medication.PAINKILLER), 1));

        condition = customer.getName() != "";
        Assert.assertTrue(condition);
        
        condition = customer.getName().equals(customerName);
        Assert.assertTrue(condition);
        
        condition = customer.countPrescriptions() > 0;
        Assert.assertTrue(condition);
        
        
        
//    	When a customer buys more than 50 units of antihistamines, they get a 10% discount off the standard price
        customer = new Customer(customerName);
        type = Medication.ANTIHISTAMINE;
        qty = 51;
        		
    	medication = new Medication(drugName, type);
        prescription = new Prescription(medication, qty);
        
        condition = decimalFormat.format(Receipt.CalculatePrescriptionCost(prescription) / 100.0).equalsIgnoreCase(decimalFormat.format( ((ANTIHISTAMINE_COST * qty) * 0.9) / 100.0));
        Assert.assertTrue(condition);
        
        qty = 50;
        condition = decimalFormat.format(Receipt.CalculatePrescriptionCost(prescription) / 100.0).equalsIgnoreCase(decimalFormat.format( ((ANTIHISTAMINE_COST * qty) * 0.9) / 100.0));
        Assert.assertFalse(condition);
        
        
        
//    	When a customer buys more than 100 units of antihistamines, they get a 20% discount off the standard price
        customer = new Customer(customerName);
        type = Medication.ANTIHISTAMINE;
        qty = 101;
        		
    	medication = new Medication(drugName, type);
        prescription = new Prescription(medication, qty);
        
        condition = decimalFormat.format(Receipt.CalculatePrescriptionCost(prescription) / 100.0).equalsIgnoreCase(decimalFormat.format( ((ANTIHISTAMINE_COST * qty) * 0.8) / 100.0));
        Assert.assertTrue(condition);
        
        qty = 100;
        condition = decimalFormat.format(Receipt.CalculatePrescriptionCost(prescription) / 100.0).equalsIgnoreCase(decimalFormat.format( ((ANTIHISTAMINE_COST * qty) * 0.8) / 100.0));
        Assert.assertFalse(condition);
        
        
//    	When a customer buys more than 100 units of painkillers, they get a 33% discount off the standard price
        customer = new Customer(customerName);
        type = Medication.PAINKILLER;
        qty = 101;
        		
    	medication = new Medication(drugName, type);
        prescription = new Prescription(medication, qty);
        
        condition = decimalFormat.format(Receipt.CalculatePrescriptionCost(prescription) / 100.0).equalsIgnoreCase(decimalFormat.format( (qty * (int)(PAINKILLER_COST * 0.67)) / 100.0) );
        Assert.assertTrue(condition);
        
        qty = 100;
        condition = decimalFormat.format(Receipt.CalculatePrescriptionCost(prescription) / 100.0).equalsIgnoreCase(decimalFormat.format( (qty * (int)(PAINKILLER_COST * 0.67)) / 100.0) );
        Assert.assertFalse(condition);
        
        
//    	When a customer buys more than 200 units of painkillers, they get a 50% discount off the standard price
        customer = new Customer(customerName);
        type = Medication.PAINKILLER;
        qty = 201;
        		
    	medication = new Medication(drugName, type);
        prescription = new Prescription(medication, qty);
        
        condition = decimalFormat.format(Receipt.CalculatePrescriptionCost(prescription) / 100.0).equalsIgnoreCase(decimalFormat.format( (qty * (PAINKILLER_COST * 0.5)) / 100.0) );
        Assert.assertTrue(condition);
        
        
//    	Any purchase receives 100 Optimal points, but buying decongestants gets a bonus 200 Optimal points
        customer = new Customer(customerName);
        type = Medication.ANTIHISTAMINE;
        qty = 15;
        		
    	medication = new Medication(drugName, type);
        prescription = new Prescription(medication, qty);
        
        condition = Receipt.CalculateOptimalPoints(prescription) == 100;
        Assert.assertTrue(condition);


        customer = new Customer(customerName);
        type = Medication.PAINKILLER;
        qty = 21;
        		
    	medication = new Medication(drugName, type);
        prescription = new Prescription(medication, qty);
        
        condition = Receipt.CalculateOptimalPoints(prescription) == 100;
        Assert.assertTrue(condition);
        
        customer = new Customer(customerName);
        type = Medication.DECONGESTANT;
        qty = 1;
        		
    	medication = new Medication(drugName, type);
        prescription = new Prescription(medication, qty);
        
        condition = Receipt.CalculateOptimalPoints(prescription) == 300;
        Assert.assertTrue(condition);
    }

}