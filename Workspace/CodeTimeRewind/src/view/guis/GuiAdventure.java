package view.guis;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GuiAdventure extends BasicGameState {
	private int stateId;
	private Image background;
	private Circle circle1;
	
	public GuiAdventure (int state) {
		this.stateId = state;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		this.background = new Image("/res/Half_Day.png");
		this.circle1 = new Circle(100, 100, 50);
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {
		this.background.draw(0,0);
		g.setColor(Color.black);
		g.draw(circle1);
		g.fill(circle1);
		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {

		
	}

	@Override
	public int getID() {
		return stateId;
	}

}
