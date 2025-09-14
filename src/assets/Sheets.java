package assets;

import javax.swing.*;

public class Sheets {

    
    ImageIcon rightPoseIdle;
    ImageIcon leftPoseIdle;

    ImageIcon hudIcon;
    ImageIcon thumbNailIcon;

    public Sheets(){
        this.hudIcon = new ImageIcon("C:\\Users\\Kerby\\OneDrive\\Desktop\\RPG GAME\\RPG\\sheets\\hud\\bar.gif");
        this.thumbNailIcon = new ImageIcon("C:\\Users\\Kerby\\OneDrive\\Desktop\\RPG GAME\\RPG\\sheets\\thumbnail\\Thumbnail.gif");
        
        this.rightPoseIdle = new ImageIcon("C:\\Users\\Kerby\\OneDrive\\Desktop\\RPG GAME\\RPG\\sheets\\player\\Last_Pose_Right.gif");
        this.leftPoseIdle = new ImageIcon("C:\\Users\\Kerby\\OneDrive\\Desktop\\RPG GAME\\RPG\\sheets\\player\\Last_Pose_Left.gif");
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

}
