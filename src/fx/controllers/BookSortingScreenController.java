package fx.controllers;

import models.Book;
import models.Join;
import models.Order;
import models.User;
import main.Database;
import main.FXMLHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class BookSortingScreenController {

    public TableView<Book> tableViewBooks;

    public List<Book> allBooks;

    public List<Book> showBooks;

    public TextField textFieldSearching;

    @FXML
    private TableColumn<Join, String> columnTitle;

    @FXML
    private TableColumn<Join, String> columnAuthor;

    @FXML
    private TableColumn<Join, String> columnGenre;

    @FXML
    private TableColumn<Join, Integer> columnPrice;

    @FXML
    private TableColumn<Join, Integer> columnAmount;

    public void toView() {
        textFieldSearching.textProperty().addListener((observable, oldValue, newValue) -> {
            updateTable();
        });

        allBooks = Database.selectAllBooks();
        columnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        columnGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        updateTable();
    }

    private List<Book> filterBooks(String search) {
        List<Book> filtered = new ArrayList<>();
        for (Book b : allBooks) {
            if (b.getTitle().toLowerCase().startsWith(search) || b.getAuthor().toLowerCase().startsWith(search) || b.getGenre().toLowerCase().startsWith(search)) {
                filtered.add(b);
            }
        }
        return filtered;
    }

    private void updateTable() {
        String search = textFieldSearching.getText();

        if (search.trim().length() == 0) {
            showBooks = allBooks;
        } else {
            showBooks = filterBooks(search);
        }
        tableViewBooks.getItems().setAll(showBooks);
    }

    public void onButtonBuyClick(ActionEvent actionEvent) {
        int selectedIndex = tableViewBooks.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            return;
        }
        Order order = new Order(0, showBooks.get(selectedIndex).getId(), User.activeUser.getId());
        Database.insertOrder(order);

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ordered Succesfully");
        alert.setHeaderText("Success");
        alert.setContentText("Order was created");
        alert.showAndWait();

        FXMLHelper.loadScreen("HelloScreen");
    }

    public void onButtonDeleteClick(ActionEvent actionEvent) {
        Database.deleteUser(User.activeUser);
        FXMLHelper.loadScreen("HelloScreen");
    }
}
