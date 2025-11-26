package Gui;

import Model.Konference;
import Model.Tilmelding;
import Storage.Storage;
import Storage.TilmeldingManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Gui extends Application {
   private Stage primaryStage;
   private final ListView <Konference> lvwKonferencer = new ListView<>();


    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        GridPane pane = new GridPane();
        initContent(pane);
        Scene scene = new Scene(pane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Konference Tilmelding");
        primaryStage.show();
    }


    public void initContent(GridPane pane) {
        SectionVBox konferenceInfo = new SectionVBox("Konference Information");
        konferenceInfo.addLabeledNode("Vælg Konference", lvwKonferencer);
        Storage.getKonferences().forEach(konference -> lvwKonferencer.getItems().add(konference));
        pane.add(konferenceInfo, 0, 0);

        SectionVBox VBoxdeltagerInfo = new SectionVBox("Deltager Information");
        pane.add(VBoxdeltagerInfo, 0, 1);
        VBoxdeltagerInfo.addButton("Tilmeld Deltager", actionEvent -> {
            Konference selected = lvwKonferencer.getSelectionModel().getSelectedItem();
            if (selected == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Vælg venligst en konference!");
                alert.initOwner(primaryStage);
                alert.showAndWait();
                return;
            }
            Tilmelding tilmeld = TilmeldingManager.createNew(selected);
            DeltagerInformation deltagerInfomation = new DeltagerInformation(this.primaryStage, tilmeld);
            deltagerInfomation.showAndWait();
        });

        SectionVBox VBoxAdmin = new SectionVBox("Admin");
        pane.add(VBoxAdmin, 1, 0);
        Label labelPassword = new Label();
        TextField textFieldPassword = new TextField();
        VBoxAdmin.addLabeledNode("Password", labelPassword);
        VBoxAdmin.addNode(textFieldPassword);
        VBoxAdmin.addButton("Login", event -> {
            ShowAdminWindow showAdminWindow = new ShowAdminWindow(primaryStage);
            showAdminWindow.showAndWait();
        });
    }
}
