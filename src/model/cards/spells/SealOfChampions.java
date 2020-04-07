package model.cards.spells;

import exceptions.InvalidTargetException;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.heroes.Hero;

public class SealOfChampions extends Spell implements MinionTargetSpell {

	public SealOfChampions() {
		super("Seal of Champions", 3, Rarity.COMMON);
		
	}

	public void performAction(Minion m) throws InvalidTargetException {
		int a=m.getAttack();
		m.setAttack((a+3));
		m.setDivine(true);
		
	}
	public void performAction(Hero h) throws InvalidTargetException {
		throw new InvalidTargetException();
	}

	
}
