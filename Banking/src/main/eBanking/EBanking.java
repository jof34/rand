package main.eBanking;

import main.eBanking.eventHandlers.EnterButtonListener;
import main.eBanking.eventHandlers.LogoutButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

public class EBanking extends JFrame {
    private boolean visible = true;
    private int width = 300;
    private int height = 300;
    private int defaultOperation = WindowConstants.EXIT_ON_CLOSE;
    private String title = "Fotis E-Banking 1st attempt";

    private JButton loginButton;
    private JButton registerButton;
    private JButton exitButton;
    private JButton logoutButton;
    private JButton enterButton;
    private JTextField usernameField;
    private JTextField passwordField;
    private JCheckBox rememberMeCheck;
    private String username = "Anonymus";
    private boolean isSuccessfulLogin = false;
    private JLabel usernameLabel;
    private JLabel welcomeLabel;
    private JLabel errorLabel;
    private JLabel fileCreationLabel;

    public EBanking() { }

    public void setupConfiguration(EbankingConfiguration configuration) {
        this.visible = configuration.visible;
        this.width = configuration.width;
        this.height = configuration.height;
        this.defaultOperation = configuration.defaultOperation;
        this.title = configuration.title;
    }

    public void initialise() {
        setLayout(new FlowLayout());
        guiInit();
        registerActionListeners();
        setDefaultCloseOperation(this.defaultOperation);
        setVisible(this.visible);
        setTitle(this.title);
        setSize(this.width, this.height);
    }


    private void guiInit() {
        // Check if user is logged
        this.fileCreationLabel = new JLabel();
        add(this.fileCreationLabel);

        // Welcome message
        this.welcomeLabel = new JLabel("Welcome to your E-banking Mr/Ms " + this.username);
        add(this.welcomeLabel);

        // loginButton Label and Field
        this.usernameLabel = new JLabel("loginButton");
        add(this.usernameLabel);
        this.usernameField = new JTextField("Enter your Username");
        this.usernameField.setBounds(150, 100, 100, 100);
        add(this.usernameField);


        this.passwordField = new JTextField("Enter your Password");
        this.passwordField.setBounds(150, 100, 100, 100);
        add(this.passwordField);

        this.rememberMeCheck = new JCheckBox("Remember me ");
        add(this.rememberMeCheck);

        // New User
        this.loginButton = new JButton("loginButton");
        add(this.loginButton);

        this.logoutButton = new JButton("logoutButton");
        this.logoutButton.setVisible(false);
        this.logoutButton.setEnabled(false);
        add(this.logoutButton);

        this.registerButton = new JButton("registerButton");
        add(this.registerButton);

        this.enterButton = new JButton("Enter Your account");
        add(this.enterButton);

        this.exitButton = new JButton("Exit Program");
        add(this.exitButton);

        this.errorLabel = new JLabel("");
        add(this.errorLabel);
    }

    private void registerActionListeners() {
        enterButton.addActionListener(new EnterButtonListener(this));
        logoutButton.addActionListener(new LogoutButtonListener(this));
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (username.equals("Anonymus")) {
                    AlreradyUser gui = new AlreradyUser(EBanking.this);
                    gui.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    gui.setVisible(true);
                    gui.setTitle("Exit Warning");
                    gui.setSize(400, 400);
                } else {
                    errorLabel.setForeground(Color.RED);
                    errorLabel.setText("You have already logged in, please logout first");
                }
            }
        });
        exitButton.addActionListener(new ActionListener() {
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
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Strings to get TextField values
                if (!username.equals("Anonymus")) {
                    errorLabel.setText("You are already logged in if you want you can press the logoutButton button");

                } else if (username.equals("Anonymus")) {

                    String UserPC = System.getProperty("user.name");
                    String s = usernameField.getText();
                    String p = passwordField.getText();

                    // Files containing usernameField and passwordField
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

                                        username.equals(lineUs);

                                        errorLabel.setText("You are logged in, welcome" + username);
                                        logoutButton.setVisible(true);
                                        logoutButton.setEnabled(true);
                                        isSuccessfulLogin = true;

                                        if (rememberMeCheck.isSelected()) {

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

                                        } else if (!rememberMeCheck.isSelected()) {

                                            DeleteHistory();
                                        }
                                    }
                                }
                                reader.close();
                                readerpass.close();
                            }

                        } catch (IOException ex) {
                            errorLabel.setText("Error");
                        }

                    } else {
                        errorLabel.setForeground(Color.red);
                        errorLabel.setText(
                                "Your Password/Username is wrong, if you are a new user Press the registerButton button");

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
        usernameField.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                String s = usernameField.getText();
                if (s.equals("Enter your Username")) {
                    usernameField.setText("");
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
        passwordField.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void mousePressed(MouseEvent e) {
                String s = passwordField.getText();
                if (s.equals("Enter your Password")) {
                    passwordField.setText("");
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

    public boolean isSuccessfulLogin() {
        return isSuccessfulLogin;
    }

    public JLabel getErrorLabel(){
        return  this.errorLabel;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
