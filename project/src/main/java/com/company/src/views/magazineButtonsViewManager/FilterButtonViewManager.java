package com.company.src.views.magazineButtonsViewManager;

import com.company.src.customNodes.MyButton;
import com.company.src.views.MagazineViewManager;
import com.company.src.views.ViewManager;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FilterButtonViewManager extends ViewManager {

    private static final int HEIGHT = 300;
    private static final int WIDTH = 200;
    private static final int MIN_HEIGHT = 220;
    private static final int MIN_WIDTH = 150;
    private static final int MAX_HEIGHT = 410;
    private static final int MAX_WIDTH = 400;

    private CheckBox[] checkBox;
    private Button confirm;

    private static final String[] checkBoxTitles = {
            "Code",
            "Name",
            "Price Net",
            "Capacity",
            "Row",
            "Shelf",
            "Amount in magazine"
    };

    public FilterButtonViewManager() {
        super(HEIGHT, WIDTH, MIN_HEIGHT, MIN_WIDTH, MAX_HEIGHT, MAX_WIDTH);
        pane.setAlignment(Pos.TOP_LEFT);
        createPage();
    }

    @Override
    protected void createPage() {
        createCheckBox();
        initNodes();
    }

    @Override
    public void initPage(Stage oldStage) {
        oldStage.hide();
        stage.show();
    }

    private void createCheckBox() {
        checkBox = new CheckBox[7];
        for (int i = 0; i < 7; ++i) {
            checkBox[i] = new CheckBox(checkBoxTitles[i]);
            checkBox[i].setSelected(MagazineViewManager.whatToShow[i]);
        }

        VBox vbox = new VBox();
        vbox.getChildren().addAll(checkBox);
        vbox.setSpacing(10);
        pane.add(vbox, 0, 0);
    }

    private void initNodes() {
        confirm = MyButton.getMyButton("Confirm", mySmallFont, null);
        confirm.setOnAction(event -> {
            confirmButton();
            refreshTable();
        });

        pane.add(confirm, 0, 1);
    }

    private void confirmButton() {
        for (int i = 0; i < 7; ++i)
            MagazineViewManager.whatToShow[i] = checkBox[i].isSelected();
    }

    private void refreshTable() {
        MagazineViewManager manager = new MagazineViewManager();
        manager.initPage(stage);
    }
}
