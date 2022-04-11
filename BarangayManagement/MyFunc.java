package BarangayManagement;


import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jay Tanza
 */
public class MyFunc {
    public ImageIcon ResizeImage(String imgPath,byte[] BLOBpic, int width, int height){
        
         ImageIcon MyImage;
        if(imgPath!=null)
        {
           MyImage = new ImageIcon(imgPath);   
        }
        else
        {
            MyImage = new ImageIcon(BLOBpic); 
        }
        Image img = MyImage.getImage();
        Image newImage = img.getScaledInstance(width,height, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
        }
    public String browseImage(JLabel lbl)
    {
        String path = "";
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.jpg","*.png","*.gif","*.bmp");
        fileChooser.addChoosableFileFilter(filter);
        int result=fileChooser.showSaveDialog(null);
        if(result==JFileChooser.APPROVE_OPTION){
            File selectedFile = fileChooser.getSelectedFile();
            path = selectedFile.getAbsolutePath();
            lbl.setIcon(ResizeImage(path,null,lbl.getWidth(),lbl.getHeight()));
            //String pth = path;
            //cmdRegisterWithImage.setVisible(true);
            //cmdRegister.setVisible(false);
        }else if(result==JFileChooser.CANCEL_OPTION){
            lbl.setIcon(null);
            //cmdRegister.setVisible(true);
            //cmdRegisterWithImage.setVisible(false);
            JOptionPane.showMessageDialog(null, "No Image Selected","Warning!",JOptionPane.INFORMATION_MESSAGE);
        }
        return path;
    }
    
    
}
