package com.proartz.JavaFXChallange;

import com.proartz.JavaFXChallange.ContactData.Contact;
import com.proartz.JavaFXChallange.ContactData.ContactData;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainWindowController {

    @FXML private TableView<Contact> tableView;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField phoneNumberField;
    @FXML private TextField notesField;

    public void initialize(){
        tableView.setItems(ContactData.getInstance().getContacts());
    }

    public void addPerson(ActionEvent actionEvent) {
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
}
