package dao;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;

import db.HibernateSessionFactory;
import entity.Book;
/**
 * Created by zzy on 2017/4/19.
 */
public class BookDao {
    public List<Book> getBooks(int offset, int limits){
        String hql="";
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            hql = "from Book";
            List books = session.createQuery(hql).list();
            session.getTransaction().commit();
            return books;
        }catch(Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }

    }
    public Book getBookByid(int bookid){
        Session session=HibernateSessionFactory.getSessionFactory().getCurrentSession();
        String hql="";
        try {
            hql="from Book where bookid = ?";
            session.beginTransaction();
            List list=session.createQuery(hql).setParameter(0,bookid).list();
            session.getTransaction().commit();
            if(list.size()==1)
                return (Book)list.get(0);
        }
        catch(Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return null;
    }

    public List<Book> getBooksByName(String bookname){
        Session session=HibernateSessionFactory.getSessionFactory().getCurrentSession();
        String hql="";
        try {
            hql="from Book b where b.bookname like ?";
            session.beginTransaction();
            List books=session.createQuery(hql).setParameter(0,"%"+bookname+"%").list();
            session.getTransaction().commit();
            if(books.size()>0)
                return books;
            else
                return null;
        }
        catch(Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return null;
    }


}
