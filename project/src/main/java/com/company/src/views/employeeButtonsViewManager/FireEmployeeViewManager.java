package com.company.src.views.employeeButtonsViewManager;

import com.company.src.customNodes.MyButton;
import com.company.src.views.EmployeeViewManager;
import com.company.src.views.ViewManager;
import com.company.src.models.individual.PeopleContainer;
import com.company.src.models.individual.Person;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class FireEmployeeViewManager extends ViewManager {

    private static final int HEIGHT = 300;
    private static final int WIDTH = 400;
    private static final int MIN_HEIGHT = 220;
    private static final int MIN_WIDTH = 250;
    private static final int MAX_HEIGHT = 410;
    private static final int MAX_WIDTH = 600;

    private Stage oldStage;

    private ComboBox<Person> comboBox;
    private Button fire;
    private Button back;
    private Label selectEmployee;


    public FireEmployeeViewManager() {
        super(HEIGHT, WIDTH, MIN_HEIGHT, MIN_WIDTH, MAX_HEIGHT, MAX_WIDTH);
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
        comboBox.getItems().addAll(PeopleContainer.getPeopleContainer().getAllEmployee());
        pane.add(comboBox, 1, 0);
    }

    private void initNodes() {
        fire = MyButton.getMyButton("Fire", mySmallFont, null);
        back = MyButton.getMyButton("Back", mySmallFont, null);
        selectEmployee = new Label("Select employee to fire");
        pane.add(selectEmployee, 0, 0);
        pane.add(fire, 1, 2);
        pane.add(back, 0, 2);
    }

    private void setButtonsFunctionality() {
        back.setOnAction((e) -> getPreviousStage());
        fire.setOnAction((e) -> fireButton());
    }

    private void fireButton() {
        if (comboBox.getValue() == null)
            System.out.println("Fulfill whole form!");
        else
            fire();
    }

    private void fire() {
        String data = comboBox.getValue().toString().split(" ")[0];
        int code;
        try {
            code = Integer.parseInt(data);

        } catch (NumberFormatException e) {
            System.out.println("You can not fire Person");
            return;
        }
        System.out.println(PeopleContainer.getPeopleContainer().deleteEmployeeFromContainer(code));
        refreshTable();
    }
}