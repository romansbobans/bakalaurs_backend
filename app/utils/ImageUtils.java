package utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ImageUtils {
 
	private static final int IMG_WIDTH = 150;
	private static final int IMG_HEIGHT = 100;
 

 
    public static BufferedImage resizeImage(BufferedImage originalImage){
    	

    	int origWidth = originalImage.getWidth();
    	Double factor = (double) (origWidth/IMG_WIDTH);
    	int height = (int) (originalImage.getHeight()/factor);
		int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
	BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, height, type);
	Graphics2D g = resizedImage.createGraphics();
	g.drawImage(originalImage, 0, 0, IMG_WIDTH, height, null);
	g.dispose();
 
	return resizedImage;
    }

}