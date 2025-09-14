package ui;

import systems.*;
import core.*;

public class HUD{

    private KeyHandler keyHandler;
    private GameState gameState;

    public HUD(KeyHandler keyHandler, GameState gameState){
        this.keyHandler = keyHandler;
        this.gameState = gameState;
    }

    private int playerHealth = 100;
    private int playerMana = 100;

    private int playerStamina = 100;
    private int playerStaminaCost = 8; // 3 orig
    private int playerStaminaRegen = 2;

    public void playerStaminaFunc() {

        if (playerStamina <= 28) {
            gameState.outOfStamina = true;
        } else if (playerStamina >= 43) {
            gameState.outOfStamina = false;
        }

        if(keyHandler.isShift && playerStamina >= 0 && !gameState.outOfStamina) playerStamina -= playerStaminaCost;
        if(playerStamina <= 100) playerStamina += playerStaminaRegen;
    }


    public int getPlayerHealth() {
        return playerHealth;
    }

    public int getPlayerMana() {
        return playerMana;
    }
    
    public int getPlayerStamina() {
        return playerStamina;
    }

}