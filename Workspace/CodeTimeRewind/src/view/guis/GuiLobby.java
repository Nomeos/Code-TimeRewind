package view.guis;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import controller.LobbyController;
import main.Game;
import model.account.Account;
import model.button.Button;

public class GuiLobby extends Gui {
	private int stateID;
	private String username;
	private int level;
	private Account playerAccount;
	private Image backgroundImage;

	private List<Button> listOfCurrentButton;
	private LobbyController controller;

	public GuiLobby() {
	}

	public GuiLobby(int lobby) {
		super(lobby);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

		this.controller = new LobbyController(this);
		this.buttonNeeded = new int[] { 5, 6, 7 };
this.backgroundImage = this.getListOfBackgrounds().get(1);
		this.listOfCurrentButton = new ArrayList<Button>();
		for (int i : this.buttonNeeded) {
			this.listOfCurrentButton.add(this.getListOfButtons().get(i));

		}

		this.username = "";
		this.level = 0;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(backgroundImage, 0, 0);
		for (Button button : this.listOfCurrentButton)
			button.draw();

		g.setColor(Color.white);

		if (this.playerAccount != null) {
			g.drawString(username, 300, 50);
			g.drawString("Level : " + level, 300, 70);

		}

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int g) throws SlickException {
		if (Game.getInstance().getPlayerAccount() != null) {
			this.playerAccount = Game.getInstance().getPlayerAccount();
			this.username = this.playerAccount.getUsername();
			this.level = this.playerAccount.getAccount_Level();
		}

	}

	@Override
	public void keyReleased(int key, char c) {
		// Do nothing because the user will not release or press any key.

	}

	@Override
	public void keyPressed(int key, char c) {
		// Do nothing because the user will not release or press any key.

	}

	@Override
	public void mousePressed(int button, int x, int y) {
		for (Button b : this.listOfCurrentButton) {
			if (b.isHovering(x, y) && button == 0) {
				b.setPressed(true);
			}
		}

	}

	@Override
	public void mouseReleased(int button, int x, int y) {

		for (Button b : this.listOfCurrentButton) {
			if (b.isHovering(x, y) && button == 0) {
				b.setPressed(false);
			}
		}
		for (Button b : this.listOfCurrentButton) {
			if (b.isHovering(x, y) && button == 0) {
				this.controller.activeButton(b);
			}
		}
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// Do nothing because no action while use the mouse mouvement feature.

	}



}
