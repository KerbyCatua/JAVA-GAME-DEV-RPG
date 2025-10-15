package entities;

import java.awt.*;
import javax.swing.*;

import assets.*;
import core.*;
import systems.*;
import ui.*;

public class Enemy {

    GameState gameState;
    Player player;
    GamePanel gamePanel;
    Sheets sheets;
    CombatSystem combatSystem;
    HUD hud;
    KeyHandler keyHandler;

    public Enemy(GameState gameState, Player player, GamePanel gamePanel , Sheets sheets, CombatSystem combatSystem, HUD hud, KeyHandler keyHandler){
        this.gameState = gameState;
        this.player = player;
        this.gamePanel = gamePanel;
        this.sheets = sheets;
        this.combatSystem = combatSystem;
        this.hud = hud;
        this.keyHandler = keyHandler;
    }

    public void enemyDeployState(Graphics2D g2) {
        
        // DRAW ENEMY AND DAMAGE NUMBER
        if(GameState.isInDarkLandMap) {
            wolfToDraw(g2);

            // g2.setColor(Color.BLACK);
            //     g2.setFont(new Font("Arial", Font.BOLD, 10));
            //     String message = "Out of Stamina!";
            //     g2.drawString
            // g2.setcolor
        }
        if(GameState.isInDryLandMap) skeletonToDraw(g2);
        if(GameState.isInSnowLandMap) mummyToDraw(g2);

    }

    // MUMMY =================================================================
    // POSITION
    private int mummyOnePositionX = 300, mummyTwoPositionX = 500, mummyThreePositionX = 600;
    private int mummyOnePositionY = 275, mummyTwoPositionY = 375, mummyThreePositionY = 200;

    // PATROLLING POSITION
    private int mummyOnePatrolingStartX = 300, mummyTwoPatrolingStartX = 450, mummyThreePatrolingStartX = 525;
    private int mummyOnePatrolingEndingX = 475, mummyTwoPatrolingEndingX = 600, mummyThreePatrolingEndingX = 650;

    // SIZE
    private int mummyOneWidth = 100, mummyTwoWidth = 100, mummyThreeWidth = 100;
    private int mummyOneHeight = 100, mummyTwoHeight = 100, mummyThreeHeight = 100;

    // MOVEMENT AND SPEED
    private int mummyOneSpeed = 2, mummyTwoSpeed = 2, mummyThreeSpeed = 2;

    // HEALTH AND ATTACK
    private int mummyOneHealth = 100, mummyTwoHealth = 100, mummyThreeHealth = 100;
    private double mummyOneAttackDamage = 10, mummyTwoAttackDamage = 10, mummyThreeAttackDamage = 10;

    // ATTACK TIMING
    private long mummyOneAttackDuration, mummyTwoAttackDuration, mummyThreeAttackDuration;
    private long mummyOneAttackCooldown, mummyTwoAttackCooldown, mummyThreeAttackCooldown;
    private boolean mummyOneAttacked = false, mummyTwoAttacked = false, mummyThreeAttacked = false;

    // HIT AND DEATH STATES
    private boolean mummyOneHitted = false, mummyTwoHitted = false, mummyThreeHitted = false;
    private boolean mummyOneDeathAnimationPlayedFlag, mummyTwoDeathAnimationPlayedFlag, mummyThreeDeathAnimationPlayedFlag;
    private long mummyOneDeathAnimationDuration, mummyTwoDeathAnimationDuration, mummyThreeDeathAnimationDuration;
    private boolean mummyOneDeathTriggered = false, mummyTwoDeathTriggered = false, mummyThreeDeathTriggered = false;

    // HITBOXES AND ANIMATION
    Rectangle mummyOneChaseBox, mummyTwoChaseBox, mummyThreeChaseBox;
    Rectangle mummyOneHitBox, mummyTwoHitBox, mummyThreeHitBox;
    Rectangle mummyOneAttackRangeBox, mummyTwoAttackRangeBox, mummyThreeAttackRangeBox;
    ImageIcon mummyOneToDraw, mummyTwoToDraw, mummyThreeToDraw;

    public void updateMummyPositionAndMummyAttack() {
        
        if(!GameState.isInSnowLandMap) return;

        // MUMMY ONE START
        if (getMummyOneHealth() <= 0 && !mummyOneDeathTriggered) {
            GameState.mummyOneIsAlive = false;
            mummyOneDeathAnimationPlayedFlag = true;
            mummyOneDeathAnimationDuration = System.currentTimeMillis();
            mummyOneDeathTriggered = true;
        }
        if (GameState.mummyOneIsAlive) {
            if (!GameState.mummyOneChasing) {
                mummyOnePositionX += mummyOneSpeed;
                if (mummyOnePositionX >= mummyOnePatrolingEndingX || mummyOnePositionX <= mummyOnePatrolingStartX) {
                    mummyOneSpeed *= -1;
                }
            }

            if (GameState.mummyOneChasing) {
                mummyOneSpeed = 2;
                int tolerance = 5;
                if (!GameState.mummyOneHitbox && !mummyOneHitted) {
                    // Move X
                    if (mummyOnePositionX < player.getPlayerPositionX() - tolerance) {
                        mummyOnePositionX += mummyOneSpeed;
                    } else if (mummyOnePositionX > player.getPlayerPositionX() + tolerance) {
                        mummyOnePositionX -= mummyOneSpeed;
                    }

                    // Move Y
                    if (mummyOnePositionY < player.getPlayerPositionY() - tolerance) {
                        mummyOnePositionY += mummyOneSpeed;
                    } else if (mummyOnePositionY > player.getPlayerPositionY() + tolerance) {
                        mummyOnePositionY -= mummyOneSpeed;
                    }
                }

                mummyOneAttackRangeBox = new Rectangle(mummyOnePositionX + 10, mummyOnePositionY + 10, 80, 80);
                if (Player.playerHitbox.intersects(mummyOneAttackRangeBox) && System.currentTimeMillis() - mummyOneAttackCooldown >= 1750) {
                    mummyOneAttacked = true;
                    mummyOneAttackDuration = System.currentTimeMillis();
                    mummyOneAttackCooldown = System.currentTimeMillis();
                    System.out.println("mummy one attacked!!!");
                }

                if (mummyOneAttacked && System.currentTimeMillis() - mummyOneAttackDuration >= 750) {
                    mummyOneAttacked = false;
                }

                if (mummyOneAttacked) {
                    double balancedMummyDamage = mummyOneAttackDamage * 0.025;
                    hud.setPlayerHealth(hud.getPlayerHealth() - balancedMummyDamage);
                }

                // PLAYER SKILLS / PUNCH
                if (combatSystem.playerAttackRange.intersects(mummyOneHitBox) && CombatSystem.playerAttacked) {
                    setMummyOneHealth(getMummyOneHealth() - (int) (combatSystem.playerPunchDamage * 0.1));
                    mummyOneHitted = true;
                } else {
                    mummyOneHitted = false;
                }

                if (mummyOneHitted) {
                    if (keyHandler.lastPoseLeft) mummyOnePositionX -= 3;
                    if (keyHandler.lastPoseRight) mummyOnePositionX += 3;
                }
            }

            mummyOneChaseBox = new Rectangle(mummyOnePositionX - 50, mummyOnePositionY - 50, 200, 200);
            if (Player.playerHitbox.intersects(mummyOneChaseBox)) {
                GameState.mummyOneChasing = true;
            }

            mummyOneHitBox = new Rectangle(mummyOnePositionX + 35, mummyOnePositionY + 48, 30, 10);
            if (Player.playerHitbox.intersects(mummyOneHitBox)) {
                GameState.mummyOneHitbox = true;
            } else {
                GameState.mummyOneHitbox = false;
            }
        }
        // MUMMY ONE END


        // MUMMY TWO START
        if (getMummyTwoHealth() <= 0 && !mummyTwoDeathTriggered) {
            GameState.mummyTwoIsAlive = false;
            mummyTwoDeathAnimationPlayedFlag = true;
            mummyTwoDeathAnimationDuration = System.currentTimeMillis();
            mummyTwoDeathTriggered = true;
        }
        if (GameState.mummyTwoIsAlive) {
            if (!GameState.mummyTwoChasing) {
                mummyTwoPositionX += mummyTwoSpeed;
                if (mummyTwoPositionX >= mummyTwoPatrolingEndingX || mummyTwoPositionX <= mummyTwoPatrolingStartX) {
                    mummyTwoSpeed *= -1;
                }
            }

            if (GameState.mummyTwoChasing) {
                mummyTwoSpeed = 2;
                int tolerance = 5;
                if (!GameState.mummyTwoHitbox && !mummyTwoHitted) {
                    // Move X
                    if (mummyTwoPositionX < player.getPlayerPositionX() - tolerance) {
                        mummyTwoPositionX += mummyTwoSpeed;
                    } else if (mummyTwoPositionX > player.getPlayerPositionX() + tolerance) {
                        mummyTwoPositionX -= mummyTwoSpeed;
                    }

                    // Move Y
                    if (mummyTwoPositionY < player.getPlayerPositionY() - tolerance) {
                        mummyTwoPositionY += mummyTwoSpeed;
                    } else if (mummyTwoPositionY > player.getPlayerPositionY() + tolerance) {
                        mummyTwoPositionY -= mummyTwoSpeed;
                    }
                }

                mummyTwoAttackRangeBox = new Rectangle(mummyTwoPositionX + 10, mummyTwoPositionY + 10, 80, 80);
                if (Player.playerHitbox.intersects(mummyTwoAttackRangeBox) && System.currentTimeMillis() - mummyTwoAttackCooldown >= 1750) {
                    mummyTwoAttacked = true;
                    mummyTwoAttackDuration = System.currentTimeMillis();
                    mummyTwoAttackCooldown = System.currentTimeMillis();
                    System.out.println("mummy two attacked!!!");
                }

                if (mummyTwoAttacked && System.currentTimeMillis() - mummyTwoAttackDuration >= 750) {
                    mummyTwoAttacked = false;
                }

                if (mummyTwoAttacked) {
                    double balancedMummyDamage = mummyTwoAttackDamage * 0.025;
                    hud.setPlayerHealth(hud.getPlayerHealth() - balancedMummyDamage);
                }

                // PLAYER SKILLS / PUNCH
                if (combatSystem.playerAttackRange.intersects(mummyTwoHitBox) && CombatSystem.playerAttacked) {
                    setMummyTwoHealth(getMummyTwoHealth() - (int) (combatSystem.playerPunchDamage * 0.1));
                    mummyTwoHitted = true;
                } else {
                    mummyTwoHitted = false;
                }

                if (mummyTwoHitted) {
                    if (keyHandler.lastPoseLeft) mummyTwoPositionX -= 3;
                    if (keyHandler.lastPoseRight) mummyTwoPositionX += 3;
                }
            }

            mummyTwoChaseBox = new Rectangle(mummyTwoPositionX - 50, mummyTwoPositionY - 50, 200, 200);
            if (Player.playerHitbox.intersects(mummyTwoChaseBox)) {
                GameState.mummyTwoChasing = true;
            }

            mummyTwoHitBox = new Rectangle(mummyTwoPositionX + 35, mummyTwoPositionY + 48, 30, 10);
            if (Player.playerHitbox.intersects(mummyTwoHitBox)) {
                GameState.mummyTwoHitbox = true;
            } else {
                GameState.mummyTwoHitbox = false;
            }
        }
        // MUMMY TWO END


        // MUMMY THREE START
        if (getMummyThreeHealth() <= 0 && !mummyThreeDeathTriggered) {
            GameState.mummyThreeIsAlive = false;
            mummyThreeDeathAnimationPlayedFlag = true;
            mummyThreeDeathAnimationDuration = System.currentTimeMillis();
            mummyThreeDeathTriggered = true;
        }
        if (GameState.mummyThreeIsAlive) {
            if (!GameState.mummyThreeChasing) {
                mummyThreePositionX += mummyThreeSpeed;
                if (mummyThreePositionX >= mummyThreePatrolingEndingX || mummyThreePositionX <= mummyThreePatrolingStartX) {
                    mummyThreeSpeed *= -1;
                }
            }

            if (GameState.mummyThreeChasing) {
                mummyThreeSpeed = 2;
                int tolerance = 5;
                if (!GameState.mummyThreeHitbox && !mummyThreeHitted) {
                    // Move X
                    if (mummyThreePositionX < player.getPlayerPositionX() - tolerance) {
                        mummyThreePositionX += mummyThreeSpeed;
                    } else if (mummyThreePositionX > player.getPlayerPositionX() + tolerance) {
                        mummyThreePositionX -= mummyThreeSpeed;
                    }

                    // Move Y
                    if (mummyThreePositionY < player.getPlayerPositionY() - tolerance) {
                        mummyThreePositionY += mummyThreeSpeed;
                    } else if (mummyThreePositionY > player.getPlayerPositionY() + tolerance) {
                        mummyThreePositionY -= mummyThreeSpeed;
                    }
                }

                mummyThreeAttackRangeBox = new Rectangle(mummyThreePositionX + 10, mummyThreePositionY + 10, 80, 80);
                if (Player.playerHitbox.intersects(mummyThreeAttackRangeBox) && System.currentTimeMillis() - mummyThreeAttackCooldown >= 1750) {
                    mummyThreeAttacked = true;
                    mummyThreeAttackDuration = System.currentTimeMillis();
                    mummyThreeAttackCooldown = System.currentTimeMillis();
                    System.out.println("mummy three attacked!!!");
                }

                if (mummyThreeAttacked && System.currentTimeMillis() - mummyThreeAttackDuration >= 750) {
                    mummyThreeAttacked = false;
                }

                if (mummyThreeAttacked) {
                    double balancedMummyDamage = mummyThreeAttackDamage * 0.025;
                    hud.setPlayerHealth(hud.getPlayerHealth() - balancedMummyDamage);
                }

                // PLAYER SKILLS / PUNCH
                if (combatSystem.playerAttackRange.intersects(mummyThreeHitBox) && CombatSystem.playerAttacked) {
                    setMummyThreeHealth(getMummyThreeHealth() - (int) (combatSystem.playerPunchDamage * 0.1));
                    mummyThreeHitted = true;
                } else {
                    mummyThreeHitted = false;
                }

                if (mummyThreeHitted) {
                    if (keyHandler.lastPoseLeft) mummyThreePositionX -= 3;
                    if (keyHandler.lastPoseRight) mummyThreePositionX += 3;
                }
            }

            mummyThreeChaseBox = new Rectangle(mummyThreePositionX - 50, mummyThreePositionY - 50, 200, 200);
            if (Player.playerHitbox.intersects(mummyThreeChaseBox)) {
                GameState.mummyThreeChasing = true;
            }

            mummyThreeHitBox = new Rectangle(mummyThreePositionX + 35, mummyThreePositionY + 48, 30, 10);
            if (Player.playerHitbox.intersects(mummyThreeHitBox)) {
                GameState.mummyThreeHitbox = true;
            } else {
                GameState.mummyThreeHitbox = false;
            }
        }
        // MUMMY THREE END

    }

    public void mummyToDraw(Graphics2D g2) {
        
        // MUMMY ONE START
        if(GameState.mummyOneIsAlive){
            g2.setColor(new Color(82, 6, 14));
            g2.fillRect(mummyOnePositionX + 25, mummyOnePositionY + 20, 100 / 2, 4);
            g2.setColor(Color.RED);
            g2.fillRect(mummyOnePositionX + 25, mummyOnePositionY + 20, getMummyOneHealth() / 2, 4);

            if(!GameState.mummyOneChasing){
                if(mummyOneSpeed > 0) mummyOneToDraw = sheets.getMummyWalkingRight();
                else if(mummyOneSpeed < 0) mummyOneToDraw = sheets.getMummyWalkingLeft();
                g2.drawImage(sheets.getPlayerShadow().getImage(), mummyOnePositionX + 20, mummyOnePositionY + 40, 60, 60, gamePanel);
                g2.drawImage(mummyOneToDraw.getImage(), mummyOnePositionX, mummyOnePositionY, mummyOneWidth, mummyOneHeight, gamePanel);
            }

            if(GameState.mummyOneChasing){
                if(player.getPlayerPositionX() > mummyOnePositionX){
                    if(mummyOneHitted){
                        mummyOneToDraw = sheets.getMummyRightHurt();
                    } else {
                        mummyOneToDraw = (mummyOneAttacked) ? sheets.getMummyRightAttack() : sheets.getMummyWalkingRight();
                    }
                } else {
                    if(mummyOneHitted){
                        mummyOneToDraw = sheets.getMummyLeftHurt();
                    } else {
                        mummyOneToDraw = (mummyOneAttacked) ? sheets.getMummyLeftAttack() : sheets.getMummyWalkingLeft();
                    }
                }

                g2.drawImage(sheets.getPlayerShadow().getImage(), mummyOnePositionX + 20, mummyOnePositionY + 40, 60, 60, gamePanel);
                g2.drawImage(mummyOneToDraw.getImage(), mummyOnePositionX, mummyOnePositionY, mummyOneWidth, mummyOneHeight, gamePanel);
            }
        }
        if(!GameState.mummyOneIsAlive && mummyOneDeathAnimationPlayedFlag){
            if(player.getPlayerPositionX() > mummyOnePositionX){
                g2.drawImage(sheets.getMummyRightDeath().getImage(), mummyOnePositionX, mummyOnePositionY, mummyOneWidth, mummyOneHeight, gamePanel);
            } else {
                g2.drawImage(sheets.getMummyLeftDeath().getImage(), mummyOnePositionX, mummyOnePositionY, mummyOneWidth, mummyOneHeight, gamePanel);
            }

            if (System.currentTimeMillis() - mummyOneDeathAnimationDuration >= 700) {
                mummyOneDeathAnimationPlayedFlag = false;
            }
        }
        // MUMMY ONE END

        // MUMMY TWO START
        if(GameState.mummyTwoIsAlive){
            g2.setColor(new Color(82, 6, 14));
            g2.fillRect(mummyTwoPositionX + 25, mummyTwoPositionY + 20, 100 / 2, 4);
            g2.setColor(Color.RED);
            g2.fillRect(mummyTwoPositionX + 25, mummyTwoPositionY + 20, getMummyTwoHealth() / 2, 4);

            if(!GameState.mummyTwoChasing){
                if(mummyTwoSpeed > 0) mummyTwoToDraw = sheets.getMummyWalkingRight();
                else if(mummyTwoSpeed < 0) mummyTwoToDraw = sheets.getMummyWalkingLeft();
                g2.drawImage(sheets.getPlayerShadow().getImage(), mummyTwoPositionX + 20, mummyTwoPositionY + 40, 60, 60, gamePanel);
                g2.drawImage(mummyTwoToDraw.getImage(), mummyTwoPositionX, mummyTwoPositionY, mummyTwoWidth, mummyTwoHeight, gamePanel);
            }

            if(GameState.mummyTwoChasing){
                if(player.getPlayerPositionX() > mummyTwoPositionX){
                    if(mummyTwoHitted){
                        mummyTwoToDraw = sheets.getMummyRightHurt();
                    } else {
                        mummyTwoToDraw = (mummyTwoAttacked) ? sheets.getMummyRightAttack() : sheets.getMummyWalkingRight();
                    }
                } else {
                    if(mummyTwoHitted){
                        mummyTwoToDraw = sheets.getMummyLeftHurt();
                    } else {
                        mummyTwoToDraw = (mummyTwoAttacked) ? sheets.getMummyLeftAttack() : sheets.getMummyWalkingLeft();
                    }
                }

                g2.drawImage(sheets.getPlayerShadow().getImage(), mummyTwoPositionX + 20, mummyTwoPositionY + 40, 60, 60, gamePanel);
                g2.drawImage(mummyTwoToDraw.getImage(), mummyTwoPositionX, mummyTwoPositionY, mummyTwoWidth, mummyTwoHeight, gamePanel);
            }
        }
        if(!GameState.mummyTwoIsAlive && mummyTwoDeathAnimationPlayedFlag){
            if(player.getPlayerPositionX() > mummyTwoPositionX){
                g2.drawImage(sheets.getMummyRightDeath().getImage(), mummyTwoPositionX, mummyTwoPositionY, mummyTwoWidth, mummyTwoHeight, gamePanel);
            } else {
                g2.drawImage(sheets.getMummyLeftDeath().getImage(), mummyTwoPositionX, mummyTwoPositionY, mummyTwoWidth, mummyTwoHeight, gamePanel);
            }

            if (System.currentTimeMillis() - mummyTwoDeathAnimationDuration >= 700) {
                mummyTwoDeathAnimationPlayedFlag = false;
            }
        }
        // MUMMY TWO END

        // MUMMY THREE START
        if(GameState.mummyThreeIsAlive){
            g2.setColor(new Color(82, 6, 14));
            g2.fillRect(mummyThreePositionX + 25, mummyThreePositionY + 20, 100 / 2, 4);
            g2.setColor(Color.RED);
            g2.fillRect(mummyThreePositionX + 25, mummyThreePositionY + 20, getMummyThreeHealth() / 2, 4);

            if(!GameState.mummyThreeChasing){
                if(mummyThreeSpeed > 0) mummyThreeToDraw = sheets.getMummyWalkingRight();
                else if(mummyThreeSpeed < 0) mummyThreeToDraw = sheets.getMummyWalkingLeft();
                g2.drawImage(sheets.getPlayerShadow().getImage(), mummyThreePositionX + 20, mummyThreePositionY + 40, 60, 60, gamePanel);
                g2.drawImage(mummyThreeToDraw.getImage(), mummyThreePositionX, mummyThreePositionY, mummyThreeWidth, mummyThreeHeight, gamePanel);
            }

            if(GameState.mummyThreeChasing){
                if(player.getPlayerPositionX() > mummyThreePositionX){
                    if(mummyThreeHitted){
                        mummyThreeToDraw = sheets.getMummyRightHurt();
                    } else {
                        mummyThreeToDraw = (mummyThreeAttacked) ? sheets.getMummyRightAttack() : sheets.getMummyWalkingRight();
                    }
                } else {
                    if(mummyThreeHitted){
                        mummyThreeToDraw = sheets.getMummyLeftHurt();
                    } else {
                        mummyThreeToDraw = (mummyThreeAttacked) ? sheets.getMummyLeftAttack() : sheets.getMummyWalkingLeft();
                    }
                }

                g2.drawImage(sheets.getPlayerShadow().getImage(), mummyThreePositionX + 20, mummyThreePositionY + 40, 60, 60, gamePanel);
                g2.drawImage(mummyThreeToDraw.getImage(), mummyThreePositionX, mummyThreePositionY, mummyThreeWidth, mummyThreeHeight, gamePanel);
            }
        }
        if(!GameState.mummyThreeIsAlive && mummyThreeDeathAnimationPlayedFlag){
            if(player.getPlayerPositionX() > mummyThreePositionX){
                g2.drawImage(sheets.getMummyRightDeath().getImage(), mummyThreePositionX, mummyThreePositionY, mummyThreeWidth, mummyThreeHeight, gamePanel);
            } else {
                g2.drawImage(sheets.getMummyLeftDeath().getImage(), mummyThreePositionX, mummyThreePositionY, mummyThreeWidth, mummyThreeHeight, gamePanel);
            }

            if (System.currentTimeMillis() - mummyThreeDeathAnimationDuration >= 700) {
                mummyThreeDeathAnimationPlayedFlag = false;
            }
        }
        // MUMMY THREE END

    }


    // SKELETON =================================================================
    // POSITION
    private int skeletonOnePositionX = 300, skeletonTwoPositionX = 500, skeletonThreePositionX = 600;
    private int skeletonOnePositionY = 275, skeletonTwoPositionY = 375, skeletonThreePositionY = 200;

    // PATROLLING POSITION
    private int skeletonOnePatrolingStartX = 300, skeletonTwoPatrolingStartX = 450, skeletonThreePatrolingStartX = 525;
    private int skeletonOnePatrolingEndingX = 475, skeletonTwoPatrolingEndingX = 600, skeletonThreePatrolingEndingX = 650;

    // SIZE
    private int skeletonOneWidth = 100, skeletonTwoWidth = 100, skeletonThreeWidth = 100;
    private int skeletonOneHeight = 100, skeletonTwoHeight = 100, skeletonThreeHeight = 100;

    // MOVEMENT AND SPEED
    private int skeletonOneSpeed = 2, skeletonTwoSpeed = 2, skeletonThreeSpeed = 2;

    // HEALTH AND ATTACK
    private int skeletonOneHealth = 100, skeletonTwoHealth = 100, skeletonThreeHealth = 100;
    private double skeletonOneAttackDamage = 10, skeletonTwoAttackDamage = 10, skeletonThreeAttackDamage = 10;

    // ATTACK TIMING
    private long skeletonOneAttackDuration, skeletonTwoAttackDuration, skeletonThreeAttackDuration;
    private long skeletonOneAttackCooldown, skeletonTwoAttackCooldown, skeletonThreeAttackCooldown;
    private boolean skeletonOneAttacked = false, skeletonTwoAttacked = false, skeletonThreeAttacked = false;

    // HIT AND DEATH STATES
    private boolean skeletonOneHitted = false, skeletonTwoHitted = false, skeletonThreeHitted = false;
    private boolean skeletonOneDeathAnimationPlayedFlag, skeletonTwoDeathAnimationPlayedFlag, skeletonThreeDeathAnimationPlayedFlag;
    private long skeletonOneDeathAnimationDuration, skeletonTwoDeathAnimationDuration, skeletonThreeDeathAnimationDuration;
    private boolean skeletonOneDeathTriggered = false, skeletonTwoDeathTriggered = false, skeletonThreeDeathTriggered = false;

    // HITBOXES AND ANIMATION
    Rectangle skeletonOneChaseBox, skeletonTwoChaseBox, skeletonThreeChaseBox;
    Rectangle skeletonOneHitBox, skeletonTwoHitBox, skeletonThreeHitBox;
    Rectangle skeletonOneAttackRangeBox, skeletonTwoAttackRangeBox, skeletonThreeAttackRangeBox;
    ImageIcon skeletonOneToDraw, skeletonTwoToDraw, skeletonThreeToDraw;


    public void updateSkeletonPositionAndSkeletonAttack() {
        
        if(!GameState.isInDryLandMap) return;

        // SKELETON ONE START
        if (getSkeletonOneHealth() <= 0 && !skeletonOneDeathTriggered) {
            GameState.skeletonOneIsAlive = false;
            skeletonOneDeathAnimationPlayedFlag = true;
            skeletonOneDeathAnimationDuration = System.currentTimeMillis();
            skeletonOneDeathTriggered = true;
        }
        if (GameState.skeletonOneIsAlive) {
            if (!GameState.skeletonOneChasing) {
                skeletonOnePositionX += skeletonOneSpeed;
                if (skeletonOnePositionX >= skeletonOnePatrolingEndingX || skeletonOnePositionX <= skeletonOnePatrolingStartX) {
                    skeletonOneSpeed *= -1;
                }
            }

            if (GameState.skeletonOneChasing) {
                skeletonOneSpeed = 2;
                int tolerance = 5;
                if (!GameState.skeletonOneHitbox && !skeletonOneHitted) {
                    // Move X
                    if (skeletonOnePositionX < player.getPlayerPositionX() - tolerance) {
                        skeletonOnePositionX += skeletonOneSpeed;
                    } else if (skeletonOnePositionX > player.getPlayerPositionX() + tolerance) {
                        skeletonOnePositionX -= skeletonOneSpeed;
                    }

                    // Move Y
                    if (skeletonOnePositionY < player.getPlayerPositionY() - tolerance) {
                        skeletonOnePositionY += skeletonOneSpeed;
                    } else if (skeletonOnePositionY > player.getPlayerPositionY() + tolerance) {
                        skeletonOnePositionY -= skeletonOneSpeed;
                    }
                }

                skeletonOneAttackRangeBox = new Rectangle(skeletonOnePositionX + 10, skeletonOnePositionY + 10, 80, 80);
                if (Player.playerHitbox.intersects(skeletonOneAttackRangeBox) && System.currentTimeMillis() - skeletonOneAttackCooldown >= 1750) {
                    skeletonOneAttacked = true;
                    skeletonOneAttackDuration = System.currentTimeMillis();
                    skeletonOneAttackCooldown = System.currentTimeMillis();
                    System.out.println("skeleton attacked!!!");
                }

                if (skeletonOneAttacked && System.currentTimeMillis() - skeletonOneAttackDuration >= 750) {
                    skeletonOneAttacked = false;
                }

                if (skeletonOneAttacked) {
                    double balancedSkeletonDamage = skeletonOneAttackDamage * 0.025; // 10 * 0.025 = 0.25
                    hud.setPlayerHealth(hud.getPlayerHealth() - balancedSkeletonDamage); // 6 dmg per second
                }

                // PLAYER SKILLS / PUNCH
                if (combatSystem.playerAttackRange.intersects(skeletonOneHitBox) && CombatSystem.playerAttacked) {
                    setSkeletonOneHealth(getSkeletonOneHealth() - (int) (combatSystem.playerPunchDamage * 0.1)); // 15 * 0.1 = 1.5 dmg (int) = 1
                    skeletonOneHitted = true;
                } else {
                    skeletonOneHitted = false;
                }

                if (skeletonOneHitted) {
                    if (keyHandler.lastPoseLeft) skeletonOnePositionX -= 3;
                    if (keyHandler.lastPoseRight) skeletonOnePositionX += 3;
                }
            }

            skeletonOneChaseBox = new Rectangle(skeletonOnePositionX - 50, skeletonOnePositionY - 50, 200, 200);
            if (Player.playerHitbox.intersects(skeletonOneChaseBox)) {
                GameState.skeletonOneChasing = true;
            }

            skeletonOneHitBox = new Rectangle(skeletonOnePositionX + 35, skeletonOnePositionY + 48, 30, 10);
            if (Player.playerHitbox.intersects(skeletonOneHitBox)) {
                GameState.skeletonOneHitbox = true;
            } else {
                GameState.skeletonOneHitbox = false;
            }
        }
        // SKELETON ONE END

        // SKELETON TWO START
        if (getSkeletonTwoHealth() <= 0 && !skeletonTwoDeathTriggered) {
            GameState.skeletonTwoIsAlive = false;
            skeletonTwoDeathAnimationPlayedFlag = true;
            skeletonTwoDeathAnimationDuration = System.currentTimeMillis();
            skeletonTwoDeathTriggered = true;
        }
        if (GameState.skeletonTwoIsAlive) {
            if (!GameState.skeletonTwoChasing) {
                skeletonTwoPositionX += skeletonTwoSpeed;
                if (skeletonTwoPositionX >= skeletonTwoPatrolingEndingX || skeletonTwoPositionX <= skeletonTwoPatrolingStartX) {
                    skeletonTwoSpeed *= -1;
                }
            }

            if (GameState.skeletonTwoChasing) {
                skeletonTwoSpeed = 2;
                int tolerance = 5;
                if (!GameState.skeletonTwoHitbox && !skeletonTwoHitted) {
                    // Move X
                    if (skeletonTwoPositionX < player.getPlayerPositionX() - tolerance) {
                        skeletonTwoPositionX += skeletonTwoSpeed;
                    } else if (skeletonTwoPositionX > player.getPlayerPositionX() + tolerance) {
                        skeletonTwoPositionX -= skeletonTwoSpeed;
                    }

                    // Move Y
                    if (skeletonTwoPositionY < player.getPlayerPositionY() - tolerance) {
                        skeletonTwoPositionY += skeletonTwoSpeed;
                    } else if (skeletonTwoPositionY > player.getPlayerPositionY() + tolerance) {
                        skeletonTwoPositionY -= skeletonTwoSpeed;
                    }
                }

                skeletonTwoAttackRangeBox = new Rectangle(skeletonTwoPositionX + 10, skeletonTwoPositionY + 10, 80, 80);
                if (Player.playerHitbox.intersects(skeletonTwoAttackRangeBox) && System.currentTimeMillis() - skeletonTwoAttackCooldown >= 1750) {
                    skeletonTwoAttacked = true;
                    skeletonTwoAttackDuration = System.currentTimeMillis();
                    skeletonTwoAttackCooldown = System.currentTimeMillis();
                    System.out.println("skeleton two attacked!!!");
                }

                if (skeletonTwoAttacked && System.currentTimeMillis() - skeletonTwoAttackDuration >= 750) {
                    skeletonTwoAttacked = false;
                }

                if (skeletonTwoAttacked) {
                    double balancedSkeletonDamage = skeletonTwoAttackDamage * 0.025; // 10 * 0.025 = 0.25
                    hud.setPlayerHealth(hud.getPlayerHealth() - balancedSkeletonDamage); // 6 dmg per second
                }

                // PLAYER SKILLS / PUNCH
                if (combatSystem.playerAttackRange.intersects(skeletonTwoHitBox) && CombatSystem.playerAttacked) {
                    setSkeletonTwoHealth(getSkeletonTwoHealth() - (int) (combatSystem.playerPunchDamage * 0.1)); // 15 * 0.1 = 1.5 dmg (int) = 1
                    skeletonTwoHitted = true;
                } else {
                    skeletonTwoHitted = false;
                }

                if (skeletonTwoHitted) {
                    if (keyHandler.lastPoseLeft) skeletonTwoPositionX -= 3;
                    if (keyHandler.lastPoseRight) skeletonTwoPositionX += 3;
                }
            }

            skeletonTwoChaseBox = new Rectangle(skeletonTwoPositionX - 50, skeletonTwoPositionY - 50, 200, 200);
            if (Player.playerHitbox.intersects(skeletonTwoChaseBox)) {
                GameState.skeletonTwoChasing = true;
            }

            skeletonTwoHitBox = new Rectangle(skeletonTwoPositionX + 35, skeletonTwoPositionY + 48, 30, 10);
            if (Player.playerHitbox.intersects(skeletonTwoHitBox)) {
                GameState.skeletonTwoHitbox = true;
            } else {
                GameState.skeletonTwoHitbox = false;
            }
        }
        // SKELETON TWO END

        // SKELETON THREE START
        if (getSkeletonThreeHealth() <= 0 && !skeletonThreeDeathTriggered) {
            GameState.skeletonThreeIsAlive = false;
            skeletonThreeDeathAnimationPlayedFlag = true;
            skeletonThreeDeathAnimationDuration = System.currentTimeMillis();
            skeletonThreeDeathTriggered = true;
        }
        if (GameState.skeletonThreeIsAlive) {
            if (!GameState.skeletonThreeChasing) {
                skeletonThreePositionX += skeletonThreeSpeed;
                if (skeletonThreePositionX >= skeletonThreePatrolingEndingX || skeletonThreePositionX <= skeletonThreePatrolingStartX) {
                    skeletonThreeSpeed *= -1;
                }
            }

            if (GameState.skeletonThreeChasing) {
                skeletonThreeSpeed = 2;
                int tolerance = 5;
                if (!GameState.skeletonThreeHitbox && !skeletonThreeHitted) {
                    // Move X
                    if (skeletonThreePositionX < player.getPlayerPositionX() - tolerance) {
                        skeletonThreePositionX += skeletonThreeSpeed;
                    } else if (skeletonThreePositionX > player.getPlayerPositionX() + tolerance) {
                        skeletonThreePositionX -= skeletonThreeSpeed;
                    }

                    // Move Y
                    if (skeletonThreePositionY < player.getPlayerPositionY() - tolerance) {
                        skeletonThreePositionY += skeletonThreeSpeed;
                    } else if (skeletonThreePositionY > player.getPlayerPositionY() + tolerance) {
                        skeletonThreePositionY -= skeletonThreeSpeed;
                    }
                }

                skeletonThreeAttackRangeBox = new Rectangle(skeletonThreePositionX + 10, skeletonThreePositionY + 10, 80, 80);
                if (Player.playerHitbox.intersects(skeletonThreeAttackRangeBox) && System.currentTimeMillis() - skeletonThreeAttackCooldown >= 1750) {
                    skeletonThreeAttacked = true;
                    skeletonThreeAttackDuration = System.currentTimeMillis();
                    skeletonThreeAttackCooldown = System.currentTimeMillis();
                    System.out.println("skeleton three attacked!!!");
                }

                if (skeletonThreeAttacked && System.currentTimeMillis() - skeletonThreeAttackDuration >= 750) {
                    skeletonThreeAttacked = false;
                }

                if (skeletonThreeAttacked) {
                    double balancedSkeletonDamage = skeletonThreeAttackDamage * 0.025; // 10 * 0.025 = 0.25
                    hud.setPlayerHealth(hud.getPlayerHealth() - balancedSkeletonDamage); // 6 dmg per second
                }

                // PLAYER SKILLS / PUNCH
                if (combatSystem.playerAttackRange.intersects(skeletonThreeHitBox) && CombatSystem.playerAttacked) {
                    setSkeletonThreeHealth(getSkeletonThreeHealth() - (int) (combatSystem.playerPunchDamage * 0.1)); // 15 * 0.1 = 1.5 dmg (int) = 1
                    skeletonThreeHitted = true;
                } else {
                    skeletonThreeHitted = false;
                }

                if (skeletonThreeHitted) {
                    if (keyHandler.lastPoseLeft) skeletonThreePositionX -= 3;
                    if (keyHandler.lastPoseRight) skeletonThreePositionX += 3;
                }
            }

            skeletonThreeChaseBox = new Rectangle(skeletonThreePositionX - 50, skeletonThreePositionY - 50, 200, 200);
            if (Player.playerHitbox.intersects(skeletonThreeChaseBox)) {
                GameState.skeletonThreeChasing = true;
            }

            skeletonThreeHitBox = new Rectangle(skeletonThreePositionX + 35, skeletonThreePositionY + 48, 30, 10);
            if (Player.playerHitbox.intersects(skeletonThreeHitBox)) {
                GameState.skeletonThreeHitbox = true;
            } else {
                GameState.skeletonThreeHitbox = false;
            }
        }
        // SKELETON THREE END

    }

    public void skeletonToDraw(Graphics2D g2) {
        
        // SKELETON ONE START
        if(GameState.skeletonOneIsAlive){
            g2.setColor(new Color(82, 6, 14));
            g2.fillRect(skeletonOnePositionX + 25, skeletonOnePositionY + 20, 100 / 2, 4);
            g2.setColor(Color.RED);
            g2.fillRect(skeletonOnePositionX + 25, skeletonOnePositionY + 20, getSkeletonOneHealth() / 2, 4);

            if(!GameState.skeletonOneChasing){
                if(skeletonOneSpeed > 0) skeletonOneToDraw = sheets.getSkeletonWalkingRight();
                else if(skeletonOneSpeed < 0) skeletonOneToDraw = sheets.getSkeletonWalkingLeft();
                g2.drawImage(sheets.getPlayerShadow().getImage(), skeletonOnePositionX + 20, skeletonOnePositionY + 40, 60, 60, gamePanel);
                g2.drawImage(skeletonOneToDraw.getImage(), skeletonOnePositionX, skeletonOnePositionY, skeletonOneWidth, skeletonOneHeight, gamePanel);
            }

            if(GameState.skeletonOneChasing){
                if(player.getPlayerPositionX() > skeletonOnePositionX){
                    if(skeletonOneHitted){
                        skeletonOneToDraw = sheets.getSkeletonRightHurt();
                    } else {
                        skeletonOneToDraw = (skeletonOneAttacked) ? sheets.getSkeletonRightAttack() : sheets.getSkeletonWalkingRight();
                    }
                } else {
                    if(skeletonOneHitted){
                        skeletonOneToDraw = sheets.getSkeletonLeftHurt();
                    } else {
                        skeletonOneToDraw = (skeletonOneAttacked) ? sheets.getSkeletonLeftAttack() : sheets.getSkeletonWalkingLeft();
                    }
                }

                g2.drawImage(sheets.getPlayerShadow().getImage(), skeletonOnePositionX + 20, skeletonOnePositionY + 40, 60, 60, gamePanel);
                g2.drawImage(skeletonOneToDraw.getImage(), skeletonOnePositionX, skeletonOnePositionY, skeletonOneWidth, skeletonOneHeight, gamePanel);
            }
        }
        if(!GameState.skeletonOneIsAlive && skeletonOneDeathAnimationPlayedFlag){
            if(player.getPlayerPositionX() > skeletonOnePositionX){
                g2.drawImage(sheets.getSkeletonRightDeath().getImage(), skeletonOnePositionX, skeletonOnePositionY, skeletonOneWidth, skeletonOneHeight, gamePanel);
            } else {
                g2.drawImage(sheets.getSkeletonLeftDeath().getImage(), skeletonOnePositionX, skeletonOnePositionY, skeletonOneWidth, skeletonOneHeight, gamePanel);
            }

            // Check if the animation duration (700ms) has passed
            if (System.currentTimeMillis() - skeletonOneDeathAnimationDuration >= 700) {
                skeletonOneDeathAnimationPlayedFlag = false; // stop showing the animation
            }
        }
        // SKELETON ONE END

        // SKELETON TWO START
        if (GameState.skeletonTwoIsAlive) {
            g2.setColor(new Color(82, 6, 14));
            g2.fillRect(skeletonTwoPositionX + 25, skeletonTwoPositionY + 20, 100 / 2, 4);
            g2.setColor(Color.RED);
            g2.fillRect(skeletonTwoPositionX + 25, skeletonTwoPositionY + 20, getSkeletonTwoHealth() / 2, 4);

            if (!GameState.skeletonTwoChasing) {
                if (skeletonTwoSpeed > 0) skeletonTwoToDraw = sheets.getSkeletonWalkingRight();
                else if (skeletonTwoSpeed < 0) skeletonTwoToDraw = sheets.getSkeletonWalkingLeft();
                g2.drawImage(sheets.getPlayerShadow().getImage(), skeletonTwoPositionX + 20, skeletonTwoPositionY + 40, 60, 60, gamePanel);
                g2.drawImage(skeletonTwoToDraw.getImage(), skeletonTwoPositionX, skeletonTwoPositionY, skeletonTwoWidth, skeletonTwoHeight, gamePanel);
            }

            if (GameState.skeletonTwoChasing) {
                if (player.getPlayerPositionX() > skeletonTwoPositionX) {
                    if (skeletonTwoHitted) {
                        skeletonTwoToDraw = sheets.getSkeletonRightHurt();
                    } else {
                        skeletonTwoToDraw = (skeletonTwoAttacked) ? sheets.getSkeletonRightAttack() : sheets.getSkeletonWalkingRight();
                    }
                } else {
                    if (skeletonTwoHitted) {
                        skeletonTwoToDraw = sheets.getSkeletonLeftHurt();
                    } else {
                        skeletonTwoToDraw = (skeletonTwoAttacked) ? sheets.getSkeletonLeftAttack() : sheets.getSkeletonWalkingLeft();
                    }
                }

                g2.drawImage(sheets.getPlayerShadow().getImage(), skeletonTwoPositionX + 20, skeletonTwoPositionY + 40, 60, 60, gamePanel);
                g2.drawImage(skeletonTwoToDraw.getImage(), skeletonTwoPositionX, skeletonTwoPositionY, skeletonTwoWidth, skeletonTwoHeight, gamePanel);
            }
        }
        if (!GameState.skeletonTwoIsAlive && skeletonTwoDeathAnimationPlayedFlag) {
            if (player.getPlayerPositionX() > skeletonTwoPositionX) {
                g2.drawImage(sheets.getSkeletonRightDeath().getImage(), skeletonTwoPositionX, skeletonTwoPositionY, skeletonTwoWidth, skeletonTwoHeight, gamePanel);
            } else {
                g2.drawImage(sheets.getSkeletonLeftDeath().getImage(), skeletonTwoPositionX, skeletonTwoPositionY, skeletonTwoWidth, skeletonTwoHeight, gamePanel);
            }

            // Check if the animation duration (700ms) has passed
            if (System.currentTimeMillis() - skeletonTwoDeathAnimationDuration >= 700) {
                skeletonTwoDeathAnimationPlayedFlag = false; // stop showing the animation
            }
        }
        // SKELETON TWO END

        // SKELETON THREE START
        if (GameState.skeletonThreeIsAlive) {
            g2.setColor(new Color(82, 6, 14));
            g2.fillRect(skeletonThreePositionX + 25, skeletonThreePositionY + 20, 100 / 2, 4);
            g2.setColor(Color.RED);
            g2.fillRect(skeletonThreePositionX + 25, skeletonThreePositionY + 20, getSkeletonThreeHealth() / 2, 4);

            if (!GameState.skeletonThreeChasing) {
                if (skeletonThreeSpeed > 0) skeletonThreeToDraw = sheets.getSkeletonWalkingRight();
                else if (skeletonThreeSpeed < 0) skeletonThreeToDraw = sheets.getSkeletonWalkingLeft();
                g2.drawImage(sheets.getPlayerShadow().getImage(), skeletonThreePositionX + 20, skeletonThreePositionY + 40, 60, 60, gamePanel);
                g2.drawImage(skeletonThreeToDraw.getImage(), skeletonThreePositionX, skeletonThreePositionY, skeletonThreeWidth, skeletonThreeHeight, gamePanel);
            }

            if (GameState.skeletonThreeChasing) {
                if (player.getPlayerPositionX() > skeletonThreePositionX) {
                    if (skeletonThreeHitted) {
                        skeletonThreeToDraw = sheets.getSkeletonRightHurt();
                    } else {
                        skeletonThreeToDraw = (skeletonThreeAttacked) ? sheets.getSkeletonRightAttack() : sheets.getSkeletonWalkingRight();
                    }
                } else {
                    if (skeletonThreeHitted) {
                        skeletonThreeToDraw = sheets.getSkeletonLeftHurt();
                    } else {
                        skeletonThreeToDraw = (skeletonThreeAttacked) ? sheets.getSkeletonLeftAttack() : sheets.getSkeletonWalkingLeft();
                    }
                }

                g2.drawImage(sheets.getPlayerShadow().getImage(), skeletonThreePositionX + 20, skeletonThreePositionY + 40, 60, 60, gamePanel);
                g2.drawImage(skeletonThreeToDraw.getImage(), skeletonThreePositionX, skeletonThreePositionY, skeletonThreeWidth, skeletonThreeHeight, gamePanel);
            }
        }
        if (!GameState.skeletonThreeIsAlive && skeletonThreeDeathAnimationPlayedFlag) {
            if (player.getPlayerPositionX() > skeletonThreePositionX) {
                g2.drawImage(sheets.getSkeletonRightDeath().getImage(), skeletonThreePositionX, skeletonThreePositionY, skeletonThreeWidth, skeletonThreeHeight, gamePanel);
            } else {
                g2.drawImage(sheets.getSkeletonLeftDeath().getImage(), skeletonThreePositionX, skeletonThreePositionY, skeletonThreeWidth, skeletonThreeHeight, gamePanel);
            }

            // Check if the animation duration (700ms) has passed
            if (System.currentTimeMillis() - skeletonThreeDeathAnimationDuration >= 700) {
                skeletonThreeDeathAnimationPlayedFlag = false; // stop showing the animation
            }
        }
        // SKELETON THREE END

    }

    // WOLF =================================================================
    // POSITION
    private int wolfOnePositionX = 300, wolfTwoPositionX = 100, wolfThreePositionX = 400;
    private int wolfOnePositionY = 250, wolfTwoPositionY = 110, wolfThreePositionY = 500;

    // PATROLING POSITION
    private int wolfOnePatrolingStartX = 300, wolfTwoPatrolingStartX = 50, wolfThreePatrolingStartX = 400;
    private int wolfOnePatrolingEndingX = 475, wolfTwoPatrolingEndingX = 225, wolfThreePatrolingEndingX = 500;

    // SIZE
    private int wolfOneWidth = 100, wolfTwoWidth = 100, wolfThreeWidth = 100;
    private int wolfOneHeight = 100, wolfTwoHeight = 100, wolfThreeHeight = 100;

    // MOVEMENT AND SPEED
    private int wolfOneSpeed = 2, wolfTwoSpeed = 2, wolfThreeSpeed = 2;

    // HEALTH AND ATTACK
    private int wolfOneHealth = 100, wolfTwoHealth = 100, wolfThreeHealth = 100;
    private double wolfOneAttackDamage = 10, wolfTwoAttackDamage = 10, wolfThreeAttackDamage = 10;

    // ATTACK TIMING
    private long wolfOneAttackDuration, wolfTwoAttackDuration, wolfThreeAttackDuration;
    private long wolfOneAttackCooldown, wolfTwoAttackCooldown, wolfThreeAttackCooldown;
    private boolean wolfOneAttacked = false, wolfTwoAttacked = false, wolfThreeAttacked = false;

    // HIT AND DEATH STATES
    private boolean wolfOneHitted = false, wolfTwoHitted = false, wolfThreeHitted = false;
    private boolean wolfOneDeathAnimationPlayedFlag, wolfTwoDeathAnimationPlayedFlag, wolfThreeDeathAnimationPlayedFlag;
    private long wolfOneDeathAnimationDuration, wolfTwoDeathAnimationDuration, wolfThreeDeathAnimationDuration;
    private boolean wolfOneDeathTriggered = false, wolfTwoDeathTriggered = false, wolfThreeDeathTriggered = false;

    // HITBOXES AND ANIMATION
    Rectangle wolfOneChaseBox, wolfTwoChaseBox, wolfThreeChaseBox;
    Rectangle wolfOneHitBox, wolfTwoHitBox, wolfThreeHitBox;
    Rectangle wolfOneAttackRangeBox, wolfTwoAttackRangeBox, wolfThreeAttackRangeBox;
    ImageIcon wolfOneToDraw, wolfTwoToDraw, wolfThreeToDraw;

    public void updateWolfPositionAndWolfAttack() {

        if(!GameState.isInDarkLandMap) return;

        // WOLF ONE 
        if (getWolfOneHealth() <= 0 && !wolfOneDeathTriggered) {
            GameState.wolfOneIsAlive = false;
            wolfOneDeathAnimationPlayedFlag = true;
            wolfOneDeathAnimationDuration = System.currentTimeMillis();
            wolfOneDeathTriggered = true;
        }
        if(GameState.wolfOneIsAlive){
            if(!GameState.wolfOneChasing){
                wolfOnePositionX += wolfOneSpeed;
                if(wolfOnePositionX >= wolfOnePatrolingEndingX || wolfOnePositionX <= wolfOnePatrolingStartX){
                    wolfOneSpeed *= -1;
                }
            }

            if(GameState.wolfOneChasing){

                wolfOneSpeed = 2;
                int tolerance = 5;
                if (!GameState.wolfOneHitbox &&!wolfOneHitted) {
                    // Move X
                    if (wolfOnePositionX < player.getPlayerPositionX() - tolerance) {
                        wolfOnePositionX += wolfOneSpeed;
                    }else if (wolfOnePositionX > player.getPlayerPositionX() + tolerance) {
                        wolfOnePositionX -= wolfOneSpeed;
                    }

                    // Move Y
                    if (wolfOnePositionY < player.getPlayerPositionY() - tolerance) {
                        wolfOnePositionY += wolfOneSpeed;
                    }else if (wolfOnePositionY > player.getPlayerPositionY()  + tolerance) {
                        wolfOnePositionY -= wolfOneSpeed;
                    }
                }

                wolfOneAttackRangeBox = new Rectangle(wolfOnePositionX + 10, wolfOnePositionY + 10, 80, 80);
                if (Player.playerHitbox.intersects(wolfOneAttackRangeBox) && System.currentTimeMillis() - wolfOneAttackCooldown >= 1750) {
                    wolfOneAttacked = true;
                    wolfOneAttackDuration = System.currentTimeMillis();
                    wolfOneAttackCooldown = System.currentTimeMillis();
                    System.out.println("wolf attacked!!!");
                }

                if(wolfOneAttacked && System.currentTimeMillis() - wolfOneAttackDuration >= 750){
                    wolfOneAttacked = false;
                }

                if(wolfOneAttacked){
                    double balancedWolfDamage = wolfOneAttackDamage * 0.025; // 10 * 0.025 = 0.25, 24 fps
                    hud.setPlayerHealth(hud.getPlayerHealth() - balancedWolfDamage); // 6 dmg per second
                }

                // PLAYER SKILLS / PUNCH
                if(combatSystem.playerAttackRange.intersects(wolfOneHitBox) && CombatSystem.playerAttacked){
                    setWolfOneHealth(getWolfOneHealth() - (int) (combatSystem.playerPunchDamage * 0.1) ); // 15 * 0.1 = 1.5 dmg (int) = 1
                    wolfOneHitted = true;
                }else wolfOneHitted = false; 

                if(wolfOneHitted){
                    if(keyHandler.lastPoseLeft) wolfOnePositionX -= 3;
                    if(keyHandler.lastPoseRight) wolfOnePositionX += 3;
                }
  
            }
            
            wolfOneChaseBox = new Rectangle(wolfOnePositionX - 50, wolfOnePositionY - 50, 200, 200);
            if(Player.playerHitbox.intersects(wolfOneChaseBox)){
                GameState.wolfOneChasing = true;
            }

            wolfOneHitBox = new Rectangle(wolfOnePositionX + 35, wolfOnePositionY + 48, 30, 10);
            if(Player.playerHitbox.intersects(wolfOneHitBox)){
                GameState.wolfOneHitbox = true;
            }else GameState.wolfOneHitbox = false ;
        }
        // WOLF ONE END

        // WOLF TWO
        if (getWolfTwoHealth() <= 0 && !wolfTwoDeathTriggered) {
            GameState.wolfTwoIsAlive = false;
            wolfTwoDeathAnimationPlayedFlag = true;
            wolfTwoDeathAnimationDuration = System.currentTimeMillis();
            wolfTwoDeathTriggered = true;
        }
        if(GameState.wolfTwoIsAlive){
            if(!GameState.wolfTwoChasing){
                wolfTwoPositionX += wolfTwoSpeed;
                if(wolfTwoPositionX >= wolfTwoPatrolingEndingX || wolfTwoPositionX <= wolfTwoPatrolingStartX){
                    wolfTwoSpeed *= -1;
                }
            }

            if(GameState.wolfTwoChasing){
                wolfTwoSpeed = 2;
                int tolerance = 5;
                if (!GameState.wolfTwoHitbox && !wolfTwoHitted) {
                    // Move X
                    if (wolfTwoPositionX < player.getPlayerPositionX() - tolerance) {
                        wolfTwoPositionX += wolfTwoSpeed;
                    } else if (wolfTwoPositionX > player.getPlayerPositionX() + tolerance) {
                        wolfTwoPositionX -= wolfTwoSpeed;
                    }

                    // Move Y
                    if (wolfTwoPositionY < player.getPlayerPositionY() - tolerance) {
                        wolfTwoPositionY += wolfTwoSpeed;
                    } else if (wolfTwoPositionY > player.getPlayerPositionY() + tolerance) {
                        wolfTwoPositionY -= wolfTwoSpeed;
                    }
                }

                wolfTwoAttackRangeBox = new Rectangle(wolfTwoPositionX + 10, wolfTwoPositionY + 10, 80, 80);
                if (Player.playerHitbox.intersects(wolfTwoAttackRangeBox) && System.currentTimeMillis() - wolfTwoAttackCooldown >= 1750) {
                    wolfTwoAttacked = true;
                    wolfTwoAttackDuration = System.currentTimeMillis();
                    wolfTwoAttackCooldown = System.currentTimeMillis();
                    System.out.println("wolfTwo attacked!!!");
                }

                if(wolfTwoAttacked && System.currentTimeMillis() - wolfTwoAttackDuration >= 750){
                    wolfTwoAttacked = false;
                }

                if(wolfTwoAttacked){
                    double balancedWolfDamage = wolfTwoAttackDamage * 0.025; // 10 * 0.025 = 0.25, 24 fps
                    hud.setPlayerHealth(hud.getPlayerHealth() - balancedWolfDamage); // 6 dmg per second
                }

                // PLAYER SKILLS / PUNCH
                if(combatSystem.playerAttackRange.intersects(wolfTwoHitBox) && CombatSystem.playerAttacked){
                    setWolfTwoHealth(getWolfTwoHealth() - (int) (combatSystem.playerPunchDamage * 0.1) ); // 15 * 0.1 = 1.5 dmg (int) = 1
                    wolfTwoHitted = true;
                } else wolfTwoHitted = false;

                if(wolfTwoHitted){
                    if(keyHandler.lastPoseLeft) wolfTwoPositionX -= 3;
                    if(keyHandler.lastPoseRight) wolfTwoPositionX += 3;
                }
            }

            wolfTwoChaseBox = new Rectangle(wolfTwoPositionX - 50, wolfTwoPositionY - 50, 200, 200);
            if(Player.playerHitbox.intersects(wolfTwoChaseBox)){
                GameState.wolfTwoChasing = true;
            }

            wolfTwoHitBox = new Rectangle(wolfTwoPositionX + 35, wolfTwoPositionY + 48, 30, 10);
            if(Player.playerHitbox.intersects(wolfTwoHitBox)){
                GameState.wolfTwoHitbox = true;
            } else GameState.wolfTwoHitbox = false;
        }
        // WOLF TWO END

        // WOLF THREE START
        if (getWolfThreeHealth() <= 0 && !wolfThreeDeathTriggered) {
            GameState.wolfThreeIsAlive = false;
            wolfThreeDeathAnimationPlayedFlag = true;
            wolfThreeDeathAnimationDuration = System.currentTimeMillis();
            wolfThreeDeathTriggered = true;
        }
        if (GameState.wolfThreeIsAlive) {
            if (!GameState.wolfThreeChasing) {
                wolfThreePositionX += wolfThreeSpeed;
                if (wolfThreePositionX >= wolfThreePatrolingEndingX || wolfThreePositionX <= wolfThreePatrolingStartX) {
                    wolfThreeSpeed *= -1;
                }
            }

            if (GameState.wolfThreeChasing) {
                wolfThreeSpeed = 2;
                int tolerance = 5;

                if (!GameState.wolfThreeHitbox && !wolfThreeHitted) {
                    // Move X
                    if (wolfThreePositionX < player.getPlayerPositionX() - tolerance) {
                        wolfThreePositionX += wolfThreeSpeed;
                    } else if (wolfThreePositionX > player.getPlayerPositionX() + tolerance) {
                        wolfThreePositionX -= wolfThreeSpeed;
                    }

                    // Move Y
                    if (wolfThreePositionY < player.getPlayerPositionY() - tolerance) {
                        wolfThreePositionY += wolfThreeSpeed;
                    } else if (wolfThreePositionY > player.getPlayerPositionY() + tolerance) {
                        wolfThreePositionY -= wolfThreeSpeed;
                    }
                }

                wolfThreeAttackRangeBox = new Rectangle(wolfThreePositionX + 10, wolfThreePositionY + 10, 80, 80);
                if (Player.playerHitbox.intersects(wolfThreeAttackRangeBox) && System.currentTimeMillis() - wolfThreeAttackCooldown >= 1750) {
                    wolfThreeAttacked = true;
                    wolfThreeAttackDuration = System.currentTimeMillis();
                    wolfThreeAttackCooldown = System.currentTimeMillis();
                    System.out.println("wolfThree attacked!!!");
                }

                if (wolfThreeAttacked && System.currentTimeMillis() - wolfThreeAttackDuration >= 750) {
                    wolfThreeAttacked = false;
                }

                if (wolfThreeAttacked) {
                    double balancedWolfDamage = wolfThreeAttackDamage * 0.025; // 10 * 0.025 = 0.25, 24 fps
                    hud.setPlayerHealth(hud.getPlayerHealth() - balancedWolfDamage); // 6 dmg per second
                }

                // PLAYER SKILLS / PUNCH
                if (combatSystem.playerAttackRange.intersects(wolfThreeHitBox) && CombatSystem.playerAttacked) {
                    setWolfThreeHealth(getWolfThreeHealth() - (int) (combatSystem.playerPunchDamage * 0.1)); // 15 * 0.1 = 1.5 dmg
                    wolfThreeHitted = true;
                } else wolfThreeHitted = false;

                if (wolfThreeHitted) {
                    if (keyHandler.lastPoseLeft) wolfThreePositionX -= 3;
                    if (keyHandler.lastPoseRight) wolfThreePositionX += 3;
                }
            }

            wolfThreeChaseBox = new Rectangle(wolfThreePositionX - 50, wolfThreePositionY - 50, 200, 200);
            if (Player.playerHitbox.intersects(wolfThreeChaseBox)) {
                GameState.wolfThreeChasing = true;
            }

            wolfThreeHitBox = new Rectangle(wolfThreePositionX + 35, wolfThreePositionY + 48, 30, 10);
            if (Player.playerHitbox.intersects(wolfThreeHitBox)) {
                GameState.wolfThreeHitbox = true;
            } else GameState.wolfThreeHitbox = false;
        }
        // WOLF THREE END

    }
    public void wolfToDraw(Graphics2D g2) {

        // WOLF ONE START
        if(GameState.wolfOneIsAlive){
            g2.setColor(new Color(82, 6, 14));
            g2.fillRect(wolfOnePositionX + 25, wolfOnePositionY + 20, 100 / 2, 4);
            g2.setColor(Color.RED);
            g2.fillRect(wolfOnePositionX + 25, wolfOnePositionY + 20, getWolfOneHealth() / 2, 4);
            if(!GameState.wolfOneChasing){
                if(wolfOneSpeed > 0) wolfOneToDraw = sheets.getWolfWalkingRight();
                else if(wolfOneSpeed < 0) wolfOneToDraw = sheets.getWolfWalkingLeft();
                g2.drawImage(sheets.getPlayerShadow().getImage(), wolfOnePositionX + 20, wolfOnePositionY + 40, 60, 60, gamePanel);
                g2.drawImage(wolfOneToDraw.getImage(), wolfOnePositionX, wolfOnePositionY, wolfOneWidth, wolfOneHeight, gamePanel);
            }
            if(GameState.wolfOneChasing){
                if(player.getPlayerPositionX() > wolfOnePositionX){
                    if(wolfOneHitted){
                        wolfOneToDraw = sheets.getWolfRightHurt();
                    }else wolfOneToDraw = (wolfOneAttacked) ? sheets.getWolfRightAttack() : sheets.getWolfWalkingRight();
                }else {
                    if(wolfOneHitted){
                        wolfOneToDraw = sheets.getWolfLeftHurt();
                    }else wolfOneToDraw = (wolfOneAttacked) ? sheets.getWolfLeftAttack() : sheets.getWolfWalkingLeft();
                }
                g2.drawImage(sheets.getPlayerShadow().getImage(), wolfOnePositionX + 20, wolfOnePositionY + 40, 60, 60, gamePanel);
                g2.drawImage(wolfOneToDraw.getImage(), wolfOnePositionX, wolfOnePositionY, wolfOneWidth, wolfOneHeight, gamePanel);
            }
        }
        if(!GameState.wolfOneIsAlive && wolfOneDeathAnimationPlayedFlag){
            if(player.getPlayerPositionX() > wolfOnePositionX){
                g2.drawImage(sheets.getWolfRightDeath().getImage(), wolfOnePositionX, wolfOnePositionY, wolfOneWidth, wolfOneHeight, gamePanel);
            }else g2.drawImage(sheets.getWolfLeftDeath().getImage(), wolfOnePositionX, wolfOnePositionY, wolfOneWidth, wolfOneHeight, gamePanel);

            // Check if the animation duration (700ms) has passed
            if (System.currentTimeMillis() - wolfOneDeathAnimationDuration >= 700) {
                wolfOneDeathAnimationPlayedFlag = false; // stop showing the animation
            }
        }
        // WOLF ONE ENDING

        // WOLF TWO START 
        if(GameState.wolfTwoIsAlive){
            g2.setColor(new Color(82, 6, 14));
            g2.fillRect(wolfTwoPositionX + 25, wolfTwoPositionY + 20, 100 / 2, 4);
            g2.setColor(Color.RED);
            g2.fillRect(wolfTwoPositionX + 25, wolfTwoPositionY + 20, getWolfTwoHealth() / 2, 4);
            if(!GameState.wolfTwoChasing){
                if(wolfTwoSpeed > 0) wolfTwoToDraw = sheets.getWolfWalkingRight();
                else if(wolfTwoSpeed < 0) wolfTwoToDraw = sheets.getWolfWalkingLeft();
                g2.drawImage(sheets.getPlayerShadow().getImage(), wolfTwoPositionX + 20, wolfTwoPositionY + 40, 60, 60, gamePanel);
                g2.drawImage(wolfTwoToDraw.getImage(), wolfTwoPositionX, wolfTwoPositionY, wolfTwoWidth, wolfTwoHeight, gamePanel);
            }
            if(GameState.wolfTwoChasing){
                if(player.getPlayerPositionX() > wolfTwoPositionX){
                    if(wolfTwoHitted){
                        wolfTwoToDraw = sheets.getWolfRightHurt();
                    }else wolfTwoToDraw = (wolfTwoAttacked) ? sheets.getWolfRightAttack() : sheets.getWolfWalkingRight();
                }else {
                    if(wolfTwoHitted){
                        wolfTwoToDraw = sheets.getWolfLeftHurt();
                    }else wolfTwoToDraw = (wolfTwoAttacked) ? sheets.getWolfLeftAttack() : sheets.getWolfWalkingLeft();
                }
                g2.drawImage(sheets.getPlayerShadow().getImage(), wolfTwoPositionX + 20, wolfTwoPositionY + 40, 60, 60, gamePanel);
                g2.drawImage(wolfTwoToDraw.getImage(), wolfTwoPositionX, wolfTwoPositionY, wolfTwoWidth, wolfTwoHeight, gamePanel);
            }
        }
        if(!GameState.wolfTwoIsAlive && wolfTwoDeathAnimationPlayedFlag){
            if(player.getPlayerPositionX() > wolfTwoPositionX){
                g2.drawImage(sheets.getWolfRightDeath().getImage(), wolfTwoPositionX, wolfTwoPositionY, wolfTwoWidth, wolfTwoHeight, gamePanel);
            }else {
                g2.drawImage(sheets.getWolfLeftDeath().getImage(), wolfTwoPositionX, wolfTwoPositionY, wolfTwoWidth, wolfTwoHeight, gamePanel);
            }

            // Check if the animation duration (700ms) has passed
            if (System.currentTimeMillis() - wolfTwoDeathAnimationDuration >= 700) {
                wolfTwoDeathAnimationPlayedFlag = false; // stop showing the animation
            }
        }
        // WOLF TWO END

        // WOLF THREE START
        if (GameState.wolfThreeIsAlive) {
            g2.setColor(new Color(82, 6, 14));
            g2.fillRect(wolfThreePositionX + 25, wolfThreePositionY + 20, 100 / 2, 4);
            g2.setColor(Color.RED);
            g2.fillRect(wolfThreePositionX + 25, wolfThreePositionY + 20, getWolfThreeHealth() / 2, 4);

            if (!GameState.wolfThreeChasing) {
                if (wolfThreeSpeed > 0) wolfThreeToDraw = sheets.getWolfWalkingRight();
                else if (wolfThreeSpeed < 0) wolfThreeToDraw = sheets.getWolfWalkingLeft();

                g2.drawImage(sheets.getPlayerShadow().getImage(), wolfThreePositionX + 20, wolfThreePositionY + 40, 60, 60, gamePanel);
                g2.drawImage(wolfThreeToDraw.getImage(), wolfThreePositionX, wolfThreePositionY, wolfThreeWidth, wolfThreeHeight, gamePanel);
            }

            if (GameState.wolfThreeChasing) {
                if (player.getPlayerPositionX() > wolfThreePositionX) {
                    wolfThreeToDraw = wolfThreeHitted ? sheets.getWolfRightHurt() :
                                    (wolfThreeAttacked ? sheets.getWolfRightAttack() : sheets.getWolfWalkingRight());
                } else {
                    wolfThreeToDraw = wolfThreeHitted ? sheets.getWolfLeftHurt() :
                                    (wolfThreeAttacked ? sheets.getWolfLeftAttack() : sheets.getWolfWalkingLeft());
                }

                g2.drawImage(sheets.getPlayerShadow().getImage(), wolfThreePositionX + 20, wolfThreePositionY + 40, 60, 60, gamePanel);
                g2.drawImage(wolfThreeToDraw.getImage(), wolfThreePositionX, wolfThreePositionY, wolfThreeWidth, wolfThreeHeight, gamePanel);
            }
        }
        if (!GameState.wolfThreeIsAlive && wolfThreeDeathAnimationPlayedFlag) {
            if (player.getPlayerPositionX() > wolfThreePositionX) {
                g2.drawImage(sheets.getWolfRightDeath().getImage(), wolfThreePositionX, wolfThreePositionY, wolfThreeWidth, wolfThreeHeight, gamePanel);
            } else {
                g2.drawImage(sheets.getWolfLeftDeath().getImage(), wolfThreePositionX, wolfThreePositionY, wolfThreeWidth, wolfThreeHeight, gamePanel);
            }

            if (System.currentTimeMillis() - wolfThreeDeathAnimationDuration >= 700) {
                wolfThreeDeathAnimationPlayedFlag = false;
            }
        }
        // WOLF THREE END

    }
    // GETTERS & SETTERS
    public int getMummyThreeHealth() {
        return mummyThreeHealth;
    }

    public void setMummyThreeHealth(int mummyThreeHealth) {
        this.mummyThreeHealth = mummyThreeHealth;
    }

    public int getMummyTwoHealth() {
        return mummyTwoHealth;
    }

    public void setMummyTwoHealth(int mummyTwoHealth) {
        this.mummyTwoHealth = mummyTwoHealth;
    }

    public int getMummyOneHealth() {
        return mummyOneHealth;
    }

    public void setMummyOneHealth(int mummyOneHealth) {
        this.mummyOneHealth = mummyOneHealth;
    }

    public int getSkeletonThreeHealth() {
        return skeletonThreeHealth;
    }

    public void setSkeletonThreeHealth(int skeletonThreeHealth) {
        this.skeletonThreeHealth = skeletonThreeHealth;
    }

    public int getSkeletonTwoHealth() {
        return skeletonTwoHealth;
    }

    public void setSkeletonTwoHealth(int skeletonTwoHealth) {
        this.skeletonTwoHealth = skeletonTwoHealth;
    }

    public int getSkeletonOneHealth() {
        return skeletonOneHealth;
    }

    public void setSkeletonOneHealth(int skeletonOneHealth) {
        this.skeletonOneHealth = skeletonOneHealth;
    }

    public int getWolfThreeHealth() {
        return wolfThreeHealth;
    }

    public void setWolfThreeHealth(int wolfThreeHealth) {
        this.wolfThreeHealth = wolfThreeHealth;
    }

    public int getWolfTwoHealth() {
        return wolfTwoHealth;
    }

    public void setWolfTwoHealth(int wolfTwoHealth) {
        this.wolfTwoHealth = wolfTwoHealth;
    }

    public int getWolfOneHealth() {
        return wolfOneHealth;
    }
    public void setWolfOneHealth(int wolfOneHealth) {
        this.wolfOneHealth = wolfOneHealth;
    }

    public int getWolfOnePositionX() {
        return wolfOnePositionX;
    }
    public int getWolfOnePositionY() {
        return wolfOnePositionY;
    }

}
