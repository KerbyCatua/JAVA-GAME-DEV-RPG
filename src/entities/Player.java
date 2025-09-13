package entities;

import systems.*;

public class Player {

    private KeyHandler keyHandler;

    public Player(KeyHandler keyHandler){
        this.keyHandler = keyHandler;
    }
    
    private int playerPositionX = 0;
    private int playerPositionY = 0;
    private int playerSizeWidth = 60;
    private int playerSizeHeight = 60;

    public void playerMovement(){
        double playerSpeed = (keyHandler.isShift) ? 2.5 : 1;
        if(keyHandler.isUp) playerPositionY -= playerSpeed;
        else if(keyHandler.isDown) playerPositionY += playerSpeed;
        else if(keyHandler.isRight) playerPositionX += playerSpeed;
        else if(keyHandler.isLeft) playerPositionX -= playerSpeed;
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