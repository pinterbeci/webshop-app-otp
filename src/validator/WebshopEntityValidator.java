package validator;

import model.WebshopEntity;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class WebshopEntityValidator {

    private static final Logger logger = Logger.getLogger(WebshopEntityValidator.class.getName());

    public static boolean isValidCustomerCustomerId(List<WebshopEntity> dataList, String currentId) {
        logger.info("Customer custormerId validálása customerId:" + currentId);
        return dataList.stream().anyMatch(currentElement -> !(currentId).equals(currentElement.getCustomerId()));
    }

    public static boolean isValidPaymentCustomerId(List<WebshopEntity> dataList, String currentId) {
        logger.info("Payment custormerId validálása customerId:" + currentId);
        return dataList.stream().anyMatch(currentElement -> !(currentId).equals(currentElement.getCustomerId()));
    }

    public static boolean isValidPaymentMode(String paymentMode) {
        String[] validPayments = {"card", "transfer"};
        logger.info("PaymentMode validálása paymentMode:" + paymentMode);
        return Arrays.stream(validPayments).anyMatch(paymentMode.toLowerCase()::equals);
    }


}
