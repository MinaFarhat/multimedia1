import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
public class ColorQuantization {
    public static BufferedImage performColorQuantization(JLabel originalImage, int k) {
        Icon icon = originalImage.getIcon();
        Image image = ((ImageIcon) icon).getImage();
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        BufferedImage quantizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int[] rgbPixels = new int[width * height];
        bufferedImage.getRGB(0, 0, width, height, rgbPixels, 0, width);

        KMeans kmeans = new KMeans(k);
        int[] clusterIndices = kmeans.cluster(rgbPixels);
        int[] centroids = kmeans.getCentroids();
        for (int i = 0; i < width * height; i++) {
            int clusterIndex = clusterIndices[i];
            int centroid = centroids[clusterIndex];
            quantizedImage.setRGB(i % width, i / width, centroid);
        }

        return quantizedImage;
    }

}