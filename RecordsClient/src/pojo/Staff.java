package pojo;

import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 *
 * @author Fego
 */
public class Staff implements Serializable {

    private static final long serialVersionUID = 1L;

    /*Staff Table Columns*/
    private int staffId, roleId, loginId, deptId;
    private String firstName, lastName, passportUrlString;
    private ImageIcon passport;

    /**
     * @return the staffId
     */
    public int getStaffId() {
        return staffId;
    }

    /**
     * @param staffId the staffId to set
     */
    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    /**
     * @return the roleId
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

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
     * @return the deptId
     */
    public int getDeptId() {
        return deptId;
    }

    /**
     * @param deptId the deptId to set
     */
    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String toString() {
        return getLastName() + " " + getFirstName();
    }

    /**
     * @return the passport
     */
    public ImageIcon getPassport() {
        return passport;
    }

    /**
     * @param passport the passport to set
     */
    public void setPassport(ImageIcon passport) {
        this.passport = passport;
    }

    /**
     * @return the passportUrlString
     */
    public String getPassportUrlString() {
        return passportUrlString;
    }

    /**
     * @param passportUrlString the passportUrlString to set
     */
    public void setPassportUrlString(String passportUrlString) {
        this.passportUrlString = passportUrlString;
    }
}
