import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class ColorQuantization3 {

    // Color quantization algorithm without using K-means clustering
    public static BufferedImage quantize(JLabel originalImage, int numColors) {
        Icon icon = originalImage.getIcon();
        Image image = ((ImageIcon) icon).getImage();
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        int[] pixels = bufferedImage.getRGB(0, 0, width, height, null, 0, width);
        Map<Integer, Integer> colorMap = new HashMap<Integer, Integer>();
        for (int color : pixels) {
            if (colorMap.containsKey(color)) {
                colorMap.put(color, colorMap.get(color) + 1);
            } else {
                colorMap.put(color, 1);
            }
        }
        Color[] colorArray = new Color[colorMap.size()];
        int[] countArray = new int[colorMap.size()];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : colorMap.entrySet()) {
            colorArray[i] = new Color(entry.getKey());
            countArray[i] = entry.getValue();
            i++;
        }
        quickSort(colorArray, countArray, 0, colorArray.length - 1);
        int[] quantizedColors = new int[numColors];
        int[] weights = new int[numColors];
        for (i = 0; i < numColors; i++) {
            quantizedColors[i] = colorArray[colorArray.length - i - 1].getRGB();
            weights[i] = countArray[colorArray.length - i - 1];
        }
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (i = 0; i < pixels.length; i++) {
            int closestColor = getClosestColorIndex(pixels[i], quantizedColors);
            outputImage.setRGB(i % width, i / width, quantizedColors[closestColor]);
        }
        return outputImage;
    }

    // Quick sort algorithm to sort the colors by their frequency
    public static void quickSort(Color[] colorArray, int[] countArray, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(colorArray, countArray, left, right);
            quickSort(colorArray, countArray, left, pivotIndex - 1);
            quickSort(colorArray, countArray, pivotIndex + 1, right);
        }
    }

    // Helper method for quick sort to partition the arrays
    public static int partition(Color[] colorArray, int[] countArray, int left, int right) {
        int pivotValue = countArray[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (countArray[j] >= pivotValue) {
                i++;
               swap(colorArray, i, j);
               // swap(colorArray, i, j);
            }
        }
        swap(colorArray, i + 1, right);
       // swap(colorArray, i + 1, right);
        return i + 1;
    }

    // Helper method to swap elements in an array
    public static void swap(Color[] array, int i, int j) {
        Color temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    // index of the closest color in the quantized color array
    public static int getClosestColorIndex(int color, int[] quantizedColors) {
        int minDistance = Integer.MAX_VALUE;
        int closestIndex = -1;
        for (int i = 0; i < quantizedColors.length; i++) {
            int distance = getColorDistance(color, quantizedColors[i]);
            if (distance < minDistance) {
                minDistance = distance;
                closestIndex = i;
            }
        }
        return closestIndex;
    }
    public static int getColorDistance(int color1, int color2) {
        Color c1 = new Color(color1);
        Color c2 = new Color(color2);
        int rDiff = c1.getRed() - c2.getRed();
        int gDiff = c1.getGreen() - c2.getGreen();
        int bDiff = c1.getBlue() - c2.getBlue();
        return rDiff * rDiff + gDiff * gDiff + bDiff * bDiff;
    }
}

// Helper method to get the
