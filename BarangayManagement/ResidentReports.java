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
public class ResidentReports 
{
    private int cid;
    private String report;
    private String rname;
    private String date;
    private String time;
    
  
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }


    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public String getTime() {
        return time;
    }

    public void setDoc(String time) {
        this.time = time;
    }
    

    
    public ResidentReports(int cid, String report,String rname,String date,String time) {
        this.cid = cid;
        this.report = report;
        this.rname = rname;
        this.date = date;
        this.time = time;

    }    
}
