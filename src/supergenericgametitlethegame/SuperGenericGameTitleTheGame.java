/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supergenericgametitlethegame;
import acm.graphics.GLabel;
import bulletHell.*;
import acm.program.*;
import javax.swing.JLabel;
import java.io.*;
import java.util.Stack;
import java.util.logging.Logger;
import sun.audio.*;
import java.awt.event.*;
/**
 *
 * @author Paolo
 */
public class SuperGenericGameTitleTheGame extends GraphicsProgram implements SuperGenericGameTitleTheGameConstants{
    private Bullet samplebullet = new Bullet();
    //private GameCanvas  = new GameCanvas();
    private JLabel Score = new JLabel("Placeholder");   
    private ObjectTracker tracker = new ObjectTracker();
    private Level level = null;
    private Player Player = new Player();
   private JLabel Health = new JLabel("Health: "+ Player.getHealth());
  
    int x = 0;
    int currentlevel = 1;
    InputStream music;
    
    private GLabel placeholder = new GLabel("Placeholder");
    /**
     * @param args the command line arguments
     */
    
    public void init(){
        addKeyListeners();
        addMouseListeners();
        add(Score, NORTH);
        add(Health, NORTH);
        addbg(1);
        try {
            music();
        } catch (IOException ex) {
            Logger.getLogger(SuperGenericGameTitleTheGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        for(int i=0;i!=3;i++){
        Bullet bullet = new Bullet();
        bullet.setDirectionDegrees(60+(i*30));
        bullet.setVelocity(500);
        bullet.setImage("bluebullet.png", 10,10);
        Player.addBulletSpawn(bullet, 90, 10, 10);
        }
        tracker.setPlayer(Player);
    }
    
    /*public void enemyCollisions(){
        Stack<Enemy>stackofenemies = tracker.getEnemies();
        for(int i=0; i<stackofenemies.size(); i++){
            if(isEnemyHit(stackofenemies.peek())){
                stackofenemies.peek().damage(1);
                
            }
            stackofenemies.pop();
        }
    }
    
        /*public boolean isEnemyHit(Enemy eminem){
            if( getElementAt(eminem.getX(),eminem.getY())!=null&&getElementAt(eminem.getX(),eminem.getY())!=Player.pImage&&getElementAt(eminem.getX(),eminem.getY())!=Level.bg){
                
                return true;
            }
            else if ( getElementAt(eminem.getX()+eminem.getXsize(),eminem.getY())!=null&&getElementAt(eminem.getX()+eminem.getXsize(),eminem.getY())!=Player.pImage&&getElementAt(eminem.getX()+eminem.getXsize(),eminem.getY())!=Level.bg){
                
                return true;
            }
            else if ( getElementAt(eminem.getX(),eminem.getY()+eminem.getYsize())!= null&&getElementAt(eminem.getX(),eminem.getY()+eminem.getYsize())!=Player.pImage&&getElementAt(eminem.getX(),eminem.getY()+eminem.getYsize())!=Level.bg){
                return true;
            }
            else if ( getElementAt(eminem.getX()+eminem.getXsize(),eminem.getY()+eminem.getYsize())!=null&&getElementAt(eminem.getX()+eminem.getXsize(),eminem.getY()+eminem.getYsize())!=Level.bg&&getElementAt(eminem.getX()+eminem.getXsize(),eminem.getY()+eminem.getYsize())!=Player.pImage){
                return true;
            }
            return false;
            
            
        }*/
        public boolean isPlayerHit(){
            if( getElementAt(Player.pImage.getX(),Player.pImage.getY())!=null&&getElementAt(Player.pImage.getX(),Player.pImage.getY())!=Player.pImage&&getElementAt(Player.pImage.getX(),Player.pImage.getY())!=Level.bg){
                
                return true;
            }
            else if ( getElementAt(Player.pImage.getX()+Player.pImage.getWidth()/4,Player.pImage.getY())!=null&&getElementAt(Player.pImage.getX()+Player.pImage.getWidth()/4,Player.pImage.getY())!=Player.pImage&&getElementAt(Player.pImage.getX()+Player.pImage.getWidth()/4,Player.pImage.getY())!=Level.bg){
                
                return true;
            }
            else if ( getElementAt(Player.pImage.getX(),Player.pImage.getY()+Player.pImage.getHeight()/4)!= null&&getElementAt(Player.pImage.getX(),Player.pImage.getY()+Player.pImage.getHeight()/4)!=Player.pImage&&getElementAt(Player.pImage.getX(),Player.pImage.getY()+Player.pImage.getHeight()/4)!=Level.bg){
                return true;
            }
            else if ( getElementAt(Player.pImage.getX()+Player.pImage.getWidth()/4,Player.pImage.getY()+Player.pImage.getHeight()/4)!=null&&getElementAt(Player.pImage.getX()+Player.pImage.getWidth()/4,Player.pImage.getY()+Player.pImage.getHeight()/4)!=Level.bg&&getElementAt(Player.pImage.getX()+Player.pImage.getWidth()/4,Player.pImage.getY()+Player.pImage.getHeight()/4)!=Player.pImage){
                return true;
            }
            return false;
   }
    public void mouseMoved(MouseEvent me){
                Player.setXCoordinate(me.getX()-0.5*Player.pImage.getWidth());
                Player.setYCoordinate(me.getY()-0.5*Player.pImage.getHeight());
            }
    public void keyPressed(KeyEvent ke) {
            switch (ke.getKeyCode()) {
                case KeyEvent.VK_E:
                    tracker.addProjectile(Player.getBullets(FRAME_PAUSE).iterator());
                    break;
                default:
                    break;
            }
        }
    public void run(){
        tracker.setBounds(getWidth(), getHeight());
        demo();
        //placeholder();
        while(true){
            
            tracker.updateObjects();
            drawFrame(tracker.getBullets(), tracker.getEnemies());
            pause(FRAME_PAUSE);
            if(level!=null)
            tracker.addEnemy(level.spawnEnemies(FRAME_PAUSE).iterator());
            //enemyCollisions();
            if(isPlayerHit()){
                Player.damagePlayer();
            }

            Health.setText("Health"+ (Player.getHealth()-(Player.getHealth()%1)));
        }
    }
    public void music() throws FileNotFoundException, IOException{
        music=new FileInputStream(new File("src\\audio\\unowen.wav"));
        AudioStream audios=new AudioStream(music);
        AudioPlayer.player.start(audios); 
        
    }
    
    public void demo(){
        /*
        Bullet bullet2 = new Bullet();
        Bullet bullet = new Bullet();
        bullet.setLocation(50, 50);
        bullet.setDirectionDegrees(290);
        bullet.setVelocity(200);
        bullet.setImage("bluebullet.png");
        tracker.addProjectile(bullet);
        bullet = new Bullet();
        bullet.setLocation(200, 200);
        bullet.setDirectionDegrees(x);
        bullet.setVelocity(75);
        bullet.setImage("redbullet.png");
        tracker.addProjectile(bullet);
        
        Enemy enemy = new Enemy(getWidth(), getHeight()); 
        for(int i=0;i!=10;i++){
        bullet = new Bullet();    
        bullet.setLocation(200, 200);
        bullet.setDirectionDegrees(30*i);
        bullet.setVelocity(600);
        bullet.setImage("redbullet.png");
        enemy.addBulletSpawn(bullet, 90, 10, i*100);
        }
        //tracker.addProjectile(bullet);
        
        enemy.setLocation(50, 50);
        enemy.setVelocity(10, 100);
        enemy.setImage("centrifuge.png");
        enemy.setImageSize(100, 100);
        tracker.addEnemy(enemy);
        */
        //tracker.addEnemy(spawnclockwisespinner(50,50,0,0));
        //wiper(350,50,0,0);
        //laser(350,50,-50,0,270);
        
        level = new Level();
        for(int i = 0;i!=5;i++){
            level.addEnemySpawn(laser(0,50,200,0,270), 1000+(i*200));
        }
        for(int i = 0;i!=5;i++){
            level.addEnemySpawn(laser(470,50,-200,0,270), 1000+(i*200));
        }
        for(int i = 0;i!=5;i++){
            level.addEnemySpawn(laser(0,0,0,150,0), 4000+(i*800));
        }
        for(int i = 0;i!=5;i++){
            level.addEnemySpawn(laser(470,0,0,150,180), 4800+(i*800));
        }
        level.addEnemySpawn(laser(470,0,0,0,270), 9000);
        level.addEnemySpawn(laser(470,50,0,0,230), 9000);
        level.addEnemySpawn(laser(0,0,0,0,310), 9000);
        level.addEnemySpawn(laser(0,50,0,0,270), 9000);
        level.addEnemySpawn(laser(320,100,0,0,270), 9500);
        level.addEnemySpawn(laser(150,100,0,0,270), 9500);
        level.addEnemySpawn(wiper(235,0,0,0), 10000);
        level.addEnemySpawn(spawncounterspinner(235,0,0,350), 12000);
        for(int i = 0;i!=2;i++){
            level.addEnemySpawn(spawncounterspinner(285+(-i*100),0,0,350), 12200);
        }
        for(int i = 0;i!=2;i++){
            level.addEnemySpawn(spawncounterspinner(335+(-i*200),0,0,350), 12400);
        }
        level.addEnemySpawn(wiper(235,0,0,20), 13000);
        level.addEnemySpawn(boomer(235,0,0,20), 16000);
        for(int i = 0;i!=2;i++){
        level.addEnemySpawn(boomer(385+(-i*300),0,0,20), 16000);
        }
        level.addEnemySpawn(spawncounterspinner(235,0,0,350), 17000);
        for(int i = 0;i!=2;i++){
            level.addEnemySpawn(spawncounterspinner(285+(-i*100),0,0,350), 17200);
        }
        for(int i = 0;i!=2;i++){
            level.addEnemySpawn(spawncounterspinner(335+(-i*200),0,0,350), 17400);
        }
        for(int i = 0;i!=5;i++){
            level.addEnemySpawn(spawncounterspinner(0,50,350,100), 19000+(i*200));
        }
        for(int i = 0;i!=5;i++){
            level.addEnemySpawn(spawncounterspinner(470,100,-350,100), 20000+(i*200));
        }
        for(int i = 0;i!=5;i++){
        level.addEnemySpawn(wiper(235,0,0,400), 22000+(i*200));
        }
        for(int i = 0;i!=2;i++){
            for(int j=0;j!=4;j++){
        level.addEnemySpawn(boomer(385+(-i*300),0,0,300), 22000+(j*400));
        }
        }
        for(int i = 0;i!=10;i++){
            level.addEnemySpawn(spawncounterspinner(0,50,350,100), 25000+(i*100));
        }
        for(int i = 0;i!=10;i++){
            level.addEnemySpawn(spawnclockwisespinner(470,100,-350,100), 25000+(i*100));
        }
        for(int i = 0;i!=10;i++){
            level.addEnemySpawn(spawncounterspinner(0,50,0,200), 27000+(i*100));
        }
        for(int i = 0;i!=10;i++){
            level.addEnemySpawn(spawnclockwisespinner(470,100,0,200), 27000+(i*100));
        }
        for(int i = 0;i!=10;i++){
            level.addEnemySpawn(laser(0,0,0,200,0), 30000+(i*200));
        }
        for(int i = 0;i!=10;i++){
            level.addEnemySpawn(laser(470,0,0,200,180), 30000+(i*200));
        }
        
        //level.addEnemySpawn(spawnclockwisespinner(200,50,-50,50), 2000);
        //level.addEnemySpawn(spawnclockwisespinner(50,500,50,-50), 3000);
        
    }
    public Enemy spawnclockwisespinner(int xloc, int yloc, int xvel, int yvel){
        Bullet bullet;
        Enemy enemy = new Enemy(getWidth(), getHeight()); 
        for(int i=0;i!=12;i++){
        bullet = new Bullet();    
        bullet.setLocation(200, 200);
        bullet.setDirectionDegrees(30*i);
        bullet.setVelocity(600);
        bullet.setImage("redbullet.png",30,30);
        enemy.addBulletSpawn(bullet, 150, 0, i*100);
        }
        //tracker.addProjectile(bullet);
        
        enemy.setLocation(xloc,yloc);
        enemy.setVelocity(xvel, yvel);
        enemy.setImage("spinner.gif");
        enemy.setImageSize(30, 30);
        return enemy;
    }
    public Enemy spawncounterspinner(int xloc, int yloc, int xvel, int yvel){
        Bullet bullet = new Bullet();
        Enemy enemy = new Enemy(getWidth(), getHeight()); 
        for(int i=0;i!=12;i++){
        bullet = new Bullet();    
        bullet.setLocation(200, 200);
        bullet.setDirectionDegrees(120+(-i*30));
        bullet.setVelocity(600);
        bullet.setImage("redbullet.png",30,30);
        enemy.addBulletSpawn(bullet, 150, 10, i*100);
        }
        //tracker.addProjectile(bullet);
        
        enemy.setLocation(xloc,yloc);
        enemy.setVelocity(xvel, yvel);
        enemy.setImage("spinner.gif");
        enemy.setImageSize(30, 30);
        return enemy;
    }
    public Enemy wiper(int xloc, int yloc, int xvel, int yvel){
        
        Bullet bullet = new Bullet();
        Enemy enemy = new Enemy(getWidth(), getHeight()); 
        for(int i=1;i!=18;i++){
        bullet = new Bullet();    
        bullet.setLocation(200, 200);
        bullet.setDirectionDegrees(-i*10);
        bullet.setVelocity(500);
        bullet.setImage("redbullet.png",30,30);
        enemy.addBulletSpawn(bullet, 150, 0, i*100);
        }
        for(int i=1;i!=18;i++){
        bullet = new Bullet();    
        bullet.setLocation(200, 200);
        bullet.setDirectionDegrees(180+(i*10));
        bullet.setVelocity(500);
        bullet.setImage("redbullet.png",30,30);
        enemy.addBulletSpawn(bullet, 150, 0, 1800+(i*100));
        }
        //tracker.addProjectile(bullet);
        
        enemy.setLocation(xloc,yloc);
        enemy.setVelocity(xvel, yvel);
        enemy.setImage("wiper.gif",50,50);
        enemy.setImageSize(30, 30);
        return enemy;
    }
    public Enemy laser(int xloc, int yloc, int xvel, int yvel,int angle){
        
        Bullet bullet = new Bullet();
        Enemy enemy = new Enemy(getWidth(), getHeight()); 
        
        bullet = new Bullet();    
        bullet.setLocation(200, 200);
        bullet.setDirectionDegrees(angle);
        bullet.setVelocity(400);
        bullet.setImage("redbullet.png",30,30);
        enemy.addBulletSpawn(bullet, 150, 10, 100);
       
        //tracker.addProjectile(bullet);
        
        enemy.setLocation(xloc,yloc);
        enemy.setVelocity(xvel, yvel);
        enemy.setImage("ufo.png");
        enemy.setImageSize(50, 50);
        return enemy;
    }
    public Enemy boomer(int xloc, int yloc, int xvel, int yvel){
        
        Bullet bullet = new Bullet();
        Enemy enemy = new Enemy(getWidth(), getHeight()); 
        for(int i=0;i!=8;i++){
        bullet = new Bullet();    
        bullet.setLocation(200, 200);
        bullet.setDirectionDegrees(45+(i*45));
        bullet.setVelocity(600);
        bullet.setImage("redbullet.png",30,30);
        enemy.addBulletSpawn(bullet, 150, 10, 600);
        }
        //tracker.addProjectile(bullet);
        
        enemy.setLocation(xloc,yloc);
        enemy.setVelocity(xvel, yvel);
        enemy.setImage("boomer.gif");
        enemy.setImageSize(30, 30);
        return enemy;
    }
    
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
    

    
    public void drawFrame(Stack bullets, Stack enemies){
        removeAll();
        addbg(currentlevel);
        Player.pImage.setSize(50, 50);
        add(Player.pImage, Player.getXCoordinate(), Player.getYCoordinate() );
        while(!bullets.empty()){
            Bullet bullet = (Bullet)bullets.pop();
            add(bullet.getImage(), bullet.getX()-(bullet.getXsize()/2), bullet.getY()-(bullet.getYsize()/2));
        }
        while(!enemies.empty()){
            Enemy enemy = (Enemy)enemies.pop();
            add(enemy.getImage(), enemy.getX()-(enemy.getXsize()/2), enemy.getY()-(enemy.getYsize()/2));
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        new SuperGenericGameTitleTheGame().start(args);
    }
    
}
