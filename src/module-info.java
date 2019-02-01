module com.proartz.JavaFXChallange {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.xml;

    opens com.proartz.JavaFXChallange;
    opens com.proartz.JavaFXChallange.ContactData;
}