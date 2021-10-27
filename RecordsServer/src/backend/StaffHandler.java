package backend;

import static backend.ClientHandler.SELECT;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import pojo.*;

/**
 * For staffs to view data and course advisor, HOD and dean to approve of
 * registration
 *
 * @author Fego
 */
public class StaffHandler extends ClientHandler {

    //Fields
    private Statement st;
    private boolean ready = false;

    //Constructor
    public StaffHandler(Socket sock, ObjectOutputStream oos, ObjectInputStream ois,
            String role,
            CopyOnWriteArrayList<ClientHandler> clients,
            Connection conn) {
        this.sock = sock;
        this.oos = oos;
        this.ois = ois;
        this.type = ClientType.STAFF;
        this.clientsList = clients;
        this.conn = conn;
        this.role = role;
    }

    @Override
    public Boolean call() {
        try {
            //create statement
            this.st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            System.out.println("Statement created");

            if (st == null) { //if statement has not been initialized
                ready = false;
                oos.writeObject(ready);
                oos.flush();
            } else {
                ready = true;
                System.out.println("About to write to client ready status");
                oos.writeObject(ready);//used to determine whether an error frame will open up or not
                oos.flush();
                System.out.println("Written to client that staff handler is ready");

                //statement has been initialized and queries are ready to be run
                mainLoop:
                while (true) {
                    System.out.println("Waiting for queries");
                    Object obj = ois.readObject();
                    System.out.println("Got query");

                    if (obj instanceof String) {//if obj is a message that contains a query
                        System.out.println("Query message received from client");
                        String msg = obj.toString();
                        splitMessage(msg);
                        System.out.println(queryType == SELECT ? "Select query type" : "other query type");
                        System.out.println("QueryType: " + queryType);

                        switch (queryType) {
                            case SELECT:
                                System.out.println("Select query found");
                                ArrayList<ArrayList<?>> studentData = selectData(st, query);

                                System.out.println("Writing data back to client");
                                oos.writeObject(studentData);
                                oos.flush();
                                System.out.println("Written to client");

                                break;

                            case UPDATE:
                                boolean updated = updateData(st, query);
                                oos.writeObject(updated);
                                oos.flush();
                                break;

                            case DELETE:
                                deleteData(st, query);
                                break;

                            case INSERT:
                                System.out.println("Received query to insert");
                                boolean inserted = insertData(st, query);
                                System.out.println("writing response to client");
                                oos.writeObject(inserted);
                                oos.flush();
                                System.out.println("Written response");
                                break;

                            case EXIT:
                                System.out.println("Staff exited");
                                break mainLoop;

                            case GETINFO:
                                System.out.println("Received query to get school info");
                                ArrayList<ArrayList<?>> schoolData = getInfo(st, query);

                                oos.writeObject(schoolData);
                                oos.flush();
                                System.out.println("Written school data to client");
                                break;

                            case GETLOGIN:
                                System.out.println("LOGIN REQUESTED");
                                Login login = getLoginDetails(st, query);
                                oos.writeObject(login);
                                oos.flush();
                                break;
                        }
                    }
                }
            }
        } catch (IOException io) {
            logger.logErrorNormal("Error in server handling client with IP: " + sock.getInetAddress().getHostAddress(), io);
        } catch (ClassNotFoundException cfe) {

        } catch (SQLException sq) {
            logger.logErrorNormal("Statement creation unable to proceed or query execution unable to complete", sq);
        } catch (Exception sq) {
            logger.logErrorNormal("Error in call method", sq);
        }

        clientsList.remove(this);
        shutdownThread();
        return true;
    }

    @Override
    public boolean updateData(Statement st, String query) {
        boolean updated = false;
        try {
            updated = st.executeUpdate(query) > 0;
            return updated;
        } catch (SQLException ex) {
            logger.logErrorNormal("Unable to update data. Contact admin", ex);
            return false;
        } catch (Exception ex) {
            logger.logErrorNormal("Unable to update data. Contact admin", ex);
            return false;
        }
    }

    @Override
    public boolean deleteData(Statement st, String query) {
        boolean deleted = false;

        try {
            deleted = st.executeUpdate(query) > 0;
            return deleted;
        } catch (SQLException ex) {
            logger.logErrorNormal("Unable to delete data. Contact admin", ex);
            return deleted;
        } catch (Exception ex) {
            logger.logErrorNormal("Unable to delete data. Contact admin", ex);
            return deleted;
        }
    }

    @Override
    public boolean insertData(Statement st, String query) {
        boolean inserted = false;
        try {
            System.out.println("Inside insert data method");
            inserted = st.executeUpdate(query) > 0;
            System.out.println(inserted ? "Inserted data into db" : "insert operarion failed");
            return inserted;
        } catch (SQLException ex) {
            ex.printStackTrace();
            logger.logErrorNormal("Unable to add new data. Contact admin", ex);
            return inserted;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.logErrorNormal("Unable to add new data. Contact admin", ex);
            return inserted;
        }
    }

    @Override
    public ArrayList<ArrayList<?>> selectData(Statement st, String query) {
        ArrayList<ArrayList<?>> records = new ArrayList<>();

        //get all student data
        try (ResultSet rs = st.executeQuery(query)) {
            //An arraylist of student data
            ArrayList<Student> studentsList = new ArrayList<>();
            ArrayList<Programme> programmesList = new ArrayList<>();
            ArrayList<Department> deptsList = new ArrayList<>();
            ArrayList<College> collegesList = new ArrayList<>();
            ArrayList<CarryOvers> carOvsList = new ArrayList<>();
            ArrayList<RegisteredCourses> regsList = new ArrayList<>();
            ArrayList<SessionDetails> sessionsList = new ArrayList<>();
            ArrayList<Course> coursesList = new ArrayList<>();

            boolean contains = false;
            int count = 0; //Counter for result set
            while (rs.next()) {
                contains = false;

                //Students
                Student stu = new Student();

                int matric = rs.getInt("MatricNo");
                for (Student student : studentsList) {
                    if (student.getMatricNo() == matric) {
                        contains = true;
                    }
                }

                if (!contains) {
                    stu.setMatricNo(matric);
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
                    stu.setPassport(getPassport(Paths.get(rs.getString("Picture"))));
                    stu.setProgrammeId(rs.getInt("Student_ProgrammeID"));
                    stu.setStudentMaxUnits(rs.getInt("Student_MaxUnits"));
                    stu.setPassportUrlString(rs.getString("Picture"));
                    stu.setStuRegistered(rs.getBoolean("StudentRegistered"));
                    studentsList.add(stu);
                }

                contains = false;

                //Colleges
                College col = new College();
                for (College college : collegesList) {
                    if (college.getCollegeMatricNo() == matric) {
                        contains = true;
                    }
                }
                if (!contains) {
                    col.setCollegeId(rs.getInt("CollegeID"));
                    col.setCollegeName(rs.getString("CollegeName"));
                    col.setCollegeMatricNo(rs.getInt("MatricNo"));
                    collegesList.add(col); //Add college
                }

                contains = false;

                //Departments
                Department dept = new Department();
                for (Department deptLocal : deptsList) {
                    if (deptLocal.getDeptMatricNo() == matric) {
                        contains = true;
                    }
                }

                if (!contains) {
                    dept.setDeptId(rs.getInt("DeptID"));
                    dept.setDeptHeadStaffID(rs.getInt("DeptHead_StaffID"));
                    dept.setDeptName(rs.getString("DeptName"));
                    dept.setDeptCollegeId(rs.getInt("Dept_CollegeID"));
                    dept.setDeptMatricNo(rs.getInt("MatricNo"));
                    dept.setDeptCollege(col);
                    dept.setStudent(stu);
                    deptsList.add(dept);
                }

                contains = false;

                //Programmes
                Programme prog = new Programme();
                for (Programme progLocal : programmesList) {
                    if (progLocal.getProgrammeMatricNo() == matric) {
                        contains = true;
                    }
                }

                if (!contains) {
                    prog.setProgrammeId(rs.getInt("ProgrammeID"));
                    prog.setProgrammeName(rs.getString("ProgrammeName"));
                    prog.setMaximumLevel(rs.getInt("MaximumLevel"));
                    prog.setProgrammeFee(rs.getDouble("ProgrammeFee"));
                    prog.setProgrammeMatricNo(rs.getInt("MatricNo"));
                    prog.setDeptId(rs.getInt("Programme_DeptID"));
                    prog.setProgrammeDept(dept);
                    prog.setStudent(stu);
                    programmesList.add(prog);
                }

                contains = false;
                //Courses
                String courseCode = rs.getString("CourseCode");
                for (Course courseLocal : coursesList) {
                    if (courseLocal.getCourseCode().equalsIgnoreCase(courseCode)) {
                        if (courseLocal.getCourseMatricNo() == matric) {
                            contains = true;
                            break;
                        }
                    }
                }

                if (courseCode != null && !(contains)) {
                    Course course = new Course();
                    course.setCourseCode(rs.getString("CourseCode"));
                    course.setCourseName(rs.getString("CourseName"));
                    course.setCourseStatus(rs.getString("CourseStatus"));
                    course.setCourseMatricNo(matric);
                    course.setCreditUnits(rs.getInt("CreditUnits"));
                    course.setLevel(rs.getInt("Course_Level"));
                    course.setProgrammeId(rs.getInt("Course_ProgrammeID"));
                    course.setProgramme(prog);
                    course.setStudent(stu);
                    coursesList.add(course);
                }

                contains = false;

                //Carry Overs
                String carry_courseCode = rs.getString("Carry_CourseCode");
                int carryMatric = rs.getInt("Carry_MatricNo");

                for (CarryOvers carryLocal : carOvsList) {
                    if (carryLocal.getCarryOverCourse().getCourseCode().equalsIgnoreCase(carry_courseCode)) {
                        if (carryLocal.getCarryMatricNo() == carryMatric) {
                            contains = true;
                        }
                    }
                }

                if (carry_courseCode != null && !contains) {
                    CarryOvers carry = new CarryOvers();
                    carry.setCarryMatricNo(rs.getInt("Carry_MatricNo"));
                    carry.setExists(true);
                    carry.setSessionId(rs.getInt("Carry_SessionID"));
                    carry.setCarryTreeStudent(stu);
                    carry.setCarryOverCourse(setupCourseWithQuery(stu.getMatricNo(), carry_courseCode));
                    carOvsList.add(carry);
                }

                contains = false;

                //Registered Courses
                String reg_courseCode = rs.getString("Reg_CourseCode");
                int regMatric = rs.getInt("Reg_MatricNo");
                for (RegisteredCourses regLocal : regsList) {
                    if (regLocal.getRegCourse().getCourseCode().equalsIgnoreCase(reg_courseCode)) {
                        if (regLocal.getRegMatricNo() == regMatric) {
                            contains = true;
                        }
                    }
                }

                if (reg_courseCode != null && !contains) {
                    RegisteredCourses reg = new RegisteredCourses();
                    reg.setRegMatricNo(regMatric);
                    reg.setExists(true);
                    reg.setSessionId(rs.getInt("Reg_SessionID"));
                    reg.setRegStudent(stu);
                    reg.setRegCourse(setupCourseWithQuery(stu.getMatricNo(), reg_courseCode));
                    regsList.add(reg);
                }

                contains = false;

                //SessionDetails
                SessionDetails session = new SessionDetails();

                for (SessionDetails sessLocal : sessionsList) {
                    if (sessLocal.getSessionMatricNo() == matric) {
                        contains = true;
                    }
                }

                if (!contains) {
                    session.setSessionName(rs.getString("SessionName"));
                    session.setMaximumUnits(rs.getInt("MaximumUnits"));
                    session.setSemester(rs.getString("Semester"));
                    session.setSessionId(rs.getInt("SessionID"));
                    session.setSessionMatricNo(matric);
                    sessionsList.add(session);
                }

                contains = false;
            }

            records.add(studentsList);
            records.add(collegesList);
            records.add(deptsList);
            records.add(programmesList);
            records.add(sessionsList);
            records.add(coursesList);
            records.add(carOvsList);
            records.add(regsList);

            return records;
        } catch (SQLException sq) {
            sq.printStackTrace();
            logger.logErrorNormal("Unable to retreive data from database", sq);
        }catch (Exception sq) {
            sq.printStackTrace();
            logger.logErrorNormal("Unable to retreive data from database", sq);
        }

        return null;
    }

    /**
     * Retrieves colleges, depts and programmes from db
     *
     * @param st statement to be used
     * @param query query to be executed
     * @return
     */
    public ArrayList<ArrayList<?>> getInfo(Statement st, String query) {
        ArrayList<ArrayList<?>> list = new ArrayList<>();

        ArrayList<College> collegesList = new ArrayList<>();
        ArrayList<Department> deptsList = new ArrayList<>();
        ArrayList<Programme> programmesList = new ArrayList<>();
        ArrayList<Course> courseList = new ArrayList<>();

        boolean contains = false;

        try (ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                //Colleges
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
            logger.logErrorNormal("Unable to retreive data from database", sq);
            return list;
        }catch (Exception sq) {
            sq.printStackTrace();
            logger.logErrorNormal("Unable to retreive data from database", sq);
            return list;
        }
    }

    public void shutdownThread() {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException sq) {
            logger.logErrorNormal("Student Handler didnt shut down properly", sq);
        } catch (Exception sq) {
            logger.logErrorNormal("Student Handler didnt shut down properly", sq);
        }
    }

}
