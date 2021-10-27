package frontend.createframes;

import backend.MainServer;
import frontend.AbstractServerFrame;
import javax.swing.JComboBox;
import pojo.Programme;

/**
 *
 * @author Fego
 */
public class CreateCourseFrame extends AbstractServerFrame {

    private Programme progGlobal;

    /**
     * Creates new form CreateCourseFrame
     */
    private CreateCourseFrame() {
        initComponents();
    }

    public CreateCourseFrame(MainServer server) {
        this();
        this.server = server;
        initBackgroundDetailsProg(server, false);
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
        btnCreate = new javax.swing.JButton();
        cmbStatus = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        txtCourseName = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        txtCreditUnit = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        txtLevel = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Create New Course");
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

        btnCreate.setBackground(new java.awt.Color(204, 204, 204));
        btnCreate.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        btnCreate.setForeground(new java.awt.Color(204, 204, 204));
        btnCreate.setText("CREATE COURSE");
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
                                .addComponent(btnCreate))
                            .addGroup(panelInfoLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel37)
                                    .addComponent(jLabel36)
                                    .addComponent(jLabel41)
                                    .addComponent(jLabel42)
                                    .addComponent(jLabel43)
                                    .addComponent(jLabel44))
                                .addGap(47, 47, 47)
                                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbStatus, 0, 282, Short.MAX_VALUE)
                                    .addComponent(txtCourseCode)
                                    .addComponent(cmbProgramme, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCourseName)
                                    .addComponent(txtCreditUnit)
                                    .addComponent(txtLevel))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCreditUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43))
                .addGap(18, 18, 18)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44))
                .addGap(18, 18, 18)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(cmbProgramme, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        if (confirmAction("Proceed With Course Creation?")) {
            System.out.println("Course creation confirmed");
            if (validateAllFields()) {

                String courseQuery = String.format("Insert into Courses (CourseCode, CourseName, CourseStatus, CreditUnits, Course_Level, Course_ProgrammeID) values \n"
                        + "('%s', '%s', '%s', %d, %d, %d);",
                        txtCourseCode.getText().trim(),
                        txtCourseName.getText().trim(),
                        cmbStatus.getSelectedItem().toString().trim(),
                        Integer.parseInt(txtCreditUnit.getText().trim()),
                        Integer.parseInt(txtLevel.getText().trim()),
                        progGlobal.getProgrammeId());

                boolean courseInserted = server.sendUpdateQuery(courseQuery);

                if (courseInserted) {
                    String progQuery = String.format("Update Programmes set Programme_CourseNumber = %d where ProgrammeID = %d;",
                            (progGlobal.getProgCourseNumber() + 1),
                            progGlobal.getProgrammeId());

                    if (server.sendUpdateQuery(progQuery)) {
                        displayMessage("Course  has been created", "Course Creation Successful");
                    } else {
                        deleteLastInsert("Courses", "CourseCode", server);
                        displayErrorMessage("Error in updating college department count. Contact admin", "Update Failed");
                    }

                    dispose();
                } else {
                    displayErrorMessage("Error in creating course. Contact admin", "Update Failed");
                    dispose();
                }

            } else {
                displayErrorMessage("A wrong value has been entered into one of the fields. Please check through", "Update Failed");
            }
        }
    }//GEN-LAST:event_btnCreateActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (confirmAction("End course creation?")) {
            dispose();
        }
    }//GEN-LAST:event_formWindowClosing

    //Custom Methods
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

    private void initPanel() {
        cmbStatus.addItem("Compulsory");
        cmbStatus.addItem("Elective");
        initCombo(cmbProgramme, progFull);
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
            Programme progMain = progFull.stream().filter(prog -> prog.toString().trim().equalsIgnoreCase(selected.trim())).findFirst().orElse(null);
            progGlobal = progMain;
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JComboBox<String> cmbProgramme;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
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