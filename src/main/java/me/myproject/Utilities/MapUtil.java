package me.myproject.Utilities;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.net.URL;

public class MapUtil extends JLabel {

    public MapUtil(String address) {
        String apiKey = "AIzaSyA2lvATUTysfX6qUU_kA_FdZuHtvGarEuk";
        
        try {
            // Mã hóa địa chỉ
            String encodedAddress = URLEncoder.encode(address, StandardCharsets.UTF_8.toString());
            
            // Tạo URL cho Static Map API
            String mapUrl = "https://maps.googleapis.com/maps/api/staticmap?" +
                    "center=" + encodedAddress +
                    "&zoom=15" +
                    "&size=600x1000" +
                    "&markers=color:red|" + encodedAddress +
                    "&key=" + apiKey;
            
            // Hiển thị hình ảnh từ URL
            ImageIcon imageIcon = new ImageIcon(new URL(mapUrl));
            this.setIcon(imageIcon);  // Thiết lập bản đồ vào JLabel
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
