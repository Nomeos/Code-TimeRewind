package view.guis;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

import controller.AdventureController;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Game;
import model.button.Button;
import model.button.LevelButton;
import model.stage.Stage;

@NoArgsConstructor
@Getter
@Setter
public class GuiAdventure extends Gui {
	private int stateId;
	private Image background;

	private List<Stage> listOfLevels;
	private boolean initializeButtons = false;
	private List<Button> listOfCurrentButton;
	private AdventureController controller;

	public GuiAdventure(int state) {
		super(state);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.controller = new AdventureController(this);
		this.buttonNeeded = new int[] { 8, 15 };
		this.background = this.getListOfBackgrounds().get(2);
		this.listOfLevels = new ArrayList<Stage>();
		this.listOfCurrentButton = new ArrayList<Button>();
		for (int i : this.buttonNeeded) {
			this.listOfCurrentButton.add(this.getListOfButtons().get(i));
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		this.background.draw(0, 0);
		g.setFont(new TrueTypeFont(new Font("Yu Gothic UI", Font.BOLD, 30), true));
		g.setColor(Color.white);
		for (Button b : this.listOfCurrentButton) {
			b.draw();
		}
		if (!this.listOfLevels.isEmpty()) {
			int i = 1;
			int chapterId = Game.getInstance().getCurrentChapter();
			if (!initializeButtons) {
				for (Stage l : this.listOfLevels) {
					LevelButton button = new LevelButton(new Image("/res/buttons/Button_13.png"),
							new Image("/res/buttons/Button_13_Hit.png"), l.getXPosition(), l.getYPosition());
					button.setLevelId(i);
					this.listOfCurrentButton.add(button);
					button.draw();
					String s = chapterId + " - " + i;
					g.drawString(s, l.getXPosition() + button.getWidth() - 100,
							l.getYPosition() + button.getHeight() - 100);
					i++;
				}
				this.initializeButtons = true;
				this.listOfCurrentButton = new ArrayList<Button>(this.listOfCurrentButton);
			} else {
				for (Button button : this.listOfCurrentButton) {
					button.draw();
					if(button instanceof LevelButton) {
						String s = chapterId + " - " + i;
						g.drawString(s, button.getX() + button.getWidth() - 100, button.getY() + button.getHeight() - 100);
						i++;
					}
					
				}

			}
		}

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (Game.getInstance() != null) {
			this.listOfLevels = new ArrayList<Stage>(
					Game.getInstance().getListOfChapters().get(Game.getInstance().getCurrentChapter() - 1));
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
