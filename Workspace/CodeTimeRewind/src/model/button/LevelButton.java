package model.button;

import org.newdawn.slick.Image;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class LevelButton extends Button {
	protected static final int WIDTH = 150;
	protected static final int HEIGHT = 150;
	private int levelId;

	public LevelButton(Image image, Image imageHit, int x, int y) {
		super(image, imageHit, x, y,WIDTH,HEIGHT);
	}
	public LevelButton(Image image, Image imageHit) {
		super(image, imageHit,WIDTH,HEIGHT);
	}
	
}
