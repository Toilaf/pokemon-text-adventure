import java.util.Scanner;
import java.util.Random;
public class Story {
	Scanner input = new Scanner(System.in);
	Random random = new Random();
	private double friend;
	public int potionsTotal;

	public void trigWarn(){
		System.out.println("Do you want to see a trigger warning? (contains spoilers)");
		String trigWarn = input.nextLine().toLowerCase();
		if (trigWarn.contains("y")){
			System.out.println("The first battle requires no warnings that are not relevant for the Pokemon games in general.");
			System.out.println("However, if you lose the first battle, or you decide to cheat and continue after the first battle, the storyline becomes reminiscent of Stockholm Syndrome.");
			System.out.println("I would've taken out this element if I could figure out a way to still keep the plotline true to the original Pokemon games. They can get creepy if you think too hard about it.");
			System.out.println("If you feel like this might be upsetting, please take care of yourself and don't play this game.");
			System.out.println("\nPress enter if you want to continue.");
			input.nextLine();
		}
	}

	public void setFriend(double modifier){
		this.friend = this.friend*modifier;
	}

	public String checkFriendship(boolean beginSentence){
		if (this.friend>=1.15){
			return "Sam";
		}
		else if (this.friend<1.15&&beginSentence) {
			return "The trainer";
		}
		else if (this.friend<1.15&&!beginSentence){
			return "the trainer";
		}
		else {
			return "you found a bug!";
		}
	}

	public void setPotionsTotal(int potionsTotal){
		this.potionsTotal = potionsTotal;
	}

	public void route (Pokemon player, int repeat) {
		for (int i=0; i<repeat; i++){
			System.out.println("Do you: flap along docilely, lag behind to investigate some food on the ground, or run away?");
			String actionTimePasser = MainClass.checkResponse("flap", "lag", "investigate", "run", "empty case");
			if (actionTimePasser.contains("flap")){
				System.out.println("You trek together along the road and through the grass. The wind ruffles your feathers and the sun is shining brightly.");
				System.out.println("You feel refreshed!");
				setFriend(1.25);
			}
			else if (actionTimePasser.contains("lag")||actionTimePasser.contains("investigate")) {
				int chance = random.nextInt(3);
				if (chance==0){
					System.out.println("Someone dropped sunflower seeds! A nice treat.");
					System.out.println("You take a few minutes to eat all of them. "+checkFriendship(true)+"'s mouth and eyes get smaller as they wait for you.");
					setFriend(1.05);
				}
				else if (chance==1){
					System.out.println("Yum, an earthworm!");
					System.out.println("When "+checkFriendship(false)+" turns around to check on you, the ends of their mouth get lower and creases appear across their face. Interesting.");
					setFriend(1.10);
				}
				else if (chance==2){
					System.out.println("That potato chip turned out to be a shiny crinkly thing! Eww!");
					System.out.println(checkFriendship(true)+"'s mouth widens, and they convulse for a moment before offering you some peanuts from their backpack.");
					setFriend(1.15);
				}
			}
			else if (actionTimePasser.contains("run")){
				setFriend(0.85);
				System.out.println("You dart into the nearest tree!");
				System.out.println("\"" + player.getName() + ", no!\" "+checkFriendship(true)+" throws a Pokeball, and your walk together is over.");
				System.out.println("Time passes.\n");
				break;
			}
			System.out.println("Time passes.\n");
		}//for loop
	}//route

	public void intro(Pokemon player){
		System.out.println("Welcome to Kanto!");
		System.out.println("You're hanging out near Pallet Town. It's pretty chill.");
		System.out.println("What's your name?");
		player.setName(input.nextLine());
		player.setMaxHP(12);
		System.out.println("What type of bird Pokemon are you? If you can't decide, type Pidgey.");
		player.setPokeType(input.next());
		System.out.println("Nice!");
	}

	public void announceSquishy(Pokemon player, Pokemon squishy){
		System.out.println("\nUnfortunately, you're hanging out with a friend when a Pokemon trainer sneaks up on you.");
		System.out.println("They throw out a Pokeball! \"Squishy, go!\" A Squirtle pops out!");
		System.out.println("It looks unsurely at the trainer before focusing on you and taking a stance.");
		squishy.setMaxHP(26);
		squishy.setHP(squishy.getMaxHP());
		squishy.setPokeType("Squirtle");
		squishy.setName("Squishy");
		player.setMaxHP(20);
		player.setHP(player.getMaxHP());
	}
	
	public void squishBattle(Pokemon player, Pokemon squishy, Battle squishBattle, Story story){
		squishBattle.setResolution("progressing");
		while (player.getHP()>0&&squishy.getHP()>0){
			System.out.println("\nDo you: run away, fight back, defend, or use Struggle?");
			String actionOne = MainClass.checkResponse("run", "fight", "defend", "struggle", "empty case");
			if (actionOne.contains("run")){
				if (squishBattle.run(player,story,squishy)){
					break;
				}
				else {
					squishy.attack(squishy, player, story);
				}
			}
			else {
				if (actionOne.contains("fight")&&squishy.getHP()>0){
					player.tackle(player, squishy);
					squishy.attack(squishy, player, story);
				} else if (actionOne.contains("defend")&&squishy.getHP()>0){
					player.defend(player);
					System.out.println("Squishy's attacks do less damage now!");
					squishy.attack(squishy, player, story);
				} else if ((actionOne.contains("struggle")&&squishy.getHP()>0)){
					player.struggle(player, squishy, story);
					squishy.attack(squishy, player, story);
				}
			}
			squishBattle.declareHealth(player, squishy);
			squishBattle.checkForWin(player, squishy);
		}
	}
	
	public void ifDefeatSquishy(){
		System.out.println("The enemy Squirtle faints!");
		System.out.println("\nThe Pokemon trainer drops several hundred Pokecoins in their haste to carry Squishy to the nearest PokeCenter!");
		System.out.println("The coins are useless to you and your lack of opposable thumbs. Despite this, the win is satisfying.");
		System.out.println("Congrats, you got an ending! Your friend got scared and ran away, but at least you're not in a Pokeball!");
	
	}
	
	public void afterSquishy(Pokemon player){
		System.out.println("You faint.");
		System.out.println("\nWhen you wake up, you're in a Pokeball with full health.");
		player.setHP(player.getMaxHP());
		System.out.println("After a few minutes, you realize it's not so bad as you thought. It's actually pretty comfy.");
		System.out.println("After a few hours, you're released! The trainer is right there, though.");
		System.out.println("\n\"Hi there, li'l " + player.getPokeType() + "!\" they say. \"My name's Sam. And your name will be... how about " + player.getName() + "?\"");
		System.out.println("They amazingly get your name right because this is Pokemon. Do you: chirp approvingly, or squawk in annoyance just to be contrary?");
		String nameChoice = MainClass.checkResponse("chirp", "squawk", "empty case", "empty case", "empty case");
		if (nameChoice.contains("chirp")){
			System.out.println("\"Aww, you like that name! " + player.getName() + " it is.\"");
			setFriend(1.05);
		}
		else if (nameChoice.contains("squawk")){
			System.out.println("\"Oh, that name's no good? How about... Bob?\"");
			System.out.println("(Keep chirping or squawking to answer "+checkFriendship(false)+".)");
			nameChoice = MainClass.checkResponse("chirp", "squawk", "empty case", "empty case", "empty case");
			if (nameChoice.contains("chirp")){
				System.out.println("\"Okay then! Bob it is.\"");
				player.setName("Bob");
			}
			else if (nameChoice.contains("squawk")){
				System.out.println("\"No? What about Katrina?\"");
				nameChoice = MainClass.checkResponse("chirp", "squawk", "empty case", "empty case", "empty case");
				if (nameChoice.contains("chirp")){
					System.out.println("\"Okay then! Katrina it is.\"");
					player.setName("Katrina");
				}
				else if (nameChoice.contains("squawk")){
					System.out.println("\"Really? Not Katrina either? But I have to call you something! What do you think about Dequan?\"");
					nameChoice = MainClass.checkResponse("chirp", "squawk", "empty case", "empty case", "empty case");
					if (nameChoice.contains("chirp")){
						System.out.println("\"Okay then! Dequan it is.\"");
						player.setName("Dequan");
					}
					else if (nameChoice.contains("squawk")){
						System.out.println("\"I'll just... call you " + player.getPokeType() + " for now.\"");
						player.setName(player.getPokeType());
					}
				}
			}
		}
	}

	public void preRt1(){
		System.out.println("They move to pet your feathers!");
		System.out.println("Do you: allow it, or squirm away?");
		String actionTwo = MainClass.checkResponse("squirm", "allow", "empty case", "empty case", "empty case");
		if (actionTwo.contains("allow")){
			System.out.println("Your feathers have been skritched softly. How fun!");
			System.out.println(checkFriendship(true) +" makes a happy human noise.");
			System.out.println("\n"+checkFriendship(true)+" makes a bunch of human noises which means they're trusting you to walk alongside them for Route 1.\n");
			friend = friend * 1.5;
		} else if (actionTwo.contains("squirm")) {
			friend = friend * 0.85;
			System.out.println(checkFriendship(true)+" moves away. Success!");
			System.out.println("\n"+checkFriendship(true)+" makes a bunch of human noises which means they're trusting you to walk alongside them for Route 1.\n");
		}
	}

	public void viridian(){
		System.out.println("Soon you reach Viridian City. It's so cool! You've never seen so many buildings in one place.");
		System.out.println("(If you were put in a Pokeball, you're out now.)");
	}

	public void finalEnding(){
		System.out.println("Congrats, you got to the longest ending!");
		if (this.friend>2.5){
			System.out.println("Wow, you and "+checkFriendship(false)+" are awesome friends! :) Way to stay in the spirit of Pokemon.");
		}
		else if (this.friend>2){
			System.out.println("You and "+checkFriendship(false)+" are awesome friends! Way to stay in the spirit of Pokemon.");
		}
		else if (this.friend>1) {
			System.out.println("You and "+checkFriendship(false)+" are sort of friends maybe!");
		}
		else if (this.friend<=1){
			System.out.println("You and "+checkFriendship(false)+" dislike each other!");
		}
		else if (this.friend<=0.75){
			System.out.println("You and "+checkFriendship(false)+" really dislike each other!");
			System.out.println("Nice job sticking "+checkFriendship(false)+" but being as annoying as possible.");
		}
	}
}
