package parser;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import utilities.ListADT;
import utilities.MyArrayList;
import utilities.MyDLL;

import java.util.*;

@Listeners(MyDllTestListener.class)
public class MyDLLTest {
    private MyDLL<String> myDll;

    @BeforeGroups
    public void initClass() {
        myDll = new MyDLL<>();
    }

    @BeforeTest
    public void initMethod() {
        myDll.clear();
    }


    @Test(testName = "Test create object",
            groups = {"dllCRUD", "smoke"},
            priority = 1,
            retryAnalyzer = ParserRetryAnalyzer.class)
    public void testCreateCollection() {
        Assert.assertEquals(0, myDll.size(), "smth failed, collection size > 0 at the init");
    }

    @Test(testName = "Test bulk add to collection",
            groups = {"dllCRUD"},
            priority = 2,
            retryAnalyzer = ParserRetryAnalyzer.class)
    public void testBulkAdd() {
        ListADT<String> list = new MyArrayList<>();
        list.add(Integer.toHexString(11));
        list.add(Integer.toHexString(22));
        list.add(Integer.toHexString(33));
        int check = myDll.size() + list.size();

        try {
            Assert.assertTrue(myDll.addAll(list), "Failed to digest given list");
        } catch (Exception e) {
            Assert.fail("Failed bo:\n" + e.getMessage());
        }
        Assert.assertEquals(check, myDll.size(), "Failed, result size not matched");
    }

    @Test(testName = "Test add item",
            groups = {"dllCRUD", "smoke"},
            priority = 2,
            dataProvider = "randomHexProvider",
            retryAnalyzer = ParserRetryAnalyzer.class)
    public void testAddToCollection(String toAdd) {
        try {
            Assert.assertTrue(myDll.add(toAdd));
        } catch (Exception e) {
            Assert.fail("Failed bo:\n" + e.getMessage());
        } finally {
            Assert.assertTrue(myDll.contains(toAdd), "Failed bo string " + toAdd + " not found");
        }

    }

    @Test(testName = "Test get from collection",
            groups = {"dllCRUD", "smoke"},
            priority = 3,
            dependsOnMethods = "testAddToCollection",
            retryAnalyzer = ParserRetryAnalyzer.class)
    public void testGetFromCollection() {
        String s = null;
        try {
            s = myDll.get(myDll.size() - 1);
            Assert.assertNotNull(s);
        } catch (Exception e) {
            Assert.fail("Failed bo:\n" + e.getMessage());
        }
        ITestResult testResult = Reporter.getCurrentTestResult();
        testResult.setAttribute("test output", s);
    }

    @Test(testName = "Test update item in collection",
            groups = {"dllCRUD", "smoke"},
            priority = 3,
            dataProvider = "randomHexProvider",
            dependsOnMethods = "testAddToCollection",
            retryAnalyzer = ParserRetryAnalyzer.class)
    public void testUpdateItem(String toSet) {
        int index = myDll.size() - 1;
        String old = null;
        try {
            old = myDll.set(index, toSet);
        } catch (Exception e) {
            Assert.fail("Failed bo:\n" + e.getMessage());
        }
        Assert.assertNotEquals(old, myDll.get(index), "Failed bo added element = new one");

        ITestResult testResult = Reporter.getCurrentTestResult();
        String s = old + " deleted; " + toSet + " pasted ";
        testResult.setAttribute("test output", s);

    }

    @Test(testName = "Test delete item from collection",
            groups = {"dllCRUD", "smoke"},
            priority = 3,
            dependsOnMethods = "testAddToCollection",
            retryAnalyzer = ParserRetryAnalyzer.class)
    public void testDeleteItem() {
        int index = myDll.size() - 1;
        String old = null;
        try {
            old = myDll.remove(index);
        } catch (Exception e) {
            Assert.fail("Failed bo:\n" + e.getMessage());
        }
        Assert.assertEquals(index, myDll.size(), "Failed, collection size have not been decremented");

        ITestResult testResult = Reporter.getCurrentTestResult();
        String s = old + " deleted";
        testResult.setAttribute("test output", s);
    }

    @Test(testName = "Test delete item from collection by value",
            groups = {"dllCRUD"},
            priority = 4,
            dataProvider = "randomHexProvider",
            dependsOnMethods = "testAddToCollection",
            retryAnalyzer = ParserRetryAnalyzer.class)
    public void testDeleteByValue(String value) {
        int index = myDll.size();
        try {
            myDll.add(value);
            myDll.remove(value);
        } catch (Exception e) {
            Assert.fail("Failed bo:\n" + e.getMessage());
        }
        Assert.assertEquals(index, myDll.size(), "Failed, collection size have not been decremented");
    }

    @Test(testName = "Test get dll to string array",
            groups = {"dllCRUD"},
            priority = 5,
            dependsOnMethods = "testAddToCollection",
            retryAnalyzer = ParserRetryAnalyzer.class)
    public void testGetItemsAsStrArray() {
        String[] array = new String[0];
        try {
            array = myDll.toArray(new String[1]);
        } catch (Exception e) {
            Assert.fail("Failed bo:\n" + e.getMessage());
        }
        Assert.assertEquals(myDll.size(), array.length, "Failed bo output size not matched init size");

        ITestResult testResult = Reporter.getCurrentTestResult();
        String s = Arrays.toString(array);
        testResult.setAttribute("test output", s);
    }


    @DataProvider(name = "randomHexProvider")
    public Object[] randomHexProvider(){
        Object[] output = new Object[1];
        output[0] = Integer.toHexString(37);

        return output;
    }


}
