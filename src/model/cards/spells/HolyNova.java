package model.cards.spells;

import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class HolyNova extends Spell implements AOESpell {

	public HolyNova() {
		super("Holy Nova", 5, Rarity.BASIC);
	
	}
	
	public void performAction(ArrayList<Minion> oppField,ArrayList<Minion> curField) {
		for(Minion i:oppField){
			int oHP=(i.getCurrentHP()-2);
			i.setCurrentHP(oHP);	
			if(i.getCurrentHP()==0){
				oppField.remove(i);
			}
		}
		for(Minion j:curField){
			int cHP=(j.getCurrentHP()+2);
			j.setCurrentHP(cHP);
		}
		
	}

}
