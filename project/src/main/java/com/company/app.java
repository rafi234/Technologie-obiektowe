package com.company;

import com.company.src.views.MainViewManager;
import javafx.application.Application;
import javafx.stage.Stage;


public class app extends Application {
    @Override
    public void start(Stage primaryStage) {
        MainViewManager loginViewManager = new MainViewManager();
        primaryStage = loginViewManager.getStage();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
