import javax.print.MultiDocPrintService;
import javax.sound.midi.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ExitChoice extends JDialog {


	JLabel label;

	JButton Yes, No;

	public ExitChoice(JFrame frame) {
		
		super(frame, "Help Window", true);
		setLayout(new FlowLayout());
		
		label = new JLabel ("Are you sure that you want to close the program?");
		add(label);
		
		Yes = new JButton("Yes");
		add(Yes);
		
		Yes.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}
		});
		
		No = new JButton("No");
		add(No);
		
		No.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
}
}
