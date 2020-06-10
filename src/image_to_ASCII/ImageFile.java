package image_to_ASCII;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageFile {
	
	private File imgFile;
	private BufferedImage img;
	
	private int height;
	private int width;
	
	private double scale = 1;
	private boolean resized = false;

	public ImageFile(String filename) {
		try {
			this.imgFile = new File(filename);
			this.img = ImageIO.read(this.imgFile);
			
			this.height = img.getHeight();
			this.width = img.getWidth();
		} catch(IOException e) {
			System.out.println("An error has occured");
			e.printStackTrace();
		}
	}
	
	public ImageFile(String filename, double scale) {
		
		try {
			this.imgFile = new File(filename);
			this.scale = scale;
			
			BufferedImage temp = ImageIO.read(this.imgFile);
			this.img = resize(temp, scale);
			if(scale != 1)
				this.resized = true;
			
			this.height = this.img.getHeight();
			this.width = this.img.getWidth();
		} catch(IOException e) {
			System.out.println("An error has occured");
			e.printStackTrace();
		}
	}
	
	public ImageFile(File imgFile) {
		try {
			this.imgFile = imgFile;
			this.img = ImageIO.read(this.imgFile);
			
			this.height = img.getHeight();
			this.width = img.getWidth();
		} catch(IOException e) {
			System.out.println("An error has occured");
			e.printStackTrace();
		}
	}
	
	public ImageFile(File imgFile, double scale) {
		
		try {
			this.imgFile = imgFile;
			this.scale = scale;
			
			BufferedImage temp = ImageIO.read(this.imgFile);
			this.img = resize(temp, scale);
			if(scale != 1)
				this.resized = true;
			
			this.height = this.img.getHeight();
			this.width = this.img.getWidth();
		} catch(IOException e) {
			System.out.println("An error has occured");
			e.printStackTrace();
		}
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	
	public File getFile() {
		return imgFile;
	}
	
	// for debug purposes
	public void showInfo() {
		System.out.println(
				"File name: "+this.getFile().getName()+ 
				"\nHeight: "+this.height+ 
				"\nWidth: "+this.width+ 
				"\nScale: "+this.scale+ 
				"\nResized: "+this.resized);
	}
	
	// adapted from https://stackoverflow.com/questions/6524196/java-get-pixel-array-from-image
	public int[][] getPixels() {
		
		final byte[] pixels = ((DataBufferByte) this.img.getRaster().getDataBuffer()).getData();
		final boolean hasAlphaChannel = this.img.getAlphaRaster() != null;
		
		int[][] result = new int[this.height][this.width];
		
		if (hasAlphaChannel) {
	         final int pixelLength = 4;
	         for (int pixel = 0, row = 0, col = 0; pixel + 3 < pixels.length; pixel += pixelLength) {
	            int argb = 0;
	            argb += (((int) pixels[pixel] & 0xff) << 24); 		// alpha
	            argb += ((int) pixels[pixel + 1] & 0xff); 			// blue
	            argb += (((int) pixels[pixel + 2] & 0xff) << 8); 	// green
	            argb += (((int) pixels[pixel + 3] & 0xff) << 16); 	// red
	            
	            Color c = new Color(argb, true);
	            if(c.getAlpha() > 0) {
	            	result[row][col] = (c.getRed()+c.getBlue()+c.getGreen()+c.getAlpha())/4;
	            } else {
	            	result[row][col] = -1;
	            }
	            
	            col++;
	            if (col == width) {
	               col = 0;
	               row++;
	            }
	         }
	    } else {
	         final int pixelLength = 3;
	         for (int pixel = 0, row = 0, col = 0; pixel + 2 < pixels.length; pixel += pixelLength) {
	            int argb = 0;
	            argb += -16777216; 									// 255 alpha
	            argb += ((int) pixels[pixel] & 0xff); 				// blue
	            argb += (((int) pixels[pixel + 1] & 0xff) << 8); 	// green
	            argb += (((int) pixels[pixel + 2] & 0xff) << 16);	// red
	            
	            Color c = new Color(argb);
	            result[row][col] = (c.getRed()+c.getBlue()+c.getGreen())/3;
	            
	            col++;
	            if (col == width) {
	               col = 0;
	               row++;
	            }
	         }
	    }
		return result;
	}
	
	// adapted from https://stackoverflow.com/questions/9417356/bufferedimage-resize
	private static BufferedImage resize(BufferedImage img, double scale) {
		
		int newW = (int)(img.getWidth()*scale);
		int newH = (int)(img.getHeight()*scale);
		
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, (img.getTransparency() == Transparency.OPAQUE ? BufferedImage.TYPE_3BYTE_BGR : BufferedImage.TYPE_4BYTE_ABGR));

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	}
}
