import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.util.*;

public class ColorPaletteExtractor extends JFrame {
    private JPanel colorPanel;
    private JLabel imageLabel;

    public ColorPaletteExtractor(String name, BufferedImage indexedImage) {
        setTitle("Color Palette Extractor for " + name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a label for the image and add it to the center
        imageLabel = new JLabel();
        int scaledWidth = indexedImage.getWidth() / 2;  // Set the desired width
        int scaledHeight = indexedImage.getHeight() / 2;  // Set the desired height
        Image scaledImage = indexedImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        add(imageLabel, BorderLayout.CENTER);

        // Create a scroll pane for the color panel and add it to the bottom
        colorPanel = new JPanel();
        colorPanel.setLayout(new FlowLayout());
        JScrollPane scrollPane = new JScrollPane(colorPanel);
        add(scrollPane, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Set a larger size for the JFrame
        int width = 400;
        int height = 600; // Increased height to accommodate the image and colors
        setSize(width, height);

        extractColorPalette(indexedImage);
    }

    public Set<Integer> extractColorPalette(BufferedImage indexedImage) {
        Set<Color> colorPalette = new HashSet<>();

        int width = indexedImage.getWidth();
        int height = indexedImage.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = indexedImage.getRGB(x, y);
                colorPalette.add(new Color(rgb));
            }
        }

        displayColors(colorPalette);
        return null;
    }

    private void displayColors(Set<Color> colors) {
        colorPanel.removeAll();

        for (Color color : colors) {
            JPanel colorSwatch = new JPanel();
            colorSwatch.setBackground(color);
            colorSwatch.setPreferredSize(new Dimension(100, 100));

            colorPanel.add(colorSwatch);
        }

        revalidate();
        repaint();
    }
}
