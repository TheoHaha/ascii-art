package main;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
	
	Alert fileMissingAlert = new Alert(AlertType.WARNING);
	Alert successAlert = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
	FileChooser fc = new FileChooser();
	
	private File loadedFile;
	private File savedFile;
	private double scale;
	private int printmode;

	public void initialize() {
		// set the scale spinner's limits
		scaleSpinner.setValueFactory(svf);

		// set all necessary stuff for the file missing alert
		fileMissingAlert.setHeaderText(null);
		fileMissingAlert.setContentText("Please load a file first!");
		fileMissingAlert.setTitle("Warning!");
		
		// set all necessary stuff for the success alert
		successAlert.setHeaderText(null);
		successAlert.setTitle("Success!");
	}
	
	public void loadImage(ActionEvent event) {
		fc.getExtensionFilters().clear();
		fc.getExtensionFilters().addAll(
				new ExtensionFilter("Images", "*.png", "*.jpg", "*.tif"),
				new ExtensionFilter("PNG files", "*.png"),
				new ExtensionFilter("JPG files", "*.jpg"),
				new ExtensionFilter("TIF files", "*.tif"));
		loadedFile = fc.showOpenDialog(null);
		
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
			fc.getExtensionFilters().clear();
			fc.getExtensionFilters().addAll(new ExtensionFilter("Text File", "*.txt"));

			scale = scaleSpinner.getValue();
			
			if (defaultRadio.isSelected()) {
				printmode = ResultFile.PRINTMODE_DEFAULT;
			} else if (invertedRadio.isSelected()) {
				printmode = ResultFile.PRINTMODE_INVERTED;
			} else if (whitespacesRadio.isSelected()) {
				printmode = ResultFile.PRINTMODE_WHITESPACES;
			}
			
			ImageFile imageFile = new ImageFile(loadedFile, scale);
			
			savedFile = fc.showSaveDialog(null);
			
			if (savedFile != null) {
				ResultFile resultFile = new ResultFile(imageFile, savedFile);
				resultFile.printResult(printmode);
				
				successAlert.setContentText("Saved successfully at "+savedFile.getAbsolutePath()+"\n\nDo you want to open the file right now?");
				successAlert.showAndWait();
				
				if(successAlert.getResult() == ButtonType.YES) {
					Desktop d = Desktop.getDesktop();
					try {
						d.open(savedFile);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			fileMissingAlert.showAndWait();
		}
	}
}
