package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import model.databaseManager.DatabaseAccountManager;

public class Main {
	public static AppGameContainer app;
	public static int BASE_WIDTH = (int) 1920;
	public static int BASE_HEIGHT = (int) 1080;
	public static String gameName = "Code::TimeRewind";
	
	public static void main(String[] args) throws SlickException{
		
		new DatabaseAccountManager().DatabaseCreation();
		app = new AppGameContainer(new Game(gameName));
		app.setTargetFrameRate(120);
		app.setDisplayMode(BASE_WIDTH, BASE_HEIGHT, false);
		app.setShowFPS(false);
		app.start();
		
	}

}