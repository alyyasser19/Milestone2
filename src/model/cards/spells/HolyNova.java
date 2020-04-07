package model.cards.spells;

import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class HolyNova extends Spell implements AOESpell {

	public HolyNova() {
		super("Holy Nova", 5, Rarity.BASIC);
	
	}
	
	public void performAction(ArrayList<Minion> oppField,ArrayList<Minion> curField) {
		if(!oppField.isEmpty()){
			int x=oppField.size();
			int i=0;
			while(i<x){
				if(!oppField.get(i).isDivine()) 
				{
				if(oppField.get(i).getCurrentHP()<=2){
					oppField.remove(i);
					x=oppField.size();
					}
				else{
					oppField.get(i).setCurrentHP(oppField.get(i).getCurrentHP()-2);
					i++;
					}
				
				}
				else
				{
					oppField.get(i).setDivine(false);
					i++;
				}
				}
		}
		if(!curField.isEmpty()){
		for(int i=0;i<curField.size();i++) {
				curField.get(i).setCurrentHP(curField.get(i).getCurrentHP()+2);
		}
		
	}

}
	}
