package view.guis;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Game;
import model.button.Button;
import model.button.LevelButton;
import model.level.Level;

@NoArgsConstructor
@Getter
@Setter
public class GuiAdventure extends BasicGameState {
	private int stateId;
	private Image background;
	private Circle circle1;
	private List<Level> listOfLevels;
	private List<LevelButton> listOfButtons;
	private boolean initializeButtons = false;

	public GuiAdventure(int state) {
		this.stateId = state;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.background = new Image("/res/Half_Day.png");
		this.listOfLevels = new ArrayList<Level>();
		this.listOfButtons = new ArrayList<LevelButton>();

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		this.background.draw(0, 0);
		g.setFont(new TrueTypeFont(new Font("Yu Gothic UI", Font.BOLD, 30), true));

		if (!this.listOfLevels.isEmpty()) {
			int i = 1;
			int chapterId = Game.getInstance().getCurrentChapter();
			if (!initializeButtons) {
				for (Level l : this.listOfLevels) {
					LevelButton button = new LevelButton(new Image("/res/buttons/LevelButton.png"),
							new Image("/res/buttons/LevelButtonHit.png"), l.getXPosition(), l.getYPosition());
					button.setLevelId(i);
					this.listOfButtons.add(button);
					button.draw();
					String s = chapterId + " - " + i;
					g.drawString(s, l.getXPosition() + button.getWidth() - 100,
							l.getYPosition() + button.getHeight() - 100);
					i++;
				}
				this.initializeButtons = true;
				this.listOfButtons = new ArrayList<LevelButton>(this.listOfButtons);
			} else {
				for (Button button : this.listOfButtons) {
					button.draw();
					String s = chapterId + " - " + i;
					g.drawString(s, button.getX() + button.getWidth() - 100,
							button.getY() + button.getHeight() - 100);
					i++;
				}

			}
		}

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (Game.getInstance() != null) {
			this.listOfLevels = Game.getInstance().getListOfChapters().get(Game.getInstance().getCurrentChapter() - 1);
		}
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		for (Button button1 : this.listOfButtons) {
			if (button1.isHovering(x, y) && button == 0) {
				button1.setPressed(true);
			}
		}

	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		for (LevelButton button1 : this.listOfButtons) {
			button1.setPressed(false);
			if (button1.isHovering(x, y) && button == 0) {
				Game.getInstance().setCurrentLevel(button1.getLevelId());
				Game.getInstance().enterState(7, new FadeOutTransition(), new FadeInTransition());
			}
		}

	}

	@Override
	public int getID() {
		return stateId;
	}

}
