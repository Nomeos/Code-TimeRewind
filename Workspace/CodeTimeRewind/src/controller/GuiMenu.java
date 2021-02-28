package controller;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import lombok.NoArgsConstructor;
import main.Game;

@NoArgsConstructor
public class GuiMenu extends BasicGameState {

	// Set the two buttons size and location
	private int buttonXPosition, buttonWidth, buttonHeight;

	// In Miliseconds
	private int[] durationBetweenCharacterFrame = { 150, 150, 150, 150, 150, 150 };
	private Animation knightWalkingAnimation;

	private Image backgroundImage, secondBackgroundImage, playButton, exitButton;

	// Use for do the sliding mainscreen
	private float backX = 0, backY = 0, backDX = 1920, backDY = 0, speed = 0.10f;

	// Check if the buttons are pressed and initialize
	private boolean isPressedPlay = false, initializePlay = true, isPressedExit = false, initializeExit = true;

	public GuiMenu(int state) {
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.buttonHeight = 150;
		this.buttonWidth = 600;
		this.buttonXPosition = ((gc.getWidth() / 2) - (buttonWidth / 2));

		this.backgroundImage = new Image("/res/Main_Screen_Background.png");
		this.secondBackgroundImage = new Image("/res/Main_Screen_Background.png");

		Image[] knightWalking = { new Image("/res/entity/Nom-eos/Walk/Knight_walk_01_uninterlace.png"),
				new Image("/res/entity/Nom-eos/Walk/Knight_walk_02_uninterlace.png"),
				new Image("/res/entity/Nom-eos/Walk/Knight_walk_03_uninterlace.png"),
				new Image("/res/entity/Nom-eos/Walk/Knight_walk_04_uninterlace.png"),
				new Image("/res/entity/Nom-eos/Walk/Knight_walk_05_uninterlace.png"),
				new Image("/res/entity/Nom-eos/Walk/Knight_walk_06_uninterlace.png") };
		knightWalkingAnimation = new Animation(knightWalking, durationBetweenCharacterFrame);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(backgroundImage, backX, backY);
		g.drawImage(secondBackgroundImage, backDX, backDY);
		if (initializePlay) {
			this.playButton = new Image("/res/buttons/PlayButton.png");
			this.playButton.draw(buttonXPosition, 350);
		} else {
			if (!isPressedPlay) {
				this.playButton.destroy();
				this.playButton = new Image("/res/buttons/PlayButton.png");
				this.playButton.draw(buttonXPosition, 350);
			} else {
				this.playButton.destroy();
				this.playButton = new Image("/res/buttons/PlayButtonHit.png");
				this.playButton.draw(buttonXPosition, 350);
			}
		}
		if (initializeExit) {
			this.exitButton = new Image("/res/buttons/ExitButton.png");
			this.exitButton.draw(buttonXPosition, 600);
		} else {
			if (!isPressedExit) {
				this.exitButton.destroy();
				this.exitButton = new Image("/res/buttons/ExitButton.png");
				this.exitButton.draw(buttonXPosition, 600);
			} else {
				this.exitButton.destroy();
				this.exitButton = new Image("/res/buttons/ExitButtonHit.png");
				this.exitButton.draw(buttonXPosition, 600);
			}
		}
		knightWalkingAnimation.draw(100, 700);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO Auto-generated method stub
		backX -= speed * delta;
		backDX -= speed * delta;
		if (backX <= -1910) {
			backX = 1910;
		}
		if (backDX <= -1910) {
			backDX = 1910;
		}

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		this.isPressedPlay = false;
		this.isPressedExit = false;
		if (buttonXPosition <= x && (buttonXPosition + buttonWidth) >= x && 350 <= y && (350 + buttonHeight) >= y
				&& button == 0) {
			Game.getInstance().enterState(1, new FadeOutTransition(), new FadeInTransition());
		}

		if (buttonXPosition <= x && (buttonXPosition + buttonWidth) >= x && 600 <= y && (600 + buttonHeight) >= y
				&& button == 0) {
			Game.getInstance().getContainer().exit();
		}

	}

	@Override
	public void mousePressed(int button, int x, int y) {
		if (buttonXPosition <= x && (buttonXPosition + buttonWidth) >= x && 350 <= y && (350 + buttonHeight) >= y
				&& button == 0) {
			this.initializePlay = false;
			this.isPressedPlay = true;
		}
		if (buttonXPosition <= x && (buttonXPosition + buttonWidth) >= x && 600 <= y && (600 + buttonHeight) >= y
				&& button == 0) {
			this.initializeExit = false;
			this.isPressedExit = true;
		}

	}
}
