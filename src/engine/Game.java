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
	private GameListener listener;
	
	public Game(Hero p1, Hero p2) throws FullHandException, CloneNotSupportedException
	{
		p1.setListener(this);
		p1.setValidator(this);
		p2.setListener(this);
		p2.setValidator(this);
		
		firstHero=p1;
		secondHero=p2;
		
		int coin = (int) (Math.random()*2);
		currentHero= coin==0?firstHero:secondHero;
		opponent= currentHero==firstHero?secondHero:firstHero;
		currentHero.setCurrentManaCrystals(1);
		currentHero.setTotalManaCrystals(1);
		for(int i=0;i<3;i++)
		currentHero.getHand().add(currentHero.drawCard());
		for(int i=0;i<4;i++)
			opponent.getHand().add(opponent.drawCard());
		
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
			throw new NotYourTurnException();
		
	}

	@Override
	public void validateAttack(Minion attacker, Minion target)
			throws CannotAttackException, NotSummonedException, TauntBypassException, InvalidTargetException {
		for(Minion a : opponent.getField()) {
			if(a.isTaunt() && !target.isTaunt())
				throw new TauntBypassException();
		}
		if(attacker.getAttack()==0)
			throw new CannotAttackException();
		if(attacker.isSleeping())
			throw new CannotAttackException();
		if(attacker.isAttacked())
			throw new CannotAttackException();
		if(!currentHero.getField().contains(attacker))
			throw new NotSummonedException();
		if(currentHero.getField().contains(target))
			throw new InvalidTargetException();
		if(!opponent.getField().contains(target))
			throw new NotSummonedException();
		
		
	}

	@Override
	public void validateAttack(Minion attacker, Hero target)
			throws CannotAttackException, NotSummonedException, TauntBypassException, InvalidTargetException {
		for(Minion a : opponent.getField()) {
			if(a.isTaunt())
				throw new TauntBypassException();
		}
		if(attacker.getAttack()==0)
			throw new CannotAttackException();
		if(attacker.isSleeping())
			throw new CannotAttackException();
		if(attacker.isAttacked())
			throw new CannotAttackException();
		if(!currentHero.getField().contains(attacker))
			throw new NotSummonedException();
		if(target.equals(currentHero))
			throw new InvalidTargetException();
		if(attacker.getName().equals("Icehowl"))
			throw new InvalidTargetException();
	}

	public void setListener(GameListener listener) {
		this.listener = listener;
	}

	@Override
	public void validateManaCost(Card card) throws NotEnoughManaException {
		if(card.getManaCost()>currentHero.getCurrentManaCrystals())
			throw new NotEnoughManaException();
		
	}

	@Override
	public void validatePlayingMinion(Minion minion) throws FullFieldException {
		if(currentHero.getField().size()==7)
			throw new FullFieldException();
		
	}

	@Override
	public void validateUsingHeroPower(Hero hero) throws NotEnoughManaException, HeroPowerAlreadyUsedException {
		if(hero.isHeroPowerUsed())
			throw new HeroPowerAlreadyUsedException();
		if(hero.getCurrentManaCrystals()<2)
			throw new NotEnoughManaException();
		
	}

	@Override
	public void onHeroDeath() {
			listener.onGameOver();
		
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
			getCurrentHero().drawCard();
			for(Minion a:currentHero.getField()) {
				a.setAttacked(false);
				a.setSleeping(false);
				}
			
		
	}

	
	
	

}
