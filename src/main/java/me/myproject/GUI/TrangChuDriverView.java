package me.myproject.GUI;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;  
import me.myproject.Utilities.ColorMain;
import me.myproject.Utilities.DIMENSION.FrameMain;

public class TrangChuDriverView extends FrameMain implements ActionListener {
    private JPanel mainPanel, headerPanel, menuPanel, contentPanel;
    private JLabel logoLabel, timeLabel, driverNameLabel, phuongTienLbl, bienSoXeLbl, thuNhapNowLbl, chaoTaiXeLbl;
    private JButton btnBatDauChuyen, btnNhanChuyen, btnKetThucChuyen, btnTrangThai, btnDanhGia, btnTKNganHang, btnLichSuChuyen, btnTroChuyen, btnDangXuat;
    private Timer timeTimer;
    private DateTimeFormatter timeFormatter;
    
    public TrangChuDriverView() 
    {
        super("Trang Chủ - Tài xế");
        init();
    }
    
    public void init() {
        // Thiết lập layout chính
        this.setLayout(new BorderLayout());
        
        // Lấy kích thước của FrameMain
        Dimension frameDimension = this.getSize();
        int frameWidth = frameDimension.width;
        int frameHeight = frameDimension.height;
        
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(frameWidth, frameHeight));
        
        // Tạo các panel chính
        createMainPanels();
        
        // Tạo header
        createHeader();
        
        // Tạo menu bên trái
        createLeftMenu();
        
        // Tạo nội dung chính
        createContent();
        
        // Thêm các panel vào layered pane
        mainPanel.setBounds(0, 70, frameWidth, frameHeight - 70);
        layeredPane.add(mainPanel, JLayeredPane.PALETTE_LAYER);
        
        headerPanel.setBounds(0, 0, frameWidth, 70);
        layeredPane.add(headerPanel, JLayeredPane.PALETTE_LAYER);
        
        this.add(layeredPane, BorderLayout.CENTER);
        
        // Đảm bảo rằng các thành phần được cập nhật đúng kích thước
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                Dimension size = getSize();
                layeredPane.setSize(size);
                headerPanel.setBounds(0, 0, size.width, 70);
                mainPanel.setBounds(0, 70, size.width, size.height - 70);
            }
        });
        
        // Hiển thị frame
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    private void createHeader() {
        // Tạo header panel
        headerPanel.setBackground(ColorMain.blueHeader);
        headerPanel.setPreferredSize(new Dimension(this.getWidth(), 70));
        
        // Container chính
        JPanel headerContentPanel = new JPanel(new BorderLayout());
        headerContentPanel.setBackground(ColorMain.blueHeader);
        
        // Phần trái: Logo
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        logoPanel.setBackground(ColorMain.blueHeader);
        logoPanel.setBorder(new EmptyBorder(5, 10, 5, 0));
        logoPanel.setPreferredSize(new Dimension(150, 70));
        
        // Thiết lập logo
        ImageIcon logoApp = new ImageIcon(getClass().getResource("/me/myproject/IMAGE/logo1.png"));
        Image logoImg = logoApp.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        logoApp = new ImageIcon(logoImg);
        JLabel logoImageLabel = new JLabel(logoApp);
        
        logoLabel = new JLabel("CRAB");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 18));
        logoLabel.setForeground(Color.WHITE);
        
        logoPanel.add(logoImageLabel);
        logoPanel.add(logoLabel);
        
        // Phần trạng thái: "Đang Hoạt Động" và thông tin phương tiện căn trái
        JPanel statusPanel = new JPanel(new GridBagLayout());
        statusPanel.setBackground(ColorMain.blueHeader);
        statusPanel.setBorder(new EmptyBorder(0, 20, 0, 0)); // Thêm khoảng cách giữa logo và trạng thái
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 5, 0); // Khoảng cách giữa trạng thái và thông tin
        
        JLabel statusLabel = new JLabel("Đang Hoạt Động");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setBorder(new EmptyBorder(0, 5, 0, 0));
        statusPanel.add(statusLabel, gbc);
        
        // Thông tin phương tiện với các nhãn riêng biệt
        JPanel vehicleInfoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        vehicleInfoPanel.setBackground(ColorMain.blueHeader);
        
        JLabel roleLabel = new JLabel("Tài Xế");
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        roleLabel.setForeground(Color.WHITE);
        
        JLabel separatorLabel1 = new JLabel("|");
        separatorLabel1.setFont(new Font("Arial", Font.PLAIN, 12));
        separatorLabel1.setForeground(Color.WHITE);
        
        phuongTienLbl = new JLabel("Honda Wave Alpha");
        phuongTienLbl.setFont(new Font("Arial", Font.PLAIN, 12));
        phuongTienLbl.setForeground(Color.WHITE);
        
        JLabel separatorLabel2 = new JLabel("|");
        separatorLabel2.setFont(new Font("Arial", Font.PLAIN, 12));
        separatorLabel2.setForeground(Color.WHITE);
        
        bienSoXeLbl = new JLabel("STF-12345");
        bienSoXeLbl.setFont(new Font("Arial", Font.PLAIN, 12));
        bienSoXeLbl.setForeground(Color.WHITE);
        
        vehicleInfoPanel.add(roleLabel);
        vehicleInfoPanel.add(separatorLabel1);
        vehicleInfoPanel.add(phuongTienLbl);
        vehicleInfoPanel.add(separatorLabel2);
        vehicleInfoPanel.add(bienSoXeLbl);
        
        gbc.gridy = 1;
        statusPanel.add(vehicleInfoPanel, gbc);
        
        // Phần trung tâm: Thu nhập và thông tin thời gian
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(ColorMain.blueHeader);
        
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        infoPanel.setBackground(ColorMain.blueHeader);
        
        JLabel incomeLabel = new JLabel("Thu nhập hôm nay");
        incomeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        incomeLabel.setForeground(Color.WHITE);
        
        thuNhapNowLbl = new JLabel("350,000 VND");
        thuNhapNowLbl.setFont(new Font("Arial", Font.BOLD, 14));
        thuNhapNowLbl.setForeground(Color.WHITE);
        
        JLabel separator = new JLabel("|");
        separator.setFont(new Font("Arial", Font.PLAIN, 14));
        separator.setForeground(Color.WHITE);
        separator.setBorder(new EmptyBorder(0, 10, 0, 10));
        
        timeLabel = new JLabel("Thời gian");
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        timeLabel.setForeground(Color.WHITE);
        
        // Cấu hình bộ định dạng thời gian
        timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        JLabel timeValueLabel = new JLabel();
        timeValueLabel.setFont(new Font("Arial", Font.BOLD, 14));
        timeValueLabel.setForeground(Color.WHITE);
        
        // Cập nhật thời gian hiển thị mỗi giây
        timeTimer = new Timer(1000, e -> {
            LocalTime now = LocalTime.now();
            timeValueLabel.setText(timeFormatter.format(now));
        });
        timeTimer.start();
        
        infoPanel.add(incomeLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        infoPanel.add(thuNhapNowLbl);
        infoPanel.add(separator);
        infoPanel.add(timeLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        infoPanel.add(timeValueLabel);
        
        centerPanel.add(infoPanel);
        
        // Phần bên phải: Thông tin tài xế - căn phải
        JPanel driverPanel = new JPanel(new GridBagLayout());
        driverPanel.setBackground(ColorMain.blueHeader);
        driverPanel.setBorder(new EmptyBorder(0, 0, 0, 15)); // Thêm padding bên phải
        
        GridBagConstraints driverGbc = new GridBagConstraints();
        driverGbc.gridx = 0;
        driverGbc.gridy = 0;
        driverGbc.anchor = GridBagConstraints.EAST;
        driverGbc.insets = new Insets(0, 0, 5, 15); // Khoảng cách giữa tên và trạng thái
        
        driverNameLabel = new JLabel("Dương Thành Long");
        driverNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        driverNameLabel.setForeground(Color.WHITE);
        driverPanel.add(driverNameLabel, driverGbc);
        
        driverGbc.gridy = 1;
        JLabel onlineLabel = new JLabel("Online");
        onlineLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        onlineLabel.setForeground(Color.WHITE);
        driverPanel.add(onlineLabel, driverGbc);
        
        // Phần trái kết hợp logo và trạng thái
        JPanel leftCombinedPanel = new JPanel(new BorderLayout());
        leftCombinedPanel.setBackground(ColorMain.blueHeader);
        leftCombinedPanel.add(logoPanel, BorderLayout.WEST);
        leftCombinedPanel.add(statusPanel, BorderLayout.CENTER);
        
        // Thêm các thành phần vào header content panel
        headerContentPanel.add(leftCombinedPanel, BorderLayout.WEST);
        headerContentPanel.add(centerPanel, BorderLayout.CENTER);
        headerContentPanel.add(driverPanel, BorderLayout.EAST);
        
        // Thêm nội dung header vào header panel
        headerPanel.removeAll();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(headerContentPanel, BorderLayout.CENTER);
        
        headerPanel.revalidate();
        headerPanel.repaint();
    }

    // Tạo các panel chính
    private void createMainPanels() {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);
        
        // Panel nội dung chính
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(255, 255, 255, 200)); // Màu trắng với độ trong suốt
        
        // Panel header
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(ColorMain.blueHeader); // Màu xanh ngọc với độ trong suốt 
        headerPanel.setPreferredSize(new Dimension(this.getWidth(), 70));
        
        // Panel menu bên trái
        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(ColorMain.blueMenuLeft); // Màu xanh đậm với độ trong suốt
        menuPanel.setPreferredSize(new Dimension(200, this.getHeight()));

        // Thêm các panel vào panel chính
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(menuPanel, BorderLayout.WEST);
    }

    private void createLeftMenu() {
        // Xóa tất cả các thành phần hiện có khỏi menuPanel
        menuPanel.removeAll();
        
        // Đặt layout tuyệt đối để định vị thành phần chính xác
        menuPanel.setLayout(null);
        
        // Tạo các nút menu
        btnNhanChuyen = createMenuButton("Nhận Chuyến Đi");
        btnKetThucChuyen = createMenuButton("Kết Thúc Chuyến Đi");
        btnDanhGia = createMenuButton("Đánh Giá");
        btnTrangThai = createMenuButton("Trạng Thái");
        btnTKNganHang = createMenuButton("Tài Khoản Ngân Hàng");
        btnLichSuChuyen = createMenuButton("Lịch Sử Chuyến Đi");
        btnTroChuyen = createMenuButton("Trò chuyện");
        btnDangXuat = createMenuButton("Đăng Xuất");
        
        // Màu đặc biệt cho nút đăng xuất
        btnDangXuat.setForeground(new Color(255, 100, 100)); // Màu đỏ nhạt
        
        // Đặt vị trí và kích thước cho các nút
        int buttonHeight = 40;
        int buttonWidth = 180;
        int leftMargin = 10;
        
        // Định vị các nút menu chính
        btnNhanChuyen.setBounds(leftMargin, 30, buttonWidth, buttonHeight);
        btnKetThucChuyen.setBounds(leftMargin, 30 + buttonHeight, buttonWidth, buttonHeight);
        btnDanhGia.setBounds(leftMargin, 30 + buttonHeight * 2, buttonWidth, buttonHeight);
        btnTrangThai.setBounds(leftMargin, 30 + buttonHeight * 3, buttonWidth, buttonHeight);
        btnTKNganHang.setBounds(leftMargin, 30 + buttonHeight * 4, buttonWidth, buttonHeight);
        btnLichSuChuyen.setBounds(leftMargin, 30 + buttonHeight * 5, buttonWidth, buttonHeight);
        btnTroChuyen.setBounds(leftMargin, 30 + buttonHeight * 6, buttonWidth, buttonHeight);
        
        // Lấy chiều cao panel trừ chiều cao header để tránh chồng chéo
        int menuPanelHeight = this.getHeight() - 70;
        
        // Định vị nút đăng xuất ở dưới cùng, cách 50px từ cạnh dưới
        btnDangXuat.setBounds(leftMargin, menuPanelHeight - buttonHeight - 50, buttonWidth, buttonHeight);
        
        menuPanel.add(btnNhanChuyen);
        menuPanel.add(btnKetThucChuyen);
        menuPanel.add(btnDanhGia);
        menuPanel.add(btnTrangThai);
        menuPanel.add(btnTKNganHang);
        menuPanel.add(btnLichSuChuyen);
        menuPanel.add(btnTroChuyen);
        menuPanel.add(btnDangXuat);
        
        // Cập nhật vị trí nút đăng xuất khi kích thước cửa sổ thay đổi
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                int updatedMenuPanelHeight = getHeight() - 70;
                btnDangXuat.setBounds(leftMargin, updatedMenuPanelHeight - buttonHeight - 50, buttonWidth, buttonHeight);
            }
        });
    }
    private void createContent() {
        // Xóa và thiết lập lại panel nội dung chính
        contentPanel.removeAll();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(255, 255, 255, 240));
        
        // Panel chứa tất cả nội dung
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        mainContentPanel.setBackground(Color.WHITE);
        mainContentPanel.setBorder(new EmptyBorder(5, 5, 10, 10));
        
        // Panel chào mừng với thông tin tài xế và đánh giá
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BorderLayout());
        welcomePanel.setBackground(Color.WHITE);
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10)); // Đường border cách đều các lề
        JPanel welcomeInnerPanel = new JPanel(new BorderLayout());
        welcomeInnerPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        welcomeInnerPanel.setBackground(Color.WHITE);
        welcomePanel.add(welcomeInnerPanel, BorderLayout.CENTER);
        
        welcomeInnerPanel.setPreferredSize(new Dimension(contentPanel.getWidth(), 150));
        welcomeInnerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));
        
        // Panel bên trái cho hình ảnh hồ sơ
        JPanel profileImagePanel = new JPanel(new BorderLayout());
        profileImagePanel.setBackground(Color.WHITE);
        profileImagePanel.setPreferredSize(new Dimension(150, 150));
        profileImagePanel.setBorder(new EmptyBorder(5, 5, 5, 10));
        
        // Hình ảnh hồ sơ tài xế
        ImageIcon inforDriver = new ImageIcon(getClass().getResource("/me/myproject/IMAGE/Dn.png"));
        Image inforImg = inforDriver.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        inforDriver = new ImageIcon(inforImg);
        JLabel inforJLabel = new JLabel(inforDriver);
        
        // Thêm hình ảnh vào panel
        profileImagePanel.add(inforJLabel, BorderLayout.CENTER);
    
        // Panel trung tâm cho văn bản chào mừng
        JPanel welcomeTextPanel = new JPanel();
        welcomeTextPanel.setLayout(new BoxLayout(welcomeTextPanel, BoxLayout.Y_AXIS));
        welcomeTextPanel.setBackground(Color.WHITE);
        welcomeTextPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // Văn bản chào mừng với tên tài xế
        chaoTaiXeLbl = new JLabel("Chào Dương Thành Long!");
        chaoTaiXeLbl.setFont(new Font("Arial", Font.BOLD, 22));
        chaoTaiXeLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Thông báo sẵn sàng làm việc
        JLabel readyMessage = new JLabel("Sẵn sàng cho ngày làm việc");
        readyMessage.setFont(new Font("Arial", Font.PLAIN, 16));
        readyMessage.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Nút "Bắt Đầu Nhận"
        btnBatDauChuyen = new JButton("Bắt Đầu Nhận");
        btnBatDauChuyen.setFont(new Font("Arial", Font.BOLD, 14));
        btnBatDauChuyen.setBackground(ColorMain.btnHeader);
        btnBatDauChuyen.setForeground(Color.WHITE);
        btnBatDauChuyen.setFocusPainted(false);
        btnBatDauChuyen.setBorderPainted(false);
        btnBatDauChuyen.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnBatDauChuyen.setPreferredSize(new Dimension(150, 40));
        btnBatDauChuyen.setMaximumSize(new Dimension(150, 40));
        btnBatDauChuyen.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        welcomeTextPanel.add(chaoTaiXeLbl);
        welcomeTextPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        welcomeTextPanel.add(readyMessage);
        welcomeTextPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        welcomeTextPanel.add(btnBatDauChuyen);
        
        // Panel bên phải cho đánh giá
        JPanel ratingPanel = new JPanel();
        ratingPanel.setLayout(new BoxLayout(ratingPanel, BoxLayout.Y_AXIS));
        ratingPanel.setBackground(Color.WHITE);
        ratingPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        ratingPanel.setPreferredSize(new Dimension(180, 150));
        
        JLabel ratingTitleLabel = new JLabel("Điểm Đánh Giá");
        ratingTitleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        ratingTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JPanel ratingValuePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ratingValuePanel.setBackground(Color.WHITE);
        
        JLabel ratingValueLabel = new JLabel("4.8");
        ratingValueLabel.setFont(new Font("Arial", Font.BOLD, 32));
        ratingValueLabel.setForeground(new Color(255, 150, 0)); // Màu cam
        
        JLabel starLabel = new JLabel("★");
        starLabel.setFont(new Font("Arial", Font.BOLD, 32));
        starLabel.setForeground(new Color(255, 150, 0)); // Màu cam
        
        ratingValuePanel.add(ratingValueLabel);
        ratingValuePanel.add(starLabel);
        
        ratingPanel.add(Box.createVerticalGlue());
        ratingPanel.add(ratingTitleLabel);
        ratingPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        ratingPanel.add(ratingValuePanel);
        ratingPanel.add(Box.createVerticalGlue());
        
        // Thêm các thành phần vào panel chào mừng
        welcomeInnerPanel.add(profileImagePanel, BorderLayout.WEST);
        welcomeInnerPanel.add(welcomeTextPanel, BorderLayout.CENTER);
        welcomeInnerPanel.add(ratingPanel, BorderLayout.EAST);
        
        // Panel số chuyến đi
        JPanel tripCountPanel = new JPanel();
        tripCountPanel.setLayout(new BorderLayout());
        tripCountPanel.setBackground(Color.WHITE);
        tripCountPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10)); 
        JPanel tripCountInnerPanel = new JPanel(new BorderLayout());
        tripCountInnerPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        tripCountInnerPanel.setBackground(Color.WHITE);
        tripCountPanel.add(tripCountInnerPanel, BorderLayout.CENTER);
        
        tripCountInnerPanel.setPreferredSize(new Dimension(contentPanel.getWidth(), 60)); // Thu nhỏ chiều cao
        tripCountInnerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        
        JPanel tripCountLeftPanel = new JPanel(new BorderLayout());
        tripCountLeftPanel.setBackground(Color.WHITE);
        tripCountLeftPanel.setBorder(new EmptyBorder(5, 10, 5, 10)); 
        
        JLabel tripCountTitleLabel = new JLabel("Số Chuyến Hôm Nay");
        tripCountTitleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        tripCountLeftPanel.add(tripCountTitleLabel, BorderLayout.CENTER);
        
        JPanel tripCountRightPanel = new JPanel(new BorderLayout());
        tripCountRightPanel.setBackground(Color.WHITE);
        tripCountRightPanel.setBorder(new EmptyBorder(5, 10, 5, 20)); 
        
        JLabel tripCountValueLabel = new JLabel("5");
        tripCountValueLabel.setFont(new Font("Arial", Font.BOLD, 28)); // Giảm font size
        tripCountValueLabel.setForeground(new Color(20, 180, 180));
        tripCountValueLabel.setHorizontalAlignment(JLabel.RIGHT);
        tripCountRightPanel.add(tripCountValueLabel, BorderLayout.CENTER);
        
        tripCountInnerPanel.add(tripCountLeftPanel, BorderLayout.WEST);
        tripCountInnerPanel.add(tripCountRightPanel, BorderLayout.EAST);
        
        // Panel bản đồ
        JPanel mapPanel = new JPanel(new BorderLayout());
        mapPanel.setBackground(Color.WHITE);
        mapPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 10)); // Cách đều các lề
        JPanel mapInnerPanel = new JPanel(new BorderLayout());
        mapInnerPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        mapInnerPanel.setBackground(Color.WHITE);
        mapPanel.add(mapInnerPanel, BorderLayout.CENTER);
        
        JLabel mapTitleLabel = new JLabel("Bản Đồ Vùng");
        mapTitleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        mapTitleLabel.setBorder(new EmptyBorder(5, 20, 5, 10));
        
        // Placeholder cho bản đồ
        JPanel mapContentPanel = new JPanel();
        mapContentPanel.setLayout(new BorderLayout()); // Sử dụng BorderLayout để dễ dàng thêm bản đồ
        mapContentPanel.setBackground(new Color(245, 245, 245)); // Màu nền nhạt để phân biệt
        mapContentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mapContentPanel.setPreferredSize(new Dimension(contentPanel.getWidth(), 250));
        
        JLabel mapPlaceholderLabel = new JLabel("Bản đồ sẽ được hiển thị tại đây", JLabel.CENTER);
        mapPlaceholderLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        mapPlaceholderLabel.setForeground(Color.GRAY);
        mapContentPanel.add(mapPlaceholderLabel, BorderLayout.CENTER);
        
        mapInnerPanel.add(mapTitleLabel, BorderLayout.NORTH);
        mapInnerPanel.add(mapContentPanel, BorderLayout.CENTER);
        
        // Thêm tất cả các panel vào panel chính với khoảng cách
        mainContentPanel.add(welcomePanel);
        mainContentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainContentPanel.add(tripCountPanel);
        mainContentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainContentPanel.add(mapPanel);
        
        // Tạo scroll pane cho nội dung chính
        JScrollPane scrollPane = new JScrollPane(mainContentPanel);
        scrollPane.setBorder(null);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.validate();
        contentPanel.repaint();
    }    
    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        
        // Thiết lập giao diện nút
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(25, 37, 60));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorder(new EmptyBorder(8, 15, 8, 10));
        
        // Chỉ thay đổi con trỏ thành hình bàn tay khi di chuột qua, không thay đổi màu chữ
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        
        // Ngăn chặn đổi màu nền khi nhấn
        button.setPressedIcon(button.getIcon());
        button.setRolloverEnabled(false);
        
        // Thêm trình lắng nghe sự kiện cho xử lý khi nhấp chuột
        button.addActionListener(this);
        
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Đặt lại màu tất cả các nút
        resetButtonColors();
        Object source = e.getSource();
        if (source == btnNhanChuyen || source == btnBatDauChuyen) {
            btnNhanChuyen.setForeground(ColorMain.blueHeader);
        } else if (source == btnKetThucChuyen) {
            btnKetThucChuyen.setForeground(ColorMain.blueHeader); 
        } else if (source == btnDanhGia) {
            btnDanhGia.setForeground(ColorMain.blueHeader); 
        } else if (source == btnTrangThai) {
            btnTrangThai.setForeground(ColorMain.blueHeader);
        }else if (source == btnTKNganHang) {
            btnTKNganHang.setForeground(ColorMain.blueHeader); 
        } else if (source == btnLichSuChuyen) {
            btnLichSuChuyen.setForeground(ColorMain.blueHeader); 
        } else if (source == btnTroChuyen) {
            btnTroChuyen.setForeground(ColorMain.blueHeader); 
        }
        else if (source == btnDangXuat) {
            // Xử lý đăng xuất
            this.dispose();
            new DangNhapView();
        }
    }

    // Phương thức trợ giúp để đặt lại màu cho tất cả các nút
    private void resetButtonColors() {
        btnNhanChuyen.setForeground(Color.WHITE);
        btnKetThucChuyen.setForeground(Color.WHITE);
        btnDanhGia.setForeground(Color.WHITE);
        btnTrangThai.setForeground(Color.WHITE);
        btnTKNganHang.setForeground(Color.WHITE);
        btnLichSuChuyen.setForeground(Color.WHITE);
        btnTroChuyen.setForeground(Color.WHITE);
        btnDangXuat.setForeground(new Color(255, 100, 100)); // Giữ màu đặc biệt cho nút đăng xuất
    }
    
    public static void main(String[] args) {
        new TrangChuDriverView();
    }
}
