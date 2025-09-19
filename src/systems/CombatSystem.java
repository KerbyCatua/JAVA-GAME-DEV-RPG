package systems;

import java.awt.Rectangle;

import core.GameState;
import entities.*;

public class CombatSystem {

    private KeyHandler keyHandler;
    private Player player;
    public CombatSystem(KeyHandler keyHandler, Player player){
        this.keyHandler = keyHandler;
        this.player = player;
    }

    public static boolean playerAttacked = false;
    public long playerAttackDuration = 0;

    public void playerAttackedFunc() {
        if (keyHandler.isAttack && !GameState.outOfMana && !playerAttacked) { 
            playerAttacked = true;
            playerAttackDuration = System.currentTimeMillis();
        }

        if (playerAttacked && System.currentTimeMillis() - playerAttackDuration >= 500) {
            playerAttacked = false;
        }

        int attackX = player.getPlayerPositionX();
        int attackY = player.getPlayerPositionY() + 10;

        if (keyHandler.lastPoseLeft) {
            attackX -= 5;
        } else if (keyHandler.lastPoseRight) {
            attackX += 25;
        }

        Rectangle playerAttack = new Rectangle(attackX, attackY, (int) (player.getPlayerSizeWidth() / 1.4), (int) (player.getPlayerSizeHeight() / 1.4));


        // // TODO TO DELETE
        // Rectangle enemy = new Rectangle(0 ,0, player.getPlayerSizeWidth(), player.getPlayerSizeHeight());

        // // TODO TO DELETE
        // if(playerAttack.intersects(enemy) && playerAttacked){
        //     System.out.println("Enemy Attacked!");
        // }

    }
    
}
