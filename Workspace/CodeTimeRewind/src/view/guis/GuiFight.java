package view.guis;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Game;
import model.fight.Fight;
import model.level.Level;

@NoArgsConstructor
@Getter
@Setter
public class GuiFight extends BasicGameState {

	private int stateId;
	private Image background;
	private Level currentLevel;
	private Fight fight;

	public GuiFight(int stateId) {
		this.stateId = stateId;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.background = new Image("/res/Start_SunsetM.png");
		this.fight = new Fight();
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		this.background.draw(0, 0);
		if (this.fight.getListOfCharacter() != null || this.fight.getListOfEnemy() != null) {
			this.fight.drawEntities(gc, g);

		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (Game.getInstance() != null) {
			int i = 1;
			int chapter = Game.getInstance().getCurrentChapter();
			for (Level level : Game.getInstance().getListOfChapters().get(chapter - 1)) {
				if (i == Game.getInstance().getCurrentLevel()) {
					this.currentLevel = level;
					break;
				} else {
					i++;
				}

			}
			this.fight = new Fight(Game.getInstance().getPlayerAccount().getListOfOwnedCharacter(),
					this.currentLevel.getListOfEnemy());

		}

	}

	@Override
	public int getID() {

		return stateId;
	}

}
