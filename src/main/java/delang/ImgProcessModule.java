package delang;
import java.util.ArrayList;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import com.googlecode.javacv.cpp.opencv_imgproc;
import com.googlecode.javacv.cpp.opencv_core.IplImage;


public class ImgProcessModule {
	public void Mosaic(IplImage image, int mon)
	{
	    int index;
	    int i, j, n, m;

	    int nCount;
	    int monWidth = mon, monHeight = mon;

	    int R, G, B;

	    for (i=0; i<image.height(); i+=mon) {
	        for (j=0; j<image.width(); j+=mon) {
	            nCount = 0;
	            B = 0; G = 0; R = 0;

	            //���� �� ó��
	            if ((i+mon) > image.height())
	                monHeight = image.height() % mon;
	            else
	                monHeight = mon;

	            if ((j+mon) > image.width())
	                monWidth = image.width() % mon;
	            else
	                monWidth = mon;

	            //B,G,R������ ��� ����
	            for (n=0; n<monHeight; n++) {
	                for (m=0; m<monWidth; m++) {
	                    index = (3*(j+m))+((i+n)*image.widthStep());

	                    /*
	                    B += (unsigned char)image->imageData[index];
	                    G += (unsigned char)image->imageData[index+1];
	                    R += (unsigned char)image->imageData[index+2];
	                    */
	                    
	                    nCount++;
	                }
	            }

	            //����� ����
	            B /= nCount;
	            G /= nCount;
	            R /= nCount;

	            //���
	            for (n=0; n<monHeight; n++) {
	                for (m=0; m<monWidth; m++) {
	                    index = (3*(j+m))+((i+n)*image.widthStep());

	                    /*
	                    image->imageData[index] = (unsigned char)B;
	                    image->imageData[index+1] = (unsigned char)G;
	                    image->imageData[index+2] = (unsigned char)R;
	                    */
	                }
	            }

	        }
	    }
	}
	
	public void Saturation(IplImage img)
	{
		opencv_imgproc.cvCvtColor(img, img, opencv_imgproc.CV_BGR2HSV);    

		for (int i=0; i < img.width() ; i++)
		{
		      for(int j=0; j < img.height(); j++)
		      {
		            int idx = 1;
		            //img.at<cv::Vec3b>(i,j)[idx] = new_value;
		      }
		}

		opencv_imgproc.cvCvtColor(img, img, opencv_imgproc.CV_HSV2BGR);
	}
}
