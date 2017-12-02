package main;

import main.eBanking.EBanking;
import main.eBanking.EbankingConfiguration;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        EbankingConfiguration configuration = new EbankingConfiguration();
        configuration.defaultOperation = WindowConstants.EXIT_ON_CLOSE;
        configuration.visible = true;
        configuration.title = "Fotis E-Banking 1st attempt";
        configuration.width = 300;
        configuration.height = 300;

        EBanking gui = new EBanking();
        gui.setupConfiguration(configuration);
        gui.initialise();

    }
}
