package controller;

import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import lombok.Getter;
import lombok.Setter;
import main.Game;
import model.account.Account;
import model.databaseManager.DatabaseAccountManager;

/**
 * This is the global controller of the game, it contains every action that my views have
 * @author Mathieu Rabot
 *
 */
@Getter
@Setter
public class Controller {
	private DatabaseAccountManager jm = new DatabaseAccountManager(); 
	private Account account = new Account();

	/**
	 * This method change the current view to the new one
	 * @param newView it contains the new view Id
	 */
	public void changeView(int newView) {
		Game.getInstance().enterState(newView, new FadeOutTransition(), new FadeInTransition());
	}
}
