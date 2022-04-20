package com.company.src.views;

import com.company.src.customNodes.MyButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainViewManager extends ViewManager {
    private static final int HEIGHT = 500;
    private static final int WIDTH = 300;
    private static final int MIN_HEIGHT = 300;
    private static final int MIN_WIDTH = 200;
    private static final int MAX_HEIGHT = 700;
    private static final int MAX_WIDTH = 500;

    public MainViewManager() {
        super(HEIGHT, WIDTH, MIN_HEIGHT, MIN_WIDTH, MAX_HEIGHT, MAX_WIDTH);
        createPage();
    }

    @Override
    protected void createPage() {
        Text menuText = new Text("Menu");
        menuText.setFont(myBigFont);

        VBox vBoxOptions = initButtons();

        pane.add(menuText, 1, 0);
        pane.add(vBoxOptions, 0, 2, 2, 1);
    }

    @Override
    public void initPage(Stage oldStage) {
        oldStage.hide();
        stage.show();
    }

    private VBox initButtons() {
        VBox vBoxOptions = new VBox();
        vBoxOptions.setSpacing(15);

        Button magazine = MyButton.getMyButton("Magazine", mySmallFont, new Insets(5, 15, 5, 15));
        Button employee = MyButton.getMyButton("Employee", mySmallFont, new Insets(5, 15, 5, 15));

        magazine.setOnAction((event) -> {
            MagazineViewManager manager = new MagazineViewManager();
            manager.initPage(stage);
        });

        employee.setOnAction(event -> {
            EmployeeViewManager manager = new EmployeeViewManager();
            manager.initPage(stage);
        });
        vBoxOptions.getChildren().addAll(magazine, employee);
        vBoxOptions.setAlignment(Pos.CENTER);

        return vBoxOptions;
    }
}
