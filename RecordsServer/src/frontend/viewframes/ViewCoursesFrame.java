package frontend.viewframes;

import backend.MainServer;
import frontend.AbstractViewFrame;
import frontend.updateframes.UpdateCourseFrame;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import pojo.Course;
import pojo.Programme;

/**
 *
 * @author Fego
 */
public class ViewCoursesFrame extends AbstractViewFrame {

    private String courseCode;
    private String[] columnNames = new String[]{"Course Code", "Course Name"};
    private String searchText = "";
    private String searchType;

    /**
     * Creates new form ViewCoursesFrame
     */
    private ViewCoursesFrame() {
        initComponents();
    }

    public ViewCoursesFrame(MainServer server) {
        this();
        this.server = server;
        initData(getOtherData(server));
        initTableRecordsCourse(tblCourses, coursesList, columnNames);
        for (String col : columnNames) {
            cmbSearch.addItem(col);
        }
        initDocumentListener(txtSearch);
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
        lblCourseCode = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        lblCourseName = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        lblCourseProg = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        lblCourseStatus = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        lblCreditUnits = new javax.swing.JLabel();
        lblCourseLevel = new javax.swing.JLabel();
        popStudentOptions = new javax.swing.JPopupMenu();
        itemView = new javax.swing.JMenuItem();
        itemUpdate = new javax.swing.JMenuItem();
        panelMain = new javax.swing.JPanel();
        paneStudents = new javax.swing.JScrollPane();
        tblCourses = new javax.swing.JTable();
        setDefaultTableStyle(tblCourses);
        tblCourses.setFillsViewportHeight(true);
        lblReportHeader5 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        cmbSearch = new javax.swing.JComboBox<>();

        panelInfo.setBackground(new java.awt.Color(84, 83, 83));
        panelInfo.setPreferredSize(new java.awt.Dimension(563, 430));
        panelInfo.setRequestFocusEnabled(false);

        jLabel41.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(204, 204, 204));
        jLabel41.setText("COURSE NAME");

        lblReportHeader7.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        lblReportHeader7.setForeground(new java.awt.Color(204, 204, 204));
        lblReportHeader7.setText("COURSE INFORMATION");

        lblCourseCode.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        lblCourseCode.setForeground(new java.awt.Color(204, 204, 204));
        lblCourseCode.setText("N/A");

        jLabel42.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(204, 204, 204));
        jLabel42.setText("COURSE CODE");

        lblCourseName.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        lblCourseName.setForeground(new java.awt.Color(204, 204, 204));
        lblCourseName.setText("N/A");

        jLabel43.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(204, 204, 204));
        jLabel43.setText("COURSE PROGRAMME");

        lblCourseProg.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        lblCourseProg.setForeground(new java.awt.Color(204, 204, 204));
        lblCourseProg.setText("N/A");

        jLabel44.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(204, 204, 204));
        jLabel44.setText("CREDIT UNITS");

        lblCourseStatus.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        lblCourseStatus.setForeground(new java.awt.Color(204, 204, 204));
        lblCourseStatus.setText("N/A");

        jLabel45.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(204, 204, 204));
        jLabel45.setText("COURSE LEVEL");

        jLabel46.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(204, 204, 204));
        jLabel46.setText("COURSE STATUS");

        lblCreditUnits.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        lblCreditUnits.setForeground(new java.awt.Color(204, 204, 204));
        lblCreditUnits.setText("N/A");

        lblCourseLevel.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        lblCourseLevel.setForeground(new java.awt.Color(204, 204, 204));
        lblCourseLevel.setText("N/A");

        javax.swing.GroupLayout panelInfoLayout = new javax.swing.GroupLayout(panelInfo);
        panelInfo.setLayout(panelInfoLayout);
        panelInfoLayout.setHorizontalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInfoLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblReportHeader7)
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel44, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel43, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel46, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblCourseCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCourseProg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCourseName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                            .addComponent(lblCourseStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCourseLevel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCreditUnits, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(94, 94, 94))
        );
        panelInfoLayout.setVerticalGroup(
            panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInfoLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblReportHeader7)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInfoLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(lblCourseCode))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInfoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel42)))
                .addGap(18, 18, 18)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCourseName)
                    .addComponent(jLabel41))
                .addGap(18, 18, 18)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCourseProg)
                    .addComponent(jLabel43))
                .addGap(18, 18, 18)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(lblCreditUnits))
                .addGap(18, 18, 18)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(lblCourseStatus))
                .addGap(18, 18, 18)
                .addGroup(panelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(lblCourseLevel))
                .addGap(50, 50, 50))
        );

        popStudentOptions.setRequestFocusEnabled(false);
        popStudentOptions.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                popStudentOptionsFocusLost(evt);
            }
        });

        itemView.setText("View Courses Details");
        itemView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemViewActionPerformed(evt);
            }
        });
        popStudentOptions.add(itemView);

        itemUpdate.setText("Update Course Details");
        itemUpdate.setActionCommand("Update Course Data");
        itemUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemUpdateActionPerformed(evt);
            }
        });
        popStudentOptions.add(itemUpdate);

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
                .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblReportHeader5)
                    .addGroup(panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel23)))
                .addGap(18, 18, 18)
                .addComponent(cmbSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void itemViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemViewActionPerformed
        initCourseFullDataPanel(courseCode); //initialize fields in the dialog

        JDialog dialogStudent = createDialog(panelInfo, panelInfo.getPreferredSize(), "Course Details");
        dialogStudent.setAlwaysOnTop(false);
        dialogStudent.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                popStudentOptions.setVisible(false);
            }

            @Override
            public void windowClosing(WindowEvent e) {
                if (confirmAction("Close full details panel?")) {
                    dialogStudent.dispose();
                }
            }
        });
        dialogStudent.setVisible(true); //Show the dialog
    }//GEN-LAST:event_itemViewActionPerformed

    private void popStudentOptionsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_popStudentOptionsFocusLost
        popStudentOptions.setVisible(false);
    }//GEN-LAST:event_popStudentOptionsFocusLost

    private void tblCoursesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCoursesMouseClicked

        if (evt.getClickCount() == 2) {
            Point location = evt.getLocationOnScreen();

            int row = tblCourses.getSelectedRow();
            if (row > -1) {
                DefaultTableModel model = (DefaultTableModel) tblCourses.getModel();

                courseCode = String.valueOf(model.getValueAt(row, 0));
                
                course = coursesList.stream().filter(co -> co.getCourseCode().equalsIgnoreCase(courseCode)).findFirst().get();
                System.out.println("Course is null in view: " + (course == null));

                popStudentOptions.setLocation(location.x, location.y);
                popStudentOptions.setVisible(true);
            }
        } else {
            popStudentOptions.setVisible(false);
        }
    }//GEN-LAST:event_tblCoursesMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (confirmAction("Close viewing courses")) {
            dispose();
        }
    }//GEN-LAST:event_formWindowClosing

    private void cmbSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSearchActionPerformed
        searchType = cmbSearch.getSelectedItem().toString().trim();
    }//GEN-LAST:event_cmbSearchActionPerformed

    private void itemUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemUpdateActionPerformed
        new UpdateCourseFrame(server, course).setVisible(true);
        popStudentOptions.setVisible(false);
    }//GEN-LAST:event_itemUpdateActionPerformed

    private void formWindowIconified(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowIconified
        popStudentOptions.setVisible(false);
    }//GEN-LAST:event_formWindowIconified

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus
        popStudentOptions.setVisible(false);
    }//GEN-LAST:event_formWindowLostFocus

    private void initCourseFullDataPanel(String code) {
        Course course = coursesList.stream().filter(co -> co.getCourseCode().trim().equalsIgnoreCase(code)).findFirst().get();
        Programme prog = progList.stream().filter(pr -> pr.getProgrammeId() == course.getProgrammeId()).findFirst().get();

        lblCourseCode.setText(course.getCourseCode());
        lblCourseLevel.setText(String.valueOf(course.getLevel()));
        lblCourseName.setText(course.getCourseName());
        lblCourseProg.setText(prog.getProgrammeName());
        lblCourseStatus.setText(course.getCourseStatus());
        lblCreditUnits.setText(String.valueOf(course.getCreditUnits()));
    }
         
    /**
     * Adds a document listener to the text field for search purposes
     *
     * @param field
     */
    private void initDocumentListener(JTextField field) {
        field.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = field.getText();
                searchText += text.charAt(text.length() - 1);
                searchCourse(tblCourses, coursesList, searchType, searchText, columnNames);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (searchText.length() > 0) {
                    searchText = searchText.substring(0, searchText.length() - 1);
                    searchCourse(tblCourses, coursesList, searchType, searchText, columnNames);
                }
                else{
                    initTableRecordsCourse(tblCourses, coursesList, columnNames);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbSearch;
    private javax.swing.JMenuItem itemUpdate;
    private javax.swing.JMenuItem itemView;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel lblCourseCode;
    private javax.swing.JLabel lblCourseLevel;
    private javax.swing.JLabel lblCourseName;
    private javax.swing.JLabel lblCourseProg;
    private javax.swing.JLabel lblCourseStatus;
    private javax.swing.JLabel lblCreditUnits;
    private javax.swing.JLabel lblReportHeader5;
    private javax.swing.JLabel lblReportHeader7;
    private javax.swing.JScrollPane paneStudents;
    private javax.swing.JPanel panelInfo;
    private javax.swing.JPanel panelMain;
    private javax.swing.JPopupMenu popStudentOptions;
    private javax.swing.JTable tblCourses;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

    @Override
    protected boolean validateAllFields() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
