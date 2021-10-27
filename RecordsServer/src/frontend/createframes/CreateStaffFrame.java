package frontend.createframes;

import backend.MainServer;
import frontend.AbstractServerFrame;
import java.util.ArrayList;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import pojo.College;
import pojo.Department;
import pojo.Roles;

/**
 *
 * @author Fego
 */
public class CreateStaffFrame extends AbstractServerFrame {

    private MainServer server;
    private String passportFile;
    private String passportFolder;
    private boolean passportSelected = false;
    private String username, password;

    //POJO Fields
    private Roles role;
    private Department deptGlobal;

    /**
     * Creates new form CreateStaffFrame
     */
    private CreateStaffFrame() {
        initComponents();
    }

    public CreateStaffFrame(MainServer server) {
        this();
        this.server = server;
        passportFolder = Preferences.userRoot().node(MainServer.REGISTRYNAME).get("passportUrl", null);
        initTotalBackgroundDetails(server, false);
        initPanel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chooserPicture = new javax.swing.JFileChooser();
        panelInfo = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        lblReportHeader7 = new javax.swing.JLabel();
        lblPassport = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        cmbCollege = new javax.swing.JComboBox<>();
        cmbDept = new javax.swing.JComboBox<>();
        btnCreate = new javax.swing.JButton();
        btnAttach = new javax.swing.JButton();
        cmbRole = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();

        chooserPicture.setDialogTitle("Choose Passport Picture");
        chooserPicture.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);
        chooserPicture.setOpaque(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Create New Staff");
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        panelInfo.setBackground(new java.awt.Color(84, 83, 83));

        jLabel35.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(204, 204, 204));
        jLabel35.setText("FIRST NAME");

        jLabel37.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(204, 204, 204));
        jLabel37.setText("COLLEGE");

        jLabel39.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(204, 204, 204));
        jLabel39.setText("DEPARTMENT");

        jLabel41.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(204, 204, 204));
        jLabel41.setText("LAST NAME");

        lblReportHeader7.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        lblReportHeader7.setForeground(new java.awt.Color(204, 204, 204));
        lblReportHeader7.setText("STAFF INFORMATION");

        lblPassport.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        lblPassport.setOpaque(true);
        lblPassport.setPreferredSize(new java.awt.Dimension(147, 140));

        txtFirstName.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        txtLastName.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        cmbCollege.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        cmbCollege.setName("CollegeCombo"); // NOI18N
        cmbCollege.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCollegeActionPerformed(evt);
            }
        });

        cmbDept.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        cmbDept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDeptActionPerformed(evt);
            }
        });

        btnCreate.setBackground(new java.awt.Color(204, 204, 204));
        btnCreate.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        btnCreate.setForeground(new java.awt.Color(204, 204, 204));
        btnCreate.setText("CREATE STAFF");
        btnCreate.setToolTipText("Add course");
        btnCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCreate.setFocusPainted(false);
        btnCreate.setOpaque(false);
        btnCreate.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnAttach.setBackground(new java.awt.Color(204, 204, 204));
        btnAttach.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        btnAttach.setForeground(new java.awt.Color(204, 204, 204));
        btnAttach.setText("ATTACH PASSPORT");
        btnAttach.setToolTipText("Add course");
        btnAttach.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAttach.setFocusPainted(false);
        btnAttach.setOpaque(false);
        btnAttach.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAttach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAttachActionPerformed(evt);
            }
        });

        cmbRole.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        cmbRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRoleActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(204, 204, 204));
        jLabel36.setText("STAFF ROLE");

        javax.swing.GroupLayout panelInfoLayout = new javax.swing.GroupLayout(panelInfo);
        panelInfo.setLayout(panelInfoLayout);
        panelInfoLayout.setHorizontalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInfoLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCreate)
                    .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblReportHeader7)
                        .addGroup(panelInfoLayout.createSequentialGroup()
                            .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelInfoLayout.createSequentialGroup()
                                    .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblPassport, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnAttach))
                                    .addGap(18, 18, 18)
                                    .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel37)
                                        .addComponent(jLabel39)))
                                .addGroup(panelInfoLayout.createSequentialGroup()
                                    .addGap(180, 180, 180)
                                    .addComponent(jLabel41)))
                            .addGap(18, 18, 18)
                            .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbDept, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbCollege, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(52, 52, 52)
                            .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel36)
                                .addComponent(jLabel35))
                            .addGap(18, 18, 18)
                            .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cmbRole, 0, 282, Short.MAX_VALUE)
                                .addComponent(txtFirstName)))))
                .addGap(63, 63, 63))
        );
        panelInfoLayout.setVerticalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInfoLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblReportHeader7)
                .addGap(18, 18, 18)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel41)
                            .addComponent(jLabel35)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37)
                            .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbCollege, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbRole, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel36)))
                        .addGap(18, 18, 18)
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbDept, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39)))
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addComponent(lblPassport, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAttach, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(59, 59, 59)
                .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmbCollegeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCollegeActionPerformed
        onChangeSelectedCollege(cmbCollege, cmbDept, true);
        onChangeSelectedDept(cmbDept);
    }//GEN-LAST:event_cmbCollegeActionPerformed

    private void cmbDeptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDeptActionPerformed
        onChangeSelectedDept(cmbDept);
    }//GEN-LAST:event_cmbDeptActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        if (confirmAction("Proceed With Staff Creation?")) {
            System.out.println("Staff creation confirmed");
            if (validateAllFields()) {
                System.out.println("Fields validated");

                String loginQuery = String.format("insert into login (username, password,roleid) values\n"
                        + "('%s', '%s',%d);",
                        username = getUsername(txtFirstName.getText().trim(), txtLastName.getText().trim()),
                        password = getPassword(txtFirstName.getText().trim(), txtLastName.getText().trim()),
                        role.getRoleId());

                System.out.println("Login query created: \n" + loginQuery);

                boolean loginInserted = server.sendUpdateQuery(loginQuery);
                System.out.println("Login done: " + loginInserted);

                if (loginInserted) {
                    //get login id
                    String loginIDQuery = "Select LoginID from login where LoginID = (Select Max(LoginID) from login);";

                    int loginID = server.getLoginID(loginIDQuery);
                    System.out.println("LoginID gotten from db: " + loginID);

                    try {
                        System.out.println("Dept Selected Null: " + (deptGlobal == null));

                        String staffQuery = String.format("Insert into Staffs (FirstName, LastName, Staff_RoleID, Staff_DeptID, Staff_LoginID, StaffPassport) values"
                                + "('%s', '%s', %d, %d, %d, '%s');",
                                txtFirstName.getText().trim(),
                                txtLastName.getText().trim(),
                                role.getRoleId(),
                                deptGlobal.getDeptId(),
                                loginID,
                                passportSelected ? "N/A" : passportFile);

                        System.out.println("Staff query created: \n" + staffQuery);

                        boolean staffInserted = server.sendUpdateQuery(staffQuery);

                        //refresh the main table
                        //send query to database to update
                        if (staffInserted) {
                            String deptQuery = String.format("Update Departments set Dept_StaffNumber = %d where DeptID = %d;",
                                    (deptGlobal.getStaffNumber() + 1),
                                    deptGlobal.getDeptId());
                            boolean progUpdated = server.sendUpdateQuery(deptQuery);
                            if (progUpdated) {
                                displayDefaultPassword(username, password);
                                displayMessage("Staff  has been created", "Staff Creation Successful");
                                dispose();
                            } else {
                                deletePictureFromPassportFolder(passportSelected, passportFile);
                                deleteCreatedLogin(loginID, server);
                                displayErrorMessage("Error in updating department count. Contact admin", "Update Failed");
                            }
                        } else {
                            deletePictureFromPassportFolder(passportSelected, passportFile);
                            deleteCreatedLogin(loginID, server);
                            displayErrorMessage("Error in creating student. Contact admin", "Update Failed");
                        }
                    } catch (Exception ex) {
                        deletePictureFromPassportFolder(passportSelected, passportFile);
                        deleteCreatedLogin(loginID, server);
                        logger.logErrorWithDialog("Error in creating login", ex);
                        System.out.println("Error Logged");
                    }
                } else {
                    deletePictureFromPassportFolder(passportSelected, passportFile);
                    displayErrorMessage("Error in creating login", "Error in update");
                }
            } else {
                displayErrorMessage("A wrong value has been entered into one of the fields. Please check through", "Update Failed");
            }
        }
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnAttachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAttachActionPerformed
        initFileChooser(chooserPicture);
        int open = chooserPicture.showOpenDialog(this);
        System.out.println("Chooser shown");

        if (open == JFileChooser.APPROVE_OPTION) {
            if (passportSelected) {
                deletePictureFromPassportFolder(passportSelected, passportFile);
                passportSelected = true;
            }

            passportFile = initPassport(chooserPicture, lblPassport, passportFolder);
            passportSelected = true;
        } else {
            passportSelected = false;
        }
    }//GEN-LAST:event_btnAttachActionPerformed

    private void cmbRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRoleActionPerformed
        onChangeSelectedRole(cmbRole);
    }//GEN-LAST:event_cmbRoleActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (confirmAction("End staff creation?")) {
            if (passportSelected) {
                deletePictureFromPassportFolder(passportSelected, passportFile);
            }
            dispose();
        }
    }//GEN-LAST:event_formWindowClosing

    //Custom Methods
    /**
     * Validates all necessary filled fields
     *
     * @return
     */
    @Override
    protected boolean validateAllFields() {
        boolean validData = true;

        if (!(fieldHasData(txtFirstName) && validateTextLetter(txtFirstName))) {
            setErrorBorder(txtFirstName, true);
            validData = false;
        } else {
            setErrorBorder(txtFirstName, false);
        }
        if (!(fieldHasData(txtLastName) && validateTextLetter(txtLastName))) {
            setErrorBorder(txtLastName, true);
            validData = false;
        } else {
            setErrorBorder(txtLastName, false);
        }
        if (!valueSelectedCombo(cmbRole)) {
            setErrorBorder(cmbRole, true);
            validData = false;
        } else {
            setErrorBorder(cmbRole, false);
        }
        if (!valueSelectedCombo(cmbDept)) {
            setErrorBorder(cmbDept, true);
            validData = false;
        } else {
            setErrorBorder(cmbDept, false);
        }

        return validData;
    }

    protected void onChangeSelectedRole(JComboBox<String> cmbRole) {
        //sets the role
        if (cmbRole.getSelectedItem() != null) {
            String selected = cmbRole.getSelectedItem().toString().trim();
            Roles roleMain = rolesFull.stream().filter(role -> role.toString().trim().equalsIgnoreCase(selected.trim())).findFirst().orElse(null);
            role = roleMain;
        }

    }

    /**
     * Initializes the panel with any pre data before it is shown
     */
    private void initPanel() {
        ArrayList<College> collegeListFull = new ArrayList<>(collegeFull.stream().filter(col -> col.getCollegeDeptNumber() > 0).collect(Collectors.toList()));
        initCombo(cmbCollege, collegeListFull);
        initCombo(cmbRole, rolesFull);
    }

    protected void onChangeSelectedDept(JComboBox<String> cmbDept) {
        //sets the department
        if (cmbDept.getSelectedItem() != null) {
            String selected = cmbDept.getSelectedItem().toString().trim();
            System.out.println("Selected Dept: " + selected);
            deptGlobal = deptFull.stream().filter(dept -> dept.toString().trim().equalsIgnoreCase(selected.trim())).findFirst().orElse(null);
            System.out.println("Selectd Global dept: " + deptGlobal);
            cmbDept.setSelectedIndex(0);
        } else {
            System.out.println("Selected Item is null");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAttach;
    private javax.swing.JButton btnCreate;
    private javax.swing.JFileChooser chooserPicture;
    private javax.swing.JComboBox<String> cmbCollege;
    private javax.swing.JComboBox<String> cmbDept;
    private javax.swing.JComboBox<String> cmbRole;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel lblPassport;
    private javax.swing.JLabel lblReportHeader7;
    private javax.swing.JPanel panelInfo;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    // End of variables declaration//GEN-END:variables
}
