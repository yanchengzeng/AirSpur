package Play;

import movers.Constants.moverType;
import movers.Worrior;
import movers.Shooter;
import movers.Enemy;
import movers.Constants;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.*;

/*The prototype for level 1 and level 2 of the game. It contains basic variables common to 
 * both levels.
 */
public class Level {
	protected moverType x;
	
	protected Random myGen;
	protected Scene myScene;
	protected Worrior myPlayer;
	protected Group myGroup;
	protected int enemyNum = 3;
	protected ArrayList<Enemy> enemies;	
	protected ArrayList<Shooter> enemyBullets;
	protected ArrayList<Shooter> playerBullets;
	protected Enemy myEnemy;
	protected Text playerHealth;
	protected Text playerScore;
	protected ArrayList<Text> enemyHealth;
	protected int sceneWidth;
	protected int sceneHeight;
	protected Stage stage;
	protected Timeline real;


	public Scene init(Stage s, int width, int height, Timeline time){
		real = time;
		enemies = new ArrayList<Enemy>();
		enemyBullets = new ArrayList<Shooter>();
		playerBullets = new ArrayList<Shooter>();
		stage = s;
		sceneWidth = width;
		sceneHeight = height;
		myGroup = new Group();
		myGen = new Random();
		myScene = new Scene(myGroup,width,height,Color.WHITE);
		return myScene;
	}

	
	
	public KeyFrame start (int frameRate) {
    	return new KeyFrame(Duration.millis(1000 / frameRate), e -> updateSprites());
    }
	
	public void updateSprites () {
	       
    }
}
