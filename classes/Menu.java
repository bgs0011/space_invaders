package game.classes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;

import game.level1_2.Game;

public class Menu extends JPanel{

    public Rectangle nameInput = new Rectangle(Game.WIDTH +200, 280 ,150,50);
    public Rectangle playButton = new Rectangle(Game.WIDTH +220, 370 ,100,50);
    public Rectangle helpButton = new Rectangle(Game.WIDTH +220, 460 ,100,50);
    public Rectangle quitButton = new Rectangle(Game.WIDTH +220, 550 ,100,50);
    
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.black);
        
        //g.drawString("Diablo 2077", Game.WIDTH -160, 100);
        
        Font fnt1 = new Font("comic sans", Font.ITALIC, 30);
        g.setFont(fnt1);
        
        g2d.setColor(Color.black);
        g2d.fillRect(Game.WIDTH +200, 280 ,150,50);
        g2d.fillRect(Game.WIDTH +220, 370 ,100,50);
        g2d.fillRect(Game.WIDTH +220, 460 ,100,50);
        g2d.fillRect(Game.WIDTH +220, 550 ,100,50);
        
        g.setColor(Color.WHITE);
        g.drawString("Play", playButton.x + 20, playButton.y +30);
        g.drawString("Help", helpButton.x + 20, helpButton.y +30);
        g.drawString("Quit", quitButton.x + 20, quitButton.y +30);
        
        Font fnt2 = new Font("comic sans", Font.ITALIC, 15);
        g.setFont(fnt2);
        g.drawString("Please enter your name:", nameInput.x , nameInput.y - 15);
        g.drawString(Game.p_name, nameInput.x + 10, nameInput.y + 30);
            
        g2d.draw(playButton);
        g2d.draw(helpButton);
        g2d.draw(quitButton);
        g2d.draw(nameInput);
    }
}