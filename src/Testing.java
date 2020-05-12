// Project idea and walkthrough by https://robertheaton.com/2018/06/12/programming-projects-for-advanced-beginners-ascii-art/ (great guy)
// TODO: Expand upon the project, GUI(?)

import image_to_ASCII.*;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class Testing {
	
	public static void main(String[] args) {
		//TODO: Implement a JFileChooser that opens and make it work with ImageFile
		//TODO: Implement a JFileChooser that saves and make it work with FileResult
		
		final JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(new JPanel());
		
		File file = fc.getSelectedFile();
		
		ImageFile image1 = new ImageFile(file);
		System.out.println(image1.getFileName());
		
		final JFileChooser fc2 = new JFileChooser();
		int returnVal2 = fc.showSaveDialog(new JPanel());
		
		FileResult result1 = new FileResult(fc2.getSelectedFile());
	}
}
