package view.guis;

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
import model.button.*;

@NoArgsConstructor
public class GuiMenu extends BasicGameState {

	private int[] durationBetweenCharacterFrame = { 150, 150, 150, 150, 150, 150 };
	private Animation knightWalkingAnimation;
	private Image backgroundImage;
	private Image secondBackgroundImage;
	private Button playButton;
	private Button exitButton;
	// Used for do the sliding mainscreen
	private float backX = 0;
	private float backY = 0;
	private float backDX = 1920;
	private float backDY = 0;
	private float speed = 0.10f;

	public GuiMenu(int state) {
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

		this.playButton = new MediumButton(new Image("/res/buttons/PlayButton.png"),
				new Image("/res/buttons/PlayButtonHit.png"), ((gc.getWidth() / 2) - (600 / 2)), 350);

		this.exitButton = new MediumButton(new Image("/res/buttons/ExitButton.png"),
				new Image("/res/buttons/ExitButtonHit.png"), ((gc.getWidth() / 2) - (600 / 2)), 600);
		
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
		this.playButton.draw();
		this.exitButton.draw();
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
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		this.playButton.setPressed(false);
		this.exitButton.setPressed(false);
		if (this.playButton.isHovering(x, y) && button == 0) {
			Game.getInstance().enterState(1, new FadeOutTransition(), new FadeInTransition());
		}

		if (this.exitButton.isHovering(x, y) && button == 0) {
			Game.getInstance().getContainer().exit();
		}

	}

	@Override
	public void mousePressed(int button, int x, int y) {
		if (this.playButton.isHovering(x, y) && button == 0) {
			this.playButton.setPressed(true);
		}
		if (this.exitButton.isHovering(x, y) && button == 0) {
			this.exitButton.setPressed(true);
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
}
