import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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
        try (FileReader fileReader = new FileReader(textFile)) {
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                int[] numbersFromFile = Arrays.stream(input.split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
