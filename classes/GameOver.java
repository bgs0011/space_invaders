package game.classes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import game.level1_2.Game;

public class GameOver {
    public Rectangle quitButton = new Rectangle(Game.WIDTH +140, 270 ,100,50);
    public Rectangle playAgainButton = new Rectangle(Game.WIDTH + 280, 270 ,180,50);
    
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.white);
        
        g.drawString("Game over, you died", Game.WIDTH -450, 100);
        g.drawString("Your score was : " + String.valueOf(Game.SCORE), Game.WIDTH -250, 452);
        
        Font fnt1 = new Font("comic sans", Font.ITALIC, 30);
        g.setFont(fnt1);
        
        g2d.setColor(Color.black);
        g2d.fillRect(Game.WIDTH +140, 270 ,100,50);
        g2d.fillRect(Game.WIDTH + 280, 270 ,180,50);
        
        
        g.setColor(Color.WHITE);
        g.drawString("Play Again", playAgainButton.x + 20, playAgainButton.y +30);
        g.drawString("Quit", quitButton.x + 20, quitButton.y +30);
            
        g2d.draw(playAgainButton);
        g2d.draw(quitButton);
    }
}