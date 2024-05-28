import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Print extends JFrame {
    JLabel originalImageLabel;
    JLabel originalImageLabel2;
    JLabel originalImageLabel3;
    JLabel quantizedImageLabel;
    JLabel quantizedImageLabel2;
    JLabel quantizedImageLabel3;
    JLabel IndexedImageLabel;
    JLabel IndexedImageLabel2;
    JLabel IndexedImageLabel3;
    JLabel originalImageInfoLabel;
    JLabel originalImageInfoLabel2;
    JLabel originalImageInfoLabel3;
    JLabel quantizedImageInfoLabel;
    JLabel quantizedImageInfoLabel2;
    JLabel quantizedImageInfoLabel3;
    JLabel IndexedImageInfoLabel;
    JLabel IndexedImageInfoLabel2;
    JLabel IndexedImageInfoLabel3;
    public Print() {
        super("Difference between three algorithms using Color Quantization algorithms");

        // Create the labels
        originalImageLabel = new JLabel();
        originalImageLabel.setHorizontalAlignment(JLabel.CENTER);
        originalImageLabel.setVerticalAlignment(JLabel.CENTER);

        originalImageLabel2 = new JLabel();
        originalImageLabel2.setHorizontalAlignment(JLabel.CENTER);
        originalImageLabel2.setVerticalAlignment(JLabel.CENTER);

        originalImageLabel3 = new JLabel();
        originalImageLabel3.setHorizontalAlignment(JLabel.CENTER);
        originalImageLabel3.setVerticalAlignment(JLabel.CENTER);

        quantizedImageLabel = new JLabel();
        quantizedImageLabel.setHorizontalAlignment(JLabel.CENTER);
        quantizedImageLabel.setVerticalAlignment(JLabel.CENTER);

        quantizedImageLabel2 = new JLabel();
        quantizedImageLabel2.setHorizontalAlignment(JLabel.CENTER);
        quantizedImageLabel2.setVerticalAlignment(JLabel.CENTER);

        quantizedImageLabel3 = new JLabel();
        quantizedImageLabel3.setHorizontalAlignment(JLabel.CENTER);
        quantizedImageLabel3.setVerticalAlignment(JLabel.CENTER);

        IndexedImageLabel = new JLabel();
        IndexedImageLabel.setHorizontalAlignment(JLabel.CENTER);
        IndexedImageLabel.setVerticalAlignment(JLabel.CENTER);

        IndexedImageLabel2 = new JLabel();
        IndexedImageLabel2.setHorizontalAlignment(JLabel.CENTER);
        IndexedImageLabel2.setVerticalAlignment(JLabel.CENTER);

        IndexedImageLabel3 = new JLabel();
        IndexedImageLabel3.setHorizontalAlignment(JLabel.CENTER);
        IndexedImageLabel3.setVerticalAlignment(JLabel.CENTER);

        originalImageInfoLabel = new JLabel();
        originalImageInfoLabel.setHorizontalAlignment(JLabel.CENTER);

        originalImageInfoLabel2 = new JLabel();
        originalImageInfoLabel2.setHorizontalAlignment(JLabel.CENTER);

        originalImageInfoLabel3 = new JLabel();
        originalImageInfoLabel3.setHorizontalAlignment(JLabel.CENTER);

        quantizedImageInfoLabel = new JLabel();
        quantizedImageInfoLabel.setHorizontalAlignment(JLabel.CENTER);

        quantizedImageInfoLabel2 = new JLabel();
        quantizedImageInfoLabel2.setHorizontalAlignment(JLabel.CENTER);

        quantizedImageInfoLabel3 = new JLabel();
        quantizedImageInfoLabel3.setHorizontalAlignment(JLabel.CENTER);

        IndexedImageInfoLabel = new JLabel();
        IndexedImageInfoLabel.setHorizontalAlignment(JLabel.CENTER);

        IndexedImageInfoLabel2 = new JLabel();
        IndexedImageInfoLabel2.setHorizontalAlignment(JLabel.CENTER);

        IndexedImageInfoLabel3 = new JLabel();
        IndexedImageInfoLabel3.setHorizontalAlignment(JLabel.CENTER);

        // Set the layout
        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        JPanel imagePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5); // Adjust the insets for spacing

        // Add the image labels and info labels to the panel
        imagePanel.add(originalImageLabel, gbc);
        gbc.gridx++;
        imagePanel.add(quantizedImageLabel, gbc);
        gbc.gridx++;
        imagePanel.add(IndexedImageLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        imagePanel.add(originalImageInfoLabel, gbc);
        gbc.gridx++;
        imagePanel.add(quantizedImageInfoLabel, gbc);
        gbc.gridx++;
        imagePanel.add(IndexedImageInfoLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        imagePanel.add(originalImageLabel2, gbc);
        gbc.gridx++;
        imagePanel.add(quantizedImageLabel2, gbc);
        gbc.gridx++;
        imagePanel.add(IndexedImageLabel2, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        imagePanel.add(originalImageInfoLabel2, gbc);
        gbc.gridx++;
        imagePanel.add(quantizedImageInfoLabel2, gbc);
        gbc.gridx++;
        imagePanel.add(IndexedImageInfoLabel2, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        imagePanel.add(originalImageLabel3, gbc);
        gbc.gridx++;
        imagePanel.add(quantizedImageLabel3, gbc);
        gbc.gridx++;
        imagePanel.add(IndexedImageLabel3, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        imagePanel.add(originalImageInfoLabel3, gbc);
        gbc.gridx++;
        imagePanel.add(quantizedImageInfoLabel3, gbc);
        gbc.gridx++;
        imagePanel.add(IndexedImageInfoLabel3, gbc);

        JScrollPane scrollPane = new JScrollPane(imagePanel);
        c.add(scrollPane, BorderLayout.CENTER);

        // Set the size and make the frame visible
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void A() {
        Icon icon = ImageSelector.imageLabel.getIcon();
        Image image = ((ImageIcon) icon).getImage();
        originalImageLabel.setIcon(new ImageIcon(resizeImage(image)));
        originalImageInfoLabel.setText("<html><body style='text-align:center'>Original Image1<br>Width: " + ImageSelector.imageLabel.getWidth() + "<br>Height: " + ImageSelector.imageLabel.getHeight() + "</body></html>");

        int k = 4;
        BufferedImage quantizedImage = ColorQuantization.performColorQuantization(ImageSelector.imageLabel, k);
        quantizedImageLabel.setIcon(new ImageIcon(resizeImage(quantizedImage)));
        quantizedImageInfoLabel.setText("<html><body style='text-align:center'>Quantization Image1<br>Width: " + quantizedImage.getWidth() + "<br>Height: " + quantizedImage.getHeight() + "</body></html>");

        BufferedImage indexedImage = IndexedImageAndSave.indexedImage(quantizedImage, "C:\\Users\\Mina Farhat\\Desktop\\A\\");
        IndexedImageLabel.setIcon(new ImageIcon(resizeImage(indexedImage)));
        IndexedImageInfoLabel.setText("<html><body style='text-align:center'>Indexed Image1<br>Width: " + indexedImage.getWidth() + "<br>Height: " + indexedImage.getHeight() + "</body></html>");

        Icon icon1 = ImageSelector.imageLabel.getIcon();
        Image image1 = ((ImageIcon) icon1).getImage();
        originalImageLabel2.setIcon(new ImageIcon(resizeImage(image1)));
        originalImageInfoLabel2.setText("<html><body style='text-align:center'>Original Image2<br>Width: " + ImageSelector.imageLabel.getWidth() + "<br>Height: " + ImageSelector.imageLabel.getHeight() + "</body></html>");

        int numColors = 64;
        Color[] colorMap = ColorQuantization2.createColorMap(numColors);
        BufferedImage outputPhoto = ColorQuantization2.quantizePhoto(ImageSelector.imageLabel, colorMap);
        quantizedImageLabel2.setIcon(new ImageIcon(resizeImage(outputPhoto)));
        quantizedImageInfoLabel2.setText("<html><body style='text-align:center'>Quantization Image2<br>Width: " + outputPhoto.getWidth() + "<br>Height: " + outputPhoto.getHeight() + "</body></html>");

        BufferedImage indexedImage1 = IndexedImageAndSave.indexedImage(outputPhoto, "C:\\Users\\Mina Farhat\\Desktop\\B\\");
        IndexedImageLabel2.setIcon(new ImageIcon(resizeImage(indexedImage1)));
        IndexedImageInfoLabel2.setText("<html><body style='text-align:center'>Indexed Image2<br>Width: " + indexedImage1.getWidth() + "<br>Height: " + indexedImage1.getHeight() + "</body></html>");

        Icon icon2 = ImageSelector.imageLabel.getIcon();
        Image image2 = ((ImageIcon) icon2).getImage();
        originalImageLabel3.setIcon(new ImageIcon(resizeImage(image2)));
        originalImageInfoLabel3.setText("<html><body style='text-align:center'>Original Image3<br>Width: " + ImageSelector.imageLabel.getWidth() + "<br>Height: " + ImageSelector.imageLabel.getHeight() + "</body></html>");

        int numColors2 = 64;
        BufferedImage outputPhoto2 = ColorQuantization3.quantize(ImageSelector.imageLabel, numColors2);
        quantizedImageLabel3.setIcon(new ImageIcon(resizeImage(outputPhoto2)));
        quantizedImageInfoLabel3.setText("<html><body style='text-align:center'>Quantization Image3<br>Width: " + outputPhoto2.getWidth() + "<br>Height: " + outputPhoto2.getHeight() + "</body></html>");


        BufferedImage indexedImage2 = IndexedImageAndSave.indexedImage(outputPhoto2, "C:\\Users\\Mina Farhat\\Desktop\\C\\");
        IndexedImageLabel3.setIcon(new ImageIcon(resizeImage(indexedImage2)));
        IndexedImageInfoLabel3.setText("<html><body style='text-align:center'>Indexed Image3<br>Width: " + indexedImage2.getWidth() + "<br>Height: " + indexedImage2.getHeight() + "</body></html>");

        new ColorPaletteExtractor("Quantized Image1", quantizedImage);
        new ColorPaletteExtractor("Quantized Image2", outputPhoto);
        new ColorPaletteExtractor("Quantized Image3", outputPhoto2);

        ColorHistogramGUI gui = new ColorHistogramGUI("Quantized Image1");
        gui.setVisible(true);
        gui.processImage(quantizedImage);
        ColorHistogramGUI gui2 = new ColorHistogramGUI("Quantized Image2");
        gui2.setVisible(true);
        gui2.processImage(outputPhoto);
        ColorHistogramGUI gui3 = new ColorHistogramGUI("Quantized Image3");
        gui3.setVisible(true);
        gui3.processImage(outputPhoto2);

        Font infoFont = originalImageInfoLabel.getFont();
        Font smallerInfoFont = infoFont.deriveFont(infoFont.getSize() - 1f);
        originalImageInfoLabel.setFont(smallerInfoFont);
        quantizedImageInfoLabel.setFont(smallerInfoFont);
        IndexedImageInfoLabel.setFont(smallerInfoFont);
        originalImageInfoLabel2.setFont(smallerInfoFont);
        quantizedImageInfoLabel2.setFont(smallerInfoFont);
        IndexedImageInfoLabel2.setFont(smallerInfoFont);
        originalImageInfoLabel3.setFont(smallerInfoFont);
        quantizedImageInfoLabel3.setFont(smallerInfoFont);
        IndexedImageInfoLabel3.setFont(smallerInfoFont);
    }


    // Helper method to resize images
    private Image resizeImage(Image image) {
        BufferedImage resizedImage = new BufferedImage(400, 300, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(image, 0, 0, 400, 300, null);
        g2d.dispose();
        return resizedImage;
    }
}
