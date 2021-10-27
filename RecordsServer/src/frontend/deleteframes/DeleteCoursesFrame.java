package frontend.deleteframes;

import backend.MainServer;
import java.awt.Point;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fego
 */
public class DeleteCoursesFrame extends AbstractDeleteFrame {

    private String courseCode;
    private String[] columnNames = new String[]{"Course Code", "Course Name"};

    /**
     * Creates new form DeleteCoursesFrame
     */
    private DeleteCoursesFrame() {
        initComponents();
    }

    public DeleteCoursesFrame(MainServer server) {
        this();
        this.server = server;
        initData(getOtherData(server));
        initTableRecordsCourse(tblCourses, coursesList, columnNames);
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
        tblCourses = new javax.swing.JTable();
        setDefaultTableStyle(tblCourses);
        tblCourses.setFillsViewportHeight(true);
        lblReportHeader5 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();

        popStudentOptions.setRequestFocusEnabled(false);
        popStudentOptions.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                popStudentOptionsFocusLost(evt);
            }
        });

        itemDelete.setText("Delete Course from Records");
        itemDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDeleteActionPerformed(evt);
            }
        });
        popStudentOptions.add(itemDelete);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("All Courses");
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

        tblCourses.setAutoCreateRowSorter(true);
        tblCourses.setBackground(new java.awt.Color(84, 83, 83));
        tblCourses.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tblCourses.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        tblCourses.setForeground(new java.awt.Color(204, 204, 204));
        tblCourses.setToolTipText("List of departments");
        tblCourses.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblCourses.setRowHeight(20);
        tblCourses.setRowMargin(3);
        tblCourses.setSelectionForeground(new java.awt.Color(51, 51, 51));
        tblCourses.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblCourses.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCoursesMouseClicked(evt);
            }
        });
        paneStudents.setViewportView(tblCourses);

        lblReportHeader5.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        lblReportHeader5.setForeground(new java.awt.Color(204, 204, 204));
        lblReportHeader5.setText("COURSES LIST");

        txtSearch.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(204, 204, 204));
        jLabel23.setText("SEARCH");

        javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
        panelMain.setLayout(panelMainLayout);
        panelMainLayout.setHorizontalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblReportHeader5)
                    .addComponent(paneStudents, javax.swing.GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelMainLayout.setVerticalGroup(
            panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblReportHeader5)
                .addGap(9, 9, 9)
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel23))
                .addGap(18, 18, 18)
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

    private void tblCoursesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCoursesMouseClicked

        if (evt.getClickCount() == 2) {
            Point location = evt.getLocationOnScreen();

            int row = tblCourses.getSelectedRow();
            if (row > -1) {
                DefaultTableModel model = (DefaultTableModel) tblCourses.getModel();

                courseCode = String.valueOf(model.getValueAt(row, 0));

                course = coursesList.stream().filter(co -> co.getCourseCode().trim().equalsIgnoreCase(courseCode)).findFirst().get();

                popStudentOptions.setLocation(location.x, location.y);
                popStudentOptions.setVisible(true);
            }
        } else {
            popStudentOptions.setVisible(false);
        }
    }//GEN-LAST:event_tblCoursesMouseClicked

    private void itemDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDeleteActionPerformed
        if (confirmAction("Do you want to delete this course from the records?\nNote: This course record cannot be recovered after this operation")) {
            //get login details
            String query = String.format("Delete from Courses where CourseCode = '%s';", course.getCourseCode().trim());

            if (server.sendUpdateQuery(query)) {
                String progQuery = String.format("Update Programmes set Programme_CourseNumber = (Programme_CourseNumber - 1) where ProgrammeID = %d;",
                        course.getProgrammeId());

                if (server.sendUpdateQuery(progQuery)) {
                    displayMessage("Course record successfully deleted from database", "Deletion Successful");
                } else {
                    logger.logErrorNormal("Unable to update programmes after course deletion", new Exception("Unable to update programmes after course deletion"));
                }
            } else {
                displayErrorMessage("Error in deleting course record. Contact maintenance team", "Operation Failed");
            }
        }
    }//GEN-LAST:event_itemDeleteActionPerformed

    private void popStudentOptionsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_popStudentOptionsFocusLost
        popStudentOptions.setVisible(false);
    }//GEN-LAST:event_popStudentOptionsFocusLost

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (confirmAction("Exit Course Deletion")) {
            popStudentOptions.setVisible(false);
            dispose();
        } else {
            popStudentOptions.setVisible(false);
        }
    }//GEN-LAST:event_formWindowClosing

    private void formWindowIconified(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowIconified
         popStudentOptions.setVisible(false);
    }//GEN-LAST:event_formWindowIconified

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus
       popStudentOptions.setVisible(false);
    }//GEN-LAST:event_formWindowLostFocus


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem itemDelete;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel lblReportHeader5;
    private javax.swing.JScrollPane paneStudents;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPopupMenu popStudentOptions;
    private javax.swing.JTable tblCourses;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}