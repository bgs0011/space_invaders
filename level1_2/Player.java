package game.level1_2;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import game.classes.*;
import game.level1_2.Game.STATE;

public class Player extends GameObject implements EntityA {

	private boolean rightAngle;
	private boolean leftAngle;
	private boolean upAngle;
	private boolean downAngle;

	private LinkedList<Bonus> bon = new LinkedList<Bonus>();
	Bonus TempBon;

	private double score;

	public static double velX = 0;
    public static double velY = 0;

	private Textures tex;

	Game game;
	Controller controller;

	public Player(double x, double y, Textures tex, Game game, Controller controller) {
		super(x, y);
		this.tex = tex;
		this.game = game;
		this.controller = controller;
		rightAngle = false;
		leftAngle = false;
		upAngle = true;
		downAngle = false;

	}

	public void tick() {
		x += velX;
		y += velY;

		if (velX > 0) {
			rightAngle = true;
			if (velY < 0) {
				upAngle = true;
				downAngle = false;
			}

			else if (velY > 0) {
				downAngle = true;
				upAngle = false;
			} else {
				upAngle = false;
				downAngle = false;
			}

			leftAngle = false;
		} else if (velX < 0) {
			leftAngle = true;
			if (velY < 0) {
				upAngle = true;
				downAngle = false;
			} else if (velY > 0) {
				downAngle = true;
				upAngle = false;

			} else {
				upAngle = false;
				downAngle = false;

			}
			rightAngle = false;
		} else if (velY < 0) {
			upAngle = true;
			if (velX < 0) {
				leftAngle = true;
				rightAngle = false;
			} else if (velX > 0) {
				rightAngle = true;
				leftAngle = false;

			} else {
				leftAngle = false;
				rightAngle = false;

			}
			downAngle = false;
		} else if (velY > 0) {
			downAngle = true;
			if (velX < 0) {
				leftAngle = true;
				rightAngle = false;
			} else if (velX > 0) {
				rightAngle = true;
				leftAngle = false;

			} else {
				leftAngle = false;
				rightAngle = false;

			}
			upAngle = false;
		}

		if (x <= 0)
			x = 0;
		if (x >= Game.WIDTH * Game.SCALE - 32)
			x = Game.WIDTH * Game.SCALE - 32;
		if (y <= 0)
			y = 0;
		if (y >= Game.HEIGHT * Game.SCALE - 32)
			y = Game.HEIGHT * Game.SCALE - 32;

		for (int i = 0; i < game.eb.size(); i++) {
			EntityB tempEnt = game.eb.get(i);

			if (Physics.Collusion(this, tempEnt)) {
				controller.removeEntity(tempEnt);
				Game.HEALTH -= 5;
				Game.SCORE -= 3;

				if (Game.SCORE <= 0) {
					Game.SCORE = 0;
				}

				if (Game.HEALTH <= 0) {
					Game.State = Game.STATE.GAME_OVER;
				}

				if (tempEnt instanceof Enemy) {
					controller.createEnemy(1);

				}
			}

		}

		bon = controller.getBon();

		for (int i = 0; i < bon.size(); i++) {
			TempBon = bon.get(i);

			TempBon.setBonus(TempBon.getBonus() - 1);

			Rectangle rbon = new Rectangle((int) TempBon.getX(), (int) TempBon.getY(), 32, 32);
			if (this.getBounds().intersects(rbon.getBounds())) {
				bon.remove(i);
				if (TempBon.isIsnegative()) {
					Game.SCORE -= 3;
					Game.HEALTH -= 3;

				} else {
					Game.SCORE += 3;
					Game.HEALTH += 3;
				}
				if (Game.HEALTH >= 200) {
					Game.HEALTH = 200;
				}

			} else if (TempBon.getBonus() <= 0) {
				bon.remove(i);

			}
		}

	}

	public void render(Graphics g) {

		if (rightAngle) {
			g.drawImage(tex.playerright, (int) x, (int) y, null);

		} else if (leftAngle) {
			g.drawImage(tex.playerleft, (int) x, (int) y, null);

		} else if (upAngle) {
			g.drawImage(tex.playerup, (int) x, (int) y, null);

		} else if (downAngle) {
			g.drawImage(tex.playerdown, (int) x, (int) y, null);

		}

		for (int i = 0; i < bon.size(); i++) {
			TempBon = bon.get(i);

			TempBon.render(g);

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

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public LinkedList<Bonus> getBon() {
		return bon;
	}

	public void setBon(LinkedList<Bonus> bon) {
		this.bon = bon;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

}