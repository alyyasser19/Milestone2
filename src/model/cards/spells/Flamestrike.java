package model.cards.spells;

import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class Flamestrike extends Spell implements AOESpell {

	
	public Flamestrike()
	{
		super("Flamestrike",7,Rarity.BASIC);
	}
	
	public void performAction(ArrayList<Minion> oppField,ArrayList<Minion> curField) {
		for(Minion i:oppField){
			int newHP=(i.getCurrentHP()-4);
			i.setCurrentHP(newHP);	
			if(i.getCurrentHP()==0){
				oppField.remove(i);
			}
		}
		
	}
	
}
