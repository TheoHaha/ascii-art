// Project idea and walkthrough by https://robertheaton.com/2018/06/12/programming-projects-for-advanced-beginners-ascii-art/ (great guy)
// TODO: Expand upon the project, GUI(?)

import image_to_ASCII.*;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class Testing {
	
	public static void main(String[] args) {
		final JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(new JPanel());
		
		File file = fc.getSelectedFile();
		
		ImageFile image1 = new ImageFile(file);
		System.out.println(image1.getFileName());
		
		int returnVal2 = fc.showSaveDialog(new JPanel());
		
		FileResult result1 = new FileResult(fc.getSelectedFile());
		result1.printResult(image1);
	}
}
