package com.company.src.models.magazine;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Shelf {
    private int row;
    private int shelfPlace;
    private double shelfCapacity;
    private double currentProductsCapacity = 0.;
    private Product product = null;
    private int numberOfProducts = 0;
    private boolean isEmpty = true;

    public Shelf(int row, int shelfPlace) {
        this.row= row;
        this.shelfPlace = shelfPlace;
        this.shelfCapacity = Constants.DEFAULT_CAPACITY_OF_SHELF;
    }

    public int addProducts(Product product, int numberOfProducts) {
        if(this.product == null)
            this.product = product;
        int leftProducts = checkHowManyProductsWillFitOnAShelf(numberOfProducts);
        this.numberOfProducts += numberOfProducts - leftProducts;
        this.currentProductsCapacity += product.getProductFlyweight().getCapacity() * (numberOfProducts - leftProducts);
        this.isEmpty = false;

        return leftProducts;
    }

    public void cleanShelf() {
        currentProductsCapacity = 0;
        product = null;
        numberOfProducts = 0;
        isEmpty = true;
    }

    public int getProducts(int numberOfProducts) {
        int missingProduct;
        if (this.numberOfProducts > 0) {
            this.numberOfProducts -= numberOfProducts;
            this.currentProductsCapacity -= numberOfProducts * product.getProductFlyweight().getCapacity();
            if (this.numberOfProducts < 0) {
                missingProduct = Math.abs(this.numberOfProducts);
                cleanShelf();
                return missingProduct;
            }
            if (this.numberOfProducts == 0) {
                cleanShelf();
            }
            return 0;
        }
        return -1;
    }

    private int checkHowManyProductsWillFitOnAShelf(int numberOfProductsToGet) {
        if (numberOfProductsToGet * product.getProductFlyweight().getCapacity() <= shelfCapacity - currentProductsCapacity)
            return 0;
        else
            return numberOfProductsToGet - maxProductsNumberThatWillFit();
    }

    private int maxProductsNumberThatWillFit() {
        return (int)Math.floor((shelfCapacity - currentProductsCapacity) / product.getProductFlyweight().getCapacity());
    }
}

