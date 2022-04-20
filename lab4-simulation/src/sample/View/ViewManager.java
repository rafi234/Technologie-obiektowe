package sample.View;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ViewManager {
    private static final int HEIGHT = 300;
    private static final int WIDTH = 300;
    private final AnchorPane mainPane;
    private final Stage mainStage;

    public ViewManager() {
        mainPane = new AnchorPane();
        Scene mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        this.setBackground();
        this.createStartButtons();

    }

    public Stage getMainStage(){
        return mainStage;
    }

    private void setBackground(){
        BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        mainPane.setBackground(new Background(backgroundFill));
    }

    private void createStartButtons(){
        Button button = new Button("Start Simulation 0%");
        Button button1 = new Button("Start Simulation 10%");
        button1.setLayoutX(WIDTH/2. - 40.);
        button1.setLayoutY(60.);
        button1.setOnAction(e -> {
            SimulationViewManager manager = new SimulationViewManager(500, 1020, 720, 0.1);
            manager.createNewSimulation(mainStage);
        });
        button.setLayoutX(WIDTH/2. - 40.);
        button.setLayoutY(20.);
        button.setOnAction(e -> {
            SimulationViewManager manager = new SimulationViewManager(500, 1020, 720, 0.);
            manager.createNewSimulation(mainStage);
        });
        mainPane.getChildren().addAll(button, button1);
    }

}