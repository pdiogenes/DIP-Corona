package processing;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Pedro
 */
public class Threshold {
    // using OPENCV
    public static Mat threshold(Mat mat) {
        Mat dst = new Mat();
        Imgproc.threshold(mat, dst, 140, 255, Imgproc.THRESH_BINARY);
        return dst;

    }

    // no OPENCV
    public static BufferedImage threshold(BufferedImage original) {
        int width = original.getWidth();
        int height = original.getHeight();

        // creates a new image
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
        // gets a writeableraster for the original image and the new image
        WritableRaster imgLimiar = img.getRaster();
        WritableRaster imgOriginal = original.getRaster();

        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                int[] p = new int[1];
                // gets the pixel grayscale value
                imgOriginal.getPixel(w, h, p);
                if (p[0] > 120) {
                    imgLimiar.setSample(w, h, 0, 1);
                } else {
                    imgLimiar.setSample(w, h, 0, 0);
                }
            }
        }
        return img;
    }

}