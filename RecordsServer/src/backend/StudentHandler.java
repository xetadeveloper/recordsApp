package backend;

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
import pojo.CarryOvers;
import pojo.College;
import pojo.Course;
import pojo.Department;
import pojo.Login;
import pojo.Programme;
import pojo.RegisteredCourses;
import pojo.SessionDetails;
import pojo.Student;

/**
 * For students to view data, register courses
 *
 * @author Fego
 */
public class StudentHandler extends ClientHandler {

    //Fields
    private boolean readyState = false;
    private Statement st;

    //Constructor
    public StudentHandler(Socket sock, ObjectOutputStream oos, ObjectInputStream ois,
            String role,
            CopyOnWriteArrayList<ClientHandler> clients,
            Connection conn) {
        this.sock = sock;
        this.oos = oos;
        this.ois = ois;
        this.role = role;
        this.clientsList = clients;
        this.type = ClientType.STUDENT;
        this.conn = conn;

    }

    @Override
    public Boolean call() {
        try {
            System.out.println("Creating statement");
            this.st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            if (st == null) { //if statement has not been initialized
                System.out.println("Statement is null");
                readyState = false;
                oos.writeObject(readyState);
                oos.flush();
            } else {
                System.out.println("Statment ready?: " + st != null);
                System.out.println("Statment closed?: " + st.isClosed());
                readyState = true;
                oos.writeObject(readyState);//used to determine whether an error frame will open up or not
                oos.flush();

                System.out.println("Written to client that student handler is ready");

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
                            case StudentHandler.SELECT:
                                System.out.println("Select query found");
                                ArrayList<Object> studentData = selectData(st, query);

                                System.out.println("Writing data back to client");
                                oos.writeObject(studentData);
                                oos.flush();
                                System.out.println("Written to client");

                                break;

                            case StudentHandler.UPDATE:
                                boolean updated = updateData(st, query);
                                oos.writeObject(updated);
                                oos.flush();
                                break;

                            case StudentHandler.DELETE:
                                deleteData(st, query);
                                break;

                            case StudentHandler.INSERT:
                                System.out.println("Received query to insert");
                                boolean inserted = insertData(st, query);
                                System.out.println("writing response to client");
                                oos.writeObject(inserted);
                                oos.flush();
                                System.out.println("Written response");
                                break;

                            case StudentHandler.EXIT:
                                System.out.println("Student exited");
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
            io.printStackTrace();
        } catch (ClassNotFoundException cfe) {

        } catch (SQLException sq) {
            logger.logErrorNormal("Statement creation unable to proceed or query execution unable to complete", sq);
            sq.printStackTrace();
        }catch (Exception sq) {
            logger.logErrorNormal("Error in call method", sq);
            sq.printStackTrace();
        }

        clientsList.remove(this);
        shutdownThread();
        System.out.println("Student handler ended....");
        return true;
    }

    /**
     * To update data, like editing their profile
     *
     * @param st
     * @param query message containing query to be updated
     * @return true if data was updated and false if it wasn't
     */
    @Override
    public boolean updateData(Statement st, String query) {
        boolean updated = false;
        try {
            updated = st.executeUpdate(query) > 0;
            return updated;
        } catch (SQLException ex) {
            logger.logErrorNormal("Unable to update data. Contact admin", ex);
            return updated;
        }catch (Exception ex) {
            logger.logErrorNormal("Unable to update data. Contact admin", ex);
            return updated;
        }
        
    }

    /**
     * For rare cases of a delete operation
     *
     * @param st
     * @param query
     * @return
     */
    @Override
    public boolean deleteData(Statement st, String query) {
        boolean deleted = false;

        try {
            deleted = st.executeUpdate(query) > 0;
            return deleted;
        } catch (SQLException ex) {
            logger.logErrorNormal("Unable to delete data. Contact admin", ex);
            return deleted;
        }catch (Exception ex) {
            logger.logErrorNormal("Unable to delete data. Contact admin", ex);
            return deleted;
        }
    }

    /**
     * For inserting student registration details
     *
     * @param st Statement object used to execute query
     * @param query query to execute
     * @param data data to be inserted
     * @return true/false if insert worked
     */
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
        }catch (Exception ex) {
            ex.printStackTrace();
            logger.logErrorNormal("Unable to add new data. Contact admin", ex);
            return inserted;
        }
    }

    /**
     * To view data
     *
     * @param st Statement object used to execute query
     * @param query query to execute
     * @return results from db
     */
    @Override
    public ArrayList<Object> selectData(Statement st, String query) {
        ArrayList<Object> studentData = new ArrayList<>();
        System.out.println("In select data");
        
        System.out.println("\nQuery: " + query);
        //retrieve all data pertaining to the student

        try (ResultSet rs = st.executeQuery(query)) {
            System.out.println("Result set created");
            int count = 0; //Counter

            //create new pojo objects for data transfer
            Student stu = new Student();
            Programme prog = new Programme();
            Department dept = new Department();
            College col = new College();
            CarryOvers carOv = new CarryOvers();
            RegisteredCourses registered = new RegisteredCourses();
            SessionDetails session = new SessionDetails();
            ArrayList<Course> coursesMain = new ArrayList<>(200);
            Course courses = new Course();

            storeData:
            while (rs.next()) {
                //If this is the first iteration get all the data
                if (count == 0) {
                    //set data for pojo classes
                    //Students
                    stu.setMatricNo(rs.getInt("MatricNo"));
                    stu.setFirstName(rs.getString("FirstName"));
                    stu.setLastName(rs.getString("LastName"));
                    stu.setAge(rs.getInt("Age"));
                    stu.setFeeBalance(rs.getDouble("FeeBalance"));
                    stu.setLevel(rs.getInt("Level"));
                    stu.setGpa(rs.getDouble("GPA"));
                    stu.setLoginId(rs.getInt("Student_LoginID"));
                    stu.setRegistrationAllowed(rs.getBoolean("RegistrationAllowed"));
                    stu.setDeanApproved(rs.getBoolean("DeanApproved"));
                    stu.setHODApproved(rs.getBoolean("HODApproved"));
                    stu.setCourseAdvisorApproved(rs.getBoolean("CourseAdvisorApproved"));
                    stu.setPassport(getPassport(Paths.get(rs.getString("Picture"))));
                    stu.setStuRegistered(rs.getBoolean("StudentRegistered"));

                    //Programmes
                    prog.setProgrammeId(rs.getInt("ProgrammeID"));
                    prog.setProgrammeName(rs.getString("ProgrammeName"));
                    prog.setMaximumLevel(rs.getInt("MaximumLevel"));
                    prog.setProgrammeFee(rs.getDouble("ProgrammeFee"));
                    prog.setDeptId(rs.getInt("Programme_DeptID"));

                    //Departments
                    dept.setDeptId(rs.getInt("DeptID"));
                    dept.setDeptHeadStaffID(rs.getInt("DeptHead_StaffID"));
                    dept.setDeptName(rs.getString("DeptName"));
                    dept.setDeptCollegeId(rs.getInt("Dept_CollegeID"));

                    //SessionDetails
                    session.setSessionName(rs.getString("SessionName"));
                    session.setMaximumUnits(rs.getInt("MaximumUnits"));
                    session.setSemester(rs.getString("Semester"));
                    session.setSessionId(rs.getInt("SessionID"));

                    //Colleges
                    col.setCollegeId(rs.getInt("CollegeID"));
                    col.setCollegeName(rs.getString("CollegeName"));

                    //Courses
                    String courseCode = rs.getString("CourseCode");

                    if (courseCode != null) {
                        Course newCourse = new Course();
                        newCourse.setCourseCode(courseCode);
                        newCourse.setCourseName(rs.getString("CourseName"));
                        newCourse.setCourseStatus(rs.getString("CourseStatus"));
                        newCourse.setCreditUnits(rs.getInt("CreditUnits"));
                        newCourse.setLevel(rs.getInt("Course_Level"));
                        newCourse.setProgrammeId(rs.getInt("Course_ProgrammeID"));
                        //Add courses to course list and  object list
                        courses.addCourseFullInfo(newCourse);
                    }

                    //CarryOvers 
                    String car_CourseCode = rs.getString("Carry_CourseCode");
                    if (car_CourseCode != null) {
                        carOv.setExists(true);
                        carOv.setCarryMatricNo(rs.getInt("Carry_MatricNo"));
                        carOv.setSessionId(rs.getInt("Carry_SessionID"));
                        carOv.addCarried(setupCourseWithQuery(rs.getInt("Carry_MatricNo"), car_CourseCode));
                    } else {
                        carOv.setExists(false);
                    }

                    //Registered Courses
                    String reg_courseCode = rs.getString("Reg_CourseCode");
                    if (reg_courseCode != null) {
                        RegisteredCourses regCourse = new RegisteredCourses();
                        regCourse.setRegMatricNo(rs.getInt("Reg_MatricNo"));
                        regCourse.setSessionId(rs.getInt("Reg_SessionID"));
                        registered.addRegisteredCourse(setupCourseWithQuery(rs.getInt("Reg_MatricNo"), reg_courseCode));
                        registered.setExists(true);
                    } else {
                        registered.setExists(false);
                    }

                    //increment count and continue
                    count++;
                    continue storeData;
                } else { //if all data has been gotten and there are more rows for either carryovers or registered courses or new courses
                    //new Courses
                    String courseCode = rs.getString("CourseCode");
                    if (courseCode != null) {
                        Course newCourse = new Course();
                        newCourse.setCourseCode(courseCode);
                        newCourse.setCourseName(rs.getString("CourseName"));
                        newCourse.setCourseStatus(rs.getString("CourseStatus"));
                        newCourse.setCreditUnits(rs.getInt("CreditUnits"));
                        newCourse.setLevel(rs.getInt("Course_Level"));
                        newCourse.setProgrammeId(rs.getInt("Course_ProgrammeID"));

                        if (!(courses.getCourseList().contains(newCourse))) {
                            courses.addCourseFullInfo(newCourse);
                        }
                    }

                    //Carry Overs
                    if (carOv.exists()) {
                        String car_CourseCode = rs.getString("Carry_CourseCode");

                        if (!(carOv.getCarriedCourses().stream().
                                map(carry -> carry.getCourseCode()).
                                anyMatch(code -> code.equalsIgnoreCase(car_CourseCode)))) {
                            carOv.addCarried(setupCourseWithQuery(rs.getInt("Carry_MatricNo"), car_CourseCode));
                        }
                    }

                    //Registetered Courses
                    if (registered.exists()) {
                        String reg_CourseCode = rs.getString("Reg_CourseCode");

                        if (!(registered.getRegisteredCourses().stream().
                                map(reg -> reg.getCourseCode()).
                                anyMatch(code -> code.equalsIgnoreCase(reg_CourseCode)))) {
                            registered.addRegisteredCourse(setupCourseWithQuery(rs.getInt("Reg_MatricNo"), reg_CourseCode));
                        }
                    }
                }
            }

            //add pojo classes to list object waiting to be serialized
            studentData.add(stu);
            studentData.add(prog);
            studentData.add(dept);
            studentData.add(col);
            studentData.add(session);
            studentData.add(carOv);
            studentData.add(registered);
            studentData.add(courses);
            
            count = 0;
            return studentData;

        } catch (SQLException sq) {
            logger.logErrorNormal("Unable to retreive information from database. Please contact admin", sq);
            System.out.println("Exceptio thrwosn in select");
            sq.printStackTrace();
            return null;
        } catch (Exception ex) {
            logger.logErrorNormal("Unable to retreive information from database. Please contact admin", ex);
            System.err.println("Got exception");
            ex.printStackTrace();
            return null;
        }
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
                contains = false;
                
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
        }
        catch (Exception sq) {
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
        }catch (Exception sq) {
            logger.logErrorNormal("Student Handler didnt shut down properly", sq);
        }
    }
}
