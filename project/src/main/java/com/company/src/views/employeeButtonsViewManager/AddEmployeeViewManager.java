package com.company.src.views.employeeButtonsViewManager;

import com.company.src.controllers.EmployeeController;
import com.company.src.customNodes.MyButton;
import com.company.src.views.EmployeeViewManager;
import com.company.src.views.ViewManager;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddEmployeeViewManager extends ViewManager {

    private static final int HEIGHT = 600;
    private static final int WIDTH = 400;
    private static final int MIN_HEIGHT = 220;
    private static final int MIN_WIDTH = 250;
    private static final int MAX_HEIGHT = 810;
    private static final int MAX_WIDTH = 600;

    private Stage oldStage;

    private static final int NUMBER_OF_DATA = 10;
    private TextField[] txtFields = new TextField[NUMBER_OF_DATA];
    private Text[] txt = new Text[NUMBER_OF_DATA];

    private EmployeeController employeeController;

    private final String[] labels = {
            "Name: ",
            "Surname: ",
            "Phone number: ",
            "Email:  ",
            "Street: ",
            "City: ",
            "Voivodeship: ",
            "Postal code: ",
            "Country: ",
            "Salary: "
    };

    public AddEmployeeViewManager() {
        super(HEIGHT, WIDTH, MIN_HEIGHT, MIN_WIDTH, MAX_HEIGHT, MAX_WIDTH);
        employeeController = new EmployeeController();
        this.createPage();
    }

    @Override
    protected void createPage() {
        this.initTextFields();
        this.initButton();
    }

    @Override
    public void initPage(Stage oldStage) {
        this.oldStage = oldStage;
        oldStage.hide();
        stage.show();
    }

    private void initTextFields() {
        for (int i = 0; i < NUMBER_OF_DATA; i++) {
            txt[i] = new Text(labels[i]);
            txt[i].setFont(mySmallFont);
            pane.add(txt[i], 0, i);
            txtFields[i] = new TextField();
            pane.add(txtFields[i], 1, i);
        }
    }

    private void initButton() {
        Button save = MyButton.getMyButton("Add person", mySmallFont, null);
        save.setOnAction((e) -> {
                    employeeController.addEmployee(
                            txtFields[0].getText(),
                            txtFields[1].getText(),
                            txtFields[2].getText(),
                            txtFields[3].getText(),
                            txtFields[4].getText(),
                            txtFields[5].getText(),
                            txtFields[6].getText(),
                            txtFields[7].getText(),
                            txtFields[8].getText(),
                            txtFields[9].getText()
                    );
                    refreshTable();
                }
        );

        Button back = MyButton.getMyButton("Back", mySmallFont, null);
        back.setOnAction(event -> getPreviousStage());

        pane.add(back, 0, NUMBER_OF_DATA);
        pane.add(save, 1, NUMBER_OF_DATA);
    }

    private void refreshTable() {
        EmployeeViewManager manager = new EmployeeViewManager();
        manager.initPage(stage);
    }

    private void getPreviousStage() {
        stage.close();
        oldStage.show();
    }
}



