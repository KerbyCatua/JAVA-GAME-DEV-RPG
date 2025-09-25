package assets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Sheets {

    // ENEMY WOLF
    ImageIcon wolfWalkingLeft, wolfWalkingRight; 
    ImageIcon wolfLeftHurt, wolfRightHurt;  
    ImageIcon wolfLeftAttack, wolfRightAttack; 
    ImageIcon wolfLeftDeath, wolfRightDeath;

    // ENEMY SKELETON
    ImageIcon skeletonWalkingLeft, skeletonWalkingRight; 
    ImageIcon skeletonLeftHurt, skeletonRightHurt;  
    ImageIcon skeletonLeftAttack, skeletonRightAttack; 
    ImageIcon skeletonLeftDeath, skeletonRightDeath;

    // PLAYER INGGO
    ImageIcon walkingLeft, walkingRight, runningLeft, runningRight;
    ImageIcon rightPoseIdle, leftPoseIdle;
    ImageIcon playerPunchLeft, playerPunchRight;
    ImageIcon playerHurtLeft, playerHurtRight;

    ImageIcon hudIcon;
    ImageIcon thumbNailIcon;

    ImageIcon playerShadow;

    ImageIcon wholeMapObjectsLayerZero;
    ImageIcon wholeMapObjectsLayerOne;
    BufferedImage wholeMap;

    public Sheets(){
        try {
            
            wholeMap = ImageIO.read(new File("sheets/map/Whole_Map.png"));

        } catch (IOException e) {
            e.getStackTrace();
        }

        this.skeletonWalkingLeft = new ImageIcon("sheets\\enemies\\map4\\skeleton\\walking_left.gif");
        this.skeletonWalkingRight = new ImageIcon("sheets\\enemies\\map4\\skeleton\\walking_right.gif");

        this.skeletonLeftHurt = new ImageIcon("sheets\\enemies\\map4\\skeleton\\hurt_left.gif");
        this.skeletonRightHurt = new ImageIcon("sheets\\enemies\\map4\\skeleton\\hurt_right.gif");

        this.skeletonLeftAttack = new ImageIcon("sheets\\enemies\\map4\\skeleton\\attack_left.gif");
        this.skeletonRightAttack = new ImageIcon("sheets\\enemies\\map4\\skeleton\\attack_right.gif");

        this.skeletonLeftDeath = new ImageIcon("sheets\\enemies\\map4\\skeleton\\death_left.gif");
        this.skeletonRightDeath = new ImageIcon("sheets\\enemies\\map4\\skeleton\\death_right.gif");

        this.playerHurtLeft = new ImageIcon("sheets/player/Hurt_Left.gif");
        this.playerHurtRight = new ImageIcon("sheets/player/Hurt_Right.gif");

        this.wolfRightDeath = new ImageIcon("sheets/enemies/map2/wolf/wolf_right_death.gif");
        this.wolfLeftDeath = new ImageIcon("sheets/enemies/map2/wolf/wolf_left_death.gif");

        this.wolfLeftHurt = new ImageIcon("sheets/enemies/map2/wolf/wolf_left_hurt.gif");
        this.wolfRightHurt = new ImageIcon("sheets/enemies/map2/wolf/wolf_right_hurt.gif");

        this.wolfLeftAttack = new ImageIcon("sheets/enemies/map2/wolf/wolf_left_attack.gif");
        this.wolfRightAttack = new ImageIcon("sheets/enemies/map2/wolf/wolf_right_attack.gif");

        this.wolfWalkingLeft = new ImageIcon("sheets/enemies/map2/wolf/wolf_left_walk.gif");
        this.wolfWalkingRight = new ImageIcon("sheets/enemies/map2/wolf/wolf_right_walk.gif");

        this.wholeMapObjectsLayerZero = new ImageIcon("sheets//map//Layer 0.png");
        this.wholeMapObjectsLayerOne = new ImageIcon("sheets/map/Layer 1.png");

        this.playerPunchRight = new ImageIcon("sheets/player/punch_right.gif");
        this.playerPunchLeft = new ImageIcon("sheets/player/punch_left.gif");
        
        this.runningLeft = new ImageIcon("sheets/player/Running_Left.gif");
        this.runningRight = new ImageIcon("sheets/player/Running_Right.gif");

        this.walkingRight = new ImageIcon("sheets/player/Walking_Right.gif");
        this.walkingLeft = new ImageIcon("sheets/player/Walking_Left.gif");

        this.playerShadow = new ImageIcon("sheets/player/Player_Shadow.png");

        this.hudIcon = new ImageIcon("sheets/hud/bar.gif");
        this.thumbNailIcon = new ImageIcon("sheets/thumbnail/Thumbnail.gif");
        
        this.rightPoseIdle = new ImageIcon("sheets/player/Idle_Right.gif");
        this.leftPoseIdle = new ImageIcon("sheets/player/Idle_Left.gif");
    }

    public ImageIcon getRightPoseIdle() {
        return rightPoseIdle;
    }

    public ImageIcon getLeftPoseIdle() {
        return leftPoseIdle;
    }

    public ImageIcon getHudIcon() {
        return hudIcon;
    }

    public ImageIcon getThumbNailIcon() {
        return thumbNailIcon;
    }

    public ImageIcon getPlayerShadow() {
        return playerShadow;
    }

    public BufferedImage getWholeMap() {
        return wholeMap;
    }

    public ImageIcon getWalkingLeft() {
        return walkingLeft;
    }
    public ImageIcon getWalkingRight() {
        return walkingRight;
    }

    public ImageIcon getRunningLeft() {
        return runningLeft;
    }

    public ImageIcon getRunningRight() {
        return runningRight;
    }

    public ImageIcon getWholeMapObjectsLayerOne() {
        return wholeMapObjectsLayerOne;
    }

    public ImageIcon getWholeMapObjectsLayerZero() {
        return wholeMapObjectsLayerZero;
    }

    public ImageIcon getWolfWalkingLeft() {
        return wolfWalkingLeft;
    }
    public ImageIcon getWolfWalkingRight() {
        return wolfWalkingRight;
    }

    public ImageIcon getPlayerPunchLeft() {
        return playerPunchLeft;
    }

    public ImageIcon getPlayerPunchRight() {
        return playerPunchRight;
    }

    public ImageIcon getWolfLeftHurt() {
        return wolfLeftHurt;
    }

    public ImageIcon getWolfRightHurt() {
        return wolfRightHurt;
    }

    public ImageIcon getWolfLeftAttack() {
        return wolfLeftAttack;
    }
    public ImageIcon getWolfRightAttack() {
        return wolfRightAttack;
    }

    public ImageIcon getWolfLeftDeath() {
        return wolfLeftDeath;
    }

    public ImageIcon getWolfRightDeath() {
        return wolfRightDeath;
    }

    public ImageIcon getPlayerHurtLeft() {
        return playerHurtLeft;
    }

    public ImageIcon getPlayerHurtRight() {
        return playerHurtRight;
    }

    public ImageIcon getSkeletonLeftAttack() {
        return skeletonLeftAttack;
    }
    public ImageIcon getSkeletonLeftDeath() {
        return skeletonLeftDeath;
    }
    public ImageIcon getSkeletonLeftHurt() {
        return skeletonLeftHurt;
    }
    public ImageIcon getSkeletonRightAttack() {
        return skeletonRightAttack;
    }
    public ImageIcon getSkeletonRightDeath() {
        return skeletonRightDeath;
    }
    public ImageIcon getSkeletonRightHurt() {
        return skeletonRightHurt;
    }
    public ImageIcon getSkeletonWalkingLeft() {
        return skeletonWalkingLeft;
    }
    public ImageIcon getSkeletonWalkingRight() {
        return skeletonWalkingRight;
    }

}
