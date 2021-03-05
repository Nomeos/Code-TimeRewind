package view.guis;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import main.Game;
import model.button.BigButton;
import model.button.Button;
import model.button.SmallButton;

public class GuiAdventure extends BasicGameState {

	private int stateID;
	private Image background;
	private Button chapterOne;
	private Button chapterTwo;
	private Button chapterThree;
	private Button lobbyButton;
	private boolean initializeChapterOne = true;
	private boolean initializeChapterTwo = true;
	private boolean initializeChapterThree = true;
	private boolean initializeLobby = true;
	private int middleOfTheScreenWidth;
	private int middleOfTheScreenHeight;
	private int lobbyButtonXPosition;
	private int lobbyButtonYPosition;

	public GuiAdventure(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.background = new Image("/res/End_Sunset.png");

		this.middleOfTheScreenWidth = (gc.getWidth() / 2) - 175;
		this.middleOfTheScreenHeight = (gc.getHeight() / 2) - 175;

		this.lobbyButtonXPosition = 50;
		this.lobbyButtonYPosition = (gc.getHeight() - 110);

		this.chapterOne = new BigButton(new Image("/res/buttons/ChapterOneButton.png"),
				new Image("/res/buttons/ChapterOneButtonHit.png"), middleOfTheScreenWidth - 370,
				middleOfTheScreenHeight);

		this.chapterTwo = new BigButton(new Image("/res/buttons/ChapterTwoButton.png"),
				new Image("/res/buttons/ChapterTwoButtonHit.png"), middleOfTheScreenWidth, middleOfTheScreenHeight);

		this.chapterThree = new BigButton(new Image("/res/buttons/ChapterThreeButton.png"),
				new Image("/res/buttons/ChapterThreeButtonHit.png"), middleOfTheScreenWidth + 370,
				middleOfTheScreenHeight);

		this.lobbyButton = new SmallButton(new Image("/res/buttons/LobbyButton.png"),
				new Image("/res/buttons/LobbyButtonHit.png"), lobbyButtonXPosition, lobbyButtonYPosition);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		this.background.draw(0, 0);
		this.chapterOne.draw();
		this.chapterTwo.draw();
		this.chapterThree.draw();

		if (initializeChapterOne) {
			this.chapterOne.draw();
		} else {
			if (!this.chapterOne.isPressed()) {
				this.chapterOne.draw();
			} else {
				this.chapterOne.draw();
			}
		}
		if (initializeChapterTwo) {
			this.chapterTwo.draw();
		} else {
			if (!this.chapterTwo.isPressed()) {
				this.chapterTwo.draw();
			} else {
				this.chapterTwo.draw();
			}
		}
		if (initializeChapterThree) {
			this.chapterThree.draw();
		} else {
			if (!this.chapterThree.isPressed()) {
				this.chapterThree.draw();
			} else {
				this.chapterThree.draw();
			}
		}
		if (initializeLobby) {
			this.lobbyButton.draw();
		} else {
			if (!this.lobbyButton.isPressed()) {
				this.lobbyButton.draw();
			} else {
				this.lobbyButton.draw();
			}
		}

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

	}

	@Override
	public void mousePressed(int button, int x, int y) {
		if (this.chapterOne.isHovering(x, y) && button == 0) {
			this.initializeChapterOne = false;
			this.chapterOne.setPressed(true);
		}
		if (this.chapterTwo.isHovering(x, y) && button == 0) {
			this.initializeChapterTwo = false;
			this.chapterTwo.setPressed(true);
		}
		if (this.chapterThree.isHovering(x, y) && button == 0) {
			this.initializeChapterThree = false;
			this.chapterThree.setPressed(true);
		}
		if (this.lobbyButton.isHovering(x, y) && button == 0) {
			this.initializeLobby = false;
			this.lobbyButton.setPressed(true);
		}
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		this.chapterOne.setPressed(false);
		this.chapterTwo.setPressed(false);
		this.chapterThree.setPressed(false);
		if (this.chapterOne.isHovering(x, y) && button == 0) {

		}
		if (this.chapterTwo.isHovering(x, y) && button == 0) {

		}
		if (this.chapterThree.isHovering(x, y) && button == 0) {

		}
		this.lobbyButton.setPressed(false);
		if (this.lobbyButton.isHovering(x, y) && button == 0) {
			Game.getInstance().enterState(3, new FadeOutTransition(), new FadeInTransition());
		}
	}

	@Override
	public int getID() {

		return stateID;
	}

}
