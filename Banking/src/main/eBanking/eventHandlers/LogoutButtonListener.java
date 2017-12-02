package main.eBanking.eventHandlers;

import main.eBanking.EBanking;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LogoutButtonListener implements ActionListener {

    private EBanking parent;

    public LogoutButtonListener(EBanking parent){
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.parent.setUsername("Anonymus");
    }
}
