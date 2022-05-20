package game.classes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JFrame;

import game.level1_2.Game;

public class Level_1_2 extends JFrame{

    public Rectangle ContinueButton = new Rectangle(Game.WIDTH -200+100, 350 ,150,50);
    public Rectangle quitButton = new Rectangle(Game.WIDTH -200 + 120, 450 ,100,50);
    
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(new Color(51,255,255));
        
        g.drawString("You completed 1st level", Game.WIDTH -300, 100);
        
        Font fnt1 = new Font("comic sans", Font.ITALIC, 30);
        g.setFont(fnt1);
        
        g.setColor(Color.WHITE);

        g2d.setColor(Color.black);
        g2d.fillRect(Game.WIDTH -200+100, 350 ,150,50);
        g2d.fillRect(Game.WIDTH -200 + 120, 450 ,100,50);
        
        g.setColor(Color.WHITE);
        g.drawString("Continue", ContinueButton.x + 20, ContinueButton.y +30);
        g.drawString("Quit", quitButton.x + 20, quitButton.y +30);
            
        g2d.draw(ContinueButton);
        g2d.draw(quitButton);
    }
}