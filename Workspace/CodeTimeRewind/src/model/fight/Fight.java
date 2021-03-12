package model.fight;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.entity.Character;
import model.entity.Enemy;

@NoArgsConstructor
@Getter
@Setter
public class Fight {
	private List<Character> listOfCharacter;
	private List<Enemy> listOfEnemy;

	public Fight(List<Character> listOfCharacter, List<Enemy> listOfEnemy) {
		this.listOfCharacter = listOfCharacter;
		this.listOfEnemy = listOfEnemy;
	}

	public void drawEntities(GameContainer gc, Graphics g) {
		int numberOfCharacter = getListOfCharacter().size();
		int numberOfEnnemy = getListOfEnemy().size();

		drawCharacters(numberOfCharacter, gc, g);
		drawEnnemies(numberOfEnnemy, gc, g);

	}

	private void drawEnnemies(int numberOfEnnemy, GameContainer gc, Graphics g) {
		int i = 0;
		try {
			switch (numberOfEnnemy) {
			case 1:
				for (Enemy e : getListOfEnemy()) {
					e.render(gc.getScreenWidth() - gc.getWidth() / 4 - e.getWidth(),
							gc.getHeight() - gc.getHeight() / 4 - (e.getHeight() / 2));

					g.drawImage(new Image("/res/zones/LifePoint.png"), e.getX(), e.getY() - 20);
				}
				break;
			case 2:
				i = 1;
				for (Enemy e : getListOfEnemy()) {
					if (i == 1) {
						e.render(gc.getScreenWidth() - gc.getWidth() / 4 - e.getWidth(),
								gc.getHeight() - gc.getHeight() / 4 - (e.getHeight() / 2));
						i++;
					} else if (i == 2) {
						e.render(gc.getScreenWidth() - gc.getWidth() / 4 - e.getWidth() + 350,
								gc.getHeight() - gc.getHeight() / 4 - (e.getHeight() / 2) - 30);
					}
					g.drawImage(new Image("/res/zones/LifePoint.png"), e.getX(), e.getY() - 20);
				}

				break;
			case 3:
				i = 1;
				for (Enemy e : getListOfEnemy()) {
					if (i == 1) {
						e.render(gc.getScreenWidth() - gc.getWidth() / 4 - e.getWidth(),
								gc.getHeight() - gc.getHeight() / 4 - (e.getHeight() / 2));
						i++;
					} else if (i == 2) {
						e.render(gc.getScreenWidth() - gc.getWidth() / 4 - e.getWidth() + 350,
								gc.getHeight() - gc.getHeight() / 4 - (e.getHeight() / 2) - 30);
						i++;
					} else {
						e.render(gc.getScreenWidth() - gc.getWidth() / 4 - e.getWidth() + 200,
								gc.getHeight() - gc.getHeight() / 4 - (e.getHeight() / 2) - 155);
					}
					g.drawImage(new Image("/res/zones/LifePoint.png"), e.getX(), e.getY() - 20);
				}
				break;
			case 4:
				i = 1;
				for (Enemy e : getListOfEnemy()) {
					if (i == 1) {
						e.render(gc.getScreenWidth() - gc.getWidth() / 4 - e.getWidth(),
								gc.getHeight() - gc.getHeight() / 4 - (e.getHeight() / 2));
						i++;
					} else if (i == 2) {
						e.render(gc.getScreenWidth() - gc.getWidth() / 4 - e.getWidth() + 350,
								gc.getHeight() - gc.getHeight() / 4 - (e.getHeight() / 2) - 30);
						i++;
					} else if (i == 3) {
						e.render(gc.getScreenWidth() - gc.getWidth() / 4 - e.getWidth() + 200,
								gc.getHeight() - gc.getHeight() / 4 - (e.getHeight() / 2) - 155);
						i++;
					} else {
						e.render(gc.getScreenWidth() - gc.getWidth() / 4 - e.getWidth() + 175,
								gc.getHeight() - gc.getHeight() / 4 - (e.getHeight() / 2) + 125);
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
		try {
			switch (numberOfCharacter) {
			case 1:
				for (Character c : getListOfCharacter()) {
					c.render(gc.getWidth() / 4 - c.getWidth(),
							gc.getHeight() - gc.getHeight() / 4 - (c.getHeight() / 2));
					g.drawImage(new Image("/res/zones/LifePoint.png"), c.getX(), c.getY() - 20);
				}
				break;
			case 2:
				for (Character c : getListOfCharacter()) {
					if (i == 1) {
						c.render(gc.getScreenWidth() - gc.getWidth() / 4 - c.getWidth(),
								gc.getHeight() - gc.getHeight() / 4 - (c.getHeight() / 2));
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
						c.render(gc.getScreenWidth() - gc.getWidth() / 4 - c.getWidth(),
								gc.getHeight() - gc.getHeight() / 4 - (c.getHeight() / 2));
						i++;
					} else if (i == 2) {
						c.render(gc.getScreenWidth() - gc.getWidth() / 4 - c.getWidth() - 350,
								gc.getHeight() - gc.getHeight() / 4 - (c.getHeight() / 2) - 30);
						i++;
					} else {
						c.render(gc.getScreenWidth() - gc.getWidth() / 4 - c.getWidth() - 200,
								gc.getHeight() - gc.getHeight() / 4 - (c.getHeight() / 2) - 155);
					}
					g.drawImage(new Image("/res/zones/LifePoint.png"), c.getX(), c.getY() - 20);
				}
				break;
			case 4:
				for (Character c : getListOfCharacter()) {
					if (i == 1) {
						c.render(gc.getScreenWidth() - gc.getWidth() / 4 - c.getWidth(),
								gc.getHeight() - gc.getHeight() / 4 - (c.getHeight() / 2));
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

	public void Attack() {

	}

}
