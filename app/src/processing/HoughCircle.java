
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class HoughCircle {

    public static Mat circleTransform(Mat mat) {

        Mat src = mat;

        Mat circles = new Mat();

        
        Imgproc.HoughCircles(mat, circles, Imgproc.HOUGH_GRADIENT, 1.0,
        (double)mat.rows()/32, // change this value to detect circles with different distances to each other
        100.0, 30.0, 60, 1150); // change the last two parameters
        // (min_radius & max_radius) to detect larger circles

        for (int x = 0; x < circles.cols(); x++) {

            double[] c = circles.get(0, x);
            Point center = new Point(Math.round(c[0]), Math.round(c[1]));
            // circle center
            Imgproc.circle(src, center, 1, new Scalar(0, 100, 100), 3, 8, 0);
            // circle outline
            int radius = (int) Math.round(c[2]);
            Imgproc.circle(src, center, radius, new Scalar(255, 0, 255), 3, 8, 0);
        }

        return src;
    }
    

}