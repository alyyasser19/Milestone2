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
		int i=0;
		int x= oppField.size();
		if(!oppField.isEmpty())
		while(i<x) {
			int cur = oppField.get(i).getCurrentHP();
			if(!oppField.get(i).isDivine()) {
			if(cur-4<=0) {
				oppField.get(i).setCurrentHP(0);
				x=oppField.size();
			}
			else {
				oppField.get(i).setCurrentHP(oppField.get(i).getCurrentHP()-4);
				i++;
			}}
			else {
				oppField.get(i).setDivine(false);
				i++;
			}
		}
		}
		
	}
	
