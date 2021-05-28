package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import model.databaseManager.DatabaseAccountManager;

/**
 * This is the main class game, this is the point of entry of the program
 * 
 * @author Mathieu Rabot
 *
 */
public class Main {
	public static AppGameContainer app;
	public static int BASE_WIDTH = (int) 1920;
	public static int BASE_HEIGHT = (int) 1080;
	public static String gameName = "Code::TimeRewind";

	/**
	 * This is the main method game, this is the point of entry of the program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		new DatabaseAccountManager().DatabaseCreation();
		try {
			app = new AppGameContainer(new Game(gameName));
			app.setTargetFrameRate(120);
			app.setDisplayMode(BASE_WIDTH, BASE_HEIGHT, false);
			app.setShowFPS(false);
			app.start();

		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}