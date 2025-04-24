package me.myproject.GUI;

import me.myproject.Utilities.DIMENSION.DimensionFrame;
import me.myproject.Utilities.DIMENSION.FrameMain;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.toedter.calendar.JDateChooser;

@SuppressWarnings("unused")
public class QuanLyDoanhThuView extends FrameMain implements ActionListener {
    
    // Các panel chính
    private JPanel panelChinh;
    private JPanel panelTieuDe;
    private JPanel panelNoiDung;
    private JPanel panelLoc;
    private JPanel panelThongKe;
    private JPanel panelBieuDo;
    private JPanel panelChiTiet;
    private JPanel panelNutBam;
    private JButton btnThoat;
    
    // Thành phần cho phần lọc
    private JComboBox<String> cboThoiGian;
    private JLabel lblTu;
    private JDateChooser dateFrom; // Thay JComboBox cboTuNgay bằng JDateChooser
    private JLabel lblDen;
    private JDateChooser dateTo;   // Thay JComboBox cboDenNgay bằng JDateChooser
    private JButton btnApDung;
    private JButton btnLamMoi;
    
    // Thành phần cho phần thống kê
    private JPanel panelDoanhThu;
    private JPanel panelChuyenDi;
    private JPanel panelTrungBinh;
    private JPanel panelPhiDichVu;
    
    // Thành phần cho phần biểu đồ
    private JPanel panelBieuDoDoanhThu;
    private JComboBox<String> cboBieuDo;
    private JPanel panelPhanBoChuyenDi;
    
    // Thành phần cho phần chi tiết
    private JPanel panelTabChiTiet;
    private JPanel tabNgay;
    private JPanel tabTuan;
    private JPanel tabThang;
    private JPanel tabLoai;
    
    // Thành phần cho các nút bấm
    private JButton btnExcel;
    private JButton btnPDF;
    private JButton btnIn;
    private JButton btnGui;
    
    // Định dạng số
    private DecimalFormat dinhDangTien = new DecimalFormat("#,###");
    
    // Các màu sắc chính
    private Color titleCyan = new Color(0, 188, 212); // Màu tiêu đề giống Đánh Giá Cá Nhân
    private Color greenColor = new Color(0, 150, 136);
    private Color blueColor = new Color(33, 150, 243);
    private Color orangeColor = new Color(255, 152, 0);
    private Color purpleColor = new Color(156, 39, 176);
    private Color redColor = new Color(244, 67, 54);
    private Color indigo = new Color(63, 81, 181);
    
    public QuanLyDoanhThuView() {
        super("RideWave - Quản Lý Doanh Thu & Thống Kê");
        khoiTaoThanhPhan();
        thietLapGiaoDien();
        capNhatThoiGianCapNhat();
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    private void khoiTaoThanhPhan() {
        // Khởi tạo panel chính
        panelChinh = new JPanel(new BorderLayout());
        panelChinh.setBackground(Color.WHITE);
        
        // Khởi tạo panel tiêu đề
        panelTieuDe = new JPanel(new BorderLayout());
        panelTieuDe.setBackground(titleCyan);
        panelTieuDe.setPreferredSize(new Dimension(DimensionFrame.widthFrame, 35));
        
        // Khởi tạo panel nội dung
        panelNoiDung = new JPanel();
        panelNoiDung.setLayout(new BoxLayout(panelNoiDung, BoxLayout.Y_AXIS));
        panelNoiDung.setBackground(Color.WHITE);
        panelNoiDung.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // Khởi tạo panel lọc
        panelLoc = new JPanel();
        panelLoc.setLayout(new BoxLayout(panelLoc, BoxLayout.Y_AXIS));
        panelLoc.setBackground(Color.WHITE);
        panelLoc.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        
        // Khởi tạo thành phần lọc
        JLabel lblThoiGian = new JLabel("Thời gian:");
        cboThoiGian = new JComboBox<>(new String[]{"Hôm nay", "Tuần này", "Tháng này", "Tùy chỉnh"});
        cboThoiGian.setPreferredSize(new Dimension(150, 25));
        
        lblTu = new JLabel("Từ:");
        dateFrom = new JDateChooser();
        dateFrom.setPreferredSize(new Dimension(150, 25));
        // Đặt ngày mặc định là 08/04/2025
        try {
            Date defaultDate = new SimpleDateFormat("dd/MM/yyyy").parse("08/04/2025");
            dateFrom.setDate(defaultDate);
        } catch (Exception e) {
            dateFrom.setDate(new Date()); // Nếu lỗi, dùng ngày hiện tại
        }
        
        lblDen = new JLabel("Đến:");
        dateTo = new JDateChooser();
        dateTo.setPreferredSize(new Dimension(150, 25));
        // Đặt ngày mặc định là 08/04/2025
        try {
            Date defaultDate = new SimpleDateFormat("dd/MM/yyyy").parse("08/04/2025");
            dateTo.setDate(defaultDate);
        } catch (Exception e) {
            dateTo.setDate(new Date()); // Nếu lỗi, dùng ngày hiện tại
        }
        
        btnApDung = taoNut("Áp Dụng", titleCyan);
        btnApDung.setPreferredSize(new Dimension(100, 25));

        btnLamMoi = taoNut("Làm Mới", greenColor);
        btnLamMoi.setPreferredSize(new Dimension(100, 25));
        btnThoat = taoNut("Thoát", new Color(211, 47, 47)); 
        btnThoat.setPreferredSize(new Dimension(80, 30));
        
        // Khởi tạo panel thống kê
        panelThongKe = new JPanel(new GridLayout(1, 4, 10, 0));
        panelThongKe.setBackground(Color.WHITE);
        
        panelDoanhThu = taoThongKe("TỔNG DOANH THU", "845,230,000", greenColor);
        panelChuyenDi = taoThongKe("TỔNG CHUYẾN ĐI", "5,287", blueColor);
        panelTrungBinh = taoThongKe("TRUNG BÌNH/CHUYẾN", "159,870đ", orangeColor);
        panelPhiDichVu = taoThongKe("PHÍ DỊCH VỤ", "169,046,000", purpleColor);
        
        // Khởi tạo panel biểu đồ 2 cột
        panelBieuDo = new JPanel(new GridLayout(1, 2, 10, 0));
        panelBieuDo.setBackground(Color.WHITE);
        
        panelBieuDoDoanhThu = taoBieuDo("Biểu đồ doanh thu theo thời gian");
        cboBieuDo = new JComboBox<>(new String[]{"Cột", "Đường", "Tròn"});
        panelPhanBoChuyenDi = taoBieuDo("Phân bố chuyến đi");
        
        // Khởi tạo panel chi tiết
        panelChiTiet = new JPanel(new BorderLayout());
        panelChiTiet.setBackground(Color.WHITE);
        panelChiTiet.setBorder(new EmptyBorder(10, 0, 10, 0));
        
        JLabel lblChiTiet = new JLabel("Chi tiết doanh thu");
        lblChiTiet.setFont(new Font("Arial", Font.BOLD, 12));
        
        // Tạo các tab
        panelTabChiTiet = new JPanel();
        panelTabChiTiet.setLayout(new BoxLayout(panelTabChiTiet, BoxLayout.X_AXIS));
        panelTabChiTiet.setBackground(Color.WHITE);
        
        tabNgay = taoTab("Theo ngày", true);
        tabTuan = taoTab("Theo tuần", false);
        tabThang = taoTab("Theo tháng", false);
        tabLoai = taoTab("Theo loại", false);
        
        // Khởi tạo panel nút bấm
        panelNutBam = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelNutBam.setBackground(Color.WHITE);
        
        btnExcel = taoNut("Excel", greenColor);
        btnPDF = taoNut("PDF", redColor);
        btnIn = taoNut("In", blueColor);
        btnGui = taoNut("Gửi", indigo);
    }
    
    private void thietLapGiaoDien() {
        // Thiết lập panel tiêu đề
        JLabel lblTieuDe = new JLabel("Quản Lý Doanh Thu_Thống Kê");
        lblTieuDe.setForeground(Color.WHITE);
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 16));
        lblTieuDe.setBorder(new EmptyBorder(5, 15, 5, 0));
        
        JLabel lblCapNhat = new JLabel("Cập nhật lần cuối: 08/04/2025 05:55");
        lblCapNhat.setForeground(Color.WHITE);
        lblCapNhat.setFont(new Font("Arial", Font.PLAIN, 11));
        lblCapNhat.setBorder(new EmptyBorder(5, 0, 5, 15));
        lblCapNhat.setHorizontalAlignment(SwingConstants.RIGHT);
        
        panelTieuDe.add(lblTieuDe, BorderLayout.WEST);
        panelTieuDe.add(lblCapNhat, BorderLayout.EAST);
        
        // Thiết lập panel lọc
        JPanel panelLocContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 10));
        panelLocContainer.setBackground(Color.WHITE);
        panelLocContainer.setBorder(new EmptyBorder(0, 0, 0, 0));
        
        JPanel panelFilterContent = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelFilterContent.setBackground(Color.WHITE);
        
        JPanel pnlThoiGian = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        pnlThoiGian.setBackground(Color.WHITE);
        pnlThoiGian.add(new JLabel("Thời gian:"));
        pnlThoiGian.add(cboThoiGian);
        
        JPanel pnlTuDen = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        pnlTuDen.setBackground(Color.WHITE);
        pnlTuDen.add(lblTu);
        pnlTuDen.add(dateFrom); // Thay cboTuNgay bằng dateFrom
        pnlTuDen.add(lblDen);
        pnlTuDen.add(dateTo);   // Thay cboDenNgay bằng dateTo
        
        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        pnlButtons.setBackground(Color.WHITE);
        pnlButtons.add(btnApDung);
        pnlButtons.add(btnLamMoi);
        
        panelFilterContent.add(pnlThoiGian);
        panelFilterContent.add(pnlTuDen);
        panelFilterContent.add(pnlButtons);
        
        panelLocContainer.add(panelFilterContent);
        panelLoc.add(panelLocContainer);
        
        // Thiết lập panel thống kê
        panelThongKe.add(panelDoanhThu);
        panelThongKe.add(panelChuyenDi);
        panelThongKe.add(panelTrungBinh);
        panelThongKe.add(panelPhiDichVu);
        
        // Thiết lập panel biểu đồ
        // Panel biểu đồ doanh thu
        JPanel pnlBieuDoContainer = new JPanel(new BorderLayout());
        pnlBieuDoContainer.setBackground(Color.WHITE);
        
        JPanel pnlBieuDoHeader = new JPanel(new BorderLayout());
        pnlBieuDoHeader.setBackground(Color.WHITE);
        pnlBieuDoHeader.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        JPanel headerLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        headerLeft.setBackground(Color.WHITE);
        JLabel lblDoanhThuTitle = new JLabel("Biểu đồ doanh thu theo thời gian");
        lblDoanhThuTitle.setFont(new Font("Arial", Font.BOLD, 11));
        headerLeft.add(lblDoanhThuTitle);
        
        JPanel headerRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        headerRight.setBackground(Color.WHITE);
        cboBieuDo.setPreferredSize(new Dimension(150, 25));
        headerRight.add(cboBieuDo);
        
        pnlBieuDoHeader.add(headerLeft, BorderLayout.WEST);
        pnlBieuDoHeader.add(headerRight, BorderLayout.EAST);
        
        JPanel pnlBieuDoContent = new JPanel();
        pnlBieuDoContent.setBackground(new Color(245, 245, 250));
        pnlBieuDoContent.setPreferredSize(new Dimension(0, 160));
        
        pnlBieuDoContainer.add(pnlBieuDoHeader, BorderLayout.NORTH);
        pnlBieuDoContainer.add(pnlBieuDoContent, BorderLayout.CENTER);
        pnlBieuDoContainer.setBorder(new LineBorder(Color.LIGHT_GRAY));
        
        // Panel phân bố chuyến đi
        JPanel pnlPhanBoContainer = new JPanel(new BorderLayout());
        pnlPhanBoContainer.setBackground(Color.WHITE);
        
        JPanel pnlPhanBoHeader = new JPanel(new BorderLayout());
        pnlPhanBoHeader.setBackground(Color.WHITE);
        pnlPhanBoHeader.setBorder(new EmptyBorder(5, 5, 5, 5));
        
        JPanel headerLeftPB = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        headerLeftPB.setBackground(Color.WHITE);
        JLabel lblPhanBoTitle = new JLabel("Phân bố chuyến đi");
        lblPhanBoTitle.setFont(new Font("Arial", Font.BOLD, 11));
        headerLeftPB.add(lblPhanBoTitle);
        
        pnlPhanBoHeader.add(headerLeftPB, BorderLayout.WEST);
        
        JPanel pnlPhanBoContent = new JPanel();
        pnlPhanBoContent.setBackground(new Color(245, 245, 250));
        pnlPhanBoContent.setPreferredSize(new Dimension(0, 160));
        
        pnlPhanBoContainer.add(pnlPhanBoHeader, BorderLayout.NORTH);
        pnlPhanBoContainer.add(pnlPhanBoContent, BorderLayout.CENTER);
        pnlPhanBoContainer.setBorder(new LineBorder(Color.LIGHT_GRAY));
        
        // Thiết lập lại panel biểu đồ
        panelBieuDo.removeAll();
        panelBieuDo.add(pnlBieuDoContainer);
        panelBieuDo.add(pnlPhanBoContainer);
        
        // Thiết lập panel tab chi tiết
        panelTabChiTiet.add(tabNgay);
        panelTabChiTiet.add(tabTuan);
        panelTabChiTiet.add(tabThang);
        panelTabChiTiet.add(tabLoai);
        panelTabChiTiet.add(Box.createHorizontalGlue());
        
        JPanel panelChiTietContainer = new JPanel(new BorderLayout());
        panelChiTietContainer.setBackground(Color.WHITE);
        panelChiTietContainer.setBorder(new LineBorder(Color.LIGHT_GRAY));
        
        JLabel lblChiTietTitle = new JLabel("Chi tiết doanh thu");
        lblChiTietTitle.setFont(new Font("Arial", Font.BOLD, 12));
        lblChiTietTitle.setBorder(new EmptyBorder(5, 5, 5, 0));
        
        JPanel tabHeaderPanel = new JPanel(new BorderLayout());
        tabHeaderPanel.setBackground(Color.WHITE);
        tabHeaderPanel.add(lblChiTietTitle, BorderLayout.WEST);
        tabHeaderPanel.add(panelTabChiTiet, BorderLayout.CENTER);
        tabHeaderPanel.setBorder(new EmptyBorder(5, 5, 0, 0));
        
        JPanel detailContent = new JPanel();
        detailContent.setBackground(new Color(245, 245, 250));
        detailContent.setPreferredSize(new Dimension(0, 150));
        
        panelChiTietContainer.add(tabHeaderPanel, BorderLayout.NORTH);
        panelChiTietContainer.add(detailContent, BorderLayout.CENTER);
        
        panelChiTiet.add(panelChiTietContainer, BorderLayout.CENTER);
        
        // Thiết lập panel nút bấm
        panelNutBam.add(btnExcel);
        panelNutBam.add(Box.createHorizontalStrut(5));
        panelNutBam.add(btnPDF);
        panelNutBam.add(Box.createHorizontalStrut(5));
        panelNutBam.add(btnIn);
        panelNutBam.add(Box.createHorizontalStrut(5));
        panelNutBam.add(btnGui);
        panelNutBam.add(Box.createHorizontalStrut(5));
        panelNutBam.add(btnThoat);
        
        // Thêm các panel vào panel nội dung
        panelNoiDung.add(panelLoc);
        panelNoiDung.add(Box.createVerticalStrut(10));
        panelNoiDung.add(panelThongKe);
        panelNoiDung.add(Box.createVerticalStrut(10));
        panelNoiDung.add(panelBieuDo);
        panelNoiDung.add(Box.createVerticalStrut(10));
        panelNoiDung.add(panelChiTiet);
        panelNoiDung.add(Box.createVerticalStrut(10));
        panelNoiDung.add(panelNutBam);
        
        // Thêm vào panel chính
        panelChinh.add(panelTieuDe, BorderLayout.NORTH);
        panelChinh.add(panelNoiDung, BorderLayout.CENTER);
        
        // Thêm vào frame
        this.getContentPane().add(panelChinh, BorderLayout.CENTER);
        
        // Gỡ bỏ các panel mặc định từ FrameMain
        this.getContentPane().remove(pnL);
        this.getContentPane().remove(pnT);
        this.getContentPane().remove(pnC);
        
        this.revalidate();
        this.repaint();
    }
    
    private JPanel taoThongKe(String tieuDe, String giaTri, Color mau) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
        
        JPanel panelHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panelHeader.setBackground(Color.WHITE);
        
        JLabel lblTieuDe = new JLabel(tieuDe);
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 11));
        panelHeader.add(lblTieuDe);
        
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(new EmptyBorder(0, 0, 15, 0));
        
        JLabel lblGiaTri = new JLabel(giaTri);
        lblGiaTri.setHorizontalAlignment(SwingConstants.CENTER);
        lblGiaTri.setFont(new Font("Arial", Font.BOLD, 18));
        lblGiaTri.setForeground(mau);
        
        centerPanel.add(lblGiaTri, BorderLayout.CENTER);
        
        panel.add(panelHeader, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel taoBieuDo(String tieuDe) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
        
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        headerPanel.setBackground(Color.WHITE);
        
        JLabel lblTieuDe = new JLabel(tieuDe);
        lblTieuDe.setFont(new Font("Arial", Font.BOLD, 11));
        headerPanel.add(lblTieuDe);
        
        // Panel trống để làm nơi giữ chỗ cho biểu đồ
        JPanel bieuDoTam = new JPanel();
        bieuDoTam.setBackground(new Color(245, 245, 250));
        bieuDoTam.setPreferredSize(new Dimension(0, 160));
        
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(bieuDoTam, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel taoTab(String tenTab, boolean active) {
        JPanel tab = new JPanel();
        tab.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 8));
        
        if (active) {
            tab.setBackground(titleCyan);
            tab.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.LIGHT_GRAY));
        } else {
            tab.setBackground(Color.WHITE);
            tab.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        }
        
        JLabel lblTab = new JLabel(tenTab);
        lblTab.setFont(new Font("Arial", Font.PLAIN, 11));
        lblTab.setForeground(active ? Color.WHITE : Color.BLACK);
        
        tab.add(lblTab);
        
        return tab;
    }
    
    private JButton taoNut(String tenNut, Color mauNen) {
        JButton nut = new JButton(tenNut) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(mauNen);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
                super.paintComponent(g);
            }
        };
        nut.setForeground(Color.WHITE);
        nut.setFocusPainted(false);
        nut.setBorderPainted(false);
        nut.setContentAreaFilled(false);
        nut.setBorder(new EmptyBorder(5, 10, 5, 10));
        nut.setFont(new Font("Arial", Font.BOLD, 11));
        nut.setPreferredSize(new Dimension(80, 30));
        nut.addActionListener(this);
        
        return nut;
    }
    
    private void capNhatThoiGianCapNhat() {
        JLabel lblCapNhat = (JLabel) panelTieuDe.getComponent(1);
        lblCapNhat.setText("Cập nhật lần cuối: " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
    }
    
    // Cập nhật dữ liệu thống kê (giả lập)
    private void capNhatDuLieuThongKe(Date tuNgay, Date denNgay) {
        // Giả lập dữ liệu dựa trên khoảng thời gian
        long doanhThu = 845230000 + (long)(Math.random() * 100000000); // Ngẫu nhiên ±100 triệu
        int chuyenDi = 5287 + (int)(Math.random() * 1000); // Ngẫu nhiên ±1000 chuyến
        double trungBinh = doanhThu / (double)chuyenDi;
        long phiDichVu = (long)(doanhThu * 0.2); // 20% doanh thu
        
        panelDoanhThu = taoThongKe("TỔNG DOANH THU", dinhDangTien.format(doanhThu), greenColor);
        panelChuyenDi = taoThongKe("TỔNG CHUYẾN ĐI", String.valueOf(chuyenDi), blueColor);
        panelTrungBinh = taoThongKe("TRUNG BÌNH/CHUYẾN", dinhDangTien.format(trungBinh) + "đ", orangeColor);
        panelPhiDichVu = taoThongKe("PHÍ DỊCH VỤ", dinhDangTien.format(phiDichVu), purpleColor);
        
        // Cập nhật panel thống kê
        panelThongKe.removeAll();
        panelThongKe.add(panelDoanhThu);
        panelThongKe.add(panelChuyenDi);
        panelThongKe.add(panelTrungBinh);
        panelThongKe.add(panelPhiDichVu);
        
        panelThongKe.revalidate();
        panelThongKe.repaint();
        
        // Cập nhật thời gian
        capNhatThoiGianCapNhat();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnApDung) {
            // Xử lý sự kiện nút Áp dụng
            String thoiGian = (String) cboThoiGian.getSelectedItem();
            Date tuNgay = dateFrom.getDate(); // Lấy ngày từ JDateChooser
            Date denNgay = dateTo.getDate();  // Lấy ngày từ JDateChooser
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            if ("Tùy chỉnh".equals(thoiGian)) {
                if (tuNgay != null && denNgay != null) {
                    capNhatDuLieuThongKe(tuNgay, denNgay);
                    JOptionPane.showMessageDialog(this, "Đã áp dụng lọc từ " + dateFormat.format(tuNgay) + " đến " + dateFormat.format(denNgay), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Giả lập khoảng thời gian mặc định nếu không chọn "Tùy chỉnh"
                try {
                    Date defaultStart = dateFormat.parse("01/04/2025");
                    Date defaultEnd = dateFormat.parse("08/04/2025");
                    capNhatDuLieuThongKe(defaultStart, defaultEnd);
                    JOptionPane.showMessageDialog(this, "Đã áp dụng lọc cho " + thoiGian, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Lỗi khi áp dụng lọc mặc định!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (source == btnLamMoi) {
            // Xử lý sự kiện nút Làm mới
            cboThoiGian.setSelectedIndex(0);
            try {
                Date defaultDate = new SimpleDateFormat("dd/MM/yyyy").parse("08/04/2025");
                dateFrom.setDate(defaultDate);
                dateTo.setDate(defaultDate);
            } catch (Exception ex) {
                dateFrom.setDate(new Date());
                dateTo.setDate(new Date());
            }
            try {
                Date defaultStart = new SimpleDateFormat("dd/MM/yyyy").parse("01/04/2025");
                Date defaultEnd = new SimpleDateFormat("dd/MM/yyyy").parse("08/04/2025");
                capNhatDuLieuThongKe(defaultStart, defaultEnd);
            } catch (Exception ex) {
                capNhatDuLieuThongKe(new Date(), new Date());
            }
            JOptionPane.showMessageDialog(this, "Đã làm mới dữ liệu!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else if (source == btnExcel) {
            JOptionPane.showMessageDialog(this, "Chức năng xuất Excel chưa được triển khai!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else if (source == btnPDF) {
            JOptionPane.showMessageDialog(this, "Chức năng xuất PDF chưa được triển khai!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else if (source == btnIn) {
            JOptionPane.showMessageDialog(this, "Chức năng in chưa được triển khai!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else if (source == btnGui) {
            JOptionPane.showMessageDialog(this, "Chức năng gửi email chưa được triển khai!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else if (source == btnThoat) {
            this.dispose();
        }
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            new QuanLyDoanhThuView();
        });
    }
}
