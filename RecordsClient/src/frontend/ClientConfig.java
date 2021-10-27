package frontend;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.prefs.Preferences;
import javax.swing.JOptionPane;

/**
 *
 * @author Fego
 */
public class ClientConfig {

    private final static String REGISTRYNAME = "mcu student records";
    private int port;
    private String errorLog, messageLog, host;

    //Instance of the class
    private final static ClientConfig INSTANCE = new ClientConfig();

    private ClientConfig() {
    }

    /**
     * Gets an instance of the class
     *
     * @return A ServerConfig instance
     */
    public static ClientConfig getInstance() {
        return INSTANCE;
    }

    /**
     * Checks if the configuration settings exist in the backing store
     *
     * @return true/false if the settings exist or not
     */
    public boolean isSet() {
        Preferences pref = Preferences.userRoot().node(REGISTRYNAME);

        if (pref.getBoolean("set", false)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean initSettings() {
        //Check backing store for settingsExist
        Preferences pref = Preferences.userRoot().node(REGISTRYNAME);

        if (isSet()) {
            //Logs
            errorLog = pref.get("errorLog", "");
            messageLog = pref.get("messageLog", "");

            //Server
            host = pref.get("host", "");
            port = pref.getInt("port", 5508);

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

        //Server
        pref.put("host", host);
        pref.putInt("port", port);

        //Log Files
        pref.put("errorLog", errorLog);
        pref.put("messageLog", messageLog);
        
        //create the log file on the filesystem
        try {
            Files.createDirectories(Paths.get(errorLog).getParent().toAbsolutePath());
            Files.createDirectories(Paths.get(messageLog).getParent().toAbsolutePath());
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
}
