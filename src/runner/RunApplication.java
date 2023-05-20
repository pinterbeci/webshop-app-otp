package runner;

import csv.handler.CSVReader;
import logger.WebshopLogger;

public class RunApplication {

    public static void main(String[] args) {
        WebshopLogger.init();
        CSVReader csvReader = new CSVReader();
        try {
            csvReader.read();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
