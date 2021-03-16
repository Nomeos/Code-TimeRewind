package model.fight;

import java.util.List;

import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProviderListener;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.entity.Character;
import model.entity.Enemy;

@Getter
@Setter
@NoArgsConstructor
public class BattleController implements InputProviderListener {
	private List<Character> listOfCharacter;
	private List<Enemy> listOfEnemy;
	private Character currentCharacter;
	private Enemy currentEnemy;
	private boolean isEnemiesTurn;

	public BattleController(List<Character> c, List<Enemy> e) {
		this.listOfEnemy = e;
		this.listOfCharacter = c;
		this.isEnemiesTurn = false;

	}

	@Override
	public void controlPressed(Command command) {
		switch ((BattleCommand) command) {
		case SPELLONE:
			attack();
			break;
		case SPELLTWO:
			attack();
			break;
		case SPELLTHREE:
			attack();
			break;
		default:
			break;
		}
	}

	@Override
	public void controlReleased(Command command) {
	}

	private void attack() {
		if (isEnemiesTurn) {
			int result = this.currentCharacter.getAttack();
			float result1 = 100 / (100 + (float) this.currentEnemy.getDefense());
			int result2 = Math.round(result * result1);
		} else {
			int result = this.currentEnemy.getAttack();
			float result1 = 100 / (100 + (float) this.currentCharacter.getDefense());
			int result2 = Math.round(result * result1);
		}

	}
}