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
import javafx.stage.Stage;
import javafx.util.Duration;

public class Shooter extends Mover {
	private static final double shooter_radius = 2;
	private static final double level_one_speed = 1.6;
	private Color shooter_color = Color.BLACK;
	Circle carrier;
	Point2D direction;
	private int movingTag;
	
	public Shooter(levelType playLevel, double initX, double initY){
		super(playLevel);
		carrier = new Circle();
		carrier.setFill(shooter_color);
		carrier.setCenterX(initX);
		carrier.setCenterY(initY);
		carrier.setRadius(shooter_radius);
		movingTag = 0;
	}
	
	public Circle getCarrier(){
		return carrier;
	}
	
	public void moveOne(moverType type){
		if (type == Constants.moverType.enemy){
			carrier.setTranslateY(carrier.getTranslateY()+level_one_speed);
		} else {
			carrier.setTranslateY(carrier.getTranslateY()-level_one_speed);
			
		}
	}
	public void moveUp(){
			carrier.setTranslateY(carrier.getTranslateY()-level_one_speed);
	}
	
	public void moveDown(){
			carrier.setTranslateY(carrier.getTranslateY()+level_one_speed);
	}
	
	public void moveLeft(){
			carrier.setTranslateX(carrier.getTranslateX()-level_one_speed);
	}
	
	public void moveRight(){
			carrier.setTranslateX(carrier.getTranslateX()+level_one_speed);
	}
	
	public void setColor(){
		carrier.setFill(Color.RED);;
	}
	
	public void setDirection(double xCor, double yCor){
		direction = new Point2D(xCor,yCor);
	}
	
	public void moveDirection(){
		carrier.setCenterX(carrier.getCenterX() + direction.getX());
		carrier.setCenterY(carrier.getCenterY() + direction.getY());
	}
	
	public void setTag(int selection){
		movingTag = selection;
	}
	
	public void movingSelection(){
		if (movingTag == 1){
			moveUp();
		} else if (movingTag == 2){
			moveRight();
		} else if (movingTag == 3){
			moveDown();
		} else {
			moveLeft();
		}
	}
}
