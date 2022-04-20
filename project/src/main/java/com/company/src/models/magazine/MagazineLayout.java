package com.company.src.models.magazine;

import com.company.src.models.individual.productFlyweight.FlyweightProduct;
import com.company.src.models.individual.productFlyweight.FlyweightProductFactory;

import java.util.ArrayList;

public class MagazineLayout {
    private ArrayList<ArrayList<Shelf>> magazineLayout;

    public MagazineLayout() {
        this.initMagazineLayout();
        this.initMagazineLayoutWithDefaultValue();

        magazineLayout.get(0).get(0).addProducts(
                new Product(
                        FlyweightProductFactory.getFlyweight(
                                234,
                                "Bułki",
                                0.59,
                                0.1
                        )
                ),
                100
        ); //TODO delete
        magazineLayout.get(0).get(1).addProducts(
                new Product(
                        FlyweightProductFactory.getFlyweight(
                                232,
                                "Browary",
                                3.49,
                                0.3
                        )
                ),
                100
        ); //TODO delete
    }

    public void initMagazineLayout() {
        magazineLayout = new ArrayList<>();

        for (int i = 0; i < Constants.DEFAULT_NUMBER_OF_ROWS; ++i)
            addRow();
    }

    public void initMagazineLayoutWithDefaultValue() {
        for (int i = 0; i < Constants.DEFAULT_NUMBER_OF_ROWS; ++i) {
            for (int j = 0; j < Constants.DEFAULT_NUMBER_OF_SHELF; ++j)
                addShelf(i, j);
        }
    }

    public Shelf getShelf(int i, int j) {
        return magazineLayout.get(i).get(j);
    }

    public int[] findFreeShelvesForProduct(FlyweightProduct productToAdd) {
        for (int i = 0; i < magazineLayout.size(); ++i) {
            for (int j = 0; j < magazineLayout.get(i).size(); ++j) {
                if (magazineLayout.get(i).get(j).isEmpty()) {
                    return getCords(i, j);
                } else if (magazineLayout.get(i).get(j).getProduct().getProductFlyweight() == productToAdd) {
                    if ((magazineLayout.get(i).get(j).getNumberOfProducts() + 1) * productToAdd.getCapacity()
                            <= magazineLayout.get(i).get(j).getShelfCapacity())
                        return getCords(i, j);
                }
            }
        }
        return null;
    }

    public void addShelf(int row, int shelf) {
        magazineLayout.get(row).add(new Shelf(row, shelf));
    }

    public void addRow() {
        magazineLayout.add(new ArrayList<>());
    }

    public void setCapacityOfShelf(int row, int shelf, double newCapacity) {
        magazineLayout.get(row).get(shelf).setShelfCapacity(newCapacity);
    }

    public void addFullShelf(Shelf shelf) {
        magazineLayout.get(shelf.getRow())
                .get(shelf.getShelfPlace())
                .addProducts(shelf.getProduct(), shelf.getNumberOfProducts());
    }

    public ArrayList<Shelf> getAllProducts() {
        ArrayList<Shelf> products = new ArrayList<>();
        for (ArrayList<Shelf> shelves : magazineLayout) {
            for (Shelf shelf : shelves) {
                if (shelf.getProduct() != null)
                    products.add(shelf);
            }
        }
        return products;
    }

    public void checkIfProductExitsAndDeleteIt(int code) {
        for (ArrayList<Shelf> shelves : magazineLayout) {
            for (Shelf currentShelf : shelves) {
                try {
                    if (code == currentShelf.getProduct().getProductFlyweight().getCode()) {
                        currentShelf.cleanShelf();
                    }
                } catch (NullPointerException ignored) {
                }
            }
        }
    }

    public ArrayList<int[]> checkIfProductExistAndGetIt(int code, int numberOfProductsToGet) {
        ArrayList<int[]> cords = new ArrayList<>();
        int numberOfProducts;

        for (int i = 0; i < magazineLayout.size(); ++i) {
            for (int j = 0; j < magazineLayout.get(i).size(); ++j) {
                if (magazineLayout.get(i).get(j).getProduct() != null) {
                    if (code == magazineLayout.get(i).get(j).getProduct().getProductFlyweight().getCode()) {
                        numberOfProducts = magazineLayout.get(i).get(j).getProducts(numberOfProductsToGet);
                        int[] cord = {i, j, numberOfProductsToGet - numberOfProducts};
                        cords.add(cord);
                        if (numberOfProducts == 0) {
                            return cords;
                        } else if (numberOfProducts == -1) {
                            return null;
                        }
                        numberOfProductsToGet = numberOfProducts;
                    }
                }
            }
        }
        return cords;
    }

    private int[] getCords(int i, int j) {
        int[] cords = new int[2];
        cords[0] = i;
        cords[1] = j;
        return cords;
    }
}

