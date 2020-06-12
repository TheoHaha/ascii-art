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

	// uneditable text field that displays loaded image's path
	@FXML private TextField loadTxtField;
	// button that handles the load function
	@FXML private Button loadBtn;
	
	// spinner that for scale input
	@FXML private Spinner<Double> scaleSpinner;
	private SpinnerValueFactory<Double> svf = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.1, 5, 1, 0.1); // min, max, default and step values for the scale spinner
	
	// print mode radio buttons and their group
	@FXML private ToggleGroup modeGroup;
	@FXML private RadioButton defaultRadio;
	@FXML private RadioButton invertedRadio;
	@FXML private RadioButton whitespacesRadio;
	
	// labels that display the loaded image's info
	@FXML private Label filenameLabel;
	@FXML private Label widthLabel;
	@FXML private Label heightLabel;
	
	// button that handles the save function
	@FXML private Button saveBtn;
	
	// alerts
	Alert fileMissingAlert = new Alert(AlertType.WARNING); // for when the user hasn't loaded an image
	Alert successAlert = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO); // for when the program has succeeded
	
	// one file chooser handles both load and save functions
	FileChooser fc = new FileChooser();
	
	// all required info is saved on these variables
	private File loadedFile;
	private File savedFile;
	private double scale;
	private int printmode;

	// this is ran as soon as the controller is called, which in this case as soon as the program runs
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
	
	// load function that runs when the load button is pressed
	public void loadImage(ActionEvent event) {
		// since we're using one file chooser to handle multiple functions, we need to clear the extension filters beforehand
		fc.getExtensionFilters().clear();
		fc.getExtensionFilters().addAll(
				new ExtensionFilter("Images", "*.png", "*.jpg", "*.tif"),
				new ExtensionFilter("PNG files", "*.png"),
				new ExtensionFilter("JPG files", "*.jpg"),
				new ExtensionFilter("TIF files", "*.tif"));
		
		// open the file chooser as an 'open' dialog and set the chosen file as our loaded file
		loadedFile = fc.showOpenDialog(null);
		
		if (loadedFile != null) {
			// make an ImageFile object out of the loaded file (only useful for getting image's dimensions)
			ImageFile selectedImage = new ImageFile(loadedFile);
			
			// set all nodes to display their appropriate info
			loadTxtField.setText(selectedImage.getFile().getAbsolutePath());
			
			filenameLabel.setText("Filename: "+selectedImage.getFile().getName());
			widthLabel.setText("Width: "+selectedImage.getWidth());
			heightLabel.setText("Height: "+selectedImage.getHeight());
		}
	}
	
	// save function that runs when the save button is pressed
	public void saveAsTxt(ActionEvent event) {
		// check if an image has actually been loaded before running
		if (loadedFile != null) {
			// since we're using one file chooser to handle multiple functions, we need to clear the extension filters beforehand
			fc.getExtensionFilters().clear();
			fc.getExtensionFilters().addAll(new ExtensionFilter("Text File", "*.txt"));

			// get the chosen scale from the scale spinner
			scale = scaleSpinner.getValue();
			
			// get the chosen print mode from the radios
			if (defaultRadio.isSelected()) {
				printmode = ResultFile.PRINTMODE_DEFAULT;
			} else if (invertedRadio.isSelected()) {
				printmode = ResultFile.PRINTMODE_INVERTED;
			} else if (whitespacesRadio.isSelected()) {
				printmode = ResultFile.PRINTMODE_WHITESPACES;
			}
			
			// make an ImageFile object out of the loaded file to prepare for the conversion
			ImageFile imageFile = new ImageFile(loadedFile, scale);
			
			// open the file chooser as a 'save' dialog and set the chosen file as our result txt
			savedFile = fc.showSaveDialog(null);
			
			if (savedFile != null) {
				// make a ResultFile object out of the prepared ImageFile and the chosen result txt
				ResultFile resultFile = new ResultFile(imageFile, savedFile);
				resultFile.printResult(printmode);
				
				// set success alert text here so we can display the path of the saved file
				successAlert.setContentText("Saved successfully at "+savedFile.getAbsolutePath()+"\n\nDo you want to open the file right now?");
				successAlert.showAndWait();
				
				// if the user chooses so, open the result txt
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
			// display the appropriate alert if no image has been loaded
			fileMissingAlert.showAndWait();
		}
	}
}
