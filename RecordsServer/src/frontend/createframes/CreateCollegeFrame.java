package frontend.createframes;

import backend.MainServer;
import frontend.AbstractServerFrame;

/**
 *
 * @author Fego
 */
public class CreateCollegeFrame extends AbstractServerFrame {

    /**
     * Creates new form CreateCollegeFrame
     */
    private CreateCollegeFrame() {
        initComponents();
    }

    public CreateCollegeFrame(MainServer server) {
        this();
        this.server = server;
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
        jLabel41 = new javax.swing.JLabel();
        lblReportHeader7 = new javax.swing.JLabel();
        txtCollegeName = new javax.swing.JTextField();
        btnCreate = new javax.swing.JButton();
        txtCollegeFull = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Create New College");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        panelInfo.setBackground(new java.awt.Color(84, 83, 83));

        jLabel41.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(204, 204, 204));
        jLabel41.setText("SHORT COLLEGE NAME");

        lblReportHeader7.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        lblReportHeader7.setForeground(new java.awt.Color(204, 204, 204));
        lblReportHeader7.setText("COLLEGE INFORMATION");

        txtCollegeName.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        btnCreate.setBackground(new java.awt.Color(204, 204, 204));
        btnCreate.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        btnCreate.setForeground(new java.awt.Color(204, 204, 204));
        btnCreate.setText("CREATE COLLEGE");
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

        txtCollegeFull.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        jLabel42.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(204, 204, 204));
        jLabel42.setText("FULL COLLEGE NAME");

        javax.swing.GroupLayout panelInfoLayout = new javax.swing.GroupLayout(panelInfo);
        panelInfo.setLayout(panelInfoLayout);
        panelInfoLayout.setHorizontalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblReportHeader7)
                    .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnCreate)
                        .addGroup(panelInfoLayout.createSequentialGroup()
                            .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel42)
                                .addComponent(jLabel41))
                            .addGap(18, 18, 18)
                            .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtCollegeName, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCollegeFull, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(56, 56, 56))
        );
        panelInfoLayout.setVerticalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInfoLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblReportHeader7)
                .addGap(26, 26, 26)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(txtCollegeName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(txtCollegeFull, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        if (confirmAction("Proceed With College Creation?")) {
            System.out.println("College creation confirmed");
            if (validateAllFields()) {
                String collegeQuery = String.format("Insert into Colleges (CollegeName, College_DeptNumber, CollegeFullName) values('%s', 0, '%s');",
                        txtCollegeName.getText().trim(),
                        txtCollegeFull.getText().trim());

                boolean collegeInserted = server.sendUpdateQuery(collegeQuery);

                if (collegeInserted) {
                    displayMessage("College  has been created", "College Creation Successful");
                    dispose();
                } else {
                    displayErrorMessage("Error in creating college. Contact admin", "Update Failed");
                    dispose();
                }

            } else {
                displayErrorMessage("A wrong value has been entered into one of the fields. Please check through", "Update Failed");
            }
        }
    }//GEN-LAST:event_btnCreateActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (confirmAction("End college creation?")) {
            dispose();
        }
    }//GEN-LAST:event_formWindowClosing

    //Custom Methods
    @Override
    protected boolean validateAllFields() {
        boolean validData = true;

        if (!(fieldHasData(txtCollegeName) && validateTextLetter(txtCollegeName))) {
            setErrorBorder(txtCollegeName, true);
            validData = false;
        } else {
            setErrorBorder(txtCollegeName, false);
        }

        if (!(fieldHasData(txtCollegeFull) && validateTextLetter(txtCollegeFull))) {
            setErrorBorder(txtCollegeFull, true);
            validData = false;
        } else {
            setErrorBorder(txtCollegeFull, false);
        }

        return validData;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel lblReportHeader7;
    private javax.swing.JPanel panelInfo;
    private javax.swing.JTextField txtCollegeFull;
    private javax.swing.JTextField txtCollegeName;
    // End of variables declaration//GEN-END:variables
}
