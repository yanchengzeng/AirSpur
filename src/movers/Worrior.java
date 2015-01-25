package movers;

import movers.Constants;
import movers.Constants.levelType;


import movers.Constants.moverType;
import javafx.animation.KeyFrame;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.Duration;

/*This class instantiates the object that the player controls which
 * loads an outside image. It's capable of moving and shooting under control.
 */
public class Worrior extends DynamicMover {
	private static final int worrior_height = 60;
	private static final int worrior_width = 36;
	private static final int level_one_health_pos_x = 370;
	private static final int level_one_health_pos_y = 60;
	private static final int level_one_score_pos_x = 700;
	private static final int level_one_score_pos_y = 60;
	private static final int level_two_health_pos_x = 300;
	private static final int level_two_health_pos_y = 30;
	private static final int level_two_score_pos_x = 30;
	private static final int level_two_score_pos_y = 30;
	private static final int level_two_health_pos_x_2 = 30;
	private static final int level_two_health_pos_y_2 = 30;
	private static final int worrior_bound_x = 780;
	private static final int worrior_bound_y= 750;
	private static final int worrior_offset_x = 20;
	private static final int worrior_offset_y = 680;
	private static final int worrior_health_one = 3000;
	private static final int worrior_health_two = 6000;
	
	private int healthTwoPosX;
	private int healthTwoPosY;
	private ImageView experiment;
	private Polygon carrier;
	
	public Worrior(levelType playLevel){
		super(playLevel);
		width = worrior_width;
		height = worrior_height;
		if (playLevel == Constants.levelType.level_one){
			health = worrior_health_one;
		healthPosX = level_one_health_pos_x;
		healthPosY = level_one_health_pos_y;
		scorePosX = level_one_score_pos_x;
		scorePosY = level_one_score_pos_y;
		} else {
			health = worrior_health_two;
			healthPosX = level_two_health_pos_x;
			healthPosY = level_two_health_pos_y;
			scorePosX = level_two_score_pos_x;
			scorePosY = level_two_score_pos_y;
			healthTwoPosX = level_two_health_pos_x_2;
			healthTwoPosY = level_two_health_pos_y_2;
		}
		boundX = worrior_bound_x;
		boundY = worrior_bound_y;
		offsetX = worrior_offset_x;
		offsetY = worrior_offset_y;
		experiment = new ImageView(new Image("images/nba_sas_logo.png"));
		
		generateCenter();
		setInitialPoints();
		carrier = new Polygon();
		carrier.getPoints().addAll(new Double[]{x1,y1,x2,y2,x3,y3});
		carrier.setFill(Color.RED);
		experiment.setX(centerX);
		experiment.setY(centerY);
	}
	
	public void setX(double motion){
		carrier.setTranslateX(motion+carrier.getTranslateX());
	}
	
	public void setY(double motion){
		carrier.setTranslateY(motion+carrier.getTranslateY());
	}
	
	public void transX(double motion){
		experiment.setTranslateX(motion+experiment.getTranslateX());
	}
	
	public void transY(double motion){
		experiment.setTranslateY(motion+experiment.getTranslateY());
	}
	
	public Shooter shootBullet(){
		Shooter bullet = new Shooter(level,carrier.getTranslateX(),carrier.getTranslateY());
		return bullet;
	}
	
	public Shooter shootBullet_image(){
		Shooter bullet = new Shooter(level,experiment.getTranslateX(),experiment.getTranslateY());
		return bullet;
	}
	
	public Polygon getCarrier(){
		return carrier;
	}
	
	public void reduce_health_one(){
		health -= Constants.worrior_health_decrement_level_one;
	}
	
	public void reduce_health_two(){
		health -= Constants.worrior_health_decrement_level_one;
	}
	
	public void update_score_one(){
		score += Constants.worrior_score_increment_level_one;
	}
	
	public void update_score_two(){
		score += Constants.worrior_score_increment_level_two;
	}
	
	public void setInitialPoints(){
		x1 = centerX-(width/2);
		x2 = centerX;
		x3 = centerX+(width/2);
		y1 = centerY+(height/2);
		y2 = centerY-(height/2);
		y3 = centerY+(height/2);
	}
	
	public ImageView getImage(){
		return experiment;
	}
	
	public int getHealthTWOPosX(){
		return healthTwoPosX;
	}
	
	public int getHealthTwoPosY(){
		return healthTwoPosY;
	}
}
