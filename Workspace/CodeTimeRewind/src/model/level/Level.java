package model.level;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.entity.Entity;

@NoArgsConstructor
@Getter
@Setter
public class Level {
	List<Entity> listOfEntity = new ArrayList<Entity>();
	private int level_Id;
	private String name;
	private boolean isLevelClear;

}
