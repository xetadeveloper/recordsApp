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

    protected static Logger getLogInstance() {
        return INSTANCE;
    }

    private void write(Path logPath, String log) {
        try (BufferedWriter writer = Files.newBufferedWriter(logPath, StandardOpenOption.CREATE, StandardOpenOption.APPEND)){
            writer.write(log);
        }
        catch(IOException ie){
            JOptionPane.showMessageDialog(null,
                    "Cannot write to log file. \nLog file may be missing or registry path incorrect. Contact your system admin!",
                    "Logging Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    protected void logErrorWithDialog(String message, Exception exception) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
        String exStackTrace = Arrays.stream(exception.getStackTrace()).map(trace -> trace.toString()).collect(Collectors.joining("\n"));
        
        String errorMessage = LocalDateTime.now() + ": " + message + "\nHeader Message: " + exception.getMessage() + "\nSTACK TRACE: " + exStackTrace;
        write(Paths.get(Preferences.userRoot().node(REGISTRYNAME).get("errorLog", null)),errorMessage); //write message to file
    }
    
    protected void logErrorNormal(String message, Exception exception) {
        String exStackTrace = Arrays.stream(exception.getStackTrace()).map(trace -> trace.toString()).collect(Collectors.joining("\n"));
        
        String errorMessage = LocalDateTime.now() + ": " + message + "\nHeader Message: " + exception.getMessage() + "\nSTACK TRACE: " + exStackTrace;
        write(Paths.get(Preferences.userRoot().node(REGISTRYNAME).get("errorLog", null)),errorMessage); //write message to file
    }

    protected void logMessage(String message) {
        String errorMessage = LocalDateTime.now() + ": " + message;
        write(Paths.get(Preferences.userRoot().node(REGISTRYNAME).get("messageLog", null)),errorMessage);
    }
}
