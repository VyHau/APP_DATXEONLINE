package me.myproject.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import me.myproject.Utilities.DIMENSION.FrameMain;
import me.myproject.Utilities.MapUtil;

public class DatXeView extends FrameMain implements ActionListener {
    // Fields for input and display
    private JTextField tfdPickup, tfdDestination;
    private JComboBox<String> cboDiscountCode;
    private JLabel lblDistance, lblPrice, lblDiscountStatus;
    private JRadioButton rdoXeSo, rdoXeGa, rdoXe4Cho, rdoXe7Cho;
    private JButton btnBooking;

    // Constants for pricing
    private final int REGULAR_BIKE_RATE = 25000; // VND per km
    private final int PREMIUM_BIKE_RATE = 28000; // VND per km
    private final int CAR_4SEAT_RATE = 45000; // VND per km
    private final int CAR_7SEAT_RATE = 65000; // VND per km

    // Colors
    private final Color TEAL_COLOR = new Color(0, 160, 160);
    private final Color LIGHT_GRAY_BG = new Color(240, 240, 240);
    private final Color SELECTED_VEHICLE_BG = new Color(230, 250, 250);
    private final Color SELECTED_VEHICLE_BORDER = new Color(0, 180, 180);

    // Current discount percentage (0-100)
    private int discountPercentage = 0;

    // Vehicle panels for styling
    private JPanel xeSoPanel, xeGaPanel, xe4ChoPanel, xe7ChoPanel;

    public DatXeView() {
        super("Đặt Xe");
        init();
    }

    private void init() {
        // Set up the frame with JLayeredPane as the content pane
        JLayeredPane layeredPane = new JLayeredPane();
        this.setContentPane(layeredPane);
        Dimension frameDimension = this.getSize();
        int frameWidth = frameDimension.width;
        int frameHeight = frameDimension.height;


        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(TEAL_COLOR);
        headerPanel.setPreferredSize(new Dimension(frameWidth, 80));
        headerPanel.setBounds(0, 0, frameWidth, 80);
        layeredPane.add(headerPanel, JLayeredPane.PALETTE_LAYER);

        // Logo/icon placeholder on left side of header
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(TEAL_COLOR);
        logoPanel.setPreferredSize(new Dimension(80, 80));
        headerPanel.add(logoPanel, BorderLayout.WEST);

        // Title in header (centered)
        JLabel titleLabel = new JLabel("ĐẶT XE", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        // Main content panel with BorderLayout
        JPanel contentPanel = new JPanel(new GridLayout(1, 2));
        contentPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        contentPanel.setOpaque(false); // Make content panel transparent to show background
        contentPanel.setBounds(0, 80, frameWidth, frameHeight - 80);
        layeredPane.add(contentPanel, JLayeredPane.PALETTE_LAYER);

        // Left panel - booking form
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridBagLayout());
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));

        // Right panel - map
        JPanel mapPanel = new JPanel(new BorderLayout());
        mapPanel.setBackground(LIGHT_GRAY_BG);
        JLabel mapLabel = new MapUtil("48 Cao Thắng ,Đà nẵng");
        mapLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mapLabel.setForeground(Color.DARK_GRAY);
        // mapLabel.setHorizontalAlignment(SwingConstants.CENTER);
        // mapLabel.setVerticalAlignment(SwingConstants.CENTER);
        mapPanel.add(mapLabel, BorderLayout.CENTER);

        // Rest of the left panel setup (unchanged)
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.weightx = 1.0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        gbc.gridy = 0;
        JLabel questionLabel = new JLabel("Bạn muốn đi đâu?");
        questionLabel.setFont(new Font("Segoe UI", Font.BOLD, 30)); // Bold and larger font size
        questionLabel.setForeground(new Color(0, 128, 128)); // Stylish color (teal)
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center text horizontally
        questionLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0)); // Margin at the bottom for spacing
        leftPanel.add(questionLabel, gbc);

        // Pickup point
        gbc.gridy = 1;
        JLabel pickupLabel = new JLabel("Điểm đón");
        pickupLabel.setFont(new Font("Arial", Font.BOLD, 14));
        leftPanel.add(pickupLabel, gbc);

        gbc.gridy = 2;
        tfdPickup = new JTextField(20);
        tfdPickup.setPreferredSize(new Dimension(tfdPickup.getPreferredSize().width, 30));
        leftPanel.add(tfdPickup, gbc);

        // Destination
        gbc.gridy = 3;
        JLabel destinationLabel = new JLabel("Điểm đến");
        destinationLabel.setFont(new Font("Arial", Font.BOLD, 14));
        leftPanel.add(destinationLabel, gbc);

        gbc.gridy = 4;
        tfdDestination = new JTextField(20);
        tfdDestination.setPreferredSize(new Dimension(tfdDestination.getPreferredSize().width, 30));
        leftPanel.add(tfdDestination, gbc);

        // Distance
        gbc.gridy = 5;
        JPanel distancePanel = new JPanel(new BorderLayout());
        distancePanel.setOpaque(false);
        JLabel distanceLabel = new JLabel("Quãng đường:");
        distanceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        lblDistance = new JLabel("5 km");
        lblDistance.setFont(new Font("Arial", Font.PLAIN, 14));
        lblDistance.setHorizontalAlignment(SwingConstants.RIGHT);
        distancePanel.add(distanceLabel, BorderLayout.WEST);
        distancePanel.add(lblDistance, BorderLayout.EAST);
        leftPanel.add(distancePanel, gbc);

        // Price
        gbc.gridy = 6;
        JPanel pricePanel = new JPanel(new BorderLayout());
        pricePanel.setOpaque(false);
        JLabel pricePromptLabel = new JLabel("Giá tiền:");
        pricePromptLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        lblPrice = new JLabel("25,000 VND (ước tính)");
        lblPrice.setFont(new Font("Arial", Font.PLAIN, 14));
        lblPrice.setForeground(TEAL_COLOR);
        lblPrice.setHorizontalAlignment(SwingConstants.RIGHT);
        pricePanel.add(pricePromptLabel, BorderLayout.WEST);
        pricePanel.add(lblPrice, BorderLayout.EAST);
        leftPanel.add(pricePanel, gbc);

        // Discount Code Section with ComboBox
        gbc.gridy = 7;
        JPanel discountPanel = new JPanel(new BorderLayout(10, 0));
        discountPanel.setOpaque(false);
        JLabel discountLabel = new JLabel("Mã giảm giá:");
        discountLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        String[] discountCodes = { "Không áp dụng", "GIAMGIA10", "GIAMGIA20" };
        cboDiscountCode = new JComboBox<>(discountCodes);
        cboDiscountCode.setPreferredSize(new Dimension(cboDiscountCode.getPreferredSize().width, 30));
        cboDiscountCode.addActionListener(e -> applySelectedDiscount());
        discountPanel.add(discountLabel, BorderLayout.WEST);
        discountPanel.add(cboDiscountCode, BorderLayout.CENTER);
        leftPanel.add(discountPanel, gbc);

        // Discount Status
        gbc.gridy = 8;
        lblDiscountStatus = new JLabel("");
        lblDiscountStatus.setFont(new Font("Arial", Font.ITALIC, 12));
        leftPanel.add(lblDiscountStatus, gbc);

        // Separator
        gbc.gridy = 9;
        JPanel separatorPanel = new JPanel();
        separatorPanel.setBorder(new MatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));
        separatorPanel.setPreferredSize(new Dimension(leftPanel.getWidth(), 1));
        leftPanel.add(separatorPanel, gbc);

        // Vehicle type header
        gbc.gridy = 11;
        JLabel vehicleTypeLabel = new JLabel("Chọn loại xe");
        vehicleTypeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        leftPanel.add(vehicleTypeLabel, gbc);

        // Vehicle selection panel
        gbc.gridy = 12;
        JPanel vehiclesPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        vehiclesPanel.setOpaque(false);

        // Create vehicle panels without images
        xeSoPanel = createImprovedVehiclePanel("Xe Số", REGULAR_BIKE_RATE); // Removed image path
        rdoXeSo = (JRadioButton) xeSoPanel.getComponent(0);
        rdoXeSo.setSelected(true);

        xeGaPanel = createImprovedVehiclePanel("Xe Ga", PREMIUM_BIKE_RATE); // Removed image path
        rdoXeGa = (JRadioButton) xeGaPanel.getComponent(0);

        xe4ChoPanel = createImprovedVehiclePanel("Xe 4 chỗ", CAR_4SEAT_RATE); // Removed image path
        rdoXe4Cho = (JRadioButton) xe4ChoPanel.getComponent(0);

        xe7ChoPanel = createImprovedVehiclePanel("Xe 7 chỗ", CAR_7SEAT_RATE); // Removed image path
        rdoXe7Cho = (JRadioButton) xe7ChoPanel.getComponent(0);

        // Add the vehicle panels to the grid
        vehiclesPanel.add(xeSoPanel);
        vehiclesPanel.add(xeGaPanel);
        vehiclesPanel.add(xe4ChoPanel);
        vehiclesPanel.add(xe7ChoPanel);

        // Group the radio buttons for vehicle selection
        ButtonGroup vehicleGroup = new ButtonGroup();
        vehicleGroup.add(rdoXeSo);
        vehicleGroup.add(rdoXeGa);
        vehicleGroup.add(rdoXe4Cho);
        vehicleGroup.add(rdoXe7Cho);

        // Add the vehicle selection panel to the left panel
        leftPanel.add(vehiclesPanel, gbc);

        // Booking button
        gbc.gridy = 13;
        gbc.insets = new Insets(20, 0, 5, 0);
        btnBooking = new JButton("ĐẶT XE");
        btnBooking.setPreferredSize(new Dimension(leftPanel.getWidth(), 40));
        btnBooking.setBackground(TEAL_COLOR);
        btnBooking.setForeground(Color.BLACK);
        btnBooking.setFocusPainted(false);
        btnBooking.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBooking.setFont(new Font("Arial", Font.BOLD, 16));
        btnBooking.addActionListener(this);
        leftPanel.add(btnBooking, gbc);

        // Add panels to content panel
        contentPanel.add(leftPanel);
        contentPanel.add(mapPanel);

        // Add listeners for radio buttons
        rdoXeSo.addActionListener(e -> {
            updateVehicleSelection();
            updatePrice();
        });
        rdoXeGa.addActionListener(e -> {
            updateVehicleSelection();
            updatePrice();
        });
        rdoXe4Cho.addActionListener(e -> {
            updateVehicleSelection();
            updatePrice();
        });
        rdoXe7Cho.addActionListener(e -> {
            updateVehicleSelection();
            updatePrice();
        });

        // Initial vehicle selection highlight
        updateVehicleSelection();

        // Initial price update
        updatePrice();

        this.setVisible(true);
    }

    private JPanel createImprovedVehiclePanel(String name, int rate) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
        panel.setBackground(new Color(250, 250, 250));

        // Create radio button with name
        JRadioButton radioButton = new JRadioButton(name);
        radioButton.setFont(new Font("Arial", Font.BOLD, 13));
        radioButton.setHorizontalAlignment(SwingConstants.CENTER);
        radioButton.setBackground(new Color(250, 250, 250));
        radioButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Create panel for icon
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        imagePanel.setBackground(Color.WHITE);

        // Format price with thousand separator
        String formattedRate = String.format("%,d", rate).replace(",", ".");
        JLabel priceLabel = new JLabel(formattedRate + " VND/km");
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        priceLabel.setForeground(TEAL_COLOR);
        priceLabel.setFont(new Font("Arial", Font.BOLD, 12));
        priceLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        // Add components to panel
        panel.add(radioButton, BorderLayout.NORTH);
        panel.add(imagePanel, BorderLayout.CENTER);
        panel.add(priceLabel, BorderLayout.SOUTH);

        // Make entire panel clickable
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioButton.setSelected(true);
                updateVehicleSelection();
                updatePrice();
            }
        });

        return panel;
    }

    private void updateVehicleSelection() {
        // Reset all panels
        xeSoPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
        xeSoPanel.setBackground(new Color(250, 250, 250));
        rdoXeSo.setBackground(new Color(250, 250, 250));

        xeGaPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
        xeGaPanel.setBackground(new Color(250, 250, 250));
        rdoXeGa.setBackground(new Color(250, 250, 250));

        xe4ChoPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
        xe4ChoPanel.setBackground(new Color(250, 250, 250));
        rdoXe4Cho.setBackground(new Color(250, 250, 250));

        xe7ChoPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
        xe7ChoPanel.setBackground(new Color(250, 250, 250));
        rdoXe7Cho.setBackground(new Color(250, 250, 250));

        // Highlight selected panel
        if (rdoXeSo.isSelected()) {
            xeSoPanel.setBorder(new LineBorder(SELECTED_VEHICLE_BORDER, 2, true));
            xeSoPanel.setBackground(SELECTED_VEHICLE_BG);
            rdoXeSo.setBackground(SELECTED_VEHICLE_BG);
        } else if (rdoXeGa.isSelected()) {
            xeGaPanel.setBorder(new LineBorder(SELECTED_VEHICLE_BORDER, 2, true));
            xeGaPanel.setBackground(SELECTED_VEHICLE_BG);
            rdoXeGa.setBackground(SELECTED_VEHICLE_BG);
        } else if (rdoXe4Cho.isSelected()) {
            xe4ChoPanel.setBorder(new LineBorder(SELECTED_VEHICLE_BORDER, 2, true));
            xe4ChoPanel.setBackground(SELECTED_VEHICLE_BG);
            rdoXe4Cho.setBackground(SELECTED_VEHICLE_BG);
        } else if (rdoXe7Cho.isSelected()) {
            xe7ChoPanel.setBorder(new LineBorder(SELECTED_VEHICLE_BORDER, 2, true));
            xe7ChoPanel.setBackground(SELECTED_VEHICLE_BG);
            rdoXe7Cho.setBackground(SELECTED_VEHICLE_BG);
        }
    }

    private void applySelectedDiscount() {
        String selectedDiscount = (String) cboDiscountCode.getSelectedItem();

        if (selectedDiscount.equals("Không áp dụng")) {
            discountPercentage = 0;
            lblDiscountStatus.setText("");
        } else if (selectedDiscount.equals("GIAMGIA10")) {
            discountPercentage = 10;
            lblDiscountStatus.setText("Áp dụng thành công: Giảm 10%");
            lblDiscountStatus.setForeground(new Color(0, 128, 0)); // Dark green
        } else if (selectedDiscount.equals("GIAMGIA20")) {
            discountPercentage = 20;
            lblDiscountStatus.setText("Áp dụng thành công: Giảm 20%");
            lblDiscountStatus.setForeground(new Color(0, 128, 0)); // Dark green
        }

        // Update price with the discount
        updatePrice();
    }

    private void updatePrice() {
        int distance = 5; // Fixed distance as in the example
        int rate;

        if (rdoXeSo.isSelected()) {
            rate = REGULAR_BIKE_RATE;
        } else if (rdoXeGa.isSelected()) {
            rate = PREMIUM_BIKE_RATE;
        } else if (rdoXe4Cho.isSelected()) {
            rate = CAR_4SEAT_RATE;
        } else { // 7-seat car
            rate = CAR_7SEAT_RATE;
        }

        int totalPrice = distance * rate;

        // Apply discount if any
        if (discountPercentage > 0) {
            totalPrice = totalPrice * (100 - discountPercentage) / 100;
        }

        // Format with thousand separator
        String formattedPrice = String.format("%,d", totalPrice).replace(",", ".");
        lblPrice.setText(formattedPrice + " VND (ước tính)");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBooking) {
            if (tfdPickup.getText().trim().isEmpty() || tfdDestination.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập điểm đón và điểm đến",
                        "Thông báo", JOptionPane.WARNING_MESSAGE);
            } else {
                String selectedVehicle = getSelectedVehicle();
                String discountInfo = discountPercentage > 0 ? "\nGiảm giá: " + discountPercentage + "%" : "";

                String message = "Đặt xe thành công!\n" +
                        "Điểm đón: " + tfdPickup.getText().trim() + "\n" +
                        "Điểm đến: " + tfdDestination.getText().trim() + "\n" +
                        "Loại xe: " + selectedVehicle + "\n" +
                        "Quãng đường: " + lblDistance.getText() + discountInfo + "\n" +
                        "Giá tiền: " + lblPrice.getText();

                // Display notification with icon
                try {
                    ImageIcon icon = new ImageIcon(getClass().getResource("/me/myproject/IMAGE/success.png"));
                    JLabel label = new JLabel(message, icon, JLabel.CENTER);
                    JOptionPane.showMessageDialog(this, label, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    // If image can't be loaded, show regular message
                    JOptionPane.showMessageDialog(this, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private String getSelectedVehicle() {
        if (rdoXeSo.isSelected())
            return "Xe Số";
        if (rdoXeGa.isSelected())
            return "Xe Ga";
        if (rdoXe4Cho.isSelected())
            return "Xe 4 chỗ";
        if (rdoXe7Cho.isSelected())
            return "Xe 7 chỗ";
        return "";
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new DatXeView());
    }
}