package model.button;

import org.newdawn.slick.Image;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class MediumButton extends Button {
	protected static final int WIDTH = 600;
	protected static final int HEIGHT = 150;

	public MediumButton(Image image, Image imageHit, float x, float y) {
		super(image, imageHit, x, y, WIDTH, HEIGHT);
	}

	public MediumButton(Image image, Image imageHit) {
		super(image, imageHit, WIDTH, HEIGHT);
	}
}
