package systems;

import java.awt.event.*;

public class KeyHandler implements KeyListener{

    public boolean isUp, isDown, isLeft, isRight, isShift;

    public boolean isAttack;

    public boolean anyKeyPressed = false;

    public boolean lastPoseRight = false, lastPoseLeft = false;

    @Override
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        anyKeyPressed = true;
        if(code == KeyEvent.VK_W) isUp = true;
        if(code == KeyEvent.VK_S) isDown = true;
        if(code == KeyEvent.VK_D) isRight = true;
        if(code == KeyEvent.VK_A) isLeft = true;

        if(code == KeyEvent.VK_SHIFT) isShift = true;

        if(code == KeyEvent.VK_J) isAttack = true;
        
        if (code == KeyEvent.VK_D) {
            lastPoseRight = true;
            lastPoseLeft = false;
        } else if (code == KeyEvent.VK_A) {
            lastPoseLeft = true;
            lastPoseRight = false;
        }

    }

    @Override
    public void keyReleased(KeyEvent e){
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W) isUp = false;
        if(code == KeyEvent.VK_S) isDown = false;
        if(code == KeyEvent.VK_D) isRight = false;
        if(code == KeyEvent.VK_A) isLeft = false;

        if(code == KeyEvent.VK_SHIFT) isShift = false;

        if(code == KeyEvent.VK_J) isAttack = false;
    }

    @Override
    public void keyTyped(KeyEvent e){}

}