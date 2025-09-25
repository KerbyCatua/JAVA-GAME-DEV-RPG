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

    public static boolean wolfOneChasing = false, wolfTwoChasing = false, wolfThreeChasing = false;
    public static boolean wolfOneHitbox = false, wolfTwoHitbox = false, wolfThreeHitbox = false;
    public static boolean wolfOneIsAlive = true, wolfTwoIsAlive = true, wolfThreeIsAlive = true;

    public static boolean skeletonOneChasing = false, skeletonTwoChasing = false, skeletonThreeChasing = false;
    public static boolean skeletonOneHitbox = false, skeletonTwoHitbox = false, skeletonThreeHitbox = false;
    public static boolean skeletonOneIsAlive = true, skeletonTwoIsAlive = true, skeletonThreeIsAlive = true;

}