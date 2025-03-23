package me.myproject.GUI;

import java.awt.BorderLayout;
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

import me.myproject.BUSINESSLOGIC.RegisterDriverBSL;
import me.myproject.Utilities.ColorMain;
import me.myproject.Utilities.DIMENSION.FrameMain;


public class RegisterDriverGUI {
    private FrameMain frame;
    private RegisterDriverBSL bussiness;
    public RegisterDriverGUI() {
        frame = new FrameMain("Đăng ký");
        bussiness=new RegisterDriverBSL(this);
        init();
    }

    public void init() {
        frame.setLayout(new BorderLayout());
        JTextField tfdName, tfdEmail, tfdPhone, tfBirth;
        JPasswordField tfPass;
        JButton btnRegister,btnBack ;
        JPanel panelCenter;


        JLabel lblPri = new JLabel("Đăng ký Driver", SwingConstants.CENTER);
        lblPri.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        lblPri.setFont(new Font("Times New Roman", Font.BOLD, 30));
        // JPanel pnlTop=frame.pnlTop;
        lblPri.setForeground(ColorMain.colorRed);
        JPanel pnlTop=frame.pnlTop;
        pnlTop.add(lblPri);
       
        JPanel panelWrapper = new JPanel();
        panelWrapper.setBorder(BorderFactory.createEmptyBorder(100, 150, 100, 150));
        panelWrapper.setLayout(new BoxLayout(panelWrapper, BoxLayout.Y_AXIS));
        panelWrapper.setBackground(ColorMain.colorPrimary);
        
      
        panelCenter = new JPanel(new GridLayout(6, 2, 0, 10));
        panelCenter.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        panelCenter.setBackground(ColorMain.colorSecondary);
    
        JLabel lblName = new JLabel("Họ Tên:");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblPhone = new JLabel("Số điện thoại:");
        JLabel lblBirth = new JLabel("Ngày sinh:");
        JLabel lblPass = new JLabel("Mật khẩu:");
    
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
        panelCenter.add(lblBirth); panelCenter.add(tfBirth);
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
        frame.dispose(); 
    }

}
