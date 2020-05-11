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
		frmTextToAscii.setResizable(false);
		frmTextToAscii.setTitle("Text to ASCII");
		frmTextToAscii.setBounds(100, 100, 550, 200);
		frmTextToAscii.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTextToAscii.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel mainPane = new JPanel();
		FlowLayout fl_mainPane = (FlowLayout) mainPane.getLayout();
		frmTextToAscii.getContentPane().add(mainPane, BorderLayout.NORTH);
		
		JPanel chooserPane = new JPanel();
		mainPane.add(chooserPane);
		chooserPane.setLayout(new BorderLayout(0, 0));
		
		filenameTextField = new JTextField();
		chooserPane.add(filenameTextField, BorderLayout.WEST);
		filenameTextField.setColumns(25);
		
		JButton browseBtn = new JButton("Browse...");
		browseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		chooserPane.add(browseBtn, BorderLayout.EAST);
		
		JButton convertBtn = new JButton("Convert to ASCII");
		mainPane.add(convertBtn);
		
		JPanel infoPane = new JPanel();
		frmTextToAscii.getContentPane().add(infoPane, BorderLayout.CENTER);
		infoPane.setLayout(new GridLayout(0, 1, 5, 0));
		
		JLabel infoLabel = new JLabel("Image Info");
		infoPane.add(infoLabel);
		
		JLabel filenameLabel = new JLabel("Filename: ");
		infoPane.add(filenameLabel);
		
		JLabel widthLabel = new JLabel("Width: ");
		infoPane.add(widthLabel);
		
		JLabel heightLabel = new JLabel("Height: ");
		infoPane.add(heightLabel);
		
		JPanel panel = new JPanel();
		frmTextToAscii.getContentPane().add(panel, BorderLayout.WEST);
		
		JPanel panel_1 = new JPanel();
		frmTextToAscii.getContentPane().add(panel_1, BorderLayout.EAST);
		
		JPanel panel_2 = new JPanel();
		frmTextToAscii.getContentPane().add(panel_2, BorderLayout.SOUTH);
	}

}
