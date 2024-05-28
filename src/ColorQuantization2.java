import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class ColorQuantization2 {
    public static Color[] createColorMap(int numColors) {
        Color[] colorMap = new Color[numColors];
        int step = 255 / (int) Math.sqrt(numColors);
        int index = 0;
        for (int r = 0; r < 255; r += step) {
            for (int g = 0; g < 255; g += step) {
                for (int b = 0; b < 255; b += step) {
                    if (index < numColors) {
                        colorMap[index++] = new Color(r, g, b);
                    }
                }
            }
        }
        return colorMap;
    }

    public static BufferedImage quantizePhoto(JLabel inputPhoto, Color[] colorMap) {
        Icon icon = inputPhoto.getIcon();
        Image image = ((ImageIcon) icon).getImage();
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        BufferedImage outputPhoto = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color inputColor = new Color(bufferedImage.getRGB(x, y));
                Color outputColor = findClosestColor(inputColor, colorMap);
                outputPhoto.setRGB(x, y, outputColor.getRGB());
            }
        }
        return outputPhoto;
    }
    public static Color findClosestColor(Color color, Color[] colorMap) {
        Color closestColor = colorMap[0];
        double closestDistance = colorDistance(color, closestColor);
        for (int i = 1; i < colorMap.length; i++) {
            double distance = colorDistance(color, colorMap[i]);
            if (distance < closestDistance) {
                closestColor = colorMap[i];
                closestDistance = distance;
            }
        }
        return closestColor;
    }

    /**
     * Computes the Euclidean distance between two colors in RGB space.
     */
    public static double colorDistance(Color c1, Color c2) {
        double r1 = c1.getRed();
        double g1 = c1.getGreen();
        double b1 = c1.getBlue();
        double r2 = c2.getRed();
        double g2 = c2.getGreen();
        double b2 = c2.getBlue();
        double dR = r1 - r2;
        double dG = g1 - g2;
        double dB = b1 - b2;
        return Math.sqrt(dR * dR + dG * dG + dB * dB);
    }
}