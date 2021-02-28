package controller;


import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import main.Game;
import model.Account;

public class GuiCharacter extends BasicGameState{

	private Account player;
	private Shape lobbyButton;
	private Image backgroundImage;
	private Font characterNameFont;
	private TrueTypeFont characterNameTTF;
	
	public GuiCharacter(int state) {
		
	}
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		
		this.lobbyButton = new Rectangle(50, gc.getHeight() - 100, 120, 60);
		this.backgroundImage = new Image("/res/Half_Sunset.png");
		this.characterNameFont = new Font("Century Gothic", Font.BOLD, 50);
		this.characterNameTTF = new TrueTypeFont(characterNameFont, true);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.drawImage(backgroundImage,0, 0);
		
		g.setColor(Color.white);
		g.draw(lobbyButton);
		g.fill(lobbyButton);
		
		g.setColor(new Color(102, 204, 255));
		g.drawString("Lobby", lobbyButton.getX() + 20, lobbyButton.getY() + 20);
		
		if(this.player != null) {
			g.setFont(characterNameTTF);
			g.drawString(this.player.getListOfOwnedCharacter().get(0).getCharacterName(), 50, 50);
			g.setFont(new TrueTypeFont(new Font("Century Gothic", Font.BOLD, 30), true));
			g.drawString("Level : " + this.player.getListOfOwnedCharacter().get(0).getCharacterLevel() +"/40", 50, 120);
		}
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO Auto-generated method stub
		if(this.player == null){
			this.player = Game.getInstance().getPlayerAccount();
		}
		if(isHoveringButton(lobbyButton, delta, delta)) {
			
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
		if (isHoveringButton(this.lobbyButton, x, y) && button == 0) {
			Game.getInstance().enterState(3, new FadeOutTransition(),new FadeInTransition());
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
		return 4;
	}

}
