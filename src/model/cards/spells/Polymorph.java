package model.cards.spells;

import exceptions.InvalidTargetException;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.heroes.Hero;

public class Polymorph extends Spell implements MinionTargetSpell {

	public Polymorph() {
		super("Polymorph", 4, Rarity.BASIC);
	}

	public void performAction(Minion m) throws InvalidTargetException {
		m.setAttack(1);
		m.setCurrentHP(1);
		m.setMaxHP(1);
		m.setName("Sheep");
		m.setTaunt(false);
		m.setDivine(false);
		m.setManaCost(1);
		m.setSleeping(true);
	}
	public void performAction(Hero h) throws InvalidTargetException{
		throw new InvalidTargetException();
	}

}
