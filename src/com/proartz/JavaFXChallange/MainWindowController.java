package com.proartz.JavaFXChallange;

import com.proartz.JavaFXChallange.ContactData.Contact;
import com.proartz.JavaFXChallange.ContactData.ContactData;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Optional;

public class MainWindowController {

    @FXML private TableView<Contact> tableView;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField phoneNumberField;
    @FXML private TextField notesField;
    @FXML private GridPane mainGridPane;

    public void initialize(){
        // initialize data model
        tableView.setItems(ContactData.getInstance().getContacts());
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // add the context menu
        ContextMenu contextMenu = new ContextMenu();

        // add delete option to the context menu
        MenuItem deleteMenuContact = new MenuItem("Delete");

        deleteMenuContact.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                deleteContact(tableView.getSelectionModel().getSelectedItem());
                //                tableView.getItems().removeAll(
//                        tableView.getSelectionModel().getSelectedItems());
            }
        });
        deleteMenuContact.disableProperty().bind(
                Bindings.isEmpty(tableView.getSelectionModel().getSelectedItems()));

        // add edit option to the context menu
        MenuItem editMenuContact = new MenuItem("Edit");

        editMenuContact.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showEditContactDialog(tableView.getSelectionModel().getSelectedItem());
            }
        });

        contextMenu.getItems().addAll(deleteMenuContact, editMenuContact);
        tableView.setContextMenu(contextMenu);
    }

    public void addContact(ActionEvent actionEvent) {
        ObservableList<Contact> data = tableView.getItems();
        data.add(new Contact(
                firstNameField.getText(),
                lastNameField.getText(),
                phoneNumberField.getText(),
                notesField.getText()));

        firstNameField.setText("");
        lastNameField.setText("");
        phoneNumberField.setText("");
        notesField.setText("");
    }

    private void deleteContact(Contact contact) {
        // create an alert to confirm contact deletion
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Contact");
        alert.setHeaderText("Delete contact: " + contact.getFirstName() + " " + contact.getLastName());
        alert.setContentText("Are You sure You want to delete the contact?");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && (result.get() == ButtonType.OK)) {
            ContactData.getInstance().deleteContact(contact);
        }
    }

    private void showEditContactDialog(Contact contact){
        ContactData.getInstance().setContact(contact);

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainGridPane.getScene().getWindow());
        dialog.setTitle("Edit contact");
        dialog.setHeaderText("Use this dialog to edit contact details");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("editDialog.fxml"));

        try {
            dialog.getDialogPane().setContent(loader.load());
        } catch(IOException e){
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();

        if(result.isPresent() && (result.get() == ButtonType.OK)) {
            EditDialogController controller = loader.getController();

            //update contact details
            System.out.println(controller.getFirstNameField().getText());
            contact.setFirstName(controller.getFirstNameField().getText());
            contact.setLastName(controller.getLastNameField().getText());
            contact.setPhoneNumber(controller.getPhoneNumberField().getText());
            contact.setNotes(controller.getNotesField().getText());
        }
    }
}
