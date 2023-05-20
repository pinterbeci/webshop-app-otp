package csv.handler;

import model.WebshopEntity;
import service.WebshopService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {

    private final WebshopService webshopService = new WebshopService();

    public Map<String, List<WebshopEntity>> read() throws Exception {
        Map<String, List<WebshopEntity>> dataMap = new HashMap<>();

        try (BufferedReader customerFileBR = new BufferedReader(new FileReader("d:\\Projects\\Java SE\\WebshopAppOTP\\data\\customer.csv"));
             BufferedReader paymentFileBR = new BufferedReader(new FileReader("d:\\Projects\\Java SE\\WebshopAppOTP\\data\\payments.csv"))) {

            List<WebshopEntity> customerList = createDataList(customerFileBR, true);
            List<WebshopEntity> paymentList = createDataList(paymentFileBR, false);

            dataMap.put("paymentData", paymentList);
            dataMap.put("customerData", customerList);
        } catch (Exception e) {
            //log üzenet
            throw new Exception("Hiba a fájl/fáljok beolvasá során!");
        }
        return dataMap;
    }

    private List<WebshopEntity> createDataList(BufferedReader fileReader, boolean isCustomerData) throws Exception {
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
            //logger
            throw new Exception("Hiba a fájl beolvasása során!");
        }
    }
}
