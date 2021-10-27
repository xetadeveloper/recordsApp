package pojo;

import java.io.Serializable;

/**
 *
 * @author Fego
 */
public class SessionDetails implements Serializable{
    private static final long serialVersionUID = 1L;
    
    /*SessionDetails Table Columns*/
    private String sessionName, semester;
    private int sessionId;
    private int maximumUnits;
    private int sessionMatricNo;

    /**
     * @return the sessionName
     */
    public String getSessionName() {
        return sessionName;
    }

    /**
     * @param sessionName the sessionName to set
     */
    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    /**
     * @return the semester
     */
    public String getSemester() {
        return semester;
    }

    /**
     * @param semester the semester to set
     */
    public void setSemester(String semester) {
        this.semester = semester;
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
     * @return the maxUnits
     */
    public int getMaximumUnits() {
        return maximumUnits;
    }

    /**
     * @param maxUnits the maxUnits to set
     */
    public void setMaximumUnits(int maxUnits) {
        this.maximumUnits = maxUnits;
    }

    /**
     * @return the sessionMatricNo
     */
    public int getSessionMatricNo() {
        return sessionMatricNo;
    }

    /**
     * @param sessionMatricNo the sessionMatricNo to set
     */
    public void setSessionMatricNo(int sessionMatricNo) {
        this.sessionMatricNo = sessionMatricNo;
    }
}
