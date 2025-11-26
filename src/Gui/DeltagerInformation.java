package Gui;

import Model.Deltager;
import Model.DeltagerType;
import Model.Hotel;
import Model.Tilmelding;
import Storage.Storage;
import Storage.TilmeldingManager;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DeltagerInformation{
    private final Stage stage;
    private final TextField txfName = new TextField();
    private final TextField txfAge = new TextField();
    private final TextField txfAddress = new TextField();
    private final TextField txfCity = new TextField();
    private final TextField txfCountry = new TextField();
    private final TextField txfMobile = new TextField();
    private final CheckBox chkSpeaker = new CheckBox("Speaker");
    private final ComboBox<DeltagerType> cbType = new ComboBox<>();
    private final Tilmelding tilmelding;
    private boolean hotelAdded = false;
    private boolean ledsagerAdded = false;

    public DeltagerInformation(Stage owner, Tilmelding tilmelding) {
        this.tilmelding = tilmelding;
        stage = new Stage();
        initWindow(owner);
    }

    private void initWindow(Stage owner) {
        stage.setTitle("Deltager Information");
        if (owner != null) {
            stage.initOwner(owner);
        }
        stage.initModality(Modality.APPLICATION_MODAL);

        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(10));

        SectionVBox personalInfoBox = new SectionVBox("Personal");
        personalInfoBox.addLabeledNode("Name", txfName);
        personalInfoBox.addLabeledNode("Age", txfAge);

        SectionVBox contactInfoBox = new SectionVBox("Contact");
        contactInfoBox.addLabeledNode("Address", txfAddress);
        contactInfoBox.addLabeledNode("City", txfCity);
        contactInfoBox.addLabeledNode("Country", txfCountry);
        contactInfoBox.addLabeledNode("Mobile", txfMobile);

        SectionVBox additionalInfoBox = new SectionVBox("Additional Info");
        cbType.getItems().addAll(DeltagerType.values());
        if (!cbType.getItems().isEmpty()) {
            cbType.getSelectionModel().selectFirst();
        }
        additionalInfoBox.addLabeledNode("Deltager Type", cbType);
        additionalInfoBox.addNode(chkSpeaker);

        HBox buttonBox = new HBox(10);
        Button btnSave = new Button("Save");
        Button btnLedsager = new Button("Add Ledsager");
        Button btnHotel = new Button("Add Hotel");
        Button btnCancel = new Button("Cancel");
        btnSave.setOnAction(e -> saveAndClose());
        btnLedsager.setOnAction(e -> {
            if(ledsagerAdded) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Ledsager has already been added.");
                alert.initOwner(stage);
                alert.showAndWait();
                return;
            }
            LedsagerInformation ledsagerInformation = new LedsagerInformation(stage, tilmelding);
            ledsagerInformation.showAndWait();
        });
        btnHotel.setOnAction(e -> {
            if (hotelAdded){
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Hotel has already been added.");
                alert.initOwner(stage);
                alert.showAndWait();
                return;
            }
            HotelInformation hotelInformation = new HotelInformation(stage, tilmelding);
            hotelInformation.showAndWait();
            Hotel saved = hotelInformation.getSelectedHotel();
            if (saved != null) {
                // mark hotel as added
                hotelAdded = true;
                btnHotel.setDisable(true);
            }
        });
        btnCancel.setOnAction(e -> stage.close());
        buttonBox.getChildren().addAll(btnSave, btnLedsager, btnHotel, btnCancel);

        root.getChildren().addAll(personalInfoBox, contactInfoBox, additionalInfoBox, buttonBox);

        Scene scene = new Scene(root , 400, 700);
        stage.setScene(scene);
    }

    private Deltager saveAndClose() {
        if (tilmelding == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Tilmelding is null. Cannot save Deltager.");
            alert.initOwner(stage);
            alert.showAndWait();
            return null;
        }

        String name = txfName.getText().trim();
        if (name.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Name cannot be empty.");
            alert.initOwner(stage);
            alert.showAndWait();
            return null;
        }
        int age = txfAge.getText().trim().isEmpty() ? 0 : Integer.parseInt(txfAge.getText().trim());
        String address = txfAddress.getText().trim();
        String city = txfCity.getText().trim();
        String country = txfCountry.getText().trim();
        String mobile = txfMobile.getText().trim();
        boolean isSpeaker = chkSpeaker.isSelected();
        DeltagerType deltagerType = cbType.getSelectionModel().getSelectedItem();

        Deltager deltager = new Deltager(name, age, address, city, country, isSpeaker, mobile, deltagerType);
        TilmeldingManager.commit(tilmelding, deltager);

        stage.close();
        return deltager;
    }

    public void showAndWait() {
        stage.showAndWait();
    }
    public void setHotelAdded(boolean hotelAdded) {
        this.hotelAdded = hotelAdded;
    }
    public void setLedsagerAdded(boolean ledsagerAdded) {
        this.ledsagerAdded = ledsagerAdded;
    }
}
