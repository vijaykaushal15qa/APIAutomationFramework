package commonutilities;
import io.restassured.RestAssured;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    public static Properties config=new Properties();
    private FileInputStream fis;
    public  static ExcelReader excel=new ExcelReader(".\\src\\test\\resources\\excel\\testdata.xlsx");
    @BeforeSuite
    public void setup()
    {

        try {
            fis=new FileInputStream(".\\src\\test\\resources\\config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            config.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        RestAssured.baseURI=config.getProperty("baseURI");
        RestAssured.basePath=config.getProperty("basePath");

    }

    @AfterSuite
    public void tearDown()
    {

    }
}
