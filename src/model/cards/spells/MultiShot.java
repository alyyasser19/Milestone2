package model.cards.spells;

import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class MultiShot extends Spell implements AOESpell{

	public MultiShot() {
		super("Multi-Shot", 4,Rarity.BASIC);
		
	}

	public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) {
		if(oppField.size()==1){
			Minion m=oppField.get(0);
			m.setCurrentHP((m.getCurrentHP()-3));
		}
		else if(oppField.size()>1){
			int i=(int) (Math.random()*oppField.size());
			int j=(int) (Math.random()*oppField.size());
			while(j==i){
				j=(int) (Math.random()*oppField.size());
			}
			Minion m1=oppField.get(i);
			Minion m2=oppField.get(j);
			m1.setCurrentHP((m1.getCurrentHP()-3));
			m2.setCurrentHP((m2.getCurrentHP()-3));
		}
		
	}

}
