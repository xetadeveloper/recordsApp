package frontend.updateframes;

import backend.MainServer;
import java.util.ArrayList;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import pojo.College;
import pojo.Department;
import pojo.Roles;
import pojo.Staff;

/**
 *
 * @author Fego
 */
public class UpdateStaffFrame extends AbstractUpdateFrame {

    private MainServer server;
    private String newPassportFile, oldPassportFile;
    private String passportFolder;
    private boolean passportChanged = false;
    private final String[] columnNames = new String[]{"StaffID", "Last Name", "First Name", "Staff Role"};

    //POJO Fields
    private Roles role;
    private Department deptGlobal;

    /**
     * Creates new form UpdateStaffFrame
     */
    private UpdateStaffFrame() {
        initComponents();
    }

    public UpdateStaffFrame(MainServer server, Staff staff) {
        this();
        this.server = server;
        this.staff = staff;
        initUpdateTotalBackgroundDetails(server, false);
        passportFolder = Preferences.userRoot().node(MainServer.REGISTRYNAME).get("passportUrl", null);
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
        btnUpdate = new javax.swing.JButton();
        btnAttach = new javax.swing.JButton();
        cmbRole = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();

        chooserPicture.setDialogTitle("Choose Passport Picture");
        chooserPicture.setFileSelectionMode(javax.swing.JFileChooser.FILES_AND_DIRECTORIES);
        chooserPicture.setOpaque(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("All Staffs");
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

        btnUpdate.setBackground(new java.awt.Color(204, 204, 204));
        btnUpdate.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(204, 204, 204));
        btnUpdate.setText("UPDATE STAFF");
        btnUpdate.setToolTipText("Add course");
        btnUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdate.setFocusPainted(false);
        btnUpdate.setOpaque(false);
        btnUpdate.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnAttach.setBackground(new java.awt.Color(204, 204, 204));
        btnAttach.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        btnAttach.setForeground(new java.awt.Color(204, 204, 204));
        btnAttach.setText("UPDATE PASSPORT");
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
                    .addComponent(btnUpdate)
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
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (confirmAction("Proceed With Staff Update?")) {
            System.out.println("Staff update confirmed");
            if (validateAllFields()) {
                int loginID = staff.getLoginId();
                Roles hodRole = rolesFull.stream().filter(role -> role.getRoleName().equalsIgnoreCase("hod")).findFirst().get();
                Department staffDept = deptFull.stream().filter(dept -> dept.getDeptId() == staff.getDeptId()).findFirst().get();

                try {
                    String staffQuery = String.format("Update Staffs set FirstName = '%s', LastName = '%s', Staff_RoleID = %d, Staff_DeptID = %d, Staff_LoginID = %d, "
                            + "StaffPassport = '%s' where StaffID = %d;",
                            txtFirstName.getText().trim(),
                            txtLastName.getText().trim(),
                            role.getRoleId(),
                            deptGlobal.getDeptId(),
                            loginID,
                            passportChanged ? newPassportFile : oldPassportFile,
                            staff.getStaffId());

                    System.out.println("Staff query created: \n" + staffQuery);

                    boolean staffUpdated = server.sendUpdateQuery(staffQuery);

                    //refresh the main table
                    //send query to database to update
                    if (staffUpdated) {
                        if (staff.getRoleId() == hodRole.getRoleId() && role.getRoleId() != hodRole.getRoleId()) {
                            //update department 
                            String deptQuery = String.format("Update Departments set DeptHead_StaffID = null where DeptID = %d;", staff.getDeptId());

                            if (server.sendUpdateQuery(deptQuery)) {
                                displayMessage("Staff  has been updated", "Staff Update Successful");
                            } else {
                                displayErrorMessage("Department head was not updated. Please update deparment head manually and contact maintenance team", "Error in Updating Department Head");
                            }
                        }
                        displayMessage("Staff  has been updated", "Staff Update Successful");
                        dispose();
                    } else {
                        deletePictureFromPassportFolder(passportChanged, newPassportFile);
                        displayErrorMessage("Error in updating staff. Contact admin", "Update Failed");
                    }
                } catch (Exception ex) {
                    deletePictureFromPassportFolder(passportChanged, newPassportFile);
                    logger.logErrorWithDialog("Error in updating staff", ex);
                    System.out.println("Error Logged");
                }
            } else {
                displayErrorMessage("A wrong value has been entered into one of the fields. Please check through", "Update Failed");
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAttachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAttachActionPerformed
        initFileChooser(chooserPicture);
        int open = chooserPicture.showOpenDialog(this);

        if (open == JFileChooser.APPROVE_OPTION) {
            if (passportChanged) {
                deletePictureFromPassportFolder(passportChanged, newPassportFile);
                passportChanged = true;
            }

            newPassportFile = initPassport(chooserPicture, lblPassport, passportFolder);
            passportChanged = true;
        } else {
            passportChanged = false;
        }
    }//GEN-LAST:event_btnAttachActionPerformed

    private void cmbRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRoleActionPerformed
        onChangeSelectedRole(cmbRole);
    }//GEN-LAST:event_cmbRoleActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (confirmAction("Exit staff update")) {
            dispose();
        }
    }//GEN-LAST:event_formWindowClosing

    //Custom Methods
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
    @Override
    protected final void initPanel() {
        oldPassportFile = staff.getPassportUrlString();
        txtFirstName.setText(staff.getFirstName());
        txtLastName.setText(staff.getLastName());
        setLabelImage(staff.getPassport(), lblPassport);

        ArrayList<College> collegeListFull = new ArrayList<>(collegeFull.stream().filter(col -> col.getCollegeDeptNumber() > 0).collect(Collectors.toList()));
        initCombo(cmbCollege, collegeListFull);
        initCombo(cmbRole, rolesFull);

        Department stfDept = deptFull.stream().filter(dep -> dep.getDeptId() == staff.getDeptId()).findFirst().get();
        College stfCol = collegeFull.stream().filter(col -> col.getCollegeId() == stfDept.getCollegeId()).findFirst().get();

        //set combo default values
        int count = 0;
        for (College col : collegeFull) {
            if (col.getCollegeId() == stfCol.getCollegeId()) {
                cmbCollege.setSelectedIndex(count);
            } else {
                ++count;
            }
        }

        count = 0;
        for (Department dep : deptFull) {
            if (dep.getDeptName().equalsIgnoreCase(stfDept.getDeptName())) {
                cmbDept.setSelectedItem(dep.getDeptName());
            } else {
                ++count;
            }
        }

        count = 0;
        for (Roles role : rolesFull) {
            if (role.getRoleId() == staff.getRoleId()) {
                cmbRole.setSelectedIndex(count);
            } else {
                ++count;
            }
        }
    }

    protected void onChangeSelectedDept(JComboBox<String> cmbDept) {
        //sets the department
        if (cmbDept.getSelectedItem() != null) {
            String selected = cmbDept.getSelectedItem().toString().trim();
            deptGlobal = deptFull.stream().filter(dept -> dept.toString().trim().equalsIgnoreCase(selected.trim())).findFirst().orElse(null);
            cmbDept.setSelectedIndex(0);
        } else {
            System.out.println("Selected Item is null");
        }
    }

    /**
     * Initializes the table with the students data
     *
     * @param table table to be initialized
     */
    protected void initTableRecords(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setColumnIdentifiers(columnNames);

        staffList.forEach(staff -> model.addRow(new Object[]{
            staff.getStaffId(),
            staff.getFirstName(),
            staff.getLastName(),
            rolesFull.stream().filter(role -> role.getRoleId() == staff.getRoleId()).
            findFirst().get().getRoleName()
        }));

        table.setModel(model);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAttach;
    private javax.swing.JButton btnUpdate;
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
