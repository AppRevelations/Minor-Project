import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


public class GetDCT {
	public static void main(String args[]) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		// Read image
		Mat img = Imgcodecs.imread("E:\\opencv\\cic.jpg", Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
		 
		// Make sure the both image dimensions are a multiple of 2
		Mat img2 = new Mat();
		int w = img.cols();
		int h = img.rows();
		int w2,h2;
		if (w % 2 == 0)
		    w2 = w;
		else
		    w2 = w+1;
		if (h % 2 == 0)
		    h2 = h;
		else
		    h2 = h+1;
		
		//Core.copyMakeBorder(img, img2, 0, h2-h, 0, w2-w, IPL_BORDER_REPLICATE);
		Core.copyMakeBorder(img, img2, 0, h2-h, 0, w2-w, 4);
		 
		// Grayscale image is 8bits per pixel,
		// but dct() method wants float values!
		Mat img3 = new Mat(img2.rows(), img2.cols(), CvType.CV_64F);
		img2.convertTo(img3, CvType.CV_64F);
		Imgcodecs.imwrite("E:\\opencv\\orig.jpg", img3);
		 
		// Let's do the DCT now: image => frequencies
		Mat freq = new Mat();
		Core.dct(img3, freq);
		 
		// Save a visualization of the DCT coefficients
		Imgcodecs.imwrite("E:\\opencv\\temp.jpg", freq);
		 
		/*for (int Start=100; Start>0; Start-=1)
		{
		    // Set some frequencies to 0
		    for (int y=Start; y<freq.rows(); y++)
		    {
		        for (int x=Start; x<freq.cols(); x++)
		        {
		            //freq.at<double>(y,x) = 0.0;
		            freq.put(y, x, 0.0);
		        }
		    }
		 
		    // Do inverse DCT: (some low) frequencies => image
		    Mat dst;
		    Core.idct(freq, dst);
		 
		    // Show frame nr
		    char txt[100];
		    sprintf(txt, "%04d", Start);
		    cv::putText( dst, txt, Point(10,20),
		     CV_FONT_HERSHEY_SIMPLEX, 0.5, CV_RGB(0,0,0) );
		 
		    // Save visualization of reconstructed image where
		    //we have thrown away some of the high frequencies
		    char fname[500];
		    sprintf(fname, "W:\\tmp\\idct_%04d.png", Start);
		    Imgcodecs.imwrite(fname, dst);
		}*/
	}
}
