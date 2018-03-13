package lesson6.labs.prob1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AddressFormWindow extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Address Form");
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(20);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Label name = new Label("Name");
        Label street = new Label("Street");
        Label city = new Label("City");
        Label state = new Label("State");
        Label zip = new Label("Zip");
        grid.add(name, 0, 0);
        grid.add(street, 1, 0);
        grid.add(city, 2, 0);
        TextField nameTextField = new TextField();
        TextField streetTextField = new TextField();
        TextField cityTextField = new TextField();
        TextField stateTextField = new TextField();
        TextField zipTextField = new TextField();
        grid.add(nameTextField, 0, 1);
        grid.add(streetTextField, 1, 1);
        grid.add(cityTextField, 2, 1);
        GridPane subGrid = new GridPane();
        subGrid.setAlignment(Pos.CENTER);
        subGrid.setHgap(20);
        subGrid.setVgap(10);
        subGrid.setPadding(new Insets(0, 50, 0, 50));
        subGrid.add(state, 0, 0);
        subGrid.add(zip, 1, 0);
        subGrid.add(stateTextField, 0, 1);
        subGrid.add(zipTextField, 1, 1);
        grid.add(subGrid, 0, 3, 3, 2);
        Button btn = new Button("Submit");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.CENTER);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 6);
        btn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				System.out.println(nameTextField.getText());
				System.out.println(streetTextField.getText());
				System.out.println(cityTextField.getText() + ", "
						+ stateTextField.getText() + " "
						+ zipTextField.getText());
			}
		});
        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
