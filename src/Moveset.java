import java.util.Random;

public class Moveset {
	Random random = new Random();

	public void calculateDamage(Pokemon attacker, Pokemon defender, int variance, float maxDamage){
		defender.setHP((int)(defender.getHP()-Math.round((-random.nextFloat()*variance)*defender.getDefense()+(attacker.getLevel()*maxDamage)*attacker.getAttack())));
	}

	public void lowerDefense(Pokemon defender, int stages){
		defender.setDefense((float)(defender.getDefense()*Math.pow(1.37, stages)*(0.25*defender.getLevel())));
	}
	
	public void lowerAttack(Pokemon defender, int stages){
		defender.setAttack((float)(defender.getAttack()*Math.pow(1.37, stages)*(0.25f*defender.getLevel())));
	}
	
	public void tackle(Pokemon attacker, Pokemon defender){
		System.out.println("The "+attacker.getWildEnemy()+" "+attacker.getPokeType()+" Tackles you!");
		calculateDamage(attacker, defender, 1, 1.8124f);
	}
	
	public void tailWhip(Pokemon attacker, Pokemon defender){
		System.out.println("The "+attacker.getWildEnemy()+" "+attacker.getPokeType()+" Whips its Tail around!");
		System.out.println("It's so cute, you lower your defense!");
		lowerDefense(defender, 1);
	}

	public String squishy(Pokemon squishy, Pokemon defender){
		if (squishy.getHP()>0){
			int moveChance = random.nextInt(4);
			if (moveChance<2){
				if (defender.getPlayer()){
					System.out.println("Squishy blows a few Bubbles at you!");
				}
				else {
					System.out.println("Squishy blows a few Bubbles at the "+ defender.getWildEnemy()+" "+defender.getPokeType() +"!");
				}
				defender.setHP(defender.getHP()-Math.round(-random.nextFloat()*2f*squishy.getAttack() + 8f*defender.getDefense()));
			}
			else if (moveChance==2){
				tackle(squishy, defender);
			}
			else {
				tailWhip(squishy, defender);
			}
		}
		return "progressing";
	}

	public String pidgey(Pokemon pidgey, Pokemon defender){
		if (pidgey.getHP()>0){
			int moveSet = random.nextInt(4);
			if (moveSet<3) {
				tackle(pidgey, defender);
			}
			else {
				System.out.println("The wild Pidgey Attacks you with Sand!");
				System.out.println("This effectively does nothing besides annoy you.");
			}

		}
		return "progressing";
	}

	public String hoothoot(Pokemon hoothoot, Pokemon defender){
		if (hoothoot.getHP()>0){
			int moveSet = random.nextInt(4);
			if (moveSet==0) {
				tackle(hoothoot, defender);
				return "progressing";
			} else if (moveSet==1){
				System.out.println("The wild Hoothoot uses Foresight!");
				System.out.println("It examines you carefully and pokes around a bit with its beak.");
				System.out.println("This effectively does nothing.");
				return "progressing";
			} else if (moveSet==2){
				System.out.println("The wild Hoothoot Growls and spreads its wings threateningly!");
				System.out.println("You're a little intimidated.");
				lowerAttack(defender, 1);
				return "progressing";
			} else if (moveSet==3){
				System.out.println("The wild Hoothoot uses Hypnosis!");
				int sleepChance = random.nextInt(10);
				if (sleepChance<=6){
					return "sleep";
				} else if (sleepChance>6){
					System.out.println("It misses!");
				}
			}
		}
		return "progressing";
	}

	public String rattata(Pokemon rattata, Pokemon defender){
		if (rattata.getHP()>0){
			int moveSet = random.nextInt(3);
			if (moveSet==0) {
				tackle(rattata, defender);
			} else if (moveSet==1){
				tailWhip(rattata, defender);
			} else if (moveSet==2){
				System.out.println("The wild Rattata Quickly Attacks you!");
				defender.setHP(defender.getHP() - Math.round(-random.nextInt(Math.round(2f*rattata.getAttack())) + 2f*defender.getDefense()));
				calculateDamage(rattata, defender, 1, 1.9f);
			}
		}
		return "progressing";
	}

}
