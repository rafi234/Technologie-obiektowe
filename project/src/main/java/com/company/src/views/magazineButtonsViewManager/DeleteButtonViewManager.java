package com.company.src.views.magazineButtonsViewManager;

import com.company.src.controllers.ProductController;
import com.company.src.customNodes.MyButton;
import com.company.src.views.MagazineViewManager;
import com.company.src.views.ViewManager;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DeleteButtonViewManager extends ViewManager {

    private static final int HEIGHT = 300;
    private static final int WIDTH = 400;
    private static final int MIN_HEIGHT = 220;
    private static final int MIN_WIDTH = 250;
    private static final int MAX_HEIGHT = 410;
    private static final int MAX_WIDTH = 600;

    private TextField txtCode = new TextField();
    private Button deleteButton = MyButton.getMyButton("Delete", mySmallFont, null);
    private Button backButton = MyButton.getMyButton("Back", mySmallFont, null);
    private Stage oldStage;
    private ProductController productController;

    public DeleteButtonViewManager() {
        super(HEIGHT, WIDTH, MIN_HEIGHT, MIN_WIDTH, MAX_HEIGHT, MAX_WIDTH);
        productController = new ProductController();
        createPage();
    }

    @Override
    protected void createPage() {
        addButtonsFunctionality();
        addNodesToPane();
    }

    @Override
    public void initPage(Stage oldStage) {
        this.oldStage = oldStage;
        oldStage.hide();
        stage.show();
    }

    private void addButtonsFunctionality() {
        backButton.setOnAction((e) -> getPreviousStage());
        deleteButton.setOnAction((e) -> deleteProduct());
    }

    private void getPreviousStage() {
        stage.hide();
        oldStage.show();
    }

    private void addNodesToPane() {
        pane.add(txtCode, 1, 0);
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(backButton, deleteButton);
        pane.add(hBox, 1, 1);
    }

    private void deleteProduct() {
        try {
            productController.checkIfProductExistAndDelete(txtCode.getText());
            refreshTable();
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    private void refreshTable() {
        MagazineViewManager manager = new MagazineViewManager();
        manager.initPage(stage);
    }
}