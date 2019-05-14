public interface Contour {
    public int[][] findContours(float[][] image);
    public int[][] approxPolyDP(int[][] curve, float epsilon);
}
