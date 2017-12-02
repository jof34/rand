import javax.print.MultiDocPrintService;
import javax.sound.midi.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.awt.*;

public class MainAccount extends JDialog {
	private long Balance;
	private String UserOf;
	private JLabel label, AmountLeft, ErrorL, TransS;
	private JTextField AmountTransfer, NameSender, NameReceive, IBAN, BIC, Amount;
	private JButton Transfer;
	public boolean Succ;

	public MainAccount(JFrame frame) {

		super(frame, "Main Account", true);
		setLayout(new FlowLayout());

		ArrayList<String> Histo = new ArrayList<>();

		try {
			this.ShowWatch();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		AmountLeft = new JLabel("Your savings are " + getBalance());
		add(AmountLeft);

		label = new JLabel("Insert the appropriate data");
		add(label);

		AmountTransfer = new JTextField("Insert the amount of money");
		AmountTransfer.setBounds(150, 100, 100, 100);
		add(AmountTransfer);

		NameSender = new JTextField("Insert the amount of money");
		NameSender.setBounds(150, 100, 100, 100);
		add(NameSender);

		NameReceive = new JTextField("Insert the amount of money");
		NameReceive.setBounds(150, 100, 100, 100);
		add(NameReceive);

		IBAN = new JTextField("Insert the amount of money");
		IBAN.setBounds(150, 100, 100, 100);
		add(IBAN);

		BIC = new JTextField("Insert the amount of money");
		BIC.setBounds(150, 100, 100, 100);
		add(BIC);

		Amount = new JTextField("Insert the amount of money");
		Amount.setBounds(150, 100, 100, 100);
		add(Amount);

		Transfer = new JButton("Transfer Amount");
		add(Transfer);

		ErrorL = new JLabel("");
		add(ErrorL);

		TransS = new JLabel("");
		add(TransS);

		Transfer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Parse the amount
				String s = AmountTransfer.getText();
				long amountWant = Long.parseLong(s);

				// rest of textFields
				String ib = IBAN.getText();
				String naS = NameSender.getText();
				String naR = NameReceive.getText();
				String nar = BIC.getText();

				// Amount

				if (!ib.isEmpty() && !naS.isEmpty() && !naR.isEmpty() && !nar.isEmpty()) {

					this.AmountTransfer();

					if (Succ == true) {

						this.SetRecord();

					}
				} else {

					ErrorL.setText("You need to fill all the fields");
				}

			}

			private void SetRecord() {

			}

			private void AmountTransfer() {
				if (Balance != 0) {
					String Am = AmountTransfer.getText();
					long Ama = Long.parseLong(Am);

					long result = Balance - Ama;

					if (result > 0) {

						Succ = true;

						Balance = result;

						TransS.setForeground(Color.GREEN);
						TransS.setText("Your Transfer was succesful");
					}
				} else {
					ErrorL.setForeground(Color.RED);
					ErrorL.setText("Insufficient funds");
				}
			}
		});

	}

	public void ShowWatch() throws InterruptedException {
		Thread t = null;
		int hours = 0, minutes = 0, seconds = 0;
		String timeString = "";

		t = new Thread((Runnable) this);
		t.start();

		JButton b = new JButton();
		b.setBounds(100, 100, 100, 50);
		add(b);

		while (true) {
			Calendar cal = Calendar.getInstance();
			hours = cal.get(Calendar.HOUR_OF_DAY);
			minutes = cal.get(Calendar.MINUTE);
			seconds = cal.get(Calendar.SECOND);

			SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
			Date date = cal.getTime();
			timeString = formatter.format(date);

			b.setText(timeString);

			Thread.sleep(1000);

		}

	}

	public void printTime() {

	}

	public long getBalance() {
		return Balance;
	}

	public void setBalance(long balance) {
		String UserPc = System.getProperty("user.name");
		BufferedReader reader;
		String p;
		long s;

		File BalanceSheet = new File(
				" C://://Users//" + UserPc + "//Documents//Project Fotis Files//" + UserOf + ".txt");

		try {
			reader = new BufferedReader(new FileReader(BalanceSheet));

			while ((p = reader.readLine()) != null) {

				s = Long.parseLong(p);

				Balance = s;

			}

		} catch (IOException e1) {

		}

	}

	public String getUserOf() {
		return UserOf;
	}

	public void setUserOf(String userOf) {
		UserOf = "Anonymus";

	}

}
