/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs356teamproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.text.*;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import org.json.JSONObject;

/**
 *
 * @author Tuan Pham
 */
public class EmployeeLogin extends javax.swing.JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    private String username;
    
    /**
     * Creates new form EmployeeLogin
     */
    public EmployeeLogin() {
        initComponents();
        conn=ScheduleDBConnect.ConnectDB();
    }
    public EmployeeLogin(String username) {
        initComponents();
        conn=ScheduleDBConnect.ConnectDB();
        this.username = username;
        update_TodayTable();
        update_TomTable();
    }
    
    private void update_TodayTable(){
        try{
            
            // Get Schedule for today
            Date date = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("MM-dd-yyyy");
            String strDate = ft.format(date);
            
            // Grab every date with same username
            String compare = "select Date from ScheduleInfo where ScheduleOwner='"+username+"'";  
            pst = conn.prepareStatement(compare);
            rs = pst.executeQuery();
            
            // If equal put in table
            while(rs.next()) {
                String getDate = rs.getString("Date");
                if (strDate.equals(getDate)){
                    String sql = "select Date,MeetingOwner,StartTime,EndTime,Room from ScheduleInfo where ScheduleOwner='"+username+"' and Date='"+getDate+"'";
                    pst = conn.prepareStatement(sql);
                    rs = pst.executeQuery();
                    todayTable.setModel(DbUtils.resultSetToTableModel(rs));
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void update_TomTable() {
        try {
            //Get Schedule for tommorow
            Date date = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("MM-dd-yyyy");
            String tomDate = ft.format(date);
            
            // Increment the day by 1
            Calendar c = Calendar.getInstance();
            c.setTime(ft.parse(tomDate));
            c.add(Calendar.DATE, 1);
            tomDate = ft.format(c.getTime());
            
            // Grab every date with same username
            String compare = "select Date from ScheduleInfo where ScheduleOwner='"+username+"'";  
            pst = conn.prepareStatement(compare);
            rs = pst.executeQuery();

            // If equal put in table
            while(rs.next()) {
                String getDate = rs.getString("Date"); 
                if (tomDate.equals(getDate)){
                String sql = "select Date,MeetingOwner,StartTime,EndTime,Room from ScheduleInfo where ScheduleOwner='"+username+"' and Date='"+tomDate+"'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                tomorrowTable.setModel(DbUtils.resultSetToTableModel(rs));
                }
            }
            
            
//            if (tomDate.equals(getDate)){
//                String sql = "select MeetingOwner,StartTime,EndTime,Room from ScheduleInfo where ScheduleOwner='"+username+"' and Date='"+tomDate+"'";
//                pst = conn.prepareStatement(sql);
//                rs = pst.executeQuery();
//                tomorrowTable.setModel(DbUtils.resultSetToTableModel(rs));
//            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
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

        updateSchedButton = new javax.swing.JButton();
        displaySchedButton = new javax.swing.JButton();
        displayMeetingButton = new javax.swing.JButton();
        scheduleMeetingButton = new javax.swing.JButton();
        displayStatusButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        dateToday = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dateTomorrow = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        todayTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tomorrowTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        updateSchedButton.setText("Update Schedule");
        updateSchedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateSchedButtonActionPerformed(evt);
            }
        });

        displaySchedButton.setText("Display Schedule");
        displaySchedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displaySchedButtonActionPerformed(evt);
            }
        });

        displayMeetingButton.setText("Display Meeting Invites");
        displayMeetingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayMeetingButtonActionPerformed(evt);
            }
        });

        scheduleMeetingButton.setText("Schedule Meeting");
        scheduleMeetingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scheduleMeetingButtonActionPerformed(evt);
            }
        });

        displayStatusButton.setText("Display Meeting Status");
        displayStatusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayStatusButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Schedule for Today and Tomorrow");

        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("E M-dd-yyyy");
        dateToday.setText("Today");

        dateTomorrow.setText("Tomorrow");

        todayTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "No Scheduled Meetings Today"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(todayTable);
        if (todayTable.getColumnModel().getColumnCount() > 0) {
            todayTable.getColumnModel().getColumn(0).setResizable(false);
        }

        tomorrowTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "No Scheduled Meetings Tomorrow"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tomorrowTable);
        if (tomorrowTable.getColumnModel().getColumnCount() > 0) {
            tomorrowTable.getColumnModel().getColumn(0).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(displaySchedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(scheduleMeetingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(updateSchedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(46, 46, 46)
                                    .addComponent(displayStatusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(displayMeetingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dateTomorrow, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateToday, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
                            .addComponent(jScrollPane4)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dateToday, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateTomorrow, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(displaySchedButton)
                    .addComponent(scheduleMeetingButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateSchedButton)
                    .addComponent(displayStatusButton))
                .addGap(18, 18, 18)
                .addComponent(displayMeetingButton)
                .addGap(44, 44, 44))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void updateSchedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateSchedButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateSchedButtonActionPerformed

    private void displaySchedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displaySchedButtonActionPerformed
        // TODO add your handling code here:
         try{
             //System.out.println(username);
             ScheduleGUI sg = new ScheduleGUI(username);
             sg.setVisible(true);
             
         }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_displaySchedButtonActionPerformed

    private void displayMeetingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayMeetingButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_displayMeetingButtonActionPerformed

    private void scheduleMeetingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scheduleMeetingButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_scheduleMeetingButtonActionPerformed

    private void displayStatusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayStatusButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_displayStatusButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EmployeeLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        
        
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dateToday;
    private javax.swing.JLabel dateTomorrow;
    private javax.swing.JButton displayMeetingButton;
    private javax.swing.JButton displaySchedButton;
    private javax.swing.JButton displayStatusButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton scheduleMeetingButton;
    private javax.swing.JTable todayTable;
    private javax.swing.JTable tomorrowTable;
    private javax.swing.JButton updateSchedButton;
    // End of variables declaration//GEN-END:variables
}
