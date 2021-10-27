package backend;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

/**
 * A class that writes to the log file
 *
 * @author Fego
 */
public class Logger {

    private final static String REGISTRYNAME = "mcu student records";
    private final static Logger INSTANCE = new Logger();

    private Logger(){}
    
    public static Logger getLogInstance() {
        return INSTANCE;
    }

    /**
     * Writes to the log file
     * @param logPath path of log file
     * @param logMessage the log message
     */
    private void write(Path logPath, String logMessage) {
        try (BufferedWriter writer = Files.newBufferedWriter(logPath, StandardOpenOption.CREATE, StandardOpenOption.APPEND)){
            writer.write("\n\n" + logMessage);
        }
        catch(IOException ie){
            JOptionPane.showMessageDialog(null,
                    "Cannot write to log file. \nLog file may be missing or registry path incorrect. Contact your system admin!",
                    "Logging Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * For error messages
     * @param message the error message
     * @param exception the error object
     */
    public void logErrorWithDialog(String message, Exception exception) {
        exception.printStackTrace();
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
        String exStackTrace = Arrays.stream(exception.getStackTrace()).map(trace -> trace.toString()).collect(Collectors.joining("\n"));
        
        String errorMessage = LocalDateTime.now() + ": " + message + "\nHeader Message: " + exception.getMessage() + "Cause: " + exception.getMessage() +"\nSTACK TRACE: " + exStackTrace;
        String url = Preferences.userRoot().node(REGISTRYNAME).get("errorLog", null);
        write(Paths.get(url),errorMessage); //write message to file
        exception.printStackTrace();
    }
    
    /**
     * For error messages
     * @param message the error message
     * @param exception the error object
     */
    public void logErrorNormal(String message, Exception exception) {
        exception.printStackTrace();
        String exStackTrace = Arrays.stream(exception.getStackTrace()).map(trace -> trace.toString()).collect(Collectors.joining("\n"));
        String errorMessage = LocalDateTime.now() + ": " + message + "\nHeader Message: " + exception.getMessage() + "Cause: " + exception.getMessage()+ 
                "\nSTACK TRACE: " + exStackTrace;
        String url = Preferences.userRoot().node(REGISTRYNAME).get("errorLog", null);
        write(Paths.get(url),errorMessage); //write message to file
        exception.printStackTrace();
    }

    /**
     * For normal log messages
     * @param message the log message
     */
    public void logMessage(String message) {
        String errorMessage = LocalDateTime.now() + ": " + message;
        write(Paths.get(Preferences.userRoot().node(REGISTRYNAME).get("messageLog", null)),errorMessage);
    }
}
