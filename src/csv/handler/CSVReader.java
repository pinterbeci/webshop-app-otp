package csv.handler;

import logger.WebshopLogger;
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

            List<WebshopEntity> customerList = createDataList(customerFileBR, true);
            List<WebshopEntity> paymentList = createDataList(paymentFileBR, false);

            if (!customerList.isEmpty()) {
                dataMap.put("customerData", customerList);
            }else{
                logger.log(Level.SEVERE, "Nem sikerült elmenteni a 'customerList'-et!");
            }
            if (!paymentList.isEmpty()) {
                dataMap.put("paymentData", paymentList);
            }else{
                logger.log(Level.SEVERE, "Nem sikerült elmenteni a 'paymentList'-et!");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Hiba a fájlok beolvasása során!", e);
        }
        return dataMap;
    }

    private List<WebshopEntity> createDataList(BufferedReader fileReader, boolean isCustomerData) {
        List<WebshopEntity> returnValue = new ArrayList<>();
        String line;
        try {
            while ((line = fileReader.readLine()) != null) {
                String[] data = line.split(";");
                WebshopEntity currentEntity = webshopService.createWebshopEntity(data, isCustomerData);
                returnValue = webshopService.addNewWebshopEntity(returnValue, currentEntity);
            }
            return returnValue;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Hiba az adatok mentése során!", e);
        }
        return Collections.emptyList();
    }
}
