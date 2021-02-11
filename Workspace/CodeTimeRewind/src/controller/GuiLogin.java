package controller;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GuiLogin extends BasicGameState {
	private int x,y;
	private int width;
	private int height;
	private final String playString = "Play";
	private Shape playButton = null, exitButton;
	private Input input;
	private TextField username;
	private Font ft = null;

	public GuiLogin() {
	}

	public GuiLogin(int state) {
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.height = 30;
		this.width = 600;
		this.x = ((gc.getWidth() / 2) - (width / 2));
		this.y = 650;
		this.ft = new Font("Century Gothic", Font.BOLD, 20);
		this.username = new TextField(gc, new TrueTypeFont(ft, true), x, y, width, height);
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		username.setBorderColor(Color.black);
		username.setBackgroundColor(Color.white);
		username.setTextColor(Color.black);
		
		username.setFocus(isAcceptingInput());
		
		username.setMaxLength(20);
		username.render(gc, g);
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
