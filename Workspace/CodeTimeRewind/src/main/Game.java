package main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import model.account.Account;
import view.guis.GuiAdventure;
import view.guis.GuiCharacter;
import view.guis.GuiLobby;
import view.guis.GuiLogin;
import view.guis.GuiMenu;
import view.guis.GuiSignup;

public class Game extends StateBasedGame {
	public final int menu = 0;
	public final int login = 1;
	public final int signup = 2;
	public final int lobby = 3;
	public final int character = 4;
	public final int adventure = 5;
	private static Game instance;
	private Account playerAccount;
	private boolean isTheRegisterSucessfull = false;
	

	public Game(String gameName) {
		// Title windows name
		super(gameName);
		instance = this;

	}

	public static Game getInstance() {
		return instance;
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		
		this.addState(new GuiMenu(menu));
		this.addState(new GuiLogin(login));
		this.addState(new GuiSignup(signup));
		this.addState(new GuiLobby(lobby));
		this.addState(new GuiCharacter(character));
		this.addState(new GuiAdventure(adventure));
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

	public boolean getTheRegisterSucessfull() {
		return isTheRegisterSucessfull;
	}

	public void setTheRegisterSucessfull(boolean isTheRegisterSucessfull) {
		this.isTheRegisterSucessfull = isTheRegisterSucessfull;
	}

	public Account getPlayerAccount() {
		return playerAccount;
	}
	public void setPlayerAccount(Account user) {
		this.playerAccount = user;
	}
}