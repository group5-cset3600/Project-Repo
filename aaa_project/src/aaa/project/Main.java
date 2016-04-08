/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aaa.project;

import aaa.project.model.FileParser;
import aaa.project.model.VM;
import aaa.project.model.VMListWrapper;
import aaa.project.view.GuiOverviewController;
import aaa.project.view.RootLayoutController;
import aaa.project.view.VMEditDialogController;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.prefs.Preferences;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    /**
     * The data as an observable list of VMs.
     */
    private ObservableList<VM> vmData = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public Main() {
        // Add some sample data
        //vmData.add(new VM("Hans", "Muster"));
        //vmData.add(new VM("Ruth", "Mueller"));
        //vmData.add(new VM("Heinz", "Kurz"));
        //vmData.add(new VM("Cornelia", "Meier"));
        //vmData.add(new VM("Werner", "Meyer"));
        //vmData.add(new VM("Lydia", "Kunz"));
        //vmData.add(new VM("Anna", "Best"));
        //vmData.add(new VM("Stefan", "Meier"));
        //vmData.add(new VM("Martin", "Mueller"));
    }

    /**
     * Returns the data as an observable list of VMs.
     *
     * @return
     */
    public ObservableList<VM> getVmData() {
        System.out.println("Main: ObservableList<VM> getVmData()");
        System.out.println("output (vmData):");
        System.out.println(vmData);
        return vmData;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Group 5 Project");

        // Set the application icon.
        this.primaryStage.getIcons().add(new Image("file:resources/images/venom.jpg"));

        initRootLayout();

        showGuiOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Try to load last opened config file.
        File file = getCfgFilePath();
        if (file != null) {
            loadCfgDataFromFile(file);
        }
    }

    /**
     * Shows the gui overview inside the root layout.
     */
    public void showGuiOverview() {
        try {
            // Load gui overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/GuiOverview.fxml"));
            AnchorPane guiOverview = (AnchorPane) loader.load();

            // Set gui overview into the center of root layout.
            rootLayout.setCenter(guiOverview);

            // Give the controller access to the main app.
            GuiOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens a dialog to edit details for the specified vm. If the user clicks
     * OK, the changes are saved into the provided vm object and true is
     * returned.
     *
     * @param vm the vm object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
//    public boolean showVmEditDialog(VM vm, String device) {
    public boolean showVmEditDialog(VM vm) {
        System.out.println("Main: showVmEditDialog");
//        System.out.println("Output of the input (vm, device): ");
//        System.out.println(vm + ", " + device);
        System.out.println("");

        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/VMEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit VM");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the vm into the controller.
            VMEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setVm(vm);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Returns the vm file preference, i.e. the file that was last opened. The
     * preference is read from the OS specific registry. If no such preference
     * can be found, null is returned.
     *
     * @return
     */
    public File getCfgFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     *
     * @param file the file or null to remove the path
     */
    public void setCfgFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            primaryStage.setTitle("Config File - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle("Config File");
        }
    }

    /**
     * Loads device data from the specified file. The current config data will
     * be replaced.
     *
     * @param file
     */
    public void loadCfgDataFromFile(File file) {
        System.out.println("Main: loadCfgDataFromFile");
        System.out.println("input (file):");
        System.out.println(file);
        try {
            JAXBContext context = JAXBContext.newInstance(VMListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            VMListWrapper wrapper = (VMListWrapper) um.unmarshal(file);

            vmData.clear();
            vmData.addAll(wrapper.getVM());

            // Save the file path to the registry.
            setCfgFilePath(file);

        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());

            alert.showAndWait();
        }

    }

    /**
     * Saves the current config data to the specified file.
     *
     * @param file
     */
    public void saveCfgDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(VMListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our vm data.
            VMListWrapper wrapper = new VMListWrapper();
            wrapper.setVM(vmData);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, file);

            // Save the file path to the registry.
            setCfgFilePath(file);
        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    public static File fileOpenConf() { //add entire section
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Config files (*.cfg)", "*.cfg");
        FileChooser.ExtensionFilter xmlFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.getExtensionFilters().add(xmlFilter);

        // Show open file dialog
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            FileParser fileParser = new FileParser(selectedFile);
        }

        return selectedFile;
    }

    public static String readFile(File inFile) {
        StringBuilder stringBuffer = new StringBuilder();
        BufferedReader bufferedReader = null;
        String curLine; //add
        try {
            FileReader fr = new FileReader(inFile); //add
            bufferedReader = new BufferedReader(fr); //add

            while ((curLine = bufferedReader.readLine()) != null) { //add
                //stringBuffer.append(text + System.getProperty("line.separator"));
                stringBuffer.append(curLine + System.getProperty("line.separator")); //add
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Main: readFile");
        System.out.println("output (stringBuffer.toString()):");
        System.out.println(stringBuffer.toString());

        return stringBuffer.toString();

    }

    /**
     * Returns the main stage.
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
