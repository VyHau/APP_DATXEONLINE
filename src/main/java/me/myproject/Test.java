package me.myproject;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import me.myproject.GUI.DangKyDriverView;
import me.myproject.GUI.DangKyUserView;
import me.myproject.Utilities.DIMENSION.DimensionFrame;

public class Test {
    private JFrame frame;
    private JPanel mainPanel;

    // Constructor
    public Test() {
        frame = new JFrame("Đăng nhập/Đăng ký");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(DimensionFrame.widthFrame, DimensionFrame.heightFrame);
        frame.setLayout(new BorderLayout());

        // Panel chứa các nút
        JPanel panel = new JPanel();
        JButton btnDK = new JButton("Đăng ký");
        JButton btnDN = new JButton("Đăng nhập");
        panel.add(btnDK);
        panel.add(btnDN);

        // Panel chính để chứa giao diện đăng ký/đăng nhập
        mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel, BorderLayout.CENTER);

        btnDK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll(); // Xóa giao diện cũ
                mainPanel.add(new DangKyUserView(), BorderLayout.CENTER);
                mainPanel.revalidate(); // Cập nhật giao diện
                mainPanel.repaint();
            }
        });

        btnDN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                mainPanel.add(new DangKyDriverView(), BorderLayout.CENTER);
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        // Thêm panel chứa nút vào frame
        frame.add(panel, BorderLayout.NORTH);
        frame.setVisible(true);
    }

   
}
