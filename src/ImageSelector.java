import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
public class ImageSelector extends JFrame implements ActionListener {
    private final JButton selectImageButton;
    public static JLabel imageLabel;

    public ImageSelector() {
        super("Image Selector");
        selectImageButton = new JButton("Select Image");
        selectImageButton.addActionListener(this);
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add(selectImageButton, BorderLayout.NORTH);
        c.add(imageLabel, BorderLayout.CENTER);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // handle button click
        if (e.getSource() == selectImageButton) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                ImageIcon icon = new ImageIcon(selectedFile.getPath());
                imageLabel.setIcon(icon);
                    Print print = new Print();
                    print.A();
                dispose();
            }
        }
    }

}

