package entity;

/**
 * Created by zzy on 2017/4/11.
 */
public class User {
    private int userid;
    private String username;
    private String password;
    private String role;


    public User(){
        super();
    }
    public User(int userid, String username, String password, String role) {
        super();
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.role = role;
    }


    @Override
    public String toString() {
        return "User [userid=" +userid+", username=" + username
                + ", password=" + password + ", role=" +role+ "]";
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

}
