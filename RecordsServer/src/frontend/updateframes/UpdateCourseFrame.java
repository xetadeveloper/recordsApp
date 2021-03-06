package frontend.updateframes;

import backend.MainServer;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.swing.JComboBox;
import pojo.College;
import pojo.Course;
import pojo.Department;
import pojo.Programme;

/**
 *
 * @author Fego
 */
public class UpdateCourseFrame extends AbstractUpdateFrame {

    private Programme progGlobal;

    /**
     * Creates new form UpdateCourseFrame
     */
    private UpdateCourseFrame() {
        initComponents();
    }

    public UpdateCourseFrame(MainServer server, Course course) {
        this();
        this.server = server;
        this.course = course;
        initData(getOtherData(server));
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

        panelInfo = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        lblReportHeader7 = new javax.swing.JLabel();
        txtCourseCode = new javax.swing.JTextField();
        cmbProgramme = new javax.swing.JComboBox<>();
        btnUpdate = new javax.swing.JButton();
        cmbStatus = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        txtCourseName = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        txtCreditUnit = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        txtLevel = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        cmbCollege = new javax.swing.JComboBox<>();
        cmbDept = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Update Course");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        panelInfo.setBackground(new java.awt.Color(84, 83, 83));

        jLabel37.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(204, 204, 204));
        jLabel37.setText("COURSE'S PROGRAMME");

        jLabel41.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(204, 204, 204));
        jLabel41.setText("COURSE CODE");

        lblReportHeader7.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        lblReportHeader7.setForeground(new java.awt.Color(204, 204, 204));
        lblReportHeader7.setText("COURSE INFORMATION");

        txtCourseCode.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        cmbProgramme.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        cmbProgramme.setName("CollegeCombo"); // NOI18N
        cmbProgramme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProgrammeActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(204, 204, 204));
        btnUpdate.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(204, 204, 204));
        btnUpdate.setText("UPDATE COURSE");
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

        cmbStatus.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N

        jLabel36.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(204, 204, 204));
        jLabel36.setText("COURSE STATUS");

        txtCourseName.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        jLabel42.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(204, 204, 204));
        jLabel42.setText("COURSE NAME");

        txtCreditUnit.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        jLabel43.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(204, 204, 204));
        jLabel43.setText("CREDIT UNITS");

        txtLevel.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        jLabel44.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(204, 204, 204));
        jLabel44.setText("LEVEL");

        cmbCollege.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
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

        jLabel38.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(204, 204, 204));
        jLabel38.setText("COURSE'S COLLEGE");

        jLabel39.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(204, 204, 204));
        jLabel39.setText("COURSE'S DEPARTMENT");

        javax.swing.GroupLayout panelInfoLayout = new javax.swing.GroupLayout(panelInfo);
        panelInfo.setLayout(panelInfoLayout);
        panelInfoLayout.setHorizontalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInfoLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addComponent(lblReportHeader7)
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInfoLayout.createSequentialGroup()
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelInfoLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnUpdate))
                            .addGroup(panelInfoLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel37)
                                    .addComponent(jLabel36)
                                    .addComponent(jLabel41)
                                    .addComponent(jLabel42)
                                    .addComponent(jLabel43)
                                    .addComponent(jLabel44)
                                    .addComponent(jLabel38)
                                    .addComponent(jLabel39))
                                .addGap(47, 47, 47)
                                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbDept, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbCollege, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cmbStatus, 0, 282, Short.MAX_VALUE)
                                        .addComponent(txtCourseCode)
                                        .addComponent(cmbProgramme, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtCourseName)
                                        .addComponent(txtCreditUnit)
                                        .addComponent(txtLevel)))))
                        .addGap(64, 64, 64))))
        );
        panelInfoLayout.setVerticalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInfoLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblReportHeader7)
                .addGap(26, 26, 26)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCourseCode, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41))
                .addGap(18, 18, 18)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCourseName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42))
                .addGap(18, 18, 18)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCreditUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43))
                .addGap(18, 18, 18)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44))
                .addGap(18, 18, 18)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbCollege, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38))
                .addGap(18, 18, 18)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbDept, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39))
                .addGap(18, 18, 18)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(cmbProgramme, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmbProgrammeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProgrammeActionPerformed
        onChangeSelectedProg(cmbProgramme);
    }//GEN-LAST:event_cmbProgrammeActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (confirmAction("Proceed With Course Update?")) {
            System.out.println("Course update confirmed");
            if (validateAllFields()) {
                String courseQuery = String.format("Update Courses set CourseCode = '%s', CourseName = '%s', CourseStatus = '%s', CreditUnits = %d, Course_Level = %d, "
                        + " Course_ProgrammeID = %d where CourseCode = '%s';",
                        txtCourseCode.getText().trim(),
                        txtCourseName.getText().trim(),
                        cmbStatus.getSelectedItem().toString().trim(),
                        Integer.parseInt(txtCreditUnit.getText().trim()),
                        Integer.parseInt(txtLevel.getText().trim()),
                        progGlobal.getProgrammeId(),
                        course.getCourseCode().trim());

                boolean courseInserted = server.sendUpdateQuery(courseQuery);

                if (courseInserted) {
                    displayMessage("Course  has been Updated", "Course Update Successful");
                    dispose();
                } else {
                    displayErrorMessage("Error in updating course. Contact admin", "Update Failed");
                    dispose();
                }

            } else {
                displayErrorMessage("A wrong value has been entered into one of the fields. Please check through", "Update Failed");
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (confirmAction("Exit Course Update")) {
            dispose();
        }
    }//GEN-LAST:event_formWindowClosing

    private void cmbCollegeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCollegeActionPerformed
        onChangeSelectedCollege(cmbCollege, cmbDept, false);
        onChangeSelectedDept(cmbDept, cmbProgramme, false);
    }//GEN-LAST:event_cmbCollegeActionPerformed

    private void cmbDeptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDeptActionPerformed
        onChangeSelectedDept(cmbDept, cmbProgramme, false);
    }//GEN-LAST:event_cmbDeptActionPerformed

    @Override
    protected final void initPanel() {
        Programme coProg = progList.stream().filter(prog -> prog.getProgrammeId() == this.course.getProgrammeId()).findFirst().get();
        Department coDept = deptList.stream().filter(dep -> dep.getDeptId() == coProg.getDeptId()).findFirst().get();
        College coCol = collegeList.stream().filter(col -> col.getCollegeId() == coDept.getCollegeId()).findFirst().get();

        txtCourseCode.setText(course.getCourseCode());
        txtCourseName.setText(course.getCourseName());
        txtCreditUnit.setText(String.valueOf(course.getCreditUnits()));
        txtLevel.setText(String.valueOf(course.getLevel()));

        System.out.println("Prog List size: " + progList.size());
        //initCombo(cmbProgramme, progList);
        cmbStatus.addItem("Compulsory");
        cmbStatus.addItem("Elective");

        //set defaullt for combos
        ArrayList<College> collegeListFull = new ArrayList<>(collegeList.stream().filter(col -> col.getCollegeDeptNumber() > 0).collect(Collectors.toList()));
        initCombo(cmbCollege, collegeListFull);

        int count = 0;
        for (College col : collegeFull) {
            if (col.getCollegeId() == coCol.getCollegeId()) {
                cmbCollege.setSelectedIndex(count);
            } else {
                ++count;
            }
        }

        count = 0;
        for (Department dep : deptFull) {
            if (dep.getDeptName().equalsIgnoreCase(coDept.getDeptName())) {
                cmbDept.setSelectedItem(dep.getDeptName());
            } else {
                ++count;
            }
        }

        for (Programme dep : progFull) {
            if (dep.getProgrammeName().equalsIgnoreCase(coProg.getProgrammeName())) {
                cmbProgramme.setSelectedItem(dep.getProgrammeName());
            } else {
                ++count;
            }
        }

        cmbStatus.setSelectedItem(course.getCourseStatus());
    }

    @Override
    protected boolean validateAllFields() {
        boolean validData = true;

        if (!(fieldHasData(txtCourseName) && validateTextLetter(txtCourseName))) {
            setErrorBorder(txtCourseName, true);
            validData = false;
        } else {
            setErrorBorder(txtCourseName, false);
        }
        if (!(fieldHasData(txtCourseCode) && validateTextAlphaNumeric(txtCourseCode))) {
            setErrorBorder(txtCourseCode, true);
            validData = false;
        } else {
            setErrorBorder(txtCourseCode, false);
        }
        if (!(fieldHasData(txtCreditUnit) && validateTextNumber(txtCreditUnit))) {
            setErrorBorder(txtCreditUnit, true);
            validData = false;
        } else {
            setErrorBorder(txtCreditUnit, false);
        }
        if (!(fieldHasData(txtLevel) && validateTextNumber(txtLevel))) {
            setErrorBorder(txtLevel, true);
            validData = false;
        } else {
            setErrorBorder(txtLevel, false);
        }

        return validData;
    }

    /**
     * When the programme combo changes value initialize the global programme
     *
     * @param cmbDept
     * @param cmbProgramme
     */
    private void onChangeSelectedProg(JComboBox<String> cmbProg) {
        if (cmbProg.getSelectedItem() != null) {
            String selected = cmbProg.getSelectedItem().toString();
            Programme progMain = progList.stream().filter(prog -> prog.toString().trim().equalsIgnoreCase(selected.trim())).findFirst().orElse(null);
            progGlobal = progMain;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbCollege;
    private javax.swing.JComboBox<String> cmbDept;
    private javax.swing.JComboBox<String> cmbProgramme;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel lblReportHeader7;
    private javax.swing.JPanel panelInfo;
    private javax.swing.JTextField txtCourseCode;
    private javax.swing.JTextField txtCourseName;
    private javax.swing.JTextField txtCreditUnit;
    private javax.swing.JTextField txtLevel;
    // End of variables declaration//GEN-END:variables
}
