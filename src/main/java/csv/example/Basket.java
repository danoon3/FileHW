package csv.example;

import java.io.*;
import java.util.Arrays;

public class Basket {
    private int[] prices;
    private String[] products;
    private int[] countOfAllProducts;
    private int[] costOfOneProduct;
    private boolean[] isFilled;
    private int sum = 0;

    public Basket(int[] prices, String[] products) {
        this.prices = prices;
        this.products = products;
        countOfAllProducts = new int[products.length];
        costOfOneProduct = new int[products.length];
        isFilled = new boolean[products.length];
    }


    public void addToCart(int productNum, int amount) {
        int productCost = amount * prices[productNum];
        countOfAllProducts[productNum] += amount;
        costOfOneProduct[productNum] += productCost;
        isFilled[productNum] = true;
    }

    public void printCar() {
        System.out.println("Ваша корзина: ");
        for (int i = 0; i < isFilled.length; i++) {
            {
                if (isFilled[i] == true) {
                    System.out.println(products[i] + " " + countOfAllProducts[i] + " шт " + prices[i] + " руб/шт " + costOfOneProduct[i] + " руб в сумме");
                }
            }
        }
        for (int f : costOfOneProduct) {
            sum += f;
        }
        System.out.println("Итого: " + sum + " руб.");
    }

    public void saveTxt(File textFile) {
        try (FileWriter fileWriter = new FileWriter(textFile)) {

            StringBuilder sb = new StringBuilder();
            for (int i : prices) {
                sb.append(i).append(" ");
            }
            sb.append("\n");

            for (String j : products) {
                sb.append(j).append(" ");
            }
            sb.append("\n");

            for (int i : countOfAllProducts) {
                sb.append(i).append(" ");
            }
            sb.append("\n");

            String result = sb.toString();
            fileWriter.write(result);
            fileWriter.flush();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    static Basket loadFromTextFile(File textFile) throws IOException {
        int[] pricesFromFile = new int[]{};
        String[] productsFromFile = new String[]{};
        int[] numbersFromFile = new int[]{};
        try (FileReader fileReader = new FileReader(textFile); BufferedReader br = new BufferedReader(fileReader)) {
            String line = br.readLine();
            while (line != null) {
                pricesFromFile = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
                line = br.readLine();
                productsFromFile = Arrays.stream(line.split(" ")).toArray(String[]::new);
                line = br.readLine();
                numbersFromFile = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
                line = br.readLine();
            }
            Basket basket = new Basket(pricesFromFile, productsFromFile);
            for (int i = 0; i < numbersFromFile.length; i++) {
                if (numbersFromFile[i] != 0) {
                    basket.addToCart(i, numbersFromFile[i]);
                }
            }
            return basket;
        }
    }

    public int[] getPrices() {
        return prices;
    }

    public String[] getProducts() {
        return products;
    }
}
