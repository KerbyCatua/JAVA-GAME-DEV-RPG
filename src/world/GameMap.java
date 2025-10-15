package world;

import java.awt.*;

import javax.swing.ImageIcon;

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

    public void objectsToDrawLayerZero(Graphics2D g2) {
        // default position
        int objectLayerZeroPositionX = 0, objectLayerZeroPositionY = 0;
        if(GameState.isInGreenLandMap){
            objectLayerZeroPositionX = 0;
            objectLayerZeroPositionY = 0;
        }else if(GameState.isInSnowLandMap){
            objectLayerZeroPositionX = 0;
            objectLayerZeroPositionY = -590;
        }else if(GameState.isInDarkLandMap){
            objectLayerZeroPositionX = -790;
            objectLayerZeroPositionY = 0;
        }else if(GameState.isInDryLandMap){
            objectLayerZeroPositionX = -790;
            objectLayerZeroPositionY = -589;
        }  

        ImageIcon layerTwoToDraw = null;

        if(GameState.isInGreenLandMap) layerTwoToDraw = sheets.getWholeMapObjectsLayerZeroGreen();
        if(GameState.isInDarkLandMap) layerTwoToDraw = sheets.getWholeMapObjectsLayerZeroDark();
        if(GameState.isInSnowLandMap) layerTwoToDraw = sheets.getWholeMapObjectsLayerZeroSnow();
        if(GameState.isInDryLandMap) layerTwoToDraw = sheets.getWholeMapObjectsLayerZeroDry();

        g2.drawImage(layerTwoToDraw.getImage(), objectLayerZeroPositionX, objectLayerZeroPositionY, mapWidth, mapHeight, null);
    }

    public void objectsToDrawLayerOne(Graphics2D g2) {
        // default position
        int objectLayerOnePositionX = 0, objectLayerOnePositionY = 0;
        if(GameState.isInGreenLandMap){
            objectLayerOnePositionX = 0;
            objectLayerOnePositionY = 0;
        }else if(GameState.isInSnowLandMap){
            objectLayerOnePositionX = 0;
            objectLayerOnePositionY = -590;
        }else if(GameState.isInDarkLandMap){
            objectLayerOnePositionX = -790;
            objectLayerOnePositionY = 0;
        }else if(GameState.isInDryLandMap){
            objectLayerOnePositionX = -790;
            objectLayerOnePositionY = -589;
        }

        ImageIcon layerOneToDraw = null;

        if(GameState.isInGreenLandMap) layerOneToDraw = sheets.getWholeMapObjectsLayerOneGreen();
        if(GameState.isInDarkLandMap) layerOneToDraw = sheets.getWholeMapObjectsLayerOneDark();
        if(GameState.isInSnowLandMap) layerOneToDraw = sheets.getWholeMapObjectsLayerOneSnow();
        if(GameState.isInDryLandMap) layerOneToDraw = sheets.getWholeMapObjectsLayerOneDry();

        g2.drawImage(layerOneToDraw.getImage(), objectLayerOnePositionX, objectLayerOnePositionY, mapWidth, mapHeight, null);
    }

    public void greenLandMap(Graphics2D g2) {
        g2.drawImage(sheets.getWholeMapInGreen(), 0, 0, mapWidth, mapHeight, null);
    }

    public void snowLandMap(Graphics2D g2) {
        g2.drawImage(sheets.getWholeMapInSnow(), 0, -590, mapWidth, mapHeight, null);
    }

    public void dryLandMap(Graphics2D g2) {
        g2.drawImage(sheets.getWholeMapInDry(), -790, -589, mapWidth, mapHeight, null);
    }

    public void darkLandMap(Graphics2D g2) {
        g2.drawImage(sheets.getWholeMapInDark(), -790, 0, mapWidth, mapHeight, null);
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
