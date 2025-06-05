package core.testutils;

import core.constants.Env;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class MyRetryAnalyzerUtility implements IRetryAnalyzer {
    private static final int maxAttempts = JSONUtility.readEnvironmentConfig(Env.QA).getMax_attempts();
    private static int currentAttempt = 1;
    @Override
    public boolean retry(ITestResult results) {
        if (currentAttempt <= maxAttempts) {
            currentAttempt++;
            return true;
        }
        return false;
    }
}
