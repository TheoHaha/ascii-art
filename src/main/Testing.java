// Project idea and walkthrough by https://robertheaton.com/2018/06/12/programming-projects-for-advanced-beginners-ascii-art/ (great guy)
// TODO: Expand upon the project, GUI(?)
package main;

import image_to_ASCII.*;

public class Testing {

	public static void main(String[] args) {
		
		ImageFile image1 = new ImageFile("resources/images/pogchamp.jpg");
		ImageFile image2 = new ImageFile("resources/images/pogchamp-sm.jpg", 2);
		
		int[][] pix1 = image1.getPixels();
		int[][] pix2 = image2.getPixels();
		
		image1.showInfo();
		FileResult n1 = new FileResult("resources/results/test-1.txt");
		n1.printResult(pix1, FileResult.PRINTMODE_INVERTED);
		
		image2.showInfo();
		FileResult n2 = new FileResult("resources/results/test-2.txt");
		n2.printResult(pix2, FileResult.PRINTMODE_INVERTED);
	}
}
