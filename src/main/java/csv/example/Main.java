package csv.example;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String[] products = {"Молоко", "Хлеб", "Колбаса", "Вода", "Сухарики", "Сметана"};
        int[] prices = {100, 40, 150, 50, 30, 120};
        Basket basket = new Basket(prices, products);
        ClientLog clientLog = new ClientLog();
        jsonFileWorker jsonFileWorker = new jsonFileWorker();
        myXMLReader myXMLReader = new myXMLReader();

        myXMLReader.read();

        File file = new File(myXMLReader.getFileNameLoad());
        File fileCSV = new File(myXMLReader.getFileNameLog());

        if (file.exists()) {
            if (myXMLReader.getEnabledLoad().equals("true")) {
                if (myXMLReader.getFormatLoad().equals("json")) {
                    basket = jsonFileWorker.loadFromJSON(file, basket);
                } else if (myXMLReader.getFormatLoad().equals("text")) {
                    basket = Basket.loadFromTextFile(file);
                }
            }
        }

        System.out.println("Список возможных для покупки товаров");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i] + " " + prices[i] + " руб/шт");
        }

        while (true) {
            System.out.println("Выберите товар и количество или введите 'end'");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                break;
            }

            String[] numbers = input.split(" ");

            int productNumber = Integer.parseInt(numbers[0]) - 1;
            int productCount = Integer.parseInt(numbers[1]);

            basket.addToCart(productNumber, productCount);

            if (myXMLReader.getEnabledLog().equals("true")) {
                clientLog.log(productNumber + 1, productCount);
                clientLog.exportAsCSV(fileCSV);
            }

            if (myXMLReader.getEnabledSave().equals("true")) {
                if (myXMLReader.getFormatSave().equals("json")) {
                    jsonFileWorker.saveToJSON(file, basket);
                } else if (myXMLReader.getFormatSave().equals("text")) {
                    basket.saveTxt(file);
                }
            }

        }
        basket.printCar();
    }
}