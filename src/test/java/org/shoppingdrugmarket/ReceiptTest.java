package org.shoppingdrugmarket;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

import java.text.DecimalFormat;

import static org.hamcrest.Matchers.equalToIgnoringCase;

public class ReceiptTest {

	DecimalFormat decimalFormat = new DecimalFormat("#.00");
	
    @Test
    public void testReceipt() throws InvalidReceipt {
        Medication medication = new Medication("Advil", Medication.PAINKILLER);
        Prescription prescription = new Prescription(medication, 150);
        Customer customer = new Customer("John Smith");

        customer.addNewPrescription(prescription);
        String receipt = customer.generatePrescriptionReceiptText();
        
        Receipt r = new Receipt();
        String calculatedCost = decimalFormat.format(r.calculatePrescriptionCost(prescription) / 100.0);
        int calculatedOptimalPoints = r.calculateOptimalPoints(prescription);

        Assert.assertThat(receipt, is(equalToIgnoringCase(
        		"Prescription receipt for "+ customer.getName() + ":\n\t Advil:\t" + calculatedCost + "\t" + calculatedOptimalPoints + "\nTotal cost:\t" + calculatedCost + "\nTotal optimal points earned:\t" + calculatedOptimalPoints + "\n"
        )));
    }

    @Test
    public void testHtmlReceipt() throws InvalidReceipt {
        Medication medication = new Medication("Advil", Medication.PAINKILLER);
        Prescription prescription = new Prescription(medication, 150);
        Customer customer = new Customer("John Smith");

        customer.addNewPrescription(prescription);
        String receipt = customer.generatePrescriptionReceiptHtml();
        
        Receipt r = new Receipt();
        String calculatedCost = decimalFormat.format(r.calculatePrescriptionCost(prescription) / 100.0);
        int calculatedOptimalPoints = r.calculateOptimalPoints(prescription);

        Assert.assertThat(receipt, is(equalToIgnoringCase(
        		"<html><body><p><h3>Prescription receipt for "+ customer.getName() + ":</h3></p><table><tr><th>Medication</th><th>Price</th><th>Optimal Points</th></tr><tr><td>Advil</td><td>" + calculatedCost + "</td><td>" + calculatedOptimalPoints + "</td></tr></table><p>Total cost: " + calculatedCost + "</p><p>Total optimal points earned: " + calculatedOptimalPoints + "</p></body></html>"
        )));
    }

}
