package act;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;

import java.util.List;
import dao.BookDao;

/**
 * Created by zzy on 2017/4/18.
 */
public class Index extends ActionSupport{
    private static final long serialVersionUID = -7072973863541486492L;
    /*
    public String index(){
//		System.out.println("in index\n");
        UserDao userDao = new UserDao();
        List users = userDao.getUsers(1,1);
        ActionContext.getContext().put("users", users);
//		System.out.println(books);
        return SUCCESS;
    }
    */
    public String index(){
//		System.out.println("in index\n");
        BookDao bookDao = new BookDao();
        List books = bookDao.getBooks(1,1);
        ActionContext.getContext().put("books", books);
//		System.out.println(books);
        return SUCCESS;
    }
}
