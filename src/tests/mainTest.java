package tests;


import java.io.IOException;

import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.Flamestrike;
import model.cards.spells.Spell;
import model.heroes.*;

public class mainTest {

	public static void main(String[] args) throws IOException {
		Hero b= new Paladin();
		Hero a=  new Hunter();
		a.getField().add(new Minion("Kalycgos", 1, Rarity.COMMON, 1, 4, false, true, false));
		Flamestrike s= new Flamestrike(); 
		 for(Minion me: a.getField()) {
			 if(me.getName().equals("Kalycgos"))
			((Card)s).setManaCost(((Card)s).getManaCost()-4);
		 }
		System.out.println(s.getManaCost());
	}

}
