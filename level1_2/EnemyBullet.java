package game.level1_2;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.classes.*;

public class EnemyBullet extends GameObject implements EntityB{

	private double x;
    private double y;
    
    private boolean rightAngle;
    private boolean leftAngle;
    private boolean upAngle;
    private boolean downAngle;
    
    Controller c;
    private Textures tex;
    private Game game;

    public EnemyBullet(double x, double y, Controller c, Textures tex, 
    		boolean upAngle, boolean downAngle, boolean rightAngle, boolean leftAngle) {
    	
    	super(x,y);
        this.tex = tex;
        this.c = c;

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
        
        for(int i = 0; i < game.eb.size();i++) {
        	EntityA tempEnt = game.ea.get(i);
        	
        	 if(Physics.Collusion(this, tempEnt)) {
        		 if(tempEnt instanceof Player) {
        			 Game.HEALTH -=3;
        			 
        		 }else {
              		c.removeEntity(tempEnt);

        		 }
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
    public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}
}