package model.fight;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.entity.Character;
import model.entity.Enemy;


@NoArgsConstructor
@Getter
@Setter
public class Fight {
	private List<Character> listOfCharacter;
	private List<Enemy> listOfEnemy;
	private List<List<Object>> listOfLifeBars;

	public Fight(List<Character> listOfCharacter, List<Enemy> listOfEnemy) {
		this.listOfCharacter = listOfCharacter;
		this.listOfEnemy = listOfEnemy;
		this.listOfLifeBars = new ArrayList<List<Object>>();
	}

	public boolean Attack(int x, int y) {
		boolean isAlive = true;
		for (Enemy currentEnemy : this.listOfEnemy) {
			if (currentEnemy.isHovering(x, y)) {
				currentEnemy.setHealth(currentEnemy.getHealth()-damageCalculation(currentEnemy));
				if(currentEnemy.getHealth() == 0) {
					isAlive = false;
				}
			}
		}
		return isAlive;

	}

	public boolean isAttacking(int x, int y) {
		boolean bool = false;
		for (Enemy currentEnemy : this.listOfEnemy) {
			if (currentEnemy.isHovering(x, y)) {
				bool = true;
				break;
			}
			bool = false;
		}
		return bool;
	}
	
	public int whoAmIAttacking(int x, int y) {
		int i = 0;
		for (Enemy currentEnemy : this.listOfEnemy) {
			if (currentEnemy.isHovering(x, y)) {
				break;
			}
			i++;
		}
		return i;
	}
	
	private int damageCalculation(Enemy e) {
		int result = this.listOfCharacter.get(0).getAttack();
		float result1 = 100/(100+(float)e.getDefense());
		int result2 = Math.round(result*result1);
		return result2;
	}

}
