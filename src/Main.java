import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String[] products = {"Молоко", "Хлеб", "Колбаса", "Вода", "Сухарики", "Сметана"};
        int[] prices = {100, 40, 150, 50, 30, 120};
        Basket basket = new Basket(prices, products);

        File file = new File("./basket.bin");

        if (file.exists()) {
            basket = Basket.loadFromBinFile(file);
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
            basket.saveBin(file);
        }
        basket.printCar();
    }
}