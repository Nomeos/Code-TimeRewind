package controller;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import main.Game;
import model.Account;

public class GuiLobby extends BasicGameState {
	private int stateID,level;
	private Shape charactersButton = null, inventoryButton = null, adventureButton = null, leftTopTriangle = null;
	private Account playerAccount;
	private Font ftTitre, ftUserName;
	private TrueTypeFont trueTypeFontTitre, trueTypeFontUserName;
	private String username;
	private float[] trianglePoint;

	private Image backgroundImage;

	public GuiLobby() {
	}

	public GuiLobby(int lobby) {
		this.stateID = lobby;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		this.charactersButton = new Rectangle(50, gc.getHeight() - 100, 120, 60);
		this.inventoryButton = new Rectangle(this.charactersButton.getX() + this.charactersButton.getWidth() + 50,
				gc.getHeight() - 100, 120, 60);
		this.adventureButton = new Circle(gc.getWidth() / 2, gc.getHeight() / 2, 200);
		this.ftUserName = new Font("Century Gothic", Font.BOLD, 20);
		this.ftTitre = new Font("Century Gothic", Font.BOLD, 50);
		this.trueTypeFontTitre = new TrueTypeFont(ftTitre, true);
		this.trueTypeFontUserName = new TrueTypeFont(ftUserName, true);

		this.trianglePoint = new float[] { 0, 0, 0, 250, 300, 0 };
		this.leftTopTriangle = new Polygon(this.trianglePoint);

		this.backgroundImage = new Image("/res/Half_Night.png");

		this.username = "";
		this.level = 0;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.drawImage(backgroundImage, 0, 0);

		g.setColor(Color.white);
		g.draw(charactersButton);
		g.fill(charactersButton);

		g.draw(inventoryButton);
		g.fill(inventoryButton);

		g.draw(adventureButton);
		g.fill(adventureButton);

		g.draw(leftTopTriangle);
		g.fill(leftTopTriangle);

		g.setColor(new Color(102, 204, 255));
		g.drawString("Character", charactersButton.getX() + 20, charactersButton.getY() + 20);
		g.drawString("Inventory", inventoryButton.getX() + 20, inventoryButton.getY() + 20);

		g.setColor(Color.white);
		g.setFont(trueTypeFontUserName);

		if(this.playerAccount != null) {
			g.drawString(username, 300, 50);
			g.drawString("Level : " + level, 300, 70);
		}
		

		g.setColor(new Color(102, 204, 255));
		g.setFont(trueTypeFontTitre);
		g.drawString("Adventure", this.adventureButton.getCenterX() - 120, this.adventureButton.getCenterY() - 35);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int g) throws SlickException {
		this.playerAccount = Game.getInstance().getPlayerAccount();
		this.username = this.playerAccount.getUsername();
		this.level = this.playerAccount.getAccount_Level();

	}

	@Override
	public void keyReleased(int key, char c) {

	}

	@Override
	public void keyPressed(int key, char c) {

	}

	@Override
	public void mousePressed(int button, int x, int y) {
		if (isHoveringButton(this.charactersButton, x, y) && button == 0) {
			Game.getInstance().enterState(4, new FadeOutTransition(),new FadeInTransition());
		}
	}
	
	public boolean isHoveringButton(Shape shape, int x, int y) {
		return shape.getX() < x && shape.getX() + shape.getWidth() > x && shape.getY() < y
				&& shape.getY() + shape.getHeight() > y;
	}

	@Override
	public void mouseReleased(int button, int x, int y) {

	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return this.stateID;
	}

}
