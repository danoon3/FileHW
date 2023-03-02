package csv.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);
        String[] products = {"Молоко", "Хлеб", "Колбаса", "Вода", "Сухарики", "Сметана"};
        int[] prices = {100, 40, 150, 50, 30, 120};
        Basket basket = new Basket(prices, products);
        ClientLog clientLog = new ClientLog();

        File file = new File("./basket.txt");
        File fileCSV = new File("./log.csv");
        File fileJSON = new File("./basket.json");

        if (fileJSON.exists()) {
            JSONParser parser = new JSONParser();
            try {
                Object obj = parser.parse(new FileReader(fileJSON));
                JSONObject jsonObject = (JSONObject) obj;
                String jsonText = String.valueOf(jsonObject);

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                basket = gson.fromJson(jsonText, Basket.class);
            } catch (IOException | ParseException e) {
                e.getMessage();
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
                clientLog.exportAsCSV(fileCSV);
                break;
            }

            String[] numbers = input.split(" ");

            int productNumber = Integer.parseInt(numbers[0]) - 1;
            int productCount = Integer.parseInt(numbers[1]);

            clientLog.log(productNumber + 1, productCount);

            basket.addToCart(productNumber, productCount);

            try (FileWriter fileWriter = new FileWriter(fileJSON)) {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                fileWriter.write(gson.toJson(basket));
                fileWriter.flush();
            }
        }
        basket.printCar();
    }
}