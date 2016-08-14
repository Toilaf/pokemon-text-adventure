import java.util.Scanner;
import java.util.Random;
import java.lang.Math;

public class MainClass {

	public static float defense;
	public static float attack;
	
	public static void main(String[] args) {
		
	//TODO check for bugs	
	//TODO maybe use an accuracy stat so the wild pidgey isn't as easy to beat?
	//TODO convert all pokemon (player, squishy, pidgeyRt1, rattataRt1, hoothootRt1) to pokemon objects
	//make declareHealth a method after you convert pokemon to pokemon objects
	//TODO make sure that every instance of checkFriendship has a true or false parameter as needed
		
	Scanner input = new Scanner (System.in);
	Random random = new Random();
	
	Testers test = new Testers();
	
	Story story = new Story();
	story.trigWarn();
	Pokemon player = new Pokemon();
	story.intro(player);
	
	System.out.println("\nUnfortunately, you're hanging out with a friend when a Pokemon trainer sneaks up on you.");
	System.out.println("They throw out a Pokeball! \"Squishy, go!\" A Squirtle pops out!");
	System.out.println("It looks unsurely at the trainer before focusing on you.");
	
	//minibattle
	double friend = 1;
	Pokemon squishy = new Pokemon();
	squishy.setMaxHP(13);
	squishy.setHP(squishy.getMaxHP());
	player.setHP(player.getMaxHP());
	//defense is a float between 0 and 1 that determines the amount of damage the player takes
	//ref: use Math.ceil(), Math.floor(), or Math.round() to round doubles
	defense = 1;
	attack = 1;
	
	while (player.getHP()>0&&squishy.getHP()>0){
		System.out.println("\nDo you: run away, fight back, defend, or use Struggle?");
		String actionOne = input.nextLine().toLowerCase();
		actionOne = checkResponse(actionOne, "run", "fight", "defend", "struggle", "empty case");
		if (actionOne.contains("run")){
			boolean runSuccess = random.nextBoolean();
			if (runSuccess){
				System.out.println("You dart into the nearest tree!");
				System.out.println("Congrats, you got an ending! Your friend got scared and ran away too, but at least you're not in a Pokeball!");
				friend = friend * 0.05;
				break;
			} else if (!runSuccess) {
				System.out.println("You fly for the nearest tree, but Squishy catches you by the foot!");
				System.out.println("Squishy blows a few bubbles!");
				player.setHP(player.getHP() - Math.round(-random.nextInt(2) + 3f*defense));
			}
		}
		else {
			if (actionOne.contains("fight")&&squishy.getHP()>0){
				System.out.println("You Tackle the enemy Squirtle!");
				squishy.setHP(squishy.getHP() - Math.round(-random.nextInt(2) + 3f*attack));
				if (squishy.getHP()>0){
					System.out.println("Squishy blows a few bubbles at you!");
					player.setHP(player.getHP() - Math.round(-random.nextInt(2) + 3f*defense));
				}
			} else if (actionOne.contains("defend")&&squishy.getHP()>0){
				System.out.println("You curl up in a tight Defense Curl!");
				defense = 0.73f*defense;
				System.out.println("Squishy blows a few bubbles at you! It does less damage now.");
				player.setHP(player.getHP() - Math.round(-random.nextInt(2) + 3f*defense));
			} else if ((actionOne.contains("struggle")&&squishy.getHP()>0)){
				System.out.println("You flail around. Your wings bash into the ground awkwardly and a feather falls off.");
				System.out.println("The trainer laughs at you.");
				squishy.setHP(squishy.getHP() - Math.round(1f*attack));
				if (squishy.getHP()>0){
					System.out.println("Squishy blows a few bubbles at you!");
					player.setHP(player.getHP() - Math.round(-random.nextInt(2) + 3f*defense));
				}
			}
			if (squishy.getHP()<=0){
				System.out.println("The enemy Squirtle faints!");
				System.out.println("\nThe Pokemon trainer drops several hundred Pokecoins in their haste to carry Squishy to the nearest PokeCenter!");
				System.out.println("This is absolutely useless to you and your lack of opposable thumbs.");
				System.out.println("Congrats, you got an ending! Your friend got scared and ran away, but at least you're not in a Pokeball!");
			}
		}//end else containing every option except run away
		declareHealth(player, squishy);
	}//end while loop
	
	//this keeps the game from continuing when you run away
	System.out.println("\nPress enter to continue your adventure.");
	System.out.println("Unless you won, or you ran away! In which case, only press enter if you want to cheat.");
	String didntRun = input.nextLine().toLowerCase();
	if(!(didntRun.equalsIgnoreCase("run away"))){
		whenNotRanAway(player, friend, input, random);
	}
	
}//main
	
public static void whenNotRanAway(Pokemon player, double friend, Scanner input, Random random){
	//this resets your defense stat
	defense = 1f;
	attack = 1f;
		
	//aftermath
	System.out.println("You faint.");
	System.out.println("\nWhen you wake up, you're in a Pokeball with full health.");
	player.setHP(player.getMaxHP());
	System.out.println("After a few minutes, you realize it's not so bad as you thought. It's actually pretty comfy.");
	System.out.println("After a few hours, you're released! The trainer is right there, though.");
	System.out.println("\n\"Hi there, li'l " + player.getPokeType() + "!\" they say. \"My name's Sam. And your name will be... how about " + player.getName() + "?\"");
	System.out.println("They amazingly get your name right because this is Pokemon. Do you: chirp approvingly, or squawk in annoyance just to be contrary?");
	String nameChoice = input.nextLine().toLowerCase();
	nameChoice = checkResponse(nameChoice, "chirp", "squawk", "empty case", "empty case", "empty case");
	if (nameChoice.contains("chirp")){
		System.out.println("\"Aww, you like that name! " + player.getName() + " it is.\"");
		friend = friend*1.05;
	}
	else if (nameChoice.contains("squawk")){
		System.out.println("\"Oh, that name's no good? How about... Bob?\"");
		System.out.println("(Keep chirping or squawking to answer "+checkFriendship(friend, false)+".)");
		nameChoice = input.nextLine().toLowerCase();
		nameChoice = checkResponse(nameChoice, "chirp", "squawk", "empty case", "empty case", "empty case");
		if (nameChoice.contains("chirp")){
			System.out.println("\"Okay then! Bob it is.\"");
			player.setName("Bob");
		}
		else if (nameChoice.contains("squawk")){
			System.out.println("\"What? Not that one either? What about Katrina?\"");
			nameChoice = input.nextLine().toLowerCase();
			nameChoice = checkResponse(nameChoice, "chirp", "squawk", "empty case", "empty case", "empty case");
			if (nameChoice.contains("chirp")){
				System.out.println("\"Okay then! Tyler it is.\"");
				player.setName("Tyler");
			}
			else if (nameChoice.contains("squawk")){
				System.out.println("\"Really? Not Tyler either? But I have to call you something! What do you think about Dequan?\"");
				nameChoice = input.nextLine().toLowerCase();
				nameChoice = checkResponse(nameChoice, "chirp", "squawk", "empty case", "empty case", "empty case");
				if (nameChoice.contains("chirp")){
					System.out.println("Okay then! Dequan it is.");
					player.setName("Dequan");
				}
				else if (nameChoice.contains("squawk")){
					System.out.println("\"Nothing? Seriously? I'll just... call you " + player.getPokeType() + " for now.\"");
					player.setName(player.getPokeType());
				}
			}
		}
	}//end naming conversation
	
	//trainer goes to pet
	System.out.println("They move to pet your feathers!");
	System.out.println("Do you: allow it, or squirm away?");
	String actionTwo = input.nextLine().toLowerCase();
	actionTwo = checkResponse(actionTwo, "squirm", "allow", "empty case", "empty case", "empty case");
	if (actionTwo.contains("allow")){
		System.out.println("Your feathers have been skritched softly. How fun!");
		System.out.println(checkFriendship(friend, true) +" makes a happy human noise.");
		System.out.println("\n"+checkFriendship(friend, true)+" makes a bunch of human noises which means they're trusting you to walk alongside them for Route 1.\n");
		friend = friend * 1.5;
	} else if (actionTwo.contains("squirm")) {
		friend = friend * 0.85;
		System.out.println(checkFriendship(friend, true)+" moves away. Success!");
		System.out.println("\n"+checkFriendship(friend, true)+" makes a bunch of human noises which means they're trusting you to walk alongside them for Route 1.\n");
	}
	
	//walking along the route
	route(player.getName(), friend, 2);
	
	//2nd pokemon battle
	boolean caught = false;
	boolean sleep = false;
	int potionsUsed = 0;
	int potionsTotal = 3;
	Pokemon enemyOne = new Pokemon();
	enemyOne.setMaxHP(12);
	enemyOne.setHP(enemyOne.getMaxHP());
	//assign random pokemon
	int enemyNumRandom = random.nextInt(3);
	if (enemyNumRandom==0){
		enemyOne.setPokeType("Pidgey");
		enemyOne.setMaxHP(enemyOne.getMaxHP()+2);
	} else if (enemyNumRandom==1){
		enemyOne.setPokeType("Rattata");
	} else if (enemyNumRandom==2) {
		enemyOne.setPokeType("Hoothoot");
	}
	System.out.println("A wild " + enemyOne.getPokeType() + " appears!");
	//here's the battle itself
	while (player.getHP()>0&&enemyOne.getHP()>0&&!caught&&!sleep){
		System.out.println("\nDo you: run away, fight back, defend, use Struggle, or use an item?");
		String actionThree = input.nextLine().toLowerCase();
		actionThree = checkResponse(actionThree, "run", "fight", "defend", "struggle", "item");
		if (actionThree.contains("run")){
			System.out.println("You dart into the nearest tree!");
			System.out.println("Congrats, you got an ending! No more sitting in a Pokeball for you!");
			friend = friend * 0.05;
			break;
		} else {
			if (actionThree.contains("fight")&&enemyOne.getHP()>0) {
				System.out.println("You Tackle the wild " + enemyOne.getPokeType() + "!");
				enemyOne.setHP(enemyOne.getHP() - Math.round(-random.nextInt(2) + 3f*attack));
				if (enemyOne.getHP()>0){
					System.out.println("");
					sleep = enemyAttack(enemyOne.getPokeType(), enemyOne.getHP(), player, friend);
				}
			}//end fight option
			else if (actionThree.contains("defend")) {
				System.out.println("You curl up in a tight Defense Curl!");
				defense = defense*0.73f;
				sleep = enemyAttack(enemyOne.getPokeType(), enemyOne.getHP(), player, friend);
			}//end defend option
			else if (actionThree.contains("struggle")){
				System.out.println("You flail around. Your wings bash into the ground awkwardly and a feather falls off.");
				System.out.println(checkFriendship(friend, true)+" laughs at you.");
				enemyOne.setHP(enemyOne.getHP() - Math.round(1f*attack));
				if (enemyOne.getHP()>0){
					System.out.println("");
					sleep = enemyAttack(enemyOne.getPokeType(), enemyOne.getHP(), player, friend);
				}
			}//end struggle option
			else if (actionThree.contains("item")){
				if (potionsUsed<potionsTotal){
					//you have potions left
					System.out.println("Do you: use a Pokeball, or a potion?");
					String itemChoose = input.nextLine().toLowerCase();
					itemChoose = checkResponse(itemChoose, "pokeball", "potion", "empty case","empty case","empty case");
					if (itemChoose.contains("pokeball")){
						System.out.println(checkFriendship(friend, true)+" throws a Pokeball!");
						float catchChance = 1.0f-(random.nextFloat()*enemyOne.getHP()/12);
						if (catchChance>0.6){
							//you catch it
							System.out.println("Gotcha!");
							System.out.println("The wild " + enemyOne.getPokeType() + " was caught!");
							caught = true;
						}
						else if (catchChance<=0.6){
							//you don't catch it
							System.out.println("Aww, they missed!");
							sleep = enemyAttack(enemyOne.getPokeType(), enemyOne.getHP(), player, friend);
						}
					}//chose pokeball and have potions
					else if (itemChoose.contains("potion")){
						//limit potion use
						System.out.println("You gain 20 health!");
						player.setHP(player.getHP()+20);
						if (player.getHP()>player.getMaxHP()){
							player.setHP(player.getMaxHP());
						}
						potionsUsed = potionsUsed + 1;
						System.out.println("You have " + (potionsTotal-potionsUsed) + " potions left.");
						sleep = enemyAttack(enemyOne.getPokeType(), enemyOne.getHP(), player, friend);
					}//chose potion and have potions
				}//if you have potions
				else if (potionsUsed>=potionsTotal){
					//you have no potions left
					System.out.println("You only have Pokeballs left. Do you want to throw a Pokeball?");
					String ballChoice = input.nextLine().toLowerCase();
					ballChoice = checkResponse(ballChoice, "yes", "no", "empty case", "empty case", "empty case");
					if (ballChoice.contains("yes")){
						System.out.println(checkFriendship(friend, true)+" throws a Pokeball!");
						float catchChance = 1.0f-(random.nextFloat()*enemyOne.getHP()/12);
						if (catchChance>0.6){
							//you catch it
							System.out.println("Gotcha!");
							caught = true;
						}
						else if (catchChance<=0.6){
							//you don't catch it
							System.out.println("Aww, they missed!");
							sleep = enemyAttack(enemyOne.getPokeType(), enemyOne.getHP(), player, friend);
						}
					}//chose pokeball and don't have potions left
					else if (ballChoice.contains("no")){
					}
				}//if you don't have potions left
			}//end chose item
			if (!(caught)&&!(sleep)){
				if (player.getHP()<0){
					player.setHP(0);
				}
			System.out.println("Your health is at " + player.getHP());
			if (enemyOne.getHP()<0){
				enemyOne.setHP(0);
			}
			System.out.println("The wild " + enemyOne.getPokeType() + "'s health is at " + (int)Math.round(((double)enemyOne.getHP()/12.0)*100.0) + "%");
			}
		}//else containing every option except run away
	}//while loop for pokemon battle
	
	//this keeps the game from continuing when you run away (again)
	System.out.println("\nPress enter to continue your adventure.");
	System.out.println("Unless you ran away! In which case, only press enter if you want to cheat.");
	String didntRunAgain = input.nextLine().toLowerCase();
	if(!(didntRunAgain.equalsIgnoreCase("run away"))){
	
	//what the game does based on the result of the battle
	if (player.getHP()<=0&&enemyOne.getHP()>0&&!caught&&!sleep){
		//you lost
		System.out.println("You vaguely register Squishy jumping out to replace you before you pop back into the Pokeball for the duration of the battle.");
		friend = friend*1.25;
	}
	if (enemyOne.getHP()<=0&&player.getHP()>0&&!caught&&!sleep){
		//you won
		System.out.println("You won!");
		System.out.println("You've earned a satisfying rush of EXP.");
		friend = friend*1.5;
		//trainer tries to pet
		System.out.println("\n"+checkFriendship(friend, true)+" moves to pet your feathers excitedly!");
		System.out.println("Do you: allow it, or squirm away?");
		String actionFour = input.nextLine().toLowerCase();
		actionFour = checkResponse(actionFour, "allow", "squirm", "empty case", "empty case", "empty case");
		if (actionFour.contains("allow")){
			System.out.println("Your feathers have been skritched. How fun!");
			System.out.println(checkFriendship(friend, true)+" makes a happy human noise.");
			friend = friend * 1.5;
		} else if (actionFour.contains("squirm")){
			friend = friend * 0.85;
			System.out.println(checkFriendship(friend, true)+" moves away. Winning a battle doesn't mean they get to invade your personal space!");
		}
	}
	if (caught){
		System.out.println("You successfully caught the wild " + enemyOne.getPokeType() + "!");
		friend = friend*1.5;
		//trainer tries to pet
		System.out.println(checkFriendship(friend, true)+" moves to pet your feathers excitedly!");
		System.out.println("Do you: allow it, or squirm away?");
		String actionFour = input.nextLine().toLowerCase();
		actionFour = checkResponse(actionFour, "allow", "squirm", "empty case", "empty case", "empty case");
		if (actionFour.contains("allow")){
			System.out.println("Your feathers have been skritched. How fun!");
			System.out.println(checkFriendship(friend, true)+" makes a happy human noise.");
			friend = friend * 1.5;
		} else if (actionFour.contains("squirm")){
			System.out.println(checkFriendship(friend, true)+" moves away. Winning a battle doesn't mean they get to invade your personal space!");
			friend = friend * 0.85;
		}
	}
	
	//walking until viridian city
	if (player.getHP()>0){
		route(player.getName(), friend, 1);
	}
	
	System.out.println("Soon you reach Viridian City. It's so cool! You've never seen so many buildings in one place.");
	System.out.println("(If you were put in a Pokeball, you're out now.)");
	
	//ending
	System.out.println("Congrats, you got to the longest ending!");
	if (friend>2.5){
		System.out.println("Wow, you and "+checkFriendship(friend, false)+" are awesome friends! :) Way to stay in the spirit of Pokemon.");
	}
	else if (friend>2){
		System.out.println("You and "+checkFriendship(friend, false)+" are awesome friends! Way to stay in the spirit of Pokemon.");
	}
	else if (friend>1) {
		System.out.println("You and "+checkFriendship(friend, false)+" are sort of friends maybe!");
	}
	else if (friend<=1){
		System.out.println("You and "+checkFriendship(friend, false)+" dislike each other!");
	}
	else if (friend<=0.75){
		System.out.println("You and "+checkFriendship(friend, false)+" really dislike each other!");
		System.out.println("Nice job sticking "+checkFriendship(friend, false)+" but being as annoying as possible.");
	}
	
	}
	//TODO if something breaks try taking this line out
	//input.close();
}

public static String checkResponse(String A, String B, String C, String D, String E, String F){
	Scanner input = new Scanner (System.in);
	String response = A;
	while (!(response.contains(B))&&!(response.contains(C))&&!(response.contains(D))&&!(response.contains(E))&&!(response.contains(F))){
		response = input.nextLine().toLowerCase();
		response.toLowerCase();
	}
	input.close();
	return response;
}//checkResponse

public static void route (String playerName, double friend, int repeat) {
	Scanner input = new Scanner (System.in);
	for (int i=0; i<repeat; i++){
		System.out.println("Do you: flap along docilely, lag behind to investigate some seeds, or run away?");
		String actionTimePasser = input.nextLine().toLowerCase();
		actionTimePasser = checkResponse(actionTimePasser, "flap", "lag", "investigate", "run", "empty case");
		if (actionTimePasser.contains("flap")){
			System.out.println("You trek together along the road and through the grass. The wind ruffles your feathers and the sun is shining brightly.");
			System.out.println("You feel refreshed!");
			friend = friend * 1.25;
		}
		else if (actionTimePasser.contains("lag")||actionTimePasser.contains("investigate")) {
			System.out.println("Someone dropped sunflower seeds! A nice treat. "+checkFriendship(friend, true)+" looks frustrated with you, though.");
			friend = friend * 1.10;
		}
		else if (actionTimePasser.contains("run")){
			System.out.println("You dart into the nearest tree!");
			System.out.println("\"" + playerName + ", no!\" The trainer throws a Pokeball, and your walk together is over.");
			System.out.println("Time passes.\n");
			friend = friend * 0.85;
			break;
		}
		System.out.println("Time passes.\n");
	}//for loop
}//route

public static boolean enemyAttack(String enemyID, int enemyHP, Pokemon player, double friend){
	Random random = new Random();
	boolean sleep = false;
	if (enemyID.equals("Pidgey")){
		int moveSet = random.nextInt(2);
		if (moveSet==0) {
			//tackle
			System.out.println("The wild Pidgey Tackles you!");
			player.setHP(player.getHP() - Math.round(-random.nextInt(2) + 3f*defense));
		} else if (moveSet==1){
			//sand attack
			System.out.println("The wild Pidgey Attacks you with Sand!");
			System.out.println("This game doesn't use status effects or accuracy affectors, so this effectively does nothing besides annoy you.");
		}
	} else if (enemyID.equals("Rattata")){
		int moveSet = random.nextInt(3);
		if (moveSet==0) {
			//tackle
			System.out.println("The wild Rattata Tackles you!");
			player.setHP(player.getHP() - Math.round(-random.nextInt(2) + 3f*defense));
		} else if (moveSet==1){
			//tail whip
			System.out.println("The wild Rattata uses Tail Whip!");
			System.out.println("It's so cute, you lower your defense!");
			defense = 1.25f;
		} else if (moveSet==2){
			//quick attack
			System.out.println("The wild Rattata Attacks you Quickly!");
			player.setHP(player.getHP() - Math.round(-random.nextInt(2) + 2f*defense));
		}
	} else if (enemyID.equals("Hoothoot")){
		int moveSet = random.nextInt(4);
		if (moveSet==0) {
			//tackle
			System.out.println("The wild Hoothoot Tackles you!");
			player.setHP(player.getHP() - Math.round(-random.nextInt(2) + 3f*defense));
		} else if (moveSet==1){
			//foresight
			System.out.println("The wild Hoothoot uses Foresight!");
			System.out.println("This game doesn't use accuracy for the most part, so this effectively does nothing.");
		} else if (moveSet==2){
			//growl
			System.out.println("The wild Hoothoot uses Growl!");
			System.out.println("You're a little intimidated.");
			attack = attack*0.8f;
		} else if (moveSet==3){
			//hypnosis
			System.out.println("The wild Hoothoot uses Hypnosis!");
			int sleepChance = random.nextInt(10);
			if (sleepChance<=6){
				System.out.println("You fall asleep!");
				System.out.println("You go back into the Pokeball for the duration of the battle.");
				System.out.println("When you wake up, you're back on Route 1 next to "+checkFriendship(friend, false));
				sleep = true;
			} else if (sleepChance>6){
				System.out.println("It misses!");
			}
		}
		}
	return sleep;
}//enemyAttack

public static void testingPokemon(){
	Scanner input = new Scanner (System.in);
	
	Pokemon pokemon = new Pokemon();
	pokemon.setName(input.nextLine());
	System.out.println(pokemon.getName());
	
	Pokemon anotherOne = new Pokemon();
	pokemon.setName(input.nextLine());
	System.out.println(pokemon.getName());
	input.close();
}

public static String checkFriendship(double friend, boolean beginSentence){
	if (friend>=1.15){
		return "Sam";
	}
	else if (friend<1.15&&beginSentence) {
		return "The trainer";
	}
	else if (friend<1.15&&!beginSentence){
		return "the trainer";
	}
	else {
		return "you found a bug! Might be in checkFriendship method";
	}
}//checkFriendship

public static void declareHealth(Pokemon player, Pokemon enemy){
	if (enemy.getHP()<0){
		enemy.setHP(0);
	}
	System.out.println("Your health is at " + player.getHP());
	if (enemy.getHP()<0){
		enemy.setHP(0);
	}
	System.out.println("Squishy's health is at " + (int)Math.round(((double)enemy.getHP()/13)*100) + "%");
}

}//class
	