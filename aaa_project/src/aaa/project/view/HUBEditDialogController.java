/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aaa.project.view;

import aaa.project.model.HUB;
import aaa.project.model.VM;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 *
 * @author sean.morris
 */
public class HUBEditDialogController {

    @FXML
    private TextField typeField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField subnetField;
    @FXML
    private TextField netmaskField;
    @FXML
    private TextField infField;
    @FXML
    private ToggleButton vmToggle; //btn1;
    @FXML
    private ToggleButton hubToggle; //btn2;

    private Stage dialogStage;
    private VM vm;
    private HUB hub;
    private boolean okClicked = false;
    String device;
    final ToggleGroup group = new ToggleGroup();
//    private boolean vmToggleSet = true;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the vm to be edited in the dialog.
     *
     * @param vm
     */
    public void setHub(HUB hub) {
        this.hub = hub;

        //if (vmToggle.isSelected()) {
//        if (vmToggleSet) {
        typeField.setText(hub.getType());
        nameField.setText(hub.getName());
        subnetField.setText(hub.getSubnet());
        netmaskField.setText(hub.getNetmask());
//        infField.setText(hub.getInf());
//        } else {
//            typeField.setText(vm.getType());
//            nameField.setText(vm.getName());
//            subnetField.setText(vm.getSubnet());
//            netmaskField.setText(vm.getNetmask());
//            infField.setText(vm.getInf());
//        }
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            //if (vmToggle.isSelected()) {
//            if (vmToggleSet) {
            hub.setType(typeField.getText());
            hub.setName(nameField.getText());
            hub.setSubnet(subnetField.getText());
            hub.setNetmask(netmaskField.getText());
//            hub.setInf(infField.getText());
//            vm.setOs(osField.getText());
//            vm.setVersion(versionField.getText());
//            vm.setSource(sourceField.getText());
//            vm.setEthernet0(ethernet0Field.getText());
//            vm.setEthernet1(ethernet1Field.getText());
//            vm.setEthernet2(ethernet2Field.getText());
//            } else {
//                vm.setType(typeField.getText());
//                vm.setName(nameField.getText());
//                vm.setSubnet(subnetField.getText());
//                vm.setNetmask(netmaskField.getText());
//                vm.setInf(infField.getText());
//            }

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (typeField.getText() == null || typeField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
//        if (osField.getText() == null || osField.getText().length() == 0) {
//            errorMessage += "No valid last name!\n"; 
//        }
//        if (versionField.getText() == null || versionField.getText().length() == 0) {
//            errorMessage += "No valid street!\n"; 
//        }
//
//        if (sourceField.getText() == null || sourceField.getText().length() == 0) {
//            errorMessage += "No valid postal code!\n"; 
        //} else {
        // try to parse the postal code into an int.
        //try {
        //    Integer.parseInt(postalCodeField.getText());
        //} catch (NumberFormatException e) {
        //    errorMessage += "No valid postal code (must be an integer)!\n"; 
        //}
//        }

//        if (ethernet0Field.getText() == null || ethernet0Field.getText().length() == 0) {
//            errorMessage += "No valid city!\n"; 
//        }
//
//        if (ethernet1Field.getText() == null || ethernet1Field.getText().length() == 0) {
//            errorMessage += "No valid birthday!\n";
//        //} //else {
//        //    if (!DateUtil.validDate(birthdayField.getText())) {
//        //        errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
//        //    }
//        }
//        
//        if (ethernet2Field.getText() == null || ethernet2Field.getText().length() == 0) {
//            errorMessage += "No valid city!\n"; 
//        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        if (event.getSource() == hubToggle) {
            //get reference to the button's stage         
            stage = (Stage) hubToggle.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("HUBEditDialog.fxml"));
            device = "HUB";
            //GuiOverviewController.handleEditVM();

            //call Main showVmEditDialog and pass vm, device
        } else if (event.getSource() == vmToggle) {
            stage = (Stage) vmToggle.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("VMEditDialog.fxml"));
            device = "VM";
        } else {
            device = null;
            root = FXMLLoader.load(getClass().getResource("VMEditDialog.fxml"));
            stage = (Stage) vmToggle.getScene().getWindow();
        }
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
