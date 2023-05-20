package csv.handler;

import model.Purchase;
import model.WebshopEntity;
import service.WebshopService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CSVReader {

    private final WebshopService webshopService = new WebshopService();

    private static final Logger logger = Logger.getLogger(CSVReader.class.getName());

    public void read() {
        Map<String, List<WebshopEntity>> dataMap = new HashMap<>();

        try (BufferedReader customerFileBR = new BufferedReader(new FileReader("d:\\Projects\\Java SE\\WebshopAppOTP\\data\\customer.csv"));
             BufferedReader paymentFileBR = new BufferedReader(new FileReader("d:\\Projects\\Java SE\\WebshopAppOTP\\data\\payments.csv"))) {

            List<WebshopEntity> customerList = webshopService.createDataList(customerFileBR, null, true);
            webshopService.setCustomerList(customerList);
            List<WebshopEntity> paymentList = webshopService.createDataList(paymentFileBR, webshopService.getCustomerList(), false);
            webshopService.setPaymentList(paymentList);

            if (!customerList.isEmpty()) {
                dataMap.put("customerData", customerList);
            } else {
                logger.log(Level.SEVERE, "Nem sikerült elmenteni a 'customerList'-et!");
            }
            if (!paymentList.isEmpty()) {
                dataMap.put("paymentData", paymentList);
            } else {
                logger.log(Level.SEVERE, "Nem sikerült elmenteni a 'paymentList'-et!");
            }

            if( !dataMap.isEmpty()){
                List<Purchase> report01List = webshopService.report01(dataMap);
                List<Purchase> topReportList = webshopService.top(report01List);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Hiba a fájlok beolvasása során!", e);
        }

    }

}
