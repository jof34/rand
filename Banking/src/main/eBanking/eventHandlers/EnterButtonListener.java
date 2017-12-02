package main.eBanking.eventHandlers;

import main.eBanking.EBanking;
import main.eBanking.MainAccount;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EnterButtonListener implements ActionListener {

    private EBanking parent;

    public EnterButtonListener(EBanking parent){
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (parent.isSuccessfulLogin()) {
            MainAccount gui = new MainAccount();
            gui.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            gui.setVisible(true);
            gui.setTitle("Exit Warning");
            this.parent.dispose();
        } else {
            this.parent.getErrorLabel().setForeground(Color.PINK);
            this.parent.getErrorLabel().setText("You are not logged in");
        }
    }
}
