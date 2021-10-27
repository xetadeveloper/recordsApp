package pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Fego
 */
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    /*Course Table Columns*/
    private String courseCode, courseName, courseStatus;
    private int creditUnits, level, programmeId;
    private ArrayList<Course> coursesFullInfo;
    private int course_MatricNo;
    private Programme programme;
    private Student student;

    public Course() {
        coursesFullInfo = new ArrayList<>();
    }

    /**
     * @return the courseCode
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * @param courseCode the courseCode to set
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * @return the courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @param courseName the courseName to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * @return the courseStatus
     */
    public String getCourseStatus() {
        return courseStatus;
    }

    /**
     * @param courseStatus the courseStatus to set
     */
    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    /**
     * @return the creditUnits
     */
    public int getCreditUnits() {
        return creditUnits;
    }

    /**
     * @param creditUnits the creditUnits to set
     */
    public void setCreditUnits(int creditUnits) {
        this.creditUnits = creditUnits;
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
     * @return the coursesFullInfo
     */
    public ArrayList<Course> getCourseList() {
        return coursesFullInfo;
    }

    /**
     * @param coursesList the coursesFullInfo to add to
     * @param course course to add
     */
    public void addCourseFullInfo(Course course) {
        coursesFullInfo.add(course);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Course) {
            return ((Course) obj).courseCode.equals(this.courseCode);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.courseCode);
        return hash;
    }

    /**
     * @return the course_MatricNo
     */
    public int getCourseMatricNo() {
        return course_MatricNo;
    }

    /**
     * @param course_MatricNo the course_MatricNo to set
     */
    public void setCourseMatricNo(int course_MatricNo) {
        this.course_MatricNo = course_MatricNo;
    }

    /**
     * @return the programme
     */
    public Programme getProgramme() {
        return programme;
    }

    /**
     * @param programme the programme to set
     */
    public void setProgramme(Programme programme) {
        this.programme = programme;
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

    public String toString() {
        return getCourseCode();
    }
}
