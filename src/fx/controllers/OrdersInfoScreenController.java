package fx.controllers;

import models.Join;
import main.Database;
import main.FXMLHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class OrdersInfoScreenController {
    public TableView<Join> tableViewOrders;

    @FXML
    private TableColumn<Join, String> columnBookTitle;

    @FXML
    private TableColumn<Join, String> columnClientName;

    public void toViewOrders() {
        List<Join> orders = Database.selectAllOrdersExtended();
        columnBookTitle.setCellValueFactory(new PropertyValueFactory<>("book"));
        columnClientName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        tableViewOrders.getItems().setAll(orders);
    }

    public void onBackButtonClick(ActionEvent actionEvent) {
        FXMLHelper.backScreen();
    }

}
