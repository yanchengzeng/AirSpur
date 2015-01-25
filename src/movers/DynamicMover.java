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

public class DynamicMover extends Mover {
	
	
	protected Integer health;
	protected int healthPosX;
	protected int healthPosY;
	protected Integer score;
	protected int scorePosX;
	protected int scorePosY;
	protected double x1,x2,x3,y1,y2,y3;
	protected  int boundX;
	protected  int boundY;
	protected  int offsetX;
	protected  int offsetY;
	protected  int width;
	protected  int height;
	
	public DynamicMover(levelType playLevel){
		super(playLevel);
		myGenerator = new Random();
		score = 0;
		
	}
	
	public void generateCenter(){
		centerX = myGenerator.nextInt(boundX-offsetX)+offsetX;
		centerY = myGenerator.nextInt(boundY-offsetY)+offsetY;
	}
	

	
	public int getHealthPosX(){
		return healthPosX;
	}
	
	public int getHealthPosY(){
		return healthPosY;
	}
	
	public int getScorePosX(){
		return scorePosX;
	}
	
	public int getScorePosY(){
		return scorePosY;
	}
	
	public Integer getHealth(){
		return health;
	}
	
	
	public Integer getScore(){
		return score;
	}
	
	
	
	
}
