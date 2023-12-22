package entity;

import main.GmPnl1;
import main.keyHandler;

import java.awt.*;

public class Player extends Entity{

    GmPnl1 gp;
    keyHandler keyH;

    public final int screenX;
    public final int screenY;

    int jumps = 1;
    boolean jump = false;
    boolean landed = false;
    int jHeight = 2;
    int jSpeed = jHeight*8;
    boolean rise = true;
    int fallSpeed = 6;
    int l = 0;
    public Player(GmPnl1 gp, keyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        setDefaultValues();
    }
    public void setDefaultValues(){
        worldX = gp.tileSize * 47;
        worldY = gp.tileSize * 30;
        speed = 4;
    }
    public void update(){
        layer = keyH.CurrentLayer;


        if (keyH.up.equals(true) && grounded()){
            jumps -= 1;
            jump = true;

        } else if (keyH.down.equals(true) && !grounded()) {
            worldY += speed;
            worldY = worldY + speed;
        } else if (keyH.left.equals(true)) {
            worldX -= speed;
            worldX = worldX - speed;
        }else if (keyH.right.equals(true)) {
            worldX += speed;
            worldX = worldX + speed;
        }



        if (layer == 0){
            l =  gp.tileSize/2;
        } else if (layer == 1) {
            l = 0;
        } else if (layer == 2){
            l = -gp.tileSize/2;
        }else if (layer == 3){
            l = -gp.tileSize;
        }else if (layer == 4){
            l = -gp.tileSize - gp.tileSize/2;
        } else if (layer == 5){
            l = -gp.tileSize*2;
        }

        if (grounded()){
            worldY = gp.worldHeight - 100;
            jumps = 1;
            rise = true;
        }
        if (!jump){
            fall();
        }
        if (jump){
            jump();
        }



    }
    public void draw(Graphics2D g2){

        g2.setColor(Color.BLACK);
        g2.fillRect(screenX,screenY + l, gp.tileSize ,gp.tileSize);
    }
    public boolean grounded (){
        if (worldY >= gp.worldHeight - 100){
            return true;
        }
        return false;
    }
    private void fall(){
        if (grounded()){
            landed = true;
        } else {
            worldY += fallSpeed;
            worldY = worldY + fallSpeed;
        }
    }
    private void jump(){

        if(jHeight <= jSpeed && rise){
            worldY -= jHeight;
            worldY = worldY - jSpeed;
            if(keyH.up.equals(true)){
                jHeight++;
            } else {
                rise = false;
            }

        } else {
            jHeight = 2;
            jump = false;
        }
    }
}
