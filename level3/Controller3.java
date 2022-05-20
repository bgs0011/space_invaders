package game.level3;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

import game.classes.*;
import game.level1_2.Bonus;
import game.level1_2.Game;

public class Controller3 {
    
    private LinkedList<Bullet3> b = new LinkedList<Bullet3>();
    private LinkedList<EnemyBullet3> eb = new LinkedList<EnemyBullet3>();
    private LinkedList<Enemy3> e = new LinkedList<Enemy3>();
    private LinkedList<Bonus> bon = new LinkedList<Bonus>();

    private Rectangle rp;
    
    Random r = new Random();
    int enemyBulletIndex = 0;

    Bullet3 TempBullet;
    EnemyBullet3 TempEnemyBullet;
    
    Enemy3 TempEnemy;
    Player3 p;
    
    Bonus TempBon;
    
    Game game;
    Textures tex;
    
    boolean isnegative;

    
    public Controller3(Game game2,Textures tex) {
        this.game = game2;
        this.tex = tex;

        addEnemy(new Enemy3(0, r.nextInt(Game.HEIGHT*Game.SCALE), 1, tex, false, false, true, false, true, 0));
        addEnemy(new Enemy3(0, r.nextInt(Game.HEIGHT*Game.SCALE), 2, tex, false, true, false, false, true, 0));
        addEnemy(new Enemy3(r.nextInt(Game.WIDTH*Game.SCALE), (Game.HEIGHT*Game.SCALE), 1, tex, false, true, false, false, true, 0));
        addEnemy(new Enemy3(r.nextInt(Game.WIDTH*Game.SCALE), (Game.HEIGHT*Game.SCALE), 1, tex, false, false, false, true, true,0 ));
        addEnemy(new Enemy3(r.nextInt(Game.WIDTH*Game.SCALE), 0, 2, tex, true, false, false, false, true, 0));
        addEnemy(new Enemy3(0, r.nextInt(Game.HEIGHT*Game.SCALE), 1, tex, true, false, false, false, false, 0));
        addEnemy(new Enemy3((Game.WIDTH*Game.SCALE),r.nextInt(Game.HEIGHT*Game.SCALE), 1, tex, true, false, false, false, false, 0));
        addEnemy(new Enemy3(r.nextInt(Game.WIDTH*Game.SCALE), 0, 2, tex, false, false, true, false, false, 0));
        addEnemy(new Enemy3((Game.WIDTH*Game.SCALE), r.nextInt(Game.HEIGHT*Game.SCALE), 1, tex, false, true, false, false, false,0));
        //addEnemy(new Enemy(750, 150, tex, false, true, false, false, false));
        ////addEnemy(new Enemy(900, 20, tex, false, false, false, true, false));
        //addEnemy(new Enemy(600, 120, tex, true, false, false, false, false));
        //addEnemy(new Enemy(150, 220, tex, true, false, false, false, false));
        //addEnemy(new Enemy(550,200, tex, true, false, false, false, false));

    }
    
    public void createEnemy(int enemy_count) {
    	addEnemy(new Enemy3(0, r.nextInt(Game.HEIGHT*Game.SCALE), 1, tex, false, true, false, false, true, 0));
        addEnemy(new Enemy3(r.nextInt(Game.WIDTH*Game.SCALE), 0, 1, tex, false, false , true, false, true, 0));
        addEnemy(new Enemy3((Game.WIDTH*Game.SCALE), r.nextInt(Game.HEIGHT*Game.SCALE), 2, tex, false, false, false, true, true,0 ));
        addEnemy(new Enemy3((Game.WIDTH*Game.SCALE), r.nextInt(Game.HEIGHT*Game.SCALE), 1, tex, true, false, false, false, true, 0));
        //addEnemy(new Enemy3(50, 520, 12, tex, true, false, false, false, true, 0));
        //addEnemy(new Enemy3(50, 420, 5, tex, true, false, false, false, false, 0));
        //addEnemy(new Enemy3(500,200, 8, tex, true, false, false, false, false, 0));

    }
    
    
    public void isdying(Player3 p) {
    	
    	this.p = p;
    	Rectangle rp = new Rectangle((int) p.getX(), (int) p.getY(), 32, 32);
    	this.rp = rp;

    }
    
    public void addEnemy(int enemy_count) {
    	for(int i=0; i < enemy_count; i++) {
    		//public EnemyBullet(double x, double y,Textures tex, double ebulletdamage, boolean upAngle, boolean downAngle, boolean rightAngle, boolean leftAngle) {
    		//addEnemy(new Enemy());
    	}
    }
    
    public void tick() {
    	
        for (int i =0;i< bon.size();i++) {
            TempBon = bon.get(i);            
            TempBon.setBonus(TempBon.getBonus()-1);
            
            Rectangle rbon = new Rectangle((int)TempBon.getX(), (int)TempBon.getY(), 32, 32);
            if(rp.getBounds().intersects(rbon.getBounds())) {
            	
            	bon.remove(i);

            	if(TempBon.isIsnegative()) {
                	Game.SCORE -=3;
                	Game.HEALTH -=3;

            	}else {
            		Game.SCORE +=5;
                	Game.HEALTH +=15;
            	}

            	if(Game.HEALTH >= 200) {
                    Game.HEALTH = 200;
                }
            	
            }else if(TempBon.getBonus()<=0) {
            	bon.remove(i);
            	
            }
        
        }
    	
        for (int i =0;i< b.size();i++) {
            TempBullet = b.get(i);
            
            
            if(TempBullet.getY()<0) {
                removeBullet(TempBullet);
            }else if(TempBullet.getX()<0) {
                removeBullet(TempBullet);
            }else if(TempBullet.getX()>(Game.WIDTH*Game.SCALE)) {
                removeBullet(TempBullet);
            }else if(TempBullet.getY()>(Game.HEIGHT*Game.SCALE)) {
                removeBullet(TempBullet);
            }
            
            for (int j =0; j<e.size(); j++) {
                TempEnemy = e.get(j);
                Rectangle rb = new Rectangle((int)TempBullet.getX(), (int)TempBullet.getY(), 32, 32);
                Rectangle re = new Rectangle((int)TempEnemy.getX(), (int)TempEnemy.getY(), 32, 32);

                if(rb.getBounds().intersects(re.getBounds())) {

                	if(TempEnemy.isbarricade()) {
                    	TempEnemy.setBarricadeduration(TempEnemy.getBarricadeduration()-TempBullet.getPbulletdamage());
                    	if(TempEnemy.getBarricadeduration()<=0) {
                    		TempEnemy.setbarricade(false);
                    	}
                	}else {
                    	TempEnemy.setHp(TempEnemy.getHp()-TempBullet.getPbulletdamage());
 
                	}
                	if(TempEnemy.getHp()<0) {
                		//remove enemy and remove bullet , create power up
                		game.setEnemy_killed(game.getEnemy_killed()+1);
                		
                		if(r.nextInt(10) < 5) {
                			isnegative = true;
                		}else {
                			isnegative = false;

                		}
                		
                		Bonus Tempbon = new Bonus(TempEnemy.getX(), TempEnemy.getY(), tex, isnegative);
                		if(r.nextDouble()*10>5) {
                    		bon.add(Tempbon);

                		}
                		
                		e.remove(j);
                    	Game.SCORE +=4; 

                        removeBullet(TempBullet);
                        
                	}
                }
            }    
            
            
            TempBullet.tick();
            
        }
        
        for (int i =0;i< e.size();i++) {
            TempEnemy = e.get(i);
            
            if(enemyBulletIndex == 100) {
            	addEnemyBullet(new EnemyBullet3(TempEnemy.getX(),TempEnemy.getY(),tex, TempEnemy.getEbulletdamage(),
            			TempEnemy.isMoveup(), TempEnemy.isMovedown(), TempEnemy.isMoveright(), TempEnemy.isMoveleft()));
            	enemyBulletIndex = 0;
            }
            enemyBulletIndex++;
            
            
            Rectangle ren = new Rectangle((int)TempEnemy.getX(), (int)TempEnemy.getY(), 32, 32);
            if(rp.getBounds().intersects(ren.getBounds())) {
                e.remove(i);
        		game.setEnemy_killed(game.getEnemy_killed()+1);

                Game.HEALTH -=3;
                Game.SCORE -= 4;
                
                if(Game.SCORE <= 0) {
                    Game.SCORE = 0;
                }
                
                if(Game.HEALTH<=0) {
                    //game over
                    Game.State = Game.STATE.GAME_OVER;
                    
                    
                }
            }

            TempEnemy.tick();
        }
        
        for (int i =0;i< eb.size();i++) {
            TempEnemyBullet = eb.get(i);
            
            if(TempEnemyBullet.getY()<0) {
            	removeEnemyBullet(TempEnemyBullet);
            }else if(TempEnemyBullet.getX()<0) {
            	removeEnemyBullet(TempEnemyBullet);
            }else if(TempEnemyBullet.getX()>(Game.WIDTH*Game.SCALE)) {
            	removeEnemyBullet(TempEnemyBullet);
            }else if(TempEnemyBullet.getY()>(Game.HEIGHT*Game.SCALE)) {
            	removeEnemyBullet(TempEnemyBullet);
            }
            
           
            Rectangle reb = new Rectangle((int)TempEnemyBullet.getX(), (int)TempEnemyBullet.getY(), 32, 32);

            if(rp.getBounds().intersects(reb.getBounds())) {

            	Game.HEALTH -=TempEnemyBullet.getEbulletdamage();
            	//Game.SCORE -=1; 
            	if(Game.SCORE <= 0) {
                    Game.SCORE = 0;
                }
                

            	if(Game.HEALTH <= 0) {
                    Game.State = Game.STATE.GAME_OVER;
                }
            }
            
            TempEnemyBullet.tick();
        }
        
    }
    
    public void render(Graphics g) {
    	
    	
    	for (int i =0;i< bon.size();i++) {
    		TempBon = bon.get(i);
    		
    		TempBon.render(g);
    		
    	} 

    	
        for (int i =0;i< b.size();i++) {
            TempBullet = b.get(i);
            
            TempBullet.render(g);
        }
        
        for (int i =0;i< e.size();i++) {
            TempEnemy = e.get(i);
            
            TempEnemy.render(g);
        }
        
  
     	for (int i =0;i< eb.size();i++) {
            TempEnemyBullet = eb.get(i);
            
            TempEnemyBullet.render(g);
        }
    
        
    }
    public void addBullet(Bullet3 block) {
        b.add(block);
    }
    public void removeBullet(Bullet3 block) {
        b.remove(block);
    }
    
    public void addEnemyBullet(EnemyBullet3 block) {
        eb.add(block);
    }
    public void removeEnemyBullet(EnemyBullet3 block) {
        eb.remove(block);
    }
    public void addEnemy(Enemy3 block) {
        e.add(block);
    }
    public void removeEnemy(Enemy3 block) {
        e.remove(block);
    }
}