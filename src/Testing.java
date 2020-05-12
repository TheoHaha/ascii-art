// Project idea and walkthrough by https://robertheaton.com/2018/06/12/programming-projects-for-advanced-beginners-ascii-art/ (great guy)
// TODO: Expand upon the project, GUI(?)

import image_to_ASCII.*;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Testing {
	
	private static final String[] imageExts = {
		"gif","jpeg","jpg","png","tif","tiff"
	};
	
	public static void main(String[] args) {
		final JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileNameExtensionFilter("All images", imageExts));
		for(String s : imageExts) {
			fc.addChoosableFileFilter(new FileNameExtensionFilter("."+s, s));
		}
		
		@SuppressWarnings("unused")
		int returnVal = fc.showOpenDialog(new JPanel());
		
		File file;
		
		if(fc.getSelectedFile() != null) {
			file = fc.getSelectedFile();
			
			ImageFile image1 = new ImageFile(file);
			System.out.println(image1.getFileName());
			
			fc.resetChoosableFileFilters();
			fc.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
			returnVal = fc.showSaveDialog(new JPanel());
			
			if(fc.getSelectedFile() != null) {
				FileResult result1 = new FileResult(fc.getSelectedFile());
				result1.printResult(image1);
			}
		}
	}
}
