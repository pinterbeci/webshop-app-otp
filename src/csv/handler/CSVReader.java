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

    public Map<String, List<WebshopEntity>> read() {
        Map<String, List<WebshopEntity>> dataMap = new HashMap<>();

        try (BufferedReader customerFileBR = new BufferedReader(new FileReader("d:\\Projects\\Java SE\\WebshopAppOTP\\data\\customer.csv"));
             BufferedReader paymentFileBR = new BufferedReader(new FileReader("d:\\Projects\\Java SE\\WebshopAppOTP\\data\\payments.csv"))) {

            List<WebshopEntity> customerList = createDataList(customerFileBR, null, true);
            webshopService.setCustomerList(customerList);
            List<WebshopEntity> paymentList = createDataList(paymentFileBR, webshopService.getCustomerList(), false);
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
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Hiba a fájlok beolvasása során!", e);
        }
        return dataMap;
    }

    private List<WebshopEntity> createDataList(BufferedReader fileReader, List<WebshopEntity> customerList, boolean isCustomerData) {
        List<WebshopEntity> returnValue = new ArrayList<>();
        String line;
        try {
            while ((line = fileReader.readLine()) != null) {
                String[] data = line.split(";");
                WebshopEntity currentEntity = webshopService.createWebshopEntity(data, isCustomerData);
                List<WebshopEntity> webshopEntityList = webshopService.addNewWebshopEntity(returnValue, customerList, currentEntity);
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

    public List<WebshopEntity> report1(Map<String, List<WebshopEntity>> dataMap){
        Purchase purchase = new Purchase();

        return null;
    }
}
