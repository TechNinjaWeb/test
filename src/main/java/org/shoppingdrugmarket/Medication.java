package org.shoppingdrugmarket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A medication that gets sold at Shopping Drug Market
 */
public class Medication {    
    @SuppressWarnings("serial")
	public static ArrayList<String> TYPES = new ArrayList<String>() {{
    	add("ANTIHISTAMINE");
    	add("DECONGESTANT");
    	add("PAINKILLER");
    }};

    private String medicationName;
    private int medicationType;
    
//    public static int GetMedicationType(String medicationType) {
//    	switch ()
//    	return Medication.MEDICATION_TYPES().indexOf(medicationType);
//    }
    
    public static int GetTypeByName(String medicationType) {
    	return Medication.TYPES.indexOf(medicationType);
    }
    
    public static String GetTypeByIndex(int medicationType) {
    	Medication.
    	return Medication.TYPES.get(medicationType);
    }

    /**
     * A medication has a name, and a type(out of the three defined above)
     *
     * @param medicationName the name of the medication, a simple string
     * @param medicationType the type of the medication, from a defined enum
     */
    public Medication(String medicationName, int medicationType) {
        this.medicationName = medicationName;
        this.medicationType = medicationType;
    }

    public int getMedicationType() {
        return medicationType;
    }

    public void setMedicationType(int newDosage) {
        medicationType = newDosage;
    }
    
    public void setMedicationType(String newDosage) {
        medicationType = Medication.TYPES.indexOf(newDosage);
    }

    public String getMedicationName() {
        return medicationName;
    }
    
}
