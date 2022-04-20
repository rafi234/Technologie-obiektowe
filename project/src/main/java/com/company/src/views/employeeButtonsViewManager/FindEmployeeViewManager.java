package com.company.src.views.employeeButtonsViewManager;

import com.company.src.controllers.EmployeeController;
import com.company.src.customNodes.MyButton;
import com.company.src.views.EmployeeViewManager;
import com.company.src.views.ViewManager;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class FindEmployeeViewManager extends ViewManager {

    private static final int HEIGHT = 300;
    private static final int WIDTH = 400;
    private static final int MIN_HEIGHT = 220;
    private static final int MIN_WIDTH = 250;
    private static final int MAX_HEIGHT = 410;
    private static final int MAX_WIDTH = 600;

    private Stage oldStage;

    private ComboBox<String> comboBox;
    private Button find;
    private Button back;
    private Label selectEmployee;
    private TextField txtCriterion;
    private Label criterion;

    private EmployeeController employeeController;

    public FindEmployeeViewManager() {
        super(HEIGHT, WIDTH, MIN_HEIGHT, MIN_WIDTH, MAX_HEIGHT, MAX_WIDTH);
        employeeController = new EmployeeController();
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
        EmployeeViewManager manager = new EmployeeViewManager();
        manager.initPage(stage);
    }

    private void createComboBox() {
        comboBox = new ComboBox<>();
        comboBox.getItems().addAll("ID", "Name", "Surname");
        pane.add(comboBox, 1, 0);
    }

    private void initNodes() {
        find = MyButton.getMyButton("Find", mySmallFont, null);
        back = MyButton.getMyButton("Back", mySmallFont, null);
        selectEmployee = new Label("Select a search criterion: ");
        criterion = new Label("Find: ");
        txtCriterion = new TextField();
        pane.add(criterion, 0, 1);
        pane.add(txtCriterion, 1, 1);
        pane.add(selectEmployee, 0, 0);
        pane.add(find, 1, 2);
        pane.add(back, 0, 2);
    }

    private void setButtonsFunctionality() {
        back.setOnAction((e) -> getPreviousStage());
        find.setOnAction((e) -> {
            employeeController.findEmployee(txtCriterion.getText(), comboBox.getValue());
            refreshTable();
        });
    }
}
