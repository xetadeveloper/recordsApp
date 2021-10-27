package backend;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.prefs.Preferences;
import javax.swing.JOptionPane;

/**
 *
 * @author Fego
 */
public class ServerConfig {

    private int port;
    private String errorLog, messageLog, dbUser, dbPass, dbUrl, passportUrl, host, driverName;
    public final static String REGISTRYNAME = "mcu student records";
    

    //Instance of the class
    private final static ServerConfig INSTANCE = new ServerConfig();

    private ServerConfig() {
    }

    /**
     * Gets an instance of the class
     *
     * @return A ServerConfig instance
     */
    public static ServerConfig getInstance() {
        return INSTANCE;
    }

    //Methods
    //Initializes the configuration settings
    public boolean initSettings() {
        //Check backing store for settingsExist
        Preferences pref = Preferences.userRoot().node(REGISTRYNAME);

        if (isSet()) {
            //Logs
            errorLog = pref.get("errorLog", "");
            messageLog = pref.get("messageLog", "");

            //Database
            dbUser = pref.get("dbUser", "students");
            dbPass = pref.get("dbPass", "students");
            driverName = pref.get("dbDriver", "com.mysql.jdbc.Driver");
            dbUrl = pref.get("dbUrl", "dbc:mysql://127.0.0.1:3306/records");

            //Server
            host = pref.get("host", "");
            port = pref.getInt("port", 5508);

            //Pictures
            passportUrl = pref.get("passportUrl", "");

            return true;
        } else {
            
            return false;
        }
    }

    /**
     * If the configuration settings are set in the backing store
     *
     * @return
     */
    public boolean isSet() {
        Preferences pref = Preferences.userRoot().node(REGISTRYNAME);

        if (pref.getBoolean("set", false)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Stores data in the backing store
     *
     * @return true/false if data was stored
     */
    public boolean storeData() {
        Preferences pref = Preferences.userRoot().node(REGISTRYNAME);

        //Database
        pref.put("dbUser", dbUser);
        pref.put("dbPass", dbPass);
        pref.put("dbDriver", driverName);
        pref.put("dbUrl", dbUrl);

        //Server
        pref.put("host", host);
        pref.putInt("port", port);

        //Pictures
        pref.put("passportUrl", passportUrl);

        //Log Files

        pref.put("errorLog", errorLog);
        pref.put("messageLog", messageLog);
        
        //create the log file on the filesystem
        try {
            Files.createDirectories(Paths.get(errorLog).getParent().toAbsolutePath());
            Files.createDirectories(Paths.get(messageLog).getParent().toAbsolutePath());
            Files.createDirectories(Paths.get(passportUrl).toAbsolutePath());
            Files.createFile(Paths.get(errorLog));
            Files.createFile(Paths.get(messageLog));
        } catch (IOException io) {
            JOptionPane.showMessageDialog(null, "Error in creating log file or passport folder, file or folder may already exist. Please create the log file manually", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        //Set state
        pref.putBoolean("set", true);

        return true;
    }

    //Getters and Setters
    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * @return the errorLog
     */
    public String getErrorLog() {
        return errorLog;
    }

    /**
     * @param errorLog the errorLog to set
     */
    public void setErrorLog(String errorLog) {
        this.errorLog = errorLog;
    }

    /**
     * @return the messageLog
     */
    public String getMessageLog() {
        return messageLog;
    }

    /**
     * @param messageLog the messageLog to set
     */
    public void setMessageLog(String messageLog) {
        this.messageLog = messageLog;
    }

    /**
     * @return the dbUser
     */
    public String getDbUserName() {
        return dbUser;
    }

    /**
     * @param dbUser the dbUser to set
     */
    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    /**
     * @return the dbPass
     */
    public String getDbPass() {
        return dbPass;
    }

    /**
     * @param dbPass the dbPass to set
     */
    public void setDbPass(char[] dbPass) {
        String pass = "";
        for (char c : dbPass) {
            pass += c;
        }
        this.dbPass = pass;
    }

    /**
     * @return the dbUrl
     */
    public String getDbUrl() {
        return dbUrl;
    }

    /**
     * @param dbUrl the dbUrl to set
     */
    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    /**
     * @return the passportUrl
     */
    public String getPassportUrl() {
        return passportUrl;
    }

    /**
     * @param passportUrl the passportUrl to set
     */
    public void setPassportUrl(String passportUrl) {
        this.passportUrl = passportUrl;
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the driverName
     */
    public String getDriverName() {
        return driverName;
    }

    /**
     * @param driverName the driverName to set
     */
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

}
