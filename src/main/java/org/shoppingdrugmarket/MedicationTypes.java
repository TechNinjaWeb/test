/**
 * 
 */
package org.shoppingdrugmarket;

import java.util.HashMap;
import java.util.Map;


/**
 * @author techninja
 *
 */
public enum MedicationTypes {
	ANTIHISTAMINE(0),
	DECONGESTANT(1),
	PAINKILLER(2);
	
	private int value;
	private static Map<Object, Object> map = new HashMap<Object, Object>();
	
	MedicationTypes(int value) {
		this.value = value;
	}
	
	public static MedicationTypes valOf(String medicationType) {
        return (MedicationTypes) map.get(medicationType);
    }
	
	static {
        for (MedicationTypes type : MedicationTypes.values()) {
            map.put(type.value, type);
        }
    }
	
	public static MedicationTypes toInteger(String medicationType)
    {
        try {
            return valOf(medicationType);
        } 
        catch (Exception ex) {
            return null;
        }
    }
	
	public int getIndex() {
		return this.value;
	}

}