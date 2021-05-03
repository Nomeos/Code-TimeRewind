package controller;

import main.Game;
import model.button.Button;
import model.button.bigButton.BigChapterOneButton;
import model.button.bigButton.BigChapterThreeButton;
import model.button.bigButton.BigChapterTwoButton;
import view.guis.GuiChapters;

/**
 * This class is a subclass of the principal controller, this is the controller
 * for the Chapters View
 * 
 * @author Mathieu Rabot
 *
 */
public class ChapterController extends Controller {
	private GuiChapters guiChapters;

	/**
	 * This is the constructor of this class
	 * 
	 * @param guiChapters this is the current guiCharacter view and allows me to
	 *                    interact with it
	 */
	public ChapterController(GuiChapters guiChapters) {
		this.guiChapters = guiChapters;
	}

	/**
	 * this method starts when you use a button in the view
	 * 
	 * @param currentButton this is the current button that has been pressed
	 */
	public void activeButton(Button currentButton) {
		if (currentButton instanceof BigChapterOneButton) {
			Game.getInstance().setCurrentChapter(1);
			this.changeView(6);
		} else if (currentButton instanceof BigChapterTwoButton) {
			Game.getInstance().setCurrentChapter(2);
			this.changeView(6);
		} else if (currentButton instanceof BigChapterThreeButton) {
			Game.getInstance().setCurrentChapter(3);
			this.changeView(6);
		} else {
			this.changeView(3);
		}
	}

}
