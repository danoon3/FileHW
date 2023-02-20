import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Basket {
    private static int[] prices;
    private static String[] products;
    private static int[] countOfAllProducts;
    private static int[] costOfOneProduct;
    private static boolean[] isFilled;
    private static int[] numbersFromFile;
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
                    System.out.println(products[i] + " " + countOfAllProducts[i] + " шт " +
                            prices[i] + " руб/шт " + costOfOneProduct[i] + " руб в сумме");
                }
            }
        }
        for (int f : costOfOneProduct) {
            sum += f;
        }
        System.out.println("Итого: " + sum + " руб.");
    }

    public void saveTxt(File textFile) throws IOException {
        try (FileWriter fileWriter = new FileWriter(textFile)) {
            for (int i : countOfAllProducts) {
                fileWriter.write(i + " ");
                fileWriter.flush();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    static Basket loadFromTextFile(File textFile) throws IOException {
        Basket basket = new Basket(prices, products);
        try (FileReader fileReader = new FileReader(textFile)) {
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                numbersFromFile = Arrays.stream(input.split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        for (int i = 0; i < numbersFromFile.length; i++) {
            if (numbersFromFile[i] != 0) {
                basket.addToCart(i, numbersFromFile[i]);
            }
        }
        return basket;
    }

    public int[] getPrices() {
        return prices;
    }

    public String[] getProducts() {
        return products;
    }
}
