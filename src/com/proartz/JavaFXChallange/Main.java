package com.proartz.JavaFXChallange;

import com.proartz.JavaFXChallange.ContactData.ContactData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        primaryStage.setTitle("Contacts");
        primaryStage.setScene(new Scene(root, 700, 275));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        ContactData.getInstance().saveContacts();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();
        ContactData.getInstance().loadContacts();
    }
}
