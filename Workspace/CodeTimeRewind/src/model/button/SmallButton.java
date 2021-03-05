package model.button;

import org.newdawn.slick.Image;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class SmallButton extends Button {
	protected static final int WIDTH = 350;
	protected static final int HEIGHT = 60;

	public SmallButton(Image image, Image imageHit, int x, int y) {
		super(image, imageHit, x, y,WIDTH,HEIGHT);
	}
}
