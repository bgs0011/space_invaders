package game.classes;

import java.awt.Graphics;
import java.awt.Rectangle;

import game.level1_2.Bonus;

public interface EntityA {
	
	public void tick();
	public void render(Graphics g);
	public Rectangle getBounds();
	
	public double getX();
	public double getY();
	
}