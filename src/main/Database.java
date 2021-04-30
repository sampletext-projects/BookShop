package main;

import models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String connectionString = "jdbc:mysql://localhost:3306/bookshop?serverTimezone=Europe/Moscow&useSSL=false&allowPublicKeyRetrieval=true";
    private static final String login = "root";
    private static final String password = "root";
    private static Connection connection;
    private static boolean initiated = false;

    public static void connect() {
        try {
            connection = DriverManager.getConnection(connectionString, login, password);
            initiated = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User selectUserByLoginAndPassword(String login, String password) {
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(String.format("SELECT * FROM bookshop.accounts WHERE login='%s' AND password='%s'", login, password));
            User user = null;
            if (results.next()) {
                int db_id = results.getInt("id");
                String db_name = results.getString("name");
                String db_login = results.getString("login");
                String db_pass = results.getString("password");
                user = new User(db_id, db_name, db_login, db_pass);
            }
            statement.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static SellerAccount selectSellerByLoginAndPassword(String login, String password) {
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(String.format("SELECT * FROM bookshop.selleraccount WHERE login='%s' AND password='%s'", login, password));
            SellerAccount user = null;
            if (results.next()) {
                int db_id = results.getInt("id");
                String db_name = results.getString("name");
                String db_login = results.getString("login");
                String db_pass = results.getString("password");
                user = new SellerAccount(db_id, db_name, db_login, db_pass);
            }
            statement.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void insertUser(User user) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(
                    String.format("INSERT INTO bookshop.accounts (name, login, password) VALUE ('%s', '%s', '%s')",
                            user.getName(), user.getLogin(), user.getPassword()));
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(User user) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(
                    String.format("DELETE FROM bookshop.accounts WHERE (id = '%d')", user.getId()));
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Book> selectAllBooks() {
        List<Book> books = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM bookshop.books");
            Book book = null;
            while (resultSet.next()) {
                int db_id = resultSet.getInt("id");
                String db_title = resultSet.getString("title");
                String db_author = resultSet.getString("author");
                String db_genre = resultSet.getString("genre");
                int db_price = resultSet.getInt("price");
                int db_amount = resultSet.getInt("amount");
                book = new Book(db_id, db_title, db_author, db_genre, db_price, db_amount);
                books.add(book);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public static List<Join> selectAllOrdersExtended() {
        List<Join> orders = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT orders.id, bookid, userid, books.title, accounts.name FROM orders LEFT JOIN bookshop.accounts ON orders.userid=accounts.id LEFT JOIN books ON orders.bookid=books.id ORDER BY orders.id");
            Join order = null;
            while (resultSet.next()) {
                int db_id = resultSet.getInt("id");
                int db_bookid = resultSet.getInt("bookid");
                int db_userid = resultSet.getInt("userid");
                String db_title = resultSet.getString("title");
                String db_name = resultSet.getString("name");
                order = new Join(db_id, db_bookid, db_userid, db_title, db_name);
                orders.add(order);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public static void insertOrder(Order order) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(String.format(
                    "INSERT INTO orders (`bookid`, `userid`) VALUES ('%d', '%d')", order.getBookId(), order.getUserId()));
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM bookshop.accounts");
            User user = null;
            while (resultSet.next()) {
                int db_id = resultSet.getInt("id");
                String db_name = resultSet.getString("name");
                String db_login = resultSet.getString("login");
                String db_password = resultSet.getString("password");
                user = new User(db_id, db_name, db_login, db_password);
                users.add(user);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void close() {
        if (initiated) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
