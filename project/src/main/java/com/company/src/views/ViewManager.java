package com.company.src.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public abstract class ViewManager {

    protected GridPane pane;
    protected final Stage stage;
    protected Scene scene;

    protected final Font myTinyFont = Font.font("Tahoma", FontWeight.NORMAL, 12);
    protected final Font mySmallFont = Font.font("Tahoma", FontWeight.NORMAL, 15);
    protected final Font myBigFont = Font.font("Tahoma", FontWeight.BOLD, 35);

    protected abstract void createPage();

    public abstract void initPage(Stage oldStage);

    public ViewManager(
            int height, int width,
            int minHeight, int minWidth,
            int maxHeight, int maxWidth
    ) {
        this.initGridPane();
        scene = new Scene(pane, width, height);
        stage = new Stage();
        stage.setMinHeight(minHeight);
        stage.setMinWidth(minWidth);
        stage.setMaxHeight(maxHeight);
        stage.setMaxWidth(maxWidth);
        stage.setScene(scene);
        this.setBackground(Color.LIGHTBLUE);
    }

    private void initGridPane() {
        this.pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(25, 25, 25, 25));
    }

    private void setBackground(Color color) {
        BackgroundFill backgroundFill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
        pane.setBackground(new Background(backgroundFill));
    }

    public Stage getStage() {
        return stage;
    }
}
