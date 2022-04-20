package com.company.src.views.magazineButtonsViewManager;

import com.company.src.controllers.ProductController;
import com.company.src.customNodes.MyButton;
import com.company.src.models.individual.productFlyweight.FlyweightProduct;
import com.company.src.models.individual.productFlyweight.FlyweightProductFactory;
import com.company.src.views.MagazineViewManager;
import com.company.src.views.ViewManager;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;


public class AddExistingProductViewManager extends ViewManager {

    private static final int HEIGHT = 300;
    private static final int WIDTH = 400;
    private static final int MIN_HEIGHT = 220;
    private static final int MIN_WIDTH = 250;
    private static final int MAX_HEIGHT = 410;
    private static final int MAX_WIDTH = 600;

    private Stage oldStage;

    private ProductController productController;

    private ComboBox<FlyweightProduct> comboBox;
    private Button submit;
    private Button back;
    private TextField txtAmount;
    private Label amount;
    private Label selectProduct;


    public AddExistingProductViewManager() {
        super(HEIGHT, WIDTH, MIN_HEIGHT, MIN_WIDTH, MAX_HEIGHT, MAX_WIDTH);
        productController = new ProductController();
        createPage();
    }

    @Override
    protected void createPage() {
        createComboBox();
        initNodes();
        setButtonsFunctionality();
    }

    @Override
    public void initPage(Stage oldStage) {
        this.oldStage = oldStage;
        oldStage.hide();
        stage.show();
    }

    private void getPreviousStage() {
        stage.close();
        oldStage.show();
    }

    private void refreshTable() {
        MagazineViewManager manager = new MagazineViewManager();
        manager.initPage(stage);
    }

    private void createComboBox() {
        ArrayList<FlyweightProduct> a = FlyweightProductFactory.getAllNameProducts();
        comboBox = new ComboBox<>();
        comboBox.getItems().addAll(a);
        pane.add(comboBox, 1, 0);
    }

    private void initNodes() {
        submit = MyButton.getMyButton("Add product", mySmallFont, null);
        back = MyButton.getMyButton("Back", mySmallFont, null);
        txtAmount = new TextField();
        amount = new Label("Amount: ");
        selectProduct = new Label("Select product");
        pane.add(selectProduct, 0, 0);
        pane.add(amount, 0, 1);
        pane.add(txtAmount, 1, 1);
        pane.add(submit, 1, 2);
        pane.add(back, 0, 2);
    }

    private void setButtonsFunctionality() {
        back.setOnAction((e) -> getPreviousStage());
        submit.setOnAction((e) -> {
            productController.addExistingProduct(txtAmount.getText(), comboBox.getValue());
            refreshTable();
        });
    }
}
