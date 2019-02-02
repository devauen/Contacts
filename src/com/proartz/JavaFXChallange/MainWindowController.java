package com.proartz.JavaFXChallange;

import com.proartz.JavaFXChallange.ContactData.Contact;
import com.proartz.JavaFXChallange.ContactData.ContactData;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class MainWindowController {

    @FXML private TableView<Contact> tableView;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField phoneNumberField;
    @FXML private TextField notesField;

    public void initialize(){
        // initialize data model
        tableView.setItems(ContactData.getInstance().getContacts());

        // add the context menu
        ContextMenu contextMenu = new ContextMenu();

        // add delete option to the context menu
        MenuItem deleteMenuContact = new MenuItem("Delete");

        deleteMenuContact.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Contact contact = tableView.getSelectionModel().getSelectedItem();
                deleteContact(contact);
            }
        });
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
}
