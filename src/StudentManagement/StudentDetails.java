/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudentManagement;

import java.io.*;
import java.sql.*;

import javax.swing.JOptionPane;
import java.util.Date;



public class StudentDetails extends javax.swing.JFrame {
    
    public StudentDetails() {
        initComponents();
      
        
    }
     Connection con;
    PreparedStatement pst;
    
  //  public void myInitComponents(){
       // jButton2.addActionListener(new java.awt.event.ActionListener() {
       //  /   public void actionPerformed(java.awt.event.ActionEvent evt) {
        //        jButton2ActionPerformed(evt);
   //         }
   // }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setBackground(new java.awt.Color(204, 255, 204));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setText("Student Database");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(368, 368, 368)
                .addComponent(jLabel1)
                .addContainerGap(436, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(46, 46, 46))
        );

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Roll no", "Name", "Father's Name", "Mother's name", "Gender", "DOB", "Phone", "Average", "Grade", "Age"
            }
        ));
        jScrollPane1.setViewportView(table1);

        jButton1.setText("Download details ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Send via mail");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(268, 268, 268)
                .addComponent(jButton1)
                .addGap(92, 92, 92)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(71, 71, 71))
        );

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    
      private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
      Mail a=new Mail();
      this.hide();
      a.setVisible(true);
      }   
    
      
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        
        String csvFilePath = "Details.csv";
         
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/studentmanagement","root","")) {
            String sql = "SELECT details.rollno,details.name,details.fathername,details.mothername,details.gender, details.dob, details.phone,details.age, marks.average,marks.grade  FROM details,marks WHERE marks.rollno=details.rollno";

             
            Statement statement = connection.createStatement();
             
            ResultSet result = statement.executeQuery(sql);
             
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));
             
            // write header line containing column names       
            fileWriter.write("rollno,name,fathername,mothername,gender,dob,phone,age,average,grade");
             
            while (result.next()) {
                int Rollno = result.getInt("rollno");
                String Name = result.getString("name");
                String FatherName = result.getString("fathername");
                String MotherName = result.getString("mothername");
                String gender = result.getString("gender");
               //
               // SimpleDateFormat datefor=new SimpleDateFormat("yyyy-MM-dd");
                 Date dob=result.getDate("dob");
               // String dob1=datefor.format(result.getString("dob"));
               
              // String dobb= result.getString("dob");
               
                String phone = result.getString("phone");
                float average = result.getFloat("average");
               
                String grade = result.getString("grade");
                 String age = result.getString("age");
                 
               
                 
                String line = String.format("%d,\"%s\",%s,%s,%s,%s,%s,%f,%s,%s",
                       Rollno , Name, FatherName, MotherName,gender,dob,phone,average,grade,age);
                 
                fileWriter.newLine();
                fileWriter.write(line);            
            }
             
            statement.close();
            fileWriter.close();
             JOptionPane.showMessageDialog(null,"Downloading completed...");
             
        } catch (SQLException e) {
            System.out.println("Datababse error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File IO error:");
            e.printStackTrace();
        }
        
           
    }//GEN-LAST:event_jButton1ActionPerformed

    
    
    
    
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentDetails().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable table1;
    // End of variables declaration//GEN-END:variables
}
