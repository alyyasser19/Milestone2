package tests;

import java.io.IOException;

import engine.Game;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.NotEnoughManaException;
import exceptions.NotYourTurnException;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.heroes.Hero;
import model.heroes.Mage;
import model.heroes.Paladin;
import model.heroes.Priest;
import engine.Game;
import model.cards.spells.AOESpell;
import model.cards.spells.FieldSpell;
import model.cards.spells.Flamestrike;
import model.cards.spells.Spell;

public class tests {

	public static void main(String[] args) throws IOException, CloneNotSupportedException, FullHandException, NotYourTurnException, NotEnoughManaException, HeroPowerAlreadyUsedException, FullFieldException {
		Hero a= new Paladin();
		Hero b= new Mage();
		Priest c= new Priest();
		a.getField().add(new Minion("Chromaggus", 1, Rarity.BASIC, 1, 11, false, false, false));
		b.getField().add(new Minion("Kalycgos", 1, Rarity.BASIC, 1, 11, false, false, false));
		c.getField().add(new Minion("Prophet Velen", 1, Rarity.BASIC, 1, 11, false, false, false));
		b.getHand().add(new Flamestrike());
		Game start= new Game(a, c);
		//b.setTotalManaCrystals(5);
		//b.setCurrentManaCrystals(5);
		//b.castSpell((AOESpell)b.getHand().get(0), a.getField());
		//System.out.println(a.getHand());
		c.setCurrentHP(10);
		c.setTotalManaCrystals(2);
		c.setCurrentManaCrystals(2);
		c.useHeroPower(c);
		System.out.println(c.getCurrentHP());
		
	}

}
