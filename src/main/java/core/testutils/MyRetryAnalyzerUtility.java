package core.testutils;

import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class MyRetryAnalyzerUtility implements IRetryAnalyzer {

    public static final Logger logger=LoggerUtility.getLogger(MyRetryAnalyzerUtility.class);
    private static int currentAttempt = 1;
    @Override
    public boolean retry(ITestResult results) {
        int maxAttempts=EnvironmentManager.getMaxAttempts();
        if (currentAttempt <= maxAttempts) {
            currentAttempt++;
            logger.info("Retrying test: {} (Attempt {}/{})", results.getName(), currentAttempt, maxAttempts);
            return true;
        }
        return false;
    }
}
