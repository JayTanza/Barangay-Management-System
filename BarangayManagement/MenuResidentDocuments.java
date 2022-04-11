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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;
/**
 *
 * @author Jay Tanza
 */
public class MenuResidentDocuments extends javax.swing.JInternalFrame {

    /**
     * Creates new form Menu1
     */
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String imgPath = null;
    Object[] cols = null;
    String price = null;
    Boolean Okay = null;
    public MenuResidentDocuments() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
        populateJtable();
        jLabel3.setVisible(false);
        jLabel4.setVisible(false);
        jLabel7.setVisible(false);
    }
    
    public void populateJtable()
    {
       ResidentDocumentsQuery cq = new ResidentDocumentsQuery();
       ArrayList<ResidentDocuments> ctList = cq.contactList();
       String[] colNames = {"ID","First Name","Last Name","Address","Doc","Reason"};
      Object[][]  rows = new Object[ctList.size()][6];
//       
       for(int i=0; i<ctList.size(); i++)
       {
         rows[i][0] = ctList.get(i).getCid();
         rows[i][1] = ctList.get(i).getFname();
         rows[i][2] = ctList.get(i).getLname();
         rows[i][3] = ctList.get(i).getAddress();
         rows[i][4] = ctList.get(i).getDoc();
         rows[i][5] = ctList.get(i).getReason();
         
         
//         ImageIcon pic = new ImageIcon(new ImageIcon(ctList.get(i).getPic()).getImage().getScaledInstance(150, 120, Image.SCALE_SMOOTH));
//         rows[i][5] = pic;
       }
       MyModel mmd = new MyModel(rows, colNames);
       jTable2.setModel(mmd);
       jTable2.setRowHeight(120);
       jTable2.getColumnModel().getColumn(4).setPreferredWidth(150);
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
        jComboBox_Documents = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jPanel_Print = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel_image = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtAmountRecieved = new javax.swing.JTextField();
        txtPay = new javax.swing.JTextField();
        cmdReceiptPreview = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

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

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(169, 224, 49));
        jLabel2.setText("REQUEST RECORDS/DOCUMENTS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(258, 258, 258)
                .addComponent(jLabel2)
                .addContainerGap(383, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel42.add(jPanel1);
        jPanel1.setBounds(0, 0, 1085, 50);

        jComboBox_Documents.setBackground(new java.awt.Color(169, 224, 49));
        jComboBox_Documents.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jComboBox_Documents.setForeground(new java.awt.Color(0, 0, 0));
        jComboBox_Documents.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Document", "Barangay Certificate", "Barangay Permit", "Barangay Clearance " }));
        jComboBox_Documents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_DocumentsActionPerformed(evt);
            }
        });
        jPanel42.add(jComboBox_Documents);
        jComboBox_Documents.setBounds(597, 62, 367, 39);

        jButton1.setBackground(new java.awt.Color(169, 224, 49));
        jButton1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("PRINT DOCUMENT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel42.add(jButton1);
        jButton1.setBounds(20, 590, 570, 50);

        jPanel_Print.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jPanel_Print.add(jLabel4);
        jLabel4.setBounds(280, 140, 40, 25);

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jPanel_Print.add(jLabel7);
        jLabel7.setBounds(250, 140, 40, 25);

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jPanel_Print.add(jLabel5);
        jLabel5.setBounds(230, 340, 110, 30);

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel_Print.add(jLabel1);
        jLabel1.setBounds(110, 420, 150, 30);
        jPanel_Print.add(jLabel3);
        jLabel3.setBounds(290, 160, 50, 30);

        jLabel_image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jPanel_Print.add(jLabel_image);
        jLabel_image.setBounds(6, 6, 355, 530);

        jPanel42.add(jPanel_Print);
        jPanel_Print.setBounds(597, 107, 367, 543);

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

        jPanel42.add(jScrollPane3);
        jScrollPane3.setBounds(20, 90, 570, 280);

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("DOCUMENT TRANSACTION :");
        jPanel42.add(jLabel6);
        jLabel6.setBounds(20, 380, 380, 30);

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("LIST OF REQUEST :");
        jPanel42.add(jLabel8);
        jLabel8.setBounds(20, 50, 270, 40);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("AMOUNT TO PAY:");
        jPanel42.add(jLabel9);
        jLabel9.setBounds(20, 420, 160, 40);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("AMOUNT RECIEVED:");
        jPanel42.add(jLabel10);
        jLabel10.setBounds(20, 460, 160, 30);

        txtAmountRecieved.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel42.add(txtAmountRecieved);
        txtAmountRecieved.setBounds(180, 460, 410, 30);

        txtPay.setEditable(false);
        txtPay.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtPay.setFocusable(false);
        jPanel42.add(txtPay);
        txtPay.setBounds(180, 420, 410, 30);

        cmdReceiptPreview.setBackground(new java.awt.Color(102, 102, 102));
        cmdReceiptPreview.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        cmdReceiptPreview.setForeground(new java.awt.Color(255, 255, 255));
        cmdReceiptPreview.setText("CONFIRM TRANSACTION");
        cmdReceiptPreview.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        cmdReceiptPreview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdReceiptPreviewActionPerformed(evt);
            }
        });
        jPanel42.add(cmdReceiptPreview);
        cmdReceiptPreview.setBounds(20, 510, 570, 30);

        jButton2.setBackground(new java.awt.Color(102, 102, 102));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("CANCEL TRANSACTION");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel42.add(jButton2);
        jButton2.setBounds(20, 550, 570, 30);

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
         
    public ImageIcon ResizeImage(String imgPath){
        ImageIcon MyImage = new ImageIcon(imgPath);
        Image img = MyImage.getImage();
        Image newImage = img.getScaledInstance(jLabelProfileImage.getWidth(), jLabelProfileImage.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
        }
    
    private void jComboBox_DocumentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_DocumentsActionPerformed
        // TODO add your handling code here:
        //BufferedImage picture = null;
        DashboardAdmin form = new DashboardAdmin();
        String LoginAs1 = form.Displayname.getText();
        String LoginAs2 = form.lblDate1.getText();
        //jLabel1
        jLabel1.setText(LoginAs2);
        jLabel5.setText(LoginAs1);
        
        jLabel3.setVisible(true);
        jLabel4.setVisible(true);
        jLabel7.setVisible(true);
        BufferedImage img = null;
        if(jComboBox_Documents.getSelectedItem()=="Select Document")
        {
            jLabel_image.setIcon(null);
        }
        else if(jComboBox_Documents.getSelectedItem()=="Barangay Clearance")
        {
             price = "50";
             ImageIcon picture = new ImageIcon("src/BarangayDocumentsPic/BarangayMactanClearance.jpg");
             Image image1 = picture.getImage().getScaledInstance(jLabel_image.getWidth(), jLabel_image.getHeight(), Image.SCALE_SMOOTH);
             jLabel_image.setIcon(new ImageIcon(image1));
             txtPay.setText(price);
        }
        else if(jComboBox_Documents.getSelectedItem()=="Barangay Permit")
        {
             price = "50";
             ImageIcon picture = new ImageIcon("src/BarangayDocumentsPic/BarangayMactanPermit.jpg");
             Image image1 = picture.getImage().getScaledInstance(jLabel_image.getWidth(), jLabel_image.getHeight(), Image.SCALE_SMOOTH);
             jLabel_image.setIcon(new ImageIcon(image1));
             txtPay.setText(price);
        }
        else if(jComboBox_Documents.getSelectedItem()=="Barangay Certificate")
        {
             price = "70";
             ImageIcon picture = new ImageIcon("src/BarangayDocumentsPic/BarangayMactanCertificate.jpg");
             Image image1 = picture.getImage().getScaledInstance(jLabel_image.getWidth(), jLabel_image.getHeight(), Image.SCALE_SMOOTH);
             jLabel_image.setIcon(new ImageIcon(image1));
             txtPay.setText(price);
        }       
    }//GEN-LAST:event_jComboBox_DocumentsActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //jButton1Action(jLabel_image);
        
    
         PrinterJob pj = PrinterJob.getPrinterJob();
  pj.setJobName(" Print Component ");
  pj.setPrintable (new Printable() {    
    public int print(Graphics pg, PageFormat pf, int pageNum){
      if (pageNum > 0){
      return Printable.NO_SUCH_PAGE;
      }
      Graphics2D g2 = (Graphics2D) pg;
      g2.translate(pf.getImageableX(), pf.getImageableY());
      g2.scale(1.65,1.65);
      jPanel_Print.paint(g2);
      //JOptionPane.showMessageDialog(null, "Reciept Printed...","Barangay System",JOptionPane.INFORMATION_MESSAGE);
      return Printable.PAGE_EXISTS;
    }
  });
    boolean ok = pj.printDialog();
    if(ok)
    {
      try
      {
        pj.print();
      }
      catch(PrinterException ex)
      {
      
      }
              
    }
      
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:        
        jLabel3.setVisible(true);
        jLabel4.setVisible(true);
        jLabel7.setVisible(true);
        int i = jTable2.getSelectedRow();
        TableModel model = jTable2.getModel();
        //jLabel7.setText(model.getValueAt(i,0).toString());
        jLabel7.setText(model.getValueAt(i,1).toString());
        jLabel4.setText(model.getValueAt(i,2).toString());
        jLabel3.setText(model.getValueAt(i,3).toString());        
    }//GEN-LAST:event_jTable2MouseClicked

    private void cmdReceiptPreviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdReceiptPreviewActionPerformed
        // TODO add your handling code here:
          
        if(Integer.valueOf(txtAmountRecieved.getText())==Integer.valueOf(txtPay.getText()))
        {     
            JOptionPane.showMessageDialog(null, "Payment Successfully Transact!","Barangay System",JOptionPane.OK_OPTION);
            jButton1.setAction(null);    
            Okay = true;
        }    
        else if(Integer.valueOf(txtAmountRecieved.getText())!=Integer.valueOf(txtPay.getText()))
        {
            JOptionPane.showMessageDialog(null, "Payment Amount is larger or smaller than Recieved Amount...","Barangay System",JOptionPane.ERROR_MESSAGE); 
            jButton1.setAction(null);  
            Okay = false;
        }
    }//GEN-LAST:event_cmdReceiptPreviewActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        txtPay.setText(null);
        txtAmountRecieved.setText(null);
        jLabel3.setText(null);
        jLabel4.setText(null);
        jLabel7.setText(null);
    // TODO add your handling code here:
        // this.dispose();
//        String LoginAs = lblLoginAs.getText();
//        String UserID = lblUserID.getText();
//        String Username = lblUsername.getText();
//        String userType = lblUserType.getText();
//        Residents ob = new Residents();
//        ob.lblLoginAs.setText(LoginAs);
//        ob.lblUserID.setText(UserID);
//        ob.lblUsername.setText(Username);
//        ob.lblUserType.setText(userType);
//        ob.setVisible(true);
//        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed
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
    private javax.swing.JButton cmdReceiptPreview;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton49;
    private javax.swing.JComboBox<String> jComboBox_Documents;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelProfileImage;
    private javax.swing.JLabel jLabel_image;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel_Print;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    public javax.swing.JTable jTable2;
    private javax.swing.JTextField txtAmountRecieved;
    public static javax.swing.JTextField txtPay;
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
