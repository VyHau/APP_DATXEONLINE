package me.myproject.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.toedter.calendar.JDateChooser;

import me.myproject.BUSINESSLOGIC.QuanLyChuyenDiBSL;
import me.myproject.Utilities.DIMENSION.DimensionFrame;
import me.myproject.Utilities.DIMENSION.FrameMain;

public class QuanLyDoanhThuView extends FrameMain implements ActionListener {
    // Các panel chính
    private JPanel panelChinh;
    private JPanel panelTieuDe;
    private JPanel panelNoiDung;
    private JPanel panelLoc;
    private JPanel panelThongKe;
    private JPanel panelBieuDo;
    private JPanel panelChiTiet;
    private JPanel panelNutBam, panelBieuDoDoanhThu;

    // Thành phần tiêu đề
    private JLabel lblCapNhat;

    // Thành phần lọc
    private JComboBox<String> cboThoiGian;
    private JLabel lblTu;
    private JDateChooser dateFrom;
    private JLabel lblDen;
    private JDateChooser dateTo;
    private JButton btnApDung;
    private JButton btnLamMoi;

    // Thành phần thống kê
    private JPanel panelDoanhThu;
    private JPanel panelChuyenDi;
    private JPanel panelTrungBinh;
    private JPanel panelPhiDichVu;

    // Thành phần biểu đồ
    private JPanel pnlBieuDoContent; // Panel cho biểu đồ doanh thu
    private JComboBox<String> cboBieuDo;
    private JPanel pnlPhanBoContent; // Panel cho phân bố chuyến đi

    // Thành phần chi tiết
    private JPanel panelTabChiTiet;
    private JPanel tabNgay;
    private JPanel tabTuan;
    private JPanel tabThang;
    private JPanel tabNam; // Thay "Theo loại" bằng "Theo năm"
    private JTable tableChiTiet; // Bảng hiển thị chi tiết
    private JScrollPane scrollPaneChiTiet; // Cuộn bảng

    // Thành phần nút bấm
    private JButton btnExcel;
    private JButton btnPDF;
    private JButton btnIn;
    private JButton btnGui;
    private JButton btnThoat;

    // Logic nghiệp vụ
    private QuanLyChuyenDiBSL business;

    // Định dạng số và màu sắc
    private DecimalFormat dinhDangTien = new DecimalFormat("###,###,### VNĐ");
    private Color titleCyan = new Color(0, 188, 212);
    private Color greenColor = new Color(0, 150, 136);
    private Color blueColor = new Color(33, 150, 243);
    private Color orangeColor = new Color(255, 152, 0);
    private Color purpleColor = new Color(156, 39, 176);
    private Color redColor = new Color(244, 67, 54);
    private Color indigo = new Color(63, 81, 181);

    public QuanLyDoanhThuView() throws Exception {
        super("RideWave - Quản Lý Doanh Thu & Thống Kê");
        business = new QuanLyChuyenDiBSL();
        business.fetchChuyenDi();
        khoiTaoThanhPhan();
        thietLapGiaoDien();
        capNhatThoiGianCapNhat();
        if (business.calculateTotalTrips() == 0) {
            JOptionPane.showMessageDialog(this, "Không có dữ liệu chuyến đi để hiển thị biểu đồ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }

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
        cboThoiGian = new JComboBox<>(new String[]{"Hôm nay", "Tuần này", "Tháng này", "Tùy chỉnh"});
        cboThoiGian.setPreferredSize(new Dimension(150, 25));
        lblTu = new JLabel("Từ:");
        dateFrom = new JDateChooser();
        dateFrom.setPreferredSize(new Dimension(150, 25));
        try {
            Date defaultDate = new SimpleDateFormat("dd/MM/yyyy").parse("08/04/2025");
            dateFrom.setDate(defaultDate);
        } catch (Exception e) {
            dateFrom.setDate(new Date());
        }
        lblDen = new JLabel("Đến:");
        dateTo = new JDateChooser();
        dateTo.setPreferredSize(new Dimension(150, 25));
        try {
            Date defaultDate = new SimpleDateFormat("dd/MM/yyyy").parse("08/04/2025");
            dateTo.setDate(defaultDate);
        } catch (Exception e) {
            dateTo.setDate(new Date());
        }
        btnApDung = taoNut("Áp Dụng", titleCyan);
        btnApDung.setPreferredSize(new Dimension(150, 25));
        btnApDung.setFont(new Font("Arial", Font.BOLD, 14));
        btnApDung.setBackground(greenColor);
        btnApDung.setForeground(Color.WHITE);
        btnApDung.setBorder(new EmptyBorder(10, 25, 10, 25));
        btnApDung.setFocusPainted(false);
        btnApDung.setOpaque(true);
        btnApDung.setBorderPainted(false);
        btnLamMoi = taoNut("Làm Mới", greenColor);
        btnLamMoi.setPreferredSize(new Dimension(150, 25));
        btnLamMoi.setFont(new Font("Arial", Font.BOLD, 14));
        btnLamMoi.setBackground(Color.RED);
        btnLamMoi.setForeground(Color.WHITE);
        btnLamMoi.setBorder(new EmptyBorder(10, 25, 10, 25));
        btnLamMoi.setFocusPainted(false);
        btnLamMoi.setOpaque(true);
        btnLamMoi.setBorderPainted(false);

        btnThoat = taoNut("Thoát", redColor);
        btnThoat.setPreferredSize(new Dimension(100, 25));
        btnThoat.setFont(new Font("Arial", Font.BOLD, 14));
        btnThoat.setBackground(Color.RED);
        btnThoat.setForeground(Color.WHITE);
        btnThoat.setBorder(new EmptyBorder(10, 25, 10, 25));
        btnThoat.setFocusPainted(false);
        btnThoat.setOpaque(true);
        btnThoat.setBorderPainted(false);

        // Khởi tạo panel thống kê
        panelThongKe = new JPanel(new GridLayout(1, 4, 10, 0));
        panelThongKe.setBackground(Color.WHITE);
        panelDoanhThu = taoThongKe("TỔNG DOANH THU", dinhDangTien.format(business.calculateTotalRevenue()), greenColor);
        panelChuyenDi = taoThongKe("TỔNG CHUYẾN ĐI", String.valueOf(business.calculateTotalTrips()), blueColor);
        double avgPerTrip = business.calculateTotalTrips() > 0 ? business.calculateTotalRevenue() / (double)business.calculateTotalTrips() : 0;
        panelTrungBinh = taoThongKe("TRUNG BÌNH/CHUYẾN", dinhDangTien.format(avgPerTrip), orangeColor);
        panelPhiDichVu = taoThongKe("PHÍ DỊCH VỤ", dinhDangTien.format(business.calculateTotalRevenue() * 0.2), purpleColor);

        // Khởi tạo panel biểu đồ
        panelBieuDo = new JPanel(new BorderLayout());
        panelBieuDo.setBackground(Color.WHITE);
        panelBieuDoDoanhThu = taoBieuDo("Biểu đồ doanh thu theo thời gian");
        cboBieuDo = new JComboBox<>(new String[]{"Cột", "Đường", "Tròn"});

        // Khởi tạo panel chi tiết
        panelChiTiet = new JPanel(new BorderLayout());
        panelChiTiet.setBackground(Color.WHITE);
        panelChiTiet.setBorder(new EmptyBorder(10, 0, 10, 0));

        // Khởi tạo tab chi tiết (thay "Theo loại" bằng "Theo năm")
        panelTabChiTiet = new JPanel();
        panelTabChiTiet.setLayout(new BoxLayout(panelTabChiTiet, BoxLayout.X_AXIS));
        panelTabChiTiet.setBackground(Color.WHITE);
        tabNgay = taoTab("Theo ngày", true);
        tabTuan = taoTab("Theo tuần", false);
        tabThang = taoTab("Theo tháng", false);
        tabNam = taoTab("Theo năm", false); // Thêm tab "Theo năm"

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

        lblCapNhat = new JLabel("Cập nhật lần cuối: " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
        lblCapNhat.setForeground(Color.WHITE);
        lblCapNhat.setFont(new Font("Arial", Font.PLAIN, 11));
        lblCapNhat.setBorder(new EmptyBorder(5, 0, 5, 15));
        lblCapNhat.setHorizontalAlignment(SwingConstants.RIGHT);

        panelTieuDe.add(lblTieuDe, BorderLayout.WEST);
        panelTieuDe.add(lblCapNhat, BorderLayout.EAST);

        // Thiết lập panel lọc
        JPanel panelLocContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 10));
        panelLocContainer.setBackground(Color.WHITE);

        JPanel panelFilterContent = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelFilterContent.setBackground(Color.WHITE);

        JPanel pnlThoiGian = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        pnlThoiGian.setBackground(Color.WHITE);
        pnlThoiGian.add(new JLabel("Thời gian:"));
        pnlThoiGian.add(cboThoiGian);

        JPanel pnlTuDen = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        pnlTuDen.setBackground(Color.WHITE);
        pnlTuDen.add(lblTu);
        pnlTuDen.add(dateFrom);
        pnlTuDen.add(lblDen);
        pnlTuDen.add(dateTo);

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

        // Thiết lập panel biểu đồ (chỉ giữ lại biểu đồ doanh thu)
        panelBieuDo = new JPanel(new BorderLayout());
        panelBieuDo.setBackground(Color.WHITE);

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

        pnlBieuDoContent = new JPanel();
        pnlBieuDoContent.setBackground(new Color(245, 245, 250));
        pnlBieuDoContent.setLayout(new BorderLayout());

        pnlBieuDoContainer.add(pnlBieuDoHeader, BorderLayout.NORTH);
        pnlBieuDoContainer.add(pnlBieuDoContent, BorderLayout.CENTER);
        pnlBieuDoContainer.setBorder(new LineBorder(Color.LIGHT_GRAY));

        panelBieuDo.add(pnlBieuDoContainer, BorderLayout.CENTER);

        // Thiết lập panel chi tiết
        panelTabChiTiet.add(tabNgay);
        panelTabChiTiet.add(tabTuan);
        panelTabChiTiet.add(tabThang);
        panelTabChiTiet.add(tabNam); // Thay "tabLoai" bằng "tabNam"
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

        // Khởi tạo JTable cho chi tiết doanh thu
        String[] columnNames = {"Thời gian", "Số chuyến đi", "Doanh thu (VNĐ)"};
        Object[][] data = new Object[0][3]; // Dữ liệu mặc định rỗng
        tableChiTiet = new JTable(data, columnNames);
        tableChiTiet.setFillsViewportHeight(true);
        tableChiTiet.setBackground(new Color(245, 245, 250));
        tableChiTiet.setFont(new Font("Arial", Font.PLAIN, 12));
        tableChiTiet.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tableChiTiet.getTableHeader().setBackground(new Color(230, 230, 230));
        tableChiTiet.setRowHeight(25);

        // Đặt kích thước cột
        tableChiTiet.getColumnModel().getColumn(0).setPreferredWidth(150); // Thời gian
        tableChiTiet.getColumnModel().getColumn(1).setPreferredWidth(100); // Số chuyến đi
        tableChiTiet.getColumnModel().getColumn(2).setPreferredWidth(150); // Doanh thu

        scrollPaneChiTiet = new JScrollPane(tableChiTiet);
        scrollPaneChiTiet.setBackground(new Color(245, 245, 250));
        scrollPaneChiTiet.setPreferredSize(new Dimension(0, 150));

        panelChiTietContainer.add(tabHeaderPanel, BorderLayout.NORTH);
        panelChiTietContainer.add(scrollPaneChiTiet, BorderLayout.CENTER);

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

        tabNgay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setActiveTab(tabNgay);
            }
        });

        tabTuan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setActiveTab(tabTuan);
            }
        });

        tabThang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setActiveTab(tabThang);
            }
        });

        tabNam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setActiveTab(tabNam);
            }
        });

        this.revalidate();
        this.repaint();
        updateRevenueChart();
        updateDetailTable(); // Cập nhật bảng chi tiết lần đầu
        cboBieuDo.addActionListener(e -> updateRevenueChart());
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

        JPanel bieuDoTam = new JPanel();
        bieuDoTam.setBackground(new Color(245, 245, 250));

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
            protected void paintComponent(Graphics2D g) {
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g.setColor(mauNen);
                g.fillRect(0, 0, getWidth(), getHeight());
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
        lblCapNhat.setText("Cập nhật lần cuối: " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
    }

    private void capNhatDuLieuThongKe(Date tuNgay, Date denNgay) {
        Timestamp startTimestamp = new Timestamp(tuNgay.getTime());
        Timestamp endTimestamp = new Timestamp(denNgay.getTime());
        double doanhThu = business.calculateTotalRevenue();
        int chuyenDi = business.calculateTotalTrips();
        double trungBinh = chuyenDi > 0 ? (double)doanhThu / chuyenDi : 0;
        long phiDichVu = (long)(doanhThu * 0.2);

        panelDoanhThu = taoThongKe("TỔNG DOANH THU", dinhDangTien.format(doanhThu), greenColor);
        panelChuyenDi = taoThongKe("TỔNG CHUYẾN ĐI", String.valueOf(chuyenDi), blueColor);
        panelTrungBinh = taoThongKe("TRUNG BÌNH/CHUYẾN", dinhDangTien.format(trungBinh), orangeColor);
        panelPhiDichVu = taoThongKe("PHÍ DỊCH VỤ", dinhDangTien.format(phiDichVu), purpleColor);

        panelThongKe.removeAll();
        panelThongKe.add(panelDoanhThu);
        panelThongKe.add(panelChuyenDi);
        panelThongKe.add(panelTrungBinh);
        panelThongKe.add(panelPhiDichVu);

        panelThongKe.revalidate();
        panelThongKe.repaint();

        capNhatThoiGianCapNhat();
        updateRevenueChart();
        updateDetailTable(); // Cập nhật bảng chi tiết
    }

    private void updateRevenueChart() {
        Date startDate = dateFrom.getDate();
        Date endDate = dateTo.getDate();

        if (startDate == null || endDate == null || startDate.after(endDate)) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khoảng thời gian hợp lệ!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Timestamp startTimestamp = new Timestamp(startDate.getTime());
        Timestamp endTimestamp = new Timestamp(endDate.getTime());

        String activeTab = getActiveTab();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        if ("Theo ngày".equals(activeTab)) {
            Map<java.util.Date, Double> revenueData = business.getRevenueByDay(startTimestamp, endTimestamp);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            for (Map.Entry<java.util.Date, Double> entry : revenueData.entrySet()) {
                String dateStr = dateFormat.format(entry.getKey());
                dataset.addValue(entry.getValue(), "Doanh Thu", dateStr);
            }
        } else if ("Theo tuần".equals(activeTab)) {
            Map<java.util.Date, Double> revenueData = business.getRevenueByDay(startTimestamp, endTimestamp);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            for (Map.Entry<java.util.Date, Double> entry : revenueData.entrySet()) {
                String dateStr = dateFormat.format(entry.getKey());
                dataset.addValue(entry.getValue(), "Doanh Thu", dateStr);
            }
        } else if ("Theo tháng".equals(activeTab)) {
            Map<String, Double> revenueData = business.getRevenueByMonth(startTimestamp, endTimestamp);
            for (Map.Entry<String, Double> entry : revenueData.entrySet()) {
                dataset.addValue(entry.getValue(), "Doanh Thu", entry.getKey());
            }
        } else if ("Theo năm".equals(activeTab)) {
            Map<String, Double> revenueData = business.getRevenueByYear(startTimestamp, endTimestamp); // Giả định có phương thức này
            for (Map.Entry<String, Double> entry : revenueData.entrySet()) {
                dataset.addValue(entry.getValue(), "Doanh Thu", entry.getKey());
            }
        }

        String chartType = (String) cboBieuDo.getSelectedItem();
        JFreeChart chart = createChart(dataset, chartType, "Biểu đồ doanh thu theo thời gian",
                activeTab.equals("Theo ngày") ? "Ngày" : activeTab.equals("Theo tuần") ? "Ngày" : activeTab.equals("Theo tháng") ? "Tháng" : "Năm",
                "Doanh Thu (VNĐ)");
        if (chart == null) {
            JOptionPane.showMessageDialog(this, "Không thể tạo biểu đồ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(new Color(245, 245, 250));
        chartPanel.setPreferredSize(new Dimension(pnlBieuDoContent.getWidth(), pnlBieuDoContent.getHeight()));

        pnlBieuDoContent.removeAll();
        pnlBieuDoContent.add(chartPanel, BorderLayout.CENTER);
        pnlBieuDoContent.revalidate();
        pnlBieuDoContent.repaint();
    }

    private void updateDetailTable() {
        Date startDate = dateFrom.getDate();
        Date endDate = dateTo.getDate();

        if (startDate == null || endDate == null || startDate.after(endDate)) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khoảng thời gian hợp lệ!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Timestamp startTimestamp = new Timestamp(startDate.getTime());
        Timestamp endTimestamp = new Timestamp(endDate.getTime());

        String activeTab = getActiveTab();
        DefaultTableModel model = new DefaultTableModel(new String[]{"Thời gian", "Số chuyến đi", "Doanh thu (VNĐ)"}, 0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        if ("Theo ngày".equals(activeTab)) {
            Map<Date, Double> revenueData = business.getRevenueByDay(startTimestamp, endTimestamp);
            Map<Date, Integer> tripCountData = business.getTripCountByDay(startTimestamp, endTimestamp); // Giả định có phương thức này
            for (Map.Entry<Date, Double> entry : revenueData.entrySet()) {
                String dateStr = dateFormat.format(entry.getKey());
                Integer tripCount = tripCountData.getOrDefault(entry.getKey(), 0);
                model.addRow(new Object[]{dateStr, tripCount, dinhDangTien.format(entry.getValue())});
            }
        } else if ("Theo tuần".equals(activeTab)) {
            Map<String, Double> revenueData = business.getRevenueByWeek(startTimestamp, endTimestamp); // Giả định có phương thức này
            Map<String, Integer> tripCountData = business.getTripCountByWeek(startTimestamp, endTimestamp); // Giả định có phương thức này
            for (Map.Entry<String, Double> entry : revenueData.entrySet()) {
                String weekStr = entry.getKey();
                Integer tripCount = tripCountData.getOrDefault(weekStr, 0);
                model.addRow(new Object[]{weekStr, tripCount, dinhDangTien.format(entry.getValue())});
            }
        } else if ("Theo tháng".equals(activeTab)) {
            Map<String, Double> revenueData = business.getRevenueByMonth(startTimestamp, endTimestamp);
            Map<String, Integer> tripCountData = business.getTripCountByMonth(startTimestamp, endTimestamp); // Giả định có phương thức này
            for (Map.Entry<String, Double> entry : revenueData.entrySet()) {
                String monthStr = entry.getKey();
                Integer tripCount = tripCountData.getOrDefault(monthStr, 0);
                model.addRow(new Object[]{monthStr, tripCount, dinhDangTien.format(entry.getValue())});
            }
        } else if ("Theo năm".equals(activeTab)) {
            Map<String, Double> revenueData = business.getRevenueByYear(startTimestamp, endTimestamp); // Giả định có phương thức này
            Map<String, Integer> tripCountData = business.getTripCountByYear(startTimestamp, endTimestamp); // Giả định có phương thức này
            for (Map.Entry<String, Double> entry : revenueData.entrySet()) {
                String yearStr = entry.getKey();
                Integer tripCount = tripCountData.getOrDefault(yearStr, 0);
                model.addRow(new Object[]{yearStr, tripCount, dinhDangTien.format(entry.getValue())});
            }
        }

        tableChiTiet.setModel(model);
        tableChiTiet.revalidate();
        tableChiTiet.repaint();
    }

    private JFreeChart createChart(DefaultCategoryDataset dataset, String type, String title, String categoryAxisLabel, String valueAxisLabel) {
        try {
            JFreeChart chart;
            if (dataset.getRowCount() == 0 || dataset.getColumnCount() == 0) {
                JOptionPane.showMessageDialog(this, "Không có dữ liệu để tạo biểu đồ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return null;
            }

            switch (type) {
                case "Đường":
                    chart = ChartFactory.createLineChart(
                            title, categoryAxisLabel, valueAxisLabel, dataset,
                            PlotOrientation.VERTICAL, false, true, false);
                    break;
                case "Tròn":
                    DefaultPieDataset pieDataset = new DefaultPieDataset();
                    for (int i = 0; i < dataset.getColumnCount(); i++) {
                        Comparable columnKey = dataset.getColumnKey(i);
                        Number value = dataset.getValue("Doanh Thu", columnKey);
                        if (value != null) {
                            pieDataset.setValue(columnKey.toString(), value.doubleValue());
                        }
                    }
                    if (pieDataset.getItemCount() == 0) {
                        JOptionPane.showMessageDialog(this, "Không có dữ liệu cho biểu đồ tròn!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                        return null;
                    }
                    chart = ChartFactory.createPieChart(
                            title, pieDataset, false, true, false);
                    break;
                case "Cột":
                default:
                    chart = ChartFactory.createBarChart(
                            title, categoryAxisLabel, valueAxisLabel, dataset,
                            PlotOrientation.VERTICAL, false, true, false);
                    break;
            }

            if (title.equals("Biểu đồ doanh thu theo thời gian") && chart.getCategoryPlot() != null) {
                NumberAxis rangeAxis = (NumberAxis) chart.getCategoryPlot().getRangeAxis();
                rangeAxis.setNumberFormatOverride(new DecimalFormat("###,###,### VNĐ"));
            }

            chart.getPlot().setBackgroundPaint(new Color(245, 245, 250));
            return chart;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tạo biểu đồ: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private String getActiveTab() {
        if (tabNgay.getBackground().equals(titleCyan)) return "Theo ngày";
        if (tabTuan.getBackground().equals(titleCyan)) return "Theo tuần";
        if (tabThang.getBackground().equals(titleCyan)) return "Theo tháng";
        if (tabNam.getBackground().equals(titleCyan)) return "Theo năm";
        return "Theo ngày";
    }

    private void setActiveTab(JPanel activeTab) {
        tabNgay.setBackground(activeTab == tabNgay ? titleCyan : Color.WHITE);
        tabTuan.setBackground(activeTab == tabTuan ? titleCyan : Color.WHITE);
        tabThang.setBackground(activeTab == tabThang ? titleCyan : Color.WHITE);
        tabNam.setBackground(activeTab == tabNam ? titleCyan : Color.WHITE);

        tabNgay.setBorder(activeTab == tabNgay ? BorderFactory.createMatteBorder(1, 1, 0, 1, Color.LIGHT_GRAY) : BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        tabTuan.setBorder(activeTab == tabTuan ? BorderFactory.createMatteBorder(1, 1, 0, 1, Color.LIGHT_GRAY) : BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        tabThang.setBorder(activeTab == tabThang ? BorderFactory.createMatteBorder(1, 1, 0, 1, Color.LIGHT_GRAY) : BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        tabNam.setBorder(activeTab == tabNam ? BorderFactory.createMatteBorder(1, 1, 0, 1, Color.LIGHT_GRAY) : BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        updateRevenueChart();
        updateDetailTable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnApDung) {
            Date tuNgay = dateFrom.getDate();
            Date denNgay = dateTo.getDate();
            if (tuNgay != null && denNgay != null && !tuNgay.after(denNgay)) {
                capNhatDuLieuThongKe(tuNgay, denNgay);
                JOptionPane.showMessageDialog(this, "Đã áp dụng lọc từ " + new SimpleDateFormat("dd/MM/yyyy").format(tuNgay)
                        + " đến " + new SimpleDateFormat("dd/MM/yyyy").format(denNgay), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn khoảng thời gian hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else if (source == btnLamMoi) {
            try {
                Date defaultDate = new SimpleDateFormat("dd/MM/yyyy").parse("08/04/2025");
                dateFrom.setDate(defaultDate);
                dateTo.setDate(defaultDate);
                capNhatDuLieuThongKe(defaultDate, defaultDate);
                JOptionPane.showMessageDialog(this, "Đã làm mới dữ liệu!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                dateFrom.setDate(new Date());
                dateTo.setDate(new Date());
                capNhatDuLieuThongKe(new Date(), new Date());
                JOptionPane.showMessageDialog(this, "Lỗi khi làm mới, dùng ngày hiện tại!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            }
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
            try {
                new QuanLyDoanhThuView();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
