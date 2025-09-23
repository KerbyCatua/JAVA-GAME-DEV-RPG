package assets;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Sheets {

    ImageIcon walkingLeft, walkingRight, runningLeft, runningRight;
    
    ImageIcon rightPoseIdle, leftPoseIdle;

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

        this.wholeMapObjectsLayerZero = new ImageIcon("sheets//map//Layer 0.png");
        this.wholeMapObjectsLayerOne = new ImageIcon("sheets/map/Layer 1.png");

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

}
