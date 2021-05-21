package controller;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.button.Button;
import model.button.SmallButton;
import model.livingEntity.LivingEntity;
import view.guis.GuiCharacter;

/**
 * This class is a subclass of the principal controller, this is the controller
 * for the Character View
 * 
 * @author Mathieu Rabot
 *
 */
@Setter
@Getter
@NoArgsConstructor
public class CharacterController extends Controller {
	private GuiCharacter guiCharacter;
	private List<List<LivingEntity>> listLivingEntities = new ArrayList<List<LivingEntity>>();
	private Integer currentPageCharacter;
	private Integer currentCharacterIndex;

	/**
	 * This is the constructor of this class
	 * 
	 * @param guiCharacter this is the current guiCharacter view and allows me to
	 *                     interact with it
	 */
	public CharacterController(GuiCharacter guiCharacter) {
		this.guiCharacter = guiCharacter;
	}

	/**
	 * this method starts when you use a button in the view
	 * 
	 * @param currentButton this is the current button that has been pressed
	 */
	public void activeButton(Button currentButton) {
		if (currentButton instanceof SmallButton) {
			this.changeView(3);
		}

	}

	public void createListLivingEntity(List<LivingEntity> livingEntity) {
		int numberOfItem = livingEntity.size();
		int numberOfPage = Math.round(numberOfItem / 6);
		int currentEntityIndex = 0;
		List<LivingEntity> currentList = null;

		for (int i = 0; i <= (numberOfPage - 1); i++) {
			for (int j = 0; j <= 5; j++) {
				if (j == 0) {
					currentList = new ArrayList<LivingEntity>();
				}
				currentList.add(livingEntity.get(currentEntityIndex));
				currentEntityIndex++;
			}
			this.listLivingEntities.add(currentList);
		}
	}

}
