package model.image;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LifeBars extends Image {
	private final static String PATH = "/res/zones/LifePoint.png";

	public LifeBars() throws SlickException {
		super(PATH);

	}

	public void fadeOut() {
		this.alpha = this.getAlpha() - 0.01f;
		this.setAlpha(alpha);
	}

}
