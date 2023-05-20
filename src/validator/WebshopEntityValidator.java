package validator;

import model.WebshopEntity;

import java.util.Arrays;
import java.util.List;

public class WebshopEntityValidator {

    public static boolean isValidCustomerCustomerId(List<WebshopEntity> dataList, String currentId) {
        return dataList.stream().anyMatch(currentElement -> !(currentId).equals(currentElement.getCustomerId()));
    }

    public static boolean isValidPaymentCustomerId(List<WebshopEntity> dataList, String currentId) {
        return dataList.stream().anyMatch(currentElement -> !(currentId).equals(currentElement.getCustomerId()));
    }

    public static boolean isValidPaymentMode(String paymentMode) {
        String[] validPayments = {"card", "transfer"};
        return Arrays.stream(validPayments).anyMatch(paymentMode.toLowerCase()::equals);
    }


}
