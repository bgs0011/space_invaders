package game.level3;

import java.awt.Graphics;

import game.classes.*;
public class Bullet3 {
    
    private double x;
    private double y;
    private double pbulletdamage;

    private boolean rightAngle;
    private boolean leftAngle;
    private boolean upAngle;
    private boolean downAngle;
    
    private Textures tex;

    public Bullet3(double x, double y,Textures tex, double pbulletdamage, boolean upAngle, boolean downAngle, boolean rightAngle, boolean leftAngle) {
        this.x = x;
        this.y = y;
        this.tex = tex;
        this.setPbulletdamage(pbulletdamage);

        this.upAngle = upAngle;
        this.downAngle = downAngle;
        this.rightAngle = rightAngle;
        this.leftAngle = leftAngle;

    }
    public void tick(){
        if(upAngle) {
        	y-=10;
        	if(rightAngle) {
        		x+=10;
            }
            if(leftAngle) {
            	x-=10;
            }
        }
        else if(downAngle) {
        	y+=10;
        	if(rightAngle) {
            	x+=10;
            }
            if(leftAngle) {
            	x-=10;
            }

        }
        else if(rightAngle) {
        	x+=10;
        	if(upAngle) {
            	y-=10;
            }
            if(downAngle) {
            	y+=10;
            }
        }
        else if(leftAngle) {
        	x-=10;
        	if(upAngle) {
            	y-=10;
            }
            if(downAngle) {
            	y+=10;
            }
        }
        
    }
    
    public  void render(Graphics g){
        g.drawImage(tex.missile, (int)x,(int)y,null);
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
	public double getPbulletdamage() {
		return pbulletdamage;
	}
	public void setPbulletdamage(double pbulletdamage) {
		this.pbulletdamage = pbulletdamage;
	}
}