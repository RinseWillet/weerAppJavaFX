package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        //titel in balk
        primaryStage.setTitle("Weer Bericht App");
        GridPane grid = new GridPane();
        //aligning van het grid
        grid.setAlignment(Pos.TOP_CENTER);
        //spacing tussen elementen
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setGridLinesVisible(false);

        //titel
        Text scenetitle = new Text("Welkom");
        //font-family, dikte en grootte (20)
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        grid.add(scenetitle, 0, 0, 2, 1);

        Label placeName = new Label("Voer uw plaats in:");
        grid.add(placeName, 0, 1);

        TextField placeTextField = new TextField();
        grid.add(placeTextField, 1, 1);

        //Startknop
        Button btn = new Button("Uw weer");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 3, 1);

        //Resetknop
        Button btnReset = new Button("Reset");
        HBox hbBtnReset = new HBox(10);
        hbBtnReset.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnReset.getChildren().add(btnReset);
        grid.add(hbBtnReset, 4, 1);

        Label placeLabel = new Label("Stad:");
        grid.add(placeLabel, 0, 2);

        final Text stadTarget = new Text();
        grid.add(stadTarget, 1, 2);

        Label weerLabel = new Label("Weer:");
        grid.add(weerLabel, 0, 3);

        final Text weerTarget = new Text();
        grid.add(weerTarget, 1, 3);

        Label weerTypeLabel = new Label("Beschrijving:");
        grid.add(weerTypeLabel, 0, 4);

        final Text weerTypeTarget = new Text();
        grid.add(weerTypeTarget, 1, 4);

        Label tempLabel = new Label("Temperatuur:");
        grid.add(tempLabel,0,6);

        final Text tempTarget = new Text();
        grid.add(tempTarget, 1, 6);

        Label minTemp = new Label("Min.");
        grid.add(minTemp,2,5);

        final Text minTempTarget = new Text();
        grid.add(minTempTarget, 2, 6);

        Label maxTemp = new Label("Max.");
        grid.add(maxTemp,3,5);

        final Text maxTempTarget = new Text();
        grid.add(maxTempTarget, 3, 6);

        Label pressureLabel = new Label("Luchtdruk:");
        grid.add(pressureLabel,0,7);

        final Text pressureTarget = new Text();
        grid.add(pressureTarget, 1, 7);

        Label humidLabel = new Label("Vochtigheid:");
        grid.add(humidLabel,0,8);

        final Text humidTarget = new Text();
        grid.add(humidTarget, 1, 8);

        // knop Weer indrukken
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                String place = placeTextField.getText();
                stadTarget.setFill(Color.DARKGREEN);
                stadTarget.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 10));
                stadTarget.setText(weerApp.weerAppStart(place).get(0));

                weerTarget.setText(weerApp.weerAppStart(place).get(1));
                weerTarget.setFill(Color.GREEN);

                weerTypeTarget.setText(weerApp.weerAppStart(place).get(2));
                weerTypeTarget.setFill(Color.GREEN);

                tempTarget.setText(weerApp.weerAppStart(place).get(3));
                tempTarget.setFill(Color.GREEN);

                minTempTarget.setText(weerApp.weerAppStart(place).get(4));
                minTempTarget.setFill(Color.BLUE);

                maxTempTarget.setText(weerApp.weerAppStart(place).get(5));
                maxTempTarget.setFill(Color.RED);

                pressureTarget.setText(weerApp.weerAppStart(place).get(6));
                pressureTarget.setFill(Color.GREEN);

                humidTarget.setText(weerApp.weerAppStart(place).get(7));
                humidTarget.setFill(Color.GREEN);
            }
        });

        //reset knop indrukken - velden leegmaken
        btnReset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //velden leegmaken
                placeTextField.clear();
                stadTarget.setText("");
                weerTarget.setText("");
                weerTypeTarget.setText("");
                tempTarget.setText("");
                minTempTarget.setText("");
                maxTempTarget.setText("");
                pressureTarget.setText("");
                humidTarget.setText("");
            }
        });

        primaryStage.setScene(new Scene(grid, 600, 550));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);

    }
}
