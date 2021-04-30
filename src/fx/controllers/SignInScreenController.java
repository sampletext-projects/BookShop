package fx.controllers;

import models.User;
import main.Database;
import main.FXMLHelper;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignInScreenController {

    public PasswordField passwordField;
    public TextField textFieldLogin;

    public void onButtonSignInClick(ActionEvent actionEvent) {
        String login = textFieldLogin.getText();
        String password = passwordField.getText();

        if (login.trim().length() == 0 || password.trim().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("LogIn Failed");
            alert.setHeaderText("Unable to Log In");
            alert.setContentText("Login or Password were empty\nPlease Try Again.");
            alert.showAndWait();
            textFieldLogin.clear();
            passwordField.clear();
            return;
        }

        User user = Database.selectUserByLoginAndPassword(login, password);
        if (user != null) {
            User.activeUser = user;
            BookSortingScreenController bookSortingScreen = FXMLHelper.loadScreenReturnController("BookSortingScreen");
            bookSortingScreen.toView();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("SignIn Failed");
            alert.setHeaderText("Unable to Sign In");
            alert.setContentText("User Not Found\nPlease Try Again.");
            alert.showAndWait();
            textFieldLogin.clear();
            passwordField.clear();
        }
    }
}
