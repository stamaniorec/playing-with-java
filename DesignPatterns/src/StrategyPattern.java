import java.util.ArrayList;


public class StrategyPattern {

	public static void main(String[] args) {
		
		FightingCharacter king = new King();
		FightingCharacter warrior = new Warrior();
		FightingCharacter burglar = new Burglar();
		
		ArrayList<FightingCharacter> list = new ArrayList<FightingCharacter>();
		list.add(king);
		list.add(warrior);
		list.add(burglar);
		
		for(FightingCharacter character : list) {
			character.fight();
		}
		
	}
	
}

abstract class GameCharacter {
	
	protected int x;
	protected int y;
	
	GameCharacter() {
		x = y = 0;
	}
	
}

abstract class FightingCharacter extends GameCharacter {
	
	protected int damage;
	FightingMethod fightMethod;
	
	FightingCharacter() {
		damage = 0;
	}
	
	public void fight() {
		fightMethod.fight();
		System.out.println("Damage: " + damage);
	}
}

interface FightingMethod {
	void fight();
}

class FightWithKnife implements FightingMethod {
	public void fight() {
		System.out.print("Attacks with knife. ");
	}
}

class FightWithSword implements FightingMethod {
	public void fight() {
		System.out.print("Attacks with sword. ");
	}
}

class King extends FightingCharacter {
	
	public King() { 
		damage = 5;
		fightMethod = new FightWithKnife();
	}
	
	public void fight() {
		System.out.println("King attacking.");
		super.fight();
	}
	
}

class Warrior extends FightingCharacter {
	
	public Warrior() {
		damage = 15;
		fightMethod = new FightWithSword();
	}
	
	public void fight() {
		System.out.println("Warrior attacking.");
		super.fight();
	}
}

class Burglar extends FightingCharacter {
	
	public Burglar() {
		damage = 13;
		fightMethod = new FightWithSword();
	}
	
	public void fight() {
		System.out.println("Burglar attacking.");
		super.fight();
	}
}

class InnKeeper extends GameCharacter {
	
	
	
}