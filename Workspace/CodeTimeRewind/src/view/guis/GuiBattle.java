package view.guis;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;

import lombok.Getter;
import lombok.Setter;
import model.button.LevelButton;

@Getter
@Setter
public class GuiBattle implements ComponentListener {

	private Image background;
	private Image hud;
	private int currentButtonPressed;

	private List<LevelButton> listOfButton;
	
	public void init(GameContainer gc) {
		try {
			this.background = new Image("/res/Start_SunsetM.png");
			this.hud = new Image("/res/zones/HudBar.png");
			this.listOfButton = new ArrayList<LevelButton>();
			createButtons(gc);

		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void render(GameContainer gc, Graphics g) {
		this.background.draw(0, 0);
		this.hud.draw(0, gc.getHeight() - 165);
		for (LevelButton currentButton : listOfButton)
			currentButton.draw();
	}

	public void updateButton(int button, int x, int y, boolean isPressed) {
		int i = 0;
		if (isPressed) {
			for (LevelButton button1 : this.listOfButton) {
				if (button1.isHovering(x, y) && button == 0) {
					button1.setPressed(true);
					this.currentButtonPressed = i;

				}
				i++;
			}
		} else {
			for (LevelButton button1 : this.listOfButton) {
				if (!button1.isHovering(x, y) && button == 0) {
					button1.setPressed(false);
				}
			}
		}

	}

	private void createButtons(GameContainer gc) {

		for (int i = 0; i <= 2; i++) {
			try {
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
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	public void componentActivated(AbstractComponent ac) {
		System.out.println("test");

	}
}
