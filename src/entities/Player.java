package entities;

import systems.*;
import ui.*;
import core.*;

public class Player{

    private KeyHandler keyHandler;
    private HUD hud;
    private GameState gameState;

    private int playerPositionX, playerPositionY;
    private int playerSizeWidth = 60;
    private int playerSizeHeight = 60;

    public Player(KeyHandler keyHandler, HUD hud, GameState gameState, int panelWidth, int panelHeight){
        this.keyHandler = keyHandler;
        this.hud = hud;
        this.gameState = gameState;

        this.playerPositionX = (panelWidth - playerSizeWidth) / 2;
        this.playerPositionY = (panelHeight - playerSizeHeight) / 2;
    }
    
    public void playerMovement(){
        double playerSpeed = 1;
        if(hud.getPlayerStamina() >= 0 && !gameState.outOfStamina){
            if(keyHandler.isShift) playerSpeed = 2.5;
        }
        
        if(keyHandler.isUp) playerPositionY -= playerSpeed;
        if(keyHandler.isDown) playerPositionY += playerSpeed;
        if(keyHandler.isRight) playerPositionX += playerSpeed;
        if(keyHandler.isLeft) playerPositionX -= playerSpeed;
    }

    public int getPlayerPositionX() {
        return playerPositionX;
    }

    public int getPlayerPositionY() {
        return playerPositionY;
    }

    public int getPlayerSizeHeight() {
        return playerSizeHeight;
    }

    public int getPlayerSizeWidth() {
        return playerSizeWidth;
    }

}