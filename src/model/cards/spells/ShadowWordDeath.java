package model.cards.spells;

import exceptions.InvalidTargetException;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.heroes.Hero;

public class ShadowWordDeath extends Spell implements MinionTargetSpell {

	public ShadowWordDeath() {
		super("Shadow Word: Death", 3, Rarity.BASIC);
		
	}

	public void performAction(Minion m) throws InvalidTargetException {
		if((m.getAttack()>=5)){
			m.setDivine(false);
			m.setCurrentHP(0);
		}
		else
			throw new InvalidTargetException();
	}
	public void performAction(Hero h) throws InvalidTargetException {
		throw new InvalidTargetException();
	}
}
