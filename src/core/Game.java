package core;

import javax.swing.*;

public class Game {
    public static void main(String[] args) {
        
        JFrame frame = new JFrame("Super Inggo's Awakening");
        GamePanel gamePanel = new GamePanel();

        frame.add(gamePanel);
        frame.pack();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

    }
}
