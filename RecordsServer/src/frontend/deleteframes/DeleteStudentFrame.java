package frontend.deleteframes;

import backend.MainServer;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fego
 */
public class DeleteStudentFrame extends AbstractDeleteFrame {

    ArrayList<ArrayList<?>> studentData;
    private String role;
    private int matricNo;
    private String[] columnNames = new String[]{"Matric No", "Last Name", "First Name"};
    private String searchText = "";
    private String searchType;

    /**
     * Creates new form DeleteStudentFrame
     */
    private DeleteStudentFrame() {
        initComponents();
    }

    public DeleteStudentFrame(MainServer server) {
        this();
        this.server = server;
        initData(getStudentData(server));
        initTableRecordsStudent(tblStudents, studentList, columnNames);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popStudentOptions = new javax.swing.JPopupMenu();
        itemDelete = new javax.swing.JMenuItem();
        panelMain = new javax.swing.JPanel();
        paneStudents = new javax.swing.JScrollPane();
        tblStudents = new javax.swing.JTable();
        setDefaultTableStyle(tblStudents);
        tblStudents.setFillsViewportHeight(true);
        lblReportHeader5 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        cmbSearch = new javax.swing.JComboBox<>();

        popStudentOptions.setRequestFocusEnabled(false);
        popStudentOptions.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                popStudentOptionsFocusLost(evt);
            }
        });

        itemDelete.setText("Delete Student from Records");
        itemDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDeleteActionPerformed(evt);
            }
        });
        popStudentOptions.add(itemDelete);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("All Students");
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                formWindowLostFocus(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowIconified(java.awt.event.WindowEvent evt) {
                formWindowIconified(evt);
            }
        });

        panelMain.setBackground(new java.awt.Color(84, 83, 83));
        panelMain.setAutoscrolls(true);

        tblStudents.setAutoCreateRowSorter(true);
        tblStudents.setBackground(new java.awt.Color(84, 83, 83));
        tblStudents.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tblStudents.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        tblStudents.setForeground(new java.awt.Color(204, 204, 204));
        tblStudents.setToolTipText("List of students");
        tblStudents.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblStudents.setRowHeight(20);
        tblStudents.setRowMargin(3);
        tblStudents.setSelectionForeground(new java.awt.Color(51, 51, 51));
        tblStudents.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblStudents.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStudentsMouseClicked(evt);
            }
        });
        paneStudents.setViewportView(tblStudents);

        lblReportHeader5.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        lblReportHeader5.setForeground(new java.awt.Color(204, 204, 204));
        lblReportHeader5.setText("STUDENTS LIST");

        txtSearch.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(204, 204, 204));
        jLabel23.setText("SEARCH");

        cmbSearch.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        cmbSearch.setName("CollegeCombo"); // NOI18N
        cmbSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paneStudents, javax.swing.GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                        .addComponent(lblReportHeader5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbSearch, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearch)
                        .addComponent(jLabel23))
                    .addComponent(lblReportHeader5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(paneStudents, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblStudentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStudentsMouseClicked

        if (evt.getClickCount() == 2) {
            Point location = evt.getLocationOnScreen();

            int row = tblStudents.getSelectedRow();

            if (row > -1) {
                DefaultTableModel model = (DefaultTableModel) tblStudents.getModel();
                matricNo = Integer.parseInt(String.valueOf(model.getValueAt(row, 0)));
                student = studentList.stream().
                        filter(student -> student.getMatricNo() == matricNo).
                        findFirst().
                        orElse(null);
                System.out.println("Matric No: " + matricNo);
                popStudentOptions.setLocation(location.x, location.y);
                popStudentOptions.setVisible(true);
            }
        } else {
            popStudentOptions.setVisible(false);
        }
    }//GEN-LAST:event_tblStudentsMouseClicked

    private void itemDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDeleteActionPerformed
        if (confirmAction("Do you want to delete this student from the records?\nNote: all information about this student cannot be recovered")) {
            //get login details
            String query = String.format("Delete from Login where LoginID = %d;", student.getLoginId());

            if (server.sendUpdateQuery(query)) {
                String progQuery = String.format("Update Programmes as set StudentsNumber = (StudentsNumber - 1) where ProgrammeID = %d;",
                        student.getProgrammeId());
                if (server.sendUpdateQuery(progQuery)) {
                    displayMessage("Student record successfully deleted from database", "Deletion Successful");
                } else {
                    logger.logErrorNormal("Unable to update programmes table after student deletion", new Exception("Unable to update programmes table after student deletion"));
                }
            } else {
                displayErrorMessage("Error in deleting student record. Contact maintenance team", "Operation Failed");
            }
        }
    }//GEN-LAST:event_itemDeleteActionPerformed

    private void popStudentOptionsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_popStudentOptionsFocusLost
        popStudentOptions.setVisible(false);
    }//GEN-LAST:event_popStudentOptionsFocusLost

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (confirmAction("Exit Student Deletion")) {
            popStudentOptions.setVisible(false);
            dispose();
        }else{
            popStudentOptions.setVisible(false);
        }
    }//GEN-LAST:event_formWindowClosing

    private void cmbSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSearchActionPerformed
        searchType = cmbSearch.getSelectedItem().toString().trim();
    }//GEN-LAST:event_cmbSearchActionPerformed

    private void formWindowIconified(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowIconified
         popStudentOptions.setVisible(false);
    }//GEN-LAST:event_formWindowIconified

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus
        popStudentOptions.setVisible(false);
    }//GEN-LAST:event_formWindowLostFocus


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbSearch;
    private javax.swing.JMenuItem itemDelete;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel lblReportHeader5;
    private javax.swing.JScrollPane paneStudents;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPopupMenu popStudentOptions;
    private javax.swing.JTable tblStudents;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
