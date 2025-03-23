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

import me.myproject.BUSINESSLOGIC.RegisterUserBSL;
import me.myproject.Utilities.ColorMain;
import me.myproject.Utilities.DIMENSION.DimensionFrame;
import me.myproject.Utilities.DIMENSION.FrameMain;

public class RegisterUserGUI extends FrameMain implements ActionListener,InputMethodListener{
    private final RegisterUserBSL bussiness;
    public JTextField tfdName, tfdEmail, tfdPhone;
    public JPasswordField tfdPass;
    public JButton btnRegister,btnBack ;

    public RegisterUserGUI() {
        super("Đăng kí User");
        bussiness=new RegisterUserBSL();
        this.init();
    }

    private void init() {
        // this.setLayout(new BorderLayout());
        JPanel panelCenter;


        JLabel lblPri = new JLabel("Đăng ký User", SwingConstants.CENTER);
        lblPri.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        lblPri.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lblPri.setForeground(ColorMain.colorRed);
        JPanel pnlTop=super.pnlTop;
        pnlTop.add(lblPri);
        pnlTop.setBackground(ColorMain.colorSecondary);
       
        JPanel panelWrapper = new JPanel();
        panelWrapper.setBorder(BorderFactory.createEmptyBorder(DimensionFrame.height10, DimensionFrame.height20, DimensionFrame.height10, DimensionFrame.height20));
        panelWrapper.setLayout(new BoxLayout(panelWrapper, BoxLayout.Y_AXIS));
        panelWrapper.setBackground(ColorMain.colorSecondary);
        
      
        panelCenter = new JPanel(new GridLayout(5, 2, 0, 10));
        panelCenter.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        panelCenter.setBackground(ColorMain.colorPrimary);
    
        JLabel lblName = new JLabel("Họ Tên:");
        lblName.setFont(new Font("Time New Romans", Font.CENTER_BASELINE, 15));
        lblName.setForeground(Color.WHITE);
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Time New Romans", Font.CENTER_BASELINE, 15));
        lblEmail.setForeground(Color.WHITE);
        JLabel lblPhone = new JLabel("Số điện thoại:");
        lblPhone.setFont(new Font("Time New Romans", Font.CENTER_BASELINE, 15));
        lblPhone.setForeground(Color.WHITE);
        JLabel lblPass = new JLabel("Mật khẩu:");
        lblPass.setFont(new Font("Time New Romans", Font.CENTER_BASELINE, 15));
        lblPass.setForeground(Color.WHITE);
    
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
    

        JScrollPane scrollPane = new JScrollPane(panelCenter);
        // scrollPane.setPreferredSize(new Dimension(panelWidth, panelHeight));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    
        panelWrapper.add(scrollPane);
        this.add(pnlTop, BorderLayout.NORTH);
        this.add(panelWrapper);
    
        this.setVisible(true);
    }

    @Override
    public void inputMethodTextChanged(InputMethodEvent event) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inputMethodTextChanged'");
    }

    @Override
    public void caretPositionChanged(InputMethodEvent event) {
        // TODO Auto-generated method stub
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
            
         


            JLabel label = new JLabel(message, JLabel.CENTER);
            JOptionPane.showMessageDialog(this, label, "Thông báo", 
                message.contains("thành công") ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
        } 
        else if (action.equals("Quay lại")) {
            new RegisterDriverGUI();
            this.dispose();
        }
    }
}
