package game.classes;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import game.level1_2.Game;
import game.level1_2.Game.STATE;

public class MouseInput implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {

		int mx = e.getX();
		int my = e.getY();

		if (Game.State == STATE.MENU) {

			if (mx >= (Game.WIDTH +220) && mx <= (Game.WIDTH + 320)) {
				if (my >= 370 && my <= 420) {
					// Pressed playButton
					Game.State = Game.STATE.LEVEL1;
					
					Game.TIMEMINUTE = 1;
					Game.TIMESECOND = 0;
					
					Game.gtime = new GameTimer(62);

				}
			}

			// helpButton
			if (mx >= (Game.WIDTH +220) && mx <= (Game.WIDTH + 320)) {
				if (my >= 460 && my <= 510) {
					JOptionPane.showMessageDialog(null,
							"This is a space game.\nYour goal is to defend the world against the enemies and kill all the aliens."
									+ "\nUse arrow keys to move your character."
									+ "\nUse space bar to shoot the aliens.");
				}
			}

			// quitButton
			if (mx >= (Game.WIDTH +220) && mx <= (Game.WIDTH + 320)) {
				if (my >= 550 && my <= 600) {
					// Pressed quitButton
					System.exit(1);
				}
			}
		}

		if (Game.State == STATE.GAME_OVER) {

			/**
			 * public Rectangle quitButton = new Rectangle(Game.WIDTH +140, 270 ,100,50);
			   public Rectangle playAgainButton = new Rectangle(Game.WIDTH + 280, 270 ,180,50);
			 */
			
			// quitButton
			if (mx >= (Game.WIDTH + 140) && mx <= (Game.WIDTH + 240)) {
				if (my >= 270 && my <= 320) {
					// Pressed quitButton
					System.exit(1);
				}
			}

			// playAgainButton
			if (mx >= (Game.WIDTH + 280) && mx <= (Game.WIDTH + 460)) {
				if (my >= 270 && my <= 320) {
					Game.State = Game.STATE.LEVEL1;
					Game.HEALTH = 100 * 2;
					Game.SCORE = 0;
					
					Game.TIMEMINUTE = 1;
					Game.TIMESECOND = 0;
					
					Game.gtime.setSeconds(62);


				}
			}
		}

		if (Game.State == STATE.YOU_WON) {

			// quitButton
			if (mx >= (Game.WIDTH - 200) && mx <= (Game.WIDTH - 100)) {
				if (my >= 250 && my <= 300) {
					// Pressed quitButton
					System.exit(1);
				}
			}

			// playAgainButton
			if (mx >= (Game.WIDTH + 50) && mx <= (Game.WIDTH + 230)) {
				if (my >= 250 && my <= 300) {
					Game.State = Game.STATE.LEVEL1;
					Game.HEALTH = 100 * 2;
					Game.SCORE = 0;
					
					Game.TIMEMINUTE = 1;
					Game.TIMESECOND = 0;
					
					Game.gtime.setSeconds(62);

				}
			}
		}

		if (Game.State == STATE.Level_1_2) {
			if (mx >= (Game.WIDTH - 80) && mx <= (Game.WIDTH + 20)) {
				if (my >= 350 && my <= 400) {
					Game.HEALTH = 100 * 2;
					Game.State = Game.STATE.LEVEL2;
					
					Game.TIMEMINUTE = 1;
					Game.TIMESECOND = 0;
					
					Game.gtime.setSeconds(62);

				}
			}
			if (mx >= (Game.WIDTH - 80) && mx <= (Game.WIDTH + 20)) {
				if (my >= 450 && my <= 500) {
					// Pressed quitButton
					System.exit(1);
				}
			}
		}

		if (Game.State == STATE.Level_2_3) {
			if (mx >= (Game.WIDTH - 80) && mx <= (Game.WIDTH + 20)) {
				if (my >= 350 && my <= 400) {
					Game.HEALTH = 100 * 2;
					Game.State = Game.STATE.LEVEL3;
					
					Game.TIMEMINUTE = 1;
					Game.TIMESECOND = 0;
					
					Game.gtime.setSeconds(62);
				}
			}
			if (mx >= (Game.WIDTH - 80) && mx <= (Game.WIDTH + 20)) {
				if (my >= 450 && my <= 500) {
					// Pressed quitButton
					System.exit(1);
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}