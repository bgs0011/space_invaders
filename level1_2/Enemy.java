package game.level1_2;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

import game.classes.*;
import game.level1_2.Game.STATE;

public class Enemy extends GameObject implements EntityB{

    Random r = new Random();
    private Game game;
    private Controller c;
    
    Player player;
    private Bonus TempBon;
    
    private int speed = r.nextInt(4) + 1;
    private Textures tex;
    boolean right;
    boolean left;
    boolean isnegative;
    boolean barricade;

    
    public Enemy(double x, double y, boolean right, boolean left, Textures tex, Controller c, Game game, boolean barricade) {
        super(x,y);
        this.right = right;
        this.left = left;
        this.tex = tex;
        this.c = c;
        this.game = game;
        this.barricade = barricade;
    }
    
    public void tick() {
    	 if(Game.State == STATE.LEVEL1) {
    	        y+=speed;
    	        
    	        if (y > (Game.HEIGHT*Game.SCALE+32)){
    	        	speed = r.nextInt(3) + 1;
    	            y=0;
    	            //x = r.nextInt(Game.WIDTH*Game.SCALE);
    	            x = r.nextInt(640);
    	            
    	        }
    	        
         }else if(Game.State == STATE.LEVEL2) {  
        	if(right) {
                x-=speed;
                if (x < -32){
    	        	speed = r.nextInt(3) + 1;
    	            x=(Game.WIDTH*Game.SCALE);
    	            //x = r.nextInt(Game.WIDTH*Game.SCALE);
    	            y = r.nextInt(720);
    	            
    	        }
                
        	}else { 
        		x+=speed;
        		if (x > (Game.WIDTH*Game.SCALE)+32){
    	        	speed = r.nextInt(3) + 1;
    	            x=0;
    	            //x = r.nextInt(Game.WIDTH*Game.SCALE);
    	            y = r.nextInt(720);
    	            
    	        }
        		
        		}

            
            
            
         }
    	 
    	 
        
        
        
        for(int i = 0; i < game.ea.size();i++) {
        	EntityA tempEnt = game.ea.get(i);
        	
        	 if(Physics.Collusion(this, tempEnt)) {
        		c.removeEntity(tempEnt);
        		Game.SCORE += 4;
        		if(r.nextInt(10) < 5) {
        			isnegative = true;
        		}else {
        			isnegative = false;

        		}
        		/////////////////////
        		Bonus Tempbon = new Bonus(x, y, tex, isnegative);
        		
        		if(barricade) {
        			barricade = false;
        		}else {
                 	c.removeEntity(this);
                 	if(r.nextDouble()*10>5) {
                     	c.addbon(Tempbon);

            		}

        		}

        		
             }
        }
        
        
    }
    
    public void render(Graphics g) {
    	if(barricade) {
    		if(right) {
                g.drawImage(tex.shieldedEnemyright,(int)x,(int)y, null);

            }        
            else if(left) {
                g.drawImage(tex.shieldedEnemyleft,(int)x,(int)y, null);

            }else {
                g.drawImage(tex.shieldedEnemydown,(int)x,(int)y, null);
    	    }
    		
    	}else {
    		
            if(right) {
                g.drawImage(tex.enemyright,(int)x,(int)y, null);

            }        
            else if(left) {
                g.drawImage(tex.enemyleft,(int)x,(int)y, null);

            }else {
                g.drawImage(tex.enemydown,(int)x,(int)y, null);

            }
    	}

        
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

	public boolean isBarricade() {
		return barricade;
	}

	public void setBarricade(boolean barricade) {
		this.barricade = barricade;
	}   
    
    
    
    
}