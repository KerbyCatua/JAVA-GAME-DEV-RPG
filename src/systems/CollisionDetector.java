package systems;

import java.awt.*;

import core.*;
import entities.*;

public class CollisionDetector {

    GamePanel gamePanel;
    public CollisionDetector(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void mapCollision() {

        // TOP LEFT MAP
        if(GameState.isInGreenLandMap){

            // OBJECT 1
            Rectangle objectOneDownWall = new Rectangle(0, gamePanel.getHeight() - 235, 210, 10);
            Rectangle objectOneLeftWall = new Rectangle(200, gamePanel.getHeight() - 225, 10, 225 + 60);

            // MAP COLLISION
            Rectangle collisionWallLeft = new Rectangle(-55, 0, 60, gamePanel.getHeight() + 60);
            Rectangle collisionWallTop = new Rectangle(0, -55, gamePanel.getWidth() + 60, 60);

            // DOWN COLLISION
            if(Player.playerHitbox.intersects(objectOneDownWall)){
                GameState.cannotMoveDownDueToCollision = true;
            }else GameState.cannotMoveDownDueToCollision = false;

            // LEFT COLLISION
            if(Player.playerHitbox.intersects(collisionWallLeft) 
               || Player.playerHitbox.intersects(objectOneLeftWall)){
                GameState.cannotMoveLeftDueToCollision = true;
            }else GameState.cannotMoveLeftDueToCollision = false;

            // UP COLLISION
            if(Player.playerHitbox.intersects(collisionWallTop)){
                GameState.cannotMoveUpDueToCollision = true;
            }else GameState.cannotMoveUpDueToCollision = false;

        }

        // TOP RIGHT MAP
        if(GameState.isInDarkLandMap){

            Rectangle collisionWallRight = new Rectangle(gamePanel.getWidth() - 5, 0, 60, gamePanel.getHeight() + 60);
            if(Player.playerHitbox.intersects(collisionWallRight)){
                GameState.cannotMoveRightDueToCollision = true;
            }else GameState.cannotMoveRightDueToCollision = false;

            Rectangle collisionWallTop = new Rectangle(-60, -55, gamePanel.getWidth() + 60, 60);
            if(Player.playerHitbox.intersects(collisionWallTop)){
                GameState.cannotMoveUpDueToCollision = true; 
            }else GameState.cannotMoveUpDueToCollision = false; 

        }

        // BOTTOM LEFT MAP
        if(GameState.isInSnowLandMap){

            Rectangle collisionWallLeft = new Rectangle(-55, -60, 60, gamePanel.getHeight() + 60);
            if(Player.playerHitbox.intersects(collisionWallLeft)){
                GameState.cannotMoveLeftDueToCollision = true;
            }else GameState.cannotMoveLeftDueToCollision = false;

            Rectangle collisionWallDown = new Rectangle(0, gamePanel.getHeight() - 5, gamePanel.getWidth() + 60, 60);
            if(Player.playerHitbox.intersects(collisionWallDown)){
                GameState.cannotMoveDownDueToCollision = true;
            }else GameState.cannotMoveDownDueToCollision = false;

        }

        //BOTTOM RIGHT MAP
        if(GameState.isInDryLandMap){

            Rectangle collisionWallDown = new Rectangle(-60, gamePanel.getHeight() - 5, gamePanel.getWidth() + 60, 60);
            if(Player.playerHitbox.intersects(collisionWallDown)){
                GameState.cannotMoveDownDueToCollision = true;
            }else GameState.cannotMoveDownDueToCollision = false;

            Rectangle collisionWallRight = new Rectangle(gamePanel.getWidth() - 5, -60, 60, gamePanel.getHeight() + 60);
            if(Player.playerHitbox.intersects(collisionWallRight)){
                GameState.cannotMoveRightDueToCollision = true;
            }else GameState.cannotMoveRightDueToCollision = false;
        
        }

    }

}
