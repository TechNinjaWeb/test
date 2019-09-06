package org.shoppingdrugmarket;

/**
 * A medication that gets sold at Shopping Drug Market
 */
public class Medication {
	/**
	 * List of known medication types
	 */
    public static enum Types {
    	// Value represents the cost in cents
    	ANTIHISTAMINE(1),
    	DECONGESTANT(2),
    	PAINKILLER(3);
    	
    	private final int value;
    	
    	/**
    	 * Initializes the value for this enum
    	 * @param value
    	 */
    	private Types(int value) {
    		this.value = value;
    	}
    	
    	/**
    	 * Get the value stored in the enum
    	 * @return
    	 */
    	public int getValue()
        {
            return value;
        }
    }

    /*
     * Variable Declarations
     */
    private String medicationName;
    private Types medicationType;

    /**
     * A medication has a name, and a type(out of the three defined above)
     *
     * @param medicationName the name of the medication, a simple string
     * @param medicationType the type of the medication, from a defined List
     */
    public Medication(String medicationName, Types medicationType) {
        this.medicationName = medicationName;
        this.medicationType = medicationType;
    }

    /**
     * Get the medication type
     * @return this medication type's name
     */
    public Types getMedicationType() {
        return medicationType;
    }

    /**
     * Get the name of this medicaiton
     * @return
     */
    public String getMedicationName() {
        return medicationName;
    }
    
}
