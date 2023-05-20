package csv.handler;

import model.Purchase;
import model.WebshopEntity;
import model.WebshopReport;

import java.io.*;
import java.util.List;

public class CSVWriter {

    public void writeReport01DataToFile(List<Purchase> purchaseList) throws IOException {
        PrintWriter writer = new PrintWriter("d:/Projects/Java SE/WebshopAppOTP/data/report01.csv");

        for (Purchase sample : purchaseList) {
            writer.print(sample.getCustomerName());
            writer.print(";");
            writer.print(sample.getAddress());
            writer.print(";");
            writer.print(sample.getAmountOfSpent());
            writer.println(";");
        }
        writer.close();
    }


    public void writeTopReportDataToFile(List<Purchase> purchaseList) throws IOException {
        PrintWriter writer = new PrintWriter("d:/Projects/Java SE/WebshopAppOTP/data/top.csv");

        for (Purchase sample : purchaseList) {
            writer.print(sample.getCustomerName());
            writer.print(";");
            writer.print(sample.getAddress());
            writer.print(";");
            writer.print(sample.getAmountOfSpent());
            writer.println(";");
        }
        writer.close();
    }

    public void writeReport02DataToFile(List<WebshopReport> webshopEntityList) throws IOException {
        PrintWriter writer = new PrintWriter("d:/Projects/Java SE/WebshopAppOTP/data/report02.csv");

        for (WebshopReport entity : webshopEntityList) {
            writer.print(entity.getWebshopId());
            writer.print(";");
            writer.print(entity.getPrice());
            writer.print(";");
            writer.print(entity.getPriceByCard());
            writer.println(";");
        }
        writer.close();
    }
}
