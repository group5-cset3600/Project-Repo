/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg00_project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pkg00_project.model.FileParser;

/**
 *
 * @author sean.morris
 */
public class Main extends Application {

    final Label fileLabel = new Label();
    private Stage primaryStage;
    private Stage currentStage = primaryStage;

    /**
     * Returns the main stage.
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/GUI.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static File fileOpenConf() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Config files (*.cfg)", "*.cfg");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            FileParser fileParser = new FileParser(selectedFile);
        }

        return selectedFile;
    }

    public static String readFile(File selectedFile) {

        //StringBuilder sb = new StringBuilder(1024);
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        String curLine;
        try {
            FileReader fr = new FileReader(selectedFile);
            br = new BufferedReader(fr);

            while ((curLine = br.readLine()) != null) {
//                curLine = br.readLine();
//                sb.append(curLine).append("line.separator");
                sb.append(curLine + System.getProperty("line.separator"));
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.getMessage();
            }

        }

//        System.out.println("Main: readFile");
//        System.out.println("output (sb.toString()):");
//        System.out.println(sb.toString());
        return sb.toString();
    }


}
