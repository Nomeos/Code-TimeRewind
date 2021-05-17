package view.guis;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import controller.SummonController;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Game;
import model.account.Account;
import model.button.Button;
import model.livingEntity.LivingEntity;

@Getter
@Setter
@NoArgsConstructor
public class GuiSummon extends Gui {

	private Account player;
	private List<Button> listOfCurrentButton;
	private SummonController controller;
	private Image background;
	private LivingEntity currentCharacterOwned;

	public GuiSummon(int state) {
		super(state);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.controller = new SummonController(this);
		this.buttonNeeded = new int[] { 8, 17 };
		this.background = this.getListOfBackgrounds().get(8);
		this.player = Game.getInstance().getPlayerAccount();

		this.listOfCurrentButton = new ArrayList<Button>();
		for (int i : this.buttonNeeded) {
			this.listOfCurrentButton.add(this.getListOfButtons().get(i));
		}

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		this.background.draw(0, 0);
		for (Button b : this.listOfCurrentButton) {
			b.draw();
		}
		if(this.currentCharacterOwned != null) {
			this.currentCharacterOwned.getImage().draw(1200,750);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

	}

	@Override
	public void keyReleased(int key, char c) {

	}

	@Override
	public void keyPressed(int key, char c) {

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

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {

	}

}
