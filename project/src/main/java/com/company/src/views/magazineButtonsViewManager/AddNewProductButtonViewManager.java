package com.company.src.views.magazineButtonsViewManager;

import com.company.src.controllers.ProductController;
import com.company.src.customNodes.MyButton;
import com.company.src.views.MagazineViewManager;
import com.company.src.views.ViewManager;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddNewProductButtonViewManager extends ViewManager {

    private static final int HEIGHT = 300;
    private static final int WIDTH = 400;
    private static final int MIN_HEIGHT = 220;
    private static final int MIN_WIDTH = 250;
    private static final int MAX_HEIGHT = 410;
    private static final int MAX_WIDTH = 600;

    private Stage oldStage;

    private ProductController productController;

    private TextField[] txtFields = new TextField[5];
    private Text[] txt = new Text[5];

    private final String[] labels = {
            "Name: ",
            "Price net: ",
            "Code: ",
            "Capacity[dm^3]:  ",
            "Amount: "
    };

    public AddNewProductButtonViewManager() {
        super(HEIGHT, WIDTH, MIN_HEIGHT, MIN_WIDTH, MAX_HEIGHT, MAX_WIDTH);
        productController = new ProductController();
        this.createPage();
    }

    @Override
    protected void createPage() {
        initTextFields();
        initButton();
    }

    @Override
    public void initPage(Stage oldStage) {
        this.oldStage = oldStage;
        oldStage.hide();
        stage.show();
    }

    private void initTextFields() {
        for (int i = 0; i < 5; i++) {
            txt[i] = new Text(labels[i]);
            txt[i].setFont(mySmallFont);
            pane.add(txt[i], 0, i);
            txtFields[i] = new TextField();
            pane.add(txtFields[i], 1, i);
        }
    }

    private void initButton() {
        Button save = MyButton.getMyButton("Save", mySmallFont, null);
        save.setOnAction((e) -> {
                    productController.checkIfDataIsReadyAndSave(
                            txtFields[2].getText(),
                            txtFields[0].getText(),
                            txtFields[1].getText(),
                            txtFields[3].getText(),
                            txtFields[4].getText()
                    );
                    refreshTable();
                }
        );
        Button back = MyButton.getMyButton("Back", mySmallFont, null);
        back.setOnAction(event -> {
            getPreviousStage();
        });
        pane.add(back, 0, 5);
        pane.add(save, 1, 5);
    }

    private void refreshTable() {
        MagazineViewManager manager = new MagazineViewManager();
        manager.initPage(stage);
    }

    private void getPreviousStage() {
        stage.close();
        oldStage.show();
    }
}