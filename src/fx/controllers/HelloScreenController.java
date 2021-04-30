package fx.controllers;

import main.FXMLHelper;
import javafx.event.ActionEvent;

public class HelloScreenController {
    public void onButtonSignInClick(ActionEvent actionEvent) {
        FXMLHelper.loadScreen("SignInScreen");
    }


    public void onButtonSignUpClick(ActionEvent actionEvent) {
        FXMLHelper.loadScreen("SignUpScreen");


    }

    public void onButtonSignInAsSellerClick(ActionEvent actionEvent) {
        FXMLHelper.loadScreen("SignInAsSellerScreen");
    }
}
