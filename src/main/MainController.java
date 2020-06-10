package main;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import image_to_ASCII.*;

public class MainController {
	// TODO: Dialog boxes for confirmation and errors
	
	@FXML private TextField loadTxtField;
	@FXML private Button loadBtn;
	
	@FXML private Spinner<Double> scaleSpinner;
	private SpinnerValueFactory<Double> svf = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.1, 5, 1, 0.1);
	
	@FXML private ToggleGroup modeGroup;
	@FXML private RadioButton defaultRadio;
	@FXML private RadioButton invertedRadio;
	@FXML private RadioButton whitespacesRadio;
	
	@FXML private Label filenameLabel;
	@FXML private Label widthLabel;
	@FXML private Label heightLabel;
	
	@FXML private Button saveBtn;
	
	private File loadedFile;
	private File savedFile;
	private double scale;
	private int printmode;


	public void initialize() {
		scaleSpinner.setValueFactory(svf);
	}
	
	public void loadImage(ActionEvent event) {
		FileChooser loadFC = new FileChooser();
		loadFC.getExtensionFilters().addAll(
				new ExtensionFilter("Images", "*.png", "*.jpg", "*.tif"),
				new ExtensionFilter("PNG files", "*.png"),
				new ExtensionFilter("JPG files", "*.jpg"),
				new ExtensionFilter("TIF files", "*.tif"));
		loadedFile = loadFC.showOpenDialog(null);
		
		if (loadedFile != null) {
			ImageFile selectedImage = new ImageFile(loadedFile);
			
			loadTxtField.setText(selectedImage.getFile().getAbsolutePath());
			
			filenameLabel.setText("Filename: "+selectedImage.getFile().getName());
			widthLabel.setText("Width: "+selectedImage.getWidth());
			heightLabel.setText("Height: "+selectedImage.getHeight());
		}
	}
	
	public void saveAsTxt(ActionEvent event) {
		if (loadedFile != null) {
			FileChooser saveFC = new FileChooser();
			saveFC.getExtensionFilters().addAll(new ExtensionFilter("Text File", "*.txt"));

			scale = scaleSpinner.getValue();
			
			if (defaultRadio.isSelected()) {
				printmode = ResultFile.PRINTMODE_DEFAULT;
			} else if (invertedRadio.isSelected()) {
				printmode = ResultFile.PRINTMODE_INVERTED;
			} else if (whitespacesRadio.isSelected()) {
				printmode = ResultFile.PRINTMODE_WHITESPACES;
			}
			
			ImageFile imageFile = new ImageFile(loadedFile, scale);
			
			savedFile = saveFC.showSaveDialog(null);
			
			if (savedFile != null) {
				ResultFile resultFile = new ResultFile(imageFile, savedFile);
				resultFile.printResult(printmode);
				
				System.out.println("Saved successfully at "+savedFile.getAbsolutePath());
			}
		} else {
			System.out.println("Please load a file first!");
		}
	}
}
