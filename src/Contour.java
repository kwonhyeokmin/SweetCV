public interface Contour {
    int[][] findContours(float[][] image);
    int[][] approxPolyDP(int[][] curve, float epsilon);
}
