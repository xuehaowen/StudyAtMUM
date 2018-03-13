package lesson6.labs.prob2;

import java.util.Arrays;
import java.util.stream.Collectors;

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
import javafx.stage.Stage;

public class StringUtilityWindow extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("String Utility");
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(20);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		GridPane leftGrid = new GridPane();
		leftGrid.setAlignment(Pos.CENTER);
		leftGrid.setHgap(10);
		leftGrid.setVgap(20);
		Button btn1 = new Button("Count Letters");
		Button btn2 = new Button("Reverse Letters");
		Button btn3 = new Button("Remove Duplicates");
		btn1.setMinWidth(150);
		btn2.setMinWidth(150);
		btn3.setMinWidth(150);
		btn1.setAlignment(Pos.BASELINE_LEFT);
		btn2.setAlignment(Pos.BASELINE_LEFT);
		btn3.setAlignment(Pos.BASELINE_LEFT);
		leftGrid.add(btn1, 0, 0);
		leftGrid.add(btn2, 0, 1);
		leftGrid.add(btn3, 0, 2);
		grid.add(leftGrid, 0, 0);
		GridPane rightGrid = new GridPane();
		rightGrid.setAlignment(Pos.CENTER);
		rightGrid.setHgap(10);
		rightGrid.setVgap(0);
		Label input = new Label("Input");
		Label output = new Label("Output");
		TextField inputTextField = new TextField();
		TextField outputTextField = new TextField();
		rightGrid.add(input, 0, 0);
		rightGrid.add(output, 0, 2);
		rightGrid.add(inputTextField, 0, 1);
		rightGrid.add(outputTextField, 0, 3);
		grid.add(rightGrid, 1, 0);
		btn1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String input = inputTextField.getText();
				outputTextField.setText(String.valueOf(input.length()));

			}
		});
		btn2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				StringBuilder sb = new StringBuilder(inputTextField.getText());
				outputTextField.setText(sb.reverse().toString());
			}
		});
		btn3.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String input = inputTextField.getText();
				outputTextField.setText(Arrays.asList(input.split("")).stream().distinct().collect(Collectors.joining()));
			}
		});
		Scene scene = new Scene(grid);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
