package model.heroes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.NotEnoughManaException;
import exceptions.NotYourTurnException;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.CurseOfWeakness;
import model.cards.spells.SiphonSoul;
import model.cards.spells.TwistingNether;

public class Warlock extends Hero {

	@Override
	public void useHeroPower() throws NotEnoughManaException, HeroPowerAlreadyUsedException, NotYourTurnException,
			FullHandException, FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
		this.setCurrentHP(this.getCurrentHP()-2);
		this.drawCard();
	 	for(Minion m: this.getField()) {
			if(m.getName().equals("Wilfred Fizzlebang")){
				if(this.getHand().get(this.getHand().size()-1) instanceof Minion)
				this.getHand().get((this.getHand().size()-1)).setManaCost(0);
				for(Minion mm: this.getField()) {
					if(mm.getName().equals("Chromaggus")) {
						if(this.getHand().get(this.getHand().size()-2) instanceof Minion)
							this.getHand().get((this.getHand().size()-2)).setManaCost(0);
					}
				}
				}
			}

		

	}

	public Warlock() throws IOException, CloneNotSupportedException {
		super("Gul'dan");
	}

	@Override
	public void buildDeck() throws IOException {
		ArrayList<Minion> neutrals= getNeutralMinions(getAllNeutralMinions("neutral_minions.csv"),13);
		getDeck().addAll(neutrals);
		for(int i = 0 ; i < 2; i++)
		{
			getDeck().add(new CurseOfWeakness());
			getDeck().add(new SiphonSoul());
			getDeck().add(new TwistingNether());
		}
		Minion wilfred=new Minion("Wilfred Fizzlebang",6,Rarity.LEGENDARY,4,4,false,false,false);
		getDeck().add(wilfred);
		Collections.shuffle(getDeck());

	}
	

}
