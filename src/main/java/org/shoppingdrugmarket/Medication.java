package org.shoppingdrugmarket;

import java.util.ArrayList;


/**
 * A medication that gets sold at Shopping Drug Market
 */
public class Medication {
	/**
	 * List of known medication types
	 */
//    @SuppressWarnings("serial")
//	public static final ArrayList<String> TYPES = new ArrayList<String>() {{
//    	add("ANTIHISTAMINE");
//    	add("DECONGESTANT");
//    	add("PAINKILLER");
//    }};
    
    public static enum TYPES {
    	ANTIHISTAMINE,
    	DECONGESTANT,
    	PAINKILLER;
    }

    /*
     * Variable Declarations
     */
    private String medicationName;
    private TYPES medicationType;

    /**
     * A medication has a name, and a type(out of the three defined above)
     *
     * @param medicationName the name of the medication, a simple string
     * @param medicationType the type of the medication, from a defined List
     */
    public Medication(String medicationName, TYPES medicationType) {
        this.medicationName = medicationName;
        this.medicationType = medicationType;
    }

    /**
     * Get the medication type
     * @return this medication type's name
     */
    public TYPES getMedicationType() {
        return medicationType;
    }

    public String getMedicationName() {
        return medicationName;
    }
    
}
