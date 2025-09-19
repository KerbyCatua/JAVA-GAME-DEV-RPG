package core;

import entities.*;
import systems.*;
import ui.*;
import assets.*;
import world.*;

import javax.swing.*;
import java.awt.*;

// TODO CAMERA SYSTEM 
// import java.awt.geom.AffineTransform;

public class GamePanel extends JPanel{

    private int panelWidth = 800;
    private int panelHeight = 600;

    Sheets sheets = new Sheets();
    KeyHandler keyHandler = new KeyHandler();
    GameState gameState = new GameState();
    HUD hud = new HUD(keyHandler, gameState);
    Player player = new Player(keyHandler, hud, gameState, panelWidth, panelHeight);
    CombatSystem combatSystem = new CombatSystem(keyHandler, player);
    GameMap gameMap = new GameMap(sheets, player, panelWidth, panelHeight);
    CollisionDetector collisionDetector = new CollisionDetector(this);

    public GamePanel(){
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        this.setDoubleBuffered(true);
        
        addKeyListener(keyHandler);
        setFocusable(true);

        this.setBackground(Color.GRAY);
        
        Timer timer = new Timer(41, e -> {
            player.playerMovementAndHitbox();

            combatSystem.playerAttackedFunc();

            collisionDetector.mapCollision();
            repaint();
        });
        timer.start();

        Timer hudCostSkillsTimer = new Timer(500, e -> {
            // passive function
            hud.playerStaminaFunc();
            hud.playerManaFunc();

            hud.playerPunchAttackedFunc();

            gameMap.mapRenderLocationGameState();
        });
        hudCostSkillsTimer.start();

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

            // TODO camera system
            // g2.translate(getWidth()/2.15, getHeight()/2.2);   
            // g2.scale(1.25, 1.25);
            // g2.translate(-player.getPlayerPositionX(), -player.getPlayerPositionY());
            
            // map location
            if(GameState.isInGreenLandMap) gameMap.greenLandMap(g2);
            if(GameState.isInSnowLandMap) gameMap.snowLandMap(g2);
            if(GameState.isInDryLandMap) gameMap.dryLandMap(g2);
            if(GameState.isInDarkLandMap) gameMap.darkLandMap(g2);

            // out of stamina
            if(gameState.outOfStamina){
                g2.setColor(Color.BLACK);
                g2.setFont(new Font("Arial", Font.BOLD, 10));
                String message = "Out of Stamina!";
                g2.drawString(message, player.getPlayerPositionX() - 10, player.getPlayerPositionY() - 10);
            }

            // out of mana
            if(GameState.outOfMana){
                g2.setColor(Color.BLACK);
                g2.setFont(new Font("Arial", Font.BOLD, 10));
                String message = "Out of Mana!";
                g2.drawString(message, player.getPlayerPositionX() - 10, player.getPlayerPositionY());
            }

            playerDisplay(g2);
            
            playerAttacks(g2);
            
            // TODO CAMERA SYSTEM
            // g2.setTransform(new AffineTransform());

            hudDisplay(g2);

            // g2.setColor(Color.yellow); // TODO ENEMY HIT BOX
            // g2.fillRect(0 ,0, player.getPlayerSizeWidth(), player.getPlayerSizeHeight());
        }

    }

    public void thumbNailDisplay(Graphics2D g2) {

        // TODO REPLACE IMAGE THUMBNAIL / TO DELETE
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        String message = "Press any key to continue";
        g2.drawString(message, (panelWidth / 2) - 130, panelHeight / 2);

        // g2.drawImage(sheets.getThumbNailIcon().getImage(), 0, 0, getWidth(), getHeight(),null);

    }

    public void playerDisplay(Graphics2D g2) {

        ImageIcon imageToDraw;
        if (keyHandler.lastPoseLeft) imageToDraw = sheets.getLeftPoseIdle();
        else imageToDraw = sheets.getRightPoseIdle();

        //shadow
        g2.drawImage(sheets.getPlayerShadow().getImage(), player.getPlayerPositionX() + 6, player.getPlayerPositionY() + 35, 45, 30, this);
        //player
        g2.drawImage(imageToDraw.getImage(), player.getPlayerPositionX(), player.getPlayerPositionY(), player.getPlayerSizeWidth(), player.getPlayerSizeHeight(), this);
        

    }

    public void playerAttacks(Graphics2D g2) {
        if (CombatSystem.playerAttacked) {
            int attackX = player.getPlayerPositionX();
            int attackY = player.getPlayerPositionY() + 10;

            if (keyHandler.lastPoseLeft) {
                attackX -= 5;
            } else if (keyHandler.lastPoseRight) {
                attackX += 25;
            }

            g2.setColor(Color.GREEN);
            g2.fillRect(attackX, attackY, (int) (player.getPlayerSizeWidth() / 1.4), (int) (player.getPlayerSizeHeight() / 1.4));
        }
    }

    public void hudDisplay(Graphics2D g2) {
        
        // MANA hud display
        g2.setColor(Color.BLUE);
        g2.fillRect(108, 60, (hud.getPlayerMana() * 2) - 6, 10);

        // STAMINA hud display
        g2.setColor(Color.ORANGE);
        g2.fillRect(101, 83, (hud.getPlayerStamina() * 2) - 51, 15);

        // main HUD
        g2.drawImage(sheets.getHudIcon().getImage(), 5, -110, 325,325, this);
    }



}