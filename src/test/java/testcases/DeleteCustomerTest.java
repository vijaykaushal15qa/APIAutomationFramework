package testcases;
import java.util.Hashtable;
import Listeners.ExtentListeners;
import api.DeleteCustomerAPI;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import commonutilities.*;
import io.restassured.response.Response;

public class DeleteCustomerTest extends BaseTest {
    @Test(dataProviderClass = DataUtil.class, dataProvider = "data")
    public void deleteCustomer(Hashtable<String, String> data) {
        Response response = DeleteCustomerAPI.sendDeleteRequestToCreateCustomerAPIWithValidID(data);
        response.prettyPrint();

        ExtentListeners.testReport.get().info(data.toString());
        System.out.println(response.statusCode());

        // One way to Fetch id value from Json Response.
        /*
        String actualId=response.jsonPath().get("id").toString();
        Assert.assertEquals(actualId,data.get("id"),"ID not matching");
        */

       // Assert.assertEquals(response.statusCode(), 200);

/*// Checking that id is present in response using JSON Object calss.
        JSONObject jsonObject=new JSONObject(response.toString());
        System.out.println(jsonObject.has("id"));
        Assert.assertTrue(jsonObject.has("id"),"ID Key is Present in the JSON Response");
*/
        // Checking the presence of Object and Deleted Keys using jsonHashKey method from Test Util.
        System.out.println("Presence check for Object Key : "+TestUtil.jsonHasKey(response.asString(), "object"));
        System.out.println("Presence check for Deleted Key : "+TestUtil.jsonHasKey(response.asString(), "deleted"));
        // Below assert verified that id field is present in the JSON response or not. If not then the test fails.
        Assert.assertTrue(TestUtil.jsonHasKey(response.asString(), "id"),"ID key is not present in the JSON response");

        String actual_id = TestUtil.getJsonKeyValue(response.asString(), "id");
        System.out.println(actual_id);
        // If actual id in the
        Assert.assertEquals(actual_id, data.get("id"),"ID not matching");

        // Printing out other fields from response.
        System.out.println("Object key value is : "+TestUtil.getJsonKeyValue(response.asString(), "object"));
        System.out.println("Deleted key value is : "+TestUtil.getJsonKeyValue(response.asString(), "deleted"));


        Assert.assertEquals(response.statusCode(), 200);

    }

}
