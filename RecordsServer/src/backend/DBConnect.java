package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Fego
 */
public class DBConnect {

    protected Connection conn;
    private String driverName, url, user, pass;
    private final static String REGISTRYNAME = "mcu student records";
    private final Logger logger = Logger.getLogInstance();
    private final ServerConfig config;

    //config variable for the retrieval of data from backing store
    //done variable as signal that backing store values exist and the database has been connected
    private boolean done = false;

    private final static DBConnect db = new DBConnect();

    /**
     * Private constructor of the class.
     */
    private DBConnect() {
        config = ServerConfig.getInstance();

        if (config.isSet()) {
            config.initSettings();
        }
    }

    public static DBConnect getInstance() {
        return db;
    }

    /**
     * Get database settings from backing store
     *
     * @return true/false if finished
     */
    private boolean initDBSettings() {
        //If configuration settings are found
        if (config.isSet()) {
            driverName = config.getDriverName();
            url = config.getDbUrl();
            user = config.getDbUserName();
            pass = config.getDbPass();

            return true;

        } else {
            return false;
        }
    }

    /**
     * Connecting to the database and opening the login frame for user
     */
    public void connect() { //at the end of this open the login frame for user to login
        try {
            initDBSettings();
            System.out.println("DriverName: " + driverName);
            Class.forName(driverName); //Registering drivers
            
            //Establishing connection
            System.out.println("url:" + url);
            System.out.println("user:" + user);
            System.out.println("pass: "  + pass);
            conn = DriverManager.getConnection(url, user, pass);
            
            setDone();
        } catch (ClassNotFoundException ex) {
            logger.logErrorWithDialog("Unable to register driver. Please contact db admin.", ex);
        } catch (SQLException sq) {
            logger.logErrorWithDialog("Error establishing database connection. Please contact db admin.", sq);
        }
    }

    /**
     * @return the done
     */
    public boolean isDone() {
        return done;
    }

    /**
     * @param done the done to set
     */
    public void setDone() {
        //If connection is still null after connect() has finished
        if (conn == null) {
            done = false;
        } else {
            done = true;
        }
    }
}
