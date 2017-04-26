package entity;

/**
 * Created by zzy on 2017/4/19.
 */
public class Book {
    private int bookid;
    private int price;
    private int amount;
    private String author;
    private String bookname;
    private String image;
    public Book(){
        super();
    }
    public Book(int bookid, String bookname,int amount, String author, int price,String image) {
        super();
        this.bookid = bookid;
        this.bookname = bookname;
        this.amount=amount;
        this.author = author;
        this.price = price;
        this.image=image;
    }


    @Override
    public String toString() {
        return "Book [bookid=" +bookid+", bookname=" + bookname+", amount=" + amount
                + ", author=" + author + ", price=" +price+ ", image="+image+"]";
    }

    public int getBookid() {return bookid;}
    public void setBookid(int bookid) {this.bookid = bookid;}
    public String getBookname() {
        return bookname;
    }
    public void setBookname(String bookname) {
        this.bookname = bookname;
    }
    public int getAmount(){
        return amount;
    }
    public void setAmount(int amount){
        this.amount=amount;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getPrice() {return price;}
    public void setPrice(int price) { this.price=price;}
    public String getImage() {return image;}
    public void setImage(String image) { this.image=image;}


}
