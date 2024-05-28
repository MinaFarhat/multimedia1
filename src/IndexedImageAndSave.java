import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.IndexColorModel;
import java.io.File;
import java.io.IOException;

public class IndexedImageAndSave {
    static public BufferedImage indexedImage(BufferedImage originalImage,String path) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int[] palette = {0xFF0000, 0x00FF00, 0x0000FF};
        IndexColorModel colorModel = new IndexColorModel(8, palette.length, palette, 0, false, -1, DataBuffer.TYPE_BYTE);
        BufferedImage indexedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_INDEXED, colorModel);
        Graphics2D g = indexedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, null);
        g.dispose();
        File outputfile = new File("indexed_image.png");
        try {
            ImageIO.write(indexedImage, "png", outputfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        saveImage(indexedImage,path);
        return indexedImage;
    }
    public static void saveImage(BufferedImage image, String directory) {
        String format = "png"; // image format, you can change it to jpg, gif, etc.
        File directoryFile = new File(directory);
        if (!directoryFile.exists()) {
            directoryFile.mkdirs();
        }
        File file;
        int counter = 1;
        do {
            file = new File(directory + "Indexed_Image_" + counter + "." + format);
            counter++;
        } while (file.exists());
        try {
            ImageIO.write(image, format, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Image saved to " + file.getAbsolutePath());
    }
}
