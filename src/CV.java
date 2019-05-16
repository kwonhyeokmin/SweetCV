import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CV implements Contour, Vizualize{
    //field
    public final int IMREAD_COLOR = 0;
    public final int IMREAD_GRAYSCALE = 1;
    public final int IMREAD_UNCHANGED = 2;

    private int[] pixel2ARGB(int pixel) {
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;

        return new int[]{red, green, blue, alpha};
    }

    private int[] pixel2RGB(int pixel) {
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;

        return new int[]{red, green, blue};
    }

    private int[] pixel2GRAY(int pixel) {
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;

        return new int[]{(red+green+blue)/3};
    }

    /**
     * Method for image reading by array type
     * @param path image file path for read
     * @param op option
     *           (cv.IMREAD_COLOR: read by RGB type,
     *           cv.IMREAD_GRAY: read by gray scale type,
     *           cv.IMREAD_UNCHANGED: read by ARGB type)
     * @return 3d-array type of image
     *         null if there is some error that not predict
     */
    public int[][][] imread(String path, int op) {
        int[] color;
        try {
            BufferedImage image = ImageIO.read(new File(path));
            int width = image.getWidth();
            int height = image.getHeight();

            int[][] red = new int[height][width];
            int[][] green = new int[height][width];
            int[][] blue = new int[height][width];
            int[][] gray = new int[height][width];
            int[][] alpha = new int[height][width];

            switch (op) {
                case 0: //RGB color image
                    for (int i = 0; i < height; i++) {
                        for (int j = 0; j < width; j++) {
                            int pixel = image.getRGB(j, i);
                            color = pixel2RGB(pixel);
                            red[j][i] = color[0];
                            green[j][i] = color[1];
                            blue[j][i] = color[2];
                        }
                    }
                    return new int[][][]{red, green, blue};

                case 1: //Gray scale image
                    for (int i = 0; i < height; i++) {
                        for (int j = 0; j < width; j++) {
                            int pixel = image.getRGB(j, i);
                            color = pixel2GRAY(pixel);
                            gray[j][i] = color[0];
                        }
                    }
                    return new int[][][]{gray};

                case 2: //RGBA image
                    for (int i = 0; i < height; i++) {
                        for (int j = 0; j < width; j++) {
                            int pixel = image.getRGB(j, i);
                            color = pixel2ARGB(pixel);
                            alpha[j][i] = color[0];
                            red[j][i] = color[1];
                            green[j][i] = color[2];
                            blue[j][i] = color[3];
                        }
                    }
                    return new int[][][]{alpha, red, green, blue};
                default:
                    throw new IllegalArgumentException("The option is not valid. Check the option argument value is 0 or 1 or 2");
            }
        } catch (IOException e) {
            Logger.getLogger(CV.class.getName()).log(Level.SEVERE, "File path is not valid. Please check the right path", e);
        } catch (IllegalArgumentException e) {
            Logger.getLogger(CV.class.getName()).log(Level.SEVERE, "The option is not valid. Check the option argument value is 0 or 1 or 2", e);
        }
        return null;
    }

    /**
     * Find contour points using Sobel Algorithm.
     * @param image Image for find contour.
     * @return Contour points type of 2-dimension integer type array.
     */
    @Override
    public int[][] findContours(float[][] image) {

        return new int[0][];
    }

    /**
     * Reduce called contour approximation task.
     * @param curve Contour points type of 2-dimension integer array.
     * @param epsilon epsilon value.
     * @return Reduced points type of 2-dimension integer array.
     */
    @Override
    public int[][] approxPolyDP(int[][] curve, float epsilon) {
        return new int[0][];
    }

    /**
     * Draw image in window.
     * @param image 3-dimension array type image for drawing.
     */
    @Override
    public void drawImage(int[][][] image) {
        int chanel = image.length;
        int height = image[0].length;
        int width = image[0][0].length;
        int[][] red;
        int[][] green;
        int[][] blue;

        if (chanel==1) {
            red = image[0];
            green = image[0];
            blue = image[0];
        }
        else if (chanel==3){
            red = image[0];
            green = image[1];
            blue = image[2];
        }
        else {
            throw new ValueException("Image chanel value is not valid");
        }

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D)bufferedImage.getGraphics();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                g.setColor(new Color(red[i][j], green[i][j], blue[i][j]));
                g.fillRect(i, j, 1, 1);
            }
        }

        JFrame frame = new JFrame("Image test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D)g;
                g2d.clearRect(0, 0, getWidth(), getHeight());
                g2d.drawImage(bufferedImage, 0, 0, this);
            }
        };
        panel.setPreferredSize(new Dimension(width, height));
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
