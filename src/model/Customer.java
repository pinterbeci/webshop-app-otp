package model;

public class Customer extends Webshop implements WebshopEntity {

    private String customerId;
    private String customerName;
    private String customerAddress;

    public Customer() {
    }

    public Customer(String webshopId, String customerId, String customerName, String customerAddress) {
        super(webshopId);
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
}
