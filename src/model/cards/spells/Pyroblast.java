package model.cards.spells;

import exceptions.InvalidTargetException;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.heroes.Hero;

public class Pyroblast extends Spell implements HeroTargetSpell, MinionTargetSpell {
	public Pyroblast()
	{
		super("Pyroblast", 10, Rarity.EPIC);
	}

	public void performAction(Minion m) throws InvalidTargetException {
		int cHP=m.getCurrentHP();
		m.setCurrentHP((cHP-10));
		
	}

	public void performAction(Hero h) {
		int cHP=h.getCurrentHP();
		h.setCurrentHP((cHP-10));
		
	}
	
}
