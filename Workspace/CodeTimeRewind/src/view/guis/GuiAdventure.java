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
import model.button.SmallButton;
import model.level.Level;

@NoArgsConstructor
@Getter
@Setter
public class GuiAdventure extends BasicGameState {
	private int stateId;
	private Image background;
	private Button lobbyButton;
	private Button adventureButton;
	private Circle circle1;
	private List<Level> listOfLevels;
	private List<Button> listOfButtons;
	private boolean initializeButtons = false;

	public GuiAdventure(int state) {
		this.stateId = state;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.background = new Image("/res/Half_Day.png");
		this.listOfLevels = new ArrayList<Level>();
		this.listOfButtons = new ArrayList<Button>();

		this.lobbyButton = new SmallButton(new Image("/res/buttons/LobbyButton.png"),
				new Image("/res/buttons/LobbyButtonHit.png"), 50, gc.getHeight() - 110);
		this.adventureButton = new SmallButton(new Image("/res/buttons/AdventureButton2.png"),
				new Image("/res/buttons/AdventureButton2Hit.png"), 450, gc.getHeight() - 110);

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		this.background.draw(0, 0);
		g.setFont(new TrueTypeFont(new Font("Yu Gothic UI", Font.BOLD, 30), true));
		g.setColor(Color.white);
		this.adventureButton.draw();
		this.lobbyButton.draw();
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
				this.listOfButtons = new ArrayList<Button>(this.listOfButtons);
			} else {
				for (Button button : this.listOfButtons) {
					button.draw();
					String s = chapterId + " - " + i;
					g.drawString(s, button.getX() + button.getWidth() - 100, button.getY() + button.getHeight() - 100);
					i++;
				}

			}
		}

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (Game.getInstance() != null) {
			this.listOfLevels = new ArrayList<Level>(
					Game.getInstance().getListOfChapters().get(Game.getInstance().getCurrentChapter() - 1));
		}
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		for (Button button1 : this.listOfButtons) {
			if (button1.isHovering(x, y) && button == 0) {
				button1.setPressed(true);
			}
		}
		if (this.lobbyButton.isHovering(x, y) && button == 0) {
			this.lobbyButton.setPressed(true);
		}
		if (this.adventureButton.isHovering(x, y) && button == 0) {
			this.adventureButton.setPressed(true);
		}

	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		this.lobbyButton.setPressed(false);
		this.adventureButton.setPressed(false);
		for (Button currentButton : this.listOfButtons) {
			currentButton.setPressed(false);
			if (currentButton.isHovering(x, y) && button == 0) {
				if (currentButton instanceof LevelButton) {
					Game.getInstance().setCurrentLevel(((LevelButton) currentButton).getLevelId());
					Game.getInstance().enterState(7, new FadeOutTransition(), new FadeInTransition());
				}
			}
		}
		if (this.lobbyButton.isHovering(x, y) && button == 0) {
			this.listOfButtons.clear();
			this.listOfLevels.clear();
			this.initializeButtons = false;
			Game.getInstance().enterState(3, new FadeOutTransition(), new FadeInTransition());
		} else if (this.adventureButton.isHovering(x, y) && button == 0) {
			this.listOfButtons.clear();
			this.listOfLevels.clear();
			this.initializeButtons = false;
			Game.getInstance().enterState(5, new FadeOutTransition(), new FadeInTransition());

		}

	}

	@Override
	public int getID() {
		return stateId;
	}

}
