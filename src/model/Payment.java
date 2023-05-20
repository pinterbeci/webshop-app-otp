package model;

public class Payment extends Webshop implements WebshopEntity {

    private String paymentMode;

    private String customerId;
    private String price;
    private String bankAccountNumber;
    private String cardNumber;
    private String payDate;

    public Payment() {
    }

    public Payment(String webshopId, String paymentMode, String customerId, String price, String bankAccountNumber, String cardNumber, String payDate) {
        super(webshopId);
        this.paymentMode = paymentMode;
        this.customerId = customerId;
        this.price = price;
        this.bankAccountNumber = bankAccountNumber;
        this.cardNumber = cardNumber;
        this.payDate = payDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }
}
