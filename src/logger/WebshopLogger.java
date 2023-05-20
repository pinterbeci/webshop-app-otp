package logger;


import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class WebshopLogger {
    private static final Logger logger = Logger.getLogger("WebshopLogger");

    public static void init() {
        FileHandler fileHandler;

        try {
            fileHandler = new FileHandler("application.log");
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);

            logger.info("Logging initialized");
        } catch (Exception e) {
            logger.log(Level.WARNING, "Exception:", e);
        }
    }
}
