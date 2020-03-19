package engine;

import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import model.cards.Card;
import model.cards.minions.Minion;
import model.heroes.Hero;
import model.heroes.HeroListener;

public class Game implements ActionValidator, HeroListener  {
	private Hero firstHero;
	private Hero secondHero;
	private Hero currentHero;
	private Hero opponent;
	
	public Game(Hero p1, Hero p2)
	{
		firstHero=p1;
		secondHero=p2;
		
		int coin = (int) (Math.random()*2);
		currentHero= coin==0?firstHero:secondHero;
		opponent= currentHero==firstHero?secondHero:firstHero;
		currentHero.setCurrentManaCrystals(1);
		currentHero.setTotalManaCrystals(1);
		
	}

	public Hero getCurrentHero() {
		return currentHero;
	}

	public Hero getOpponent() {
		return opponent;
	}

	@Override
	public void validateTurn(Hero user) throws NotYourTurnException {
		if(getCurrentHero()!=user)
			throw new NotYourTurnException("This is not your turn!");
		
	}

	@Override
	public void validateAttack(Minion attacker, Minion target)
			throws CannotAttackException, NotSummonedException, TauntBypassException, InvalidTargetException {
		for(Minion a : opponent.getField()) {
			if(a.isTaunt() && !target.isTaunt())
				throw new TauntBypassException("A minion with taunt is in the way!");
		}
		if(attacker.getAttack()==0)
			throw new CannotAttackException("your minion has 0 attack, are you mad!!!");
		if(attacker.isSleeping())
			throw new CannotAttackException("Not this turn, Minion is sleeping ZzZzZ");
		if(attacker.isAttacked())
			throw new CannotAttackException("This Minion can't attack anymore,Wait till your next turn");
		if(!currentHero.getField().contains(attacker))
			throw new NotSummonedException("Nice Try....");
		if(currentHero.getField().contains(target))
			throw new InvalidTargetException("What exactly are you trying to do here..? The enemy is over there..");
		
		
	}

	@Override
	public void validateAttack(Minion attacker, Hero target)
			throws CannotAttackException, NotSummonedException, TauntBypassException, InvalidTargetException {
		if(attacker.getAttack()==0)
			throw new CannotAttackException("your minion has 0 attack, are you mad!!!");
		if(attacker.isSleeping())
			throw new CannotAttackException("Not this turn, Minion is sleeping ZzZzZ");
		if(attacker.isAttacked())
			throw new CannotAttackException("This Minion can't attack anymore,Wait till your next turn");
		if(!currentHero.getField().contains(attacker))
			throw new NotSummonedException("Nice Try....");
		if(target.equals(currentHero))
			throw new InvalidTargetException("What exactly are you trying to do here..? The enemy is over there..");
		
	}

	@Override
	public void validateManaCost(Card card) throws NotEnoughManaException {
		if(card.getManaCost()>currentHero.getCurrentManaCrystals())
			throw new NotEnoughManaException("Not Enough Mana");
		
	}

	@Override
	public void validatePlayingMinion(Minion minion) throws FullFieldException {
		if(currentHero.getField().size()==7)
			throw new FullFieldException("Your Field is full!!");
		
	}

	@Override
	public void validateUsingHeroPower(Hero hero) throws NotEnoughManaException, HeroPowerAlreadyUsedException {
		if(hero.isHeroPowerUsed())
			throw new HeroPowerAlreadyUsedException("already used that this turn");
		if(hero.getCurrentManaCrystals()<2)
			throw new NotEnoughManaException("Not enough mana");
		
	}

	@Override
	public void onHeroDeath() {
		if(currentHero.getCurrentHP()==0 || opponent.getCurrentHP()==0)
			System.out.println("do nothing for now");//still not implemented
		
	}

	@Override
	public void damageOpponent(int amount) {
		opponent.setCurrentHP(opponent.getCurrentHP()-amount);
		
	}

	@Override
	public void endTurn() throws FullHandException, CloneNotSupportedException {
		if(firstHero==currentHero) {
			opponent=firstHero;
			currentHero=secondHero;
		}else {
			currentHero=firstHero;
			opponent=secondHero;
		}
			currentHero.setTotalManaCrystals(currentHero.getTotalManaCrystals()+1);
			currentHero.setCurrentManaCrystals(currentHero.getTotalManaCrystals());
			currentHero.setHeroPowerUsed(false);
			for(Minion a:currentHero.getField()) {
				a.setAttacked(false);
				a.setSleeping(false);}
			//Draw a card to be added
			
		
	}

	
	
	

}
