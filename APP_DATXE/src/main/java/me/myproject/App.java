// package me.myproject;

// import java.awt.BorderLayout;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// import javax.swing.JButton;
// import javax.swing.JFrame;
// import javax.swing.JPanel;

// import me.myproject.GUI.RegisterDriverGUI1;
// import me.myproject.GUI.RegisterUserGUI1;

// public class App {
//     public static void main(String[] args) {
//         // FrameMain frame=new FrameMain("Main");
//         // ImageIcon icon = new ImageIcon(ImageAccess.image1);
//         // // ImagePanel panel = new ImagePanel(ImageAccess.image2, (int)FrameMain.width-50, (int)FrameMain.height-100);
//         // // frame.add(panel);
//         // JLabel lbl = new JLabel(icon);
//         // // FrameMain.pnC.add(lbl);
//         // frame.pnlCenter.add(lbl);

//         // frame.add(frame.pnlLeft,BorderLayout.WEST);
//         // frame.add(frame.pnlTop,BorderLayout.NORTH);
//         // frame.add(frame.pnlCenter,BorderLayout.CENTER);

//         // frame.setVisible(true);
//         // new RegisterUserGUI();
//             JFrame frame = new JFrame("Đăng nhập/Đăng ký"); // Sửa lỗi FrameMain
//             frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//             frame.setSize(400, 300);
//             frame.setLayout(new BorderLayout());
    
//             // Panel chứa các nút
//             JPanel panel = new JPanel();
//             JButton btnDK=new JButton("Đăng ký");
//             JButton btnDN=new JButton("Đăng nhập");

//             btnDK.addActionListener(new ActionListener() {

//                 @Override
//                 public void actionPerformed(ActionEvent e) {
//                     frame.add(new RegisterUserGUI1(), BorderLayout.CENTER);
//                 }
               
//             });
//             btnDN.addActionListener(new ActionListener() {

//                 @Override
//                 public void actionPerformed(ActionEvent e) {
//                     frame.add(new RegisterDriverGUI1(), BorderLayout.CENTER);
//                 }
               
//             });
            

    
//             // Thêm panel vào frame
//             frame.add(panel, BorderLayout.NORTH);
    
//             // Thêm giao diện đăng ký
//             // Đảm bảo RegisterUserGUI là một JPanel
    
//             frame.setVisible(true);

//     }
// }
package me.myproject;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import me.myproject.GUI.RegisterDriverGUI1;
import me.myproject.GUI.RegisterUserGUI1;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Đăng nhập/Đăng ký");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Panel chứa các nút
        JPanel panel = new JPanel();
        JButton btnDK = new JButton("Đăng ký");
        JButton btnDN = new JButton("Đăng nhập");
        panel.add(btnDK);
        panel.add(btnDN);

        // Panel chính để chứa giao diện đăng ký/đăng nhập
        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel, BorderLayout.CENTER);

        btnDK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll(); // Xóa giao diện cũ
                mainPanel.add(new RegisterUserGUI1(), BorderLayout.CENTER);
                mainPanel.revalidate(); // Cập nhật giao diện
                mainPanel.repaint();
            }
        });

        btnDN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                mainPanel.add(new RegisterDriverGUI1(), BorderLayout.CENTER);
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        // Thêm panel chứa nút vào frame
        frame.add(panel, BorderLayout.NORTH);

        frame.setVisible(true);
    }
}
