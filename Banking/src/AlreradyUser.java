import javax.print.MultiDocPrintService;
import javax.sound.midi.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.awt.*;

public class AlreradyUser extends JDialog {

	private JLabel label, ErrorLabel, date1, year1;
	private JTextField Name, Username, Password, Password2, date, year;
	private JCheckBox Man, Woman, Nothing;
	public ArrayList<String> InfoUser = new ArrayList<>();
	private JButton Register, EnterThe;
	private JComboBox month;
	private boolean ageMain = false;
	public String UserOf;

	public AlreradyUser(JFrame frame) {

		super(frame, "Registration Form", true);
		setLayout(new FlowLayout());

		label = new JLabel("Please insert your data");
		add(label);

		// User Input
		Name = new JTextField("Insert your name");
		add(Name);

		Username = new JTextField("Insert your Username");
		add(Username);

		Password = new JTextField("Insert your Password");
		add(Password);

		Password2 = new JTextField("Re-Insert your Password");
		add(Password2);

		// End of User Input

		// Sex Checkboxes
		Man = new JCheckBox("Man");
		add(Man);
		Woman = new JCheckBox("Woman");
		add(Woman);
		Nothing = new JCheckBox("I don't want to disclose");
		add(Nothing);
		// End of Sex choice

		// Register button
		Register = new JButton("Register");
		add(Register);
		// Register insta

		// ComboBoxs age
		// date box
		date = new JTextField("Insert the day");
		date.setBounds(50, 50, 50, 50);
		add(date);

		// Month Comb

		String[] months = { "", "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		month = new JComboBox(months);
		add(month);
		// Year
		year = new JTextField("Insert the year");
		year.setBounds(50, 50, 50, 50);
		add(year);

		date1 = new JLabel("");
		add(date1);

		// End of comboboxes
		ErrorLabel = new JLabel("");
		add(ErrorLabel);

		EnterThe = new JButton("Enter your account");
		EnterThe.setEnabled(false);
		EnterThe.setVisible(false);
		add(EnterThe);
		
		//Enter the programm
		EnterThe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		
		//Register button
		Register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String dateS = date.getText();
				String yearS = year.getText();

				String monthz = month.getSelectedItem().toString();

				if (!dateS.isEmpty() && !yearS.isEmpty() && !monthz.equals("")) {
					ageMain = true;
				} else {
					date1.setText("You need to be above 18 to create an account");
				}

				if (ageMain == true) {

					if (Man.isSelected() || Woman.isSelected() || Nothing.isSelected()) {
						boolean PassSu = false, UserSu = false;
						String UserLoc = Username.getText();
						String PassLoc = Password.getText();
						String area = Username.getText();

						char[] lettersUser = area.toCharArray();
						int letter = 0;
						int space = 0;
						int num = 0;
						int other = 0;

						for (int i = 0; i < area.length(); i++) {
							if (Character.isLetter(lettersUser[i])) {
								letter++;
							} else if (Character.isDigit(lettersUser[i])) {
								num++;
							} else if (Character.isSpaceChar(lettersUser[i])) {
								space++;
							} else if (!Character.isLetter(lettersUser[i]) || !Character.isDigit(lettersUser[i])
									|| !Character.isSpaceChar(lettersUser[i])) {
								other++;
							}
						}

						if (space >= 1 || other >= 1) {
							ErrorLabel.setForeground(Color.RED);
							ErrorLabel.setText(
									"You have entered invalid characters only letters and numbers are allowed");
						} else if (space == 0 && other == 0 && (letter == 0 || num == 0)) {
							ErrorLabel.setForeground(Color.RED);
							ErrorLabel.setText("Your username needs both letters and numbers");

						} else if (space == 0 && other == 0 && letter >= 1 && num >= 1) {

							UserLoc = area;

							InfoUser.add(area);

							String passArea = Password.getText();
							String passArea2 = Password2.getText();
							char[] PassChars = passArea.toCharArray();

							if (passArea.equals(passArea2)) {

								int letterP = 0;
								int spaceP = 0;
								int numP = 0;
								int otherP = 0;

								for (int x = 0; x < passArea.length(); x++) {
									if (Character.isLetter(PassChars[x])) {
										letterP++;
									} else if (Character.isDigit(PassChars[x])) {
										numP++;
									} else if (Character.isSpaceChar(PassChars[x])) {
										spaceP++;
									} else if (!Character.isLetter(PassChars[x]) || !Character.isDigit(PassChars[x])
											|| !Character.isSpaceChar(PassChars[x])) {
										otherP++;
									}
								}

								if (spaceP >= 1) {
									ErrorLabel.setForeground(Color.RED);
									ErrorLabel.setText(
											"Space between characters is not allowed, the Password needs numbers,letters etc.");

								} else if (spaceP == 0 && letterP >= 1 && otherP >= 1 && numP >= 1) {

									UserOf = UserLoc;

									InfoUser.add(passArea);
									PassSu = true;
									UserSu = true;

								}

								if (PassSu == true && UserSu == true) {

									String UserPC = System.getProperty("user.name");
									String nameUser = Name.getText();

									InfoUser.add(nameUser);

									File dire = new File("C://Users//" + UserPC + "//Documents//Project Fotis Files//Gahterd Info//");

									if (dire.mkdirs()) {

										File Log1 = new File("C://Users//" + UserPC + "//Documents//Project Fotis Files//" + UserOf + ".txt");
										File Pass1 = new File("C://Users//" + UserPC + "//Documents//Project Fotis Files//" + UserOf + "Pass.txt");
										File UserName1 = new File("C://Users//" + UserPC + "//Documents//Project Fotis Files//" + UserOf + "UserName.txt");
										File Info = new File("C://Users//" + UserPC + "//Documents//Project Fotis Files//Gahterd Info//Information.txt");

										try {
											if (Log1.createNewFile() && Pass1.createNewFile()
													&& UserName1.createNewFile() && Info.createNewFile()) {

												PrintWriter writer = new PrintWriter(new FileWriter(Log1, true));
												PrintWriter writer1 = new PrintWriter(new FileWriter(Pass1, true));
												PrintWriter writer2 = new PrintWriter(new FileWriter(UserName1, true));
												PrintWriter writer3 = new PrintWriter(new FileWriter(Info, true));

												writer.println(UserLoc);
												writer1.println(PassLoc);
												writer2.println(UserLoc);
												writer3.println(InfoUser);

												writer.close();
												writer1.close();
												writer2.close();
												writer3.close();

												EnterThe.setEnabled(true);
												EnterThe.setVisible(true);
												ErrorLabel.setForeground(Color.GREEN);
												ErrorLabel.setText("Account created succesfully");

											} else if (Log1.exists() && Pass1.exists() && UserName1.exists()
													&& Info.exists()) {
												ErrorLabel.setForeground(Color.RED);
												ErrorLabel.setText("Account exists");
											}
										} catch (IOException e1) {
											// TODO Auto-generated catch block
											ErrorLabel.setForeground(Color.RED);
											ErrorLabel.setText("There was a problem with the account try again!");
										}
									} else {
										ErrorLabel.setForeground(Color.RED);
										ErrorLabel.setText("Directory could not be created");
									}
								} else {
									ErrorLabel.setForeground(Color.RED);
									ErrorLabel.setText("Something went wrong try again");
								}

							} else {
								ErrorLabel.setForeground(Color.RED);
								ErrorLabel.setText("Passwords are not the same");

							}

						}

					} else {
						ErrorLabel.setForeground(Color.RED);
						ErrorLabel.setText("You haven't chosen one of the boxes");
					}
				} else {
					ErrorLabel.setForeground(Color.RED);
					ErrorLabel.setText("Insert the correct age");
				}
			}
		});

	}

}

/*
 * String dateS = date.getText(); String yearS = year.getText(); int yearZ =
 * Integer.parseInt(yearS); String monthz = month.getSelectedItem().toString();
 * 
 * if(!dateS.isEmpty() && !yearS.isEmpty() && yearZ <= 1999 &&
 * !monthz.equals(""))
 */
