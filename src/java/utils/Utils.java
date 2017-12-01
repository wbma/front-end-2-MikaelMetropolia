
package utils;

import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Class containing useful utility functions, for use in all possible places...
 * @author Ville L
 */
public class Utils {
    
    
        // helper method to convert 'findByX()' call result to boolean form
    public static boolean notNull(Object o) {
    
        return !(o == null);
    }
    
    // helper method to easily set a single-line status field for a json (response) object
    public static JSONObject setResponseStatus(String statusMsg) {
        
        try {
            return new JSONObject()
           .put("status", statusMsg);
        } catch (Exception e) {
            return null;
        }
    }
    
    // A way to tuck the pesky mandatory try-catch statement away from the actual class files...
    public static JSONObject putJson(JSONObject j, String key, Object value) {
        
        try {
            return new JSONObject()
           .put(key, value);
        } catch (Exception e) {
            return null;
        }
    } // end putJson()
} // end class
