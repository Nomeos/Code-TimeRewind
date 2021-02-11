package controller;

import java.awt.Font;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import main.Game;

public class GuiMenu extends BasicGameState {

	private int x;
	private int width;
	private int height;
	private final String playString = "Play", exitString = "Exit";
	private Shape playButton = null, exitButton = null;
	private int mouseX;
	private int mouseY;
	private String position = "";
	private Font ft = null;

	public GuiMenu() {
	}

	public GuiMenu(int state) {
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.height = 150;
		this.width = 600;

		this.x = ((gc.getWidth() / 2) - (width / 2));
		this.playButton = new Rectangle(x, 400, width, height);
		this.exitButton = new Rectangle(x, 650, width, height);
		this.ft = new Font("Century Gothic", Font.BOLD, 20);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setBackground(Color.gray);
		g.setColor(new Color(64, 207, 249));

		g.draw(playButton);
		g.draw(exitButton);

		g.fill(playButton);
		g.fill(exitButton);

		g.setFont(new TrueTypeFont(ft, true));

		g.setColor(Color.white);
		g.drawString(playString, playButton.getCenterX(), playButton.getCenterY());
		g.drawString(exitString, exitButton.getCenterX(), exitButton.getCenterY());
		g.drawString(this.position, 50, 50);
		

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO Auto-generated method stub
		mouseX = Mouse.getX();
		mouseY = Mouse.getY();
		position = "voici les positions : " + mouseX + " et " + mouseY;

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		if (isHoveringButton(playButton, x, y) && button == 0) {
			Game.getInstance().enterState(1);
		}
		if (isHoveringButton(exitButton, x, y) && button == 0) {
			Game.getInstance().getContainer().exit();
		}
	}

	public boolean isHoveringButton(Shape shape, int x, int y) {
		return shape.getX() < x && shape.getX() + shape.getWidth() > x && shape.getY() < y
				&& shape.getY() + shape.getHeight() > y;
	}

}
