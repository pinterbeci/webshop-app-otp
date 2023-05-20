package model;

public interface WebshopEntity {
    String getWebshopId();

    void setWebshopId(String webshopId);

    String getCustomerId();

    void setCustomerId(String webshopId);

    default String getPrice() {
        return "";
    }

    default void setPrice(String price) {

    }
}
