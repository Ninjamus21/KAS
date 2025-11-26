package Gui;

import Model.Konference;
import Model.Tilmelding;
import Storage.Storage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class ShowAdminWindow {
    private final Stage stage;
    private final ListView<Konference> lvwKonferencer = new ListView<>();


    public ShowAdminWindow(Stage owner){
        stage = new Stage();
        initAdminWindow(owner);
    }

    private void initAdminWindow(Stage owner) {
        stage.setTitle("Admin");
        if (owner != null) {
            stage.initOwner(owner);
        }
        stage.initModality(Modality.APPLICATION_MODAL);
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        SectionVBox adminTitleBox = new SectionVBox("Admin");
        adminTitleBox.setAlignment(Pos.CENTER);

        SectionVBox konferenceInfo = new SectionVBox("Konference Information");
        konferenceInfo.addLabeledNode("Vælg Konference", lvwKonferencer);
        Storage.getKonferences().forEach(konference -> lvwKonferencer.getItems().add(konference));


        SectionVBox oversigtButtonsBox = new SectionVBox("Oversigter");
        Button deltagerOversigtButton = new Button("Deltagere");
        Button ledsagerOversigtButton = new Button("Ledsagere");
        Button ledsagersUdflugtOversigtButton = new Button("Udflugter");
        Button deltagerHotellerButton = new Button("Hoteller og deltagere");
        Button specifikDeltagersInformationerButton = new Button("Deltager information");
        oversigtButtonsBox.getChildren().add(deltagerOversigtButton);
        oversigtButtonsBox.getChildren().add(ledsagerOversigtButton);
        oversigtButtonsBox.getChildren().add(ledsagersUdflugtOversigtButton);
        oversigtButtonsBox.getChildren().add(deltagerHotellerButton);
        oversigtButtonsBox.getChildren().add(specifikDeltagersInformationerButton);
        oversigtButtonsBox.setAlignment(Pos.CENTER);

        SectionVBox lukKnapBox = new SectionVBox(" ");

        Button closeButton = new Button("OK");
        closeButton.setOnAction(event -> stage.close());
        lukKnapBox.getChildren().add(closeButton);
        lukKnapBox.setAlignment(Pos.CENTER);


        root.getChildren().addAll(adminTitleBox, konferenceInfo,oversigtButtonsBox, lukKnapBox);

        Scene scene = new Scene(root , 400, 700);
        stage.setScene(scene);

    }

    public void showAndWait(){
        stage.showAndWait();
    }
}