/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bulletHell;

import ADTs.Graph.GraphDP;
import acm.graphics.*;
import java.awt.*;
import java.util.*;
import javax.swing.JTree;
import supergenericgametitlethegame.SuperGenericGameTitleTheGameConstants;
import static supergenericgametitlethegame.SuperGenericGameTitleTheGameConstants.APPLICATION_HEIGHT;
import static supergenericgametitlethegame.SuperGenericGameTitleTheGameConstants.APPLICATION_WIDTH;
import static supergenericgametitlethegame.SuperGenericGameTitleTheGameConstants.FRAME_PAUSE;


/**
 *
 * @author Paolo
 */
public class Boss implements SuperGenericGameTitleTheGameConstants{
    public static GImage bg;
    
    private double xboundary = 0;
    private double yboundary = 0;
    
    private int cycle = 3000;
    private int cyclebuffer = FRAME_PAUSE;
    private int currenttime = 0;
    private int defaultspawntime = 3000;
    private boolean levelfinished = false;
    
    private Enemy bigboss = null;
    private boolean bigbossspawned = false;
    private GraphDP<Enemy> bosses = new GraphDP<>();
    ArrayList<Enemy> visited = new ArrayList<>();
    
    //private ArrayList<Enemy> enemytemplates = new ArrayList<>();
    //private ArrayList<Integer> spawntimes = new ArrayList<>();
    //private ArrayList<Boolean> alreadyspawned = new ArrayList<>();
    
    public void initBoss(){
        //initialize bigboss
        Bullet bullet = new Bullet();
        Enemy enemy = new Enemy(APPLICATION_WIDTH, APPLICATION_HEIGHT/3);     
        bullet = new Bullet();    
        bullet.setLocation(200, 20);
        bullet.setDirectionDegrees(270);
        bullet.setVelocity(400);
        bullet.setImage("redbullet.png",30,30);
        enemy.addBulletSpawn(bullet, 150, 10, 100);    
        //tracker.addProjectile(bullet);      
        enemy.setLocation(275,1);
        enemy.setVelocity(50, 10);
        enemy.setBehavior(0, true);
        enemy.setImage("boss.gif", 50,50);
        enemy.setImageSize(200, 200);
        enemy.setHealth(300);
        bigboss=enemy;
        bosses.addVertex(bigboss);
    }
    
    public void addEnemySpawn(Enemy newenemy, int spawntime){
        
    }
    
    public ArrayList spawnEnemies(int time){
        //System.out.print(currenttime + "\n");
        currenttime+=time;
        visited.clear();
        ArrayList<Enemy> newenemies = new ArrayList<>();
        if(currenttime>cycle){
            currenttime=0;
        if(!bigbossspawned){
            newenemies.add(bigboss);
            bigbossspawned=true;
            //System.out.print("spawning bigboss\n");
            return newenemies;
        }
        else{
        Enemy current = bigboss;
        Queue bfsq = new LinkedList();
        bfsq.add(bigboss);
        while(!bfsq.isEmpty()){
            current = (Enemy)bfsq.remove();
            if(current.getHealth()<=0){
                cutBranch(current);
                bosses.removeVertex(current);
                continue;
            }
            else if(bosses.getNumConnections(current)<2&&bigboss.getHealth()>0){
                //System.out.print("spawning smol boss\n");
                Enemy newsmolboss = new Enemy(APPLICATION_WIDTH, APPLICATION_HEIGHT/2);
                newsmolboss.setImage(current.getImageFile(), current.getXsize()/2, current.getYsize()/2);
                newsmolboss.setVelocity(current.getXVelocity()*-1, current.getYVelocity());
                newsmolboss.setLocation(current.getX(), current.getY()+(current.getXsize()*.75));
                newsmolboss.setSpawns(current.getSpawns());
                newsmolboss.setBehavior(0, true);
                newsmolboss.setHealth(current.getHealth()/2);
                newenemies.add(newsmolboss);
                bosses.addVertex(newsmolboss);
                //System.out.print(bosses.getNumConnections(current)+"\n");
                bosses.addconnection(current, newsmolboss, 1);
                if(bosses.getNumConnections(current)<2)
                    continue;
                else
                    break;
            }
            if(!visited.contains(current)){
                visited.add(current);
            }
            ArrayList adj = bosses.getAdjecency(current);
            for(int i=0; i<adj.size(); i++){
                if(!visited.contains(adj.get(i))){
                    visited.add((Enemy)adj.get(i));
                    bfsq.add(adj.get(i));
                }
            }
        }
        return newenemies;
        }
        }
        return newenemies;
    }
    
    private void cutBranch(Enemy root){
        ArrayList<Enemy> cutvisited = new ArrayList<>();
        
        Enemy current = root;
        Queue bfsq = new LinkedList();
        bfsq.add(root);
        while(!bfsq.isEmpty()){
            current = (Enemy)bfsq.remove();
            if(!cutvisited.contains(current)){
                cutvisited.add(current);
            }
            for(Object element: bosses.getAdjecency(current)){
                if(!cutvisited.contains(element)){
                    cutvisited.add((Enemy)element);
                    bfsq.add(element);
                }
            }
        }
        
        for(int i=cutvisited.size()-1; i>=0; i--){
            bosses.removeVertex(cutvisited.get(i));
            cutvisited.get(i).kill();
        }
    }
    
    
    public boolean isLevelFinished(){
        return levelfinished;
    }
    
    public void setcyclebuffer(int buffer){
        cyclebuffer = buffer;
    }
    
    public static void setbglevel(String filename){
            bg = new GImage(filename);     
    }
    public static void setmusiclevel(int level){
        
    }
    
}
