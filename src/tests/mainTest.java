package tests;


import java.io.IOException;

import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Icehowl;
import model.cards.minions.Minion;
import model.cards.spells.Flamestrike;
import model.cards.spells.Spell;
import model.heroes.*;

public class mainTest {

	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		Hero b= new Paladin();
		Hero a=  new Hunter();
		a.getField().add(new Minion("Kalycgos", 1, Rarity.COMMON, 1, 4, false, false, false));
		a.getField().add(new Icehowl());
		Flamestrike s= new Flamestrike(); 
		 s.performAction(a.getField(), b.getField());
		 a.getField().get(0).setCurrentHP(1);
		System.out.println(a.getField());
	}

}
