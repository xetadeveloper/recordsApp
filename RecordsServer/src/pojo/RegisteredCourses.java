package pojo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Fego
 */
public class RegisteredCourses implements Serializable {

    private static final long serialVersionUID = 1L;

    /*RegisteredCourses Table Columns*/
    private int sessionId;
    private boolean exists;
    private int regMatricNo;
    private ArrayList<Course> registered;

    /*Foreign Keys*/
    private Student regStudent;
    private Course regCourseDetails;

    public RegisteredCourses() {
        registered = new ArrayList<>(50);
    }

    /**
     * @return the sessionId
     */
    public int getSessionId() {
        return sessionId;
    }

    /**
     * @param sessionId the sessionId to set
     */
    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * @return the exists
     */
    public boolean exists() {
        return exists;
    }

    /**
     * @param exists the exists to set
     */
    public void setExists(boolean exists) {
        this.exists = exists;
    }

    /**
     * @return the regMatricNo
     */
    public int getRegMatricNo() {
        return regMatricNo;
    }

    /**
     * @param regMatricNo the regMatricNo to set
     */
    public void setRegMatricNo(int regMatricNo) {
        System.out.println("REgmatric no in reg class: " + regMatricNo);
        this.regMatricNo = regMatricNo;
    }

    /**
     * @return the regStudent
     */
    public Student getRegStudent() {
        return regStudent;
    }

    /**
     * @param reg_Student the regStudent to set
     */
    public void setRegStudent(Student reg_Student) {
        this.regStudent = reg_Student;
    }

    /**
     * @return the regCourseDetails
     */
    public Course getRegCourse() {
        return regCourseDetails;
    }

    /**
     * @param reg_CourseFull the regCourseDetails to set
     */
    public void setRegCourse(Course reg_CourseFull) {
        this.regCourseDetails = reg_CourseFull;
    }

    /**
     * @return the registered
     */
    public ArrayList<Course> getRegisteredCourses() {
        return registered;
    }

    /**
     * @param registered the registered to set
     */
    public void addRegisteredCourse(Course registered) {
        this.registered.add(registered);
    }
}
