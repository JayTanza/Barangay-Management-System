/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BarangayManagement;

import javax.swing.plaf.basic.BasicInternalFrameUI;
import com.sun.jdi.connect.spi.Connection;
import java.awt.event.KeyEvent;
//import java.lang.System.Logger;
//import java.lang.System.Logger.Level;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.mysql.jdbc.Statement;
import com.sun.jdi.connect.spi.Connection;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import java.awt.print.PrinterException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;
/**
 *
 * @author Jay Tanza
 */
public class MenuAdminReports extends javax.swing.JInternalFrame {

    /**
     * Creates new form Menu1
     */
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
//    String imgPath = null;
//    Object[] cols = null;
    public MenuAdminReports() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
        populateJtable();
        showTime();
        showDate();
//        jLabel3.setVisible(false);
//        jLabel4.setVisible(false);
//        jLabel7.setVisible(false);
    }
    
    public void populateJtable()
    {
       ResidentReportsQuery cq = new ResidentReportsQuery();
       ArrayList<ResidentReports> ctList = cq.contactList();
       String[] colNames = {"ID","Report","Rname","Date","Time"};
      Object[][]  rows = new Object[ctList.size()][5];
//       
       for(int i=0; i<ctList.size(); i++)
       {
         rows[i][0] = ctList.get(i).getCid();
         rows[i][1] = ctList.get(i).getRname();
         rows[i][2] = ctList.get(i).getReport();
         rows[i][3] = ctList.get(i).getDate();
         rows[i][4] = ctList.get(i).getTime();
         
         
//         ImageIcon pic = new ImageIcon(new ImageIcon(ctList.get(i).getPic()).getImage().getScaledInstance(150, 120, Image.SCALE_SMOOTH));
//         rows[i][5] = pic;
       }
       MyModel mmd = new MyModel(rows, colNames);
       jTable2.setModel(mmd);
       jTable2.setRowHeight(120);
       jTable2.getColumnModel().getColumn(4).setPreferredWidth(150);
    }
            void showDate() {
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("MM/dd/yyyy");
        lblDate.setText("Date: " + s.format(d));
    }

    void showTime() {
        new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss a");
        lblTime.setText("Time: " + s.format(d));
            }
            
        }
        ).start();
    }

    void report(){
        Connection con=null;
        String report = "INSERT INTO `audittrail`(`Date`, `Time`) VALUES (?,?)";
            Date d = new Date();       
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat c = new SimpleDateFormat("hh:mm:ss");
            String date = (s.format(d));
            String time = (c.format(d));
    try{
            pst = MyConnection.getConnection().prepareStatement(report);
            //pst = MyConnection.getConnection().prepareStatement(report2); //jTextField_Username
            pst.setString(1, date);
            pst.setString(2, time);
            
            pst.executeUpdate();
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

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabelProfileImage = new javax.swing.JLabel();
        jButton49 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel42 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        contact = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        jButton51 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        jLabelProfileImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));

        jDesktopPane1.setLayer(jLabelProfileImage, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDesktopPane1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabelProfileImage, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDesktopPane1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabelProfileImage, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jButton49.setBackground(new java.awt.Color(169, 224, 49));
        jButton49.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton49.setForeground(new java.awt.Color(0, 0, 0));
        jButton49.setText("Select Image");
        jButton49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton49ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(169, 224, 49));
        jLabel12.setText("AGE:");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jLabel16.setText("jLabel16");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setPreferredSize(new java.awt.Dimension(995, 692));

        jPanel42.setBackground(new java.awt.Color(0, 0, 0));
        jPanel42.setLayout(null);

        jPanel1.setBackground(new java.awt.Color(21, 25, 28));

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(169, 224, 49));
        jLabel2.setText("RESIDENT REPORTS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(289, 289, 289)
                .addComponent(jLabel2)
                .addContainerGap(293, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        jPanel42.add(jPanel1);
        jPanel1.setBounds(0, 0, 990, 80);

        jPanel3.setBackground(new java.awt.Color(21, 25, 28));
        jPanel3.setForeground(new java.awt.Color(21, 25, 28));

        contact.setEditable(false);
        contact.setBackground(new java.awt.Color(0, 0, 0));
        contact.setFont(new java.awt.Font("Trebuchet MS", 3, 14)); // NOI18N
        contact.setForeground(new java.awt.Color(255, 255, 255));
        contact.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        contact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contactActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(169, 224, 49));
        jLabel1.setText("FROM: ");

        lblTime.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTime.setForeground(new java.awt.Color(169, 224, 49));
        lblTime.setText("Time :");

        lblDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDate.setForeground(new java.awt.Color(169, 224, 49));
        lblDate.setText("Date :");

        jButton51.setBackground(new java.awt.Color(0, 0, 0));
        jButton51.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton51.setForeground(new java.awt.Color(169, 224, 49));
        jButton51.setText("SUBMIT REPORT");
        jButton51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton51ActionPerformed(evt);
            }
        });

        jTable2.setBackground(new java.awt.Color(31, 36, 42));
        jTable2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTable2.setForeground(new java.awt.Color(255, 255, 255));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contact, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton51, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton51, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(contact, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel42.add(jPanel3);
        jPanel3.setBounds(30, 100, 920, 540);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, 983, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton49ActionPerformed
//        // TODO add your handling code here:
//        //String File;
//        /**JFileChooser fileChooser = new JFileChooser();
//        fileChooser.showOpenDialog(null);
//        File f = fileChooser.getSelectedFile();
//        String fileName = f.getAbsolutePath();
//        Path.setText(fileName);
//        Image getAsolutePath = null;
//        ImageIcon icon = new ImageIcon(fileName);
//        Image image = icon.getImage().getScaledInstance(jLabelProfileImage.getWidth(), jLabelProfileImage.getHeight(), Image.SCALE_DEFAULT);
//        jLabelProfileImage.setIcon(icon);
//        jLabelProfileImage.setIcon(ResizeImage(f));**/
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
//        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.jpg","*.png","*.gif","*.bmp");
//        fileChooser.addChoosableFileFilter(filter);
//        int result=fileChooser.showSaveDialog(null);
//        if(result==JFileChooser.APPROVE_OPTION){
//            File selectedFile = fileChooser.getSelectedFile();
//            String path = selectedFile.getAbsolutePath();
//            jLabelProfileImage.setIcon(ResizeImage(path));
//            String s = path;
//            //cmdRegisterWithImage.setVisible(true);
//            //cmdRegister.setVisible(false);
//        }else if(result==JFileChooser.CANCEL_OPTION){
//            jLabelProfileImage.setIcon(null);
//            //cmdRegister.setVisible(true);
//            //cmdRegisterWithImage.setVisible(false);
//            JOptionPane.showMessageDialog(null, "Select an Image","Barangay System",JOptionPane.INFORMATION_MESSAGE);
//        }
    }//GEN-LAST:event_jButton49ActionPerformed

    private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton51ActionPerformed
//        // TODO add your handling code here:
//        //INSERT INTO `requestdoc`(`Firstname`, `Lastname`, `Address`, `Doc`, `Reason`) VALUES (?,?,?,?,?)
//        String registerUserQuery = "INSERT INTO `report`(`Report`, `Rname`, `Date`, `Time`) VALUES (?,?,?,?)";
//     try{
//                          //InputStream is = new FileInputStream(new File(s));
//                          pst = (PreparedStatement) MyConnection.getConnection().prepareStatement(registerUserQuery);
//                          pst.setString(1,jTextArea1.getText());
//                          pst.setString(2,contact.getText());
//                          pst.setString(3,lblDate.getText());
//                          pst.setString(4,lblTime.getText());
//                          pst.executeUpdate();
//    }                                         
//
//        catch (SQLException ex) {
//            Logger.getLogger(ReqDoc.class.getName()).log(Level.SEVERE, null, ex);
//        }
//         JOptionPane.showMessageDialog(null, "Request Successfully Registered","Barangay System",JOptionPane.INFORMATION_MESSAGE);
//    
    }//GEN-LAST:event_jButton51ActionPerformed

    private void contactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contactActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
//
//        int i = jTable2.getSelectedRow();
//        TableModel model = jTable2.getModel();
//        jTextField_ID.setText(model.getValueAt(i,0).toString());
//        jTextField_Firstname .setText(model.getValueAt(i,1).toString());
//        jTextField_Middlename .setText(model.getValueAt(i,2).toString());
//        jTextField_Lastname.setText(model.getValueAt(i,3).toString());
//        jTextField_Address.setText(model.getValueAt(i,4).toString());
//
//        if(jTable2.getValueAt(i,5)!=null)
//        {
//            ImageIcon image=(ImageIcon)jTable2.getValueAt(i,5);
//            Image image2=image.getImage().getScaledInstance(jLabelProfileImage1.getWidth(),jLabelProfileImage1.getHeight(),Image.SCALE_SMOOTH);
//            ImageIcon image3=new ImageIcon(image2);
//            jLabelProfileImage1.setIcon(image3);
//        }
//        else
//        {
//            System.out.println("No image");
//        }
//        try
//        {
//            String sql2 = "SELECT `Firstname`, `ResidentID`, `Middlename`, `Lastname`, `Birthdate`, `Age`, `Phonenumber`, `Address`, `Gender`, `profilepic` FROM `residentaccountinfo` where Firstname=?";
//            pst = MyConnection.getConnection().prepareStatement(sql2);
//            pst.setString(1,jTextField_Search.getText());
//            ResultSet rs = pst.executeQuery();
//            if(rs.next())
//            {
//                String setid = rs.getString("ResidentID");
//                jTextField_ID.setText(setid);
//
//                String setname = rs.getString("Firstname");
//                jTextField_Firstname.setText(setname);
//
//                String setname2 = rs.getString("Middlename");
//                jTextField_Lastname.setText(setname2);
//
//                String setname3 = rs.getString("Lastname");
//                jTextField_Middlename.setText(setname3);
//
//                String address = rs.getString("Address");
//                jTextField_Address.setText(address);
//
//                //               String Birthdate = rs.getString("Birthdate"); // What ever column
//                //               java.util.Date date = new SimpleDateFormat("").parse(Birthdate);
//                //               jDateChooser_DOB.setDate(date);
//                String DOB = rs.getString("Birthdate");
//                jTextField_DOB.setText(DOB);
//
//                String Age = rs.getString("Age");
//                jTextField_Age.setText(Age);
//
//                String Phonenumber = rs.getString("Phonenumber");
//                jTextField_Phonenumber.setText(Phonenumber);
//
//                String Gender = rs.getString("Gender");
//                jTextField_Gender.setText(Gender);
//
//                byte[] img = rs.getBytes("profilepic");
//                ImageIcon image = new ImageIcon(img);
//                Image im = image.getImage();
//                Image myimg = im.getScaledInstance(jLabelProfileImage1.getWidth(), jLabelProfileImage1.getHeight(), Image.SCALE_SMOOTH);
//                ImageIcon newImage = new ImageIcon(myimg);
//                jLabelProfileImage1.setIcon(newImage);
//            }
//        }
//        catch (Exception ex)
//        {
//            JOptionPane.showMessageDialog(null, ex);
//        }
    }//GEN-LAST:event_jTable2MouseClicked
         
    public ImageIcon ResizeImage(String imgPath){
        ImageIcon MyImage = new ImageIcon(imgPath);
        Image img = MyImage.getImage();
        Image newImage = img.getScaledInstance(jLabelProfileImage.getWidth(), jLabelProfileImage.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
        }
    //    public ImageIcon ResizeImage(String imgPath){
//        ImageIcon MyImage = new ImageIcon(imgPath);
//        Image img = MyImage.getImage();
//        Image newImage = img.getScaledInstance(jLabelProfileImage.getWidth(), jLabelProfileImage.getHeight(), Image.SCALE_SMOOTH);
//        ImageIcon image = new ImageIcon(newImage);
//        return image;
//        }    /**public void showTable() 
//    {
//       String registerUserQuery = "SELECT * FROM residentaccountinfo";
//       try {
//        ps = MyConnection.getConnection().prepareStatement(registerUserQuery);
//        rs = ps.executeQuery();
//       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
//       
//       }catch (SQLException ex) {
//                   JOptionPane.showMessageDialog(null, ex);
//                }
//    }**/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField contact;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton51;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelProfileImage;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    public javax.swing.JTable jTable2;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblTime;
    // End of variables declaration//GEN-END:variables
//    private void printRecord(JPanel jPanel_Print)
//    {   
//      PrinterJob printerJob = PrinterJob.getPrinterJob();
//      
//      printerJob.setJobName("Print Document");
//      printerJob.setPrintable(new Printable)
//      {
//        //@Override
//        public int print(Graphics graphics, PageFormat pageformat, int pageIndex) throws PrinterException
//        {
//      if (pageIndex > 0){
//      return Printable.NO_SUCH_PAGE;
//      }
//      Graphics2D g2 = (Graphics2D) graphics;
//      g2.translate(pageformat.getImageableX(), pageformat.getImageableY());
//      jPanel_Print.paint(g2);
//      //JOptionPane.showMessageDialog(null, "Reciept Printed...","Barangay System",JOptionPane.INFORMATION_MESSAGE);
//      return Printable.PAGE_EXISTS;
//       }
//     });
//         boolean returningResult = printerJob.printDialog();
//        
//        if(returningResult)
//      {
//             try
//             {
//               printerJob.print();
//             }catch(PrinterException printerException)
//          {
//             JOptionPane.showMessageDialog(this, "Print Error: " + printerException.getMesage());
//          }
//      }
//    }
//}
//    private Icon ResizeImage(File f) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    private BufferedImage resize(BufferedImage bi, int width, int height) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    

   
}
