package core;

import entities.*;
import systems.*;
import ui.*;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{

    private int panelWidth = 800;
    private int panelHeight = 600;

    KeyHandler keyHandler = new KeyHandler();
    GameState gameState = new GameState();
    HUD hud = new HUD(keyHandler, gameState);
    Player player = new Player(keyHandler, hud, gameState, panelWidth, panelHeight);
    
    // constructor
    public GamePanel(){
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        this.setDoubleBuffered(true);

        addKeyListener(keyHandler);
        setFocusable(true);
        
        Timer timer = new Timer(16, e -> {
            player.playerMovement();
            repaint();
        });
        timer.start();

        Timer playerStaminaFunc = new Timer(500, e -> {
            hud.playerStaminaFunc();
            repaint();
        });
        playerStaminaFunc.start();

        requestFocusInWindow(true);
    }

    @Override 
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if(!gameState.thumbNailIsPressed){
            thumbNailDisplay(g2);
            if(keyHandler.anyKeyPressed) gameState.thumbNailIsPressed = true;
        }else{
            // game start
            setBackground(Color.GRAY);

            // out of stamina
            if(gameState.outOfStamina){
                g2.setColor(Color.BLACK);
                g2.setFont(new Font("Arial", Font.BOLD, 10));
                String message = "Out of Stamina!";
                g2.drawString(message, player.getPlayerPositionX() - 10, player.getPlayerPositionY() - 10);
            }

            playerDisplay(g2);
            
            hudDisplay(g2);
        }

    }

    public void thumbNailDisplay(Graphics2D g2) {
        setBackground(Color.BLACK);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        String message = "Press any key to continue";
        g2.drawString(message, (panelWidth / 2) - 130, panelHeight / 2);
    }

    public void playerDisplay(Graphics2D g2) {
        // player
        g2.setColor(Color.RED);
        g2.fillRect(player.getPlayerPositionX(), player.getPlayerPositionY(), player.getPlayerSizeWidth(), player.getPlayerSizeHeight());
    }

    public void hudDisplay(Graphics2D g2) {
        // stamina hud display
        g2.setColor(Color.ORANGE);
        g2.fillRect(75, 50, (hud.getPlayerStamina() * 2), 5);
    }

}