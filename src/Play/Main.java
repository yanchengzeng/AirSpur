package Play;
import movers.Constants;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.*;

/*The main class serves to start the splash screen and instantiate 
 * animation and timeline objects and set up the stage for the
 * whole game.
 */
public class Main extends Application{
	
	
    private PopUp myPop;
	
	//start the whole and set up the stage
	public void start (Stage s) {
		Timeline animation = new Timeline();
        s.setTitle("AirSpurs");
        myPop = new PopUp();
        Scene scene1 = myPop.init(s, 800, 800,animation,0);  
        s.setScene(scene1);
        s.show();
        KeyFrame frame1 = myPop.start(Constants.NUM_FRAMES_PER_SECOND);
        animation.setCycleCount(Animation.INDEFINITE);
        animation.getKeyFrames().add(frame1);
        animation.play();
    }
	

	public static void main(String[] args) {
		launch(args);
	}
	
	
	

}
