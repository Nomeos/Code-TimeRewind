package view.guis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Game;
import model.animation.BezierPath;
import model.animation.PathAnimation;
import model.databaseManager.DatabaseCharacterManager;
import model.entity.Character;
import model.entity.Enemy;
import model.entity.Entity;
import model.fight.BattleCommand;
import model.fight.BattleController;
import model.level.Level;

@NoArgsConstructor
@Getter
@Setter
public class BattleGameState extends Gui {

	private int stateId;

	private Level currentLevel;
	private boolean initiliazeVariable = false;
	private GuiBattle hud;
	private BattleController battleController;

	private List<Rectangle> lifeBars;
	private List<Character> listOfCharacter;
	private List<Enemy> listOfEnemy;
	private List<Entity> orderedBattleTurn;
	private List<Integer> missingDeadEnnemies;

	public int currentTurn;
	public int currentEnemyAnimation = 0;

	private PathAnimation characterAnimation;
	private PathAnimation enemyAnimation;

	public BattleGameState(int stateId) {
		this.stateId = stateId;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

		this.hud = new GuiBattle();
		this.hud.init(gc);
		this.orderedBattleTurn = new ArrayList<Entity>();
		this.lifeBars = new ArrayList<Rectangle>();
		this.missingDeadEnnemies = new ArrayList<Integer>();
		this.currentTurn = 0;

		this.characterAnimation = new PathAnimation(new BezierPath(0, 0, 400, 1, -50, 20, 0, 0), 2000);
		this.enemyAnimation = new PathAnimation(new BezierPath(0, 0, -400, -1, -50, 20, 0, 0), 2000);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		initializeVariables();

		this.characterAnimation.update(delta);
		this.enemyAnimation.update(delta);
		int i = 1;
		for (Enemy e : this.listOfEnemy) {
			if (e.isFadingOut()) {
				if (!this.missingDeadEnnemies.contains(i)) {
					this.missingDeadEnnemies.add(i);
				}
				e.fadeOut();
			}
			i++;
		}

		if (this.currentTurn > this.orderedBattleTurn.size() - 1) {
			this.currentTurn = 0;
		}

		if (this.orderedBattleTurn.get(currentTurn) instanceof Enemy) {
			if (this.orderedBattleTurn.get(currentTurn).getHealth() <= 0) {
				this.orderedBattleTurn.remove(currentTurn);
				this.currentEnemyAnimation++;
			} else {
				setCurrentEnemyAnimation();
				this.battleController.setCurrentEnemy((Enemy) this.orderedBattleTurn.get(currentTurn));
				this.battleController.setCurrentCharacter(this.listOfCharacter.get(0));
				this.battleController.setEnemiesTurn(true);
				this.battleController.controlPressed(BattleCommand.SPELLONE);
			}

		} else {
			this.battleController.setEnemiesTurn(false);
			this.currentEnemyAnimation = 1;
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		if (this.battleController != null) {
			if (this.battleController.isEnemiesTurn() == false) {
				this.hud.setDrawButton(true);
			}
		}
		this.hud.render(gc, g);
		if (getListOfCharacter() != null && getListOfEnemy() != null) {
			drawEntities(gc, g);
			createLifeBars(gc, g);
			g.drawString("current turn : " + this.currentTurn, 50, 50);

		}

	}

	private int setCurrentEnemyAnimation() {
		for (Integer j : this.missingDeadEnnemies) {
			if (j == this.currentEnemyAnimation) {
				this.currentEnemyAnimation++;
			}
		}
		return this.currentEnemyAnimation;
	}

	public void drawEntities(GameContainer gc, Graphics g) {
		int numberOfCharacter = getListOfCharacter().size();
		int numberOfEnnemy = getListOfEnemy().size();
		drawCharacters(numberOfCharacter, gc, g);
		drawEnnemies(numberOfEnnemy, gc, g);

	}

	private void createLifeBars(GameContainer gc, Graphics g) {
		this.lifeBars.clear();

		for (Character c : this.listOfCharacter) {
			if (c.getHealth() > 0) {
				this.lifeBars.add(new Rectangle(c.getX() + 2, c.getY() - 18, calculateLifeBarWidth(c), 11));
			}
		}
		for (Enemy e : this.listOfEnemy) {
			if (e.getHealth() > 0) {
				this.lifeBars.add(new Rectangle(e.getX() + 2, e.getY() - 18, calculateLifeBarWidth(e), 11));
			}

		}
		g.setColor(Color.red);
		for (Rectangle o : this.lifeBars) {
			g.draw(o);
			g.fill(o);
		}
	}

	private int calculateLifeBarWidth(Entity c) {
		float result = c.getHealth() / (float) c.getMaxHealth();
		int result1 = Math.round(result * 145);
		if (result1 <= 0) {
			result1 = 0;
		}
		return result1;
	}

	private void drawEnnemies(int numberOfEnnemy, GameContainer gc, Graphics g) {
		int i = 0;
		Vector2f p = this.enemyAnimation.currentLocation();
		switch (numberOfEnnemy) {
		case 1:

			for (Enemy e : getListOfEnemy()) {
				e.render(Math.round(p.x + ((gc.getWidth() / 4) * 3) - e.getWidth()),
						Math.round(p.y + (gc.getHeight() - gc.getHeight() / 2)), g);

			}

			break;
		case 2:
			i = 1;
			for (Enemy e : getListOfEnemy()) {
				if (i == 1) {
					if (this.currentEnemyAnimation == i && !e.isFadingOut()) {
						e.render(Math.round(p.x + ((gc.getWidth() / 4) * 3) - e.getWidth()),
								Math.round(p.y + (gc.getHeight() - gc.getHeight() / 2)), g);
					} else {
						e.render((gc.getWidth() / 4) * 3 - e.getWidth(), gc.getHeight() - gc.getHeight() / 2, g);
					}

					i++;
				} else if (i == 2) {
					if (this.currentEnemyAnimation == i && !e.isFadingOut()) {
						e.render(Math.round(p.x + (gc.getScreenWidth() - gc.getWidth() / 4 - e.getWidth() + 350)),
								Math.round(p.y + (gc.getHeight() - gc.getHeight() / 2 - 30)), g);
					} else {
						e.render(gc.getScreenWidth() - gc.getWidth() / 4 - e.getWidth() + 350,
								gc.getHeight() - gc.getHeight() / 2 - 30, g);
					}

				}

			}

			break;
		case 3:
			i = 1;
			for (Enemy e : getListOfEnemy()) {
				if (i == 1) {
					if (this.currentEnemyAnimation == i && !e.isFadingOut()) {
						e.render(Math.round(p.x + ((gc.getWidth() / 4) * 3) - e.getWidth()),
								Math.round(p.y + (gc.getHeight() - gc.getHeight() / 2)), g);
					} else {
						e.render((gc.getWidth() / 4) * 3 - e.getWidth(), gc.getHeight() - gc.getHeight() / 2, g);
					}
					i++;
				} else if (i == 2) {
					if (this.currentEnemyAnimation == i && !e.isFadingOut()) {
						e.render(Math.round(p.x + (gc.getScreenWidth() - gc.getWidth() / 4 - e.getWidth() + 350)),
								Math.round(p.y + (gc.getHeight() - gc.getHeight() / 2 - 30)), g);
					} else {
						e.render(gc.getScreenWidth() - gc.getWidth() / 4 - e.getWidth() + 350,
								gc.getHeight() - gc.getHeight() / 2 - 30, g);
					}
					i++;
				} else {
					if (this.currentEnemyAnimation == i && !e.isFadingOut()) {
						e.render(Math.round(p.x + (gc.getScreenWidth() - gc.getWidth() / 4 - e.getWidth() + 200)),
								Math.round(p.y + (gc.getHeight() - gc.getHeight() / 2 - 155)), g);
					} else {
						e.render(gc.getScreenWidth() - gc.getWidth() / 4 - e.getWidth() + 200,
								gc.getHeight() - gc.getHeight() / 2 - 155, g);
					}

				}

			}
			break;
		case 4:
			i = 1;
			for (Enemy e : getListOfEnemy()) {
				if (i == 1) {
					if (this.currentEnemyAnimation == i && !e.isFadingOut()) {
						e.render(Math.round(p.x + ((gc.getWidth() / 4) * 3) - e.getWidth()),
								Math.round(p.y + (gc.getHeight() - gc.getHeight() / 2)), g);
					} else {
						e.render((gc.getWidth() / 4) * 3 - e.getWidth(), gc.getHeight() - gc.getHeight() / 2, g);
					}
					i++;
				} else if (i == 2) {
					if (this.currentEnemyAnimation == i && !e.isFadingOut()) {
						e.render(Math.round(p.x + (gc.getScreenWidth() - gc.getWidth() / 4 - e.getWidth() + 350)),
								Math.round(p.y + (gc.getHeight() - gc.getHeight() / 2 - 30)), g);
					} else {
						e.render(gc.getScreenWidth() - gc.getWidth() / 4 - e.getWidth() + 350,
								gc.getHeight() - gc.getHeight() / 2 - 30, g);
					}
					i++;
				} else if (i == 3) {
					if (this.currentEnemyAnimation == i && !e.isFadingOut()) {
						e.render(Math.round(p.x + (gc.getScreenWidth() - gc.getWidth() / 4 - e.getWidth() + 200)),
								Math.round(p.y + (gc.getHeight() - gc.getHeight() / 2 - 155)), g);
					} else {
						e.render(gc.getScreenWidth() - gc.getWidth() / 4 - e.getWidth() + 200,
								gc.getHeight() - gc.getHeight() / 2 - 155, g);
					}
					i++;
				} else {
					if (this.currentEnemyAnimation == i && !e.isFadingOut()) {
						e.render(Math.round(p.x + (gc.getScreenWidth() - gc.getWidth() / 4 - e.getWidth() + 175)),
								Math.round(p.y + (gc.getHeight() - gc.getHeight() / 2 + 125)), g);
					} else {
						e.render(gc.getScreenWidth() - gc.getWidth() / 4 - e.getWidth() + 175,
								gc.getHeight() - gc.getHeight() / 2 + 125, g);
					}

				}

			}
			break;

		}
	}

	private void drawCharacters(int numberOfCharacter, GameContainer gc, Graphics g) {
		int i = 0;
		Vector2f p = this.characterAnimation.currentLocation();
		switch (numberOfCharacter) {
		case 1:
			for (Character c : getListOfCharacter()) {
				c.render(Math.round(p.x + (gc.getWidth() / 4)), Math.round(p.y + (gc.getHeight() - gc.getHeight() / 2)),
						g);
			}
			break;
		case 2:
			for (Character c : getListOfCharacter()) {
				if (i == 1) {
					c.render(gc.getWidth() / 4, gc.getHeight() - gc.getHeight() / 2, g);
					i++;
				} else {
					c.render(gc.getScreenWidth() - gc.getWidth() / 4 - c.getWidth() - 350,
							gc.getHeight() - gc.getHeight() / 4 - (c.getHeight() / 2) - 30, g);
				}

			}
			break;
		case 3:
			for (Character c : getListOfCharacter()) {
				if (i == 1) {
					c.render(gc.getWidth() / 4, gc.getHeight() - gc.getHeight() / 2, g);
					i++;
				} else if (i == 2) {
					c.render(gc.getScreenWidth() - gc.getWidth() / 4 - c.getWidth() - 350,
							gc.getHeight() - gc.getHeight() / 4 - (c.getHeight() / 2) - 30, g);
					i++;
				} else {
					c.render(gc.getWidth() / 4, gc.getHeight() - gc.getHeight() / 2, g);
				}

			}
			break;
		case 4:
			for (Character c : getListOfCharacter()) {
				if (i == 1) {
					c.render(gc.getWidth() / 4, gc.getHeight() - gc.getHeight() / 2 - 100, g);
					i++;
				} else if (i == 2) {
					c.render(gc.getScreenWidth() - gc.getWidth() / 4 - c.getWidth() - 350,
							gc.getHeight() - gc.getHeight() / 4 - (c.getHeight() / 2) - 30, g);
					i++;
				} else if (i == 3) {
					c.render(gc.getScreenWidth() - gc.getWidth() / 4 - c.getWidth() - 200,
							gc.getHeight() - gc.getHeight() / 4 - (c.getHeight() / 2) - 155, g);
					i++;
				} else {
					c.render(gc.getScreenWidth() - gc.getWidth() / 4 - c.getWidth() - 175,
							gc.getHeight() - gc.getHeight() / 4 - (c.getHeight() / 2) + 125, g);
				}

			}
			break;

		}
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		this.hud.updateButton(button, x, y, true);
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		this.hud.updateButton(button, x, y, false);
		int i = 0;
		for (Enemy e : this.listOfEnemy) {
			if (e.isHovering(x, y)) {
				this.listOfCharacter.get(0).setAnimation(characterAnimation);
				this.battleController.setCurrentEnemy(this.listOfEnemy.get(i));
				this.battleController.setCurrentCharacter(this.listOfCharacter.get(0));
				if (!this.battleController.isInitDone()) {
					this.battleController.init();
					this.battleController.setInitDone(true);
				}
				if (this.hud.getCurrentButtonPressed() == 0 || this.hud.getCurrentButtonPressed() == 3) {
					this.battleController.controlPressed(BattleCommand.SPELLONE);
				} else if (this.hud.getCurrentButtonPressed() == 2) {
					this.battleController.controlPressed(BattleCommand.SPELLTWO);
				} else {
					this.battleController.controlPressed(BattleCommand.SPELLTHREE);
				}
				break;
			}
			i++;
		}

	}

	private void initializeVariables() {
		if (Game.getInstance() != null && !initiliazeVariable) {
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
			setListOfCharacter(Game.getInstance().getPlayerAccount().getListOfOwnedCharacter());
			setListOfEnemy(this.currentLevel.getListOfEnemy());
			this.listOfEnemy.forEach(f -> {
				f.setAnimation(enemyAnimation);
				f.setSpells(DatabaseCharacterManager.getInstance().getAllCharacterSpells(f.getName()));

			});
			this.battleController = new BattleController(listOfCharacter, listOfEnemy, this);
			this.initiliazeVariable = true;
			calculateTurnOrder();
		}
	}

	private void calculateTurnOrder() {

		this.orderedBattleTurn.addAll(listOfEnemy);
		this.orderedBattleTurn.addAll(listOfCharacter);
		Collections.sort(this.orderedBattleTurn);
		Collections.reverse(this.orderedBattleTurn);
		Collections.reverse(listOfEnemy);
	}

	@Override
	public int getID() {

		return stateId;
	}

}
