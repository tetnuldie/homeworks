package parser;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class ParserRetryAnalyzer implements IRetryAnalyzer {
    private int retryCount;
    private final int retryLimit = 2;
    @Override
    public boolean retry(ITestResult iTestResult) {
        if(retryCount < retryLimit && !iTestResult.isSuccess()){
            retryCount++;
            return true;
        }else{
            return false;
        }
    }
}
