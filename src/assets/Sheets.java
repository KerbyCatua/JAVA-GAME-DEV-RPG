package assets;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Sheets {

    // ENEMY MUMMY
    ImageIcon mummyWalkingLeft, mummyWalkingRight;
    ImageIcon mummyLeftHurt, mummyRightHurt;  
    ImageIcon mummyLeftAttack, mummyRightAttack;
    ImageIcon mummyLeftDeath, mummyRightDeath;

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

    ImageIcon cloudsMapOpeningEffect, cloudsMapIdleEffect;

    ImageIcon wholeMapObjectsLayerOneGreen, wholeMapObjectsLayerOneDark, wholeMapObjectsLayerOneSnow, wholeMapObjectsLayerOneDry;
    ImageIcon wholeMapObjectsLayerZeroGreen, wholeMapObjectsLayerZeroDark, wholeMapObjectsLayerZeroSnow, wholeMapObjectsLayerZeroDry;

    ImageIcon wholeMapObjectsLayerZero;
    ImageIcon wholeMapObjectsLayerOne;
    BufferedImage wholeMap, wholeMapInGreen, wholeMapInDark, wholeMapInSnow, wholeMapInDry;

    public Sheets(){
        try {

            //wholeMapInGreen, wholeMapInDark, wholeMapInSnow, wholeMapInDry
            wholeMapInGreen = ImageIO.read(new File("sheets\\map\\Whole_Map_Green.png"));
            wholeMapInDark = ImageIO.read(new File("sheets\\map\\Whole_Map_Dark.png"));
            wholeMapInSnow = ImageIO.read(new File("sheets\\map\\Whole_Map_Snow.png"));
            wholeMapInDry = ImageIO.read(new File("sheets\\map\\Whole_Map_Desert.png"));
            wholeMap = ImageIO.read(new File("sheets/map/Whole_Map.png"));

        } catch (IOException e) {
            e.getStackTrace();
        }

        this.mummyWalkingLeft = new ImageIcon("sheets/enemies/map3/mummy/walking_left.gif");
        this.mummyWalkingRight = new ImageIcon("sheets/enemies/map3/mummy/walking_right.gif");

        this.mummyLeftHurt = new ImageIcon("sheets/enemies/map3/mummy/hurt_left.gif");
        this.mummyRightHurt = new ImageIcon("sheets/enemies/map3/mummy/hurt_right.gif");

        this.mummyLeftAttack = new ImageIcon("sheets/enemies/map3/mummy/attack_left.gif");
        this.mummyRightAttack = new ImageIcon("sheets/enemies/map3/mummy/attack_right.gif");

        this.mummyLeftDeath = new ImageIcon("sheets/enemies/map3/mummy/death_left.gif");
        this.mummyRightDeath = new ImageIcon("sheets/enemies/map3/mummy/death_right.gif");

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

        this.cloudsMapOpeningEffect = new ImageIcon("sheets\\map\\clouds_opening.gif");
        this.cloudsMapIdleEffect = new ImageIcon("sheets\\map\\clouds_idle.gif");

        // OBJECTS LAYER 1 AND 0
        this.wholeMapObjectsLayerOneGreen = new ImageIcon("sheets\\map\\GREEN LAYER 1.png");
        this.wholeMapObjectsLayerOneDark = new ImageIcon("sheets\\map\\DARK LAYER 1.png");
        this.wholeMapObjectsLayerOneSnow = new ImageIcon("sheets\\map\\SNOW LAYER 1.png");
        this.wholeMapObjectsLayerOneDry = new ImageIcon("sheets\\map\\DRY LAYER 1.png");
        this.wholeMapObjectsLayerZeroGreen = new ImageIcon("sheets\\map\\GREEN LAYER 0.png");
        this.wholeMapObjectsLayerZeroDark = new ImageIcon("sheets\\map\\DARK LAYER 0.png");
        this.wholeMapObjectsLayerZeroSnow = new ImageIcon("sheets\\map\\SNOW LAYER 0.png");
        this.wholeMapObjectsLayerZeroDry = new ImageIcon("sheets\\map\\DRY LAYER 0.png");


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

    public ImageIcon getMummyLeftAttack() {
        return mummyLeftAttack;
    }
    public ImageIcon getMummyLeftDeath() {
        return mummyLeftDeath;
    }
    public ImageIcon getMummyLeftHurt() {
        return mummyLeftHurt;
    }
    public ImageIcon getMummyRightAttack() {
        return mummyRightAttack;
    }
    public ImageIcon getMummyRightDeath() {
        return mummyRightDeath;
    }
    public ImageIcon getMummyRightHurt() {
        return mummyRightHurt;
    }
    public ImageIcon getMummyWalkingLeft() {
        return mummyWalkingLeft;
    }
    public ImageIcon getMummyWalkingRight() {
        return mummyWalkingRight;
    }

    public ImageIcon getCloudsMapIdleEffect() {
        return cloudsMapIdleEffect;
    }
    public ImageIcon getCloudsMapOpeningEffect() {
        return cloudsMapOpeningEffect;
    }
    public BufferedImage getWholeMapInDark() {
        return wholeMapInDark;
    }
    public BufferedImage getWholeMapInDry() {
        return wholeMapInDry;
    }
    public BufferedImage getWholeMapInGreen() {
        return wholeMapInGreen;
    }

    public BufferedImage getWholeMapInSnow() {
        return wholeMapInSnow;
    }
    public ImageIcon getWholeMapObjectsLayerOneDark() {
        return wholeMapObjectsLayerOneDark;
    }
    public ImageIcon getWholeMapObjectsLayerOneDry() {
        return wholeMapObjectsLayerOneDry;
    }
    public ImageIcon getWholeMapObjectsLayerOneGreen() {
        return wholeMapObjectsLayerOneGreen;
    }
    public ImageIcon getWholeMapObjectsLayerOneSnow() {
        return wholeMapObjectsLayerOneSnow;
    }
    public ImageIcon getWholeMapObjectsLayerZeroDark() {
        return wholeMapObjectsLayerZeroDark;
    }
    public ImageIcon getWholeMapObjectsLayerZeroDry() {
        return wholeMapObjectsLayerZeroDry;
    }
    public ImageIcon getWholeMapObjectsLayerZeroGreen() {
        return wholeMapObjectsLayerZeroGreen;
    }
    public ImageIcon getWholeMapObjectsLayerZeroSnow() {
        return wholeMapObjectsLayerZeroSnow;
    }

}
