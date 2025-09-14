package systems;

import java.awt.Rectangle;

import entities.*;

public class CombatSystem {

    private KeyHandler keyHandler;
    private Player player;
    public CombatSystem(KeyHandler keyHandler, Player player){
        this.keyHandler = keyHandler;
        this.player = player;
    }

    public boolean playerAttacked = false; 
    public long playerAttackDuration = 0;

    public void playerAttackedFunc() {
        if (keyHandler.isAttack) { 
            playerAttacked = true;
            playerAttackDuration = System.currentTimeMillis();
        }

        if (playerAttacked && System.currentTimeMillis() - playerAttackDuration >= 500) {
            playerAttacked = false;
        }

        int attackX = player.getPlayerPositionX();
        int attackY = player.getPlayerPositionY();

        if (keyHandler.lastPoseLeft) {
            attackX -= 30;
        } else if (keyHandler.lastPoseRight) {
            attackX += 30;
        }

        Rectangle playerAttack = new Rectangle(attackX, attackY, player.getPlayerSizeWidth(), player.getPlayerSizeHeight());

        Rectangle enemy = new Rectangle(0 ,0, player.getPlayerSizeWidth(), player.getPlayerSizeHeight());

        if(playerAttack.intersects(enemy) && playerAttacked){
            System.out.println("Enemy Attacked!");
        }

    }
    
}
