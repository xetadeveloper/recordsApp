package pojo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Fego
 */
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;

    public Roles() {
        rolesList = new ArrayList<>();
    }
    
    /*Roles Table Columns*/
    private int roleId;
    private String roleName;
    private int roleMatricNo;
    private ArrayList<Roles> rolesList;

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
     * @return the roleName
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName the roleName to set
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * @return the collegeMatricNo
     */
    public int getCollegeMatricNo() {
        return roleMatricNo;
    }

    /**
     * @param collegeMatricNo the collegeMatricNo to set
     */
    public void setCollegeMatricNo(int collegeMatricNo) {
        this.roleMatricNo = collegeMatricNo;
    }

    /**
     * @return the rolesList
     */
    public ArrayList<Roles> getRolesList() {
        return rolesList;
    }

    /**
     * @param rolesList the rolesList to set
     */
    public void addRole(Roles role) {
        this.rolesList = rolesList;
    }
    
        @Override
    public String toString(){
        return getRoleName();
    }
}
