package backend;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import pojo.Course;
import pojo.Login;
import pojo.Programme;

/**
 * @author Fego
 */
public abstract class ClientHandler implements Callable<Boolean> {

    protected Socket sock;
    protected ObjectInputStream ois;
    protected ObjectOutputStream oos;
    public String role;
    protected String message;
    protected String query;
    protected int queryType, userType, operation;
    protected final Logger logger = Logger.getLogInstance();
    protected CopyOnWriteArrayList<ClientHandler> clientsList;
    protected ClientType type;
    protected Connection conn;
    protected Path defaultPassportUrl = Paths.get("src//images//pic1.jpg");
    public String firstName, lastName;

    //Constant Fields
    protected final static int SELECT = 0, INSERT = 1, UPDATE = 2, DELETE = 3, EXIT = 4, GETINFO = 5, GETLOGIN = 6;

    protected enum ClientType {
        STUDENT, STAFF, ADMIN
    }

    protected enum QueryType {
        SELECT, INSERT, UPDATE, DELETE
    }

    protected enum MessageType {
        QUERY, LOGIN
    }

    @Override
    public abstract Boolean call();

    public abstract boolean updateData(Statement st, String msg);

    public abstract boolean deleteData(Statement st, String msg);

    public abstract boolean insertData(Statement st, String msg);

    public abstract Object selectData(Statement st, String msg);

    /**
     * Initializes the statement field with a valid statement
     *
     * @param localConn
     * @param con the connection to use to create th statement
     * @param conn
     * @param st statement to be initialized
     * @return true if statement was created and false if error were encountered
     * @throws java.sql.SQLException
     */
    protected boolean initStatement(ThreadLocal<Connection> localConn, Statement st, Connection conn) throws SQLException {
        //st = localConn.get().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        if (st != null) {
            System.out.println("Statement has been created");
            return true;
        } else {
            System.out.println("Statement is still null");
            return false;
        }
    }

    /**
     * Splits message from client into parts that denote the message type, query
     * and tables involved.
     *
     * @param msg message sent from client
     */
    protected void splitMessage(String msg) {
        /*Format
            -----------
            queryType:query:tables,[,tables...];
         */
        String[] msgSplit = msg.split(":");

        //get the operation type which determines the return value
        queryType = Integer.parseInt(msgSplit[0]);
        //get the query
        query = msgSplit[1];
    }

    /**
     * For getting the passport picture
     *
     * @param imagePath
     * @return
     */
    protected ImageIcon getPassport(Path imagePath) {
        BufferedImage passport = null;
        try {
            passport = ImageIO.read(imagePath.toFile());
        } catch (IOException io) {
           // logger.logErrorNormal("Unable to retrieve passport picture from file system. Check that path is corretly supplied", io);
            try {
                System.out.println("Couldn't find the passport picture for this student, supplying default");
                passport = ImageIO.read(defaultPassportUrl.toFile());
                System.out.println("Default supplied");
            } catch (IOException ioo) {
               // logger.logErrorNormal("Cannot read the default passport image", ioo);
                System.out.println("Could not find the default, supplying nothing");
                passport = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
                System.out.println("nothing supplied");
            }
        }
        
        System.out.println("Returning image");
        return new ImageIcon(passport);
    }
    
     /**
     * Setting up a course object by retrieving it from the db and then
     * returning it
     *
     * @param matricNo
     * @param courseCode
     * @return
     */
    protected Course setupCourseWithQuery(int matricNo, String courseCode) {
        String queryCourse = String.format("Select * from Courses where CourseCode = '%s';", courseCode);
        try (Statement stIn = this.conn.createStatement();
                ResultSet rsIn = stIn.executeQuery(queryCourse);) { //set and get the carry over courses' details
            if (rsIn.next()) {
                Course carryCourse = setCourse(courseCode,
                        rsIn.getString("CourseName"),
                        rsIn.getString("CourseStatus"),
                        matricNo,
                        rsIn.getInt("CreditUnits"),
                        rsIn.getInt("Course_Level"),
                        rsIn.getInt("Course_ProgrammeID"),
                        null);

                return carryCourse;
            } else {
                return null;
            }
        } catch (SQLException sq) {
            logger.logErrorNormal("Unable to get carry over full course details", sq);
            return null;
        }
    }

    /**
     * Initializing A course object
     *
     * @param code
     * @param name
     * @param status
     * @param matric
     * @param creditUnit
     * @param level
     * @param progID
     * @param prog
     * @return
     */
    protected Course setCourse(String code, String name, String status, int matric, int creditUnit, int level, int progID, Programme prog) {
        Course course = new Course();
        course.setCourseCode(code);
        course.setCourseName(name);
        course.setCourseStatus(status);
        course.setCourseMatricNo(matric);
        course.setCreditUnits(creditUnit);
        course.setLevel(level);
        course.setProgrammeId(progID);
        course.setProgramme(prog);

        return course;
    }
    
    public InetAddress getAddress(){
        return sock.getInetAddress();
    }
    
    /**
     * Retrieves login details from the db
     * @param st
     * @param query
     * @return 
     */
    protected Login getLoginDetails(Statement st, String query){
        try(ResultSet rs = st.executeQuery(query)){
            if(rs.next()){
                Login login = new Login();
                login.setLoginId(rs.getInt("LoginID"));
                login.setPassword(rs.getString("Password"));
                login.setUsername(rs.getString("Username"));
                return login;
            }
            else return null;
        }catch(SQLException sq){
            logger.logErrorNormal(message, sq);
            return null;
        }
    }

}
