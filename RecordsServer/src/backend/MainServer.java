package backend;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import javax.swing.JOptionPane;
import pojo.College;
import pojo.Course;
import pojo.Department;
import pojo.Programme;
import pojo.Roles;
import pojo.Staff;
import pojo.Student;

/**
 *
 * @author Fego
 */
public class MainServer {

    private Connection conn;
    private Statement st;
    private ServerSocket serverSock;
    private Socket sock;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private int port;
    public final static String REGISTRYNAME = "mcu student records";
    private final Logger logger = Logger.getLogInstance();
    private final CopyOnWriteArrayList<ClientHandler> clientsList = new CopyOnWriteArrayList<>();
    protected ExecutorService service = Executors.newSingleThreadExecutor(getThreadFactory());
    private ServerConfig config = ServerConfig.getInstance();
    private Future<?> future;

    public MainServer() {
        this.port = config.getPort();

        try {
            conn = DBConnect.getInstance().conn;
            st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException ex) {
            logger.logErrorNormal("Error in creating statement in MainServer", ex);
        }
    }

    /**
     * @return the future
     */
    public Future<?> getFuture() {
        return future;
    }

    /**
     * @param future the future to set
     */
    public void setFuture(Future<?> future) {
        this.future = future;
    }

    /**
     * @return the st
     */
    public Statement getStatement() {
        return st;
    }

    class Handler implements Runnable {

        @Override
        public void run() {
            try {
                logger.logMessage("Application Started...");
                System.out.println("Listener Started...");
                listenAndApply();
            } catch (IOException io) {
                io.printStackTrace();
                logger.logErrorWithDialog("Couldn't listen in on port " + port, io);
            } catch (SQLException sq) {
                sq.printStackTrace();
                logger.logErrorWithDialog("Could not create statement, Contact admin.", sq);
            }
        }
    }

    public void startListener() {
        setFuture(service.submit(new Handler()));
    }

    /**
     * Listens in for connections and spawns new threads as needed
     *
     * @throws java.io.IOException if server socket could not be bound to port
     * @throws java.sql.SQLException if statement could not be created using the
     * connection
     */
    protected void listenAndApply() throws IOException, SQLException {
        try {
            ExecutorService exec = Executors.newCachedThreadPool();
            conn = DBConnect.getInstance().conn;
            serverSock = new ServerSocket(port);
            System.out.println("Port: " + port);

            //Main server activity
            while (true) {
                try {
                    System.out.println("The listener don start");
                    sock = serverSock.accept(); //blocks till incoming connection
                    oos = new ObjectOutputStream(sock.getOutputStream());
                    ois = new ObjectInputStream(sock.getInputStream());

                    //assign a thread to validate the login and assign a proper thread to deal with client based on their role
                    ValidateAndAssign va = new ValidateAndAssign(sock, oos, ois, getClientsList(), conn, getThreadFactory());
                    exec.submit(va);

                } catch (IOException io) {
                    logger.logErrorWithDialog("Couldn't establish client connection after listening", io);
                }
            }
        } catch (BindException bind) {
            JOptionPane.showMessageDialog(null, "Another instance of the server is open. Please close that one first", "Error in opening server", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    /**
     * Closes all open resources
     */
    public void closeResources() {
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

            if (conn != null && getStatement() != null) {
                conn.close();
            }
        } catch (IOException io) {
            logger.logErrorWithDialog("Resources failed to close. Please contact admin", io);
        } catch (SQLException sq) {
            logger.logErrorWithDialog("Connection or Statement refused to close. Please contact admin", sq);
        }
    }

    public List<Runnable> shutdownThread() {
        return service.shutdownNow();
    }

    /**
     * @return the clientsList
     */
    public CopyOnWriteArrayList<ClientHandler> getClientsList() {
        return clientsList;
    }

    /**
     * Get a thread factory that creates daemon threads
     *
     * @return
     */
    public ThreadFactory getThreadFactory() {
        return new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread th = Executors.defaultThreadFactory().newThread(r);
                th.setDaemon(true);
                return th;
            }
        };
    }

    /**
     * Retrieves colleges, depts and programmes from db
     *
     * @param st statement to be used
     * @param query query to be executed
     * @return
     */
    public ArrayList<ArrayList<?>> getSchoolInfo() {
        //get data of all colleges and the rest from db
        String query = "select * from colleges\n"
                + "left join departments on departments.Dept_CollegeID = colleges.CollegeID\n"
                + "left join programmes on programmes.Programme_DeptID = departments.DeptID\n"
                + "left join courses on courses.course_programmeID = programmes.ProgrammeID;";

        System.out.println("Creating ArrayList to store all data");
        ArrayList<ArrayList<?>> list = new ArrayList<>();

        ArrayList<College> collegesList = new ArrayList<>();
        ArrayList<Department> deptsList = new ArrayList<>();
        ArrayList<Programme> programmesList = new ArrayList<>();
        ArrayList<Course> courseList = new ArrayList<>();

        boolean contains = false;

        try (ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                //Colleges
                College col = new College();
                int colID = rs.getInt("CollegeID");
                for (College college : collegesList) {
                    if (colID == 0 || college.getCollegeId() == colID) {
                        contains = true;
                    }
                }

                if (!contains) {
                    col.setCollegeId(rs.getInt("CollegeID"));
                    col.setCollegeName(rs.getString("CollegeName"));
                    col.setCollegeDeptNumber(rs.getInt("College_DeptNumber"));
                    collegesList.add(col); //Add college
                }

                contains = false;

                //Departments
                Department dept = new Department();
                int deptID = rs.getInt("DeptID");

                for (Department deptLocal : deptsList) {
                    if (deptID == 0 || deptLocal.getDeptId() == deptID) {
                        contains = true;
                    }
                }

                if (!contains) {
                    dept.setDeptId(rs.getInt("DeptID"));
                    dept.setDeptHeadStaffID(rs.getInt("DeptHead_StaffID"));
                    dept.setDeptName(rs.getString("DeptName"));
                    dept.setDeptCollegeId(rs.getInt("Dept_CollegeID"));
                    dept.setProgNumber(rs.getInt("Dept_ProgrammeNumber"));
                    dept.setDeptCollege(col);
                    deptsList.add(dept);
                }

                contains = false;

                //Programmes
                Programme prog = new Programme();
                int progID = rs.getInt("ProgrammeID");

                for (Programme progLocal : programmesList) {
                    if (progID == 0 || progLocal.getProgrammeId() == progID) {
                        contains = true;
                    }
                }

                if (!contains) {
                    prog.setProgrammeId(rs.getInt("ProgrammeID"));
                    prog.setProgrammeName(rs.getString("ProgrammeName"));
                    prog.setMaximumLevel(rs.getInt("MaximumLevel"));
                    prog.setProgrammeFee(rs.getDouble("ProgrammeFee"));
                    prog.setDeptId(rs.getInt("Programme_DeptID"));
                    prog.setMatricType(rs.getInt("MatricType"));
                    prog.setStudentNumber(rs.getInt("StudentsNumber"));
                    prog.setProgCourseNumber(rs.getInt("Programme_CourseNumber"));
                    prog.setStudentYearNumber(rs.getInt("ProgYearStudentCount"));
                    prog.setProgrammeDept(dept);
                    programmesList.add(prog);
                }

                contains = false;

                //Courses
                Course course = new Course();
                String courseCode = rs.getString("CourseCode");

                for (Course courseLocal : courseList) {
                    if (courseCode == null || courseLocal.getCourseCode().equalsIgnoreCase(courseCode)) {
                        contains = true;
                    }
                }

                if (!contains) {
                    course.setCourseCode(courseCode);
                    course.setCourseName(rs.getString("CourseName"));
                    course.setCourseStatus(rs.getString("CourseStatus"));
                    course.setCreditUnits(rs.getInt("CreditUnits"));
                    course.setLevel(rs.getInt("Course_Level"));
                    course.setProgrammeId(rs.getInt("Course_ProgrammeID"));
                    courseList.add(course);
                }

                contains = false;
            }

            list.add(collegesList);
            list.add(deptsList);
            list.add(programmesList);
            list.add(courseList);

            return list;
        } catch (SQLException sq) {
            sq.printStackTrace();
            logger.logErrorWithDialog("Unable to retreive data from database", sq);
            return list;
        } catch (Exception sq) {
            sq.printStackTrace();
            logger.logErrorWithDialog("Unable to retreive data from database", sq);
            return list;
        }
    }

    /**
     * Gets the login id for any query
     *
     * @param query
     * @return
     */
    public int getLoginID(String query) {
        int result = 0;

        try (ResultSet rs = st.executeQuery(query)) {
            result = rs.next() ? rs.getInt("LoginID") : 0;
        } catch (SQLException ex) {
            logger.logErrorWithDialog("Unable to get data from db", ex);
        } catch (Exception ex) {
            logger.logErrorWithDialog("Unable to get data from db", ex);
        }
        return result;
    }

    /**
     * Gets a single result from
     *
     * @param query
     * @param columnName
     * @return
     */
    public Object getQuerySingleResult(String query, String columnName) {
        Object result = null;

        try (ResultSet rs = st.executeQuery(query)) {
            result = rs.next() ? rs.getObject(columnName) : 0;
        } catch (SQLException ex) {
            logger.logErrorWithDialog("Unable to get data from db", ex);
        } catch (Exception ex) {
            logger.logErrorWithDialog("Unable to get data from db", ex);
        }
        return result;
    }

    /**
     * Retrieves all the roles from the db
     *
     * @param student
     * @return
     */
    public ArrayList<Roles> getRoles(boolean student) {
        String query;
        if (student) {
            query = "Select * from Roles where RoleName in ('Student') order by RoleID;";
        } else {
            query = "Select * from Roles where RoleName not in ('Student') order by RoleID;";
        }

        try (ResultSet rs = st.executeQuery(query)) {
            ArrayList<Roles> roleList = new ArrayList<>();

            while (rs.next()) {
                Roles role = new Roles();
                role.setRoleId(rs.getInt("RoleID"));
                role.setRoleName(rs.getString("RoleName"));
                roleList.add(role);
            }

            System.out.println("Returning roles");
            return roleList;
        } catch (SQLException ex) {
            System.out.println("Exception found");
            logger.logErrorWithDialog("Unable to get data from db", ex);
            return new ArrayList<>();
        } catch (Exception ex) {
            System.out.println("Exception found");
            logger.logErrorWithDialog("Unable to get data from db", ex);
            return new ArrayList<>();
        }
    }

    /**
     * Retrieves all staffs from the db
     *
     * @param query
     * @return
     */
    public ArrayList<Staff> getStaffs() {
        String query = "Select * from Staffs;";
        try (ResultSet rs = st.executeQuery(query)) {
            ArrayList<Staff> staffList = new ArrayList<>();

            while (rs.next()) {
                Staff staff = new Staff();
                staff.setFirstName(rs.getString("FirstName"));
                staff.setLastName(rs.getString("LastName"));
                staff.setLoginId(rs.getInt("Staff_LoginID"));
                staff.setStaffId(rs.getInt("StaffID"));
                staff.setDeptId(rs.getInt("Staff_DeptID"));
                staff.setRoleId(rs.getInt("Staff_RoleID"));
                staffList.add(staff);
            }

            return staffList;
        } catch (SQLException ex) {
            System.out.println("Exception found");
            logger.logErrorWithDialog("Unable to get data from db", ex);
            return new ArrayList<>();
        } catch (Exception ex) {
            System.out.println("Exception found");
            logger.logErrorWithDialog("Unable to get data from db", ex);
            return new ArrayList<>();
        }
    }

    /**
     * Retrieves all colleges from the db
     *
     * @param query
     * @return
     */
    public ArrayList<College> getColleges() {
        String query = "Select * from Colleges;";

        try (ResultSet rs = st.executeQuery(query)) {
            ArrayList<College> colList = new ArrayList<>();

            while (rs.next()) {
                College col = new College();
                col.setCollegeId(rs.getInt("CollegeID"));
                col.setCollegeName(rs.getString("CollegeName"));
                col.setCollegeDeptNumber(rs.getInt("College_DeptNumber"));
                colList.add(col);
            }

            return colList;
        } catch (SQLException ex) {
            System.out.println("Exception found");
            logger.logErrorWithDialog("Unable to get data from db", ex);
            return new ArrayList<>();
        } catch (Exception ex) {
            System.out.println("Exception found");
            logger.logErrorWithDialog("Unable to get data from db", ex);
            return new ArrayList<>();
        }
    }

    /**
     * Retrieves all depts from the db
     *
     * @param query
     * @return
     */
    public ArrayList<Department> getDepartments() {
        String query = "Select * from Departments;";

        try (ResultSet rs = st.executeQuery(query)) {
            ArrayList<Department> deptList = new ArrayList<>();

            while (rs.next()) {
                Department dept = new Department();
                dept.setDeptId(rs.getInt("DeptID"));
                dept.setDeptHeadStaffID(rs.getInt("DeptHead_StaffID"));
                dept.setDeptName(rs.getString("DeptName"));
                dept.setDeptCollegeId(rs.getInt("Dept_CollegeID"));
                dept.setProgNumber(rs.getInt("Dept_ProgrammeNumber"));
                deptList.add(dept);
            }

            return deptList;
        } catch (SQLException ex) {
            System.out.println("Exception found");
            logger.logErrorWithDialog("Unable to get data from db", ex);
            return new ArrayList<>();
        } catch (Exception ex) {
            System.out.println("Exception found");
            logger.logErrorWithDialog("Unable to get data from db", ex);
            return new ArrayList<>();
        }
    }

    /**
     * Retrieves all programmes from the db
     *
     * @param query
     * @return
     */
    public ArrayList<Programme> getProgrammes() {
        String query = "Select * from Programmes;";

        try (ResultSet rs = st.executeQuery(query)) {
            ArrayList<Programme> progList = new ArrayList<>();

            while (rs.next()) {
                Programme prog = new Programme();
                prog.setProgrammeId(rs.getInt("ProgrammeID"));
                prog.setProgrammeName(rs.getString("ProgrammeName"));
                prog.setMaximumLevel(rs.getInt("MaximumLevel"));
                prog.setProgrammeFee(rs.getDouble("ProgrammeFee"));
                prog.setDeptId(rs.getInt("Programme_DeptID"));
                prog.setMatricType(rs.getInt("MatricType"));
                prog.setStudentNumber(rs.getInt("StudentsNumber"));
                prog.setProgCourseNumber(rs.getInt("Programme_CourseNumber"));
                progList.add(prog);
            }

            return progList;
        } catch (SQLException ex) {
            System.out.println("Exception found");
            logger.logErrorWithDialog("Unable to get data from db", ex);
            return new ArrayList<>();
        } catch (Exception ex) {
            System.out.println("Exception found");
            logger.logErrorWithDialog("Unable to get data from db", ex);
            return new ArrayList<>();
        }
    }

    /**
     * Gets a result set if needed
     *
     * @param query
     * @return
     */
    public Object[] getResultSet(String query) {

        try {
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery(query);
            return new Object[]{st, rs};
        } catch (SQLException sq) {
            logger.logErrorWithDialog("Error in getting results from db", sq);
            return null;
        } catch (Exception sq) {
            logger.logErrorWithDialog("Error in getting results from db", sq);
            return null;
        }
    }

    /**
     * Gets all student information
     *
     * @return
     */
    public ArrayList<Student> getStudents() {
        String query = "Select * from Students;";
        try (ResultSet rs = st.executeQuery(query)) {
            ArrayList<Student> studentsList = new ArrayList<>();

            while (rs.next()) {
                studentsList.add(setAndGetStudent(rs));
            }

            return studentsList;
        } catch (SQLException sq) {
            logger.logErrorWithDialog("Unable to get data from db", sq);
            return new ArrayList<>();
        } catch (Exception sq) {
            logger.logErrorWithDialog("Unable to get data from db", sq);
            return new ArrayList<>();
        }
    }

    /**
     * Gets all student information with a particular programme ID
     *
     * @param progID
     * @return
     */
    public ArrayList<Student> getStudents(int progID) {
        String query = String.format("Select * from Students where Student_ProgrammeID = %d;", progID);
        try (ResultSet rs = st.executeQuery(query)) {
            ArrayList<Student> studentsList = new ArrayList<>();

            while (rs.next()) {
                studentsList.add(setAndGetStudent(rs));
            }

            return studentsList;
        } catch (SQLException sq) {
            logger.logErrorWithDialog("Unable to get data from db", sq);
            return new ArrayList<>();
        } catch (Exception sq) {
            logger.logErrorWithDialog("Unable to get data from db", sq);
            return new ArrayList<>();
        }
    }

    private Student setAndGetStudent(ResultSet rs) throws SQLException {
        Student stu = new Student();
        
        try {
            stu.setMatricNo(rs.getInt("MatricNo"));
            stu.setAge(rs.getInt("Age"));
            stu.setCourseAdvisorApproved(rs.getBoolean("CourseAdvisorApproved"));
            stu.setHODApproved(rs.getBoolean("HODApproved"));
            stu.setDeanApproved(rs.getBoolean("DeanApproved"));
            stu.setRegistrationAllowed(rs.getBoolean("RegistrationAllowed"));
            stu.setFeeBalance(rs.getDouble("FeeBalance"));
            stu.setGpa(rs.getDouble("Gpa"));
            stu.setLastName(rs.getString("LastName"));
            stu.setFirstName(rs.getString("FirstName"));
            stu.setLevel(rs.getInt("Level"));
            stu.setLoginId(rs.getInt("Student_LoginId"));
            stu.setStudentMaxUnits(rs.getInt("Student_MaxUnits"));
            stu.setPassportUrlString(rs.getString("Picture"));
            stu.setProgrammeId(rs.getInt("Student_ProgrammeID"));
            stu.setStuRegistered(rs.getBoolean("StudentRegistered"));
        } catch (Exception ex) {
            logger.logErrorWithDialog("Error in retrieveing data from result set", ex);
            return stu;
        }

        return stu;
    }

    /**
     * Sends an Update, Insert, Delete query to the DB
     *
     * @param query
     * @return
     */
    public boolean sendUpdateQuery(String query) {
        boolean done = false;

        try {
            done = st.executeUpdate(query) > -1;
        } catch (SQLException ex) {
            logger.logErrorWithDialog("Update Error", ex);
        } catch (Exception ex) {
            logger.logErrorWithDialog("Update Error", ex);
        }

        return done;
    }
}

//Validate and Assign Class
class ValidateAndAssign implements Runnable {

    private Connection conn;
    private Statement st;
    private Socket sock;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private ResultSet rs;
    private static CopyOnWriteArrayList<ClientHandler> clientsList;
    private ExecutorService exec;
    private final Logger logger = Logger.getLogInstance();
    private Future<Boolean> exit;
    private ThreadFactory factory;

    public ValidateAndAssign(Socket sock, ObjectOutputStream oos, ObjectInputStream ois, CopyOnWriteArrayList<ClientHandler> clients, Connection conn, ThreadFactory factory) {
        this.clientsList = clients;
        this.ois = ois;
        this.oos = oos;
        this.sock = sock;
        this.conn = conn;
        this.factory = factory;
        exec = Executors.newSingleThreadExecutor(factory);
    }

    /**
     * Validates and assigns new thread based on role
     */
    @Override
    public void run() {
        System.out.println("About to validate...");
        validate();
        System.out.println("Validation finished");
    }

    public void validate() {
        try {
            st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            System.out.println("Created statement, about to start while loop");

            loginLoop:
            while (true) {
                String message = ois.readUTF(); //get verify query
                System.out.println("gotten message...");

                if (message.equalsIgnoreCase("exit")) { //If the login frame exits
                    System.out.println("Client closed connection");
                    shutdownThread(); //close all resources 
                    return;
                } else {
                    System.out.println("Message is a query");
                    rs = st.executeQuery(message); //run query
                    rs.next();
                    String role = rs.getString("Role"); //get the role

                    if (role != null) { //if login credentials are valid
                        // send signal to client that login exists
                        oos.writeBoolean(true);
                        oos.flush();
                        System.out.println("Written valid login to client");

                        oos.writeUTF(role); //write role to client to enable client open appropriate window
                        oos.flush();

                        System.out.println("written role to client");

                        //Run appropriate thread for the role specified
                        if (role.equalsIgnoreCase("student")) {
                            System.out.println("Opening student handler");
                            StudentHandler student = new StudentHandler(sock, oos, ois, role, clientsList, conn);
                            clientsList.add(student); //add to list of connections
                            setExit(exec.submit(student)); //run thread for new student
                        } else if (role.equalsIgnoreCase("course advisor") || role.equalsIgnoreCase("hod") || role.equalsIgnoreCase("dean")) {
                            System.out.println("Opening staff handler");
                            StaffHandler staff = new StaffHandler(sock, oos, ois, role, clientsList, conn);
                            clientsList.add(staff); //add to list of connections
                            setExit(exec.submit(staff)); //run thread for new staff
                        } else if (role.equalsIgnoreCase("admin")) {
                            StaffHandler admin = new StaffHandler(sock, oos, ois, role, clientsList, conn);
                            clientsList.add(admin); //add to list of connections
                            setExit(exec.submit(admin)); //run thread for new admin
                        }

                        //When client handler returns
                        if (getExit().get()) {
                            logger.logMessage("Client application closed");
                            break loginLoop; //exit loop and end thread execution
                        } else {
                            logger.logErrorNormal("Client application ended with an error", new Exception());
                        }

                    } else { //login doesn't exist
                        oos.writeBoolean(false);//tell client that password doesn't exist
                        oos.flush();
                    }

                    rs.close(); //close result set
                }
            }
        } catch (IOException io) {
            logger.logErrorWithDialog("Error in transferring data from server to client", io);
        } catch (SQLException sq) {
            System.err.println("Error occured here in sql exception");
            sq.printStackTrace();
            logger.logErrorWithDialog("Unable to validate login. DB Error", sq);
        } catch (InterruptedException | ExecutionException ie) {
            logger.logErrorWithDialog("ClientHandler Interrupted", ie);
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

            if (st != null) {
                st.close();
            }
        } catch (IOException io) {
            logger.logErrorWithDialog("Resources failed to close. Please contact admin", io);
        } catch (SQLException sq) {
            logger.logErrorWithDialog("Connection or Statement refused to close. Please contact admin", sq);
        }
    }

    protected void shutdownThread() {
        closeResources();
    }

    /**
     * @return the exit
     */
    public Future<Boolean> getExit() {
        return exit;
    }

    /**
     * @param exit the exit to set
     */
    public void setExit(Future<Boolean> exit) {
        this.exit = exit;
    }

}
