package models;

public class Order {
    private int id;
    private int bookId;
    private int userId;

    public Order(int id, int bookId, int userId) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
