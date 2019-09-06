package org.shoppingdrugmarket;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

import java.text.DecimalFormat;

import static org.hamcrest.Matchers.equalToIgnoringCase;

public class ReceiptTest {

	DecimalFormat decimalFormat = new DecimalFormat("#.00");
	
    @Test
    public void testReceipt() {
        Medication medication = new Medication("Advil", Medication.Types.PAINKILLER);
        Prescription prescription = new Prescription(medication, 150);
        Customer customer = new Customer("John Smith");

        customer.addNewPrescription(prescription);
        String receipt = customer.generatePrescriptionReceiptText();
        
        String calculatedCost = decimalFormat.format(Receipt.CalculatePrescriptionCost(prescription) / 100.0);
        int calculatedOptimalPoints = Receipt.CalculateOptimalPoints(prescription);

        Assert.assertThat(receipt, is(equalToIgnoringCase(
        		"Prescription receipt for "+ customer.getName() + ":\n\t Advil:\t" + calculatedCost + "\t" + calculatedOptimalPoints + "\nTotal cost:\t" + calculatedCost + "\nTotal optimal points earned:\t" + calculatedOptimalPoints + "\n"
        )));
    }

    @Test
    public void testHtmlReceipt() {
        Medication medication = new Medication("Advil", Medication.Types.PAINKILLER);
        Prescription prescription = new Prescription(medication, 150);
        Customer customer = new Customer("John Smith");

        customer.addNewPrescription(prescription);
        String receipt = customer.generatePrescriptionReceiptHtml();
        
        String calculatedCost = decimalFormat.format(Receipt.CalculatePrescriptionCost(prescription) / 100.0);
        int calculatedOptimalPoints = Receipt.CalculateOptimalPoints(prescription);

        Assert.assertThat(receipt, is(equalToIgnoringCase(
        		"<html><body><p><h3>Prescription receipt for "+ customer.getName() + ":</h3></p><table><tr><th>Medication</th><th>Price</th><th>Optimal Points</th></tr><tr><td>Advil</td><td>" + calculatedCost + "</td><td>" + calculatedOptimalPoints + "</td></tr></table><p>Total cost: " + calculatedCost + "</p><p>Total optimal points earned: " + calculatedOptimalPoints + "</p></body></html>"
        )));
    }

}
