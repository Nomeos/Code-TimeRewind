package view.guis;

import java.util.ArrayList;
import java.util.List;

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

public class GuiChapters extends BasicGameState {

	private int stateID;
	private Image background;
	private Button chapterOne;
	private Button chapterTwo;
	private Button chapterThree;
	private Button lobbyButton;
	private List<Button> listOfButton;
	private int middleOfTheScreenWidth;
	private int middleOfTheScreenHeight;
	private int lobbyButtonXPosition;
	private int lobbyButtonYPosition;

	public GuiChapters(int stateID) {
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
		this.listOfButton = new ArrayList<Button>();
		this.listOfButton.add(chapterOne);
		this.listOfButton.add(chapterThree);
		this.listOfButton.add(chapterTwo);
		this.listOfButton.add(lobbyButton);
		
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		this.background.draw(0, 0);
		for(Button button : this.listOfButton) button.draw();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

	}

	@Override
	public void mousePressed(int button, int x, int y) {
		if (this.chapterOne.isHovering(x, y) && button == 0) {
			this.chapterOne.setPressed(true);
		}
		if (this.chapterTwo.isHovering(x, y) && button == 0) {
			this.chapterTwo.setPressed(true);
		}
		if (this.chapterThree.isHovering(x, y) && button == 0) {
			this.chapterThree.setPressed(true);
		}
		if (this.lobbyButton.isHovering(x, y) && button == 0) {
			this.lobbyButton.setPressed(true);
		}
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		this.chapterOne.setPressed(false);
		this.chapterTwo.setPressed(false);
		this.chapterThree.setPressed(false);
		this.lobbyButton.setPressed(false);
		if (this.chapterOne.isHovering(x, y) && button == 0) {
			Game.getInstance().setCurrentChapter(1);
			Game.getInstance().enterState(6, new FadeOutTransition(), new FadeInTransition());
		}
		if (this.chapterTwo.isHovering(x, y) && button == 0) {
			Game.getInstance().setCurrentChapter(2);
			Game.getInstance().enterState(6, new FadeOutTransition(), new FadeInTransition());
		}
		if (this.chapterThree.isHovering(x, y) && button == 0) {
			Game.getInstance().setCurrentChapter(3);
			Game.getInstance().enterState(6, new FadeOutTransition(), new FadeInTransition());
		}
		if (this.lobbyButton.isHovering(x, y) && button == 0) {
			Game.getInstance().enterState(6, new FadeOutTransition(), new FadeInTransition());
		}
	}

	@Override
	public int getID() {

		return stateID;
	}

}
