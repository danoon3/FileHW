import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Basket implements Serializable {
    private static final long serialVersionUID = 1L;
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
                if (isFilled[i]) {
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

    public void saveBin(File binFile, Basket basket) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(binFile);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(basket);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    static Basket loadFromBinFile(File binFile) {
        Basket basket = null;
        try (FileInputStream fileInputStream = new FileInputStream(binFile);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            basket = (Basket) objectInputStream.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
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
