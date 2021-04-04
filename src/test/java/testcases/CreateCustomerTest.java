package testcases;
import java.util.Hashtable;

import Listeners.ExtentListeners;
import org.testng.Assert;
import org.testng.annotations.Test;
import api.CreateCustomerAPI;
import commonutilities.*;
import io.restassured.response.Response;

public class CreateCustomerTest extends BaseTest {


    @Test(dataProviderClass = DataUtil.class, dataProvider = "data")
    public void validateCreateCustomerAPIWithValidSecretKey(Hashtable<String, String> data) {
        Response response = CreateCustomerAPI.sendPostRequestToCreateCustomerAPIWithValidAuthKey(data);

        ExtentListeners.testReport.get().info(data.toString());
        response.prettyPrint();
        System.out.println(response.statusCode());
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(dataProviderClass = DataUtil.class, dataProvider = "data")
	public void validateCreateCustomerAPIWithInValidSecretKey(Hashtable<String, String> data) {
        Response response = CreateCustomerAPI.sendPostRequestToCreateCustomerAPIWithInValidAuthKey(data);
		ExtentListeners.testReport.get().info(data.toString());
		response.prettyPrint();
		System.out.println(response.statusCode());
		Assert.assertEquals(response.statusCode(), 200);
	}
}
