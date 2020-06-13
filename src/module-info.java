module ascii {
	requires java.desktop;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires transitive javafx.graphics;
	
	exports theo.image_to_ASCII;
	opens theo.main to javafx.fxml;
	exports theo.main;
}