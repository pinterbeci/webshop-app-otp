package model;

public class Purchase extends Customer implements WebshopEntity {

    private String customerName;
    private String address;
    private String amountOfSpent;

    public Purchase() {
    }

    public Purchase(String webshopId, String customerId, String customerName, String customerAddress) {
        super(webshopId, customerId, customerName, customerAddress);
    }

    @Override
    public String getCustomerName() {
        return customerName;
    }

    @Override
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAmountOfSpent() {
        return amountOfSpent;
    }

    public void setAmountOfSpent(String amountOfSpent) {
        this.amountOfSpent = amountOfSpent;
    }


}
