package frontend;

import backend.Logger;
import backend.MainServer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import pojo.CarryOvers;
import pojo.College;
import pojo.Course;
import pojo.Department;
import pojo.Programme;
import pojo.RegisteredCourses;
import pojo.Roles;
import pojo.SessionDetails;
import pojo.Staff;
import pojo.Student;

/**
 *
 * @author Fego
 */
public abstract class AbstractServerFrame extends javax.swing.JFrame {

    //Screen size
    protected MainServer server;
    protected Logger logger = Logger.getLogInstance();
    protected Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    protected Font tblHeaderFont = new Font("Segoe UI Emoji", Font.BOLD, 14);
    protected Color backgroundColor = new Color(84, 83, 83);
    protected Color foregroundColor = new Color(204, 204, 204);
    protected Border outer = BorderFactory.createLineBorder(new Color(255, 0, 0), 1, true);
    protected Border inner = new EmptyBorder(8, 8, 8, 8);
    protected Border errorBorder = new CompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true), new EmptyBorder(8, 8, 8, 8));
    protected Border normalBorder = new EmptyBorder(0, 0, 0, 0);

    //Student Details Classes for staffs
    protected ArrayList<Student> studentList = new ArrayList<>(500);
    protected ArrayList<Staff> staffList = new ArrayList<>(500);
    protected ArrayList<College> collegeList = new ArrayList<>(500);
    protected ArrayList<Department> deptList = new ArrayList<>(500);
    protected ArrayList<CarryOvers> carryOverList = new ArrayList<>(500);
    protected ArrayList<RegisteredCourses> regCoursesList = new ArrayList<>(500);
    protected ArrayList<Programme> progList = new ArrayList<>(500);
    protected ArrayList<SessionDetails> sessionList = new ArrayList<>(500);
    protected ArrayList<Course> coursesList = new ArrayList<>(500);

    protected ArrayList<College> collegeFull = new ArrayList<>();
    protected ArrayList<Department> deptFull = new ArrayList<>();
    protected ArrayList<Programme> progFull = new ArrayList<>();
    protected ArrayList<Course> coursesFull = new ArrayList<>();
    protected ArrayList<Roles> rolesFull = new ArrayList<>();
    protected ArrayList<Staff> staffFull = new ArrayList<>();

    /**
     * Creates new form ServerMainFrame
     */
    public AbstractServerFrame() {
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
     * Shows a dialog to display message
     *
     * @param message message to be displayed
     * @param title title of the dialog
     */
    protected void displayMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Shows a dialog to display error message
     *
     * @param message message to be displayed
     * @param title title of the dialog
     */
    protected final void displayErrorMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Used to validate that no number is entered in the text field
     *
     * @param txt textfield to validate
     * @return
     */
    protected boolean validateTextLetter(JTextField txt) {
        String regex = "^[a-zA-Z ]+$";
        boolean match = Pattern.matches(regex, txt.getText().trim());
        return match;
    }
    
    protected boolean validateTextAlphaNumeric(JTextField txt) {
        String regex = "^[a-zA-Z0-9 ]+$";

        boolean match = Pattern.matches(regex, txt.getText().trim());
        return match;
    }

    /**
     * Used to validate that no letter is entered in the text field
     *
     * @param txt textfield to validate
     * @return true/false if only numbers are found
     */
    protected boolean validateTextNumber(JTextField txt) {
        String regex = "^[0-9]+$";

        boolean match = Pattern.matches(regex, txt.getText().trim());
        return match;
    }

    protected boolean validateTextDecimal(JTextField txt) {
        String regex = "^[0-9.]+$";

        boolean match = Pattern.matches(regex, txt.getText().trim());
        return match;
    }
    
    protected boolean validateTextCurrency(String txt) {
        String regex = "^[0-9.-]+$";

        boolean match = Pattern.matches(regex, txt.trim());
        return match;
    }

    /**
     * When the college combo changes value initialize the dept combo with
     * different values
     *
     * @param cmbCollege
     * @param cmbDept
     * @param listFull
     */
    protected void onChangeSelectedCollege(JComboBox<String> cmbCollege, JComboBox<String> cmbDept, boolean listFull) {
        String selected = cmbCollege.getSelectedItem().toString();

        College coll = listFull ? collegeFull.stream().filter(college -> college.toString().trim().equalsIgnoreCase(selected.trim())).findFirst().get()
                : collegeList.stream().filter(college -> college.toString().equalsIgnoreCase(selected.trim())).findFirst().get();

        //Depts
        if (coll != null) {
            System.out.println("College is not null");
            DefaultComboBoxModel model = (DefaultComboBoxModel) cmbDept.getModel();
            model.removeAllElements();

            if (listFull) {
                System.out.println("Using deptFull");
                for (Department dept : deptFull) {
                    if (dept.getCollegeId() == coll.getCollegeId()) {
                        model.addElement(dept.toString());
                    }
                }
            } else {
                System.out.println("Using deptList");
                for (Department dept : deptList) {
                    if (dept.getCollegeId() == coll.getCollegeId()) {
                        model.addElement(dept.toString());
                    }
                }
            }

            cmbDept.setModel(model);
            System.out.println("Model size: " + model.getSize());
            if (model.getSize() > 0) {
                cmbDept.setSelectedIndex(0);
            }
        } else {
            displayErrorMessage("No college found. There must be at least one college", "Error in finding colleges from db");
        }
    }

    /**
     * When the dept combo changes value initialize the programme combo with
     * different values
     *
     * @param cmbDept
     * @param cmbProgramme
     * @param listFull
     */
    protected void onChangeSelectedDept(JComboBox<String> cmbDept, JComboBox<String> cmbProgramme, boolean listFull) {
        if (cmbDept.getSelectedItem() != null) {
            String selected = cmbDept.getSelectedItem().toString();

            Department deptLocal = listFull ? deptFull.stream().filter(dept -> dept.toString().equalsIgnoreCase(selected.trim())).findFirst().orElse(null)
                    : deptList.stream().filter(dept -> dept.toString().equalsIgnoreCase(selected.trim())).findFirst().get();

            //Depts
            DefaultComboBoxModel model = (DefaultComboBoxModel) cmbProgramme.getModel();
            model.removeAllElements();

            if (listFull) {
                progFull.stream().filter((prog) -> (prog.getDeptId() == deptLocal.getDeptId())).forEach((prog) -> {
                    model.addElement(prog.toString());
                });
            } else {
                progList.stream().filter((prog) -> (prog.getDeptId() == deptLocal.getDeptId())).forEach((prog) -> {
                    model.addElement(prog.toString());
                });
            }

            cmbProgramme.setModel(model);
            if (model.getSize() > 0) {
                cmbProgramme.setSelectedIndex(0);
            }
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
     * @param component panel to be added to the new dialog
     * @param size
     * @param title
     * @return a new dialog
     */
    protected JDialog createDialog(JComponent component, Dimension size, String title) {
        JDialog dialog = new JDialog(this);
        dialog.add(component);
        setDefaultDialogStyle(dialog, size, title);
        return dialog;
    }

    /**
     * Sets the dialog style
     *
     * @param dialog dialog to be modified
     * @param size
     * @param title
     */
    protected void setDefaultDialogStyle(JDialog dialog, Dimension size, String title) {
        dialog.setSize(size);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setTitle(title);
        dialog.setAlwaysOnTop(false);
        dialog.setAutoRequestFocus(true);
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
     * Initializing the school info
     *
     * @param server
     * @param client
     */
    protected final void initSchoolInfo(MainServer server) {
        ArrayList< ArrayList<?>> records = server.getSchoolInfo();
        System.out.println("Got data from db");

        for (ArrayList<?> record : records) {
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
                System.out.println("Found course");
                for (Object data : record) {
                    coursesFull.add((Course) data);
                }
            }
        }
    }

    /**
     * Initializes a combo with values from the arraylist
     *
     * @param cmb
     * @param data
     */
    protected void initCombo(JComboBox<String> cmb, ArrayList<?> data) {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cmb.getModel();

        if (data.size() > 0) {
            data.stream().forEach(value -> {
                model.addElement(value.toString());
            });
        }

        cmb.setModel(model);
        if (data.size() > 0) {
            cmb.setSelectedIndex(0);
        }
    }

    /**
     * Used to create a login and password for new person until the person
     * changes it
     *
     * @param firstname
     * @param lastname
     * @return
     */
    protected String getPassword(String firstname, String lastname) {
        String pass = "";
        if (firstname.length() > 0) {
            pass += firstname.charAt(0);
            pass += firstname.charAt(firstname.length() - 1);
        }

        if (lastname.length() > 0) {
            pass += lastname.charAt(0);
            pass += lastname.charAt(lastname.length() - 1);
        }

        return pass.length() > 0 ? pass : "pa$$w0rd";
    }

    /**
     * Used to create a login and password for new person until the person
     * changes it
     *
     * @param firstname
     * @param lastname
     * @return
     */
    protected String getUsername(String firstname, String lastname) {
        String user = "";
        if (firstname.length() > 0) {
            user += firstname.charAt(firstname.length() - 1);
            user += firstname.charAt(0);
        }

        if (lastname.length() > 0) {
            user += lastname.charAt(lastname.length() - 1);
            user += lastname.charAt(0);
        }

        return user.length() > 0 ? user : "Uzar_05";
    }

    protected double getUnformattedCurrencyValue(String number) {
        return Double.parseDouble(number.trim().replaceAll("[,]", ""));
    }

    protected String getFormattedCurrencyValue(double number) {
        return NumberFormat.getInstance(Locale.US).format(number);
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
     * Checks if a field has data inputed
     *
     * @param field the field to be validated
     * @return true/false if field has data
     */
    protected boolean fieldHasData(JTextField field) {
        return !(field.getText().trim().isEmpty());
    }
    
    protected boolean fieldHasCurrencyData(String field) {
        return !(field.trim().isEmpty());
    }

    /**
     * Deletes the passport from the passport folder if any error occurs in the
     * creation
     *
     * @param passportSelected
     * @param passportFile
     */
    protected void deletePictureFromPassportFolder(boolean passportSelected, String passportFile) {
        if (passportSelected) {
            try {
                Files.deleteIfExists(Paths.get(passportFile));
            } catch (IOException ex) {
                logger.logErrorNormal("Failed to delete the copy of passport picture selected", ex);
            }
        }
    }

    /**
     * Deletes the newly created login if error occurs on student creation after
     * login has been created
     *
     * @param loginID the login id to be used in the query
     * @param server
     */
    protected void deleteCreatedLogin(int loginID, MainServer server) {
        System.out.println("LoginID: " + loginID);
        String deleteQuery = String.format("Delete from login where LoginID = %d;", loginID);

        System.out.println("Login Delete Query: \n" + deleteQuery);

        if (server.sendUpdateQuery(deleteQuery)) {
            logger.logMessage("Login deleted on failed student creation");
        }
    }

    /**
     * Copies the passport picture chosen from the JFileChooser to the default
     * passport location
     *
     * @param path
     * @param passportFolder
     * @param recreatedPath
     * @return
     */
    protected Path copyPicture(Path path, String passportFolder, Path recreatedPath, int count) {
        System.out.println("Path: " + path.toString());
        try {
            Path pathOut;
            if (recreatedPath == null) {
                pathOut = Paths.get(passportFolder + "//" + path.getFileName().toString());
            } else {
                pathOut = Paths.get(passportFolder + "//" + recreatedPath.getFileName().toString());
            }

            if (Files.exists(pathOut)) {
                String pathOutString = pathOut.toAbsolutePath().toString().trim();
                int index = pathOutString.lastIndexOf(".");

                String pathOutTemp;
                if (recreatedPath == null) {
                    pathOutTemp = Paths.get(pathOutString.substring(0, index)).toString();
                } else {
                    pathOutTemp = Paths.get(pathOutString.substring(0, (index - 1))).toString();
                }

                String extension = pathOutString.substring(index + 1);

                pathOutTemp += (++count) + "." + extension;
                Path finalPath = Paths.get(pathOutTemp);
                return copyPicture(path, passportFolder, finalPath.toAbsolutePath(), count);
            } else {
                return Files.copy(path, pathOut);
            }
        } catch (IOException ex) {
            logger.logErrorWithDialog("Error in copying picture", ex);
            return null;
        } catch (Exception ex) {
            logger.logErrorWithDialog("Error in copying picture", ex);
            return null;
        }
    }

    /**
     * Initializes the JFileChooser with style and dimensions if necessary
     *
     * @param chooser
     */
    protected void initFileChooser(JFileChooser chooser) {
        chooser.setCurrentDirectory(Paths.get(System.getenv("ProgramFiles")).getParent().toAbsolutePath().toFile());
    }

    /**
     * gets the URL of the file chosen and initializes the label with the
     * picture
     *
     * @param chooser
     * @param label
     * @param passportFolder
     */
    protected String initPassport(JFileChooser chooser, JLabel label, String passportFolder) {
        //Get Url
        String chosenUrl = chooser.getSelectedFile().getAbsolutePath();
        String passportPath = copyPicture(Paths.get(chosenUrl), passportFolder, null, 0).toAbsolutePath().toString().replace("\\", "\\\\");

        //Set label icon
        ImageIcon icon = new ImageIcon(passportPath);
        setLabelImage(icon, label);
        return passportPath;
    }

    /**
     * Initialized the background details
     *
     * @param server
     * @param student
     */
    protected final void initTotalBackgroundDetails(MainServer server, boolean student) {
        rolesFull = server.getRoles(student);
        staffFull = server.getStaffs();
        initSchoolInfo(server);
    }

    /**
     * Initialized the background details
     *
     * @param server
     * @param student
     */
    protected final void initBackgroundDetailsDept(MainServer server, boolean student) {
        rolesFull = server.getRoles(student);
        staffFull = server.getStaffs();
        deptFull = server.getDepartments();
        collegeFull = server.getColleges();
    }

    /**
     * Initialized the background details
     *
     * @param server
     * @param student
     */
    protected final void initBackgroundDetailsProg(MainServer server, boolean student) {
        initSchoolInfo(server);
    }

    /**
     * Checks if there is a selected value in the combo box
     *
     * @param cmb
     * @return
     */
    protected boolean valueSelectedCombo(JComboBox<String> cmb) {
        return cmb.getSelectedIndex() > -1;
    }

    protected abstract boolean validateAllFields();

    /**
     * Displays the login details for the new user created
     *
     * @param user
     * @param pass
     */
    protected void displayDefaultPassword(String user, String pass) {
        String message = "Please ensure you change your defualt password on login\n"
                + "Username: " + user + "\n"
                + "Password: " + pass;

        displayMessage(message, "User Login Details");
    }

    /**
     * Deletes the last inserted row incase of any error that occured
     *
     * @param tableName
     * @param idColumnName
     * @param id
     * @param server
     * @return
     */
    protected boolean deleteLastInsert(String tableName, String idColumnName, MainServer server) {
        String getIDQuery = "Select Max(%s) from %s;";
        int id = (Integer) server.getQuerySingleResult(getIDQuery, idColumnName);

        String deleteQuery = String.format("Delete from %s where %s = %d;",
                tableName.trim(),
                idColumnName,
                id);
        return server.sendUpdateQuery(deleteQuery);
    }

    /**
     * Confirms if at least one record exists in the table
     *
     * @param server
     * @param tblName
     * @param alias
     * @return
     */
    protected final boolean recordExists(MainServer server, String tblName, String alias) {
        String existQuery = String.format("select (Select count(*) from %s) %s from dual;",
                tblName, alias);

        Object[] data = server.getResultSet(existQuery);
        try (Statement st = (Statement) data[0];
                ResultSet rs = (ResultSet) data[1]) {
            if (rs != null) {
                if (rs.next()) {
                    if (rs.getInt(alias) > 0) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (SQLException sq) {
            logger.logErrorNormal("Error in getting data from db for validation", sq);
            return false;
        }
    }

    protected void setErrorBorder(JComponent comp, boolean error) {
        if(error) comp.setBorder(outer);
        else comp.setBorder(normalBorder);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
