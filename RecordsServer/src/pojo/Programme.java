package pojo;

import java.io.Serializable;

/**
 *
 * @author Fego
 */
public class Programme implements Serializable{
    private static final long serialVersionUID = 1L;
    
    /*Programme Table Columns*/
    private int programmeId, deptId;
    private String programmeName;
    private double programmeFee;
    private int maximumLevel;
    private int programmeMatricNo;
    private int studentNumber;
    private int matricType, progCourseNumber, studentYearNumber;
    
    /*Foreign Keys*/
    private Student student;
    private Department programmeDept;

    /**
     * @return the programmeId
     */
    public int getProgrammeId() {
        return programmeId;
    }

    /**
     * @param programmeId the programmeId to set
     */
    public void setProgrammeId(int programmeId) {
        this.programmeId = programmeId;
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
     * @return the programmeName
     */
    public String getProgrammeName() {
        return programmeName;
    }

    /**
     * @param programmeName the programmeName to set
     */
    public void setProgrammeName(String programmeName) {
        this.programmeName = programmeName;
    }

    /**
     * @return the programmeFee
     */
    public double getProgrammeFee() {
        return programmeFee;
    }

    /**
     * @param programmeFee the programmeFee to set
     */
    public void setProgrammeFee(double programmeFee) {
        this.programmeFee = programmeFee;
    }

    /**
     * @return the maximumLevel
     */
    public int getMaximumLevel() {
        return maximumLevel;
    }

    /**
     * @param maximumLevel the maximumLevel to set
     */
    public void setMaximumLevel(int maximumLevel) {
        this.maximumLevel = maximumLevel;
    }

    /**
     * @return the programmeMatricNo
     */
    public int getProgrammeMatricNo() {
        return programmeMatricNo;
    }

    /**
     * @param programmeMatricNo the programmeMatricNo to set
     */
    public void setProgrammeMatricNo(int programmeMatricNo) {
        this.programmeMatricNo = programmeMatricNo;
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
     * @return the programmeDept
     */
    public Department getProgrammeDept() {
        return programmeDept;
    }

    /**
     * @param programmeDept the programmeDept to set
     */
    public void setProgrammeDept(Department programmeDept) {
        this.programmeDept = programmeDept;
    }
    
     public String toString(){
        return getProgrammeName();
    }

    /**
     * @return the studentNumber
     */
    public int getStudentNumber() {
        return studentNumber;
    }

    /**
     * @param studentNumber the studentNumber to set
     */
    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    /**
     * @return the matricType
     */
    public int getMatricType() {
        return matricType;
    }

    /**
     * @param matricType the matricType to set
     */
    public void setMatricType(int matricType) {
        this.matricType = matricType;
    }

    /**
     * @return the progCourseNumber
     */
    public int getProgCourseNumber() {
        return progCourseNumber;
    }

    /**
     * @param progCourseNumber the progCourseNumber to set
     */
    public void setProgCourseNumber(int progCourseNumber) {
        this.progCourseNumber = progCourseNumber;
    }

    /**
     * @return the studentYearNumber
     */
    public int getStudentYearNumber() {
        return studentYearNumber;
    }

    /**
     * @param studentYearNumber the studentYearNumber to set
     */
    public void setStudentYearNumber(int studentYearNumber) {
        this.studentYearNumber = studentYearNumber;
    }
}
