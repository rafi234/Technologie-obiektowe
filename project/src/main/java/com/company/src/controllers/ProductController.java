package com.company.src.controllers;

import com.company.src.models.individual.productFlyweight.FlyweightProduct;
import com.company.src.models.individual.productFlyweight.FlyweightProductFactory;
import com.company.src.models.magazine.Magazine;
import com.company.src.models.magazine.Product;

import java.util.ArrayList;

public class ProductController {
    public void addExistingProduct(String code, FlyweightProduct amount) {
        if (code.isEmpty() || amount == null)
            System.out.println("Fulfill whole form!");
        else
            saveExistingProduct(amount.toString().split(" ")[0], code);

    }

    private void saveExistingProduct(String code, String amount) {
        FlyweightProduct productDetails = null;
        int productsLeft = 0;

        try {
            productDetails = FlyweightProductFactory.getFlyweight(Integer.parseInt(code));
            productsLeft = Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            System.out.println("Failed to add the product! Incorrect input!");
        }
        int[] freeShelf = Magazine.getMagazine().findFreeShelvesForProduct(productDetails);

        displayStatement(fillMagazine(productsLeft, freeShelf, productDetails));

    }

    public void checkIfDataIsReadyAndSave(String code, String name, String priceNet, String capacity, String amount) {
        if (code.isEmpty() || name.isEmpty() || priceNet.isEmpty() || capacity.isEmpty() || amount.isEmpty()) {
            System.out.println("Data is missing!");
        } else {
            saveNewProduct(code, name, priceNet, capacity, amount);
        }
    }

    private void saveNewProduct(String code, String name, String priceNet, String capacity, String amount) {
        int[] freeShelf = null;
        int productsLeft = 0;
        FlyweightProduct productDetails = null;
        try {
            productDetails = FlyweightProductFactory.getFlyweight(
                    Integer.parseInt(code),
                    name,
                    Double.parseDouble(priceNet),
                    Double.parseDouble(capacity)
            );
            freeShelf = Magazine.getMagazine().findFreeShelvesForProduct(productDetails);
            productsLeft = Integer.parseInt(amount);

        } catch (NumberFormatException e) {
            System.out.println("Failed to add the product! Incorrect input!");
        }

        displayStatement(fillMagazine(productsLeft, freeShelf, productDetails));
    }

    private boolean fillMagazine(int productsLeft, int[] freeShelf, FlyweightProduct flyweightProduct) {
        Product product = new Product(flyweightProduct);
        do {
            productsLeft = Magazine.
                    getMagazine().
                    getShelf(freeShelf[0], freeShelf[1])
                    .addProducts(product, productsLeft);
            freeShelf = Magazine.getMagazine().findFreeShelvesForProduct(flyweightProduct);
        } while (productsLeft > 0);

        return productsLeft == 0;
    }

    private void displayStatement(boolean isEnoughSpace) {
        if (isEnoughSpace)
            System.out.println("All product are saved to the magazine!");
        else
            System.out.println("Not enough space to fit all product!");
    }

    public void checkIfProductExistAndDelete(String code) {
        if (code.isEmpty())
            System.out.println("No product code entered!");
        else
            deleteProduct(code);
    }

    private void deleteProduct(String code) {
        try {
            Magazine.getMagazine().checkIfProductExitsAndDeleteIt(Integer.parseInt(code));
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    public void submitOrder(FlyweightProduct product, String amount) {
        if (amount.isEmpty() || product == null)
            System.out.println("Fulfill whole form!");
        else
            takeProductsFromMagazine(product, amount);
    }

    private void takeProductsFromMagazine(FlyweightProduct product, String amount) {
        String data = product.toString().split(" ")[0];
        int value, code;
        try {
            value = Integer.parseInt(amount);
            code = Integer.parseInt(data);
        } catch (NumberFormatException e) {
            System.out.println("Amount must be integer!");
            return;
        }
        printTakenProduct(Magazine.getMagazine().checkIfProductExistAndGetIt(code, value));
    }

    private void printTakenProduct(ArrayList<int[]> cords) {
        for (int[] c : cords) {
            System.out.println("From row " + c[0]
                    + " and shelf " + c[1]
                    + " has been taken " + c[2]
                    + "products.");
        }
    }


}
