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
	
	private File selectedFile;
	private File resultFile;
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
		selectedFile = loadFC.showOpenDialog(null);
		
		if(selectedFile != null) {

			ImageFile selectedImage = new ImageFile(selectedFile);
			selectedImage.showInfo();
			
			loadTxtField.setText(selectedFile.getAbsolutePath());
			
			filenameLabel.setText("Filename: "+selectedImage.getFile().getName());
			widthLabel.setText("Width: "+selectedImage.getWidth());
			heightLabel.setText("Height: "+selectedImage.getHeight());
		}
	}
	
	public void saveAsTxt(ActionEvent event) {
		FileChooser saveFC = new FileChooser();

		scale = scaleSpinner.getValue();
		
		if (defaultRadio.isSelected()) {
			printmode = ResultFile.PRINTMODE_DEFAULT;
			System.out.println("default mode");
		} else if (invertedRadio.isSelected()) {
			printmode = ResultFile.PRINTMODE_INVERTED;
			System.out.println("default mode");
		} else if (whitespacesRadio.isSelected()) {
			printmode = ResultFile.PRINTMODE_WHITESPACES;
			System.out.println("whitespace mode");
		}
	}
	
	public void setDefaultMode(ActionEvent event) {
		printmode = ResultFile.PRINTMODE_DEFAULT;
		System.out.println("default mode");
	}
	
	public void setInvertedMode(ActionEvent event) {
		printmode = ResultFile.PRINTMODE_INVERTED;
		System.out.println("inverto mode");
	}
	
	public void setWhitespacesMode(ActionEvent event) {
		printmode = ResultFile.PRINTMODE_WHITESPACES;
		System.out.println("whitespace mode");
	}
}
