import java.util.Random;
import java.util.Scanner;
public class Battle {

	private int potionsUsed;
	private String resolution;
	Random random = new Random();
	//if caught, return "caught". if sleep, return "sleep". if you win, return "win". if you're defeated, return "defeat".

	public Battle(Pokemon player, Pokemon adversary, Pokemon squishy){
		player.setHP(player.getMaxHP());
		player.setAttack(player.getMaxAttack());
		player.setDefense(player.getMaxDefense());
		squishy.setHP(squishy.getMaxHP());
		squishy.setAttack(squishy.getMaxAttack());
		squishy.setDefense(squishy.getMaxDefense());
		adversary.setHP(adversary.getMaxHP());
		adversary.setAttack(adversary.getMaxAttack());
		adversary.setDefense(adversary.getMaxDefense());
		this.potionsUsed = 0;
		this.resolution = "progressing";
	}

	public void options(Pokemon player, Pokemon adversary, Scanner input, Story story, Battle battle){
		while (player.getHP()>0&&adversary.getHP()>0&&this.resolution.equals("progressing")){
			System.out.println("\nDo you: run away, fight back, defend, use Struggle, or use an item?");
			String action = MainClass.checkResponse("run", "fight", "defend", "struggle", "item");
			if (action.contains("run")){
				if (run(player,story,adversary)){
					this.resolution = "run";
					break;
				}
				else {
					this.resolution = adversary.attack(adversary, player, story);
				}
			}
			else if (action.contains("fight")){
				player.tackle(player, adversary);
				this.resolution = adversary.attack(adversary, player, story);
			}
			else if (action.contains("defend")){
				player.defend(player);
				this.resolution = adversary.attack(adversary, player, story);
			}
			else if (action.contains("struggle")){
				player.struggle(player, adversary, story);
				this.resolution = adversary.attack(adversary, player, story);
			}
			else if (action.contains("item")){
				item(player, adversary, story, input);
			}
			declareHealth(player, adversary);
		}
		checkForWin(player, adversary);
	}

	public boolean run(Pokemon player, Story story, Pokemon adversary){
		boolean runSuccess = random.nextBoolean();
		if (runSuccess){
			if (adversary.getPokeType().equals("Squirtle")){
				System.out.println("You successfully fly away!");
				System.out.println("Your friend was spooked by the battle, but you eventually find them hunting for insects in the tall grass.");
				System.out.println("Congrats, you got an ending!");
				return true;
			}
			else {
				System.out.println("You dart into the nearest tree! No more following "+story.checkFriendship(false)+"'s tune!");
				System.out.println("Congrats, you got an ending!");
				story.setFriend(0.05);
				return true;
			}
		}
		else {
			if (adversary.getPokeType().equals("Squirtle")){
				System.out.println("You fly for the nearest tree, but Squishy catches you by the foot!");
				return false;
			}
			else {
				System.out.println("You fly for the nearest tree, but the "+adversary.getWildEnemy()+" "+adversary.getPokeType()+" catches you by the foot!");
				return false;
			}
		}
	}

	public void item(Pokemon player, Pokemon adversary, Story story, Scanner input){
		if (this.potionsUsed<story.potionsTotal){
			System.out.println("Do you: use a Pokeball, or a potion?");
			String itemChoose = MainClass.checkResponse("pokeball", "potion", "empty case","empty case","empty case");
			if (itemChoose.contains("pokeball")){
				pokeball(player, adversary, story);
			}
			else if (itemChoose.contains("potion")){
				System.out.println("You're 20 points healthier!");
				player.setHP(player.getHP()+20);
				if (player.getHP()>player.getMaxHP()){
					player.setHP(player.getMaxHP());
				}
				potionsUsed = potionsUsed + 1;
				System.out.println("You have " + (story.potionsTotal-potionsUsed) + " potions left.");
				adversary.attack(adversary, player, story);
			}
		}
		else if (potionsUsed>=story.potionsTotal){
			System.out.println("You only have Pokeballs left. Do you want to throw a Pokeball?");
			String ballChoice = MainClass.checkResponse("yes", "no", "empty case", "empty case", "empty case");
			if (ballChoice.contains("yes")){
				pokeball(player, adversary, story);
			}
			else if (ballChoice.contains("no")){
			}
		}
	}

	public void pokeball(Pokemon player, Pokemon adversary, Story story){
		System.out.println(story.checkFriendship(true)+" throws a Pokeball!");
		float catchChance = 1.0f-(random.nextFloat()*adversary.getHP()/12);
		if (catchChance>0.6){
			System.out.println("Gotcha!");
			System.out.println("The wild "+adversary.getPokeType()+" was caught!");
			this.resolution = "caught";
		}
		else if (catchChance<=0.6){
			System.out.println("Aww, they missed!");
			adversary.attack(adversary, player, story);
		}
	}

	public void potion(Pokemon player, Pokemon adversary, Story story){
		System.out.println("You're 20 points healthier!");
		player.setHP(player.getHP()+20);
		if (player.getHP()>player.getMaxHP()){
			player.setHP(player.getMaxHP());
		}
		this.potionsUsed = this.potionsUsed + 1;
		System.out.println("You have " + (story.potionsTotal-this.potionsUsed) + " potions left.");
		adversary.attack(adversary, player, story);
	}

	public void setResolution(String resolution){
		this.resolution = resolution;
	}

	public String getResolution(){
		return this.resolution;
	}

	public void checkForWin(Pokemon player, Pokemon adversary){
		if (player.getHP()<=0) {
			this.resolution = "defeat";
		}
		if (adversary.getHP()<=0){
			this.resolution = "win";
		}
	}

	public void declareHealth(Pokemon player, Pokemon adversary){
		if (this.resolution.equals("progressing")){
			if (player.getHP()<0){
				player.setHP(0);
			}
			System.out.println("\nYour health is at " + player.getHP());
			if (adversary.getHP()<0){
				adversary.setHP(0);
			}
			if (adversary.getPokeType().equals("Squirtle")){
				System.out.println("Squishy's health is at " + (int)Math.round(((double)adversary.getHP()/adversary.getMaxHP())*100) + "%");
			}
			else {
				System.out.println("The "+adversary.getWildEnemy()+" "+adversary.getPokeType()+"'s health is at "+(int)Math.round(((double)adversary.getHP()/adversary.getMaxHP())*100) + "%");
			}
		}
	}

}

