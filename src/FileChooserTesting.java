// Project idea and walkthrough by https://robertheaton.com/2018/06/12/programming-projects-for-advanced-beginners-ascii-art/ (great guy)
// TODO: Expand upon the project, GUI(?)

import image_to_ASCII.*;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;

public class FileChooserTesting {
	
	// all the GUI elements
	private static JFileChooser fc;
	private static JButton loadBtn;
	private static JButton saveBtn;
	private static JButton unloadBtn;
	private static JLabel fileLabel;
	
	// stores the file that we've loaded using the JFileChooser
	private static ImageFile selectedImg = null;
	
	// all supported image extensions, can easily add more
	private static final String[] imageExts = {
		"gif","jpeg","jpg","png","tif","tiff"
	};
	private static final String[] jpgExts = { "jpeg","jpg" };
	private static final String[] tifExts = { "tif","tiff" };
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setBounds(0, 0, 275, 100);
		frame.setLayout(new FlowLayout());
		frame.setVisible(true);
		
		// the load button and its functionality
		loadBtn = new JButton("Load...");
		loadBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadImage();
				if(selectedImg != null) {
					saveBtn.setEnabled(true);
					fileLabel.setText("Loaded file: "+selectedImg.getFileName());
				}
			}
		});
		
		// the save button and its functionality
		saveBtn = new JButton("Save...");
		saveBtn.setEnabled(false);
		saveBtn.setToolTipText("Load an image first!");
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveResult(selectedImg);
			}
		});
		
		// the unload button and its functionality
		unloadBtn = new JButton("Unload");
		unloadBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				unloadImage();
			}
		});
		
		// a label that shows whether we have loaded a file or not
		fileLabel = new JLabel("Loaded file: <none>");
		
		// add everything to the frame
		frame.add(loadBtn);
		frame.add(saveBtn);
		frame.add(fileLabel);
		frame.add(unloadBtn);
	}
	
	// load the image with an OpenDialog
	public static void loadImage() {
		// initialize the file chooser
		fc = new JFileChooser();
		
		// set up the file filters
		fc.resetChoosableFileFilters();
		String s = "";
		for(String ext : imageExts) {
			//fc.addChoosableFileFilter(new FileNameExtensionFilter(ext.toUpperCase(), ext));
			if (ext != imageExts[imageExts.length - 1]) {
				s += "."+ext+", ";
			} else {
				s += "."+ext;
			}
		}
		fc.addChoosableFileFilter(new FileNameExtensionFilter("GIF", "gif"));
		fc.addChoosableFileFilter(new FileNameExtensionFilter("JPEG, JPG", jpgExts));
		fc.addChoosableFileFilter(new FileNameExtensionFilter("PNG", "png"));
		fc.addChoosableFileFilter(new FileNameExtensionFilter("TIFF, TIF", tifExts));
		fc.setFileFilter(new FileNameExtensionFilter("Images ("+s+")", imageExts));
		
		// open up the load window
		int returnVal = fc.showOpenDialog(new JPanel());
		
		// sets the selected file if the user chooses to load it
		if(fc.getSelectedFile() != null && returnVal == JFileChooser.APPROVE_OPTION) {
			selectedImg = new ImageFile(fc.getSelectedFile());
		}
	}
	
	// save the result as a .txt file with a SaveDialog
	public static void saveResult(ImageFile image) {
		// initialize the file chooser and create an ImageFile
		fc = new JFileChooser();
		
		// set up the file filters
		fc.resetChoosableFileFilters();
		fc.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
		
		// open up the save window
		int returnVal = fc.showSaveDialog(new JPanel());
		
		// save the result to the specified .txt file
		if(fc.getSelectedFile() != null && returnVal == JFileChooser.APPROVE_OPTION) {
			FileResult result1 = new FileResult(fc.getSelectedFile().getPath()+".txt");				
			result1.printResult(image.scale(0.5));
		}
	}
	
	// unload any currently loaded image
	public static void unloadImage() {
		// check if we got an image loaded, can't unload if we haven't loaded
		if(selectedImg != null) {
			selectedImg = null;
			saveBtn.setEnabled(false);
			fileLabel.setText("Loaded file: <none>");
		}
	}
}
