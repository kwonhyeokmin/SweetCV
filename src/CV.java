

public class CV implements Contour, Vizualize{
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
    public void drawImage(float[][] image) {

    }
}
