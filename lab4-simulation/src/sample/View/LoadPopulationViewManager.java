package sample.View;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.pamiatka.History;
import sample.person.Person;
import sample.person.Population;

import java.util.ArrayList;

public class LoadPopulationViewManager {
    private final AnchorPane pane;
    private final Stage stage;

    private int key;

    public ArrayList<Person> population = null;
    private History history;

    public void createLoadPopulationWindow(History history){
        this.history = history;
        this.stage.show();
    }

    public LoadPopulationViewManager(){
        pane = new AnchorPane();
        Scene scene = new Scene(pane, 200, 200);
        stage = new Stage();
        stage.setScene(scene);
        this.addTextField();
        this.addLoadButton();
    }

    private void addTextField(){
        TextField textField = new TextField();
        textField.setLayoutX(50.);
        textField.setLayoutY(20.);
        textField.setOnKeyTyped(e -> {
            try {
                key = Integer.parseInt(textField.getText());
            }catch (NumberFormatException err){
                System.out.println(err.toString());
            }
        });

        pane.getChildren().addAll(textField);
    }

    private void addLoadButton(){
        Button button = new Button("load");
        button.setLayoutX(70.);
        button.setLayoutY(50.);
        button.setOnAction(e -> {
            loadPopulation(history);
            if(population != null){
                Population.populationList = population;
            }
            else {
                int size = history.getSizeOfMemento();
                System.out.println("size = " + size + " key = " + key);
                String valueToChoose = 0 == size  ? "You haven't done any load yet!" :
                        "[0, " + size +"]\n";
                System.out.println("There is no load with such a value!" +
                        "\nValue to choose: " + valueToChoose);
            }
            stage.hide();
        });
        pane.getChildren().add(button);
    }

    public void loadPopulation(History history) {
        population = history.getMemento(key).getPopulation();
        Population.counter = history.getMemento(key).getCounter();
        SimulationViewManager.keyCounter = history.getMemento(key).getKeyCounter();
    }
}
