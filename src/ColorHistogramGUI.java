import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class ColorHistogramGUI extends JFrame {

    private JPanel imagePanel, histogramPanel;
    private JLabel imageLabel, redLabel, greenLabel, blueLabel;
    private int[] redHistogram, greenHistogram, blueHistogram;
    private static final int HISTOGRAM_WIDTH = 400;
    private static final int HISTOGRAM_HEIGHT = 250;

    public ColorHistogramGUI(String name) {
        this.setTitle("Color Histogram " + name);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the panels
        imagePanel = new JPanel();
        histogramPanel = new JPanel();

        // Create the labels
        imageLabel = new JLabel();
        redLabel = new JLabel();
        greenLabel = new JLabel();
        blueLabel = new JLabel();

        // Set the preferred size of the imageLabel
        imageLabel.setPreferredSize(new Dimension(350, 350));

        // Set the layout manager and alignment of imagePanel
        imagePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        imagePanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        // Add the components to the panels
        imagePanel.add(imageLabel);
        histogramPanel.add(createHistogramPanel(redLabel, "Red", Color.RED));
        histogramPanel.add(createHistogramPanel(greenLabel, "Green", Color.GREEN));
        histogramPanel.add(createHistogramPanel(blueLabel, "Blue", Color.BLUE));
        histogramPanel.setLayout(new GridLayout(1, 3));

        // Add the panels to the frame
        this.add(imagePanel, BorderLayout.CENTER);
        this.add(histogramPanel, BorderLayout.SOUTH);
    }

    private JPanel createHistogramPanel(JLabel label, String title, Color color) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title));

        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);

        return panel;
    }

    private BufferedImage createHistogramImage(int[] histogram, Color color) {
        BufferedImage image = new BufferedImage(HISTOGRAM_WIDTH, HISTOGRAM_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the background
        g.setColor(new Color(255, 255, 255, 200));
        g.fillRect(0, 0, HISTOGRAM_WIDTH, HISTOGRAM_HEIGHT);

        // Set the bar color and transparency
        Color barColor;
        if (color.equals(Color.RED))
            barColor = new Color(180, 0, 0, 180); // Adjust the RGB values to make the color darker
        else if (color.equals(Color.GREEN))
            barColor = new Color(0, 160, 0, 180); // Adjust the RGB values to make the color darker
        else if (color.equals(Color.BLUE))
            barColor = new Color(0, 0, 180, 180); // Adjust the RGB values to make the color darker
        else
            barColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), 180);

        // Calculate the scaling factor
        int maxValue = getMaxValue(histogram);
        double scale = (double) HISTOGRAM_HEIGHT / maxValue;

        // Draw the grid lines
        g.setColor(Color.LIGHT_GRAY);
        Stroke dashedStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{2}, 0);
        g.setStroke(dashedStroke);
        for (int i = 0; i <= maxValue; i += 20) {
            int y = HISTOGRAM_HEIGHT - (int) (i * scale);
            g.drawLine(0, y, HISTOGRAM_WIDTH, y);
        }

        // Draw the histogram bars
        g.setStroke(new BasicStroke(3.0f)); // Adjust the stroke width here (e.g., 3.0f for thicker lines)
        for (int i = 0; i < HISTOGRAM_WIDTH; i++) {
            int barHeight = (int) (histogram[i] * scale);
            int x = i;
            int y = HISTOGRAM_HEIGHT - barHeight;

            if (color.equals(Color.RED))
                g.setColor(new Color(180, 0, 0)); // Adjust the RGB values to make the color darker
            else if (color.equals(Color.GREEN))
                g.setColor(new Color(0, 160, 0)); // Adjust the RGB values to make the color darker
            else if (color.equals(Color.BLUE))
                g.setColor(new Color(0, 0, 180)); // Adjust the RGB values to make the color darker
            else
                g.setColor(barColor);

            g.fillRect(x, y, 1, barHeight);
        }

        // Draw the y-axis numbers
        g.setColor(Color.BLACK);
        FontMetrics fontMetrics = g.getFontMetrics();
        int labelHeight = fontMetrics.getHeight();
        int yIncrement = (int) Math.ceil((double) maxValue / 5);

        for (int i = 0; i <= maxValue; i += yIncrement) {
            int x = 5;
            int y = HISTOGRAM_HEIGHT - (int) (i * scale) + labelHeight;
            String label = String.valueOf(i);
            g.drawString(label, x, y);
        }

        // Draw the x-axis labels
        g.setColor(Color.BLACK);
        int xIncrement = HISTOGRAM_WIDTH / histogram.length;

        for (int i = 0; i < histogram.length; i++) {
            int x = i * xIncrement + xIncrement / 2;
            int y = HISTOGRAM_HEIGHT + labelHeight;
            String label = String.valueOf(i);
            g.drawString(label, x, y);
        }

        return image;
    }

    private int getMaxValue(int[] data) {
        int maxValue = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] > maxValue) {
                maxValue = data[i];
            }
        }
        return maxValue;
    }

    void processImage(BufferedImage image) {
        // Calculate the scaling factor to fit the image within the label
        double scale = Math.min((double) imageLabel.getWidth() / image.getWidth(),
                (double) imageLabel.getHeight() / image.getHeight());

        // Resize the image using the scaling factor
        int scaledWidth = (int) (scale * image.getWidth());
        int scaledHeight = (int) (scale * image.getHeight());
        Image scaledImage = image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

        // Update the imageLabel with the resized image
        imageLabel.setIcon(new ImageIcon(scaledImage));

        BufferedImage bufferedImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_RGB);
        bufferedImage.createGraphics().drawImage(scaledImage, 0, 0, Color.WHITE, null);

        // Calculate the histogram for each color channel
        redHistogram = new int[HISTOGRAM_WIDTH];
        greenHistogram = new int[HISTOGRAM_WIDTH];
        blueHistogram = new int[HISTOGRAM_WIDTH];

        for (int y = 0; y < bufferedImage.getHeight(); y++) {
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                Color color = new Color(bufferedImage.getRGB(x, y));
                redHistogram[color.getRed()]++;
                greenHistogram[color.getGreen()]++;
                blueHistogram[color.getBlue()]++;
            }
        }

        // Update the histogram labels
        redLabel.setIcon(new ImageIcon(createHistogramImage(redHistogram, Color.RED)));
        greenLabel.setIcon(new ImageIcon(createHistogramImage(greenHistogram, Color.GREEN)));
        blueLabel.setIcon(new ImageIcon(createHistogramImage(blueHistogram, Color.BLUE)));
    }
}
