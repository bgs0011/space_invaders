package game.level3;


import java.awt.Graphics;
import java.util.Random;
import game.classes.*;

public class Enemy3 {

    private double x,y;
    private double ebulletdamage;
    
    private double hp = 16;

    private boolean moveup;
    private boolean movedown;
	private boolean moveright;
    private boolean moveleft;
    private boolean barricade;
    private double barricadeduration;

    //Random r = new Random();
    
    private Textures tex;

    public Enemy3(double x, double y, double ebulletdamage, Textures tex, 
    		boolean moveup, boolean movedown, boolean moveright, boolean moveleft, boolean barricade, double barricadeduration) {
        this.x = x;
        this.y = y;
        this.ebulletdamage = ebulletdamage;
        
        this.tex = tex;
        this.moveup = moveup;
        this.movedown = movedown;
        this.moveright = moveright;
        this.moveleft = moveleft;
        this.barricade = barricade;
        if(barricade) {
            this.barricadeduration = 16;

        }else {
            this.barricadeduration = 0;

        }
    }
    
    public void tick() {
        //y+=3;
        
        //if (y > (Game.HEIGHT*Game.SCALE)){
          // y=0;
            //x = r.nextInt(Game.WIDTH*Game.SCALE);
            
            if(moveright) {
    			if(920<=x) {
    				moveright = false;
    				movedown = true;
    				
    			}else {
    				x += 5;
    			}
    			
    		}
    		
    		if(movedown) {
    			if(y>=580) {
    				movedown = false;
    				moveleft = true;
    				
    			}else {
    				y +=5;
    			}
    			
    		}
    		
    		if(moveleft) {
    			if(30>=x) {
    				moveleft = false;
    				moveup = true;
    				
    			}else {
    				x -= 5;
    			}
    			
    		}
    		
    		if(moveup) {
    			if(y<=30) {
    				moveup = false;
    				moveright = true;
    				
    			}else {
    				y -= 5;
    			}
    			
    		}
            
    }
    
    public void render(Graphics g) {
            if(barricade) {
            	if(moveright) {
                    g.drawImage(tex.shieldedEnemyright,(int)x,(int)y, null);

            	}else if(moveup){
                    g.drawImage(tex.shieldedEnemyup,(int)x,(int)y, null);
            		
            	}else if(moveleft) {
                    g.drawImage(tex.shieldedEnemyleft,(int)x,(int)y, null);

                }else if(movedown){
                    g.drawImage(tex.shieldedEnemydown,(int)x,(int)y, null);
                }            	
            	
            }
            else {
            	if(moveright) {
                    g.drawImage(tex.enemyright,(int)x,(int)y, null);

            	}else if(moveup){
                    g.drawImage(tex.enemyup,(int)x,(int)y, null);
            		
            	}else if(moveleft) {
                    g.drawImage(tex.enemyleft,(int)x,(int)y, null);

                }else if(movedown){
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
    
    public boolean isMoveup() {
		return moveup;
	}

	public void setMoveup(boolean moveup) {
		this.moveup = moveup;
	}

	public boolean isMovedown() {
		return movedown;
	}

	public void setMovedown(boolean movedown) {
		this.movedown = movedown;
	}

	public boolean isMoveright() {
		return moveright;
	}

	public void setMoveright(boolean moveright) {
		this.moveright = moveright;
	}

	public boolean isMoveleft() {
		return moveleft;
	}

	public void setMoveleft(boolean moveleft) {
		this.moveleft = moveleft;
	}

	public double getHp() {
		return hp;
	}

	public void setHp(double hp) {
		this.hp = hp;
	}

	public double getEbulletdamage() {
		return ebulletdamage;
	}

	public void setEbulletdamage(double ebulletdamage) {
		this.ebulletdamage = ebulletdamage;
	}

	public boolean isbarricade() {
		return barricade;
	}

	public void setbarricade(boolean barricade) {
		this.barricade = barricade;
	}

	public double getBarricadeduration() {
		return barricadeduration;
	}

	public void setBarricadeduration(double barricadeduration) {
		this.barricadeduration = barricadeduration;
	}
	
	
	
    
}