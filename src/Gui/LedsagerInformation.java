package Gui;

import Model.Ledsager;
import Model.Tilmelding;
import Model.Udflugt;
import Storage.Storage;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class LedsagerInformation {
    private final Stage stage;
    private final TextField txfName = new TextField();
    private final ListView <Udflugt> lvwUdflugter = new ListView<>();
    private final Tilmelding tilmelding;

    public LedsagerInformation(Stage owner, Tilmelding tilmelding) {
        this.tilmelding = tilmelding;
        stage = new Stage();
        stage.setTitle("Ledsager Information");
        if (owner != null) {
            stage.initOwner(owner);
        }
        stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);


        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(10));

        SectionVBox ledsagerInfoBox = new SectionVBox("Ledsager");
        ledsagerInfoBox.addLabeledNode("Name", txfName);
        root.getChildren().add(ledsagerInfoBox);

        SectionVBox udflugtInfoBox = new SectionVBox("Udflugter");

        // Allow multiple selection
        lvwUdflugter.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
        lvwUdflugter.getItems().setAll(Storage.getUdflugts());
        udflugtInfoBox.addLabeledNode("Vælg Udflugter", lvwUdflugter);
        root.getChildren().add(udflugtInfoBox);

        Button btnSave = new Button("Save and Close");
        Button btnCancel = new Button("Cancel");
        btnSave.setOnAction(event -> saveAndClose());
        btnCancel.setOnAction(actionEvent -> stage.close());

        root.getChildren().addAll(btnSave, btnCancel);

        Scene scene = new Scene(root , 400, 700);
        stage.setScene(scene);
    }

    public void showAndWait() {
        stage.showAndWait();
    }

    public Ledsager saveAndClose() {
        if (tilmelding == null || tilmelding.getDeltager() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Internal Error");
            alert.setHeaderText("No tilmelding");
            alert.setContentText("Cannot add ledsager because tilmelding or deltager is missing.");
            alert.initOwner(stage);
            alert.showAndWait();
            return null;
        }

        String name = txfName.getText().trim();
        if (name.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText("Invalid Name");
            alert.setContentText("Please enter a valid name for the Ledsager.");
            alert.initOwner(stage);
            alert.showAndWait();
            return null;
        }

        // create ledsager on the deltager (keeps model consistency)
        Ledsager ledsager = tilmelding.getDeltager().createLedsager(name);

        // attach selected udflugter to this tilmelding for the ledsager
        ObservableList<Udflugt> selected = lvwUdflugter.getSelectionModel().getSelectedItems();
        for (Udflugt ud : selected) {
            tilmelding.tilmeldLedsagerTilUdflugt(tilmelding.getDeltager(), ud);
        }
        stage.close();
        return ledsager;
    }

}
