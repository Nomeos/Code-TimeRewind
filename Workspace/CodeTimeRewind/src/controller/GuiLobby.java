package controller;

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
import model.Account;

public class GuiLobby extends BasicGameState {
	private int stateID;
	private int level;
	private int smallButtonHeight;
	private int smallButtonWidth;
	private int smallButtonYPosition;
	private int characterButtonXPosition;
	private int inventoryButtonXPosition;
	private int adventureButtonWidth;
	private int adventureButtonHeight;
	private int adventureButtonXPosition;
	private int adventureButtonYPosition;
	private Account playerAccount;
	private String username;
	private boolean initializeCharacter = true;
	private boolean isPressedCharacter = false;
	private boolean initializeInventory = true;
	private boolean isPressedInventory = false;
	private boolean initializeAdventure = true;
	private boolean isPressedAdventure = false;
	private Image backgroundImage;
	private Image charactersButton;
	private Image inventoryButton;
	private Image adventureButton;

	public GuiLobby() {
	}

	public GuiLobby(int lobby) {
		this.stateID = lobby;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.smallButtonHeight = 60;
		this.smallButtonWidth = 350;
		this.characterButtonXPosition = 50;
		this.smallButtonYPosition = (gc.getHeight() - 110);
		this.inventoryButtonXPosition = characterButtonXPosition + smallButtonWidth + 50;
		this.adventureButtonWidth = 400;
		this.adventureButtonHeight = 400;
		this.adventureButtonXPosition = (gc.getWidth() / 2) - (adventureButtonWidth / 2);
		this.adventureButtonYPosition = (gc.getHeight() / 2) - (adventureButtonHeight / 2);

		this.backgroundImage = new Image("/res/Half_Night.png");

		this.username = "";
		this.level = 0;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(backgroundImage, 0, 0);

		renderAdventureButton();
		renderCharacterButton();
		renderInventoryButton();

		g.setColor(Color.white);

		if (this.playerAccount != null) {
			g.drawString(username, 300, 50);
			g.drawString("Level : " + level, 300, 70);

		}

	}

	public void renderCharacterButton() {
		try {
			if (initializeCharacter) {
				this.charactersButton = new Image("/res/buttons/CharacterButton.png");
				this.charactersButton.draw(characterButtonXPosition, smallButtonYPosition);
			} else {
				if (!isPressedCharacter) {
					this.charactersButton.destroy();
					this.charactersButton = new Image("/res/buttons/CharacterButton.png");
					this.charactersButton.draw(characterButtonXPosition, smallButtonYPosition);
				} else {
					this.charactersButton.destroy();
					this.charactersButton = new Image("/res/buttons/CharacterButtonHit.png");
					this.charactersButton.draw(characterButtonXPosition, smallButtonYPosition);
				}
			}
		} catch (SlickException e) {

			e.printStackTrace();
		}
	}

	public void renderInventoryButton() {
		try {
			if (initializeInventory) {
				this.inventoryButton = new Image("/res/buttons/InventoryButton.png");
				this.inventoryButton.draw(inventoryButtonXPosition, smallButtonYPosition);
			} else {
				if (!isPressedInventory) {
					this.inventoryButton.destroy();
					this.inventoryButton = new Image("/res/buttons/InventoryButton.png");
					this.inventoryButton.draw(inventoryButtonXPosition, smallButtonYPosition);
				} else {
					this.inventoryButton.destroy();
					this.inventoryButton = new Image("/res/buttons/InventoryButtonHit.png");
					this.inventoryButton.draw(inventoryButtonXPosition, smallButtonYPosition);
				}
			}
		} catch (SlickException e) {

			e.printStackTrace();
		}
	}

	public void renderAdventureButton() {

		try {
			if (initializeAdventure) {
				this.adventureButton = new Image("/res/buttons/AdventureButton.png");

				this.adventureButton.draw(adventureButtonXPosition, adventureButtonYPosition);
			} else {
				if (!isPressedAdventure) {
					this.adventureButton.destroy();
					this.adventureButton = new Image("/res/buttons/AdventureButton.png");
					this.adventureButton.draw(adventureButtonXPosition, adventureButtonYPosition);
				} else {
					this.adventureButton.destroy();
					this.adventureButton = new Image("/res/buttons/AdventureButtonHit.png");
					this.adventureButton.draw(adventureButtonXPosition, adventureButtonYPosition);
				}
			}
		} catch (SlickException e) {

			e.printStackTrace();
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
		if (characterButtonXPosition <= x && (characterButtonXPosition + smallButtonWidth) >= x
				&& smallButtonYPosition <= y && (smallButtonYPosition + smallButtonHeight) >= y && button == 0) {
			this.initializeCharacter = false;
			this.isPressedCharacter = true;
		}
		if (inventoryButtonXPosition <= x && (inventoryButtonXPosition + smallButtonWidth) >= x
				&& smallButtonYPosition <= y && (smallButtonYPosition + smallButtonHeight) >= y && button == 0) {
			this.initializeInventory = false;
			this.isPressedInventory = true;
		}
		if (adventureButtonXPosition <= x && (adventureButtonXPosition + adventureButtonWidth) >= x
				&& adventureButtonYPosition <= y && (adventureButtonYPosition + adventureButtonHeight) >= y
				&& button == 0) {
			this.initializeAdventure = false;
			this.isPressedAdventure = true;
		}
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		this.isPressedCharacter = false;
		this.isPressedInventory = false;
		this.isPressedAdventure = false;
		if (characterButtonXPosition <= x && (characterButtonXPosition + smallButtonWidth) >= x
				&& smallButtonYPosition <= y && (smallButtonYPosition + smallButtonHeight) >= y && button == 0) {
			Game.getInstance().enterState(4, new FadeOutTransition(), new FadeInTransition());
		}
		if (inventoryButtonXPosition <= x && (inventoryButtonXPosition + smallButtonWidth) >= x
				&& smallButtonYPosition <= y && (smallButtonYPosition + smallButtonHeight) >= y && button == 0) {

		}
		if (adventureButtonXPosition <= x && (adventureButtonXPosition + adventureButtonWidth) >= x
				&& adventureButtonYPosition <= y && (adventureButtonYPosition + adventureButtonHeight) >= y
				&& button == 0) {

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
