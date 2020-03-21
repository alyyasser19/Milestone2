package model.heroes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.NotEnoughManaException;
import exceptions.NotYourTurnException;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.DivineSpirit;
import model.cards.spells.HolyNova;
import model.cards.spells.ShadowWordDeath;

public class Priest extends Hero {

	public Priest() throws IOException {
		super("Anduin Wrynn");
	}

	@Override
	public void buildDeck() throws IOException {
		ArrayList<Minion> neutrals= getNeutralMinions(getAllNeutralMinions("neutral_minions.csv"),13);
		getDeck().addAll(neutrals);
		for(int i = 0 ; i < 2; i++)
		{
			getDeck().add(new DivineSpirit());
			getDeck().add(new HolyNova());
			getDeck().add(new ShadowWordDeath());
		}
		Minion velen=new Minion("Prophet Velen", 7, Rarity.LEGENDARY, 7, 7, false, false, false);
		
		getDeck().add(velen);
		Collections.shuffle(getDeck());

	}
	public void useHeroPower(Object target) throws NotEnoughManaException, HeroPowerAlreadyUsedException, NotYourTurnException,
			FullHandException, FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
		int heal= 2;
		for(Minion M: this.getField()) {
			if(M.getName().equals("Prophet Velen"))
				heal=8;
		}
		if(target instanceof Minion) {
			Minion m= (Minion)target;
				m.setCurrentHP(getCurrentHP()+heal);
									 }
		if(target instanceof Hero) {
			Hero h = (Hero) target;
			h.setCurrentHP(getCurrentHP()+heal);
	}
		}
	

}
