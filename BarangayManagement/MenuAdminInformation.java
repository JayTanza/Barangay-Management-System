/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BarangayManagement;
import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvSaveImage;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import com.sun.jdi.connect.spi.Connection;
import java.awt.event.KeyEvent;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
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
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.proteanit.sql.DbUtils;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
/**
 *
 * @author Jay Tanza
 */
public class MenuAdminInformation extends javax.swing.JInternalFrame {

    /**
     * Creates new form Menu1
     */
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String imgPath = null;
    
    public MenuAdminInformation() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
    }
//    static
//    {
//      File file = new File("");
//    }
//    ////////////////////////////////////START CAMERA CODE////////////////////////////////////////////
//    private DaemonThread myThread = null;
//    private VideoCapture webSource = null;
//    private final Mat frame = new Mat(1000, 1000, 1);
//    private final MatOfByte mem = new MatOfByte();
//    private class DaemonThread implements Runnable {
//        protected volatile boolean runnable = false;
//        private JLabel jLabelProfileImage;
//        public DaemonThread(JLabel displayLabel) {
//            this.jLabelProfileImage = displayLabel;
//        }
//        @Override
//        public void run() {
//            synchronized (this) {
//                while (runnable) {
//                    if (webSource.grab()) {
//                        try {
//                            webSource.retrieve(frame);
//                            Highgui.imencode(".bmp", frame, mem);
//                            Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
//
//                            BufferedImage buff = (BufferedImage) im;
//                            Graphics g = jLabelProfileImage.getGraphics();
//
//                            if (g.drawImage(buff, 1, 1, jLabelProfileImage.getWidth(), jLabelProfileImage.getHeight(), null)) {
//                                if (runnable == false) {
//                                    this.wait();
//                                }
//                            }
//                        } catch (Exception e) {
//                        }
//                    }
//                }
//            }
//        }
//    }
///////////////////////////////////END CAMERA CODE/////////////////////////////////////////////


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel42 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField_Firstname = new javax.swing.JTextField();
        jTextField_Age = new javax.swing.JTextField();
        jTextField_Phonenumber = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField_Lastname = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField_Middlename = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField_Gender = new javax.swing.JTextField();
        jTextField_DOB = new javax.swing.JTextField();
        jTextField_Address1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField_ID = new javax.swing.JTextField();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabelProfileImage = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton52 = new javax.swing.JButton();
        jButton54 = new javax.swing.JButton();
        jButton53 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(995, 692));

        jPanel42.setBackground(new java.awt.Color(0, 0, 0));
        jPanel42.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(169, 224, 49));
        jLabel1.setText("FIRST NAME :");
        jPanel42.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, -1, -1));

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(169, 224, 49));
        jLabel5.setText("AGE:");
        jPanel42.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 240, -1, -1));

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(169, 224, 49));
        jLabel6.setText("PHONE NUMBER:");
        jPanel42.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 240, -1, -1));

        jTextField_Firstname.setEditable(false);
        jTextField_Firstname.setBackground(new java.awt.Color(31, 36, 42));
        jTextField_Firstname.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_Firstname.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_Firstname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_Firstname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_FirstnameActionPerformed(evt);
            }
        });
        jPanel42.add(jTextField_Firstname, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, 180, 40));

        jTextField_Age.setEditable(false);
        jTextField_Age.setBackground(new java.awt.Color(31, 36, 42));
        jTextField_Age.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_Age.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_Age.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_Age.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_AgeActionPerformed(evt);
            }
        });
        jPanel42.add(jTextField_Age, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 260, 180, 40));

        jTextField_Phonenumber.setEditable(false);
        jTextField_Phonenumber.setBackground(new java.awt.Color(31, 36, 42));
        jTextField_Phonenumber.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_Phonenumber.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_Phonenumber.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_Phonenumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_PhonenumberActionPerformed(evt);
            }
        });
        jTextField_Phonenumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_PhonenumberKeyTyped(evt);
            }
        });
        jPanel42.add(jTextField_Phonenumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 260, 180, 40));

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(169, 224, 49));
        jLabel8.setText("ADDRESS :");
        jPanel42.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 320, -1, -1));

        jTextField_Lastname.setEditable(false);
        jTextField_Lastname.setBackground(new java.awt.Color(31, 36, 42));
        jTextField_Lastname.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_Lastname.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_Lastname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_Lastname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_LastnameActionPerformed(evt);
            }
        });
        jPanel42.add(jTextField_Lastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 340, 180, 40));

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(169, 224, 49));
        jLabel4.setText("MIDDLE NAME :");
        jPanel42.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 240, -1, -1));

        jTextField_Middlename.setEditable(false);
        jTextField_Middlename.setBackground(new java.awt.Color(31, 36, 42));
        jTextField_Middlename.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_Middlename.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_Middlename.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_Middlename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_MiddlenameActionPerformed(evt);
            }
        });
        jPanel42.add(jTextField_Middlename, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 260, 180, 40));

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(169, 224, 49));
        jLabel7.setText("DATE OF BIRTH:");
        jPanel42.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 160, -1, -1));

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(169, 224, 49));
        jLabel9.setText("POSITION :");
        jPanel42.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 320, -1, -1));

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(169, 224, 49));
        jLabel10.setText("LAST NAME :");
        jPanel42.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 320, -1, -1));

        jTextField_Gender.setEditable(false);
        jTextField_Gender.setBackground(new java.awt.Color(31, 36, 42));
        jTextField_Gender.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_Gender.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_Gender.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_Gender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_GenderActionPerformed(evt);
            }
        });
        jPanel42.add(jTextField_Gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 340, 180, 40));

        jTextField_DOB.setEditable(false);
        jTextField_DOB.setBackground(new java.awt.Color(31, 36, 42));
        jTextField_DOB.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_DOB.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_DOB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_DOB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_DOBActionPerformed(evt);
            }
        });
        jPanel42.add(jTextField_DOB, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 180, 180, 40));

        jTextField_Address1.setEditable(false);
        jTextField_Address1.setBackground(new java.awt.Color(31, 36, 42));
        jTextField_Address1.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_Address1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_Address1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_Address1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Address1ActionPerformed(evt);
            }
        });
        jPanel42.add(jTextField_Address1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 340, 180, 40));

        jLabel12.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(169, 224, 49));
        jLabel12.setText("ID:");
        jPanel42.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 160, -1, -1));

        jTextField_ID.setEditable(false);
        jTextField_ID.setBackground(new java.awt.Color(31, 36, 42));
        jTextField_ID.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_ID.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_ID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_IDActionPerformed(evt);
            }
        });
        jPanel42.add(jTextField_ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 180, 180, 40));

        jDesktopPane1.setBackground(new java.awt.Color(31, 36, 42));

        jLabelProfileImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));

        jDesktopPane1.setLayer(jLabelProfileImage, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDesktopPane1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabelProfileImage, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDesktopPane1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabelProfileImage, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel42.add(jDesktopPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 250, 220));

        jPanel1.setBackground(new java.awt.Color(21, 25, 28));

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(169, 224, 49));
        jLabel2.setText("PERSONAL INFORMATION");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(159, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(124, 124, 124))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        jPanel42.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 100));

        jButton52.setBackground(new java.awt.Color(0, 0, 0));
        jButton52.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton52.setForeground(new java.awt.Color(169, 224, 49));
        jButton52.setText("CHANGE PROFILE PICTURE");
        jButton52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton52ActionPerformed(evt);
            }
        });
        jPanel42.add(jButton52, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 400, 250, 40));

        jButton54.setBackground(new java.awt.Color(0, 0, 0));
        jButton54.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton54.setForeground(new java.awt.Color(169, 224, 49));
        jButton54.setText("EDIT");
        jButton54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton54ActionPerformed(evt);
            }
        });
        jPanel42.add(jButton54, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 460, 250, 40));

        jButton53.setBackground(new java.awt.Color(0, 0, 0));
        jButton53.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton53.setForeground(new java.awt.Color(169, 224, 49));
        jButton53.setText("UPDATE");
        jButton53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton53ActionPerformed(evt);
            }
        });
        jPanel42.add(jButton53, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 520, 250, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_MiddlenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_MiddlenameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_MiddlenameActionPerformed

    private void jTextField_LastnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_LastnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_LastnameActionPerformed

    private void jTextField_PhonenumberKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_PhonenumberKeyTyped
        // TODO add your handling code here:

        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE))
        {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_PhonenumberKeyTyped

    private void jTextField_PhonenumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_PhonenumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_PhonenumberActionPerformed

    private void jTextField_AgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_AgeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_AgeActionPerformed

    private void jTextField_FirstnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_FirstnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_FirstnameActionPerformed
    public ImageIcon ResizeImage(String imgPath){
        ImageIcon MyImage = new ImageIcon(imgPath);
        Image img = MyImage.getImage();
        Image newImage = img.getScaledInstance(jLabelProfileImage.getWidth(), jLabelProfileImage.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
        }     
    private void jTextField_GenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_GenderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_GenderActionPerformed

    private void jTextField_DOBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_DOBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_DOBActionPerformed

    private void jTextField_Address1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Address1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Address1ActionPerformed

    private void jTextField_IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_IDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_IDActionPerformed

    private void jButton52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton52ActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.jpg","*.png","*.gif","*.bmp");
        fileChooser.addChoosableFileFilter(filter);
        int result=fileChooser.showSaveDialog(null);
        if(result==JFileChooser.APPROVE_OPTION){
            File selectedFile = fileChooser.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            jLabelProfileImage.setIcon(ResizeImage(path));
            imgPath = path;
            //cmdRegisterWithImage.setVisible(true);
            //cmdRegister.setVisible(false);
        }else if(result==JFileChooser.CANCEL_OPTION){
            jLabelProfileImage.setIcon(null);
            //cmdRegister.setVisible(true);
            //cmdRegisterWithImage.setVisible(false);
            JOptionPane.showMessageDialog(null, "Select an Image","Barangay System",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton52ActionPerformed

    private void jButton54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton54ActionPerformed
        // TODO add your handling code here:
        //jTextField_Firstname.setEditable(true);
        jTextField_Phonenumber.setEditable(true);
        jTextField_Age.setEditable(true);
        jTextField_Address1.setEditable(true);

    }//GEN-LAST:event_jButton54ActionPerformed

    private void jButton53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton53ActionPerformed
        // TODO add your handling code here:
        try{
            String id = jTextField_ID.getText();
            String age = jTextField_Age.getText();
            String address = jTextField_Address1.getText();
            String phonenumber = jTextField_Phonenumber.getText();
            //byte img = jLabelProfileImage.getIcon;

            String registerUserQuery = "UPDATE `adminaccountinfo` SET `profilepic`=?, `Age`='"+age+"',`Address`='"+address+"',`Phonenumber`='"+phonenumber+"' WHERE `ID`='"+id+"'";
            //String registerUserQuery = "UPDATE `residentaccountinfo` SET`profilepic`=?,`Age`=?,`Address`=?,`Phonenumber`=? WHERE `ResidentID`=?";
            //        String age = jTextField_Age.getText();
            //        String phonenumber = jTextField_Phonenumber.getText();
            //        String address = jTextField_Address1.getText();

            InputStream img = new FileInputStream(new File(imgPath));
            pst = MyConnection.getConnection().prepareStatement(registerUserQuery);
            pst.setBlob(1,img);
            //           pst.setString(1,jTextField_Age.getText());
            //           pst.setString(2,jTextField_Address1.getText());
            //           pst.setString(3,jTextField_Phonenumber.getText());
            pst.executeUpdate();
        }
        catch(Exception e){JOptionPane.showMessageDialog(null, e);}
        //con.close();
        JOptionPane.showMessageDialog(null, "User Successfully Registered","Barangay System",JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_jButton53ActionPerformed
//    private static File get_image_file;
//    private static final SecureRandom RAND = new SecureRandom();
//    private static String IMAGE_FILE_NAME = null;
//    
//    private void setWebCamCapturedImageOnLabel(JLabel image)
//    {
//       try
//       {
//         stopWebCamera();
//         if(get_image_file != null)
//         {
//           ImageIcon imageIcon = new ImageIcon(new ImageIcon(IMAGE_FILE_NAME).getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_DEFAULT));
//           image.setIcon(imageIcon);
//         }
//         
//       }catch(Exception e)
//       {
//         JOptionPane.showMessageDialog(null, e, "Warning", 0);
//       }
//    }
    /**public void showTable() 
    {
       String registerUserQuery = "SELECT * FROM residentaccountinfo";
       try {
        ps = MyConnection.getConnection().prepareStatement(registerUserQuery);
        rs = ps.executeQuery();
       jTable1.setModel(DbUtils.resultSetToTableModel(rs));
       
       }catch (SQLException ex) {
                   JOptionPane.showMessageDialog(null, ex);
                }
    }**/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton54;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JLabel jLabelProfileImage;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel42;
    private javax.swing.JSplitPane jSplitPane1;
    public javax.swing.JTextField jTextField_Address1;
    public javax.swing.JTextField jTextField_Age;
    public javax.swing.JTextField jTextField_DOB;
    public javax.swing.JTextField jTextField_Firstname;
    public javax.swing.JTextField jTextField_Gender;
    public javax.swing.JTextField jTextField_ID;
    public javax.swing.JTextField jTextField_Lastname;
    public javax.swing.JTextField jTextField_Middlename;
    public javax.swing.JTextField jTextField_Phonenumber;
    // End of variables declaration//GEN-END:variables

    private Icon ResizeImage(File f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private BufferedImage resize(BufferedImage bi, int width, int height) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setLocationRelativeTo(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

   
}
