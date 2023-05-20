package service;

import model.Customer;
import model.Payment;
import model.Purchase;
import model.WebshopEntity;
import validator.CSVLineValidator;
import validator.WebshopEntityValidator;

import java.io.BufferedReader;
import java.text.ParseException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebshopService {

    private List<WebshopEntity> customerList;
    private List<WebshopEntity> paymentList;
    private static final Logger logger = Logger.getLogger(WebshopService.class.getName());

    public List<WebshopEntity> createDataList(BufferedReader fileReader, List<WebshopEntity> customerList, boolean isCustomerData) {
        List<WebshopEntity> returnValue = new ArrayList<>();
        String line;
        try {
            while ((line = fileReader.readLine()) != null) {
                String[] data = line.split(";");
                WebshopEntity currentEntity = createWebshopEntity(data, isCustomerData);
                List<WebshopEntity> webshopEntityList = addNewWebshopEntity(returnValue, customerList, currentEntity);
                boolean isEmptyWebshopEntityList = webshopEntityList.isEmpty();

                if (!isEmptyWebshopEntityList) {
                    returnValue = webshopEntityList;
                }
            }
            return returnValue;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Hiba az adatok mentése során!", e);
        }
        return Collections.emptyList();
    }

    public WebshopEntity createWebshopEntity(String[] line, boolean isCustomerData) throws ParseException {
        if (CSVLineValidator.validCSVLine(line, isCustomerData)) {
            List<String> lineArray = Arrays.asList(line);
            if (isCustomerData) {
                Customer customer = new Customer();
                customer.setWebshopId(lineArray.get(0));
                customer.setCustomerId(lineArray.get(1));
                customer.setCustomerName(lineArray.get(2));
                customer.setCustomerAddress(lineArray.get(3));
                logger.info("Új Customer objektum létrehozása");
                return customer;
            } else {
                Payment payment = new Payment();
                payment.setWebshopId(lineArray.get(0));
                payment.setCustomerId(lineArray.get(1));
                payment.setPaymentMode(lineArray.get(2));
                payment.setPrice(lineArray.get(3));
                payment.setBankAccountNumber(lineArray.get(4));
                payment.setCardNumber(lineArray.get(5));
                payment.setPayDate(lineArray.get(6));
                logger.info("Új Payment objektum létrehozása");
                return payment;
            }
        } else
            logger.log(Level.WARNING, "Nem sikerült létrehozni az új WesbhopEntity objektumot");
        return null;

    }

    private List<WebshopEntity> addNewCustomerEntity(List<WebshopEntity> customerList, Customer entity) {
        if (customerList.isEmpty()) {
            customerList = new ArrayList<>();
            customerList.add(entity);
        } else {
            if (WebshopEntityValidator.isValidCustomerCustomerId(customerList, entity.getCustomerId())) {
                customerList.add(entity);
            }
        }
        return customerList;
    }

    private List<WebshopEntity> addNewPaymentEntity(List<WebshopEntity> paymentList, Payment entity) {
        if (paymentList.isEmpty()) {
            paymentList = new ArrayList<>();
            paymentList.add(entity);
        } else {
            if (WebshopEntityValidator.isValidPaymentMode(entity.getPaymentMode())) {
                paymentList.add(entity);
                return paymentList;
            }
        }

        return paymentList;
    }

    public List<WebshopEntity> addNewWebshopEntity(List<WebshopEntity> webshopEntityList, List<WebshopEntity> customerEntityList, WebshopEntity entity) {
        if (entity instanceof Customer) {
            return addNewCustomerEntity(webshopEntityList, (Customer) entity);
        }
        if (entity instanceof Payment) {
            if (!customerEntityList.isEmpty()) {
                boolean isValidPaymentCustomerId = customerEntityList.stream().anyMatch(currentCustomer -> (entity.getCustomerId()).equals(currentCustomer.getCustomerId()));
                if (isValidPaymentCustomerId) {
                    return addNewPaymentEntity(webshopEntityList, (Payment) entity);
                }
            }
        }
        return new ArrayList<>();
    }

    public WebshopEntity getEntityByCustomerId(List<WebshopEntity> entityList, String customerId) {
        for (WebshopEntity webshopEntity : entityList) {
            if (webshopEntity.getCustomerId().equals(customerId))
                return webshopEntity;
        }
        return null;
    }

    public List<WebshopEntity> getEntityListByCustomerId(List<WebshopEntity> entityList, String customerId) {
        List<WebshopEntity> returnValue = new ArrayList<>();
        for (WebshopEntity webshopEntity : entityList) {
            if (webshopEntity.getCustomerId().equals(customerId))
                returnValue.add(webshopEntity);
        }
        return returnValue;
    }

    public List<Purchase> report01(Map<String, List<WebshopEntity>> dataMap) {
        List<WebshopEntity> paymentEntityList = new ArrayList<>();
        List<WebshopEntity> custormerEntityList = new ArrayList<>();
        List<Purchase> purchaseList = new ArrayList<>();

        if (!dataMap.isEmpty()) {
            if (dataMap.containsKey("paymentData") && !dataMap.get("paymentData").isEmpty()) {
                paymentEntityList = dataMap.get("paymentData");
            }
            if (dataMap.containsKey("customerData") && !dataMap.get("customerData").isEmpty()) {
                custormerEntityList = dataMap.get("customerData");
            }
            if (!paymentEntityList.isEmpty()) {
                for (WebshopEntity entity : paymentEntityList) {
                    if (hasCustomerInPurchaseList(purchaseList, entity.getCustomerId())) {

                        Purchase purchase = new Purchase();
                        purchase.setAmountOfSpent(String.valueOf(amounOfPaymentById(paymentEntityList, entity.getCustomerId())));
                        purchase.setCustomerId(entity.getCustomerId());

                        purchaseList.add(purchase);
                    }
                }
            }
            if (!custormerEntityList.isEmpty()) {
                for (WebshopEntity entity : custormerEntityList) {
                    if (entity instanceof Customer) {
                        for (Purchase purchase : purchaseList) {
                            if (purchase.getCustomerId().equals(entity.getCustomerId())) {
                                purchase.setAddress(((Customer) entity).getCustomerAddress());
                                purchase.setCustomerName(((Customer) entity).getCustomerName());
                            }
                        }
                    }
                }
            }
        }
        return purchaseList;
    }

    public List<Purchase> top(List<Purchase> purchaseList) {
        List<Purchase> returnValue = new ArrayList<>();
        List<String> mostSpentAmountsArray = mostSpentAmounts(purchaseList);
        if (!purchaseList.isEmpty()) {
            if (!mostSpentAmountsArray.isEmpty()) {
                for (Purchase purchase : purchaseList) {
                    if (mostSpentAmountsArray.contains(purchase.getAmountOfSpent()))
                        returnValue.add(purchase);
                }
            }
        }
        return returnValue;
    }

    public List<WebshopEntity> report02(List<WebshopEntity> entityList) {

    }

    private List<String> mostSpentAmounts(List<Purchase> purchaseList) {
        TreeSet<Long> amountSet = new TreeSet<>();
        List<String> returnValue = new ArrayList<>();
        int i = 0;
        for (Purchase purchase : purchaseList) {
            amountSet.add(Long.parseLong(purchase.getAmountOfSpent()));
        }
        returnValue.add(Long.toString(amountSet.last()));
        amountSet.pollLast();
        returnValue.add(Long.toString(amountSet.last()));
        return returnValue;
    }

    private boolean hasCustomerInPurchaseList(List<Purchase> purchaseList, String customerId) {
        for (Purchase purchase : purchaseList) {
            if ((customerId).equals(purchase.getCustomerId()))
                return false;
        }
        return true;
    }

    private long amounOfPaymentById(List<WebshopEntity> entityList, String customerId) {
        long amountOfPayment = 0;
        for (WebshopEntity webshopEntity : entityList) {
            if (webshopEntity.getCustomerId().equals(customerId)) {
                amountOfPayment += Long.parseLong(webshopEntity.getPrice());
            }
        }
        return amountOfPayment;
    }

    public List<WebshopEntity> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<WebshopEntity> customerList) {
        this.customerList = customerList;
    }

    public List<WebshopEntity> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<WebshopEntity> paymentList) {
        this.paymentList = paymentList;
    }
}
