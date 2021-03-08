package view.guis;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import main.Game;
import model.account.Account;
import model.button.BigButton;
import model.button.Button;
import model.button.SmallButton;

public class GuiLobby extends BasicGameState {
	private int stateID;
	private String username;
	private int level;
	private Account playerAccount;
	private Image backgroundImage;
	private Button characterButton;
	private Button inventoryButton;
	private Button adventureButton;
	private List<Button> listOfButton;

	public GuiLobby() {
	}

	public GuiLobby(int lobby) {
		this.stateID = lobby;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

		int characterButtonXPosition = 50;
		int smallButtonYPosition = (gc.getHeight() - 110);
		int inventoryButtonXPosition = characterButtonXPosition + 50;
		int adventureButtonXPosition = (gc.getWidth() / 2) - (350 / 2);
		int adventureButtonYPosition = (gc.getHeight() / 2) - (350 / 2);

		this.characterButton = new SmallButton(new Image("/res/buttons/CharacterButton.png"),
				new Image("/res/buttons/CharacterButtonHit.png"), characterButtonXPosition, smallButtonYPosition);
		this.inventoryButton = new SmallButton(new Image("/res/buttons/InventoryButton.png"),
				new Image("/res/buttons/InventoryButtonHit.png"),
				inventoryButtonXPosition + this.characterButton.getWidth(), smallButtonYPosition);
		this.adventureButton = new BigButton(new Image("/res/buttons/AdventureButton.png"),
				new Image("/res/buttons/AdventureButtonHit.png"), adventureButtonXPosition, adventureButtonYPosition);
		this.backgroundImage = new Image("/res/Half_Night.png");

		this.listOfButton = new ArrayList<Button>();
		this.listOfButton.add(adventureButton);
		this.listOfButton.add(characterButton);
		this.listOfButton.add(inventoryButton);

		this.username = "";
		this.level = 0;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(backgroundImage, 0, 0);
		for (Button button : this.listOfButton)
			button.draw();

		g.setColor(Color.white);

		if (this.playerAccount != null) {
			g.drawString(username, 300, 50);
			g.drawString("Level : " + level, 300, 70);

		}

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int g) throws SlickException {
		if (this.playerAccount == null) {
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
		if (this.characterButton.isHovering(x, y) && button == 0) {
			this.characterButton.setPressed(true);
		}
		if (this.inventoryButton.isHovering(x, y) && button == 0) {
			this.inventoryButton.setPressed(true);
		}
		if (this.adventureButton.isHovering(x, y) && button == 0) {
			this.adventureButton.setPressed(true);
		}
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		this.characterButton.setPressed(false);
		this.inventoryButton.setPressed(false);
		this.adventureButton.setPressed(false);

		if (this.characterButton.isHovering(x, y) && button == 0) {
			Game.getInstance().enterState(4, new FadeOutTransition(), new FadeInTransition());
		}
		if (this.inventoryButton.isHovering(x, y) && button == 0) {

		}
		if (this.adventureButton.isHovering(x, y) && button == 0) {
			Game.getInstance().enterState(5, new FadeOutTransition(), new FadeInTransition());
		}
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// Do nothing because no action while use the mouse mouvement feature.

	}

	@Override
	public int getID() {
		return this.stateID;
	}

}
