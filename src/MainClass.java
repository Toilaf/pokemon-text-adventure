import java.util.Scanner;
import java.util.Random;
import java.lang.Math;

public class MainClass {

	public static void main(String[] args) {

		//TODO check for bugs	
		//TODO maybe use an accuracy stat so the wild pidgey isn't as easy to beat?

		//TODO adjust battle stats to be more accurate to the games
		//rt 1 hp: 17-22?
		//rt 1 attack does 4-7 damage normally
		//involve levels in Moveset damages

		//TODO class stuff:
		//TODO export all system.in texty bits to Story story
		//TODO convert all pokemon (player, squishy, enemyOne) to pokemon objects

		Scanner input = new Scanner (System.in);
		Random random = new Random();

		Story story = new Story();
		story.trigWarn();
		Pokemon player = new Pokemon(true, "bug", 0);
		story.intro(player);

		Pokemon squishy = new Pokemon(false,"enemy",0);
		story.announceSquishy(player, squishy);
		Battle squishBattle = new Battle(player, squishy, squishy);
		story.meetSquishyBattle(player, squishy, squishBattle, story);

		if (squishBattle.getResolution().equals("win")){
			story.ifDefeatSquishy();
		}
		else if (squishBattle.getResolution().equals("ran")){
		}
		else {
			story.afterSquishy(player);
			story.preRt1();
			story.route(player, 2);

			Pokemon enemyOne = new Pokemon(false, "wild", 1);
			Battle rtOne = new Battle(player, squishy, enemyOne);
			enemyOne.randPokeType(random, "Pidgey", "Rattata", "Hoothoot");
			rtOne.options(player, enemyOne, input, story, rtOne);

			System.out.println("\nPress enter to continue your adventure.");
			System.out.println("Unless you ran away! In which case, only press enter if you want to cheat.");
			input.nextLine();

			if (player.getHP()<=0&&enemyOne.getHP()>0&&!rtOne.getResolution().equals("caught")&&!rtOne.getResolution().equals("sleep")){
				//you lost
				System.out.println("You vaguely register Squishy jumping out to replace you before you pop back into the Pokeball for the duration of the battle.");
				story.setFriend(1.25);
				squishy.setLevel(squishy.getLevel()+1);
			}
			if (enemyOne.getHP()<=0&&player.getHP()>0&&!rtOne.getResolution().equals("caught")&&!rtOne.getResolution().equals("sleep")){
				//you won
				System.out.println("You won!");
				System.out.println("You've earned a satisfying rush of EXP.");
				story.setFriend(1.5);
				//trainer tries to pet
				System.out.println("\n"+story.checkFriendship(true)+" moves to pet your feathers excitedly!");
				System.out.println("Do you: allow it, or squirm away?");
				String actionFour = checkResponse("allow", "squirm", "empty case", "empty case", "empty case");
				if (actionFour.contains("allow")){
					System.out.println("Your feathers have been skritched. How fun!");
					System.out.println(story.checkFriendship(true)+" makes a happy human noise.");
					story.setFriend(1.5);
				} else if (actionFour.contains("squirm")){
					story.setFriend(0.85);
					System.out.println(story.checkFriendship(true)+" moves away. Winning a battle doesn't mean they get to invade your personal space!");
				}
			}
			if (rtOne.getResolution().equals("caught")){
				System.out.println("You successfully caught the wild " + enemyOne.getPokeType() + "!");
				story.setFriend(1.5);
				//trainer tries to pet
				System.out.println(story.checkFriendship(true)+" moves to pet your feathers excitedly!");
				System.out.println("Do you: allow it, or squirm away?");
				String actionFour = checkResponse("allow", "squirm", "empty case", "empty case", "empty case");
				if (actionFour.contains("allow")){
					System.out.println("Your feathers have been skritched. How fun!");
					System.out.println(story.checkFriendship(true)+" makes a happy human noise.");
					story.setFriend(1.5);
				} else if (actionFour.contains("squirm")){
					System.out.println(story.checkFriendship(true)+" moves away. Winning a battle doesn't mean they get to invade your personal space!");
					story.setFriend(0.9);
				}
			}
			if (rtOne.getResolution().equals("sleep")){
				System.out.println("You fall asleep!");
				System.out.println("You go back into the Pokeball for the duration of the battle.");
				System.out.println("When you wake up, you're back on Route 1 next to "+story.checkFriendship(false));
				story.setFriend(0.95);
			}

			if (!(rtOne.getResolution().equals("defeat"))&&!rtOne.getResolution().equals("sleep")){
				story.route(player, 1);
			}

			story.viridian();
			story.finalEnding();
			input.close();
		}
	}

	public static String checkResponse(String A, String B, String C, String D, String E){
		Scanner input = new Scanner(System.in);
		String action = input.nextLine().toLowerCase();
		while (!(action.contains(B))&&!(action.contains(C))&&!(action.contains(D))&&!(action.contains(E))&&!(action.contains(A))){
			action = input.nextLine().toLowerCase();
			action.toLowerCase();
		}
		return action;
	}//checkResponse

}//class
