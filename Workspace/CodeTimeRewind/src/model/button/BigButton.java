package model.button;

import org.newdawn.slick.Image;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class BigButton extends Button {
	protected static final int WIDTH = 350;
	protected static final int HEIGHT = 350;

	public BigButton(Image image, Image imageHit, int x, int y) {
		super(image, imageHit, x, y, WIDTH, HEIGHT);
	}

}
