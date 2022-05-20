package game.level1_2;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.classes.*;

public class Bullet extends GameObject implements EntityA{
    
    private boolean rightAngle;
    private boolean leftAngle;
    private boolean upAngle;
    private boolean downAngle;
    
    private Textures tex;
    private Game game;

    public Bullet(double x, double y,Textures tex, Game game, boolean upAngle, boolean downAngle, boolean rightAngle, boolean leftAngle) {
        super(x,y);
        this.tex = tex;
        this.game = game;
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
        
        for(int i = 0; i < game.eb.size();i++) {
        	EntityB tempEnt = game.eb.get(i);
        	
        	 if(Physics.Collusion(this, tempEnt)) {
        		 game.setEnemy_killed(game.getEnemy_killed()+1);
        		 game.setEnemy_count(game.getEnemy_count()-1);

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