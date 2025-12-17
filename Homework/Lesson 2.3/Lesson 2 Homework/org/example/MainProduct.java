package org.example;
import java.time.LocalDate;
public class MainProduct {
    public static void main(String[] args) {
        Product[] productsArray = new Product[5];
        productsArray[0] = new Product("Samsung S25 Ultra", LocalDate.of(2025, 2, 1), "Samsung Corp.", "Korea", 5599, true);
        productsArray[1] = new Product("iPhone 14", LocalDate.of(2025, 3, 10), "Apple Inc.", "USA", 6999, false);
        productsArray[2] = new Product("Xiaomi 12", LocalDate.of(2025, 4, 15), "Xiaomi Corp.", "China", 2999, true);
        productsArray[3] = new Product("Huawei P50", LocalDate.of(2025, 5, 20), "Huawei", "China", 3999, false);
        productsArray[4] = new Product("OnePlus 11", LocalDate.of(2025, 6, 25), "OnePlus", "China", 3499, true);

        for (int d = 0; d< productsArray.length; d++) {
            productsArray[d].printInfo();
        }
    }
}
