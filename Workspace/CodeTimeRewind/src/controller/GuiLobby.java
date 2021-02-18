package controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GuiLobby extends BasicGameState {
	private int stateID;
	private Shape charactersButton = null, inventoryButton = null;

	public GuiLobby() {}
	
	public GuiLobby(int lobby) {
		this.stateID = lobby;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		this.charactersButton = new Rectangle(50, gc.getHeight()-100, 120, 60);
		this.inventoryButton = new Rectangle(this.charactersButton.getX()+this.charactersButton.getWidth()+50, gc.getHeight()-100, 120, 60);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.draw(charactersButton);
		g.fill(charactersButton);
		
		g.draw(inventoryButton);
		g.fill(inventoryButton);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int g) throws SlickException {
		// TODO Auto-generated method stub
		
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
