package controller;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import main.Game;
import model.Account;

public class GuiLobby extends BasicGameState {
	private int stateID;
	private Shape charactersButton = null, inventoryButton = null, adventureButton = null, leftTopTriangle = null;
	private Account playerAccount;
	private Font ftTitre, ftUserName;
	private TrueTypeFont trueTypeFontTitre, trueTypeFontUserName;
	private boolean accountInformationTransfered = false;
	private float[] trianglePoint;

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

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
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
		g.drawString(this.playerAccount.getUsername(), 300, 50);
		g.drawString("Level : " + this.playerAccount.getAccount_Level(), 300, 70);

		g.setColor(new Color(102, 204, 255));
		g.setFont(trueTypeFontTitre);
		g.drawString("Adventure", this.adventureButton.getCenterX() - 120, this.adventureButton.getCenterY() - 35);

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int g) throws SlickException {
		// TODO Auto-generated method stub
		if (Game.getInstance().getPlayerAccount() != null && accountInformationTransfered == false) {
			this.playerAccount = Game.getInstance().getPlayerAccount();
			this.accountInformationTransfered = true;
		}

	}

	@Override
	public void keyReleased(int key, char c) {

	}

	@Override
	public void keyPressed(int key, char c) {

	}

	@Override
	public void mousePressed(int button, int x, int y) {

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
