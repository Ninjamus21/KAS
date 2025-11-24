package Gui;

import Storage.Storage;
import javafx.application.Application;


public class App {
    public static void main(String[] args) {
        Application.launch(Gui.class);
        Storage.initStorage();
    }
}
