package Gui;

import Model.Hotel;
import Model.HotelTilkøb;
import Model.Tilmelding;
import Storage.Storage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class HotelInformation {
    private boolean saved = false;
    private final Stage stage;
    private final Tilmelding tilmelding;
    private final ListView<Hotel> lvwHotels = new ListView<>();
    private final ListView<HotelTilkøb> lvwHotelTilkob = new ListView<>();
    private Hotel selectedHotel = null;
    private HotelTilkøb selectedHotelTilkob = null;

    public HotelInformation(Stage owner, Tilmelding tilmelding) {
        this.tilmelding = tilmelding;
        stage = new Stage();
        stage.setTitle("Hotel Information");
        if (owner != null) {
            stage.initOwner(owner);
        }
        stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);

        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(10));

        SectionVBox hotelInfoBox = new SectionVBox("Vælg Hotel");
        // Populate the ListView with hotels from the konference if available
        if (tilmelding != null && tilmelding.getKonference() != null) {
            try {
                lvwHotels.getItems().addAll(tilmelding.getKonference().getHotels());
            } catch (Exception ignored) {
            }
        }
        if (lvwHotels.getItems().isEmpty()) {
            try {
                lvwHotels.getItems().addAll(Storage.getHotels());
            } catch (Exception ignored) {
            }
        }
        hotelInfoBox.addNode(lvwHotels);
        root.getChildren().add(hotelInfoBox);

        SectionVBox buttonBox = new SectionVBox("");
        Button btnSave = new Button("Save and Close");
        Button btnCancel = new Button("Cancel");
        btnSave.setOnAction(event -> {
            Hotel sel = lvwHotels.getSelectionModel().getSelectedItem();
            HotelTilkøb tilkøbSel = lvwHotelTilkob.getSelectionModel().getSelectedItem();
            if (sel == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Vælg venligst et hotel!");
                alert.initOwner(stage);
                alert.showAndWait();
                return;
            }

            if (tilmelding != null) {
                tilmelding.addHotel(sel);
                tilmelding.addHotelTilkøb(tilkøbSel);
            }
            selectedHotelTilkob = tilkøbSel;
            selectedHotel = sel;
            saved = true;
            stage.close();
        });

        SectionVBox hotelTilkobBox = new SectionVBox("Hotel Tilkøb");
        hotelTilkobBox.addNode(lvwHotelTilkob);
         lvwHotels.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
             updateListview();
         });
        root.getChildren().add(hotelTilkobBox);

        btnCancel.setOnAction(event -> stage.close());
        buttonBox.addNode(btnSave);
        buttonBox.addNode(btnCancel);
        root.getChildren().add(buttonBox);


        Scene scene = new Scene(root, 300, 400);
        stage.setScene(scene);
    }

    public void showAndWait() {
        stage.showAndWait();
    }

    public Hotel getSelectedHotel() {
        return saved ? selectedHotel : null;
    }
    public HotelTilkøb getSelectedHotelTilkob() {
        return saved ? selectedHotelTilkob : null;
    }

    public void updateListview(){
        lvwHotelTilkob.getItems().clear();
        if(lvwHotels.getSelectionModel().getSelectedItem() != null){
            lvwHotelTilkob.getItems().addAll(lvwHotels.getSelectionModel().getSelectedItem().getHotelTilkobs());
        }
    }
}
