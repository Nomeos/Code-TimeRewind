package view.guis;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import controller.MenuController;
import lombok.NoArgsConstructor;
import main.Game;
import model.button.*;

/**
 * 
 * @author mathieu.rabot
 *
 */
@NoArgsConstructor
public class GuiMenu extends Gui {

	private int[] durationBetweenCharacterFrame = { 150, 150, 150, 150, 150, 150 };
	private Animation knightWalkingAnimation;
	private Image backgroundImage;
	private Image secondBackgroundImage;

	// Used for do the sliding mainscreen
	private float backX = 0;
	private float backY = 0;
	private float backDX = 1920;
	private float backDY = 0;
	private float speed = 0.10f;
	private List<Button> listOfCurrentButton;
	private MenuController controller;

	public GuiMenu(int state) {
		super(state);
		try {
			this.init();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void init() throws SlickException {
		this.controller = new MenuController(this);
		this.buttonNeeded = new int[] { 0, 1 };
		this.listOfCurrentButton = new ArrayList<Button>();
		for (int i : this.buttonNeeded) {
			this.listOfCurrentButton.add(this.getListOfButtons().get(i));

		}

		this.backgroundImage = this.getListOfBackgrounds().get(0);
		this.secondBackgroundImage = this.getListOfBackgrounds().get(0);

		Image[] knightWalking = { new Image("/res/entity/Nom-eos/Walk/Knight_walk_01_uninterlace.png"),
				new Image("/res/entity/Nom-eos/Walk/Knight_walk_02_uninterlace.png"),
				new Image("/res/entity/Nom-eos/Walk/Knight_walk_03_uninterlace.png"),
				new Image("/res/entity/Nom-eos/Walk/Knight_walk_04_uninterlace.png"),
				new Image("/res/entity/Nom-eos/Walk/Knight_walk_05_uninterlace.png"),
				new Image("/res/entity/Nom-eos/Walk/Knight_walk_06_uninterlace.png") };
		knightWalkingAnimation = new Animation(knightWalking, durationBetweenCharacterFrame);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(backgroundImage, backX, backY);
		g.drawImage(secondBackgroundImage, backDX, backDY);
		for (Button button : this.listOfCurrentButton) {
			button.draw();
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
		} else if (backDX <= -1910) {
			backDX = 1910;
		}

	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		for (Button b : this.listOfCurrentButton) {
			if (b.isHovering(x, y) && button == 0) {
				b.setPressed(false);
			}
		}
		for (Button b : this.listOfCurrentButton) {
			if (b.isHovering(x, y) && button == 0) {
				this.controller.activeButton(b);
			}
		}

	}

	@Override
	public void mousePressed(int button, int x, int y) {
		for (Button b : this.listOfCurrentButton) {
			if (b.isHovering(x, y) && button == 0) {
				b.setPressed(true);
			}
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
}
