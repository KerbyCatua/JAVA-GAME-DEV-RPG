package entities;

import systems.*;
import ui.*;

import java.awt.Rectangle;

import core.*;

public class Player{

    private KeyHandler keyHandler;
    private HUD hud;
    private GameState gameState;

    private int playerPositionX, playerPositionY;
    private int playerSizeWidth = 60;
    private int playerSizeHeight = 60;
    public static Rectangle playerHitbox;

    public Player(KeyHandler keyHandler, HUD hud, GameState gameState, int panelWidth, int panelHeight){

        this.keyHandler = keyHandler;
        this.hud = hud;
        this.gameState = gameState;

        this.playerPositionX = (panelWidth - playerSizeWidth) / 2;
        this.playerPositionY = (panelHeight - playerSizeHeight) / 2;
    }
    
    public void playerMovementAndHitbox(){
        double playerSpeed = 15; // TODO CHANGE TO ORIG 2
        if(hud.getPlayerStamina() >= 0 && !gameState.outOfStamina){
            if(keyHandler.isShift) playerSpeed = 4; // Shift Speed
        }
        
        if(keyHandler.isUp && !CombatSystem.playerAttacked && !GameState.cannotMoveUpDueToCollision ) playerPositionY -= playerSpeed;
        if(keyHandler.isDown && !CombatSystem.playerAttacked && !GameState.cannotMoveDownDueToCollision ) playerPositionY += playerSpeed;
        if(keyHandler.isRight && !CombatSystem.playerAttacked && !GameState.cannotMoveRightDueToCollision ) playerPositionX += playerSpeed;
        if(keyHandler.isLeft && !CombatSystem.playerAttacked && !GameState.cannotMoveLeftDueToCollision ) playerPositionX -= playerSpeed;

                                    //  offset & size   
        playerHitbox = new Rectangle(getPlayerPositionX() + 20, getPlayerPositionY() + 20, (getPlayerSizeWidth() - 41), (getPlayerSizeHeight()) - 27);
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