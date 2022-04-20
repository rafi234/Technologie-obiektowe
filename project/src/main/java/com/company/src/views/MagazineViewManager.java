package com.company.src.views;

import com.company.src.customNodes.MyButton;
import com.company.src.views.magazineButtonsViewManager.*;
import com.company.src.models.magazine.Magazine;
import com.company.src.models.magazine.Shelf;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MagazineViewManager extends ViewManager {
    private static final int HEIGHT = 450;
    private static final int WIDTH = 800;
    private static final int MIN_HEIGHT = 300;
    private static final int MIN_WIDTH = 180;
    private static final int MAX_HEIGHT = 720;
    private static final int MAX_WIDTH = 1020;

    public static boolean[] whatToShow = {true, true, true, true, true, true, true};

    public MagazineViewManager() {
        super(HEIGHT, WIDTH, MIN_HEIGHT, MIN_WIDTH, MAX_HEIGHT, MAX_WIDTH);
        createPage();
    }

    @Override
    protected void createPage() {
        TableView<Shelf> tableView = getTable();

        VBox vBox = new VBox(tableView);
        pane.add(vBox, 1, 1);

        pane.add(getButtons(), 3, 1);

        scene.setRoot(pane);
        stage.setScene(scene);
    }

    public TableView<Shelf> getTable() {
        TableView<Shelf> tableView = new TableView<>();

        tableView.setPlaceholder(new Label("Magazine is empty."));

        if (whatToShow[0]) {
            TableColumn<Shelf, String> column1 = new TableColumn<>("code");
            column1.setCellValueFactory(p -> {
                try {
                    return new SimpleStringProperty(String.valueOf(p.getValue().getProduct().getProductFlyweight().getCode()));
                } catch (NullPointerException ignored) {}
                return null;
            });
            tableView.getColumns().add(column1);
        }

        if (whatToShow[1]) {
            TableColumn<Shelf, String> column2 = new TableColumn<>("Product");
            column2.setCellValueFactory(p -> {
                try {
                    return new SimpleStringProperty(p.getValue().getProduct().getProductFlyweight().getName());
                } catch (NullPointerException ignored) {}
                return null;
            });
            tableView.getColumns().add(column2);
        }

        if (whatToShow[2]) {
            TableColumn<Shelf, String> column3 = new TableColumn<>("Price net");
            column3.setCellValueFactory(p -> {
                try {
                   return new SimpleStringProperty(String.valueOf(p.getValue().getProduct().getProductFlyweight().getPriceNet()));
                } catch (NullPointerException ignored) {}
                return null;
            });
            tableView.getColumns().add(column3);
        }

        if (whatToShow[3]) {
            TableColumn<Shelf, String> column4 = new TableColumn<>("Capacity");
            column4.setCellValueFactory(p -> {
                try {
                    return new SimpleStringProperty(String.valueOf(p.getValue().getProduct().getProductFlyweight().getCapacity()));
                } catch (NullPointerException ignored) {}
                return null;
            });
            tableView.getColumns().add(column4);
        }

        if (whatToShow[4]) {
            TableColumn<Shelf, String> column5 = new TableColumn<>("Row");
            column5.setCellValueFactory(p -> {
                try {
                    return new SimpleStringProperty(String.valueOf(p.getValue().getRow()));
                } catch (NullPointerException ignored) {}
                return null;
            });
            tableView.getColumns().add(column5);
        }

        if (whatToShow[5]) {
            TableColumn<Shelf, String> column6 = new TableColumn<>("Shelf");
            column6.setCellValueFactory(p -> {
                try {
                    return new SimpleStringProperty(String.valueOf(p.getValue().getShelfPlace()));
                } catch (NullPointerException ignored) {}
                return null;
            });
            tableView.getColumns().add(column6);
        }

        if (whatToShow[6]) {
            TableColumn<Shelf, String> column7 = new TableColumn<>("Amount in Magazine");
            column7.setCellValueFactory(p -> {
                try {
                    if(p.getValue().getNumberOfProducts() != 0)
                        return new SimpleStringProperty(String.valueOf(p.getValue().getNumberOfProducts()));
                } catch (NullPointerException ignored) {}
                return null;
            });
            tableView.getColumns().add(column7);
        }

        this.fillTable(tableView);
        return tableView;
    }

    private void fillTable(TableView<Shelf> tableView) {
        ArrayList<Shelf> shelves = Magazine.getMagazine().getAllProducts();
        for (Shelf shelf : shelves) {
            tableView.getItems().add(shelf);
        }
    }

    private VBox getButtons() {
        Button addNewProduct = MyButton.getMyButton("Add new product", myTinyFont, null);
        addNewProduct.setOnAction((event) -> {
            AddNewProductButtonViewManager manager = new AddNewProductButtonViewManager();
            manager.initPage(stage);
        });

        Button addExistingProduct = MyButton.getMyButton("Add existing product", myTinyFont, null);
        addExistingProduct.setOnAction(event -> {
            AddExistingProductViewManager manager = new AddExistingProductViewManager();
            manager.initPage(stage);
        });


        Button deleteProduct = MyButton.getMyButton("Delete product", myTinyFont, null);
        deleteProduct.setOnAction((e) -> {
            DeleteButtonViewManager manager = new DeleteButtonViewManager();
            manager.initPage(stage);
        });

        Button submitOrder = MyButton.getMyButton("Submit order", myTinyFont, null);
        submitOrder.setOnAction((event -> {
            SubmitOrderButtonViewManager manager = new SubmitOrderButtonViewManager();
            manager.initPage(stage);
        }));

        Button filter = MyButton.getMyButton("Filter", myTinyFont, null);
        filter.setOnAction(event -> {
            FilterButtonViewManager manager = new FilterButtonViewManager();
            manager.initPage(stage);
        });

        Button goBackToMenu = new Button("Back to menu");
        goBackToMenu.setOnAction(event -> {
            MainViewManager manager = new MainViewManager();
            manager.initPage(stage);
        });

        VBox buttons = new VBox();
        buttons.setSpacing(10);
        buttons.snapPositionY(10);
        buttons.getChildren().addAll(addExistingProduct, addNewProduct, deleteProduct, submitOrder, filter, goBackToMenu);
        return buttons;
    }

    @Override
    public void initPage(Stage oldStage) {
        oldStage.hide();
        stage.show();
    }
}
