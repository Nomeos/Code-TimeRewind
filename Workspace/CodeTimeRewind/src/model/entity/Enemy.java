package model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Enemy extends Entity {
	public Enemy(String name, int level, int health, int defense, int attack, int speed) {
		super(name,level,health,defense,attack,speed);
	}
}
