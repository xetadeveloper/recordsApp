package pojo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Fego
 */
public class CarryOvers implements Serializable {

    private static final long serialVersionUID = 1L;

    /*CarryOvers Table Columns*/
    private int carryMatricNo, sessionId;
    private boolean exists;
    private ArrayList<Course> carried;

    /*Foreign Keys*/
    private Student carryStudent;
    private Course carryOverCourse;

    public CarryOvers() {
        carried = new ArrayList<>(200);
    }

    /**
     * @return the carryMatricNo
     */
    public int getCarryMatricNo() {
        return carryMatricNo;
    }

    /**
     * @param carryMatricNo the carryMatricNo to set
     */
    public void setCarryMatricNo(int carryMatricNo) {
        this.carryMatricNo = carryMatricNo;
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
     * @return the carryStudent
     */
    public Student getCarryTreeStudent() {
        return carryStudent;
    }

    /**
     * @param student the carryStudent to set
     */
    public void setCarryTreeStudent(Student student) {
        this.carryStudent = student;
    }

    /**
     * @return the carryOverCourse
     */
    public Course getCarryOverCourse() {
        return carryOverCourse;
    }

    /**
     * @param carryOverCourse the carryOverCourse to set
     */
    public void setCarryOverCourse(Course carryOverCourse) {
        this.carryOverCourse = carryOverCourse;
    }

    /**
     * @return the carried
     */
    public ArrayList<Course> getCarriedCourses() {
        return carried;
    }

    /**
     * @param carried the carried to set
     */
    public void addCarried(Course carried) {
        this.carried.add(carried);
    }
}
