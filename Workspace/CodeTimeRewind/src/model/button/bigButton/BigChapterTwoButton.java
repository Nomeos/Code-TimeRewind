package model.button.bigButton;

import org.newdawn.slick.Image;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * this is a subclass from the Button class
 * 
 * @author Mathieu Rabot
 *
 */
@NoArgsConstructor
@Setter
@Getter
public class BigChapterTwoButton extends BigButton {
	private static final float X = 785;
	private static final float Y = 365;

	/**
	 * This is one of the constructors of this class
	 * 
	 * @param image    this is the principal image of this button
	 * @param imageHit this is the image if the the button is press
	 */
	public BigChapterTwoButton(Image image, Image imageHit) {
		super(image, imageHit, X, Y);
	}
}
