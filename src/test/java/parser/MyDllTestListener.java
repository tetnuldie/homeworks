package parser;

import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.Iterator;


public class MyDllTestListener implements ITestListener {
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed: " + result.getName());
        System.out.println("Params:");
        Object[] params = result.getParameters();
        for (Object o : params) {
            System.out.println(o.toString());
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getName());
        System.out.println("Params:");
        Object[] params = result.getParameters();
        for (Object o : params) {
            System.out.println(o.toString());
        }
    }
}
