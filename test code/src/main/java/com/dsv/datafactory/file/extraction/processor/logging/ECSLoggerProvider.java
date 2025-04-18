package com.dsv.datafactory.file.extraction.processor.logging;

import com.dsv.logger.ECSLogger;

import static com.dsv.datafactory.file.extraction.processor.util.ConfigurationLoader.getOrDefault;

public class ECSLoggerProvider {
    //TODO: Can we use some of this strings in constant enum ?
    public static final String LOG_APP_NAME_SDD_ENV_VAR = "LOG_APP_NAME_SDD";
    public static final String LOG_APP_ID_SDD_ENV_VAR = "LOG_APP_ID_SDD";
    public static final String LOG_LEVEL_ENV_VAR = "LOG_LEVEL";

    public static ECSLogger getLogger(String className) {
        return getLogger(className, ECSLogger.Level.valueOf(getOrDefault(LOG_LEVEL_ENV_VAR, "WARN")));
    }

    public static ECSLogger getLogger(String className, ECSLogger.Level level) {

        //TODO: Don't know exacly how ECSLogger works, but is it suppose to have this default values present like this ? or should them be protected?
        ECSLogger logger = new ECSLogger(
                getOrDefault(LOG_APP_NAME_SDD_ENV_VAR, "aifactory"),
                getOrDefault(LOG_APP_ID_SDD_ENV_VAR, "5389"),
                className);
        logger.setLevel(className, level);
        return logger;
    }
}