package me.myproject.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import me.myproject.BUSINESSLOGIC.DangNhapBSL;
import me.myproject.Utilities.DIMENSION.FrameMain;

public class DangNhapView extends FrameMain implements ActionListener {
    private final DangNhapBSL bussiness;
    public JTextField tfdPhone;
    public JPasswordField tfdPass;
    public JButton btnLogin, btnForgetPass;

    public DangNhapView() {
        super("Đăng nhập");
        bussiness = new DangNhapBSL();
        init();
    }

    private void init() {
        // Thiết lập layout chính
        this.setLayout(new BorderLayout());
        
        // Lấy kích thước của FrameMain
        Dimension frameDimension = this.getSize();
        int frameWidth = frameDimension.width;
        int frameHeight = frameDimension.height;

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(frameWidth, frameHeight));
        
        // Hình nền - phủ toàn bộ frame
        JLabel backgroundLabel = new JLabel();
        ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/me/myproject/IMAGE/bgrmenu.png"));
        Image backgroundImage = backgroundIcon.getImage().getScaledInstance(frameWidth, frameHeight, Image.SCALE_SMOOTH);
        backgroundLabel.setIcon(new ImageIcon(backgroundImage));
        backgroundLabel.setBounds(0, 0, frameWidth, frameHeight);
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
    
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(230, 240, 255, 200)); 
        
        int panelWidth = (int)(frameWidth * 0.5);  // 50% chiều rộng của frame
        int panelHeight = (int)(frameHeight * 0.7); // 70% chiều cao của frame
        
        panel.setBounds((frameWidth - panelWidth) / 2, (frameHeight - panelHeight) / 2, panelWidth, panelHeight);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel titleLabel = new JLabel("ĐĂNG NHẬP");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, gbc);
        
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        JLabel phoneLabel = new JLabel("Nhập SDT");
        phoneLabel.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(phoneLabel, gbc);
        gbc.gridx = 1;
        tfdPhone = new JTextField(15);
        panel.add(tfdPhone, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel passLabel = new JLabel("Mật khẩu");
        passLabel.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(passLabel, gbc);
        gbc.gridx = 1;
        tfdPass = new JPasswordField(15);
        panel.add(tfdPass, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        JCheckBox rememberPasswordCheckBox = new JCheckBox("Nhớ mật khẩu");
        panel.add(rememberPasswordCheckBox, gbc);
        gbc.gridx = 1;
        btnForgetPass = new JButton("Quên mật khẩu?");
        btnForgetPass.setBorderPainted(false);
        btnForgetPass.setContentAreaFilled(false);
        btnForgetPass.setForeground(Color.BLUE);
        btnForgetPass.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnForgetPass.addActionListener(this);
        panel.add(btnForgetPass, gbc);
        
        // Nút "Đăng nhập"
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        btnLogin = new JButton("Đăng nhập");
        btnLogin.setBackground(new Color(0, 191, 255));
        btnLogin.setForeground(Color.BLACK);
        btnLogin.addActionListener(this);
        panel.add(btnLogin, gbc);
        
        gbc.gridy = 5;
        JLabel orLabel = new JLabel("OR");
        orLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(orLabel, gbc);
        
        gbc.gridy = 6;
        JLabel signUpLabel = new JLabel("Bạn chưa có tài khoản?");
        signUpLabel.setForeground(Color.BLUE);
        signUpLabel.setHorizontalAlignment(SwingConstants.CENTER);
        signUpLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signUpLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new DangKyView(); 
                dispose(); 
            }
        });
        panel.add(signUpLabel, gbc);
        layeredPane.add(panel, JLayeredPane.PALETTE_LAYER);
        this.add(layeredPane, BorderLayout.CENTER);
        
        // Đảm bảo rằng các thành phần được cập nhật đúng kích thước
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                Dimension size = getSize();
                layeredPane.setSize(size);
                backgroundLabel.setBounds(0, 0, size.width, size.height);
                int newPanelWidth = (int)(size.width * 0.5);
                int newPanelHeight = (int)(size.height * 0.7);
                panel.setBounds((size.width - newPanelWidth) / 2, (size.height - newPanelHeight) / 2, newPanelWidth, newPanelHeight);
            }
        });
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("Đăng nhập")) {
            String phone = tfdPhone.getText();
            String pass = String.valueOf(tfdPass.getPassword());
            String message = bussiness.xuLyDangNhap(phone, pass);
            ImageIcon icon = new ImageIcon("src/resources/image2.png");
            JLabel label = new JLabel(message, icon, JLabel.CENTER);
            JOptionPane.showMessageDialog(this, label, "Thông báo",message.contains("thành công") ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
        } else if (action.equals("Quên mật khẩu?")) {
            String phone = tfdPhone.getText().trim();
            String message = bussiness.xuLyQuenMatKhau(phone);
            JOptionPane.showMessageDialog(this, message, "Thông báo",message.contains("Mã OTP đã được gửi") ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
            if (!message.contains("Mã OTP đã được gửi")) 
                return; // Nếu gửi OTP thất bại, dừng lại

            String otpInput = JOptionPane.showInputDialog(this, "Nhập mã OTP đã gửi:", "Xác nhận OTP", JOptionPane.QUESTION_MESSAGE);
            String otpValidMessage = bussiness.xacThucOTP(phone, otpInput);
            JOptionPane.showMessageDialog(this, otpValidMessage, "Thông báo", otpValidMessage.contains("thành công") ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
            if (!otpValidMessage.contains("thành công")) 
                return; // Nếu xác thực OTP thất bại, dừng lại

            // Nhập mật khẩu mới
            JPasswordField newPassField = new JPasswordField();
            JPasswordField confirmPassField = new JPasswordField();
            Object[] fields = { "Mật khẩu mới:", newPassField, "Xác nhận mật khẩu:", confirmPassField };
            int option = JOptionPane.showConfirmDialog(this, fields, "Đổi mật khẩu", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                String newPass = new String(newPassField.getPassword()).trim();
                String confirmPass = new String(confirmPassField.getPassword()).trim();
                String updateMessage = bussiness.capNhatMatKhau(phone, newPass, confirmPass);
                ImageIcon icon = new ImageIcon("src/resources/image2.png");
                JLabel label = new JLabel(updateMessage, icon, JLabel.CENTER);
                JOptionPane.showMessageDialog(this, label, "Thông báo", updateMessage.contains("thành công") ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}