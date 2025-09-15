package core;

import entities.*;
import systems.*;
import ui.*;
import assets.*;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{

    private int panelWidth = 800;
    private int panelHeight = 600;

    Sheets sheets = new Sheets();
    KeyHandler keyHandler = new KeyHandler();
    GameState gameState = new GameState();
    HUD hud = new HUD(keyHandler, gameState);
    Player player = new Player(keyHandler, hud, gameState, panelWidth, panelHeight);
    CombatSystem combatSystem = new CombatSystem(keyHandler, player);

    public GamePanel(){
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        this.setDoubleBuffered(true);
        
        addKeyListener(keyHandler);
        setFocusable(true);

        this.setBackground(Color.GRAY);
        
        Timer timer = new Timer(41, e -> {
            player.playerMovement();
            combatSystem.playerAttackedFunc();
            repaint();
        });
        timer.start();

        Timer playerStaminaFunc = new Timer(1000, e -> {
            hud.playerStaminaFunc();
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
            

            // out of stamina
            if(gameState.outOfStamina){
                g2.setColor(Color.BLACK);
                g2.setFont(new Font("Arial", Font.BOLD, 10));
                String message = "Out of Stamina!";
                g2.drawString(message, player.getPlayerPositionX() - 10, player.getPlayerPositionY() - 10);
            }

            playerDisplay(g2);
            
            playerAttacks(g2);
            
            hudDisplay(g2);

            // g2.setColor(Color.yellow); // TODO ENEMY HIT BOX
            // g2.fillRect(0 ,0, player.getPlayerSizeWidth(), player.getPlayerSizeHeight());
        }

    }

    public void thumbNailDisplay(Graphics2D g2) {

        // TODO REPLACE IMAGE THUMBNAIL / TO DELETE
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        String message = "Press any key to continue";
        g2.drawString(message, (panelWidth / 2) - 130, panelHeight / 2);

        g2.drawImage(sheets.getThumbNailIcon().getImage(), 0, 0, getWidth(), getHeight(),null);

    }

    public void playerDisplay(Graphics2D g2) {

        ImageIcon imageToDraw;
        if (keyHandler.lastPoseLeft) imageToDraw = sheets.getLeftPoseIdle();
        else imageToDraw = sheets.getRightPoseIdle();

        //shadow
        g2.drawImage(sheets.getPlayerShadow().getImage(), player.getPlayerPositionX() - 40, player.getPlayerPositionY() - 25, 140, 95,null);
        //player
        g2.drawImage(imageToDraw.getImage(), player.getPlayerPositionX(), player.getPlayerPositionY(), player.getPlayerSizeWidth(), player.getPlayerSizeHeight(),null);

    }

    public void playerAttacks(Graphics2D g2) {
        if (combatSystem.playerAttacked) {
            int attackX = player.getPlayerPositionX();
            int attackY = player.getPlayerPositionY();

            if (keyHandler.lastPoseLeft) {
                attackX -= 30;
            } else if (keyHandler.lastPoseRight) {
                attackX += 30;
            }

            g2.setColor(Color.GREEN);
            g2.fillRect(attackX, attackY, player.getPlayerSizeWidth(), player.getPlayerSizeHeight());
        }
    }

    public void hudDisplay(Graphics2D g2) {
        // STAMINA hud display
        g2.setColor(Color.ORANGE);
        g2.fillRect(101, 83, (hud.getPlayerStamina() * 2) - 51, 15);

        // main HUD
        g2.drawImage(sheets.getHudIcon().getImage(), 5, -110, 325,325, null);
    }

}