package me.myproject.GUI;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import me.myproject.Utilities.DIMENSION.FrameMain;

public class HuyChuyenDiView extends FrameMain {
    private JTable currentTripsTable;
    private JTextField reasonField;
    private JButton cancelButton;
    private JPanel mainPanel;
    private JPanel centerPanel;
    private JScrollPane scrollPane;
    
    // Định nghĩa các màu chính
    private final Color PRIMARY_COLOR = new Color(0, 163, 136);
    private final Color ACCENT_COLOR = new Color(242, 88, 34);
    private final Color BACKGROUND_COLOR = new Color(245, 245, 245);
    private final Color INFO_PANEL_COLOR = new Color(240, 240, 240);
    
    public HuyChuyenDiView() {
        super("Hủy chuyến đi");
        initComponents();
        this.setVisible(true);
    }
    
    private void initComponents() {
        // Lấy kích thước của FrameMain
        Dimension frameDimension = this.getSize();
        int frameWidth = frameDimension.width;
        int frameHeight = frameDimension.height;
        
        // Main panel với background color nhẹ
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        mainPanel.setBackground(BACKGROUND_COLOR);
        
        // Header - Căng chỉnh tiêu đề ở giữa
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel headerLabel = new JLabel("HỦY CHUYẾN ĐI", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 26));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setOpaque(true);
        headerLabel.setBackground(PRIMARY_COLOR);
        headerLabel.setPreferredSize(new Dimension(frameWidth, 60));
        headerLabel.setBorder(new EmptyBorder(15, 10, 15, 10));
        headerPanel.add(headerLabel, BorderLayout.CENTER);
        
        // Panel chính chứa nội dung với padding
        JPanel contentPanel = new JPanel(new BorderLayout(0, 20));
        contentPanel.setBackground(BACKGROUND_COLOR);
        contentPanel.setBorder(new EmptyBorder(25, 30, 25, 30));
        
        // Current trips panel
        JPanel tripsPanel = new JPanel(new BorderLayout(0, 10));
        tripsPanel.setBackground(BACKGROUND_COLOR);
        
        JPanel tripsHeaderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        tripsHeaderPanel.setBackground(BACKGROUND_COLOR);
        
        JLabel currentTripsLabel = new JLabel("Chuyến đi hiện tại:");
        currentTripsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        currentTripsLabel.setForeground(new Color(50, 50, 50));
        tripsHeaderPanel.add(currentTripsLabel);
        
        // Table với styling cải thiện
        String[] columnNames = {"Mã chuyến", "Tài xế", "Điểm đón", "Điểm đến", "Loại xe"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        
        // Thêm dữ liệu mẫu để demo
        model.addRow(new Object[]{"CD001", "Nguyễn Văn A", "Trường Đại học XYZ", "Trung tâm thương mại ABC", "4 chỗ"});
        
        currentTripsTable = new JTable(model);
        currentTripsTable.setRowHeight(30);
        currentTripsTable.setFont(new Font("Arial", Font.PLAIN, 13));
        currentTripsTable.setShowGrid(true);
        currentTripsTable.setGridColor(new Color(220, 220, 220));
        currentTripsTable.setSelectionBackground(new Color(226, 242, 238));
        currentTripsTable.setSelectionForeground(Color.BLACK);
        
        JTableHeader header = currentTripsTable.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 13));
        header.setBackground(new Color(220, 240, 235));
        header.setForeground(new Color(50, 50, 50));
        header.setPreferredSize(new Dimension(100, 35));
        
        scrollPane = new JScrollPane(currentTripsTable);
        scrollPane.setBorder(new LineBorder(new Color(200, 200, 200), 1));
        
        tripsPanel.add(tripsHeaderPanel, BorderLayout.NORTH);
        tripsPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Trip info panel với style cải thiện
        JPanel infoPanel = new JPanel(new BorderLayout(0, 10));
        infoPanel.setBackground(BACKGROUND_COLOR);
        
        JPanel infoHeaderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        infoHeaderPanel.setBackground(BACKGROUND_COLOR);
        
        JLabel tripInfoLabel = new JLabel("Thông tin chuyến đi:");
        tripInfoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        tripInfoLabel.setForeground(new Color(50, 50, 50));
        infoHeaderPanel.add(tripInfoLabel);
        
        JPanel tripDetailsPanel = new JPanel();
        tripDetailsPanel.setLayout(new BoxLayout(tripDetailsPanel, BoxLayout.Y_AXIS));
        tripDetailsPanel.setBackground(INFO_PANEL_COLOR);
        tripDetailsPanel.setBorder(new CompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1),
            new EmptyBorder(20, 20, 20, 20)
        ));
        
        // Thêm phần thông tin dưới dạng dễ đọc hơn
        JPanel routePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        routePanel.setBackground(INFO_PANEL_COLOR);
        JLabel routeIcon = new JLabel("↔️");
        JLabel routeLabel = new JLabel(" Trường Đại học XYZ — Trung tâm thương mại ABC");
        routeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        routePanel.add(routeIcon);
        routePanel.add(routeLabel);
        
        JPanel driverPanel = createInfoRow(" Thông tin tài xế:", INFO_PANEL_COLOR, true);
        JPanel namePanel = createInfoRow("     Tên tài xế: Nguyễn Văn A", INFO_PANEL_COLOR, false);
        JPanel phonePanel = createInfoRow("     SĐT: 0987654321", INFO_PANEL_COLOR, false);
        JPanel licensePlatePanel = createInfoRow("     Biển số: 51F-12345", INFO_PANEL_COLOR, false);
        JPanel carTypePanel = createInfoRow("     Loại xe: 4 chỗ", INFO_PANEL_COLOR, false);
        
        tripDetailsPanel.add(routePanel);
        tripDetailsPanel.add(Box.createVerticalStrut(15));
        tripDetailsPanel.add(driverPanel);
        tripDetailsPanel.add(Box.createVerticalStrut(5));
        tripDetailsPanel.add(namePanel);
        tripDetailsPanel.add(Box.createVerticalStrut(3));
        tripDetailsPanel.add(phonePanel);
        tripDetailsPanel.add(Box.createVerticalStrut(3));
        tripDetailsPanel.add(licensePlatePanel);
        tripDetailsPanel.add(Box.createVerticalStrut(3));
        tripDetailsPanel.add(carTypePanel);
        
        infoPanel.add(infoHeaderPanel, BorderLayout.NORTH);
        infoPanel.add(tripDetailsPanel, BorderLayout.CENTER);
        
        // Reason panel được làm đẹp hơn
        JPanel reasonPanel = new JPanel(new BorderLayout(0, 10));
        reasonPanel.setBackground(BACKGROUND_COLOR);
        
        JPanel reasonHeaderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        reasonHeaderPanel.setBackground(BACKGROUND_COLOR);
        
        JLabel reasonLabel = new JLabel("Lý do hủy:");
        reasonLabel.setFont(new Font("Arial", Font.BOLD, 16));
        reasonLabel.setForeground(new Color(50, 50, 50));
        reasonHeaderPanel.add(reasonLabel);
        
        reasonField = new JTextField();
        reasonField.setFont(new Font("Arial", Font.PLAIN, 14));
        reasonField.setBorder(new CompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1),
            new EmptyBorder(8, 10, 8, 10)
        ));
        
        reasonPanel.add(reasonHeaderPanel, BorderLayout.NORTH);
        reasonPanel.add(reasonField, BorderLayout.CENTER);
        
        // Bọc các phần chính vào center panel với spacing phù hợp
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(BACKGROUND_COLOR);
        
        centerPanel.add(tripsPanel);
        centerPanel.add(Box.createVerticalStrut(20)); // Khoảng cách giữa các section
        centerPanel.add(infoPanel);
        centerPanel.add(Box.createVerticalStrut(20)); // Khoảng cách giữa các section
        centerPanel.add(reasonPanel);
        
        // Button panel với styling cải thiện
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        buttonPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        
        cancelButton = new JButton("HỦY CHUYẾN ĐI");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setBackground(ACCENT_COLOR);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBorder(new EmptyBorder(10, 25, 10, 25));
        cancelButton.setFocusPainted(false);
        
        // Tạo hover effect đẹp hơn
        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancelButton.setBackground(new Color(252, 108, 54));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancelButton.setBackground(ACCENT_COLOR);
            }
        });
        
        // Thêm action listener
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(HuyChuyenDiView.this, 
                    "Đã hủy chuyến đi thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        buttonPanel.add(cancelButton);
        
        // Thêm các phần vào content panel
        contentPanel.add(centerPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Thêm các phần chính vào main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        // Thêm ComponentListener để cập nhật kích thước khi frame thay đổi kích thước
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateComponentSizes();
            }
        });
        
        // Đặt mainPanel trực tiếp làm content pane của frame để phủ toàn bộ frame
        this.setContentPane(mainPanel);
    }
    
    // Helper method để tạo các dòng thông tin với định dạng nhất quán
    private JPanel createInfoRow(String text, Color bgColor, boolean isBold) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panel.setBackground(bgColor);
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", isBold ? Font.BOLD : Font.PLAIN, 14));
        panel.add(label);
        return panel;
    }
    
    // Phương thức để cập nhật kích thước các thành phần khi frame thay đổi kích thước
    private void updateComponentSizes() {
        Dimension size = getSize();
        int frameWidth = size.width;
        int frameHeight = size.height;
        
        // Cập nhật kích thước theo tỷ lệ để duy trì tính thẩm mỹ
        int tableHeight = Math.max(150, (int)(frameHeight * 0.22));
        scrollPane.setPreferredSize(new Dimension(frameWidth - 80, tableHeight));
        
        // Căng chỉnh lại layout
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                HuyChuyenDiView view = new HuyChuyenDiView();
                // Đặt kích thước khởi tạo phù hợp
                view.setLocationRelativeTo(null); // Căn giữa màn hình
            }
        });
    }
}