import java.util.Scanner;
public class Story {
	Scanner input = new Scanner(System.in);
	
	public void trigWarn(){
		System.out.println("Do you want to see a trigger warning? (contains spoilers)");
		String trigWarn = input.nextLine().toLowerCase();
		if (trigWarn.contains("y")){
			System.out.println("The first battle contains no warnings that are not relevant for the Pokemon games in general.");
			System.out.println("However, if you lose the first battle, or you decide to cheat and continue after the first battle, the storyline becomes reminiscent of Stockholm Syndrome.");
			System.out.println("I would've taken out this element if I could figure out a way to still keep the game true to the original Pokemon games.");
			System.out.println("If you feel like this might be upsetting, please take care of yourself and don't play.");
			System.out.println("\nPress enter if you want to continue.");
			input.nextLine();
		}
	}
	
	public Pokemon intro(Pokemon player){
		System.out.println("Welcome to Kanto!");
		System.out.println("You're hanging out near Pallet Town. It's pretty chill.");
		System.out.println("What's your name?");
		player.setName(input.nextLine());
		player.setMaxHP(12);
		System.out.println("What type of bird Pokemon are you? If you can't decide, type Pidgey.");
		player.setPokeType(input.next());
		System.out.println("Nice!");
		return player;
	}
}
