package models;

public class Join extends Order {
    private String book;
    private String userName;

    public Join(int id, int bookId, int userId, String book, String userName) {
        super(id, bookId, userId);
        this.book = book;
        this.userName = userName;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
