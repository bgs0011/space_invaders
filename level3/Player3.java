package game.level3;

import java.awt.Graphics;

import game.classes.*;
import game.level1_2.Game;

public class Player3{
    
    private double x;
    private double y;
    private double pbulletdamage;
    private double powerup;
    
    private boolean rightAngle;
    private boolean leftAngle;
    private boolean upAngle;
    private boolean downAngle;
    
    public static double velX = 0;
    public static double velY = 0;
    
    private Textures tex;
    
    public Player3(double x,double y,Textures tex, double pbulletdamage, double powerup) {
        this.x = x;
        this.y = y;    
        this.tex = tex;
        this.powerup = 0;
        this.pbulletdamage = pbulletdamage;
        
        rightAngle = false;
        leftAngle = false;
        upAngle = true;
        downAngle = false;
    }
    
    public double getPowerup() {
		return powerup;
	}



	public void setPowerup(double powerup) {
		this.powerup = powerup;
	}



	public void tick() {
    	/*
    	if(this.hp <= 0){
            GenericSpaceShooter.gssh.removeEntity(this);
            JOptionPane.showMessageDialog(null, "You died, your score was: "+ score);
            System.exit(0);
        }
    	*/
    	
    	
        x+=velX;
        y+=velY;
        
        
        if(velX>0) {
            rightAngle = true;
            if(velY<0) {
            	upAngle = true;
                downAngle = false;
            }
            else if(velY>0) {
            	downAngle = true;
                upAngle = false;
            }
            else {
                upAngle = false;
                downAngle = false;
            }
            
            leftAngle = false;
        }        
        else if(velX<0) {
        	leftAngle = true;
        	if(velY<0) {
            	upAngle = true;
                downAngle = false;
            }
        	else if(velY>0) {
            	downAngle = true;
                upAngle = false;

            }
            else {
                upAngle = false;
                downAngle = false;

            }
            rightAngle = false;
        }
        else if(velY<0) {
        	upAngle = true;
        	if(velX<0) {
            	leftAngle = true;
                rightAngle = false;            	
        	}
        	else if(velX>0) {
                 rightAngle = true;
                 leftAngle = false;

        	 }else {
                 leftAngle = false;
                 rightAngle = false;            	

        	 }
            downAngle = false;
        }
        else if(velY>0) {
        	downAngle = true;
        	if(velX<0) {
            	leftAngle = true;
                rightAngle = false;            	
        	}
        	else if(velX>0) {
                 rightAngle = true;
                 leftAngle = false;

        	 }else {
                 leftAngle = false;
                 rightAngle = false;            	

        	 }
            upAngle = false;
        }
        
        	
        if(x<=0)
           x=0;
        if(x>=Game.WIDTH*Game.SCALE-32)
            x = Game.WIDTH*Game.SCALE-32;
        if(y<=0)
            y=0;
        if(y>=Game.HEIGHT*Game.SCALE-32)
            y=Game.HEIGHT*Game.SCALE-32;
        
    }
    public void render(Graphics g) {
    	if(rightAngle) {
            g.drawImage(tex.playerright,(int)x,(int)y, null);

        }        
        else if(leftAngle) {
            g.drawImage(tex.playerleft,(int)x,(int)y, null);

        }
        else if(upAngle) {
            g.drawImage(tex.playerup,(int)x,(int)y, null);

        }
        else if(downAngle) {
            g.drawImage(tex.playerdown,(int)x,(int)y, null);

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

    public double getVelX() {
        return velX;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

	public boolean isRightAngle() {
		return rightAngle;
	}

	public void setRightAngle(boolean rightAngle) {
		this.rightAngle = rightAngle;
	}

	public boolean isLeftAngle() {
		return leftAngle;
	}

	public void setLeftAngle(boolean leftAngle) {
		this.leftAngle = leftAngle;
	}

	public boolean isUpAngle() {
		return upAngle;
	}

	public void setUpAngle(boolean upAngle) {
		this.upAngle = upAngle;
	}

	public boolean isDownAngle() {
		return downAngle;
	}

	public void setDownAngle(boolean downAngle) {
		this.downAngle = downAngle;
	}


	public double getPbulletdamage() {
		return pbulletdamage;
	}

	public void setPbulletdamage(double pbulletdamage) {
		this.pbulletdamage = pbulletdamage;
	}
	
}