package Gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Gui extends Application {
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        initContent(pane);
        Scene scene = new Scene(pane, 400, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void initContent(GridPane pane){

    }
}
