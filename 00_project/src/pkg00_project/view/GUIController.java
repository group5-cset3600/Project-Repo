/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg00_project.view;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import pkg00_project.Main;

/**
 *
 * @author sean.morris
 */
public class GUIController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private TextArea fileTextArea;
    
    @FXML
    private Pane canvas;

    // Reference to the main application
    private Main mainApp;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @FXML
    public void openItem() {
        File selectedFile = mainApp.fileOpenConf();
        if (selectedFile != null) {
            fileTextArea.setText(mainApp.readFile(selectedFile));
        }
    }

    @FXML
    public void closeItem(ActionEvent Event) {
        fileTextArea.setText("");
    }

    @FXML
    public void exitItem(ActionEvent Event) {
        System.exit(0);
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }

}