package view.guis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.Game;
import model.animation.AnimationListener;
import model.animation.BezierPath;
import model.animation.PathAnimation;
import model.entity.Character;
import model.entity.Enemy;
import model.entity.Entity;
import model.fight.BattleCommand;
import model.fight.BattleController;

import model.level.Level;

@NoArgsConstructor
@Getter
@Setter
public class BattleGameState extends BasicGameState {

	private int stateId;

	private Level currentLevel;
	private boolean initiliazeVariable = false;
	private GuiBattle hud;
	private BattleController battleController;
	private List<List<Object>> lifeBars;
	private List<Character> listOfCharacter;
	private List<Enemy> listOfEnemy;
	private List<Entity> orderedBattleTurn;
	private int currentTurn;

	private List<PathAnimation> animations;
	private PathAnimation animation;

	private AnimationListener endPlayerAttack;


	public BattleGameState(int stateId) {
		this.stateId = stateId;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

		this.hud = new GuiBattle();
		this.hud.init(gc);
		this.orderedBattleTurn = new ArrayList<Entity>();
		this.lifeBars = new ArrayList<List<Object>>();
		this.currentTurn = 0;

		this.animations = new ArrayList<PathAnimation>();
		this.animation = new PathAnimation(new BezierPath(0, 0, 1700, 50, -50, 20, 0, 0), 2000);
		this.animations.add(new PathAnimation(new BezierPath(0, 0, 1600, 50, -50, 20, 0, 0), 2000));
		this.animations.add(new PathAnimation(new BezierPath(0, 0, 2200, -100, -50, 20, 0, 0), 2000));
		this.animations.add(new PathAnimation(new BezierPath(0, 0, 1900, -300, -50, 20, 0, 0), 2000));
		this.animations.add(new PathAnimation(new BezierPath(0, 0, 1900, 300, -50, 20, 0, 0), 2000));
		initAnimationListener();
		this.animations.forEach(a ->{a.addListener(2000, endPlayerAttack);});

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		this.hud.render(gc, g);
		if (getListOfCharacter() != null && getListOfEnemy() != null) {
			drawEntities(gc, g);
			createLifeBars(gc, g);
		}

	}
	private void initAnimationListener() {
		AnimationListener endPlayerAttack = new AnimationListener() {
			
			@Override
			public void on() {
				endPlayerAttack();
				
			}
		};
		this.endPlayerAttack = endPlayerAttack;
	}
	
	private void endPlayerAttack() {
		
	}
	
	public void drawEntities(GameContainer gc, Graphics g) {
		int numberOfCharacter = getListOfCharacter().size();
		int numberOfEnnemy = getListOfEnemy().size();
		drawCharacters(numberOfCharacter, gc, g);
		drawEnnemies(numberOfEnnemy, gc, g);

	}

	private void createLifeBars(GameContainer gc, Graphics g) {
		this.lifeBars.clear();
		int i = 0;
		for (Character c : this.listOfCharacter) {
			this.lifeBars.add(new ArrayList<Object>());
			this.lifeBars.get(i).add(new Rectangle(c.getX() + 2, c.getY() - 18, calculateLifeBarWidth(c), 11));
			i++;
		}
		for (Enemy e : this.listOfEnemy) {
			this.lifeBars.add(new ArrayList<Object>());
			this.lifeBars.get(i).add(new Rectangle(e.getX() + 2, e.getY() - 18, calculateLifeBarWidth(e), 11));
			i++;
		}
		g.setColor(Color.red);
		for (List<Object> o : this.lifeBars) {
			g.draw((Shape) o.get(0));
			g.fill((Shape) o.get(0));
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
		try {
			switch (numberOfEnnemy) {
			case 1:
				for (Enemy e : getListOfEnemy()) {
					e.render((gc.getWidth() / 4) * 3, gc.getHeight() - gc.getHeight() / 2);

					g.drawImage(new Image("/res/zones/LifePoint.png"), e.getX(), e.getY() - 20);
				}

				break;
			case 2:
				i = 1;
				for (Enemy e : getListOfEnemy()) {
					if (i == 1) {
						e.render(gc.getScreenWidth() - gc.getWidth() / 4 - e.getWidth(),
								gc.getHeight() - gc.getHeight() / 2);
						i++;
					} else if (i == 2) {
						e.render(gc.getScreenWidth() - gc.getWidth() / 4 - e.getWidth() + 350,
								gc.getHeight() - gc.getHeight() / 2 - 30);
					}
					g.drawImage(new Image("/res/zones/LifePoint.png"), e.getX(), e.getY() - 20);
				}

				break;
			case 3:
				i = 1;
				for (Enemy e : getListOfEnemy()) {
					if (i == 1) {
						e.render(gc.getScreenWidth() - gc.getWidth() / 4 - e.getWidth(),
								gc.getHeight() - gc.getHeight() / 2);
						i++;
					} else if (i == 2) {
						e.render(gc.getScreenWidth() - gc.getWidth() / 4 - e.getWidth() + 350,
								gc.getHeight() - gc.getHeight() / 2 - 30);
						i++;
					} else {
						e.render(gc.getScreenWidth() - gc.getWidth() / 4 - e.getWidth() + 200,
								gc.getHeight() - gc.getHeight() / 2 - 155);
					}
					g.drawImage(new Image("/res/zones/LifePoint.png"), e.getX(), e.getY() - 20);
				}
				break;
			case 4:
				i = 1;
				for (Enemy e : getListOfEnemy()) {
					if (i == 1) {
						e.render(gc.getScreenWidth() - gc.getWidth() / 4 - e.getWidth(),
								gc.getHeight() - gc.getHeight() / 2);
						i++;
					} else if (i == 2) {
						e.render(gc.getScreenWidth() - gc.getWidth() / 4 - e.getWidth() + 350,
								gc.getHeight() - gc.getHeight() / 2 - 30);
						i++;
					} else if (i == 3) {
						e.render(gc.getScreenWidth() - gc.getWidth() / 4 - e.getWidth() + 200,
								gc.getHeight() - gc.getHeight() / 2 - 155);
						i++;
					} else {
						e.render(gc.getScreenWidth() - gc.getWidth() / 4 - e.getWidth() + 175,
								gc.getHeight() - gc.getHeight() / 2 + 125);
					}
					g.drawImage(new Image("/res/zones/LifePoint.png"), e.getX(), e.getY() - 20);
				}
				break;

			}
		} catch (SlickException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void drawCharacters(int numberOfCharacter, GameContainer gc, Graphics g) {
		int i = 0;
		Vector2f p = this.animation.currentLocation();
		try {
			switch (numberOfCharacter) {
			case 1:
				for (Character c : getListOfCharacter()) {
					c.render(Math.round(p.x + (gc.getWidth() / 4)),
							Math.round(p.y + (gc.getHeight() - gc.getHeight() / 2)));
					g.drawImage(new Image("/res/zones/LifePoint.png"), c.getX(), c.getY() - 20);

				}
				break;
			case 2:
				for (Character c : getListOfCharacter()) {
					if (i == 1) {
						c.render(gc.getWidth() / 4, gc.getHeight() - gc.getHeight() / 2);
						i++;
					} else {
						c.render(gc.getScreenWidth() - gc.getWidth() / 4 - c.getWidth() - 350,
								gc.getHeight() - gc.getHeight() / 4 - (c.getHeight() / 2) - 30);
					}
					g.drawImage(new Image("/res/zones/LifePoint.png"), c.getX(), c.getY() - 20);
				}
				break;
			case 3:
				for (Character c : getListOfCharacter()) {
					if (i == 1) {
						c.render(gc.getWidth() / 4, gc.getHeight() - gc.getHeight() / 2);
						i++;
					} else if (i == 2) {
						c.render(gc.getScreenWidth() - gc.getWidth() / 4 - c.getWidth() - 350,
								gc.getHeight() - gc.getHeight() / 4 - (c.getHeight() / 2) - 30);
						i++;
					} else {
						c.render(gc.getWidth() / 4, gc.getHeight() - gc.getHeight() / 2);
					}
					g.drawImage(new Image("/res/zones/LifePoint.png"), c.getX(), c.getY() - 20);
				}
				break;
			case 4:
				for (Character c : getListOfCharacter()) {
					if (i == 1) {
						c.render(gc.getWidth() / 4, gc.getHeight() - gc.getHeight() / 2 - 100);
						i++;
					} else if (i == 2) {
						c.render(gc.getScreenWidth() - gc.getWidth() / 4 - c.getWidth() - 350,
								gc.getHeight() - gc.getHeight() / 4 - (c.getHeight() / 2) - 30);
						i++;
					} else if (i == 3) {
						c.render(gc.getScreenWidth() - gc.getWidth() / 4 - c.getWidth() - 200,
								gc.getHeight() - gc.getHeight() / 4 - (c.getHeight() / 2) - 155);
						i++;
					} else {
						c.render(gc.getScreenWidth() - gc.getWidth() / 4 - c.getWidth() - 175,
								gc.getHeight() - gc.getHeight() / 4 - (c.getHeight() / 2) + 125);
					}
					g.drawImage(new Image("/res/zones/LifePoint.png"), c.getX(), c.getY() - 20);
				}
				break;

			}
		} catch (SlickException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		initializeVariables();
		for (PathAnimation animation : this.animations)
			animation.update(delta);
		Enemy e = new Enemy();
		if (this.orderedBattleTurn.get(currentTurn).getClass() == e.getClass()) {
			this.battleController.setCurrentEnemy((Enemy) this.orderedBattleTurn.get(currentTurn));
			this.battleController.setCurrentCharacter(this.listOfCharacter.get(0));
			this.battleController.setEnemiesTurn(true);
			this.battleController.controlPressed(BattleCommand.SPELLONE);
			System.out.println("Enemies's turn !");
		} else {
			this.battleController.setEnemiesTurn(false);
			System.out.println("My Turn !");
		}
		if (this.currentTurn == this.orderedBattleTurn.size()) {
			this.currentTurn = 0;
		}

	}

	@Override
	public void mousePressed(int button, int x, int y) {
		this.hud.updateButton(button, x, y, true);
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		this.hud.updateButton(button, x, y, false);
		int j = 0;
		int i = 0;
		for (Enemy e : this.listOfEnemy) {
			if(e.isHovering(x, y)) {
				for (PathAnimation animation : this.animations) {
					if (j == i) {
						this.animation = animation;
						animation.start();
						if(this.hud.getCurrentButtonPressed() == 0) {
							this.battleController.controlPressed(BattleCommand.SPELLTHREE);
						} else if(this.hud.getCurrentButtonPressed() == 1) {
							this.battleController.controlPressed(BattleCommand.SPELLTWO);
						} else {
							this.battleController.controlPressed(BattleCommand.SPELLONE);
						}
						
						break;
					}
					j++;

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
			this.battleController = new BattleController(listOfCharacter, listOfEnemy);
			this.initiliazeVariable = true;
			calculateTurnOrder();
		}
	}

	private void calculateTurnOrder() {
		for (Enemy e : this.listOfEnemy) {
			this.orderedBattleTurn.add(e);
		}
		for (Character c : this.listOfCharacter) {
			this.orderedBattleTurn.add(c);
		}
		Collections.sort(this.orderedBattleTurn);
		Collections.reverse(this.orderedBattleTurn);
	}

	@Override
	public int getID() {

		return stateId;
	}

}
