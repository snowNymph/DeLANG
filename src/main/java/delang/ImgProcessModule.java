package delang;
import java.util.ArrayList;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import com.googlecode.javacv.cpp.opencv_imgproc;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

import java.nio.ByteBuffer;

public class ImgProcessModule {
	public void Mosaic(IplImage image, int mon)
	{
	    int index;
	    int i, j, n, m;

	    int nCount;
	    int monWidth = mon, monHeight = mon;

        ByteBuffer buffer = image.getByteBuffer();
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
                        B += buffer.get(index) & 0xFF;
                        G += buffer.get(index+1) & 0xFF;
                        R += buffer.get(index+2) & 0xFF;

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
                        buffer.put(index, (byte) B);
                        buffer.put(index+1, (byte) G);
                        buffer.put(index+2, (byte) R);
	                }
	            }

	        }
	    }
	}
	
	public void Saturation(IplImage img)
	{
        ByteBuffer buffer = img.getByteBuffer();
        opencv_imgproc.cvCvtColor(img, img, opencv_imgproc.CV_BGR2HSV);
		for (int i=0; i < img.height() ; i++)
		{
		      for(int j=0; j < img.width(); j++)
		      {
                  int index = i * img.widthStep() + j * img.nChannels();
                  buffer.put(index+1, (byte) (buffer.get(index+1) & 0xFF + 10));
		      }
		}

		opencv_imgproc.cvCvtColor(img, img, opencv_imgproc.CV_HSV2BGR);
	}
}
