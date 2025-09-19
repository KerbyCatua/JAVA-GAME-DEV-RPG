package core;

public class GameState {
    public boolean thumbNailIsPressed = false;

    // HUD player
    public boolean outOfStamina = false;
    public static boolean outOfMana = false;

    public static boolean isInGreenLandMap = true; // main camp
    public static boolean isInSnowLandMap = false;
    public static boolean isInDarkLandMap = false;
    public static boolean isInDryLandMap = false;

    public static boolean cannotMoveRightDueToCollision = false;
    public static boolean cannotMoveLeftDueToCollision = false;
    public static boolean cannotMoveUpDueToCollision = false;
    public static boolean cannotMoveDownDueToCollision = false;
    
}