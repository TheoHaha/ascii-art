// made with windowbuilder pro

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

public class MyGui {

	private JFrame frmTextToAscii;
	private JTextField filenameTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		frmTextToAscii.setTitle("Text to ASCII");
		frmTextToAscii.setBounds(100, 100, 650, 200);
		frmTextToAscii.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTextToAscii.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel mainPane = new JPanel();
		FlowLayout fl_mainPane = (FlowLayout) mainPane.getLayout();
		frmTextToAscii.getContentPane().add(mainPane, BorderLayout.NORTH);
		
		JPanel chooserPane = new JPanel();
		mainPane.add(chooserPane);
		chooserPane.setLayout(new BorderLayout(0, 0));
		
		filenameTextField = new JTextField();
		filenameTextField.setToolTipText("Choose a file...");
		filenameTextField.setPreferredSize(new Dimension(7, 23));
		chooserPane.add(filenameTextField, BorderLayout.WEST);
		filenameTextField.setColumns(25);
		
		JButton browseBtn = new JButton("Browse...");
		browseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// open the JFileChooser to choose an image
			}
		});
		chooserPane.add(browseBtn, BorderLayout.EAST);
		
		JButton convertBtn = new JButton("Convert to ASCII");
		convertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// convert the selected image to ASCII art
			}
		});
		
		JPanel scalePane = new JPanel();
		scalePane.setToolTipText("Set this in case the image is too large or too small to be converted effectively");
		mainPane.add(scalePane);
		scalePane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Scale:");
		scalePane.add(lblNewLabel);
		
		JSpinner scaleSpinner = new JSpinner();
		scaleSpinner.setToolTipText("");
		lblNewLabel.setLabelFor(scaleSpinner);
		scalePane.add(scaleSpinner);
		scaleSpinner.setPreferredSize(new Dimension(45, 23));
		scaleSpinner.setModel(new SpinnerNumberModel(1.0, 0.01, null, 0.1));
		mainPane.add(convertBtn);
		
		JPanel infoPane = new JPanel();
		frmTextToAscii.getContentPane().add(infoPane, BorderLayout.CENTER);
		infoPane.setLayout(new GridLayout(0, 1, 5, 0));
		
		JPanel infoSubPane1 = new JPanel();
		infoPane.add(infoSubPane1);
		infoSubPane1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel infoLabel = new JLabel("Image Info");
		infoSubPane1.add(infoLabel);
		infoLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel filenameLabel = new JLabel("Filename: ");
		infoSubPane1.add(filenameLabel);
		
		JPanel infoSubPane2 = new JPanel();
		infoPane.add(infoSubPane2);
		infoSubPane2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel widthLabel = new JLabel("Width: ");
		infoSubPane2.add(widthLabel);
		
		JLabel widthScaledLabel = new JLabel("Width (scaled):");
		infoSubPane2.add(widthScaledLabel);
		
		JLabel heightLabel = new JLabel("Height: ");
		infoSubPane2.add(heightLabel);
		
		JLabel heightScaledLabel = new JLabel("Height (scaled):");
		infoSubPane2.add(heightScaledLabel);
		
		JPanel placeholder1 = new JPanel();
		frmTextToAscii.getContentPane().add(placeholder1, BorderLayout.WEST);
		
		JPanel placeholder2 = new JPanel();
		frmTextToAscii.getContentPane().add(placeholder2, BorderLayout.EAST);
		
		JPanel placeholder3 = new JPanel();
		frmTextToAscii.getContentPane().add(placeholder3, BorderLayout.SOUTH);
	}

}
