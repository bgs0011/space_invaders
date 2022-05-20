package game.level1_2;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.Random;

import game.classes.*;
import game.level1_2.Game.STATE;


public class Controller {
    
    private LinkedList<EntityA> ea = new LinkedList<EntityA>();
    private LinkedList<EntityB> eb = new LinkedList<EntityB>();
    private LinkedList<Bonus> bon = new LinkedList<Bonus>();

    Random r = new Random();
    private Bonus TempBon;
    private Enemy e1;
    private Player p1;
    
    private Game game;
    private Textures tex;
    
    boolean setbarricade;
    
    public Controller(Game game,Textures tex) {
        this.game = game;
        this.tex = tex;

    }
    
    EntityA entA;
    EntityB entB;
    
    public void tick() {    	
    	
      	//entity A
        for (int i =0;i< ea.size();i++) {
            entA = ea.get(i);
            
            if(entA.getY()<0) {
                removeEntity(entA);
            }else if(entA.getX()<0) {
            	removeEntity(entA);
            }else if(entA.getX()>(Game.WIDTH*Game.SCALE)) {
            	removeEntity(entA);
            }else if(entA.getY()>(Game.HEIGHT*Game.SCALE)) {
            	removeEntity(entA);
            }
            if (entA instanceof Player) {
            	p1 = (Player)entA;
            	for (int x =0;x< bon.size();x++) {
                    TempBon = bon.get(x);
                    
                    TempBon.setBonus(TempBon.getBonus()-1);
                    if(TempBon.getBonus()<=0) {
                    	bon.remove(x);
                    	
                    }
                }
            }
            
            entA.tick();
        }
        
      //entity B
        for (int i =0;i< eb.size();i++) {
            entB = eb.get(i);
            
            if (entB instanceof Enemy) {
            	e1 = (Enemy)entB;
            	
            }
            
            
            if(entB.getY()<0) {
                removeEntity(entB);
                game.setEnemy_killed(game.getEnemy_killed()+1);
                
            }else if(entB.getX()<-32) {
            	removeEntity(entB);
                game.setEnemy_killed(game.getEnemy_killed()+1);
                
            }else if(entB.getX()>((Game.WIDTH*Game.SCALE)-32)) {
            	removeEntity(entB);
                game.setEnemy_killed(game.getEnemy_killed()+1);

            }else if(entB.getY()>(Game.HEIGHT*Game.SCALE-32)) {
            	removeEntity(entB);
                game.setEnemy_killed(game.getEnemy_killed()+1);

            }
            
            entB.tick();
        }
    }

	public void render(Graphics g) {
    	
    	//entity A
        for (int i =0;i< ea.size();i++) {
            entA = ea.get(i);
            entA.render(g);
        }
        
        //entity B
        for (int i =0;i< eb.size();i++) {
            entB = eb.get(i);
            
            entB.render(g);
        }
    }
    
    public void addEntity(EntityA block) {
    	ea.add(block);
    }
    
    public void removeEntity(EntityA block) {
    	ea.remove(block);
    }
    
    public void addEntity(EntityB block) {
    	eb.add(block);
    }
    
    public void removeEntity(EntityB block) {
    	eb.remove(block);
    }
    
    public void createEnemy(int enemy_count) {
        for(int i=0;i <enemy_count ; i++) {
        	
        	if(r.nextInt(10)<5) {
        		setbarricade = false;
        	}else {
        		setbarricade = true;
        	}
        	
        	
        	if(Game.State == STATE.MENU ) {
            	addEntity(new Enemy(r.nextInt(Game.WIDTH*Game.SCALE+32),0, false, false, tex,this,game,setbarricade));

        	
        	}else if(Game.State == STATE.GAME_OVER ) {
            	addEntity(new Enemy(r.nextInt(Game.WIDTH*Game.SCALE+32),0, false, false, tex,this,game,setbarricade));
        	
        	}
        	else if(Game.State == STATE.Level_1_2) {
                addEntity(new Enemy(10, r.nextInt(Game.HEIGHT*Game.SCALE),false, true,tex,this,game,setbarricade));
                addEntity(new Enemy((Game.WIDTH*Game.SCALE-32), r.nextInt(Game.HEIGHT*Game.SCALE),true, false,tex,this,game,setbarricade));

        	}else if(Game.State == STATE.LEVEL1) {
            	addEntity(new Enemy(r.nextInt(Game.WIDTH*Game.SCALE-32),0, false, false, tex,this,game, setbarricade));
            	
            }else if(Game.State == STATE.LEVEL2) {
                addEntity(new Enemy(10, r.nextInt(Game.HEIGHT*Game.SCALE),false, true,tex,this,game,setbarricade));
                addEntity(new Enemy((Game.WIDTH*Game.SCALE-32), r.nextInt(Game.HEIGHT*Game.SCALE),true, false,tex,this,game,setbarricade));
        
            }
        
         }
    }
    
    public LinkedList<EntityA> getEntityA(){
    	return ea;
    }
    
    public LinkedList<EntityB> getEntityB(){
    	return eb;
    }
    
	public void addbon(Bonus Tempbon) {
		bon.add(Tempbon);
	}
	
	public void setBon(LinkedList<Bonus> bon) {
		this.bon = bon;
	}
	
	public LinkedList<Bonus> getBon() {
		return bon;
	}

}