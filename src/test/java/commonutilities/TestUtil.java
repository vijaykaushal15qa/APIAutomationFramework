package commonutilities;

import Listeners.ExtentListeners;
import org.json.JSONObject;


public class TestUtil {



    public static boolean jsonHasKey(String json,String key) {

        JSONObject jsonObject = new JSONObject(json);
        ExtentListeners.testReport.get().info("Validating the presence of Key : "+key);

        return jsonObject.has(key);// Validating the presence of key passed in arguments while calling in the Json Response.

    }


    public static String getJsonKeyValue(String json, String key) {

        JSONObject jsonObject = new JSONObject(json);
        ExtentListeners.testReport.get().info("Validating Value of Key : "+key);

        return jsonObject.get(key).toString();// Returning the value of key passed in argument.

    }


}
