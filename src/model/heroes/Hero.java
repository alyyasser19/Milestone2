package model.heroes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import engine.ActionValidator;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Icehowl;
import model.cards.minions.Minion;
import model.cards.minions.MinionListener;
import model.cards.spells.AOESpell;
import model.cards.spells.FieldSpell;
import model.cards.spells.HeroTargetSpell;
import model.cards.spells.LeechingSpell;
import model.cards.spells.MinionTargetSpell;
import exceptions.*;

public abstract class Hero implements MinionListener{
	private String name;
	private int currentHP;
	private boolean heroPowerUsed;
	private int totalManaCrystals;
	private int currentManaCrystals;
	private ArrayList<Card> deck;
	private ArrayList<Minion> field;
	private ArrayList<Card> hand;
	private static int fatigueDamage=0;
	private HeroListener listener;
	private ActionValidator validator;

	public HeroListener getListener() {
		return listener;
	}
	public void setListener(HeroListener listener) {
		this.listener = listener;
	}
	public void setValidator(ActionValidator validator) {
		this.validator = validator;
	}
	public Hero(String name) throws IOException, CloneNotSupportedException {
		this.name = name;
		currentHP = 30;
		deck = new ArrayList<Card>();
		field = new ArrayList<Minion>();
		hand = new ArrayList<Card>();
		buildDeck();
		for(Card a: deck) 
			if(a instanceof Minion)
				((Minion) a).setListener(this);	
		}

	public void useHeroPower() throws NotEnoughManaException,
	HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException,
	FullFieldException, CloneNotSupportedException{
		validator.validateTurn(this);
		validator.validateUsingHeroPower(this);
		currentManaCrystals=currentManaCrystals-2;
		heroPowerUsed=true;
	}
	public void playMinion(Minion m) throws NotYourTurnException,
	NotEnoughManaException, FullFieldException{
		validator.validateTurn(this);
		validator.validateManaCost((Card)m);
		validator.validatePlayingMinion(m);
		getHand().remove(m);
		getField().add(m);
	}
	public void attackWithMinion(Minion attacker, Minion target) throws
	CannotAttackException, NotYourTurnException, TauntBypassException,
	InvalidTargetException, NotSummonedException{
		validator.validateTurn(this);
		validator.validateAttack(attacker, target);
		attacker.attack(target);

	}
	 public void attackWithMinion(Minion attacker, Hero target) throws
	 CannotAttackException, NotYourTurnException, TauntBypassException,
	 NotSummonedException, InvalidTargetException{
			validator.validateTurn(this);
			validator.validateAttack(attacker, target);
			attacker.attack(target);
	 }
	 @SuppressWarnings("unlikely-arg-type")
	public void castSpell(FieldSpell s) throws NotYourTurnException,
	 NotEnoughManaException{
		 validator.validateTurn(this);
		 for(Minion me: this.getField()) {
			 if(me.getName().equals("Kalycgos") && this instanceof Mage)
			((Card)s).setManaCost(((Card)s).getManaCost()-4);
		 }
		 validator.validateManaCost((Card)s);
		 s.performAction(this.getField());
		 currentManaCrystals=currentManaCrystals-((Card)s).getManaCost();
		 hand.remove(s);
		 
	 }
	 @SuppressWarnings("unlikely-arg-type")
	public void castSpell(AOESpell s, ArrayList<Minion >oppField) throws
	 NotYourTurnException, NotEnoughManaException{
		 validator.validateTurn(this);
		 for(Minion me: this.getField()) {
			 if(me.getName().equals("Kalycgos") && this instanceof Mage)
			((Card)s).setManaCost(((Card)s).getManaCost()-4);
		 }
		 validator.validateManaCost((Card)s);
		 s.performAction(oppField, this.getField());
		 currentManaCrystals=currentManaCrystals-((Card)s).getManaCost();
		 hand.remove(s);
	 }
	 @SuppressWarnings("unlikely-arg-type")
	public void castSpell(MinionTargetSpell s, Minion m) throws NotYourTurnException,
	 NotEnoughManaException, InvalidTargetException{
		 validator.validateTurn(this);
		 for(Minion me: this.getField()) {
			 if(me.getName().equals("Kalycgos") && this instanceof Mage)
			((Card)s).setManaCost(((Card)s).getManaCost()-4);
		 }
		 validator.validateManaCost((Card)s);
		 s.performAction(m);
		 currentManaCrystals=currentManaCrystals-((Card)s).getManaCost();
		 hand.remove(s);
	 }
	 @SuppressWarnings("unlikely-arg-type")
	public void castSpell(HeroTargetSpell s, Hero h) throws NotYourTurnException,
	 NotEnoughManaException{
		 validator.validateTurn(this);
		 for(Minion me: this.getField()) {
			 if(me.getName().equals("Kalycgos") && this instanceof Mage)
			((Card)s).setManaCost(((Card)s).getManaCost()-4);
		 }
		 validator.validateManaCost((Card)s);
		 s.performAction(h);
		 currentManaCrystals=currentManaCrystals-((Card)s).getManaCost();
		 hand.remove(s);
	 }
	 @SuppressWarnings("unlikely-arg-type")
	public void castSpell(LeechingSpell s, Minion m) throws NotYourTurnException,
	 NotEnoughManaException{
		 validator.validateTurn(this);
		 for(Minion me: this.getField()) {
			 if(me.getName().equals("Kalycgos") && this instanceof Mage)
			((Card)s).setManaCost(((Card)s).getManaCost()-4);
		 }
		 validator.validateManaCost((Card)s);
		 int x=s.performAction(m);
		 currentHP=currentHP+x;
		 currentManaCrystals=currentManaCrystals-((Card)s).getManaCost();
		 hand.remove(s);
	 }
	 
	 public void endTurn() throws FullHandException, CloneNotSupportedException{
		 listener.endTurn();
	 }
	 public Card drawCard() throws FullHandException, CloneNotSupportedException{
		 if(getHand().size()==10) {
			 throw new FullHandException(getDeck().remove(0));
		 }
		 if(getDeck().isEmpty()) {
			 if(fatigueDamage==0) {
				setCurrentHP(getCurrentHP()-1);
				fatigueDamage=1;
				return null;
			 }setCurrentHP(getCurrentHP()-fatigueDamage);
			 fatigueDamage=fatigueDamage+1;
			 return null;
		 }
		 this.hand.add(getDeck().get(0));

		 if(hand.size()!=10)
		 	for(Minion m: this.getField()) {
				if(m.getName().equals("Chromaggus")){
					this.getHand().add(this.getHand().get(this.getHand().size()-1).clone());
					}
				}
			 return getDeck().remove(0);
			 
	 }
	public abstract void buildDeck() throws IOException, CloneNotSupportedException;
	public void onMinionDeath(Minion m) {
		this.getField().remove(m);
	}

	public static final ArrayList<Minion> getAllNeutralMinions(String filePath) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		ArrayList<Minion> minions = new ArrayList<Minion>();
		String current = br.readLine();
		while (current != null) {
			String[] line = current.split(",");
			Minion minion = null;
			String n = line[0];
			int m = Integer.parseInt(line[1]);
			Rarity r = null;
			switch (
				(line[2])
			) {
			case "b":
				r = Rarity.BASIC;
				break;
			case "c":
				r = Rarity.COMMON;
				break;
			case "r":
				r = Rarity.RARE;
				break;
			case "e":
				r = Rarity.EPIC;
				break;
			case "l":
				r = Rarity.LEGENDARY;
				break;
			}
			int a = Integer.parseInt(line[3]);
			int p = Integer.parseInt(line[4]);
			boolean t = line[5].equals("TRUE") ? true : false;
			boolean d = line[6].equals("TRUE") ? true : false;
			boolean c = line[7].equals("TRUE") ? true : false;
			if (!n.equals("Icehowl"))
				minion = new Minion(n, m, r, a, p, t, d, c);
			else
				minion = new Icehowl();
			minions.add(minion);
			current = br.readLine();
		}
		br.close();
		return minions;
	}

	public static final ArrayList<Minion> getNeutralMinions(ArrayList<Minion> minions, int count) {
		ArrayList<Minion> res = new ArrayList<Minion>();
		int i = 0;
		while (i < count) {
			
			int index = (int) (Math.random() * minions.size());
			Minion minion = minions.get(index);
			int occ = 0;
			for (int j = 0; j < res.size(); j++) {
				if (res.get(j).getName().equals(minion.getName()))
					occ++;
			}
			if (occ == 0)
			{
				if(res.contains(minion)) {
					try {
						res.add(minion.clone());
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
					i++;
				}else {
				res.add(minion);
				i++;}
			}
			else if(occ==1 && minion.getRarity()!=Rarity.LEGENDARY)
			{
				if(res.contains(minion)) {
					try {
						res.add(minion.clone());
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
					i++;
				}else {
				res.add(minion);
				i++;}
			}
		}
		return res;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int hp) {
		this.currentHP = hp;
		if (this.currentHP > 30)
			this.currentHP = 30;
		else if (this.currentHP <= 0) {
			this.currentHP = 0;
			listener.onHeroDeath();
			
		}
	}

	public int getTotalManaCrystals() {
		return totalManaCrystals;
	}

	public void setTotalManaCrystals(int totalManaCrystals) {
		this.totalManaCrystals = totalManaCrystals;
		if (this.totalManaCrystals > 10)
			this.totalManaCrystals = 10;
	}

	public int getCurrentManaCrystals() {
		return currentManaCrystals;
	}

	public void setCurrentManaCrystals(int currentManaCrystals) {
		this.currentManaCrystals = currentManaCrystals;
		if (this.currentManaCrystals > 10)
			this.currentManaCrystals = 10;
	}

	public ArrayList<Minion> getField() {
		return field;
	}

	

	public ArrayList<Card> getHand() {
		return hand;
	}

	public boolean isHeroPowerUsed() {
		return heroPowerUsed;
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public void setHeroPowerUsed(boolean powerUsed) {
		this.heroPowerUsed = powerUsed;
	}

	public String getName() {
		return name;
	}
}
