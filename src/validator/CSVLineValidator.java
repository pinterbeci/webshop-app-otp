package validator;

public class CSVLineValidator {

    public static boolean validCSVLine(String[] line, boolean isCustomerData) {
        boolean isValidCSVLine = false;
        if (line.length > 0) {
            if (isCustomerData) {
                return line.length == 4;
            } else {
                return line.length == 7;
            }
        } else {
            return isValidCSVLine;
        }
    }
}
