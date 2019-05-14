import javax.imageio.ImageIO;
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
     * Draw image window.
     * @param image Image for drawing.
     */
    @Override
    public void drawImage(int[][][] image) {

    }
}
