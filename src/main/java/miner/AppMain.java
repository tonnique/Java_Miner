package miner;

import miner.GUI.*;

import javax.swing.*;
import java.awt.*;

public class AppMain {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                MainFrame mf = new MainFrame();
                mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mf.setVisible(true);
            }
        });
    }
}
