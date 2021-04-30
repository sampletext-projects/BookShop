package fx.controllers;

import models.User;
import main.Database;
import main.FXMLHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ClientsInfoScreenController {

    public TableView<User> tableViewUsers;

    @FXML
    private TableColumn<User, String> columnId;
    @FXML
    private TableColumn<User, String> columnName;
    @FXML
    private TableColumn<User, String> columnLogin;

    public void toViewClients() {
        List<User> users = Database.selectAllUsers();
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        tableViewUsers.getItems().setAll(users);
    }

    public void onBackButtonClick(ActionEvent actionEvent) {
        FXMLHelper.backScreen();
    }
}
