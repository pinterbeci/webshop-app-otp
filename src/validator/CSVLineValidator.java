package validator;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CSVLineValidator {

    private static final Logger logger = Logger.getLogger(CSVLineValidator.class.getName());

    public static boolean validCSVLine(String[] line, boolean isCustomerData) {
        boolean isValidCSVLine = false;
        if (line.length > 0) {
            logger.info("Beolvasott validálása: " + line);
            if (isCustomerData) {
                return line.length == 4;
            } else {
                return line.length == 7;
            }
        } else {
            logger.log(Level.WARNING, "Nem megfelelő a beolvasott sor " + line);
            return isValidCSVLine;
        }
    }
}
