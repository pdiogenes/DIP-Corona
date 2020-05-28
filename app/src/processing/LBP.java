package processing;

import hist.Histogram;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

public class LBP {

    public static Mat calcLBP (Mat mat) {

        Mat matjrene = mat.clone();
        Mat dstjrene = new Mat(mat.height(), mat.width(), mat.type(), new Scalar(0, 0, 0));
        
        int valorPixel = 0;

        int limiar;
        int rows = matjrene.height();
        int col = matjrene.width();
        for (int x = 1; x < rows - 1; x++) {
            for (int y = 1; y < col - 1; y++) {
                valorPixel = 0;
                limiar =  (int) matjrene.get(x, y)[0]; 
                valorPixel += (matjrene.get(x -1, y)[0] <= limiar) ? 0 : 1;
                valorPixel += (matjrene.get(x -1, y+1)[0] <= limiar) ? 0 : 2;
                valorPixel += (matjrene.get(x, y + 1)[0] <= limiar) ? 0 : 4;
                valorPixel += (matjrene.get(x  + 1, y + 1)[0] <= limiar) ? 0 : 8;
                valorPixel += (matjrene.get(x  + 1, y)[0] <= limiar) ? 0 : 16;
                valorPixel += (matjrene.get(x  -1, y -1 )[0] <= limiar) ? 0 : 32;
                valorPixel += (matjrene.get(x  , y - 1)[0] <= limiar) ? 0 : 64;
                valorPixel += (matjrene.get(x -1 , y -1 )[0] <= limiar) ? 0 : 128;
                dstjrene.put(x, y, valorPixel & 0xFF );
            }
        }
        
        Histogram hist = new Histogram(dstjrene);
        return hist.getHist();
    }
}