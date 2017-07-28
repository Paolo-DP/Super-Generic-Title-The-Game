/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bulletHellObjectClasses;
import acm.graphics.GImage;


/**
 *
 * @author Paolo
 */
public class Projectile {
    GImage bulletimage = null;
    double xvelocity = 0;
    double yvelocity = 0;
    double direction = 0; //in degrees
    
    
    public void setVelocity(double new_velocity){
        double rads = (180/3.14)*direction;
        xvelocity = new_velocity * Math.cos(rads);
        yvelocity = new_velocity * Math.sin(rads);
    }
    
    public void setVelocity(double newx, double newy){
        xvelocity = newx;
        yvelocity = newy;
    }
    
    public void setXVelocity(double newx){
        xvelocity = newx;
    }
    public void setYVelocity(double newy){
        yvelocity = newy;
    }
    public double getXVelocity(){
        return xvelocity;
    }
    public double getYVelocity(){
        return yvelocity;
    }
    /**sets the direction in degrees*/
    public void setDirectionDegrees(double degree){
        direction = degree;
    }
    
    /**set the bullets image with a GImage as a parameter*/
    public void setProjectileImage(GImage img){
        bulletimage = img;
    }
    
    /**set image using the filename found in src/images */
    public void setProjectileImage(String filename){
        bulletimage = new GImage(filename);
    }
    
}
