package dao;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;

import db.HibernateSessionFactory;


import entity.User;

/**
 * Created by zzy on 2017/4/12.
 */
public class UserDao {
    /*
    public boolean usersLogin(User u) {
        String hql = "";
        try {
            Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            hql = "from User where username=? and password=?";
            Query query = session.createQuery(hql);
            query.setParameter(0, u.getUsername());
            query.setParameter(1, u.getPassword());
            List list = query.list();
            session.getTransaction().commit();
            if( list.size() > 0 )
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    */
    public User getUserByUsername(String username) {
        String hql = "";
        try {
            Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            hql = "from User where username=?";
            Query query = session.createQuery(hql);
            query.setParameter(0, username);
            List<User> list = query.list();
            session.getTransaction().commit();
            if( list.size() > 0 ) {
                //System.out.println(list.get(0).getUsername());
                return list.get(0);
            }
            else {
                System.out.println("Failed to get user in db.\n");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isUsernameExists(String username) {
        User user = getUserByUsername(username);
        if(user == null)
            return false;
        return true;
    }

    public User saveUser(User u) {
        //String hql = "";
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.save(u);
            session.getTransaction().commit();
            return u;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        }
    }

    public List<User> getUsers(int offset,int limits){
        String hql="";
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            hql = "from User";
            List users = session.createQuery(hql).list();
            session.getTransaction().commit();
            return users;
        }catch(Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }

    }


}
