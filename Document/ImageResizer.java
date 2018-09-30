
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties; 
import javax.imageio.ImageIO;
 
/**
 * This program demonstrates how to resize an image.
 *
 * @author Dudi Venkata Raghava
 *
 */
public class ImageResizer {
 
	private static BufferedImage inputImage = null;
	private static Properties prop = new Properties();
	private static InputStream input = null;
	
    /**
     * Resizes an image to a absolute width and height (the image may not be
     * proportional)
     * @param inputImage of the original image	 
     * @param inputImagePath Path of the original image
     * @param outputImagePath Path to save the resized image
     * @param scaledWidth absolute width in pixels
     * @param scaledHeight absolute height in pixels
     * @throws IOException
     */
    public static void resize(BufferedImage inputImage, String outputImagePath, int scaledWidth, int scaledHeight)
            throws IOException {
		
        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth,scaledHeight, inputImage.getType());
 
        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
 
        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1);
 
        // writes to output file
        ImageIO.write(outputImage, formatName, new File(outputImagePath));
    }

 
    /**
     * Test resizing images
     */
    public static void main(String[] args) {
 
        try {
            
			// Read Properties files
			input = new FileInputStream("config.properties");
			
			// load a properties file
			prop.load(input);
			
			String imageFolderLocation = prop.getProperty("imagesLocation");
			
            File[] files = new File(imageFolderLocation).listFiles();
			int minScaledWidth = Integer.valueOf(prop.getProperty("minimumHorizantal"));
            int minScaledHeight = Integer.valueOf(prop.getProperty("minimumVertical"));
			            
			for(File inputFile : files){

				// reads input image
				inputImage = ImageIO.read(inputFile);
				int currentImageWidth   = inputImage.getWidth();
				int currentImageHeight  = inputImage.getHeight();	
		
				if((currentImageWidth<minScaledWidth) || (currentImageHeight<minScaledHeight)){
					ImageResizer.resize(inputImage, inputFile.getAbsolutePath().replace(".jpg","_Modified.jpg"), minScaledWidth,  minScaledHeight);
				}
				
			
			}
 
        } catch (IOException ex) {
            System.out.println("Error resizing the image.");
            ex.printStackTrace();
        }
    }
 
}