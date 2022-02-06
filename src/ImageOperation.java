package src;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.plaf.TextUI;

public class ImageOperation {
	public static void operate(int key) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(null);
		fileChooser.getSelectedFile();
		File file = fileChooser.getSelectedFile();

		// file FileInputStream
		try {
			FileInputStream fis = new FileInputStream(file);

			byte[] data = new byte[fis.available()]; // This line is converting the Image into data array which is
														// further being converted to bytes.
			fis.read(data); // This is reading the data from 'data'

			int i = 0;

			for (byte b : data) {
				System.out.println(b);
				data[i] = (byte) (b ^ key); // -----XOR of the data --------> Most Important part for
											// Encryption/Decryption------
				i++;
			}
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(data); // writing the changed data or encrypted data
			fos.close();
			fis.close();
			JOptionPane.showMessageDialog(null, "Encryption / Decryption Successfull");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setTitle("File Crypter"); // Title of the dialog box
		f.setSize(300, 100); // Size of the dialog box
		f.setLocationRelativeTo(null); // To Position the dialog box in center
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // For Closing Operation

		Font font = new Font("Roboto", Font.ROMAN_BASELINE, 10); // Setting the Font

		// Creation of button
		JButton button = new JButton();
		button.setText("Choose File");
		button.setFont(font);
		// button.setSize(10, 100);
		// button.setBounds(100, 10, 290, 50);
		button.setForeground(Color.BLACK);
		button.setBackground(Color.CYAN);
		button.setBorderPainted(true);

		JLabel headerLabel = new JLabel("", JLabel.CENTER);
		headerLabel.setText("Enter Secret Passcode :");
		// headerLabel.setSize(10, 10);

		// Creation of Text Field
		JTextField textField = new JTextField(15);
		// input type="text" title="Enter the Key for Operation" //-------Fot Trial only
		// ------> Not aware of result or error
		textField.setFont(font);
		textField.setText("Crypter Key ?");
		// textField.setSize(10, 30);

		button.addActionListener(e -> { // e->{} is a lambda function
			System.out.println("The Button is clicked");
			String text = textField.getText();
			int temp = Integer.parseInt(text);
			operate(temp);

		});

		f.setLayout(new FlowLayout());
		f.add(headerLabel);
		f.add(textField);
		f.add(button);

		f.setVisible(true);

	}

}
