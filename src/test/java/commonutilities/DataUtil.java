package commonutilities;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.Hashtable;

public class DataUtil extends BaseTest{

    @DataProvider(name="data",parallel = true)
    public static Object[][] getData(Method m) {
        // Extracting the Total Row Count From Test Data Sheet.
        int rows = excel.getRowCount(config.getProperty("testDataSheetName"));
        System.out.println("Total rows are : " + rows);

        String testName = m.getName();// Assgning testname as TestMethod name as we have kept both same.
        System.out.println("Test name is : "+testName);

        // Finding the test case start row
        int testCaseRowNum = 1;
        for (testCaseRowNum = 1; testCaseRowNum <= rows; testCaseRowNum++) {
            //Extracting the testcasename from testdata sheet by extracting data from column 0 and row1
            //and then comparing with the testName extracted above from methodname.
            //If both are equal that means testcaseRowNum is 1.
            String testCaseName = excel.getCellData(config.getProperty("testDataSheetName"), 0, testCaseRowNum);
            if (testCaseName.equalsIgnoreCase(testName))
                break;
        }

        System.out.println("Test case starts from row num: " + testCaseRowNum);

        // Checking total rows in test case. Test data will start after 1 Row from testCaseRowNum as.
        // Row followed immediately after testCaseRowNum will be column headers/
        // Actual Test Case data will start from testCaseRowNum+2.
        int dataStartRowNum = testCaseRowNum + 2;
        int testRows = 0;// This variable will hold the count of test data rows for a particular testcase.

        //Iterating using while loop till blank row is observed to get the total test data rows count for a specific test case.

        while (!excel.getCellData(config.getProperty("testDataSheetName"), 0, dataStartRowNum + testRows).equals("")) {
            testRows++;
        }
        System.out.println("Total rows of data are : " + testRows);// Printing the total test data rows.

        //Checking total cols in test case.
        //Column headers will start just after testCaseRowNum.
        int colStartColNum = testCaseRowNum + 1;

        int testCols = 0;// This will hold the count for total number of column in test data.
        // Iterating using while loop till blank value is observed in columns to get total columns count.

        while (!excel.getCellData(config.getProperty("testDataSheetName"), testCols, colStartColNum).equals("")) {
            testCols++;
        }
        System.out.println("Total cols are : " + testCols);

        // Printing data
        // Creating Two Dimensional Object Array.
        Object[][] data = new Object[testRows][1];
        int i = 0;
        for (int rNum = dataStartRowNum; rNum < (dataStartRowNum + testRows); rNum++) {
            // Creating HashTable to Store Data in Key Valey Pairs.

            Hashtable<String, String> table = new Hashtable<String, String>();
            for (int cNum = 0; cNum < testCols; cNum++) {

                String colName = excel.getCellData(config.getProperty("testDataSheetName"), cNum, colStartColNum);
                String testData = excel.getCellData(config.getProperty("testDataSheetName"), cNum, rNum);
                table.put(colName, testData);
            }
            data[i][0] = table;
            i++;
        }
        return data;
    }

}