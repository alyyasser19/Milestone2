package model.cards.spells;

import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class HolyNova extends Spell implements AOESpell {

	public HolyNova() {
		super("Holy Nova", 5, Rarity.BASIC);
	
	}
	
	public void performAction(ArrayList<Minion> oppField,ArrayList<Minion> curField) {
		if(!oppField.isEmpty())
		for(int i=0;i<oppField.size();i++) {
			if(oppField.get(i).getCurrentHP()<=2)
				oppField.remove(i);
			else
				oppField.get(i).setCurrentHP(oppField.get(i).getCurrentHP()-2);
		}
		if(!curField.isEmpty())
		for(int i=0;i<curField.size();i++) {
				curField.get(i).setCurrentHP(curField.get(i).getCurrentHP()+22);
		}
		
	}

}
