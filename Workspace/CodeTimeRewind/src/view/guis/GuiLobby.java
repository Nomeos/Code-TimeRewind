package view.guis;

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
	private int level;
	private int smallButtonYPosition;
	private int characterButtonXPosition;
	private int inventoryButtonXPosition;

	private int adventureButtonXPosition;
	private int adventureButtonYPosition;
	private Account playerAccount;
	private String username;
	private boolean initializeCharacter = true;
	private boolean initializeInventory = true;
	private boolean initializeAdventure = true;
	private Image backgroundImage;
	private Button characterButton;
	private Button inventoryButton;
	private Button adventureButton;

	public GuiLobby() {
	}

	public GuiLobby(int lobby) {
		this.stateID = lobby;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

		this.characterButtonXPosition = 50;
		this.smallButtonYPosition = (gc.getHeight() - 110);
		this.inventoryButtonXPosition = characterButtonXPosition + 50;
		this.adventureButtonXPosition = (gc.getWidth() / 2) - (350 / 2);
		this.adventureButtonYPosition = (gc.getHeight() / 2) - (350 / 2);

		this.characterButton = new SmallButton(new Image("/res/buttons/CharacterButton.png"),
				new Image("/res/buttons/CharacterButtonHit.png"), characterButtonXPosition, smallButtonYPosition);
		this.inventoryButton = new SmallButton(new Image("/res/buttons/InventoryButton.png"),
				new Image("/res/buttons/InventoryButtonHit.png"),
				inventoryButtonXPosition + this.characterButton.getWIDTH(), smallButtonYPosition);
		this.adventureButton = new BigButton(new Image("/res/buttons/AdventureButton.png"),
				new Image("/res/buttons/AdventureButtonHit.png"), adventureButtonXPosition, adventureButtonYPosition);
		this.backgroundImage = new Image("/res/Half_Night.png");

		this.username = "";
		this.level = 0;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(backgroundImage, 0, 0);

		if (initializeCharacter) {
			this.characterButton.draw();
		} else {
			if (!this.characterButton.isPressed()) {
				this.characterButton.draw();
			} else {
				this.characterButton.draw();
			}
		}
		if (initializeInventory) {
			this.inventoryButton.draw();
		} else {
			if (!this.inventoryButton.isPressed()) {
				this.inventoryButton.draw();
			} else {
				this.inventoryButton.draw();
			}
		}
		if (initializeAdventure) {
			this.adventureButton.draw();
		} else {
			if (!this.adventureButton.isPressed()) {
				this.adventureButton.draw();
			} else {
				this.adventureButton.draw();
			}
		}

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
			this.initializeCharacter = false;
			this.characterButton.setPressed(true);
		}
		if (this.inventoryButton.isHovering(x, y) && button == 0) {
			this.initializeInventory = false;
			this.inventoryButton.setPressed(true);
		}
		if (this.adventureButton.isHovering(x, y) && button == 0) {
			this.initializeAdventure = false;
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
