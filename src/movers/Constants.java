package movers;

/*Stores constant values for all other classes
 * 
 */
public class Constants {
	
	public static final int boundaryX = 800;
	public static final int boundaryY = 800;
	
	public static final int level_one_enemy_num = 3;
	public static final int worrior_health_decrement_level_one = 100;
	public static final int worrior_health_decrement_level_two = 100;
	public static final int enemy_health_decrement = 300;
	public static final int worrior_score_increment_level_one = 10;
	public static final int worrior_score_increment_level_two = 200;
	public static final int worrior_score_award_level_two = 200;
	 
	
	public static final int player_speed_one = 12;
	public static final int player_speed_two = 24;
	public static final int NUM_FRAMES_PER_SECOND = 60;
	
	public enum moverType {
		player, enemy, boss
	}
	
	public enum levelType{
		level_one, level_two
	}
}
