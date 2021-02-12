package controller;

import java.awt.Font;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GuiSignup extends BasicGameState {
	public GuiSignup() {
	}

	private int x,y;
	private int width;
	private int height;
	private Input input;
	private TextField username, password, passwordConfirmation;
	private Font ft = null;

	public GuiSignup(int state) {
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.height = 150;
		this.width = 600;

		this.x = ((gc.getWidth() / 2) - (width / 2));
		this.y = 650;
		this.ft = new Font("Century Gothic", Font.BOLD, 20);

		this.passwordConfirmation = new TextField(gc, new TrueTypeFont(ft, true), x, y-200, width, height);

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		passwordConfirmation.setBorderColor(Color.black);
		passwordConfirmation.setBackgroundColor(Color.white);
		passwordConfirmation.setTextColor(Color.black);
		passwordConfirmation.setFocus(true);
		passwordConfirmation.setMaxLength(20);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// TODO Auto-generated method stub
		System.out.println("ok");

	}
	@Override
	public void keyPressed(int key, char c) {
		System.out.println(key+" "+c);
	}

}
