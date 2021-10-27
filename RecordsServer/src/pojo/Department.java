package pojo;

import java.io.Serializable;

/**
 *
 * @author Fego
 */
public class Department implements Serializable{
    private static final long serialVersionUID = 1L;
    
    /*Department Table Columns*/
    private int deptId, collegeId, deptHeadStaffID;
    private String deptName;
    private int deptMatricNo;
    private int staffNumber, progNumber, deptStaffID;
    
    /*Foreign Keys*/
    private Student student;
    private College deptCollege;

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
     * @return the collegeId
     */
    public int getCollegeId() {
        return collegeId;
    }

    /**
     * @param collegeId the collegeId to set
     */
    public void setDeptCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }

    /**
     * @return the deptName
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * @param deptName the deptName to set
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * @return the deptHeadStaffID
     */
    public int getDeptHeadStaffID() {
        return deptHeadStaffID;
    }

    /**
     * @param deptHeadStaffID the deptHeadStaffID to set
     */
    public void setDeptHeadStaffID(int deptHeadStaffID) {
        this.deptHeadStaffID = deptHeadStaffID;
    }

    /**
     * @return the deptMatricNo
     */
    public int getDeptMatricNo() {
        return deptMatricNo;
    }

    /**
     * @param deptMatricNo the deptMatricNo to set
     */
    public void setDeptMatricNo(int deptMatricNo) {
        this.deptMatricNo = deptMatricNo;
    }

    /**
     * @return the student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * @param student the student to set
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * @return the deptCollege
     */
    public College getDeptCollege() {
        return deptCollege;
    }

    /**
     * @param deptCollege the deptCollege to set
     */
    public void setDeptCollege(College deptCollege) {
        this.deptCollege = deptCollege;
    }
    
     public String toString(){
        return getDeptName();
    }

    /**
     * @return the staffNumber
     */
    public int getStaffNumber() {
        return staffNumber;
    }

    /**
     * @param staffNumber the staffNumber to set
     */
    public void setStaffNumber(int staffNumber) {
        this.staffNumber = staffNumber;
    }

    /**
     * @return the progNumber
     */
    public int getProgNumber() {
        return progNumber;
    }

    /**
     * @param progNumber the progNumber to set
     */
    public void setProgNumber(int progNumber) {
        this.progNumber = progNumber;
    }

    /**
     * @return the deptStaffID
     */
    public int getDeptStaffID() {
        return deptStaffID;
    }

    /**
     * @param deptStaffID the deptStaffID to set
     */
    public void setDeptStaffID(int deptStaffID) {
        this.deptStaffID = deptStaffID;
    }
}
