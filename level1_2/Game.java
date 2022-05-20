package game.level1_2;


import game.classes.*;
import game.level3.Bullet3;
import game.level3.Controller3;
import game.level3.Player3;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 480;
	public static final int HEIGHT = 320;
	public static final int SCALE = 2;
	public static int TIMESECOND = 0;
	public static int TIMEMINUTE = 1;	

	long end =0;
	
	public final String TITLE = "Diablo 2077";
	
	private boolean running = false;
	private Thread thread;
	
	private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null ;
	private BufferedImage background = null;
	private BufferedImage background1 = null;
	private BufferedImage background2 = null;
	private BufferedImage background3 = null;
	private BufferedImage background12 = null;
	private BufferedImage background23 = null;
	private BufferedImage gameover = null;
	private BufferedImage youwin = null;
	
	public static String p_name = "";

	private boolean is_shooting = false;	
	private int enemy_count = 20;
	private int enemy_killed = 0;
	
	public static GameTimer gtime;

	private Player p;
	private Controller c;
	private Player3 p3;
	private Controller3 c3;
	private Textures tex; 
	private Menu menu;
	private GameOver gameOver;
	private youWon youWon;

	private Level_1_2 level_1_2;
	private Level_2_3 level_2_3;

	public LinkedList<EntityA> ea;
	public LinkedList<EntityB> eb;
	
	public LinkedList<EntityA> ea2;
	public LinkedList<EntityB> eb2;
	
	public static int HEALTH = 100 *2;
	public static int SCORE = 0;
    
    String[] names = new String[11];
    int[] scores = new int[11];     

	public static enum STATE{
		MENU,
		LEVEL1,
		Level_1_2,
		LEVEL2,
		Level_2_3,
		LEVEL3,
		GAME_OVER,
		YOU_WON		
		
	};
		
	public static STATE State = STATE.MENU;
	
	public void init() {
		
		requestFocus(); // with this we dont need to click the board to start the key input
		BufferedImageLoader loader = new BufferedImageLoader();
		try { 
			spriteSheet = loader.loadImage("/sprite_sheet.png");
			background = loader.loadImage("/menu.png");
			background1 = loader.loadImage("/background1.png");
			background2 = loader.loadImage("/background2.png");
			background3 = loader.loadImage("/background3.png");
			background12 = loader.loadImage("/level1-2.png");
			background23 = loader.loadImage("/level2-3.png");
			gameover = loader.loadImage("/die.png");
			youwin = loader.loadImage("/youwin.png");

		}catch(IOException e) {
			e.printStackTrace();
		}
		
		addKeyListener(new KeyInput(this));
		
		tex = new Textures(this);

		c = new Controller(this,tex);
		p = new Player(200,200,tex,this, c);

		menu = new Menu();
		gameOver = new GameOver();
		youWon = new youWon();

		level_1_2 = new Level_1_2();
		level_2_3 = new Level_2_3();
		
		p3 = new Player3(200,200,tex,2,0);
		c3 = new Controller3(this,tex);
		
		ea = c.getEntityA();
		eb = c.getEntityB();
		
		this.addMouseListener(new MouseInput());
		c.createEnemy(enemy_count);
		c3.createEnemy(enemy_count);

		
	}

	private synchronized void start() {
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	private synchronized void stop() {
		if (!running)
			return;
		
		running = false ;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
		
	}
	
	public void run(){
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis()- timer > 1000) {
				timer += 1000;
				updates = 0;
				frames = 0;
				
			}
		}
		stop();
		
	}
	
	private void tick(){
		

		if(State == STATE.MENU) {
			p.tick();
			c.tick();

		}else if(State == STATE.LEVEL1) {
			p.tick();
			c.tick();
			
			
			long start = System.currentTimeMillis();
			start = (start/1000)% 60;
			if(end+1 == start ) {
				
				if(!(TIMESECOND == 0)) {
					TIMESECOND -=1;

				}
				
				if(!(TIMEMINUTE == 0) && TIMESECOND==0 ) {
					TIMEMINUTE -=1;
					TIMESECOND = 59;

				}
				
				
				

			}

			end = start;

		}else if(State == STATE.LEVEL2) {
			p.tick();
			c.tick();
			
			long start = System.currentTimeMillis();
			start = (start/1000)% 60;
			if(end+1 == start ) {
				
				if(!(TIMESECOND == 0)) {
					TIMESECOND -=1;

				}
				
				if(!(TIMEMINUTE == 0) && TIMESECOND==0 ) {
					TIMEMINUTE -=1;
					TIMESECOND = 59;

				}
				
				
				

			}

			end = start;
			
		}else if(State == STATE.LEVEL3) {
			c3.isdying(p3);
			p3.tick();
			c3.tick();
			
			long start = System.currentTimeMillis();
			start = (start/1000)% 60;
			if(end+1 == start ) {
				
				if(!(TIMESECOND == 0)) {
					TIMESECOND -=1;

				}
				
				if(!(TIMEMINUTE == 0) && TIMESECOND==0 ) {
					TIMEMINUTE -=1;
					TIMESECOND = 59;

				}
				
				
				

			}

			end = start;
			
		}else if(State == STATE.GAME_OVER) {			
			p.setVelX(0);
			p.setVelY(0);
			
			c = new Controller(this,tex);
			p = new Player(200,200,tex,this, c);
			ea = c.getEntityA();
			eb = c.getEntityB();
			c.createEnemy(enemy_count);
			
			enemy_count = 20;
			enemy_killed =0;
			is_shooting = false;
			
		}else if(State == STATE.YOU_WON) {			
			p.setVelX(0);
			p.setVelY(0);
			p3.setVelX(0);
			p3.setVelY(0);
		
		}else if(State == STATE.Level_1_2) {			
			p.setVelX(0);
			p.setVelY(0);
			
			c = new Controller(this,tex);
			p = new Player(200,200,tex,this, c);
			ea = c.getEntityA();
			eb = c.getEntityB();
			c.createEnemy(enemy_count);
			
			enemy_count =10;
			enemy_killed =0;
			is_shooting = false;

		}else if(State == STATE.Level_2_3) {
			p3.setVelX(0);
			p3.setVelY(0);
			
			enemy_count = 4;
			enemy_killed = 0;
			
			is_shooting = false;

		}
		
		if(enemy_killed > 3 ) {
			if(State == STATE.Level_1_2 || State == STATE.LEVEL2 ) {
				enemy_killed = enemy_killed/2;
				c.createEnemy(enemy_killed);
				enemy_count = 20;
				enemy_killed =0;

			}else if(State == STATE.MENU || State == STATE.LEVEL1){
				c.createEnemy(enemy_killed);
				enemy_killed =0;
				
			}
			
		}
		if(enemy_killed >= 4 ) {
			c3.createEnemy(enemy_killed);
			enemy_count = 5;
			enemy_killed =0;
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		///////////////////////////////////////////////
		g.drawImage(image, 0 , 0 ,getWidth(),getHeight(), this);
		g.setColor(Color.black);
		g.fillRect(0,0,1000,1000);
		g.drawImage(background,0,0,null);
		
		
		if(State == STATE.MENU) {
			g.drawImage(background,0,0,null);
			menu.render(g);
			
		}else if(State == STATE.LEVEL1) {
            g.drawImage(background1,0,0,null);
         
            
			

            p.render(g);
            c.render(g);
            
            Font fnt0 = new Font("arial", Font.BOLD, 20);
            g.setFont(fnt0);
            g.setColor(Color.yellow);

            g.drawString("Level 1", Game.WIDTH + 400, Game.HEIGHT - 270);
            g.drawString("Score: " + String.valueOf(SCORE), Game.WIDTH + 370, Game.HEIGHT - 240);
            g.drawString("Velocity X: " + String.valueOf(Player.velX), Game.WIDTH + 330, Game.HEIGHT - 210);
            g.drawString("Velocity Y: " + String.valueOf(Player.velY), Game.WIDTH + 330, Game.HEIGHT - 180);
            g.drawString("Remaining time : "+String.valueOf(TIMEMINUTE)+ "." + String.valueOf(TIMESECOND) , WIDTH + 265 , HEIGHT - 150);

            g.setColor(Color.GRAY);
            g.fillRect(5, 5, 200, 50);
            
            g.setColor(Color.green);
            g.fillRect(5, 5, HEALTH, 50);
            
            g.setColor(Color.white);
            g.drawRect(5, 5, 200, 50);
            
            
            
        }else if(State == STATE.LEVEL2) {
            
            g.drawImage(background2,0,0,null);

            g.setColor(Color.yellow);

            p.render(g);
            c.render(g);
            
            Font fnt0 = new Font("arial", Font.BOLD, 20);
            g.setFont(fnt0);
            g.setColor(Color.yellow);

            g.drawString("Level 2", Game.WIDTH + 400, Game.HEIGHT - 270);
            g.drawString("Score: " + String.valueOf(SCORE), Game.WIDTH + 370, Game.HEIGHT - 240);
            g.drawString("Velocity X: " + String.valueOf(Player.velX), Game.WIDTH + 330, Game.HEIGHT - 210);
            g.drawString("Velocity Y: " + String.valueOf(Player.velY), Game.WIDTH + 330, Game.HEIGHT - 180);
            g.drawString("Remaining time : "+String.valueOf(TIMEMINUTE)+ "." + String.valueOf(TIMESECOND) , WIDTH + 265 , HEIGHT - 150);
			
            g.setColor(Color.GRAY);
            g.fillRect(5, 5, 200, 50);
            
            g.setColor(Color.green);
            g.fillRect(5, 5, HEALTH, 50);
            
            g.setColor(Color.white);
            g.drawRect(5, 5, 200, 50);
        
        
        }else if(State == STATE.LEVEL3) {
            g.drawImage(background3,0,0,null);
            
            g.setColor(Color.yellow);
		
			p3.render(g);
			c3.render(g);
						
			Font fnt0 = new Font("arial", Font.BOLD, 20);
            g.setFont(fnt0);
            g.setColor(Color.yellow);

            g.drawString("Level 3", Game.WIDTH + 400, Game.HEIGHT - 270);
            g.drawString("Score: " + String.valueOf(SCORE), Game.WIDTH + 370, Game.HEIGHT - 240);
            g.drawString("Velocity X: " + String.valueOf(Player.velX), Game.WIDTH + 330, Game.HEIGHT - 210);
            g.drawString("Velocity Y: " + String.valueOf(Player.velY), Game.WIDTH + 330, Game.HEIGHT - 180);
            g.drawString("Remaining time : "+String.valueOf(TIMEMINUTE)+ "." + String.valueOf(TIMESECOND) , WIDTH + 265 , HEIGHT - 150);
			
            g.setColor(Color.GRAY);
            g.fillRect(5, 5, 200, 50);
            
            g.setColor(Color.green);
            g.fillRect(5, 5, HEALTH, 50);
            
            g.setColor(Color.white);
            g.drawRect(5, 5, 200, 50);

		}
		else if(State == STATE.GAME_OVER) {	
			g.drawImage(gameover,0,0,null);
			gameOver.render(g);

		}else if(State == STATE.Level_1_2) {
			g.drawImage(background12,0,0,null);
			level_1_2.render(g);

		}else if(State == STATE.Level_2_3) {
			g.drawImage(background23,0,0,null);
			level_2_3.render(g);

		}else if (State == STATE.YOU_WON) {
			g.drawImage(youwin,0,0,null);
			youWon.render(g);

		} 
		///////////////////////////////////////////////
		
	    g.dispose();
		bs.show();
	}


	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		if(State == STATE.MENU) {
        	if(e.getKeyChar() == 8) {
    		    if (p_name != null && p_name.length() > 0 ) {
    		    	p_name = p_name.substring(0, p_name.length() - 1);
    		    }
        	}else {
    		    p_name += e.getKeyChar();

        	}
        	
		
		}
		else if(State == STATE.LEVEL1) {
			if(key == KeyEvent.VK_RIGHT){
				p.setVelX(5);
			}else if(key == KeyEvent.VK_LEFT){
				p.setVelX(-5);
			}else if(key == KeyEvent.VK_DOWN){
				p.setVelY(5);
			}else if(key == KeyEvent.VK_UP){
				p.setVelY(-5);
			}else if(key == KeyEvent.VK_SPACE && is_shooting == false){
				is_shooting = true; 
				c.addEntity(new Bullet(p.getX(),p.getY(),tex, this,
						p.isUpAngle(), p.isDownAngle(), p.isRightAngle(), p.isLeftAngle()));
			}
		
		}else if(State == STATE.LEVEL2) {
			if(key == KeyEvent.VK_RIGHT){
				p.setVelX(5);
			}else if(key == KeyEvent.VK_LEFT){
				p.setVelX(-5);
			}else if(key == KeyEvent.VK_DOWN){
				p.setVelY(5);
			}else if(key == KeyEvent.VK_UP){
				p.setVelY(-5);
			}else if(key == KeyEvent.VK_SPACE && is_shooting == false){
				is_shooting = true; 
				c.addEntity(new Bullet(p.getX(),p.getY(),tex, this,
						p.isUpAngle(), p.isDownAngle(), p.isRightAngle(), p.isLeftAngle()));
			}
			
		}else if(State == STATE.LEVEL3) {
			if(key == KeyEvent.VK_RIGHT){
				p3.setVelX(5);
			}else if(key == KeyEvent.VK_LEFT){
				p3.setVelX(-5);
			}else if(key == KeyEvent.VK_DOWN){
				p3.setVelY(5);
			}else if(key == KeyEvent.VK_UP){
				p3.setVelY(-5);
			}else if(key == KeyEvent.VK_SPACE && is_shooting == false){
				is_shooting = true; 
				c3.addBullet(new Bullet3(p3.getX(),p3.getY(),tex, p3.getPbulletdamage(),
						p3.isUpAngle(), p3.isDownAngle(), p3.isRightAngle(), p3.isLeftAngle()));
		    }	
			
		} 
	}
	
	
	public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        
       if(State == STATE.LEVEL1) {
        	if(key == KeyEvent.VK_RIGHT) {
 				p.setVelX(0);
 			}else if(key == KeyEvent.VK_LEFT) {
 				p.setVelX(0);
 			}else if(key == KeyEvent.VK_DOWN) {
 				p.setVelY(0);
 			}else if(key == KeyEvent.VK_UP) {
 				p.setVelY(0);
 			}else if(key == KeyEvent.VK_SPACE) {
 				is_shooting = false;
 			}
            
        }else if(State == STATE.LEVEL2) {
        	if(key == KeyEvent.VK_RIGHT) {
 				p.setVelX(0);
 			}else if(key == KeyEvent.VK_LEFT) {
 				p.setVelX(0);
 			}else if(key == KeyEvent.VK_DOWN) {
 				p.setVelY(0);
 			}else if(key == KeyEvent.VK_UP) {
 				p.setVelY(0);
 			}else if(key == KeyEvent.VK_SPACE) {
 				is_shooting = false;
 			}
        	
        }else if(State == STATE.LEVEL3) {
        	if(key == KeyEvent.VK_RIGHT) {
    			p3.setVelX(0);
    		}else if(key == KeyEvent.VK_LEFT) {
    			p3.setVelX(0);
    		}else if(key == KeyEvent.VK_DOWN) {
    			p3.setVelY(0);
    		}else if(key == KeyEvent.VK_UP) {
    			p3.setVelY(0);
    		}else if(key == KeyEvent.VK_SPACE) {
    			is_shooting = false;
            }
        }
       
	}
	
	public static void main(String args[]) {
		Game game = new Game();
		
		game.setPreferredSize(new Dimension(WIDTH * SCALE , HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE , HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE , HEIGHT * SCALE));
		
		JFrame frame = new JFrame (game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
				
		game.start();
		
	}
	
	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}
	
	public void setSpriteSheet(BufferedImage spriteSheet) {
		this.spriteSheet = spriteSheet;
	}
	
	public Player getP() {
		return p;
	}

	public void setP(Player p) {
		this.p = p;
	}
	
	public Player3 getP3() {
		return p3;
	}
	public void setP3(Player3 p3) {
		this.p3 = p3;
	}
	

	public int getEnemy_count() {
		return enemy_count;
	}

	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}

	public int getEnemy_killed() {
		return enemy_killed;
	}

	public void setEnemy_killed(int enemy_killed) {
		this.enemy_killed = enemy_killed;
	}

	public boolean isIs_shooting() {
		return is_shooting;
	}

	public void setIs_shooting(boolean is_shooting) {
		this.is_shooting = is_shooting;
	}

	public static GameTimer getGtime() {
		return gtime;
	}

	public static void setGtime(GameTimer gtime) {
		Game.gtime = gtime;
	}

	public Controller getC() {
		return c;
	}

	public void setC(Controller c) {
		this.c = c;
	}
	
	public Textures getTex() {
		return tex;
	}

	public void setTex(Textures tex) {
		this.tex = tex;
	}

	public LinkedList<EntityA> getEa() {
		return ea;
	}

	public void setEa(LinkedList<EntityA> ea) {
		this.ea = ea;
	}

	public LinkedList<EntityB> getEb() {
		return eb;
	}

	public void setEb(LinkedList<EntityB> eb) {
		this.eb = eb;
	}

	

}