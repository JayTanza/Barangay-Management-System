/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BarangayManagement;

import com.mysql.jdbc.Statement;
import com.sun.jdi.connect.spi.Connection;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Jay Tanza
 */
public class ResidentRecordsQuery 
{
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    public boolean insertResidentRecords(ResidentRecords cont)
    {
       boolean contactIsCreated =  true;
       Connection con = (Connection) MyConnection.getConnection();
       PreparedStatement ps;
       
       //ps = con.prepareStatement("INSERT INTO `residentaccountinfo`(`Firstname`, `Middlename`, `Lastname`, `Birthdate`, `Age`, `Phonenumber`, `Address`, `Gender`, `Occupation`, `About`, `profilepic`, `Fathersname`, `Mothersname`, `FathersnameOcc`, `MothersnameOcc`, `Religion`, `No.Siblings`, `Birthrank`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
       String registerUserQuery = "INSERT INTO `residentaccountinfo`(`Firstname`, `Middlename`, `Lastname`, `Birthdate`, `Age`, `Phonenumber`, `Address`, `Gender`,`profilepic`,`ResidentID`) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try{            
                    //InputStream img = new FileInputStream(new File(imgPath));
                    pst = MyConnection.getConnection().prepareStatement(registerUserQuery);
                    //InputStream is = new FileInputStream(new File(s));
                    pst.setString(1,cont.getFname());
                    pst.setString(2,cont.getMname());
                    pst.setString(3,cont.getLname());
                    pst.setString(4,cont.getBirthdate());
                    pst.setString(5,cont.getAge());
                    pst.setString(6,cont.getPhonenumber());
                    pst.setString(7,cont.getAddress());
                    pst.setString(8,cont.getGender());
                    pst.setBytes(9,cont.getPic());
                    pst.setInt(10,cont.getCid());
                    pst.executeUpdate();
                    if(pst.executeUpdate() != 0)
                    {
                       JOptionPane.showMessageDialog(null, "New Contact Added");
                    }
                    else
                    {
                       JOptionPane.showMessageDialog(null, "Something Wrong");
                       contactIsCreated = false;
                    }

        }   catch(Exception e){JOptionPane.showMessageDialog(null, e);}
        
       return contactIsCreated ;
    }   
    
    // create a list of contact
    public ArrayList<ResidentRecords> contactList()
    {
       ArrayList<ResidentRecords> clist = new ArrayList<ResidentRecords>();
       
        java.sql.Connection con = MyConnection.getConnection();
        Statement st;
        ResultSet rs;
        try {
            //InputStream img = new FileInputStream(new File(imgPath));
            st = (Statement) con.createStatement();
            rs = st.executeQuery("SELECT `ResidentID`, `Firstname`, `Middlename`, `Lastname`, `Birthdate`, `Age`, `Phonenumber`, `Address`, `Gender`, `profilepic`  FROM `residentaccountinfo`");
            
            ResidentRecords ct;
            
            while(rs.next())
            {         
                      ct = new ResidentRecords(rs.getInt("ResidentID"),
                                rs.getString("Firstname"),
                                rs.getString("Middlename"),
                                rs.getString("Lastname"),
                                rs.getString("Birthdate"),
                                rs.getString("Age"),
                                rs.getString("Phonenumber"),
                                rs.getString("Address"),
                                rs.getString("Gender"),
                                rs.getBytes("profilepic"));
                      clist.add(ct);
                    }
        } catch (SQLException ex) {
            Logger.getLogger(ResidentRecordsQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clist;
    } 
}
