package backend;

import frontend.ClientConfig;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * The main client class
 *
 * @author Fego
 */
public class MainClient {

    //Fields
    private Socket sock;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private int port;
    private String host;
    private final static String REGISTRYNAME = "mcu student records";
    private final Logger logger = Logger.getLogInstance();
    private String role;
    private boolean ready = false;
    private boolean validLogin = false;

    /**
     * Start the connection of client to server
     */
    public MainClient() {
        try {
            if (connectServer()) {
                ready = true;
            }
        } catch (Exception ex) {
            System.exit(1);
        }
    }

    /**
     * Retrieving the server settings from the backing store
     *
     * @return true/false if settings have been configured in the backing store
     */
    protected boolean initSettings() {
        ClientConfig config = ClientConfig.getInstance();

        if (config.isSet()) {
            this.port = config.getPort();
            this.host = config.getHost();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Connect to the server application
     *
     * @return true/false if client was able to connect to server
     */
    private boolean connectServer() {
        if (initSettings()) {
            //connect to server
            System.out.println("Trying to connect to server...");
            try {
                System.out.println("Host: " + host);
                System.out.println("Port: " + port);
                sock = new Socket(host, port);
                System.out.println("Socket done");
                oos = new ObjectOutputStream(sock.getOutputStream());
                ois = new ObjectInputStream(sock.getInputStream());
            } catch (IOException io) {
                logger.logErrorWithDialog("One or more resources failed to open. Server may not be online or incorrect configuration details, please contact admin", io);
                System.exit(1);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Validate login credentials and set the role of a client
     *
     * @param user username of client
     * @param pass password of client
     * @return true/false if login credentials are valid
     */
    public boolean validateLogin(String user, String pass) {
        try {
            //query to be sent to server to verify login
            String verifyLoginQuery = String.format("select"
                    + "                    case"
                    + "                      when exists (select * from login where login.username = '%s' and login.password = '%s') \n"
                    + "                      then (select rolename from roles,login where login.username = '%s' and login.RoleID = roles.RoleID)\n"
                    + "                   end as Role;",
                    user, pass, user);

            System.out.println("\n\nQuery: " + verifyLoginQuery);
            oos.writeUTF(verifyLoginQuery); //write query to the server to verify login details
            oos.flush();

            System.out.println("Written query to server");

            //read response from the server whether login is valid
            if (ois.readBoolean()) {
                setValidLogin(true);
                System.out.println("Gotten valid login from server");

                setRole(ois.readUTF()); //get role of client

                System.out.println("Gotten role from client which is: " + role);
                return isValidLogin();
            } else {
                setValidLogin(false);
                return isValidLogin();
            }

        } catch (IOException io) {
            logger.logErrorWithDialog("One or more resources failed to open. Please contact admin", io);
        }

        return false;
    }

    /**
     * Sends a query to the server to be executed
     *
     * @param query query to be sent
     */
    public boolean sendQuery(String query) {
        try {
            oos.writeObject(query);
            oos.flush();
            return true;
        } catch (IOException io) {
            logger.logErrorNormal("Unable to send message to server. Server may be offline, Contact Server Admin", io);
            return false;
        } catch (Exception io) {
            System.out.println("Error in sending query");
            logger.logErrorNormal("Unable to send message to server. Server may be offline, Contact Server Admin", io);
            return false;
        }
    }

    public boolean sendQueryUTF(String query) {
        try {
            oos.writeUTF(query);
            oos.flush();
            return true;
        } catch (IOException io) {
            logger.logErrorWithDialog("Unable to send message to server", io);
            return false;
        } catch (Exception io) {
            logger.logErrorNormal("Unable to send message to server. Server may be offline, Contact Server Admin", io);
            return false;
        }
    }

    public Object getResponse() {
        try {
            System.out.println("Getting response...");
            Object obj = null;
            try {
                obj = ois.readObject();
                System.out.println("Got object from server");
            } catch (IOException io) {
                io.printStackTrace();
            }
            return obj;
        } catch (ClassNotFoundException cfe) {
            logger.logErrorWithDialog("Unable to parse data from server", cfe);
            return null;
        } catch (Exception io) {
            logger.logErrorWithDialog("Error in getting data from server", io);
            return null;
        }
    }

    /**
     * Closes all open resources
     */
    protected void closeResources() {
        try {
            if (sock != null) {
                sock.close();
            }

            if (oos != null) {
                oos.close();
            }

            if (ois != null) {
                ois.close();
            }
        } catch (IOException io) {
            logger.logErrorWithDialog("Resources failed to close. Please contact admin", io);
        }
    }

    /**
     * @return the ready
     */
    public boolean isReady() {
        return ready;
    }

    /**
     * @param ready the ready to set
     */
    public void setReady(boolean ready) {
        this.ready = ready;
    }

    /**
     * @return the validLogin
     */
    public boolean isValidLogin() {
        return validLogin;
    }

    /**
     * @param validLogin the validLogin to set
     */
    public void setValidLogin(boolean validLogin) {
        this.validLogin = validLogin;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }
}
