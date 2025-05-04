package me.myproject.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import me.myproject.Utilities.DIMENSION.FrameMain;
import me.myproject.Utilities.ColorMain;

public class NhanChuyenView extends FrameMain implements ActionListener {
    private JPanel mainPanel, contentPanel, headerPanel, statusPanel, statsPanel, tipsPanel;
    private JLabel lblTitle, lblLocation, lblSearchingStatus, lblStatusMessage;
    private JLabel lblStatsTitle, lblTripsLabel, lblTripsValue, lblIncomeLabel, lblIncomeValue;
    private JLabel lblTipsTitle;
    private JButton btnBack, btnRefresh;
    private Timer activeTimer;
    
    public NhanChuyenView() {
        super("Nhận Chuyến - Ứng Dụng Đặt Xe");
        init();
    }
    
    private void init() {
        // Thiết lập layout chính
        this.setLayout(new BorderLayout());
        
        // Tạo các panel chính
        createMainPanels();
        
        // Tạo header
        createHeader();
        
        // Tạo nội dung chính
        createContent();
        
        // Hiển thị frame
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    private void createMainPanels() {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
    
        // Thêm một EmptyBorder để tạo khoảng cách giữa đường viền và lề của JFrame
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Tạo khoảng cách 10px từ viền màn hình
    
        // Panel nội dung chính
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2)); // Đường viền cho contentPanel
    
        // Panel header
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(ColorMain.blueHeader);
        headerPanel.setPreferredSize(new Dimension(this.getWidth(), 70));
    
        // Thêm các panel vào panel chính
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
    }
    
    private void createHeader() {
        // Panel bên trái của header với nút Back
        JPanel leftHeaderPanel = new JPanel();
        leftHeaderPanel.setLayout(new BoxLayout(leftHeaderPanel, BoxLayout.X_AXIS));
        leftHeaderPanel.setBackground(ColorMain.blueHeader);
        leftHeaderPanel.setBorder(new EmptyBorder(5, 10, 5, 5));
        
        // Nút Back
        ImageIcon backIcon = new ImageIcon(getClass().getResource("/me/myproject/IMAGE/back.png"));
        btnBack = new JButton();
        btnBack.setIcon(backIcon);
        btnBack.setBackground(ColorMain.blueHeader);
        btnBack.setBorderPainted(false);
        btnBack.setFocusPainted(false);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBack.addActionListener(this);
        
        // Tiêu đề và địa điểm
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(ColorMain.blueHeader);
        
        lblTitle = new JLabel("Nhận Chuyến");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        lblLocation = new JLabel("Khu vực: Quận Thanh Khê, Đà Nẵng");
        lblLocation.setFont(new Font("Arial", Font.PLAIN, 12));
        lblLocation.setForeground(Color.WHITE);
        lblLocation.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        titlePanel.add(lblTitle);
        titlePanel.add(lblLocation);
        
        leftHeaderPanel.add(btnBack);
        leftHeaderPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        leftHeaderPanel.add(titlePanel);
        
        // Panel bên phải của header với nút Refresh
        JPanel rightHeaderPanel = new JPanel();
        rightHeaderPanel.setLayout(new BoxLayout(rightHeaderPanel, BoxLayout.X_AXIS));
        rightHeaderPanel.setBackground(ColorMain.blueHeader);
        rightHeaderPanel.setBorder(new EmptyBorder(5, 5, 5, 10));
        
        btnRefresh = new JButton("Tải lại");
        btnRefresh.setFont(new Font("Arial", Font.BOLD, 14));
        btnRefresh.setForeground(Color.WHITE);
        btnRefresh.setBackground(ColorMain.btnHeader1);
        btnRefresh.setBorderPainted(false);
        btnRefresh.setFocusPainted(true);
        btnRefresh.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRefresh.addActionListener(this);
        
        rightHeaderPanel.add(btnRefresh);
        
        // Thêm cả hai panel vào header
        headerPanel.add(leftHeaderPanel, BorderLayout.WEST);
        headerPanel.add(rightHeaderPanel, BorderLayout.EAST);
    }
    
    private void createContent() {
        // Panel trạng thái tìm kiếm
        createStatusPanel();
        
        // Panel thống kê ngày hôm nay
        createStatsPanel();
        
        // Panel mẹo hữu ích
        createTipsPanel();

        // Thêm tất cả vào content panel
        contentPanel.add(statusPanel);
        contentPanel.add(statsPanel);
        contentPanel.add(tipsPanel);
        
        // Thêm border cho contentPanel
        contentPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 2),
            new EmptyBorder(5, 5, 5, 5)
        ));
    }
    
    private void createStatusPanel() {
        statusPanel = new JPanel();
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
        statusPanel.setBackground(Color.WHITE);
        statusPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
                new EmptyBorder(20, 20, 20, 20)
        ));
        statusPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Panel để chứa biểu tượng tìm kiếm (hình vuông với đường viền đứt)
        JPanel searchIconPanel = new JPanel();
        searchIconPanel.setLayout(new GridBagLayout());
        searchIconPanel.setBackground(Color.WHITE);
        searchIconPanel.setBorder(BorderFactory.createDashedBorder(Color.GRAY, 2, 2));
        searchIconPanel.setPreferredSize(new Dimension(80, 80));
        searchIconPanel.setMaximumSize(new Dimension(80, 80));
        searchIconPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Thêm biểu tượng tìm kiếm vào trong panel (có thể thay thế bằng hình ảnh thực)
        ImageIcon searchIcon = new ImageIcon(getClass().getResource("/me/myproject/IMAGE/back.png"));
        JLabel searchIconLabel = new JLabel(searchIcon);
        searchIconPanel.add(searchIconLabel);
        
        // Label thông báo trạng thái tìm kiếm
        lblSearchingStatus = new JLabel("Đang tìm kiếm chuyến đi");
        lblSearchingStatus.setFont(new Font("Arial", Font.BOLD, 18));
        lblSearchingStatus.setForeground(new Color(60, 60, 60));
        lblSearchingStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        lblStatusMessage = new JLabel("Chúng tôi sẽ thông báo khi có chuyến xe mới");
        lblStatusMessage.setFont(new Font("Arial", Font.PLAIN, 14));
        lblStatusMessage.setForeground(new Color(120, 120, 120));
        lblStatusMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Panel trạng thái hoạt động
        JPanel activeStatusPanel = new JPanel();
        activeStatusPanel.setLayout(new BoxLayout(activeStatusPanel, BoxLayout.X_AXIS));
        activeStatusPanel.setBackground(Color.WHITE);
        activeStatusPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY),
                new EmptyBorder(10, 0, 0, 0)
        ));
        activeStatusPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        activeStatusPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        
        // Chỉ báo trạng thái hoạt động (hình vuông xanh)
        JPanel activeIndicator = new JPanel();
        activeIndicator.setBackground(new Color(76, 175, 80));
        activeIndicator.setPreferredSize(new Dimension(15, 15));
        activeIndicator.setMaximumSize(new Dimension(15, 15));
        activeIndicator.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        
        JLabel lblActive = new JLabel("Đang Hoạt Động");
        lblActive.setFont(new Font("Arial", Font.BOLD, 14));
        lblActive.setForeground(new Color(76, 175, 80));
        
        activeStatusPanel.add(activeIndicator);
        activeStatusPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        activeStatusPanel.add(lblActive);
        
        // Thêm tất cả vào panel trạng thái
        statusPanel.add(Box.createVerticalStrut(10));
        statusPanel.add(searchIconPanel);
        statusPanel.add(Box.createVerticalStrut(20));
        statusPanel.add(lblSearchingStatus);
        statusPanel.add(Box.createVerticalStrut(5));
        statusPanel.add(lblStatusMessage);
        statusPanel.add(Box.createVerticalStrut(20));
        statusPanel.add(activeStatusPanel);
    }
        
    private void createStatsPanel() {
        statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setBackground(Color.WHITE);
        statsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
                new EmptyBorder(20, 20, 20, 20)  // Tăng padding từ 15px lên 20px
        ));
        statsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Tiêu đề panel
        lblStatsTitle = new JLabel("Hôm nay của bạn");
        lblStatsTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lblStatsTitle.setForeground(new Color(60, 60, 60));
        lblStatsTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Panel chứa thông tin thống kê
        JPanel statsInfoPanel = new JPanel();
        statsInfoPanel.setLayout(new BoxLayout(statsInfoPanel, BoxLayout.X_AXIS));
        statsInfoPanel.setBackground(Color.WHITE);
        statsInfoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Panel chuyến đi - Tăng chiều cao từ 50px lên 70px
        JPanel tripsPanel = new JPanel();
        tripsPanel.setLayout(new BoxLayout(tripsPanel, BoxLayout.Y_AXIS));
        tripsPanel.setBackground(Color.WHITE);
        tripsPanel.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));
        tripsPanel.setPreferredSize(new Dimension(0, 70)); // Tăng chiều cao
        tripsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70)); // Tăng chiều cao tối đa
        
        lblTripsLabel = new JLabel("Chuyến đi:");
        lblTripsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        lblTripsLabel.setForeground(new Color(120, 120, 120));
        lblTripsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        lblTripsValue = new JLabel("5");
        lblTripsValue.setFont(new Font("Arial", Font.BOLD, 24));
        lblTripsValue.setForeground(new Color(33, 150, 243));
        lblTripsValue.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Sử dụng FlowLayout cho nội dung trong panel
        JPanel tripContentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        tripContentPanel.setBackground(Color.WHITE);
        tripContentPanel.add(lblTripsLabel);
        tripContentPanel.add(lblTripsValue);
        
        tripsPanel.add(Box.createVerticalGlue());
        tripsPanel.add(tripContentPanel);
        tripsPanel.add(Box.createVerticalGlue());
        
        // Panel thu nhập - Tăng chiều cao từ 50px lên 70px
        JPanel incomePanel = new JPanel();
        incomePanel.setLayout(new BoxLayout(incomePanel, BoxLayout.Y_AXIS));
        incomePanel.setBackground(Color.WHITE);
        incomePanel.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));
        incomePanel.setPreferredSize(new Dimension(0, 70)); // Tăng chiều cao
        incomePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70)); // Tăng chiều cao tối đa
        
        lblIncomeLabel = new JLabel("Thu nhập:");
        lblIncomeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        lblIncomeLabel.setForeground(new Color(120, 120, 120));
        lblIncomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        lblIncomeValue = new JLabel("350.000 đ");
        lblIncomeValue.setFont(new Font("Arial", Font.BOLD, 24));
        lblIncomeValue.setForeground(new Color(76, 175, 80));
        lblIncomeValue.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Sử dụng FlowLayout cho nội dung trong panel
        JPanel incomeContentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        incomeContentPanel.setBackground(Color.WHITE);
        incomeContentPanel.add(lblIncomeLabel);
        incomeContentPanel.add(lblIncomeValue);
        
        incomePanel.add(Box.createVerticalGlue());
        incomePanel.add(incomeContentPanel);
        incomePanel.add(Box.createVerticalGlue());
        
        // Thêm vào panel chứa - Các panel trải rộng để chiếm toàn bộ chiều rộng có sẵn
        statsInfoPanel.add(tripsPanel);
        statsInfoPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        statsInfoPanel.add(incomePanel);
        
        // Đảm bảo mỗi thành phần chiếm tỷ lệ bằng nhau
        tripsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        incomePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Thêm vào panel thống kê
        statsPanel.add(lblStatsTitle);
        statsPanel.add(Box.createVerticalStrut(15));
        statsPanel.add(statsInfoPanel);
    }
    
    private void createTipsPanel() {
        tipsPanel = new JPanel();
        tipsPanel.setLayout(new BoxLayout(tipsPanel, BoxLayout.Y_AXIS));
        tipsPanel.setBackground(Color.WHITE);
        tipsPanel.setBorder(new EmptyBorder(15, 20, 15, 20));  // Giữ border cho panel mẹo
        tipsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    
        // Tiêu đề panel
        lblTipsTitle = new JLabel("Mẹo hữu ích");
        lblTipsTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lblTipsTitle.setForeground(new Color(60, 60, 60));
        lblTipsTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
    
        // Panel chứa các mẹo - sử dụng GridLayout để bố trí theo hàng ngang
        JPanel tipsContentPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        tipsContentPanel.setBackground(Color.WHITE);
        tipsContentPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    
        // Tạo các tip với ảnh
        JPanel tipPhonePanel = createTip("Đảm bảo điện thoại luôn đủ pin và có kết nối mạng ổn định", 
                                        "/me/myproject/IMAGE/bongden.png");
        JPanel tipRefreshPanel = createTip("Nhấn nút Tải lại để cập nhật tìm kiếm mới", 
                                        "/me/myproject/IMAGE/bongden.png");
    
        // Thêm vào panel chứa
        tipsContentPanel.add(tipPhonePanel);
        tipsContentPanel.add(tipRefreshPanel);
    
        // Thêm vào panel mẹo
        tipsPanel.add(lblTipsTitle);
        tipsPanel.add(Box.createVerticalStrut(15)); // Tạo khoảng cách giữa tiêu đề và các mẹo
        tipsPanel.add(tipsContentPanel);
    }    
    private JPanel createTip(String text, String imagePath) {
        JPanel tipPanel = new JPanel();
        tipPanel.setLayout(new BoxLayout(tipPanel, BoxLayout.X_AXIS));
        tipPanel.setBackground(Color.WHITE);
        tipPanel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1, true));
    
        // Giảm chiều cao của panel mẹo - Áp dụng bằng cách đặt kích thước ưa thích
        tipPanel.setPreferredSize(new Dimension(0, 40)); // Giảm chiều cao xuống 40px
        tipPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
    
        // Thêm ảnh thực tế thay vì placeholder
        JLabel imageLabel = new JLabel();
        try {
            ImageIcon tipIcon = new ImageIcon(getClass().getResource(imagePath));
            // Đảm bảo ảnh không bị tràn bằng cách thay đổi kích thước ảnh
            Image img = tipIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Thay đổi kích thước ảnh
            imageLabel.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            // Fallback khi không tìm thấy ảnh - hiển thị placeholder
            JPanel imagePanel = new JPanel();
            imagePanel.setBackground(Color.LIGHT_GRAY);
            imagePanel.setPreferredSize(new Dimension(30, 30));
            imagePanel.setMaximumSize(new Dimension(30, 30));
            imagePanel.setMinimumSize(new Dimension(30, 30));
            imagePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            imageLabel.add(imagePanel);
        }
    
        // Tạo label văn bản
        JLabel textLabel = new JLabel(text);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        textLabel.setForeground(new Color(80, 80, 80));
        textLabel.setBorder(new EmptyBorder(5, 10, 5, 10));
    
        // Thêm vào panel
        tipPanel.add(Box.createHorizontalStrut(10)); // Thêm khoảng cách bên trái
        tipPanel.add(imageLabel);
        tipPanel.add(Box.createHorizontalStrut(5)); // Khoảng cách giữa ảnh và text
        tipPanel.add(textLabel);
        tipPanel.add(Box.createHorizontalGlue()); // Đảm bảo panel mở rộng
    
        return tipPanel;
    }
    @Override
    public void dispose() {
        if (activeTimer != null && activeTimer.isRunning()) {
            activeTimer.stop();
        }
        super.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnBack) {
            // Trở về trang chủ
            this.dispose();
            new TrangChuDriverView();
        } else if (source == btnRefresh) {
            // Cập nhật tìm kiếm
            // Để đơn giản, chỉ cần thay đổi giao diện hiển thị (ví dụ: đổi màu nút)
            btnRefresh.setBackground(new Color(0, 120, 109));
            // Sau 1 giây, đổi trở lại màu ban đầu
            Timer colorTimer = new Timer(1000, evt -> {
                btnRefresh.setBackground(new Color(0, 150, 136));
            });
            colorTimer.setRepeats(false);
            colorTimer.start();
        }
    }
    
    // Main method cho việc kiểm thử độc lập
    public static void main(String[] args) {
        new NhanChuyenView();
    }
}