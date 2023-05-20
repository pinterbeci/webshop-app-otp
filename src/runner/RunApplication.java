package runner;

import csv.handler.CSVReader;
import logger.WebshopLogger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RunApplication {
    private static final Logger logger = Logger.getLogger(RunApplication.class.getName());

    public static void main(String[] args) {
        WebshopLogger.init();
        CSVReader csvReader = new CSVReader();
        try {
            csvReader.read();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Hiba a fájlok beolvasása során!", e);
        }
    }
}
