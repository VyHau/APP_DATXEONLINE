package me.myproject.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JDateChooser;

//import me.myproject.BUSINESSLOGIC.LichSuDiChuyenBSL;
import me.myproject.Utilities.DIMENSION.FrameMain;
import me.myproject.MODEL.LoaiXe;

public class LichSuChuyenDiView extends FrameMain implements ActionListener {
    //private final LichSuDiChuyenBSL business;
    private JComboBox<String> cboLoaiXe;
    private JDateChooser dateFrom, dateTo;
    private JButton btnLoc, btnXuatFile, btnQuayLai;
    private JTable tblLichSu;
    private DefaultTableModel modelTable;
    private JPanel pnlChiTiet;
    private JLabel lblNgayChiTiet, lblLoaiXeChiTiet, lblDiemDonChiTiet, lblDiemDenChiTiet;
    private JLabel lblTaiXeChiTiet, lblGiaTienChiTiet;
    private JPanel pnlDanhGia;
    private JButton[] btnDanhGia;

    public LichSuChuyenDiView() {
        super("Lịch sử di chuyển");
        //business = new LichSuDiChuyenBSL();
        init();
    }

    private void init() {
        // Thiết lập bố cục chính
        this.setLayout(new BorderLayout());
        
        // Panel tiêu đề
        JPanel headerPanel = createHeaderPanel();
        this.add(headerPanel, BorderLayout.NORTH);
        
        // Panel lọc
        JPanel filterPanel = createFilterPanel();
        this.add(filterPanel, BorderLayout.CENTER);
        
        // Hiển thị giao diện
        this.setVisible(true);
    }
    
    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 178, 192));
        panel.setPreferredSize(new Dimension(getWidth(), 60));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 15));
        
        ImageIcon backIcon = new ImageIcon(getClass().getResource("/me/myproject/IMAGE/back.png"));
        Image backImg = backIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        backIcon = new ImageIcon(backImg);
        btnQuayLai = new JButton(backIcon);
        btnQuayLai.setBorderPainted(false);
        btnQuayLai.setFocusPainted(false);
        btnQuayLai.setPreferredSize(new Dimension(30, 30)); 
        btnQuayLai.setBackground(new Color(0, 178, 192)); 
        btnQuayLai.addActionListener(this); 
        panel.add(btnQuayLai);
        
        JLabel lblTitle = new JLabel("LỊCH SỬ DI CHUYỂN");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setForeground(Color.WHITE);
        panel.add(lblTitle);
        
        return panel;
    }
    
    private JPanel createFilterPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel tìm kiếm với các điều khiển lọc
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        searchPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        
        // Dropdown loại xe
        JPanel pnlLoaiXe = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlLoaiXe.add(new JLabel("Loại xe:"));
        cboLoaiXe = new JComboBox<>();
        cboLoaiXe.setPreferredSize(new Dimension(100, 25));
        cboLoaiXe.setBackground(Color.WHITE);
        pnlLoaiXe.add(cboLoaiXe);
        searchPanel.add(pnlLoaiXe);
        // Đổ dữ liệu loại xe vào combobox
        
        // Chọn khoảng thời gian
        JPanel pnlDate = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlDate.add(new JLabel("Từ ngày:"));
        dateFrom = new JDateChooser();
        dateFrom.setPreferredSize(new Dimension(120, 25));
        dateFrom.setDate(new Date());
        pnlDate.add(dateFrom);
        
        pnlDate.add(new JLabel("Đến ngày:"));
        dateTo = new JDateChooser();
        dateTo.setPreferredSize(new Dimension(120, 25));
        dateTo.setDate(new Date());
        pnlDate.add(dateTo);
        searchPanel.add(pnlDate);
        
        // Các nút Lọc và Xuất file
        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnLoc = new JButton("Lọc");
        btnLoc.setBackground(new Color(0, 178, 192));
        btnLoc.setForeground(Color.WHITE);
        btnLoc.addActionListener(this);
        pnlButtons.add(btnLoc);
        
        btnXuatFile = new JButton("Xuất file");
        btnXuatFile.setBackground(new Color(0, 178, 192));
        btnXuatFile.setForeground(Color.WHITE);
        btnXuatFile.addActionListener(this);
        pnlButtons.add(btnXuatFile);
        
        searchPanel.add(pnlButtons);
        
        mainPanel.add(searchPanel, BorderLayout.NORTH);
        
        // Bảng v
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout(10, 0));
        
        // Bảng lịch sử di chuyển
        String[] columnNames = {"Ngày", "Loại xe", "Điểm đón", "Điểm đến", "Tài xế", "Giá tiền", "Trạng thái"};
        modelTable = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblLichSu = new JTable(modelTable);
        
        // Căn giữa dữ liệu trong bảng
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tblLichSu.getColumnCount(); i++) {
            tblLichSu.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        // Tùy chỉnh tiêu đề bảng
        JTableHeader header = tblLichSu.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 12));
        header.setBackground(new Color(240, 240, 240));
        header.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        
        JScrollPane scrollPane = new JScrollPane(tblLichSu);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Panel chi tiết bên phải
        pnlChiTiet = createDetailPanel();
        contentPanel.add(pnlChiTiet, BorderLayout.EAST);
        
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        return mainPanel;
    }
    
    private JPanel createDetailPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), "CHI TIẾT CHUYẾN ĐI"));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(250, 400));
        
        // Ngày
        JPanel pnlNgay = new JPanel(new BorderLayout());
        pnlNgay.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        pnlNgay.add(new JLabel("Ngày:"), BorderLayout.NORTH);
        lblNgayChiTiet = new JLabel();
        // Khi chọn hàng trên bảng hiện ngày
        pnlNgay.add(lblNgayChiTiet, BorderLayout.CENTER);
        panel.add(pnlNgay);
        
        // Loại xe
        JPanel pnlLoaiXe = new JPanel(new BorderLayout());
        pnlLoaiXe.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        pnlLoaiXe.add(new JLabel("Loại xe:"), BorderLayout.NORTH);
        lblLoaiXeChiTiet = new JLabel();
        // Chọn hàng hiện loại xe
        pnlLoaiXe.add(lblLoaiXeChiTiet, BorderLayout.CENTER);
        panel.add(pnlLoaiXe);
        
        // Điểm đón
        JPanel pnlDiemDon = new JPanel(new BorderLayout());
        pnlDiemDon.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        pnlDiemDon.add(new JLabel("Điểm đón:"), BorderLayout.NORTH);
        lblDiemDonChiTiet = new JLabel();
        // Hiển thị điểm đón theo hàng được chọn
        pnlDiemDon.add(lblDiemDonChiTiet, BorderLayout.CENTER);
        panel.add(pnlDiemDon);
        
        // Điểm đến
        JPanel pnlDiemDen = new JPanel(new BorderLayout());
        pnlDiemDen.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        pnlDiemDen.add(new JLabel("Điểm đến:"), BorderLayout.NORTH);
        lblDiemDenChiTiet = new JLabel();
        // Hiển thị điểm đến theo hàng được chọn
        pnlDiemDen.add(lblDiemDenChiTiet, BorderLayout.CENTER);
        panel.add(pnlDiemDen);
        
        // Tài xế
        JPanel pnlTaiXe = new JPanel(new BorderLayout());
        pnlTaiXe.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        pnlTaiXe.add(new JLabel("Tài xế:"), BorderLayout.NORTH);
        lblTaiXeChiTiet = new JLabel();
        // Hiển thị tên tài xế theo hàng được chọn
        pnlTaiXe.add(lblTaiXeChiTiet, BorderLayout.CENTER);
        panel.add(pnlTaiXe);
        
        // Giá tiền
        JPanel pnlGiaTien = new JPanel(new BorderLayout());
        pnlGiaTien.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        pnlGiaTien.add(new JLabel("Giá tiền:"), BorderLayout.NORTH);
        lblGiaTienChiTiet = new JLabel();
        // Hiển thị giá tiền theo hàng được chọn
        pnlGiaTien.add(lblGiaTienChiTiet, BorderLayout.CENTER);
        panel.add(pnlGiaTien);
        
        // Đánh giá
        JPanel pnlDanhGiaContainer = new JPanel(new BorderLayout());
        pnlDanhGiaContainer.add(new JLabel("Đánh giá:"), BorderLayout.NORTH);
        
        pnlDanhGia = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnDanhGia = new JButton[5];
        for (int i = 0; i < 5; i++) {
            btnDanhGia[i] = new JButton();
            btnDanhGia[i].setFont(new Font("Arial", Font.PLAIN, 12));
            btnDanhGia[i].setPreferredSize(new Dimension(40, 40));
            btnDanhGia[i].setBackground(Color.WHITE);  
            btnDanhGia[i].setText(String.valueOf(i + 1));
            btnDanhGia[i].addActionListener(this);
            pnlDanhGia.add(btnDanhGia[i]);
        }
        pnlDanhGiaContainer.add(pnlDanhGia, BorderLayout.CENTER);
        panel.add(pnlDanhGiaContainer);       
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if (source == btnLoc) {
            // Lấy giá trị lọc
            String loaiXe = (String) cboLoaiXe.getSelectedItem();
            Date tuNgay = dateFrom.getDate();
            Date denNgay = dateTo.getDate();
            
            // Gọi lớp business để lọc dữ liệu
            // business.filterTravelHistory(loaiXe, tuNgay, denNgay);
            
            // Tạm thời in thông tin lọc
            System.out.println("Lọc dữ liệu: Loại xe: " + loaiXe + 
                               ", Từ ngày: " + new SimpleDateFormat("dd/MM/yyyy").format(tuNgay) + 
                               ", Đến ngày: " + new SimpleDateFormat("dd/MM/yyyy").format(denNgay));
        } else if (source == btnXuatFile) {
            System.out.println("Đang xuất dữ liệu...");
            // business.exportToFile();
        } else if (source == btnQuayLai) {
            // Xử lý sự kiện nút quay lại
            System.out.println("Quay lại màn hình trước đó");
            this.dispose(); // Đóng cửa sổ hiện tại
            new TrangChuUserView();
        } else {
            // Kiểm tra nếu nguồn là một trong các nút đánh giá
            for (int i = 0; i < btnDanhGia.length; i++) {
                if (source == btnDanhGia[i]) {
                    setRating(i + 1);
                    break;
                }
            }
        }
    }
    
    private void setRating(int rating) {
        System.out.println("Số sao đánh giá: " + rating);
        for (int i = 0; i < btnDanhGia.length; i++) {
            btnDanhGia[i].setBackground(i < rating ? Color.YELLOW : Color.WHITE);
        }
    }

    
    public static void main(String[] args) {
        new LichSuChuyenDiView();
    }
}
