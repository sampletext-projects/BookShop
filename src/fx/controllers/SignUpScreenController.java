package fx.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.Database;
import main.FXMLHelper;
import models.User;

public class SignUpScreenController {
    public TextField textFieldName;
    public TextField textFieldLogin;
    public PasswordField passwordField;

    public void onButtonSignUpClick(ActionEvent actionEvent) {
        String name = textFieldName.getText();
        String login = textFieldLogin.getText();
        String pass = passwordField.getText();

        if (name.trim().length() == 0 || login.trim().length() == 0 || pass.trim().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Creating Account Failed");
            alert.setHeaderText("Unable to Create Account");
            alert.setContentText("Name or Login or Password were empty\nPlease Try Again.");
            alert.showAndWait();
            textFieldName.clear();
            textFieldLogin.clear();
            passwordField.clear();
            return;
        }
        Database.insertUser(new User(0, name, login, pass));
        FXMLHelper.backScreen();
    }
}

