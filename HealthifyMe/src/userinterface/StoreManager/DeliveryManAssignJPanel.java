/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface.StoreManager;

//import Business.DeliveryMan.DeliveryMan;
import Business.EcoSystem;
import Business.CustomerRequestOrder;
import Business.Employee.Employee;
import Business.Enterprise.Enterprise;
import Business.Organization.Organization;
import Business.Organization.Organization.Type;
//import Business.Organization;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.NutritionistWorkRequest;
import Business.WorkQueue.StoreWorkRequest;
//import Business.WorkQueue.WorkOrderRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import java.awt.Component;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sriram
 */
public class DeliveryManAssignJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private CustomerRequestOrder order;
    private EcoSystem business;
    private Enterprise enterprise;
    private StoreWorkRequest request;

    /**
     * Creates new form DeliveryManAssignJPanel
     */

    DeliveryManAssignJPanel(JPanel userProcessContainer, EcoSystem business, Enterprise enterprise, StoreWorkRequest request) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.business=business;
        this.request = request;
        this.enterprise = enterprise;
        populateComboBox();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        deliveryManCmbBox = new javax.swing.JComboBox<>();
        appointDelManBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(238, 137, 19));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(80, 80, 82));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Assign Deliveryman");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 11, 1104, 37));

        backBtn.setBackground(new java.awt.Color(255, 102, 0));
        backBtn.setFont(new java.awt.Font("SansSerif", 1, 11)); // NOI18N
        backBtn.setText("< Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });
        add(backBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 82, 37));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 11)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Deliveryman:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 113, 151, -1));

        deliveryManCmbBox.setFont(new java.awt.Font("SansSerif", 1, 11)); // NOI18N
        deliveryManCmbBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(deliveryManCmbBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(422, 110, 150, 30));

        appointDelManBtn.setBackground(new java.awt.Color(255, 102, 0));
        appointDelManBtn.setFont(new java.awt.Font("SansSerif", 1, 11)); // NOI18N
        appointDelManBtn.setText("Appoint");
        appointDelManBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appointDelManBtnActionPerformed(evt);
            }
        });
        add(appointDelManBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(442, 170, 95, 36));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/userinterface/images/deliveryBg.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 600));
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout)userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backBtnActionPerformed

    private void appointDelManBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appointDelManBtnActionPerformed
        // TODO add your handling code here:
        for(Organization org: enterprise.getOrganizationDirectory().getOrganizationList()){
            for(UserAccount user: org.getUserAccountDirectory().getUserAccountList()){
            if(deliveryManCmbBox.getSelectedItem().equals(user.getEmployee().getName())){    
                request.setReceiver(user);
                request.setStatus("Deliverman Assigned");
                user.getWorkQueue().getWorkRequestList().add(request);
                JOptionPane.showMessageDialog(null, "Deliveryman assigned successfully!","Success",JOptionPane.INFORMATION_MESSAGE);
            }
        }
        }
    }//GEN-LAST:event_appointDelManBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton appointDelManBtn;
    private javax.swing.JButton backBtn;
    private javax.swing.JComboBox<String> deliveryManCmbBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables

    private void populateComboBox() {
        DefaultComboBoxModel dm=new DefaultComboBoxModel();
           for(Organization o:enterprise.getOrganizationDirectory().getOrganizationList()){
                for(UserAccount e : o.getUserAccountDirectory().getUserAccountList()){
                    if(e.getRole().toString().equals("Business.Role.DeliveryManRole")){
                        dm.addElement(e.getEmployee().getName());
                        deliveryManCmbBox.setModel(dm);
                    }
                }
             }
  }
}