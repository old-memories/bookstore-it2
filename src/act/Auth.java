package act;

import entity.User;
import dao.UserDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;

public class Auth extends ActionSupport
{
    public User user;
    private int userid;
    private String username;
    private String password;
    private String role;
    private static final long serialVersionUID = 1L;


    @Override
    public String execute() throws Exception {
        return "success";
    }


    public boolean isLogin() {
        Map<String, Object> session = ActionContext.getContext().getSession();
        if(session.containsKey("user"))
            return true;
        return false;
    }

    public String login() throws Exception
    {
        //test wheather struts can get username and password
        //System.out.println(user.getUsername());
        //System.out.println(user.getPassword());
        if(user!=null)
            System.out.println(user);
        else
            return "input";

        if(isLogin())		//if user has logged in, redirect to index
            return "success";
        UserDao userDao = new UserDao();
        User userDB = userDao.getUserByUsername(user.getUsername());
        //System.out.println(userDB.getUsername());
        //System.out.println(userDB.getPassword());
        //System.out.println(userDB.getRole());

        if(userDB!=null && userDB.getPassword().equals(user.getPassword())){
            System.out.println(userDB);
            user.setUserid(userDB.getUserid());
            user.setRole(userDB.getRole());
            allow_passport();
            return "success";
        }
        else
            return "input";
    }

    public String logout() {
        Map<String, Object> session = ActionContext.getContext().getSession();
        if(session.containsKey("user")){
            session.remove("user");
            return "success";
        }
        else
            return "input";
    }

    public String register() throws Exception
    {
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        UserDao userDao = new UserDao();
        //confirm the username not exists
        if(!userDao.isUsernameExists(user.getUsername()))
        {
            //All new users' role is set as user
            user.setRole("user");
            user = userDao.saveUser(user);
            System.out.println(user);
            allow_passport();
            return "success";
        } else {
            return "input";
        }
    }

    public void setUser(User user){
        this.user=user;
    }
    public User getUser(){
        return user;
    }
    public int getUserid() {return userid;}
    public void setUserid(int userid) {this.userid = userid;}
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {return role;}
    public void setRole(String role) { this.role=role;}
/*
    public String t(){
        System.out.println(test.get( ));
        return "success";
    }
    */
    private void allow_passport() {
    Map<String, Object> session = ActionContext.getContext().getSession();
    user.setPassword(null);//erase the information of password
    session.put("user", user);
    User user2 = (User) ActionContext.getContext().getSession().get("user");
    System.out.println(user);
    System.out.println(user2);
}
}