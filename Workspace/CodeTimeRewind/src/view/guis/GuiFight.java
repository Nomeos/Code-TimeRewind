package view.guis;

import java.util.ArrayList;
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
import model.animation.BezierPath;
import model.animation.PathAnimation;
import model.button.LevelButton;
import model.entity.Character;
import model.entity.Enemy;
import model.entity.Entity;
import model.fight.Fight;
import model.level.Level;

@NoArgsConstructor
@Getter
@Setter
public class GuiFight extends BasicGameState {

	private int stateId;
	private Image background;
	private Image hud;
	private Level currentLevel;
	private Fight fight;
	private boolean initiliazeVariable = false;
	private List<Rectangle> lifeBars;
	private List<LevelButton> listOfButton;
	private List<Character> listOfCharacter;
	private List<Enemy> listOfEnemy;
	private List<List<Object>> listOfLifeBars;
	private List<PathAnimation> animations;
	private PathAnimation animation;

	public GuiFight(int stateId) {
		this.stateId = stateId;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.background = new Image("/res/Start_SunsetM.png");
		this.fight = new Fight();
		this.hud = new Image("/res/zones/HudBar.png");
		
		this.listOfLifeBars = new ArrayList<List<Object>>();
		this.animations = new ArrayList<PathAnimation>();
		this.animation = new PathAnimation(new BezierPath(0, 0, 1700, 50, -50, 20, 0, 0), 2000);
		this.animations.add(new PathAnimation(new BezierPath(0, 0, 1600, 50, -50, 20, 0, 0), 2000));
		this.animations.add(new PathAnimation(new BezierPath(0, 0, 2200, -100, -50, 20, 0, 0), 2000));
		this.animations.add(new PathAnimation(new BezierPath(0, 0, 1900, -300, -50, 20, 0, 0), 2000));
		this.animations.add(new PathAnimation(new BezierPath(0, 0, 1900, 300, -50, 20, 0, 0), 2000));

		this.listOfButton = new ArrayList<LevelButton>();
		for (int i = 0; i <= 2; i++) {
			this.listOfButton.add(new LevelButton(new Image("/res/buttons/SortInLevels.png"),
					new Image("/res/buttons/SortInLevelsHit.png")));
			if (i == 0) {
				this.listOfButton.get(i).setX(gc.getWidth() - 142);
				this.listOfButton.get(i).setY(gc.getHeight() - 154);

			} else if (i == 1) {
				this.listOfButton.get(i).setX(gc.getWidth() - 279);
				this.listOfButton.get(i).setY(gc.getHeight() - 154);

			} else {
				this.listOfButton.get(i).setX(gc.getWidth() - 415);
				this.listOfButton.get(i).setY(gc.getHeight() - 154);

			}

		}
		this.lifeBars = new ArrayList<Rectangle>();

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		this.background.draw(0, 0);
		this.hud.draw(0, gc.getHeight() - 165);
		if (getListOfCharacter() != null && getListOfEnemy() != null) {
			drawEntities(gc, g);

		}

		for (LevelButton currentButton : listOfButton)
			currentButton.draw();

	}

	public void drawEntities(GameContainer gc, Graphics g) {
		int numberOfCharacter = getListOfCharacter().size();
		int numberOfEnnemy = getListOfEnemy().size();
		this.listOfLifeBars.clear();
		int i = 0;
		for (Character c : this.listOfCharacter) {
			this.listOfLifeBars.add(new ArrayList<Object>());
			this.listOfLifeBars.get(i).add(new Rectangle(c.getX() + 2, c.getY() - 18, calculateLifeBarWidth(c), 11));
			i++;
		}
		for (Enemy e : this.listOfEnemy) {
			this.listOfLifeBars.add(new ArrayList<Object>());
			this.listOfLifeBars.get(i).add(new Rectangle(e.getX() + 2, e.getY() - 18, calculateLifeBarWidth(e), 11));
			i++;
		}
		g.setColor(Color.red);
		for (List<Object> o : this.listOfLifeBars) {
			g.draw((Shape) o.get(0));
			g.fill((Shape) o.get(0));
		}
		drawCharacters(numberOfCharacter, gc, g);
		drawEnnemies(numberOfEnnemy, gc, g);

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
			this.fight = new Fight(getListOfCharacter(), getListOfEnemy());
			this.initiliazeVariable = true;
		}
		for (PathAnimation animation : this.animations)
			animation.update(delta);
		

	}


	@Override
	public void mousePressed(int button, int x, int y) {
		for (LevelButton button1 : this.listOfButton) {
			if (button1.isHovering(x, y) && button == 0) {
				button1.setPressed(true);
			}
		}
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		for (LevelButton button1 : this.listOfButton) {
			if (!button1.isHovering(x, y) && button == 0) {
				button1.setPressed(false);
			}
		}
		if (this.fight.isAttacking(x, y)) {
			int i = this.fight.whoAmIAttacking(x, y);
			int j = 0;
			for (PathAnimation animation : this.animations) {
				if(j == i) {
					this.animation = animation;
					animation.start();
				}
				j++;
			}
			if(!this.fight.Attack(x, y)) {
			
			}
		}
	}

	@Override
	public int getID() {

		return stateId;
	}

}
