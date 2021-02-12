package main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import controller.GuiLogin;
import controller.GuiMenu;
import controller.GuiSignup;

public class Game extends StateBasedGame {
	public final int menu = 0;
	public final int login = 1;
	public final int signup = 2;
	private static Game instance;

	public Game(String gameName) {
		// Title windows name
		super(gameName);
		this.addState(new GuiMenu(menu));
		this.addState(new GuiLogin(login));
		this.addState(new GuiSignup(signup));
		instance = this;

	}

	public static Game getInstance() {
		return instance;
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {

		this.getState(menu).init(gc, this);
		this.getState(login).init(gc, this);
		this.getState(signup).init(gc, this);

		this.enterState(menu);
	}

	@Override
	public void keyReleased(int key, char c) {
		this.getCurrentState().keyReleased(key, c);
	}

	@Override
	public void keyPressed(int key, char c) {
		this.getCurrentState().keyPressed(key, c);
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		this.getCurrentState().mousePressed(button, x, y);
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		this.getCurrentState().mouseReleased(button, x, y);
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		this.getCurrentState().mouseMoved(oldx, oldy, newx, newy);

	}
}