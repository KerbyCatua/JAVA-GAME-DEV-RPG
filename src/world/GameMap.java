package world;

import java.awt.*;

import assets.*;
import core.GameState;
import entities.*;

public class GameMap {

    private int panelWidth;
    private int panelHeight;

    Sheets sheets;
    Player player;
    public GameMap(Sheets sheets, Player player, int panelWidth, int panelHeight){
        this.sheets = sheets;
        this.player = player;

        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
    }

    private int mapWidth = 1590;
    private int mapHeight = 1190;

    public void greenLandMap(Graphics2D g2) {
        g2.drawImage(sheets.getWholeMap(), 0, 0, mapWidth, mapHeight, null);
    }

    public void snowLandMap(Graphics2D g2) {
        g2.drawImage(sheets.getWholeMap(), 0, -590, mapWidth, mapHeight, null);
    }

    public void dryLandMap(Graphics2D g2) {
        g2.drawImage(sheets.getWholeMap(), -790, -589, mapWidth, mapHeight, null);
    }

    public void darkLandMap(Graphics2D g2) {
        g2.drawImage(sheets.getWholeMap(), -790, 0, mapWidth, mapHeight, null);
    }

    public void mapRenderLocationGameState() {

        if (player.getPlayerPositionY() >= panelHeight && GameState.isInGreenLandMap) {
            GameState.isInSnowLandMap = true;
            GameState.isInGreenLandMap = false;
            GameState.isInDarkLandMap = false;
            GameState.isInDryLandMap = false;

            player.setPlayerPositionY(0 - player.getPlayerSizeHeight());
        } 
        // GREEN LAND TO SNOW
        else if (player.getPlayerPositionY() <= -player.getPlayerSizeHeight() && GameState.isInSnowLandMap) {
            GameState.isInSnowLandMap = false;
            GameState.isInGreenLandMap = true;
            GameState.isInDarkLandMap = false;
            GameState.isInDryLandMap = false;

            player.setPlayerPositionY(panelHeight);
        }

        else if (player.getPlayerPositionX() >= panelWidth && GameState.isInSnowLandMap){
            GameState.isInSnowLandMap = false;
            GameState.isInGreenLandMap = false;
            GameState.isInDarkLandMap = false;
            GameState.isInDryLandMap = true;

            player.setPlayerPositionX(0 - player.getPlayerSizeHeight());
        }
        // SNOW TO DRY 
        else if (player.getPlayerPositionX() <= -player.getPlayerSizeHeight() && GameState.isInDryLandMap){
            GameState.isInSnowLandMap = true;
            GameState.isInGreenLandMap = false;
            GameState.isInDarkLandMap = false;
            GameState.isInDryLandMap = false;

            player.setPlayerPositionX(panelWidth);
        }

        
        else if (player.getPlayerPositionY() <= -player.getPlayerSizeHeight() && GameState.isInDryLandMap){
            GameState.isInSnowLandMap = false;
            GameState.isInGreenLandMap = false;
            GameState.isInDarkLandMap = true;
            GameState.isInDryLandMap = false;

            player.setPlayerPositionY(panelHeight);
        }
        // DRY TO DARK
        else if (player.getPlayerPositionY() >= panelHeight && GameState.isInDarkLandMap){
            GameState.isInSnowLandMap = false;
            GameState.isInGreenLandMap = false;
            GameState.isInDarkLandMap = false;
            GameState.isInDryLandMap = true;

            player.setPlayerPositionY(0 - player.getPlayerSizeHeight());
        }

        else if (player.getPlayerPositionX() <= -player.getPlayerSizeHeight() && GameState.isInDarkLandMap){
            GameState.isInSnowLandMap = false;
            GameState.isInGreenLandMap = true;
            GameState.isInDarkLandMap = false;
            GameState.isInDryLandMap = false;

            player.setPlayerPositionX(panelWidth);
        }
        // GREEN LAND TO DARK LAND 
        else if (player.getPlayerPositionX() >= panelWidth && GameState.isInGreenLandMap){
            GameState.isInSnowLandMap = false;
            GameState.isInGreenLandMap = false;
            GameState.isInDarkLandMap = true;
            GameState.isInDryLandMap = false;

            player.setPlayerPositionX(0 - player.getPlayerSizeHeight());
        }

    }

    

}
