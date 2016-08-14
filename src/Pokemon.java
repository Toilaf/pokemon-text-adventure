
public class Pokemon {
	private String name;
	private String pokeType;
	private int maxHP;
	private int hp;
	private int level;
	
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
	
	public void setLevel(){
		//level = (int)Math.round(routefound*1.1 + 4)
		//then we get Route 1: 5. Route 2: 
	}
	
}//end class
