package movers;

import movers.Constants;
import movers.Constants.levelType;

import java.util.Random;

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
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

/*This class instantiates the enemy objects that move automatically 
 * and are capable of shooting bullets.
 */
public class Enemy extends DynamicMover {

	public static final double enemy_speed = 1.2;
	private static final Color enemy_color = Color.BLACK;
	private static final int enemy_height_one = 36;
	private static final int enemy_height_two = 60;
	private static final int enemy_width_one = 24;
	private static final int enemy_width_two = 60;
	private static final int level_two_health_pos_x = 300;
	private static final int level_two_health_pos_y = 30;
	private static final int level_two_score_pos_x = 30;
	private static final int level_two_score_pos_y = 30;
	private static final int enemy_bound_x = 800;
	private static final int enemy_bound_y= 150;
	private static final int enemy_offset_x = 0;
	private static final int enemy_offset_y = 100;
	private static final int enemy_health_two = 2100;
	private static final int enemy_health_one = 10;
	private static final int enemy_radius  = 20;

	private Polygon carrier;
	private Circle launcher;

	public Enemy(levelType playLevel){
		super(playLevel);
		if(playLevel == Constants.levelType.level_one){
			health = enemy_health_one;
			width = enemy_width_one;
			height = enemy_height_one;
		} else {
			health = enemy_health_two;
			width = enemy_width_two;
			height = enemy_height_two;
		}
		healthPosX = level_two_health_pos_x;
		healthPosY = level_two_health_pos_y;
		scorePosX = level_two_score_pos_x;
		scorePosX = level_two_score_pos_y;
		boundX = enemy_bound_x;
		boundY = enemy_bound_y;
		offsetX = enemy_offset_x;
		offsetY = enemy_offset_y;
		generateCenter();
		setInitialPoints();
		carrier = new Polygon();
		carrier.getPoints().addAll(new Double[]{x1,y1,x2,y2,x3,y3});
		carrier.setFill(enemy_color);

		launcher = new Circle();
		launcher.setFill(enemy_color);
		launcher.setCenterX(centerX);
		launcher.setCenterY(centerY);
		launcher.setRadius(enemy_radius);
	}


	//move the enemy forward
	public void moveForward(){
		carrier.setTranslateY(carrier.getTranslateY()+enemy_speed);
	}
	
	//instantiates a bullet that comes with the enemy
	public Shooter singleBullet(){
		Shooter bullet = new Shooter(level,this.getCenterX(),this.getCenterY());
		return bullet;
	}

	//return the shape object for coordinates operation
	public Polygon getCarrier(){
		return carrier;
	}

	public double getCurrentPosX(){
		return centerX + carrier.getTranslateX();
	}

	public double getCurrentPosY(){
		return centerY + carrier.getTranslateY();
	}

	public void setInitialPoints(){
		x1 = centerX-(width/2);
		x2 = centerX;
		x3 = centerX+(width/2);
		y1 = centerY-(height/2);
		y2 = centerY+(height/2);
		y3 = centerY-(height/2);
	}

	public void setX(double motion){
		carrier.setTranslateX(motion+carrier.getTranslateX());
	}

	public void setY(double motion){
		carrier.setTranslateY(motion+carrier.getTranslateY());
	}

	public double getX(){
		return Math.abs(carrier.getTranslateX());
	}

	public double getY(){
		return Math.abs(carrier.getTranslateY());
	}


	public Circle getLauncher(){
		return launcher;
	}
	
	public void reduce_health(){
		health -= Constants.enemy_health_decrement;
	}
}
