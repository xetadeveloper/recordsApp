package frontend;

import backend.MainClient;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import pojo.CarryOvers;
import pojo.College;
import pojo.Course;
import pojo.Department;
import pojo.Programme;
import pojo.RegisteredCourses;
import pojo.SessionDetails;
import pojo.Student;

/**
 *
 * @author Fego
 */
public abstract class AbstractClientFrame extends javax.swing.JFrame {

    //Text property
    protected Font tblHeaderFont = new Font("Segoe UI Emoji", Font.BOLD, 14);
    protected Color backgroundColor = new Color(84, 83, 83);
    protected Color foregroundColor = new Color(204, 204, 204);
    protected final static int SELECT = 0, INSERT = 1, UPDATE = 2, DELETE = 3, EXIT = 4, GETINFO = 5, GETLOGIN = 6;
    protected Dimension showCourseInfoSize = new Dimension(559, 380);
    protected String[] columnNames = new String[]{"Matric No", "Last Name", "First Name"};

    //Screen size
    protected Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    //Student Details Classes for students
    protected Student student;
    protected College college;
    protected Department dept;
    protected CarryOvers carryOver;
    protected RegisteredCourses regCourses;
    protected Programme programme;
    protected SessionDetails session;
    protected Course newCourses;

    //Student Details Classes for staffs
    protected ArrayList<Student> studentList = new ArrayList<>(500);
    protected ArrayList<College> collegeList = new ArrayList<>(500);
    protected ArrayList<Department> deptList = new ArrayList<>(500);
    protected ArrayList<CarryOvers> carryOverList = new ArrayList<>(500);
    protected ArrayList<RegisteredCourses> regCoursesList = new ArrayList<>(500);
    protected ArrayList<Programme> programmeList = new ArrayList<>(500);
    protected ArrayList<SessionDetails> sessionList = new ArrayList<>(500);
    protected ArrayList<Course> coursesList = new ArrayList<>(500);

    protected ArrayList<College> collegeFull = new ArrayList<>();
    protected ArrayList<Department> deptFull = new ArrayList<>();
    protected ArrayList<Programme> progFull = new ArrayList<>();
    protected ArrayList<Course> coursesFull = new ArrayList<>();

    /**
     * Creates new form ClientFrame
     */
    public AbstractClientFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Custom Methods
    /**
     * Adds rows from one table to another
     *
     * @param src Source
     * @param dest Destination
     */
    protected void addCourseRow(JTable src, JTable dest) {
        //Get values from source table
        int row = src.getSelectedRow();
        System.out.println("Row Number: " + row);

        String[] newRow = new String[src.getColumnCount()];

        for (int i = 0; i < newRow.length; i++) {
            newRow[i] = src.getValueAt(row, i).toString();
        }

        //Add row to selected newCourses table
        DefaultTableModel model = (DefaultTableModel) dest.getModel();

        int rowCount = dest.getRowCount();
        int colCount = dest.getColumnCount();
        boolean add = true;
        System.out.println("RowCount: " + rowCount);
        System.out.println("ColumnCount: " + colCount);
        if (rowCount == 0) {
            add = true;
        } else {
            for (int i = 0; i < rowCount; i++) {
                if (dest.getValueAt(i, 0).toString().equalsIgnoreCase(newRow[0])) {
                    add = false;
                }
            }
        }

        if (add) {
            model.addRow(newRow);
            dest.setModel(model);
        }
    }

    /**
     * Removes a row from the table
     *
     * @param fromTable table to remove row from
     */
    protected void removeCourseRow(JTable fromTable) {
        DefaultTableModel model = (DefaultTableModel) fromTable.getModel();
        int rowIndex = fromTable.getSelectedRow();

        if (rowIndex > -1) {
            model.removeRow(rowIndex);
        }
    }

    /**
     * Checks for null values
     *
     * @param obj object to be tested for null
     * @return A string representation of that object
     */
    protected String checkNull(Object obj) {
        if (obj == null) {
            return "N/A";
        } else {
            return obj.toString();
        }
    }

    /**
     * Setting default style for tables
     *
     * @param table
     */
    protected void setDefaultTableStyle(JTable table) {
        JTableHeader header = table.getTableHeader();
        header.setFont(tblHeaderFont);
        header.setBackground(this.backgroundColor);
        header.setForeground(this.foregroundColor);

        table.setDefaultEditor(Object.class, null);
    }

    /**
     * Setting dialog for course info
     *
     * @param panelToAdd panel to be added to the new dialog
     * @return a new dialog
     */
    protected JDialog createDialog(JPanel panelToAdd, Dimension size) {
        JDialog dialog = new JDialog(this);
        dialog.add(panelToAdd);
        setDefaultDialogStyle(dialog, size);
        return dialog;
    }

    /**
     * Sets the dialog style
     *
     * @param dialog dialog to be modified
     */
    protected void setDefaultDialogStyle(JDialog dialog, Dimension size) {
        dialog.setSize(size);
        dialog.setLocationRelativeTo(this);
        dialog.setResizable(false);
        dialog.setTitle("Course Details");
        dialog.setAlwaysOnTop(false);
        dialog.setAutoRequestFocus(true);
    }

    /**
     * When a table loses focus deselect all rows
     *
     * @param table
     */
    protected void tblLostFocus(JTable table) {
        JPanel panel;

        // table.clearSelection();
    }

    /**
     * Initializes any table with values from the courseMap
     *
     * @param table the JList to be initialized
     * @param courseList the list to get data from
     */
    protected void initTable(JTable table, ArrayList<Course> courseList) {
        DefaultTableModel model = new DefaultTableModel(); //get the model of the list

        //Set model properties
        model.setColumnCount(2);
        model.setColumnIdentifiers(new String[]{"Course Code", "Credit Units"}); //Set the table header

        //Set rows from data provided
        courseList.stream().forEach((course) -> model.addRow(new String[]{course.getCourseCode(), String.valueOf(course.getCreditUnits())}));
        table.setModel(model); //set model
    }

    //Dummy data methods
    protected void initTableDummy(JTable table) {
        DefaultTableModel model = new DefaultTableModel(); //get the model of the list

        //Set model properties
        model.setColumnCount(2);
        model.setColumnIdentifiers(new String[]{"Course Code", "Credit Units"}); //Set the table header
        String[] row1 = new String[]{"CMP 301", "3"};
        String[] row2 = new String[]{"CMP 303", "2"};
        String[] row3 = new String[]{"CMP 305", "3"};

        model.addRow(row1);
        model.addRow(row2);
        model.addRow(row3);
        table.setModel(model); //set model
    }

    protected void initTableNoData(JTable table) {
        DefaultTableModel model = new DefaultTableModel(); //get the model of the list

        //Set model properties
        model.setColumnCount(2);
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"Course Code", "Credit Units"}); //Set the table header
        table.setModel(model); //set model
    }

    /**
     * Shows a dialog to display message
     *
     * @param message message to be displayed
     * @param title title of the dialog
     */
    protected void displayMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Initializes combo boxes
     *
     * @param combo the combo to add values to
     * @param data the list holding the data
     */
    protected void initCombo(JComboBox<String> combo, ArrayList<?> data) {
        System.out.println("In init combo");
        DefaultComboBoxModel model = (DefaultComboBoxModel) combo.getModel();

        if (data.size() > 0) {
            System.out.println("Greater than zero");
            data.stream().forEach(value -> {
                System.out.println("Value:  + value");
                model.addElement(value.toString());
            });
        }

        combo.setModel(model);
        System.out.println("Done init");
    }

    /**
     * Shows a dialog to display error message
     *
     * @param message message to be displayed
     * @param title title of the dialog
     */
    protected void displayErrorMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    protected void loseFocus(JTable table) {
        table.clearSelection();
    }

    /**
     * To open question dialogs for confirming user actions
     *
     * @param message question to be asked
     * @return boolean value if user clicked yes or no
     */
    public boolean confirmAction(String message) {
        int option = JOptionPane.showConfirmDialog(null, message, "Confirm", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Initializes the student data in the class that can be used at any time
     *
     * @param records
     */
    protected final void initData(ArrayList<ArrayList<Object>> records) {
        System.out.println("Length of student data is: " + records.size());
        for (ArrayList<Object> record : records) {
            System.out.println("Record Length: " + record.size());
            if (record.size() > 0 && record.get(0) instanceof Student) {
                System.out.println("Found student object");
                for (Object data : record) {
                    System.out.println("MatricNo: " + ((Student) data).getMatricNo());
                    studentList.add((Student) data);
                }
            } else if (record.size() > 0 && record.get(0) instanceof College) {
                System.out.println("found college object");
                for (Object data : record) {
                    collegeList.add((College) data);
                }
            } else if (record.size() > 0 && record.get(0) instanceof Programme) {
                System.out.println("found programme");
                for (Object data : record) {
                    programmeList.add((Programme) data);
                }
            } else if (record.size() > 0 && record.get(0) instanceof Department) {
                System.out.println("Found dept");
                for (Object data : record) {
                    deptList.add((Department) data);
                }
            } else if (record.size() > 0 && record.get(0) instanceof CarryOvers) {
                System.out.println("Found carry over");
                for (Object data : record) {
                    carryOverList.add((CarryOvers) data);
                }
            } else if (record.size() > 0 && record.get(0) instanceof RegisteredCourses) {
                System.out.println("Found registered courses");
                for (Object data : record) {
                    System.out.println("Reg MatricNO: " + ((RegisteredCourses) data).getRegMatricNo());
                    regCoursesList.add((RegisteredCourses) data);
                }
            } else if (record.size() > 0 && record.get(0) instanceof SessionDetails) {
                System.out.println("FOund session details");
                for (Object data : record) {
                    sessionList.add((SessionDetails) data);
                }
            } else if (record.size() > 0 && record.get(0) instanceof Course) {
                System.out.println("Found course");
                for (Object student : record) {
                    coursesList.add((Course) student);
                }
            }
        }
    }

    /**
     * Initializes the table with the students data
     *
     * @param table table to be initialized
     * @param studentList
     * @param columnNames
     */
    protected final void initTableRecords(JTable table, ArrayList<Student> studentList, String[] columnNames) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(columnNames);

        studentList.forEach(student -> model.addRow(new Object[]{
            student.getMatricNo(),
            student.getLastName(),
            student.getFirstName()
        }));

        table.setModel(model);
    }

    /**
     * Scales the image to the size of the label and sets it
     *
     * @param icon the image to be set
     * @param label the label to set the image
     */
    protected void setLabelImage(ImageIcon icon, JLabel label) {
        Dimension dim = label.getPreferredSize();
        BufferedImage scaledImage = new BufferedImage((int) dim.getWidth(), (int) dim.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D grph = scaledImage.createGraphics();
        grph.drawImage(icon.getImage(), 0, 0, (int) dim.getWidth(), (int) dim.getHeight(), null);
        grph.dispose();

        //set Image
        label.setIcon(new ImageIcon(scaledImage));
    }

    /**
     * Initializing the school info
     *
     * @param client
     */
    protected void initSchoolInfo(MainClient client) {
        //get data of all colleges and the rest from db
        String query = "select * from colleges\n"
                + "left join departments on departments.Dept_CollegeID = colleges.CollegeID\n"
                + "left join programmes on programmes.Programme_DeptID = departments.DeptID\n"
                + "left join courses on courses.course_programmeID = programmes.ProgrammeID;";

        System.out.println("\n\nGet info query: " + query);
        client.sendQuery(GETINFO + ":" + query);
        System.out.println("Sent query to retreive all info");

        ArrayList< ArrayList<Object>> records = (ArrayList< ArrayList<Object>>) client.getResponse();
        System.out.println("Got data");

        for (ArrayList<Object> record : records) {
            if (record.size() > 0 && record.get(0) instanceof College) {
                System.out.println("found college object");
                for (Object data : record) {
                    collegeFull.add((College) data);
                }
            } else if (record.size() > 0 && record.get(0) instanceof Programme) {
                System.out.println("found programme");
                for (Object data : record) {
                    progFull.add((Programme) data);
                }
            } else if (record.size() > 0 && record.get(0) instanceof Department) {
                System.out.println("Found dept");
                for (Object data : record) {
                    deptFull.add((Department) data);
                }
            } else if (record.size() > 0 && record.get(0) instanceof Course) {
                System.out.println("Found Course");
                for (Object data : record) {
                    coursesFull.add((Course) data);
                }
            }
        }
    }

    /**
     * Calculates the total registered units for the student
     *
     * @param regs
     * @return the value for the label
     */
    protected int getTotalRegisteredUnits(RegisteredCourses regs) {
        int units = 0;

        if (regs != null && regs.getRegisteredCourses().size() > 0) {
            units = regs.getRegisteredCourses().stream().mapToInt(regCourse -> regCourse.getCreditUnits()).sum();
        }

        return units;
    }

    /**
     * Creates dialog for changing login details
     *
     * @param client
     */
    protected void changeStaffLogin(MainClient client) {
        JDialog dialog = new JDialog(this);
        JPanel loginPanel = new PanelChangeLogin(client, dialog);
        setDefaultDialogStyle(dialog, loginPanel.getPreferredSize());
        dialog.add(loginPanel);
        dialog.setVisible(true);
        dialog.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                if (confirmAction("Exit login update")) {
                    dialog.dispose();
                }
            }
        });
    }

    /**
     * For searching students on a table
     *
     * @param tbl
     * @param studentList
     * @param searchType
     * @param text
     * @param columnNames
     */
    protected void searchStudent(JTable tbl, ArrayList<Student> studentList, String searchType, String text, String[] columnNames) {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        ArrayList<Student> list;

        if (searchType.equalsIgnoreCase("Matric No")) {
            try (Stream<Student> filtered = studentList.stream().filter(stu -> String.valueOf(stu.getMatricNo()).contains(text.trim().toLowerCase()))) {
                list = new ArrayList<>(filtered.collect(Collectors.toList()));
                initTableRecords(tbl, list, columnNames);
            }
        } else if (searchType.equalsIgnoreCase("Last Name")) {
            try (Stream<Student> filtered = studentList.stream().filter(stu -> stu.getLastName().toLowerCase().contains(text.trim().toLowerCase()))) {
                list = new ArrayList<>(filtered.collect(Collectors.toList()));
                initTableRecords(tbl, list, columnNames);
            }
        } else if (searchType.equalsIgnoreCase("First Name")) {
            try (Stream<Student> filtered = studentList.stream().filter(stu -> stu.getFirstName().toLowerCase().contains(text.trim().toLowerCase()))) {
                list = new ArrayList<>(filtered.collect(Collectors.toList()));
                initTableRecords(tbl, list, columnNames);
            }
        }

        tbl.setModel(model);
    }

    /**
     * Used to get a course object from a list of courses
     *
     * @param courseCode
     * @param course
     * @return
     */
    protected Course getCourse(String courseCode, ArrayList<Course> course) {
        return course.stream().filter(co -> co.getCourseCode().equalsIgnoreCase(courseCode.trim())).findFirst().get();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
