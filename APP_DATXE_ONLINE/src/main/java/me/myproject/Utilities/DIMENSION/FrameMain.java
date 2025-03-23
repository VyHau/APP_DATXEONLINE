package me.myproject.Utilities.DIMENSION;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import me.myproject.Utilities.ColorMain;

public class FrameMain extends  JFrame{
    public   JPanel pnlLeft;
    public   JPanel pnlTop;
    public   JPanel pnlCenter;

    public static double  width;
    public static double  height;
    private int widthFrame;
    private int heightFrame;
    
    public FrameMain(String title) {
        widthFrame=DimensionFrame.widthFrame;
        heightFrame=DimensionFrame.heightFrame;
        this.setSize(widthFrame, heightFrame);
        this.setTitle(title);
        this.setLayout(new BorderLayout());

        pnlLeft = new JPanel();
        pnlTop=new JPanel();
        pnlCenter=new JPanel();
   
        pnlLeft.setBackground(ColorMain.colorSecondary);
        pnlLeft.setPreferredSize(new java.awt.Dimension(widthFrame*1/10, heightFrame)); // Kích thước mong muốn
        pnlLeft.setMinimumSize(new java.awt.Dimension(widthFrame*1/10, heightFrame));   // Kích thước tối thiểu
        pnlLeft.setMaximumSize(new java.awt.Dimension(widthFrame*1/10, heightFrame)); 
      
        pnlTop.setBackground(ColorMain.colorPrimary);
        pnlTop.setPreferredSize(new java.awt.Dimension(widthFrame, heightFrame*1/10)); // Kích thước mong muốn
        pnlTop.setMinimumSize(new java.awt.Dimension(widthFrame, heightFrame*1/10));   // Kích thước tối thiểu
        pnlTop.setMaximumSize(new java.awt.Dimension(widthFrame, heightFrame*1/10)); 
        

        pnlCenter.setBackground(ColorMain.colorThird);

        FrameMain.width = getWidth();
        FrameMain.height = getHeight();

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // this.setVisible(true);
    }




    
}
