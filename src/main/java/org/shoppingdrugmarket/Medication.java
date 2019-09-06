package org.shoppingdrugmarket;

import java.util.ArrayList;


/**
 * A medication that gets sold at Shopping Drug Market
 */
public class Medication {
	/**
	 * List of known medication types
	 */
    @SuppressWarnings("serial")
	public static final ArrayList<String> TYPES = new ArrayList<String>() {{
    	add("ANTIHISTAMINE");
    	add("DECONGESTANT");
    	add("PAINKILLER");
    }};

    /*
     * Variable Declarations
     */
    private String medicationName;
    private String medicationType;
    
    /**
     * Get the medication's index using it's name
     * @param medicationType an index for the type to match
     * @return the medication type's name
     */
    public static int GetTypeByName(String medicationType) {
    	return Medication.TYPES.indexOf(medicationType);
    }
    
    /**
     * Get the medication's name using it's index
     * @param medicationType a name for the type to match
     * @return the medication type's index
     */
    public static String GetTypeByIndex(int medicationType) {
    	return Medication.TYPES.get(medicationType);
    }

    /**
     * A medication has a name, and a type(out of the three defined above)
     *
     * @param medicationName the name of the medication, a simple string
     * @param medicationType the type of the medication, from a defined List
     */
    public Medication(String medicationName, int medicationType) {
        this.medicationName = medicationName;
        this.medicationType = TYPES.get(medicationType);
    }

    /**
     * Get the medication type
     * @return this medication type's name
     */
    public String getMedicationType() {
        return medicationType;
    }

    /**
     * Set the medication type
     * @param newDosage the new medication type's number to set
     */
    public void setMedicationType(int newDosage) {
        medicationType = TYPES.get(newDosage);
    }
    
    /**
     * Set the medication type to a string
     * @param newDosage
     */
    public void setMedicationType(String newDosage) {
        medicationType = TYPES.contains(newDosage) ? newDosage : null;
    }

    public String getMedicationName() {
        return medicationName;
    }
    
}
