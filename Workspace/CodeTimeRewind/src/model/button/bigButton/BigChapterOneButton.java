package model.button.bigButton;

import org.newdawn.slick.Image;

import model.button.BigButton;

/**
 * this is a subclass from the BigButton class
 * 
 * @author Mathieu Rabot
 *
 */
public class BigChapterOneButton extends BigButton {
	private static final float X = 415;
	private static final float Y = 365;
	
	/**
	 * This is one of the constructors of this class
	 * 
	 * @param image    this is the principal image of this button
	 * @param imageHit this is the image if the the button is press
	 */
	public BigChapterOneButton(Image image, Image imageHit) {
		super(image, imageHit, X, Y);
	}
}
