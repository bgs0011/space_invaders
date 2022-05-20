package game.level3;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.classes.*;

public class EnemyBullet3 implements EntityB{
    
    private double x;
    private double y;
    private double ebulletdamage;
    
    private boolean rightAngle;
    private boolean leftAngle;
    private boolean upAngle;
    private boolean downAngle;
    
    private Textures tex;

    public EnemyBullet3(double x, double y,Textures tex, double ebulletdamage, boolean upAngle, boolean downAngle, boolean rightAngle, boolean leftAngle) {
    	this.x = x;
        this.y = y;
        this.tex = tex;
        this.setEbulletdamage(ebulletdamage);

        this.upAngle = upAngle;
        this.downAngle = downAngle;
        this.rightAngle = rightAngle;
        this.leftAngle = leftAngle;

    }
    public void tick(){
   
    	if(upAngle) {
        	x+=10;
        	
        }
        else if(downAngle) {
        	x-=10;
        	

        }
        else if(rightAngle) {
        	y+=10;
        	
        }
        else if(leftAngle) {
        	y-=10;
        	
        }
       
    }
    
    public  void render(Graphics g){
        g.drawImage(tex.enemybullet, (int)x,(int)y,null);
    }
    
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
	public double getEbulletdamage() {
		return ebulletdamage;
	}
	public void setEbulletdamage(double ebulletdamage) {
		this.ebulletdamage = ebulletdamage;
	}
	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
}