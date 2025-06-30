package core.testutils;

import core.constants.Env;
import core.env_config.Environment;
import org.apache.logging.log4j.Logger;

public class EnvironmentManager {
    private static final Logger logger = LoggerUtility.getLogger(EnvironmentManager.class);
    private static Environment environment;

    public static void setEnvironment(Env env) {
        try {
            environment = JSONUtility.readEnvironmentConfig(env);
            logger.info("Environment set to: {}", env);
        } catch (Exception e) {
            logger.error("Failed to set environment: {}", e.getMessage());
            throw new RuntimeException("Failed to set environment: " + e.getMessage());
        }
    }

    public static String getUrl() {
        if (environment == null) {
            setEnvironment(Env.QA);
        }
        return environment.getUrl();
    }
    public static int getMaxAttempts() {
        if (environment == null) {
            setEnvironment(Env.QA);
        }
        int attempts = environment.getMax_attempts();
        return (attempts > 0) ? attempts : 2; // 2 is the default retry value  using if else statement
    }

}