package main;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import model.account.Account;
import model.level.Level;
import view.guis.*;


public class Game extends StateBasedGame {
	public final int menu = 0;
	public final int login = 1;
	public final int signup = 2;
	public final int lobby = 3;
	public final int character = 4;
	public final int chapters = 5;
	public final int adventure = 6;
	public final int fight = 7;
	private int currentChapter;
	private int currentLevel;
	private static Game instance;
	private Account playerAccount;
	private List<List<Level>> ListOfChapters;
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
		this.addState(new GuiChapters(chapters));
		this.addState(new GuiAdventure(adventure));
		this.addState(new GuiFight(fight));
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
	public List<List<Level>> getListOfChapters() {
		return ListOfChapters;
	}

	public void setListOfChapters(List<List<Level>> listOfChapters) {
		ListOfChapters = listOfChapters;
	}

	public int getCurrentChapter() {
		return currentChapter;
	}

	public void setCurrentChapter(int currentChapter) {
		this.currentChapter = currentChapter;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}
}