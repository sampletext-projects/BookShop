package models;

public class SellerAccount extends User {
    public static SellerAccount activeUser;
    private int id;
    private String name;
    private String login;
    private String password;

    public SellerAccount(int id, String name, String login, String password) {
        super(id, name, login, password);
    }
}
