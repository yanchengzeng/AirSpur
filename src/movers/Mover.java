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

public class Mover{
	
	protected Color color;
	protected int centerX,centerY;
	public Random myGenerator;
	protected levelType level;
	protected boolean bumpTag;
	protected boolean destroyTag;
	protected int speed;
	
	public Mover(levelType playLevel){
		super();
		level = playLevel;
		bumpTag = false;
	}
	
	public int getCenterX(){
		return centerX;
	}
	
	public int getCenterY(){
		return centerY;
	}
	
	public void setBumpTag(){
		bumpTag = true;
	}
	
	public boolean getBumpTag(){
		return bumpTag;
	}
	
	public void setDestroyTag(){
		bumpTag = true;
	}
	
	public boolean getDestroyTag(){
		return bumpTag;
	}


}
