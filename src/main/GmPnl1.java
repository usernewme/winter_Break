package main;

import entity.Player;
import tile.tileManager;

import javax.swing.*;
import java.awt.*;

public class GmPnl1 extends JPanel implements Runnable{
    final int originalTileSize = 16;
    final int scale = 4;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    public final int maxWorldCol = 94;
    public final int maxWorldRow = 61;
    public final int worldWidth = tileSize *  maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    public double FPS = 60;
    tileManager tileM = new tileManager(this);
    keyHandler keyH = new keyHandler();
    Thread gameThread;
    public Player player = new Player(this,keyH);

    GmPnl1(){
      this.setPreferredSize(new Dimension(screenWidth,screenHeight));
      this.setBackground(Color.white);
      this.setDoubleBuffered(true);
      this.addKeyListener(keyH);
      this.setFocusable(true);


    }
    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;


        while(gameThread != null){
            long currentTime = System.nanoTime();

            update();

            repaint();


            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long)remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        player.draw(g2);

        g2.dispose();
    }
}
