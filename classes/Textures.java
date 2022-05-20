package game.classes;

import java.awt.Image;
import java.awt.image.BufferedImage;

import game.level1_2.*;

public class Textures {
    
    public BufferedImage playerup, playerdown, playerright, playerleft;
    public BufferedImage missile, enemybullet, bonuspointminus, bonuspointplus;
    public BufferedImage enemyup, enemydown, enemyright, enemyleft;
    public BufferedImage shieldedEnemyup, shieldedEnemydown, shieldedEnemyright, shieldedEnemyleft;

    
    private SpriteSheet ss;
    
    public Textures(Game game) {
        ss = new SpriteSheet(game.getSpriteSheet());
        
        getTextures();
    }
    
    private void getTextures() {
        playerup = ss.grabImage(1, 1, 32, 32);
        playerdown = ss.grabImage(3, 1, 32, 32);
        playerright = ss.grabImage(2, 1, 32, 32);
        playerleft = ss.grabImage(4, 1, 32, 32);

        missile = ss.grabImage(1, 3, 32, 32);
        bonuspointminus = ss.grabImage(3, 3, 32, 32);
        bonuspointplus = ss.grabImage(2, 3, 32, 32);
        enemybullet = ss.grabImage(1, 3, 32, 32);
        
        enemyup = ss.grabImage(3, 2, 32, 32);
        enemydown = ss.grabImage(1, 2, 32, 32);
        enemyright = ss.grabImage(2, 2, 32, 32);
        enemyleft = ss.grabImage(4, 2, 32, 32);

        shieldedEnemyup = ss.grabImage(3, 4, 32, 32);
        shieldedEnemydown = ss.grabImage(1, 4, 32, 32);
        shieldedEnemyright = ss.grabImage(2, 4, 32, 32);
        shieldedEnemyleft = ss.grabImage(4, 4, 32, 32);
    
    }

}