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
import model.cards.spells.LevelUp;
import model.cards.spells.SealOfChampions;

public class Paladin extends Hero {
	@Override
	public void useHeroPower() throws NotEnoughManaException, HeroPowerAlreadyUsedException, NotYourTurnException,
			FullHandException, FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
		Minion recruit = new Minion("Silver Hand Recruit", 1, Rarity.BASIC, 1, 1, false, false, false);
		recruit.setListener(this);
		if(getField().size()==7)
			throw new FullFieldException();
		else {
			for(Minion m: getField()) {
				if(m.getName().equals("Silver Hand Recruit")) {
					getField().add(recruit.clone());
					return;}}
			getField().add(recruit);
		}}

	public Paladin() throws IOException, CloneNotSupportedException
	{
		super("Uther Lightbringer");
	}
	
	@Override
	public void buildDeck() throws IOException {
		ArrayList<Minion> neutrals= getNeutralMinions(getAllNeutralMinions("neutral_minions.csv"),15);
		getDeck().addAll(neutrals);
		for(int i = 0 ; i < 2; i++)
		{
			getDeck().add(new SealOfChampions());
			getDeck().add(new LevelUp());
		}
		Minion tirion=new Minion("Tirion Fordring", 4, Rarity.LEGENDARY, 6, 6, true, true, false);
	
		getDeck().add(tirion);
		Collections.shuffle(getDeck());
	}
	
}
