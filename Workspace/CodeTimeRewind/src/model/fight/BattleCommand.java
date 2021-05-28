package model.fight;

import org.newdawn.slick.command.Command;

/**
 * This is an enum that contains the action that the character and the enemy can
 * do in a fight
 * 
 * @author Mathieu Rabot
 *
 */
public enum BattleCommand implements Command {
	SPELLONE, SPELLTWO, SPELLTHREE, NONE;

}