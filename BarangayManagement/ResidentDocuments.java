/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BarangayManagement;

/**
 *
 * @author Jay Tanza
 */
public class ResidentDocuments 
{
    private int cid;
    private String fname;
    private String lname;
    private String address;
    private String doc;
    private String reason;
  
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }


    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }
    
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    
    public ResidentDocuments(int cid, String fname,String lname,String address,String doc,String reason) {
        this.cid = cid;
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.doc = doc;
        this.reason = reason;

    }    
}
