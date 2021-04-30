package fx.controllers;

import models.SellerAccount;
import models.User;
import main.Database;
import main.FXMLHelper;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignInAsSellerScreenController {

    public TextField textFieldSellerLogin;
    public PasswordField passwordFieldSeller;

    public void onButtonSignInAsSellerClick(ActionEvent actionEvent) {
        String login = textFieldSellerLogin.getText();
        String password = passwordFieldSeller.getText();

        if (login.trim().length() == 0 || password.trim().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("LogIn Failed");
            alert.setHeaderText("Unable to Log In");
            alert.setContentText("Login or Password were empty\nPlease Try Again.");
            alert.showAndWait();
            textFieldSellerLogin.clear();
            passwordFieldSeller.clear();
            return;
        }

        SellerAccount user = Database.selectSellerByLoginAndPassword(login, password);
        if (user != null) {
            SellerAccount.activeUser = user;
            FXMLHelper.loadScreen("SellersScreen");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("SignIn Failed");
            alert.setHeaderText("Unable to Sign In");
            alert.setContentText("User Not Found\nPlease Try Again.");
            alert.showAndWait();
            textFieldSellerLogin.clear();
            passwordFieldSeller.clear();
        }
    }
}