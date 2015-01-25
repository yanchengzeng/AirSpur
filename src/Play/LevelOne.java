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

/*The first level of the game.
 * 
 */

public class LevelOne extends Level {

	protected static final int enemyNumBound = 60;
	protected static final int enemyNumOffset = 40;
	protected static final int winning_goal = 10;
	protected static final int image_offset = 30;

	protected static int round_time_level_one;
	protected static int counter_level_one;
	private ArrayList<Shooter> toRemoveEnemyBullets;
	private ArrayList<Shooter> toRemovePlayerBullets;
	private ArrayList<Enemy> toRemoveEnemies;
	private boolean presentState;

	public Scene init(Stage s, int width, int height, Timeline time){
		super.init(s, width, height,time);
		presentState = true;
		counter_level_one = 0;
		round_time_level_one = 100;
		myPlayer = new Worrior(Constants.levelType.level_one);
		myGroup.getChildren().add(myPlayer.getImage());
		generateEnemy(Constants.level_one_enemy_num);
		toRemoveEnemyBullets = new ArrayList<>();
		toRemovePlayerBullets = new ArrayList<>();
		toRemoveEnemies = new ArrayList<>();
		enemyHealth = new ArrayList<>();
		enemyHealth.add(new Text(50,60,String.valueOf(winning_goal-myPlayer.getScore()/10)));
		enemyHealth.get(0).setFont(new Font(40));
		playerHealth = new Text(myPlayer.getHealthPosX(), myPlayer.getHealthPosY(),myPlayer.getHealth().toString());
		playerHealth.setFont(new Font(40));
		playerScore = new Text(myPlayer.getScorePosX(), myPlayer.getScorePosY(),myPlayer.getScore().toString());
		playerScore.setFont(new Font(40));
		myGroup.getChildren().add(playerHealth);
		myGroup.getChildren().add(playerScore);
		myGroup.getChildren().add(enemyHealth.get(0));
		
		
		myScene.setOnKeyPressed(e -> handleKeyInput(e));

		return myScene;
	}

	//go back to the splash homescreen
	private void goToPop () {
		PopUp back = new PopUp();
		Scene scene= back.init(stage, 800, 800,real,1);
		KeyFrame frame = back.start(Constants.NUM_FRAMES_PER_SECOND);
		real.getKeyFrames().add(frame);
		presentState = false;
		stage.setScene(scene);
	}
	
	//go to level two of the game
	private void goToTwo(){
		LevelTwo levelTwo= new LevelTwo();
		Scene scene= levelTwo.init(stage, 800, 800,real);
		KeyFrame frame = levelTwo.start(Constants.NUM_FRAMES_PER_SECOND);
		real.getKeyFrames().add(frame);
		presentState = false;
		stage.setScene(scene);
	}

	//handle keyboard input
	private void handleKeyInput(KeyEvent e){
		KeyCode keyCode = e.getCode();
		if (keyCode == KeyCode.RIGHT) {
			double amount  = myPlayer.getImage().getTranslateX() + Constants.player_speed_one;
			if(amount < sceneWidth-myPlayer.getCenterX()){
				myPlayer.getImage().setTranslateX(amount);
			}
		}
		else if (keyCode == KeyCode.LEFT) {
			double amount = myPlayer.getImage().getTranslateX() - Constants.player_speed_one; 
			if (amount > -myPlayer.getCenterX()) {
				myPlayer.getImage().setTranslateX(amount);
			}
		}
		else if (keyCode == KeyCode.UP) {
			double amount = myPlayer.getImage().getTranslateY() - Constants.player_speed_one;
			if (amount > -myPlayer.getCenterY()){
				myPlayer.getImage().setTranslateY(amount);
			}
		}
		else if (keyCode == KeyCode.DOWN) {
			double amount = myPlayer.getImage().getTranslateY() + Constants.player_speed_one;
			if (amount < sceneHeight-myPlayer.getCenterY()){ 
				myPlayer.getImage().setTranslateY(amount);
			}
		}
		else if (keyCode == KeyCode.SPACE) {
			Shooter bullet = new Shooter(Constants.levelType.level_one,myPlayer.getImage().getTranslateX()+myPlayer.getCenterX()+image_offset,myPlayer.getImage().getTranslateY()+myPlayer.getCenterY());
			playerBullets.add(bullet);
			bullet.setColor();
			bullet.moveOne(Constants.moverType.player);
			myGroup.getChildren().add(bullet.getCarrier());
		} else if (keyCode == KeyCode.F) {
			goToPop();
		} else if (keyCode == KeyCode.P){
			goToTwo();
		}
	}

	//check if any of the enemies hit the player
	private void checkEnemyCollide () {
		for(Enemy enemy : enemies){
			if (myPlayer.getImage().getBoundsInParent().intersects(enemy.getCarrier().getBoundsInParent())) {
				if(!enemy.getBumpTag()){
					myPlayer.reduce_health_one();
					enemy.setBumpTag();
				}
			}
		}
	}

	//generate bullets for each enemy
	public void generateBullet(moverType type, double xcor, double ycor){
		Shooter bullet = new Shooter(Constants.levelType.level_one,xcor,ycor);
		if(type == Constants.moverType.enemy){
			enemyBullets.add(bullet);
		} else {
			playerBullets.add(bullet);
		}
		myGroup.getChildren().add(bullet.getCarrier());
	}

	//generate new enemies for each period of time
	public void generateEnemy(int enemyNum){
		for( int i = 0; i < enemyNum; i++){
			Enemy enemy = new Enemy(Constants.levelType.level_one);
			enemies.add(enemy);
			myGroup.getChildren().add(enemy.getCarrier());
			Shooter bullet = enemy.singleBullet();
			enemyBullets.add(bullet);
			myGroup.getChildren().add(bullet.getCarrier());
		}
	}
	
	//check if a bullet hits a target and thus updates the health and score
	private void checkBulletCollide() {
		for(Enemy enemy: enemies){
			for(Shooter bullet: playerBullets){
				if (enemy.getCarrier().getBoundsInParent().intersects(bullet.getCarrier().getBoundsInParent())) {
					if(!bullet.getBumpTag() && !enemy.getDestroyTag()){
						myPlayer.update_score_one();
						bullet.setBumpTag();
						enemy.setDestroyTag();
						myGroup.getChildren().remove(enemy);
						myGroup.getChildren().remove(bullet);
						toRemovePlayerBullets.add(bullet);
						toRemoveEnemies.add(enemy);					
				//		System.out.println("here");
					}
				}
			}

		}	

		for (Shooter bullet : enemyBullets){
			if (myPlayer.getImage().getBoundsInParent().intersects(bullet.getCarrier().getBoundsInParent())) {
				if(!bullet.getBumpTag()){
					myPlayer.reduce_health_one();
					bullet.setBumpTag();
				}
			}
		}
	}

	//check collision and keep the motion of each object
	public void updateSprites(){
		if(presentState){
			enemyBullets.removeAll(toRemoveEnemyBullets);
			playerBullets.removeAll(toRemovePlayerBullets);
			enemies.removeAll(toRemoveEnemies);

			for(Shooter bullet: toRemoveEnemyBullets){
				myGroup.getChildren().removeAll(bullet.getCarrier());
			}
			for(Shooter bullet: toRemovePlayerBullets){
				myGroup.getChildren().removeAll(bullet.getCarrier());
			}
			for(Enemy enemy: toRemoveEnemies){
				myGroup.getChildren().removeAll(enemy.getCarrier());
			}


			for(Enemy enemy : enemies){
				enemy.moveForward();
				if (counter_level_one%round_time_level_one == 0){
					generateBullet(Constants.moverType.enemy,enemy.getCurrentPosX(),enemy.getCurrentPosY());
				}
				if(enemy.getCarrier().getTranslateX() > sceneHeight){
					toRemoveEnemies.add(enemy);
				}
			}

			for(Shooter bullet : enemyBullets){
				bullet.moveOne(Constants.moverType.enemy);
				if(bullet.getCarrier().getTranslateX() > sceneHeight){
					toRemoveEnemyBullets.add(bullet);
				}
			}

			for(Shooter bullet : playerBullets){
				bullet.moveOne(Constants.moverType.player);
				if(bullet.getCarrier().getTranslateX() > sceneHeight){
					toRemovePlayerBullets.add(bullet);
				}
			}

			if (counter_level_one == round_time_level_one){

				generateEnemy(myGen.nextInt(Constants.level_one_enemy_num));
				counter_level_one = 0;
				round_time_level_one = myGen.nextInt(enemyNumBound-enemyNumOffset)+enemyNumOffset;
			} else{
				counter_level_one++;
			}

			playerHealth.setText(myPlayer.getHealth().toString());
			if(myPlayer.getHealth() <= 200){
				playerHealth.setFill(Color.RED);
			}
			enemyHealth.get(0).setText(String.valueOf(winning_goal-myPlayer.getScore()/10));
			playerScore.setText(myPlayer.getScore().toString());
			checkBulletCollide();
			checkEnemyCollide();

			if (myPlayer.getHealth() <= 0 ){
				goToPop();
			}
			
			if(myPlayer.getScore() >= 100){
				goToTwo();
			}
		}

	}


}
