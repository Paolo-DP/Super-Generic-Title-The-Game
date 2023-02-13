/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supergenericgametitlethegame;
import acm.graphics.*;
//import acm.program.GraphicsProgram;
//import java.util.Queue;
import bulletHell.*;
//import java.util.Iterator;
import java.util.Stack;

/**
 *
 * @author Paolo
 */
public class GameCanvas extends GCanvas implements SuperGenericGameTitleTheGameConstants{
    private GLabel placeholder = new GLabel("Placeholder");
    private Level Level = new Level();
    private Player Player = new Player();
    private int currentlevel = 0;
    
    public void placeholder(){
        placeholder.setLocation(getWidth()/2, getHeight()/2);
        add(placeholder);
    }
    public void addbg(int level){
        currentlevel=level;
        if(level==1){
           Level.setbglevel("testbg.png");
        }
        add(Level.bg,0,0);
    }
    public void addplayer(){
        add(Player.pImage);
    }
    public void setbglocation(int x){
        
    }
    public boolean isPlayerHit(){
            if( getElementAt(Player.pImage.getX(),Player.pImage.getY())!=null){
                return true;
            }
            else if ( getElementAt(Player.pImage.getX()+Player.pImage.getWidth(),Player.pImage.getY())!=null){
                return true;
            }
            else if ( getElementAt(Player.pImage.getX(),Player.pImage.getY()+Player.pImage.getHeight())!=null){
                return true;
            }
            else if ( getElementAt(Player.pImage.getX()+Player.pImage.getWidth(),Player.pImage.getY()+Player.pImage.getHeight())!=null){
                return true;
            }
            return false;
   }
    
    public void drawFrame(Stack bullets, Stack enemies){
        removeAll();
        addbg(currentlevel);
        while(!bullets.empty()){
            Bullet bullet = (Bullet)bullets.pop();
            add(bullet.getImage(), bullet.getX(), bullet.getY());
        }
        while(!enemies.empty()){
            Enemy enemy = (Enemy)enemies.pop();
            add(enemy.getImage(), enemy.getX(), enemy.getY());
        }
    }
    
    
}
