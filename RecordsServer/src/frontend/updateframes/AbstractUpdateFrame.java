package frontend.updateframes;

import backend.MainServer;
import frontend.AbstractViewFrame;
import pojo.Student;

/**
 *
 * @author Fego
 */
public abstract class AbstractUpdateFrame extends AbstractViewFrame {

    /**
     * Creates new form AbstractUpdateFrame
     */
    public AbstractUpdateFrame() {
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
     * Returns a student using the matricNo
     *
     * @param matricNo
     * @return
     */
    protected Student getStudent(int matricNo) {
        return studentList.stream().filter(stu -> stu.getMatricNo() == matricNo).findFirst().get();
    }

    protected final void initUpdateTotalBackgroundDetails(MainServer server, boolean student) {
        initTotalBackgroundDetails(server, student);

        if (student) {
            initData(getStudentData(server));
        }
        else{
             initData(getStaffData(server));
        }
    }
    
    protected abstract void initPanel();


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
