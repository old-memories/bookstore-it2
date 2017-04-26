package act;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import entity.Cart;
import entity.User;
import dao.OrderDao;
import entity.Order;


/**
 * Created by zzy on 2017/4/19.

import java.util.List;

/**
 * Created by zzy on 2017/4/19.
 */
public class OrderAct extends ActionSupport {



    private Map<String,Object> dataMap = new HashMap<String, Object>();
    private int orderid;

    public void setOrderid(int orderid){
        this.orderid=orderid;
    }
    public int getOrderid(){
        return orderid;
    }
    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }



    public String allOrders(){
        try {
            User user = (User) ActionContext.getContext().getSession().get("user");
            if(user==null)
                return "input";
            OrderDao orderDao = new OrderDao();
            System.out.print(user.getUserid());
            List<Order> orders = orderDao.getUserOrders(user.getUserid());
            System.out.println("what?");
            System.out.println(orders);
            ActionContext.getContext().put("orders", orders);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    public String createOrder(){
        try{
            System.out.println("creating order...\n");
            Map session=ActionContext.getContext().getSession();
            Order order=new Order();
            order.setUser((User)session.get("user"));
            order.setPucharsed('N');
            Cart cart=(Cart) session.get("cart");
            order=cart.createOrder(order);
            OrderDao orderDao=new OrderDao();
            System.out.println("saved order.\n");
            orderDao.saveOrder(order);
            ActionContext.getContext().put("totalPrice",order.getTot_price());
            ActionContext.getContext().put("orderid", order.getOrderid());
            ActionContext.getContext().put("books", order.getBooks());
            session.remove("cart");
            System.out.println("created order.\n");
            return "created";
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "fail";
    }

    public String confirmOrder(){
        OrderDao orderDao= new OrderDao();
        orderDao.changeState(orderid,'Y');
        return "confirmed";


    }
    public String cancelOrder(){
        OrderDao orderDao=new OrderDao();
        orderDao.changeState(orderid,'C');
        return "confirmed";

    }
    public String destroyOrder() {
        OrderDao orderDao = new OrderDao();
        boolean t = orderDao.destroyOrder(orderid);
//		boolean t = true;
        System.out.println("destroy order: ");
        System.out.println(t);
        if(t) {
            dataMap.clear();
            dataMap.put("success", true);
            return "success";
        }
        else
            return "fail";
    }

}
