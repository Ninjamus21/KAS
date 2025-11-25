package Gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;

public class Gui extends Application {
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        initContent(pane);
        Scene scene = new Scene(pane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    TextField txfDeltagerNavn = new TextField();

    public void initContent(GridPane pane){
    SectionVBox vBoxFirst = new SectionVBox("Deltager Information");
    vBoxFirst.addLabeledNode("Deltager Navn");
    }
}
