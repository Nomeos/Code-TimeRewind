package controller;



import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GuiLogin extends BasicGameState {
	private int x;
	private int width;
	private int height;
	private final String playString = "Play";
	private Shape playButton = null, exitButton;
	private Input input;



	public GuiLogin() {}
	public GuiLogin(int state) {}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.height = 150;
		this.width = 600;

		this.x = ((gc.getWidth()/2)-(width/2));

		this.playButton = new Rectangle(x, 450, width, height);
		this.exitButton = new Rectangle(x,650,width,height);





	}
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		/*g.setColor(new Color(64,207,249));
		g.draw(playButton);
		g.draw(exitButton);

		g.fill(playButton);
		g.fill(exitButton);

		g.setColor(Color.white);

		g.drawString(playString, playButton.getCenterX(),playButton.getCenterY());*/






	}
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// TODO Auto-generated method stub

	}






}
