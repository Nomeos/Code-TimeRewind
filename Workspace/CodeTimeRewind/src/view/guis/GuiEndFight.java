package view.guis;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import controller.EndFightController;
import main.Game;
import model.button.Button;
import model.button.SmallButton;
import model.livingEntity.character.Character;

public class GuiEndFight extends Gui {

	private Image backgroundImage;
	private Image title;
	private Character currentCharacter;
	private SmallButton leaveLevel;
	private List<Button> listOfCurrentButton;
	private EndFightController controller;
	private List<Rectangle> experienceBars;

	public GuiEndFight(int endFight) {

	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

		this.controller = new EndFightController(this);
		this.buttonNeeded = new int[] {};
		this.backgroundImage = new Image("/res/Night.png");
		this.title = new Image("/res/zones/StageClear.png");
		this.listOfCurrentButton = new ArrayList<Button>();
		for (int i : this.buttonNeeded) {
			this.listOfCurrentButton.add(this.getListOfButtons().get(i));
		}

		this.experienceBars = new ArrayList<Rectangle>();

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

		this.backgroundImage.draw(0, 0);
		this.title.draw((gc.getWidth() / 2) - (840 / 2), 50);
		this.leaveLevel.draw();
		if (this.currentCharacter != null) {
			this.currentCharacter.setInBattle(false);
			createLifeBars(gc, g);
			this.currentCharacter.render(200, 720, g);
			g.drawString("Earned experience : " + this.currentCharacter.getXpObtained(),
					202 + this.currentCharacter.getWidth(), 722 + (this.currentCharacter.getHeight() / 2) + 40);

		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		initializeVariables();
	}

	private void createLifeBars(GameContainer gc, Graphics g) {
		this.experienceBars.clear();
		this.experienceBars.add(new Rectangle(202 + this.currentCharacter.getWidth(),
				722 + (this.currentCharacter.getHeight() / 2) + 20,
				calculateNewExperienceBarWidth(this.currentCharacter), 11));
		this.experienceBars.add(new Rectangle(202 + this.currentCharacter.getWidth(),
				722 + (this.currentCharacter.getHeight() / 2) + 20,
				calculateOldExperienceBarWidth(this.currentCharacter), 11));

		int i = 0;
		for (Rectangle o : this.experienceBars) {
			if (i == 1 || i == 3 || i == 5 || i == 7) {
				g.setColor(Color.green);
				g.draw(o);
				g.fill(o);
			} else {
				g.setColor(Color.yellow);
				g.draw(o);
				g.fill(o);
			}
			i++;
		}
	}

	private int calculateOldExperienceBarWidth(Character c) {
		float result = c.getOldExperience() / (float) c.getMaxExperience();
		int result1 = Math.round(result * 145);
		if (result1 <= 0) {
			result1 = 0;
		}
		return result1;
	}

	private int calculateNewExperienceBarWidth(Character c) {
		float result = (c.getXpObtained() + c.getOldExperience()) / (float) c.getMaxExperience();
		int result1 = Math.round(result * 145);
		if (result1 <= 0) {
			result1 = 0;
		}
		return result1;
	}

	@Override
	public int getID() {

		return 8;
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

	private void initializeVariables() {
		if (Game.getInstance() != null) {
			this.currentCharacter = Game.getInstance().getPlayerAccount().getListOfOwnedCharacter().get(0);
		}
	}

}
