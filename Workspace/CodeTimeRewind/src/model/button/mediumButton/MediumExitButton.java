package model.button.mediumButton;

import org.newdawn.slick.Image;

/**
 * 
 * this is a subclass from the Button class
 * 
 * @author Mathieu Rabot
 *
 */
public class MediumExitButton extends MediumButton {
	protected static final float X = 660;
	protected static final float Y = 600;
	
	/**
	 * This is one of the constructors of this class
	 * 
	 * @param image    this is the principal image of this button
	 * @param imageHit this is the image if the the button is press
	 */
	public MediumExitButton(Image image, Image imageHit) {
		super(X,Y,image,imageHit);
	}
}
