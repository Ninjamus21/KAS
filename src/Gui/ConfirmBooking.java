package Gui;

import Model.Tilmelding;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConfirmBooking {
    private final Tilmelding tilmelding;
    private final Stage stage;
    public ConfirmBooking(Stage owner, Tilmelding tilmelding) {
        this.tilmelding = tilmelding;
        stage = new Stage();
        initBookingWindow(owner);
    }

    private void initBookingWindow(Stage owner) {
        stage.setTitle("Confirm Booking");
        if (owner != null) {
            stage.initOwner(owner);
        }
        stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);

        VBox root = new VBox(10);
        root.setPadding(new javafx.geometry.Insets(10));

        SectionVBox bookingInfoBox = new SectionVBox("Booking Confirmed");
        bookingInfoBox.addLabeledNode("Deltager Name", new Label(tilmelding.getDeltager().getName()));
        bookingInfoBox.addLabeledNode("Konference", new Label(tilmelding.getKonference().getName()));
        if (tilmelding.getDeltager().getLedsager() != null) {
            bookingInfoBox.addLabeledNode("Ledsager", new Label(tilmelding.getDeltager().getLedsager().getName()));
        } else {
            bookingInfoBox.addLabeledNode("Ledsager", new Label("Ingen Ledsager"));
        }
        if(tilmelding.getHotel() != null) {
            bookingInfoBox.addLabeledNode("Hotel", new Label(tilmelding.getHotel().getName()));
        } else {
            bookingInfoBox.addLabeledNode("Hotel", new Label("Ingen Hotel"));
        }
        bookingInfoBox.addNode(new Label("Total Pris: " + tilmelding.beregnPris() + " DKK"));
        bookingInfoBox.addButton("betal og afslut <3", event -> stage.close()); // also close the deltager info window


        root.getChildren().add(bookingInfoBox);


        Scene scene = new Scene(root, 300, 250);
        stage.setScene(scene);
    }

    public void showAndWait() {
        stage.showAndWait();
    }
}
