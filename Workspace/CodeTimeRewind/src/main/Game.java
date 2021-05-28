package main;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import lombok.Getter;
import lombok.Setter;
import model.account.Account;
import model.accountOwnCharacter.AccountOwnCharacter;
import model.stage.Stage;
import view.guis.BattleGameState;
import view.guis.GuiAdventure;
import view.guis.GuiChapters;
import view.guis.GuiCharacter;
import view.guis.GuiEndFight;
import view.guis.GuiLobby;
import view.guis.GuiLogin;
import view.guis.GuiMenu;
import view.guis.GuiSignup;
import view.guis.GuiSummon;

/**
 * This is the principal class that my game use, it manages all the views and
 * makes the transition between them, it also contains every global variable
 * that my game user in different views
 * 
 * @author Mathieu Rabot
 *
 */
@Getter
@Setter
public class Game extends StateBasedGame {
	public final int menu = 0;
	public final int login = 1;
	public final int signup = 2;
	public final int lobby = 3;
	public final int character = 4;
	public final int chapters = 5;
	public final int adventure = 6;
	public final int fight = 7;
	public final int endFight = 8;
	public final int summon = 9;
	private int currentChapter;
	private int currentLevel;
	private static Game instance;
	private Account playerAccount;
	private List<List<Stage>> ListOfChapters;
	private boolean isTheRegisterSucessfull = false;
	private List<AccountOwnCharacter> CurrentCharacterInFight = new ArrayList<AccountOwnCharacter>();

	/**
	 * This is the constructor for this class
	 * 
	 * @param gameName This is the name of the game
	 */
	public Game(String gameName) {
		// Title windows name
		super(gameName);
		instance = this;

	}

	/**
	 * This is the instance for this class, you can access it from every views for
	 * interact with the variables
	 * 
	 * @return It returns this current object
	 */
	public static Game getInstance() {
		return instance;
	}

	/**
	 * This method initialize all the views and add them to a list
	 */
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {

		this.addState(new GuiMenu(menu));
		this.addState(new GuiLogin(login));
		this.addState(new GuiSignup(signup));
		this.addState(new GuiLobby(lobby));
		this.addState(new GuiCharacter(character));
		this.addState(new GuiChapters(chapters));
		this.addState(new GuiAdventure(adventure));
		this.addState(new BattleGameState(fight));
		this.addState(new GuiEndFight(endFight));
		this.addState(new GuiSummon(summon));
	}

	/**
	 * This method is call when a key is release
	 */
	@Override
	public void keyReleased(int key, char c) {
		this.getCurrentState().keyReleased(key, c);

	}

	/**
	 * This method is call when a key is press
	 */
	@Override
	public void keyPressed(int key, char c) {
		this.getCurrentState().keyPressed(key, c);
	}

	/**
	 * This method is call when a mouse button is press
	 */
	@Override
	public void mousePressed(int button, int x, int y) {
		this.getCurrentState().mousePressed(button, x, y);
	}

	/**
	 * This method is call when a mouse button is release
	 */
	@Override
	public void mouseReleased(int button, int x, int y) {
		this.getCurrentState().mouseReleased(button, x, y);
	}

	/**
	 * This method is call when the mouse is moving
	 */
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		this.getCurrentState().mouseMoved(oldx, oldy, newx, newy);

	}

}