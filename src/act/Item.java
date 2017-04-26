package act;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.BookDao;
import entity.Book;
import entity.Cart;
import net.sf.json.JSONObject;

import java.util.*;

/**
 * Created by zzy on 2017/4/19.
 */
public class Item extends ActionSupport{
    private static final long serialVersionUID = -4176737865303623314L;
    private int bookid;
    private Book book;
    private int amount;
    private String searchName;
    private Map<String,Object> dataMap = new HashMap<String, Object>();

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }

    public void setSearchName(String searchName){
        this.searchName=searchName;
    }

    public String getSearchName(){
        return searchName;
    }


    public void setAmount(int amount){
        this.amount=amount;
    }

    public int getAmount(){
        return  amount;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }
    public int getBookid(){
        return bookid;
    }

    public Book getBook(){
        return book;
    }

    public void setBook(Book book){
        this.book=book;
    }

    public String showBookByid(){
        System.out.println(bookid);
        BookDao bookDao=new BookDao();
        Book book=bookDao.getBookByid(bookid);
        System.out.println(book);
        ActionContext.getContext().put("book",book);
        return "success";
    }

    public String addToCart() {
        // get cart object from session or create a cart save into session
        Map session = ActionContext.getContext().getSession();
        Cart cart;
        if(session.containsKey("cart")) {
            cart = (Cart) session.get("cart");
        } else {
            cart = new Cart();
            session.put("cart", cart);
        }

        // add item into session
        cart.addGoodsInCart(bookid, amount);
        System.out.println("bookid:");
        System.out.print(bookid);
        System.out.println(cart);
        System.out.println(cart.getTotalPrice());

        // ajax data
        dataMap.clear();
        dataMap.put("success", true);
        System.out.println("\ndataMap size: ");
        System.out.print(dataMap.size());
        return "ajax";
    }

    public String cartInfo(){
        Map session = ActionContext.getContext().getSession();
        Cart cart;
        if(session.containsKey("cart"))
            cart=(Cart) session.get("cart");
        else{
            cart=new Cart();session.put("cart",cart);
        }
        HashMap<Integer,Integer>goods=cart.getGoods();
        BookDao bookDao=new BookDao();
        Set<Integer> keys = goods.keySet();
        Iterator<Integer> it=keys.iterator();
        ArrayList<Book>books = new ArrayList<Book>();
        while(it.hasNext())
        {
            int bookid=it.next();
            Book book=bookDao.getBookByid(bookid);
            book.setAmount(goods.get(bookid));
            books.add(book);
            System.out.println(book);
        }
        dataMap.clear();
        dataMap.put("cart",books);
        return "ajax";

    }

    public String changeAmountInCart(){
        Map session=ActionContext.getContext().getSession();
        Cart cart=(Cart) session.get("cart");
        dataMap.clear();
        if(cart==null){
            dataMap.put("success",false);
            return "ajax";
        }
        int change=Integer.parseInt(((String[])ActionContext.getContext().getParameters().get("change"))[0]);
        cart.addGoodsInCart(bookid,change);
        dataMap.put("success",true);
        return "ajax";
    }

    public String removeBookInCart(){
        Map session=ActionContext.getContext().getSession();
        Cart cart=(Cart) session.get("cart");
        if(cart==null){
            dataMap.put("success",false);
            return "ajax";
        }
        cart.removeGoodsFromCart(bookid);
        dataMap.put("success",true);
        return "ajax";


    }


    public String search(){
        BookDao bookDao=new BookDao();
        List books=bookDao.getBooksByName(searchName);
        ActionContext.getContext().put("books",books);
        ActionContext.getContext().put("search_nav","search:"+searchName);
        return "search";
    }



}
