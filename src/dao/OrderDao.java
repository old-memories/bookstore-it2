package dao;
import org.hibernate.query.Query;
import org.hibernate.Session;
import java.util.List;
import entity.User;
import entity.Order;



import db.HibernateSessionFactory;

/**
 * Created by zzy on 2017/4/19.
 */
public class OrderDao {
    public List<Order> getUserOrders(int userid) {
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        String hql = "";
        try {
            session.beginTransaction();
            System.out.println("id:\n");
            System.out.print(userid);
            hql = "from Order od where userid=:userid";
            Query query = session.createQuery(hql);
            query.setParameter("userid", userid);
            List<Order> orders = query.list();
            //
            session.getTransaction().commit();
            System.out.println(orders);
            return orders;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }
    public boolean changeState(int orderid,char state){
        Session session=HibernateSessionFactory.getSessionFactory().getCurrentSession();
        try{
            session.beginTransaction();
            Order order= (Order) session.load(Order.class,orderid);
            order.setPucharsed(state);
            session.getTransaction().commit();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }
    }

    public boolean destroyOrder(int orderid) {
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Order order = (Order) session.load(Order.class, orderid);
            session.delete(order);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }
    }

    public boolean saveOrder(Order order){
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.save(order);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }
    }
}
