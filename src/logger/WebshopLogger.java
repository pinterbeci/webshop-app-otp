package logger;


import runner.RunApplication;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class WebshopLogger {
    private static final Logger logger = Logger.getLogger(WebshopLogger.class.getName());

    public static void init() {
        FileHandler fileHandler;

        try {

            fileHandler = new FileHandler("application.log", true);
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            logger.info("WebshopLogger initialized!");

            if (logger.isLoggable(Level.INFO)) {
                logger.info("Information message");
            }

            if (logger.isLoggable(Level.WARNING)) {
                logger.warning("Warning message");
            }

            if (logger.isLoggable(Level.SEVERE)) {
                logger.info("Serious message");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception:", e);
        }
    }
}
