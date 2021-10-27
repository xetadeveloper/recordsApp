package backend;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The configuration file containing the database connection parameters and
 * other properties of the file
 *
 * @author Fego
 */
public class ServerLogin {

    private final Connection conn = DBConnect.getInstance().conn;
    private final Logger logger = Logger.getLogInstance();
    public boolean done = false;

    public ServerLogin(String user, char[] passwd) {
        String pass = "";
        for(char c : passwd){
            pass+=c;
        }

        if (validateLoginDB(user, pass)) {
            this.done = true;
        }
    }

    /**Validates login credentials from the user
     * @param user username
     * @param pass password
     */
    protected final boolean validateLoginDB(String user, String pass) {
        try {
            String verify = String.format("select exists (select * from login,roles where login.username = '%s' and login.password = '%s' and login.roleID = 6) as Result;",user,pass);
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery(verify);
            rs.next();
            boolean exists = rs.getInt("Result") == 1;

            return exists;
        } catch (SQLException sql) {
            logger.logErrorWithDialog("Unable to validate login credentials. DB Error", sql);
            return false;
        }
    }
}
