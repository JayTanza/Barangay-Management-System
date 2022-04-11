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
public class SplashScreenLoad {
    
    public static void main(String[] args)
    {
       SplashScreen i =  new SplashScreen();
       i.setVisible(true);
       Dashboard m = new Dashboard();
       m.setVisible(false);
       
       try
       {
          for(int x=0; x<=100; x++)
          {
            Thread.sleep(20);
            SplashScreen.jLabel10.setText(Integer.toString(x)+"%");
            SplashScreen.jProgressBar2.setValue(x);
            
            if(x==100)
            {
              i.setVisible(false);
              m.setVisible(true);
            }
          }
       }catch(Exception e){
       } 
    }
}
    
