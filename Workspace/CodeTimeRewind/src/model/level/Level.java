package model.level;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.entity.Enemy;

@NoArgsConstructor
@Getter
@Setter
public class Level {
	List<Enemy> listOfEnemy = new ArrayList<Enemy>();
	private String name;
	private boolean isLevelClear;
	private int xPosition;
	private int yPosition;

	public Level(String name, boolean isLevelClear, int xPosition, int yPosition) {
		this.name = name;
		this.isLevelClear = isLevelClear;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}

}
