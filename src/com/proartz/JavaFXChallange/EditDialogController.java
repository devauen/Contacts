package com.proartz.JavaFXChallange;

import com.proartz.JavaFXChallange.ContactData.Contact;
import com.proartz.JavaFXChallange.ContactData.ContactData;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditDialogController {

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField phoneNumberField;
    @FXML private TextField notesField;

    public void initialize(){
        firstNameField.setText(ContactData.getInstance().getContact().getFirstName());
        lastNameField.setText(ContactData.getInstance().getContact().getLastName());
        phoneNumberField.setText(ContactData.getInstance().getContact().getPhoneNumber());
        notesField.setText(ContactData.getInstance().getContact().getNotes());
    }

    public void processResults(Contact contact) {

    }

    public TextField getFirstNameField() {
        return firstNameField;
    }

    public void setFirstNameField(TextField firstNameField) {
        this.firstNameField = firstNameField;
    }

    public TextField getLastNameField() {
        return lastNameField;
    }

    public void setLastNameField(TextField lastNameField) {
        this.lastNameField = lastNameField;
    }

    public TextField getPhoneNumberField() {
        return phoneNumberField;
    }

    public void setPhoneNumberField(TextField phoneNumberField) {
        this.phoneNumberField = phoneNumberField;
    }

    public TextField getNotesField() {
        return notesField;
    }

    public void setNotesField(TextField notesField) {
        this.notesField = notesField;
    }
}
