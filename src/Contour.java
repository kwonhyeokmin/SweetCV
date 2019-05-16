public interface Contour {
    int[][] findContours(int[][] image);
    int[][] approxPolyDP(int[][] curve, float epsilon);
}
