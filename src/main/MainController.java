package main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class MainController {
	
	@FXML private TextField browseTxtField;
	@FXML private Button browseBtn;
	
	@FXML private Spinner<Double> scaleSpinner;
	private SpinnerValueFactory<Double> svf = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.1, 5, 1, 0.1);
	
	@FXML private ToggleGroup modeGroup;
	@FXML private RadioButton defaultRadio;
	@FXML private RadioButton invertedRadio;
	@FXML private RadioButton whitespacesRadio;
	
	@FXML private Label filenameLabel;
	@FXML private Label widthLabel;
	@FXML private Label heightLabel;
	
	@FXML private Button convertBtn;

	public void initialize() {
		scaleSpinner.setValueFactory(svf);
	}
}
