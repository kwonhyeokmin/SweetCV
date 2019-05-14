import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Test {
    public static void main(String[] args) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("C:\\Users\\hykwo\\IdeaProjects\\dcamp\\SweetCV\\sample.jpg"));

            JFrame frame = new JFrame("Test image");

        } catch (IOException e) {
            throw new RuntimeException("File path is invalid");
        }
    }
}
