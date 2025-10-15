package core;

import entities.*;
import systems.*;
import ui.*;
import assets.*;
import world.*;

import javax.swing.*;
import java.awt.*;
// import java.awt.geom.AffineTransform;

// TODO CAMERA SYSTEM 
import java.awt.geom.AffineTransform;

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
    Enemy enemy = new Enemy(gameState, player, this, sheets, combatSystem, hud, keyHandler);

    public GamePanel(){
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        this.setDoubleBuffered(true);
        
        addKeyListener(keyHandler);
        setFocusable(true);

        this.setBackground(Color.BLACK);
        
        Timer timer = new Timer(41, e -> {
            player.playerMovementAndHitbox();

            combatSystem.playerAttackedFunc();

            collisionDetector.mapCollision();

            enemy.updateWolfPositionAndWolfAttack();
            enemy.updateSkeletonPositionAndSkeletonAttack();
            enemy.updateMummyPositionAndMummyAttack();
            repaint();
        });
        timer.start();

        Timer hudCostSkillsTimer = new Timer(500, e -> {
            // PASSIVE HUD MANA, HEALTH, STAMINA
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
            g2.translate(getWidth()/2.15, getHeight()/2.2);   
            g2.scale(1.25, 1.25);
            g2.translate(-player.getPlayerPositionX(), -player.getPlayerPositionY());
            
            // map location
            if(GameState.isInGreenLandMap) gameMap.greenLandMap(g2);
            if(GameState.isInSnowLandMap) gameMap.snowLandMap(g2);
            if(GameState.isInDryLandMap) gameMap.dryLandMap(g2);
            if(GameState.isInDarkLandMap) gameMap.darkLandMap(g2); //layer 0

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

            gameMap.objectsToDrawLayerZero(g2); // layer 0
            playerDisplay(g2); //layer 1
            // playerAttacks(g2);
            enemy.enemyDeployState(g2);
            gameMap.objectsToDrawLayerOne(g2); //layer 2

            // TODO  AMAMAYA
            g2.drawImage(sheets.getCloudsMapOpeningEffect().getImage(), 0, 0, 800, 600, this);
            // TODO CAMERA SYSTEM
            g2.setTransform(new AffineTransform());
            hudDisplay(g2);
            
        
        }

    }


    public void thumbNailDisplay(Graphics2D g2) {

        // TODO REPLACE IMAGE THUMBNAIL / TO DELETE
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        String message = "Press any key to continue";
        g2.drawString(message, (panelWidth / 2) - 130, panelHeight / 2);

        // g2.drawImage(sheets.getThumbNailIcon().getImage(), 0, 0, getWidth(), getHeight(),null);

    }

    public void playerDisplay(Graphics2D g2) {

        ImageIcon imageToDraw;
        if(CombatSystem.playerAttacked){
            imageToDraw = (keyHandler.lastPoseLeft)
                        ? sheets.getPlayerPunchLeft() : sheets.getPlayerPunchRight();
        }else if (keyHandler.isDown || keyHandler.isUp) {
            if(keyHandler.lastPoseLeft){
                imageToDraw = (keyHandler.isShift && !GameState.outOfMana)
                           ? sheets.getRunningLeft() : sheets.getWalkingLeft();
            }else{
                imageToDraw = (keyHandler.isShift && !GameState.outOfMana)
                           ? sheets.getRunningRight() : sheets.getWalkingRight();
            }
        }
        else if (keyHandler.isLeft) {
            imageToDraw = (keyHandler.isShift && !GameState.outOfMana)
                           ? sheets.getRunningLeft() : sheets.getWalkingLeft();
        }
        else if (keyHandler.isRight) {
            imageToDraw = (keyHandler.isShift && !GameState.outOfMana)
                           ? sheets.getRunningRight() : sheets.getWalkingRight();
        }
        else if (keyHandler.lastPoseLeft) {
            imageToDraw = sheets.getLeftPoseIdle();
        }
        else {
            imageToDraw = sheets.getRightPoseIdle();
        }

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

        // HEALTH HUD DISPLAY
        g2.setColor(new Color(90, 89, 90));
        g2.fillRect(110, 30, (int) (100 * 1.9), 30);
        g2.setColor(new Color(220, 42, 42));
        g2.fillRect(110, 30, (int) (hud.getPlayerHealth() * 1.9), 30);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 10));
        g2.drawString(String.valueOf("HP: " + (int) hud.getPlayerHealth()) + " / 100", 125, 50);
        
        // MANA HUD DISPLAY
        g2.setColor(new Color(90, 89, 90));
        g2.fillRect(108, 60, (100 * 2) - 6, 10);
        g2.setColor(new Color(73, 154, 201));
        g2.fillRect(108, 60, (hud.getPlayerMana() * 2) - 6, 10);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 9));
        g2.drawString(String.valueOf("MP: " + (hud.getPlayerMana() - 1)) + " / 100", 120, 68);

        // STAMINA HUD DISPLAY
        g2.setColor(new Color(90, 89, 90));
        g2.fillRect(103, 83, (int) (100 * 1.5), 15);
        g2.setColor(new Color(237, 127, 2));
        g2.fillRect(103, 83, (int) (hud.getPlayerStamina() * 1.5), 15);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 9));
        g2.drawString(String.valueOf("SP: " + (hud.getPlayerStamina() - 1)) + " / 100", 115, 90);

        // MAIN HUD
        g2.drawImage(sheets.getHudIcon().getImage(), 5, -110, 325,325, this);

    }

}