package me.myproject.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import me.myproject.BUSINESSLOGIC.RegisterUserBSL;
import me.myproject.Utilities.ColorMain;
import me.myproject.Utilities.DIMENSION.FrameMain;

public class RegisterUserGUI {
    private FrameMain frame;
    private RegisterUserBSL bussiness;
    public RegisterUserGUI() {
        frame = new FrameMain("Đăng ký");
        bussiness=new RegisterUserBSL(this);
        init();
    }

    public void init() {
        frame.setLayout(new BorderLayout());
        JTextField tfdName, tfdEmail, tfdPhone, tfBirth;
        JPasswordField tfPass;
        JButton btnRegister,btnBack ;
        JPanel panelCenter;


        JLabel lblPri = new JLabel("Đăng ký User", SwingConstants.CENTER);
        lblPri.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        lblPri.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lblPri.setForeground(ColorMain.colorRed);
        JPanel pnlTop=frame.pnlTop;
        pnlTop.add(lblPri);
        pnlTop.setBackground(ColorMain.colorSecondary);
       
        JPanel panelWrapper = new JPanel();
        panelWrapper.setBorder(BorderFactory.createEmptyBorder(100, 150, 100, 150));
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
        tfBirth = new JTextField(15);
        tfPass = new JPasswordField(15);
        btnRegister = new JButton("Đăng ký");
        btnRegister.addActionListener(bussiness);
        btnBack = new JButton("Quay lại");
        btnBack.addActionListener(bussiness);
        
        
        panelCenter.add(lblName); panelCenter.add(tfdName);
        panelCenter.add(lblEmail); panelCenter.add(tfdEmail);
        panelCenter.add(lblPhone); panelCenter.add(tfdPhone);
        panelCenter.add(lblPass); panelCenter.add(tfPass);
        panelCenter.add(btnBack); panelCenter.add(btnRegister);
    

        JScrollPane scrollPane = new JScrollPane(panelCenter);
        // scrollPane.setPreferredSize(new Dimension(panelWidth, panelHeight));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    
        panelWrapper.add(scrollPane);
        frame.add(pnlTop, BorderLayout.NORTH);
        frame.add(panelWrapper);
    
        frame.setVisible(true);
    }
    public void dispose(){
        this.frame.dispose();
    }
}
