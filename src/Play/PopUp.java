package Play;

import movers.Constants.moverType;
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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.*;

/*This class is the splash window class. It provides user with game menu and serves 
 * as the homescreen of the whole game.
 * Dependencies: Constants
 */
public class PopUp {

	private static final int NUM_FRAMES_PER_SECOND = 60;
	private static final int go_font_size = 60;
	private static final int info_font_size = 30;
	private static final int head_font_size = 60;

	private Scene myScene;
	private Rectangle myRec;
	private Group myGroup;
	private Stage ss;
	private Scene needed;
	private Timeline rr;
	private ImageView myImage;
	private Text prompt;
	private Text head;
	private Text info;
	private Text postMessage;
	private Button startButton;
	private Button skipButton;
	
	//initialize the scene
	public Scene init(Stage s, int width, int height, Timeline real, int visit){
		ss=s;
		rr = real;
		myGroup = new Group();
		startButton = new Button();
		startButton.setText("Go");
		startButton.setTranslateX(360);
		startButton.setTranslateY(680);
		startButton.setOnMouseClicked(e -> handleMouseInput(e));
		startButton.setPrefSize(60, 60);
		myGroup.getChildren().add(startButton);
		
		skipButton = new Button();
		skipButton.setText("skip");
		skipButton.setTranslateX(660);
		skipButton.setTranslateY(680);
		skipButton.setOnMouseClicked(e -> handleMouseInputSkip(e));
		skipButton.setPrefSize(60, 60);
		myGroup.getChildren().add(skipButton);
		
		String headline = "Welcome to AirSpur!";
		Mix.addText(head,head_font_size,headline,120,120,myGroup);

		String howToPlay = specifyInstruction();
		Mix.addText(info,info_font_size,howToPlay,20,200,myGroup);
		String message = "";
		if(visit == 1){
			message = "You Lost... But try again!";
		} else if (visit == 2){
			message = "Congrats! You won!";
		}
		
		Mix.addText(postMessage, 50, message, 40, 550, myGroup);
		myScene = new Scene(myGroup,width,height,Color.WHITE);
		return myScene;

	}
	//input the info displayed at the homescreen
	private String specifyInstruction(){
		String instruction = "In level 1: \n ";
		instruction+="	Press space to shoot and press arrow keys to move\n";
		instruction+="In Level 2: \n";
		instruction+="	Add \"A\", \"W\", \"D\", \"S\" to shoot all directions\n";
		instruction+= "\nTop left: Enemy health (or enemies left)\n";
		instruction+= "Top middle: Player health\nTop right: Player score";
		return instruction;
	}
	//start button
	private void handleMouseInput (MouseEvent e) {
		LevelOne levelOne = new LevelOne();
		Scene scene2= levelOne.init(ss, 800, 800,rr);
		KeyFrame frame2 = levelOne.start(NUM_FRAMES_PER_SECOND);
		rr.getKeyFrames().add(frame2);
		ss.setScene(scene2);
	}
	//exit button
	private void handleMouseInputSkip(MouseEvent e){
		LevelTwo levelTwo= new LevelTwo();
		Scene scene= levelTwo.init(ss, 800, 800,rr);
		KeyFrame frame = levelTwo.start(Constants.NUM_FRAMES_PER_SECOND);
		rr.getKeyFrames().add(frame);
		ss.setScene(scene);
	}


	//start the frame
	public KeyFrame start (int frameRate) {
		return new KeyFrame(Duration.millis(1000 / frameRate), e -> updateSprites1());
	}

	
	private void updateSprites1 () {

	}
}
