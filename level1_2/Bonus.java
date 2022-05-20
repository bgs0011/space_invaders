package game.level1_2;

import java.awt.Graphics;

import game.classes.Textures;

public class Bonus {
	private double x;
	private double y;
	private double bonus;
    private Textures tex;
    boolean isnegative;

	public Bonus(double x, double y, Textures tex, boolean isnegative) {
		this.bonus = 200;
		this.x = x;
		this.y = y;
		this.tex = tex;
		this.isnegative = isnegative;


	}
	
	public void render(Graphics g) {
		
		if(isnegative) {
	        g.drawImage(tex.bonuspointminus,(int)x,(int)y,null);

		}else {
	        g.drawImage(tex.bonuspointplus,(int)x,(int)y,null);

		}

	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
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

	public boolean isIsnegative() {
		return isnegative;
	}

	public void setIsnegative(boolean isnegative) {
		this.isnegative = isnegative;
	}
	
	

}
