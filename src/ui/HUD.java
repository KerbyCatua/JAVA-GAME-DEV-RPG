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

    private double playerHealth = 100;


    private int playerMana = 100;
    private int playerManaRegen = 1;
    private int playerPunchManaCost = 3;

    private int playerStamina = 100;
    private int playerStaminaCost = 4; // 3 orig
    private int playerStaminaRegen = 1;

    public void playerPunchAttackedFunc() {
    
        if(CombatSystem.playerAttacked) playerMana -= playerPunchManaCost;

    }

    public void playerManaFunc() {
        if(playerMana <= 5) GameState.outOfMana = true;
        else if(playerMana >= 20) GameState.outOfMana = false;

        if(playerMana <= 100 && !CombatSystem.playerAttacked) playerMana += playerManaRegen;
        
    }

    public void playerStaminaFunc() {

        if (playerStamina <= 5) {
            gameState.outOfStamina = true;
        } else if (playerStamina >= 20) {
            gameState.outOfStamina = false;
        }

        if(keyHandler.isShift && playerStamina >= 0 && !gameState.outOfStamina) playerStamina -= playerStaminaCost;
        else if(playerStamina <= 100) playerStamina += playerStaminaRegen;
    }
    
    public int getPlayerStamina() {
        return playerStamina;
    }

    public int getPlayerMana() {
        return playerMana;
    }

    public double getPlayerHealth() {
        return playerHealth;
    }

    public void setPlayerHealth(double playerHealth) {
        this.playerHealth = playerHealth;
    }

}