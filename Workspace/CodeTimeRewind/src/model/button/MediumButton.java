package model.button;

import org.newdawn.slick.Image;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
/**
 * this is a subclass from the Button class
 * 
 * @author Mathieu Rabot
 *
 */
public class MediumButton extends Button {
	protected static final int WIDTH = 600;
	protected static final int HEIGHT = 150;

	/**
	 * This is the first constructor of this class
	 * 
	 * @param image    this is the principal image of this button
	 * @param imageHit this is the image if the the button is press
	 * @param x        this is the X position of the button
	 * @param y        this is the Y position of the button
	 */
	public MediumButton(Image image, Image imageHit, float x, float y) {
		super(image, imageHit, x, y, WIDTH, HEIGHT);
	}

	/**
	 * This is the second constructor of this class without the button position
	 * 
	 * @param image    this is the principal image of this button
	 * @param imageHit this is the image if the the button is press
	 */
	public MediumButton(Image image, Image imageHit) {
		super(image, imageHit, WIDTH, HEIGHT);
	}
}
