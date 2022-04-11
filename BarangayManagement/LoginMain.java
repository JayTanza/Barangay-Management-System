 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BarangayManagement;

import com.mysql.jdbc.Statement;
import com.sun.jdi.connect.spi.Connection;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author Jay Tanza
 */
public class LoginMain extends javax.swing.JFrame {

    
    /*Global Variables*/
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String UserName = "";
    String Position  = "";
    String reportGetID = "";
    String reportGetID2 = "";
    String imgPath = null;
    //CreateStament Statement = null;
    //String Username = null;
    //jTabbedPane1.setEnabled(1, false);
    int ctr = 0;
    private CardLayout cardLayout;
    void initImage()
    {
       String[] filesName={"ahong1.jpg","ahong2.jpg","ahong3.jpg","ahong4.jpg","ahong5.jpg","ahong6.jpg",};
       for(String s:filesName)
       {
         ImageIcon icon = new ImageIcon("src/Images2/"+s);
         JLabel label = new JLabel(icon);
         jLabel_ImageSlider.add(label);
         Image image1 = icon.getImage().getScaledInstance(jLabel_ImageSlider.getWidth(), jLabel_ImageSlider.getHeight(), Image.SCALE_SMOOTH);
         jLabel_ImageSlider.setIcon(new ImageIcon(image1));
       }
       // create layout
       cardLayout = new CardLayout();
       jLabel_ImageSlider.setLayout(cardLayout);
    }
    
    public LoginMain() {
        initComponents();
        //con = (Connection) MyConnection.getConnection();
        warning.setVisible(false);
        warning1.setVisible(false);
        WarningSign.setVisible(false);
        
        showDate(); // Class para sa Date
        showTime(); // Class para sa Time
        initImage();
        //showSlide();
        setIconImage();

    }
    
    
//    void showSlide()
//    {
////      BufferedImage img = null;
////        if(jRadioButton6.isSelected()==true)
////        {
////            ImageIcon picture = new ImageIcon("src/Images2/ahong cover1.jpg");
////             Image image1 = picture.getImage().getScaledInstance(jLabel_ImageSlider.getWidth(), jLabel_ImageSlider.getHeight(), Image.SCALE_SMOOTH);
////             jLabel_ImageSlider.setIcon(new ImageIcon(image1));  
////        }
////        else if(jRadioButton7.isSelected()==true)
////        {
////             ImageIcon picture = new ImageIcon("src/Images2/ahong cover1.jpg");
////             Image image1 = picture.getImage().getScaledInstance(jLabel_ImageSlider.getWidth(), jLabel_ImageSlider.getHeight(), Image.SCALE_SMOOTH);
////             jLabel_ImageSlider.setIcon(new ImageIcon(image1));            
////        }
////        else if(jRadioButton8.isSelected()==true)
////        {
////             ImageIcon picture = new ImageIcon("src/BarangayDocumentsPic/barangay clearance.jfif");
////             Image image1 = picture.getImage().getScaledInstance(jLabel_ImageSlider.getWidth(), jLabel_ImageSlider.getHeight(), Image.SCALE_SMOOTH);
////             jLabel_ImageSlider.setIcon(new ImageIcon(image1));            
////        }
////        else if(jRadioButton9.isSelected()==true)
////        {
////             ImageIcon picture = new ImageIcon("src/BarangayDocumentsPic/barangay clearance.jfif");
////             Image image1 = picture.getImage().getScaledInstance(jLabel_ImageSlider.getWidth(), jLabel_ImageSlider.getHeight(), Image.SCALE_SMOOTH);
////             jLabel_ImageSlider.setIcon(new ImageIcon(image1));            
////        }
////        else if(jRadioButton10.isSelected()==true)
////        {
////             ImageIcon picture = new ImageIcon("src/BarangayDocumentsPic/barangay clearance.jfif");
////             Image image1 = picture.getImage().getScaledInstance(jLabel_ImageSlider.getWidth(), jLabel_ImageSlider.getHeight(), Image.SCALE_SMOOTH);
////             jLabel_ImageSlider.setIcon(new ImageIcon(image1));            
////        }
//    }
    
    int pos = 0;
    void showDate() {
        Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("MM/dd/yyyy");
        lblDate.setText("Date: " + s.format(d));
        lblDate1.setText("Date: " + s.format(d));
        lblDate2.setText("Date: " + s.format(d));
    }
    public ImageIcon ResizeImage2(String imgPath){
        ImageIcon MyImage = new ImageIcon(imgPath);
        Image img = MyImage.getImage();
        Image newImage = img.getScaledInstance(jLabel_ImageSlider.getWidth(), jLabel_ImageSlider.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
        }
      
    void showTime() {
        new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            Date d = new Date();
        SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss a");
        lblTime.setText("Time: " + s.format(d));
        lblTime1.setText("Time: " + s.format(d));
        lblTime2.setText("Time: " + s.format(d));
            }
            
        }
        ).start();
    }
    void report(){
        Connection con=null;
        String report = "INSERT INTO `audittrail`(`Date`, `Time`) VALUES (?,?)";
        //String report2 = "INSERT INTO `useraccountinfo`(`Fullname`) VALUES (?)";
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
           // pst.setString(1, "Unknown User: "+ jTextField_Username.getText());
            //pst.setString(1, LoginAs);
            
            pst.executeUpdate();
            }catch(Exception e){
    JOptionPane.showMessageDialog(null, e);
    }
    }
        void report2(){
        Connection con=null;
        
        String report = "INSERT INTO `adminaccountInfo`(ID) VALUES (?)";

    try{
            pst = MyConnection.getConnection().prepareStatement(report);
            pst.setString(1, reportGetID);
            pst.executeUpdate();
    }catch(Exception e){
    JOptionPane.showMessageDialog(null, e);
    }
    }
        void report3(){
        Connection con=null;
        
        String report = "INSERT INTO `useraccountInfo`(ID) VALUES (?)";

    try{
            pst = MyConnection.getConnection().prepareStatement(report);
            pst.setString(1, reportGetID);
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

        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel_ImageSlider = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        SignUpResident = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField_Fullname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField_Username2 = new javax.swing.JTextField();
        jPasswordField4 = new javax.swing.JPasswordField();
        jPasswordField5 = new javax.swing.JPasswordField();
        jLabel40 = new javax.swing.JLabel();
        jComboBox_Question1 = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        jTextField_Answer = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        warning = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        ClearResident = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField_fullname = new javax.swing.JTextField();
        jTextField_answer = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jTextField_username = new javax.swing.JTextField();
        jButton_Search = new javax.swing.JButton();
        jTextField_securityquestion = new javax.swing.JTextField();
        Password = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        warning1 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jTextField_Fullname1 = new javax.swing.JTextField();
        jTextField_Username3 = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jPasswordField6 = new javax.swing.JPasswordField();
        jLabel47 = new javax.swing.JLabel();
        jPasswordField7 = new javax.swing.JPasswordField();
        jLabel48 = new javax.swing.JLabel();
        jComboBox_Question2 = new javax.swing.JComboBox<>();
        jLabel49 = new javax.swing.JLabel();
        jComboBox_Question3 = new javax.swing.JComboBox<>();
        jLabel50 = new javax.swing.JLabel();
        jTextField_Answer1 = new javax.swing.JTextField();
        SignUpAdmin = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        ClearAdmin = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jButton18 = new javax.swing.JButton();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jTextField_fullname1 = new javax.swing.JTextField();
        jTextField_answer1 = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        jTextField_username1 = new javax.swing.JTextField();
        jButton_Search1 = new javax.swing.JButton();
        jTextField_securityquestion1 = new javax.swing.JTextField();
        Password1 = new javax.swing.JTextField();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jTextField_Firstname = new javax.swing.JTextField();
        jTextField_Lastname = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jTextField_Middlename = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jDateChooser_DOB = new com.toedter.calendar.JDateChooser();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jTextField_Age = new javax.swing.JTextField();
        jTextField_Phonenumber = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jTextField_Address = new javax.swing.JTextField();
        jComboBox_Gender = new javax.swing.JComboBox<>();
        jDesktopPane4 = new javax.swing.JDesktopPane();
        jLabelProfileImage3 = new javax.swing.JLabel();
        jButton49 = new javax.swing.JButton();
        jButton50 = new javax.swing.JButton();
        jLabel70 = new javax.swing.JLabel();
        jButton51 = new javax.swing.JButton();
        jLabel71 = new javax.swing.JLabel();
        jPasswordField9 = new javax.swing.JPasswordField();
        jLabel72 = new javax.swing.JLabel();
        jPasswordField10 = new javax.swing.JPasswordField();
        jComboBox_Question4 = new javax.swing.JComboBox<>();
        jLabel73 = new javax.swing.JLabel();
        jTextField_Answer2 = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        jButton52 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jTextField_Firstname1 = new javax.swing.JTextField();
        jTextField_Lastname1 = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        jTextField_Middlename1 = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jDateChooser_DOB1 = new com.toedter.calendar.JDateChooser();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jTextField_Age1 = new javax.swing.JTextField();
        jTextField_Phonenumber1 = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jTextField_Address1 = new javax.swing.JTextField();
        jComboBox_Gender1 = new javax.swing.JComboBox<>();
        jDesktopPane5 = new javax.swing.JDesktopPane();
        jLabelProfileImage4 = new javax.swing.JLabel();
        jButton53 = new javax.swing.JButton();
        jButton54 = new javax.swing.JButton();
        jLabel83 = new javax.swing.JLabel();
        jButton55 = new javax.swing.JButton();
        jLabel84 = new javax.swing.JLabel();
        jPasswordField11 = new javax.swing.JPasswordField();
        jLabel85 = new javax.swing.JLabel();
        jPasswordField12 = new javax.swing.JPasswordField();
        jComboBox_Question5 = new javax.swing.JComboBox<>();
        jLabel86 = new javax.swing.JLabel();
        jTextField_Answer3 = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        jButton56 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jTextField_Username = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPasswordField_Password = new javax.swing.JPasswordField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        CheckBox = new javax.swing.JCheckBox();
        jButton5 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jButtonResidentLogin = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        lblTime1 = new javax.swing.JLabel();
        lblDate1 = new javax.swing.JLabel();
        jButton21 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jTextField_Username1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jPasswordField_Password1 = new javax.swing.JPasswordField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        CheckBox1 = new javax.swing.JCheckBox();
        jButton7 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jButtonAdminLogin = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        lblTime2 = new javax.swing.JLabel();
        lblDate2 = new javax.swing.JLabel();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        jPasswordField8 = new javax.swing.JPasswordField();
        jLabel62 = new javax.swing.JLabel();
        AdminAccess = new javax.swing.JButton();
        jButtonResidentLogin1 = new javax.swing.JButton();
        WarningSign = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Male_User_35px.png"))); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(21, 25, 28));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(31, 36, 42));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBackground(new java.awt.Color(31, 36, 42));

        jPanel5.setBackground(new java.awt.Color(31, 36, 42));

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 25)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(169, 224, 49));
        jLabel9.setText("BARANGAY INFORMATION ");

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 1, 25)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(169, 224, 49));
        jLabel10.setText("& MANAGEMENT SYSTEM");

        jLabel_ImageSlider.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images2/ahong cover1.jpg"))); // NOI18N
        jLabel_ImageSlider.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(169, 224, 49), 2, true));

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Cancel_32px.png"))); // NOI18N
        jButton14.setToolTipText("Close");
        jButton14.setBorder(null);
        jButton14.setBorderPainted(false);
        jButton14.setContentAreaFilled(false);
        jButton14.setRequestFocusEnabled(false);
        jButton14.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Cancel_30px_3.png"))); // NOI18N
        jButton14.setVerifyInputWhenFocusTarget(false);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Minus_32px_1.png"))); // NOI18N
        jButton15.setToolTipText("Minimize");
        jButton15.setBorder(null);
        jButton15.setBorderPainted(false);
        jButton15.setContentAreaFilled(false);
        jButton15.setFocusPainted(false);
        jButton15.setRequestFocusEnabled(false);
        jButton15.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Minus_30px_3.png"))); // NOI18N
        jButton15.setVerifyInputWhenFocusTarget(false);
        jButton15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton15MouseClicked(evt);
            }
        });
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton1.setText("PREVIOUS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton6.setText("NEXT");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_ImageSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(jButton1)
                                .addGap(48, 48, 48)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 45, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_ImageSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton6))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab1", jPanel5);

        jPanel7.setBackground(new java.awt.Color(31, 36, 42));

        SignUpResident.setBackground(new java.awt.Color(169, 224, 49));
        SignUpResident.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        SignUpResident.setForeground(new java.awt.Color(21, 25, 28));
        SignUpResident.setText("Sign Up");
        SignUpResident.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignUpResidentActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(169, 224, 49));
        jLabel7.setText("Confirm Password");

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(169, 224, 49));
        jLabel6.setText("Password");

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(169, 224, 49));
        jLabel1.setText("Username");

        jTextField_Fullname.setBackground(new java.awt.Color(31, 36, 42));
        jTextField_Fullname.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_Fullname.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_Fullname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_Fullname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_FullnameActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(169, 224, 49));
        jLabel3.setText("Fullname");

        jLabel8.setBackground(new java.awt.Color(169, 224, 49));
        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(169, 224, 49));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Back_To_32px_2.png"))); // NOI18N
        jLabel8.setText("Back");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        jTextField_Username2.setBackground(new java.awt.Color(31, 36, 42));
        jTextField_Username2.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_Username2.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_Username2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_Username2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_Username2KeyReleased(evt);
            }
        });

        jPasswordField4.setBackground(new java.awt.Color(31, 36, 42));
        jPasswordField4.setForeground(new java.awt.Color(255, 255, 255));
        jPasswordField4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));

        jPasswordField5.setBackground(new java.awt.Color(31, 36, 42));
        jPasswordField5.setForeground(new java.awt.Color(255, 255, 255));
        jPasswordField5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));

        jLabel40.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(169, 224, 49));
        jLabel40.setText("Security Question");

        jComboBox_Question1.setBackground(new java.awt.Color(31, 36, 42));
        jComboBox_Question1.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jComboBox_Question1.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox_Question1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "What's your favorite food?", "What's your favorite anime?", "What's your favorite color?", "What's your favorite place?", "What's your favorite song?" }));
        jComboBox_Question1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));

        jLabel31.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(169, 224, 49));
        jLabel31.setText("Answer");

        jTextField_Answer.setBackground(new java.awt.Color(31, 36, 42));
        jTextField_Answer.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_Answer.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_Answer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_Answer.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_AnswerFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_AnswerFocusLost(evt);
            }
        });
        jTextField_Answer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_AnswerActionPerformed(evt);
            }
        });

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Cancel_32px.png"))); // NOI18N
        jButton12.setToolTipText("Close");
        jButton12.setBorder(null);
        jButton12.setBorderPainted(false);
        jButton12.setContentAreaFilled(false);
        jButton12.setRequestFocusEnabled(false);
        jButton12.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Cancel_30px_3.png"))); // NOI18N
        jButton12.setVerifyInputWhenFocusTarget(false);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Minus_32px_1.png"))); // NOI18N
        jButton13.setToolTipText("Minimize");
        jButton13.setBorder(null);
        jButton13.setBorderPainted(false);
        jButton13.setContentAreaFilled(false);
        jButton13.setFocusPainted(false);
        jButton13.setRequestFocusEnabled(false);
        jButton13.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Minus_30px_3.png"))); // NOI18N
        jButton13.setVerifyInputWhenFocusTarget(false);
        jButton13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton13MouseClicked(evt);
            }
        });
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 33)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(169, 224, 49));
        jLabel4.setText("CREATE ACCOUNT");

        warning.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        warning.setForeground(new java.awt.Color(255, 51, 51));
        warning.setText("Sorry Username Already Taken!");

        jLabel53.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(169, 224, 49));
        jLabel53.setText("RESIDENT");

        ClearResident.setBackground(new java.awt.Color(169, 224, 49));
        ClearResident.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        ClearResident.setForeground(new java.awt.Color(21, 25, 28));
        ClearResident.setText("Clear");
        ClearResident.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearResidentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(100, 100, 100)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(warning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                        .addComponent(jTextField_Fullname, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField_Username2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                        .addGap(139, 139, 139)
                                        .addComponent(jLabel8)))
                                .addComponent(jComboBox_Question1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPasswordField5, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                    .addComponent(SignUpResident, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(ClearResident, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jPasswordField4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField_Answer)))
                        .addGap(0, 8, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel53)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                    .addContainerGap(94, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addGap(81, 81, 81)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(warning)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_Fullname, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_Username2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordField4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordField5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox_Question1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_Answer, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SignUpResident, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ClearResident, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(38, 38, 38))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(55, 55, 55)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(538, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("tab2", jPanel7);

        jPanel8.setBackground(new java.awt.Color(31, 36, 42));

        jLabel27.setFont(new java.awt.Font("Trebuchet MS", 1, 33)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(169, 224, 49));
        jLabel27.setText("ACCOUNT RECOVERY");

        jLabel36.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(169, 224, 49));
        jLabel36.setText("Answer");

        jButton3.setBackground(new java.awt.Color(169, 224, 49));
        jButton3.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(21, 25, 28));
        jButton3.setText("Recover");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel37.setBackground(new java.awt.Color(169, 224, 49));
        jLabel37.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(169, 224, 49));
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Back_To_32px_2.png"))); // NOI18N
        jLabel37.setText("Back");
        jLabel37.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel37MouseClicked(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(169, 224, 49));
        jLabel38.setText("Security Question");

        jLabel39.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(169, 224, 49));
        jLabel39.setText("Password");

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(169, 224, 49));
        jLabel5.setText("Fullname");

        jTextField_fullname.setBackground(new java.awt.Color(31, 36, 42));
        jTextField_fullname.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_fullname.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_fullname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_fullname.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField_fullname.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextField_fullname.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        jTextField_fullname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_fullnameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_fullnameFocusLost(evt);
            }
        });
        jTextField_fullname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_fullnameActionPerformed(evt);
            }
        });
        jTextField_fullname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_fullnameKeyPressed(evt);
            }
        });

        jTextField_answer.setBackground(new java.awt.Color(31, 36, 42));
        jTextField_answer.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_answer.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_answer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));

        jLabel32.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(169, 224, 49));
        jLabel32.setText("Username");

        jTextField_username.setEditable(false);
        jTextField_username.setBackground(new java.awt.Color(51, 51, 51));
        jTextField_username.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_username.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_username.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_usernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_usernameFocusLost(evt);
            }
        });
        jTextField_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_usernameActionPerformed(evt);
            }
        });
        jTextField_username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_usernameKeyPressed(evt);
            }
        });

        jButton_Search.setBackground(new java.awt.Color(169, 224, 49));
        jButton_Search.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButton_Search.setForeground(new java.awt.Color(21, 25, 28));
        jButton_Search.setText("Search");
        jButton_Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SearchActionPerformed(evt);
            }
        });

        jTextField_securityquestion.setEditable(false);
        jTextField_securityquestion.setBackground(new java.awt.Color(51, 51, 51));
        jTextField_securityquestion.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_securityquestion.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_securityquestion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_securityquestion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_securityquestionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_securityquestionFocusLost(evt);
            }
        });
        jTextField_securityquestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_securityquestionActionPerformed(evt);
            }
        });
        jTextField_securityquestion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_securityquestionKeyPressed(evt);
            }
        });

        Password.setEditable(false);
        Password.setBackground(new java.awt.Color(51, 51, 51));
        Password.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        Password.setForeground(new java.awt.Color(255, 255, 255));
        Password.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        Password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                PasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                PasswordFocusLost(evt);
            }
        });
        Password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PasswordActionPerformed(evt);
            }
        });
        Password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PasswordKeyPressed(evt);
            }
        });

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Cancel_32px.png"))); // NOI18N
        jButton10.setToolTipText("Close");
        jButton10.setBorder(null);
        jButton10.setBorderPainted(false);
        jButton10.setContentAreaFilled(false);
        jButton10.setRequestFocusEnabled(false);
        jButton10.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Cancel_30px_3.png"))); // NOI18N
        jButton10.setVerifyInputWhenFocusTarget(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Minus_32px_1.png"))); // NOI18N
        jButton11.setToolTipText("Minimize");
        jButton11.setBorder(null);
        jButton11.setBorderPainted(false);
        jButton11.setContentAreaFilled(false);
        jButton11.setFocusPainted(false);
        jButton11.setRequestFocusEnabled(false);
        jButton11.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Minus_30px_3.png"))); // NOI18N
        jButton11.setVerifyInputWhenFocusTarget(false);
        jButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton11MouseClicked(evt);
            }
        });
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 54, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField_securityquestion, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField_fullname, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField_username, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField_answer)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                        .addComponent(jButton_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(44, 44, 44))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel37)
                .addGap(181, 181, 181))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_fullname, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_username, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_securityquestion, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_answer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel37)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab3", jPanel8);

        jPanel9.setBackground(new java.awt.Color(31, 36, 42));

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Minus_32px_1.png"))); // NOI18N
        jButton16.setToolTipText("Minimize");
        jButton16.setBorder(null);
        jButton16.setBorderPainted(false);
        jButton16.setContentAreaFilled(false);
        jButton16.setFocusPainted(false);
        jButton16.setRequestFocusEnabled(false);
        jButton16.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Minus_30px_3.png"))); // NOI18N
        jButton16.setVerifyInputWhenFocusTarget(false);
        jButton16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton16MouseClicked(evt);
            }
        });
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Cancel_32px.png"))); // NOI18N
        jButton17.setToolTipText("Close");
        jButton17.setBorder(null);
        jButton17.setBorderPainted(false);
        jButton17.setContentAreaFilled(false);
        jButton17.setRequestFocusEnabled(false);
        jButton17.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Cancel_30px_3.png"))); // NOI18N
        jButton17.setVerifyInputWhenFocusTarget(false);
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Trebuchet MS", 1, 33)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(169, 224, 49));
        jLabel43.setText("CREATE ACCOUNT");

        warning1.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        warning1.setForeground(new java.awt.Color(255, 51, 51));
        warning1.setText("Sorry Username Already Taken!");

        jLabel44.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(169, 224, 49));
        jLabel44.setText("Fullname");

        jLabel45.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(169, 224, 49));
        jLabel45.setText("Username");

        jTextField_Fullname1.setBackground(new java.awt.Color(31, 36, 42));
        jTextField_Fullname1.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_Fullname1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_Fullname1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_Fullname1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Fullname1ActionPerformed(evt);
            }
        });

        jTextField_Username3.setBackground(new java.awt.Color(31, 36, 42));
        jTextField_Username3.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_Username3.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_Username3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_Username3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_Username3KeyReleased(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(169, 224, 49));
        jLabel46.setText("Password");

        jPasswordField6.setBackground(new java.awt.Color(31, 36, 42));
        jPasswordField6.setForeground(new java.awt.Color(255, 255, 255));
        jPasswordField6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));

        jLabel47.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(169, 224, 49));
        jLabel47.setText("Confirm Password");

        jPasswordField7.setBackground(new java.awt.Color(31, 36, 42));
        jPasswordField7.setForeground(new java.awt.Color(255, 255, 255));
        jPasswordField7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));

        jLabel48.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(169, 224, 49));
        jLabel48.setText("Security Question");

        jComboBox_Question2.setBackground(new java.awt.Color(31, 36, 42));
        jComboBox_Question2.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jComboBox_Question2.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox_Question2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "What's your favorite food?", "What's your favorite anime?", "What's your favorite color?", "What's your favorite place?", "What's your favorite song?" }));
        jComboBox_Question2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));

        jLabel49.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(169, 224, 49));
        jLabel49.setText("Position");

        jComboBox_Question3.setBackground(new java.awt.Color(31, 36, 42));
        jComboBox_Question3.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jComboBox_Question3.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox_Question3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chairman", "Kagawad", "Treasurer", "Secretary", "SK Chairman", "SK Kagawad", "SK Secretary" }));
        jComboBox_Question3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));

        jLabel50.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(169, 224, 49));
        jLabel50.setText("Answer");

        jTextField_Answer1.setBackground(new java.awt.Color(31, 36, 42));
        jTextField_Answer1.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_Answer1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_Answer1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_Answer1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_Answer1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_Answer1FocusLost(evt);
            }
        });
        jTextField_Answer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Answer1ActionPerformed(evt);
            }
        });

        SignUpAdmin.setBackground(new java.awt.Color(169, 224, 49));
        SignUpAdmin.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        SignUpAdmin.setForeground(new java.awt.Color(21, 25, 28));
        SignUpAdmin.setText("Sign Up");
        SignUpAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignUpAdminActionPerformed(evt);
            }
        });

        jLabel51.setBackground(new java.awt.Color(169, 224, 49));
        jLabel51.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(169, 224, 49));
        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Back_To_32px_2.png"))); // NOI18N
        jLabel51.setText("Back");
        jLabel51.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel51.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel51MouseClicked(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(169, 224, 49));
        jLabel52.setText("OFFICIAL");

        ClearAdmin.setBackground(new java.awt.Color(169, 224, 49));
        ClearAdmin.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        ClearAdmin.setForeground(new java.awt.Color(21, 25, 28));
        ClearAdmin.setText("Clear");
        ClearAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearAdminActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel48)
                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(100, 100, 100)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel45)
                                    .addComponent(warning1)))
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jComboBox_Question3, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jPasswordField6, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField_Fullname1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField_Username3)
                                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPasswordField7, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                                    .addComponent(SignUpAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(ClearAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jTextField_Answer1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jComboBox_Question2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel51)
                        .addGap(189, 189, 189))))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel52)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel43)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(warning1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_Fullname1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_Username3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPasswordField6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPasswordField7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox_Question3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel48)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox_Question2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_Answer1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SignUpAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ClearAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel51)
                .addContainerGap())
        );

        jTabbedPane1.addTab("tab4", jPanel9);

        jPanel13.setBackground(new java.awt.Color(0, 0, 0));

        jPanel14.setBackground(new java.awt.Color(31, 36, 42));

        jLabel54.setFont(new java.awt.Font("Trebuchet MS", 1, 33)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(169, 224, 49));
        jLabel54.setText("ACCOUNT RECOVERY");

        jLabel55.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(169, 224, 49));
        jLabel55.setText("Answer");

        jButton18.setBackground(new java.awt.Color(169, 224, 49));
        jButton18.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButton18.setForeground(new java.awt.Color(21, 25, 28));
        jButton18.setText("Recover");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jLabel56.setBackground(new java.awt.Color(169, 224, 49));
        jLabel56.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(169, 224, 49));
        jLabel56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Back_To_32px_2.png"))); // NOI18N
        jLabel56.setText("Back");
        jLabel56.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel56.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel56MouseClicked(evt);
            }
        });

        jLabel57.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(169, 224, 49));
        jLabel57.setText("Security Question");

        jLabel58.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(169, 224, 49));
        jLabel58.setText("Password");

        jLabel59.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(169, 224, 49));
        jLabel59.setText("Fullname");

        jTextField_fullname1.setBackground(new java.awt.Color(31, 36, 42));
        jTextField_fullname1.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_fullname1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_fullname1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_fullname1.setCaretColor(new java.awt.Color(255, 255, 255));
        jTextField_fullname1.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextField_fullname1.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        jTextField_fullname1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_fullname1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_fullname1FocusLost(evt);
            }
        });
        jTextField_fullname1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_fullname1ActionPerformed(evt);
            }
        });
        jTextField_fullname1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_fullname1KeyPressed(evt);
            }
        });

        jTextField_answer1.setBackground(new java.awt.Color(31, 36, 42));
        jTextField_answer1.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_answer1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_answer1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));

        jLabel60.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(169, 224, 49));
        jLabel60.setText("Username");

        jTextField_username1.setEditable(false);
        jTextField_username1.setBackground(new java.awt.Color(51, 51, 51));
        jTextField_username1.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_username1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_username1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_username1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_username1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_username1FocusLost(evt);
            }
        });
        jTextField_username1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_username1ActionPerformed(evt);
            }
        });
        jTextField_username1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_username1KeyPressed(evt);
            }
        });

        jButton_Search1.setBackground(new java.awt.Color(169, 224, 49));
        jButton_Search1.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButton_Search1.setForeground(new java.awt.Color(21, 25, 28));
        jButton_Search1.setText("Search");
        jButton_Search1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Search1ActionPerformed(evt);
            }
        });

        jTextField_securityquestion1.setEditable(false);
        jTextField_securityquestion1.setBackground(new java.awt.Color(51, 51, 51));
        jTextField_securityquestion1.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_securityquestion1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_securityquestion1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_securityquestion1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_securityquestion1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_securityquestion1FocusLost(evt);
            }
        });
        jTextField_securityquestion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_securityquestion1ActionPerformed(evt);
            }
        });
        jTextField_securityquestion1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_securityquestion1KeyPressed(evt);
            }
        });

        Password1.setEditable(false);
        Password1.setBackground(new java.awt.Color(51, 51, 51));
        Password1.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        Password1.setForeground(new java.awt.Color(255, 255, 255));
        Password1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        Password1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Password1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Password1FocusLost(evt);
            }
        });
        Password1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Password1ActionPerformed(evt);
            }
        });
        Password1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Password1KeyPressed(evt);
            }
        });

        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Cancel_32px.png"))); // NOI18N
        jButton19.setToolTipText("Close");
        jButton19.setBorder(null);
        jButton19.setBorderPainted(false);
        jButton19.setContentAreaFilled(false);
        jButton19.setRequestFocusEnabled(false);
        jButton19.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Cancel_30px_3.png"))); // NOI18N
        jButton19.setVerifyInputWhenFocusTarget(false);
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Minus_32px_1.png"))); // NOI18N
        jButton20.setToolTipText("Minimize");
        jButton20.setBorder(null);
        jButton20.setBorderPainted(false);
        jButton20.setContentAreaFilled(false);
        jButton20.setFocusPainted(false);
        jButton20.setRequestFocusEnabled(false);
        jButton20.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Minus_30px_3.png"))); // NOI18N
        jButton20.setVerifyInputWhenFocusTarget(false);
        jButton20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton20MouseClicked(evt);
            }
        });
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(0, 54, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField_fullname1, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField_username1, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField_answer1)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                                    .addComponent(jButton_Search1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(Password1, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                                .addComponent(jTextField_securityquestion1))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel56)
                                .addGap(136, 136, 136))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel54)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(44, 44, 44))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_fullname1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_username1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_securityquestion1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_answer1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(Password1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Search1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel56)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 633, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("tab5", jPanel13);

        jPanel18.setBackground(new java.awt.Color(31, 36, 42));

        jTextField_Firstname.setBackground(new java.awt.Color(31, 36, 42));
        jTextField_Firstname.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_Firstname.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_Firstname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_Firstname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_FirstnameActionPerformed(evt);
            }
        });

        jTextField_Lastname.setBackground(new java.awt.Color(31, 36, 42));
        jTextField_Lastname.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_Lastname.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_Lastname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_Lastname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_LastnameActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(169, 224, 49));
        jLabel28.setText("MIDDLE NAME :");

        jTextField_Middlename.setBackground(new java.awt.Color(31, 36, 42));
        jTextField_Middlename.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_Middlename.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_Middlename.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_Middlename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_MiddlenameActionPerformed(evt);
            }
        });

        jLabel63.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(169, 224, 49));
        jLabel63.setText("LAST NAME :");

        jLabel64.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(169, 224, 49));
        jLabel64.setText("FIRST NAME :");

        jLabel65.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(169, 224, 49));
        jLabel65.setText("DATE OF BIRTH:");

        jDateChooser_DOB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));

        jLabel66.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(169, 224, 49));
        jLabel66.setText("AGE:");

        jLabel67.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(169, 224, 49));
        jLabel67.setText("PHONE NUMBER:");

        jTextField_Age.setBackground(new java.awt.Color(31, 36, 42));
        jTextField_Age.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_Age.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_Age.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_Age.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_AgeActionPerformed(evt);
            }
        });

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

        jLabel68.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(169, 224, 49));
        jLabel68.setText("ADDRESS :");

        jLabel69.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(169, 224, 49));
        jLabel69.setText("GENDER :");

        jTextField_Address.setBackground(new java.awt.Color(31, 36, 42));
        jTextField_Address.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_Address.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_Address.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_Address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_AddressActionPerformed(evt);
            }
        });

        jComboBox_Gender.setBackground(new java.awt.Color(31, 36, 42));
        jComboBox_Gender.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jComboBox_Gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MALE", "FEMALE" }));
        jComboBox_Gender.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));

        jDesktopPane4.setBackground(new java.awt.Color(0, 0, 0));

        jLabelProfileImage3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));

        jDesktopPane4.setLayer(jLabelProfileImage3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane4Layout = new javax.swing.GroupLayout(jDesktopPane4);
        jDesktopPane4.setLayout(jDesktopPane4Layout);
        jDesktopPane4Layout.setHorizontalGroup(
            jDesktopPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 196, Short.MAX_VALUE)
            .addGroup(jDesktopPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDesktopPane4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabelProfileImage3, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jDesktopPane4Layout.setVerticalGroup(
            jDesktopPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 176, Short.MAX_VALUE)
            .addGroup(jDesktopPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDesktopPane4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabelProfileImage3, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
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

        jButton50.setBackground(new java.awt.Color(169, 224, 49));
        jButton50.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton50.setForeground(new java.awt.Color(0, 0, 0));
        jButton50.setText("Register");
        jButton50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton50ActionPerformed(evt);
            }
        });

        jLabel70.setFont(new java.awt.Font("Trebuchet MS", 1, 33)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(169, 224, 49));
        jLabel70.setText("RESIDENT ACCOUNT");

        jButton51.setBackground(new java.awt.Color(169, 224, 49));
        jButton51.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton51.setForeground(new java.awt.Color(0, 0, 0));
        jButton51.setText("Clear");
        jButton51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton51ActionPerformed(evt);
            }
        });

        jLabel71.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(169, 224, 49));
        jLabel71.setText("Password");

        jPasswordField9.setBackground(new java.awt.Color(31, 36, 42));
        jPasswordField9.setForeground(new java.awt.Color(255, 255, 255));
        jPasswordField9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));

        jLabel72.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(169, 224, 49));
        jLabel72.setText("Confirm Password");

        jPasswordField10.setBackground(new java.awt.Color(31, 36, 42));
        jPasswordField10.setForeground(new java.awt.Color(255, 255, 255));
        jPasswordField10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));

        jComboBox_Question4.setBackground(new java.awt.Color(31, 36, 42));
        jComboBox_Question4.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jComboBox_Question4.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox_Question4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "What's your favorite food?", "What's your favorite anime?", "What's your favorite color?", "What's your favorite place?", "What's your favorite song?" }));
        jComboBox_Question4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));

        jLabel73.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(169, 224, 49));
        jLabel73.setText("Security Question");

        jTextField_Answer2.setBackground(new java.awt.Color(31, 36, 42));
        jTextField_Answer2.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_Answer2.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_Answer2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_Answer2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_Answer2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_Answer2FocusLost(evt);
            }
        });
        jTextField_Answer2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Answer2ActionPerformed(evt);
            }
        });

        jLabel74.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(169, 224, 49));
        jLabel74.setText("Answer");

        jButton52.setBackground(new java.awt.Color(169, 224, 49));
        jButton52.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton52.setForeground(new java.awt.Color(0, 0, 0));
        jButton52.setText("Back");
        jButton52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton52ActionPerformed(evt);
            }
        });

        jButton31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Minus_32px_1.png"))); // NOI18N
        jButton31.setToolTipText("Minimize");
        jButton31.setBorder(null);
        jButton31.setBorderPainted(false);
        jButton31.setContentAreaFilled(false);
        jButton31.setFocusPainted(false);
        jButton31.setRequestFocusEnabled(false);
        jButton31.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Minus_30px_3.png"))); // NOI18N
        jButton31.setVerifyInputWhenFocusTarget(false);
        jButton31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton31MouseClicked(evt);
            }
        });
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        jButton32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Cancel_32px.png"))); // NOI18N
        jButton32.setToolTipText("Close");
        jButton32.setBorder(null);
        jButton32.setBorderPainted(false);
        jButton32.setContentAreaFilled(false);
        jButton32.setRequestFocusEnabled(false);
        jButton32.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Cancel_30px_3.png"))); // NOI18N
        jButton32.setVerifyInputWhenFocusTarget(false);
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel73)
                                    .addComponent(jComboBox_Question4, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel18Layout.createSequentialGroup()
                                        .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jTextField_Answer2)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel18Layout.createSequentialGroup()
                                .addComponent(jDesktopPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel18Layout.createSequentialGroup()
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel67)
                                    .addComponent(jTextField_Phonenumber, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel69)
                                    .addComponent(jComboBox_Gender, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPasswordField9, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPasswordField10, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel18Layout.createSequentialGroup()
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField_Age, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel66))
                                .addGap(4, 4, 4)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel68)
                                    .addComponent(jTextField_Address)))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel18Layout.createSequentialGroup()
                                        .addComponent(jLabel63)
                                        .addGap(107, 107, 107))
                                    .addGroup(jPanel18Layout.createSequentialGroup()
                                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField_Lastname)
                                            .addComponent(jTextField_Firstname)
                                            .addGroup(jPanel18Layout.createSequentialGroup()
                                                .addComponent(jLabel64)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField_Middlename, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel65)
                                    .addComponent(jDateChooser_DOB, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(45, 45, 45))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel70)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel64)
                        .addGap(4, 4, 4)
                        .addComponent(jTextField_Firstname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(4, 4, 4)
                        .addComponent(jTextField_Middlename, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel63)
                        .addGap(4, 4, 4)
                        .addComponent(jTextField_Lastname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel65)
                        .addGap(4, 4, 4)
                        .addComponent(jDateChooser_DOB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPasswordField9)
                    .addComponent(jPasswordField10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel73))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jComboBox_Question4)
                    .addComponent(jTextField_Answer2, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField_Age, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel68)
                            .addComponent(jLabel66))
                        .addGap(4, 4, 4)
                        .addComponent(jTextField_Address, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel67)
                        .addGap(3, 3, 3)
                        .addComponent(jTextField_Phonenumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel69)
                        .addGap(3, 3, 3)
                        .addComponent(jComboBox_Gender, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDesktopPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jButton49, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton50, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton51, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton52, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab6", jPanel17);

        jPanel20.setBackground(new java.awt.Color(31, 36, 42));

        jTextField_Firstname1.setBackground(new java.awt.Color(31, 36, 42));
        jTextField_Firstname1.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_Firstname1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_Firstname1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_Firstname1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Firstname1ActionPerformed(evt);
            }
        });

        jTextField_Lastname1.setBackground(new java.awt.Color(31, 36, 42));
        jTextField_Lastname1.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_Lastname1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_Lastname1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_Lastname1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Lastname1ActionPerformed(evt);
            }
        });

        jLabel75.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(169, 224, 49));
        jLabel75.setText("MIDDLE NAME :");

        jTextField_Middlename1.setBackground(new java.awt.Color(31, 36, 42));
        jTextField_Middlename1.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_Middlename1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_Middlename1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_Middlename1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Middlename1ActionPerformed(evt);
            }
        });

        jLabel76.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(169, 224, 49));
        jLabel76.setText("LAST NAME :");

        jLabel77.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(169, 224, 49));
        jLabel77.setText("FIRST NAME :");

        jLabel78.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(169, 224, 49));
        jLabel78.setText("DATE OF BIRTH:");

        jDateChooser_DOB1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));

        jLabel79.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(169, 224, 49));
        jLabel79.setText("AGE:");

        jLabel80.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(169, 224, 49));
        jLabel80.setText("PHONE NUMBER:");

        jTextField_Age1.setBackground(new java.awt.Color(31, 36, 42));
        jTextField_Age1.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_Age1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_Age1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_Age1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Age1ActionPerformed(evt);
            }
        });

        jTextField_Phonenumber1.setBackground(new java.awt.Color(31, 36, 42));
        jTextField_Phonenumber1.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_Phonenumber1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_Phonenumber1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_Phonenumber1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Phonenumber1ActionPerformed(evt);
            }
        });
        jTextField_Phonenumber1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_Phonenumber1KeyTyped(evt);
            }
        });

        jLabel81.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(169, 224, 49));
        jLabel81.setText("ADDRESS :");

        jLabel82.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(169, 224, 49));
        jLabel82.setText("POSITION:");

        jTextField_Address1.setBackground(new java.awt.Color(31, 36, 42));
        jTextField_Address1.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_Address1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_Address1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_Address1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Address1ActionPerformed(evt);
            }
        });

        jComboBox_Gender1.setBackground(new java.awt.Color(31, 36, 42));
        jComboBox_Gender1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jComboBox_Gender1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chairman", "Kagawad", "Treasurer", "Secretary", "SK Chairman", "SK Kagawad", "SK Secretary" }));
        jComboBox_Gender1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));

        jDesktopPane5.setBackground(new java.awt.Color(0, 0, 0));

        jLabelProfileImage4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));

        jDesktopPane5.setLayer(jLabelProfileImage4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane5Layout = new javax.swing.GroupLayout(jDesktopPane5);
        jDesktopPane5.setLayout(jDesktopPane5Layout);
        jDesktopPane5Layout.setHorizontalGroup(
            jDesktopPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 196, Short.MAX_VALUE)
            .addGroup(jDesktopPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDesktopPane5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabelProfileImage4, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jDesktopPane5Layout.setVerticalGroup(
            jDesktopPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 176, Short.MAX_VALUE)
            .addGroup(jDesktopPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDesktopPane5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabelProfileImage4, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jButton53.setBackground(new java.awt.Color(169, 224, 49));
        jButton53.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton53.setForeground(new java.awt.Color(0, 0, 0));
        jButton53.setText("Select Image");
        jButton53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton53ActionPerformed(evt);
            }
        });

        jButton54.setBackground(new java.awt.Color(169, 224, 49));
        jButton54.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton54.setForeground(new java.awt.Color(0, 0, 0));
        jButton54.setText("Register");
        jButton54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton54ActionPerformed(evt);
            }
        });

        jLabel83.setFont(new java.awt.Font("Trebuchet MS", 1, 33)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(169, 224, 49));
        jLabel83.setText("OFFICIAL ACCOUNT");

        jButton55.setBackground(new java.awt.Color(169, 224, 49));
        jButton55.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton55.setForeground(new java.awt.Color(0, 0, 0));
        jButton55.setText("Clear");
        jButton55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton55ActionPerformed(evt);
            }
        });

        jLabel84.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(169, 224, 49));
        jLabel84.setText("Password");

        jPasswordField11.setBackground(new java.awt.Color(31, 36, 42));
        jPasswordField11.setForeground(new java.awt.Color(255, 255, 255));
        jPasswordField11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));

        jLabel85.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(169, 224, 49));
        jLabel85.setText("Confirm Password");

        jPasswordField12.setBackground(new java.awt.Color(31, 36, 42));
        jPasswordField12.setForeground(new java.awt.Color(255, 255, 255));
        jPasswordField12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));

        jComboBox_Question5.setBackground(new java.awt.Color(31, 36, 42));
        jComboBox_Question5.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jComboBox_Question5.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox_Question5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "What's your favorite food?", "What's your favorite anime?", "What's your favorite color?", "What's your favorite place?", "What's your favorite song?" }));
        jComboBox_Question5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));

        jLabel86.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(169, 224, 49));
        jLabel86.setText("Security Question");

        jTextField_Answer3.setBackground(new java.awt.Color(31, 36, 42));
        jTextField_Answer3.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_Answer3.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_Answer3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_Answer3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_Answer3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_Answer3FocusLost(evt);
            }
        });
        jTextField_Answer3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Answer3ActionPerformed(evt);
            }
        });

        jLabel87.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(169, 224, 49));
        jLabel87.setText("Answer");

        jButton56.setBackground(new java.awt.Color(169, 224, 49));
        jButton56.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jButton56.setForeground(new java.awt.Color(0, 0, 0));
        jButton56.setText("Back");
        jButton56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton56ActionPerformed(evt);
            }
        });

        jButton33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Cancel_32px.png"))); // NOI18N
        jButton33.setToolTipText("Close");
        jButton33.setBorder(null);
        jButton33.setBorderPainted(false);
        jButton33.setContentAreaFilled(false);
        jButton33.setRequestFocusEnabled(false);
        jButton33.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Cancel_30px_3.png"))); // NOI18N
        jButton33.setVerifyInputWhenFocusTarget(false);
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        jButton34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Minus_32px_1.png"))); // NOI18N
        jButton34.setToolTipText("Minimize");
        jButton34.setBorder(null);
        jButton34.setBorderPainted(false);
        jButton34.setContentAreaFilled(false);
        jButton34.setFocusPainted(false);
        jButton34.setRequestFocusEnabled(false);
        jButton34.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Minus_30px_3.png"))); // NOI18N
        jButton34.setVerifyInputWhenFocusTarget(false);
        jButton34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton34MouseClicked(evt);
            }
        });
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel86)
                                    .addComponent(jComboBox_Question5, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel20Layout.createSequentialGroup()
                                        .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jTextField_Answer3)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel20Layout.createSequentialGroup()
                                .addComponent(jDesktopPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel20Layout.createSequentialGroup()
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel80)
                                    .addComponent(jTextField_Phonenumber1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel82)
                                    .addComponent(jComboBox_Gender1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPasswordField11, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPasswordField12, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel20Layout.createSequentialGroup()
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField_Age1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel79))
                                .addGap(4, 4, 4)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel81)
                                    .addComponent(jTextField_Address1)))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel20Layout.createSequentialGroup()
                                        .addComponent(jLabel76)
                                        .addGap(107, 107, 107))
                                    .addGroup(jPanel20Layout.createSequentialGroup()
                                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField_Lastname1)
                                            .addComponent(jTextField_Firstname1)
                                            .addGroup(jPanel20Layout.createSequentialGroup()
                                                .addComponent(jLabel77)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField_Middlename1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel78)
                                    .addComponent(jDateChooser_DOB1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel75)
                                    .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(45, 45, 45))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel83)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel77)
                        .addGap(4, 4, 4)
                        .addComponent(jTextField_Firstname1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel75)
                        .addGap(4, 4, 4)
                        .addComponent(jTextField_Middlename1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel76)
                        .addGap(4, 4, 4)
                        .addComponent(jTextField_Lastname1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel78)
                        .addGap(4, 4, 4)
                        .addComponent(jDateChooser_DOB1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPasswordField11)
                    .addComponent(jPasswordField12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel86))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jComboBox_Question5)
                    .addComponent(jTextField_Answer3, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField_Age1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel81)
                            .addComponent(jLabel79))
                        .addGap(4, 4, 4)
                        .addComponent(jTextField_Address1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel80)
                        .addGap(3, 3, 3)
                        .addComponent(jTextField_Phonenumber1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel82)
                        .addGap(3, 3, 3)
                        .addComponent(jComboBox_Gender1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDesktopPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jButton53, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton54, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton55, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton56, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab7", jPanel19);

        jPanel2.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -40, 450, 660));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, 450, 620));

        jTabbedPane2.setBackground(new java.awt.Color(0, 0, 0));

        jPanel10.setBackground(new java.awt.Color(21, 25, 28));

        jButton2.setBackground(new java.awt.Color(169, 224, 49));
        jButton2.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(21, 25, 28));
        jButton2.setText("BARANGAY OFFICIAL");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(169, 224, 49));
        jButton4.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(21, 25, 28));
        jButton4.setText("BARANGAY RESIDENT");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 33)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(169, 224, 49));
        jLabel2.setText("LOG-IN AS");

        lblTime.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTime.setForeground(new java.awt.Color(169, 224, 49));
        lblTime.setText("Time");

        lblDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDate.setForeground(new java.awt.Color(169, 224, 49));
        lblDate.setText("Date");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(jLabel2))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(97, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98)
                .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        jTabbedPane2.addTab("tab1", jPanel10);

        jPanel11.setBackground(new java.awt.Color(21, 25, 28));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField_Username.setBackground(new java.awt.Color(31, 36, 42));
        jTextField_Username.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_Username.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_Username.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_Username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_UsernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_UsernameFocusLost(evt);
            }
        });
        jTextField_Username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_UsernameActionPerformed(evt);
            }
        });
        jPanel11.add(jTextField_Username, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, 280, 40));

        jLabel11.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(169, 224, 49));
        jLabel11.setText("Create New User");
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        jPanel11.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 520, 130, 20));

        jPasswordField_Password.setBackground(new java.awt.Color(31, 36, 42));
        jPasswordField_Password.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jPasswordField_Password.setForeground(new java.awt.Color(255, 255, 255));
        jPasswordField_Password.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jPasswordField_Password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPasswordField_PasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPasswordField_PasswordFocusLost(evt);
            }
        });
        jPasswordField_Password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField_PasswordActionPerformed(evt);
            }
        });
        jPanel11.add(jPasswordField_Password, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 330, 280, 40));

        jLabel12.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(169, 224, 49));
        jLabel12.setText("User Name");
        jPanel11.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, 80, 20));

        jLabel13.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(169, 224, 49));
        jLabel13.setText("Password");
        jPanel11.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 300, 70, 20));

        CheckBox.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        CheckBox.setForeground(new java.awt.Color(169, 224, 49));
        CheckBox.setText("See Password");
        CheckBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        CheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckBoxActionPerformed(evt);
            }
        });
        jPanel11.add(CheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 400, -1, 20));

        jButton5.setBackground(new java.awt.Color(169, 224, 49));
        jButton5.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(21, 25, 28));
        jButton5.setText("RESIDENT");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 120, 30));

        jLabel14.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(169, 224, 49));
        jLabel14.setText("Forgot Password?");
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        jPanel11.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 400, 130, 20));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Lock_35px.png"))); // NOI18N
        jPanel11.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 40, 40));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_User_Shield_100px.png"))); // NOI18N
        jPanel11.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 90, 70));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Male_User_35px.png"))); // NOI18N
        jPanel11.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 40, 40));

        jButtonResidentLogin.setBackground(new java.awt.Color(169, 224, 49));
        jButtonResidentLogin.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButtonResidentLogin.setForeground(new java.awt.Color(21, 25, 28));
        jButtonResidentLogin.setText("Sign In");
        jButtonResidentLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResidentLoginActionPerformed(evt);
            }
        });
        jPanel11.add(jButtonResidentLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 450, 280, 40));

        jLabel42.setBackground(new java.awt.Color(169, 224, 49));
        jLabel42.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(169, 224, 49));
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Back_To_32px_2.png"))); // NOI18N
        jLabel42.setText("Back");
        jLabel42.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel42MouseClicked(evt);
            }
        });
        jPanel11.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        lblTime1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTime1.setForeground(new java.awt.Color(169, 224, 49));
        lblTime1.setText("Time");
        jPanel11.add(lblTime1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, -1, -1));

        lblDate1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDate1.setForeground(new java.awt.Color(169, 224, 49));
        lblDate1.setText("Date");
        jPanel11.add(lblDate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, -1, -1));

        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Twitter_32px.png"))); // NOI18N
        jButton21.setToolTipText("Twitter");
        jButton21.setBorder(null);
        jButton21.setBorderPainted(false);
        jButton21.setContentAreaFilled(false);
        jButton21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton21.setFocusPainted(false);
        jButton21.setRequestFocusEnabled(false);
        jButton21.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Twitter_32px_2.png"))); // NOI18N
        jButton21.setVerifyInputWhenFocusTarget(false);
        jButton21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton21MouseClicked(evt);
            }
        });
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton21, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 560, -1, 40));

        jButton26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Facebook_32px_9.png"))); // NOI18N
        jButton26.setToolTipText("Facebook");
        jButton26.setBorder(null);
        jButton26.setBorderPainted(false);
        jButton26.setContentAreaFilled(false);
        jButton26.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton26.setFocusPainted(false);
        jButton26.setRequestFocusEnabled(false);
        jButton26.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Facebook_32px_7.png"))); // NOI18N
        jButton26.setVerifyInputWhenFocusTarget(false);
        jButton26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton26MouseClicked(evt);
            }
        });
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton26, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 560, -1, 40));

        jButton27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_YouTube_32px.png"))); // NOI18N
        jButton27.setToolTipText("Youtube");
        jButton27.setBorder(null);
        jButton27.setBorderPainted(false);
        jButton27.setContentAreaFilled(false);
        jButton27.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton27.setFocusPainted(false);
        jButton27.setRequestFocusEnabled(false);
        jButton27.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_YouTube_32px_1.png"))); // NOI18N
        jButton27.setVerifyInputWhenFocusTarget(false);
        jButton27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton27MouseClicked(evt);
            }
        });
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton27, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 560, -1, 40));

        jTabbedPane2.addTab("tab2", jPanel11);

        jPanel12.setBackground(new java.awt.Color(21, 25, 28));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField_Username1.setBackground(new java.awt.Color(31, 36, 42));
        jTextField_Username1.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jTextField_Username1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField_Username1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jTextField_Username1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_Username1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_Username1FocusLost(evt);
            }
        });
        jTextField_Username1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_Username1ActionPerformed(evt);
            }
        });
        jPanel12.add(jTextField_Username1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 280, 40));

        jLabel16.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(169, 224, 49));
        jLabel16.setText("Create New User");
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });
        jPanel12.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 510, 130, 20));

        jPasswordField_Password1.setBackground(new java.awt.Color(31, 36, 42));
        jPasswordField_Password1.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jPasswordField_Password1.setForeground(new java.awt.Color(255, 255, 255));
        jPasswordField_Password1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        jPasswordField_Password1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPasswordField_Password1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPasswordField_Password1FocusLost(evt);
            }
        });
        jPasswordField_Password1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField_Password1ActionPerformed(evt);
            }
        });
        jPanel12.add(jPasswordField_Password1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 320, 280, 40));

        jLabel19.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(169, 224, 49));
        jLabel19.setText("User Name");
        jPanel12.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 80, 20));

        jLabel20.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(169, 224, 49));
        jLabel20.setText("Password");
        jPanel12.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, 70, 20));

        CheckBox1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        CheckBox1.setForeground(new java.awt.Color(169, 224, 49));
        CheckBox1.setText("See Password");
        CheckBox1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));
        CheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckBox1ActionPerformed(evt);
            }
        });
        jPanel12.add(CheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 390, -1, 20));

        jButton7.setBackground(new java.awt.Color(169, 224, 49));
        jButton7.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(21, 25, 28));
        jButton7.setText("OFFICIAL");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 120, 30));

        jLabel21.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(169, 224, 49));
        jLabel21.setText("Forgot Password?");
        jLabel21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });
        jPanel12.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 390, 130, 20));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Lock_35px.png"))); // NOI18N
        jPanel12.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 40, 40));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_User_Shield_100px.png"))); // NOI18N
        jPanel12.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 90, 70));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Male_User_35px.png"))); // NOI18N
        jPanel12.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 40, 40));

        jButtonAdminLogin.setBackground(new java.awt.Color(169, 224, 49));
        jButtonAdminLogin.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButtonAdminLogin.setForeground(new java.awt.Color(21, 25, 28));
        jButtonAdminLogin.setText("Sign In");
        jButtonAdminLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdminLoginActionPerformed(evt);
            }
        });
        jPanel12.add(jButtonAdminLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 430, 280, 40));

        jLabel41.setBackground(new java.awt.Color(169, 224, 49));
        jLabel41.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(169, 224, 49));
        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Back_To_32px_2.png"))); // NOI18N
        jLabel41.setText("Back");
        jLabel41.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel41MouseClicked(evt);
            }
        });
        jPanel12.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        lblTime2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTime2.setForeground(new java.awt.Color(169, 224, 49));
        lblTime2.setText("Time");
        jPanel12.add(lblTime2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, -1, -1));

        lblDate2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDate2.setForeground(new java.awt.Color(169, 224, 49));
        lblDate2.setText("Date");
        jPanel12.add(lblDate2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, -1, -1));

        jButton28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Twitter_32px.png"))); // NOI18N
        jButton28.setToolTipText("Twitter");
        jButton28.setBorder(null);
        jButton28.setBorderPainted(false);
        jButton28.setContentAreaFilled(false);
        jButton28.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton28.setFocusPainted(false);
        jButton28.setRequestFocusEnabled(false);
        jButton28.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Twitter_32px_2.png"))); // NOI18N
        jButton28.setVerifyInputWhenFocusTarget(false);
        jButton28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton28MouseClicked(evt);
            }
        });
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton28, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 550, -1, 40));

        jButton29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Facebook_32px_9.png"))); // NOI18N
        jButton29.setToolTipText("Facebook");
        jButton29.setBorder(null);
        jButton29.setBorderPainted(false);
        jButton29.setContentAreaFilled(false);
        jButton29.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton29.setFocusPainted(false);
        jButton29.setRequestFocusEnabled(false);
        jButton29.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Facebook_32px_7.png"))); // NOI18N
        jButton29.setVerifyInputWhenFocusTarget(false);
        jButton29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton29MouseClicked(evt);
            }
        });
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton29, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 550, -1, 40));

        jButton30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_YouTube_32px.png"))); // NOI18N
        jButton30.setToolTipText("Youtube");
        jButton30.setBorder(null);
        jButton30.setBorderPainted(false);
        jButton30.setContentAreaFilled(false);
        jButton30.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton30.setFocusPainted(false);
        jButton30.setRequestFocusEnabled(false);
        jButton30.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_YouTube_32px_1.png"))); // NOI18N
        jButton30.setVerifyInputWhenFocusTarget(false);
        jButton30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton30MouseClicked(evt);
            }
        });
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton30, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 550, -1, 40));

        jTabbedPane2.addTab("tab3", jPanel12);

        jPanel16.setBackground(new java.awt.Color(21, 25, 28));

        jLabel61.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(169, 224, 49));
        jLabel61.setText("Enter Barangay Admin password to access");

        jPasswordField8.setBackground(new java.awt.Color(31, 36, 42));
        jPasswordField8.setForeground(new java.awt.Color(255, 255, 255));
        jPasswordField8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(169, 224, 49), 2));

        jLabel62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_Lock_35px.png"))); // NOI18N

        AdminAccess.setBackground(new java.awt.Color(169, 224, 49));
        AdminAccess.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        AdminAccess.setForeground(new java.awt.Color(21, 25, 28));
        AdminAccess.setText("Sign In");
        AdminAccess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdminAccessActionPerformed(evt);
            }
        });

        jButtonResidentLogin1.setBackground(new java.awt.Color(169, 224, 49));
        jButtonResidentLogin1.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButtonResidentLogin1.setForeground(new java.awt.Color(21, 25, 28));
        jButtonResidentLogin1.setText("Cancel");
        jButtonResidentLogin1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResidentLogin1ActionPerformed(evt);
            }
        });

        WarningSign.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        WarningSign.setForeground(new java.awt.Color(255, 0, 0));
        WarningSign.setText("*");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPasswordField8, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(AdminAccess, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonResidentLogin1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(WarningSign, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(245, 245, 245)
                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(WarningSign, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62)
                    .addComponent(jPasswordField8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AdminAccess, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonResidentLogin1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(259, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("tab4", jPanel15);

        jPanel1.add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -40, 460, 660));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
     
    
            
    private void jLabel41MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel41MouseClicked
        // TODO add your handling code here:
        
        jTabbedPane2.setSelectedIndex(0);
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jLabel41MouseClicked

    private void jButtonAdminLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdminLoginActionPerformed
        // TODO add your handling code here:
                // TODO add your handling code here:
        String username = jTextField_Username1.getText();
        String password = String.valueOf(jPasswordField_Password1.getPassword());

        String query = "SELECT `Firstname`,`Middlename`,`Lastname`,`Birthdate`,`Age`,`Address`,`Position`,`Profilepic`,`Phonenumber`,`ID`  FROM `adminaccountinfo` WHERE `Firstname` = ? AND `Password` = ?";

        try
        {
            pst = (com.mysql.jdbc.PreparedStatement) MyConnection.getConnection().prepareStatement(query);

            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();
            if(rs.next())
            {
                //InputStream img = new FileInputStream(new File(imgPath));
                //show a new form
                UserName = (rs.getString(1));
                Position = (rs.getString("Position"));
                
              
                DashboardAdmin form = new DashboardAdmin();
                form.setVisible(true);
                form.pack();
                form.setLocationRelativeTo(null);
                form.Displayname.setText(rs.getString("Firstname"));
                form.Middlename.setText(rs.getString("Middlename"));
                form.Lastname.setText(rs.getString("Lastname"));
                form.DOB.setText(rs.getString("Birthdate"));
                form.Age.setText(rs.getString("Age"));
                form.Address.setText(rs.getString("Address"));
                form.ID.setText(rs.getString("ID"));
                form.Gender.setText(rs.getString("Position"));
                form.Phonenumber.setText(rs.getString("Phonenumber"));
                //form.jLabel_Image.setIcon(rs.image.getImage());
                
                //pst.setBlob(9,img);
                
               byte[] img = rs.getBytes("Profilepic");
               ImageIcon image = new ImageIcon(img);
                
               Image im = image.getImage();
               Image myimg = im.getScaledInstance(form.jLabel_image.getWidth(), form.jLabel_image.getHeight(), Image.SCALE_SMOOTH);
               ImageIcon newImage = new ImageIcon(myimg);
               form.jLabel_image.setIcon(newImage);
//               
               //form.jLabelProfileImage.setIcon(rs.getImage("profilepic"));
                //close the current form(login form)
                this.dispose();
                JOptionPane.showMessageDialog(null,"Welcome! ,"+Position+" "+UserName);    
            }
            else
            {
                //error message
                ctr++;
                JOptionPane.showMessageDialog(null,"Invalid Username / Password","Login Error",1);
                System.out.println(ctr);
            }
            if (ctr == 1) {
            
            JOptionPane.showMessageDialog(null, "Last 2 more attempts", "Notice", JOptionPane.INFORMATION_MESSAGE);
        }
            if (ctr == 2) {
            
            JOptionPane.showMessageDialog(null, "Last 1 more attempt", "Notice", JOptionPane.INFORMATION_MESSAGE);
        }
            if (ctr == 3) {
            JOptionPane.showMessageDialog(null, "Password Failed..", "Notice", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
            
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonAdminLoginActionPerformed

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void CheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckBox1ActionPerformed
        // TODO add your handling code here:
        if(CheckBox1.isSelected())
        {
            jPasswordField_Password1.setEchoChar((char)0);
        }
        else
        {
            jPasswordField_Password1.setEchoChar('*');
        }
    }//GEN-LAST:event_CheckBox1ActionPerformed

    private void jPasswordField_Password1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField_Password1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField_Password1ActionPerformed

    private void jPasswordField_Password1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField_Password1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField_Password1FocusLost

    private void jPasswordField_Password1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField_Password1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField_Password1FocusGained

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(6);
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jTextField_Username1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Username1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Username1ActionPerformed

    private void jTextField_Username1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_Username1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Username1FocusLost

    private void jTextField_Username1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_Username1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Username1FocusGained

    private void jLabel42MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel42MouseClicked
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(0);
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jLabel42MouseClicked

    private void jButtonResidentLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResidentLoginActionPerformed
        // TODO add your handling code here:
        String username = jTextField_Username.getText();
        String password = String.valueOf(jPasswordField_Password.getPassword());

        String query = "SELECT `Firstname`,`Middlename`,`Lastname`,`Birthdate`,`Age`,`Address`,`Gender`,`profilepic`,`Phonenumber`,`ResidentID`  FROM `residentaccountinfo` WHERE `Firstname` = ? AND `Password` = ?";

        try
        {
            pst = (com.mysql.jdbc.PreparedStatement) MyConnection.getConnection().prepareStatement(query);

            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();
            if(rs.next())
            {
                //InputStream img = new FileInputStream(new File(imgPath));
                //show a new form
                
//                SplashScreen sp = new SplashScreen();
//                sp.setVisible(true);
//               
//               if(rs.next())
//               {
                UserName = (rs.getString(1));

                Dashboard form = new Dashboard();
                form.setVisible(true);
                form.pack();
                form.setLocationRelativeTo(null);
                form.Displayname2.setText(rs.getString("Firstname"));
                form.Middlename.setText(rs.getString("Middlename"));
                form.Lastname.setText(rs.getString("Lastname"));
                form.DOB.setText(rs.getString("Birthdate"));
                form.Age.setText(rs.getString("Age"));
                form.Address.setText(rs.getString("Address"));
                form.ID.setText(rs.getString("ResidentID"));
                form.Gender.setText(rs.getString("Gender"));
                form.Phonenumber.setText(rs.getString("Phonenumber"));
                //form.jLabel_Image.setIcon(rs.image.getImage());
                
                //pst.setBlob(9,img);
                
               byte[] img = rs.getBytes("profilepic");
               ImageIcon image = new ImageIcon(img);
                
               Image im = image.getImage();
               Image myimg = im.getScaledInstance(form.jLabel_image.getWidth(), form.jLabel_image.getHeight(), Image.SCALE_SMOOTH);
               ImageIcon newImage = new ImageIcon(myimg);
               form.jLabel_image.setIcon(newImage);
               
               ImageIcon picture = new ImageIcon("src/Image2/ahong1.jpg");
               Image image1 = picture.getImage().getScaledInstance(form.jLabel1.getWidth(), form.jLabel1.getHeight(), Image.SCALE_SMOOTH);
               form.jLabel1.setIcon(new ImageIcon(image1));
//               
               //form.jLabelProfileImage.setIcon(rs.getImage("profilepic"));
                //close the current form(login form)
                this.dispose();
                JOptionPane.showMessageDialog(null,"Welcome! Resident,"+UserName); 
               
            }
            else
            {
                ctr++;
                //error message
                JOptionPane.showMessageDialog(null,"Invalid Username / Password","Login Error",1);
                System.out.println(ctr);
            }
            if (ctr == 1) {
            
            JOptionPane.showMessageDialog(null, "Last 2 more attempts", "Notice", JOptionPane.INFORMATION_MESSAGE);
        }
            else if (ctr == 2) {
            
            JOptionPane.showMessageDialog(null, "Last 1 more attempt", "Notice", JOptionPane.INFORMATION_MESSAGE);
        }
            else if (ctr == 3) {
            JOptionPane.showMessageDialog(null, "Password Failed..", "Notice", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonResidentLoginActionPerformed

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void CheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckBoxActionPerformed
        // TODO add your handling code here:
        if(CheckBox.isSelected())
        {
            jPasswordField_Password.setEchoChar((char)0);
        }
        else
        {
            jPasswordField_Password.setEchoChar('*');
        }
    }//GEN-LAST:event_CheckBoxActionPerformed

    private void jPasswordField_PasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField_PasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField_PasswordActionPerformed

    private void jPasswordField_PasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField_PasswordFocusLost
        // TODO add your handling code here:
        String password = String.valueOf(jPasswordField_Password.getPassword());
        if(password.trim().equals("") ||
            password.trim().toLowerCase().equals("password"))
        {
            jPasswordField_Password.setText("Password");
            jPasswordField_Password.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_jPasswordField_PasswordFocusLost

    private void jPasswordField_PasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField_PasswordFocusGained
        // TODO add your handling code here:
        String password = String.valueOf(jPasswordField_Password.getPassword());
        if(password.trim().toLowerCase().equals("password"))
        {
            jPasswordField_Password.setText("");
            jPasswordField_Password.setForeground(Color.black);
        }
    }//GEN-LAST:event_jPasswordField_PasswordFocusGained

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(5);
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jTextField_UsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_UsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_UsernameActionPerformed

    private void jTextField_UsernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_UsernameFocusLost
        // TODO add your handling code here:
        if(jTextField_Username.getText().trim().equals("") ||
            jTextField_Username.getText().trim().toLowerCase().equals("username"))
        {
            jTextField_Username.setText("Username");
            jTextField_Username.setForeground(new Color(155,153,153));
        }
    }//GEN-LAST:event_jTextField_UsernameFocusLost

    private void jTextField_UsernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_UsernameFocusGained
        // TODO add your handling code here:
        if(jTextField_Username.getText().trim().toLowerCase().equals("username"))
        {
            jTextField_Username.setText("");
            // change the text field color to black
            jTextField_Username.setForeground(Color.black);
        }
    }//GEN-LAST:event_jTextField_UsernameFocusGained

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(1);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(3);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel51MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel51MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel51MouseClicked

    private void SignUpAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignUpAdminActionPerformed

        if(jTextField_Fullname1.getText().equals("") || jTextField_Username3.getText().equals("") || jPasswordField6.getText().equals("") || jPasswordField7.getText().equals("") || jTextField_Answer1.getText().equals(""))
        {
          JOptionPane.showMessageDialog(null, "Please Specified the Required Fields  (  *  )","Barangay System",JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            if(jPasswordField6.getText().equals(jPasswordField7.getText()))
            {
            String registerUserQuery = "INSERT INTO `adminaccountinfo`(`Fullname`, `Username`, `Password`, `Position`, `SecurityQ`, `Answer`) VALUES (?,?,?,?,?,?)";
            String UserCheck = jTextField_Username3.getText();
            String sql2 = "SELECT Username FROM `adminaccountinfo` where Username='"+UserCheck+"'"; //check kung ang username is already taken
            String getUserID = "SELECT ID FROM `adminaccountinfo` where Username='"+UserCheck+"'";
            //Connection con = (Connection) MyConnection.getConnection();
            try
            {
              pst = MyConnection.getConnection().prepareStatement(sql2);
              rs = pst.executeQuery();
           while(rs.next()){
           warning1.setText(rs.getString(2));
           }

           if(warning1.getText().equals("")){
               int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure?", "Barangay System",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if (selectedOption==JOptionPane.YES_OPTION)
                {
                    try{
                          //InputStream is = new FileInputStream(new File(s));
                          pst = MyConnection.getConnection().prepareStatement(registerUserQuery);
                          pst.setString(1,jTextField_Fullname1.getText());
                          pst.setString(2,jTextField_Username3.getText());
                          pst.setString(3,jPasswordField6.getText());
                          pst.setString(4,jComboBox_Question3.getSelectedItem().toString());
                          pst.setString(5,jComboBox_Question2.getSelectedItem().toString());
                          pst.setString(6,jTextField_Answer1.getText());
                          pst.executeUpdate();
                    try{
                    pst = MyConnection.getConnection().prepareStatement(getUserID); 
                    rs = pst.executeQuery();
                    while(rs.next()){
                    reportGetID = rs.getString(1);
                    report2();
                }
            }   catch(Exception e){JOptionPane.showMessageDialog(null, e);}
                    //con.close();
                    JOptionPane.showMessageDialog(null, "User Successfully Registered","Barangay System",JOptionPane.INFORMATION_MESSAGE);  
                    jTextField_Fullname1.getText().equals("");
                    jTextField_Username3.getText().equals("");
                    jPasswordField6.getText().equals("");
                    jPasswordField7.getText().equals("");
                    jTextField_Answer1.getText().equals("");
                    
           }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
            //JOptionPane.showMessageDialog(null,"Can't Connect, Please Contact your Administrator","Barangay System",JOptionPane.ERROR_MESSAGE);
        }
           }
               }else{
                //JOptionPane.showMessageDialog(null, "Username Already Exist");
               warning1.setVisible(true);
               warning1.setText("");
               
           }
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
            //JOptionPane.showMessageDialog(null,"Can't Connect, Please Contact your Administrator","Barangay System",JOptionPane.ERROR_MESSAGE);
        }
        }else{
            JOptionPane.showMessageDialog(null, "Password not Match","Barangay System",JOptionPane.ERROR_MESSAGE);
            }
        }
                // TODO add your handling code here:
//        String registerUserQuery = "INSERT INTO `adminaccountinfo`(`Fullname`, `Username`, `Password`, `Position`, `SecurityQ`, `Answer`) VALUES (?,?,?,?,?,?)";
//        String fname = jTextField_Fullname1.getText();
//        String uname = jTextField_Username3.getText();
//        String password1 = String.valueOf(jPasswordField6.getPassword());
//        String password2 = String.valueOf(jPasswordField7.getPassword());
//        String position = jComboBox_Question3.getSelectedItem().toString();
//        String securityQ = jComboBox_Question2.getSelectedItem().toString();
//        String answer = jTextField_Answer1.getText();
//        try {
//            pst = MyConnection.getConnection().prepareStatement(registerUserQuery);
//            pst.setString(1, fname);
//            pst.setString(2, uname);
//            pst.setString(3, password1);
//            pst.setString(4, position);
//            pst.setString(5, securityQ);
//            pst.setString(6, answer);
//
//            
//               
//        if(uname.length()==0)
//        {
//            JOptionPane.showMessageDialog(null, "Add A Username");
//        }
//
//        else if(password1.length()==0)
//        {
//            JOptionPane.showMessageDialog(null, "Add A Password");
//        }
//        else if(!password1.equals(password2))
//        {
//            JOptionPane.showMessageDialog(null, "Retype The Password Again");
//            //password2.setText(null);
//        }
//        else if(answer.length()==0)
//        {
//            JOptionPane.showMessageDialog(null, "Add a Answer");
//        }
//        else if(checkUsername(uname))
//        {
//            JOptionPane.showMessageDialog(null, "This Username Already Exist");
//        }
//        else
//        {
//            JOptionPane.showMessageDialog(null, "Validation Success");
//        }
//
//        if(verifyFieldsAdminRegister())
//        {
//            String Username = null;
//
//            if(!checkUsername(Username))
//            {
//                PreparedStatement ps;
//                ResultSet rs;
//            }
//        }
//        if(pst.executeUpdate() != 0)
//        {
//          JOptionPane.showMessageDialog(null, "Your Account Information Has Been Created");
//        }
//        else
//        {
//          JOptionPane.showMessageDialog(null, "Error: Check Your Information");
//        }
//         
//        }catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex);
//        }
    }//GEN-LAST:event_SignUpAdminActionPerformed

    
    private void jTextField_Answer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Answer1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Answer1ActionPerformed

    private void jTextField_Answer1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_Answer1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Answer1FocusLost

    private void jTextField_Answer1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_Answer1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Answer1FocusGained

    private void jTextField_Username3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_Username3KeyReleased
        // TODO add your handling code here:
        String Username = jTextField_Username3.getText();

        try
        {
            Statement S = (Statement) MyConnection.getConnection().createStatement();
            ResultSet rs = S.executeQuery("SELECT * FROM `adminaccountinfo` WHERE `Username` ='"+Username+"'");

            if(rs.next())
            {
                String yes = rs.getString("Username");
                System.out.println(yes);
                //Username.setForeground(Color.red);
                warning1.setVisible(true);
            }
            else
            {
                System.out.println("No Users");
                warning1.setVisible(false);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jTextField_Username3KeyReleased

    private void jTextField_Fullname1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Fullname1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Fullname1ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton16MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16MouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseClicked
        this.setState(ICONIFIED);
    }//GEN-LAST:event_jButton11MouseClicked

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void PasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PasswordKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PasswordKeyPressed

    private void PasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PasswordActionPerformed

    private void PasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PasswordFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_PasswordFocusLost

    private void PasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PasswordFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_PasswordFocusGained

    private void jTextField_securityquestionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_securityquestionKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_securityquestionKeyPressed

    private void jTextField_securityquestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_securityquestionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_securityquestionActionPerformed

    private void jTextField_securityquestionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_securityquestionFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_securityquestionFocusLost

    private void jTextField_securityquestionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_securityquestionFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_securityquestionFocusGained

    private void jButton_SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_SearchActionPerformed
        // TODO add your handling code here:

        String a1 = jTextField_fullname.getText();
        String sql = "SELECT `Firstname`,`SecretQ` FROM `residentaccountinfo` WHERE `Firstname`='"+a1+"'";
        try
        {
            pst = MyConnection.getConnection().prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next())
            {
                jTextField_username.setText(rs.getString("Firstname"));
                jTextField_securityquestion.setText(rs.getString("SecretQ"));
                rs.close();
                pst.close();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Incorrect username");
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton_SearchActionPerformed
  
    private void jTextField_usernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_usernameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_usernameKeyPressed

    private void jTextField_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_usernameActionPerformed

    private void jTextField_usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_usernameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_usernameFocusLost

    private void jTextField_usernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_usernameFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_usernameFocusGained

    private void jTextField_fullnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_fullnameKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {

            try {
                String sql = "SELECT * FROM `useraccountinfo` WHERE `Username` = ?";
                pst = MyConnection.getConnection().prepareStatement(sql);
                pst.setString(2, jTextField_fullname.getText());
                rs = pst.executeQuery();
                if(rs.next())
                {
                    jTextField_username.setText(rs.getString("Fullname"));
                    //jComboBox_Question.setText(rs.getString("SecurityQ"));
                    //jComboBox_Question.addItem(rs.getString("SecurityQ"));
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Username not found in DATABASE");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }//GEN-LAST:event_jTextField_fullnameKeyPressed

    private void jTextField_fullnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_fullnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_fullnameActionPerformed

    private void jTextField_fullnameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_fullnameFocusLost
        // TODO add your handling code here:
        if(jTextField_fullname.getText().equals(""))
        {
            jTextField_fullname.setText("Enter Fullname to Search");
        }
    }//GEN-LAST:event_jTextField_fullnameFocusLost

    private void jTextField_fullnameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_fullnameFocusGained
        // TODO add your handling code here:
        if(jTextField_fullname.getText().trim().equals("Enter Username to Search"))
        {
            jTextField_fullname.setText("");
        }
        jTextField_fullname.setForeground(Color.GRAY);
    }//GEN-LAST:event_jTextField_fullnameFocusGained

    private void jLabel37MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel37MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel37MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String a1 = jTextField_answer.getText();
        String a2 = jTextField_fullname.getText();
        String sql = "SELECT `Password` FROM `residentaccountinfo` WHERE `Answer`='"+a1+"'";
        String sql2 = "SELECT `Password` FROM `residentaccountinfo` WHERE `Firstname`='"+a2+"'";
        //`Firstname`='"+a2+"
        //WHERE `Firstname` = ? AND `Password` = ?
        try
        {
            pst = MyConnection.getConnection().prepareStatement(sql);
            pst = MyConnection.getConnection().prepareStatement(sql2);
            rs = pst.executeQuery();
            if(rs.next())
            {
                //jTextField_answer.setText(rs.getString("Answer"));
                Password.setText(rs.getString("Password"));
                rs.close();
                pst.close();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Incorrect Answer");
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed

    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton13MouseClicked
        this.setState(ICONIFIED);
    }//GEN-LAST:event_jButton13MouseClicked

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jTextField_AnswerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_AnswerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_AnswerActionPerformed

    private void jTextField_AnswerFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_AnswerFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_AnswerFocusLost

    private void jTextField_AnswerFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_AnswerFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_AnswerFocusGained

    private void jTextField_Username2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_Username2KeyReleased
        // username check
        String Username = jTextField_Username2.getText();

        try
        {
            Statement S = (Statement) MyConnection.getConnection().createStatement();
            ResultSet rs = S.executeQuery("SELECT * FROM `useraccountinfo` WHERE `Username` ='"+Username+"'");

            if(rs.next())
            {
                String yes = rs.getString("Username");
                System.out.println(yes);
                //Username.setForeground(Color.red);
                warning.setVisible(true);
            }
            else
            {
                System.out.println("No Users");
                warning.setVisible(false);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jTextField_Username2KeyReleased

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        jTabbedPane1.setSelectedIndex(0);
        this.jTabbedPane1.setEnabledAt(0, false);
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jTextField_FullnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_FullnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_FullnameActionPerformed

    private void SignUpResidentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignUpResidentActionPerformed
        // TODO add your handling code here:
        if(jTextField_Fullname.getText().equals("") || jTextField_Username2.getText().equals("") || jPasswordField4.getText().equals("") || jPasswordField5.getText().equals("") || jTextField_Answer.getText().equals(""))
        {
          JOptionPane.showMessageDialog(null, "Please Specified the Required Fields  (  *  )","Barangay System",JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            if(jPasswordField4.getText().equals(jPasswordField5.getText()))
            {
            String registerUserQuery = "INSERT INTO `useraccountinfo`(`Fullname`, `Username`, `Password`, `SecurityQ`, `Answer`) VALUES (?,?,?,?,?)";
            String UserCheck = jTextField_Username2.getText();
            String sql3 = "SELECT Username FROM `useraccountinfo` where Username='"+UserCheck+"'"; //check kung ang username is already taken
            String getUserID = "SELECT ID FROM `useraccountinfo` where Username='"+UserCheck+"'";
            //Connection con = (Connection) MyConnection.getConnection();
            try
            {
              pst = MyConnection.getConnection().prepareStatement(sql3);
              rs = pst.executeQuery();
           while(rs.next()){
           warning.setText(rs.getString(2));
           }

           if(warning.getText().equals("")){
               int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure?", "Barangay System",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if (selectedOption==JOptionPane.YES_OPTION)
                {
                    try{
                          //InputStream is = new FileInputStream(new File(s));
                          pst = MyConnection.getConnection().prepareStatement(registerUserQuery);
                          pst.setString(1,jTextField_Fullname.getText());
                          pst.setString(2,jTextField_Username2.getText());
                          pst.setString(3,jPasswordField4.getText());
                          pst.setString(4,jComboBox_Question1.getSelectedItem().toString());
                          pst.setString(5,jTextField_Answer.getText());
                          pst.executeUpdate();
                    try{
                    pst = MyConnection.getConnection().prepareStatement(getUserID); 
                    rs = pst.executeQuery();
                    while(rs.next()){
                    reportGetID2 = rs.getString(1);
                    report3();
                }
            }   catch(Exception e){JOptionPane.showMessageDialog(null, e);}
                    //con.close();
                    JOptionPane.showMessageDialog(null, "User Successfully Registered","Barangay System",JOptionPane.INFORMATION_MESSAGE);  
                    jTextField_Fullname.getText().equals("");
                    jTextField_Username2.getText().equals("");
                    jPasswordField4.getText().equals("");
                    jPasswordField5.getText().equals("");
                    jTextField_Answer.getText().equals("");
                    
           }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
            //JOptionPane.showMessageDialog(null,"Can't Connect, Please Contact your Administrator","Barangay System",JOptionPane.ERROR_MESSAGE);
        }
           }
               }else{
                //JOptionPane.showMessageDialog(null, "Username Already Exist");
               warning.setVisible(true);
               warning.setText("");
               
           }
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
            //JOptionPane.showMessageDialog(null,"Can't Connect, Please Contact your Administrator","Barangay System",JOptionPane.ERROR_MESSAGE);
        }
        }else{
            JOptionPane.showMessageDialog(null, "Password not Match","Barangay System",JOptionPane.ERROR_MESSAGE);
            }
        }
//         if(jTextField_Fullname1.getText().equals("") || jTextField_Username3.getText().equals("") || jPasswordField6.getText().equals("") || jPasswordField7.getText().equals("") || jTextField_Answer1.getText().equals(""))
//        {
//          JOptionPane.showMessageDialog(null, "Please Specified the Required Fields  (  *  )","Barangay System",JOptionPane.ERROR_MESSAGE);
//        }
//        else
//        {
//            if(jPasswordField6.getText().equals(jPasswordField7.getText()))
//            {
//            String registerUserQuery = "INSERT INTO `adminaccountinfo`(`Fullname`, `Username`, `Password`, `Position`, `SecurityQ`, `Answer`) VALUES (?,?,?,?,?,?)";
//            String UserCheck = jTextField_Username3.getText();
//            String sql2 = "SELECT Username FROM `adminaccountinfo` where Username='"+UserCheck+"'"; //check kung ang username is already taken
//            String getUserID = "SELECT ID FROM `adminaccountinfo` where Username='"+UserCheck+"'";
//            //Connection con = (Connection) MyConnection.getConnection();
//            try
//            {
//              pst = MyConnection.getConnection().prepareStatement(sql2);
//              rs = pst.executeQuery();
//           while(rs.next()){
//           warning1.setText(rs.getString(2));
//           }
//
//           if(warning1.getText().equals("")){
//               int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure?", "Barangay System",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
//                if (selectedOption==JOptionPane.YES_OPTION)
//                {
//                    try{
//                          //InputStream is = new FileInputStream(new File(s));
//                          pst = MyConnection.getConnection().prepareStatement(registerUserQuery);
//                          pst.setString(1,jTextField_Fullname1.getText());
//                          pst.setString(2,jTextField_Username3.getText());
//                          pst.setString(3,jPasswordField6.getText());
//                          pst.setString(4,jComboBox_Question3.getSelectedItem().toString());
//                          pst.setString(5,jComboBox_Question2.getSelectedItem().toString());
//                          pst.setString(6,jTextField_Answer1.getText());
//                          pst.executeUpdate();
//                    try{
//                    pst = MyConnection.getConnection().prepareStatement(getUserID); 
//                    rs = pst.executeQuery();
//                    while(rs.next()){
//                    reportGetID = rs.getString(1);
//                    report2();
//                }
//            }   catch(Exception e){JOptionPane.showMessageDialog(null, e);}
//                    //con.close();
//                    JOptionPane.showMessageDialog(null, "User Successfully Registered","Barangay System",JOptionPane.INFORMATION_MESSAGE);  
//                    jTextField_Fullname1.getText().equals("");
//                    jTextField_Username3.getText().equals("");
//                    jPasswordField6.getText().equals("");
//                    jPasswordField7.getText().equals("");
//                    jTextField_Answer1.getText().equals("");
//                    
//           }catch(Exception e){
//           JOptionPane.showMessageDialog(null, e);
//            //JOptionPane.showMessageDialog(null,"Can't Connect, Please Contact your Administrator","Barangay System",JOptionPane.ERROR_MESSAGE);
//        }
//           }
//               }else{
//                //JOptionPane.showMessageDialog(null, "Username Already Exist");
//               warning1.setVisible(true);
//               warning1.setText("");
//               
//           }
//        }catch(Exception e){
//           JOptionPane.showMessageDialog(null, e);
//            //JOptionPane.showMessageDialog(null,"Can't Connect, Please Contact your Administrator","Barangay System",JOptionPane.ERROR_MESSAGE);
//        }
//        }else{
//            JOptionPane.showMessageDialog(null, "Password not Match","Barangay System",JOptionPane.ERROR_MESSAGE);
//            }
//        }
    }//GEN-LAST:event_SignUpResidentActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed

    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton15MouseClicked
        this.setState(ICONIFIED);
    }//GEN-LAST:event_jButton15MouseClicked

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        String a1 = jTextField_answer1.getText();
        String sql = "SELECT `Password` FROM `adminaccountinfo` WHERE `Answer`='"+a1+"'";//"SELECT `Fullname`,`Username`, `Password`  FROM `UserAccountInfo` WHERE `username` = ? AND `password` = ?"
        try
        {
            pst = MyConnection.getConnection().prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next())
            {
                //jTextField_answer.setText(rs.getString("Answer"));
                Password1.setText(rs.getString("Password"));
                rs.close();
                pst.close();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Incorrect Answer");
            }
        
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jLabel56MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel56MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel56MouseClicked

    private void jTextField_fullname1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_fullname1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_fullname1FocusGained

    private void jTextField_fullname1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_fullname1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_fullname1FocusLost

    private void jTextField_fullname1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_fullname1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_fullname1ActionPerformed

    private void jTextField_fullname1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_fullname1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_fullname1KeyPressed

    private void jTextField_username1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_username1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_username1FocusGained

    private void jTextField_username1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_username1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_username1FocusLost

    private void jTextField_username1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_username1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_username1ActionPerformed

    private void jTextField_username1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_username1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_username1KeyPressed

    private void jButton_Search1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Search1ActionPerformed
        // TODO add your handling code here:
        String a1 = jTextField_fullname1.getText();
        String sql = "SELECT `Firstname`,`SecurityQ` FROM `adminaccountinfo` WHERE `Firstname`='"+a1+"'";
        try
        {
            pst = MyConnection.getConnection().prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next())
            {
                jTextField_username1.setText(rs.getString("Firstname"));
                jTextField_securityquestion1.setText(rs.getString("SecurityQ"));
                rs.close();
                pst.close();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Incorrect username");
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton_Search1ActionPerformed

    private void jTextField_securityquestion1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_securityquestion1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_securityquestion1FocusGained

    private void jTextField_securityquestion1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_securityquestion1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_securityquestion1FocusLost

    private void jTextField_securityquestion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_securityquestion1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_securityquestion1ActionPerformed

    private void jTextField_securityquestion1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_securityquestion1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_securityquestion1KeyPressed

    private void Password1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Password1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_Password1FocusGained

    private void Password1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Password1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_Password1FocusLost

    private void Password1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Password1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Password1ActionPerformed

    private void Password1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Password1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Password1KeyPressed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton20MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton20MouseClicked

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton20ActionPerformed

    private void ClearAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearAdminActionPerformed
        // TODO add your handling code here:
        jTextField_Fullname1.setText(null);
        jTextField_Username3.setText(null);
        jPasswordField6.setText(null);
        jPasswordField7.setText(null);
        jTextField_Answer1.setText(null);
    }//GEN-LAST:event_ClearAdminActionPerformed

    private void ClearResidentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearResidentActionPerformed
        // TODO add your handling code here:
        jTextField_Fullname.setText(null);
        jTextField_Username2.setText(null);
        jPasswordField4.setText(null);
        jPasswordField5.setText(null);
        jTextField_Answer.setText(null);
    }//GEN-LAST:event_ClearResidentActionPerformed

    private void AdminAccessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdminAccessActionPerformed
        // TODO add your handling code here:

        String password = String.valueOf(jPasswordField8.getPassword());

        String query = "SELECT  * FROM `admincreateaccountpassword` WHERE `password` = ?";

        try
        {
            pst = MyConnection.getConnection().prepareStatement(query);

            pst.setString(1, password);
            rs = pst.executeQuery();
            if(rs.next())
            {
                //show a new form
                jTabbedPane2.setSelectedIndex(2);
                jPasswordField8.setText(null);
            }else
            {
                //error message
                JOptionPane.showMessageDialog(null,"Invalid Username / Password","Login Error",2);
                WarningSign.setVisible(true);
                jPasswordField8.setText(null);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }//GEN-LAST:event_AdminAccessActionPerformed

    private void jButtonResidentLogin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResidentLogin1ActionPerformed
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(0);
    }//GEN-LAST:event_jButtonResidentLogin1ActionPerformed

    private void jTextField_FirstnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_FirstnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_FirstnameActionPerformed

    private void jTextField_LastnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_LastnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_LastnameActionPerformed

    private void jTextField_MiddlenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_MiddlenameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_MiddlenameActionPerformed

    private void jTextField_AgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_AgeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_AgeActionPerformed

    private void jTextField_PhonenumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_PhonenumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_PhonenumberActionPerformed

    private void jTextField_PhonenumberKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_PhonenumberKeyTyped
        // TODO add your handling code here:

        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE))
        {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField_PhonenumberKeyTyped

    private void jTextField_AddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_AddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_AddressActionPerformed

    private void jButton49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton49ActionPerformed
        // TODO add your handling code here:
        //String File;
        /**JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File f = fileChooser.getSelectedFile();
        String fileName = f.getAbsolutePath();
        Path.setText(fileName);
        Image getAsolutePath = null;
        ImageIcon icon = new ImageIcon(fileName);
        Image image = icon.getImage().getScaledInstance(jLabelProfileImage.getWidth(), jLabelProfileImage.getHeight(), Image.SCALE_DEFAULT);
        jLabelProfileImage.setIcon(icon);
        jLabelProfileImage.setIcon(ResizeImage(f));**/
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.jpg","*.png","*.gif","*.bmp");
        fileChooser.addChoosableFileFilter(filter);
        int result=fileChooser.showSaveDialog(null);
        if(result==JFileChooser.APPROVE_OPTION){
            File selectedFile = fileChooser.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            jLabelProfileImage3.setIcon(ResizeImage(path));
            imgPath = path;
            //cmdRegisterWithImage.setVisible(true);
            //cmdRegister.setVisible(false);
        }else if(result==JFileChooser.CANCEL_OPTION){
            jLabelProfileImage3.setIcon(null);
            //cmdRegister.setVisible(true);
            //cmdRegisterWithImage.setVisible(false);
            JOptionPane.showMessageDialog(null, "Select an Image","Barangay System",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton49ActionPerformed
    
    public ImageIcon ResizeImage(String imgPath){
        ImageIcon MyImage = new ImageIcon(imgPath);
        Image img = MyImage.getImage();
        Image newImage = img.getScaledInstance(jLabelProfileImage3.getWidth(), jLabelProfileImage3.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
        } 
    
    private void jButton50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton50ActionPerformed
        // TODO add your handling code here:
        if(jTextField_Firstname.getText().equals("") || jTextField_Middlename.getText().equals("") || jTextField_Lastname.getText().equals("") || jPasswordField9.getText().equals("") || jPasswordField10.getText().equals("") || jTextField_Answer2.getText().equals("") || jDateChooser_DOB.getDate().equals("") 
            || jTextField_Phonenumber.getText().equals("") ||  jLabelProfileImage3.getIcon().equals("") || jTextField_Age.getText().equals("") || jTextField_Address.getText().equals("")
        )

        {
            JOptionPane.showMessageDialog(null, "Please Specified the Required Fields  (  *  )","Barangay System",JOptionPane.ERROR_MESSAGE);
        }

        else
        {
            String registerUserQuery = "INSERT INTO `residentaccountinfo`(`Firstname`, `Middlename`, `Lastname`, `Birthdate`, `Age`, `Phonenumber`, `Address`, `Gender`, `profilepic`, `Password`, `SecretQ`, `Answer`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure?", "Barangay System",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if (selectedOption==JOptionPane.YES_OPTION)
            {
                 try{
                    InputStream img = new FileInputStream(new File(imgPath));
                    pst = MyConnection.getConnection().prepareStatement(registerUserQuery);
                    //InputStream is = new FileInputStream(new File(s));
                    pst.setString(1,jTextField_Firstname.getText());
                    pst.setString(2,jTextField_Middlename.getText());
                    pst.setString(3,jTextField_Lastname.getText());
                    pst.setString(4,((JTextField)jDateChooser_DOB.getDateEditor().getUiComponent()).getText());
                    pst.setString(5,jTextField_Age.getText());
                    pst.setString(6,jTextField_Phonenumber.getText());
                    pst.setString(7,jTextField_Address.getText());
                    pst.setString(8,jComboBox_Gender.getSelectedItem().toString());
                    pst.setBlob(9,img);
                    pst.setString(10,jPasswordField9.getText());
                    pst.setString(11,jComboBox_Question4.getSelectedItem().toString());
                    pst.setString(12,jTextField_Answer2.getText());
                    pst.executeUpdate();

                }   catch(Exception e){JOptionPane.showMessageDialog(null, e);}
                //con.close();
                JOptionPane.showMessageDialog(null, "User Successfully Registered","Barangay System",JOptionPane.INFORMATION_MESSAGE);
                jTextField_Firstname.setText(null);
                jTextField_Middlename.setText(null);
                jTextField_Lastname.setText(null);
                jDateChooser_DOB.setDate(null);
                jPasswordField10.setText(null);
                //jTextField_Age1.setText(null);
                jTextField_Address.setText(null);
                jPasswordField9.setText(null);
                jTextField_Phonenumber.setText(null);
                jTextField_Age.setText(null);
                jLabelProfileImage3.setIcon(null);
                jTextField_Answer2.setText(null);
            }
        }
    }//GEN-LAST:event_jButton50ActionPerformed

    private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton51ActionPerformed
        // TODO add your handling code here:
        jTextField_Firstname.setText(null);
                jTextField_Middlename.setText(null);
                jTextField_Lastname.setText(null);
                jDateChooser_DOB.setDate(null);
                jPasswordField10.setText(null);
                //jTextField_Age1.setText(null);
                jTextField_Address.setText(null);
                jPasswordField9.setText(null);
                jTextField_Phonenumber.setText(null);
                jTextField_Age.setText(null);
                jLabelProfileImage3.setIcon(null);
                jTextField_Answer2.setText(null);
    }//GEN-LAST:event_jButton51ActionPerformed

    private void jTextField_Answer2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_Answer2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Answer2FocusGained

    private void jTextField_Answer2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_Answer2FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Answer2FocusLost

    private void jTextField_Answer2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Answer2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Answer2ActionPerformed

    private void jButton52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton52ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton52ActionPerformed

    private void jTextField_Firstname1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Firstname1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Firstname1ActionPerformed

    private void jTextField_Lastname1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Lastname1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Lastname1ActionPerformed

    private void jTextField_Middlename1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Middlename1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Middlename1ActionPerformed

    private void jTextField_Age1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Age1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Age1ActionPerformed

    private void jTextField_Phonenumber1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Phonenumber1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Phonenumber1ActionPerformed

    private void jTextField_Phonenumber1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_Phonenumber1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Phonenumber1KeyTyped

    private void jTextField_Address1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Address1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Address1ActionPerformed

    private void jButton53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton53ActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.jpg","*.png","*.gif","*.bmp");
        fileChooser.addChoosableFileFilter(filter);
        int result=fileChooser.showSaveDialog(null);
        if(result==JFileChooser.APPROVE_OPTION){
            File selectedFile = fileChooser.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            jLabelProfileImage4.setIcon(ResizeImage(path));
            imgPath = path;
            //cmdRegisterWithImage.setVisible(true);
            //cmdRegister.setVisible(false);
        }else if(result==JFileChooser.CANCEL_OPTION){
            jLabelProfileImage4.setIcon(null);
            //cmdRegister.setVisible(true);
            //cmdRegisterWithImage.setVisible(false);
            JOptionPane.showMessageDialog(null, "Select an Image","Barangay System",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton53ActionPerformed

//    public ImageIcon ResizeImage(String imgPath){
//        ImageIcon MyImage = new ImageIcon(imgPath);
//        Image img = MyImage.getImage();
//        Image newImage = img.getScaledInstance(jLabelProfileImage3.getWidth(), jLabelProfileImage3.getHeight(), Image.SCALE_SMOOTH);
//        ImageIcon image = new ImageIcon(newImage);
//        return image;
//        }
    
    private void jButton54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton54ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        if(jTextField_Firstname1.getText().equals("") || jTextField_Middlename1.getText().equals("") || jTextField_Lastname1.getText().equals("") || jPasswordField11.getText().equals("") || jPasswordField12.getText().equals("") || jTextField_Answer3.getText().equals("") || jDateChooser_DOB1.getDate().equals("") 
            || jTextField_Phonenumber1.getText().equals("") ||  jLabelProfileImage4.getIcon().equals("") || jTextField_Age1.getText().equals("") || jTextField_Address1.getText().equals("")
        )

        {
            JOptionPane.showMessageDialog(null, "Please Specified the Required Fields  (  *  )","Barangay System",JOptionPane.ERROR_MESSAGE);
        }

        else
        {
            String registerUserQuery = "INSERT INTO `adminaccountinfo`(`Firstname`, `Middlename`, `Lastname`, `Birthdate`,`Age`,`Phonenumber`, `Address`,`Position`, `Profilepic`, `Password`, `SecurityQ`, `Answer`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            int selectedOption = JOptionPane.showConfirmDialog(null, "Are you sure?", "Barangay System",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if (selectedOption==JOptionPane.YES_OPTION)
            {
                try{
                    InputStream img = new FileInputStream(new File(imgPath));
                    pst = MyConnection.getConnection().prepareStatement(registerUserQuery);
                    //InputStream is = new FileInputStream(new File(s));
                    pst.setString(1,jTextField_Firstname1.getText());
                    pst.setString(2,jTextField_Middlename1.getText());
                    pst.setString(3,jTextField_Lastname1.getText());
                    pst.setString(4,((JTextField)jDateChooser_DOB1.getDateEditor().getUiComponent()).getText());
                    pst.setString(5,jTextField_Age1.getText());
                    pst.setString(6,jTextField_Phonenumber1.getText());
                    pst.setString(7,jTextField_Address1.getText());
                    pst.setString(8,jComboBox_Gender1.getSelectedItem().toString());
                    pst.setBlob(9,img);
                    pst.setString(10,jPasswordField11.getText());
                    pst.setString(11,jComboBox_Question5.getSelectedItem().toString());
                    pst.setString(12,jTextField_Answer3.getText());
                    pst.executeUpdate();

                }   catch(Exception e){JOptionPane.showMessageDialog(null, e);}
                //con.close();
                JOptionPane.showMessageDialog(null, "User Successfully Registered","Barangay System",JOptionPane.INFORMATION_MESSAGE);
                jTextField_Firstname1.setText(null);
                jTextField_Middlename1.setText(null);
                jTextField_Lastname1.setText(null);
                jDateChooser_DOB1.setDate(null);
                //jTextField_Age1.setText(null);
                jTextField_Address1.setText(null);
                jPasswordField12.setText(null);
                jPasswordField11.setText(null);
                jTextField_Phonenumber1.setText(null);
                jTextField_Age1.setText(null);
                jLabelProfileImage4.setIcon(null);
                jTextField_Answer3.setText(null);
            }
        }
    }//GEN-LAST:event_jButton54ActionPerformed

    private void jButton55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton55ActionPerformed
        // TODO add your handling code here:
        jTextField_Firstname1.setText(null);
                jTextField_Middlename1.setText(null);
                jTextField_Lastname1.setText(null);
                jDateChooser_DOB1.setDate(null);
                //jTextField_Age1.setText(null);
                jTextField_Address1.setText(null);
                jPasswordField12.setText(null);
                jPasswordField11.setText(null);
                jTextField_Phonenumber1.setText(null);
                jTextField_Age1.setText(null);
                jLabelProfileImage4.setIcon(null);
                jTextField_Answer3.setText(null);
    }//GEN-LAST:event_jButton55ActionPerformed

    private void jTextField_Answer3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_Answer3FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Answer3FocusGained

    private void jTextField_Answer3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_Answer3FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Answer3FocusLost

    private void jTextField_Answer3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_Answer3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_Answer3ActionPerformed

    private void jButton56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton56ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton56ActionPerformed

    private void jButton21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton21MouseClicked
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            String url = "www.twitter.com";
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        } catch (IOException ex) {
            Logger.getLogger(LoginMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton21MouseClicked

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton26MouseClicked
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            String url = "www.facebook.com";
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        } catch (IOException ex) {
            Logger.getLogger(LoginMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton26MouseClicked

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton27MouseClicked
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            String url = "www.youtube.com";
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        } catch (IOException ex) {
            Logger.getLogger(LoginMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton27MouseClicked

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton28MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton28MouseClicked

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton29MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton29MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton29MouseClicked

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton30MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton30MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton30MouseClicked

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton31MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton31MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton31MouseClicked

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton34MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton34MouseClicked

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        cardLayout.previous(jLabel_ImageSlider);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        cardLayout.next(jLabel_ImageSlider);
    }//GEN-LAST:event_jButton6ActionPerformed
    
    public boolean verifyFieldsResidentRegister()
    {
        String fname = jTextField_Fullname.getText();
        String uname = jTextField_Username2.getText();
        String pass1 = String.valueOf(jPasswordField4.getPassword());
        String pass2 = String.valueOf(jPasswordField5.getPassword());
        String securityQ = jComboBox_Question1.getSelectedItem().toString();
        String answer = jTextField_Answer.getText();
        if(fname.trim().equals("") || uname.trim().equals("") || pass1.trim().equals("") || pass2.trim().equals("") || answer.trim().equals(""))
        {
              JOptionPane.showMessageDialog(null, "One or More Fields Are Empty","Empty Fields",2);
              return false; 
           }
           // check if the two password are equals
           else if(pass1.equals(pass2))
           {
              return true;   
           }
           
           // if everything is ok
           else
           {
              JOptionPane.showMessageDialog(null, "Password Doesn't Match","Confirm Password",2);
              return false;
           }
    }
    public boolean verifyFieldsAdminRegister()
    {
        String fname = jTextField_Fullname1.getText();
        String uname = jTextField_Username3.getText();
        String pass1 = String.valueOf(jPasswordField6.getPassword());
        String pass2 = String.valueOf(jPasswordField7.getPassword());
//        String position = jComboBox_Question3.getSelectedItem().toString();
//        String securityQ = jComboBox_Question2.getSelectedItem().toString();
        String answer = jTextField_Answer1.getText();
        if(fname.trim().equals("") || uname.trim().equals("") || pass1.trim().equals("") || pass2.trim().equals("") || answer.trim().equals(""))
        {
              JOptionPane.showMessageDialog(null, "One or More Fields Are Empty","Empty Fields",2);
              return false; 
           }
           // check if the two password are equals
           else if(pass1.equals(pass2))
           {
              return true;   
           }
           
           // if everything is ok
           else
           {
              JOptionPane.showMessageDialog(null, "Password Doesn't Match","Confirm Password",2);
              return false;
           }
    }
    public boolean checkUsername(String Username)
     {
              PreparedStatement st;
              ResultSet rs;
              boolean username_exist = false;
              
              
              
              String a1 = jTextField_Username2.getText();
              String query = "SELECT * FROM `useraccountinfo` WHERE `Username` ='"+a1+"'";
              
      try {
      st = MyConnection.getConnection().prepareStatement(query);
            
      st.setString(2,Username);
      rs = st.executeQuery();
            
      if(rs.next())
      {
        username_exist = true;
        JOptionPane.showMessageDialog(null,"This Username is Already Taken. Choose Another One", "Username Failed",2);
      }
      } catch (SQLException ex) {
      Logger.getLogger(LoginMain.class.getName()).log(Level.SEVERE, null, ex);
      }
        return username_exist;              
      }
                   
    
     
    
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
            java.util.logging.Logger.getLogger(LoginMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new LoginMain().setVisible(true);
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AdminAccess;
    private javax.swing.JCheckBox CheckBox;
    private javax.swing.JCheckBox CheckBox1;
    private javax.swing.JButton ClearAdmin;
    private javax.swing.JButton ClearResident;
    private javax.swing.JTextField Password;
    private javax.swing.JTextField Password1;
    private javax.swing.JButton SignUpAdmin;
    private javax.swing.JButton SignUpResident;
    private javax.swing.JLabel WarningSign;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton54;
    private javax.swing.JButton jButton55;
    private javax.swing.JButton jButton56;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButtonAdminLogin;
    private javax.swing.JButton jButtonResidentLogin;
    private javax.swing.JButton jButtonResidentLogin1;
    private javax.swing.JButton jButton_Search;
    private javax.swing.JButton jButton_Search1;
    private javax.swing.JComboBox<String> jComboBox_Gender;
    private javax.swing.JComboBox<String> jComboBox_Gender1;
    private javax.swing.JComboBox<String> jComboBox_Question1;
    private javax.swing.JComboBox<String> jComboBox_Question2;
    private javax.swing.JComboBox<String> jComboBox_Question3;
    private javax.swing.JComboBox<String> jComboBox_Question4;
    private javax.swing.JComboBox<String> jComboBox_Question5;
    private com.toedter.calendar.JDateChooser jDateChooser_DOB;
    private com.toedter.calendar.JDateChooser jDateChooser_DOB1;
    private javax.swing.JDesktopPane jDesktopPane4;
    private javax.swing.JDesktopPane jDesktopPane5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelProfileImage3;
    private javax.swing.JLabel jLabelProfileImage4;
    private javax.swing.JLabel jLabel_ImageSlider;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField10;
    private javax.swing.JPasswordField jPasswordField11;
    private javax.swing.JPasswordField jPasswordField12;
    private javax.swing.JPasswordField jPasswordField4;
    private javax.swing.JPasswordField jPasswordField5;
    private javax.swing.JPasswordField jPasswordField6;
    private javax.swing.JPasswordField jPasswordField7;
    private javax.swing.JPasswordField jPasswordField8;
    private javax.swing.JPasswordField jPasswordField9;
    private javax.swing.JPasswordField jPasswordField_Password;
    private javax.swing.JPasswordField jPasswordField_Password1;
    private javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTextField jTextField_Address;
    private javax.swing.JTextField jTextField_Address1;
    private javax.swing.JTextField jTextField_Age;
    private javax.swing.JTextField jTextField_Age1;
    private javax.swing.JTextField jTextField_Answer;
    private javax.swing.JTextField jTextField_Answer1;
    private javax.swing.JTextField jTextField_Answer2;
    private javax.swing.JTextField jTextField_Answer3;
    private javax.swing.JTextField jTextField_Firstname;
    private javax.swing.JTextField jTextField_Firstname1;
    private javax.swing.JTextField jTextField_Fullname;
    private javax.swing.JTextField jTextField_Fullname1;
    private javax.swing.JTextField jTextField_Lastname;
    private javax.swing.JTextField jTextField_Lastname1;
    private javax.swing.JTextField jTextField_Middlename;
    private javax.swing.JTextField jTextField_Middlename1;
    private javax.swing.JTextField jTextField_Phonenumber;
    private javax.swing.JTextField jTextField_Phonenumber1;
    private javax.swing.JTextField jTextField_Username;
    private javax.swing.JTextField jTextField_Username1;
    private javax.swing.JTextField jTextField_Username2;
    private javax.swing.JTextField jTextField_Username3;
    private javax.swing.JTextField jTextField_answer;
    private javax.swing.JTextField jTextField_answer1;
    private javax.swing.JTextField jTextField_fullname;
    private javax.swing.JTextField jTextField_fullname1;
    private javax.swing.JTextField jTextField_securityquestion;
    private javax.swing.JTextField jTextField_securityquestion1;
    private javax.swing.JTextField jTextField_username;
    private javax.swing.JTextField jTextField_username1;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDate1;
    private javax.swing.JLabel lblDate2;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTime1;
    private javax.swing.JLabel lblTime2;
    private javax.swing.JLabel warning;
    private javax.swing.JLabel warning1;
    // End of variables declaration//GEN-END:variables

    private void setIconImage() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("mactanlogo.png")));
    }
}
