// File: ClientProject/src/me/myproject/GUI/DangKyView.java
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

import me.myproject.BUSINESSLOGIC.DangKyBSL;
import me.myproject.Utilities.DIMENSION.FrameMain;

public class DangKyView extends FrameMain implements ActionListener {
    private final DangKyBSL business;
    public JTextField tfdFullName, tfdBLXCode, tfdVehicleType, tfdLicensePlate, tfdPhone, tfdEmail;
    public JPasswordField tfdPass;
    public JButton btnRegister;

    public DangKyView() {
        super("Đăng ký");
        business = new DangKyBSL();
        init();
    }

    private void init() {
        // Thiết lập layout chính
        this.setLayout(new BorderLayout());

        // Lấy kích thước của FrameMain bằng getSize()
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
        // Đặt panel vào giữa frame
        panel.setBounds((frameWidth - panelWidth) / 2, (frameHeight - panelHeight) / 2, panelWidth, panelHeight);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel titleLabel = new JLabel("ĐĂNG KÝ");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        JLabel fullNameLabel = new JLabel("Họ tên");
        fullNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(fullNameLabel, gbc);
        gbc.gridx = 1;
        tfdFullName = new JTextField(15);
        panel.add(tfdFullName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel blxCodeLabel = new JLabel("Nhập mã BLX");
        blxCodeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(blxCodeLabel, gbc);
        gbc.gridx = 1;
        tfdBLXCode = new JTextField(15);
        panel.add(tfdBLXCode, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel vehicleTypeLabel = new JLabel("Nhập loại xe");
        vehicleTypeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(vehicleTypeLabel, gbc);
        gbc.gridx = 1;
        tfdVehicleType = new JTextField(15);
        panel.add(tfdVehicleType, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel licensePlateLabel = new JLabel("Nhập biển số");
        licensePlateLabel.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(licensePlateLabel, gbc);
        gbc.gridx = 1;
        tfdLicensePlate = new JTextField(15);
        panel.add(tfdLicensePlate, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel phoneLabel = new JLabel("SĐT");
        phoneLabel.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(phoneLabel, gbc);
        gbc.gridx = 1;
        tfdPhone = new JTextField(15);
        panel.add(tfdPhone, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(emailLabel, gbc);
        gbc.gridx = 1;
        tfdEmail = new JTextField(15);
        panel.add(tfdEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        JLabel passLabel = new JLabel("Mật khẩu");
        passLabel.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(passLabel, gbc);
        gbc.gridx = 1;
        tfdPass = new JPasswordField(15);
        panel.add(tfdPass, gbc);

        // Checkbox "Chấp nhận điều khoản"
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        JCheckBox termsCheckBox = new JCheckBox("Chấp nhận điều khoản");
        termsCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(termsCheckBox, gbc);

        // Nút "Đăng ký"
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        btnRegister = new JButton("Đăng ký");
        btnRegister.setBackground(new Color(0, 191, 255));
        btnRegister.setForeground(Color.BLACK);
        btnRegister.addActionListener(this);
        panel.add(btnRegister, gbc);

        gbc.gridy = 10;
        JLabel orLabel = new JLabel("OR");
        orLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(orLabel, gbc);

        gbc.gridy = 11;
        JLabel loginLabel = new JLabel("Bạn đã có tài khoản?");
        loginLabel.setForeground(Color.BLUE);
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new DangNhapView(); // Chuyển sang màn hình đăng nhập
                dispose(); // Đóng màn hình đăng ký
            }
        });
        panel.add(loginLabel, gbc);
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

        // Hiển thị frame
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("Đăng ký")) {
            String fullName = tfdFullName.getText().trim();
            String blxCode = tfdBLXCode.getText().trim();
            String vehicleType = tfdVehicleType.getText().trim();
            String licensePlate = tfdLicensePlate.getText().trim();
            String phone = tfdPhone.getText().trim();
            String email = tfdEmail.getText().trim();
            String pass = String.valueOf(tfdPass.getPassword()).trim();
            String message = business.xuLyDangKy(fullName, blxCode, vehicleType, licensePlate, phone, email, pass);
            ImageIcon icon = new ImageIcon("src/resources/image2.png");
            JLabel label = new JLabel(message, icon, JLabel.CENTER);
            JOptionPane.showMessageDialog(this, label, "Thông báo",
                    message.contains("thành công") ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new DangKyView();
    }
}