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
public class ResidentReportsQuery 
{
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    public boolean insertResidentDocuments(ResidentReports cont)
    {
       boolean contactIsCreated =  true;
       Connection con = (Connection) MyConnection.getConnection();
       PreparedStatement ps;
       
       //ps = con.prepareStatement("INSERT INTO `residentaccountinfo`(`Firstname`, `Middlename`, `Lastname`, `Birthdate`, `Age`, `Phonenumber`, `Address`, `Gender`, `Occupation`, `About`, `profilepic`, `Fathersname`, `Mothersname`, `FathersnameOcc`, `MothersnameOcc`, `Religion`, `No.Siblings`, `Birthrank`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
       //String registerUserQuery = "INSERT INTO `residentaccountinfo`(`Firstname`, `Middlename`, `Lastname`, `Birthdate`, `Age`, `Phonenumber`, `Address`, `Gender`,`profilepic`,`ResidentID`) VALUES (?,?,?,?,?,?,?,?,?,?)";
       //String registerUserQuery= "INSERT INTO `report`(`Report`, `Rname`, `Date`, `Time`) VALUES (?,?,?,?)";
       String registerUserQuery= "INSERT INTO `requestdoc`(`Firstname`, `Lastname`, `Address`, `Doc`, `Reason`) VALUES (?,?,?,?,?)";
       try{            
                    //InputStream img = new FileInputStream(new File(imgPath));
                    pst = MyConnection.getConnection().prepareStatement(registerUserQuery);
                    //InputStream is = new FileInputStream(new File(s));
                    pst.setString(1,cont.getReport());                 
                    pst.setString(2,cont.getRname());                 
                    pst.setString(3,cont.getDate());
                    pst.setString(4,cont.getTime());
                    //pst.setString(5,cont.getReason());
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
    public ArrayList<ResidentReports> contactList()
    {
       ArrayList<ResidentReports> clist = new ArrayList<ResidentReports>();
       
        java.sql.Connection con = MyConnection.getConnection();
        Statement st;
        ResultSet rs;
        try {
            //InputStream img = new FileInputStream(new File(imgPath));
            st = (Statement) con.createStatement();
            //rs = st.executeQuery("SELECT `ResidentID`, `Firstname`, `Middlename`, `Lastname`, `Birthdate`, `Age`, `Phonenumber`, `Address`, `Gender`, `profilepic`  FROM `residentaccountinfo`");
            //SELECT `ID`, `Firstname`, `Lastname`, `Address`, `Doc`, `Reason` FROM `requestdoc` WHERE
            //rs = st.executeQuery("SELECT `ID`, `Firstname`, `Lastname`, `Address`, `Doc`, `Reason` FROM `requestdoc`");
            //SELECT `ID`, `Report`, `Rname`, `Date`, `Time` * FROM `report`
            rs = st.executeQuery("SELECT `ID`, `Report`, `Rname`, `Date`, `Time`  FROM `report`");
            ResidentReports ct;
            
            while(rs.next())
            {         
                      ct = new ResidentReports(rs.getInt("ID"),
                                rs.getString("Report"),                            
                                rs.getString("Date"),
                                rs.getString("Time"),
                                rs.getString("Rname"));
                      clist.add(ct);
                    }
        } catch (SQLException ex) {
            Logger.getLogger(ResidentReportsQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clist;
    } 
}
