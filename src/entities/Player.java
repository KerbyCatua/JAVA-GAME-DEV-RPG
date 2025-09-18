package entities;

import systems.*;
import ui.*;
import core.*;

public class Player{

    private KeyHandler keyHandler;
    private HUD hud;
    private GameState gameState;

    private int playerPositionX, playerPositionY;
    private int playerSizeWidth = 50;
    private int playerSizeHeight = 50;

    public Player(KeyHandler keyHandler, HUD hud, GameState gameState, int panelWidth, int panelHeight){
        this.keyHandler = keyHandler;
        this.hud = hud;
        this.gameState = gameState;

        this.playerPositionX = (panelWidth - playerSizeWidth) / 2;
        this.playerPositionY = (panelHeight - playerSizeHeight) / 2;
    }
    
    public void playerMovement(){
        double playerSpeed = 2;
        if(hud.getPlayerStamina() >= 0 && !gameState.outOfStamina){
            if(keyHandler.isShift) playerSpeed = 5; // Shift Speed
        }
        
        if(keyHandler.isUp && !CombatSystem.playerAttacked) playerPositionY -= playerSpeed;
        if(keyHandler.isDown && !CombatSystem.playerAttacked) playerPositionY += playerSpeed;
        if(keyHandler.isRight && !CombatSystem.playerAttacked) playerPositionX += playerSpeed;
        if(keyHandler.isLeft && !CombatSystem.playerAttacked) playerPositionX -= playerSpeed;
    }

    public int getPlayerPositionX() {
        return playerPositionX;
    }

    public void setPlayerPositionX(int playerPositionX) {
        this.playerPositionX = playerPositionX;
    }

    public int getPlayerPositionY() {
        return playerPositionY;
    }

    public void setPlayerPositionY(int playerPositionY) {
        this.playerPositionY = playerPositionY;
    }

    public int getPlayerSizeHeight() {
        return playerSizeHeight;
    }

    public int getPlayerSizeWidth() {
        return playerSizeWidth;
    }

}