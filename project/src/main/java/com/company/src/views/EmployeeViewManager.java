package com.company.src.views;

import com.company.src.controllers.EmployeeController;
import com.company.src.views.employeeButtonsViewManager.AddEmployeeViewManager;
import com.company.src.views.employeeButtonsViewManager.FindEmployeeViewManager;
import com.company.src.views.employeeButtonsViewManager.FireEmployeeViewManager;
import com.company.src.models.individual.Employee;
import com.company.src.models.individual.PeopleContainer;
import com.company.src.models.individual.Person;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class EmployeeViewManager extends ViewManager {
    private static final int HEIGHT = 450;
    private static final int WIDTH = 1300;
    private static final int MIN_HEIGHT = 300;
    private static final int MIN_WIDTH = 180;
    private static final int MAX_HEIGHT = 720;
    private static final int MAX_WIDTH = 1700;

    public static final CheckBox isFoundListOfPeopleOnDisplay = new CheckBox();

    public static boolean[] whatToShow = {true, true, true, true, true, true, true, true, true, true, true, true};

    public EmployeeViewManager() {
        super(HEIGHT, WIDTH, MIN_HEIGHT, MIN_WIDTH, MAX_HEIGHT, MAX_WIDTH);
        createPage();
    }

    @Override
    protected void createPage() {
        TableView<Person> tableView = getTable();

        VBox vBox = new VBox(tableView);
        pane.add(vBox, 1, 1);
        pane.add(getButtons(), 3, 1);
        Label emptyLabel = new Label("       ");
        pane.add(emptyLabel, 2, 1);
        scene.setRoot(pane);
        stage.setScene(scene);
    }

    public TableView<Person> getTable() {
        TableView<Person> tableView = new TableView<>();
        tableView.setMinWidth(1000);

        tableView.setPlaceholder(new Label("Not even boss in here!?"));

        if (whatToShow[0]) {
            TableColumn<Person, String> column1 = new TableColumn<>("Name");
            column1.setCellValueFactory(p -> {
                try {
                    return new SimpleStringProperty(String.valueOf(p.getValue().getName()));
                } catch (NullPointerException ignored) {
                }
                return null;
            });
            tableView.getColumns().add(column1);
        }

        if (whatToShow[1]) {
            TableColumn<Person, String> column2 = new TableColumn<>("Surname");
            column2.setCellValueFactory(p -> {
                try {
                    return new SimpleStringProperty(p.getValue().getSurname());
                } catch (NullPointerException ignored) {
                }
                return null;
            });
            tableView.getColumns().add(column2);
        }

        if (whatToShow[2]) {
            TableColumn<Person, String> column3 = new TableColumn<>("Phone number");
            column3.setCellValueFactory(p -> {
                try {
                    return new SimpleStringProperty(String.valueOf(p.getValue().getPhoneNumber()));
                } catch (NullPointerException ignored) {
                }
                return null;
            });
            tableView.getColumns().add(column3);
        }

        if (whatToShow[3]) {
            TableColumn<Person, String> column4 = new TableColumn<>("Email");
            column4.setCellValueFactory(p -> {
                try {
                    return new SimpleStringProperty(String.valueOf(p.getValue().getEmail()));
                } catch (NullPointerException ignored) {
                }
                return null;
            });
            tableView.getColumns().add(column4);
        }

        if (whatToShow[4]) {
            TableColumn<Person, String> column5 = new TableColumn<>("Street");
            column5.setCellValueFactory(p -> {
                try {
                    return new SimpleStringProperty(String.valueOf(p.getValue().getAddress().getStreet()));
                } catch (NullPointerException ignored) {
                }
                return null;
            });
            tableView.getColumns().add(column5);
        }

        if (whatToShow[5]) {
            TableColumn<Person, String> column6 = new TableColumn<>("City");
            column6.setCellValueFactory(p -> {
                try {
                    return new SimpleStringProperty(String.valueOf(p.getValue().getAddress().getCity()));
                } catch (NullPointerException ignored) {
                }
                return null;
            });
            tableView.getColumns().add(column6);
        }

        if (whatToShow[6]) {
            TableColumn<Person, String> column7 = new TableColumn<>("Voivodeship");
            column7.setCellValueFactory(p -> {
                try {
                    return new SimpleStringProperty(String.valueOf(p.getValue().getAddress().getVoivodeship()));
                } catch (NullPointerException ignored) {
                }
                return null;
            });
            tableView.getColumns().add(column7);
        }

        if (whatToShow[7]) {
            TableColumn<Person, String> column8 = new TableColumn<>("Postal code");
            column8.setCellValueFactory(p -> {
                try {
                    return new SimpleStringProperty(String.valueOf(p.getValue().getAddress().getPostalCode()));
                } catch (NullPointerException ignored) {
                }
                return null;
            });
            tableView.getColumns().add(column8);
        }

        if (whatToShow[8]) {
            TableColumn<Person, String> column9 = new TableColumn<>("Country");
            column9.setCellValueFactory(p -> {
                try {
                    return new SimpleStringProperty(String.valueOf(p.getValue().getAddress().getCountry()));
                } catch (NullPointerException ignored) {
                }
                return null;
            });
            tableView.getColumns().add(column9);
        }

        if (whatToShow[9]) {
            TableColumn<Person, String> column10 = new TableColumn<>("Salary");
            column10.setCellValueFactory(p -> {
                try {
                    if (p.getValue() instanceof Employee) {
                        Employee e = (Employee) p.getValue();
                        return new SimpleStringProperty(String.valueOf(e.getSalary()));
                    }
                } catch (NullPointerException ignored) {
                }
                return null;
            });
            tableView.getColumns().add(column10);
        }

        if (whatToShow[10]) {
            TableColumn<Person, String> column11 = new TableColumn<>("Date of employment");
            column11.setCellValueFactory(p -> {
                try {
                    if (p.getValue() instanceof Employee) {
                        Employee e = (Employee) p.getValue();
                        return new SimpleStringProperty(String.valueOf(e.getDateOfEmployment()));
                    }
                } catch (NullPointerException ignored) {
                }
                return null;
            });
            tableView.getColumns().add(column11);
        }

        if (whatToShow[11]) {
            TableColumn<Person, String> column12 = new TableColumn<>("ID");
            column12.setCellValueFactory(p -> {
                try {
                    if (p.getValue() instanceof Employee) {
                        Employee e = (Employee) p.getValue();
                        return new SimpleStringProperty(String.valueOf(e.getId()));
                    }
                } catch (NullPointerException ignored) {
                }
                return null;
            });
            tableView.getColumns().add(column12);
        }

        if(isFoundListOfPeopleOnDisplay.isSelected() && EmployeeController.foundPeople.size() != 0)
            this.fillTableWithFoundPeople(tableView, EmployeeController.foundPeople);
        else
            this.fillTable(tableView);

        return tableView;
    }

    private void fillTable(TableView<Person> tableView) {
        for (Person p : PeopleContainer.getPeopleContainer().getAllEmployee()) {
            tableView.getItems().add(p);
        }
    }

    private void fillTableWithFoundPeople(TableView<Person> tableView, ArrayList<Person> peopleFound) {
        for (Person p : peopleFound) {
            tableView.getItems().add(p);
        }
    }

    private VBox getButtons() {
        Button addEmployee = new Button("add employee");
        addEmployee.setOnAction((event) -> {
            AddEmployeeViewManager manager = new AddEmployeeViewManager();
            manager.initPage(stage);
        });

        Button fireEmployee = new Button("fire employee");
        fireEmployee.setOnAction((e) -> {
            FireEmployeeViewManager manager = new FireEmployeeViewManager();
            manager.initPage(stage);
        });

        Button findEmployee = new Button("find employee");
        findEmployee.setOnAction((event -> {
            FindEmployeeViewManager manager = new FindEmployeeViewManager();
            manager.initPage(stage);
        }));

        Button goBackToMenu = new Button("Back to menu");
        goBackToMenu.setOnAction(event -> {
            MainViewManager manager = new MainViewManager();
            manager.initPage(stage);
        });

        isFoundListOfPeopleOnDisplay.setOnAction(event -> refreshTable());

        VBox buttons = new VBox();
        HBox findButton = new HBox();
        findButton.getChildren().addAll(findEmployee, isFoundListOfPeopleOnDisplay);
        buttons.setSpacing(10);
        buttons.snapPositionY(10);
        buttons.getChildren().addAll(addEmployee, fireEmployee, findButton, goBackToMenu);
        return buttons;
    }

    private void refreshTable(){
        createPage();
    }

    @Override
    public void initPage(Stage oldStage) {
        oldStage.hide();
        stage.show();
    }
}
