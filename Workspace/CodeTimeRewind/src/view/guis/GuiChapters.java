package view.guis;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import controller.ChapterController;
import main.Game;
import model.button.BigButton;
import model.button.Button;
import model.button.SmallButton;

public class GuiChapters extends Gui {

	private Image background;
	private List<Button> listOfCurrentButton;
	private ChapterController controller;

	public GuiChapters(int stateID) {
		super(stateID);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

		this.controller = new ChapterController(this);
		this.buttonNeeded = new int[] { 8, 9, 10, 11 };
		this.background = new Image("/res/End_Sunset.png");

		this.listOfCurrentButton = new ArrayList<Button>();
		for (int i : this.buttonNeeded) {
			this.listOfCurrentButton.add(this.getListOfButtons().get(i));
		}

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		this.background.draw(0, 0);
		for (Button button : this.listOfCurrentButton)
			button.draw();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

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
}
