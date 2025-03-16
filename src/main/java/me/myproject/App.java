package me.myproject;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import me.myproject.Utilities.DIMENSION.FrameMain;
import me.myproject.Utilities.ImageAccess;
import me.myproject.Utilities.ImagePanel;

public class App{
public static void main(String[] args) {
        FrameMain frame=new FrameMain("Main");
        ImageIcon icon = new ImageIcon(ImageAccess.image1);
        ImagePanel panel = new ImagePanel(ImageAccess.image2, FrameMain.widthFrame-50, FrameMain.heightFrame-100);
        // frame.add(panel);
        JLabel lbl = new JLabel(icon);
        // FrameMain.pnC.add(lbl);
        FrameMain.pnC.add(panel);

        frame.add(FrameMain.pnL,BorderLayout.WEST);
        frame.add(FrameMain.pnT,BorderLayout.NORTH);
        frame.add(FrameMain.pnC,BorderLayout.CENTER);

        frame.setVisible(true);
        

      
  }
}
