package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyHandler implements KeyListener {
    public Boolean up = false;
    public Boolean down = false;
    public Boolean left = false;
    public Boolean right = false;
    public Boolean backLayer = false;

    public int CurrentLayer = 0;
    public double maxLayer = 4;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W && CurrentLayer < maxLayer){
            backLayer = true;
            CurrentLayer ++;
        }
        if (code == KeyEvent.VK_S && CurrentLayer > 0){
            down = true;
            CurrentLayer --;
        }
        if (code == KeyEvent.VK_A){
            left = true;
        }
        if (code == KeyEvent.VK_D){
            right = true;
        }
        if (code == KeyEvent.VK_I){
            up = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W){
            backLayer = false;
        }
        if (code == KeyEvent.VK_S){
            down = false;
        }
        if (code == KeyEvent.VK_A){
            left = false;
        }
        if (code == KeyEvent.VK_D){
            right = false;
        }
        if (code == KeyEvent.VK_I){
            up = false;
        }
    }
}
