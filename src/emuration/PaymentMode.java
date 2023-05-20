package emuration;

public enum PaymentMode {
    CARD("card"),
    TRANSFER("transfer");
    private final String name;

    PaymentMode(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
