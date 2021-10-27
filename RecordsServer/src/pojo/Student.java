package pojo;

import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 *
 * @author Fego
 */
public class Student implements Serializable{
    /*Student Table Columns*/
    private int matricNo, age, level, programmeId, loginId, studentMaxUnits;
    private String firstname, lastname, passportUrlString;
    private double gpa, feeBalance;
    private ImageIcon picture;
    private boolean registrationAllowed, DeanApproved, HODApproved, CourseAdvisorApproved, stuRegistered;
    private static final long serialVersionUID = 1L;

    /**
     * @return the matricNo
     */
    public int getMatricNo() {
        return matricNo;
    }

    /**
     * @param matricNo the matricNo to set
     */
    public void setMatricNo(int matricNo) {
        this.matricNo = matricNo;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
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
     * @return the first name
     */
    public String getFirstName() {
        return firstname;
    }

    /**
     * @param firstname the first name to set
     */
    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the last name
     */
    public String getLastName() {
        return lastname;
    }

    /**
     * @param lastname the last name to set
     */
    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the grade point average
     */
    public double getGpa() {
        return gpa;
    }

    /**
     * @param gpa the gpa to set
     */
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    /**
     * @return the feeBalance
     */
    public double getFeeBalance() {
        return feeBalance;
    }

    /**
     * @param feeBalance the feeBalance to set
     */
    public void setFeeBalance(double feeBalance) {
        this.feeBalance = feeBalance;
    }

    /**
     * @return the picture
     */
    public ImageIcon getPassport() {
        return picture;
    }

    /**
     * @param picture the picture to set
     */
    public void setPassport(ImageIcon picture) {
        this.picture = picture;
    }

    /**
     * @return the registrationAllowed
     */
    public boolean isRegistrationAllowed() {
        return registrationAllowed;
    }

    /**
     * @param registrationAllowed the registrationAllowed to set
     */
    public void setRegistrationAllowed(boolean registrationAllowed) {
        this.registrationAllowed = registrationAllowed;
    }

    /**
     * @return the DeanApproved
     */
    public boolean isDeanApproved() {
        return DeanApproved;
    }

    /**
     * @param DeanApproved the DeanApproved to set
     */
    public void setDeanApproved(boolean DeanApproved) {
        this.DeanApproved = DeanApproved;
    }

    /**
     * @return the HODApproved
     */
    public boolean isHODApproved() {
        return HODApproved;
    }

    /**
     * @param HODApproved the HODApproved to set
     */
    public void setHODApproved(boolean HODApproved) {
        this.HODApproved = HODApproved;
    }

    /**
     * @return the CourseAdvisorApproved
     */
    public boolean isCourseAdvisorApproved() {
        return CourseAdvisorApproved;
    }

    /**
     * @param CourseAdvisorApproved the CourseAdvisorApproved to set
     */
    public void setCourseAdvisorApproved(boolean CourseAdvisorApproved) {
        this.CourseAdvisorApproved = CourseAdvisorApproved;
    }

    /**
     * @return the studentMaxUnits
     */
    public int getStudentMaxUnits() {
        return studentMaxUnits;
    }

    /**
     * @param studentMaxUnits the studentMaxUnits to set
     */
    public void setStudentMaxUnits(int studentMaxUnits) {
        this.studentMaxUnits = studentMaxUnits;
    }

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

    /**
     * @return the stuRegistered
     */
    public boolean isStuRegistered() {
        return stuRegistered;
    }

    /**
     * @param stuRegistered the stuRegistered to set
     */
    public void setStuRegistered(boolean stuRegistered) {
        this.stuRegistered = stuRegistered;
    }
}
