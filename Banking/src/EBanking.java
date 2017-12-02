import javax.print.MultiDocPrintService;
import javax.sound.midi.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;
import java.awt.*;

public class EBanking extends JFrame {

	private JButton Login, Register, exit, Logout, Enter1;
	private JTextField LoginA, PasswordA;
	private JCheckBox Remeb;
	private String UserOf;
	private boolean enter = false;

	private JLabel Login1, Pass1, Wel, ErrorLab, FileCr;

	public EBanking() {

		setLayout(new FlowLayout());

		UserOf = "Anonymus";

		// Check if user is logged

		FileCr = new JLabel();
		add(FileCr);

		// Welcome message
		Wel = new JLabel("Welcome to your E-banking Mr/Ms " + UserOf);
		add(Wel);

		// Login Label and Field
		Login1 = new JLabel("Login");
		add(Login1);

		LoginA = new JTextField("Enter your Username");
		LoginA.setBounds(150, 100, 100, 100);
		add(LoginA);

		PasswordA = new JTextField("Enter your Password");
		PasswordA.setBounds(150, 100, 100, 100);
		add(PasswordA);

		Remeb = new JCheckBox("Remember me ");
		add(Remeb);

		// End of Login
		// New User
		Login = new JButton("Login");
		add(Login);

		Logout = new JButton("Logout");
		Logout.setVisible(false);
		Logout.setEnabled(false);
		add(Logout);

		Register = new JButton("Register");
		add(Register);

		Enter1 = new JButton("Enter Your account");
		add(Enter1);

		exit = new JButton("Exit Program");
		add(exit);

		ErrorLab = new JLabel("");
		add(ErrorLab);

		Enter1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (enter == true) {

					MainAccount gui = new MainAccount(EBanking.this);
					gui.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					gui.setVisible(true);
					gui.setTitle("Exit Warning");
					
				} else {

					ErrorLab.setForeground(Color.PINK);
					ErrorLab.setText("You are not logged in");

				}
			}
		});

		Logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!UserOf.equals("Anonymus")) {

					UserOf = "Anonymus";

				} else {
					ErrorLab.setForeground(Color.PINK);
					ErrorLab.setText("You are not logged in");
				}
			}
		});

		Register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(UserOf.equals("Anonymus")) {
				AlreradyUser gui = new AlreradyUser(EBanking.this);
				gui.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				gui.setVisible(true);
				gui.setTitle("Exit Warning");
				gui.setSize(400, 400);
			} else {
				
				ErrorLab.setForeground(Color.RED);
				ErrorLab.setText("You have already logged in, please logout first");
			}
			}
		});

		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ExitChoice gui = new ExitChoice(EBanking.this);
				gui.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				gui.setVisible(true);
				gui.setEnabled(true);
				gui.setTitle("Exit Warning");
				gui.setSize(400, 400);

			}
		});

		Login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Strings to get TextField values
				if (!UserOf.equals("Anonymus")) {
					ErrorLab.setText("You are already logged in if you want you can press the Logout button");

				} else if (UserOf.equals("Anonymus")) {

					String UserPC = System.getProperty("user.name");
					String s = LoginA.getText();
					String p = PasswordA.getText();

					// Files containing username and password
					File Log1 = new File("C://://Users//" + UserPC + "//Documents//Project Fotis Files//" + s + ".txt");
					File Pass1 = new File(
							"C://://Users//" + UserPC + "//Documents//Project Fotis Files//" + s + "Pass.txt");
					File UserName = new File(
							"C://://Users//" + UserPC + "//Documents//Project Fotis Files//" + p + "UserName.txt");

					BufferedReader reader, readerpass, readerUs;
					String line, linep, lineUs;

					if (Log1.exists() && Log1.isFile() && Pass1.exists() && Pass1.isFile()) {
						try {

							reader = new BufferedReader(new FileReader(Log1));
							readerpass = new BufferedReader(new FileReader(Pass1));

							while ((line = reader.readLine()) != null && (linep = readerpass.readLine()) != null) {

								if (line.equals(s) && linep.equals(p)) {

									readerUs = new BufferedReader(new FileReader(UserName));

									while ((lineUs = readerUs.readLine()) != null) {

										UserOf.equals(lineUs);

										ErrorLab.setText("You are logged in, welcome" + UserOf);
										Logout.setVisible(true);
										Logout.setEnabled(true);
										enter = true;

										if (Remeb.isSelected()) {

											File Log2 = new File("C://://Users//" + UserPC
													+ "//Documents//Project Fotis Files//Remember//Log2.txt");

											if (Log2.exists()) {

												Log2.delete();

												if (Log2.createNewFile()) {

													PrintWriter writer = new PrintWriter(new FileWriter(Log2, true));

													writer.print(lineUs);

													writer.close();
												}
											} else if (!Log2.exists()) {
												if (Log2.createNewFile()) {

													PrintWriter writer = new PrintWriter(new FileWriter(Log2, true));

													writer.print(lineUs);

													writer.close();
												}
											}

										} else if (!Remeb.isSelected()) {

											DeleteHistory();
										}
									}
								}
								reader.close();
								readerpass.close();
							}

						} catch (IOException ex) {
							ErrorLab.setText("Error");
						}

					} else {
						ErrorLab.setForeground(Color.red);
						ErrorLab.setText(
								"Your Password/Username is wrong, if you are a new user Press the Register button");

					}

				}
			}

			private void DeleteHistory() {
				String UserPC = System.getProperty("user.name");

				File Log2 = new File("C://://Users//" + UserPC + "//Documents//Project Fotis Files//History//Log2.txt");

				if (Log2.exists() && Log2.isFile()) {

					Log2.delete();
				}
			}

		});

		LoginA.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				String s = LoginA.getText();
				if (s.equals("Enter your Username")) {
					LoginA.setText("");

				}
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		PasswordA.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				String s = PasswordA.getText();
				if (s.equals("Enter your Password")) {
					PasswordA.setText("");

				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EBanking gui = new EBanking();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setVisible(true);
		gui.setTitle("Fotis E-Banking 1st attempt");
		gui.setSize(300, 300);

	}

}
