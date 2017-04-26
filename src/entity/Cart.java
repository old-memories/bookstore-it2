package entity;

import dao.BookDao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by zzy on 2017/4/23.
 */
public class Cart {
    private HashMap<Integer, Integer> goods;
    private int totalPrice;

    @Override
    public String toString() {
        return "Cart [goods=" + goods + ", totalPrice=" + totalPrice + "]";
    }


    public Cart()
    {
        goods = new HashMap<Integer, Integer>();
        totalPrice = 0;
    }


    public HashMap<Integer, Integer> getGoods() {
        return goods;
    }

    public double getTotalPrice() {
        calTotalPrice();
        return totalPrice/100;
    }

    public boolean addGoodsInCart(int bookid ,int amount)
    {
        if(goods.containsKey(bookid))
        {
            int oldAmount = goods.get(bookid);
            if(oldAmount + amount > 0)	// the total amount of a certain item should > 0
                goods.put(bookid, oldAmount+amount);
            else
                return false;
        }
        else
            goods.put(bookid, amount);
        return true;
    }

    public boolean removeGoodsFromCart(int bookid)
    {
        goods.remove(bookid);
        return true;
    }

    public double calTotalPrice()
    {
        int sum = 0;
        BookDao bookDao = new BookDao();
        Set<Integer> keys = goods.keySet();
        Iterator<Integer> it = keys.iterator();
        while(it.hasNext())
        {
            Integer bookid = it.next();
            Book book = bookDao.getBookByid(bookid);
            sum += book.getPrice()* goods.get(bookid);
        }
        totalPrice = sum;
        return sum;
    }

    public Order createOrder(Order order) {
        int sum = 0;
        BookDao bookDao = new BookDao();
        Iterator iter = goods.keySet().iterator();
        while(iter.hasNext()) {
            Integer bookid = (Integer) iter.next();
            Book book = bookDao.getBookByid(bookid);
            sum += book.getPrice() * goods.get(bookid);
            BooksOrder bo = new BooksOrder(book, goods.get(bookid));
            order.getBooks().add(bo);
        }
        order.setTot_price(sum);
        return order;
    }
}
