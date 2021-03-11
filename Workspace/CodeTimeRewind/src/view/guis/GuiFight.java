package view.guis;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GuiFight extends BasicGameState {

	private int stateId;
	private Image background;
	
	public GuiFight(int stateId) {
		this.stateId = stateId;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.background = new Image("/res/Start_Sunset.png");
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		this.background.draw(0, 0);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

		
	}

	@Override
	public int getID() {

		return stateId;
	}

}
