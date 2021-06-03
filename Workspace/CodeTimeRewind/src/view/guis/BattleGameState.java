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
import org.newdawn.slick.state.StateBasedGame;

import controller.BattleController;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Game;
import model.animation.BezierPath;
import model.animation.PathAnimation;
import model.enemyPerStage.EnemyPerStage;
import model.fight.BattleCommand;
import model.livingEntity.LivingEntity;
import model.livingEntity.enemy.Enemy;
import model.stageByAccount.StageByAccount;

@NoArgsConstructor
@Getter
@Setter
public class BattleGameState extends Gui {

	private boolean initiliazeVariable = false;
	private GuiBattle hud;
	private BattleController battleController;
	private PathAnimation characterAnimation;
	private PathAnimation enemyAnimation;

	private List<Rectangle> lifeBars;

	private List<LivingEntity> orderedBattleTurn;
	private List<Integer> missingDeadEnnemies;

	public int currentTurn;
	public int currentEnemyAnimation = 0;

	public BattleGameState(int stateId) {
		super(stateId);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

		this.hud = new GuiBattle();
		this.characterAnimation = new PathAnimation(new BezierPath(0, 0, 400, 1, -50, 20, 0, 0), 2000);
		this.enemyAnimation = new PathAnimation(new BezierPath(0, 0, -400, -1, -50, 20, 0, 0), 2000);
		this.hud.init(gc);
		this.orderedBattleTurn = new ArrayList<LivingEntity>();
		this.lifeBars = new ArrayList<Rectangle>();
		this.missingDeadEnnemies = new ArrayList<Integer>();
		this.currentTurn = 0;
		this.battleController = new BattleController(this);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		initializeVariables();

		this.characterAnimation.update(delta);
		this.enemyAnimation.update(delta);
		int i = 1;
		for (LivingEntity e : this.battleController.getListOfCurrentEnemies()) {
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

		if (this.orderedBattleTurn.get(currentTurn).isAnEnemy()) {
			if (this.orderedBattleTurn.get(currentTurn).getHealth() <= 0) {
				this.orderedBattleTurn.remove(currentTurn);
				this.currentEnemyAnimation++;
			} else {
				setCurrentEnemyAnimation();
				this.battleController.setCurrentEnemy((Enemy) this.orderedBattleTurn.get(currentTurn));
				this.battleController.setCurrentCharacter(this.battleController.getListOfCharacter().get(0));
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
		if (this.battleController.getListOfCurrentEnemies() != null
				&& this.battleController.getListOfCharacter() != null) {
			this.lifeBars.clear();
			drawEntities(gc, g);
			g.setColor(Color.red);
			for (Rectangle o : this.lifeBars) {
				g.draw(o);
				g.fill(o);
			}
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
		int numberOfCharacter = this.battleController.getListOfCharacter().size();
		int numberOfEnnemy = this.battleController.getListOfCurrentEnemies().size();
		drawCharacters(numberOfCharacter, gc, g);
		drawEnnemies(numberOfEnnemy, gc, g);

	}

	private void createLifeBar(LivingEntity le, Graphics g) {

		if (this.lifeBars.size() == 8) {
			this.lifeBars.clear();
		} else {
			this.lifeBars.add(new Rectangle(le.getX() + 2, le.getY() - 18, calculateLifeBarWidth(le), 11));
		}
	}

	private int calculateLifeBarWidth(LivingEntity c) {
		float result = c.getHealth() / (float) c.getDefaultHealth();
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

			for (LivingEntity e : this.battleController.getListOfCurrentEnemies()) {
				e.render(Math.round(p.x + ((gc.getWidth() / 4) * 3)),
						Math.round(p.y + (gc.getHeight() - gc.getHeight() / 2)), g);
				createLifeBar(e, g);
			}

			break;
		case 2:
			i = 1;
			for (LivingEntity e : this.battleController.getListOfCurrentEnemies()) {
				if (i == 1) {
					if (this.currentEnemyAnimation == i && !e.isFadingOut()) {
						e.render(Math.round(p.x + ((gc.getWidth() / 4) * 3)),
								Math.round(p.y + (gc.getHeight() - gc.getHeight() / 2)), g);
						createLifeBar(e, g);
					} else {
						e.render((gc.getWidth() / 4) * 3, gc.getHeight() - gc.getHeight() / 2, g);
						createLifeBar(e, g);
					}

					i++;
				} else if (i == 2) {
					if (this.currentEnemyAnimation == i && !e.isFadingOut()) {
						e.render(Math.round(p.x + (gc.getScreenWidth() - gc.getWidth() / 4 - +350)),
								Math.round(p.y + (gc.getHeight() - gc.getHeight() / 2 - 30)), g);
						createLifeBar(e, g);
					} else {
						e.render(gc.getScreenWidth() - gc.getWidth() / 4 - +350,
								gc.getHeight() - gc.getHeight() / 2 - 30, g);
						createLifeBar(e, g);
					}

				}

			}

			break;
		case 3:
			i = 1;
			for (LivingEntity e : this.battleController.getListOfCurrentEnemies()) {
				if (i == 1) {
					if (this.currentEnemyAnimation == i && !e.isFadingOut()) {
						e.render(Math.round(p.x + ((gc.getWidth() / 4) * 3)),
								Math.round(p.y + (gc.getHeight() - gc.getHeight() / 2)), g);
						createLifeBar(e, g);
					} else {
						e.render((gc.getWidth() / 4) * 3, gc.getHeight() - gc.getHeight() / 2, g);
						createLifeBar(e, g);
					}
					i++;
				} else if (i == 2) {
					if (this.currentEnemyAnimation == i && !e.isFadingOut()) {
						e.render(Math.round(p.x + (gc.getScreenWidth() - gc.getWidth() / 4 - +350)),
								Math.round(p.y + (gc.getHeight() - gc.getHeight() / 2 - 30)), g);
						createLifeBar(e, g);
					} else {
						e.render(gc.getScreenWidth() - gc.getWidth() / 4 - +350,
								gc.getHeight() - gc.getHeight() / 2 - 30, g);
						createLifeBar(e, g);
					}
					i++;
				} else {
					if (this.currentEnemyAnimation == i && !e.isFadingOut()) {
						e.render(Math.round(p.x + (gc.getScreenWidth() - gc.getWidth() / 4 - +200)),
								Math.round(p.y + (gc.getHeight() - gc.getHeight() / 2 - 155)), g);
						createLifeBar(e, g);
					} else {
						e.render(gc.getScreenWidth() - gc.getWidth() / 4 - +200,
								gc.getHeight() - gc.getHeight() / 2 - 155, g);
						createLifeBar(e, g);
					}

				}

			}
			break;
		case 4:
			i = 1;
			for (LivingEntity e : this.battleController.getListOfCurrentEnemies()) {
				if (i == 1) {
					if (this.currentEnemyAnimation == i && !e.isFadingOut()) {
						e.render(Math.round(p.x + ((gc.getWidth() / 4) * 3)) - 150,
								Math.round(p.y + (gc.getHeight() - gc.getHeight() / 2)), g);
						createLifeBar(e, g);

					} else {
						e.render((gc.getWidth() / 4 * 3) - -150, gc.getHeight() - gc.getHeight() / 2, g);
						createLifeBar(e, g);
					}
					i++;
				} else if (i == 2) {
					if (this.currentEnemyAnimation == i && !e.isFadingOut()) {
						e.render(Math.round(p.x + (gc.getWidth() / 4 + 350)) - 150,
								Math.round(p.y + (gc.getHeight() - gc.getHeight() / 2 - 30)), g);
						createLifeBar(e, g);
					} else {
						e.render((gc.getWidth() / 4 * 3) + 350 - 150, gc.getHeight() - gc.getHeight() / 2 - 30, g);
						createLifeBar(e, g);
					}
					i++;
				} else if (i == 3) {
					if (this.currentEnemyAnimation == i && !e.isFadingOut()) {
						e.render(Math.round(p.x + (gc.getWidth() / 4 + 200)) - 150,
								Math.round(p.y + (gc.getHeight() - gc.getHeight() / 2 - 155)), g);
						createLifeBar(e, g);
					} else {
						e.render((gc.getWidth() / 4 * 3) + 200 - 150, gc.getHeight() - gc.getHeight() / 2 - 155, g);
						createLifeBar(e, g);
					}
					i++;
				} else {
					if (this.currentEnemyAnimation == i && !e.isFadingOut()) {
						e.render(Math.round(p.x + (gc.getWidth() / 4 * 3 + 175)) - 150,
								Math.round(p.y + (gc.getHeight() - gc.getHeight() / 2 + 125)), g);
						createLifeBar(e, g);
					} else {
						e.render((gc.getWidth() / 4 * 3) + 175 - 150, gc.getHeight() - gc.getHeight() / 2 + 125, g);
						createLifeBar(e, g);
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
			i = 1;
			for (LivingEntity c : this.battleController.getListOfCharacter()) {
				if (i == 1) {
					if (this.currentEnemyAnimation == i && !c.isFadingOut()) {
						c.render(Math.round(p.x + (gc.getWidth() / 4)),
								Math.round(p.y + (gc.getHeight() - gc.getHeight() / 2)), g);
						createLifeBar(c, g);
					} else {
						c.render(gc.getWidth() / 4, gc.getHeight() - gc.getHeight() / 2, g);
						createLifeBar(c, g);
					}
				}
			}
			break;
		case 2:
			i = 1;
			for (LivingEntity c : this.battleController.getListOfCharacter()) {
				if (i == 1) {
					if (this.currentEnemyAnimation == i && !c.isFadingOut()) {
						c.render(Math.round(p.x + (gc.getWidth() / 4)),
								Math.round(p.y + (gc.getHeight() - gc.getHeight() / 2)), g);
						createLifeBar(c, g);
					} else {
						c.render(gc.getWidth() / 4, gc.getHeight() - gc.getHeight() / 2, g);
						createLifeBar(c, g);
					}

					i++;
				} else if (i == 2) {
					if (this.currentEnemyAnimation == i && !c.isFadingOut()) {
						c.render(Math.round(p.x + (gc.getScreenWidth() - gc.getWidth() / 4 - 350)),
								Math.round(p.y + (gc.getHeight() - gc.getHeight() / 2 - 30)), g);
						createLifeBar(c, g);
					} else {
						c.render(gc.getScreenWidth() - gc.getWidth() / 4 + 350,
								gc.getHeight() - gc.getHeight() / 2 - 30, g);
						createLifeBar(c, g);
					}
				}

			}
			break;
		case 3:
			i = 1;
			for (LivingEntity c : this.battleController.getListOfCharacter()) {
				if (i == 1) {
					if (this.currentEnemyAnimation == i && !c.isFadingOut()) {
						c.render(Math.round(p.x + (gc.getWidth() / 4)),
								Math.round(p.y + (gc.getHeight() - gc.getHeight() / 2)), g);
						createLifeBar(c, g);
					} else {
						c.render(gc.getWidth() / 4, gc.getHeight() - gc.getHeight() / 2, g);
						createLifeBar(c, g);
					}

					i++;
				} else if (i == 2) {
					if (this.currentEnemyAnimation == i && !c.isFadingOut()) {
						c.render(Math.round(p.x + (gc.getScreenWidth() - gc.getWidth() / 4 - 350)),
								Math.round(p.y + (gc.getHeight() - gc.getHeight() / 2 - 30)), g);
						createLifeBar(c, g);
					} else {
						c.render(gc.getWidth() / 4 - 350, gc.getHeight() - gc.getHeight() / 2 - 30, g);
						createLifeBar(c, g);
					}
					i++;
				} else if (i == 3) {
					if (this.currentEnemyAnimation == i && !c.isFadingOut()) {
						c.render(Math.round(p.x + (gc.getScreenWidth() - gc.getWidth() / 4 - 200)),
								Math.round(p.y + (gc.getHeight() - gc.getHeight() / 2 - 155)), g);
						createLifeBar(c, g);
					} else {
						c.render(gc.getWidth() / 4 - 200, gc.getHeight() - gc.getHeight() / 2 - 155, g);
						createLifeBar(c, g);
					}

				}

			}
			break;
		case 4:
			i = 1;
			for (LivingEntity c : this.battleController.getListOfCharacter()) {

				if (i == 1) {
					if (this.currentEnemyAnimation == i && !c.isFadingOut()) {
						c.render(Math.round(p.x + (gc.getWidth() / 4)),
								Math.round(p.y + (gc.getHeight() - gc.getHeight() / 2)), g);
						createLifeBar(c, g);
					} else {
						c.render(gc.getWidth() / 4, gc.getHeight() - gc.getHeight() / 2, g);
						createLifeBar(c, g);
					}

					i++;
				} else if (i == 2) {
					if (this.currentEnemyAnimation == i && !c.isFadingOut()) {
						c.render(Math.round(p.x + (gc.getScreenWidth() - gc.getWidth() / 4 - 350)),
								Math.round(p.y + (gc.getHeight() - gc.getHeight() / 2 - 30)), g);
						createLifeBar(c, g);
					} else {
						c.render(gc.getWidth() / 4 - 350, gc.getHeight() - gc.getHeight() / 2 - 30, g);
						createLifeBar(c, g);
					}
					i++;
				} else if (i == 3) {
					if (this.currentEnemyAnimation == i && !c.isFadingOut()) {
						c.render(Math.round(p.x + (gc.getScreenWidth() - gc.getWidth() / 4 - 200)),
								Math.round(p.y + (gc.getHeight() - gc.getHeight() / 2 - 155)), g);
						createLifeBar(c, g);
					} else {
						c.render(gc.getWidth() / 4 - 200, gc.getHeight() - gc.getHeight() / 2 - 155, g);
						createLifeBar(c, g);
					}
					i++;
				} else if (i == 4) {
					if (this.currentEnemyAnimation == i && !c.isFadingOut()) {
						c.render(Math.round(p.x + (gc.getScreenWidth() - gc.getWidth() / 4 - 175)),
								Math.round(p.y + (gc.getHeight() - gc.getHeight() / 2 + 125)), g);
						createLifeBar(c, g);
					} else {
						c.render(gc.getWidth() / 4 - 175, gc.getHeight() - gc.getHeight() / 2 + 125, g);
						createLifeBar(c, g);

					}
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
		for (LivingEntity e : this.battleController.getListOfCurrentEnemies()) {
			if (e.isHovering(x, y)) {
				this.battleController.setCurrentEnemy(this.battleController.getListOfCurrentEnemies().get(i));
				this.battleController.setCurrentCharacter(this.battleController.getListOfCharacter().get(0));
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
			this.battleController.getAllEntitiesForThisStage();
			this.initiliazeVariable = true;
			calculateTurnOrder();
		}
	}

	private void calculateTurnOrder() {

		this.orderedBattleTurn.addAll(this.battleController.getListOfCurrentEnemies());
		this.orderedBattleTurn.addAll(this.battleController.getListOfCharacter());
		Collections.sort(this.orderedBattleTurn);
		Collections.reverse(this.orderedBattleTurn);
		Collections.reverse(this.battleController.getListOfCurrentEnemies());
	}

}
