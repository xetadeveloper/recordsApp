package pojo;

import java.io.Serializable;

/**
 *
 * @author Fego
 */
public class College implements Serializable{
    /*College Table Columns*/
    private static final long serialVersionUID = 1L;
    private int collegeId;
    private String collegeName, fullCollegeName;
    private int collegeMatricNo, collegeDeptNumber, collegeStaffID;
    
    /*Foreign Keys*/
    private Student student;
    private Department dept;

    /**
     * @return the collegeId
     */
    public int getCollegeId() {
        return collegeId;
    }

    /**
     * @param collegeId the collegeId to set
     */
    public void setCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }

    /**
     * @return the collegeName
     */
    public String getCollegeName() {
        return collegeName;
    }

    /**
     * @param collegeName the collegeName to set
     */
    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    /**
     * @return the collegeMatricNo
     */
    public int getCollegeMatricNo() {
        return collegeMatricNo;
    }

    /**
     * @param collegeMatricNo the collegeMatricNo to set
     */
    public void setCollegeMatricNo(int collegeMatricNo) {
        this.collegeMatricNo = collegeMatricNo;
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
     * @return the dept
     */
    public Department getDept() {
        return dept;
    }

    /**
     * @param dept the dept to set
     */
    public void setDept(Department dept) {
        this.dept = dept;
    }
    
    public String toString(){
        return getCollegeName();
    }

    /**
     * @return the collegeDeptNumber
     */
    public int getCollegeDeptNumber() {
        return collegeDeptNumber;
    }

    /**
     * @param collegeDeptNumber the collegeDeptNumber to set
     */
    public void setCollegeDeptNumber(int collegeDeptNumber) {
        this.collegeDeptNumber = collegeDeptNumber;
    }

    /**
     * @return the collegeStaffID
     */
    public int getCollegeStaffID() {
        return collegeStaffID;
    }

    /**
     * @param collegeStaffID the collegeStaffID to set
     */
    public void setCollegeStaffID(int collegeStaffID) {
        this.collegeStaffID = collegeStaffID;
    }

    /**
     * @return the fullCollegeName
     */
    public String getFullCollegeName() {
        return fullCollegeName;
    }

    /**
     * @param fullCollegeName the fullCollegeName to set
     */
    public void setFullCollegeName(String fullCollegeName) {
        this.fullCollegeName = fullCollegeName;
    }
}
