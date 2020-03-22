package model.cards.spells;

import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class TwistingNether extends Spell implements AOESpell {

	public TwistingNether() {
		super("Twisting Nether", 8, Rarity.EPIC);

	}

	public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) {
		boolean flag=false;
		for(Minion i:oppField){
			if(i.isDivine()){
				flag=true;
				break;}
								}
		if(!flag){
			for(Minion j:curField){
				if(j.isDivine()){
					flag=true;
					break;}
		}
		if(flag){
			oppField.clear();
			curField.clear();
			
		}
		}
		}
	}
		
	


