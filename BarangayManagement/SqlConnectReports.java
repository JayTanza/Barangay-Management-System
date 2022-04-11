/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BarangayManagement;
import java.sql.*;
import javax.swing.*;
/**
 *
 * @author Admin
 */
public class SqlConnectReports {
    Connection con=null;
    public static Connection ConnectDB(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost/barangaysystemreports","root","");
            //JOptionPane.showMessageDialog(null, "Connected to Database");
            return con;
        }
        
    catch(Exception e){
    //JOptionPane.showMessageDialog(null, e);
    return null;
}
}
}
