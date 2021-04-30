package fx.controllers;

import main.FXMLHelper;
import javafx.event.ActionEvent;

public class SellersScreenController {

    public void onButtonClientsClick(ActionEvent actionEvent) {
        ClientsInfoScreenController clientsInfoScreenController = FXMLHelper.loadScreenReturnController("ClientsInfo");
        clientsInfoScreenController.toViewClients();
    }

    public void onButtonOrdersClick(ActionEvent actionEvent) {
        OrdersInfoScreenController ordersInfoScreenController=FXMLHelper.loadScreenReturnController("OrdersInfo");
        ordersInfoScreenController.toViewOrders();
    }
}
