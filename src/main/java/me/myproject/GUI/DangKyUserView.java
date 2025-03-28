package me.myproject.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import me.myproject.BUSINESSLOGIC.DangKyUserBSL;
import me.myproject.Utilities.ColorMain;
import me.myproject.Utilities.DIMENSION.DimensionFrame;


public class DangKyUserView extends JPanel implements ActionListener, InputMethodListener {
    private final DangKyUserBSL bussiness;
    private JTextField tfdName, tfdEmail, tfdPhone;
    private JPasswordField tfdPass;
    private JButton btnRegister, btnBack;

    public DangKyUserView() {
        bussiness = new DangKyUserBSL();
        this.init();
    }

    private void init() {
        this.setLayout(new BorderLayout());
        this.setBackground(ColorMain.colorSecondary);

        // Tiêu đề
        JLabel lblPri = new JLabel("Đăng ký User", SwingConstants.CENTER);
        lblPri.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        lblPri.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lblPri.setForeground(ColorMain.colorRed);

        JPanel pnlTop = new JPanel();
        pnlTop.setBackground(ColorMain.colorSecondary);
        pnlTop.add(lblPri);
        this.add(pnlTop, BorderLayout.NORTH);

        JPanel panelWrapper = new JPanel();
        panelWrapper.setBorder(BorderFactory.createEmptyBorder(DimensionFrame.height10, DimensionFrame.height20, DimensionFrame.height10, DimensionFrame.height20));
        panelWrapper.setLayout(new BoxLayout(panelWrapper, BoxLayout.Y_AXIS));
        panelWrapper.setBackground(ColorMain.colorSecondary);
        // Panel trung tâm chứa form nhập liệu
        JPanel panelCenter = new JPanel(new GridLayout(5, 2, 0, 10));
        panelCenter.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        panelCenter.setBackground(ColorMain.colorPrimary);

        JLabel lblName = createLabel("Họ Tên:");
        JLabel lblEmail = createLabel("Email:");
        JLabel lblPhone = createLabel("Số điện thoại:");
        JLabel lblPass = createLabel("Mật khẩu:");

        tfdName = new JTextField(15);
        tfdEmail = new JTextField(15);
        tfdPhone = new JTextField(15);
        tfdPass = new JPasswordField(15);
        btnRegister = new JButton("Đăng ký");
        btnRegister.addActionListener(this);
        btnBack = new JButton("Quay lại");
        btnBack.addActionListener(this);

        panelCenter.add(lblName); panelCenter.add(tfdName);
        panelCenter.add(lblEmail); panelCenter.add(tfdEmail);
        panelCenter.add(lblPhone); panelCenter.add(tfdPhone);
        panelCenter.add(lblPass); panelCenter.add(tfdPass);
        panelCenter.add(btnBack); panelCenter.add(btnRegister);

        // Bọc form trong JScrollPane nếu nội dung dài
        JScrollPane scrollPane = new JScrollPane(panelCenter);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelWrapper.add(scrollPane);
        this.add(panelWrapper, BorderLayout.CENTER);
    }

    // Hàm hỗ trợ tạo JLabel với định dạng sẵn
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Time New Romans", Font.CENTER_BASELINE, 15));
        label.setForeground(Color.WHITE);
        return label;
    }

    @Override
    public void inputMethodTextChanged(InputMethodEvent event) {
        throw new UnsupportedOperationException("Unimplemented method 'inputMethodTextChanged'");
    }

    @Override
    public void caretPositionChanged(InputMethodEvent event) {
        throw new UnsupportedOperationException("Unimplemented method 'caretPositionChanged'");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("Đăng ký")) {
            String name = tfdName.getText();
            String email = tfdEmail.getText();
            String phone = tfdPhone.getText();
            String pass = new String(tfdPass.getPassword());

            String message = bussiness.xuLyDangKy(name, email, phone, pass);

            JOptionPane.showMessageDialog(this, message, "Thông báo", 
                message.contains("thành công") ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
        } else if (action.equals("Quay lại")) {
            // Nếu form được nhúng trong Frame, cần có phương thức xử lý quay lại
            SwingUtilities.getWindowAncestor(this).dispose();
        }
    }
}