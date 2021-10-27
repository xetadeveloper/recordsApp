package frontend;

import backend.MainClient;
import static frontend.AbstractClientFrame.UPDATE;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import pojo.Login;

/**
 *
 * @author Fego
 */
public class PanelChangeLogin extends javax.swing.JPanel {

    Login loginGlobal;
    MainClient client;
    boolean student;
    int matricNo;
    JDialog owner;

    /**
     * Creates new form PanelChangeLogin
     */
    private PanelChangeLogin() {
        initComponents();
    }

    /**
     * For staff/admin frame
     *
     * @param client
     * @param owner
     */
    public PanelChangeLogin(MainClient client, JDialog owner) {
        this();
        lblError.setText("");
        this.client = client;
        student = false;
        this.owner = owner;
    }

    /**
     * For student frame
     *
     * @param client
     * @param owner
     * @param role
     * @param matricNo
     */
    public PanelChangeLogin(MainClient client, JDialog owner, String role, int matricNo) {
        this(client, owner);

        if (role.equalsIgnoreCase("student")) {
            this.matricNo = matricNo;
            student = true;
            txtUsername.setText(String.valueOf(matricNo).trim());
            txtUsername.setEditable(false);
            lblInstruction.setText("Enter old password");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCourseDetails1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lblReportHeader6 = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        txtUsername = new javax.swing.JTextField();
        lblInstruction = new javax.swing.JLabel();
        passPass = new javax.swing.JPasswordField();
        lblError = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(486, 290));

        panelCourseDetails1.setBackground(new java.awt.Color(84, 83, 83));

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 204, 204));
        jLabel10.setText("USERNAME");

        jLabel21.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(204, 204, 204));
        jLabel21.setText("PASSWORD");

        lblReportHeader6.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        lblReportHeader6.setForeground(new java.awt.Color(204, 204, 204));
        lblReportHeader6.setText("CHANGE LOGIN");

        btnLogin.setBackground(new java.awt.Color(204, 204, 204));
        btnLogin.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(204, 204, 204));
        btnLogin.setText("VERIFY");
        btnLogin.setToolTipText("Add course");
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.setFocusPainted(false);
        btnLogin.setOpaque(false);
        btnLogin.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        txtUsername.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        lblInstruction.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        lblInstruction.setForeground(new java.awt.Color(204, 204, 204));
        lblInstruction.setText("Enter old login details");

        passPass.setToolTipText("password");

        lblError.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        lblError.setForeground(new java.awt.Color(153, 0, 51));
        lblError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblError.setText("error message");
        lblError.setToolTipText("Error");

        javax.swing.GroupLayout panelCourseDetails1Layout = new javax.swing.GroupLayout(panelCourseDetails1);
        panelCourseDetails1.setLayout(panelCourseDetails1Layout);
        panelCourseDetails1Layout.setHorizontalGroup(
            panelCourseDetails1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCourseDetails1Layout.createSequentialGroup()
                .addGroup(panelCourseDetails1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblError, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelCourseDetails1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelCourseDetails1Layout.createSequentialGroup()
                            .addGap(34, 34, 34)
                            .addComponent(lblReportHeader6))
                        .addGroup(panelCourseDetails1Layout.createSequentialGroup()
                            .addGap(51, 51, 51)
                            .addGroup(panelCourseDetails1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnLogin)
                                .addGroup(panelCourseDetails1Layout.createSequentialGroup()
                                    .addGroup(panelCourseDetails1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel21))
                                    .addGap(18, 18, 18)
                                    .addGroup(panelCourseDetails1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblInstruction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(passPass)
                                        .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        panelCourseDetails1Layout.setVerticalGroup(
            panelCourseDetails1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCourseDetails1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lblReportHeader6)
                .addGap(18, 18, 18)
                .addComponent(lblInstruction)
                .addGap(14, 14, 14)
                .addGroup(panelCourseDetails1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCourseDetails1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(passPass, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(lblError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCourseDetails1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCourseDetails1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        if (btnLogin.getText().trim().equalsIgnoreCase("CHANGE LOGIN DETAILS")) {
            //get password
            char[] password = passPass.getPassword();
            String mainPass = "";
            for (char p : password) {
                mainPass += p;
            }
            
            if (changePassword(client, txtUsername.getText(), mainPass, loginGlobal.getLoginId())) {
                JOptionPane.showMessageDialog(this, "Login Details Sucessfully Updated", "Update Succesful", JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(this, "These are your new login details\nUsername: " + txtUsername.getText() + "\nPassword: " + mainPass,
                        "Login Details", JOptionPane.INFORMATION_MESSAGE);
                owner.dispose();

            } else {
                JOptionPane.showMessageDialog(this, "Error updating login details", "Update Failed", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            verifyAndGetLogin(client);
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    /**
     * Verifies and retrieves the login from the database
     *
     * @param client
     * @return
     */
    private void verifyAndGetLogin(MainClient client) {
        char[] password = passPass.getPassword();
        String mainPass = "";
        for (char p : password) {
            mainPass += p;
        }

        String verifyQuery = AbstractClientFrame.GETLOGIN + ":" + String.format("Select * from Login where username = '%s' and password = '%s'",
                txtUsername.getText().trim(),
                mainPass);

        client.sendQuery(verifyQuery);
        Login login = (Login) client.getResponse();
        if (login == null) {
            lblError.setText("Login not found");
        } else {
            lblError.setText("");
            loginGlobal = login;
            lblInstruction.setText("Enter new login details");

            if (student) {
                txtUsername.setText(String.valueOf(matricNo));
            } else {
                txtUsername.setText("");
            }
            passPass.setText("");
            btnLogin.setText("CHANGE LOGIN DETAILS");
        }
    }

    /**
     * For changing passwords
     *
     * @param client
     * @param passNew
     * @param loginID
     * @return
     */
    protected boolean changePassword(MainClient client, String user, String passNew, int loginID) {
        String query = String.format("Update Login set Password = '%s', Username = '%s' where LoginID = %d;",
                passNew, user, loginID);

        client.sendQuery(UPDATE + ":" + query);

        return (Boolean) client.getResponse();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblInstruction;
    private javax.swing.JLabel lblReportHeader6;
    private javax.swing.JPanel panelCourseDetails1;
    private javax.swing.JPasswordField passPass;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
