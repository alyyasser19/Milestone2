package model.cards.spells;

import exceptions.InvalidTargetException;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.heroes.Hero;

public class KillCommand extends Spell implements MinionTargetSpell, HeroTargetSpell {

	public KillCommand() {
		super("Kill Command", 3, Rarity.COMMON);
		
	}
	public void performAction(Hero h) {
		int cHP=h.getCurrentHP();
		h.setCurrentHP((cHP-3));
		
	}
	
	public void performAction(Minion m) throws InvalidTargetException {
		int cHP=m.getCurrentHP();
		m.setCurrentHP((cHP-5));
		
	}
}
