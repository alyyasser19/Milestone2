package model.cards.spells;
import model.cards.minions.*;
import model.cards.Rarity;

public class DivineSpirit extends Spell implements MinionTargetSpell {

	public DivineSpirit() {
		super("Divine Spirit", 3, Rarity.BASIC);
		
	}
	
	public void performAction(Minion m){
		int mHP=m.getMaxHP();
		m.setMaxHP(2*mHP);
		int cHP=m.getCurrentHP();
		m.setCurrentHP(2*cHP);
		
		
	}

}
