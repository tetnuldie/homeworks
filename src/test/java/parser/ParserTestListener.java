package parser;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import utilities.Iterator;

import static parser.XMLParserTest.errors;

public class ParserTestListener implements ITestListener {
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

        if (errors.isEmpty()) {
            System.out.println("Parser have not found any errors; Given xml been treated as valid");
        } else {

            System.out.println("Errors found by parser:");

            Iterator<String> errorsIter = errors.iterator();
            while (errorsIter.hasNext()) {
                System.out.println(errorsIter.next());
            }
        }
    }
}
