package core.testutils;

import com.google.gson.Gson;
import core.constants.Env;
import core.env_config.Config;
import core.env_config.Environment;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JSONUtility {
    // Read the configuration for the selected environment (DEV, QA, UAT)
    public static Environment readEnvironmentConfig(Env env) {
        Gson gson = new Gson();
        String filepath = System.getProperty("user.dir") + File.separator + "ConfigurationS" + File.separator + "config.json";
        File jsonFile = new File(filepath);

        try (FileReader fileReader = new FileReader(jsonFile)) {
                Config config = gson.fromJson(fileReader, Config.class);
            return config.getEnvironments().get(env.name());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read config.json: " + e.getMessage());
        }
    }
}
