package assets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Sheets {

    
    ImageIcon rightPoseIdle;
    ImageIcon leftPoseIdle;

    ImageIcon hudIcon;
    ImageIcon thumbNailIcon;

    ImageIcon playerShadow;

    BufferedImage wholeMap;

    public Sheets(){
        try {
            
            wholeMap = ImageIO.read(new File("sheets/map/Whole_Map.png"));

        } catch (IOException e) {
            e.getStackTrace();
        }

        this.playerShadow = new ImageIcon("sheets/player/Player_Shadow.png");

        this.hudIcon = new ImageIcon("sheets/hud/bar.gif");
        this.thumbNailIcon = new ImageIcon("sheets/thumbnail/Thumbnail.gif");
        
        this.rightPoseIdle = new ImageIcon("sheets/player/Last_Pose_Right.gif");
        this.leftPoseIdle = new ImageIcon("sheets/player/Last_Pose_Left.gif");
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

}
