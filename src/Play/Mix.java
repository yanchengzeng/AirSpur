package Play;

import movers.Constants;
import movers.Shooter;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

//a class for static methods
public class Mix {
	public static void addText(Text text, int font, String content, double x, double y, Group group){
		text = new Text(x,y,content);
		text.setFont(new Font(font));
		group.getChildren().add(text);
	}
	
}


