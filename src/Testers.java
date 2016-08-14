import java.util.Random;
public class Testers {
	Random random = new Random();
	
	public void enemyAttack(Pokemon player, double friend){
		String testEnemyID = "Pidgey";
		int testEnemyHP = 12;
		boolean testSleep = false;
		testSleep = MainClass.enemyAttack(testEnemyID, testEnemyHP, player, friend);	
	}
	
	public void defenseStat(){
		int testHP = 11;
		float defenseCurl = 0.2373046875f;
		testHP = testHP - Math.round(-random.nextInt(2) + 3f*defenseCurl); //make this 2-3 after pressing defenseCurl once and twice, and 0-1 after pressing it about 3 times, etc
		System.out.println(testHP);
	}
		
		//test for attack stat
		//int testEnemyHP = 12;
		//attack = 0.5625f;
		//testEnemyHP = testEnemyHP - Math.round(-random.nextInt(2) + 2f*attack);
		//System.out.println(testEnemyHP);
}
