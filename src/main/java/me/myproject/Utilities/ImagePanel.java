package me.myproject.Utilities;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
    private Image image;
    private int width;
    private int height;

    public ImagePanel(String imagePath, int width, int height) {
        this.width = width;
        this.height = height;

        // Load image from path
        URL imageUrl = ImageAccess.class.getResource(imagePath);
        if (imageUrl != null) {
            this.image = new ImageIcon(imageUrl).getImage();
        } else {
            System.err.println("Không tìm thấy ảnh: " + imagePath);
        }

        setPreferredSize(new Dimension(width, height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, width, height, this);
        }
    }
}