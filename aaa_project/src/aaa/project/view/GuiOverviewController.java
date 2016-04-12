/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aaa.project.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import aaa.project.Main;
import aaa.project.model.VM;
import aaa.project.model.HUB;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author sean.morris
 */
public class GuiOverviewController {

    @FXML
    private TableView<VM> vmTable;
    @FXML
    private TableView<HUB> hubTable;
    @FXML
    private TableColumn<VM, String> nameColumn;
    @FXML
    private TableColumn<VM, String> typeColumn;
    @FXML
    private TableColumn<HUB, String> nameColumn1;
    @FXML
    private TableColumn<HUB, String> typeColumn1;

    @FXML
    private Label typeLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label osLabel;
    @FXML
    private Label versionLabel;
    @FXML
    private Label sourceLabel;
    @FXML
    private Label ethernet0Label;
    @FXML
    private Label ethernet1Label;
    @FXML
    private Label ethernet2Label;
    @FXML
    private Label typeLabel1;
    @FXML
    private Label nameLabel1;
    @FXML
    private Label subnetLabel;
    @FXML
    private Label netmaskLabel;
    @FXML
    private Label infLabel;

    // Reference to the main application.
    private Main mainApp;

    /**
     * The constructor. The constructor is called before the initialize()
     * method.
     */
    public GuiOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the table with the two columns.
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        //typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
//        vmObject.setName(((TextField) childNode.get(i)).getText());
        nameColumn1.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        //typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        typeColumn1.setCellValueFactory(cellData -> cellData.getValue().typeProperty());

        // Clear details.
        showVmDetails(null);
        showHubDetails(null);

        // Listen for selection changes and show the details when changed.
        vmTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showVmDetails(newValue));
        hubTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showHubDetails(newValue));

    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        vmTable.setItems(mainApp.getVmData());
        hubTable.setItems(mainApp.getHubData());
    }

    /**
     * Fills all text fields to show details about the device. If the specified
     * device is null, all text fields are cleared.
     *
     * @param VM the vm or null
     */
    private void showVmDetails(VM vm) {
        if (vm != null) {
            // Fill the labels with info from the vm object.
            typeLabel.setText(vm.getType());
            nameLabel.setText(vm.getName());
            osLabel.setText(vm.getOs());
            versionLabel.setText(vm.getVersion());
            sourceLabel.setText(vm.getSource());
            ethernet0Label.setText(vm.getEthernet0());
            ethernet1Label.setText(vm.getEthernet1());
            ethernet2Label.setText(vm.getEthernet2());
        } else {
            // vm is null, remove all the text.
            typeLabel.setText("");
            nameLabel.setText("");
            osLabel.setText("");
            versionLabel.setText("");
            sourceLabel.setText("");
            ethernet0Label.setText("");
            ethernet1Label.setText("");
            ethernet2Label.setText("");
        }
    }
    
    private void showHubDetails(HUB vm) {
        if (vm != null) {
            // Fill the labels with info from the vm object.
            typeLabel1.setText(vm.getType());
            nameLabel1.setText(vm.getName());
            subnetLabel.setText(vm.getSubnet());
            netmaskLabel.setText(vm.getNetmask());
//            infLabel.setText(vm.getInf());

        } else {
            // vm is null, remove all the text.
            typeLabel1.setText("");
            nameLabel1.setText("");
            subnetLabel.setText("");
            netmaskLabel.setText("");
            infLabel.setText("");        }
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    //private void handleDeleteVM() {
    //int selectedIndex = vmTable.getSelectionModel().getSelectedIndex();
    //vmTable.getItems().remove(selectedIndex);

    private void handleDeleteVM() {
        int selectedIndex = vmTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            vmTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Device Selected");
            alert.setContentText("Please select a device in the table.");

            alert.showAndWait();
        }

    }
    
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteHUB() {
        int selectedIndex = hubTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            hubTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Device Selected");
            alert.setContentText("Please select a device in the table.");

            alert.showAndWait();
        }

    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new vm.
     */
    @FXML
    private void handleNewVM() {
        System.out.println("GuiOverviewController: handleNewVM");
//        System.out.println("Output of input (device): ");
//        System.out.println(device);
        System.out.println("");
        
        String device = "VM";
        VM tempVm = new VM();
//        boolean okClicked = mainApp.showVmEditDialog(tempVm, device);
        boolean okClicked = mainApp.showVmEditDialog(tempVm);
        if (okClicked) {
            mainApp.getVmData().add(tempVm);
        }
    }
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new vm.
     */
    @FXML
    private void handleNewHUB() {
        System.out.println("GuiOverviewController: handleNewHUB");
//        System.out.println("Output of input (device): ");
//        System.out.println(device);
        System.out.println("");
        
        String device = "HUB";
        HUB tempHub = new HUB();
//        boolean okClicked = mainApp.showVmEditDialog(tempVm, device);
        boolean okClicked = mainApp.showHubEditDialog(tempHub);
        if (okClicked) {
            mainApp.getHubData().add(tempHub);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected vm.
     */
    @FXML
    protected void handleEditVM() {
        System.out.println("GuiOverviewController: handleEditVM");
        System.out.println("");
        
        VM selectedVm = vmTable.getSelectionModel().getSelectedItem();
        if (selectedVm != null) {
            boolean okClicked = mainApp.showVmEditDialog(selectedVm);
            if (okClicked) {
                showVmDetails(selectedVm);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Device Selected");
            alert.setContentText("Please select a device in the table.");

            alert.showAndWait();
        }
    }
    
    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected vm.
     */
    @FXML
    protected void handleEditHUB() {
        System.out.println("GuiOverviewController: handleEditVM");
        System.out.println("");
        
        HUB selectedHub = hubTable.getSelectionModel().getSelectedItem();
        if (selectedHub != null) {
            boolean okClicked = mainApp.showHubEditDialog(selectedHub);
            if (okClicked) {
                showHubDetails(selectedHub);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Device Selected");
            alert.setContentText("Please select a device in the table.");

            alert.showAndWait();
        }
    }

}
