package model.image;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * This is a subclass of Image, it contains the Life bar image
 * 
 * @author Mathieu Rabot
 *
 */
public class LifeBars extends Image {
	private final static String PATH = "/res/zones/LifePoint.png";

	/**
	 * This is the constructor of this class
	 * 
	 * @throws SlickException It throws a slick Exception if the image create failed
	 */
	public LifeBars() throws SlickException {
		super(PATH);

	}

	/**
	 * This method make the life bar disappear
	 */
	public void fadeOut() {
		this.alpha = this.getAlpha() - 0.01f;
		this.setAlpha(alpha);
	}

}
