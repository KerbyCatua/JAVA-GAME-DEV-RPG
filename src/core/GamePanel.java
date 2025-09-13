package core;

import entities.*;
import systems.*;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{

    KeyHandler keyHandler = new KeyHandler();
    Player player = new Player(keyHandler);
    GameState gameState = new GameState();

    public GamePanel(){
        this.setPreferredSize(new Dimension(800, 600));
        this.setDoubleBuffered(true);

        addKeyListener(keyHandler);
        setFocusable(true);
        
        Timer timer = new Timer(16, e -> {
            player.playerMovement();
            repaint();
        });
        timer.start();

        requestFocusInWindow(true);
    }

    @Override 
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if(!gameState.thumbNailIsPressed){
            setBackground(Color.BLACK);
            if(keyHandler.anyKeyPressed) gameState.thumbNailIsPressed = true;
        }else{
            setBackground(Color.GRAY);
            g2.setColor(Color.RED);
            g2.fillRect(player.getPlayerPositionX(), player.getPlayerPositionY(), player.getPlayerSizeWidth(), player.getPlayerSizeHeight());
        }

    }

}