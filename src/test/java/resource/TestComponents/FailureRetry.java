package resource.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class FailureRetry  implements IRetryAnalyzer {


    int count = 0;
    int maxTry = 1;

    @Override
    public boolean retry(ITestResult result) {
        // TODO Auto-generated method stub
        if(count<maxTry)
        {
            System.out.println("Failure retry:"+ result.getMethod().getMethodName());
            count++;
            return true;
        }
        return false;
    }

}

