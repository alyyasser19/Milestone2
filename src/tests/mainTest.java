package tests;
import java.io.IOException;

import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.Spell;
import model.heroes.*;

public class mainTest {

	public static void main(String[] args) throws IOException {
		Hero a=  new Hunter();
		System.out.println(a.getDeck());
		Card ab = new Minion("aly", 1, Rarity.LEGENDARY, 10, 10, false, true, true);
System.out.println(ab instanceof Minion);
	}

}
