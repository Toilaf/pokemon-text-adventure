import java.util.Random;
public class Pokemon {
	private String name;
	private String pokeType;
	private int maxHP;
	private int hp;
	private int level;
	private float maxAttack;
	private float maxDefense;
	private float attack;
	private float defense;
	private boolean isPlayer;
	private String wildEnemy;
	Random random = new Random();
	Moveset moveset = new Moveset();

	public Pokemon(boolean isPlayer, String wildEnemy, int route){
		this.isPlayer = isPlayer;
		this.wildEnemy = wildEnemy;
		setAttack(1);
		setDefense(1);
		if (wildEnemy.equals("wild")){
			this.maxHP = 16 + random.nextInt(5) + route;
			this.level = (int)Math.round(5*Math.pow(1.09, route) + random.nextGaussian()*2); //alternatively, level = (int)Math.round(route*1.1 + 4)
			this.maxAttack = (float)(1+ random.nextGaussian()*0.45*(0.20*this.level));
			this.maxDefense = (float)(1+ random.nextGaussian()*0.45*(0.20*this.level));
		}
	}

	public boolean getPlayer(){
		return this.isPlayer;
	}

	public String getWildEnemy(){
		return wildEnemy;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setPokeType(String pokeType){
		this.pokeType = pokeType;
	}

	public String getPokeType(){
		return pokeType;
	}

	public void setHP(int hp){
		this.hp = hp;
	}

	public int getHP(){
		return hp;
	}

	public void setMaxHP(int maxHP){
		this.maxHP = maxHP;
	}

	public int getMaxHP(){
		return maxHP;
	}

	public void setLevel(int level){
		this.level = level;
	}

	public int getLevel(){
		return level;
	}

	public float getAttack() {
		return attack;
	}
	
	public float getMaxAttack(){
		return maxAttack;
	}

	public void setAttack(float attack) {
		this.attack = attack;
	}
	
	public void setMaxAttack(float maxAttack){
		this.maxAttack = maxAttack;
	}

	public float getDefense() {
		return defense;
	}
	
	public float getMaxDefense(){
		return maxDefense;
	}

	public void setDefense(float defense) {
		this.defense = defense;
	}
	
	public void setMaxDefense(float maxDefense){
		this.maxDefense = maxDefense;
	}

	public void randPokeType(Random random, String A, String B, String C){
		int wildNumRandom = random.nextInt(3);
		if (wildNumRandom==0){
			this.pokeType = A;
		} else if (wildNumRandom==1){
			this.pokeType = B;
		} else if (wildNumRandom==2) {
			this.pokeType = C;
		}
		int legendaryChance = random.nextInt(100);
		if (legendaryChance==0){
			this.pokeType = "Congratulations, you found a legendary Pokemon! Sadly, I haven't programmed it in yet.";
			//TODO
		}
		System.out.println("A wild " + pokeType + " appears!");
	}

	public String attack(Pokemon pokemon, Pokemon defender, Story story){
		Moveset pokeattack = new Moveset();
		if (pokemon.getPokeType().equals("Squirtle")){
			return pokeattack.squishy(pokemon, defender);
		}
		else if (pokemon.getPokeType().equals("Pidgey")){
			return pokeattack.pidgey(pokemon, defender);
		}
		else if (pokemon.getPokeType().equals("Hoothoot")){
			return pokeattack.hoothoot(pokemon, defender);
		}
		else if (pokemon.getPokeType().equals("Rattata")){
			return pokeattack.rattata(pokemon, defender);
		}
		else {
			return "you found a bug";
		}
	}

	public void tackle(Pokemon player, Pokemon defender){
		System.out.println("You Tackle the "+ defender.getWildEnemy()+" "+defender.getPokeType() +"!");
		moveset.calculateDamage(player, defender, 1, 1.8124f);
	}

	public void defend(Pokemon player){
		System.out.println("You curl up in a tight Defense Curl!");
		player.setDefense(player.getDefense()*0.73f);
	}

	public void struggle(Pokemon player, Pokemon defender, Story story){
		System.out.println("You Struggle and flail around. Your wings bash into the ground awkwardly and a feather falls off.");
		System.out.println(story.checkFriendship(true)+" laughs at you.");
		moveset.calculateDamage(player, defender, 1, 1.7f);
	}

}//end class
