package pojo;

import java.io.Serializable;

/**
 *
 * @author Fego
 */
public class Login implements Serializable{
    private static final long serialVersionUID = 1L;
    
    /*Login Table Columns*/
    private int loginId;
    private String username,password;
    private int loginMatricNo;

    /**
     * @return the loginId
     */
    public int getLoginId() {
        return loginId;
    }

    /**
     * @param loginId the loginId to set
     */
    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the loginMatricNo
     */
    public int getLoginMatricNo() {
        return loginMatricNo;
    }

    /**
     * @param loginMatricNo the loginMatricNo to set
     */
    public void setLoginMatricNo(int loginMatricNo) {
        this.loginMatricNo = loginMatricNo;
    }
}
