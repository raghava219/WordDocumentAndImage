
//https://www.codejava.net/java-se/graphics/how-to-resize-images-in-java

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
 
import javax.imageio.ImageIO;
 
/**
 * This program demonstrates how to resize an image.
 *
 * @author www.codejava.net
 *
 */
public class ImageResizer {
 
    /**
     * Resizes an image to a absolute width and height (the image may not be
     * proportional)
     * @param inputImagePath Path of the original image
     * @param outputImagePath Path to save the resized image
     * @param scaledWidth absolute width in pixels
     * @param scaledHeight absolute height in pixels
     * @throws IOException
     */
    public static void resize(String inputImagePath,
            String outputImagePath, int scaledWidth, int scaledHeight)
            throws IOException {
        // reads input image
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
 
        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, inputImage.getType());
 
        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
 
        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath
                .lastIndexOf(".") + 1);
 
        // writes to output file
        ImageIO.write(outputImage, formatName, new File(outputImagePath));
    }
 
    /**
     * Resizes an image by a percentage of original size (proportional).
     * @param inputImagePath Path of the original image
     * @param outputImagePath Path to save the resized image
     * @param percent a double number specifies percentage of the output image
     * over the input image.
     * @throws IOException
     */
    public static void resize(String inputImagePath,
            String outputImagePath, double percent) throws IOException {
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
        int scaledWidth = (int) (inputImage.getWidth() * percent);
        int scaledHeight = (int) (inputImage.getHeight() * percent);
        resize(inputImagePath, outputImagePath, scaledWidth, scaledHeight);
    }
 
    /**
     * Test resizing images
     */
    public static void main(String[] args) {
		File[] files = new File("./Images").listFiles();
        String inputImagePath = "./Images/Raghava.jpg";
        String outputImagePath1 = "./Images/Raghava_Fixed.jpg";
        String outputImagePath2 = "./Images/Raghava_Smaller.jpg";
        String outputImagePath3 = "./Images/Raghava_Bigger.jpg";
 
        try {
            // resize to a fixed width (not proportional)
            int scaledWidth = 1024;
            int scaledHeight = 768;
			
			// resize smaller by 50%
            double percent1 = 0.5;
            
			// resize bigger by 50%
            double percent2 = 1.5;
            
			for(File file : files){
				//ImageResizer.resize(inputImagePath, outputImagePath1, scaledWidth, scaledHeight);
				ImageResizer.resize(file.getAbsolutePath(), file.getAbsolutePath().replace(".jpg","_Fixed.jpg"), scaledWidth, scaledHeight);
				//ImageResizer.resize(inputImagePath, outputImagePath2, percent);
				ImageResizer.resize(file.getAbsolutePath(), file.getAbsolutePath().replace(".jpg","_Smaller.jpg"), percent1);
				//ImageResizer.resize(inputImagePath, outputImagePath3, percent);
				ImageResizer.resize(file.getAbsolutePath(), file.getAbsolutePath().replace(".jpg","_Bigger.jpg"), percent2);
			}
 
        } catch (IOException ex) {
            System.out.println("Error resizing the image.");
            ex.printStackTrace();
        }
    }
 
}