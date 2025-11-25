package Gui;

import Storage.Storage;
import javafx.application.Application;


public class App {
    public static void main(String[] args) {
        Storage.initStorage();
        Application.launch(Gui.class);
    }
}
