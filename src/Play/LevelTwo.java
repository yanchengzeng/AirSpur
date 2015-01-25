package Play;

import javafx.animation.Timeline;
import javafx.stage.Stage;
import movers.Constants.moverType;
import movers.Mover;
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
/*The second level of the game
 * This class is fairly simarly with level one. The methods have close functions and yet 
 * have different implementations.
 */

public class LevelTwo extends Level {

	protected static final int boundary_check_time = 100;
	protected static int round_time_level_one;
	protected static int counter_level_one;
	protected static final int image_offset = 30;
	protected static final int enemyNumBound = 40;
	protected static final int enemyNumOffset = 20;
	protected static final int up = 1;
	protected static final int right = 2;
	protected static final int down = 3;
	protected static final int left =41;

	protected int boundary_checker;
	private ArrayList<Shooter> toRemoveEnemyBullets;
	private ArrayList<Shooter> toRemovePlayerBullets;
	Enemy firstEnemy;
	Enemy secondEnemy;
	Point2D firstVelocity;
	Point2D secondVelocity;
	private boolean presentState;

	public Scene init(Stage s, int width, int height, Timeline time){
		super.init(s, width, height,time);
		boundary_checker = 0;
		counter_level_one = 0;
		round_time_level_one = 100;
		presentState = true;
		myPlayer = new Worrior(Constants.levelType.level_one);
		toRemoveEnemyBullets = new ArrayList<>();
		toRemovePlayerBullets = new ArrayList<>();
		enemyHealth = new ArrayList<>();
		firstEnemy = new Enemy(Constants.levelType.level_two);
		secondEnemy = new Enemy(Constants.levelType.level_two);
		firstVelocity = new Point2D(myGen.nextInt(4) -2, myGen.nextInt(4)-2);
		secondVelocity = new Point2D(myGen.nextInt(4) -2, myGen.nextInt(4) -2);
		enemies = new ArrayList<>();
		enemies.add(firstEnemy);
		enemies.add(secondEnemy);
		myGroup.getChildren().add(firstEnemy.getLauncher());
		myGroup.getChildren().add(secondEnemy.getLauncher());


		myPlayer = new Worrior(Constants.levelType.level_one);
		myGroup.getChildren().add(myPlayer.getImage());
		myScene.setOnKeyPressed(e -> handleKeyInput(e));


		enemyHealth = new ArrayList<>();
		enemyHealth.add(new Text(50,60,firstEnemy.getHealth().toString()));
		enemyHealth.get(0).setFont(new Font(40));
		enemyHealth.add(new Text(150,60,secondEnemy.getHealth().toString()));
		enemyHealth.get(1).setFont(new Font(40));
		

		playerHealth = new Text(myPlayer.getHealthPosX(), myPlayer.getHealthPosY(),myPlayer.getHealth().toString());
		playerHealth.setFont(new Font(40));
		playerScore = new Text(myPlayer.getScorePosX(), myPlayer.getScorePosY(),myPlayer.getScore().toString());
		playerScore.setFont(new Font(40));

		myGroup.getChildren().add(playerHealth);
		myGroup.getChildren().add(playerScore);
		myGroup.getChildren().add(enemyHealth.get(0));
		myGroup.getChildren().add(enemyHealth.get(1));

		return myScene;
	}

	private void goToPop (int result) {
		PopUp back = new PopUp();
		Scene scene= back.init(stage, 800, 800,real,result);
		KeyFrame frame = back.start(Constants.NUM_FRAMES_PER_SECOND);
		real.getKeyFrames().add(frame);
		presentState = false;
		stage.setScene(scene);
	}

	private void handleKeyInput(KeyEvent e){
		KeyCode keyCode = e.getCode();
		if (keyCode == KeyCode.RIGHT) {
			double amount  = myPlayer.getImage().getTranslateX() + Constants.player_speed_two;
			if(amount < sceneWidth-myPlayer.getCenterX()){
				myPlayer.getImage().setTranslateX(amount);
			}
		}
		else if (keyCode == KeyCode.LEFT) {
			double amount = myPlayer.getImage().getTranslateX() - Constants.player_speed_two; 
			if (amount > -myPlayer.getCenterX()) {
				myPlayer.getImage().setTranslateX(amount);
			}
		}
		else if (keyCode == KeyCode.UP) {
			double amount = myPlayer.getImage().getTranslateY() - Constants.player_speed_two;
			if (amount > -myPlayer.getCenterY()){
				myPlayer.getImage().setTranslateY(amount);
			}
		}
		else if (keyCode == KeyCode.DOWN) {
			double amount = myPlayer.getImage().getTranslateY() + Constants.player_speed_two;
			if (amount < sceneHeight-myPlayer.getCenterY()){ 
				myPlayer.getImage().setTranslateY(amount);
			}
		}
		else if (keyCode == KeyCode.SPACE) {
			Shooter bullet = new Shooter(Constants.levelType.level_one,myPlayer.getImage().getTranslateX()+myPlayer.getCenterX()+image_offset,myPlayer.getImage().getTranslateY()+myPlayer.getCenterY());
			playerBullets.add(bullet);
			bullet.setColor();
			bullet.setTag(up);
			bullet.moveOne(Constants.moverType.player);
			myGroup.getChildren().add(bullet.getCarrier());
		} else if (keyCode == KeyCode.F) {
			goToPop(1);
		} else if (keyCode == KeyCode.P){
			goToPop(2);
		} else if (keyCode == KeyCode.A){
			Shooter bullet = new Shooter(Constants.levelType.level_one,myPlayer.getImage().getTranslateX()+myPlayer.getCenterX()+image_offset,myPlayer.getImage().getTranslateY()+myPlayer.getCenterY());
			playerBullets.add(bullet);
			bullet.setColor();
			bullet.setTag(left);
			bullet.moveUp();
			myGroup.getChildren().add(bullet.getCarrier());
		} else if (keyCode == KeyCode.S){
			Shooter bullet = new Shooter(Constants.levelType.level_one,myPlayer.getImage().getTranslateX()+myPlayer.getCenterX()+image_offset,myPlayer.getImage().getTranslateY()+myPlayer.getCenterY());
			playerBullets.add(bullet);
			bullet.setColor();
			bullet.setTag(down);
			bullet.moveDown();
			myGroup.getChildren().add(bullet.getCarrier());
		} else if (keyCode == KeyCode.D){
			Shooter bullet = new Shooter(Constants.levelType.level_one,myPlayer.getImage().getTranslateX()+myPlayer.getCenterX()+image_offset,myPlayer.getImage().getTranslateY()+myPlayer.getCenterY());
			playerBullets.add(bullet);
			bullet.setColor();
			bullet.setTag(right);
			bullet.moveRight();
			myGroup.getChildren().add(bullet.getCarrier());
		} else if (keyCode == KeyCode.W){
			Shooter bullet = new Shooter(Constants.levelType.level_one,myPlayer.getImage().getTranslateX()+myPlayer.getCenterX()+image_offset,myPlayer.getImage().getTranslateY()+myPlayer.getCenterY());
			playerBullets.add(bullet);
			bullet.setColor();
			bullet.setTag(up);
			bullet.moveLeft();
			myGroup.getChildren().add(bullet.getCarrier());
		}
	}

	public void updateSprites(){
		if(presentState){
			if(firstVelocity.getX() == 0 || firstVelocity.getY() == 0){
				firstVelocity = new Point2D(myGen.nextInt(4) -2, myGen.nextInt(4)-2);
			}

			if(secondVelocity.getX() == 0 || secondVelocity.getY() == 0){
				secondVelocity = new Point2D(myGen.nextInt(4) -2, myGen.nextInt(4)-2);
			}

			checkBoundary();
			checkEnemyCollide();
			checkBulletCollide();

			playerHealth.setText(myPlayer.getHealth().toString());
			if(myPlayer.getHealth() <= 100){
				playerHealth.setFill(Color.RED);
			}
			enemyHealth.get(0).setText(firstEnemy.getHealth().toString());
			enemyHealth.get(1).setText(secondEnemy.getHealth().toString());
			playerScore.setText(myPlayer.getScore().toString());
			
			checkBulletCollide();
			checkEnemyCollide();

			if (myPlayer.getHealth() <= 0 ){
				goToPop(1);
			}
			
			if(enemies.get(0).getHealth() <= 0 || enemies.get(1).getHealth() <= 0){
				goToPop(2);
			}
		}
	}

	private void checkEnemyCollide () {
		for(Enemy enemy : enemies){
			if (myPlayer.getImage().getBoundsInParent().intersects(enemy.getLauncher().getBoundsInParent())) {
				myPlayer.reduce_health_one();
			}
		}
	}

	private void checkBulletCollide() {
		for(Enemy enemy: enemies){
			for(Shooter bullet: playerBullets){
				if (enemy.getLauncher().getBoundsInParent().intersects(bullet.getCarrier().getBoundsInParent())) {
					if(!bullet.getBumpTag()){
						myPlayer.update_score_two();
						bullet.setBumpTag();
						enemy.reduce_health();
						myGroup.getChildren().remove(bullet);
						toRemovePlayerBullets.add(bullet);
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

	public void checkBoundary(){

		if(presentState){

			enemyBullets.removeAll(toRemoveEnemyBullets);
			playerBullets.removeAll(toRemovePlayerBullets);

			for(Shooter bullet: toRemoveEnemyBullets){
				myGroup.getChildren().removeAll(bullet.getCarrier());
			}
			for(Shooter bullet: toRemovePlayerBullets){
				myGroup.getChildren().removeAll(bullet.getCarrier());
			}

			if (counter_level_one%round_time_level_one == 0){
				generateBullet();
			}

			for(Shooter bullet : enemyBullets){
				bullet.moveDirection();
				if(bullet.getCarrier().getTranslateX() > sceneHeight){
					toRemoveEnemyBullets.add(bullet);
				}
			}

			for(Shooter bullet : playerBullets){
				bullet.movingSelection();
				if(bullet.getCarrier().getTranslateX() > sceneHeight){
					toRemovePlayerBullets.add(bullet);
				}
			}

			if (counter_level_one == round_time_level_one){
				counter_level_one = 0;
				round_time_level_one = myGen.nextInt(enemyNumBound-enemyNumOffset)+enemyNumOffset;
			} else{
				counter_level_one++;
			}

			firstEnemy.getLauncher().setRotate(firstEnemy.getLauncher().getRotate() + 1);
			firstEnemy.getLauncher().setCenterX(firstEnemy.getLauncher().getCenterX() + firstVelocity.getX());
			firstEnemy.getLauncher().setCenterY(firstEnemy.getLauncher().getCenterY() + firstVelocity.getY());
			if (firstEnemy.getLauncher().getCenterX() >= myScene.getWidth() || firstEnemy.getLauncher().getCenterX() <= 0) {
				firstVelocity = new Point2D(firstVelocity.getX() * -1, firstVelocity.getY());
			}
			if (firstEnemy.getLauncher().getCenterY() >= myScene.getHeight() || firstEnemy.getLauncher().getCenterY() <= 0) {
				firstVelocity = new Point2D(firstVelocity.getX(), firstVelocity.getY() * -1);
			}

			secondEnemy.getLauncher().setRotate(secondEnemy.getLauncher().getRotate() + 1);
			secondEnemy.getLauncher().setCenterX(secondEnemy.getLauncher().getCenterX() + secondVelocity.getX());
			secondEnemy.getLauncher().setCenterY(secondEnemy.getLauncher().getCenterY() + secondVelocity.getY());
			if (secondEnemy.getLauncher().getCenterX() >= myScene.getWidth() || secondEnemy.getLauncher().getCenterX() <= 0) {
				secondVelocity = new Point2D(secondVelocity.getX() * -1, secondVelocity.getY());
			}
			if (secondEnemy.getLauncher().getCenterY() >= myScene.getHeight() || secondEnemy.getLauncher().getCenterY() <= 0) {
				secondVelocity = new Point2D(secondVelocity.getX(), secondVelocity.getY() * -1);
			}
		}
	}

	public void generateBullet(){
		Shooter bullet11 = new Shooter(Constants.levelType.level_two,firstEnemy.getLauncher().getCenterX(),firstEnemy.getLauncher().getCenterY());
		bullet11.setDirection(firstVelocity.getX(), firstVelocity.getY());
		myGroup.getChildren().add(bullet11.getCarrier());
		Shooter bullet12 = new Shooter(Constants.levelType.level_two,firstEnemy.getLauncher().getCenterX(),firstEnemy.getLauncher().getCenterY());
		bullet12.setDirection(-firstVelocity.getX(), firstVelocity.getY());
		myGroup.getChildren().add(bullet12.getCarrier());
		Shooter bullet13 = new Shooter(Constants.levelType.level_two,firstEnemy.getLauncher().getCenterX(),firstEnemy.getLauncher().getCenterY());
		bullet13.setDirection(firstVelocity.getX(), -firstVelocity.getY());
		myGroup.getChildren().add(bullet13.getCarrier());
		Shooter bullet14 = new Shooter(Constants.levelType.level_two,firstEnemy.getLauncher().getCenterX(),firstEnemy.getLauncher().getCenterY());
		bullet14.setDirection(-firstVelocity.getX(), -firstVelocity.getY());
		myGroup.getChildren().add(bullet14.getCarrier());
		enemyBullets.add(bullet11);
		enemyBullets.add(bullet12);
		enemyBullets.add(bullet13);
		enemyBullets.add(bullet14);


		Shooter bullet21 = new Shooter(Constants.levelType.level_two,secondEnemy.getLauncher().getCenterX(),secondEnemy.getLauncher().getCenterY());
		bullet21.setDirection(secondVelocity.getX(), secondVelocity.getY());
		myGroup.getChildren().add(bullet21.getCarrier());
		Shooter bullet22 = new Shooter(Constants.levelType.level_two,secondEnemy.getLauncher().getCenterX(),secondEnemy.getLauncher().getCenterY());
		bullet22.setDirection(-secondVelocity.getX(), secondVelocity.getY());
		myGroup.getChildren().add(bullet22.getCarrier());
		Shooter bullet23 = new Shooter(Constants.levelType.level_two,secondEnemy.getLauncher().getCenterX(),secondEnemy.getLauncher().getCenterY());
		bullet23.setDirection(secondVelocity.getX(), -secondVelocity.getY());
		myGroup.getChildren().add(bullet23.getCarrier());
		Shooter bullet24 = new Shooter(Constants.levelType.level_two,secondEnemy.getLauncher().getCenterX(),secondEnemy.getLauncher().getCenterY());
		bullet24.setDirection(-secondVelocity.getX(), -secondVelocity.getY());
		myGroup.getChildren().add(bullet24.getCarrier());
		enemyBullets.add(bullet21);
		enemyBullets.add(bullet22);
		enemyBullets.add(bullet23);
		enemyBullets.add(bullet24);
	}

}
