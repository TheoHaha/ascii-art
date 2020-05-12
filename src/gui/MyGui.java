// made with windowbuilder pro
package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Cursor;

public class MyGui {

	private JFrame frmTextToAscii;
	private JTextField filenameTextField;
	/**
	 * @wbp.nonvisual location=42,229
	 */
	private final ButtonGroup printmodeBtnGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyGui window = new MyGui();
					window.frmTextToAscii.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MyGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTextToAscii = new JFrame();
		frmTextToAscii.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frmTextToAscii.setResizable(false);
		frmTextToAscii.setTitle("Text to ASCII");
		frmTextToAscii.setBounds(100, 100, 500, 245);
		frmTextToAscii.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTextToAscii.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel mainPane = new JPanel();
		frmTextToAscii.getContentPane().add(mainPane);
		mainPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
		
		JPanel chooserPane = new JPanel();
		mainPane.add(chooserPane);
		chooserPane.setLayout(new BorderLayout(0, 0));
		
		filenameTextField = new JTextField();
		filenameTextField.setToolTipText("Choose a file...");
		filenameTextField.setPreferredSize(new Dimension(7, 23));
		chooserPane.add(filenameTextField, BorderLayout.WEST);
		filenameTextField.setColumns(35);
		
		JButton browseBtn = new JButton("Browse...");
		browseBtn.setToolTipText("Choose an image to convert to ASCII art!");
		browseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// open the JFileChooser to choose an image
			}
		});
		chooserPane.add(browseBtn, BorderLayout.EAST);
		
		JPanel scalePane = new JPanel();
		mainPane.add(scalePane);
		scalePane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Scale:");
		scalePane.add(lblNewLabel);
		
		JSpinner scaleSpinner = new JSpinner();
		scaleSpinner.setToolTipText("Set this in case the image is too large or too small to be converted effectively");
		lblNewLabel.setLabelFor(scaleSpinner);
		scalePane.add(scaleSpinner);
		scaleSpinner.setPreferredSize(new Dimension(45, 23));
		scaleSpinner.setModel(new SpinnerNumberModel(1.0, 0.01, null, 0.1));
		
		JPanel printmodePane = new JPanel();
		mainPane.add(printmodePane);
		
		JLabel printmodeLabel = new JLabel("Print mode:");
		printmodeLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		printmodePane.add(printmodeLabel);
		
		JRadioButton defaultModeBtn = new JRadioButton("Default");
		defaultModeBtn.setSelected(true);
		printmodePane.add(defaultModeBtn);
		
		JRadioButton invertedModeBtn = new JRadioButton("Inverted");
		printmodePane.add(invertedModeBtn);
		
		JRadioButton whitespacesModeBtn = new JRadioButton("Whitespaces");
		printmodePane.add(whitespacesModeBtn);
		
		JRadioButton whiteinvModeBtn = new JRadioButton("Whitespaces Inverted");
		printmodePane.add(whiteinvModeBtn);
		
		printmodeBtnGroup.add(defaultModeBtn);
		printmodeBtnGroup.add(invertedModeBtn);
		printmodeBtnGroup.add(whitespacesModeBtn);
		printmodeBtnGroup.add(whiteinvModeBtn);
		
		JPanel infoNconvertPane = new JPanel();
		mainPane.add(infoNconvertPane);
		infoNconvertPane.setBorder(new EmptyBorder(0, 5, 5, 5));
		infoNconvertPane.setLayout(new BorderLayout(0, 0));
		
		JPanel infoPane = new JPanel();
		infoNconvertPane.add(infoPane);
		infoPane.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Image Info", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		infoPane.setLayout(new BorderLayout(0, 0));
		
		JPanel infoSubPane = new JPanel();
		infoSubPane.setBorder(new EmptyBorder(0, 5, 0, 5));
		infoPane.add(infoSubPane);
		GridBagLayout gbl_infoSubPane = new GridBagLayout();
		gbl_infoSubPane.columnWidths = new int[]{447, 0};
		gbl_infoSubPane.rowHeights = new int[] {30, 30, 30};
		gbl_infoSubPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_infoSubPane.rowWeights = new double[]{0.0, 0.0, 0.0};
		infoSubPane.setLayout(gbl_infoSubPane);
		
		JLabel heightLabel = new JLabel("Height: ");
		heightLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		heightLabel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		GridBagConstraints gbc_heightLabel = new GridBagConstraints();
		gbc_heightLabel.fill = GridBagConstraints.VERTICAL;
		gbc_heightLabel.anchor = GridBagConstraints.WEST;
		gbc_heightLabel.gridx = 0;
		gbc_heightLabel.gridy = 2;
		infoSubPane.add(heightLabel, gbc_heightLabel);
		
		JLabel widthLabel = new JLabel("Width: ");
		widthLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_widthLabel = new GridBagConstraints();
		gbc_widthLabel.insets = new Insets(0, 0, 5, 0);
		gbc_widthLabel.anchor = GridBagConstraints.WEST;
		gbc_widthLabel.gridx = 0;
		gbc_widthLabel.gridy = 1;
		infoSubPane.add(widthLabel, gbc_widthLabel);
		
		JLabel filenameLabel = new JLabel("Filename: ");
		filenameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		filenameLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		GridBagConstraints gbc_filenameLabel = new GridBagConstraints();
		gbc_filenameLabel.insets = new Insets(0, 0, 5, 0);
		gbc_filenameLabel.anchor = GridBagConstraints.WEST;
		gbc_filenameLabel.gridx = 0;
		gbc_filenameLabel.gridy = 0;
		infoSubPane.add(filenameLabel, gbc_filenameLabel);
		
		JButton convertBtn = new JButton("Convert to ASCII...");
		convertBtn.setToolTipText("Convert the selected image to ASCII art and save it as a .txt file.");
		infoNconvertPane.add(convertBtn, BorderLayout.SOUTH);
	}

}
